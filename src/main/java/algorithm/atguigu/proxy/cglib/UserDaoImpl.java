package algorithm.atguigu.proxy.cglib;

public class UserDaoImpl implements UserDao {
    public void save() {
        System.out.println("保存数据方法");
    }
}