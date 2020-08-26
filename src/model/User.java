package model;

/**
 *
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

    /**
     * this method allows a user to login to access the DataBase
     */
    public void login(){
        //everytime a user logs in you must use time stamp that the user logged in to a txt file
        //there easy ways to do this with a java method. this must be appended to the file not overwritten over previous logs!!

    }
}
