package learningspring.ioc.version3;


/**
 * 佛祖保佑，此代码无bug，
 * 就算有，也一眼看出。
 *
 * @author ray
 * @version 1.0
 * @date 2018-06-11 上午12:20
 **/
public class UserServlet {
    public static void main(String[] args){
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("/Users/ray/IdeaProjects/LearningSpring/src/main/java/learningspring/ioc/version3/mybean.xml");
        User user = (User) beanFactory.getBean("user");
        user.sayHello();
    }
}
