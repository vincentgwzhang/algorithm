package algorithm.atguigu.proxy.staticproxy;

/**
 * 优点：没有修改原本的类，增加其他功能
 * 缺点：代理对象与目标对象需要同样的接口，一旦接口增加，那么两边都要维护
 */
public class Client {

    public static void main(String[] args) {
        TeacherDAO teacherDAO = new TeacherDAO();

        TeacherDAOProxy teacherDAOProxy = new TeacherDAOProxy(teacherDAO);

        teacherDAOProxy.teach();
    }

}
