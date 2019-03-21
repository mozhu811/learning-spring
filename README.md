#Spring框架学习笔记

[TOC]

# IOC 和 DI 的概述

## IOC(Inversion of Controll)

**思想是反转资源获取的方向**，传统的资源查找方式要求组件向容器发起请求查找资源。作为回应，容器适时的返回资源。而应用了IOC之后，则是**容器主动的将资源推送给它所管理的组件，组件所要做的仅是选择一种合适的方式来接收资源**

## DI(Dependency Injection)

是IOC的另一种表述方式，即**组件以一些预先定义好的方式(如：getter方法)来接收来自容器的资源注入**

<!-- more -->

## IOC的前身

### 分离接口与实现

### 采用工厂设计模式

### 采用反转控制

# Spring配置

在SpringIOC容器读取bean配置创建bean实例之前，必须对它进行实例化。只有在容器实例化后，才可以从IOC容器里获取bean实例并使用

Spring提供了两种类型的IOC容器实现

+ **BeanFactory：IOC容器的基本实现，在调用getBean()方法时才会实例化对象**
+ **ApplicationContext：提供了更多的高级特性，在加载配置文件后就会实例化对象。是BeanFactory的子接口**

`BeanFactory`是Spring框架的基础设施，面向Spring本身

`ApplicationContext`面向使用Spring框架的开发者，几乎所有的应用场合都直接使用`ApplicationContext`而非底层的`BeanFactory`

**无论使用何种方式，配置文件时都是相同的**

```xml
<!-- 配置bean -->
<!-- class: bean的全类名，通过反射的方式在IOC容器中创建bean，所以要求bean中必须有无参构造器 -->
<bean id="people" class="learningspring.ioc.examples.People">
    <property name="name" value="Chen"/>
</bean>
```

```java
public class People {
    private String name;

    public People() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                '}';
    }
}
```

```java
@Test
public void test(){
    // 创建IOC容器
    ApplicationContext ctx = new FileSystemXmlApplicationContext("F:\\Projects\\IdeaProjects\\LearningSpring\\src\\main\\java\\learningspring\\ioc\\examples\\applicationContext.xml");

    // 从IOC容器中获取bean实例
    People people = (People) ctx.getBean("people");

    System.out.println(people);
}
```

## ApplicationContext

`ApplicationContext`有两个实现类：

+ `ClassPathXmlApplicationContext`：加载类路径里的配置文件
+ `FileSystemXmlApplicationContext`：加载文件系统里的配置文件

## Bean的相关配置

### bean标签的id和name的配置

+ `id`：使用了约束中的唯一约束。不能有特殊字符
+ `name`：没有使用约束中的唯一约束（理论上可以重复，但是实际开发中不能出现）。可以有特殊字符

### bean的生命周期的配置

+ `init-method`：bean被初始化的时候执行的方法
+ `destroy-method`：bean被销毁的时候执行的方法，前提是bean是单例的，工厂关闭

### bean的作用范围的配置

+ `scope`：bean的作用范围
  + **singleton：单例模式，默认的作用域。**
  + **prototype：多例模式。**
  + request：应用在Web项目中，Spring创建这个类后，将这个类存入到request范围中。
  + session：应用在Web项目中，Spring创建这个类后，将这个类存入到session范围中。
  + globalsession：应用在Web项目中，必须在porlet环境下使用。但是如果没有这种环境，相当于session。

## Spring的Bean管理配置

### Spring的Bean的实例化方式

#### 无参构造方式（默认）

```java
/**
 * //TODO
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class Dog {

    private String name;
    private Integer length;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", length=" + length +
                '}';
    }
}

```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- Spring Bean的实例化方式-->

    <!-- 无参构造的方式 -->
    <bean id="dog" class="learningspring.ioc.examples.demo3.Dog"/>

