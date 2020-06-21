public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque d = new LinkedListDeque();
        for (int i = 0; i < word.length(); i++) {
            char c = word.toLowerCase().charAt(i);
            d.addLast(c);
        }
        return d;
    }


    public boolean isPalindrome(String word) {
        if (word == null || word.length() == 0 || word.length() == 1) {
            return true;
        }
        Deque d = wordToDeque(word);
        int j = 0;
        if (d.size() % 2 == 0) {
            j = d.size() / 2;
        } else {
            j = (d.size() - 1) / 2;
        }
        for (int i = 0; i < j; i++) {
            if (d.removeLast() == d.removeFirst()) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null || word.length() == 0 || word.length() == 1) {
            return true;
        }
        Deque d = wordToDeque(word);
        int j = 0;
        if (d.size() % 2 == 0) {
            j = d.size() / 2;
        } else {
            j = (d.size() - 1) / 2;
        }
        for (int i = 0; i < j; i++) {
            char x = (char) d.removeFirst();
            char y = (char) d.removeLast();
            char xx = Character.toLowerCase(x);
            char yy = Character.toLowerCase(y);
            if (cc.equalChars(xx, yy)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

}
