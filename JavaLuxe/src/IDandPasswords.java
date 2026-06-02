import java.util.HashMap;



public class IDandPasswords {
    HashMap<String, User> registerandlogininfo = new HashMap<String, User>();

    IDandPasswords() {
        registerandlogininfo.put("Pio", new User("Pio222", "Pio@gmail.com", "USER"));
        registerandlogininfo.put("Steven", new User("Steven123", "Steven@gmail.com", "USER"));
        registerandlogininfo.put("Marius", new User("Marius2007", "Marius@gmail.com", "USER"));
        registerandlogininfo.put("Nikko", new User("Nikko221", "Nikko@gmail.com", "USER"));
       
    }

    
    public boolean login(String username, String password) {
        User user = registerandlogininfo.get(username);  
        /* 
        check HashMap Storage at IDandPasswords and get username */

        return user != null && user.password.equals(password); 
/*      It only return as true if user exist and the password correct, 
        however it return false if both not exist */ 
        
    }
   

    public boolean register(String username, String password, String email) {
        if (registerandlogininfo.containsKey(username)) { 
            return false;  
   /* If the user exists in the IDandPasswords, the program expect to run it but if the user does not exist,
      it returns null by default, indicating the username is not registered. */
        }
        registerandlogininfo.put(username, new User(password, email, "USER"));
        return true; 
        /*If the username does not exist → 
         a new User object is created with the provided password, email, and a default role "USER". */
    }
    public User getUser(String username) {
    	return registerandlogininfo.get(username);
    }
    public static void main(String[] args) {
        new RegistrationandLoginSystem();
      
       
    }
}

