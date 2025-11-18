/**
 * Paquete de utilidades para procesamiento y normalización de texto.
 * <p>
 * Este paquete proporciona herramientas estáticas para limpiar, normalizar
 * y extraer información de cadenas de texto. Las utilidades están diseñadas
 * para facilitar el preprocesamiento de texto antes de realizar análisis
 * lingüísticos o estadísticos.
 * </p>
 *
 * <h2>Clases Principales</h2>
 * <ul>
 *   <li>
 *     {@link utils.TextNormalizer}:
 *     <p>
 *     Clase utilitaria que ofrece métodos para:
 *     </p>
 *     <ul>
 *       <li>Normalizar texto eliminando acentos y caracteres especiales.</li>
 *       <li>Convertir texto a minúsculas.</li>
 *       <li>Extraer palabras individuales de una línea de texto.</li>
 *       <li>Limpiar espacios en blanco redundantes.</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * <h2>Características</h2>
 * <ul>
 *   <li><strong>Métodos estáticos:</strong> Todas las utilidades son accesibles
 *       sin necesidad de instanciar objetos.</li>
 *   <li><strong>Normalización Unicode:</strong> Convierte caracteres acentuados
 *       a su forma base utilizando normalización NFD.</li>
 *   <li><strong>Limpieza robusta:</strong> Elimina caracteres no alfabéticos
 *       y normaliza espacios en blanco.</li>
 *   <li><strong>Integración con estructuras de datos:</strong> Retorna resultados
 *       en estructuras personalizadas como {@link datastructures.list.List}.</li>
 * </ul>
 *
 * <h2>Casos de Uso</h2>
 * <p>
 * Este paquete es especialmente útil para:
 * </p>
 * <ul>
 *   <li>Preprocesamiento de texto para análisis de frecuencia de palabras.</li>
 *   <li>Normalización de entrada de usuario.</li>
 *   <li>Limpieza de datos textuales antes de almacenarlos o compararlos.</li>
 *   <li>Tokenización básica de texto en palabras individuales.</li>
 * </ul>
 *
 * <h2>Ejemplo de Uso</h2>
 * <pre>{@code
 * // Obtener palabras limpias de una línea de texto
 * String linea = "¡Hola, mundo! ¿Cómo estás?";
 * List<String> palabras = TextNormalizer.obtenerPalabras(linea);
 *
 * // Resultado: ["hola", "mundo", "como", "estas"]
 * for (String palabra : palabras) {
 *     System.out.println(palabra);
 * }
 * }</pre>
 *
 * @see utils.TextNormalizer
 * @see datastructures.list.List
 */
package utils;