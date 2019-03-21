package learningspring.ioc.xml.demo4;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Spring属性注入方式的测试类
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class AppTest {
    /**
     * 构造方法的属性注入方式
     */
    @Test
    public void test1(){
        ApplicationContext ctx = new FileSystemXmlApplicationContext("F:\\Projects\\IdeaProjects\\LearningSpring\\src\\main\\java\\learningspring\\ioc\\examples\\demo4\\applicationContext.xml");
        Car car = (Car) ctx.getBean("car");
        System.out.println(car);
    }

    /**
     * Set方法的属性注入方式
     */
    @Test
    public void test2(){
        ApplicationContext ctx = new FileSystemXmlApplicationContext("F:\\Projects\\IdeaProjects\\LearningSpring\\src\\main\\java\\learningspring\\ioc\\examples\\demo4\\applicationContext.xml");
        Dog dog = (Dog) ctx.getBean("dog");
        System.out.println(dog);
    }

    /**
     * 为Bean注入对象属性的测试
     */
    @Test
    public void test3(){
        ApplicationContext ctx = new FileSystemXmlApplicationContext("F:\\Projects\\IdeaProjects\\LearningSpring\\src\\main\\java\\learningspring\\ioc\\examples\\demo4\\applicationContext.xml");
        Employee employee = (Employee) ctx.getBean("employee");
        System.out.println(employee);
    }

    /**
     * p名称空间的属性注入方式
     */
    @Test
    public void test4(){
        ApplicationContext ctx = new FileSystemXmlApplicationContext("F:\\Projects\\IdeaProjects\\LearningSpring\\src\\main\\java\\learningspring\\ioc\\examples\\demo4\\applicationContext.xml");
        Cat cat = (Cat) ctx.getBean("cat");
        System.out.println(cat);
    }

    /**
     * SpEL表达式注入的方式
     */
    @Test
    public void test5(){
        ApplicationContext ctx = new FileSystemXmlApplicationContext("F:\\Projects\\IdeaProjects\\LearningSpring\\src\\main\\java\\learningspring\\ioc\\examples\\demo4\\applicationContext.xml");
        Cat cat = (Cat) ctx.getBean("cat2");
        System.out.println(cat);
    }
}
