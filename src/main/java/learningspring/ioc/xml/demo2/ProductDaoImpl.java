package learningspring.ioc.xml.demo2;

/**
 * //TODO
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class ProductDaoImpl implements ProductDao{
    @Override
    public void save() {
        System.out.println("ProductDaoImpl.save");
    }

    public void setup(){
        System.out.println("ProductDaoImpl.setup");
    }

    public void destroy(){
        System.out.println("ProductDaoImpl.destroy");
    }
}
