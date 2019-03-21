package learningspring.aop.aspectj.annotation.demo2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * AspectJ的注解方式配置测试类
 *
 * @author Chen Rui
 * @version 1.0
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:aspectj-annotation.xml")
public class AppTest {

    @Resource(name = "productDao")
    private ProductDao productDao;

    @Test
    public void test(){

        productDao.save();

        productDao.delete();

        productDao.modify();

        productDao.query();
    }
}
