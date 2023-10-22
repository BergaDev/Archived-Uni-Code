package Testing;

public class ComprehensivePolicy extends InsurancePolicy{
   private int driverAge;
   private int level;

   public ComprehensivePolicy(String policyHolderName, int id, Car car, int numberOfClaims, int driverAge, int level) {
       super(policyHolderName, id, car, numberOfClaims);
       this.driverAge = driverAge;
       this.level = level;
   }

   @Override
   double calcPayment(double flatRate) {
       double payment = (getCar().getPrice()/50 + getNumberOfClaims()*200 + flatRate);
       if(driverAge < 30){
           payment += (30-driverAge)*50; 
       }
       return payment; 
   }

   @Override
   void print() {
       System.out.println("Comprehensive Policy");
       System.out.println("Policy Holder Name: " + getPolicyHolderName());
       System.out.println("ID: " + getId());
       System.out.println("Number of Claims: " + getNumberOfClaims());
   }
}
