package Week1.Employee_Inheritance;

public abstract class Employee 
{
    protected String id;
    protected String name;
    
    public Employee (String id, String name)
    {
        this.id = id;
        this.name = name;
    }
    public void print ()
    {
        System.out.print("ID:"+id+" Name: "+name);
    }
    public String toString()
    {
        return "ID:"+id+" Name: "+name;
    }
    public abstract double calcPay ();
}
