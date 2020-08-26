package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author joshuadorsett
 */
public class Appointment {
    private int appointmentId;
    private int associatedCustomerId;
    private int associatedUserId;
    private String appointmentTitle;
    private String appointmentType;
    private String appointmentLocation;
    private LocalDateTime start;
    private LocalDateTime end;

    public Appointment(int id, int customerId, int userId, String title, String type, LocalDateTime start, LocalDateTime end, String location){
        this.appointmentId = id;
        this.associatedCustomerId = customerId;
        this.associatedUserId = userId;
        this.appointmentTitle = title;
        this.appointmentType = type;
        this.start = start;
        this.end = end;
        this.appointmentLocation = location;
    }
    public int getAppointmentId() {
        return this.appointmentId;
    }

    public int getAssociatedCustomerId(){
        return this.associatedCustomerId;
    }

    public int getAssociatedUserId(){
        return this.associatedUserId;
    }

    public String getAppointmentTitle(){
        return this.appointmentTitle;
    }

    public String getAppointmentType(){
        return this.appointmentType;
    }

    public String getAppointmentLocation(){
        return this.appointmentLocation;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}

