package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;
import utilities.MakeConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import static utilities.MakePreparedStatement.getPreparedStatement;
import static utilities.MakePreparedStatement.makePreparedStatement;

/**
 * the DAO for the user table
 * @author joshuadorsett
 */
public class UserDaoImpl {
//    this is a static variable kept for the active user of this software.
    private static User activeUser;
    /**
     * get all User objects from database
     */
    public static ObservableList<User> getAllUsers() throws SQLException {
        ObservableList<User> allUsers = FXCollections.observableArrayList();
        Connection connection = MakeConnection.getConnection();
        String selectStatement = "Select * FROM U07nke.user";
        makePreparedStatement(connection, selectStatement);
        PreparedStatement preparedStatement = getPreparedStatement();
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        while (resultSet.next()) {
            int userId = resultSet.getInt("userId");
            String userName = resultSet.getString("userName");
            String password = resultSet.getString("password");

            User user = new User(userId, userName, password);
            allUsers.add(user);
            System.out.println(userId + " | " + userName + " | " + password + " ");
        }
        return allUsers;
    }

//      select a certain user based on the textfield at login
    public static User getUserByName(String logInName) throws SQLException {
        Connection connection = MakeConnection.getConnection();
        String selectStatement = "Select * FROM U07nke.user WHERE userName = ? ";
        makePreparedStatement(connection, selectStatement);
        PreparedStatement preparedStatement = getPreparedStatement();
        preparedStatement.setString(1, logInName);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();

        while (resultSet.next()) {
            int id = resultSet.getInt("userId");
            String name = resultSet.getString("userName");
            String password = resultSet.getString("password");

            User user = new User(id, name, password);
            activeUser = user;
            System.out.println(id + " | " + /*userName + */" ");
        }
        return activeUser;
    }

//    getter for the active user
    public static User getActiveUser(){
        return activeUser;
    }

}
