package view_controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao.CustomerDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Customer;

/**
 * FXML Controller class
 *
 * @author joshuadorsett
 */
public class ModifyCustomerController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private TextField phoneNumber;
    @FXML
    private Label customerId;
    private Customer customer = HomeController.getModifyCustomer();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        name.setText(customer.getCustomerName());
        address.setText(customer.getCustomerAddress());
        phoneNumber.setText(customer.getCustomerPhoneNumber());
        customerId.setText(Integer.toString(customer.getCustomerId()));
    }

    @FXML
    private void save(ActionEvent event) throws IOException, SQLException {
        CustomerDaoImpl.modifyCustomer(name.getText(),address.getText(),phoneNumber.getText());
        sceneChange("Home.fxml", event);
    }

    @FXML
    private void cancel(ActionEvent event) throws IOException {
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