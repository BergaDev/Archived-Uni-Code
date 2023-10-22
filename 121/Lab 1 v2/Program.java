import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    public static int runs;

    public static void main(String[] args){
    final double flatRate = 100;
    double PPstore;
    String more;
    int choice;

    Scanner input = new Scanner(System.in);

    //Create arraylist
    ArrayList<InsurancePolicy> people = new ArrayList<>();
   ArrayList<Address> addressList = new ArrayList<>();
   ArrayList<User> userList = new ArrayList<>();
    //ArrayList<InsurancePolicy> policies = new ArrayList<>(policies);

    System.out.println("Choose an option");
    System.out.println("1) Create a new entry");
    System.out.println("2) Print all policies");
        System.out.println("3) Use test case data and print");
        System.out.println("4) Print a list of all policies"); 
        System.out.println("5) Filter by car model");
        System.out.println("6) Filter by expiry date");
        System.out.print("Enter 21 to exit");
    choice = input.nextInt();
   
while(choice != 21){
    if(choice == 1){

        String phName;
        int enterID;
        String carModel;
        int manuYear;
        int price;
        int numClaims;
        String enteredComments;

        //Address details
        int qstreetNum;
        String qStreet;
        String qSuburb;
        String qCity;
        //int choice;

        System.out.println("Choose the number of one of the below options");
        System.out.println("1) Create a new account");
        if (choice == 1){
        
            System.out.println("Enter the name of the user ");
            input.nextLine();
        phName = input.nextLine();
        System.out.println("Enter the user ID");
        enterID = input.nextInt();
        System.out.println("Enter the model of the car");
        input.nextLine();
        carModel = input.nextLine();

        System.out.println("Enter the manufucturing year of the car");
        manuYear = input.nextInt();
        System.out.println("Enter the price of the car");
        price = input.nextInt();
        System.out.println("Enter the number of claims by this user");
        numClaims = input.nextInt();
        System.out.println("Enter some comments about this claim");
        input.nextLine();
        enteredComments = input.nextLine();

        System.out.println("Choose the car type ");
        System.out.println("1) Sedan");
        System.out.println("2) Luxury");
        choice = input.nextInt();
        if(choice == 1){
            people.add(new ThirdPartyPolicy(phName, enterID, new Car(carModel, CarType.LUX, manuYear, price), numClaims, enteredComments));
            //policies.add(new ThirdPartyPolicy(phName, enterID, null, numClaims, enteredComments));
        } else if(choice == 2){
            people.add(new ThirdPartyPolicy(phName, enterID, new Car(carModel, CarType.SED, manuYear, price), numClaims, enteredComments));
        }
        choice = 0;

        //addressList.add(new Address(qstreetNum, qStreet, qSuburb, qCity));
        }
    } else if(choice == 2){
        InsurancePolicy.printPolicies(people, userList, addressList);
        for (Address addresses : addressList){
            Address.print();
            }
            for (User users : userList){
                User.print();
            }

            for (InsurancePolicy policy : people){
        
                //code
                //double PPstore;
                PPstore = policy.calcPayment(flatRate);
                System.out.println(policy);
                System.out.print(" Policy Premium $" + PPstore);
                System.out.println();
                //runs = 1;
             }
         choice = 0;
    } else if (choice == 0){
        System.out.println("Choose an option");
        System.out.println("1) Create a new entry");
        System.out.println("2) Print the existing policies");
        System.out.println("3) Use test case data and print");
        System.out.println("4) Print a list of all policies"); 
        System.out.println("5) Filter by car model");
        System.out.println("6) Filter by expiry date");
        System.out.print("Enter 21 to exit");
        choice = input.nextInt();
    } else if (choice == 3){
        people.add(new ThirdPartyPolicy("John Doe", 10, new Car("Tesla Model 3", CarType.LUX, 2023, 1000000), 1, "Some comments"));
        people.add(new ThirdPartyPolicy("James Jack", 12, new Car("Mazda 3", CarType.SED, 2010, 20000), 3, "Some more comments"));

      //  people.add(new User("First", 14, null));

        //code
        //double PPstore;
        
       // PPstore = policy.calcPayment(flatRate);
       // System.out.println(policy);
       // System.out.print(" Policy Premium $" + PPstore);
        System.out.println();
        InsurancePolicy.carPriceRise(0.05);
        
        //If person1 print address1
        for (Address addresses : addressList){
            Address.print();
            }
            for (User users : userList){
                User.print();
            }

            for (InsurancePolicy policy : people){
        
                //code
                //double PPstore;
                PPstore = policy.calcPayment(flatRate);
                System.out.println(policy);
                System.out.print(" Policy Premium $" + PPstore);
                System.out.println();
                //runs = 1;
             }
             choice = 0;
    } else if (choice == 4){
        InsuranceCompany.allPolicies();
    } else if (choice == 5){
        String CarModel;
        System.out.print("What model of car to filter by? ");
        CarModel = input.nextLine();
        InsuranceCompany.filterByCarModel(CarModel);
        choice = 0;
    } else if (choice == 6){
        String date;
        System.out.println("Enter the date");
        date = input.nextLine();
        choice = 0;
        
       // InsuranceCompany.filterByExpiryDate(date);
    }

    }

}
}
  