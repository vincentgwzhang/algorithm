package interview.lock;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 一个初始值为 0 的变量，两个线程对其交替操作，一个+1，一个-1， 来5轮
 * 无论是notify 还是 wait, 还是讯号，都要用while, 否则会引起虚假唤醒的问题 spurious
 * 
 * 
 * synchronized 的底层是 monitorenter 和 monitorexit, 
 * 而 wait / notify 等方法也依赖于 monitor 对象。因此wait / notify 也只能在 Synchronized 中才能使用
 * 
 * 
 * synchronized / lock 默认是非公平锁
 * @author gzhang
 *
 */
public class ProducerConsumerTest {
	
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(2);
		ProducerConsumerData data = new ProducerConsumerData();
		new Thread(() -> {
			try {
				for(int index = 0; index < 5; index ++) {
					data.increment();
				}
				latch.countDown();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "AA").start();
		
		new Thread(() -> {
			try {
				for(int index = 0; index < 5; index ++) {
					data.decrement();
				}
				latch.countDown();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "BB").start();
		latch.await();
	}

}

class ProducerConsumerData {
	private int number = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public void increment() throws Exception {
		lock.lock();
		System.out.println("Inside the function increment");
		TimeUnit.SECONDS.sleep(new Random().nextInt(3));
		try {
			while (number != 0) {
				condition.await();
			}
			number ++;
			System.out.println(Thread.currentThread().getName() + " [increment] run finish");
			condition.signalAll();
		} catch(Exception e) {
			
		} finally {
			lock.unlock();
		}
	}
	
	public void decrement() throws Exception {
		lock.lock();
		System.out.println("Inside the function decrement");
		TimeUnit.SECONDS.sleep(new Random().nextInt(3));
		try {
			while (number == 0) {
				condition.await();
			}
			number --;
			System.out.println(Thread.currentThread().getName() + " [decrement] run finish");
			condition.signalAll();
		} catch(Exception e) {
			
		} finally {
			lock.unlock();
		}
	}
}