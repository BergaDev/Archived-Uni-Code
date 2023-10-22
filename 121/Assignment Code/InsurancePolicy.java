import java.util.ArrayList;

// InsurancePolicy class
abstract class InsurancePolicy {
    String policyHolderName;
    private int id;
    Car car;
    protected int numberOfClaims;
    private MyDate expiryDate;

    // Constructor
    public InsurancePolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate) {
        this.policyHolderName = policyHolderName;
        this.id = id;
        this.car = car;
        this.numberOfClaims = numberOfClaims;
        this.expiryDate = expiryDate;
    }

    // Getters and setters
    public String getPolicyHolderName() {
        return policyHolderName;
    }

    public int getId() {
        return id;
    }

    public Car getCar() {
        return car;
    }

    public int getNumberOfClaims() {
        return numberOfClaims;
    }

    public MyDate getExpiryDate() {
        return expiryDate;
    }

    public void setPolicyHolderName(String policyHolderName) {
        this.policyHolderName = policyHolderName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setNumberOfClaims(int numberOfClaims) {
        this.numberOfClaims = numberOfClaims;
    }

    public void setExpiryDate(MyDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    // Abstract method for calculating premium payment
    public abstract double calcPayment(int flatRate);

    // Static method to print a list of policies
    public static void printPolicies(ArrayList<InsurancePolicy> policies, int flatRate) {
        for (InsurancePolicy policy : policies) {
            policy.print();
            System.out.println("Premium: $" + policy.calcPayment(flatRate)); // Assuming flatRate is available
            System.out.println();
        }
    }

    // Method to increase the policy's car price by the given percentage
    public void carPriceRise(double risePercent) {
        car.setPrice(car.getPrice() * (1 + risePercent));
    }

    // Static method to increase the price of cars for all policies in a list
    public static void carPriceRiseAll(ArrayList<InsurancePolicy> policies, double risePercent) {
        for (InsurancePolicy policy : policies) {
            policy.carPriceRise(risePercent);
        }
    }

    // Static method to filter a list of policies based on the car model
    public static ArrayList<InsurancePolicy> filterByCarModel(ArrayList<InsurancePolicy> policies, String carModel) {
        ArrayList<InsurancePolicy> filteredList = new ArrayList<>();
        for (InsurancePolicy policy : policies) {
            if (policy.car.getModel().equalsIgnoreCase(carModel)) {
                filteredList.add(policy);
            }
        }
        return filteredList;
    }

    // Static method to filter a list of policies based on the expiry date
    public static ArrayList<InsurancePolicy> filterByExpiryDate(ArrayList<InsurancePolicy> policies, MyDate date) {
        ArrayList<InsurancePolicy> filteredList = new ArrayList<>();
        for (InsurancePolicy policy : policies) {
            if (date.isExpired(policy.getExpiryDate())) {
                filteredList.add(policy);
            }
        }
        return filteredList;
    }

    // Print method to display policy details
    public void print() {
        System.out.println("Policy Holder Name: " + policyHolderName);
        System.out.println("Policy ID: " + id);
        System.out.println("Car Details: " + car.getModel() + " - " + car.getType() + " - " + car.getManufacturingYear());
        System.out.println("Number of Claims: " + numberOfClaims);
        System.out.println("Expiry Date: " + expiryDate.getDay() + "-" + expiryDate.getMonth() + "-" + expiryDate.getYear());
    }

    // Override toString method to convert the entire object to string
    @Override
    public String toString() {
        return "Policy Holder Name: " + policyHolderName + "\n" +
               "Policy ID: " + id + "\n" +
               "Car Details: " + car.getModel() + " - " + car.getType() + " - " + car.getManufacturingYear() + "\n" +
               "Number of Claims: " + numberOfClaims + "\n" +
               "Expiry Date: " + expiryDate.getDay() + "-" + expiryDate.getMonth() + "-" + expiryDate.getYear();
    }
}
