import java.util.ArrayList;
import java.util.Collections;

enum CarType {
    SUV,
    SED,
    LUX,
    HATCH,
    
}


class Car implements Cloneable {
    String model;
    private CarType type;
    private int manufacturingYear;
    double price;

    // Constructor
    public Car(String model, CarType type, int manufacturingYear, double price) {
        this.model = model;
        this.type = type;
        this.manufacturingYear = manufacturingYear;
        this.price = price;
    }

    // Getters and setters
    public String getModel() {
        return model;
    }

    public CarType getType() {
        return type;
    }

    public int getManufacturingYear() {
        return manufacturingYear;
    }

    public double getPrice() {
        return price;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public void setManufacturingYear(int manufacturingYear) {
        this.manufacturingYear = manufacturingYear;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Override toString method to convert the entire object to string
    @Override
    public String toString() {
        return "Car Model: " + model + "\n" +
               "Car Type: " + type + "\n" +
               "Manufacturing Year: " + manufacturingYear + "\n" +
               "Car Price: $" + price;
    }

    // Override clone method to support cloning
    @Override
    public Car clone() throws CloneNotSupportedException {
        return (Car) super.clone();
    }
}
