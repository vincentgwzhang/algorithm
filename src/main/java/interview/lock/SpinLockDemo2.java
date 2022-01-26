package interview.lock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemo2 {

	AtomicReference<Integer> value = new AtomicReference<>();

	public void set(int expected, int updated, int version) {
		while (!value.compareAndSet(expected, updated)) {
			try {
				System.out.println(Thread.currentThread().getName() + " :: " + version + " :: update from" + expected + " to " + updated);
				TimeUnit.SECONDS.sleep(new Random().nextLong(3) + 1);
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
				demo.set(1, 2, index);
			}
		},"AAA").start();

		new Thread(() -> {
			for (int index = 0; index < 10; index ++) {
				demo.set(2, 1, index);
			}
		},"BBB").start();

		while (Thread.activeCount() > 2) {
			TimeUnit.SECONDS.sleep(1);
		}
	}

}
