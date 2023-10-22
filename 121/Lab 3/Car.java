public enum CarType {
    SUV,
    SED,
    LUX,
    HATCH,
}

public class Car {

    private String model;
    private CarType type;
    private int manufacturingYear;
    private double price;

    public Car(String model, CarType type, int manufacturingYear, double price) {
        this.model = model;
        this.type = type;
        this.manufacturingYear = manufacturingYear;
        this.price = price;
    }

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

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", type=" + type +
                ", manufacturingYear=" + manufacturingYear +
                ", price=" + price +
                '}';
    }
}
