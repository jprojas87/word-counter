import text.WordCounter;

import java.nio.file.Path;
import java.util.Scanner;

// 99
public class Main {

    private static final Libro[] LIBROS = {
            new Libro("Alice Adventures in Wonderland", Path.of("books", "alice-adventures-in-wonderland.txt")),
            new Libro("Moby Dick", Path.of("books", "moby-dick.txt"))
    };

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

    private record Libro(String nombre, Path ruta) {
    }
}
