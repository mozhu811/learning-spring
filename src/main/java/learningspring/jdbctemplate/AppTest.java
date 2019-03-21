package learningspring.jdbctemplate;

import learningspring.jdbctemplate.entity.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Spring JDBC Template的使用
 *
 * @author Chen Rui
 * @version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
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

    /**
     * 使用C3P0连接池
     *
     * 插入操作
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

    /**
     * 使用jdbcTemplate 完成修改操作
     */
    @Test
    public void test5(){
        int i = jdbcTemplateC3P0.update("UPDATE account SET name = ? WHERE id = ?", "Bob", 1);
        if (i > 0){
            System.out.println("Update Successful");
        }
    }

    /**
     * 使用jdbcTemplate 完成删除操作
     */
    @Test
    public void test6(){
        int i = jdbcTemplateC3P0.update("DELETE FROM account WHERE id = ?", 2);
        if (i > 0){
            System.out.println("Delete Successful");
        }
    }

    /**
     * 查询操作
     *
     * 查询单个字符串结果
     */
    @Test
    public void test7(){
        String result = jdbcTemplateC3P0.queryForObject("SELECT name FROM account WHERE id = ?", String.class, 1);
        if (result != null){
            System.out.println(result);
        } else{
            System.out.println("NULL");
        }
    }

    /**
     * 统计查询
     */
    @Test
    public void test8(){
        Long result = jdbcTemplateC3P0.queryForObject("SELECT COUNT(*) FROM account", Long.class);
        System.out.println(result);
    }

    /**
     * 将查询的结果封装成对象
     * 要创建一个自定义rowMapper来实现RowMapper接口
     */
    @Test
    public void test9(){
        Account account = jdbcTemplateC3P0.queryForObject("SELECT * FROM account WHERE id = ?", new MyRowMapper(), 1);
        if (account != null){
            System.out.println(account);
        } else{
            System.out.println("NULL");
        }
    }

    /**
     * 查询多条记录
     */
    @Test
    public void test10(){
        List<Account> accounts = jdbcTemplateC3P0.query("SELECT * FROM account", new MyRowMapper());
        accounts.forEach(System.out::println);
    }
}
