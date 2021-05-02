package interview.lock;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class DeadLockDemo {
	
	/**
	 * 如何查看死锁呢？
	 * 1, C:\Program Files\Java\jdk1.8.0_172\bin\jsp.exe 也就是linus 的ps
	 * 也就是运行： jps -l
	 * 
	 * 你会得到类似于 
	 * 16556 org.eclipse.jdt.internal.junit.runner.RemoteTestRunner
	 * 
	 * 2, 然后你通过jstack 16556 获取结果
	 * Found 1 deadlock.
	 */
	@Test
	public void test1() {
		String lockA = "lockA";
		String lockB = "lockB";
		new Thread(new DeadLockData(lockA, lockB), "TT1").start();
		new Thread(new DeadLockData(lockB, lockA), "TT2").start();
		
		int loop = 0;
		while (Thread.activeCount()>2) {
			System.out.println("I am waiting here, loop = " + ++loop);
			try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
		}
		System.out.println("Everything finish");
	}
	
	@Test
	public void test2() {
		String lockA = "lockA";
		String lockB = "lockB";
		new Thread(new DeadLockData2(lockA, lockB), "TT1").start();
		try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
		new Thread(new DeadLockData2(lockA, lockB), "TT2").start();
		
		int loop = 0;
		while (Thread.activeCount()>2) {
			try {TimeUnit.SECONDS.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		}
		System.out.println("Everything finish");
	}
	
}
class DeadLockData implements Runnable {
	private String lockA;
	private String lockB;
	public DeadLockData(String lockA, String lockB) {
		super();
		this.lockA = lockA;
		this.lockB = lockB;
	}
	@Override
	public void run() {
		synchronized (lockA) {
			System.out.println(Thread.currentThread().getName() + " is holding 1 " + lockA + " and try to get " + lockB);
			try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
			synchronized(lockB) {
				//其实两个人都进不来
				System.out.println(Thread.currentThread().getName() + " is holding 2 " + lockB + " and try to get " + lockA);	
			}
		}
	}
}

class DeadLockData2 implements Runnable {
	private String lockA;
	private String lockB;
	public DeadLockData2(String lockA, String lockB) {
		super();
		this.lockA = lockA;
		this.lockB = lockB;
	}
	@Override
	public void run() {
		synchronized (lockA) {
			System.out.println(Thread.currentThread().getName() + " Into lockA block");
			synchronized(lockB) {
				System.out.println(Thread.currentThread().getName() + " Into lockB block");
				try {TimeUnit.SECONDS.sleep(50);} catch (InterruptedException e) {e.printStackTrace();}
			}
		}
	}
}