</beans>
```



#### 静态工厂实例化方式

```java
/**
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class Car {
    private String name;
    private Double price;

    public Car() {
    }

    public Car(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
```

```java
package learningspring.ioc.examples.demo3;

/**
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class CarFactory {

    public static Car createCar(){
        return new Car();
    }
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- Spring Bean的实例化方式-->
    <!-- 静态工厂的方式 -->
    <bean id="car" class="learningspring.ioc.examples.demo3.CarFactory" factory-method="createCar"/>

</beans>
```



#### 实例工厂实例化方式

```java
/**
 * //TODO
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class Dog {

    private String name;
    private Integer length;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", length=" + length +
                '}';
    }
}

```

```java
/**
 * //TODO
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class DogFactory {

    public Dog createDog(){
        return new Dog();
    }
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- Spring Bean的实例化方式-->

    <!-- 实例工厂的方式 -->
    <bean id="dogFactory" class="learningspring.ioc.examples.demo3.DogFactory"/>
    <bean id="dog2" factory-bean="dogFactory" factory-method="createDog"/>
</beans>
```



### Spring的属性注入方式

#### 构造方法方式的属性注入

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--构造方法方式的属性注入-->
    <bean id="car" class="learningspring.ioc.examples.demo3.Car">
        <constructor-arg name="name" value="BWM"/>
        <constructor-arg name="price" value="800000"/>
    </bean>
</beans>
```

#### Set方法方式的属性注入

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--Set方法方式的属性注入-->
    <bean id="dog" class="learningspring.ioc.examples.demo3.Dog">
        <property name="name" value="Golden"/>
        <property name="length" value="100"/>
    </bean>
</beans>
```

#### 为Bean注入引用类型的数据

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--构造方法方式的属性注入-->
    <bean id="car" class="learningspring.ioc.examples.demo3.Car">
        <constructor-arg name="name" value="BWM"/>
        <constructor-arg name="price" value="800000"/>
    </bean>

    <!--Set方法方式的属性注入-->
    <bean id="dog" class="learningspring.ioc.examples.demo3.Dog">
        <property name="name" value="Golden"/>
        <property name="length" value="100"/>
    </bean>

    <!--为Bean注入对象属性-->
    <!--构造方法方式一样可行-->
    <bean id="employee" class="learningspring.ioc.examples.demo3.Employee">
        <property name="name" value="Chen"/>
        <property name="car" ref="car"/>
        <property name="dog" ref="dog"/>
    </bean>
</beans>
```

#### P名称空间的属性注入（Spring2.5）

+ 通过引入p名称空间完成属性注入
  + 普通属性：p:属性名=“值”
  + 对象属性：p:属性名-ref=“值”

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--P名称空间的属性注入-->
    <bean id="cat" class="learningspring.ioc.examples.demo3.Cat" p:name="Orange" p:length="100"/>
    
    <!--为Bean注入对象属性-->
    <bean id="employee" class="learningspring.ioc.examples.demo3.Employee" p:cat-ref="cat">
        <property name="name" value="Chen"/>
        <property name="car" ref="car"/>
        <property name="dog" ref="dog"/>
    </bean>
</beans>
```

#### SpEL方式的属性注入（Spring3）

SpEL：Spring Expresssion Language  的表达式语言

语法：#{表达式}

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--SpEL表达式注入-->
    <bean id="cat2" class="learningspring.ioc.examples.demo4.Cat">
        <!--字符串要加单引号-->
        <!--也可以通过#{beanName.属性名或方法名}来通过其他bean的属性或者方法来注入-->
        <property name="name" value="#{'Orange'}"/>
        <property name="length" value="#{101}"/>
    </bean>
</beans>
```



### 注入集合类型的数据

```java
/**
 * 注入集合类型的数据测试
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class CollectionBean {

    private String[] strs;
    private List<String> list;
    private Set<String> set;
    private Map<String, String> map;

    public void setStrs(String[] strs) {
        this.strs = strs;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "CollectionBean{" +
                "strs=" + Arrays.toString(strs) +
                ", list=" + list +
                ", set=" + set +
                ", map=" + map +
                '}';
    }
}

```



```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--Spring的集合属性的注入-->
    <!--注入数组类型-->
    <bean id="collectionBean" class="learningspring.ioc.examples.demo4.CollectionBean">
        <!-- 注入数组类型 -->
        <property name="strs">
            <list>
                <value>Tom</value>
                <value>Jack</value>
            </list>
        </property>

        <!-- 注入List集合 -->
        <property name="list">
            <list>
                <value>Lucy</value>
                <value>Lily</value>
            </list>
        </property>

        <!-- 注入Set集合 -->
        <property name="set">
            <set>
                <value>aaa</value>
                <value>bbb</value>
                <value>ccc</value>
            </set>
        </property>

        <!-- 注入Map集合 -->
        <property name="map">
            <map>
                <entry key="a" value="1"/>
                <entry key="b" value="2"/>
            </map>
        </property>
    </bean>
</beans>
```

### Spring分模块开发的配置

+ 加载配置文件时，直接加载多个配置文件

```java
ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext1.xml", "applicationContext2.xml");
```

+ 在一个配置文件中引入多个配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--引入配置文件-->
	<import resource="applicationContext2.xml"/>
    
</beans>
```

# Spring开发中的常用注解

## @Component

该注解在类上使用，使用该注解就相当于在配置文件中配置了一个Bean，例如：

```java
@Component("userDao")
public class UserDaoImpl implements UserDao {
    @Override
    public void save() {
        System.out.println("UserDaoImpl.save");
    }
}
```

相当于以下内容：

```xml
<bean id="userDao" class="learningspring.ioc.examplesannotation.demo1.UserDaoImpl"></bean>
```

该注解有3个衍生注解：

+ **@Controller：修饰Web 层类**
+ **@Service：修饰Service层类**
+ **@Repository：修饰Dao层类**

## @Value

该注解用于给属性注入值，有2种用法

+ 如果有set方法，则需要将该注解添加到set方法上
+ 如果没有set方法，则需要将该注解添加到属性上

```java
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

    @Value("100") // 注入属性值
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

    @Value("Golden") // 注入属性值
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

```

## @Autowired

`@Value` 通常用于普通属性的注入。

`@Autowired` 通常用于为对象类型的属性注入值，但是按照**类型**完成属性注入

习惯是按照**名称**完成属性注入，所以必须让`@Autowired`注解和`@Qualifier`注解**一同使用**。

（如果没有`@Qualifier`注解，修改以下例子中`@Repository`注解的值，也能编译成功）

```java
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Override
    public void save() {
        System.out.println("UserServiceImpl.save");
        userDao.save();
    }
}
```

```java
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Override
    public void save() {
        System.out.println("UserDaoImpl.save");
    }
}
```

## @Resource

该注解也可以用于属性注入，通常情况下使用**@Resource注解**，默认按照**名称**完成属性注入。

该注解由J2EE提供，需要导入包`javax.annotation.Resource`。

`@Resource`有两个重要的属性：`name`和`type`，而Spring将`@Resource`注解的`name`属性解析为bean的名字，而`type`属性则解析为bean的类型。所以，如果使用`name`属性，则使用byName的自动注入策略，而使用`type`属性时则使用byType自动注入策略。如果既不制定`name`也不制定`type`属性，这时将通过反射机制使用byName自动注入策略。

```java
/**
 * UserController
 *
 * @author Chen Rui
 * @version 1.0
 **/
@Controller("userController")
public class UserController {
    
    @Resource(name = "userService")
    private UserService userService; 
    
}
```

```java
/**
 * UserService实现类
 *
 * @author Chen Rui
 * @version 1.0
 **/

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource(name = "userDao")
    private UserDao userDao;

    @Override
    public void save() {
        System.out.println("UserServiceImpl.save");
        userDao.save();
    }
}

```

```java
/**
 * UserDao实现类
 * @author Chen Rui
 * @version 1.0
 **/

@Component("userDao")
public class UserDaoImpl implements UserDao {
    @Override
    public void save() {
        System.out.println("UserDaoImpl.save");
    }
}

```

## @PostConstruct 和 @PreDestroy

`@PostConstruct`和`@PreDestroy`注解，前者为Bean生命周期相关的注解，在配置文件中，使用到了i`nit-method`参数来配置Bean初始化之前要执行的方法，该注解也是同样的作用。将该注解添加到想在初始化之前执行的目标方法上，就可以实现该功能，而后者则是Bean生命周期中`destroy-method`目标方法，使用该注解在指定方法上，即可实现在Bean销毁之前执行该方法。

```java
/**
 * UserDao实现类
 * @author Chen Rui
 * @version 1.0
 **/

@Component("userDao")
public class UserDaoImpl implements UserDao {
    
    @PostConstruct
    public void init(){
        System.out.println("UserDaoImpl.init");
    }
    
    @Override
    public void save() {
        System.out.println("UserDaoImpl.save");
    }
    
    @PreDestroy
    public void destroy(){
        System.out.println("UserDaoImpl.destroy");
    }
}
```

## @Scope

Bean的作用范围的注解，默认为singleton（单例）

可选值：

+ singleton
+ prototype
+ request
+ session
+ globalsession

```java
/**
 * UserDao实现类
 * @author Chen Rui
 * @version 1.0
 **/

@Component("userDao")
@Scope // 默认为singleton
public class UserDaoImpl implements UserDao {

    @PostConstruct
    public void init(){
        System.out.println("UserDaoImpl.init");
    }

