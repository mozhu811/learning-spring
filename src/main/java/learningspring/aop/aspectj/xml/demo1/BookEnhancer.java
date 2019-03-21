package learningspring.aop.aspectj.xml.demo1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;

/**
 * 书本类的增强类
 *
 * @author Chen Rui
 * @version 1.0
 **/

@Aspect
public class BookEnhancer{

    public void before(){
        System.out.println("Before……");
    }

    public void after(){
        System.out.println("After……");
    }

    public void afterThrowing(Exception e) throws Throwable{
        System.out.println("AfterThrowing……" + e.getMessage());
    }

    public void afterReturning(JoinPoint joinPoint, Object id){
        System.out.println("AfterReturning……" +id);
    }

    public void around(ProceedingJoinPoint jp) throws Throwable{
        System.out.println("Around……");
        jp.proceed();
        System.out.println("Around……");
    }
}
