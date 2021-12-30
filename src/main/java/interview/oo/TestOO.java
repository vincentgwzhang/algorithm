package interview.oo;

import java.util.concurrent.TimeUnit;

public class TestOO {

    public static void main(String[] args) throws InterruptedException {
        B b = new B();
        b = null;
        TimeUnit.SECONDS.sleep(100);
    }

}