    @Override
    public void save() {
        System.out.println("UserDaoImpl.save");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("UserDaoImpl.destroy");
    }
}
```

## 基于XML配置和基于注解配置的对比

|                | 基于XML的配置                                                | 基于注解的配置                                               |
| -------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Bean的定义     | \<bean id="Bean的id" class="类的全路径"/>                    | @Component或衍生注解@Controller，@Service和@Repository       |
| Bean的名称     | 通过id或name指定                                             | @Component(“Bean的id”)                                       |
| Bean的属性注入 | \<property>或者通过p命名空间                                 | 通过注解@Autowired 按类型注入<br />通过@Qualifier按名称注入  |
| Bean的生命周期 | init-method指定Bean初始化前执行的方法，destroy-method指定Bean销毁前执行的方法 | @PostConstruct 对应于int-method<br />@PreDestroy 对应于destroy-method |
| Bean的作用域   | 在bean标签中配置scope属性                                    | @Scope, 默认是singleton<br />配置多例可以在目标类上使用@Scope(“prototype”) |
| 使用场景       | Bean来自第三方，可以使用在任何场景                           | Bean的实现类由自己维护                                       |

XML可以适用于任何场景，就算Bean来自第三方也可以适用XML方式来管理。而注解方式就无法在此场景下使用，注解方式可以让开发的过程更加方便，但前提是Bean由自己维护，这样才能在源码中添加注解。

所以可以使用**两者混合**的方式来开发项目，使用**XML配置文件来管理Bean，使用注解来进行属性注入**

# Spring AOP

## AOP的概述

即**面向切面编程**，通过**预编译**方式和运行期动态代理实现程序功能的统一维护的一种技术。利用AOP可以对业务逻辑的各个部分进行**隔离**，从而使得业务逻辑各部分之间的**耦合度降低**，提高程序的**可重用性**，同时提高了开发的效率。

## AOP的案例（应用场景）

背景：某项目已经写好了保存到数据库的方法。假设现在需要添加一个新的功能，例如权限校验，在保存到数据库之前要对用户权限进行校验。

```java
public class UserDaoImpl implements UserDao {
    @Override
    public void save(){
        ...
    }
}
```

现在需要多加一个需求，在用户将数据保存到数据库之前，进行权限校验。

此时通常就会在该方法中添加一个方法来进行权限校验然后在save方法中调用。

```java
public class UserDaoImpl implements UserDao {
    @Override
    public void save(){
        checkPri();
        // 保存到数据库
    }
    
    private void checkPri(){
        // 权限校验
    }
}
```

用这样的方法来实现，弊端就是只能在这一个类中使用，通常一个项目中有许多的方法都可能需要执行权限校验，此时就要在每个类中编写同样的代码，所以该方法并不科学。

此时就有了一个更好的方法，即**纵向继承**。

定义一个通用的Dao，在通用的Dao中编写权限校验的方法。

```java
public class BaseDao{
    public void checkPri(){
        // 权限校验
    }
}
```

然后每一个不同的类都去继承这个类，再调用该方法

```java
public class UserDaoImpl extends BaseDao implements UserDao{
    @Override
    public void save(){
        checkPri();
        // 保存到数据库
    }
}
```

此时就只需要维护`BaseDao`中的一份代码就可以，大大减轻了工作量，提高了效率。

但AOP的思想更高一步，不采用纵向继承，而采用**横向抽取**来取代

```java
public class UserDaoImpl implements UserDao{
    @Override
    public void save(){
        // 保存到数据库
    }
}
```

横向抽取机制实质上就是**代理机制**，通过创建`UserDaoImpl`类的代理类，通过代理类来调用权限校验的方法。

## AOP底层实现原理

AOP的实现使用了动态代理技术，动态代理分为两种

+ JDK动态代理：只能对实现了接口的类产生代理
+ Cglib动态代理（类似于javassist的第三方代理技术）：对没有实现接口的类产生代理对象，即生成子类对象。

### JDK动态代理

#### JDK动态代理案例

该案例实现一个计算器的日志功能

首先创建一个接口`Calculator`

```java
/**
 * 计算器接口
 *
 * @author Chen Rui
 * @version 1.0
 **/
public interface Calculator {

    /**
     * 加法
     * @param a 实数
     * @param b 实数
     * @return 相加结果
     */
    int add(int a, int b);

    /**
     * 减法
     * @param a 实数,被减数
     * @param b 实数,减数
     * @return 相减结果
     */
    int sub(int a, int b);

    /**
     * 乘法
     * @param a 实数
     * @param b 实数
     * @return 相乘结果
     */
    int mul(int a, int b);

    /**
     * 除法
     * @param a 实数,被除数
     * @param b 实数,除数
     * @return 相除结果
     */
    int div(int a, int b);
}
```

接着创建一个类`CalculatorImpl`来实现该接口并重写方法

```java
/**
 * 计算器实现类
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class CalculatorImpl implements Calculator {

    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int sub(int a, int b) {
        return a - b;
    }

    @Override
    public int mul(int a, int b) {
        return a * b;
    }

    @Override
    public int div(int a, int b) {
        if (b == 0){
            System.out.println("除数不能为0");
            return 0;
        }
        return  a / b;
    }
}

```

在测试类中测试该计算器代码

```java
/**
 * @author Chen Rui
 * @version 1.0
 **/
public class AppTest {
    
    @Test
    public void test() {
        Calculator target = new CalculatorImpl();
        int a = 10;
        int b = 10;
        System.out.println("res --> " + target.add(a, b));

        System.out.println("res --> " + target.mul(a, b));

        System.out.println("res --> " + target.sub(a, b));

        System.out.println("res --> " + target.div(a, b));
    }
}
```

此时控制台的输出结果为：

```
res --> 20
res --> 100
res --> 0
res --> 1
```

现在为该计算器增加**打印日志**的功能

创建一个计算器的代理类`CalculatorLoggingProxy`，在类中首先定义被代理的目标对象target，并通过构造函数进行赋值。

```java
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Date;

