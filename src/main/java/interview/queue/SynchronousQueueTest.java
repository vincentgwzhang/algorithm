package interview.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * SynchronousQueue 只存储一个object, 消费一个才接受一个
 *
 */
public class SynchronousQueueTest {
	
	public static void main(String[] args) {
		BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
		CountDownLatch latch = new CountDownLatch(2);
		new Thread(()-> {
			try {
				blockingQueue.put("a");
				System.out.println("Finish input a");
				blockingQueue.put("b");
				System.out.println("Finish input b");
				blockingQueue.put("c");
				System.out.println("Finish input c");
				latch.countDown();
			} catch(Exception e) {
				
			}
		}).start();
		
		new Thread(()-> {
			try {
				blockingQueue.take();
				TimeUnit.SECONDS.sleep(3);
				blockingQueue.take();
				TimeUnit.SECONDS.sleep(3);
				blockingQueue.take();
				latch.countDown();
			} catch(Exception e) {
				
			}
		}).start();
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
