package learningspring.ioc.xml.demo5;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Spring的复杂数据类型的注入
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class AppTest {

    /**
     * 注入数组类型
     */
    @Test
    public void test1(){
        ApplicationContext ctx = new FileSystemXmlApplicationContext("F:\\Projects\\IdeaProjects\\LearningSpring\\src\\main\\java\\learningspring\\ioc\\examples\\demo4\\applicationContext.xml");
        CollectionBean collectionBean = (CollectionBean) ctx.getBean("collectionBean");
        System.out.println(collectionBean);
    }

    /**
     * 注入集合类型
     */
    @Test
    public void test2(){

    }
}
