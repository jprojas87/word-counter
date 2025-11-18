package utils;

import datastructures.list.List;

import java.text.Normalizer;

public final class TextNormalizer {

    private TextNormalizer() {
    }

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

    private static String limpiarTexto(String linea) {
        return Normalizer.normalize(linea, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .toLowerCase()
                .replaceAll("[^a-zA-Z ]", " ")
                .trim();
    }

}
