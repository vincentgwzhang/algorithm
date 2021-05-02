package algorithm.atguigu.proxy.staticproxy;

/**
 * 同样实现同样的接口
 */
public class TeacherDAOProxy implements ITeacherDAO {

    private ITeacherDAO target; // 目标对象，通过接口聚合

    public TeacherDAOProxy(final ITeacherDAO target) {
        this.target = target;
    }

    @Override
    public void teach() {
        System.out.println("代理开始");
        target.teach();
        System.out.println("代理结束");
    }
}
