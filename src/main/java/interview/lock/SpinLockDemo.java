package interview.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemo {
	
	AtomicReference<Thread> atomicReference = new AtomicReference<>();
	
	/**
	 * 不断地通过循环的方式去获取，核心就是compareAndSet
	 */
	public void myLock() {
		Thread t = Thread.currentThread();
		while(!atomicReference.compareAndSet(null, t)) {
			System.out.println("I am waiting");
			try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {}
		}
	}
	
	public void myUnlock() {
		Thread t = Thread.currentThread();
		atomicReference.compareAndSet(t, null );
	}
	
	public static void main(String[] args) {
		SpinLockDemo demo = new SpinLockDemo();
		new Thread(() -> {
			
		},"AAA").start();
	}

}
