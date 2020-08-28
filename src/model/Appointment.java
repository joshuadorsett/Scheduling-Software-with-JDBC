package model;

import java.time.LocalDateTime;

/**
 * creates an appointment object used to contain the data selected from the DB
 * @author joshuadorsett
 */
public class Appointment {
    private int appointmentId;
    private int customerId;
    private int userId;
    private String title;
    private String type;
    private String location;
    private LocalDateTime start;
    private LocalDateTime end;

    public Appointment(int appointmentId, int customerId, int userId, String title, String type, LocalDateTime start, LocalDateTime end, String location){
        this.appointmentId = appointmentId;
        this.customerId = customerId;
        this.userId = userId;
        this.title = title;
        this.type = type;
        this.start = start;
        this.end = end;
        this.location = location;
    }
    public int getAppointmentId() {
        return this.appointmentId;
    }

    public int getCustomerId(){
        return this.customerId;
    }

    public int getUserId(){
        return this.userId;
    }

    public String getTitle(){
        return this.title;
    }

    public String getLocation(){
        return this.location;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }
}

