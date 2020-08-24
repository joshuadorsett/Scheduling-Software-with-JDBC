package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import utilities.MakeConnection;
import utilities.MakePreparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static utilities.MakePreparedStatement.*;

/**
 *
 * @author joshuadorsett
 */
public class AppointmentDaoImpl {
    /**
     * create Appointment object and convert it into SQL code and add it to database
     */
    public static void addAppointment(String customerId, String title,
                                      String location, String type, String start ) throws SQLException {
        Connection connection = MakeConnection.getConnection();
        String insertStatement = "INSERT INTO appointment(customerId,userId,title,description,"+
                "location,contact,type,url,start,end,createDate,createdBy,lastUpdateBy)" +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        MakePreparedStatement.makePreparedStatement(connection, insertStatement);
        PreparedStatement preparedStatement = MakePreparedStatement.getPreparedStatement();

        LocalDate localDate = LocalDate.now();
        String stringLocalDate = localDate.toString();
        String userId = Integer.toString(UserDaoImpl.getActiveUser().getUserId());
        String createdBy = UserDaoImpl.getActiveUser().getUserName();
        String lastUpdateBy = createdBy;

        preparedStatement.setString(1, customerId);
        preparedStatement.setString(2, userId);
        preparedStatement.setString(3, title);
        preparedStatement.setString(4, "none");
        preparedStatement.setString(5, location);
        preparedStatement.setString(6, "none");
        preparedStatement.setString(7, type);
        preparedStatement.setString(8, "none");
        preparedStatement.setString(9, start);
        preparedStatement.setString(10, "2020-12-31 00:00:00");
        preparedStatement.setString(11, stringLocalDate);
        preparedStatement.setString(12, createdBy);
        preparedStatement.setString(13, lastUpdateBy);

        preparedStatement.execute();

        if (preparedStatement.getUpdateCount() > 0 ){
            System.out.println(preparedStatement.getUpdateCount() + " appointment added.");
        } else {
            System.out.println("appointment was not added");
        }
    }

    /**
     * get all Appointment objects from database
     * @return
     */
    public static ObservableList<Appointment> getAllAppointments() throws SQLException {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        Connection connection = MakeConnection.getConnection(); //get reference to connection object
        String selectStatement = "Select * FROM U07nke.appointment";
        makePreparedStatement(connection, selectStatement); //create statement object
        PreparedStatement preparedStatement = getPreparedStatement();
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        while (resultSet.next()) {
            int appointmentId = resultSet.getInt("appointmentId");
            int customerId = resultSet.getInt("customerId");
            int userId = resultSet.getInt("userId");
            String title = resultSet.getString("title");
            String location = resultSet.getString("location");
            String type = resultSet.getString("type");
            LocalDate startDate = resultSet.getDate("start").toLocalDate();
            LocalTime startTime = resultSet.getTime("start").toLocalTime();
            String author = resultSet.getString("createdBy");
            LocalDate lastDate = resultSet.getDate("lastUpdate").toLocalDate();
            LocalDateTime lastTimestamp = resultSet.getTimestamp("lastUpdate").toLocalDateTime();

            Appointment appointment = new Appointment(appointmentId, customerId,userId,title,type,startDate,startTime,location);
            allAppointments.add(appointment);
            System.out.println(appointmentId + " | " + title + " | " + startDate + " " +
                    startTime + " | " + author + " | " + lastDate +
                    " | " + lastTimestamp);
        }
        return allAppointments;
    }

