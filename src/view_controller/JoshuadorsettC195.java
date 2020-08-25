package view_controller;

import dao.AppointmentDaoImpl;
import dao.CustomerDaoImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utilities.Logger;
import utilities.MakeConnection;

import java.io.IOException;
import java.net.SocketException;
import java.sql.SQLException;

public class JoshuadorsettC195 extends Application {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
            MakeConnection.makeConnection();
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
}
