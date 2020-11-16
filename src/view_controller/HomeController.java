package view_controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.AppointmentDaoImpl;
import dao.CustomerDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private RadioButton allRadio;
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
    private TableColumn<Appointment, String> endTime;
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, Integer> customerIdCustomerTable;
    @FXML
    private TableColumn<Customer, String> name;
    @FXML
    private TableColumn<Customer, Integer> address;
    @FXML
    private TableColumn<Customer, Integer> phone;
    private static Appointment modifyAppointment; /*the selected Appointment to be modified*/
    private static Customer modifyCustomer; /*the selected Customer to be modified*/
    private static Customer customerToMeetWith; /* customer selected for new appointment*/
    private CustomerDaoImpl customerDao;
    private AppointmentDaoImpl aptDao;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        generateCalendarTable();
        generateCustomerTable();
    }

    public void generateCalendarTable(){

        if (allRadio.isSelected()) {
            try {
                calendarTable.setItems(aptDao.getAll());
                appointmentId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
                customerId.setCellValueFactory(new PropertyValueFactory<>("associatedCustomerId"));
                userId.setCellValueFactory(new PropertyValueFactory<>("associatedUserId"));
                title.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
                                time.setCellValueFactory(new PropertyValueFactory<>("start"));
                endTime.setCellValueFactory(new PropertyValueFactory<>("end"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else if (thisWeekRadio.isSelected()) {
            try {
                calendarTable.setItems(aptDao.getAllWeekly());
                appointmentId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
                customerId.setCellValueFactory(new PropertyValueFactory<>("associatedCustomerId"));
                userId.setCellValueFactory(new PropertyValueFactory<>("associatedUserId"));
                title.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
                time.setCellValueFactory(new PropertyValueFactory<>("start"));
                endTime.setCellValueFactory(new PropertyValueFactory<>("end"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            try {
                calendarTable.setItems(aptDao.getAllMonthly());
                appointmentId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
                customerId.setCellValueFactory(new PropertyValueFactory<>("associatedCustomerId"));
                userId.setCellValueFactory(new PropertyValueFactory<>("associatedUserId"));
                title.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
                time.setCellValueFactory(new PropertyValueFactory<>("start"));
                endTime.setCellValueFactory(new PropertyValueFactory<>("end"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void generateCustomerTable(){
        try {
            customerTable.setItems(customerDao.getAll());
            customerIdCustomerTable.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            name.setCellValueFactory(new PropertyValueFactory<>("customerName"));
            address.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
            phone.setCellValueFactory(new PropertyValueFactory<>("customerPhoneNumber"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @FXML
    public void addAppointment(ActionEvent event) throws IOException {
        customerToMeetWith = customerTable.getSelectionModel().getSelectedItem();
        if (customerToMeetWith != null) {
            sceneChange("AddAppointment.fxml", event);
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.NONE);
            alert.setTitle("Select Customer");
            alert.setHeaderText("Select Customer");
            alert.setContentText("Please select a customer to meet with");
            alert.showAndWait();
        }
    }
    public static Customer getCustomerToMeetWith() {
        return customerToMeetWith;
    }

    @FXML
    public void modifyAppointment(ActionEvent event) throws IOException {
        try {
            modifyAppointment = calendarTable.getSelectionModel().getSelectedItem();
            sceneChange("ModifyAppointment.fxml", event);
        } catch (LoadException throwables) {
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.initModality(Modality.NONE);
            alert2.setTitle("Cannot Modify");
            alert2.setHeaderText("Cannot Modify");
            alert2.setContentText("Please select an appointment to modify.");
            alert2.showAndWait();
        }
    }
    /**
     * @return modifyAppointment
     */
    public static Appointment getModifyAppointment() {
        return modifyAppointment;
    }

    @FXML
    public void deleteAppointment(ActionEvent event) throws SQLException {
        try {
            Appointment appointment = calendarTable.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.NONE);
            alert.setTitle("Appointment Delete");
            alert.setHeaderText("Confirm?");
            alert.setContentText("Are you sure you want to delete " + appointment.getTitle() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                aptDao.delete(appointment);
                generateCalendarTable();
                System.out.println("Appointment " + appointment.getTitle() + " was removed.");

            } else {
                System.out.println("Appointment " + appointment.getTitle() + " was not removed.");
            }
        } catch (Exception throwables) {
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.initModality(Modality.NONE);
            alert2.setTitle("Cannot Delete");
            alert2.setHeaderText("Cannot Delete");
            alert2.setContentText("Please select an appointment to delete.");
            alert2.showAndWait();
        }
    }

    @FXML
    public void addCustomer(ActionEvent event) throws IOException {
        sceneChange("AddCustomer.fxml", event);
    }

    @FXML
    public void modifyCustomer(ActionEvent event) throws IOException {
        try{
            modifyCustomer = customerTable.getSelectionModel().getSelectedItem();
            sceneChange("ModifyCustomer.fxml", event);
        } catch (LoadException throwables) {
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.initModality(Modality.NONE);
            alert2.setTitle("Cannot Modify");
            alert2.setHeaderText("Cannot Modify");
            alert2.setContentText("Please select a customer to modify.");
            alert2.showAndWait();
        }
    }

    public static Customer getModifyCustomer() {
        return modifyCustomer;
    }

    @FXML
    public void deleteCustomer(ActionEvent event) {
        try {
            Customer customer = customerTable.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.NONE);
            alert.setTitle("Customer Delete");
            alert.setHeaderText("Confirm?");
            alert.setContentText("Are you sure you want to delete " + customer.getCustomerName() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                try {
                    customerDao.delete(customer);
                    generateCustomerTable();
                    System.out.println("Customer " + customer.getCustomerName() + " was removed.");
                } catch (SQLException throwables) {
                    Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                    alert2.initModality(Modality.NONE);
                    alert2.setTitle("Cannot Delete");
                    alert2.setHeaderText("Cannot Delete");
                    alert2.setContentText("Can't delete a customer that is scheduled for an appointment");
                    alert2.showAndWait();
                }
            } else {
                System.out.println("Customer " + customer.getCustomerName() + " was not removed.");
            }
        } catch (Exception throwables) {
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.initModality(Modality.NONE);
            alert2.setTitle("Cannot Delete");
            alert2.setHeaderText("Cannot Delete");
            alert2.setContentText("Please select a customer to delete and delete customer's associated appointments first.");
            alert2.showAndWait();
        }
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
    public void exitProgram(ActionEvent event) {
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

    public void allRadio(ActionEvent actionEvent) {
        generateCalendarTable();
    }

    public void thisMonthRadio(ActionEvent actionEvent) {
        generateCalendarTable();
    }

    public void thisWeekRadio(ActionEvent actionEvent) {
        generateCalendarTable();
    }
}
