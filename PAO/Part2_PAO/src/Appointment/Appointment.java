package Appointment;

import Person.Patient.Patient;

public class Appointment {

    private Patient patient;
    private int date;
    private int time;

    public Appointment(Patient patient, int date, int time) {
        this.patient = patient;
        this.date = date;
        this.time = time;
    }

    public Patient getPatient() {
        return patient;
    }

    public int getDate() {
        return date;
    }

    public int getTime() {
        return time;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return   patient +
                ", date=" + date +
                ", time=" + time ;
    }
}
