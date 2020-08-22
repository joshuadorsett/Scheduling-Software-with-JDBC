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
    private int associatedUserid;
    private String appointmentTitle;
    private String appointmentType;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String appointmentLocation;

    public Appointment(int id, int custId, int userId, String title, String type, LocalDate date, LocalTime time, String location){
        this.appointmentId = id;
        this.associatedCustomerId = custId;
        this.associatedUserid = userId;
        this.appointmentTitle = title;
        this.appointmentType = type;
        this.appointmentDate = date;
        this.appointmentTime = time;
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
        this.associatedUserid = id;
    }

    public int getAssociatedUserId(){
        return this.associatedUserid;
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
//        add code for converting default time into the UTC time in the Db
        this.appointmentTime = time;
    }

    public LocalTime getAppointmentTime(){
//        add code for converting this from the UTC time in the Db
//        to the system default time of the current computer (easier than you think)
        return this.appointmentTime;
    }


    public void setAppointmentLocation(String location){
        this.appointmentLocation = location;
    }

    public String getAppointmentLocation(){
        return this.appointmentLocation;
    }

}

