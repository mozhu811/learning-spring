package learningspring.aop.aspectj.xml.demo2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Date;

/**
 * ProductDao的增强类(切面类)
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class ProductEnhancer {

    /**
     * 前置增强案例
     * 在调用save方法之前进行权限校验
     * @param joinPoint 切入点对象
     */
    public void checkPri(JoinPoint joinPoint){
        System.out.println("【前置增强】权限校验" + joinPoint);
    }

    /**
     * 后置增强案例
     * 在调用delete方法之后，写入日志记录操作时间
     * @param result 目标方法返回的对象
     */
    public void writeLog(Object result){
        System.out.println("【后置增强】写入日志 操作时间：" + result.toString());
    }

    /**
     * 环绕增强
     * 在调用modify方法前后，显示性能参数
     * @param joinPoint 切入点对象
     * @throws Throwable 可抛出的异常
     */
    public Object monitor(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("【环绕增强】当前空闲内存" + Runtime.getRuntime().freeMemory()/(1024 * 1024) + "MB");
        Object obj = joinPoint.proceed();
        System.out.println("【环绕增强】当前空闲内存" + Runtime.getRuntime().freeMemory()/(1024 * 1024) + "MB");
        return obj;
    }

    /**
     * 异常抛出增强
     * 在调用query时若抛出异常则打印异常信息
     * @param ex 异常对象
     */
    public void exception(Throwable ex){
        System.out.println("【异常抛出增强】" + "异常信息：" +ex.getMessage());
    }

    /**
     * 最终增强
     * 无论query方法是否抛出异常都打印当前时间
     */
    public void finallyAdvice(){
        System.out.println("【最终增强】" + new Date().toString());
    }
}
