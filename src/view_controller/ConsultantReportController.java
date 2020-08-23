package view_controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.User;

/**
 * FXML Controller class
 *
 * @author joshuadorsett
 */
public class ConsultantReportController implements Initializable {

    @FXML
    private TableView<User> consultantTable;
    @FXML
    private TableColumn<User, Integer> consultantId;
    @FXML
    private TableColumn<User, String> consultantName;
    @FXML
    private TableView<Appointment> AppointmentTable;
    @FXML
    private TableColumn<Appointment, String> title;
    @FXML
    private TableColumn<Appointment, LocalDate> date;
    @FXML
    private TableColumn<Appointment, LocalTime> time;
    @FXML
    private Button goBack;
    private static User selectedUser;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        generateConsultantTable();
    }

    public void generateConsultantTable(){
        try {

            consultantTable.setItems(UserDaoImpl.getAllUsers());
            consultantId.setCellValueFactory(new PropertyValueFactory<>("userId"));
            consultantName.setCellValueFactory(new PropertyValueFactory<>("userName"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void generateAppointmentTable() throws SQLException {
        selectedUser = consultantTable.getSelectionModel().getSelectedItem();
        String userIdString = Integer.toString(selectedUser.getUserId());
        AppointmentTable.setItems(AppointmentDaoImpl.getConsultantReport(userIdString));
        title.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        date.setCellValueFactory(new PropertyValueFactory<>("appointmentDate"));
        time.setCellValueFactory(new PropertyValueFactory<>("appointmentTime"));
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        sceneChange("Home.fxml", event);
    }

    public void sceneChange(String path, ActionEvent event) throws IOException {
        Parent Parent = FXMLLoader.load(getClass().getResource(path));
        Scene Scene = new Scene(Parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
    }

}
