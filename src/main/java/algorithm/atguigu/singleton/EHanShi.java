package algorithm.atguigu.singleton;

/**
 * 造成内存浪费，因为即使不用，在加载的时候都会创建一个新的
 */
public class EHanShi {

    public static void main(String[] args) {
        Singleton1 instance = Singleton1.getInstance();
        Singleton1 instance2 = Singleton1.getInstance();
        System.out.println(instance == instance2);
        System.out.println("instance.hashCode=" + instance.hashCode());
        System.out.println("instance2.hashCode=" + instance2.hashCode());
    }

}

class Singleton1 {

    private Singleton1() {

    }

    private final static Singleton1 instance = new Singleton1();

    public static Singleton1 getInstance() {
        return instance;
    }

}