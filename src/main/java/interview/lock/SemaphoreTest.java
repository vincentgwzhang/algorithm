package interview.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {
	
	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(3);
		
		for(int index = 1; index < 6; index ++) {
			new Thread(()-> {
				try {
					semaphore.acquire();
					System.out.println(Thread.currentThread().getName() + " [===>]");
					
					TimeUnit.SECONDS.sleep(3);
					System.out.println(Thread.currentThread().getName() + " [<===]");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					semaphore.release();
				}
			}, String.valueOf(index)).start();
		}
	}
	
}
