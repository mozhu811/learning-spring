package learningspring.ioc.xml.demo3;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Spring Bean的实例化方式的测试
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class AppTest {

    /**
     * 无参构造实例化方式
     */
    @Test
    public void test1(){
        ApplicationContext ctx = new FileSystemXmlApplicationContext("F:\\Projects\\IdeaProjects\\LearningSpring\\src\\main\\java\\learningspring\\ioc\\examples\\demo3\\applicationContext.xml");
        Dog dog = (Dog) ctx.getBean("dog");
        System.out.println(dog);
    }

    /**
     * 静态工厂的实例化方式
     */
    @Test
    public void test2(){
        ApplicationContext ctx = new FileSystemXmlApplicationContext("F:\\Projects\\IdeaProjects\\LearningSpring\\src\\main\\java\\learningspring\\ioc\\examples\\demo3\\applicationContext.xml");
        Car car = (Car) ctx.getBean("car");
        System.out.println(car);
    }

    /**
     * 实例工厂的实例化方式
     */
    @Test
    public void test3(){
        ApplicationContext ctx = new FileSystemXmlApplicationContext("F:\\Projects\\IdeaProjects\\LearningSpring\\src\\main\\java\\learningspring\\ioc\\examples\\demo3\\applicationContext.xml");
        Dog dog = (Dog) ctx.getBean("dog2");
        System.out.println(dog);
    }
}