/**
 * 计算器代理类
 * 实现扩展打印日志功能
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class CalculatorProxy {
    /**
     * 被代理的对象
     */
    private Calculator target;

    public CalculatorProxy(Calculator target) {
        this.target = target;
    }

    public Calculator createProxy(){
        Calculator proxy;

        ClassLoader classLoader = target.getClass().getClassLoader();

        Class[] interfaces = new Class[]{Calculator.class};

        InvocationHandler handler = new InvocationHandler() {
            /**
             * @param proxy     正在返回的代理对象，一般在invoke方法中都不使用该对象
             *                  如果使用该对象，则会引发栈内存溢出。因为会循环调用invoke方法。
             * @param method    正在被调用的方法
             * @param args      调用方式时传入的参数
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 获取方法名
                String methodName = method.getName();
                // 输出日志逻辑
                System.out.println(new Date() + ": The method " + methodName + " begins with " + Arrays.asList(args));
                // 执行方法
                Object result = method.invoke(target, args);
                // 输出日志逻辑
                System.out.println(new Date() + ": The method " + methodName + " ends with " + result);
                return result;
            }
        };

        proxy = (Calculator) Proxy.newProxyInstance(classLoader,interfaces,handler);

        return proxy;
    }
}

```

此时重新编写测试方法

```java
/**
 * @author Chen Rui
 * @version 1.0
 **/
public class AppTest {
    
    @Test
    public void test() {
        Calculator target = new CalculatorImpl();
        // 创建代理对象
        Calculator proxy = new CalculatorProxy(target).createProxy();
        int a = 10;
        int b = 10;
        System.out.println("res --> " + proxy.add(a, b));

        System.out.println("res --> " + proxy.mul(a, b));

        System.out.println("res --> " + proxy.sub(a, b));

        System.out.println("res --> " + proxy.div(a, b));
    }
}
```

到此就完成了在不改变`CalculatorImpl`类的源代码的情况下，实现对计算器的功能增加，实现了日志打印的功能。此时控制台的打印内容为

```
Sun Mar 17 20:36:26 CST 2019: The method add begins with [10, 10]
Sun Mar 17 20:36:26 CST 2019: The method add ends with 20
res --> 20
Sun Mar 17 20:36:26 CST 2019: The method mul begins with [10, 10]
Sun Mar 17 20:36:26 CST 2019: The method mul ends with 100
res --> 100
Sun Mar 17 20:36:26 CST 2019: The method sub begins with [10, 10]
Sun Mar 17 20:36:26 CST 2019: The method sub ends with 0
res --> 0
Sun Mar 17 20:36:26 CST 2019: The method div begins with [10, 10]
Sun Mar 17 20:36:26 CST 2019: The method div ends with 1
res --> 1
```



### Cglib动态代理

#### Cglib动态代理案例

同样来实现一个对计算器来增加打印日志功能

首先创建计算器类`Calculator`

```java
/**
 * 计算器类
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int sub(int a, int b) {
        return a - b;
    }

    public int mul(int a, int b) {
        return a * b;
    }

    public int div(int a, int b) {
        if (b == 0){
            System.out.println("除数不能为0");
            return 0;
        }
        return  a / b;
    }
}

```

此时需要导入cglib的jar包，在maven中添加依赖

```xml
<dependency>
    <groupId>cglib</groupId>
    <artifactId>cglib</artifactId>
    <version>2.2.2</version>
</dependency>
```

接着创建计算器的代理类`CalculatorProxy`并且实现`MethodInterceptor`接口并重写`intercept`方法。

在类中首先定义被代理的目标对象，并通过构造函数进行赋值。然后创建`createProxy()`方法返回被增强的计算器对象。

```java
/**
 * 计算器代理类
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class CalculatorProxy implements MethodInterceptor {

    /**
     * 被代理的对象
     */
    private Calculator target;

    public CalculatorProxy(Calculator target) {
        this.target = target;
    }

    public Calculator createProxy(){

        // 1.创建cglib的核心类对象
        Enhancer enhancer = new Enhancer();

        // 2.设置父类
        enhancer.setSuperclass(target.getClass());

        // 3.设置回调（类似于jdk动态代理中的InvocationHandler对象）
        enhancer.setCallback(this);

        // 4.创建代理对象
        Calculator proxy = (Calculator) enhancer.create();

        return proxy;
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        // 获取方法名
        String methodName = method.getName();
        // 输出日志逻辑
        System.out.println(new Date() + ": The method " + methodName + " begins with " + Arrays.asList(args));
        // 执行方法
        Object result = methodProxy.invokeSuper(proxy, args);
        // 输出日志逻辑
        System.out.println(new Date() + ": The method " + methodName + " ends with " + result);
        return result;
    }
}

```

## Spring中的AOP实现——AspectJ

### AOP开发中的相关术语

```java
public class UserDao{
    public void save(){}
    
    public void query(){}
    
    public void update(){}
    
    public void delete(){}
}
```

- joinpoint(连接点) ： 可以被拦截到的点。save(), query(),update(),delete()方法都可以增强，这些方法就可以称为连接点。
- pointcut(切入点)：真正被拦截到的点。在实际开发中，可以只对save()方法进行增强，那么save()方法就是切入点。
- advice(增强)：方法层面的增强，现在可以对save()方法进行权限校验，权限校验(checkPri())的方法称为增强。
- introduction(引介)：类层面的增强。
- target(目标)：被增强的对象。
- weaving(织入)：将增强(advice)应用到目标(target)的过程
- proxy(代理)：代理对象，被增强以后的代理对象
- aspect(切面)：多个增强(advice)和多个切入点(pointcut)的组合

### AspectJ的XML配置案例

首先创建一个接口`ProductDao`，在里面定义添加商品，查询商品，修改商品，删除商品方法。

```java
/**
 * ProductDao
 *
 * @author Chen Rui
 * @version 1.0
 **/
public interface ProductDao {

    /**
     * 添加商品
     */
    void save();

    /**
     * 删除商品
     */
    void delete();

    /**
     * 修改商品
     */
    void modify();

    /**
     * 查询商品
     */
    void query();
}

```

接着创建一个类`ProductDaoImpl`来实现该接口

```java
/**
 * ProductDao的实现类
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class ProductDaoImpl implements ProductDao {

    @Override
    public void save() {
        System.out.println("添加商品");
    }

    @Override
    public void delete() {
        System.out.println("删除商品");
    }
    
    @Override
    public void modify() {
        System.out.println("修改商品");
    }
    
    @Override
    public void query() {
        System.out.println("查询商品");
    }
    
}

```

现在目的就是给`save()`方法进行增强，使得在调用`save()`方法前进行权限校验。

要实现此功能，先创建一个**增强类**，或者叫**切面类**。里面编写要增强的功能，例如权限校验。

创建增强类`ProductEnhancer`

```java
/**
 * ProductDao的增强类(切面类)
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class ProductEnhancer {

    public void checkPri(){
        System.out.println("【前置增强】权限校验");
    }

}

```

然后创建配置文件`aspectj-xml.xml`来配置，该文件名此案例仅用于演示，实际开发中不要采取此名，依据实际需求编写。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/aop
    http://www.springframework.org/schema/aop/spring-aop.xsd">

    <!-- 配置目标对象，即被增强的对象 -->
    <bean id="productDao" class="learningspring.aop.aspectj.xml.demo2.ProductDaoImpl"/>

    <!-- 将增强类(切面类)交给Spring管理 -->
    <bean id="productEnhancer" class="learningspring.aop.aspectj.xml.demo2.ProductEnhancer"/>
    
    <!-- 通过对AOP的配置完成对目标对象产生代理 -->
    <aop:config>
        <!-- 表达式配置哪些类的哪些方法需要进行增强 -->
        <!-- 对ProductDaoImpl类中的save方法进行增强 -->
        <!--
        “*” 表示任意返回值类型
        “..” 表示任意参数
        -->
        <aop:pointcut id="pointcut1" expression="execution(* learningspring.aop.aspectj.xml.demo2.ProductDaoImpl.save(..))"/>

        <!-- 配置切面 -->
        <aop:aspect ref="productEnhancer">
            <!-- 前置增强 -->
            <!-- 实现在调用save方法之前调用checkPri方法来进行权限校验-->
            <aop:before method="checkPri" pointcut-ref="pointcut1"/>
        </aop:aspect>
    </aop:config>
    
</beans>
```

至此切入点及切面都已配置完成，编写测试类和方法

```java
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * AspectJ的XML方式配置测试类
 *
 * @author Chen Rui
 * @version 1.0
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:aspectj-xml.xml")
public class AppTest {

    @Resource(name = "productDao")
    private ProductDao productDao;

    @Test
    public void test(){
        // 对save方法进行增强
        productDao.save();

        productDao.delete();
        
        productDao.modify();
        
        productDao.query();
    }
}

```

运行`test()`方法，控制台打印结果如下：

```
【前置增强】权限校验
添加商品
删除商品
修改商品
查询商品
```

至此就实现了在不修改`ProductDaoImpl`类的情况下，对其中的`save()`方法进行增强。

### Spring中常用的增强类型

#### 前置增强

在目标方法执行之前执行，可以获得切入点的信息

修改之前的`ProductEnhancer`类的`checkPri()`方法的参数。

```java
import org.aspectj.lang.JoinPoint;

/**
 * ProductDao的增强类(切面类)
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class ProductEnhancer {

    public void checkPri(JoinPoint joinPoint){
        System.out.println("【前置增强】权限校验" + joinPoint);
    }

}
```

执行测试方法，控制台输出

```
【前置增强】权限校验execution(void learningspring.aop.aspectj.xml.demo2.ProductDao.save())
添加商品
删除商品
修改商品
查询商品
```



#### 后置增强

在目标方法执行之后执行，可以获得方法的返回值

首先修改`ProductDao`中的`delete()`方法的返回值类型，改成String

```java
/**
 * ProductDao
 *
 * @author Chen Rui
 * @version 1.0
 **/
public interface ProductDao {

    /**
     * 添加商品
     */
    void save();

    /**
     * 删除商品
     */
    String delete();

    /**
     * 修改商品
     */
    void modify();

    /**
     * 查询商品
     */
    void query();
}

```

再修改`ProductDaoImpl`中的`delete()`方法

```java
/**
 * ProductDao的实现类
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class ProductDaoImpl implements ProductDao {

    @Override
    public void save() {
        System.out.println("添加商品");
    }

    @Override
    public String delete() {
        System.out.println("删除商品");
        return new Date().toString();
    }

    @Override
    public void modify() {
        System.out.println("修改商品");
    }

    @Override
    public void query() {
        System.out.println("查询商品");
    }
}

```

修改`ProductEnhancer`类，添加`writeLog()`方法，实现写日志功能

```java
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
}
```

然后修改`aspectj.xml`配置文件，配置新的**切入点**和**切面**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/aop
    http://www.springframework.org/schema/aop/spring-aop.xsd">

    <!-- 配置目标对象，即被增强的对象 -->
    <bean id="productDao" class="learningspring.aop.aspectj.xml.demo2.ProductDaoImpl"/>

    <!-- 将增强类(切面类)交给Spring管理 -->
    <bean id="productEnhancer" class="learningspring.aop.aspectj.xml.demo2.ProductEnhancer"/>
    
    <!-- 通过对AOP的配置完成对目标对象产生代理 -->
    <aop:config>
        <!-- 表达式配置哪些类的哪些方法需要进行增强 -->
        <!-- 对ProductDaoImpl类中的save方法进行增强 -->
        <!--
        “*” 表示任意返回值类型
        “..” 表示任意参数
        -->
        <!-- 前置增强的切入点配置 -->
        <aop:pointcut id="pointcut1" expression="execution(* learningspring.aop.aspectj.xml.demo2.ProductDaoImpl.save(..))"/>
        
        <!-- 后置增强的切入点配置 -->
        <aop:pointcut id="pointcut2" expression="execution(* learningspring.aop.aspectj.xml.demo2.ProductDaoImpl.delete(..))"/>

        <!-- 配置切面 -->
        <aop:aspect ref="productEnhancer">
            <!-- 前置增强 -->
            <!-- 实现在调用save方法之前调用checkPri方法来进行权限校验-->
            <aop:before method="checkPri" pointcut-ref="pointcut1"/>
            
            <!-- 后置增强 -->
            <!-- returning里面的值必须和writeLog()方法里的参数名相同，本案例为result-->
            <aop:after-returning method="writeLog" returning="result" pointcut-ref="pointcut2"/>
        </aop:aspect>
    </aop:config>

</beans>
```

执行测试方法，控制台打印结果

```
【前置增强】权限校验execution(void learningspring.aop.aspectj.xml.demo2.ProductDao.save())
添加商品
删除商品
【后置增强】写入日志 操作时间：Tue Mar 19 15:59:48 CST 2019
修改商品
查询商品
```

#### 环绕增强

在目标方法执行之前和之后都执行

利用环绕增强来实现在调用`modify()`方法前后进行性能监控

首先修改`ProductEnhancer`类，添加`monitor()`方法

```java
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
}

```

然后再修改`aspectj.xml`配置文件，添加新的**切入点**和**切面**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/aop
    http://www.springframework.org/schema/aop/spring-aop.xsd">

    <!-- 配置目标对象，即被增强的对象 -->
    <bean id="productDao" class="learningspring.aop.aspectj.xml.demo2.ProductDaoImpl"/>

    <!-- 将增强类(切面类)交给Spring管理 -->
    <bean id="productEnhancer" class="learningspring.aop.aspectj.xml.demo2.ProductEnhancer"/>
    
    <!-- 通过对AOP的配置完成对目标对象产生代理 -->
    <aop:config>
        <!-- 表达式配置哪些类的哪些方法需要进行增强 -->
        <!-- 对ProductDaoImpl类中的save方法进行增强 -->
        <!--
        “*” 表示任意返回值类型
        “..” 表示任意参数
        -->
        <!-- 前置增强的切入点配置 -->
        <aop:pointcut id="pointcut1" expression="execution(* learningspring.aop.aspectj.xml.demo2.ProductDaoImpl.save(..))"/>

        <!-- 后置增强的切入点配置 -->
        <aop:pointcut id="pointcut2" expression="execution(* learningspring.aop.aspectj.xml.demo2.ProductDaoImpl.delete(..))"/>

        <!-- 环绕增强的切入点配置 -->
        <aop:pointcut id="pointcut3" expression="execution(* learningspring.aop.aspectj.xml.demo2.ProductDaoImpl.modify(..))"/>

        <!-- 配置切面 -->
        <aop:aspect ref="productEnhancer">
            <!-- 前置增强 -->
            <!-- 实现在调用save方法之前调用checkPri方法来进行权限校验-->
            <aop:before method="checkPri" pointcut-ref="pointcut1"/>

            <!-- 后置增强 -->
            <!-- returning里面的值必须和writeLog()方法里的参数名相同，本案例为result-->
            <aop:after-returning method="writeLog" returning="result" pointcut-ref="pointcut2"/>

            <!-- 环绕增强 -->
            <aop:around method="monitor" pointcut-ref="pointcut3"/>

        </aop:aspect>
    </aop:config>

</beans>
```

运行测试方法，控制台打印结果：

```
【前置增强】权限校验execution(void learningspring.aop.aspectj.xml.demo2.ProductDao.save())
添加商品
删除商品
【后置增强】写入日志 操作时间：Tue Mar 19 15:58:49 CST 2019
【环绕增强】当前空闲内存185MB
修改商品
【环绕增强】当前空闲内存185MB
查询商品
```

#### 异常抛出增强

在程序出现异常时执行

利用异常抛出增强来实现获取异常信息的功能

首先修改`ProductDaoImpl`中的`query()`方法，使该方法抛出异常

```java
/**
 * ProductDao的实现类
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class ProductDaoImpl implements ProductDao {

    @Override
    public void save() {
        System.out.println("添加商品");
    }

    @Override
    public void query() {
        System.out.println("查询商品");
        int a = 1/0;
    }

    @Override
    public void modify() {
        System.out.println("修改商品");
    }

    @Override
    public String delete() {
        System.out.println("删除商品");
        return new Date().toString();
    }
}
```

接着修改`ProductEnhancer`类，添加`exception()`方法

```java
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
}

```

然后再修改`aspectj-xml.xml`配置文件，添加新的**切入点**和**切面**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/aop
    http://www.springframework.org/schema/aop/spring-aop.xsd">

    <!-- 配置目标对象，即被增强的对象 -->
    <bean id="productDao" class="learningspring.aop.aspectj.xml.demo2.ProductDaoImpl"/>

    <!-- 将增强类(切面类)交给Spring管理 -->
    <bean id="productEnhancer" class="learningspring.aop.aspectj.xml.demo2.ProductEnhancer"/>
    
    <!-- 通过对AOP的配置完成对目标对象产生代理 -->
    <aop:config>
        <!-- 表达式配置哪些类的哪些方法需要进行增强 -->
        <!-- 对ProductDaoImpl类中的save方法进行增强 -->
        <!--
        “*” 表示任意返回值类型
        “..” 表示任意参数
        -->
        <!-- 前置增强的切入点配置 -->
        <aop:pointcut id="pointcut1" expression="execution(* learningspring.aop.aspectj.xml.demo2.ProductDaoImpl.save(..))"/>

        <!-- 后置增强的切入点配置 -->
        <aop:pointcut id="pointcut2" expression="execution(* learningspring.aop.aspectj.xml.demo2.ProductDaoImpl.delete(..))"/>

        <!-- 环绕增强的切入点配置 -->
        <aop:pointcut id="pointcut3" expression="execution(* learningspring.aop.aspectj.xml.demo2.ProductDaoImpl.modify(..))"/>

        <!-- 异常抛出增强的切入点配置 -->
        <aop:pointcut id="pointcut4" expression="execution(* learningspring.aop.aspectj.xml.demo2.ProductDaoImpl.query(..))"/>

        <!-- 配置切面 -->
        <aop:aspect ref="productEnhancer">
            <!-- 前置增强 -->
            <!-- 实现在调用save方法之前调用checkPri方法来进行权限校验-->
            <aop:before method="checkPri" pointcut-ref="pointcut1"/>

            <!-- 后置增强 -->
            <!-- returning里面的值必须和writeLog()方法里的参数名相同，本案例为result-->
            <aop:after-returning method="writeLog" returning="result" pointcut-ref="pointcut2"/>

            <!-- 环绕增强 -->
            <aop:around method="monitor" pointcut-ref="pointcut3"/>

            <!-- 异常抛出增强 -->
            <aop:after-throwing method="exception" throwing="ex" pointcut-ref="pointcut4"/>
        </aop:aspect>
    </aop:config>

</beans>
```

最后执行测试方法，控制台输出结果：

```
【前置增强】权限校验execution(void learningspring.aop.aspectj.xml.demo2.ProductDao.save())
添加商品
删除商品
【后置增强】写入日志 操作时间：Tue Mar 19 15:58:16 CST 2019
【环绕增强】当前空闲内存183MB
修改商品
【环绕增强】当前空闲内存183MB
查询商品
【异常抛出增强】异常信息：/ by zero
```

#### 最终增强

无论代码是否有异常最终都会执行

继续在异常抛出增强的代码修改，实现无论是否抛出异常都会打印当前时间信息

首先修改`ProductEnhancer`类，添加`finallyAdvice()`方法

```java
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

```

修改`aspectj.xml`配置文件，添加新的**切面**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/aop
    http://www.springframework.org/schema/aop/spring-aop.xsd">

    <!-- 配置目标对象，即被增强的对象 -->
    <bean id="productDao" class="learningspring.aop.aspectj.xml.demo2.ProductDaoImpl"/>

    <!-- 将增强类(切面类)交给Spring管理 -->
    <bean id="productEnhancer" class="learningspring.aop.aspectj.xml.demo2.ProductEnhancer"/>
    
    <!-- 通过对AOP的配置完成对目标对象产生代理 -->
    <aop:config>
        <!-- 表达式配置哪些类的哪些方法需要进行增强 -->
        <!-- 对ProductDaoImpl类中的save方法进行增强 -->
        <!--
        “*” 表示任意返回值类型
        “..” 表示任意参数
        -->
        <!-- 前置增强的切入点配置 -->
        <aop:pointcut id="pointcut1" expression="execution(* learningspring.aop.aspectj.xml.demo2.ProductDaoImpl.save(..))"/>

        <!-- 后置增强的切入点配置 -->
        <aop:pointcut id="pointcut2" expression="execution(* learningspring.aop.aspectj.xml.demo2.ProductDaoImpl.delete(..))"/>

        <!-- 环绕增强的切入点配置 -->
        <aop:pointcut id="pointcut3" expression="execution(* learningspring.aop.aspectj.xml.demo2.ProductDaoImpl.modify(..))"/>

        <!-- 异常抛出增强的切入点配置 -->
        <aop:pointcut id="pointcut4" expression="execution(* learningspring.aop.aspectj.xml.demo2.ProductDaoImpl.query(..))"/>

        <!-- 配置切面 -->
        <aop:aspect ref="productEnhancer">
            <!-- 前置增强 -->
            <!-- 实现在调用save方法之前调用checkPri方法来进行权限校验-->
            <aop:before method="checkPri" pointcut-ref="pointcut1"/>

            <!-- 后置增强 -->
            <!-- returning里面的值必须和writeLog()方法里的参数名相同，本案例为result-->
            <aop:after-returning method="writeLog" returning="result" pointcut-ref="pointcut2"/>

            <!-- 环绕增强 -->
            <aop:around method="monitor" pointcut-ref="pointcut3"/>

            <!-- 异常抛出增强 -->
            <aop:after-throwing method="exception" throwing="ex" pointcut-ref="pointcut4"/>

            <!-- 最终增强 -->
            <aop:after method="finallyAdvice" pointcut-ref="pointcut4"/>
        </aop:aspect>
    </aop:config>

</beans>
```

最后运行测试代码，控制台输出结果：

```
【前置增强】权限校验execution(void learningspring.aop.aspectj.xml.demo2.ProductDao.save())
添加商品
删除商品
【后置增强】写入日志 操作时间：Tue Mar 19 15:57:01 CST 2019
【环绕增强】当前空闲内存183MB
修改商品
【环绕增强】当前空闲内存183MB
查询商品
【最终增强】Tue Mar 19 15:57:01 CST 2019
【异常抛出增强】异常信息：/ by zero
```

### AOP切入点表达式语法

AOP切入点表达式是基于execution的函数完成的

语法：**[访问修饰符] 方法返回值 包名.类名.方法名(参数)**

“*” 表示任意返回值类型
“..” 表示任意参数

+ `public void learningspring.aop.aspectj.xml.demo2.ProductDaoImpl.save(..) `：具体到某个增强的方法
+ `* *.*.*.*Dao.save(..) `：所有包下的所有以Dao结尾的类中的save方法都会被增强
+ `* learningspring.aop.aspectj.xml.demo2.ProductDaoImpl+.save(..) `：ProductDaoImpl及其子类的save方法都会被增强
+ `* learningspring.aop.aspectj.xml..*.*(..)`：xml包及其子包的所有类的方法都会被增强

### AspectJ的注解配置案例

首先也是创建一个接口`ProductDao`

```java
/**
 * ProductDao接口
 *
 * @author Chen Rui
 * @version 1.0
 **/
public interface ProductDao {

    /**
     * 添加商品
     */
    void save();

    /**
     * 查询商品
     */
    void query();

    /**
     * 修改商品
     */
    void modify();

    /**
     * 删除商品
     */
    String delete();
}
```

然后创建一个Dao实现类`ProductDaoImpl`

```java
/**
 * ProductDao的实现类
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class ProductDaoImpl implements ProductDao {

    @Override
    public void save() {
        System.out.println("添加商品");
    }

    @Override
    public String delete() {
        System.out.println("删除商品");
        return new Date().toString();
    }

    @Override
    public void modify() {
        System.out.println("修改商品");
    }

    @Override
    public void query() {
        System.out.println("查询商品");
        int a = 1/0;
    }
}
```

接着创建**增强类**`ProductEnhancer`，在该类里面使用注解

使用`@Pointcut`注解可以配置切入点信息，在较多的方法都要使用同一个增强时，就可以配置一个切入点让目标方法都去引用

`@Before`：前置增强

`@AfterReturning`：后置增强，其中的returning的值必须和方法传入的参数名相同

`@Around`：环绕增强

`@AfterThrowing`：异常抛出增强，其中的throwing的值必须和方法传入的参数名相同

`@After`：最终增强

```java
/**
 * ProductDao的增强类(切面类)
 *
 * @author Chen Rui
 * @version 1.0
 **/
@Aspect
public class ProductEnhancer {

    /**
     * 切入点配置
     * 对ProductDaoImpl里的方法都增强
     */
    @Pointcut(value = "execution(* learningspring.aop.aspectj.annotation.demo2.ProductDaoImpl.*(..))")
    private void pointcut1(){}

    /**
     * 前置增强案例
     * 在调用save方法之前进行权限校验
     * @param joinPoint 切入点对象
     */
    @Before(value = "execution(* learningspring.aop.aspectj.annotation.demo2.ProductDaoImpl.save())")
    public void checkPri(JoinPoint joinPoint){
        System.out.println("【前置增强】权限校验" + joinPoint);
    }

    /**
     * 后置增强案例
     * 在调用delete方法之后，写入日志记录操作时间
     * @param result 目标方法返回的对象
     */
    @AfterReturning(returning = "result", value = "execution(* learningspring.aop.aspectj.annotation.demo2.ProductDaoImpl.delete())")
    public void writeLog(Object result){
        System.out.println("【后置增强】写入日志 操作时间：" + result.toString());
    }

    /**
     * 环绕增强
     * 在调用modify方法前后，显示性能参数
     * @param joinPoint 切入点对象
     * @throws Throwable 可抛出的异常
     */
    @Around(value = "execution(* learningspring.aop.aspectj.annotation.demo2.ProductDaoImpl.modify())")
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
    @AfterThrowing(throwing = "ex", value = "execution(* learningspring.aop.aspectj.annotation.demo2.ProductDaoImpl.query())")
    public void exception(Throwable ex){
        System.out.println("【异常抛出增强】" + "异常信息：" +ex.getMessage());
    }

    /**
     * 最终增强
     * 无论ProductDaoImpl里的每个方法是否抛出异常都打印当前时间
     */
    @After(value = "pointcut1()")
    public void finallyAdvice(){
        System.out.println("【最终增强】" + new Date().toString());
    }
}

```

编写测试方法

```java
/**
 * AspectJ的注解方式配置测试类
 *
 * @author Chen Rui
 * @version 1.0
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:aspectj-annotation.xml")
public class AppTest {

    @Resource(name = "productDao")
    private ProductDao productDao;

    @Test
    public void test(){

        productDao.save();

        productDao.delete();

        productDao.modify();

        productDao.query();
    }
}
```

运行，控制台输出

```
【前置增强】权限校验execution(void learningspring.aop.aspectj.annotation.demo2.ProductDao.save())
添加商品
【最终增强】Tue Mar 19 16:01:06 CST 2019
删除商品
【最终增强】Tue Mar 19 16:01:06 CST 2019
【后置增强】写入日志 操作时间：Tue Mar 19 16:01:06 CST 2019
【环绕增强】当前空闲内存186MB
修改商品
【环绕增强】当前空闲内存186MB
【最终增强】Tue Mar 19 16:01:06 CST 2019
查询商品
【最终增强】Tue Mar 19 16:01:06 CST 2019
【异常抛出增强】异常信息：/ by zero
```

# Spring JDBC Template

Spring提供了提供了多种持久层技术的模板类

| ORM持久化技术   | 模板类                                               |
| --------------- | ---------------------------------------------------- |
| JDBC            | org.springframework.jdbc.core.JdbcTemplate           |
| Hibernate3.0    | org.springframework.orm.hibernate3.HibernateTemplate |
| IBatis(Mybatis) | org.springframework.orm.ibatis.SqlMapClientTemplate  |
| JPA             | org.springframework.orm.jpa.JpaTemplate              |

## JDBC Template的入门

首先引入jar包，在`pom.xml`文件中加入`spring-jdbc`，`spring-tx`，`mysql-connector-java`（本案例使用的是MySQL8）三个依赖。

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>4.3.14.RELEASE</version>
</dependency>

<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-tx</artifactId>
    <version>4.3.14.RELEASE</version>
</dependency>

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.15</version>
</dependency>
```

然后创建数据库表，本例使用的MySQL8

```mysql
create table account
(
	id int auto_increment
		primary key,
	name varchar(8) not null,
	money double default 0
)
comment '账户表';
```

### 基本使用

最基本的使用，不依赖于Spring 的管理，手动创建对象，采用硬编码的方式进行属性注入。不推荐使用该方法。

```java
public class AppTest {
    /**
     * 硬编码
     */
    @Test
    public void test1(){
        // 创建连接池
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/springjdbc?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false ");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        // 创建JDBC Template
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int i = jdbcTemplate.update("INSERT INTO account VALUES (null ,?,?)", "Tom", 20000d);
        if (i > 0){
            System.out.println("Update Successful");
        }
    }
}
```

接下来使用第二种方法，把连接池对象和模板(Template)都交给Spring来管理

创建`spring-jdbc.xml`该文件用来管理Bean

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- 配置数据库连接池 -->
    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/springjdbc?useUnicode=true&amp;characterEncoding=utf-8&amp;serverTimezone=Asia/Shanghai&amp;useSSL=false"/>
        <property name="username" value="root"/>
        <property name="password" value="123456"/>
    </bean>

    <!-- 配置Spring JDBC Template -->
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="dataSource"/>
    </bean>

</beans>
```

