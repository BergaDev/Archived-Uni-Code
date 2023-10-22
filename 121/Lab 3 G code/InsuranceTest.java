import java.util.ArrayList;
import java.util.Scanner;

public class InsuranceTest {
    public static void main(String[] args) {
        // Create some car instances
        Car car1 = new Car("Toyota Corolla", CarType.SED, 2019, 25000);
        Car car2 = new Car("BMW X5", CarType.SUV, 2021, 60000);
        Car car3 = new Car("Mercedes C-Class", CarType.LUX, 2020, 45000);

        // Create insurance policy instances
        ThirdPartyPolicy policy1 = new ThirdPartyPolicy("John Doe", 101, car1, 2, "Basic coverage", new MyDate(2025, 6, 30));
        ComprehensivePolicy policy2 = new ComprehensivePolicy("Alice Smith", 102, car2, 1, 28, 2, new MyDate(2024, 8, 15));
        ComprehensivePolicy policy3 = new ComprehensivePolicy("Bob Johnson", 103, car3, 0, 35, 3, new MyDate(2023, 12, 31));

        // Add policies to a list
        ArrayList<InsurancePolicy> policies = new ArrayList<>();
        policies.add(policy1);
        policies.add(policy2);
        policies.add(policy3);

        // Create a user
        Address userAddress = new Address(123, "Main St", "Downtown", "New York");
        User user = new User("Michael", 1001, userAddress);

        // Add policies to the user
        user.addPolicy(policy1);
        user.addPolicy(policy2);
        user.addPolicy(policy3);

        // Print user information along with policies details
        System.out.println("User Information:");
        user.print();

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

        // Change the city of the user
        user.getAddress().setCity("Wollongong");

        // Ask the customer to enter the information for a new address
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new street number: ");
        int newStreetNum = scanner.nextInt();
        System.out.print("Enter new street name: ");
        String newStreet = scanner.next();
        System.out.print("Enter new suburb: ");
        String newSuburb = scanner.next();
        System.out.print("Enter new city: ");
        String newCity = scanner.next();

        Address newAddress = new Address(newStreetNum, newStreet, newSuburb, newCity);
        user.setAddress(newAddress);

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
        System.out.print("Enter car model to filter policies: ");
        String carModelToFilter = scanner.next();
        ArrayList<InsurancePolicy> filteredPolicies = user.filterByCarModel(carModelToFilter);

        // Print the filtered list of policies
        System.out.println("\nFiltered Policies:");
        InsurancePolicy.printPolicies(filteredPolicies, 100); // Assuming flatRate is 100

        // Additional testing for InsuranceCompany
        InsuranceCompany insuranceCompany = new InsuranceCompany("ABC Insurance", "admin", "password", 100);
        
        // Login to insuranceCompany
        System.out.print("Enter admin username: ");
        String adminUsername = scanner.next();
        System.out.print("Enter admin password: ");
        String adminPassword = scanner.next();
        boolean loggedIn = insuranceCompany.validateAdmin(adminUsername, adminPassword);
        if (loggedIn) {
            System.out.println("Admin login successful!");
        } else {
            System.out.println("Invalid admin credentials. Exiting...");
            System.exit(0);
        }
        
        // Add users to insuranceCompany
        insuranceCompany.addUser(user);
        insuranceCompany.addUser("John Doe", 1002, new Address(456, "Park Ave", "Uptown", "Los Angeles"));
        insuranceCompany.addUser("Alice Smith", 1003, new Address(789, "Broadway St", "Downtown", "Chicago"));
        insuranceCompany.addUser("Bob Johnson", 1004, new Address(111, "Main St", "Midtown", "San Francisco"));

        // Add policies to some users
        insuranceCompany.createThirdPartyPolicy(1002, "John Doe", 104, car1, 1, new MyDate(2025, 6, 30), "Basic coverage");
        insuranceCompany.createComprehensivePolicy(1003, "Alice Smith", 105, car2, 2, new MyDate(2024, 8, 15), 35, 2);
        
        // Find a user and print their policies
        System.out.print("Enter a userID to print user details and policies: ");
        int userID = scanner.nextInt();
        insuranceCompany.printPolicies(userID);

        // Find a policy for a user
        System.out.print("Enter a userID: ");
        int userIDFindPolicy = scanner.nextInt();
        System.out.print("Enter a policy ID: ");
        int policyIDFindPolicy = scanner.nextInt();
        InsurancePolicy foundPolicyForUser = insuranceCompany.findPolicy(userIDFindPolicy, policyIDFindPolicy);
        if (foundPolicyForUser != null) {
            System.out.println("Found Policy:");
            foundPolicyForUser.print();
        } else {
            System.out.println("Policy not found.");
        }

        // Print all users in the insurance company
        insuranceCompany.print();

        // Raise car prices for all users and policies by 10%
        insuranceCompany.carPriceRise(0.1);

        // Print all users again after car price increase
        insuranceCompany.print();

        // Print total premium payments for a given user
        System.out.print("Enter a userID to calculate total premium payments: ");
        int userIDCalcTotal = scanner.nextInt();
        double totalPaymentsForUser = insuranceCompany.calcTotalPayments(userIDCalcTotal);
        System.out.println("Total Premium Payments for userID " + userIDCalcTotal + ": $" + totalPaymentsForUser);

        // Print total premium payments for all users in the company
        double totalPaymentsForCompany = insuranceCompany.calcTotalPayments();
        System.out.println("Total Premium Payments for the entire company: $" + totalPaymentsForCompany);

        // Get all policies in the company and print them
        ArrayList<InsurancePolicy> allPolicies = insuranceCompany.allPolicies();
        System.out.println("\nAll Policies in the Company:");
        InsurancePolicy.printPolicies(allPolicies, 100); // Assuming flatRate is 100

        // Filter policies by expiry date for a given user
        System.out.print("Enter a userID to filter policies by expiry date: ");
        int userIDFilterExpiry = scanner.nextInt();
        System.out.print("Enter the expiry date (year month day): ");
        int year = scanner.nextInt();
        int month = scanner.nextInt();
        int day = scanner.nextInt();
        MyDate expiryDate = new MyDate(year, month, day);
        ArrayList<InsurancePolicy> filteredByExpiry = insuranceCompany.filterByExpiryDate(userIDFilterExpiry, expiryDate);
        System.out.println("\nFiltered Policies by Expiry Date:");
        InsurancePolicy.printPolicies(filteredByExpiry, 100); // Assuming flatRate is 100

        // Filter policies by car model for the entire company
        System.out.print("Enter a car model to filter policies in the company: ");
        String carModelFilterCompany = scanner.next();
        ArrayList<InsurancePolicy> filteredByCarModelCompany = insuranceCompany.filterByCarModel(carModelFilterCompany);
        System.out.println("\nFiltered Policies in the Company by Car Model:");
        InsurancePolicy.printPolicies(filteredByCarModelCompany, 100); // Assuming flatRate is 100

        // Filter policies by expiry date for the entire company
        System.out.print("Enter the expiry date (year month day) to filter policies in the company: ");
        int yearFilterCompany = scanner.nextInt();
        int monthFilterCompany = scanner.nextInt();
        int dayFilterCompany = scanner.nextInt();
        MyDate expiryDateFilterCompany = new MyDate(yearFilterCompany, monthFilterCompany, dayFilterCompany);
        ArrayList<InsurancePolicy> filteredByExpiryCompany = insuranceCompany.filterByExpiryDate(expiryDateFilterCompany);
        System.out.println("\nFiltered Policies in the Company by Expiry Date:");
        InsurancePolicy.printPolicies(filteredByExpiryCompany, 100); // Assuming flatRate is 100

        // Find a user and change their address
        System.out.print("Enter a userID to find and change address: ");
        int userIDChangeAddress = scanner.nextInt();
        User foundUser = insuranceCompany.findUser(userIDChangeAddress);
        if (foundUser != null) {
            System.out.print("Enter new street number: ");
            int newStreetNumCompany = scanner.nextInt();
            System.out.print("Enter new street name: ");
            String newStreetCompany = scanner.next();
            System.out.print("Enter new suburb: ");
            String newSuburbCompany = scanner.next();
            System.out.print("Enter new city: ");
            String newCityCompany = scanner.next();
            Address newAddressCompany = new Address(newStreetNumCompany, newStreetCompany, newSuburbCompany, newCityCompany);
            foundUser.setAddress(newAddressCompany);
            System.out.println("Address updated successfully.");
        } else {
            System.out.println("User not found.");
        }

        // Close the scanner
        scanner.close();
    }
}
