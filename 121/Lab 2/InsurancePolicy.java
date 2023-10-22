//Author: Matthew Bergamini - MAIN IN InsurancePolicy
import java.util.ArrayList;


abstract class InsurancePolicy {
//Shared strings
  protected String policyHolderName;
  protected int id;
  protected Car car;
  protected int numberOfClaims;
  protected static int runs = 0;

  //Lab 2
  protected MyDate expiryDate;

  protected static double policyPremium;
  static double PPstore;

  

  public InsurancePolicy(String policyHolderName, int id, Car car, int numberOfClaims) {
    this.policyHolderName = policyHolderName;
    this.id = id;
    this.car = car;
    this.numberOfClaims = numberOfClaims;
    //this.streetNum = streetNum;
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

  public static void main(String[] args){
    final double flatRate = 100;

    //Create arraylist
    ArrayList<InsurancePolicy> people = new ArrayList<>();
    //Create the objects
    people.add(new ThirdPartyPolicy("John", 1, new Car("Toyota Rav4", CarType.SED, 2023, 15000), 1, "This is a comment on John"));
    people.add(new ComprehensivePolicy("Jane", 2, new Car("Suzuki Jimmy", CarType.TRUCK, 2022, 20000), 2, 31, 2));
    people.add(new ThirdPartyPolicy("Bob", 3, new Car("Tesla Model 3", CarType.LUX, 2023, 30000), 3, "This is a comment on Bob"));
   // people.add(new Address(1));


    
    //The below is run twice as to complete Q7, using print method and then toString method
        //Do this per object
         for (InsurancePolicy policy : people){
        
            //code
            //double PPstore;
            PPstore = policy.calcPayment(flatRate);
            System.out.println(policy);
            System.out.print(" Policy Premium $" + PPstore);
            System.out.println();
            //runs = 1;
         }
    
        for (InsurancePolicy policy2 : people){
            runs++;
            //code
            //double PPstore;
            PPstore = policy2.calcPayment(flatRate);
            System.out.println(policy2);
            System.out.print(" Policy Premium $" + PPstore);
            System.out.println();
            //runs = 1;
    }
   
  

  }
}