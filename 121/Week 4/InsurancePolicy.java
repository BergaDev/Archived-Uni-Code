import java.util.ArrayList;

class InsurancePolicy implements Cloneable, Comparable<InsurancePolicy> {
    private String policyHolderName;
    private int id;
    private Car car;
    private int numberOfClaims;
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

    // Method to calculate the insurance policy payment
    public double calcPayment(int flatRate) {
        return 0; // The calculation will be implemented in the subclasses
    }

    // Method to print the insurance policy information
    public void print() {
        System.out.println("Policy Holder: " + policyHolderName);
        System.out.println("Policy ID: " + id);
        System.out.println("Car Information:");
        System.out.println(car);
        System.out.println("Number of Claims: " + numberOfClaims);
        System.out.println("Expiry Date: " + expiryDate);
    }

    // Override toString method to convert the entire object to string
    @Override
    public String toString() {
        return "Policy Holder: " + policyHolderName + "\n" +
               "Policy ID: " + id + "\n" +
               "Car Information:\n" + car + "\n" +
               "Number of Claims: " + numberOfClaims + "\n" +
               "Expiry Date: " + expiryDate;
    }

    // Override clone method to support cloning
    @Override
    public InsurancePolicy clone() throws CloneNotSupportedException {
        InsurancePolicy clonedPolicy = (InsurancePolicy) super.clone();
        clonedPolicy.car = this.car.clone();
        clonedPolicy.expiryDate = this.expiryDate.clone();
        return clonedPolicy;
    }

    // Override compareTo method to compare policies based on expiry date
    @Override
    public int compareTo(InsurancePolicy other) {
        return this.expiryDate.compareTo(other.expiryDate);
    }

    // Static method to make a shallow copy of an ArrayList of policies
    public static ArrayList<InsurancePolicy> shallowCopy(ArrayList<InsurancePolicy> policies) {
        return (ArrayList<InsurancePolicy>) policies.clone();
    }

    // Static method to make a deep copy of an ArrayList of policies using clone()
    public static ArrayList<InsurancePolicy> deepCopy(ArrayList<InsurancePolicy> policies) {
        ArrayList<InsurancePolicy> clonedList = new ArrayList<>();
        for (InsurancePolicy policy : policies) {
            try {
                clonedList.add(policy.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return clonedList;
    }

    public static void printPolicies(ArrayList<InsurancePolicy> policies, int i) {
    }
}
