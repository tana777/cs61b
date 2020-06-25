package synthesizer;

/** HW 1: Packages, Interfaces, Generics, Exceptions, Iteration
 *
 *  Task 2: AbstractBoundedQueue Abstract Class
 *
 *  @author tanagegen 06/23/2020
 *
 */
public abstract class AbstractBoundedQueue implements BoundedQueue {
    protected int fillCount;
    protected int capacity;

    public int capacity() {
        return capacity;
    }

    public int fillCount() {
        return fillCount;
    }
    /**
     * Note that isEmpty, isFull, peek, dequeue, enqueue, are inherited from BoundedQueue,
     * so you should not to declare these explicitly in your AbstractBoundedQueue.java file.
     */
}
