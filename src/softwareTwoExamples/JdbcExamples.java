package softwareTwoExamples;

import utilities.MakeConnection;
import utilities.MakePreparedStatement;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class JdbcExamples {
    public static void selectPreparedStatementTest() throws SQLException {
        Connection connection = MakeConnection.getConnection(); //get reference to connection object
        String selectStatement = "Select * FROM U07nke.country";
        MakePreparedStatement.makePreparedStatement(connection, selectStatement); //create statement object
        PreparedStatement ps = MakePreparedStatement.getPreparedStatement();
        ps.execute();
        ResultSet resultSet = ps.getResultSet();
        while (resultSet.next()){
            int countryId = resultSet.getInt("countryId");
            String countryName = resultSet.getString("country");
            LocalDate dateCreated = resultSet.getDate("createDate").toLocalDate();
            LocalTime timeCreated = resultSet.getTime("createDate").toLocalTime();
            String author = resultSet.getString("createdBy");
            LocalDate lastDate = resultSet.getDate("lastUpdate").toLocalDate();
            LocalDateTime lastTimestamp = resultSet.getTimestamp("lastUpdate").toLocalDateTime();
            String editor = resultSet.getString("lastUpdateBy");

            System.out.println(countryId + " | " + countryName +" | " + dateCreated +" "+
                    timeCreated + " | " + author + " | " + lastDate +
                    " | " + lastTimestamp);
//            OR create a country object for each iteration and append them into a display observable list utside of the loop!
//            Country country = new Country(countryId, countryName, author, lastTimestamp);
        }
    }
    public static void deletePreparedStatementTest() throws SQLException {
        Connection connection = MakeConnection.getConnection(); //get reference to connection object

        // make the sql code with ? for each value that is variable. this index starts at 1 and skips if a value is entered.
        String deleteStatement = "DELETE FROM country WHERE country = ?";

        //create prepared statement
        MakePreparedStatement.makePreparedStatement(connection, deleteStatement); //create statement object
        PreparedStatement ps = MakePreparedStatement.getPreparedStatement();

        // make variables for ps
        String countryName;

        // keyboard input
        Scanner keyboard = new Scanner(System.in);

        System.out.print("enter a country to delete! ");
        countryName = keyboard.nextLine();

        // key-value mapping
        ps.setString(1, countryName);


        //execute statement
        ps.execute();

        if (ps.getUpdateCount() > 0 ){
            System.out.println(ps.getUpdateCount() + " rows deleted.");
        } else {
            System.out.println("no rows deleted");
        }
    }

    public static void updatePreparedStatementTest() throws SQLException {
        Connection connection = MakeConnection.getConnection(); //get reference to connection object

        // make the sql code with ? for each value that is variable. this index starts at 1 and skips if a value is entered.
        String updateStatement = "UPDATE country SET country = ?, createdBy = ? WHERE country = ?";

        //create prepared statement
        MakePreparedStatement.makePreparedStatement(connection, updateStatement); //create statement object
        PreparedStatement ps = MakePreparedStatement.getPreparedStatement();

        // make variables for ps
        String countryName, newCountry, createdBy;

        // keyboard input
        Scanner keyboard = new Scanner(System.in);
        //promt one enter country for filter
        System.out.print("enter a country to update! ");
        countryName = keyboard.nextLine();
        // prompt for new country
        System.out.print("enter new country! ");
        newCountry = keyboard.nextLine();
        //prompt for who created it
        System.out.print("enter user name! ");
        createdBy = keyboard.nextLine();

        // key-value mapping
        ps.setString(1, newCountry);
        ps.setString(2, createdBy);
        ps.setString(3, countryName);

        //execute statement
        ps.execute();

        if (ps.getUpdateCount() > 0 ){
            System.out.println(ps.getUpdateCount() + " rows updated.");
        } else {
            System.out.println("no rows updated");
        }
    }

    public static void insertPreparedStatementTest() throws SQLException {
        Connection connection = MakeConnection.getConnection(); //get reference to connection object

        // make the sql code with ? for each value that is variable. this index starts at 1 and skips if a value is entered.
        String insertStatement = "INSERT INTO country(country, createDate, createdBy, lastUpdateBy) VALUES(?,?,?,?)";

        //create prepared statement
        MakePreparedStatement.makePreparedStatement(connection, insertStatement); //create statement object
        PreparedStatement ps = MakePreparedStatement.getPreparedStatement();

        // make variables for ps
        String countryName;
        String  createDate = "2020-03-28 00:00:00";
        String createdBy = "Joshua";
        String lastUpdateBy = "Joshua";

        // keyboard input
        Scanner keyboard = new Scanner(System.in);
        System.out.print("enter a country! ");
        countryName = keyboard.nextLine();

        // key-value mapping
        ps.setString(1, countryName);
        ps.setString(2, createDate);
        ps.setString(3, createdBy);
        ps.setString(4, lastUpdateBy);

        //execute statement
        ps.execute();

        if (ps.getUpdateCount() > 0 ){
            System.out.println(ps.getUpdateCount() + " rows inserted.");
        } else {
            System.out.println("no rows inserted");
        }
    }
}
