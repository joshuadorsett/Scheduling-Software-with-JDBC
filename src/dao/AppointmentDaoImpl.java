package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import utilities.MakeConnection;
import utilities.MakePreparedStatement;

import java.sql.*;
import java.text.ParseException;
import java.time.*;

import static utilities.MakePreparedStatement.*;

/**
 * This is the DataBase Access Object for the appointment table
 * @author joshuadorsett
 */
public class AppointmentDaoImpl {
//    creates a prepared statement object and then uses variables from text fields to insert into SQL.
    public static void addAppointment(String customerId, String title,
                                      String location, String type, LocalDateTime start, LocalDateTime end) throws SQLException {
        Connection connection = MakeConnection.getConnection();
        String insertStatement = "INSERT INTO appointment(customerId,userId,title,description,"+
                "location,contact,type,url,start,end,createDate,createdBy,lastUpdateBy)" +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        MakePreparedStatement.makePreparedStatement(connection, insertStatement);
        PreparedStatement preparedStatement = MakePreparedStatement.getPreparedStatement();

        LocalDateTime localDate = LocalDateTime.now();
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
        preparedStatement.setTimestamp(9, Timestamp.valueOf(start));
        preparedStatement.setTimestamp(10, Timestamp.valueOf(end));
        preparedStatement.setTimestamp(11, Timestamp.valueOf(localDate));
        preparedStatement.setString(12, createdBy);
        preparedStatement.setString(13, lastUpdateBy);

        preparedStatement.execute();


        String startString = start.toString();
        if (preparedStatement.getUpdateCount() > 0 ){
            System.out.println(preparedStatement.getUpdateCount() + " appointment added for " +startString);
        } else {
            System.out.println("appointment was not added");
        }
    }

//    uses a prepared statement to make an update statement with variables from the textfield
    public static void modifyAppointment(String customerId, String title,
                                         String location, String type, LocalDateTime start, LocalDateTime end, String appointmentId) throws SQLException, ParseException {
        Connection connection = MakeConnection.getConnection();
        String updateStatement = "UPDATE appointment SET customerId = ?, title = ?, location = ?, type = ?, start = ?, end = ? WHERE appointmentId = ?;";
        MakePreparedStatement.makePreparedStatement(connection, updateStatement);
        PreparedStatement preparedStatement = MakePreparedStatement.getPreparedStatement();

        preparedStatement.setString(1,customerId);
        preparedStatement.setString(2,title);
        preparedStatement.setString(3,location);
        preparedStatement.setString(4,type);
        preparedStatement.setTimestamp(5, Timestamp.valueOf(start));
        preparedStatement.setTimestamp(6, Timestamp.valueOf(end));
        preparedStatement.setString(7,appointmentId);

        preparedStatement.execute();

        String startString = start.toString();
        if (preparedStatement.getUpdateCount() > 0 ){
            System.out.println(preparedStatement.getUpdateCount() + " appointment updated for " +startString);
        } else {
            System.out.println("appointment not updated");
        }

    }

//    this creates a select statement to return all appointments as an observable list
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
            LocalDateTime start = resultSet.getTimestamp("start").toLocalDateTime();
            LocalDateTime end = resultSet.getTimestamp("end").toLocalDateTime();

            Appointment appointment = new Appointment(appointmentId, customerId,userId,title,type,start,end,location);
            allAppointments.add(appointment);
            System.out.println(appointmentId + " | " + title + " at " + start );
        }
        return allAppointments;
    }

//    this creates a select statement to return appointments this month as an observable list
    public static ObservableList<Appointment> getMonthlyAppointments() throws SQLException {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        Connection connection = MakeConnection.getConnection(); //get reference to connection object
        String selectStatement = "Select * FROM U07nke.appointment WHERE start BETWEEN (LAST_DAY(NOW())+ INTERVAL 1 DAY - interval 1 month) AND (LAST_DAY(NOW())+ INTERVAL 1 DAY)";
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
            LocalDateTime start = resultSet.getTimestamp("start").toLocalDateTime();
            LocalDateTime end = resultSet.getTimestamp("end").toLocalDateTime();

            Appointment appointment = new Appointment(appointmentId, customerId,userId,title,type,start,end,location);
            allAppointments.add(appointment);
            System.out.println(appointmentId + " | " + title + " at " + start );
        }
        return allAppointments;
    }

    //    this creates a select statement to return weekly appointments as an observable list
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
            LocalDateTime start = resultSet.getTimestamp("start").toLocalDateTime();
            LocalDateTime end = resultSet.getTimestamp("end").toLocalDateTime();

            Appointment appointment = new Appointment(appointmentId, customerId,userId,title,type,start,end,location);
            allAppointments.add(appointment);
            System.out.println(appointmentId + " | " + title + " at " + start );
        }
        return allAppointments;
    }

//    this creates a select statement to return all remote appointments as an observable list
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
            LocalDateTime start = resultSet.getTimestamp("start").toLocalDateTime();
            LocalDateTime end = resultSet.getTimestamp("end").toLocalDateTime();

            Appointment appointment = new Appointment(appointmentId, customerId,userId,title,type,start,end,location);
            remoteAppointments.add(appointment);
            System.out.println(appointmentId + " | " + title + " at " + start );
        }
        return remoteAppointments;
    }

//    this creates a select statement to return all In-Person appointments as an observable list
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
            LocalDateTime start = resultSet.getTimestamp("start").toLocalDateTime();
            LocalDateTime end = resultSet.getTimestamp("end").toLocalDateTime();

            Appointment appointment = new Appointment(appointmentId, customerId,userId,title,type,start,end,location);
            inPersonAppointments.add(appointment);
            System.out.println(appointmentId + " | " + title + " at " + start );

        }
        return inPersonAppointments;
    }

    //    this creates a select statement to return all appointments for each seperate consultatnt as an observable list
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
            LocalDateTime start = resultSet.getTimestamp("start").toLocalDateTime();
            LocalDateTime end = resultSet.getTimestamp("end").toLocalDateTime();

            Appointment appointment = new Appointment(appointmentId, customerId,userId,title,type,start,end,location);
            userAppointments.add(appointment);
            System.out.println(appointmentId + " | " + title + " at " + start );

        }
        return userAppointments;
    }

//            this creates a delete statement that deletes the appointment selected in param
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
