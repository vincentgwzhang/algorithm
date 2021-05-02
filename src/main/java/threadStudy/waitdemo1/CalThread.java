package threadStudy.waitdemo1;

import java.util.concurrent.TimeUnit;

public class CalThread extends Thread {

    private Entity entity;

    public CalThread(Entity entity) {
        this.entity = entity;
    }

    @Override
    public void run() {
        try {
            synchronized (entity) {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("Total thread run finish");
                notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