在测试类中加入相应的注解，以及配置文件信息，编写新的测试方法

```java
/**
 * Spring JDBC Template的使用
 *
 * @author Chen Rui
 * @version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-jdbc.xml")
public class AppTest {
    /**
     * Spring 配置文件方式
     * 把连接池和模板(Template)都交给spring管理
     * 日志信息：Loaded JDBC driver: com.mysql.cj.jdbc.Driver
     * 是使用的默认的连接池
     */
    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test2(){
        int i = jdbcTemplate.update("INSERT INTO account VALUES (null ,?,?)", "Jack", 30000d);
        if (i > 0){
            System.out.println("Update Successful");
        }
    }
```

通过`@Resource`注解从IOC容器中获取到模板对象，然后通过该模板对象来操作数据库。

这样就完成了Spring JDBC Template的最基本使用

### 数据库连接池

在实际开发中，可能并不会使用默认的连接池，而是去使用一些开源的数据库连接池，在该例中介绍两种数据库连接池DBCP和C3P0

#### DBCP连接池的配置

首先创建连接数据库的配置文件`db.properties`，注意，不同的MySQL版本可能url信息会不同，比如MySQL8就需要添加`serverTimezone`参数。

```properties
jdbc.driverClassName=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/springjdbc?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false 
jdbc.username=root
jdbc.password=123456
```

