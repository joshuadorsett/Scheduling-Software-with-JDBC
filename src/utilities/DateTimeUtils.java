package utilities;

import dao.AppointmentDAO;
import dao.AppointmentDaoImpl;
import model.Appointment;

import java.sql.*;
import java.time.LocalDateTime;


public class DateTimeUtils {


    private static final AppointmentDAO aptDao = new AppointmentDaoImpl();


//    a method that returns true if there is an appointment within 15 minutes of login
//    it remains false for appointments that are now past tense
    public static boolean fifteenMinuteAlert() throws SQLException {

        LocalDateTime fifteenAfterNow = LocalDateTime.now().plusMinutes(15);

        LocalDateTime now = LocalDateTime.now();

        for (Appointment a : aptDao.getAll()) {
            if (a.getStart().isBefore(fifteenAfterNow) && a.getStart().isAfter(now)) {
                return true;
            }
        }
        return false;
    }


//    this returns true if there is an appointment in the desired appointment time
    public static boolean overlaps(LocalDateTime start, LocalDateTime end) throws SQLException {
        for (Appointment a : aptDao.getAll()) {
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
