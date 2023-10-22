import java.util.ArrayList;
import java.util.Scanner;

// InsurancePolicy class
abstract class InsurancePolicy {
    String policyHolderName;
    int id;
    Car car;
    int numberOfClaims;
    MyDate expiryDate;

    // Constructor
    public InsurancePolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate) {
        this.policyHolderName = policyHolderName;
        this.id = id;
        this.car = car;
        this.numberOfClaims = numberOfClaims;
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

    // Static method to calculate the total premium payments for a list of policies
    public static double calcTotalPayments(ArrayList<InsurancePolicy> policies, int flatRate) {
        double totalPremium = 0;
        for (InsurancePolicy policy : policies) {
            totalPremium += policy.calcPayment(flatRate);
        }
        return totalPremium;
    }

    // Method to increase the policy's car price by the given percentage
    public void carPriceRise(double risePercent) {
        carPriceRise(risePercent);
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
            if (policy.car.model.equalsIgnoreCase(carModel)) {
                filteredList.add(policy);
            }
        }
        return filteredList;
    }

    // Print method to display policy details
    public void print() {
        System.out.println("Policy Holder Name: " + policyHolderName);
        System.out.println("Policy ID: " + id);
        System.out.println("Car Details: " + car.model + " - " + car.type + " - " + car.manufacturingYear);
        System.out.println("Number of Claims: " + numberOfClaims);
        System.out.println("Expiry Date: " + expiryDate.day + "-" + expiryDate.month + "-" + expiryDate.year);
    }

    double calcTotalPayments(int userID){
        return 0.0;
    }

    // Add getters and setters if needed
}
