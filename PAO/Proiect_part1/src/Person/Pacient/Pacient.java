package Person.Pacient;

import Person.Person;

import java.util.Objects;

public abstract class Pacient extends Person{

    private String doctor_first_name;
    private String doctor_last_name;

    public Pacient(String last_name, String first_name, int age, String sex, String doctor_first_name, String doctor_last_name) {
        super(last_name, first_name, age, sex);
        this.doctor_first_name = doctor_first_name;
        this.doctor_last_name = doctor_last_name;
    }

    public String getDoctor_first_name() {
        return doctor_first_name;
    }

    public String getDoctor_last_name() {
        return doctor_last_name;
    }


    @Override
    public String toString() {
        return super.toString() + " " +
                "doctor_first_name='" + doctor_first_name + '\'' +
                ", doctor_last_name='" + doctor_last_name + '\'' +
                " " ;
    }
}
