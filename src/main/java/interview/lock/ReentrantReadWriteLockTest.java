package interview.lock;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest {
	
	static class MyObject {
		private Object object;
		private ReadWriteLock lock = new ReentrantReadWriteLock();
		
		public void get() {
			lock.readLock().lock();
			System.out.println(Thread.currentThread().getName() + "准备读数据!!");
			
			try {
				Thread.sleep(new Random().nextInt(1000));
				System.out.println(Thread.currentThread().getName() + "读数据为:" + this.object);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.readLock().unlock();
			}
		}
		
		public void put(Object object) {
			lock.writeLock().lock();
			System.out.println(Thread.currentThread().getName() + "准备写数据");
			
			try {
				Thread.sleep(new Random().nextInt(1000));
				this.object = object;
				System.out.println(Thread.currentThread().getName() + "写数据为" + this.object);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.writeLock().unlock();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		final MyObject myObject = new MyObject();
		ExecutorService executor = Executors.newCachedThreadPool();
		
		for (int i = 0; i < 3; i++) {
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					for (int j = 0; j < 5; j++)
						myObject.put(new Random().nextInt(1000));
				}
			});
		}
		
		for (int i = 0; i < 3; i++) {
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					for (int j = 0; j < 5; j++)
						myObject.get();
				}
			});
		}
		
		executor.shutdown();
	}

}