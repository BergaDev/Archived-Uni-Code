package Week1.Employee_Inheritance;

import java.util.*;

public class Program 
{
    public static void main(String[] args) 
    {
        SalaryEmployee emp1 = new SalaryEmployee ("E01", "Fred", 45000);
        CasualEmployee emp2 = new CasualEmployee ("E05", "Kim", 20, 21.5);
        SalaryEmployee emp3 = new SalaryEmployee ("E02", "Sue", 75000);

        ArrayList<Employee> employees = new ArrayList<>(); // ArrayList of Parent
        employees.add (emp1); // adding children to the list
        employees.add (emp2);
        employees.add (emp3);
        
        for (Employee emp : employees)
            emp.print (); // print by using print method
        
		for (Employee emp : employees)
            System.out.println(emp); // print by using toString() method
		
        double total = 0;
        for (Employee emp : employees)
            total += emp.calcPay ();
		
        System.out.println ("Total pay = " + total);
    }  
}
