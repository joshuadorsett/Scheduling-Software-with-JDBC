package dao;

import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.SQLException;
import java.time.LocalDateTime;

public interface AppointmentDAO {


    void add(String customerId, String title, String location, String type, LocalDateTime start, LocalDateTime end) throws SQLException;

    void modify(String customerId, String title, String location, String type, LocalDateTime start, LocalDateTime end, String appointmentId) throws SQLException;

    ObservableList<Appointment> getAll() throws SQLException;

    ObservableList<Appointment> getAllMonthly() throws SQLException;

    ObservableList<Appointment> getAllWeekly() throws SQLException;

    ObservableList<Appointment> getAllRemote() throws  SQLException;

    ObservableList<Appointment> getAllInPerson() throws  SQLException;

    ObservableList<Appointment> getConsultantReport(String consultant) throws SQLException;

    void delete(Appointment appointment) throws SQLException;


}
