import java.util.ArrayList;
import java.util.Scanner;

public class InsuranceTest {
    public static void main(String[] args) {
        //ArrayList<InsurancePolicy> User = new ArrayList<>();

        // Create some car instances
        Car car1 = new Car("Toyota Corolla", CarType.SED, 2019, 25000);
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
        Address userAddress = new Address(123, "Main St", "Downtown", "New York");
        User user = new User("Michael", 1001, userAddress);
        //Adding second user 
        Address userAddress2 = new Address(6, "North Street", "North Wollongong", "Wollongong");
        User user2 = new User("James", 69, userAddress2);

        // Add policies to the user
        user.addPolicy(policy1);
        user2.addPolicy(policy2);
        user.addPolicy(policy3);

        // Print user information along with policies details
        System.out.println("User Information:");
        /*
        for (User users : User){
            user.print();
        }
        */
        user.print();
        user2.print();
        

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
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new street number: ");
        int newStreetNum = scanner.nextInt();
        System.out.print("Enter new street name: ");
        String newStreet = scanner.next();
        System.out.print("Enter new suburb: ");
        String newSuburb = scanner.next();
        System.out.print("Enter new city: ");
        String newCity = scanner.next();
        //Affects user1
        Address newAddress = new Address(newStreetNum, newStreet, newSuburb, newCity);
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
        System.out.print("Enter car model to filter policies: ");
        String carModelToFilter = scanner.next();
        ArrayList<InsurancePolicy> filteredPolicies = user.filterByCarModel(carModelToFilter);

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
        System.out.println("Invalid Admin Login: " + invalidLogin ); // Expected output: false
    }
}