package Person.Patient;

import Person.Doctor;
import Person.Person;


public abstract class Patient extends Person {

    private Doctor doctor;
    private float price;

    public Patient(String lastName, String firstName, int age, String sex, Doctor doctor) {
        super(lastName, firstName, age, sex);
        this.doctor = doctor;
    }


    @Override
    public String toString() {
        return  super.toString() + " " +
                "doctorFirstName='" + doctor.getFirstName() + '\'' +
                ", doctorLastName='" + doctor.getLastName() + '\'' +
                " " ;
    }

    public Doctor getDoctor() {
        return doctor;
    }
}

