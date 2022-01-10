package interview.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * cyclicBarrier 就是它动完了，其他线程才动
 * @author gzhang
 *
 */
public class CyclicBarrierDemo {
	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
			System.out.println("=================start");
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("=================end");
		});

		for (int i = 0; i < 3; i++) {
			new Thread(() -> {
				try {
					System.out.println("I am thread " + Thread.currentThread().getName() + ", doing anything, start");
					cyclicBarrier.await();// 所有线程来到某个交汇点，然后才运行上面的代码，等待上面那个函数完成才继续
					System.out.println("I am thread " + Thread.currentThread().getName() + ", doing anything, finish");
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
			}, String.valueOf(i)).start();
		}
	}

}