接着创建一个新的配置文件`spring-dbcp.xml`和前面的配置文件做区分。

利用`context:property-placeholder`标签引入`db.properties`配置文件，通过`${key}`的方式来获取对应的value。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 引入数据库配置文件 -->
    <context:property-placeholder location="db.properties"/>

    <!-- 配置DBCP连接池 -->
    <bean id="dataSource" class="org.apache.commons.dbcp2.BasicDataSource">
        <property name="driverClassName" value="${jdbc.driverClassName}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>

    <!-- 配置Spring JDBC Template -->
    <bean id="jdbcTemplateDBCP" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="dataSource"/>
    </bean>
</beans>
```

编写测试方法

```java
/**
 * Spring JDBC Template的使用
 *
 * @author Chen Rui
 * @version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-dbcp.xml")
public class AppTest {
    //使用开源的数据库连接池进行配置

    /**
     * 使用DBCP连接池
     */
    @Resource(name = "jdbcTemplateDBCP")
    private JdbcTemplate jdbcTemplateDBCP;

    @Test
    public void test3(){
        int i = jdbcTemplateDBCP.update("INSERT INTO account VALUES (null ,?,?)", "Lucy", 40000d);
        if (i > 0){
            System.out.println("Update Successful");
        }
    }
}
```

#### C3P0连接池配置

同样是创建一个新的配置文件`spring-c3p0.xml`，以作区分，同时也要引入数据库配置文件`db.properties`

要注意`property`标签的`name`属性和前面的配置文件**稍有不同**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans.xsd
	http://www.springframework.org/schema/context
	http://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 引入数据库配置文件 -->
    <context:property-placeholder location="db.properties"/>

    <!-- 配置C3P0连接池 -->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${jdbc.driverClassName}"/>
        <property name="jdbcUrl" value="${jdbc.url}"/>
        <property name="user" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>

    <!-- 配置Spring JDBC Template -->
    <bean id="jdbcTemplateC3P0" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="dataSource"/>
    </bean>
</beans>
```

