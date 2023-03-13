import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int n;


    // construct an empty randomized queue
    public RandomizedQueue() {
        this.queue = (Item[]) new Object[1];
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    //helper methods
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < n; i++) {
            temp[i] = queue[i];
        }
        queue = temp;
    }

    private void swap(int i) {
        Item last = queue[n - 1];
        queue[n - 1] = queue[i];
        queue[i] = last;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("item is null");
        if (n == queue.length) resize(2 * queue.length);
        queue[n++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("can't remove because queue is empty");
        int index = StdRandom.uniformInt(n);
        if (index != n - 1) {
            swap(index);
        }
        Item item = queue[--n];
        queue[n] = null;
        if (n > 0 && n == queue.length / 4) resize(queue.length / 2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("can't remove first because queue is empty");
        int index = StdRandom.uniformInt(n);
        return queue[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private int[] permIndex = StdRandom.permutation(n);
        private int i = 0;

        public boolean hasNext() {
            return i < n;
        }

        public Item next() {
            if (i >= n) throw new NoSuchElementException("there is no next element");
            return queue[permIndex[i++]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("the remove operation is not supported");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        int size = queue.size();
        System.out.println(size);


        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                queue.enqueue(item);
            } else {
                break;
            }
        }

        Iterator<String> iterator = queue.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("----------------");
        Iterator<String> iterator2 = queue.iterator();

        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }
        System.out.println("----------------");

        queue.dequeue();
        queue.dequeue();

        Iterator<String> iterator3 = queue.iterator();
        while (iterator3.hasNext()) {
            System.out.println(iterator3.next());
        }
    }
}
