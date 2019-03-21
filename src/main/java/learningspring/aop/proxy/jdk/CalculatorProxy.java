package learningspring.aop.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Date;

/**
 * 计算器代理类
 * 实现扩展打印日志功能
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class CalculatorProxy {
    /**
     * 被代理的对象
     */
    private Calculator target;

    public CalculatorProxy(Calculator target) {
        this.target = target;
    }

    public Calculator createProxy(){
        Calculator proxy;

        ClassLoader classLoader = target.getClass().getClassLoader();

        Class[] interfaces = new Class[]{Calculator.class};

        InvocationHandler handler = new InvocationHandler() {
            /**
             *
             * @param proxy     正在返回的代理对象，一般在invoke方法中都不使用该对象
             *                  如果使用该对象，则会引发栈内存溢出。因为会循环调用invoke方法。
             * @param method    正在被调用的方法
             * @param args      调用方式时传入的参数
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 获取方法名
                String methodName = method.getName();
                // 输出日志逻辑
                System.out.println(new Date() + ": The method " + methodName + " begins with " + Arrays.asList(args));
                // 执行方法
                Object result = method.invoke(target, args);
                // 输出日志逻辑
                System.out.println(new Date() + ": The method " + methodName + " ends with " + result);
                return result;
            }
        };

        proxy = (Calculator) Proxy.newProxyInstance(classLoader,interfaces,handler);

        return proxy;
    }
}
