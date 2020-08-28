package view_controller;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.User;
import utilities.DateTimeUtils;
import utilities.Logger;

/**
 *
 * @author joshuadorsett
 */
public class LogInController implements Initializable {
    @FXML
    private TextField name;
    @FXML
    private TextField password;
    @FXML
    private Button login;
    @FXML
    private Button cancel;
//    static ResourceBundle resources;
//    static Locale locale;


    @FXML
    void cancel(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle(JoshuaDorsettC195.resources.getString("exit"));
        alert.setHeaderText(JoshuaDorsettC195.resources.getString("exit"));
        alert.setContentText(JoshuaDorsettC195.resources.getString("exitMessage"));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        } else {
            System.out.println("You clicked cancel.");
        }
    }

    @FXML
    void loginButton(ActionEvent event) {
        try {
            User activeUser = UserDaoImpl.getUserByName(name.getText());
            if (name.getText().equals("")) {
                Alert alert3 = new Alert(Alert.AlertType.CONFIRMATION);
                alert3.initModality(Modality.NONE);
                alert3.setTitle(JoshuaDorsettC195.resources.getString("invalidUser"));
                alert3.setHeaderText(JoshuaDorsettC195.resources.getString("invalidUser"));
                alert3.setContentText(JoshuaDorsettC195.resources.getString("invalidUserMessage"));
                alert3.showAndWait();
            }
            if (activeUser.getPassword().equals(password.getText())) {
                if(DateTimeUtils.fifteenMinuteAlert()){
                    Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                    alert2.initModality(Modality.NONE);
                    alert2.setTitle(JoshuaDorsettC195.resources.getString("fifteenMinuteAlert"));
                    alert2.setHeaderText(JoshuaDorsettC195.resources.getString("fifteenMinuteAlert"));
                    alert2.setContentText(JoshuaDorsettC195.resources.getString("fifteenMinuteAlertMessage"));
                    alert2.showAndWait();
                }
                Logger.logger(true);
                sceneChange("Home.fxml", event);
            } else {
                Logger.logger(false);
                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                alert2.initModality(Modality.NONE);
                alert2.setTitle(JoshuaDorsettC195.resources.getString("invalidPassword"));
                alert2.setHeaderText(JoshuaDorsettC195.resources.getString("invalidPassword"));
                alert2.setContentText(JoshuaDorsettC195.resources.getString("invalidPasswordMessage"));
                alert2.showAndWait();
            }
        }catch (Exception e){
                Alert alert3 = new Alert(Alert.AlertType.CONFIRMATION);
                alert3.initModality(Modality.NONE);
                alert3.setTitle(JoshuaDorsettC195.resources.getString("invalidUser"));
                alert3.setHeaderText(JoshuaDorsettC195.resources.getString("invalidUser"));
                alert3.setContentText(JoshuaDorsettC195.resources.getString("invalidUserMessage"));
                alert3.showAndWait();
            }
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        login.setText(JoshuaDorsettC195.resources.getString("login"));
        cancel.setText(JoshuaDorsettC195.resources.getString("cancel"));
    }
}