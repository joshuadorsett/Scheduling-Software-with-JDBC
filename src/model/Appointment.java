package model;

import java.time.LocalDateTime;

/**
 * creates an appointment object used to contain the data selected from the DB
 * @author joshuadorsett
 */

public class Appointment {


    private final int appointmentId;

    private final int customerId;

    private final int userId;

    private final String title;

    private final String type;

    private final String location;

    private final LocalDateTime start;

    private final LocalDateTime end;


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

