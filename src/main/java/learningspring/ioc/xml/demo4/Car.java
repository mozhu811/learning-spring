package learningspring.ioc.xml.demo4;

/**
 * //TODO
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class Car {
    private String name;
    private Double price;

    public Car() {
    }

    public Car(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
