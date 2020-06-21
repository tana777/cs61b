/** Project 1B: Applying and Testing Data Structure version 1.0
 *
 * @author Tana Gegen 06/21/2020
 *
 * Task 5: OffByOne
 * */

public class OffByOne implements CharacterComparator {

    @Override
    public boolean equalChars(char x, char y) {
        if (Math.abs(x - y) == 1) {
            return true;
        }
        return false;
    }
}
