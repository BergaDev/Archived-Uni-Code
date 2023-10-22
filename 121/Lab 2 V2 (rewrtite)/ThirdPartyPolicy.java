public class ThirdPartyPolicy extends InsurancePolicy {
    protected static String name;
    protected String comments;
    
    public ThirdPartyPolicy(int id, String name){
        super(id, name);
        this.id = id;
        this.name = name;
    }

    public static String print(){
    //code
    System.out.println("Policy Holder Name: " + name + " This ID is " + id);
    return "";
    }

    public static double TPP(flatRate){
        //code    //CarPrice here
        ThirdPP = (110/100+numberOfClaims*200+flatRate);
        return 0;
    }
   
}
