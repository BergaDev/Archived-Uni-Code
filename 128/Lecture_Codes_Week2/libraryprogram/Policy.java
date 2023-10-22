package libraryprogram;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hooman
 */
enum Genres {Fantasy,ScienceFiction,Westerns,Romance,Thriller,Mystery,DetectiveStory,Dystopia}

public class Policy 
{
    private long ID;
    private String name;
    private String author;
    private String location;
    private boolean available;
    private Genres genre; 
    private double price;
    public Policy(long ibsn,String name, String auth,String loc,boolean avail,Genres gen, double price)
    {
        ID=ibsn;
        this.name=name;
        author=auth;
        location=loc;
        available=avail;
        genre=gen;
        this.price=price;
    }
    public long getID()
    {
        return ID;
    }
    public String getAuthor()
    {
        return author;
    }
    public boolean getAvailable()
    {
        return available;
    }
    public double getPrice()
    {
        return price;
    }
    public Genres getGenre()
    {
        return genre;
    }
    public void setAvailable(boolean avail)
    {
        available=avail;
    }
    public String getLocation()
    {
        return location;
    }
    public void setLocation(String loc)
    {
        location=loc;
    }
    public void print()
    {
        System.out.println("ID: "+ID+ " NAME: "+ name+ " Author: "+author+" Location: "+location+" Available: "+available+" price:"+price+" Genre: "+genre);
    }
    
    public static void printPolicys(ArrayList<Policy> Policys) // another design to put it in the library
    {
        for (Policy Policy:Policys)
        {
            Policy.print();
        }  
    }
    
    public static ArrayList<Policy> filterByName(ArrayList<Policy> Policys,String pattern)
    {
        ArrayList<Policy> filteredPolicys= new ArrayList<Policy>();
        for (Policy Policy:Policys)
        {
            if (Policy.name.contains(pattern))
            {
                filteredPolicys.add(Policy);
            }
        } 
        return filteredPolicys; 
    }
 
}
