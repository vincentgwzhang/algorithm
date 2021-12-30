package algorithm.atguigu.singleton;

public class EnumProvide {

    public static void main(String[] args) {
        Singleton5 singleton1 = Singleton5.getInstance();
        Singleton5 singleton2 = Singleton5.getInstance();

        System.out.println(singleton1 == singleton2);
    }

}

class Singleton5 {

    private static enum Demo {
        INSTANCE;

        private Singleton5 singleton5;

        private Demo() {
            singleton5 = new Singleton5();
        }
    }

    public static Singleton5 getInstance() {
        return Demo.INSTANCE.singleton5;
    }
}