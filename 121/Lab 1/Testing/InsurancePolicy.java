package Testing;
public abstract class InsurancePolicy {
    private String policyHolderName;
    private int id;
    private Car car;
    private int numberOfClaims;

    public InsurancePolicy(String policyHolderName, int id, Car car, int numberOfClaims) {
        this.policyHolderName = policyHolderName;
        this.id = id;
        this.car = car;
        this.numberOfClaims = numberOfClaims;
    }

    public String getPolicyHolderName() {
        return policyHolderName;
    }

    public void setPolicyHolderName(String policyHolderName) {
        this.policyHolderName = policyHolderName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getNumberOfClaims() {
        return numberOfClaims;
    }

    public void setNumberOfClaims(int numberOfClaims) {
        this.numberOfClaims = numberOfClaims;
    }

   abstract double calcPayment(double flatRate);

   abstract void print();


   public static void main(String[] args){
   // System.out.println("Test that this can be run");
   ThirdPartyPolicy need1 = new ThirdPartyPolicy("James", 1, "SUV", 0, "No");
    
   }
}

//Code from other files below here
/* 

public class ThirdPartyPolicy extends InsurancePolicy{
   private String comments;

   public ThirdPartyPolicy(String policyHolderName, int id, Car car, int numberOfClaims, String comments) {
       super(policyHolderName, id, car, numberOfClaims);
       this.comments = comments;
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
*/