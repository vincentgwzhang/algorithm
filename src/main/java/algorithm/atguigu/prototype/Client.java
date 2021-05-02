package algorithm.atguigu.prototype;

public class Client
{
    public static void main(String[] args) {
        Sheep sheep = new Sheep("tom", 1, "blue");
        Sheep friendSheep = new Sheep("jack", 2, "green");
        sheep.setFriend(friendSheep);

        Sheep sheep2 = (Sheep)sheep.clone();
        Sheep sheep3 = (Sheep)sheep.clone();
        Sheep sheep4 = (Sheep)sheep.clone();
        Sheep sheep5 = (Sheep)sheep.clone();

        System.out.println("sheep2 =" + sheep2 + "sheep2.friend=" + sheep2.getFriend().hashCode());
        System.out.println("sheep3 =" + sheep3 + "sheep3.friend=" + sheep3.getFriend().hashCode());
        System.out.println("sheep4 =" + sheep4 + "sheep4.friend=" + sheep4.getFriend().hashCode());
        System.out.println("sheep5 =" + sheep5 + "sheep5.friend=" + sheep5.getFriend().hashCode());
        System.out.println("sheep5 =" + sheep5 + "sheep5.friend=" + sheep5.getFriend().hashCode());
        System.out.println("friendSheep=" + friendSheep.hashCode());
    }
}
