package synthesizer;
import java.lang.reflect.Array;
import java.util.Iterator;

/** HW 1: Packages, Interfaces, Generics, Exceptions, Iteration
 *
 *  Task 3: ArrayRingBuffer
 *
 *  Task 5: Iteration and Exceptions
 *
 *  @author tanagegen 06/23/2020
 *
 */

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(Object x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        }
        if (last == capacity) {
            last = 0;
        }
        rb[last] = (T) x;
        last = last + 1;
        fillCount = fillCount + 1;
    }


    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        if (first == this.capacity) {
            first = 0;
        }
        T tmp = rb[first];
        rb[first] = null;
        first = first + 1;
        fillCount = fillCount - 1;
        return tmp;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        if (first == this.capacity) {
            first = 0;
        }
        return rb[first];
    }

    @Override
    public Iterator iterator() {
        return new queueIterator();
    }


    // TODO: When you get to part 5, implement the needed code to support iteration.
    private class queueIterator implements Iterator<T> {
        private int ptr;
        public queueIterator() {
            ptr = first;
        }
        public boolean hasNext() {
            return (ptr != capacity);
        }
        public T next() {
            T returnItem = rb[ptr];
            ptr = ptr + 1;
            return returnItem;
        }
    }
}
