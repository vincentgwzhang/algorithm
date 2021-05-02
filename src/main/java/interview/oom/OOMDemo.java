package interview.oom;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class OOMDemo {
	
	@Test
	public void stackOverflowError() {
		//自己重复调用同一个方法
	}
	
	/**
	 * -Xms10m -Xmx10m
	 */
	@Test
	public void javaHeapSpace() {
		byte[] bytes = new byte[12*1024*1024];
	}
	
	/**
	 * java.lang.OutOfMemoryError: GC overhead limit exceeded
	 */
	@Test
	public void gcOverheadLimitExeeded() {
		//GC 回收时间过长, 定义是超过 98% 的时间做 GC
		List<String> list = new ArrayList<>();
		int i = 0;
		try {
			while(true) {
				list.add(String.valueOf(i++).intern());
			}
		} catch(Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	//-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
	/**
	 * java.lang.OutOfMemoryError: Direct buffer memory
	 * 就是说超过了允许使用的物理内存。在NIO里面，物理内存是能够直接分配而绕过GC 的，
	 * 而物理内存分配用尽以后，GC的空间就会被压缩，从而导致OOM
	 */
	@Test
	public void directBufferMemory() {
		
		try {
			ByteBuffer.allocateDirect(6 * 1024 * 1024);
		} catch(Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 高并发请求服务器时，经常出现如下异常， java.lang.outOfMemoryError: unable to create new native thread
	 * 
	 * 导致的原因：
	 * 1，你创建了过多的线程，超过系统极限。
	 * 2，Linux 系统默认允许单个进程可以创建最多1024个线程。
	 * 
	 */
	@Test
	public void unableToCreateNewNativeThread() {
		try {
			while(true) {
				new Thread(() -> {
					try {
						TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}) ;
			}
		} catch(Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=10m
	 * 
	 * Metaspace 与持久带最大的区别在于，metaspace 并不在虚拟机内存中而是使用被地内存，存放
	 * 1，虚拟机加载类的信息。
	 * 2，常量池
	 * 3，静态变量
	 * 4，即使编译后的代码
	 * 
	 * 错误：
	 * java.lang.OutOfMemoryError: Metaspace
	 */
	@Test
	public void testMetaSpace() {
		int i = 0;//模拟计算多少次以后发生异常
		try {
			while(true) {
				i++;
				Enhancer enhancer = new Enhancer();
				enhancer.setSuperclass(OOMTest.class);
				enhancer.setUseCache(false);
				enhancer.setCallback(new MethodInterceptor() {
					@Override
					public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
						System.out.println("I will be called everytime the object class called a function");
						return proxy.invokeSuper(obj, args);
					}});
				OOMTest test = (OOMTest)enhancer.create();
				test.display();
				test.display2();
			}
		} catch(Throwable e) {
			System.out.println(i);
			e.printStackTrace();
		}
	}
	
	static class OOMTest{
		public void display() {
			System.out.println("hello");
		}
		
		public void display2() {
			System.out.println("hello2");
		}
	}
}
