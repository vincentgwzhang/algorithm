package threadStudy.waitdemo1;

import org.junit.Assert;
import org.junit.Test;

public class Boss1 {

    public boolean isPowerOfThree(int n) {
        if (n < 0) {
            n = -1 * n;
        }

        if (n == 0) return true;

        while (n != 1) {
            if ( n % 3 == 0) {
                n = n / 3;
            } else {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test1() {
        Assert.assertTrue(isPowerOfThree(27));
    }

}
