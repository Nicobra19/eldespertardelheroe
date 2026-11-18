<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/">
        <html lang="es">
            <head>
                <meta charset="UTF-8"/>
                <title>Ranking - El despertar del héroe</title>
                <link rel="stylesheet" href="ranking.css"/>
            </head>
            <body>
                <main>
                    <h1>El despertar del héroe</h1>
                    <h2>Clasificación de aventureros</h2>
                    <xsl:choose>
                        <xsl:when test="ranking/registro">
                            <table>
                                <thead>
                                    <tr>
                                        <th>Posición</th>
                                        <th>Jugador</th>
                                        <th>Puntuación</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <xsl:for-each select="ranking/registro">
                                        <xsl:sort select="puntuacion" data-type="number" order="descending"/>
                                        <tr>
                                            <td><xsl:value-of select="position()"/>.</td>
                                            <td><xsl:value-of select="nombre"/></td>
                                            <td><xsl:value-of select="puntuacion"/></td>
                                        </tr>
                                    </xsl:for-each>
                                </tbody>
                            </table>
                        </xsl:when>
                        <xsl:otherwise>
                            <p class="vacio">Todavía no hay puntuaciones guardadas.</p>
                        </xsl:otherwise>
                    </xsl:choose>
                </main>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