    public static ObservableList<Appointment> getMonthlyAppointments() throws SQLException {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        Connection connection = MakeConnection.getConnection(); //get reference to connection object
        String selectStatement = "Select * FROM U07nke.appointment WHERE start BETWEEN NOW() AND LAST_DAY(NOW())";
        makePreparedStatement(connection, selectStatement); //create statement object
        PreparedStatement preparedStatement = getPreparedStatement();
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        while (resultSet.next()) {
            int appointmentId = resultSet.getInt("appointmentId");
            int customerId = resultSet.getInt("customerId");
            int userId = resultSet.getInt("userId");
            String title = resultSet.getString("title");
            String location = resultSet.getString("location");
            String type = resultSet.getString("type");
            LocalDate startDate = resultSet.getDate("start").toLocalDate();
            LocalTime startTime = resultSet.getTime("start").toLocalTime();
            String author = resultSet.getString("createdBy");
            LocalDate lastDate = resultSet.getDate("lastUpdate").toLocalDate();
            LocalDateTime lastTimestamp = resultSet.getTimestamp("lastUpdate").toLocalDateTime();

            Appointment appointment = new Appointment(appointmentId, customerId,userId,title,type,startDate,startTime,location);
            allAppointments.add(appointment);
            System.out.println(appointmentId + " | " + title + " | " + startDate + " " +
                    startTime + " | " + author + " | " + lastDate +
                    " | " + lastTimestamp);
        }
        return allAppointments;
    }

    public static ObservableList<Appointment> getWeeklyAppointments() throws SQLException {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        Connection connection = MakeConnection.getConnection(); //get reference to connection object
        String selectStatement = "Select * FROM U07nke.appointment WHERE WEEK(NOW()) = WEEK(start)";
        makePreparedStatement(connection, selectStatement); //create statement object
        PreparedStatement preparedStatement = getPreparedStatement();
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        while (resultSet.next()) {
            int appointmentId = resultSet.getInt("appointmentId");
            int customerId = resultSet.getInt("customerId");
            int userId = resultSet.getInt("userId");
            String title = resultSet.getString("title");
            String location = resultSet.getString("location");
            String type = resultSet.getString("type");
            LocalDate startDate = resultSet.getDate("start").toLocalDate();
            LocalTime startTime = resultSet.getTime("start").toLocalTime();
            String author = resultSet.getString("createdBy");
            LocalDate lastDate = resultSet.getDate("lastUpdate").toLocalDate();
            LocalDateTime lastTimestamp = resultSet.getTimestamp("lastUpdate").toLocalDateTime();

            Appointment appointment = new Appointment(appointmentId, customerId,userId,title,type,startDate,startTime,location);
            allAppointments.add(appointment);
            System.out.println(appointmentId + " | " + title + " | " + startDate + " " +
                    startTime + " | " + author + " | " + lastDate +
                    " | " + lastTimestamp);
        }
        return allAppointments;
    }
    public static ObservableList<Appointment> getRemoteAppointments() throws  SQLException {
        ObservableList<Appointment> remoteAppointments = FXCollections.observableArrayList();
        Connection connection = MakeConnection.getConnection();
        String selectStatement = "Select * FROM U07nke.appointment Where type = 'Remote' ";
        makePreparedStatement(connection, selectStatement);
        PreparedStatement preparedStatement = getPreparedStatement();
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        while (resultSet.next()) {
            int appointmentId = resultSet.getInt("appointmentId");
            int customerId = resultSet.getInt("customerId");
            int userId = resultSet.getInt("userId");
            String title = resultSet.getString("title");
            String location = resultSet.getString("location");
            String type = resultSet.getString("type");
            LocalDate startDate = resultSet.getDate("start").toLocalDate();
            LocalTime startTime = resultSet.getTime("start").toLocalTime();
            String author = resultSet.getString("createdBy");
            LocalDate lastDate = resultSet.getDate("lastUpdate").toLocalDate();
            LocalDateTime lastTimestamp = resultSet.getTimestamp("lastUpdate").toLocalDateTime();

            Appointment appointment = new Appointment(appointmentId, customerId,userId,title,type,startDate,startTime,location);
            remoteAppointments.add(appointment);
            System.out.println(appointmentId + " | " + title + " | " + startDate + " " +
                    startTime + " | " + author + " | " + lastDate +
                    " | " + lastTimestamp);
        }
        return remoteAppointments;
    }

