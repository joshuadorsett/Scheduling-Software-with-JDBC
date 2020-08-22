package view_controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Country;
import utilities.MakeConnection;
import utilities.MakeStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class JoshuadorsettC195 extends Application {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        MakeConnection.makeConnection();
        selectSQLtest();
        launch(args);
        MakeConnection.endConnection();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void selectSQLtest() throws SQLException {
        Connection connection = MakeConnection.getConnection(); //get reference to connection object
        MakeStatement.makeStatement(connection); //create statement object
        Statement statement = MakeStatement.getStatement(); // get reference to statement object

        String selectStatement = "Select * FROM U07nke.country";
        statement.execute(selectStatement);
        ResultSet resultSet = statement.getResultSet();
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
                                            " | " + lastTimestamp + " | ");
//            OR create a country object for each iteration and append them into a display observable list utside of the loop!
//            Country country = new Country(countryId, countryName, author, lastTimestamp);
        }
    }

    public static void insertSQLtest() throws SQLException {
//        inserting raw sql

        Connection connection = MakeConnection.getConnection(); //get reference to connection object
        MakeStatement.makeStatement(connection); //create statement object
        Statement statement = MakeStatement.getStatement(); // get reference to statement object

        //raw sql insert statement
        String insertStatement = "INSERT INTO country(country, createDate, createdBy, lastUpdateBy) "
                + "VALUES('Russia', '2020-02-22 00:00:00', 'Dave', 'Josh')";

        statement.execute(insertStatement); //execute sql statement

        //confirm rows are affected
        if (MakeStatement.getStatement().getUpdateCount() > 0){
            System.out.println(statement.getUpdateCount()+" country added");
        } else {
            System.out.println("country was not added");
        }
    }

}
