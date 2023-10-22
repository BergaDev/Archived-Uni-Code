import java.util.ArrayList;

public class InsuranceCompany {
    String name;
    //User array
    private String adminUsername;
    private String adminPassword; 
    private int flatRate;
    private ArrayList<User>users;

    public InsuranceCompany(String adminUsername, String adminPassword, int flatRate){
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.flatRate = flatRate;
    }
    
    boolean validateAdmin(String Username, String Password) {
        String userName = Username;
        String userPassword = Password;
        if (Username == adminUsername & Password == adminPassword){
            return true;
        } else{
            return false;
        }
        
    }

    boolean addUser(User user){
        //code
        return true;
    }
}
