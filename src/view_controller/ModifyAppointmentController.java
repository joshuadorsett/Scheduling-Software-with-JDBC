package view_controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author joshuadorsett
 */
public class ModifyAppointmentController implements Initializable {

    @FXML
    private TextField customerId;
    @FXML
    private TextField title;
    @FXML
    private RadioButton remoteRadio;
    @FXML
    private ToggleGroup type;
    @FXML
    private RadioButton inPersonradio;
    @FXML
    private TextField date;
    @FXML
    private TextField time;
    @FXML
    private TextField location;
    @FXML
    private Label appointmentId;
    @FXML
    private Label userId;
    @FXML
    private Button saveAppointment;
    @FXML
    private Button cancelAppointment;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void saveAppointment(ActionEvent event) throws IOException {
        sceneChange("Home.fxml", event);
    }

    @FXML
    public void cancelAppointment(ActionEvent event) throws IOException {
        sceneChange("Home.fxml", event);
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
