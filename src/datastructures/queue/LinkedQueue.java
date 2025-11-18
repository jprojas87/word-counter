package datastructures.queue;

import java.util.Iterator;

public class LinkedQueue<T> implements Iterable<T> {
    private Node first;
    private Node last;
    private int count;

    public void enqueue(T item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;

        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }

        count++;
    }

    public T dequeue() {
        T item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        count--;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return count;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedQueueIterator();
    }

    private class Node {
        T item;
        Node next;
    }

    private class LinkedQueueIterator implements Iterator<T> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T item = current.item;
            current = current.next;
            return item;
        }
    }
}
