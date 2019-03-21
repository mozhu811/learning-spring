package learningspring.aop.aspectj.xml.demo1;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * AspectJ的XML方式的测试
 *
 * @author Chen Rui
 * @version 1.0
 **/

public class AppTest{

    @Test
    public void testAspectj(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("mybean1.xml");
        Book myBook = (Book) context.getBean("book");

        /*
        Before……
        Around……
        Book.add
        Around……
        After……
        ------
        Before……
        Around……
        Book.delete
        Around……
        AfterReturning……null (与环绕通知同时用会变为null，暂时未知原因)
        After……
        ------
        Before……
        Around……
        Book.modify
        Around……
        After……
        ------
        Before……
        Around……
        Book.query
        Around……
        After……
        ------
        Before……
        Around……
        Book.throwException
        AfterThrowing……MyException
        After……
         */
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
