# Casos de prueba (apartado 7.2)

Todas las pruebas de juego se ejecutaron sobre **copias** del ranking, nunca sobre
`ranking.xml` de trabajo. Las transcripciones de consola están en
`pruebas/evidencias/juego/` y las de marcas en `pruebas/evidencias/marcas/`.
Entorno: OpenJDK 21.0.13, libxml2 2.9.14 (`xmllint`) y libxslt 1.1.39 (`xsltproc`).

Leyenda: ✔ coincide con lo esperado · ⚠ incidencia (el comportamiento difiere del enunciado y se explica).

## Entradas y combate

| Id | Situación / entrada | Esperado | Obtenido | Evidencia |
|---|---|---|---|---|
| P01a | Menú principal: `abc` | Aviso y nueva lectura, sin cierre | «Introduce un numero valido» y sigue | ✔ `P01_entradas_y_abandono.txt` |
| P01b | Menú principal: `9`; menú de descanso: `7` | «No valido» y se repite el menú | Igual | ✔ mismo archivo |
| P01c | Nombre vacío y nombre de 4 espacios | Se rechaza y se vuelve a pedir | «El nombre no puede estar vacio» dos veces; acepta «Eva Prueba» | ✔ mismo archivo |
| P01d | Abandonar (opción 3 en descanso) con 0 puntos | Termina y se procesa como candidato al ranking | «Puntuacion guardada»; Eva Prueba 0 en el XML | ✔ `P01_ranking_despues.xml` |
| P02 | Partida completa ganando | 3 × 100 + vida restante | Vida final 36 → 336 puntos; insertado en 2.º lugar | ✔ `P02_victoria.txt`, `P02_ranking_despues.xml` |
| P03 | Partida perdiendo (espada corta, sin curarse) | «Has Muerto» y se guarda la puntuación | Muere ante el Rey Esqueleto con 100 puntos; guardado | ✔ `P03_derrota.txt` · ⚠ tras morir el programa se cierra en 5 s en vez de volver al menú |
| P05 | 6 partidas reales, se registran todos los golpes | Larga 15–25, corta 10–15 | Larga 23–25 (21 golpes), corta 10–14 (10 golpes): siempre en rango | ✔ `P05_rango_dano.txt` · ⚠ el daño se sortea una vez por partida, no en cada golpe |
| P06 | Golpe que deja al enemigo a 0 | El enemigo no responde | Tras «0 de vida restante» pasa al siguiente encuentro sin «te golpea» | ✔ `P02_victoria.txt` |

## Inventario

| Id | Situación / entrada | Esperado | Obtenido | Evidencia |
|---|---|---|---|---|
| P07a | Vida 90, super poción (+50) | Vida limitada a 100 | Ver Stats: Vida 100, Super Pociones 0 | ✔ `P07_inventario.txt` |
| P07b | Ver Stats durante el combate | No consume turno ni hay respuesta | Ningún «te golpea» tras consultar | ✔ mismo archivo |
| P07c | Super poción sin existencias | Aviso y no se gasta turno | «No te quedan Super Pociones» | ✔ mismo archivo |
| P07d | Poción normal con la vida a 100 | Según enunciado: no permitirlo | Se gasta la poción (Normales 2 → 1) | ⚠ decisión de diseño del autor; ver memoria §10 |
| P08 | Ganar, «Volver al menu» y nueva partida | Vida, pociones y puntos reiniciados | Vida 100, Super 1, Normales 2, Puntos 0; Goblin 40 | ✔ `P08_reinicio_partida.txt` |

## Ranking y persistencia

