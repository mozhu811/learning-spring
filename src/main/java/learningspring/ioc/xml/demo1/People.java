package learningspring.ioc.xml.demo1;

/**
 * //TODO
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class People {
    private String name;

    public People() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                '}';
    }
}
