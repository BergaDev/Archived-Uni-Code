import java.util.ArrayList;

abstract class InsurancePolicy{

    protected int var1;
    protected static int id;
    protected Car car;
    protected int numberOfClaims;
    protected static double flatRate;

    public InsurancePolicy(int var1, String name){
        this.var1 = var1;
    }

    public static void main(String[] args){
        //line 1

        ArrayList<InsurancePolicy> people = new ArrayList<>();

        InsurancePolicy p1 = (new ThirdPartyPolicy(1, "Steve"));
        InsurancePolicy p2 = (new ComprehensivePolicy(2, "James"));

        people.add(p1);
        people.add(p2);

        for (InsurancePolicy policy : people){
            flatRate = 26;
        
            //code
            //double PPstore;
            ThirdPartyPolicy.TPP(flatRate);
            System.out.println(policy);
            ThirdPartyPolicy.print();
            ComprehensivePolicy.print();
            System.out.print(" Policy Premium $");
            System.out.println();
            //runs = 1;
         }

         /* 
        for (InsurancePolicy policy : people){
        
            //code
            //double PPstore;
            //PPstore = policy.calcPayment(flatRate);
            System.out.println(policy);
            ComprehensivePolicy.print();
            System.out.print(" Policy Premium $");
            System.out.println();
            //runs = 1;
         }
         */

    }

    

}