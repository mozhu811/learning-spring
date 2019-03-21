package learningspring.aop.aspectj.xml.demo1;

/**
 * 书本类
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class Book{

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
        throw new RuntimeException("MyException");
    }
}
