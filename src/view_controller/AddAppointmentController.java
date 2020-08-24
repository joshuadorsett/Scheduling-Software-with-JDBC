package view_controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import dao.AppointmentDaoImpl;
import dao.UserDaoImpl;
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
import model.Customer;
import model.User;

/**
 * FXML Controller class
 *
 * @author joshuadorsett
 */
public class AddAppointmentController implements Initializable {

    @FXML
    private Label customerId;
    @FXML
    private TextField title;
    @FXML
    private RadioButton remoteRadio;
    @FXML
    private ToggleGroup type;
    @FXML
    private RadioButton inPersonRadio;
    @FXML
    private TextField date;
    @FXML
    private TextField time;
    @FXML
    private TextField location;
    @FXML
    private Label userId;
    @FXML
    private Button saveAppointment;
    @FXML
    private Button cancelAppointment;
    private Customer selectedCustomer;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectedCustomer = HomeController.getCustomerToMeetWith();
        customerId.setText(selectedCustomer.getCustomerName());
        userId.setText(UserDaoImpl.getActiveUser().getUserName());
    }

    @FXML
    private void saveAppointment(ActionEvent event) throws IOException, SQLException {
        String typeSelected;
        if (remoteRadio.isSelected()){
            typeSelected = "Remote";
        }
        else {
            typeSelected = "In-Person";
        }
        String start = date.getText() + " "+ time.getText();
        String customerIdString = Integer.toString(selectedCustomer.getCustomerId());

        AppointmentDaoImpl.addAppointment(customerIdString, title.getText(), location.getText(), typeSelected, start);
        sceneChange("Home.fxml", event);
    }

    @FXML
    private void cancelAppointment(ActionEvent event) throws IOException {
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