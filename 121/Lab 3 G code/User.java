import java.util.ArrayList;

// User class
class User {
    private String name;
    private int userID;
    private Address address;
    private ArrayList<InsurancePolicy> policies;

    // Constructor
    public User(String name, int userID, Address address) {
        this.name = name;
        this.userID = userID;
        this.address = address;
        this.policies = new ArrayList<>();
    }

    // Method to add a policy to the user's list of policies
    public boolean addPolicy(InsurancePolicy policy) {
        // Check if the policyID is unique
        for (InsurancePolicy p : policies) {
            if (p.getId() == policy.getId()) {
                return false;
            }
        }
        policies.add(policy);
        return true;
    }

    // Getter and setter for address
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    // Method to find a policy by policyID
    public InsurancePolicy findPolicy(int policyID) {
        for (InsurancePolicy policy : policies) {
            if (policy.getId() == policyID) {
                return policy;
            }
        }
        return null; // Policy not found
    }

    // Method to print all the information of this user, including policies information
    public void print() {
        System.out.println("User ID: " + userID);
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Policies Information:");
        InsurancePolicy.printPolicies(policies, 100); // Assuming flatRate is 100
    }

    // Method to calculate the total premium payments for all policies owned by this user
    public double calcTotalPremiums(int flatRate) {
        double totalPremium = 0;
        for (InsurancePolicy policy : policies) {
            totalPremium += policy.calcPayment(flatRate);
        }
        return totalPremium;
    }

    // Method to increase the price of cars for all policies owned by this user
    public void carPriceRiseAll(double risePercent) {
        for (InsurancePolicy policy : policies) {
            policy.getCar().setPrice(policy.getCar().getPrice() * (1 + risePercent));
        }
    }

    // Method to filter policies based on the car model for this user
    public ArrayList<InsurancePolicy> filterByCarModel(String carModel) {
        ArrayList<InsurancePolicy> filteredList = new ArrayList<>();
        for (InsurancePolicy policy : policies) {
            if (policy.getCar().getModel().equalsIgnoreCase(carModel)) {
                filteredList.add(policy);
            }
        }
        return filteredList;
    }
}
