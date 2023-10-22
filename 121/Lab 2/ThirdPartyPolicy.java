//Author: Matthew Bergamini - MAIN IN InsurancePolicy
class ThirdPartyPolicy extends InsurancePolicy{
    //Class string
  String comments;
  //Store premium
  double TPP;

  public ThirdPartyPolicy(String policyHolderName, int id, Car car, int numberOfClaims, String comments){
    super(policyHolderName, id, car, numberOfClaims);
    this.comments = comments;

    
  }

  @Override
  double calcPayment(double flatRate){
    return car.price / 100 + numberOfClaims * 200 + flatRate;
  }

  
    @Override
  public String toString(){
    if (runs == 0){
        return super.print() + " Comments: " + comments + " This is super.print";
    } else {
        return super.toString() + " Comments: " + comments + " This is super.toString";
    //Runs the toString line in InsurancePolicy and then adds this to the end
    }
     
  
  }
  
}
