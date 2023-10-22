/**
 *
 * @author Hooman Shidanshidi hooman@uow.edu.au
 */
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.management.openmbean.ArrayType;

public class User implements Cloneable, Comparable<Address>
{
    private static int count = 0;
    private String name;
    private int userID;
    private String password;
    private Address address;
    //private ArrayList<InsurancePolicy> policies;
    private HashMap<Integer, InsurancePolicy> policies = new HashMap<Integer, InsurancePolicy>();
    
    public User(String name,Address address, String password)
    {
        this.name=name;
        // 3) ID generation for the User to be automatic by using a static count and increment it.
        this.userID=User.count++;
        this.password=password;
        this.address=address;
        //this.policies= new ArrayList<>();
        this.policies = new HashMap<>();
    }
	
    public User(int userID,String name, Address address, String password)
    {
        this.name=name;
        this.userID=userID;
        this.password=password;
        this.address=address;
       // this.policies= new ArrayList<>();
       this.policies = new HashMap<>();
    }

    public String getName()
    {
        return name;
    }
    
    public int getUserID()
    {
        return userID;
    }
    
    public String getUserPassword()
    {
        return password;
    }
    
    public Address getAddress()
    {
        return address;
    }
    
    public void setAddress(Address address)
    {
        address = address;
    }
    
    public void setCity(String ct)
    {
        address.setCity(ct);
    }
	   
    public String getCity()
    {
        return address.getCity();
    }
    
   /*  public ArrayList<InsurancePolicy> getPolicies()
    {
        return policies;
    }
    */

      public HashMap<Integer, InsurancePolicy> getPolicies(){
        return policies;
      }
    
    public boolean validateUser(int userID, String password) // and not username as username might not be unique as we didn't check it
    {
        if ((this.userID==userID) && this.password.equals(password))
        {
            return true;
        }
        return false;
    }
	
    // finds the policy and returns it. Returns null if policyID does not exist.
    /* 
    public InsurancePolicy findPolicy( int policyID) 
    {
        for (InsurancePolicy policy: policies)
        {
            if (policy.getPolicyID() == policyID)
                return policy;  
        }
        return null;
    }
    */

    public InsurancePolicy findPolicy(int policyID)
    {
        return policies.get(policyID); // Get policy using policyID as key
    }
	
    // adds a policy, returns true if successful (when policyID is unique) and returns false if not.  
    /* 
    public boolean addPolicy(InsurancePolicy policy)
    {
        if (findPolicy(policy.getPolicyID())==null)
        {
            policies.add(policy);
            return true;
        }
        return false;
    }
    */
    public boolean addPolicy(InsurancePolicy policy)
    {
        if (!policies.containsKey(policy.getPolicyID())) // Check if policyID is unique
        {
            policies.put(policy.getPolicyID(), policy); // Add policy to HashMap
            return true;
        }
        else 
        {
            return false;
        }
    }
    
    // returns the total premium payments for this user by calling the corresponding static method inside InsurancePolicy. 
    /* 
    public double calcTotalPremiums(int flatRate)
    {
        return InsurancePolicy.calcTotalPayments(policies, flatRate);
    }
    */

    public double calcTotalPremiums(int flatRate)
      {
    double totalPayment = 0;

    for (InsurancePolicy policy : policies.values()) {
        totalPayment += policy.calcPremium(flatRate);
    }

    return totalPayment;
      }


    
    // calls the corresponding static method inside InsurancePolicy to increase the car price for all the policies the user owns.
    /* 
    public void carPriceRiseAll(double risePercent)
    {
        InsurancePolicy.carPriceRiseAll(policies, risePercent);
    }
    */

    public void carPriceRiseAll(double risePercent)
    {
    for (InsurancePolicy policy : policies.values()) {
        policy.carPriceRise(risePercent);
    }
      } 


