package interview.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {
	
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(6);
		
		for(int index = 1; index <= 6; index ++) {
			new Thread(()->{
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Thread " + Thread.currentThread().getName() + " go");
				countDownLatch.countDown();
			}, String.valueOf(index)).start(); 
		}
		countDownLatch.await();
		System.out.println("Main thread " + Thread.currentThread().getName() + " go");
	}
	
}
