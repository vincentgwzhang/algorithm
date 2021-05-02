package interview.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ReferenceDemo {
	
	/**
	 * 软引用使用空间：如果加载大量图片，每次都读，性能不好，只能加载缓存里面。这个时候有软引用就比较
	 * @throws InterruptedException
	 */
	//-Xms5m -Xmx5m
	@Test
	public void soft_reference() throws InterruptedException {
		Object o1 = new Object();
		SoftReference<Object> softReference = new SoftReference<>(o1);
		System.out.println(o1);
		System.out.println(softReference.get());
		
		o1 = null;
		System.gc();
		
		try {
			byte[] bytes = new byte[4 *1024*1024];
		} catch(Throwable e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println(o1);
			System.out.println(softReference.get());//产生了GC, 被回收了
		}
	}
	
	@Test
	public void week_reference() {
		Object o1 = new Object();
		WeakReference<Object> reference = new WeakReference<>(o1);
		
		o1 = null;
		System.gc();
		System.out.println(reference.get());
	}
	
	@Test
	public void week_reference_queue() throws InterruptedException {
		Object o1 = new Object();
		ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
		WeakReference<Object> reference = new WeakReference<>(o1, referenceQueue);
		System.out.println(o1);
		System.out.println(reference);//java.lang.ref.WeakReference@6e5e91e4
		System.out.println(reference.get());
		System.out.println(referenceQueue.poll());
		
		System.out.println("========================");
		o1 = null;
		System.gc();
		Thread.sleep(500);
		
		System.out.println(o1);
		System.out.println(reference.get());
		/**
		 * 对于弱引用，在 GC 回收之前，会被放入引用队列，这就是整个弱引用
		 */
		System.out.println(referenceQueue.poll());//java.lang.ref.WeakReference@6e5e91e4
	}
	
	@Test
	public void phamtom_referece() throws InterruptedException {
		Object o1 = new Object();
		ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
		PhantomReference<Object> reference = new PhantomReference<>(o1, referenceQueue);
		System.out.println(o1);
		System.out.println(reference);//java.lang.ref.PhantomReference@6e5e91e4
		System.out.println(reference.get());//永远是 null
		System.out.println(referenceQueue.poll());//null
		
		System.out.println("========================");
		o1 = null;
		System.gc();
		Thread.sleep(500);
		
		System.out.println(o1);
		System.out.println(reference.get());
		/**
		 * 对于弱引用，在 GC 回收之前，会被放入引用队列，这就是整个弱引用
		 */
		System.out.println(referenceQueue.poll());//java.lang.ref.PhantomReference@6e5e91e4
	}
	
}
