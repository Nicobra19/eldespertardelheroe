# El despertar del héroe

Aventura por turnos en consola escrita en Java (1.º DAM, curso 2026–2027).
El héroe se enfrenta a tres enemigos seguidos (El Goblin, El Rey Esqueleto y
El Leviatán). Las mejores puntuaciones se guardan en `ranking.xml`, que se valida
con una DTD externa, se consulta con XPath y se publica como página HTML con XSLT y CSS.

Repositorio: <https://github.com/Nicobra19/eldespertardelheroe>

## 1. Requisitos y versiones

| Herramienta | Versión probada | Para qué se usa |
|---|---|---|
| JDK | Eclipse Temurin 25.0.4.1 (equipo del autor) y OpenJDK 21 (pruebas); funciona desde JDK 8 | Compilar (`javac`) y ejecutar (`java`) el juego |
| Visual Studio Code + Extension Pack for Java | la del aula | Editar, compilar, depurar y usar Git integrado |
| Git | 2.x | Control de versiones |
| `xmllint` (libxml2) | 2.9.14 | Validar el XML con la DTD y ejecutar las XPath |
| `xsltproc` (libxslt) | 1.1.39 | Transformar el XML en HTML |
| Extensión XSLT/XPath + Saxon (opcional) | la instalada en VS Code | Transformar desde el IDE |

Instalar `xmllint` y `xsltproc`:

- **Windows:** dentro de WSL `sudo apt install libxml2-utils xsltproc`, o con Chocolatey `choco install xsltproc` (incluye `xmllint`).
- **Linux (Debian/Ubuntu):** `sudo apt install libxml2-utils xsltproc`.
- **macOS:** ya vienen instalados con el sistema.

No hay bibliotecas externas: la lectura y escritura del XML usa las API estándar
de Java (`javax.xml`, DOM).

## 2. Estructura de carpetas

```text
ElDespertarDelHeroe/
├── src/                     Código fuente Java (.java)
│   ├── maingame.java        Clase principal: menú, partida y combate
│   ├── RankingXML.java      Carga, valida, ordena y guarda el ranking (DOM)
│   └── Character, Hero, Goblin, Skeleton, Leviathan, Weapon, LongSword,
│       ShortSword, Potion, NormalPotion, SuperPotion (.java)
├── ranking.xml              Ranking de trabajo (7 registros; pcc es una partida real)
├── ranking.dtd              DTD externa del ranking
├── marcas/
│   └── xpath-consultas.md   Las 5 consultas XPath numeradas
├── web/
│   ├── ranking.xsl          Transformación XSLT a HTML
│   ├── ranking.css          Hoja de estilos externa
│   └── ranking.html         HTML generado con xsltproc
├── pruebas/
│   ├── CASOS.md             Tabla de casos de prueba y resultados
│   ├── xml-errores/         Copias inválidas para la DTD (2 errores distintos)
│   ├── xml-incorrectos/     Copias mal formadas o con datos incorrectos
│   ├── xml-vacio/           Ranking sin registros (con su DTD)
│   ├── xml-modificado/      Copia con un dato cambiado para regenerar el HTML
│   ├── ranking-lleno/       Ranking con 10 registros (con su DTD)
│   └── evidencias/          Salidas reales de consola, validación, XPath y XSLT
├── .vscode/tasks.json       Tarea XSLT de VS Code (extensión XSLT/XPath + Saxon)
├── .gitignore
└── README.md
```

`ranking.xml` y `ranking.dtd` están en la raíz porque el programa busca
`ranking.xml` en la carpeta desde la que se ejecuta y la DTD a su lado.

## 3. Compilar y ejecutar

Siempre desde la carpeta raíz del proyecto (donde está este README).

Windows (PowerShell o CMD):

```text
javac -encoding UTF-8 -d bin src\*.java
java -cp bin maingame
```

Linux / macOS:

```bash
javac -encoding UTF-8 -d bin src/*.java
java -cp bin maingame
```

`javac` traduce cada `.java` a bytecode (`.class`) dentro de `bin/`; `java` arranca
la JVM, que carga `maingame.class` y ejecuta su `main`. La carpeta `bin/` no se
sube a Git (ver `.gitignore`).

En VS Code: *Archivo → Abrir carpeta* → `ElDespertarDelHeroe`, abrir `src/maingame.java`
y pulsar *Run* (o *Debug* para usar puntos de ruptura). Comprueba en
`launch.json` que `"cwd"` es `${workspaceFolder}` para que encuentre `ranking.xml`.

## 4. Reglas del juego

