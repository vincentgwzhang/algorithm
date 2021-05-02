package interview.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
	
	public static void main(String[] args) {
		ReadWriteLockDemoCache cache = new ReadWriteLockDemoCache();
		for(int index = 0; index < 5; index ++) {
			final int i = index;
			new Thread(() -> {
				cache.put(i + "", i + "");
			}).start();			
		}
		
		for(int index = 0; index < 5; index ++) {
			final int i = index;
			new Thread(() -> {
				System.out.println(cache.get(i+ ""));
			}).start();			
		}
		
		while(Thread.activeCount()>2) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class ReadWriteLockDemoCache {//模拟电子显示屏幕
	
	private volatile Map<String, Object> map = new HashMap<>();
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	
	public void put(String key, Object value) {
		lock.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " is writing:" + key + " before");
			try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {}
			map.put(key, value);
			System.out.println(Thread.currentThread().getName() + " is writing:" + key + " end");
		} catch (Exception e) {
			
		} finally {
			lock.writeLock().unlock();			
		}
		
	}
	
	public Object get(String key) {
		Object object = null;
		lock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " is reding:" + key + " before");
			try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {}
			object = map.get(key);
			System.out.println(Thread.currentThread().getName() + " is reding:" + key + " end");
		} catch(Exception e) {
			
		} finally {
			lock.readLock().unlock();			
		}
		return object;
	}
	
}
