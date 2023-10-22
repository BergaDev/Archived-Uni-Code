//Author: Matthew Bergamini - MAIN IN InsurancePolicy
class ComprehensivePolicy extends InsurancePolicy{
    //Class specific vars
  int driverAge;
  int level;

  //Get and store
  public ComprehensivePolicy(String policyHolderName, int id, Car car, int numberOfClaims, int driverAge, int level){
    super(policyHolderName, id, car, numberOfClaims);
    this.driverAge = driverAge;
    this.level = level;
  }

  //The calculation for the type 
  @Override
  double calcPayment(double flatRate){
    double premium = car.price / 50 + numberOfClaims * 200 + flatRate;
    if (driverAge < 30){
      premium += (30 - driverAge) * 50;
    }
    return premium;
  }

  //take and print
  //Making sure to allow swaps between print() and toString()
  @Override
  public String toString(){
    if (runs == 0){
        return super.print() + " Driver Age: " + driverAge +  " Level: " + level + " This is super.print";
    } else {
    return super.toString() + " Driver Age: " + driverAge +  " Level: " + level + " This is super.toString";
    }
  }
}