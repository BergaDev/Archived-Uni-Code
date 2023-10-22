public class ComprehensivePolicy extends InsurancePolicy {
    protected static String name;
    protected int driverAge;
    protected int level;
    
    public ComprehensivePolicy(int id, String name){
        super(id, name);
        this.id = id;
        this.name = name;
    }

    public static String print(){
    //code
    System.out.println("Policy Holder Name: " + name + " ID number is " + id);
    return "";
  }
   
}
