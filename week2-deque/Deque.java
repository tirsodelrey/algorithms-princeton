import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>{
    // construct an empty deque
    private Node first;
    private Node last;
    private int n;

    private class Node {
        Item item;
        Node previous;
        Node next;

    }

    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item is null");
        }
        Node newNode = new Node();

        if(isEmpty()){
            first = last = newNode;
            first.item = item;
            first.previous = null;
            first.next = null;
        }
        else {
            Node oldFirst = first;
            first = newNode;
            first.item = item;
            first.next = oldFirst;
            first.previous = null;
        }
        n++;

    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item is null");
        }
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()){
            first = last;
            last.previous = null;
        }
        else {
            oldLast.next = last;
            last.previous = oldLast;
        }
        n++;

    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("can't remove first because queue is empty");
        }
        //get the item from old first
        Item item = first.item;
        first = first.next;
        if (isEmpty()){
            last = first;
        }
        n--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("can't remove last because queue is empty");
        }
        Item item = last.item;
        last = last.previous;
        last.next = null;
        if(isEmpty()){
            first = last;
        }
        n--;
        return item;

    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current.next == null) {
                throw new NoSuchElementException("there is no next element");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("the remove operation is not supported");
        }
    }

    // unit testing (required)
    public static void main(String[] args){

    }
}
