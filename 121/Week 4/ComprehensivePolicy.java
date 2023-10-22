import java.util.ArrayList;
import java.util.Collections;

class ComprehensivePolicy extends InsurancePolicy implements Cloneable {
    private int driverAge;
    private int level;

    // Constructor
    public ComprehensivePolicy(String policyHolderName, int id, Car car, int numberOfClaims, int driverAge, int level, MyDate expiryDate) {
        super(policyHolderName, id, car, numberOfClaims, expiryDate);
        this.driverAge = driverAge;
        this.level = level;
    }

    // Getters and setters
    public int getDriverAge() {
        return driverAge;
    }

    public int getLevel() {
        return level;
    }

    public void setDriverAge(int driverAge) {
        this.driverAge = driverAge;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    // Override the calcPayment method for ComprehensivePolicy
    @Override
    public double calcPayment(int flatRate) {
        double comprehensivePremium = getCar().getPrice() / 50 + getNumberOfClaims() * 200 + flatRate;
        if (driverAge < 30) {
            comprehensivePremium += (30 - driverAge) * 50;
        }
        return comprehensivePremium;
    }

    // Override the print method to include additional attributes
    @Override
    public void print() {
        super.print();
        System.out.println("Driver Age: " + driverAge);
        System.out.println("Level: " + level);
    }

    // Override the toString method to include additional attributes
    @Override
    public String toString() {
        return super.toString() + "\nDriver Age: " + driverAge + "\nLevel: " + level;
    }

    // Override the clone method to create a deep copy
    @Override
    public ComprehensivePolicy clone() throws CloneNotSupportedException {
        ComprehensivePolicy clonedPolicy = (ComprehensivePolicy) super.clone();
        clonedPolicy.setExpiryDate(new MyDate(this.getExpiryDate().getDay(), this.getExpiryDate().getMonth(), this.getExpiryDate().getYear()));
        clonedPolicy.setCar(new Car(this.getCar().getModel(), this.getCar().getType(), this.getCar().getManufacturingYear(), this.getCar().getPrice()));
        return clonedPolicy;
    }
}
