package utilities;

import dao.AppointmentDaoImpl;
import javafx.util.StringConverter;
import model.Appointment;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

    static Fifteen fifteen = () -> LocalDateTime.now().plusMinutes(15);

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
    public static StringConverter<LocalDate> newConverter =
            new StringConverter<LocalDate> () {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                @Override
                public String toString(LocalDate date) {
                    if (date != null) {
                        return dateFormat.format(date);
                    } else {
                        return "";
                    }
                }
                @Override
                public LocalDate fromString(String string) {
                    if (string != null && !string.isEmpty()) {
                        return LocalDate.parse(string, dateFormat);
                    } else {
                        return null;
                    }
                }
            };
}
