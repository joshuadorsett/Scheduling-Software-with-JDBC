package view_controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Appointment;
import model.Customer;

/**
 * FXML Controller class
 *
 * @author joshuadorsett
 */
public class HomeController implements Initializable {
    @FXML
    private ToggleGroup homeRadio;
    @FXML
    private RadioButton allRadio;
    @FXML
    private RadioButton thisMonthRadio;
    @FXML
    private RadioButton thisWeekRadio;
    @FXML
    private TableView<Appointment> calendarTable;
    @FXML
    private TableColumn<Appointment, Integer> appointmentId;
    @FXML
    private TableColumn<Appointment, Integer> userId;
    @FXML
    private TableColumn<Appointment, Integer> customerId;
    @FXML
    private TableColumn<Appointment, String> title;
    @FXML
    private TableColumn<Appointment, String> location;
    @FXML
    private TableColumn<Appointment, LocalDate> date;
    @FXML
    private TableColumn<Appointment, LocalTime> time;
    @FXML
    private TableColumn<Appointment, String> type;
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, Integer> customerIdCustomerTable;
    @FXML
    private TableColumn<Customer, String> name;
    @FXML
    private TableColumn<Customer, String> address;
    @FXML
    private TableColumn<Customer, Integer> phone;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void addAppointment(ActionEvent event) throws IOException {
        sceneChange("AddAppointment.fxml", event);
    }

    @FXML
    public void modifyAppointment(ActionEvent event) throws IOException {
        sceneChange("ModifyAppointment.fxml", event);
    }

    @FXML
    public void deleteAppointment(ActionEvent event) {
    }

    @FXML
    public void addCustomer(ActionEvent event) throws IOException {
        sceneChange("AddCustomer.fxml", event);
    }

    @FXML
    public void modifyCustomer(ActionEvent event) throws IOException {
        sceneChange("ModifyCustomer.fxml", event);
    }

    @FXML
    public void deleteCustomer(ActionEvent event) {
    }

    @FXML
    public void typeReport(ActionEvent event) throws IOException {
        sceneChange("TypeReport.fxml", event);
    }

    @FXML
    public void consultantReport(ActionEvent event) throws IOException {
        sceneChange("ConsultantReport.fxml", event);
    }

    @FXML
    public void locationReport(ActionEvent event) throws IOException {
        sceneChange("LocationReport.fxml", event);
    }
    @FXML
    public void LogOut(ActionEvent event) throws IOException {
        sceneChange("LogIn.fxml", event);
    }

    @FXML
    public void exitProgram(ActionEvent event) throws SQLException, Exception {
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
