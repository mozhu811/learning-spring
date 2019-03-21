package learningspring.aop.aspectj.xml.demo2;

/**
 * //TODO
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
