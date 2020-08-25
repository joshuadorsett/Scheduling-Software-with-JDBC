package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import utilities.MakeConnection;
import utilities.MakePreparedStatement;
import view_controller.HomeController;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
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

    public static void addCustomer(String customerName, String address, String phoneNumber) throws SQLException{
        Connection connection = MakeConnection.getConnection();
        String insertAddressStatement = "INSERT INTO address(address, address2, cityId, postalCode, phone, createDate, " +
                "createdBy,lastUpdateBy) VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertAddressStatement, PreparedStatement.RETURN_GENERATED_KEYS);
        LocalDate localDate = LocalDate.now();
        String stringLocalDate = localDate.toString();
        String createdBy = UserDaoImpl.getActiveUser().getUserName();
        String lastUpdateBy = createdBy;
        // key-value mapping
        preparedStatement.setString(1, address);
        preparedStatement.setString(2,"none");
        preparedStatement.setString(3,"1");
        preparedStatement.setString(4,"none");
        preparedStatement.setString(5, phoneNumber);
        preparedStatement.setString(6, stringLocalDate);
        preparedStatement.setString(7, createdBy);
        preparedStatement.setString(8, lastUpdateBy);
        //execute statement for address
        preparedStatement.execute();
//        get the PK of address so we can insert a FK into customer
        ResultSet rs = preparedStatement.getGeneratedKeys();
        int key = rs.next() ? rs.getInt(1) : 0;
        String addressIdString = Integer.toString(key);
//        next insert statement
        String insertCustomerStatement = "INSERT INTO customer(customerName, addressId, active, createDate, " +
                "createdBy, lastUpdateBy) VALUES(?,?,?,?,?,?)";
        MakePreparedStatement.makePreparedStatement(connection, insertCustomerStatement);
        PreparedStatement preparedStatement2 = MakePreparedStatement.getPreparedStatement();
        // key-value mapping
        preparedStatement2.setString(1, customerName);
        preparedStatement2.setString(2, addressIdString);
        preparedStatement2.setString(3, "1");
        preparedStatement2.setString(4, stringLocalDate);
        preparedStatement2.setString(5, createdBy);
        preparedStatement2.setString(6, lastUpdateBy);
        preparedStatement2.execute();

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
        String selectStatement = "SELECT * FROM U07nke.customer INNER JOIN U07nke.address ON customer.addressId = address.addressId;";
        makePreparedStatement(connection, selectStatement); //create statement object
        PreparedStatement preparedStatement = getPreparedStatement();
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        while (resultSet.next()) {
            int customerId = resultSet.getInt("customerId");
            int addressId = resultSet.getInt("addressId");
            String customerName = resultSet.getString("customerName");
            String address = resultSet.getString("address");
            String phone = resultSet.getString("phone");
            LocalDate dateCreated = resultSet.getDate("createDate").toLocalDate();
            LocalTime timeCreated = resultSet.getTime("createDate").toLocalTime();
            String author = resultSet.getString("createdBy");
            LocalDate lastDate = resultSet.getDate("lastUpdate").toLocalDate();
            LocalDateTime lastTimestamp = resultSet.getTimestamp("lastUpdate").toLocalDateTime();

            Customer customer = new Customer(customerId,addressId, customerName,address,phone);
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
    public static void modifyCustomer(String name, String address, String phoneNumber) throws SQLException {
        Connection connection = MakeConnection.getConnection();
        String updateStatement = "UPDATE customer SET customerName = ? WHERE customerId = ?";
        MakePreparedStatement.makePreparedStatement(connection, updateStatement);
        PreparedStatement preparedStatement = MakePreparedStatement.getPreparedStatement();
        String customerId = Integer.toString(HomeController.getModifyCustomer().getCustomerId());
        preparedStatement.setString(1,name);
        preparedStatement.setString(2, customerId);
        preparedStatement.execute();

        String updateStatement2 = "UPDATE address SET address = ?, phone = ? WHERE addressId = ?";
        MakePreparedStatement.makePreparedStatement(connection, updateStatement2);
        PreparedStatement preparedStatement2 = MakePreparedStatement.getPreparedStatement();
        String addressId = Integer.toString(HomeController.getModifyCustomer().getAddressId());
        preparedStatement2.setString(1,address);
        preparedStatement2.setString(2,phoneNumber);
        preparedStatement2.setString(3,addressId);
        preparedStatement2.execute();

        if (preparedStatement.getUpdateCount() > 0 ){
            System.out.println(preparedStatement.getUpdateCount() + " customers modified.");
        } else {
            System.out.println("customer not modified");
        }

    }
    /**
     * delete Customer object from database
     */
    public static void deleteCustomer(Customer customer) throws SQLException{
        Connection connection = MakeConnection.getConnection();
        String deleteStatement2 = "DELETE FROM customer WHERE customerId = ?";
        makePreparedStatement(connection, deleteStatement2);
        PreparedStatement preparedStatement2 = getPreparedStatement();
        String customerId = Integer.toString(customer.getCustomerId());
        preparedStatement2.setString(1, customerId);
        preparedStatement2.execute();

        String deleteStatement = "DELETE FROM address WHERE addressId = ?";
        makePreparedStatement(connection, deleteStatement);
        PreparedStatement preparedStatement = getPreparedStatement();
        String addressId = Integer.toString(customer.getAddressId());
        preparedStatement.setString(1, addressId);
        preparedStatement.execute();

        if (preparedStatement2.getUpdateCount() > 0 ){
            System.out.println(preparedStatement.getUpdateCount() + " customers deleted.");
        } else {
            System.out.println("no customers deleted");
        }
    }
}