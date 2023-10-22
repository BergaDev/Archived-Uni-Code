/**
 *
 * @author Hooman Shidanshidi hooman@uow.edu.au
 */
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;

public abstract class InsurancePolicy implements Cloneable, Comparable<MyDate>
{
    protected String policyHolderName;  
    protected int policyID; 
    protected Car car; 
    protected int numberOfClaims;
    private MyDate expiryDate;
    
    public InsurancePolicy(String policyHolderName, int policyID, Car car, int numberOfClaims, MyDate expiryDate)
    {
        this.policyHolderName = policyHolderName;
        this.policyID = policyID; 
        this.car = car;
        this.numberOfClaims = numberOfClaims;
        this.expiryDate = expiryDate;
    }
    
    // get and set methods------------------------------------------------------
    public String getPolicyHolderName()
    {
        return policyHolderName;
    }
    
    public void setPolicyHolderName(String newName)
    {
        policyHolderName = newName;
    }
    
    public int getPolicyID()
    {
        return policyID;
    }
    
    public Car getCar()
    {
        return car;
    }
    
    public void setCar(Car cr)
    {
        car = cr;
    }
    
    public String getCarModel()
    {
        return car.getModel();
    }
    
    public void setCarModel(String model)
    {
        car.setCarModel(model);
    }
     
    public MyDate getExpiryDate()
    {
        return expiryDate;
    }
    
    public void setExpiryDate(MyDate eDate)
    {
        expiryDate=eDate;
    }
    
    public abstract double calcPremium(double flatRate);
    
    // It has one parameter, a price rise in percent. The method increases the policy’s car price by rise percent.
    public void carPriceRise(double risePercent)
    {
        car.priceRise(risePercent);
    }
	
    // print and toString methods-----------------------------------------------
	
    public void print()
    {
        System.out.print("Policy holder name: "+policyHolderName+" ID: "+policyID);
        car.print();
        System.out.print(" Number of claims: "+numberOfClaims);
        //System.out.println(" Expiry Date: " + expiryDate);
	expiryDate.print();
    }

    public void print(double flatRate)
    {
        print();
        System.out.print(" Premium Payment: "+calcPremium(flatRate));
    }
    
    @Override
    public String toString()
    {
        return "Policy holder name: "+policyHolderName+" ID: "+policyID+" Car: "+car+" Number of claims: "+numberOfClaims + " Date: "+ expiryDate;
    }
    
    // prints a list of policies. 
    public static void printPolicies(ArrayList<InsurancePolicy>policies)
    {
        for(InsurancePolicy policy: policies )
        {
           policy.print();
        }
    }

    //print a list of policies and the premiums
    public static void printPolicies(ArrayList<InsurancePolicy> policies,double flatRate)
    {
        for(InsurancePolicy policy: policies )
        {
           policy.print(flatRate);
        }
    }
    
    //calculates the total premium payments for a list of policies. 
    public static double calcTotalPayments(ArrayList<InsurancePolicy> policies, double flatRate)
    {
        double total = 0;
        for (InsurancePolicy policy : policies)
        {
            total += policy.calcPremium(flatRate);
        }
        return total;
    }  
    
    // calls the carPriceRise method for all the policies in a given list ( in a for each loop). This is to increase the price of cars for all policies in a list.
    public static void carPriceRiseAll(ArrayList<InsurancePolicy> policies, double risePercent)
    {
        for(InsurancePolicy policy : policies)
        {
            policy.carPriceRise(risePercent);
        }  
    }
    
    // filter methods-----------------------------------------------------------
    
    // which filter a list of policies and creates a filtered list of policies, all with the given car model. 
    public static ArrayList<InsurancePolicy> filterByCarModel(ArrayList<InsurancePolicy> policies, String carModel)
    {
        ArrayList<InsurancePolicy> filteredPolicies= new ArrayList<> ();
        
        for(InsurancePolicy policy: policies)
        {
            if(policy.getCarModel().equals(carModel))
            {
                filteredPolicies.add(policy);
            }
        }
        return filteredPolicies;
    } 
    
    // which filter a list of policies and creates a filtered list of policies that are expired by the given date. 
    public static ArrayList<InsurancePolicy> filterByExpiryDate (HashMap<Integer, InsurancePolicy> policies, MyDate date)
    {
        ArrayList<InsurancePolicy> filteredPolicies= new ArrayList<> ();

        for(InsurancePolicy policy: policies)
        {
            if(policy.getExpiryDate().isExpired(date))
            {
                filteredPolicies.add(policy);
            }
        }
        return filteredPolicies;
    }

    
    //Copy constructor 
    public InsurancePolicy(InsurancePolicy InsurancePolicy){
      this.policyID = InsurancePolicy.policyID;
      this.car = InsurancePolicy.car;
      this.numberOfClaims = InsurancePolicy.numberOfClaims;
      this.expiryDate = InsurancePolicy.expiryDate;
    }
    
    public static ArrayList<InsurancePolicy>shallowCopy(ArrayList<InsurancePolicy>policies){
      ArrayList<InsurancePolicy> shallowCopy = new ArrayList<>();
      shallowCopy = (ArrayList) policies.clone();
      return shallowCopy;
    }

    public static ArrayList<InsurancePolicy>  deepCopy(ArrayList<InsurancePolicy>policies) throws CloneNotSupportedException{
      ArrayList<InsurancePolicy> tempdeepCopy = new ArrayList<InsurancePolicy>();

      for(InsurancePolicy policy : policies){

        //tempdeepCopy.add(policy.clone());
        tempdeepCopy =(ArrayList) policy.clone();
      }
      return tempdeepCopy;
    }

    /*
    @Override
    public InsurancePolicy clone(InsurancePolicy pol){
      return this.clone();
    }
    */

    public int compareTo(MyDate st){
      int day = 05;
      int month = 05;
      int year = 2004;

      if(day==st.day && month==st.month && year==st.year){
        return 1;
      } else {
        return 404;
      }
      

    }


}
