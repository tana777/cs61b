public class LinkedListDeque<General> {
    private class IntNode {
        public General item;
        public IntNode prev;
        public IntNode next;

        public IntNode(General i, IntNode p, IntNode n) {
            item = i;
            prev = p;
            next = n;
        }

    }
    private IntNode sentinel;
    private int size;

    /** Creates an empty LinkedListDeque. */
    public LinkedListDeque() {
        sentinel = new IntNode(null, null, null);
        size = 0;
    }

    public LinkedListDeque(General x) {
        sentinel = new IntNode(null, null, null);
        sentinel.next = new IntNode(x,sentinel,sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    /** Adds item to the front of the list. */
    public void addFirst(General item) {
        if (size == 0) {
            sentinel.next = new IntNode(item, sentinel,sentinel);
            sentinel.prev = sentinel.next;
        } else {
            sentinel.next = new IntNode(item, sentinel, sentinel.next);
            sentinel.next.next.prev = sentinel.next;
        }
        size = size + 1;
    }

    /** Add item to the last of the list. */
    public void addLast(General item) {
        if (size == 0) {
            sentinel.prev = new IntNode(item, sentinel,sentinel);
            sentinel.next = sentinel.prev;
        }
        else {
            sentinel.prev = new IntNode(item, sentinel.prev, sentinel);
            sentinel.prev.prev.next = sentinel.prev;
        }
        size = size + 1;
    }


    /** Check if deque is empty. */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /** Compute the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Print the item in the deque from first to last, separated by a space. */
    public void printDeque() {
        IntNode ptr = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public General removeFirst() {
        if (size == 0) {
            return null;
        }
        General val = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size = size - 1;
        return val;
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, return null. */
    public General removeLast() {
        if (size == 0) {
            return null;
        }
        General val = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size = size - 1;
        return val;
    }

    /** Gets the item at the given index, if no such item exits, returns null. Must not alter the deque. */
    public General get(int index) {
        if (index >= size) {
            return null;
        }
        IntNode ptr = sentinel.next;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                return ptr.item;
            }
            ptr = ptr.next;
        }
        return null;
    }
/**
    public static void main(String[] args) {
        LinkedListDeque y = new LinkedListDeque(9);
        boolean yy = y.isEmpty();
        int sizeY = y.size();
        y.printDeque();
        General removeFy;
        removeFy = (General) y.removeFirst();
        General removeLy = (General) y.removeLast();
        General get_value_y = (General) y.get(1);

        LinkedListDeque x = new LinkedListDeque(34);
        x.addFirst(2);
        x.addLast(100);
        boolean xx = x.isEmpty();
        int sizeX = x.size();
        x.printDeque();
        General removeFx = (General) x.removeFirst();
        General removeLx = (General) x.removeLast();
        int sizeX_after = x.size();
        General get_value_x = (General) x.get(0);

    }
 */
}
