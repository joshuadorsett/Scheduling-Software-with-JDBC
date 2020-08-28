package view_controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import dao.AppointmentDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;

/**
 * FXML Controller class
 *
 * @author joshuadorsett
 */
public class TypeReportController implements Initializable {
    @FXML
    private TableView<Appointment> RemoteTable;
    @FXML
    private TableColumn<Appointment, String> remoteTitle;
    @FXML
    private TableColumn<Appointment, LocalDate> remoteDate;
    @FXML
    private TableView<Appointment> inPersonTable;
    @FXML
    private TableColumn<Appointment, String> inPersonTitle;
    @FXML
    private TableColumn<Appointment, LocalDate> inPersonDate;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            inPersonTable.setItems(AppointmentDaoImpl.getAllInPerson());
            inPersonTitle.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
            inPersonDate.setCellValueFactory(new PropertyValueFactory<>("start"));

            RemoteTable.setItems(AppointmentDaoImpl.getAllRemote());
            remoteTitle.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
            remoteDate.setCellValueFactory(new PropertyValueFactory<>("start"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
