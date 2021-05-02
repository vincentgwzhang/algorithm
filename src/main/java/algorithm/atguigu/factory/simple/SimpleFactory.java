package algorithm.atguigu.factory.simple;

public class SimpleFactory
{

    public static Item createItem(String name) {
        if (name.equalsIgnoreCase("item1")) {
            return new Item1();
        } else if (name.equalsIgnoreCase("item2")) {
            return new Item2();
        } else {
            return new Item3();
        }
    }

    static class Item {}

    public static class Item1 extends Item {}
    public static class Item2 extends Item {}
    public static class Item3 extends Item {}
}
