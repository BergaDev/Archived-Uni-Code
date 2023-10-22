//Author: Matthew Bergamini - MAIN IN InsurancePolicy
import java.util.ArrayList;


abstract class InsurancePolicy {
//Shared strings
  protected String policyHolderName;
  protected static int id;
  protected static Car car;
  protected int numberOfClaims;
  protected static int runs = 0;
  protected String nameID;
  protected MyDate expiryDate;
  //protected String city;

  protected static double policyPremium;
  

  

  public InsurancePolicy(String policyHolderName, int id, Car car, int numberOfClaims) {
    this.policyHolderName = policyHolderName;
    this.id = id;
    this.car = car;
    this.numberOfClaims = numberOfClaims;
  }
  

  public InsurancePolicy(String city) {
    
    }




abstract double calcPayment(double flatRate);

  //Used and overridden in classes 
  public String toString(){
    return "Policy Holder Name: " + policyHolderName + " ID: " + id + car + " Number of Claims: " + numberOfClaims;
  }

  public String print(){
    //code
    System.out.println("Policy Holder Name: " + policyHolderName + " ID: " + id + car + " Number of Claims: " + numberOfClaims);
    return "";
  }

  public static void printPolicies(ArrayList<InsurancePolicy>people, ArrayList<User>userList, ArrayList<Address>addressList){
    //code
    for (InsurancePolicy policy : people){
        
        //code
        //double PPstore;
        /*
        PPstore = policy.calcPayment(flatRate);
        System.out.println(policy);
        System.out.print(" Policy Premium $" + PPstore);
        System.out.println();
        */
        //If person1 print address1
        for (Address addresses : addressList){
        Address.print();
        }
        for (User users : userList){
            User.print();
        }
        //runs = 1;
     }
  }

  //This is the model in the lecture codes
  /*
  public void printUsers() //Also you can move it to User
    {
        for (User user:users)
        {
            user.print();
        }  
        */

        /* 
        public double  calcTotalBalance()
        {
            double total = 0;
            for (Account acc : accounts) 
            {
                total += acc.getBalance();
            }
            return total;
        }
        */

    static void carPriceRise(double risePercent){
        double percent = risePercent;
        Car.price = (car.price * percent);
    }

    static void calcTotalPayments (ArrayList< InsurancePolicy> policies, ArrayList<InsurancePolicy> people, int flatRate){
        double totalCalc = 0;
        
        for (InsurancePolicy policy : people){
        
            //code
            double PPstore;
            PPstore = policy.calcPayment(flatRate);
            System.out.println(policy);
            System.out.print(" Policy Premium $" + PPstore);
            System.out.println();
            //runs = 1;

            totalCalc += (PPstore);
         }
    }
}