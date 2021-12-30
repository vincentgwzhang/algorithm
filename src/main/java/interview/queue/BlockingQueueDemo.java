package interview.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author gzhang
 *
 */
public class BlockingQueueDemo {
	public static void main(String[] args) throws InterruptedException {
		// FIFO
		/**
		 * add, element, remove will throw exception
		 */
		BlockingQueue<String> blockQueue = new ArrayBlockingQueue<>(3);

		System.out.println(blockQueue.add("a"));
		System.out.println(blockQueue.add("b"));
		System.out.println(blockQueue.add("c"));
		System.out.println("=================");
		System.out.println(blockQueue.element());//get the head
		System.out.println(blockQueue.remove());
		System.out.println(blockQueue.remove());
		System.out.println(blockQueue.remove());
		
		System.out.println(blockQueue.offer("a"));
		System.out.println(blockQueue.offer("b"));
		System.out.println(blockQueue.offer("c"));
		System.out.println(blockQueue.offer("x"));//not throw exception, true or false
		System.out.println(blockQueue.peek());
		System.out.println(blockQueue.poll());
		System.out.println(blockQueue.poll());
		System.out.println(blockQueue.poll());
		System.out.println(blockQueue.poll());//not throw exception, return object or null
		System.out.println("=================");
		
		//take has blocking status
		
		System.out.println(blockQueue.offer("a", 2L, TimeUnit.SECONDS));//过两秒都无法塞进去的话就判false
		System.out.println(blockQueue.offer("b", 2L, TimeUnit.SECONDS));
		System.out.println(blockQueue.offer("c", 2L, TimeUnit.SECONDS));
		System.out.println(blockQueue.offer("d", 2L, TimeUnit.SECONDS));
		System.out.println(blockQueue.poll(2L, TimeUnit.SECONDS));//过两秒都无法拿的话就null
		System.out.println(blockQueue.poll(2L, TimeUnit.SECONDS));//过两秒都无法拿的话就null
		System.out.println(blockQueue.poll(2L, TimeUnit.SECONDS));//过两秒都无法拿的话就null
		System.out.println(blockQueue.poll(2L, TimeUnit.SECONDS));//过两秒都无法拿的话就null
		
		
		//阻塞态
		blockQueue.put("a");
		System.out.println(blockQueue.take());
		
	}
}
