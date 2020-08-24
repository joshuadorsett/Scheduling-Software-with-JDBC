package view_controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.Customer;

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
    private DatePicker date;
    @FXML
    private ComboBox time;
    @FXML
    private ComboBox endTime;
    @FXML
    private TextField location;
    @FXML
    private Label userId;
    @FXML
    private Button saveAppointment;
    @FXML
    private Button cancelAppointment;
    private Customer selectedCustomer;
    StringConverter<LocalDate> converter;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectedCustomer = HomeController.getCustomerToMeetWith();
        customerId.setText(selectedCustomer.getCustomerName());
        userId.setText(UserDaoImpl.getActiveUser().getUserName());
        date.setConverter(converter);
        converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter =
                    DateTimeFormatter.ofPattern("dd-MM-yyyy");

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };

        time.getItems().addAll("08:00:00", "08:30:00","09:00:00","09:30:00","10:00:00", "10:30:00",
                "11:00:00", "11:30:00","12:00:00","12:30:00","01:00:00","01:30:00","02:00:00","02:30:00", "03:00:00",
                "03:30:00","04:00:00","04:30:00","05:00:00","05:30:00");
        endTime.getItems().addAll("08:30:00","09:00:00","09:30:00","10:00:00", "10:30:00",
                "11:00:00", "11:30:00","12:00:00","12:30:00","01:00:00","01:30:00","02:00:00","02:30:00", "03:00:00",
                "03:30:00","04:00:00","04:30:00","05:00:00","05:30:00","06:00:00");

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
        String start = date.getValue() + " "+ time.getSelectionModel().getSelectedItem().toString();
        String end = date.getValue() + " "+ endTime.getSelectionModel().getSelectedItem().toString();
        String customerIdString = Integer.toString(selectedCustomer.getCustomerId());

        AppointmentDaoImpl.addAppointment(customerIdString, title.getText(), location.getText(), typeSelected, start, end);
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