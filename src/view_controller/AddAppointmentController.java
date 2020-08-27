package view_controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.Customer;
import utilities.DateTimeUtils;

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
    private DatePicker date;
    @FXML
    private ComboBox time;
    @FXML
    private ComboBox endTime;
    @FXML
    private TextField location;
    @FXML
    private Label userId;
    private Customer selectedCustomer;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectedCustomer = HomeController.getCustomerToMeetWith();
        customerId.setText(selectedCustomer.getCustomerName());
        userId.setText(UserDaoImpl.getActiveUser().getUserName());
        date.setConverter(
            new StringConverter<LocalDate>() {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                @Override
                public String toString(LocalDate date) {
                    if (date != null) {
                        return dateFormat.format(date);
                    } else {
                        return "";
                    }
                }
                @Override
                public LocalDate fromString(String string) {
                    if (string != null && !string.isEmpty()) {
                        return LocalDate.parse(string, dateFormat);
                    } else {
                        return null;
                    }
                }
            }
        );
        time.getItems().addAll("08:00:00", "08:30:00","09:00:00","09:30:00","10:00:00", "10:30:00",
                "11:00:00", "11:30:00","12:00:00","12:30:00","13:00:00","13:30:00","14:00:00","14:30:00", "15:00:00",
                "15:30:00","16:00:00","16:30:00","17:00:00","17:30:00","20:00:00");
        endTime.getItems().addAll("08:30:00","09:00:00","09:30:00","10:00:00", "10:30:00",
                "11:00:00", "11:30:00","12:00:00","12:30:00","13:00:00","13:30:00","14:00:00","14:30:00", "15:00:00",
                "15:30:00","16:00:00","16:30:00","17:00:00","17:30:00","18:00:00","20:30:00");
    }

    @FXML
    private void saveAppointment(ActionEvent event) throws IOException, SQLException {
        try {
            String typeSelected;
            if (remoteRadio.isSelected()) {
                typeSelected = "Remote";
            } else {
                typeSelected = "In-Person";
            }
            String start = date.getValue()+" "+time.getSelectionModel().getSelectedItem().toString();
            String end = date.getValue()+" "+endTime.getSelectionModel().getSelectedItem().toString();
            LocalDateTime startTimeStamp = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            LocalDateTime endTimeStamp = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            String customerIdString = Integer.toString(selectedCustomer.getCustomerId());
            if(DateTimeUtils.overlaps(startTimeStamp,endTimeStamp)){
                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                alert2.initModality(Modality.NONE);
                alert2.setTitle("Cannot Add");
                alert2.setHeaderText("Cannot Add");
                alert2.setContentText("Sorry, there is already something scheduled for then.");
                alert2.showAndWait();
            } else {
                AppointmentDaoImpl.addAppointment(customerIdString, title.getText(), location.getText(), typeSelected, startTimeStamp, endTimeStamp);
                sceneChange("Home.fxml", event);
            }
        } catch(NullPointerException throwables){
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.initModality(Modality.NONE);
            alert2.setTitle("Cannot Add");
            alert2.setHeaderText("Cannot Add");
            alert2.setContentText("Please complete all data fields.");
            alert2.showAndWait();
        }
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