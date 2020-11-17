package dao;

import javafx.collections.ObservableList;
import model.User;

import java.sql.SQLException;

public interface UserDAO {


    ObservableList<User> getAllUsers() throws SQLException;

    User getUserByName(String logInName) throws SQLException;

    User getActiveUser();


}
