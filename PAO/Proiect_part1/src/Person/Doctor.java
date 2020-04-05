package Person;

import Person.Person;

import java.util.Objects;

public class Doctor extends Employee {

    private String speciality;
    private int consultation_cost;
    private int shift_start;
    private int shift_end;

    public Doctor(String last_name, String first_name, int age, String sex, String speciality, int consultation_cost, int shift_start, int shift_end) {
        super(last_name, first_name, age, sex);
        this.speciality = speciality;
        this.consultation_cost = consultation_cost;
        this.shift_start = shift_start;
        this.shift_end = shift_end;
    }

    public int getShift_start() {
        return shift_start;
    }

    public int getShift_end() {
        return shift_end;
    }

    public String getSpeciality() {
        return speciality;
    }

    public int getConsultation_cost() {
        return consultation_cost;
    }

    public void setConsultation_cost(int consultation_cost) {
        this.consultation_cost = consultation_cost;
    }

    @Override
    public String toString() {
        return super.toString() +
                "speciality='" + speciality + '\'' +
                ", consultation_cost=" + consultation_cost +
                ", shift_start=" + shift_start +
                ", shift_end=" + shift_end ;
    }


    public void setShift_start(int shift_start) {
        this.shift_start = shift_start;
    }

    public void setShift_end(int shift_end) {
        this.shift_end = shift_end;
    }

}
