package learningspring.aop.proxy.jdk;

/**
 * 计算器接口
 *
 * @author Chen Rui
 * @version 1.0
 **/
public interface Calculator {

    /**
     * 加法
     * @param a 实数
     * @param b 实数
     * @return 相加结果
     */
    int add(int a, int b);

    /**
     * 减法
     * @param a 实数,被减数
     * @param b 实数,减数
     * @return 相减结果
     */
    int sub(int a, int b);

    /**
     * 乘法
     * @param a 实数
     * @param b 实数
     * @return 相乘结果
     */
    int mul(int a, int b);

    /**
     * 除法
     * @param a 实数,被除数
     * @param b 实数,除数
     * @return 相除结果
     */
    int div(int a, int b);
}
