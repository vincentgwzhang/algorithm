package interview.casstudy;

//import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeExample {
//    static Unsafe U;
//    int x = 2;
//
//    static {
//        Field f = null;
//        try {
//            f = Unsafe.class.getDeclaredField("theUnsafe");
//            f.setAccessible(true);
//            U = (Unsafe) f.get(null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) throws Throwable {
//        long offset = U.objectFieldOffset(UnsafeExample.class.getDeclaredField("x"));
//
//        UnsafeExample example = new UnsafeExample();
//        boolean ret;
//
//        ret = U.compareAndSwapInt(example, offset, 2, 3);
//        System.out.println("offset: {" + offset + "}, ret: {" + ret + "}, x: {" + example.x + "}");
//
//        ret = U.compareAndSwapInt(example, offset, 4, 5);
//        System.out.println("offset: {" + offset + "}, ret: {" + ret + "}, x: {" + example.x + "}");
//    }
}
