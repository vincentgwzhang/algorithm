package algorithm.atguigu.proxy.dynamicproxy;

import java.lang.reflect.Proxy;

public class ProxyFactory {

    // 维护目标对象
    private Object target;

    public ProxyFactory(final Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), (proxy, method, args) -> {
            System.out.println("代理开始");
            Object value = method.invoke(target, args);
            System.out.println("代理开始");
            return value;
        });
    }
}
