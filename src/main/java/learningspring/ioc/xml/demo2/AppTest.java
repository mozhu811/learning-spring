package learningspring.ioc.xml.demo2;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Spring入门
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class AppTest {

    /**
     * 传统的调用方式
     *
     * 弊端：如果底层的实现方式更换了，需要修改源代码
     */
    @Test
    public void test1(){
        UserDao userDao = new UserDaoImpl();
        userDao.save();
    }

    /**
     * 例如，从JDBC更换成Hibernate实现
     */
    @Test
    public void test2(){
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.save();
    }

    /**
     * Spring配置方式的实现
     *
     * 现在如果要切换实现类，可以直接修改applicationContext.xml里的配置就可以实现。
     */
    @Test
    public void test3(){
        ApplicationContext ctx = new FileSystemXmlApplicationContext("F:\\Projects\\IdeaProjects\\LearningSpring\\src\\main\\java\\learningspring\\ioc\\examples\\demo2\\applicationContext.xml");
        UserDao userDao = (UserDao) ctx.getBean("userDao");
        userDao.save();
    }

    /**
     * Spring bean的生命周期案例测试
     */
    @Test
    public void test4(){
        ApplicationContext ctx = new FileSystemXmlApplicationContext("F:\\Projects\\IdeaProjects\\LearningSpring\\src\\main\\java\\learningspring\\ioc\\examples\\demo2\\applicationContext.xml");
        ProductDao productDao = (ProductDao) ctx.getBean("productDao");
        productDao.save();
        ((FileSystemXmlApplicationContext) ctx).close();
    }

    /**
     * Bean的作用域测试
     * 单例模式，查看对象地址是否相同
     */
    @Test
    public void test5(){
        ApplicationContext ctx = new FileSystemXmlApplicationContext("F:\\Projects\\IdeaProjects\\LearningSpring\\src\\main\\java\\learningspring\\ioc\\examples\\demo2\\applicationContext.xml");
        ProductDao productDao = (ProductDao) ctx.getBean("productDao");
        ProductDao productDao1 = (ProductDao) ctx.getBean("productDao");
        System.out.println(productDao.toString());
        System.out.println(productDao1.toString());
        System.out.println(productDao == productDao1);
        ((FileSystemXmlApplicationContext) ctx).close();
    }
}
