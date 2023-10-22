import java.util.ArrayList;
import java.util.Collections;

class User implements Cloneable, Comparable<User> {
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

    // Getters and setters
    public String getName() {
        return name;
    }

    public int getUserID() {
        return userID;
    }

    public Address getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    // Getter for policies
    public ArrayList<InsurancePolicy> getPolicies() {
        return policies;
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

    // Method to filter policies based on the expiry date for this user
    public ArrayList<InsurancePolicy> filterByExpiryDate(MyDate date) {
        ArrayList<InsurancePolicy> filteredList = new ArrayList<>();
        for (InsurancePolicy policy : policies) {
            if (date.isExpired(policy.getExpiryDate())) {
                filteredList.add(policy);
            }
        }
        return filteredList;
    }

    // Override toString method to convert the entire object to string
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User ID: ").append(userID).append("\n");
        sb.append("Name: ").append(name).append("\n");
        sb.append("Address: ").append(address).append("\n");
        sb.append("Policies Information:\n");
        for (InsurancePolicy policy : policies) {
            sb.append(policy.toString()).append("\n");
        }
        return sb.toString();
    }

    // Override clone method to support cloning
    @Override
    public User clone() throws CloneNotSupportedException {
        User clonedUser = (User) super.clone();
        clonedUser.address = (Address) this.address.clone();
        clonedUser.policies = InsurancePolicy.deepCopy(this.policies);
        return clonedUser;
    }

    // Override compareTo method to compare users based on the city name
    @Override
    public int compareTo(User other) {
        return this.address.getCity().compareTo(other.address.getCity());
    }

    // Method to create a deep copy of user's policies
    public ArrayList<InsurancePolicy> deepCopyPolicies() {
        return InsurancePolicy.deepCopy(this.policies);
    }

    // Method to create a shallow copy of user's policies
    public ArrayList<InsurancePolicy> shallowCopyPolicies() {
        return InsurancePolicy.shallowCopy(this.policies);
    }

    // Method to sort all the user's policies based on the expiry date
    public ArrayList<InsurancePolicy> sortPoliciesByDate() {
        ArrayList<InsurancePolicy> sortedList = new ArrayList<>(this.policies);
        Collections.sort(sortedList);
        return sortedList;
    }
}
