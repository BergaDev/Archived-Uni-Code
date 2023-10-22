/**
 *
 * @author Hooman Shidanshidi hooman@uow.edu.au
 */

public class ComprehensivePolicy extends InsurancePolicy implements Cloneable
{
    protected int driverAge;
    protected int level;
    
    public ComprehensivePolicy (String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, int driverAge, int level)
    {
        super(policyHolderName, id, car, numberOfClaims, expiryDate);
        this.driverAge= driverAge;
        this.level=level;
    }
	
    @Override
    public double calcPremium(double flatRate)
    {     
        if(driverAge<=30)
        {
            return car.getPrice()/50 + numberOfClaims * 200 + flatRate + (30-driverAge) * 50;
        }
        else
            return car.getPrice()/50 + numberOfClaims * 200 + flatRate;
    }

    // print
    @Override
    public void print()
    {
        super.print();
	System.out.println(" Driver Age: " + driverAge+ " Level: " + level);
    }
    
    @Override
    public String toString()
    {
        return super.toString()+" Driver Age: " + driverAge+ " Level: " + level;
    }  

    public ComprehensivePolicy(ComprehensivePolicy ComprehensivePolicy){
      this.driverAge = ComprehensivePolicy.driverAge;
      this.level = ComprehensivePolicy.level;
    }
}
