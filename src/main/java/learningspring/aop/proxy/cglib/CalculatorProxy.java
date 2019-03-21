package learningspring.aop.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

/**
 * 计算器代理类
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class CalculatorProxy implements MethodInterceptor {

    /**
     * 被代理的对象
     */
    private Calculator target;

    public CalculatorProxy(Calculator target) {
        this.target = target;
    }

    public Calculator createProxy(){

        // 1.创建cglib的核心类对象
        Enhancer enhancer = new Enhancer();

        // 2.设置父类
        enhancer.setSuperclass(target.getClass());

        // 3.设置回调（类似于jdk动态代理中的InvocationHandler对象）
        enhancer.setCallback(this);

        // 4.创建代理对象
        Calculator proxy = (Calculator) enhancer.create();

        return proxy;
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        // 获取方法名
        String methodName = method.getName();
        // 输出日志逻辑
        System.out.println(new Date() + ": The method " + methodName + " begins with " + Arrays.asList(args));
        // 执行方法
        Object result = methodProxy.invokeSuper(proxy, args);
        // 输出日志逻辑
        System.out.println(new Date() + ": The method " + methodName + " ends with " + result);
        return result;
    }
}
