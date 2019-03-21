package learningspring.ioc.version2;

/**
 * 这是Ioc实现原理的发展过程二
 * 使用工厂类解耦合操作
 * 但是这样的方式产生了新的耦合
 * UserServlet和UserFactory的耦合度又高了
 *
 * ioc实现
 * @see learningspring.ioc.version3.UserServlet
 *
 * @author ray
 * @version 1.0
 * @date 2018-06-10 下午11:52
 **/

public class UserServlet {

    public static void main(String[] args) {

        User user = UserFactory.getUser();
        user.sayHello();
    }
}
