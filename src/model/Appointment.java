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

    public void setAppointmentId(int id){
        this.appointmentId = id;
    }

    public int getAppointmentId() {
        return this.appointmentId;
    }

    public void setAssociatedCustomerId(int id){
        this.associatedCustomerId = id;
    }

    public int getAssociatedCustomerId(){
        return this.associatedCustomerId;
    }

    public void setAssociatedUserId(int id){
        this.associatedUserId = id;
    }

    public int getAssociatedUserId(){
        return this.associatedUserId;
    }

    public void setAppointmentTitle(String title){
        this.appointmentTitle = title;
    }

    public String getAppointmentTitle(){
        return this.appointmentTitle;
    }

    public void setAppointmentType(String type){
        this.appointmentType = type;
    }

    public String getAppointmentType(){
        return this.appointmentType;
    }

    public void setAppointmentDate(LocalDate date){
        this.appointmentDate = date;
    }

    public LocalDate getAppointmentDate(){
        return this.appointmentDate;
    }

    public void setAppointmentTime(LocalTime time){
        this.appointmentTime = time;
    }

    public LocalTime getAppointmentTime(){
        return this.appointmentTime;
    }

    public void setAppointmentEndTime(LocalTime appointmentEndTime) {
        this.appointmentEndTime = appointmentEndTime;
    }

    public LocalTime getAppointmentEndTime() {
        return appointmentEndTime;
    }

    public void setAppointmentLocation(String location){
        this.appointmentLocation = location;
    }

    public String getAppointmentLocation(){
        return this.appointmentLocation;
    }

}

