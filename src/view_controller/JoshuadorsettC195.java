package view_controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utilities.MakeConnection;

public class JoshuadorsettC195 extends Application {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
