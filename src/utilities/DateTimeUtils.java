package utilities;

import dao.AppointmentDaoImpl;
import javafx.util.StringConverter;
import model.Appointment;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
//    Lambda Expression #1
//    a Lambda expression to to calculate 15 minutes from the current time
    static Fifteen fifteen = () -> LocalDateTime.now().plusMinutes(15);

//    a method that returns true if there is an appointment within 15 minutes of login
//    it remains false for appointments that have are now past tense
    public static boolean fifteenMinuteAlert() throws SQLException {
        for (Appointment a : AppointmentDaoImpl.getAllAppointments()) {
            System.out.println(fifteen.fifteen());
            if (a.getStart().isBefore(fifteen.fifteen()) && a.getStart().isAfter(LocalDateTime.now())) {
                System.out.println("appointment times in the next 15 minutes : "+a.getStart().toString());
                return true;
            }
        }
        return false;
    }

//    this returns true if there is an appointment in the desired appointment time
    public static boolean overlaps(LocalDateTime start, LocalDateTime end) throws SQLException {
        for (Appointment a : AppointmentDaoImpl.getAllAppointments()) {
            if (a.getStart().isEqual(start))
                return true;
            if (a.getEnd().isEqual(end))
                return true;
            if (a.getEnd().isBefore(end) && a.getEnd().isAfter(start))
                return true;
            if (a.getStart().isAfter(start) && a.getStart().isBefore(end))
                return true;
        }
        return false;
    }
}
