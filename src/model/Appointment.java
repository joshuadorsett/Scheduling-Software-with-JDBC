package model;

import java.time.LocalDate;
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
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private LocalTime appointmentEndTime;
    private String appointmentLocation;

    public Appointment(int id, int customerId, int userId, String title, String type, LocalDate date, LocalTime time, LocalTime endTime, String location){
        this.appointmentId = id;
        this.associatedCustomerId = customerId;
        this.associatedUserId = userId;
        this.appointmentTitle = title;
        this.appointmentType = type;
        this.appointmentDate = date;
        this.appointmentTime = time;
        this.appointmentEndTime = endTime;
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

    public LocalDate getAppointmentDate(){
        return this.appointmentDate;
    }

    public LocalTime getAppointmentTime(){
        return this.appointmentTime;
    }

    public LocalTime getAppointmentEndTime() {
        return appointmentEndTime;
    }

    public String getAppointmentLocation(){
        return this.appointmentLocation;
    }

}

