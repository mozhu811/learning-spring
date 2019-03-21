package learningspring.aop.proxy.cglib;

import org.junit.Test;

/**
 * //TODO
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class AppTest {

    @Test
    public void test(){
        Calculator calculator = new Calculator();
        Calculator proxy = new CalculatorProxy(calculator).createProxy();

        int x = 20;
        int y = 20;
        System.out.println("res--> " + proxy.add(x, y));

        System.out.println("res--> " + proxy.mul(x, y));

        System.out.println("res--> " + proxy.sub(x, y));

        System.out.println("res--> " + proxy.div(x, y));
    }
}
