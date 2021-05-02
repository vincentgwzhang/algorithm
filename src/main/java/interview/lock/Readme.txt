公平锁就是多线程进入一个私人空间资源的时候，按照到达的时间顺序一个一个获取
非公平锁的意思就是可以允许问问题加塞(重点就是重点)

ReentrantLock 默认是非公平锁 

//==================================================//
如果一个线程进入method01 的时候，他就能自动获取method02, 因为其他的线程也无法进入method02了
public synchronized void method01() {
    method02();
}

public synchronized void method02() {
}
//==================================================//

自旋锁 spin lock (CAS就是自旋锁)，下面就是自旋锁的例子
public final int getAndAddInt(Object var1, long var2, int var4) {
   int var5;
   do {
       var5 = this.getIntVolatile(var1, var2);
   } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
   return var5;
}


可重入锁就是同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁

