package learningspring.ioc.version3;

/**
 * 佛祖保佑，此代码无bug，
 * 就算有，也一眼看出。
 *
 * @author ray
 * @version 1.0
 * @date 2018-06-11 上午12:19
 **/
public class User {
    private String name;
    private String major;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void sayHello(){
        System.out.println("" +
                "Call User.sayHello()……\n"
                + "My name is " + this.name
                + ", my major is " + this.major);
    }

}
