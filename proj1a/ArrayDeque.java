public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }
    /**
    The position of the next item to be inserted is always nextLast.
    size is always the number of items in the AList.
    The last item in the list is always in position size - 1. */
     private void resize(int capacity) {
        T[] a = (T []) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 4);
        }
        items[nextLast] = x;
        if (nextLast < items.length - 1) {
            nextLast = nextLast + 1;
        } else {
            nextLast = items.length - 1 - nextLast;
        }
        size = size + 1;
    }

    public void addFirst(T y) {
        if (size == items.length) {
            resize(size * 4);
        }
        items[nextFirst] = y;
        if (nextFirst > 0) {
            nextFirst = nextFirst - 1;
        } else {
            nextFirst = items.length - 1 - nextFirst;

        }
        size = size + 1;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {

        for (int i = nextFirst + 1; i < items.length; i++) {
            System.out.print(items[i] + " ");
        }

        for (int i = 0; i < nextLast; i++) {
            System.out.print(items[i] + " ");
        }
    }

    public T removeFirst() {
         if (size == 0) {
             return null;
         }
        T returnItem;
        if (nextFirst + 1 >= items.length) {
            returnItem = items[items.length - (nextFirst + 1)];
             items[items.length - (nextFirst + 1)] = null;
             size = size - 1;
             nextFirst = items.length - (nextFirst + 1);
        } else {
            returnItem = items[nextFirst + 1];
             items[nextFirst + 1] = null;
             size = size - 1;
             nextFirst = nextFirst + 1;
        }
        return returnItem;
    }

    public T removeLast() {
         if (size == 0) {
             return null;
         }
        T returnItem = items[nextLast - 1];
        items[nextLast - 1] = null;
        size = size - 1;
        nextLast = nextLast - 1;
        return returnItem;
    }

    public T get(int index) {
        if (index >= items.length) {
            return null;
        }
        else {
            int res = nextFirst + index + 1;
            if (res < items.length) {
                return items[res];
            } else {
                return items[-(items.length - res)];
            }
        }
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> X  = new ArrayDeque();
        X.addFirst(0);
        X.addLast(1);
        X.addLast(2);
        X.addLast(3);
        X.addLast(4);
        X.addLast(5);
        X.addLast(6);
        int r = X.removeLast();
        X.addLast(7);
        int rr = X.removeLast();



//
//        X.addLast(0);
//        int get_value = X.get(0);
//        X.printDeque();
//        int re_value = X.removeFirst();
//        int size_val = X.size();
    }

}
