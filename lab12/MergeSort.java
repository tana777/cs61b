import edu.princeton.cs.algs4.Queue;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        Queue<Queue<Item>> queueitems = new Queue<>();
        while(!items.isEmpty()) {
            Queue<Item> tmp = new Queue<>();
            tmp.enqueue(items.dequeue());
            queueitems.enqueue(tmp);
        }
        return queueitems;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> ms = new Queue();
        while (!q1.isEmpty() && !q2.isEmpty()) {
            Item small = getMin(q1, q2);
            ms.enqueue(small);
        }
        if (q1.isEmpty()) {
            while (!q2.isEmpty()) {
                ms.enqueue(q2.dequeue());
            }
        } else {
            while (!q1.isEmpty()) {
                ms.enqueue(q1.dequeue());
            }
        }
        return ms;
    }

//    /** Returns a Queue that contains the given items sorted from least to greatest. */
//    public static <Item extends Comparable> Queue<Item> mergeSort(
//            Queue<Item> items) {
//        Queue<Item> copy = new Queue<>();
//        for (Item i: items
//             ) {
//            copy.enqueue(i);
//        }
//
//        if (copy.size() <= 1) {
//            return copy;
//        }
//        Queue<Queue<Item>> queueItems = makeSingleItemQueues(copy);
//        Queue<Item> sortedQueues = mergeSortedQueues(queueItems.dequeue(), queueItems.dequeue());
//        while(!queueItems.isEmpty()) {
//            sortedQueues = mergeSortedQueues(sortedQueues, queueItems.dequeue());
//        }
//        return sortedQueues;
//    }

    /**
     * Step 1 − if it is only one element in the list it is already sorted, return.
     * Step 2 − divide the list recursively into two halves until it can no more be divided.
     * Step 3 − merge the smaller lists into new list in sorted order.
     */

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        Queue<Item> copy = new Queue<>();
        for (Item i: items
        ) {
            copy.enqueue(i);
        }
        int n = items.size();

        if (copy.size() <= 1) {
            return copy;
        } else {
            Queue<Item> queue1 = new Queue<>();
            Queue<Item> queue2 = new Queue<>();
            for (int i = 0; i < n / 2; i++ ) {
                queue1.enqueue(copy.dequeue());
            }
            for (int i = n / 2; i < n; i++) {
                queue2.enqueue(copy.dequeue());
            }
            queue1 = mergeSort(queue1);
            queue2 = mergeSort(queue2);
            return mergeSortedQueues(queue1, queue2);

        }
    }


    public static void main(String[] args) {
        Queue<String> students = new Queue<String>();
        students.enqueue("Alice");
        students.enqueue("Vanessa");
        students.enqueue("Ethan");
        for (String s: students
             ) {
            System.out.println(s);
        }
        Queue<String> sorted = MergeSort.mergeSort(students);
        for (String s: sorted) {
            System.out.println(s);
        }


    }
}
