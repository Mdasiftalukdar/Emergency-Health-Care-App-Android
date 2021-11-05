package com.example.EmergencyHealthcare.AppointmentSet;

public class AppointmentSetHelper {

    String name,workPlace,date,time,status;

    public AppointmentSetHelper(String name, String workPlace, String date, String time) {
        this.name = name;
        this.workPlace = workPlace;
        this.date = date;
        this.time = time;
        this.status= "not approved yet";

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getDate() {
        return date;
    }

    public void setdate(String date) {
        this.date = date;
    }

    public String gettime() {
        return time;
    }

    public void settime(String time) {
        this.time = time;
    }

    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status = status;
    }


}
