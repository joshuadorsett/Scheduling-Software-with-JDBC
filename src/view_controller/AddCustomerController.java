package view_controller;

import java.io.IOException;
import java.sql.SQLException;

import dao.CustomerDAO;
import dao.CustomerDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author joshuadorsett
 */
public class AddCustomerController {


    @FXML
    private TextField name;

    @FXML
    private TextField address;

    @FXML
    private TextField phoneNumber;

    private final CustomerDAO customerDao = new CustomerDaoImpl();


    @FXML
    public void save(ActionEvent event) throws IOException, SQLException {
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.initModality(Modality.NONE);
        alert2.setTitle("Cannot Add");
        alert2.setHeaderText("Cannot Add");
        alert2.setContentText("Please enter data into all text fields.");
        boolean alertNeeded = false;

        if (name.getText().equals(""))
            alertNeeded = true;
        if (address.getText().equals(""))
            alertNeeded = true;
        if (phoneNumber.getText().equals(""))
            alertNeeded = true;
        if (alertNeeded)
            alert2.showAndWait();
        else{
            customerDao.add(name.getText(), address.getText(), phoneNumber.getText());
            sceneChange("Home.fxml", event);
        }
    }


    @FXML
    public void cancel(ActionEvent event) throws IOException {
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
