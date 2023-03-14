import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        //int k = 6;
        RandomizedQueue<String> rq = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            rq.enqueue(item);
        }

        Iterator<String> it = rq.iterator();
        int count = 0;
        while (it.hasNext() && count < k) {
            System.out.println(it.next());
            count++;
        }
    }
}
