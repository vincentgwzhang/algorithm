package interview.oo;

public class B extends A{

    static {
        System.out.println("这是第2条输出，因为轮到B load进内存");
    }

    public B() {
        System.out.println("这是第4条输出");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("B要被淘汰");
        super.finalize();
    }
}
