package synthesizer;

/** HW 1: Packages, Interfaces, Generics, Exceptions, Iteration
 *
 *  Task 4: GuitarString
 *
 *  @author tanagegen 06/24/2020
 *
 */

//Make sure this class is public
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final means
     * the values cannot be changed at runtime. We'll discuss this and other topics
     * in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        int myCapacity = (int) Math.round(SR / frequency);
        buffer = new ArrayRingBuffer<Double>(myCapacity);
        double x = 0;
        while (!buffer.isFull()) {
            buffer.enqueue(x);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.dequeue();
            double r = Math.random() - 0.5;
            buffer.enqueue(r);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm. 
     */
    public void tic() {
        double f = buffer.dequeue();
        double avg = ((f + buffer.peek()) / 2) * DECAY;
        buffer.enqueue(avg);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }
}
