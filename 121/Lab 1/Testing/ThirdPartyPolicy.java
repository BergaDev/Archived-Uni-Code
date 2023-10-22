package Testing;

public class ThirdPartyPolicy extends InsurancePolicy{
   private String comments;

   public ThirdPartyPolicy(String policyHolderName, int id, Car car, int numberOfClaims, String comments) {
       super(policyHolderName, id, car, numberOfClaims);
       this.comments = comments;
       //this.car = car;
   }

   @Override
   double calcPayment(double flatRate) {
       return (getCar().getPrice()/100 + getNumberOfClaims()*200 + flatRate);
   }

   @Override
   void print() {
       System.out.println("Third Party Policy");
       System.out.println("Policy Holder Name: " + getPolicyHolderName());
       System.out.println("ID: " + getId());
       System.out.println("Number of Claims: " + getNumberOfClaims());
       System.out.println("Comments: " + comments);
       System.out.println("Car Details:");
       System.out.println("\tModel: " + getCar().getModel());
       System.out.println("\tType: " + getCar().getType());
       System.out.println("\tManufacturing Year: " + getCar().getManufacturingYear());
       System.out.println("\tPrice: " + getCar().getPrice());
   }
}
