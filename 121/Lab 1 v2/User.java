import java.util.ArrayList;

public class User {

    static private String name;
    static private int userID;
    static private Address address;
    static private ArrayList<InsurancePolicy> policies;
    static double userPremium;

    public User(String name, int userID, Address address) {
        this.name = name;
        this.userID = userID;
        this.address = address;
        this.policies = new ArrayList<>();
    }

    //Check duplicate and add 
    public boolean addPolicy(InsurancePolicy policy) {
        if (policies.contains(policy)) {
            return false;
        }

        policies.add(policy);
        return true;
    }

    //Search policies
    
    public InsurancePolicy findPolicy(int policyID) {
        for (InsurancePolicy policy : policies) {
            if (policy.getPolicyID() == policyID) {
                System.out.println(policy);
            }
        }

        return null;
    }

    //Print per policy
    public static void print() {
        System.out.println("User name: " + name);
        System.out.println("User ID: " + userID);
        System.out.println("Address: " + address);
        System.out.println("Policies:");
        
        for (InsurancePolicy policy : policies) {
            policy.print();
        }
    }


    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", userID=" + userID +
                ", address=" + address +
                ", policies=" + policies +
                '}';
    }

    public void printPolicies(int flatRate) {
        for (InsurancePolicy policy : policies) {
            policy.print();
        }
    }

    
    static public void calcTotalPremiums(int flatRate) {
        double totalPremiums = 0;
        for (InsurancePolicy policy : policies) {
            totalPremiums += (userPremium*(flatRate));
        }

        
    }
    

    public void carPriceRiseAll(double risePercent) {
        for (InsurancePolicy policy : policies) {
            policy.carPriceRise(risePercent);
        }
    }
     
    public ArrayList<InsurancePolicy> filterByCarModel(String carModel) {
        ArrayList<InsurancePolicy> filteredPolicies = new ArrayList<>();
        for (InsurancePolicy policy : policies) {
            if (Car.getCarModel().contains(carModel)) {
                filteredPolicies.add(policy);
            }
        }

        System.out.println(filteredPolicies);
    }
    
}