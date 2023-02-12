package models;



/**
 *
 * @author xbali
 */
import models.User;
public class AccountService {
    
    public AccountService(){
        
    }
    
    public User login(String username, String password){
        String abe = "abe";
        String barb = "barb";
        String passwords = "password";
        
        if((username.equals(abe) || username.equals(barb)) && passwords.equals(password)){
            User user = new User(username, password);
            user.setPassword(null);
            return user;
        }else{
           return null; 
        }    
    }
    
}