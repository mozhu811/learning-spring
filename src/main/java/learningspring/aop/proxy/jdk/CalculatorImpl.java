package learningspring.aop.proxy.jdk;

/**
 * 计算器实现类
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class CalculatorImpl implements Calculator {

    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int sub(int a, int b) {
        return a - b;
    }

    @Override
    public int mul(int a, int b) {
        return a * b;
    }

    @Override
    public int div(int a, int b) {
        if (b == 0){
            System.out.println("除数不能为0");
            return 0;
        }
        return  a / b;
    }
}
