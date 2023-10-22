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
public class Library 
{
    ArrayList<Policy>Policys;
    ArrayList<User> users;
    // users to be added later
    public Library()
    {
        Policys=new ArrayList<Policy>();
        users=new ArrayList<User>();
    }
    public Policy findPolicy(long ID) //This one also can be moved to Policy  Policy findPolicy(ArrayList<Policy> Policys, int userID)
    {
        for (Policy Policy:Policys)
        {
            if (Policy.getID()==ID)
                return Policy;  
        }
        return null;      
    }
    public boolean addPolicy(Policy Policy)
    {
        if (findPolicy(Policy.getID())==null)
        {
            Policys.add(Policy);
            return true;
        }
        else
            return false;
    }
    public void printPolicys()
    {
        System.out.println("\n Policy List in Library  ");
//        for (Policy Policy:Policys)
//        {
//            Policy.print();
//        }
        Policy.printPolicys(Policys); // Use the Policy method or do it here .
    }
    
    
    
    public User findUser(int userID) // This is different from Library.findPolicy to show another design
    {
//        for (User user:users)
//        {
//            if (user.getID()==userID)
//                return user;  
//        }
//        return null; 
        return User.findUser(users,userID); // call User static findUser
    }
    public boolean addUser(User user) // This is different from Library.addPolicy to show another design
    {
//        if (findUser(user.getID())==null)
//        {
//            users.add(user);
//            return true;
//        }
//        else
//            return false;
        return User.addUser(users, user); // call User static addUser
    }
    public void printUsers() //Also you can move it to User
    {
        for (User user:users)
        {
            user.print();
        }  
    }
//    public boolean borrowPolicy(int ID)
//    {
//        Policy Policy=findPolicy(ID);
//        if ((Policy!=null)&&(Policy.getAvailable()))   
//            {
//              Policy.setAvailable(false);
//              return true;
//            }
//        return false;  
//    }
    public boolean borrowPolicy(long ID,int userID)
    {
         Policy Policy=findPolicy(ID);
         User user=findUser(userID);
        if ((Policy!=null)&&(user!=null)&&(Policy.getAvailable())&&(user.borrowPolicy(Policy)))   
            {  
              return true;
            }
        return false;    
    }
    public boolean returnPolicy(long ID,int userID)
    {
         Policy Policy=findPolicy(ID);
         User user=findUser(userID);
        if ((Policy!=null)&&(user!=null)&&(!Policy.getAvailable())&&(user.returnPolicy(Policy)))   
            {  
              return true;
            }
        return false;    
    }
    public void printAllBorrowedPolicys()
    {
        System.out.println("\n all Borrowed Policys in Library  ");
        for (Policy Policy:Policys)
        {
            if (!Policy.getAvailable())
                Policy.print();
        } 
        
    }
     public ArrayList<Policy> filterPolicysByName(String pattern)
    {
        return Policy.filterByName(Policys, pattern); // Use the Policy method
        
    }
    public ArrayList<Policy> filterPolicysByAuthor(String pattern) // This one does the whole logic here 
    {
        ArrayList<Policy> filteredPolicys= new ArrayList<Policy>();
        for (Policy Policy:Policys)
        {
            if (Policy.getAuthor().contains(pattern))
            {
                filteredPolicys.add(Policy);
            }
        } 
        return filteredPolicys;     
    }
    public ArrayList<Double> getTotalPricePerGenre()
    {
        ArrayList<Double> totalPrice=new ArrayList<Double>();
        double total=0;
        for (Genres genre:Genres.values())
        {
            total=0;
            for (Policy Policy:Policys)
            {
                if(Policy.getGenre().equals(genre))
                    total+=Policy.getPrice();
            }
            totalPrice.add(total);
        }
        return totalPrice;
    }
        
 
    public void reportTotalPricePerGenre(ArrayList<Double> prices)
    {
        System.out.println("\n Total Price Per Genre ");
        for (int i=0;i<prices.size();i++)
            System.out.println(Genres.values()[i]+"     "+prices.get(i));  
    }
    public ArrayList<String> populateDistinctAuthorList()
    {
        ArrayList<String> authors =new ArrayList<String>();
        for (Policy Policy:Policys)
        {
            boolean found=false;
            for (String author:authors)
            {
                if (Policy.getAuthor().equals(author))
                {
                    found=true;
                    break;
                }
            }
            if (!found)
                authors.add(Policy.getAuthor());
        }
        return authors;   
    }
    public ArrayList<Integer> getTotalPerAuthor(ArrayList<String> authors)
            
    {
        ArrayList<Integer> totalCount=new ArrayList<Integer>();
        int count=0;
        for (String author:authors)
        {
            count=0;
            for (Policy Policy:Policys)
            {
                if(Policy.getAuthor().equals(author))
                    count++;
            }
            totalCount.add(count);
        }
        return totalCount;
    }
    public void reportTotalPPerAuthor(ArrayList<String> authors, ArrayList<Integer> counts)
    {
        System.out.println("\n Total Number of Policys Per Author ");
        for (int i=0;i<counts.size();i++)
            System.out.println(authors.get(i)+"     "+counts.get(i));  
    }
    
    
   
}
