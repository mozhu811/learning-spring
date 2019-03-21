package learningspring.ioc.xml.demo4;

/**
 * //TODO
 *
 * @author Chen Rui
 * @version 1.0
 **/
public class Employee {

    private String name;
    private Car car;
    private Dog dog;
    private Cat cat;

    public Employee() {
    }

    public Employee(String name, Car car, Dog dog, Cat cat) {
        this.name = name;
        this.car = car;
        this.dog = dog;
        this.cat = cat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public Cat getCat() {
        return cat;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", car=" + car +
                ", dog=" + dog +
                ", cat=" + cat +
                '}';
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }
}
