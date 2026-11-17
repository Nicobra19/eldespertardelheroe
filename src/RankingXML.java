import java.io.IOException;
import java.net.URI;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public final class RankingXML {
    private static final int MAX_REGISTROS = 10;
    private static final String RAIZ = "ranking";

    private final Path archivo;
    private final String[] nombres = new String[MAX_REGISTROS];
    private final int[] puntuaciones = new int[MAX_REGISTROS];
    private int cantidad;
    private boolean errorLectura;

    public RankingXML(String rutaArchivo) {
        archivo = Paths.get(rutaArchivo);
    }

    public void cargar() {
        if (Files.notExists(archivo)) {
            return;
        }

        String[] nombresLeidos = new String[MAX_REGISTROS];
        int[] puntuacionesLeidas = new int[MAX_REGISTROS];
        int cantidadLeida = 0;

        try {
            Path dtdLocal = archivo.resolveSibling("ranking.dtd").toAbsolutePath().normalize();
            DocumentBuilder constructor = crearConstructor(dtdLocal);
            Document documento = constructor.parse(archivo.toFile());
            Element raiz = documento.getDocumentElement();
            if (raiz == null || !RAIZ.equals(raiz.getTagName())) {
                throw new IllegalArgumentException("La raiz XML debe ser <ranking>.");
            }

            for (Node nodo = raiz.getFirstChild(); nodo != null; nodo = nodo.getNextSibling()) {
                if (nodo.getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                if (!"registro".equals(nodo.getNodeName())) {
                    throw new IllegalArgumentException("Elemento no permitido dentro de <ranking>.");
                }
                if (cantidadLeida == MAX_REGISTROS) {
                    throw new IllegalArgumentException("El ranking no puede superar diez registros.");
                }

                Element registro = (Element) nodo;
                String nombre = leerCampo(registro, "nombre");
                String textoPuntuacion = leerCampo(registro, "puntuacion");
                if (nombre.trim().isEmpty()) {
                    throw new IllegalArgumentException("El nombre del jugador no puede estar vacio.");
                }

                int puntuacion = Integer.parseInt(textoPuntuacion.trim());
                if (puntuacion < 0) {
                    throw new IllegalArgumentException("Las puntuaciones no pueden ser negativas.");
                }
                if (cantidadLeida > 0 && puntuacion > puntuacionesLeidas[cantidadLeida - 1]) {
                    throw new IllegalArgumentException("Los registros deben estar ordenados por puntuacion.");
                }

                nombresLeidos[cantidadLeida] = nombre;
                puntuacionesLeidas[cantidadLeida] = puntuacion;
                cantidadLeida++;
            }

            System.arraycopy(nombresLeidos, 0, nombres, 0, cantidadLeida);
            System.arraycopy(puntuacionesLeidas, 0, puntuaciones, 0, cantidadLeida);
            cantidad = cantidadLeida;
        } catch (ParserConfigurationException | SAXException | IOException | IllegalArgumentException e) {
            errorLectura = true;
            System.err.println("No se pudo leer " + archivo + ": " + e.getMessage());
            System.err.println("El archivo se conserva sin cambios; corrige el XML para volver a usar el ranking.");
        }
    }

    public void mostrarRanking() {
        if (errorLectura) {
            System.out.println("Ranking no disponible: corrige ranking.xml antes de volver a consultarlo.");
            return;
        }
        if (cantidad == 0) {
            System.out.println("Todavia no hay puntuaciones guardadas.");
            return;
        }

        System.out.println("---------------- RANKING ----------------");
        for (int i = 0; i < cantidad; i++) {
            System.out.println((i + 1) + ". " + nombres[i] + " - " + puntuaciones[i] + " puntos");
        }
        System.out.println("-----------------------------------------");
    }

    public boolean registrarPuntuacion(String nombre, int puntuacion)
            throws IOException, ParserConfigurationException, TransformerException {
        if (errorLectura) {
            return false;
        }
        if (nombre == null || nombre.trim().isEmpty() || puntuacion < 0) {
            throw new IllegalArgumentException("El nombre y la puntuacion deben ser validos.");
        }

        int posicion = 0;
        while (posicion < cantidad && puntuaciones[posicion] >= puntuacion) {
            posicion++;
        }
        if (cantidad == MAX_REGISTROS && posicion == MAX_REGISTROS) {
            return false;
        }

        int ultimaPosicion = Math.min(cantidad, MAX_REGISTROS - 1);
        for (int i = ultimaPosicion; i > posicion; i--) {
            nombres[i] = nombres[i - 1];
            puntuaciones[i] = puntuaciones[i - 1];
        }
        nombres[posicion] = nombre.trim();
        puntuaciones[posicion] = puntuacion;
        if (cantidad < MAX_REGISTROS) {
            cantidad++;
        }

        guardar();
        return true;
    }

    public boolean estaDisponible() {
        return !errorLectura;
    }

    private void guardar() throws IOException, ParserConfigurationException, TransformerException {
        Path temporal = archivo.resolveSibling(archivo.getFileName() + ".tmp");
        Document documento = crearDocumento();
        TransformerFactory fabricaTransformadores = TransformerFactory.newInstance();
        fabricaTransformadores.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        fabricaTransformadores.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        fabricaTransformadores.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
        Transformer transformador = fabricaTransformadores.newTransformer();
        transformador.setOutputProperty("encoding", "UTF-8");
        transformador.setOutputProperty("indent", "yes");
        transformador.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "ranking.dtd");
        transformador.transform(new DOMSource(documento), new StreamResult(temporal.toFile()));

        try {
            Files.move(temporal, archivo, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        } catch (AtomicMoveNotSupportedException e) {
            Files.move(temporal, archivo, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    private Document crearDocumento() throws ParserConfigurationException {
        DocumentBuilder constructor = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        DocumentType tipoDocumento = constructor.getDOMImplementation()
                .createDocumentType(RAIZ, null, "ranking.dtd");
        Document documento = constructor.getDOMImplementation().createDocument(null, RAIZ, tipoDocumento);
        documento.insertBefore(
                documento.createProcessingInstruction("xml-stylesheet",
                        "type=\"text/xsl\" href=\"ranking.xsl\""),
                documento.getDocumentElement());

        for (int i = 0; i < cantidad; i++) {
            Element registro = documento.createElement("registro");
            Element nombre = documento.createElement("nombre");
            nombre.setTextContent(nombres[i]);
            Element puntuacion = documento.createElement("puntuacion");
            puntuacion.setTextContent(Integer.toString(puntuaciones[i]));
            registro.appendChild(nombre);
            registro.appendChild(puntuacion);
            documento.getDocumentElement().appendChild(registro);
        }
        return documento;
    }

    private static String leerCampo(Element registro, String nombreCampo) {
        Node campo = null;
        String[] camposEsperados = {"nombre", "puntuacion"};
        int indiceCampo = 0;
        for (Node nodo = registro.getFirstChild(); nodo != null; nodo = nodo.getNextSibling()) {
            if (nodo.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if (indiceCampo == camposEsperados.length
                    || !camposEsperados[indiceCampo].equals(nodo.getNodeName())) {
                throw new IllegalArgumentException("Campo no permitido dentro de <registro>.");
            }
            if (nombreCampo.equals(nodo.getNodeName())) {
                campo = nodo;
            }
            indiceCampo++;
        }
        if (indiceCampo != camposEsperados.length || campo == null
                || campo.hasChildNodes() && tieneElementoHijo(campo)) {
            throw new IllegalArgumentException("Cada <registro> debe contener nombre y puntuacion en orden.");
        }
        return campo.getTextContent();
    }

    private static boolean tieneElementoHijo(Node nodo) {
        for (Node hijo = nodo.getFirstChild(); hijo != null; hijo = hijo.getNextSibling()) {
            if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                return true;
            }
        }
        return false;
    }

    private static DocumentBuilder crearConstructor(Path dtdLocal) throws ParserConfigurationException {
        DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
        fabrica.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        fabrica.setValidating(true);
        fabrica.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", true);
        fabrica.setFeature("http://xml.org/sax/features/external-general-entities", false);
        fabrica.setFeature("http://xml.org/sax/features/external-parameter-entities", true);
        fabrica.setXIncludeAware(false);
        fabrica.setExpandEntityReferences(false);
        fabrica.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        fabrica.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

        DocumentBuilder constructor = fabrica.newDocumentBuilder();
        constructor.setEntityResolver((publicId, systemId) -> {
            if (systemId == null) {
                throw new SAXException("El XML debe declarar el DTD local ranking.dtd.");
            }

            URI uri = URI.create(systemId);
            if (!uri.isAbsolute() || !"file".equalsIgnoreCase(uri.getScheme())) {
                throw new SAXException("Solo se permite cargar el DTD local ranking.dtd.");
            }

            Path solicitado = Paths.get(uri).toRealPath();
            Path permitido = dtdLocal.toRealPath();
            if (!solicitado.equals(permitido)) {
                throw new SAXException("El XML referencia un DTD distinto de ranking.dtd.");
            }

            InputSource origen = new InputSource(Files.newInputStream(permitido));
            origen.setSystemId(permitido.toUri().toString());
            return origen;
        });
        constructor.setErrorHandler(new ErrorHandler() {
            @Override
            public void warning(SAXParseException e) throws SAXException {
                throw e;
            }

            @Override
            public void error(SAXParseException e) throws SAXException {
                throw e;
            }

            @Override
            public void fatalError(SAXParseException e) throws SAXException {
                throw e;
            }
        });
        return constructor;
    }
}
