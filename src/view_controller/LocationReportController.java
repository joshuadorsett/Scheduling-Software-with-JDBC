package view_controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
public class LocationReportController implements Initializable {

    @FXML
    private TableView<Appointment> locationTable;
    @FXML
    private TableColumn<Appointment, String> title;
    @FXML
    private TableColumn<Appointment, String> location;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            locationTable.setItems(AppointmentDaoImpl.getAll());
            title.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
            location.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
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
