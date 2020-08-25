package utilities;

import javafx.util.StringConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;

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
            utcFormat.setTimeZone(TimeZone.getTimeZone("GMT+1:00"));
            DateFormat defaultTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            defaultTimeFormat.setTimeZone(TimeZone.getTimeZone(timeZoneId));
            return utcFormat.format(defaultTimeFormat.parse(dateToConvert));
    }
}
