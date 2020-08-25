package view_controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

import dao.AppointmentDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointment;
import utilities.DateTimeUtils;

/**
 * FXML Controller class
 *
 * @author joshuadorsett
 */
public class ModifyAppointmentController implements Initializable {
    @FXML
    public DatePicker modDate;
    @FXML
    private Label customerId;
    @FXML
    private TextField title;
    @FXML
    private RadioButton remoteRadio;
    @FXML
    private ComboBox time;
    @FXML
    private ComboBox endTime;
    @FXML
    private TextField location;
    @FXML
    private Label userId;

    private Appointment appointment;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        appointment = HomeController.getModifyAppointment();
        userId.setText(Integer.toString(appointment.getAssociatedUserId()));
        customerId.setText(Integer.toString(appointment.getAssociatedCustomerId()));
        title.setText(appointment.getAppointmentTitle());
        location.setText(appointment.getAppointmentLocation());
        modDate.setConverter(DateTimeUtils.setDateFormatConverter());
        time.getItems().addAll("08:00:00", "08:30:00","09:00:00","09:30:00","10:00:00", "10:30:00",
                "11:00:00", "11:30:00","12:00:00","12:30:00","13:00:00","13:30:00","14:00:00","14:30:00", "15:00:00",
                "15:30:00","16:00:00","16:30:00","17:00:00","17:30:00");
        endTime.getItems().addAll("08:30:00","09:00:00","09:30:00","10:00:00", "10:30:00",
                "11:00:00", "11:30:00","12:00:00","12:30:00","13:00:00","13:30:00","14:00:00","14:30:00", "15:00:00",
                "15:30:00","16:00:00","16:30:00","17:00:00","17:30:00","18:00:00");
    }

    @FXML
    public void saveAppointment(ActionEvent event) throws IOException, SQLException, ParseException {
        String typeSelected;
        if (remoteRadio.isSelected()){
            typeSelected = "Remote";
        }
        else {
            typeSelected = "In-Person";
        }
        String start = modDate.getValue() + " "+ time.getSelectionModel().getSelectedItem().toString();
        String end = modDate.getValue() + " "+ endTime.getSelectionModel().getSelectedItem().toString();
        String utcStart = DateTimeUtils.toUtcTimeZone(start);
        String utcEnd = DateTimeUtils.toUtcTimeZone(end);
        String customerIdString = Integer.toString(appointment.getAssociatedCustomerId());
        String appointmentId = Integer.toString(appointment.getAppointmentId());
        AppointmentDaoImpl.modifyAppointment(customerIdString, title.getText(), location.getText(), typeSelected, utcStart, utcEnd, appointmentId);
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
