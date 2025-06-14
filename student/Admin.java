package student;

public class Admin implements User {
    private String username;
    private String password;
    
    //ye object h?

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }  
}