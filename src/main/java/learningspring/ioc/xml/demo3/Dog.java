package learningspring.ioc.xml.demo3;

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
