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
 *
 * @author joshuadorsett
 */
public class UserDaoImpl {
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
    public static User getActiveUser(){
        return activeUser;
    }

}
