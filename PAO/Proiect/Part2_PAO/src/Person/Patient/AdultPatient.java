package Person.Patient;

import Person.Doctor;

public class AdultPatient extends Patient {


    //Adults pay the full price


    public AdultPatient(String lastName, String firstName, int age, String sex, Doctor doctor) {
        super(lastName, firstName, age, sex, doctor);
    }
}

