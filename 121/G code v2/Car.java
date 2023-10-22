import java.util.ArrayList;
import java.util.Scanner;

// CarType enum
enum CarType {
    SUV,
    SED,
    LUX,
    HATCH,
    // Add more types as needed
}

// Car class
class Car {
    String model;
    CarType type;
    int manufacturingYear;
    double price;

    // Constructor
    public Car(String model, CarType type, int manufacturingYear, double price) {
        this.model = model;
        this.type = type;
        this.manufacturingYear = manufacturingYear;
        this.price = price;
    }

    // Add getters and setters if needed
}
