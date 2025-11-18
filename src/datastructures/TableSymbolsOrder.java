package datastructures;

import datastructures.queue.Queue;

/**
 * Implementación de una tabla de símbolos ORDENADA usando arreglos
 * paralelos para claves y valores. Mantiene las claves siempre ordenadas.
 *
 * @param <Key>   tipo de clave (debe poder compararse)
 * @param <Value> tipo de valor asociado a cada clave
 */
public class TableSymbolsOrder<Key extends Comparable<Key>, Value> {
    /**
     * Tamaño inicial del arreglo donde se almacenan claves y valores.
     */
    private static final int CAPACIDAD_INICIAL = 1000;

    /**
     * Arreglo que guarda todas las claves en orden ascendente.
     */
    private Key[] keys;
    /**
     * Arreglo que guarda los valores asociados a cada clave.
     * El índice coincide con el de la clave correspondiente.
     */
    private Value[] vals;
    /**
     * Cantidad actual de pares clave–valor almacenados.
     */
    private int n;

    /**
     * Crea una tabla de símbolos vacía con una capacidad inicial fija.
     */
    @SuppressWarnings("unchecked")
    public TableSymbolsOrder() {
        keys = (Key[]) new Comparable[CAPACIDAD_INICIAL];
        vals = (Value[]) new Object[CAPACIDAD_INICIAL];
        n = 0;
    }

    /**
     * Devuelve la cantidad de elementos almacenados.
     *
     * @return número de claves guardadas
     */
    public int size() {
        return n;
    }

    /**
     * Cambia el tamaño interno de los arreglos cuando se llenan
     * o cuando es necesario reducir espacio.
     *
     * @param capacidad nuevo tamaño de los arreglos
     */
    @SuppressWarnings("unchecked")
    private void resize(int capacidad) {
        Key[] keys2 = (Key[]) new Comparable[capacidad];
        Value[] vals2 = (Value[]) new Object[capacidad];

        System.arraycopy(keys, 0, keys2, 0, n);
        System.arraycopy(vals, 0, vals2, 0, n);

        keys = keys2;
        vals = vals2;
    }

    /**
     * Realiza una búsqueda binaria para encontrar la posición de una clave.
     * Si la clave existe, devuelve su índice.
     * Si no existe, devuelve el lugar donde debería insertarse.
     *
     * @param key clave buscada
     * @return índice de la clave
     */
    public int rank(Key key) {
        int lo = 0, hi = n - 1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int cmp = key.compareTo(keys[mid]);

            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    /**
     * Inserta una nueva clave con su valor.
     * Si la clave ya existe, reemplaza el valor anterior.
     *
     * @param key clave a insertar o actualizar
     * @param val valor asociado
     */
    public void put(Key key, Value val) {
        int i = rank(key);

        if (i < n && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }

        if (n == keys.length) resize(2 * keys.length);

        for (int j = n; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }

        keys[i] = key;
        vals[i] = val;
        n++;
    }

    /**
     * Obtiene el valor asociado a una clave.
     * Si la clave no existe, devuelve null.
     *
     * @param key clave a buscar
     * @return valor asociado o null si no se encuentra
     */
    public Value get(Key key) {
        if (n == 0) return null;

        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) return vals[i];
        return null;
    }

    /**
     * Devuelve la clave más pequeña almacenada.
     *
     * @return menor clave o null si está vacío
     */
    public Key min() {
        if (n == 0) return null;
        return keys[0];
    }

    /**
     * Devuelve la clave más grande almacenada.
     *
     * @return mayor clave o null si está vacío
     */
    public Key max() {
        if (n == 0) return null;
        return keys[n - 1];
    }

    /**
     * Devuelve la clave que está en la posición indicada.
     *
     * @param k índice dentro de las claves ordenadas
     * @return clave en la posición o null si está fuera de rango
     */
    public Key select(int k) {
        if (k < 0 || k >= n) return null;
        return keys[k];
    }

    /**
     * Devuelve la mayor clave que sea menor o igual que la clave dada.
     *
     * @param key clave usada como referencia
     * @return clave piso o null si no existe una menor o igual
     */
    public Key floor(Key key) {
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) return keys[i];
        if (i == 0) return null;
        return keys[i - 1];
    }

    /**
     * Devuelve la menor clave que sea mayor o igual que la clave dada.
     *
     * @param key clave usada como referencia
     * @return clave techo o null si no existe una mayor o igual
     */
    public Key ceiling(Key key) {
        int i = rank(key);
        if (i == n) return null;
        return keys[i];
    }

    /**
     * Elimina una clave y su valor asociado.
     * Si la clave no existe, no hace nada.
     *
     * @param key clave a eliminar
     */
    public void delete(Key key) {
        if (n == 0) return;

        int i = rank(key);
        if (i >= n || keys[i].compareTo(key) != 0) return;

        for (int j = i; j < n - 1; j++) {
            keys[j] = keys[j + 1];
            vals[j] = vals[j + 1];
        }

        keys[n - 1] = null;
        vals[n - 1] = null;
        n--;

        if (n > 0 && n == keys.length / 4)
            resize(keys.length / 2);
    }

    /**
     * Elimina la clave más pequeña de la tabla.
     * Si está vacía, no hace nada.
     */
    public void deleteMin() {
        if (n == 0) return;
        delete(min());
    }

    /**
     * Elimina la clave más grande de la tabla.
     * Si está vacía, no hace nada.
     */
    public void deleteMax() {
        if (n == 0) return;
        delete(max());
    }

    /**
     * Devuelve todas las claves en orden ascendente.
     *
     * @return Iterable con todas las claves ordenadas
     */
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    /**
     * Devuelve todas las claves comprendidas entre dos valores,
     * incluyendo ambos límites si existen.
     *
     * @param lo clave mínima
     * @param hi clave máxima
     * @return Iterable con las claves dentro del rango dado
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<>();

        if (lo == null || hi == null) return queue;
        if (lo.compareTo(hi) > 0) return queue;

        int start = rank(lo);
        int end = rank(hi);

        for (int i = start; i < n && i <= end; i++) {
            queue.enqueue(keys[i]);
        }

        return queue;
    }
}
