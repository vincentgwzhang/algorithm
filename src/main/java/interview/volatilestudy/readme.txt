volatile 是Java VM 提供的轻量级机制。具有三大特性：
1，保证可见性。就是说线程修改完某个变量以后，其他的线程必须马上获得通知。
2，不保证原子性。轻量级，就是不保证原子性。
3，禁止指令重排。(prevent compiler reorder code)

JMM Java 内存模型。是一种规范，就是：
1，线程