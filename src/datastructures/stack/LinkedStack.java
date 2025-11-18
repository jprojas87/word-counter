package datastructures.stack;

import java.util.Iterator;

public class LinkedStack<Item> implements Iterable<Item> {
    Node first;
    int count;

    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        count++;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        count--;
        return item;
    }

    public Item peek() {
        return first.item;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        // count == 0;
        return first == null;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedStackIterator();
    }

    private class Node {
        Item item;
        Node next;
    }

    private class LinkedStackIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
