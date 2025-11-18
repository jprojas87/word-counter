package utils;

import datastructures.list.List;

import java.text.Normalizer;

/**
 * Clase utilitaria para normalizar y procesar texto.
 * <p>
 * Proporciona métodos estáticos para limpiar cadenas de texto, eliminar
 * caracteres especiales, normalizar acentos y extraer palabras individuales
 * de una línea de texto.
 * </p>
 * <p>
 * Esta clase no puede ser instanciada ya que todos sus métodos son estáticos
 * y su constructor es privado.
 * </p>
 */

public final class TextNormalizer {

    /**
     * Constructor privado para evitar la instanciación de esta clase utilitaria.
     */

    private TextNormalizer() {
    }

    /**
     * Extrae y normaliza todas las palabras de una línea de texto.
     * <p>
     * Este método realiza los siguientes pasos:
     * <ol>
     *   <li>Verifica si la línea es nula o está vacía, retornando una lista vacía en ese caso.</li>
     *   <li>Limpia el texto eliminando caracteres especiales y normalizando el formato.</li>
     *   <li>Divide el texto limpio en palabras individuales usando espacios como delimitadores.</li>
     *   <li>Agrega cada palabra a una lista y la retorna.</li>
     * </ol>
     * </p>
     *
     * @param linea la línea de texto a procesar
     * @return una lista con las palabras normalizadas encontradas en la línea,
     *         o una lista vacía si la línea es nula, está vacía o no contiene palabras válidas
     */

    public static List<String> obtenerPalabras(String linea) {

        if (linea == null || linea.isBlank()) {
            return new List<>();
        }

        String limpio = limpiarTexto(linea);

        if (limpio.isEmpty()) {
            return new List<>();
        }

        String[] partes = limpio.split("\\s+");
        var resultado = new List<String>();

        for (String palabra : partes) {
            resultado.addLast(palabra);
        }

        return resultado;
    }

    /**
     * Limpia y normaliza una cadena de texto.
     * <p>
     * Este método realiza las siguientes transformaciones:
     * <ul>
     *   <li>Normaliza el texto a formato NFD (Normalization Form Canonical Decomposition).</li>
     *   <li>Elimina todos los caracteres no ASCII (acentos, símbolos especiales, etc.).</li>
     *   <li>Convierte todo el texto a minúsculas.</li>
     *   <li>Reemplaza cualquier carácter que no sea una letra o espacio por un espacio.</li>
     *   <li>Elimina espacios en blanco al inicio y final de la cadena.</li>
     * </ul>
     * </p>
     *
     * @param linea la cadena de texto a limpiar
     * @return la cadena de texto limpia y normalizada, conteniendo solo letras minúsculas
     *         y espacios simples entre palabras
     */

    private static String limpiarTexto(String linea) {
        return Normalizer.normalize(linea, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .toLowerCase()
                .replaceAll("[^a-zA-Z ]", " ")
                .trim();
    }
}
