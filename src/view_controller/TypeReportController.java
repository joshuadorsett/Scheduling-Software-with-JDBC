package view_controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author joshuadorsett
 */
public class TypeReportController implements Initializable {
    @FXML
    private Button goBack;
    @FXML
    private TableView<?> RemoteTable;
    @FXML
    private TableColumn<?, ?> remoteTitle;
    @FXML
    private TableColumn<?, ?> remoteDate;
    @FXML
    private TableColumn<?, ?> remoteTime;
    @FXML
    private TableView<?> inPersonTable;
    @FXML
    private TableColumn<?, ?> inPersonTitle;
    @FXML
    private TableColumn<?, ?> inPersonDate;
    @FXML
    private TableColumn<?, ?> inPersonTime;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
