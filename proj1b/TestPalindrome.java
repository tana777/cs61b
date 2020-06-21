import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    static OffByOne offByOne = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
//    Uncomment this class once you've created your Palindrome class.

    @Test
    public void testisPalindrome() {
        assertFalse(palindrome.isPalindrome("friday"));
        assertFalse(palindrome.isPalindrome("BUtTER"));
        assertTrue(palindrome.isPalindrome("A"));
        assertTrue(palindrome.isPalindrome(null));
        assertTrue(palindrome.isPalindrome("kakkak"));
        assertTrue(palindrome.isPalindrome("Reviver"));


    }

    @Test
    public void testnewisPalindrome() {
        assertTrue(palindrome.isPalindrome("tops", offByOne));
        assertTrue(palindrome.isPalindrome("Truss", offByOne));
        assertTrue(palindrome.isPalindrome("", offByOne));
        assertTrue(palindrome.isPalindrome("a", offByOne));
        assertFalse(palindrome.isPalindrome("picture", offByOne));
        assertTrue(palindrome.isPalindrome("Tops", offByOne));

    }

}