编写测试方法

```java
/**
 * Spring JDBC Template的使用
 *
 * @author Chen Rui
 * @version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class AppTest {
    //使用开源的数据库连接池进行配置

    /**
     * 使用C3P0连接池
     */
    @Resource(name = "jdbcTemplateC3P0")
    private JdbcTemplate jdbcTemplateC3P0;

    @Test
    public void test4(){
        int i = jdbcTemplateC3P0.update("INSERT INTO account VALUES (null ,?,?)", "Lily", 50000d);
        if (i > 0){
            System.out.println("Update Successful");
        }
    }
}

```

### 完成基本的CRUD操作

以下内容都是使用的**C3P0连接池**，并且通过`@Resource`注解从IOC容器中获取了`jdbcTemplateC3P0`对象

#### 插入操作

```java
/**
 * 插入操作
 */
@Test
public void test(){
    int i = jdbcTemplateC3P0.update("INSERT INTO account VALUES (null ,?,?)", "Lily", 50000d);
    if (i > 0){
        System.out.println("Update Successful");
    }
}
```

#### 修改操作

```java
/**
 * 修改操作
 */
@Test
public void test(){
    int i = jdbcTemplateC3P0.update("UPDATE account SET name = ? WHERE id = ?", "Bob", 1);
    if (i > 0){
        System.out.println("Update Successful");
    }
}
```

