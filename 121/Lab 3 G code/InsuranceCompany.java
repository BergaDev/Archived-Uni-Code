import java.util.ArrayList;

public class InsuranceCompany {
    private String name;
    private ArrayList<User> users;
    private String adminUsername;
    private String adminPassword;
    private int flatRate;

    // Constructor
    public InsuranceCompany(String name, String adminUsername, String adminPassword, int flatRate) {
        this.name = name;
        this.users = new ArrayList<>();
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.flatRate = flatRate;
    }

    // Method to validate admin login
    public boolean validateAdmin(String username, String password) {
        return adminUsername.equals(username) && adminPassword.equals(password);
    }

    // Method to add a user to the insurance company
    public boolean addUser(User user) {
        // Check if the userID is unique
        for (User u : users) {
            if (u.getUserID() == user.getUserID()) {
                return false;
            }
        }
        users.add(user);
        return true;
    }

    // Method to add a user to the insurance company by providing individual details
    public boolean addUser(String name, int userID, Address address) {
        User newUser = new User(name, userID, address);
        return addUser(newUser);
    }

    // Method to find a user by userID
    public User findUser(int userID) {
        for (User user : users) {
            if (user.getUserID() == userID) {
                return user;
            }
        }
        return null; // User not found
    }

    // Method to add a policy to a user
    public boolean addPolicy(int userID, InsurancePolicy policy) {
        User user = findUser(userID);
        if (user != null) {
            return user.addPolicy(policy);
        }
        return false; // User not found
    }

    // Method to find a policy by userID and policyID
    public InsurancePolicy findPolicy(int userID, int policyID) {
        User user = findUser(userID);
        if (user != null) {
            return user.findPolicy(policyID);
        }
        return null; // User not found
    }

    // Method to print user information and all policies for the given userID
    public void printPolicies(int userID) {
        User user = findUser(userID);
        if (user != null) {
            user.print();
        } else {
            System.out.println("User with ID " + userID + " not found.");
        }
    }

    // Method to print all users and their policies
    public void print() {
        for (User user : users) {
            user.print();
        }
    }

    // Method to create a Third-Party Policy for the given user
    public boolean createThirdPartyPolicy(int userID, String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, String comments) {
        User user = findUser(userID);
        if (user != null) {
            ThirdPartyPolicy policy = new ThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, comments, expiryDate);
            return user.addPolicy(policy);
        }
        return false; // User not found
    }

    // Method to create a Comprehensive Policy for the given user
    public boolean createComprehensivePolicy(int userID, String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, int driverAge, int level) {
        User user = findUser(userID);
        if (user != null) {
            ComprehensivePolicy policy = new ComprehensivePolicy(policyHolderName, id, car, numberOfClaims, driverAge, level, expiryDate);
            return user.addPolicy(policy);
        }
        return false; // User not found
    }

    // Method to calculate the total premium payments for the given user
    public double calcTotalPayments(int userID) {
        User user = findUser(userID);
        if (user != null) {
            return user.calcTotalPremiums(flatRate);
        }
        return 0; // User not found
    }

    // Method to calculate the total premium payments for all users in the company
    public double calcTotalPayments() {
        double totalPremium = 0;
        for (User user : users) {
            totalPremium += user.calcTotalPremiums(flatRate);
        }
        return totalPremium;
    }

    // Method to raise the price of cars for all users and policies by the given percentage
    public void carPriceRise(double risePercent) {
        for (User user : users) {
            user.carPriceRiseAll(risePercent);
        }
    }

    // Method to get a list of all policies in the company across all users
    public ArrayList<InsurancePolicy> allPolicies() {
        ArrayList<InsurancePolicy> allPolicies = new ArrayList<>();
        for (User user : users) {
            allPolicies.addAll(user.getPolicies());
        }
        return allPolicies;
    }

    // Method to filter policies by car model across all users
    public ArrayList<InsurancePolicy> filterByCarModel(String carModel) {
        ArrayList<InsurancePolicy> filteredList = new ArrayList<>();
        for (User user : users) {
            filteredList.addAll(user.filterByCarModel(carModel));
        }
        return filteredList;
    }

    // Method to filter policies by expiry date across all users
    public ArrayList<InsurancePolicy> filterByExpiryDate(MyDate date) {
        ArrayList<InsurancePolicy> filteredList = new ArrayList<>();
        for (User user : users) {
            filteredList.addAll(user.filterByExpiryDate(date));
        }
        return filteredList;
    }

    // Method to filter policies by car model for a given user
    public ArrayList<InsurancePolicy> filterByCarModel(int userID, String carModel) {
        User user = findUser(userID);
        if (user != null) {
            return user.filterByCarModel(carModel);
        }
        return new ArrayList<>(); // User not found
    }

    // Method to filter policies by expiry date for a given user
    public ArrayList<InsurancePolicy> filterByExpiryDate(int userID, MyDate date) {
        User user = findUser(userID);
        if (user != null) {
            return user.filterByExpiryDate(date);
        }
        return new ArrayList<>(); // User not found
    }

    // Additional methods and accessors if needed
}
