package learningspring.aop.aspectj.annotation.demo1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 佛祖保佑，此代码无bug，
 * 就算有，也一眼看出。
 *
 * @author ray
 * @version 1.0
 * @date 2018-07-05 下午2:58
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:mybean2.xml"})
public class AppTest {
    @Autowired
    private Book myBook;

    @Test
    public void testAspectj(){
        myBook.add();
        System.out.println("------");
        myBook.delete("1");
        System.out.println("------");
        myBook.modify();
        System.out.println("------");
        myBook.query();
        System.out.println("------");
        myBook.throwException();
    }

}
