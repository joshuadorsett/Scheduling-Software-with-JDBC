package view_controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utilities.MakeConnection;
import utilities.MakeStatement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JoshuadorsettC195 extends Application {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        MakeConnection.startConnection();
        test();
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

    public static void test() throws SQLException {
        Connection connection = MakeConnection.getConnection(); //get reference to connection object

        MakeStatement.setStatement(connection); //create statement object

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
