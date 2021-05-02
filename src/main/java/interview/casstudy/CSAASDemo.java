package interview.casstudy;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class CSAASDemo {
	/**
	 * 如果线程的期望值和真实值一样，那么本次修改成功。
	 */
	@Test
	public void testCASDemo() {
		AtomicInteger ai = new AtomicInteger(5);
		ai.compareAndSet(5, 6);
		
		System.out.println(ai.get());
	}
	
	@Test
	public void testABA() {
		
	}
	
}
