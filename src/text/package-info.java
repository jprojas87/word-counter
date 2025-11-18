/**
 * Paquete para análisis y procesamiento de texto.
 * <p>
 * Este paquete contiene clases especializadas en el análisis de archivos
 * de texto, incluyendo conteo de palabras, análisis de frecuencia, y
 * generación de estadísticas textuales.
 * </p>
 *
 * <h2>Clases Principales</h2>
 * <ul>
 *   <li>
 *     {@link text.WordCounter}:
 *     <p>
 *     Analizador de frecuencia de palabras que proporciona:
 *     </p>
 *     <ul>
 *       <li>Conteo total de palabras en un archivo.</li>
 *       <li>Identificación de palabras únicas y repetidas.</li>
 *       <li>Detección de la palabra más frecuente.</li>
 *       <li>Seguimiento de la primera y última palabra del texto.</li>
 *       <li>Agrupación de palabras por letra inicial (A-Z).</li>
 *       <li>Generación de estadísticas detalladas.</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * <h2>Funcionalidades</h2>
 * <p>
 * Las clases de este paquete están diseñadas para:
 * </p>
 * <ul>
 *   <li><strong>Procesar archivos grandes:</strong> Lectura eficiente línea por línea
 *       utilizando {@link java.io.BufferedReader}.</li>
 *   <li><strong>Análisis estadístico:</strong> Generación de métricas sobre el contenido
 *       textual procesado.</li>
 *   <li><strong>Integración con estructuras de datos:</strong> Uso de tablas de símbolos
 *       ordenadas ({@link datastructures.TableSymbolsOrder}) para búsquedas eficientes.</li>
 *   <li><strong>Normalización de texto:</strong> Integración con {@link utils.TextNormalizer}
 *       para preprocesamiento consistente.</li>
 * </ul>
 *
 * <h2>Flujo de Trabajo Típico</h2>
 * <ol>
 *   <li>Crear una instancia de {@link text.WordCounter}.</li>
 *   <li>Procesar un archivo usando {@link text.WordCounter#procesarArchivo(java.nio.file.Path)}.</li>
 *   <li>Mostrar estadísticas con {@link text.WordCounter#mostrarEstadisticas()}.</li>
 * </ol>
 *
 * <h2>Ejemplo de Uso</h2>
 * <pre>{@code
 * // Crear un analizador de palabras
 * WordCounter analizador = new WordCounter();
 *
 * // Procesar un archivo
 * Path ruta = Path.of("books", "moby-dick.txt");
 * analizador.procesarArchivo(ruta);
 *
 * // Mostrar estadísticas
 * analizador.mostrarEstadisticas();
 *
 * // Salida:
 * // ========================================
 * //              RESULTADOS
 * // ========================================
 * // Total de palabras: 215133
 * // Palabras diferentes: 17140
 * // Palabras repetidas: 13553
 * // Primera palabra: the
 * // Ultima palabra: orphan
 * // Palabra más frecuente: the (14508 veces)
 * // ...
 * }</pre>
 *
 * <h2>Dependencias</h2>
 * <p>
 * Este paquete depende de:
 * </p>
 * <ul>
 *   <li>{@link datastructures.TableSymbolsOrder} - Para almacenamiento eficiente de frecuencias.</li>
 *   <li>{@link datastructures.list.List} - Para manejo de colecciones de palabras.</li>
 *   <li>{@link utils.TextNormalizer} - Para normalización y limpieza de texto.</li>
 * </ul>
 *
 * @see text.WordCounter
 * @see datastructures.TableSymbolsOrder
 * @see utils.TextNormalizer
 */
package text;