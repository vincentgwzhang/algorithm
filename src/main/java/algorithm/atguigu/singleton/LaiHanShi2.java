package algorithm.atguigu.singleton;

public class LaiHanShi2
{
    public static void main(String[] args)
    {
        Singleton4 instance = Singleton4.getInstance();
        Singleton4 instance2 = Singleton4.getInstance();
        System.out.println(instance == instance2); // true
        System.out.println("instance.hashCode=" + instance.hashCode());
        System.out.println("instance2.hashCode=" + instance2.hashCode());
    }
}

class Singleton4
{
    private static volatile Singleton4 instance;

    private Singleton4()
    {
    }

    public static Singleton4 getInstance()
    {
        if(instance == null) {
            synchronized (Singleton4.class) {
                if(instance == null) {
                    instance = new Singleton4();
                }
            }

        }
        return instance;
    }
}