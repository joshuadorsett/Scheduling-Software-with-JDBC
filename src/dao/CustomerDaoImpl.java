package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import utilities.MakeConnection;
import utilities.MakePreparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static utilities.MakePreparedStatement.getPreparedStatement;
import static utilities.MakePreparedStatement.makePreparedStatement;


/**
 *
 * @author joshuadorsett
 */
public class CustomerDaoImpl {
    /**
     * create Customer object and convert it into SQL code and add it to database
     */
    public static void addCustomer(String customerName, int addressId, int active) throws SQLException{
        Connection connection = MakeConnection.getConnection();
        String insertStatement = "INSERT INTO customer(customerName, addressId, active, createDate, " +
                "createdBy, lastUpdateBy) VALUES(?,?,?,?,?,?)";
        MakePreparedStatement.makePreparedStatement(connection, insertStatement); //create statement object
        PreparedStatement preparedStatement = MakePreparedStatement.getPreparedStatement();
        LocalDate localDate = LocalDate.now();
        String stringLocalDate = localDate.toString();
        String addressIdString = Integer.toString(addressId);
        String activeString = Integer.toString(active);
        String createdBy = "user";
        String lastUpdateBy = "user";


        // key-value mapping
        preparedStatement.setString(1, customerName);
        preparedStatement.setString(2, addressIdString);
        preparedStatement.setString(3, activeString);
        preparedStatement.setString(4, stringLocalDate);
        preparedStatement.setString(5, createdBy);
        preparedStatement.setString(6, lastUpdateBy);

        //execute statement
        preparedStatement.execute();

        if (preparedStatement.getUpdateCount() > 0 ){
            System.out.println(preparedStatement.getUpdateCount() + " customer added.");
        } else {
            System.out.println("customer was not added");
        }
    }

    /**
     * get all Customer objects from database
     */
    public static ObservableList<Customer> getAllCustomers() throws SQLException{
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        Connection connection = MakeConnection.getConnection(); //get reference to connection object
        String selectStatement = "Select * FROM U07nke.customer";
        makePreparedStatement(connection, selectStatement); //create statement object
        PreparedStatement preparedStatement = getPreparedStatement();
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        while (resultSet.next()) {
            int customerId = resultSet.getInt("customerId");
            String customerName = resultSet.getString("customerName");
            int addressId = resultSet.getInt("addressId");
            int active = resultSet.getInt("active");
            LocalDate dateCreated = resultSet.getDate("createDate").toLocalDate();
            LocalTime timeCreated = resultSet.getTime("createDate").toLocalTime();
            String author = resultSet.getString("createdBy");
            LocalDate lastDate = resultSet.getDate("lastUpdate").toLocalDate();
            LocalDateTime lastTimestamp = resultSet.getTimestamp("lastUpdate").toLocalDateTime();
            String editor = resultSet.getString("lastUpdateBy");

            Customer customer = new Customer(customerId,customerName,addressId,active);
            allCustomers.add(customer);
            System.out.println(customerId + " | " + customerName + " | " + dateCreated + " " +
                    timeCreated + " | " + author + " | " + lastDate +
                    " | " + lastTimestamp);
        }
        return allCustomers;
    }

    /**
     * modify Customer object and convert it into SQl code and update database
     */
    public void modifyCustomer() throws SQLException {

    }
    /**
     * delete Customer object from database
     */
    public static void deleteCustomer(Customer customer) throws SQLException{
        Connection connection = MakeConnection.getConnection();
        String deleteStatement = "DELETE FROM customer WHERE customerId = ?";
        makePreparedStatement(connection, deleteStatement);
        PreparedStatement preparedStatement = getPreparedStatement();
        String customerId = Integer.toString(customer.getCustomerId());
        preparedStatement.setString(1, customerId);
        preparedStatement.execute();
        if (preparedStatement.getUpdateCount() > 0 ){
            System.out.println(preparedStatement.getUpdateCount() + " customers deleted.");
        } else {
            System.out.println("no customers deleted");
        }
    }
}