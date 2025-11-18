package datastructures.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Double<T> implements Iterable<T> {

    private Node first;
    private Node last;
    private int count;

    // Método auxiliar para obtener un nodo en una posición específica
    private Node getNode(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }

        Node current;
        // Optimización: recorrer desde el lado más cercano
        if (index < count / 2) {
            // Recorrer desde el inicio
            current = first;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            // Recorrer desde el final
            current = last;
            for (int i = count - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    // Método auxiliar para obtener el nodo predecesor (corregido)
    private Node getPreviousNode(int index) {
        if (index <= 0 || index > count) {
            return null;
        }
        return getNode(index - 1);
    }

    public boolean add(T item) {
        addLast(item);
        return true;
    }

    public void add(T item, int index) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }

        if (index == count) {
            addLast(item);
        } else if (index == 0) {
            addFirst(item);
        } else {
            Node successor = getNode(index);
            Node predecessor = successor.prev;

            Node newNode = new Node();
            newNode.item = item;
            newNode.prev = predecessor;
            newNode.next = successor;

            predecessor.next = newNode;
            successor.prev = newNode;

            count++;
        }
    }

    public void addFirst(T item) {
        Node newNode = new Node();
        newNode.item = item;

        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
        }
        count++;
    }

    public void addLast(T item) {
        Node newNode = new Node();
        newNode.item = item;

        if (last == null) {
            first = newNode;
        } else {
            newNode.prev = last;
            last.next = newNode;
        }
        last = newNode;
        count++;
    }

    public void clear() {
        // Ayudar al garbage collector liberando referencias
        Node current = first;
        while (current != null) {
            Node next = current.next;
            current.item = null;
            current.next = null;
            current.prev = null;
            current = next;
        }
        first = null;
        last = null;
        count = 0;
    }

    public boolean contains(T item) {
        Node current = first;
        while (current != null) {
            if (Objects.equals(item, current.item)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public T get(int index) {
        return getNode(index).item;
    }

    public T getFirst() {
        if (first == null) {
            throw new NoSuchElementException("La lista está vacía");
        }
        return first.item;
    }

    public T getLast() {
        if (last == null) {
            throw new NoSuchElementException("La lista está vacía");
        }
        return last.item;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean remove(T item) {
        Node current = first;

        while (current != null) {
            if (Objects.equals(item, current.item)) {
                // Elemento encontrado, remover
                if (current.prev == null) {
                    // Es el primer nodo
                    first = current.next;
                } else {
                    current.prev.next = current.next;
                }

                if (current.next == null) {
                    // Es el último nodo
                    last = current.prev;
                } else {
                    current.next.prev = current.prev;
                }

                count--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public T removeFirst() {
        if (first == null) {
            throw new NoSuchElementException("La lista está vacía");
        }

        T item = first.item;
        first = first.next;

        if (first == null) {
            // La lista quedó vacía
            last = null;
        } else {
            first.prev = null;
        }

        count--;
        return item;
    }

    public T removeLast() {
        if (last == null) {
            throw new NoSuchElementException("La lista está vacía");
        }

        T item = last.item;
        last = last.prev;

        if (last == null) {
            // La lista quedó vacía
            first = null;
        } else {
            last.next = null;
        }

        count--;
        return item;
    }

    public int size() {
        return count;
    }

    public int indexOf(T item) {
        Node current = first;
        int index = 0;

        while (current != null) {
            if (Objects.equals(item, current.item)) {
                return index;
            }
            current = current.next;
            index++;
        }

        return -1; // No encontrado
    }

    public T set(int index, T item) {
        Node node = getNode(index);
        T oldItem = node.item;
        node.item = item;
        return oldItem;
    }

    @Override
    public Iterator<T> iterator() {
        return new DoubleLinkedListIterator();
    }

    private class Node {
        T item;
        Node next;
        Node prev;
    }

    private class DoubleLinkedListIterator implements Iterator<T> {
        Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T item = current.item;
            current = current.next;
            return item;
        }
    }
}