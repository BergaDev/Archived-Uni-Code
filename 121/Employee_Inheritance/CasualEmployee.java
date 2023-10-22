package Week1.Employee_Inheritance;

public class CasualEmployee extends Employee
{
    protected double hours;
    protected double rate;
    
    public CasualEmployee (String id, String name, double hours, double rate)
    {
        super (id, name);
        this.hours = hours;
        this.rate = rate;
    }
    @Override
    public void print()
    {
        super.print ();
        System.out.println (" Hours: "+hours+" Rate: "+rate);
    }  
    @Override
    public String toString()
    {
        return super.toString()+" Hours: "+hours+" Rate: "+rate;
    } 	
    public double calcPay ()
    {
        return hours * rate;
    }
}
