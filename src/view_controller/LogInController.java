package view_controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.UserDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.User;

/**
 *
 * @author joshuadorsett
 */
public class LogInController implements Initializable {
    @FXML
    private TextField name;
    @FXML
    private TextField password;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    void cancel(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirmation Needed");
        alert.setHeaderText("Confirm Exit");
        alert.setContentText("Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        } else {
            System.out.println("You clicked cancel.");
        }
    }

    @FXML
    void loginButton(ActionEvent event) throws IOException, SQLException {

        try {
            User activeUser = UserDaoImpl.getUserByName(name.getText());
            System.out.println(activeUser.getUserPassword());
            if (name.getText().equals("")){
                Alert alert3 = new Alert(Alert.AlertType.CONFIRMATION);
                alert3.initModality(Modality.NONE);
                alert3.setTitle("Invalid UserName");
                alert3.setHeaderText("Invalid UserName");
                alert3.setContentText("The UserName was Invalid.");
                alert3.showAndWait();
            }
            if (activeUser.getUserPassword().equals(password.getText())) {
                sceneChange("Home.fxml", event);
            } else {
                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                alert2.initModality(Modality.NONE);
                alert2.setTitle("Invalid Password");
                alert2.setHeaderText("Invalid Password");
                alert2.setContentText("The password was incorrect.");
                alert2.showAndWait();
            }
        } catch (Exception e){
            Alert alert3 = new Alert(Alert.AlertType.CONFIRMATION);
            alert3.initModality(Modality.NONE);
            alert3.setTitle("Invalid UserName");
            alert3.setHeaderText("Invalid UserName");
            alert3.setContentText("The UserName was Invalid.");
            alert3.showAndWait();
        }
//        sceneChange("Home.fxml", event);
    }
    /**
     * changes scenes.
     * @param path path of the new scene.
     * @param event action even that caused the scene change.
     * @throws IOException is thrown if it cannot access the FXML loader path.
     */
    public void sceneChange(String path, ActionEvent event) throws IOException {
        Parent Parent = FXMLLoader.load(getClass().getResource(path));
        Scene Scene = new Scene(Parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
    }

}