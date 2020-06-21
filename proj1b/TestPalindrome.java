import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    /** Unit Tests for isPalindrome.
     *
     *  Task 3A: isPalindrome Testing
     *
     */
    @Test
    public void testisPalindrome() {
        assertTrue(palindrome.isPalindrome("A"));
    }

    @Test
    public void testisPalindrome2() {
        assertTrue(palindrome.isPalindrome("kakkak"));
    }

    @Test
    public void testisPalindrome3() {
        assertFalse(palindrome.isPalindrome("Reviver"));
    }


    /** Unit Tests for isPalindrome with OffByOne. */
    @Test
    public void testPalindrome() {
        OffByOne offByOne = new OffByOne();
        assertTrue(palindrome.isPalindrome("tops", offByOne));
    }

    @Test
    public void testPalindrome2() {
        OffByOne offByOne = new OffByOne();
        assertTrue(palindrome.isPalindrome("a", offByOne));
    }

    @Test
    public void testPalindrome3() {
        OffByOne offByOne = new OffByOne();
        assertFalse(palindrome.isPalindrome("Tops", offByOne));
    }

    @Test
    public void testPalindrome4() {
        OffByOne offByOne = new OffByOne();
        assertTrue(palindrome.isPalindrome("TrusS", offByOne));
    }

    /** Unit Tests for isPalindrome with OffByN.
     */
    @Test
    public void testNPalindrome() {
        OffByN offBy3 = new OffByN(3);
        assertTrue(palindrome.isPalindrome("wort", offBy3));
    }

    @Test
    public void testNPalindrome2() {
        OffByN offBy3 = new OffByN(3);
        assertFalse(palindrome.isPalindrome("Wort", offBy3));
    }

    @Test
    public void testNPalindrome3() {
        OffByN offBy5 = new OffByN(5);
        assertTrue(palindrome.isPalindrome("tiffany", offBy5));
    }
}
