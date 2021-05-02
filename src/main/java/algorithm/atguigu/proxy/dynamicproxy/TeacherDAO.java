package algorithm.atguigu.proxy.dynamicproxy;

public class TeacherDAO implements ITeacherDAO{

    @Override
    public void teach() {
        System.out.println("老师授课中...");
    }
}
