package Week1.Employee_Inheritance;

public class SalaryEmployee extends Employee
{
    protected double salary;
    
    public SalaryEmployee (String id, String name, double salary)
    {
        super (id, name);
        this.salary = salary;
    }
    @Override
    public void print ()
    {
        super.print ();
        System.out.println (" Salary: "+salary);
    }
    @Override
    public String toString()
    {
        return super.toString()+" Salary: "+salary;
    } 
    @Override
    public double calcPay ()
    {
        return salary / 52.3;
    }
}
