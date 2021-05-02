package algorithm.atguigu.proxy.dynamicproxy;

public class Client {

    public static void main(String[] args) {
        ProxyFactory proxy = new ProxyFactory(new TeacherDAO());
        ITeacherDAO iTeacherDAO = (ITeacherDAO)proxy.getProxyInstance();
        System.out.println(iTeacherDAO.toString());
        iTeacherDAO.teach();
    }

}
