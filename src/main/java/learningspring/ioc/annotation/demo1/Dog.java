package learningspring.ioc.annotation.demo1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Value 注解用于属性注入
 * 当类有提供set方法时添加在set方法上
 * 如果没有，则添加到属性上
 *
 * @author Chen Rui
 * @version 1.0
 **/

@Component("dog")
public class Dog {
    private String name;

    @Value("100")
    private Double length;

    public Dog() {
    }

    public Dog(String name, Double length) {
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    @Value("Golden")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", length=" + length +
                '}';
    }
}
