package learningspring.ioc.xml.demo4;

/**
 * //TODO
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class Cat {
    private String name;
    private Integer length;

    public Cat() {
    }

    public Cat(String name, Integer length) {
        this.name = name;
        this.length = length;
    }

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
        return "Cat{" +
                "name='" + name + '\'' +
                ", length=" + length +
                '}';
    }
}
