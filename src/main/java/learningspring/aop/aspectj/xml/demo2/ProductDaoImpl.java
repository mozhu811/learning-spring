package learningspring.aop.aspectj.xml.demo2;

import java.util.Date;

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
