package interview.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class CallableDemo1 {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<Integer> futureTask = new FutureTask<>(new MyThread1());
		new Thread(futureTask, "TT1").start();
		TimeUnit.SECONDS.sleep(3);
		new Thread(futureTask, "TT2").start();//只会运行一次，第二次是不会再运行的
		System.out.println(futureTask.get());//一旦实现有get, 那么线程就会阻塞
	}
	
}

class MyThread1 implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		System.out.println(Thread.currentThread().getName());
		return (int)System.currentTimeMillis() % 1000000;
	}
	
}