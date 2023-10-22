import java.util.ArrayList;

public class InsuranceTest {
    public static boolean isValid = false;

    public static void main(String[] args) {
        // Creating an insurance company
        InsuranceCompany company = new InsuranceCompany("XYZ Insurance", "admin", "password", 100);

        // Adding users to the policy
        Address address1 = new Address(101, "Ocean Ave", "Woonona", "Wollongong");
        Address address2 = new Address(202, "George Street", "CBD", "Sydney");
        Address address3 = new Address(303, "Loop", "Gywnville", "Wollongong");

        company.addUser("John Doe", 1001, address1);
        company.addUser("Jane Smith", 1002, address2);
        company.addUser("Bob Johnson", 1003, address3);

        // Adding policies to users
        Car car1 = new Car("Toyota Camry", CarType.SED, 2019, 20000);
        Car car2 = new Car("Honda Civic", CarType.SED, 2018, 18000);

        company.createThirdPartyPolicy(1001, "John Doe", 2001, car1, 0, new MyDate(1, 1, 2024), "No comments");
        company.createComprehensivePolicy(1002, "Jane Smith", 2002, car2, 1, new MyDate(1, 1, 2025), 35, 3);

        // Print all users and their policies
        company.print();

        // Calculate total premium payments for a user
        double totalPaymentsUser1001 = company.calcTotalPayments(1001);
        System.out.println("Total Premium Payments for User ID 1001: $" + totalPaymentsUser1001);

        // Calculate total premium payments for all users in the company
        double totalPaymentsCompany = company.calcTotalPayments();
        System.out.println("Total Premium Payments for the Company: $" + totalPaymentsCompany);

        // Filter policies by car model for all users in the company
        ArrayList<InsurancePolicy> filteredByCarModel = InsuranceCompany.filterByCarModel("Toyota Camry");
        System.out.println("Policies filtered by car model (Toyota Camry):");
        for (InsurancePolicy policy : filteredByCarModel) {
            System.out.println(policy);
        }

        // Filter policies by expiry date for all users in the company
        MyDate filterDate = new MyDate(1, 1, 2025);
        ArrayList<InsurancePolicy> filteredByExpiryDate = company.filterByExpiryDate(filterDate);
        System.out.println("Policies filtered by expiry date (after 1-Jan-2025):");
        for (InsurancePolicy policy : filteredByExpiryDate) {
            System.out.println(policy);
        }

        // Changing user's address
        Address newAddress = new Address(404, "Street Changed", "City Changed", "State Changed");
        company.changeUserAddress(1002, newAddress);

        // Creating shallow and deep copies of the company's users
        ArrayList<User> shallowCopyUsers = company.shallowCopyUsers();
        ArrayList<User> deepCopyUsers = company.deepCopyUsers();

        // Adding a new user to the company
        Address address4 = new Address(505, "New Street", "New Suburb", "New City");
        company.addUser("Alice Johnson", 1004, address4);

        // Sorting users by city name
        company.sortUsersByCity();
        System.out.println("Sorted Users by City Name:");
        company.print();

        // Sorting users by total premium payments
        company.sortUsersByTotalPremiums(100);
        System.out.println("Sorted Users by Total Premium Payments:");
        company.print();

        // Deep cloning the insurance company
        InsuranceCompany clonedCompany = null;
        try {
            clonedCompany = company.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("We have had an error cloning");
            e.printStackTrace(); //This will point in the direction of a clone error
        }

        if (clonedCompany != null) {
            System.out.println("Is cloned company equal to original company? " + (clonedCompany.equals(company)));
            System.out.println("Is cloned company's user list equal to original company's user list? " + (clonedCompany.users.equals(company.users)));
        }
    }
}
