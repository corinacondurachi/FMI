import Person.Doctor;

import java.util.ArrayList;
import java.util.Arrays;

public class Office {

    private int nr_doctors;
    private String city;
    private String address;
    private ArrayList<Doctor> doctors;;

    public Office(int nr_doctors, String city, String address, ArrayList<Doctor> doctors) {
        this.nr_doctors = nr_doctors;
        this.city = city;
        this.address = address;
        this.doctors = doctors;
    }

    public int getNr_doctors() {
        return nr_doctors;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public void setNr_doctors(int nr_doctors) {
        this.nr_doctors = nr_doctors;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDoctors(ArrayList<Doctor> doctors) {
        this.doctors = doctors;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Doctor doctor:doctors) {
            sb.append(doctor).append("\n");
        }
        return "Clinic Imperial has " + nr_doctors +
                " doctors and is situated in " + city +
                ", Address: " + address +
                ". \nThe following doctors are working here: \n" + sb.toString();
    }
}
