package interview.oo;

public class B extends A{

    static {
        System.out.println("a");
    }

    public B() {
        System.out.println("b");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
