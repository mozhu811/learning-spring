package learningspring.ioc.version1;

/**
 * 这是Ioc实现原理的发展过程一
 * 模拟在Servlet调用User类的方法
 * 按照这种方式，缺陷就是耦合度太高
 * 如果类名更改，后面的代码更改的地方过多
 *
 * 之后就演变成用工厂模式来解耦合
 * @see learningspring.ioc.version1.UserServlet
 *
 * @author ray
 * @version 1.0
 * @date 2018-06-10 下午11:44
 *
 **/

public class UserServlet {
    public static void main(String[] args) {

        User user = new User();
        user.sayHello();
    }
}
