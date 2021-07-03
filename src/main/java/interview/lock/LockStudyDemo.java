package interview.lock;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class LockStudyDemo {
	
	/**
	 * 事实证明：
	 * 1，class 的 static 无论有多少个，都只有一把锁
	 * 2，先获得锁的线程能解开所有的锁，加塞。
	 */
	@Test
	public void testLock01() {
		new Thread(() -> {LockStudyDemoBean.method01();}, "TT1" ).start(); 
		try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {}
		for(int i = 0; i < 10; i ++) {new Thread(() -> {LockStudyDemoBean.method01();}, "YY" + i ).start();}
		
		while(Thread.activeCount() > 2) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
			}
		}
	}
	
}

class LockStudyDemoBean {
	
	public static synchronized void method01() {
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
		}
		method02();
	}
	
	public static synchronized void method02() {
		System.out.println(Thread.currentThread().getName() + " is running");
	}
	
}