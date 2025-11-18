package text;

import datastructures.TableSymbolsOrder;
import datastructures.list.List;
import utils.TextNormalizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Analizador de frecuencia de palabras en archivos de texto.
 * <p>
 * Esta clase procesa archivos de texto para contar la frecuencia de aparición
 * de cada palabra, realizar un seguimiento de las palabras por letra inicial,
 * e identificar estadísticas como la palabra más frecuente, la primera y última
 * palabra del texto.
 * </p>
 * <p>
 * Utiliza una tabla de símbolos ordenada ({@link TableSymbolsOrder}) para
 * mantener el conteo de frecuencias de forma eficiente.
 * </p>
 */

public class WordCounter {

    /**
     * Tabla de símbolos que asocia cada palabra con su frecuencia de aparición.
     */
    private final TableSymbolsOrder<String, Integer> tabla = new TableSymbolsOrder<>();

    /**
     * Arreglo que cuenta las palabras según su letra inicial (a-z).
     * El índice 0 corresponde a 'a', el índice 1 a 'b', y así sucesivamente.
     */
    private final int[] conteoIniciales = new int[26];

    /**
     * La primera palabra encontrada en el texto analizado.
     */
    private String primeraPalabra;

    /**
     * La última palabra encontrada en el texto analizado.
     */
    private String ultimaPalabra;

    /**
     * Contador del número total de palabras procesadas (incluyendo repeticiones).
     */
    private int totalPalabras;

    /**
     * Lee y procesa un archivo de texto línea por línea.
     * <p>
     * Este método abre el archivo especificado, lee cada línea, extrae las palabras
     * utilizando {@link TextNormalizer#obtenerPalabras(String)}, y las procesa
     * mediante el método {@link #agregarPalabra(String)}.
     * </p>
     * <p>
     * Si ocurre un error de E/S durante la lectura del archivo, se imprime un mensaje
     * de error en la consola.
     * </p>
     *
     * @param ruta la ruta del archivo de texto a procesar
     */

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

    /**
     * Registra una palabra en el análisis de frecuencia.
     * <p>
     * Este método realiza las siguientes operaciones:
     * <ul>
     *   <li>Ignora palabras nulas o vacías.</li>
     *   <li>Incrementa el contador total de palabras.</li>
     *   <li>Registra la primera palabra si aún no se ha establecido.</li>
     *   <li>Actualiza la última palabra procesada.</li>
     *   <li>Actualiza la frecuencia de la palabra en la tabla de símbolos.</li>
     *   <li>Incrementa el contador de la letra inicial correspondiente.</li>
     * </ul>
     * </p>
     *
     * @param palabra la palabra a registrar en el análisis
     */

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

    /**
     * Muestra en consola un resumen completo de las estadísticas del análisis.
     * <p>
     * Las estadísticas incluyen:
     * <ul>
     *   <li>Número total de palabras procesadas.</li>
     *   <li>Número de palabras diferentes (únicas).</li>
     *   <li>Número de palabras que aparecen más de una vez.</li>
     *   <li>Primera y última palabra del texto.</li>
     *   <li>Palabra más frecuente y su número de apariciones.</li>
     *   <li>Conteo de palabras agrupadas por letra inicial (A-Z).</li>
     * </ul>
     * </p>
     */

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
