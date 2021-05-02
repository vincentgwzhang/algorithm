package algorithm.duilie;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * 用数组模拟环形队列
 * 
 * 核心思想：
 * 环形就是循环，循环就是加上一环，而最后就在环中的其中一节。于是，取模就是关键。
 *
 */
public class CircleQueueDemo {
	@Test
	public void testArrayQueue() {
		CircleQueue queue = new CircleQueue(3);
		assertFullAndEmpty(queue, false, true);
		
		for(int index = 0; index < 2; index ++) {
			queue.addQueue(15);
			queue.addQueue(16);
			assertFullAndEmpty(queue, false, false);
			Assert.assertTrue(queue.size()==2);
			
			Assert.assertTrue(queue.getQueue() == 15);
			assertFullAndEmpty(queue, false, false);
			Assert.assertTrue(queue.size()==1);
			
			Assert.assertTrue(queue.getQueue() == 16);
			assertFullAndEmpty(queue, false, true);
			Assert.assertTrue(queue.size()==0);
			
			queue.addQueue(15);
			Assert.assertTrue(queue.size()==1);
			queue.addQueue(16);
			Assert.assertTrue(queue.size()==2);
			queue.addQueue(17);
			Assert.assertTrue(queue.size()==3);
			assertFullAndEmpty(queue, true, false);
			
			Assert.assertTrue(queue.getQueue() == 15);
			assertFullAndEmpty(queue, false, false);
			Assert.assertTrue(queue.size()==2);
			
			Assert.assertTrue(queue.getQueue() == 16);
			assertFullAndEmpty(queue, false, false);
			Assert.assertTrue(queue.size()==1);
			
			Assert.assertTrue(queue.getQueue() == 17);
			assertFullAndEmpty(queue, false, true);
			Assert.assertTrue(queue.size()==0);
		}
	}
	
	private void assertFullAndEmpty(CircleQueue queue, boolean isFull, boolean isEmpty) {
		Assert.assertTrue(queue.isFull() == isFull);
		Assert.assertTrue(queue.isEmpty() == isEmpty);
	}
	
	private void assertFullAndEmpty(CircleArray queue, boolean isFull, boolean isEmpty) {
		Assert.assertTrue(queue.isFull() == isFull);
		Assert.assertTrue(queue.isEmpty() == isEmpty);
	}
}

class CircleQueue {
	
	private int maxSize;
	private int front = 0;//Always point to the first number
	private int rear  = 0;//Always point to the last number
	private int[] arr;
	private boolean isFull = false;
	
	public CircleQueue(int arrMaxSize) {
		maxSize = arrMaxSize;
		arr = new int[maxSize];
		front = 0;
		rear  = 0;
		isFull = false;
	}
	
	public boolean isFull() {
		return isFull;
	}
	
	public boolean isEmpty() {
		return rear == front && isFull == false;
	}
	
	public boolean addQueue(int n) {
		if (isFull()) {
			return false;
		}
		arr[rear] = n;
		rear = (rear + 1) % maxSize;
		if(rear == front) {
			isFull = true;
		}
		return true;
	}
	
	public int getQueue() {
		if (isEmpty()) {
			throw new RuntimeException("Queue is empty");
		}
		int result = arr[front];
		front = (front+1) % maxSize;
		isFull = false;
		return result;
	}
	
	public void showQueue() {
		while (!isEmpty()) {
			System.out.printf("%d", arr[front]);
			front = (front + 1) % maxSize;
		}
	}
	
	public int size() {
		if (isFull()) {
			return maxSize;
		}
		return (rear + maxSize - front) % maxSize;
	}
	
	public int headQueue() {
		if(isEmpty()) {
			throw new RuntimeException("No more data");
		}
		else return arr[front];
	}
}

class CircleArray {
	private int maxSize; // 表示数组的最大容量
	//front 变量的含义做一个调整： front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素 
	//front 的初始值 = 0
	private int front; 
	//rear 变量的含义做一个调整：rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定.
	//rear 的初始值 = 0
	private int rear; // 队列尾
	private int[] arr; // 该数据用于存放数据, 模拟队列
	
	public CircleArray(int arrMaxSize) {
		maxSize = arrMaxSize;
		arr = new int[maxSize];
	}
	
	// 判断队列是否满
	public boolean isFull() {
		return (rear  + 1) % maxSize == front;
	}
	
	// 判断队列是否为空
	public boolean isEmpty() {
		return rear == front;
	}
	
	// 添加数据到队列
	public void addQueue(int n) {
		// 判断队列是否满
		if (isFull()) {
			System.out.println("队列满，不能加入数据~");
			return;
		}
		//直接将数据加入
		arr[rear] = n;
		//将 rear 后移, 这里必须考虑取模
		rear = (rear + 1) % maxSize;
	}
	
	// 获取队列的数据, 出队列
	public int getQueue() {
		// 判断队列是否空
		if (isEmpty()) {
			// 通过抛出异常
			throw new RuntimeException("队列空，不能取数据");
		}
		// 这里需要分析出 front是指向队列的第一个元素
		// 1. 先把 front 对应的值保留到一个临时变量
		// 2. 将 front 后移, 考虑取模
		// 3. 将临时保存的变量返回
		int value = arr[front];
		front = (front + 1) % maxSize;
		return value;

	}
	
	// 显示队列的所有数据
	public void showQueue() {
		// 遍历
		if (isEmpty()) {
			System.out.println("队列空的，没有数据~~");
			return;
		}
		// 思路：从front开始遍历，遍历多少个元素
		// 动脑筋
		for (int i = front; i < front + size() ; i++) {
			System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
		}
	}
	
	// 求出当前队列有效数据的个数
	public int size() {
		// rear = 2
		// front = 1
		// maxSize = 3 
		return (rear + maxSize - front) % maxSize;   
	}
	
	// 显示队列的头数据， 注意不是取出数据
	public int headQueue() {
		// 判断
		if (isEmpty()) {
			throw new RuntimeException("队列空的，没有数据~~");
		}
		return arr[front];
	}
}