/** Project 1B: Applying and Testing Data Structure version 1.0
 *
 * @author Tana Gegen 06/21/2020
 *
 * Task 1: Deque Interface
 * */

public interface Deque<T> {

    /* Interface methods must be public, so we actually can remove public here.
     */

    public void addFirst(T item);
    public void addLast(T item);
    public boolean isEmpty();
    public int size();
    public void printDeque();
    public T removeFirst();
    public T removeLast();
    public T get(int index);
}
