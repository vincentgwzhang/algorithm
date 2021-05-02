package interview.gc;

import java.util.Random;

import org.junit.Test;

public class GCDemo {
	
	/**
	 * -XX:+UseSerialGC   串行垃圾回收器
	 * -XX:+UseParallelGC 并行垃圾回收器
	 * -XX:+UseConcMarkSweepGC 并发清除垃圾回收器
	 * 
	 * 注意：串行老年代GC已经被丢弃
	 * @param args
	 */
	public static void main(String[] args) {
		//默认是并行垃圾回收器
	}
	
	/**
	 * 如果新生代使用了Serial的垃圾回收机制，那么老年代也会自动使用Serial Old垃圾回收机制
	 * 对应参数： -XX:+UseSerialGC
	 * 总参数： -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC
	 */
	
	/**
	 *
Heap
 def new generation(这个代表串行垃圾收集器)   total 3072K, used 134K [0x00000000ff600000, 0x00000000ff950000, 0x00000000ff950000)
  eden space 2752K,   4% used [0x00000000ff600000, 0x00000000ff621a98, 0x00000000ff8b0000)
  from space 320K,   0% used [0x00000000ff900000, 0x00000000ff900000, 0x00000000ff950000)
  to   space 320K,   0% used [0x00000000ff8b0000, 0x00000000ff8b0000, 0x00000000ff900000)
 tenured generation(这个代表串行垃圾收集器)   total 6848K, used 5368K [0x00000000ff950000, 0x0000000100000000, 0x0000000100000000)
   the space 6848K,  78% used [0x00000000ff950000, 0x00000000ffe8e188, 0x00000000ffe8e200, 0x0000000100000000)
 Metaspace       used 4977K, capacity 5108K, committed 5248K, reserved 1056768K
  class space    used 586K, capacity 624K, committed 640K, reserved 1048576K
	 */
	@Test
	public void testNewGenerationGC_Serial() {
		try {
			String str = "atguigu";
			while(true) {
				str += str + new Random().nextInt(777777) + new Random().nextInt(888888);
				str.intern();
			}
		} catch(Throwable e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ParNew 就是新生代的并行垃圾收集器，
	 * 如果新生代使用了 ParNew 的垃圾回收机制，那么老年代也会自动使用 CMS 垃圾回收机制
	 * 对应参数： -XX:+UseParNewGC -XX:ParallelGCThreads=8 ==> 指定多少并行的线程，一般和CPU数一样
	 * 总参数： -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC -XX:ParallelGCThreads=8
	 */
	@Test
	public void testNewGenerationGC_Parallel() {
		try {
			String str = "atguigu";
			while(true) {
				str += str + new Random().nextInt(777777) + new Random().nextInt(888888);
				str.intern();
			}
		} catch(Throwable e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Parallel Scavenge 在新生代和老生代的串行收集器并行化
	 * 如果新生代使用了 Parallel Scavenge 的垃圾回收机制，那么老年代也会自动使用  Parallel Old 垃圾回收机制
	 * 对应参数： -XX:+UseParallelGC -XX:+UseParallelOldGC
	 * 总参数： -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC
	 * 这也是JDK8 以后的标准
	 */
	/**
	 * Heap
 PSYoungGen      total 2048K, used 119K [0x00000000ffd00000, 0x0000000100000000, 0x0000000100000000)
  eden space 1024K, 11% used [0x00000000ffd00000,0x00000000ffd1de10,0x00000000ffe00000)
  from space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
  to   space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
 ParOldGen       total 7168K, used 5469K [0x00000000ff600000, 0x00000000ffd00000, 0x00000000ffd00000)
  object space 7168K, 76% used [0x00000000ff600000,0x00000000ffb57408,0x00000000ffd00000)
 Metaspace       used 4982K, capacity 5108K, committed 5248K, reserved 1056768K
  class space    used 586K, capacity 624K, committed 640K, reserved 1048576K
	 */
	@Test
	public void testNewGenerationGC_Scavenge() {
		try {
			String str = "atguigu";
			while(true) {
				str += str + new Random().nextInt(777777) + new Random().nextInt(888888);
				str.intern();
			}
		} catch(Throwable e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 总参数： -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC
Heap
 garbage-first heap   total 10240K, used 4096K [0x00000000ff600000, 0x00000000ff700050, 0x0000000100000000)
  region size 1024K, 5 young (5120K), 0 survivors (0K)
 Metaspace       used 4965K, capacity 5114K, committed 5248K, reserved 1056768K
  class space    used 584K, capacity 625K, committed 640K, reserved 1048576K
  
  跟CMS比较，较少产生碎片，可以指定期望停顿时间，更高性能，Eden, Servivior 和 Tenurd 内存区域不再连续
  而是变成了一个个的 Region, 每个region 1M-32M
  整体上使用标记整理，并且不在分年轻代，年老代，而是把整个堆划分为若干个区域，每个区域1-32M, 最多设置2048个区域。也即是说最大支持 32M * 2048 = 64G 的堆
  但它依然是分代收集，因为一个区域要么是Eden 区，要么是 Old 区。
  
	 */
	@Test
	public void testG1GC() {
//		try {
//			String str = "atguigu";
//			while(true) {
//				str += str + new Random().nextInt(777777) + new Random().nextInt(888888);
//				str.intern();
//			}
//		} catch(Throwable e) {
//			e.printStackTrace();
//		}
	}

}
