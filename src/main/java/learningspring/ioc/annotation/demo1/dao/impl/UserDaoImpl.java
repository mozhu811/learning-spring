package learningspring.ioc.annotation.demo1.dao.impl;

import learningspring.ioc.annotation.demo1.dao.UserDao;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 实现类
 * Component 注解相当于<bean id="userDao" class="learningspring.ioc.examplesannotation.demo1.dao.impl.Calculator"></bean>
 * @author Chen Rui
 * @version 1.0
 **/

@Component("userDao")
@Scope
public class UserDaoImpl implements UserDao {

    @PostConstruct
    public void init(){
        System.out.println("Calculator.init");
    }

    @Override
    public void save() {
        System.out.println("Calculator.save");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Calculator.destroy");
    }
}
