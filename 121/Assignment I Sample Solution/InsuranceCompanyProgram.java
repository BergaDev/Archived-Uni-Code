/**
 *
 * @author Hooman Shidanshidi hooman@uow.edu.au
 */

public class InsuranceCompanyProgram 
{
    public static void main(String[] args) throws CloneNotSupportedException 
    {
        InsuranceCompany insuranceCompany = new InsuranceCompany("HoomanCompany", "admin", "admin", 12);
        UserInterface.mainMenu(insuranceCompany);
    }   

}
