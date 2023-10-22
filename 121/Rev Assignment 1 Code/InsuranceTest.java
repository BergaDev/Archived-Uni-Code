/* Matthew Bergamini 8137225 */

import java.util.ArrayList;
import java.util.Scanner;

public class InsuranceTest implements Cloneable {
    static int choice = 0;
    static boolean isValid;
    public static void main(String[] args) {
        Address userAddress = new Address(123, "Beach", "North Wollongong", "Wollongong");
        User user = new User("Michael", 1001, userAddress);
        Car car1 = new Car("Toyota Corolla", CarType.SED, 2019, 25000);
        

        Scanner input = new Scanner(System.in);
        while (choice != 30){
            if (choice == 0){

                System.out.println("Choose an option");
                System.out.println("1) Test Case");
                System.out.println("2) Create User");
                System.out.println("3) Create ThirdParty Policy");
                System.out.println("4) Create Comprehensive Policy");
                System.out.println("5) Print User Information");
                System.out.println("6) Filter By Car Model");
                System.out.println("7) Filter by Expiry Date");
                System.out.println("8) Update Address");
                System.out.println("9) Log Out");
                System.out.println("10) It's like option 1 but we clone");
                choice = input.nextInt();

        }

       if (choice == 1) {
            ArrayList<InsurancePolicy> User = new ArrayList<>();

        // Create some car instances
        //Car car1 = new Car("Toyota Corolla", CarType.SED, 2019, 25000);
        Car car2 = new Car("BMW X5", CarType.SUV, 2021, 60000);
        Car car3 = new Car("Tesla Model Y", CarType.LUX, 2023, 80000);

        // Create insurance policy instances
        ThirdPartyPolicy policy1 = new ThirdPartyPolicy("John Doe", 101, car1, 2, "Basic coverage", new MyDate(30, 6, 2022));
        ComprehensivePolicy policy2 = new ComprehensivePolicy("Alice Smith", 102, car2, 1, 28, 2, new MyDate(15, 8, 2024));
        ComprehensivePolicy policy3 = new ComprehensivePolicy("Bob Johnson", 103, car3, 0, 35, 3, new MyDate(31, 12, 2023));

        // Add policies to a list
        ArrayList<InsurancePolicy> policies = new ArrayList<>();
        policies.add(policy1);
        policies.add(policy2);
        policies.add(policy3);

        // Create a user
        //Address userAddress = new Address(123, "Main St", "Downtown", "New York");
        //User user = new User("Michael", 1001, userAddress);
        //Adding second user 
        Address userAddress2 = new Address(6, "North Street", "North Wollongong", "Wollongong");
        User user2 = new User("James", 69, userAddress2);

        // Add policies to the user
        user.addPolicy(policy1);
        user2.addPolicy(policy2);
        user.addPolicy(policy3);

        // Print user information along with policies details
        System.out.println("User Information:");
        for (InsurancePolicy users : User){
            user.print();
        }
        
        //user.print();
        //user2.print();
        

        // Find a policy by policy ID (valid)
        InsurancePolicy foundPolicy = user.findPolicy(102);
        if (foundPolicy != null) {
            System.out.println("Found Policy:");
            foundPolicy.print();
            foundPolicy.car.price *= 1.1; // Car price rise by 10%
            System.out.println("Policy after car price rise:");
            foundPolicy.print();
            foundPolicy.policyHolderName = "Robert";
            foundPolicy.car.model = "Toyota Camry 2018";
        } else {
            System.out.println("Policy has not been found");
        }

        // Change the city of the user, this will do user1
        user.getAddress().setCity("Wollongong");

        // Ask the customer to enter the information for a new address
        /*
        System.out.print("Enter new street number: ");
        int newStreetNum = input.nextInt();
        System.out.print("Enter new street name: ");
        String newStreet = input.next();
        System.out.print("Enter new suburb: ");
        String newSuburb = input.next();
        System.out.print("Enter new city: ");
        String newCity = input.next();
        */
        //Affects user1
        Address newAddress = new Address(6, "newStreet", "newSuburb", "newCity");
        user.address = newAddress;

        // Print user information after address change
        System.out.println("User Information after address change:");
        user.print();

        // Print the total premium payments for all policies this user owns
        double totalPremium = user.calcTotalPremiums(100); // Assuming flatRate is 100
        System.out.println("Total Premium Payments: $" + totalPremium);

        // Add 10% to the price of cars for all the policies this user owns
        user.carPriceRiseAll(0.1);

        // Print the total premium payments again after the price increase
        totalPremium = user.calcTotalPremiums(100); // Assuming flatRate is 100
        System.out.println("Total Premium Payments after 10% car price increase: $" + totalPremium);

        // Ask the customer to enter a car model for filtering
        ArrayList<InsurancePolicy> filteredPolicies = user.filterByCarModel("Tesla Model Y");

        // Print the filtered list of policies
        System.out.println("\nFiltered Policies:");
        InsurancePolicy.printPolicies(filteredPolicies, 100); 

        // Test case for validateAdmin method
        InsuranceCompany company = new InsuranceCompany("XYZ Insurance", "admin", "password", 100);

        // Test with valid admin credentials
        boolean validLogin = company.validateAdmin("admin", "password");
        System.out.println("Valid Admin Login: " + validLogin); // Expected output: true

        // Test with invalid admin credentials
        boolean invalidLogin = company.validateAdmin("admin", "Wrongpassword");
        System.out.println("Valid Admin Login: " + invalidLogin ); // Expected output: false

        choice = 0;
            } else if (choice == 2){
                InsuranceCompany company = new InsuranceCompany("XYZ Insurance", "admin", "password", 100);
                System.out.println("Enter the admin Username");
                String username = input.nextLine();
                System.out.println("Enter the admin password");
                String password = input.nextLine();
                company.validateAdmin(username, password);
                if (choice == 2){
                    System.out.println("Valid Admin Login:"); // Expected output: true

                    //Create user here
                    System.out.print("Users name: ");
                    String name = input.nextLine();
                    System.out.print("Users id");
                    int id = input.nextInt();
                    System.out.println("This is 1st user, setting userAddress");
                    System.out.print("Street Number: ");
                    int userStreetNo = input.nextInt();
                    System.out.print("Street Name: ");
                    input.nextLine();
                    String userStreet = input.nextLine();
                    System.out.print("Suburb: ");
                    input.nextLine();
                    String userSuburb = input.nextLine();
                    System.out.print("City: ");
                    input.nextLine();
                    String city = input.nextLine();

                    //Address userAddress = new Address(123, "Main St", "Downtown", "New York");
                    //Address userAddress = new Address(userStreetNo, userStreet, userSuburb, city);
                    User user2 = new User(name, id, userAddress);
                    System.out.println("Created user is: " + name);
                    

                } else {
                    System.out.println("Those are the wrong credentials");
                }
                choice = 0;
                

            } else if (choice == 3){
                ArrayList<InsurancePolicy> policies = new ArrayList<>();
                //Address userAddress = new Address(123, "Main St", "Downtown", "New York");
                //User user = new User("Michael", 1001, userAddress);

                System.out.println("User id: ");
                int customerID = input.nextInt();
                System.out.println("Number of claims: ");
                int customerClaims = input.nextInt();
                System.out.println("Comments: ");
                input.nextLine();
                String customerComments = input.nextLine();
                System.out.print("Expiry day: ");
                int expDay = input.nextInt();
                System.out.print("Expiry month: ");
                int expMonth = input.nextInt();
                System.out.print("Expiry Year: ");
                int expYear = input.nextInt();

                System.out.print("Model Name: ");
                input.nextLine();
                String carModel = input.nextLine();
                System.out.print("Manufacturing Year: ");
                int manuYear = input.nextInt();
                System.out.print("Car Price: ");
                int carPrice = input.nextInt();
                System.out.print("Car type: 1) SED, 2) LUX, 3) SUV, 4) HATCH");
                input.nextLine();
                int type = input.nextInt();
                
                if (type == 1){
                    Car car2 = new Car(carModel, CarType.SED, manuYear, carPrice);
                    ThirdPartyPolicy policy1 = new ThirdPartyPolicy(user.getName(), customerID, car2, customerClaims, customerComments, new MyDate(expDay, expMonth, expYear));
                    policies.add(policy1);
                    user.addPolicy(policy1);
                    System.out.println("Added " + user.getName() + " Policy for " + car1.getModel() + " Sucsessfully");
                } else if (type == 2){
                    Car car2 = new Car(carModel, CarType.LUX, manuYear, carPrice);
                    ThirdPartyPolicy policy1 = new ThirdPartyPolicy(user.getName(), customerID, car2, customerClaims, customerComments, new MyDate(expDay, expMonth, expYear));
                    policies.add(policy1);
                    user.addPolicy(policy1);
                    System.out.println("Added " + user.getName() + " Policy for " + car2.getModel() + " Sucsessfully");
                } else if (type == 3){
                    Car car2 = new Car(carModel, CarType.SUV, manuYear, carPrice);
                    ThirdPartyPolicy policy1 = new ThirdPartyPolicy(user.getName(), customerID, car2, customerClaims, customerComments, new MyDate(expDay, expMonth, expYear));
                    policies.add(policy1);
                    user.addPolicy(policy1);
                    System.out.println("Added " + user.getName() + " Policy for " + car2.getModel() + " Sucsessfully");
                } else if (type == 4){
                    Car car2 = new Car(carModel, CarType.HATCH, manuYear, carPrice);
                    ThirdPartyPolicy policy1 = new ThirdPartyPolicy(user.getName(), customerID, car2, customerClaims, customerComments, new MyDate(expDay, expMonth, expYear));
                    policies.add(policy1);
                    user.addPolicy(policy1);
                    System.out.println("Added " + user.getName() + " Policy for " + car2.getModel() + " Sucsessfully");
                } else {
                    System.out.println("You entered something wrong");
                }
                choice = 0;
                
                //ThirdPartyPolicy policy1 = new ThirdPartyPolicy(customerName, customerID, car1, customerClaims, customerComments, new MyDate(expDay, expMonth, expYear));
            } else if (choice == 4){
                ArrayList<InsurancePolicy> policies = new ArrayList<>();
                //Address userAddress = new Address(123, "Main St", "Downtown", "New York");
                //User user = new User("Michael", 1001, userAddress);

                System.out.println("User id: ");
                int customerID = input.nextInt();
                System.out.println("Number of claims: ");
                int customerClaims = input.nextInt();
                System.out.print("Holder Age: ");
                int hAge = input.nextInt();
                System.out.print("Holder level: ");
                int hLevel = input.nextInt();
                System.out.println("Comments: ");
                input.nextLine();
                String customerComments = input.nextLine();
                System.out.print("Expiry day: ");
                int expDay = input.nextInt();
                System.out.print("Expiry month: ");
                int expMonth = input.nextInt();
                System.out.print("Expiry Year: ");
                int expYear = input.nextInt();

                System.out.print("Model Name: ");
                input.nextLine();
                String carModel = input.nextLine();
                System.out.print("Manufacturing Year: ");
                int manuYear = input.nextInt();
                System.out.print("Car Price: ");
                int carPrice = input.nextInt();
                System.out.print("Car type: 1) SED, 2) LUX, 3) SUV, 4) HATCH");
                input.nextLine();
                int type = input.nextInt();
                
                if (type == 1){
                    Car car2 = new Car(carModel, CarType.SED, manuYear, carPrice);
                    ComprehensivePolicy policy1 = new ComprehensivePolicy(user.getName(), customerID, car2, customerClaims, hAge, hLevel, new MyDate(expDay, expMonth, expYear));
                    policies.add(policy1);
                    user.addPolicy(policy1);
                    System.out.println("Added " + user.getName() + " Policy for " + car2.getModel() + " Sucsessfully");
                } else if (type == 2){
                    Car car2 = new Car(carModel, CarType.LUX, manuYear, carPrice);
                    ComprehensivePolicy policy1 = new ComprehensivePolicy(user.getName(), customerID, car2, customerClaims, hAge, hLevel, new MyDate(expDay, expMonth, expYear));
                    policies.add(policy1);
                    user.addPolicy(policy1);
                    System.out.println("Added " + user.getName() + " Policy for " + car2.getModel() + " Sucsessfully");
                } else if (type == 3){
                    Car car2 = new Car(carModel, CarType.SUV, manuYear, carPrice);
                    ComprehensivePolicy policy1 = new ComprehensivePolicy(user.getName(), customerID, car2, customerClaims, hAge, hLevel, new MyDate(expDay, expMonth, expYear));
                    policies.add(policy1);
                    user.addPolicy(policy1);
                    System.out.println("Added " + user.getName() + " Policy for " + car2.getModel() + " Sucsessfully");
                } else if (type == 4){
                    Car car2 = new Car(carModel, CarType.HATCH, manuYear, carPrice);
                    ComprehensivePolicy policy1 = new ComprehensivePolicy(user.getName(), customerID, car2, customerClaims, hAge, hLevel, new MyDate(expDay, expMonth, expYear));
                    policies.add(policy1);
                    user.addPolicy(policy1);
                    System.out.println("Added " + user.getName() + " Policy for " + car2.getModel() + " Sucsessfully");
                } else {
                    System.out.println("You entered something wrong");
                }
                choice = 0;
                
                //ThirdPartyPolicy policy1 = new ThirdPartyPolicy(customerName, customerID, car1, customerClaims, customerComments, new MyDate(expDay, expMonth, expYear));
            } else if (choice == 5){
                System.out.println("Enter the Users ID (enter 1001)");
                int userID = input.nextInt();
                InsuranceCompany.printPolicies(userID);

                choice = 0;

            } else if (choice == 6){ 
                System.out.print("Enter the car model: ");
                String carModel = input.nextLine();
                InsuranceCompany.filterByCarModel(carModel);
            } else if (choice == 7){
                System.out.print("Expiry day: ");
                int expDay = input.nextInt();
                System.out.print("Expiry month: ");
                int expMonth = input.nextInt();
                System.out.print("Expiry Year: ");
                int expYear = input.nextInt();

                choice = 0;


                //InsuranceCompany.filterByExpiryDate(MyDate (10-10-10));

            } else if (choice == 8){

                System.out.print("Enter new street number: ");
                int newStreetNum = input.nextInt();
                System.out.print("Enter new street name: ");
                String newStreet = input.next();
                System.out.print("Enter new suburb: ");
                String newSuburb = input.next();
                System.out.print("Enter new city: ");
                String newCity = input.next();
                //Affects user1
                Address newAddress = new Address(newStreetNum, newStreet, newSuburb, newCity);
                user.address = newAddress;
                System.out.println("Change Sucsessful");

                choice = 0;
            } else if (choice == 9){
                choice = 30;
            } else if (choice == 10) {
            ArrayList<InsurancePolicy> User = new ArrayList<>();
            ArrayList<InsurancePolicy> deepCopyUsers = new ArrayList<>();
            ArrayList<InsurancePolicy> shalowCopyUsers = new ArrayList<>();

        // Create some car instances
        //Car car1 = new Car("Toyota Corolla", CarType.SED, 2019, 25000);
        Car car2 = new Car("BMW X5", CarType.SUV, 2021, 60000);
        Car car3 = new Car("Tesla Model Y", CarType.LUX, 2023, 80000);

        // Create insurance policy instances
        ThirdPartyPolicy policy1 = new ThirdPartyPolicy("John Doe", 101, car1, 2, "Basic coverage", new MyDate(30, 6, 2022));
        ComprehensivePolicy policy2 = new ComprehensivePolicy("Alice Smith", 102, car2, 1, 28, 2, new MyDate(15, 8, 2024));
        ComprehensivePolicy policy3 = new ComprehensivePolicy("Bob Johnson", 103, car3, 0, 35, 3, new MyDate(31, 12, 2023));

        // Add policies to a list
        ArrayList<InsurancePolicy> policies = new ArrayList<>();
        policies.add(policy1);
        policies.add(policy2);
        policies.add(policy3);

        // Create a user
        //Address userAddress = new Address(123, "Main St", "Downtown", "New York");
        //User user = new User("Michael", 1001, userAddress);
        //Adding second user 
        Address userAddress2 = new Address(6, "North Street", "North Wollongong", "Wollongong");
        User user2 = new User("James", 69, userAddress2);

        // Add policies to the user
        user.addPolicy(policy1);
        user2.addPolicy(policy2);
        user.addPolicy(policy3);

        // Print user information along with policies details
        System.out.println("User Information:");
        for (InsurancePolicy users : User){
            user.print();
        }

        shallowCopyUsers();

        shalowCopyUsers = (ArrayList)User.clone(); 
        
        System.out.println("Orginal Array List is " + User);
        System.out.println("Shallow copy Array List is " + shalowCopyUsers);

        /* 
        for (InsurancePolicy users : User) {
            try {
                deepCopyUsers.add(User.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        */
        
       

        choice = 0;
            }
        
        
        }
    }

    /*
     // Method to create a deep copy of users
     public ArrayList<User> deepCopyUsers() {
        ArrayList<User> deepCopy = new ArrayList<>();
        for (InsurancePolicy users : User) {
            try {
                deepCopy.add(User.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return deepCopy;
    }
*/

    public static ArrayList<User> shallowCopyUsers() {
      ArrayList<InsurancePolicy> shalowCopyUsers = new ArrayList<>();
      shallowCopyUsers = (ArrayList)User.clone();
      System.out.println("The shallow Coppied results are " + shallowCopyUsers);

    }
}