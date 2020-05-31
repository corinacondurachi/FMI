package Person.Patient;

import Person.Doctor;

public class StudentPatient extends Patient {

    // Students have 25% discount


    public StudentPatient(String lastName, String firstName, int age, String sex, Doctor doctor) {
        super(lastName, firstName, age, sex, doctor);
    }
}
