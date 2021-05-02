package interview.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * 用银行做例子：
 * 
 * public ThreadPoolExecutor(
 *    int corePoolSize,                     // 当天当值的窗口的数目
      int maximumPoolSize,                  // 银行总共拥有的窗口的数目
      long keepAliveTime,                   // 加班窗口空闲等待时间，如果超过了，那么就回收
      TimeUnit unit,                        // 时间单位
      BlockingQueue<Runnable> workQueue,    // 候客区
      ThreadFactory threadFactory,          // 生成线程的 factory
      RejectedExecutionHandler handler      // 如果人数越来越多，怎么个拒绝法（有四种实现）
      
      PS:
      1，只有候客区（阻塞队列）满了，才能通知。
      2，当超过候客区的时候，并且最大化窗口已经打开的时候，就会执行拒绝策略。
      
      
      4个线程池的拒绝策略：
      1，AbortPolicy: 默认。告诉虚拟机，拒绝运行。
      2，CallerRunsPolicy: 
      3，DiscardOldPolicy:
      4，DiscardPolicy:
      
      如何合理配置线程池:就是maximumPoolSize
      要看你的CPU密集型还是IO密集型，如果是CPU密集型就是Runtime.getRuntime().availableProcessors()+1
      所谓CPU密集就是基本只有运算
      
      如果是IO密集型的话，就是大量的IO阻塞那么就是 8 / (1 - 0.9) = 80 个线程
      
 *
 */
public class MyThreadPoolDemo {
	
	@Test
	public void testDemo1() {
		//线程池的底层是 ThreadPoolExecutor
		//适合一个一个任务地执行
		ExecutorService service = Executors.newFixedThreadPool(5);
		
		
		try {
			for(int index = 1; index <= 10; index ++) {
				service.execute(()->{
					System.out.println(Thread.currentThread().getName() + " is running");
				});
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			service.shutdown();
		}
	}
	
	@Test
	public void testDemo2() {
		//线程池的底层是 ThreadPoolExecutor
		//适合执行单一任务
		ExecutorService service = Executors.newSingleThreadExecutor();
		
		try {
			for(int index = 1; index <= 10; index ++) {
				service.execute(()->{
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + " is running");
				});
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				service.awaitTermination(20, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			service.shutdown();
		}
		System.out.print("Function run finish");
	}
	
	@Test
	public void testDemo3() {
		//线程池的底层是 ThreadPoolExecutor
		//使用SynchronousQueue的目的就是保证“对于提交的任务，
		//如果有空闲线程，则使用空闲线程来处理；否则新建一个线程来处理任务”。
		//适合短又快的任务堆，有扩容效果
		ExecutorService service = Executors.newCachedThreadPool();
		
		
		try {
			for(int index = 1; index <= 10; index ++) {
				service.execute(()->{
					System.out.println(Thread.currentThread().getName() + " is running");
				});
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			service.shutdown();
		}
	}
	
	/**
	 * AbortPolicy: 客满就抛出异常
	 * CallerRunsPolicy: 由主执行线程运行整个任务
	 * DiscardOldestPolicy, DiscardPolicy: 抛弃最旧的
	 */
	@Test
	public void testDemo4() {
		int CORE_SIZE = 2;
		int MAX_SIZE  = 5;
		int IDEL_SIZE = 3;
		long LIVE_TIME = 1L;
		ExecutorService service = new ThreadPoolExecutor(
				CORE_SIZE, 
				MAX_SIZE, 
				LIVE_TIME, 
				TimeUnit.SECONDS, 
				new LinkedBlockingQueue<Runnable>(IDEL_SIZE),//CAPACITY: 5 + 3
				Executors.defaultThreadFactory(),
				new ThreadPoolExecutor.DiscardPolicy());
		try {
			for(int index = 1; index <= 20; index ++) {
				service.execute(()->{
					System.out.println(Thread.currentThread().getName() + " is running 2");
				});
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			service.shutdown();
		}
	}
	
	@Test
	public void testUtils() {
		System.out.println(Runtime.getRuntime().availableProcessors());
	}
}
