package model;

/**
 * creates an user object used to contain the data selected from the DB
 * @author joshuadorsett
 */
public class User {
    private int userId;
    private String userName;
    private String userPassword;

    public User(int userId, String userName, String userPassword){
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public int getUserId(){
        return this.userId;
    }

    public String getUserName(){
        return this.userName;
    }

    public String getUserPassword(){
        return this.userPassword;
    }
}
