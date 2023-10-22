/**
 *
 * @author Hooman Shidanshidi hooman@uow.edu.au
 */
import java.util.InputMismatchException;

public class ThirdPartyPolicy extends InsurancePolicy implements Cloneable
{
    protected String comments;

    public ThirdPartyPolicy (String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, String comments)
    {
        super(policyHolderName, id, car, numberOfClaims, expiryDate);
        this.comments = comments;
    }

    @Override
    public double calcPremium(double flatRate)
    {
        return car.getPrice()/100 + numberOfClaims * 200 + flatRate;
    }

    @Override
    public void print()
    {
        super.print();
	System.out.println(" Comments: " + comments);
    }
    
    @Override
    public String toString()
    {
        return super.toString()+ " Comments: " + comments;
    }
   /* 
    //Copy constructor 
    public ThirdPartyPolicy(ThirdPartyPolicy ThirdPartyPolicy){
      this.comments = ThirdPartyPolicy.comments;
    }
    */


 
}
