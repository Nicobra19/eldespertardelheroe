# Consultas XPath sobre `ranking.xml`

Herramienta: `xmllint` (libxml2), ejecutado desde la carpeta raíz del proyecto.
Archivo de prueba: `ranking.xml` (7 registros ficticios: test1 380, pcc 336, test2 330,
test3 280, test4 230, test5 230 y test6 95). pcc es una partida real jugada con el programa.
Salida completa de la ejecución: `pruebas/evidencias/marcas/M03_xpath_resultados.txt`.

## Consulta 1 · Selección de campos

- **Pregunta:** ¿qué jugadores aparecen en el ranking y en qué orden están guardados?
- **Expresión:** `/ranking/registro/nombre/text()`
- **Comando:** `xmllint --xpath "/ranking/registro/nombre/text()" ranking.xml`
- **Resultado esperado:** test1, pcc, test2, test3, test4, test5, test6 (orden del documento).
- **Resultado obtenido:** test1, pcc, test2, test3, test4, test5, test6. Coincide.

## Consulta 2 · Filtro por puntuación

- **Pregunta:** ¿qué jugadores superan los 250 puntos?
- **Expresión:** `/ranking/registro[number(puntuacion) > 250]/nombre/text()`
- **Resultado esperado:** test1 (380), pcc (336), test2 (330), test3 (280).
- **Resultado obtenido:** test1, pcc, test2, test3. Coincide.
- **Nota:** `number()` convierte el texto a número; sin él la comparación también
  funcionaría en XPath 1.0, pero así queda explícito que se compara como número.

## Consulta 3 · Filtro por jugador

- **Pregunta:** ¿cuántos puntos tiene el jugador test5?
- **Expresión:** `/ranking/registro[nombre = 'test5']/puntuacion/text()`
- **Resultado esperado:** 230.
- **Resultado obtenido:** 230. Coincide.

## Consulta 4 · Combinación de condiciones en un predicado

- **Pregunta:** ¿qué jugadores tienen entre 100 y 299 puntos?
- **Expresión:** `/ranking/registro[number(puntuacion) >= 100 and number(puntuacion) < 300]/nombre/text()`
- **Resultado esperado:** test3 (280), test4 (230), test5 (230). Quedan fuera test1,
  pcc y test2 (≥ 300) y test6 (95 < 100).
- **Resultado obtenido:** test3, test4, test5. Coincide.

## Consulta 5 · Función de recuento

- **Pregunta:** ¿cuántos jugadores están empatados con 230 puntos?
- **Expresión:** `count(/ranking/registro[number(puntuacion) = 230])`
- **Resultado esperado:** 2 (test4 y test5).
- **Resultado obtenido:** 2. Coincide.
