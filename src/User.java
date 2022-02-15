package src;

public class User {
    private String username;
    private String password;

    public User(String username_, String password_){
        username = username_;
        password = password_;
    }

    /*
    private boolean Login(String username_, String password_){
        if(username.compareTo(username_) == 0 && password.compareTo(password_) == 0){ return true;}
        return false;
    }
    */

    private String getUsername(){
        return username;
    }

}
