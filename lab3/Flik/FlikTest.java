import static org.junit.Assert.*;

import org.junit.Test;

public class FlikTest {
    @Test
    public void testisSameNumber() {
        Integer a = 128;
        Integer b = 128;
        boolean r = Flik.isSameNumber(a, b);
        assertTrue(r);

//        assertTrue(String.valueOf(r),true);

    }

}
