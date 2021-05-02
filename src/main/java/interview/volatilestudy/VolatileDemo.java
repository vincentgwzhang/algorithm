package interview.volatilestudy;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

import org.junit.Test;

public class VolatileDemo {
	
	@Test
	public void testAtomicStampReference() {
		AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 5001);
		new Thread(() ->  {
			System.out.println(atomicStampedReference.getStamp());//5001
			System.out.println("CAS = " + atomicStampedReference.compareAndSet(100, 101, 5001, 5001) + ", value = " + atomicStampedReference.getReference());
			System.out.println("CAS = " + atomicStampedReference.compareAndSet(101, 100, 5001, 5001) + ", value = " + atomicStampedReference.getReference());
		}, "T3").start();;
		
		new Thread(() ->  {
			try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {}
			System.out.println(atomicStampedReference.getStamp());
			System.out.println("CAS = " + atomicStampedReference.compareAndSet(100, 108, 5001, 5002) + ", value = " + atomicStampedReference.getReference());
		}, "T4").start();;
		
		while(Thread.activeCount()>2) {
			Thread.yield();
		}
	}
	
	@Test
	public void testAtomicReference() {
		AtomicReference<AtomicReferenceData> atomicReference = new AtomicReference<>();
		
		AtomicReferenceData a1 = new AtomicReferenceData("AA", 11);
		AtomicReferenceData a2 = new AtomicReferenceData("BB", 12);
		
		atomicReference.set(a1);
		
		atomicReference.compareAndSet(a1, a2);
		atomicReference.compareAndSet(a2, a1);
		atomicReference.compareAndSet(a1, a2);
		
		System.out.println(atomicReference.compareAndSet(a2, a1) + "\t" + atomicReference.get().toString());
	}
	
	/**
	 * 验证volatile 不保证原子性。
	 */
	@Test
	public void testVerifyAutomic() {
		VolatileDemoData data = new VolatileDemoData();
		for(int index = 0; index < 100; index ++) {
			new Thread(() ->  {
				for(int index2 = 0; index2 < 20; index2 ++) {
					data.plus();
					try {TimeUnit.SECONDS.sleep(1);} catch (Exception e) {}
				}
			}).start();
		}
		
		int count = 0;
		//Java线程中有一个Thread.yield( )方法，很多人翻译成线程让步。
		//顾名思义，就是说当一个线程使用了这个方法之后，它就会把自己CPU执行的时间让掉，让自己或者其它的线程运行。
		while(Thread.activeCount() > 2) {
			//Thread.yield();
			count ++;
			try {TimeUnit.SECONDS.sleep(1);} catch (Exception e) {}
			System.out.println("Run yield for " + count + " time");
		}
		System.out.println("Finally, data.number=" + data.number);
		System.out.println("Finally, data.number=" + data.ai.get());
	}
	
	/**
	 * 加入 VolatileDemoData.number 等于 0， number 之前没有加入volatile 关键字
	 */
	@Test
	public void testVerifyVisibility() {
		VolatileDemoData data = new VolatileDemoData();
		new Thread(() ->  {
			System.out.println(Thread.currentThread().getName() + " come in");
			try {TimeUnit.SECONDS.sleep(3);} catch (Exception e) {}
			data.addTo60();
			System.out.println(Thread.currentThread().getName() + " update finish");
		}, "AAA").start();
		
		/**
		 * TODO: 这个实验是说，如果不加volatile ，主线程无法获知变量已经改了，但是一旦加任何一句话，就知道了。
		 * 为什么呢？很奇怪
		 */
		while (data.number == 0) {
			//System.out.println("Main thread identify number still 0");
			//try {TimeUnit.SECONDS.sleep(1);} catch (Exception e) {}
		}
		
		System.out.println(Thread.currentThread().getName() + " run finish");
	}
	
}

class VolatileDemoData {
	
	AtomicInteger ai = new AtomicInteger();
	
	volatile int number = 0;
	
	public void addTo60() {
		this.number = 60;
	}
	
	public void plus() {// 解决非原子性就是使用volatile + atomic
		this.ai.getAndIncrement();
		this.number++;
	}
}

class AtomicReferenceData {
	String userName;
	int age;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public AtomicReferenceData(String userName, int age) {
		this.userName = userName;
		this.age = age;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AtomicReferenceData other = (AtomicReferenceData) obj;
		if (age != other.age)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "AtomicReference [userName=" + userName + ", age=" + age + "]";
	}
	
}
