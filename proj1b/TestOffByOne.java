import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    //Uncomment this class once you've created your
    // CharacterComparator interface and OffByOne class.
    @Test
    public void testEqualChars() {
        assertFalse(offByOne.equalChars('z','a'));
        assertTrue(offByOne.equalChars('a', 'b'));
        assertFalse(offByOne.equalChars('a', 'z'));
        assertTrue(offByOne.equalChars('%', '&'));
        assertFalse(offByOne.equalChars('c','c'));
    }

}
