package learningspring.aop.proxy.jdk;

import org.junit.Test;

/**
 * @author Chen Rui
 * @version 1.0
 **/
public class AppTest {

    @Test
    public void test() {
        Calculator target = new CalculatorImpl();
        Calculator proxy = new CalculatorProxy(target).createProxy();

        int a = 10;
        int b = 10;
        System.out.println("res --> " + proxy.add(a, b));

        System.out.println("res --> " + proxy.mul(a, b));

        System.out.println("res --> " + proxy.sub(a, b));

        System.out.println("res --> " + proxy.div(a, b));
    }
}
