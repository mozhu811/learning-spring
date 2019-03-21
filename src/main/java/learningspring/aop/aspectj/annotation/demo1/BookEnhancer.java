package learningspring.aop.aspectj.annotation.demo1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Before：在方法之前执行
 * After：在方法之后执行，无论成功与否
 * AfterThrowing：方法出现异常执行
 * AfterReturning：方法执行成功之后执行
 * Around：在方法之前和之后执行
 **/

@Component
@Aspect
public class BookEnhancer {

    @Pointcut("execution(* learningspring.aop.aspectj.annotation.demo1.Book.*(..))")
    private void myPointCut1(){}

    @Pointcut("execution(* learningspring.aop.aspectj.annotation.demo1.Book.delete(..))")
    private void myPointCut2(){}

    @Before("myPointCut1()")
    public void before(){
        System.out.println("Before……");
    }

    @After("myPointCut1()")
    public void after(){
        System.out.println("After……");
    }

    @AfterThrowing(throwing = "e", pointcut = "myPointCut1()")
    public void afterThrowing(Exception e) throws Throwable{
        System.out.println("AfterThrowing……" + e.getMessage());
    }

    @AfterReturning(returning = "id", pointcut = "myPointCut2()")
    public void afterReturning(JoinPoint joinPoint, Object id){
        System.out.println("AfterReturning……" +id);
    }

    @Around("myPointCut1()")
    public Object around(ProceedingJoinPoint jp) throws Throwable{
        System.out.println("Around……");
        jp.proceed();
        System.out.println("Around……");
        return new Object();
    }
}
