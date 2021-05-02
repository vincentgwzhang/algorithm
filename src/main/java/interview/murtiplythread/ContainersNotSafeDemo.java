package interview.murtiplythread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ContainersNotSafeDemo {
	
	@Test
	public void testOnCollections() throws InterruptedException {
		List<String> lists = Collections.synchronizedList(new ArrayList<>());
		
		for(int index = 0; index < 3; index ++) {
			new Thread(()->{
				lists.add(System.currentTimeMillis() + "");
			}).start();
		}
		while(Thread.activeCount()>2) {
			TimeUnit.SECONDS.sleep(1);
		}
		System.out.println(lists);
	}
	
	/**
	 * CopyOnWriteArrayList :
	 * 1, 线程安全的。
	 * 2，因为读写分离的。写的时候，把底层的数据组复印一份出来写，写完后删除原本的数组。
	 * 3, 开销很大
	 */
	@Test
	public void testOnCopyOnWrite() {
		List<String> list = new CopyOnWriteArrayList<>();
	}
	
}
