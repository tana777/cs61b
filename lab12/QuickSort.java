import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Quick;

public class QuickSort {
    /**
     * Returns a new queue that contains the given queues catenated together.
     *
     * The items in q2 will be catenated after all of the items in q1.
     */
    private static <Item extends Comparable> Queue<Item> catenate(Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> catenated = new Queue<Item>();
        for (Item item : q1) {
            catenated.enqueue(item);
        }
        for (Item item: q2) {
            catenated.enqueue(item);
        }
        return catenated;
    }

    /** Returns a random item from the given queue. */
    private static <Item extends Comparable> Item getRandomItem(Queue<Item> items) {
        int pivotIndex = (int) (Math.random() * items.size());
        Item pivot = null;
        // Walk through the queue to find the item at the given index.
        for (Item item : items) {
            if (pivotIndex == 0) {
                pivot = item;
                break;
            }
            pivotIndex--;
        }
        return pivot;
    }

    /**
     * Partitions the given unsorted queue by pivoting on the given item.
     *
     * @param unsorted  A Queue of unsorted items
     * @param pivot     The item to pivot on
     * @param less      An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are less than the given pivot.
     * @param equal     An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are equal to the given pivot.
     * @param greater   An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are greater than the given pivot.
     */
    private static <Item extends Comparable> void partition(
            Queue<Item> unsorted, Item pivot,
            Queue<Item> less, Queue<Item> equal, Queue<Item> greater) {
        while (!unsorted.isEmpty()) {
            Item output = unsorted.dequeue();
            int compare = output.compareTo(pivot);
            if (compare > 0) {
                greater.enqueue(output);
            } else if (compare < 0) {
                less.enqueue(output);
            } else {
                equal.enqueue(output);
            }
        }
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> quickSort(
            Queue<Item> items) {

        Queue<Item> copy = new Queue<>();
        for (Item i: items
             ) {
            copy.enqueue(i);

        }
        if (copy.size() <= 1) {
            return copy;

        } else {
            Item pivot = getRandomItem(copy);
            Queue<Item> less = new Queue<>();
            Queue<Item> equal = new Queue<>();
            Queue<Item> greater = new Queue<>();
            partition(copy, pivot, less, equal, greater);
            Queue<Item> first = catenate(quickSort(less), equal);
            first = catenate(first, quickSort(greater));
            return first;

        }
    }

    public static void main(String[] args) {
        Queue<Integer> number = new Queue<Integer>();
        number.enqueue(0);
        number.enqueue(2);
        number.enqueue(2);
        number.enqueue(3);
        number.enqueue(3);
        number.enqueue(9);
        number.enqueue(6);
        number.enqueue(5);
        number.enqueue(6);
        number.enqueue(6);
        for (Integer i: number) {
            System.out.println(i);
        }
        Queue<Integer> res = QuickSort.quickSort(number);
        for (Integer i: res) {
            System.out.println(i);
        }
//        Queue<String> students = new Queue<String>();
//        students.enqueue("Alice");
//        students.enqueue("Vanessa");
//        students.enqueue("Ethan");
//        for (String s: students
//        ) {
//            System.out.println(s);
//        }
//        Queue<String> sorted = QuickSort.quickSort(students);
//        for (String s: sorted) {
//            System.out.println(s);
//        }


    }
}
