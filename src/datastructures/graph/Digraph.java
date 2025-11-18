package datastructures.graph;

import datastructures.bag.Bag;

/**
 * Clase que representa un grafo dirigido (dígrafo) usando listas de adyacencia.
 * <p>
 * Cada vértice tiene una "bolsa" (Bag) que guarda los vértices a los que apunta.
 * Por ejemplo, si hay una arista de 1 → 3, el vértice 1 tendrá al 3 en su "bolsa".
 * <p>
 * Esta clase permite crear un grafo, agregar conexiones entre vértices,
 * saber cuántos vértices y conexiones tiene, calcular cuántas conexiones
 * tiene cada vértice y obtener una versión del grafo con las direcciones invertidas
 */
@SuppressWarnings("unchecked")
public class Digraph {
    /**
     * Número de vértices
     */
    private final int V;

    /**
     * Lista de adyacencia: cada vértice tiene un Bag de vértices adyacentes
     */
    private final Bag<Integer>[] adj;
    /**
     * Número de aristas
     */
    private int E;

    /**
     * Crea un nuevo grafo dirigido con un número dado de vértices.
     * Al inicio no hay ninguna arista.
     *
     * @param V número de vértices del grafo
     */
    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    /**
     * Agrega una arista dirigida del vértice <strong>v</strong> hacia el vértice <strong>w</strong>.
     *
     * @param v vértice de origen
     * @param w vértice de destino
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    /**
     * Devuelve el número total de vértices del grafo.
     *
     * @return número de vértices
     */
    public int V() {
        return V;
    }

    /**
     * Devuelve el número total de aristas del grafo.
     *
     * @return número de aristas
     */
    public int edges() {
        return E;
    }

    /**
     * Calcula el grado (número de conexiones salientes) de un vértice.
     *
     * @param G grafo en el que se quiere calcular
     * @param v vértice del cual se desea saber su grado
     * @return cantidad de aristas que salen del vértice v
     */
    public int degree(Digraph G, int v) {
        int degree = 0;
        for (int w : G.adj(v)) {
            degree++;
        }
        return degree;
    }

    /**
     * Encuentra el grado máximo (la cantidad mayor de aristas salientes)
     * entre todos los vértices del grafo.
     *
     * @param G grafo a analizar
     * @return grado máximo
     */
    public int maxDegree(Digraph G) {
        int max = 0;
        for (int v = 0; v < G.V(); v++)
            if (degree(G, v) > max) {
                max = degree(G, v);
            }
        return max;
    }

    /**
     * Calcula el grado promedio del grafo.
     * Se obtiene dividiendo el número total de aristas entre el número de vértices.
     *
     * @param G grafo a analizar
     * @return grado promedio
     */
    public double avgDegree(Digraph G) {
        return (double) G.edges() / G.V();
    }

    /**
     * Cuenta cuántos bucles (loops) hay en el grafo.
     * Un bucle ocurre cuando una arista va de un vértice hacia sí mismo (v → v).
     *
     * @param G grafo a analizar
     * @return número de bucles encontrados
     */
    public int numberOfSelfLoops(Digraph G) {
        int count = 0;
        for (int v = 0; v < G.V(); v++)
            for (int w : G.adj(v)) {
                if (v == w) count++;
            }
        return count;
    }

    /**
     * Devuelve todos los vértices adyacentes (conectados desde v).
     *
     * @param v vértice origen
     * @return lista iterable con los vértices conectados desde v
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /**
     * Devuelve un nuevo grafo con las aristas invertidas.
     * Es decir, si en el grafo actual hay una arista v → w,
     * en el grafo invertido habrá una arista w → v.
     *
     * @return nuevo grafo con las aristas invertidas
     */
    public Digraph reverse() {
        Digraph R = new Digraph(V);
        for (int v = 0; v < V; v++)
            for (int w : adj(v))
                R.addEdge(w, v);
        return R;
    }

    /**
     * Devuelve una representación del grafo en texto.
     * Incluye el número de vértices, aristas y las conexiones de cada vértice.
     *
     * @return cadena con la descripción del grafo
     */
    public String toString() {
        String s = V + " vertices, " + E + " edges\n";
        for (int v = 0; v < V; v++) {
            s += v + ": ";
            for (int w : this.adj(v))
                s += w + " ";
            s += "\n";
        }
        return s;
    }
}
