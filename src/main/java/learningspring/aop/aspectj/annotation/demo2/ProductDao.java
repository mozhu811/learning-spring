package learningspring.aop.aspectj.annotation.demo2;

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