    public static ObservableList<Appointment> getInPersonAppointments() throws  SQLException {
        ObservableList<Appointment> inPersonAppointments = FXCollections.observableArrayList();
        Connection connection = MakeConnection.getConnection();
        String selectStatement = "Select * FROM U07nke.appointment WHERE type = 'In-Person' ";
        makePreparedStatement(connection, selectStatement);
        PreparedStatement preparedStatement = getPreparedStatement();
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        while (resultSet.next()) {
            int appointmentId = resultSet.getInt("appointmentId");
            int customerId = resultSet.getInt("customerId");
            int userId = resultSet.getInt("userId");
            String title = resultSet.getString("title");
            String location = resultSet.getString("location");
            String type = resultSet.getString("type");
            LocalDate startDate = resultSet.getDate("start").toLocalDate();
            LocalTime startTime = resultSet.getTime("start").toLocalTime();
            String author = resultSet.getString("createdBy");
            LocalDate lastDate = resultSet.getDate("lastUpdate").toLocalDate();
            LocalDateTime lastTimestamp = resultSet.getTimestamp("lastUpdate").toLocalDateTime();

            Appointment appointment = new Appointment(appointmentId, customerId,userId,title,type,startDate,startTime,location);
            inPersonAppointments.add(appointment);
            System.out.println(appointmentId + " | " + title + " | " + startDate + " " +
                    startTime + " | " + author + " | " + lastDate +
                    " | " + lastTimestamp);
        }
        return inPersonAppointments;
    }

    public static ObservableList<Appointment> getConsultantReport(String id) throws SQLException {
        ObservableList<Appointment> userAppointments = FXCollections.observableArrayList();
        Connection connection = MakeConnection.getConnection();
        String selectStatement = "Select * FROM U07nke.appointment WHERE userId = ?";
        makePreparedStatement(connection, selectStatement); //create statement object
        PreparedStatement preparedStatement = getPreparedStatement();
        preparedStatement.setString(1, id);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        while (resultSet.next()) {
            int appointmentId = resultSet.getInt("appointmentId");
            int customerId = resultSet.getInt("customerId");
            int userId = resultSet.getInt("userId");
            String title = resultSet.getString("title");
            String location = resultSet.getString("location");
            String type = resultSet.getString("type");
            LocalDate startDate = resultSet.getDate("start").toLocalDate();
            LocalTime startTime = resultSet.getTime("start").toLocalTime();
            String author = resultSet.getString("createdBy");
            LocalDate lastDate = resultSet.getDate("lastUpdate").toLocalDate();
            LocalDateTime lastTimestamp = resultSet.getTimestamp("lastUpdate").toLocalDateTime();

            Appointment appointment = new Appointment(appointmentId, customerId,userId,title,type,startDate,startTime,location);
            userAppointments.add(appointment);
            System.out.println(appointmentId + " | " + title + " | " + startDate + " " +
                    startTime + " | " + author + " | " + lastDate +
                    " | " + lastTimestamp);
        }
        return userAppointments;
    }

    /**
     * modify Appointment object and convert it into SQl code and update database
     */
    public void modifyAppointment() throws SQLException {

    }

    /**
     * delete Appointment object from database
     * @param appointment
     */
    public static void deleteAppointment(Appointment appointment) throws SQLException {
        Connection connection = MakeConnection.getConnection();
        String deleteStatement = "DELETE FROM appointment WHERE appointmentId = ?";
        makePreparedStatement(connection, deleteStatement);
        PreparedStatement preparedStatement = getPreparedStatement();
        String appointmentId = Integer.toString(appointment.getAppointmentId());
        preparedStatement.setString(1, appointmentId);
        preparedStatement.execute();
        if (preparedStatement.getUpdateCount() > 0 ){
            System.out.println(preparedStatement.getUpdateCount() + " appointments deleted.");
        } else {
            System.out.println("no appointments deleted");
        }
    }
}
