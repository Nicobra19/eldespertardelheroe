# El despertar del héroe

Aventura por turnos de consola escrita en Java. Las puntuaciones se guardan en
`ranking.xml`, que también puede abrirse en un navegador para ver la tabla HTML.

## Requisitos

- JDK 8 o posterior.
- No se necesitan dependencias externas.

## Compilar y ejecutar

Desde la carpeta del proyecto:

```text
javac -encoding UTF-8 -d bin src\*.java
java -cp bin maingame
```

Ejecuta el programa desde esta carpeta para que encuentre `ranking.xml`,
`ranking.dtd` y `ranking.xsl`. Si el XML no existe, el programa comienza con un
ranking vacio y lo crea cuando se registra una puntuacion que entra en el top 10.
Las puntuaciones se ordenan de mayor a menor; en un empate se conserva delante
el registro que ya estaba guardado.

## Ranking y documentos XML

- `ranking.xml`: datos de ejemplo y archivo persistente de las mejores diez
  puntuaciones. El nombre del jugador se escribe mediante DOM para escapar
  correctamente caracteres como `&` y `<`.
- `ranking.dtd`: define la estructura y el orden de los elementos del ranking.
- `ranking.xsl` y `ranking.css`: transforman y presentan la clasificacion como
  una pagina HTML.
- `xpath-consultas.txt`: cinco consultas XPath y sus resultados sobre el XML
  inicial.
- `xml-pruebas`: ejemplos separados con errores estructurales para probar la
  validacion sin modificar el XML de trabajo.

El programa valida cada lectura contra el DTD local `ranking.dtd` y comprueba
tambien las reglas de puntuaciones y orden que la DTD no puede expresar. Solo
permite resolver ese archivo DTD local; no carga DTD ni entidades externas. Si
el XML existente no supera la validacion, informa del problema y no lo sustituye.
Los dos XML de `xml-pruebas` sirven para comprobar que el programa rechaza errores
DTD distintos sin modificar el archivo de trabajo.

La memoria del proyecto se encuentra en `memoria.pdf`.

El juego original fue creado por Nicobra19. Se utilizo asistencia de IA para
implementar y documentar la persistencia XML y para revisar su integracion.

<img width="360" height="360" src="https://github.com/user-attachments/assets/333ceafc-d6ad-4912-ac6a-6214a8ea29ca"/>
<img width="360" height="360" alt="Captura del juego" src="https://github.com/user-attachments/assets/22924b5b-14b4-4d02-a3a5-d7825b2e8369" />