package model;

/**
 *
 * @author joshuadorsett
 */
public class User {
    private static int userIdCounter = 0;
    private int userId;
    private String userName;
    private String userPassword;

    public User(int userId, String userName, String userPassword){
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public void setUserId(int id){
        this.userId = id;
    }

    public int getUserId(){
        return this.userId;
    }
    /**
     * a method for getting and then incrementing the latest static User ID.
     * @return the User ID counter for a new User object.
     */
    public int generateId(){
        return userIdCounter++;
    }

    public void setUserName(String name){
        this.userName = name;
    }

    public String getUserName(){
        return this.userName;
    }

    public void setUserPassword(String password){
        this.userPassword = password;
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
