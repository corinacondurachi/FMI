package Person.Pacient;

import Person.Pacient.Pacient;
import Person.Person;

public class Adult_Pacient extends Pacient {


    //Adults pay the full price


    public Adult_Pacient(String last_name, String first_name, int age, String sex, String doctor_first_name, String doctor_last_name) {
        super(last_name, first_name, age, sex, doctor_first_name, doctor_last_name);
    }
}

