package learningspring.ioc.xml.demo1;

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

    @Test
    public void test(){
        // 创建IOC容器
        ApplicationContext ctx =
                new FileSystemXmlApplicationContext("F:\\Projects\\IdeaProjects\\LearningSpring\\src\\main\\java\\learningspring\\ioc\\examples\\applicationContext.xml");

        // 从IOC容器中获取bean实例
        People people = (People) ctx.getBean("people");

        System.out.println(people);
    }
}
