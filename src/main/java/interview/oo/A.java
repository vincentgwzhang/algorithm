package interview.oo;

public class A {

    static {
        System.out.println("这是第1条输出，因为 A 先 load 进内存");
    }

    public A() {
        System.out.println("这是第3条输出");
    }

}