    // creates a Third-Party Policy and adds it to the list of the user’s policies, returns false if the id is not unique. 
    // Create an object from ThirdPartyPolicy and call addPolicy method to add it to the list
    public boolean createThirdPartyPolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, String comments )
    {
        return addPolicy(new ThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, expiryDate,comments));
    }
    
    // creates a Comprehensive Policy and adds it to the list of the user’s policies, returns false if the id is not unique. 
    // Create an object from ComprehensivePolicy and call addPolicy method to add it to the list
    public boolean createComprehensivePolicy( String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, int driverAge, int level)
    {
        return addPolicy(new ComprehensivePolicy(policyHolderName,  id,  car,  numberOfClaims,  expiryDate,  driverAge,  level));        
    }
   
    // filters the policies and returns a list of policies with the car model containing the given carModel by calling the corresponding static method inside InsurancePolicy. 
    public ArrayList<InsurancePolicy> filterByCarModel (String carModel)
    {
        return InsurancePolicy.filterByCarModel(policies, carModel);
    }
    
    public ArrayList<InsurancePolicy> filterByExpiryDate (MyDate date)
    {
        return InsurancePolicy.filterByExpiryDate (policies, date);
    }

    // prints all the information of this user including all the policies information
    public void printUserInformation()
    {
        System.out.println("User ID:" + userID + " Name:" + name);
        System.out.println(" Address: ");
        address.print();
    }
	
    public void print()
    {
        // System.out.println("User ID:" + userID + " Name:" + name);
        // System.out.println(" Address: ");
        // address.print();
	printUserInformation(); //instead of previous 3 lines
        InsurancePolicy.printPolicies(policies);
    }
	
    public void print(double flatRate)
    {
        // System.out.println("User ID:" + userID + " Name:" + name);
        // System.out.println(" Address: ");
        // address.print();
	printUserInformation(); //instead of previous 3 lines
        InsurancePolicy.printPolicies(policies);
    }
	
    // converts the user and his/her policies to String
    @Override
    public String toString()
    {
        String output = "User ID:" + userID + " Name:" + name + " Address: " + address.toString() + " Policies: \n";
        for(InsurancePolicy policy : policies)
        {
            output += policy.toString()+"\n";
        }
        return output;
    }
    
    
    // prints all the policies this user owns as well as the premium payment for each policy by calling the corresponding static method inside InsurancePolicy. 
    public void printPolicies(double flatRate)
    {
//        System.out.println("List of Policies:");
//        for(InsurancePolicy policy : policies)
//        {
//            System.out.println(policy);
//            System.out.println("Premium ="+ policy.calcPremium(flatRate));
//        }
	InsurancePolicy.printPolicies(policies,flatRate);
        System.out.println("Total Premium Payments:" + calcTotalPremiums(flatRate));
    }
	
    public static void printUsers(ArrayList<User> users)
    {
        for(User user : users)
            System.out.println(user); 
    }

    // calculation
    public double calcTotalPremiums(double flatRate)
    {
        return InsurancePolicy.calcTotalPayments(policies, flatRate);
    }
    
    public ArrayList<String> populateDistinctCarModels()
    {
        ArrayList<String> models =new ArrayList<String>();
        for (InsurancePolicy policy:policies)
        {
            boolean found=false;
            for (String model:models)
            {
                if (policy.getCarModel().equals(model))
                {
                    found=true;
                    break;
                }
            }
            if (!found)
                models.add(policy.getCarModel());
        }
        return models; 
    }

    public int getTotalCountForCarModel(String carModel)
    {
        int count=0;
        for( InsurancePolicy policy:policies)
        {
            if(policy.getCarModel().equals(carModel))
                count++;
        }
        return count;
    }
	
    public double getTotalPaymentForCarModel(String carModel,double flatRate)
    {
        double total=0;
        for( InsurancePolicy policy:policies)
        {
            if(policy.getCarModel().equals(carModel))
                total+=policy.calcPremium(flatRate);
        }
        return total;
    }

    public ArrayList<Integer> getTotalCountPerCarModel (ArrayList<String> carModels)
    {
        ArrayList<Integer> totalCounts=new ArrayList<Integer>();
//            int count=0;
        for (String model:carModels)
        {
            //assuming that the other method is not done. This code was correct: 
//            int count=0;
//            for( InsurancePolicy policy:policies)
//            {
//                if(policy.getCarModel().equals(model))
//                    count++;
//            }
//            totalCounts.add(count); 

            //a better way by using the other method
            totalCounts.add(getTotalCountForCarModel(model));
        }
        return totalCounts;
    }
	
    ArrayList<Double> getTotalPaymentPerCarModel (ArrayList<String> carModels,double flatRate)
    {
        ArrayList<Double> totalPayments=new ArrayList<Double>();
//            double payment=0;
        for (String model:carModels)
        {
            //assuming that the other method is not done. The folowwing code should have been done
//            double total=0;
//            for( InsurancePolicy policy:policies)
//            {
//                if(policy.getCarModel().equals(model))
//                    total+=policy.calcPremium(flatRate);
//            }
//            totalPayments.add(total);         

            //a better way now by using the other method
            totalPayments.add(getTotalPaymentForCarModel(model,flatRate));
        }	
        return totalPayments;
    }	

    //as spec but it is not good. all lists are sent as parameters
    public void reportPaymentsPerCarModel (ArrayList<String> carModels, ArrayList<Integer>counts, ArrayList<Double> premiums)
    {
        System.out.println("\n CarModel \t \t \t Total Premium \t \t \tAverage Premium");
        // or System.out.printf("%-15s%-30s%-30s%-%n","Car Model","Total Premium","Average Premium");
        for (int i=0;i<counts.size();i++)
            System.out.println(carModels.get(i)+" \t \t \t "+premiums.get(i)+" \t \t \t "+premiums.get(i)/(double)counts.get(i)); 
            //or System.out.printf("%-15s%-30s%-30s%-%n",carModels.get(i),premiums.get(i),premiums.get(i)/(double)counts.get(i));

        System.out.println("------------------------------------");
    }

    //a list of given models and flatRate is sent 
    public void reportPaymentsPerCarModel (ArrayList<String> carModels, double flatRate)
    {
        ArrayList<Integer> counts=getTotalCountPerCarModel(carModels);
        ArrayList<Double> premiums=getTotalPaymentPerCarModel(carModels, flatRate);
        reportPaymentsPerCarModel (carModels, counts, premiums); // instead of doing this again as below

//        System.out.println("\n CarModel \t \t \t Total Premium \t \t \tAverage Premium");
//        for (int i=0;i<counts.size();i++)
//            System.out.println(carModels.get(i)+" \t \t \t "+premiums.get(i)+" \t \t \t "+premiums.get(i)/(double)counts.get(i)); 
    }

    public void reportPaymentsPerCarModel (double flatRate) // for all models
    {
        ArrayList<String> carModels=populateDistinctCarModels();
        reportPaymentsPerCarModel (carModels,flatRate); // better than below        
        
//        ArrayList<String> carModels=populateDistinctCarModels();
//        ArrayList<Integer> counts=getTotalCountPerCarModel(carModels);
//        ArrayList<Double> premiums=getTotalPaymentPerCarModel(carModels, flatRate);
//        reportPaymentsPerCarModel (carModels, counts, premiums); 
//        System.out.println("\n CarModel \t \t \t Total Premium \t \t \tAverage Premium");
//        for (int i=0;i<counts.size();i++)
//            System.out.println(carModels.get(i)+" \t \t \t "+premiums.get(i)+" \t \t \t "+premiums.get(i)/(double)counts.get(i)); 
    }
    
    //Copy Constructor 
    public User(User User){
      this.name = User.name;
      this.userID = User.userID;
      this.password = User.password;
      this.address = User.address;
      this.policies = User.policies;
    }


    public ArrayList<InsurancePolicy>shallowCopy(ArrayList<InsurancePolicy>policies){
      ArrayList<InsurancePolicy> shallowCopy = new ArrayList<>();
      shallowCopy = (ArrayList) policies.clone();
      return shallowCopy;
    }

    public static ArrayList<InsurancePolicy>  deepCopy(ArrayList<InsurancePolicy>policies) throws CloneNotSupportedException{
      ArrayList<InsurancePolicy> tempdeepCopy = new ArrayList<InsurancePolicy>();

      for(InsurancePolicy policy : policies){

       // tempdeepCopy.add(policy.clone());
       //THIS IS NOT WORKING, GIVING AN ERROR ON .clone 
        //tempdeepCopy =(ArrayList) policy.clone();
      }
      return tempdeepCopy;
    }

    //City name compare
    public int compareTo(Address st){
      String cityName = "Wollongong";

      if(cityName==st.city){
        return 1;
      } else {
        return 404;
      }
      

    }

    public int compareTo1(User st){
      int policyPayments = 10;

      if(policyPayments==calcTotalPremiums(100)){
        return 1;
      } else {
        return 404;
      }

    }

    public ArrayList<InsurancePolicy> sortPoliciesByDate(){
      ArrayList<InsurancePolicy> tempPolicyDate = new ArrayList<InsurancePolicy>();
      for (InsurancePolicy policy : policies){
        

        MyDate dateGrab = policy.getExpiryDate();
        
        //if exp then add
        tempPolicyDate.add(policy);

        

      }
      return tempPolicyDate;
    }



}
