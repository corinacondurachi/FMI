package Person.Patient;

import Person.Doctor;

public class PensionerPatient extends Patient {

    //Pensioners have a 50% discount


    public PensionerPatient(String lastName, String firstName, int age, String sex, Doctor doctor) {
        super(lastName, firstName, age, sex, doctor);
    }
}
