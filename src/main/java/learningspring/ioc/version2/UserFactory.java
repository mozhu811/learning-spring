package learningspring.ioc.version2;

/**
 * 佛祖保佑，此代码无bug，
 * 就算有，也一眼看出。
 *
 * @author ray
 * @version 1.0
 * @date 2018-06-10 下午11:52
 **/
public class UserFactory {
    public static User getUser(){
        return new User();
    }
}
