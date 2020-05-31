package Management;

import Person.Doctor;

import java.util.ArrayList;
import java.util.Arrays;

public class Office {

    private static int nrDoctors;
    private static String city;
    private static String address;
    private static ArrayList<Doctor> doctors;


    protected Office(int nrDoctors, String city, String address, ArrayList<Doctor> doctors) {
        this.nrDoctors = nrDoctors;
        this.city = city;
        this.address = address;
        this.doctors = doctors;
    }

    private static Office _instance = null;
    public static Office getInstance(int nrDoctors, String city, String address, ArrayList<Doctor> doctors){
        if(_instance == null) {
            _instance = new Office(nrDoctors,  city,  address, doctors);
        }
        return _instance;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Doctor doctor:doctors) {
            sb.append(doctor).append("\n");
        }
        return "Clinic Imperial has " + nrDoctors +
                " doctors and is situated in " + city +
                ", Address: " + address +
                ". \nThe following doctors are working here: \n" + sb.toString();
    }
}
