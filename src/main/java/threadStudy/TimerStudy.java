package threadStudy;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class TimerStudy {

    @Test
    public void test1_Only_Run_Once() throws Exception{
        Date date = new Date();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("系统正在运行……1");
                throw new RuntimeException();
            }
        }, 1000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("系统正在运行……2");
            }
        }, 2000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("系统正在运行……3");
            }
        }, 3000);


        while(Thread.activeCount() > 2) {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("Active count:" + Thread.activeCount());
        }
    }
}