#### 删除操作

```java
/**
 * 删除操作
 */
@Test
public void test(){
    int i = jdbcTemplateC3P0.update("DELETE FROM account WHERE id = ?", 2);
    if (i > 0){
        System.out.println("Delete Successful");
    }
}
```

#### 查询操作

##### 查询某个属性

```java
/**
 * 查询操作
 *
 * 查询单个字符串结果
 */
@Test
public void test(){
    String result = jdbcTemplateC3P0.queryForObject("SELECT name FROM account WHERE id = ?", String.class, 1);
    if (result != null){
        System.out.println(result);
    } else{
        System.out.println("NULL");
    }
}

/**
 * 统计查询
 * 返回数据表中的记录数
 */
@Test
public void test(){
    Long result = jdbcTemplateC3P0.queryForObject("SELECT COUNT(*) FROM account", Long.class);
    System.out.println(result);
}
```

##### 查询返回单个对象

要实现查询的数据封装成一个对象的话，查询`queryForObject`的参数列表可知需要一个`rowMapper`的参数。所以需要创建一个执行数据封装的类来实现`RowMapper<T>`接口里的`mapRow`方法，在这个方法里进行数据对象的封装。

```java
/**
 * 数据封装类
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class MyRowMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();
        account.setId(rs.getInt("id"));
        account.setName(rs.getString("name"));
        account.setMoney(rs.getDouble("money"));
        return account;
    }
}

```

编写测试方法

```java
/**
 * 将查询的结果封装成对象
 * 要创建一个自定义rowMapper来实现RowMapper接口
 */
@Test
public void test(){
    Account account = jdbcTemplateC3P0.queryForObject("SELECT * FROM account WHERE id = ?", new MyRowMapper(), 1);
    if (account != null){
        System.out.println(account);
    } else{
        System.out.println("NULL");
    }
}
```

##### 查询返回对象集合

要实现查询返回对象集合依然需要自定义类实现`RowMapper<T>`接口，调用的是`query`方法

```java
/**
 * 查询多条记录
 */
@Test
public void test10(){
    List<Account> accounts = jdbcTemplateC3P0.query("SELECT * FROM account", new MyRowMapper());
    accounts.forEach(System.out::println);
}
```

# Spring事务管理



