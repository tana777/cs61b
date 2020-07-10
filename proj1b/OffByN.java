/** Project 1B: Applying and Testing Data Structure version 1.0
 *
 * @author Tana Gegen 06/21/2020
 *
 * Task 5: OffByN
 * */

public class OffByN implements CharacterComparator {

    private int N;

    public OffByN(int n) {
        this.N = n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if (Math.abs(x - y) == N) {
            return true;
        }
        return false;
    }
}
