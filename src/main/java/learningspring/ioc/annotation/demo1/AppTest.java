package learningspring.ioc.annotation.demo1;

import learningspring.ioc.annotation.demo1.dao.UserDao;
import learningspring.ioc.annotation.demo1.dao.impl.UserDaoImpl;
import learningspring.ioc.annotation.demo1.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * //TODO
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class AppTest {

    /**
     * 传统方式
     */
    @Test
    public void test1(){
        UserDao userDao = new UserDaoImpl();
        userDao.save();
    }

    /**
     * Spring Ioc注解方式测试
     */
    @Test
    public void test2(){
        ApplicationContext ctx = new FileSystemXmlApplicationContext("F:\\Projects\\IdeaProjects\\LearningSpring\\src\\main\\java\\learningspring\\ioc\\examplesannotation\\demo1\\applicationContext.xml");
        UserDao userDao = (UserDao) ctx.getBean("userDao");
        userDao.save();

        Dog dog = (Dog) ctx.getBean("dog");
        System.out.println(dog);
    }

    /**
     * Spring 注解测试
     * Autowired测试
     */
    @Test
    public void test3(){
        ApplicationContext ctx = new FileSystemXmlApplicationContext("F:\\Projects\\IdeaProjects\\LearningSpring\\src\\main\\java\\learningspring\\ioc\\examplesannotation\\demo1\\applicationContext.xml");
        UserService userService = (UserService) ctx.getBean("userService");
        userService.save();
    }
}
