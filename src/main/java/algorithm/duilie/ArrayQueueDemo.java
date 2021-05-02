package algorithm.duilie;

import org.junit.Assert;
import org.junit.Test;

/**
 *用数组模拟队列
 */
public class ArrayQueueDemo {
	
	@Test
	public void testArrayQueue() {
		ArrayQueue queue = new ArrayQueue(3);
		assertFullAndEmpty(queue, false, true);
		
		queue.addToQueue(15);
		queue.addToQueue(16);
		assertFullAndEmpty(queue, false, false);
		
		Assert.assertTrue(queue.getFromQueue() == 15);
		assertFullAndEmpty(queue, false, false);
		
		Assert.assertTrue(queue.getFromQueue() == 16);
		assertFullAndEmpty(queue, false, true);
		
		queue.addToQueue(17);
		assertFullAndEmpty(queue, true, false);
		
		Assert.assertTrue(queue.headQueue() == 17);
		assertFullAndEmpty(queue, true, false);
		
		Assert.assertTrue(queue.getFromQueue() == 17);
		assertFullAndEmpty(queue, true, true);
	}
	
	private void assertFullAndEmpty(ArrayQueue queue, boolean isFull, boolean isEmpty) {
		Assert.assertTrue(queue.isFull() == isFull);
		Assert.assertTrue(queue.isEmpty() == isEmpty);
	}
	
}

class ArrayQueue {
	private int maxSize;
	private int front = -1;
	private int rear  = -1;
	private int[] arr;
	
	public ArrayQueue(int arrMaxSize) {
		maxSize = arrMaxSize;
		arr = new int[maxSize];
		front = -1;
		rear  = -1;
	}
	
	public boolean isFull() {
		return rear == maxSize - 1;
	}
	
	public boolean isEmpty() {
		return rear == front;
	}
	
	public boolean addToQueue(int n) {
		if (isFull()) {
			return false;
		}
		
		arr[++rear] = n;
		return true;
	}
	
	public int getFromQueue() {
		if (isEmpty()) {
			throw new RuntimeException("Queue is empty");
		}
		return arr[++front];
	}
	
	public void showQueue() {
		if(isEmpty()) {
			return ;
		}
		for(int index = front+ 1; index <= rear; index++) {
			System.out.println(arr[index]);
		}
	}
	
	public int headQueue() {
		if(isEmpty()) {
			throw new RuntimeException("No more data");
		}
		else return arr[front+1];
	}
}
