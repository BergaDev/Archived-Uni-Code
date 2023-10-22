/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryprogram;

import java.util.ArrayList;

/**
 *
 * @author hooman
 */
public class LibraryProgram {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Policy Policy1=new Policy(1476796629,"Tesla","Model Y","Address?",true,Genres.Romance,100);
        Policy Policy2=new Policy(399578285,"Mazda","3","Address 2?",true,Genres.Fantasy,70);
        User user1=new User(12,"Robert",Type.Student);
        User user2=new User(16,"Sara",Type.Staff);
        Library library=new Library();
        addPolicy(library,Policy1);
        addPolicy(library,Policy2);
        addUser(library,user1);
        addUser(library,user2);
//        if (library.addPolicy(Policy1))
//            System.out.println("The Policy has been added successfully");
//        else
//            System.out.println("The Policy can not be added as the ID already exists");
//        if (library.addPolicy(Policy2))
//            System.out.println("The Policy has been added successfully");
//        else
//            System.out.println("The Policy can not be added as the ID already exists");

        // test find Policy
        Policy Policy=library.findPolicy(1476796629);
        if (Policy==null)
        {
            System.out.println("The ID does not exist");
        }
        else
        {
            Policy.print();
            Policy.setLocation("Level 3 Section 1");
        }
        
        
//        if (library.borrowPolicy(1476796629,12))
//        {
//          System.out.println("Borrowed successfully");  
//        }
//        else
//        {
//            System.out.println("not successful");  
//        }
        borrowPolicy(library,1476796629,12);
        borrowPolicy(library,1476796629,12);
        borrowPolicy(library,1476796629,11);
        borrowPolicy(library,9999,120);
        returnPolicy(library,1476796629,12);
        returnPolicy(library,1476796629,16);
        borrowPolicy(library,1476796629,16);
        //borrowPolicy(library,399578285,16);
        user1.print();
        user2.print();
        
        filterByName(library,"German");
        filterByAuthor(library,"a");
        
        ArrayList<String> authors=library.populateDistinctAuthorList();
        ArrayList<Integer> counts=library.getTotalPerAuthor(authors);
        library.reportTotalPPerAuthor(authors, counts);
        
        library.printPolicys();
        library.printAllBorrowedPolicys();
    }
    public static void addPolicy(Library library, Policy Policy)
    {
       if (library.addPolicy(Policy))
            System.out.println("The Policy has been added successfully");
        else
            System.out.println("The Policy can not be added as the ID already exists");    
    }
    public static void addUser(Library library, User user)
    {
       if (library.addUser(user))
            System.out.println("The user has been added successfully");
        else
            System.out.println("The user can not be added as the ID already exists");    
    }
    public static void borrowPolicy(Library library, long ID,int userID)
    {
        if (library.borrowPolicy(ID,userID))
        {
          System.out.println("Borrowed successfully");  
        }
        else
        {
            System.out.println("Borrowing was not successful");  
        }
    }
    public static void returnPolicy(Library library, long ID,int userID)
    {
        if (library.returnPolicy(ID,userID))
        {
          System.out.println("Returned successfully");  
        }
        else
        {
            System.out.println("Returning was not successful");  
        }
    }
    
    public static void filterByName( Library library, String pattern)
    {
        System.out.println("\u001B[31m Filter Policys by title");
        ArrayList<Policy> PolicyList= library.filterPolicysByName(pattern);
        Policy.printPolicys(PolicyList);
    }
    
    public static void filterByAuthor( Library library, String pattern)
    {
        System.out.println("\u001B[31m Filter Policys by author");
        ArrayList<Policy> PolicyList= library.filterPolicysByAuthor(pattern);
        Policy.printPolicys(PolicyList);
    }
    
}
