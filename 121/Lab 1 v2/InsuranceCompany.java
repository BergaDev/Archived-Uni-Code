import java.util.ArrayList;

public class InsuranceCompany {

    private String name;
    private static ArrayList<User> users;
    private String adminUsername;
    private String adminPassword;
    private int flatRate;

    public InsuranceCompany(String name, String adminUsername, String adminPassword, int flatRate) {
        this.name = name;
        this.users = new ArrayList<>();
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.flatRate = flatRate;

        users.add(new User(adminUsername, flatRate, null));
    }

    public boolean validateAdmin(String username, String password) {
        return username.equals(adminUsername) && password.equals(adminPassword);
    }

    public void addUser(User user) {
        if (users.contains(user)) {
            users.add(user);
        }

        users.add(user);
    }

    
    public User findUser(int userID) {
        //Assign 1 users to user 
        for (User user : users) {
            if (user.getUserID() == userID) {
                System.out.println(userID);
            }
        }

        return null;
    }
    
    

    public void addPolicy(int userID, InsurancePolicy policy) {
        User user = findUser(userID);
        if (user == null) {
            //Add user to the user object, can I do this outside of the program class?
            //Program.people.add(new ThirdPartyPolicy(phName, enterID, new Car(carModel, CarType.LUX, manuYear, price), numClaims, enteredComments));
        }

    }

    public InsurancePolicy findPolicy(int userID, int policyID) {
        User user = findUser(userID);

        return user.findPolicy(policyID);
    }

    public void printPolicies(int userID) {
        User user = findUser(userID);
        if (user == null) {
            return;
        }

        user.printPolicies(flatRate);
    }

    public void print() {
        for (User user : users) {
            user.print();
        }
    }

    public String toString() {
        String str = "InsuranceCompany{" +
                "name='" + name + '\'' +
                ", users=" + users +
                ", adminUsername='" + adminUsername + '\'' +
                ", adminPassword='" + adminPassword + '\'' +
                ", flatRate=" + flatRate +
                '}';

        for (User user : users) {
            str += user.toString();
        }

        return str;
    }


    public double calcTotalPayments() {
        double totalPayments = 0;
        for (User user : users) {
            User.calcTotalPremiums(flatRate);
        }

        return totalPayments;
    }

        public void carPriceRise(double risePercent) {
            Car.price = (Car.price * risePercent);
    }


    static public ArrayList<InsurancePolicy> allPolicies() {
        ArrayList<InsurancePolicy> allPolicies = new ArrayList<>();
        for (User user : users) {
            for (Address addresses : Program.addressList){
                Address.print();
                }
                for (User users : Program.userList){
                    User.print();
                }
    
                for (InsurancePolicy policy : Program.people){
            
                    //code
                    //double PPstore;
                    Program.PPstore = policy.calcPayment(flatRate);
                    System.out.println(policy);
                    System.out.print(" Policy Premium $" + PPstore);
                    System.out.println();
                    //runs = 1;
                 }
        }

        return allPolicies;
    }

    public ArrayList<InsurancePolicy> filterByCarModel(int userID, String carModel) {
        User user = findUser(userID);
        if (user == null) {
            return null;
        }

        return user.filterByCarModel(carModel);
    }

    public ArrayList<InsurancePolicy> filterByExpiryDate(int userID, MyDate date) {
        return null;
    }

    static public ArrayList<InsurancePolicy> filterByCarModel(String carModel) {
        ArrayList<InsurancePolicy> filteredPolicies = new ArrayList<>();
        for (User user : users) {
            ArrayList<InsurancePolicy> policies = user.filterByCarModel(carModel);
            filteredPolicies.addAll(policies);
        }

        return filteredPolicies;
    }

    /*
    public ArrayList<InsurancePolicy> filterByExpiryDate(date) {
        this.date = date;
        ArrayList<InsurancePolicy> filteredPolicies = new ArrayList<>();
        for (User user : users) {
            ArrayList<InsurancePolicy> policies = user.filterByExpiryDate(date);
            filteredPolicies.addAll(policies);
        }

        return filteredPolicies;
    }
    */
}