- Menú: 1 Nueva partida · 2 Consultar ranking · 3 Ayuda · 4 Salir.
- El héroe empieza con 100 de vida, 1 super poción (+50) y 2 pociones normales (+25).
  La vida nunca pasa de 100 ni baja de 0.
- Armas: espada larga (15–25 de daño) y espada corta (10–15). El daño se sortea
  con `Math.random()` al empezar cada partida.
- Enemigos: Goblin 40 vida / 10 de daño, Rey Esqueleto 60 / 25, Leviatán 80 / 18.
- En combate: 1 Atacar · 2 Curarse · 3 Ver Stats. Si el enemigo sobrevive a un
  ataque, responde una vez. Curarse y Ver Stats no provocan respuesta.
- Entre combates se descansa: 1 Comenzar · 2 Seguir descansando · 3 Salir (abandonar).
- Puntuación: 100 por enemigo derrotado; al ganar se suma la vida restante.
  Ganar, perder o abandonar envía la puntuación al ranking.

## 5. Ranking y persistencia

- Al arrancar, `RankingXML.cargar()` lee `ranking.xml`, lo valida contra `ranking.dtd`
  y llena dos arrays paralelos (`nombres[10]` y `puntuaciones[10]`) con un contador.
- Si `ranking.xml` no existe, el ranking empieza vacío y se crea al guardar.
- Si el XML está mal formado, no cumple la DTD o tiene datos incorrectos
  (puntuación negativa, nombre vacío, desorden, más de 10 registros), se muestra
  el error y **el archivo no se sobrescribe**.
- Orden de mayor a menor; en un empate queda delante el registro más antiguo.
  Con 10 registros solo entra una puntuación mayor que la última.
- Los nombres se escriben con DOM, que escapa `&` y `<` (`&amp;`, `&lt;`).

**Restablecer una copia de prueba:** las copias están en `pruebas/`. Para probar,
por ejemplo, un ranking vacío sin tocar el de trabajo:

```bash
mkdir ../prueba-vacio
cp pruebas/xml-vacio/ranking.xml pruebas/xml-vacio/ranking.dtd ../prueba-vacio/
cd ../prueba-vacio && java -cp ../ElDespertarDelHeroe/bin maingame
```

Para volver al ranking original del repositorio: `git checkout -- ranking.xml`.

## 6. Validación con la DTD

```bash
xmllint --noout --valid ranking.xml
xmllint --noout --valid pruebas/xml-errores/ranking-falta-puntuacion.xml
xmllint --noout --valid pruebas/xml-errores/ranking-elemento-no-permitido.xml
```

El primero no muestra nada (válido). Los otros dos muestran el error de la regla
que incumplen. Salidas guardadas en `pruebas/evidencias/marcas/M01*` y `M02*`.

## 7. Consultas XPath

Las cinco consultas, con su pregunta y resultado esperado, están en
`marcas/xpath-consultas.md`. Ejemplo:

```bash
xmllint --xpath "count(/ranking/registro[number(puntuacion) = 230])" ranking.xml
```

## 8. Transformación XSLT a HTML

```bash
xsltproc -o web/ranking.html web/ranking.xsl ranking.xml
```

- Entrada: `ranking.xml` + `web/ranking.xsl`. Salida: `web/ranking.html`.
- Abre `web/ranking.html` en el navegador; enlaza `ranking.css` con ruta relativa.
- Para comprobar la regeneración: cambia una puntuación en `ranking.xml`,
  repite el comando y recarga la página.

Alternativa desde VS Code: con la extensión *XSLT/XPath for Visual Studio Code* y
Saxon configurado (`XSLT.tasks.saxonJar`), ejecuta *Terminal → Run Build Task →
ranking with ranking.xml* (definida en `.vscode/tasks.json`) y guarda el resultado
en `web/`. Así se generó `pruebas/evidencias/marcas/M07_tablaranking_vscode_saxon.html`
tras la partida real de «pcc».

## 9. Pruebas

Ver `pruebas/CASOS.md` (casos del apartado 7.2 con resultado esperado y obtenido).

## 10. Autoría y apoyos

- Autor: Nicolas Hyginio (GitHub: Nicobra19), 1.º DAM, XTART Formación Profesional. Profesorado: Jose Luis Lopez y Pedro Campos.
- Juego (`maingame.java` y clases de personajes, armas y pociones): código propio.
- `RankingXML.java`, la DTD y las primeras XPath se hicieron con ayuda de IA
  (commit `7f47472`). La reorganización de carpetas, este README, las pruebas
  documentadas y la memoria se prepararon con ayuda de Claude (Anthropic).
  Ver el apartado «Uso de IA» de la memoria.

Licencia: ver `LICENSE`.
