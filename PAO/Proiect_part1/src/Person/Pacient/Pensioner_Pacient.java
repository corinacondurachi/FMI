package Person.Pacient;

public class Pensioner_Pacient extends Pacient {

    //Pensioners have a 50% discount


    public Pensioner_Pacient(String last_name, String first_name, int age, String sex, String doctor_first_name, String doctor_last_name ) {
        super(last_name, first_name, age, sex, doctor_first_name, doctor_last_name);
    }
}
