package text;

import datastructures.TableSymbolsOrder;
import datastructures.list.List;
import utils.TextNormalizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WordCounter {

    private final TableSymbolsOrder<String, Integer> tabla = new TableSymbolsOrder<>();
    private final int[] conteoIniciales = new int[26];
    private String primeraPalabra;
    private String ultimaPalabra;
    private int totalPalabras;

    public void procesarArchivo(Path ruta) {
        try (BufferedReader br = Files.newBufferedReader(ruta)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                List<String> palabras = TextNormalizer.obtenerPalabras(linea);
                for (String palabra : palabras) {
                    agregarPalabra(palabra);
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo: " + e.getMessage());
        }
    }

    private void agregarPalabra(String palabra) {
        if (palabra == null || palabra.isEmpty()) return;

        totalPalabras++;

        if (primeraPalabra == null) {
            primeraPalabra = palabra;
        }

        ultimaPalabra = palabra;

        tabla.put(palabra, tabla.get(palabra) == null ? 1 : tabla.get(palabra) + 1);

        char c = palabra.charAt(0);
        if (c >= 'a' && c <= 'z') {
            conteoIniciales[c - 'a']++;
        }
    }

    public void mostrarEstadisticas() {
        System.out.println("\n========================================");
        System.out.println("             RESULTADOS");
        System.out.println("========================================");
        System.out.println("Total de palabras: " + totalPalabras);
        System.out.println("Palabras diferentes: " + tabla.size());

        int repetidas = 0, max = 0;
        String masFrecuente = null;

        for (String palabra : tabla.keys()) {
            int f = tabla.get(palabra);
            if (f > 1) repetidas++;
            if (f > max) {
                max = f;
                masFrecuente = palabra;
            }
        }

        System.out.println("Palabras repetidas: " + repetidas);
        System.out.println("Primera palabra: " + primeraPalabra);
        System.out.println("Ultima palabra: " + ultimaPalabra);
        System.out.println("Palabra más frecuente: " + masFrecuente + " (" + max + " veces)");

        System.out.println("\nConteo por letra inicial:");
        for (int i = 0; i < 26; i++)
            if (conteoIniciales[i] > 0) {
                System.out.printf("%c: %d%n", (char) ('A' + i), conteoIniciales[i]);
            }
    }
}
