package utilities;

import dao.AppointmentDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;
import model.Appointment;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import static utilities.MakePreparedStatement.getPreparedStatement;
import static utilities.MakePreparedStatement.makePreparedStatement;

public class DateTimeUtils {
    public static StringConverter<LocalDate> setDateFormatConverter() {
        StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter =
                    DateTimeFormatter.ofPattern("dd-MM-yyyy");
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };
        return converter;
    }
    public static String toDbTimeZone(String dateToConvert) throws ParseException {
            ZoneId timeZoneId = ZoneId.systemDefault();
            DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            utcFormat.setTimeZone(TimeZone.getTimeZone("GMT+1"));
            DateFormat defaultTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            defaultTimeFormat.setTimeZone(TimeZone.getTimeZone(timeZoneId));
            return utcFormat.format(defaultTimeFormat.parse(dateToConvert));
    }
    public static boolean fifteenMinuteAlert() throws SQLException {
        for (Appointment a : AppointmentDaoImpl.getAllAppointments()) {
            LocalDateTime fifteenMinsLater = LocalDateTime.now().plusMinutes(15);
            System.out.println(fifteenMinsLater.toString());
            if (a.getStartTs().isBefore(fifteenMinsLater) && a.getStartTs().isAfter(LocalDateTime.now())) {
                System.out.println("appointment times in the next 15 minutes : "+a.getAppointmentTime().toString());
                return true;
            }
        }
        return false;
        }
    public static boolean overlaps(LocalDateTime start, LocalDateTime end) throws SQLException {
        for (Appointment a : AppointmentDaoImpl.getAllAppointments()) {
            if (a.getStartTs().isEqual(start)){
                return true;
            }
            System.out.println(a.getStartTs().toString());
        }
        return false;
    }


}
