

import java.util.ArrayList;
import java.util.List;

public class Car {

    private String brand;
    private String type;
    private long age;
    private CarState state;

    private List<KmState> kmStateList =new ArrayList<>();

    public Car(String brand, String type, long age, CarState state) {
        this.brand = brand;
        this.type = type;
        this.age = age;
        this.state = state;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public CarState getState() {
        return state;
    }

    public void setState(CarState state) {
        this.state = state;
    }

    public List<KmState> getKmStateList() {
        return kmStateList;
    }

    public void setKmStateList(List<KmState> kmStateList) {
        this.kmStateList = kmStateList;
    }
}
