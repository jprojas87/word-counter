package datastructures.graph;

import datastructures.bag.Bag;

/**
 * Implementación de un grafo usando lista de adyacencia con Bags.
 * <p>
 * Soporta tanto grafos dirigidos como no dirigidos.
 * Los vértices se representan mediante enteros de 0 a V-1.
 */
public class DirectedGraphs {
    /**
     * Indica si el grafo es dirigido (true) o no dirigido (false)
     */
    private final boolean directed;
    /**
     * Número de vértices en el grafo
     */
    private int vertices;
    /**
     * Número de aristas en el grafo
     */
    private int edges;
    /**
     * Lista de adyacencia: cada vértice tiene un Bag de vértices adyacentes
     */
    private Bag<Integer>[] adj;

    /**
     * Constructor que crea un grafo con el número especificado de vértices.
     *
     * @param vertices número de vértices del grafo
     * @param directed true si el grafo es dirigido, false si es no dirigido
     * @throws IllegalArgumentException si el número de vértices es negativo
     */
    @SuppressWarnings("unchecked")
    public DirectedGraphs(int vertices, boolean directed) {
        if (vertices < 0) {
            throw new IllegalArgumentException("The number of vertices cannot be negative");
        }

        this.vertices = vertices;
        this.edges = 0;
        this.directed = directed;

        this.adj = (Bag<Integer>[]) new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            this.adj[i] = new Bag<>();
        }
    }

    /**
     * Agrega un nuevo vértice al grafo.
     * <p>
     * El nuevo vértice tendrá el índice vertices (antes de incrementar).
     */
    @SuppressWarnings("unchecked")
    public void addVertex() {
        Bag<Integer>[] newAdj = (Bag<Integer>[]) new Bag[vertices + 1];

        System.arraycopy(adj, 0, newAdj, 0, vertices);

        newAdj[vertices] = new Bag<>();

        this.adj = newAdj;
        this.vertices++;
    }

    /**
     * Agrega una arista entre los vértices v y w.
     * <p>
     * En grafos no dirigidos, agrega la arista en ambas direcciones.
     * En grafos dirigidos, agrega la arista solo de v hacia w.
     *
     * @param v vértice origen
     * @param w vértice destino
     * @throws IndexOutOfBoundsException si algún vértice está fuera de rango
     */
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);

        adj[v].add(w);

        if (!directed) {
            adj[w].add(v);
        }

        edges++;
    }

    /**
     * Calcula el grado de un vértice específico.
     * <p>
     * Nota: En grafos dirigidos, esto retorna el grado de salida (out-degree).
     *
     * @param v vértice a consultar
     * @return grado del vértice
     * @throws IllegalArgumentException si el vértice está fuera de rango
     */
    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * Calcula el grado máximo entre todos los vértices del grafo.
     *
     * @return el grado máximo
     */
    public int maxDegree() {
        int max = 0;
        for (int v = 0; v < vertices; v++) {
            int deg = degree(v);
            if (deg > max) {
                max = deg;
            }
        }
        return max;
    }

    /**
     * Calcula el grado promedio de los vértices del grafo.
     * <p>
     * En grafos no dirigidos: 2E / V
     * En grafos dirigidos: E / V
     *
     * @return grado promedio
     */
    public double averageDegree() {
        if (vertices == 0) {
            return 0;
        }

        if (!directed) {
            return 2.0 * edges / vertices;
        }

        return (double) edges / vertices;
    }

    /**
     * Cuenta el número de bucles (self-loops) en el grafo.
     * <p>
     * Un self-loop es una arista que conecta un vértice consigo mismo.
     *
     * @return número de self-loops
     */
    public int numberOfSelfLoops() {
        int count = 0;
        for (int v = 0; v < vertices; v++) {
            for (int w : adj[v]) {
                if (v == w) {
                    count++;
                }
            }
        }

        if (!directed) {
            return count / 2;
        }

        return count;
    }

    /**
     * Retorna el número de aristas en el grafo.
     *
     * @return número de aristas
     */
    public int edges() {
        return edges;
    }

    /**
     * Retorna el número de vértices en el grafo.
     *
     * @return número de vértices
     */
    public int V() {
        return vertices;
    }

    /**
     * Retorna los vértices adyacentes al vértice v.
     *
     * @param v vértice a consultar
     * @return un Iterable con los vértices adyacentes
     * @throws IndexOutOfBoundsException si el vértice está fuera de rango
     */
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * Retorna el grafo inverso (solo para grafos dirigidos).
     * <p>
     * El grafo inverso tiene todas las aristas en dirección contraria.
     *
     * @return nuevo grafo con aristas invertidas
     * @throws UnsupportedOperationException si el grafo no es dirigido
     */
    public DirectedGraphs reverse() {
        if (!directed) {
            throw new UnsupportedOperationException("Only directed graphs can be inverted");
        }

        DirectedGraphs R = new DirectedGraphs(vertices, true);
        for (int v = 0; v < vertices; v++) {
            for (int w : adj(v)) {
                R.addEdge(w, v);
            }
        }
        return R;
    }

    /**
     * Válida que un vértice esté en el rango válido [0, V).
     *
     * @param v vértice a validar
     * @throws IllegalArgumentException si el vértice está fuera de rango
     */
    private void validateVertex(int v) {
        if (v < 0 || v >= vertices) {
            throw new IndexOutOfBoundsException("vertex index out of range");
        }
    }

    /**
     * Retorna una representación en String del grafo.
     *
     * @return String con la información del grafo
     */
    @Override
    public String toString() {
        System.out.println("Tipo: " + (directed ? "Dirigido" : "No dirigido"));
        StringBuilder sb = new StringBuilder();
        for (int v = 0; v < vertices; v++) {
            sb.append(v).append(" -> [");
            boolean first = true;
            for (int w : adj[v]) {
                if (!first) {
                    sb.append(", ");
                }
                sb.append(w);
                first = false;
            }
            sb.append("]\n");
        }
        return sb.toString();
    }

}