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
// enum Type {Student,Staff,Admin} // version 1
enum Type 
{
    Student(3),Staff(5),Admin(7);
    int borrowLimit;
    Type(int limit)
    {
        borrowLimit=limit;
    }
    int getBorrowingLimit()
    {
        return borrowLimit;
    }
    
}


public class User 
{
    //private static final int[] maxBorrow={3,5,7};//version 1
    private int ID;
    private String name;
    private Type type;
    private ArrayList<Policy> policies;
    public User (int id,String name1,Type type1)
    {
        ID=id;
        name=name1;
        type=type1;
        policies=new ArrayList<Policy>();
    }
    public int getID()
    {
        return ID;
    }
    public static User findUser(ArrayList<User> users,int userID)  // This is different from Library.findPolicy to show another design
    {
        for (User user:users)
        {
            if (user.getID()==userID)
                return user;  
        }
        return null;      
    }
    public static boolean addUser(ArrayList<User> users,User user) // This is different from Library.addPolicy to show another design
    {
        if (findUser(users,user.getID())==null)
        {
            users.add(user);
            return true;
        }
        else
            return false;
    }
    public boolean borrowPolicy(Policy Policy)
    {
        // if ((Policy.getAvailable())&&(Policys.size()<maxBorrow[type.ordinal()]))// version 1
        if ((Policy.getAvailable())&&(policies.size()<type.getBorrowingLimit()))
        {
            policies.add(Policy);
            Policy.setAvailable(false);
            return true;
        }
        else
            return false;
    }
    public boolean returnPolicy(Policy Policy)
    {
        for (Policy PolicyItem:policies)
        {
            if (PolicyItem.getID()==Policy.getID())
            {
                policies.remove(PolicyItem);
                PolicyItem.setAvailable(true);
                return true;
            }
        }
        return false;
    }
    public void print()
    {
         System.out.println("ID: "+ID+" Name: "+name+"Type: "+type);
         printBorrowedPolicys();
    }
    public void printBorrowedPolicys()
    {
        System.out.println("\n Borrowing Record ");
        for (Policy Policy:policies)
            Policy.print();
    }
    public int numBorrowedPolicys()
    {
        return policies.size();
    }
    
    
    
}
    
    
    

