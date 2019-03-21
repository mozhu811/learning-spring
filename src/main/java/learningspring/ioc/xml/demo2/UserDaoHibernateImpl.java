package learningspring.ioc.xml.demo2;

/**
 * //TODO
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class UserDaoHibernateImpl implements UserDao {
    @Override
    public void save() {
        System.out.println("UserDaoHibernateImpl.save");
    }
}
