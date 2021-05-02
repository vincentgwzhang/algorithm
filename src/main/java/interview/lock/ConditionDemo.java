package interview.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程之间按照顺序调用，实现 A->B->C 精确调用顺序
 * @author gzhang
 *
 */
public class ConditionDemo {
	
	public static void main(String[] args) {
		ConditionResource resource = new ConditionResource();
		new Thread(() -> {resource.printA();}, "TT1").start();
		new Thread(() -> {resource.printB();}, "TT2").start();
		new Thread(() -> {resource.printC();}, "TT3").start(); 
	}
	
}

class ConditionResource {
	
	private int number = 1; //A:1, B:2, C:3
	private Lock lock = new ReentrantLock();
	private Condition c1 = lock.newCondition();
	private Condition c2 = lock.newCondition();
	private Condition c3 = lock.newCondition();
	
	public void printA() {
		lock.lock();
		try {
			while (number != 1) {
				c1.await();
			}
			
			System.out.println(Thread.currentThread().getName() + " working");
			
			number=2;
			c2.signalAll();// 通知所有block在 c2.wait() 的线程
		} catch(Exception e) {
			
		} finally {
			lock.unlock();
		}
	}
	
	public void printB() {
		lock.lock();
		try {
			while (number != 2) {
				c2.await();
			}
			
			System.out.println(Thread.currentThread().getName() + " working");
			
			number=3;
			c3.signalAll();// 通知所有block在 c2.wait() 的线程
		} catch(Exception e) {
			
		} finally {
			lock.unlock();
		}
	}
	
	public void printC() {
		lock.lock();
		try {
			while (number != 3) {
				c3.await();
			}
			
			System.out.println(Thread.currentThread().getName() + " working");
			
			number=1;
			c1.signalAll();// 通知所有block在 c2.wait() 的线程
		} catch(Exception e) {
			
		} finally {
			lock.unlock();
		}
	}
	
	
}