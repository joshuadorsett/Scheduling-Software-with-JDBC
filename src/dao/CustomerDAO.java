package dao;

import javafx.collections.ObservableList;
import model.Customer;

import java.sql.SQLException;

public interface CustomerDAO {


    ObservableList<Customer> getAll() throws SQLException;

    void add(String customerName, String address, String phoneNumber) throws SQLException;

    void modify(String name, String address, String phoneNumber) throws SQLException;

    void delete(Customer customer) throws SQLException;


}
