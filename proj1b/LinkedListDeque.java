public class LinkedListDeque<T> implements Deque<T>  {

    private class IntNode {
        private T item;
        private IntNode prev;
        private IntNode next;

        public IntNode(T i, IntNode p, IntNode n) {
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

    /** Adds item to the front of the list. */
    @Override
    public void addFirst(T item) {
        if (size == 0) {
            sentinel.next = new IntNode(item, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        } else {
            sentinel.next = new IntNode(item, sentinel, sentinel.next);
            sentinel.next.next.prev = sentinel.next;
        }
        size = size + 1;
    }

    /** Add item to the last of the list. */
    @Override
    public void addLast(T item) {
        if (size == 0) {
            sentinel.prev = new IntNode(item, sentinel, sentinel);
            sentinel.next = sentinel.prev;
        } else {
            sentinel.prev = new IntNode(item, sentinel.prev, sentinel);
            sentinel.prev.prev.next = sentinel.prev;
        }
        size = size + 1;
    }


    /** Check if deque is empty. */
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /** Compute the number of items in the deque. */
    @Override
    public int size() {
        return size;
    }

    /** Print the item in the deque from first to last, separated by a space. */
    @Override
    public void printDeque() {
        IntNode ptr = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null. */
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T val = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size = size - 1;
        return val;
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, return null. */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T val = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size = size - 1;
        return val;
    }

    /** Gets the item at the given index, if no such item exits,
     * returns null. Must not alter the deque. */
    @Override
    public T get(int index) {
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


    private IntNode helper(IntNode p, int i) {
        if (i == 0) {
            return p.next;
        }
        return helper(p.next, i - 1);
    }


    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        IntNode v = helper(sentinel, index);
        if (v == null) {
            return null;
        }
        return v.item;
    }
}
