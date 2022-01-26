package interview.lock;


import java.util.concurrent.TimeUnit;

public class VolatileUnsafe {
    private static class VolatileVar implements Runnable {
        public static Object o = new Object();
        private volatile Boolean flag = true; //有volatile和没volatile效果明显

        @Override
        public void run() {
            while (true) {
                if (flag) {
                    System.out.println(Thread.currentThread().getName() + ":>>>>>" + flag);
                    try {
                        TimeUnit.SECONDS.sleep(2L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileVar v = new VolatileVar();
        Thread t1 = new Thread(v);
        Thread t2 = new Thread(v);
        t1.start();
        t2.start();
        Thread.sleep(500);
        v.flag = false;
        System.out.println("I sleep 10s");
        Thread.sleep(10000);
        System.out.println("I will start now");
        v.flag = true;
    }
}