| Id | Situación / entrada | Esperado | Obtenido | Evidencia |
|---|---|---|---|---|
| P09a | Sin `ranking.xml` → Consultar ranking | Ranking vacío con mensaje | «Todavia no hay puntuaciones guardadas.» | ✔ `P09a_archivo_ausente.txt` |
| P09b | XML válido sin registros | Igual que P09a | Igual | ✔ `P09b_xml_vacio.txt` |
| P10a | Ranking lleno (10 registros, último 0) y partida de 0 puntos | No entra (no supera al último); XML intacto | «La puntuacion no entra en el top 10.»; sha256 igual antes y después | ✔ `P10a_lleno_descarte.txt` |
| P10b | Dos partidas de 0 puntos seguidas | Empate: el más antiguo primero | 7.º Empate Antiguo, 8.º Empate Nuevo (también tras reiniciar) | ✔ `P10b_*.txt`, `P10b_ranking_despues.xml` |
| P11 | Reabrir el juego tras P02 | Se conservan registros y orden | Victor Prueba 336 en 2.º lugar; test4/test5 en su orden | ✔ `P11_persistencia_reinicio.txt` |
| P12a | `ranking.xml` mal formado (sin `</ranking>`) | Avisar y no sobrescribir | Mensaje de error; ranking no disponible; sha256 igual | ✔ `P12a_mal_formado.txt` · ⚠ al abandonar no se avisa de que la puntuación no se guarda |
| P12b | Puntuación `-5` (válida para la DTD) | Java lo rechaza; no sobrescribe | «Las puntuaciones no pueden ser negativas.»; sha256 igual | ✔ `P12b_puntuacion_negativa.txt` |
| P14 | Partida real «pcc» jugada por el autor en su equipo | Victoria con vida restante sumada; 2.º puesto entre 380 y 330 | 336 puntos, insertado tras test1 y antes de test2; añadido al ranking de entrega | ✔ `P14_partida_pcc_ranking.xml` |
| P13 | Nombre `Ana & <Bob>` | Se escapa en el XML y se recarga bien | XML: `Ana &amp; &lt;Bob&gt;`; consola tras reiniciar: `Ana & <Bob>` | ✔ `P13_*.txt`, `P13_ranking_despues.xml` |

## XML, XPath y publicación

| Id | Situación / entrada | Esperado | Obtenido | Evidencia |
|---|---|---|---|---|
| M01 | `xmllint --noout --valid ranking.xml` | Sin mensajes, salida 0 | Sin mensajes, salida 0 | ✔ `M01_validacion_correcta.txt` |
| M02a | Registro sin `puntuacion` | Error de contenido de `registro` | «expecting (nombre , puntuacion), got (nombre )» | ✔ `M02_error_falta-puntuacion.txt` |
| M02b | Elemento `nivel` añadido | Elemento no declarado | «No declaration for element nivel» | ✔ `M02_error_elemento-no-permitido.txt` |
| M03 | Cinco consultas XPath | Ver `marcas/xpath-consultas.md` | Las cinco coinciden | ✔ `M03_xpath_resultados.txt` |
| M04 | `xsltproc` sobre `ranking.xml` | Tabla ordenada numéricamente; pcc 2.º; 95 al final; test4 antes que test5 | Igual (7 filas) | ✔ `web/ranking.html` |
| M07 | XSLT desde VS Code (Saxon) tras la partida de pcc | Misma tabla que xsltproc para esos datos | pcc 2.º con 336; 6 filas en orden | ✔ `M07_tablaranking_vscode_saxon.html` |
| M05 | XSLT sobre XML vacío | Mensaje claro | «Todavía no hay puntuaciones guardadas.» | ✔ `M05_ranking_vacio.html` |
| M06 | test6 pasa de 95 a 400 y se regenera | test6 sube al 1.º puesto | `diff` muestra test6 400 en 1.º y el resto desplazado | ✔ `M06_diff_regeneracion.txt` |
| E01 | `.gitignore` con `bin/`, `src/Hero.class` y `.vscode/settings.json` creados | Ignorados; fuentes, XML y `.vscode/tasks.json` no | `!! bin/…`, `!! src/Hero.class`, `!! .vscode/settings.json`; tasks.json no ignorado | ✔ `entorno_gitignore.txt` |
| E02 | Copiar el proyecto a otra carpeta y seguir el README | Compila, ejecuta, valida, consulta y transforma | Ranking de 7 registros; HTML con 7 filas + cabecera | ✔ `reproduccion_otra_carpeta.txt` |
