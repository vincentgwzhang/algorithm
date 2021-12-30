package interview.lock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * PriorityBlockingQueue ==> PriorityBlockingQueue(int initialCapacity, Comparator<? super E> comparator)
 * ArrayBlockingQueue
 * LinkedBlockingQueue
 *
 * BlockingQueue:
 * Throws exception: =================> add(e), remove(), element()
 * Special value   : =================> offer(e), poll(), peak()
 * Blocks          : =================> put(e), take()
 * Times out       : =================> offer(e, time, unit), poll(time, unit)
 *
 */



public class ComplicateDemo {
	public static void main(String[] args) throws Exception {
		ComplicateDemoResource resource = new ComplicateDemoResource(new ArrayBlockingQueue<>(10));
		new Thread(() -> {
			try {
				resource.myProduce();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "Prod").start();
		
		new Thread(() -> {
			try {
				resource.myConsume();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "Prod").start();
		
		TimeUnit.SECONDS.sleep(5);
		resource.stop();
	}
}

class ComplicateDemoResource {
	private volatile boolean FLAG = true;
	private AtomicInteger atomicInteger = new AtomicInteger();
	
	BlockingQueue<String> blockingQueue = null;

	public ComplicateDemoResource(BlockingQueue<String> blockingQueue) {
		super();
		this.blockingQueue = blockingQueue;
		System.out.println(blockingQueue.getClass().getName());
	}

	public void myProduce() throws Exception {
		String data = null;
		boolean returnValue;
		while (FLAG) {
			data = atomicInteger.incrementAndGet() + "";
			returnValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
			if (returnValue) {
				System.out.println(Thread.currentThread().getName() + " offer " + data + " success");
			} else {
				System.out.println(Thread.currentThread().getName() + " offer " + data + " fail");
			}
			TimeUnit.SECONDS.sleep(1);
		}
		System.out.println(Thread.currentThread().getName() + " Loop break out");
	}

	public void myConsume() throws Exception {
		String data = null;
		while (FLAG) {
			data = blockingQueue.poll(2L, TimeUnit.SECONDS);
			if (data == null || data.contentEquals("")) {
				System.out.println(Thread.currentThread().getName() + " consume end");
			} else {
				System.out.println(Thread.currentThread().getName() + " consume data:" + data);
			}
		}
		System.out.println(Thread.currentThread().getName() + " Loop break out");
	}
	
	public void stop() {
		FLAG = false;
	}
	
}