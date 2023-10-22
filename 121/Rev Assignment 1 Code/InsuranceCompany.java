/* Matthew Bergamini 8137225 */
import java.util.ArrayList;


class InsuranceCompany implements Cloneable {
    private String name;
    private static ArrayList<User> users;
    private String adminUsername;
    private String adminPassword;
    private int flatRate;

    // Constructor
    public InsuranceCompany(String name, String adminUsername, String adminPassword, int flatRate) {
        this.name = name;
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.flatRate = flatRate;
        this.users = new ArrayList<>();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public int getFlatRate() {
        return flatRate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public void setFlatRate(int flatRate) {
        this.flatRate = flatRate;
    }

    // Validate admin login
    public boolean validateAdmin(String username, String password) {
        if (username == adminUsername & password == adminPassword){
            InsuranceTest.isValid = true;
            return true;
        } else {
            return false;
        }
    }

    // Add a user to the company
    public boolean addUser(User user) {
        for (User u : users) {
            if (u.getUserID() == user.getUserID()) {
                return false;
            }
        }
        users.add(user);
        return true;
    }

    // Add a user to the company by providing details
    public boolean addUser(String name, int userID, Address address) {
        User newUser = new User(name, userID, address);
        return addUser(newUser);
    }

    // Find a user by userID
    public static User findUser(int userID) {
        for (User user : users) {
            if (user.getUserID() == userID) {
                return user;
            }
        }
        return null;
    }

    // Add a policy to a user in the company
    public boolean addPolicy(int userID, InsurancePolicy policy) {
        User user = findUser(userID);
        if (user != null) {
            return user.addPolicy(policy);
        }
        return false;
    }

    // Find a policy for a user by policyID
    public InsurancePolicy findPolicy(int userID, int policyID) {
        User user = findUser(userID);
        if (user != null) {
            return user.findPolicy(policyID);
        }
        return null;
    }

    // Print user information and policies by userID
    public static void printPolicies(int userID) {
        User user = findUser(userID);
        if (user != null) {
            user.print();
        } else {
            System.out.println("User not found for the given userID.");
        }
    }

    // Print all users and their policies in the company
    public void print() {
        System.out.println("Insurance Company: " + name);
        for (User user : users) {
            user.print();
        }
    }

    // Create and add a ThirdPartyPolicy for a user by userID
    public boolean createThirdPartyPolicy(int userID, String policyHolderName, int id, Car car,
                                          int numberOfClaims, MyDate expiryDate, String comments) {
        User user = findUser(userID);
        if (user != null) {
            ThirdPartyPolicy policy = new ThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, comments, expiryDate);
            return user.addPolicy(policy);
        }
        return false;
    }

    // Create and add a ComprehensivePolicy for a user by userID
    public boolean createComprehensivePolicy(int userID, String policyHolderName, int id, Car car,
                                             int numberOfClaims, MyDate expiryDate, int driverAge, int level) {
        User user = findUser(userID);
        if (user != null) {
            ComprehensivePolicy policy = new ComprehensivePolicy(policyHolderName, id, car, numberOfClaims, driverAge, level, expiryDate);
            return user.addPolicy(policy);
        }
        return false;
    }

    // Calculate the total premium payments for a user by userID
    public double calcTotalPayments(int userID) {
        User user = findUser(userID);
        if (user != null) {
            return user.calcTotalPremiums(flatRate);
        }
        return 0.0;
    }

    // Calculate the total premium payments for all users in the company
    public double calcTotalPayments() {
        double totalPremium = 0;
        for (User user : users) {
            totalPremium += user.calcTotalPremiums(flatRate);
        }
        return totalPremium;
    }

    // Raise the price of cars for all users and policies by the given percentage
    public void carPriceRise(double risePercent) {
        for (User user : users) {
            user.carPriceRiseAll(risePercent);
        }
    }

    // Get all policies in the company
    public ArrayList<InsurancePolicy> allPolicies() {
        ArrayList<InsurancePolicy> allPolicies = new ArrayList<>();
        for (User user : users) {
            allPolicies.addAll(user.getPolicies());
        }
        return allPolicies;
    }

    // Filter policies by car model for all users in the company
    public static ArrayList<InsurancePolicy> filterByCarModel(String carModel) {
        ArrayList<InsurancePolicy> filteredList = new ArrayList<>();
        for (User user : users) {
            ArrayList<InsurancePolicy> policies = user.filterByCarModel(carModel);
            filteredList.addAll(policies);
        }
        return filteredList;
    }

    // Filter policies by expiry date for all users in the company
    public ArrayList<InsurancePolicy> filterByExpiryDate(MyDate date) {
        ArrayList<InsurancePolicy> filteredList = new ArrayList<>();
        for (User user : users) {
            ArrayList<InsurancePolicy> policies = user.filterByExpiryDate(date);
            filteredList.addAll(policies);
        }
        return filteredList;
    }

    // Filter policies by car model for a specific user by userID
    public ArrayList<InsurancePolicy> filterByCarModel(int userID, String carModel) {
        User user = findUser(userID);
        if (user != null) {
            return user.filterByCarModel(carModel);
        }
        return new ArrayList<>();
    }

    // Filter policies by expiry date for a specific user by userID
    public ArrayList<InsurancePolicy> filterByExpiryDate(int userID, MyDate date) {
        User user = findUser(userID);
        if (user != null) {
            return user.filterByExpiryDate(date);
        }
        return new ArrayList<>();
    }

    // Find a user with the given ID (valid) and change their current address
    public void changeUserAddress(int userID, Address newAddress) {
        User user = findUser(userID);
        if (user != null) {
            user.setAddress(newAddress);
        } else {
            System.out.println("User not found for the given userID.");
        }
    }

    // Override toString method to convert the entire object to string
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Insurance Company: ").append(name).append("\n");
        for (User user : users) {
            sb.append(user.toString()).append("\n");
        }
        return sb.toString();
    }
}
