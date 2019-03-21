package learningspring.aop.proxy.cglib;

/**
 * 计算器类
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int sub(int a, int b) {
        return a - b;
    }

    public int mul(int a, int b) {
        return a * b;
    }

    public int div(int a, int b) {
        if (b == 0){
            System.out.println("除数不能为0");
            return 0;
        }
        return  a / b;
    }
}
