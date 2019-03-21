package learningspring.aop.aspectj.annotation.demo1;

import org.springframework.stereotype.Component;

/**
 * 佛祖保佑，此代码无bug，
 * 就算有，也一眼看出。
 *
 * @author ray
 * @version 1.0
 * @date 2018-07-05 下午2:39
 **/

@Component
public class Book {

    /*
    切入点
     */

    public void add(){
        System.out.println("Book.add");
    }

    public Object delete(String id){
        System.out.println("Book.delete");
        return id;
    }

    public void modify(){
        System.out.println("Book.modify");
    }

    public void query(){
        System.out.println("Book.query");
    }

    public void throwException(){
        System.out.println("Book.throwException");
        // 抛出异常
        throw new RuntimeException("MyException");
    }
}
