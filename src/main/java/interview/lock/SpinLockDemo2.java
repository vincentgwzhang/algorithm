package interview.lock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemo2 {

	AtomicReference<Integer> value = new AtomicReference<>();

	public void set(int expected, int updated) {
		while (!value.compareAndSet(expected, updated)) {
			try {
				TimeUnit.SECONDS.sleep(new Random().nextLong(3));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		SpinLockDemo2 demo = new SpinLockDemo2();
		demo.value.set(1);

		new Thread(() -> {
			for (int index = 0; index < 10; index ++) {
				System.out.println("AAA start, change 1 -> 2");
				demo.set(1, 2);
			}
		},"AAA").start();

		new Thread(() -> {
			for (int index = 0; index < 10; index ++) {
				System.out.println("BBB start, change 2 -> 1");
				demo.set(2, 1);
			}
		},"BBB").start();

		while (Thread.activeCount() > 2) {
			TimeUnit.SECONDS.sleep(1);
		}
	}

}
