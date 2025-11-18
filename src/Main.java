import text.WordCounter;

import java.nio.file.Path;
import java.util.Scanner;


/**
 * Aplicación principal para el conteo de palabras en archivos de texto.
 * <p>
 * Esta clase proporciona una interfaz de línea de comandos que permite al usuario
 * seleccionar un libro de una lista predefinida y analizar la frecuencia de palabras
 * en el texto.
 * </p>
 * <p>
 * Los libros disponibles están definidos en un arreglo constante de registros {@link Libro},
 * cada uno con un nombre descriptivo y la ruta al archivo de texto correspondiente.
 * </p>
 */

public class Main {

    private static final Libro[] LIBROS = {
            new Libro("Alice Adventures in Wonderland", Path.of("books", "alice-adventures-in-wonderland.txt")),
            new Libro("Moby Dick", Path.of("books", "moby-dick.txt"))
    };

    /**
     * Punto de entrada de la aplicación.
     * <p>
     * Presenta al usuario un menú con los libros disponibles, solicita la selección
     * de uno de ellos, y procesa el archivo para generar estadísticas de palabras
     * utilizando la clase {@link WordCounter}.
     * </p>
     * <p>
     * Si el usuario ingresa una opción inválida, el programa termina con un mensaje
     * de error.
     * </p>
     *
     * @param args argumentos de la línea de comandos (no se utilizan)
     */

    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("   CONTEO DE PALABRAS DE UN LIBRO");
        System.out.println("========================================\n");

        System.out.println("Libros disponibles:");
        System.out.println("---------------------");
        for (int i = 0; i < LIBROS.length; i++) {
            System.out.printf("%d) %s%n", i + 1, LIBROS[i].nombre());
        }

        System.out.print("\nSeleccione un libro (1-" + LIBROS.length + "): ");

        int opcion = sc.nextInt();
        sc.nextLine();

        if (opcion < 1 || opcion > LIBROS.length) {
            System.out.println("Opción no válida. Finalizando programa.");
            return;
        }

        Libro seleccionado = LIBROS[opcion - 1];

        System.out.println("\nAnalizando: " + seleccionado.nombre());
        System.out.println("Procesando archivo...");

        WordCounter analizador = new WordCounter();
        analizador.procesarArchivo(seleccionado.ruta());
        analizador.mostrarEstadisticas();
    }

    /**
     * Registro que representa un libro con su nombre y ruta al archivo.
     *
     * @param nombre el título del libro
     * @param ruta   la ruta del archivo de texto que contiene el libro
     */

    private record Libro(String nombre, Path ruta) {
    }
}
