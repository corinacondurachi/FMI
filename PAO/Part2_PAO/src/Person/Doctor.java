package Person;

import Person.Person;

import java.util.Objects;

public class Doctor extends Employee {

    private String speciality;
    private int consultationCost;
    private int shiftStart;
    private int shiftEnd;

    public Doctor(String lastName, String firstName, int age, String sex, String speciality, int consultationCost, int shiftStart, int shiftEnd) {
        super(lastName, firstName, age, sex);
        this.speciality = speciality;
        this.consultationCost = consultationCost;
        this.shiftStart = shiftStart;
        this.shiftEnd = shiftEnd;
    }

    public int getConsultationCost() {
        return consultationCost;
    }

    public int getShiftStart() {
        return shiftStart;
    }

    public int getShiftEnd() {
        return shiftEnd;
    }

    public String getSpeciality() {
        return speciality;
    }


    public void setConsultationCost(int consultationCost) {
        this.consultationCost = consultationCost;
    }

    public void setShiftStart(int shiftStart) {
        this.shiftStart = shiftStart;
    }

    public void setShiftEnd(int shiftEnd) {
        this.shiftEnd = shiftEnd;
    }

    @Override
    public String toString() {
        return super.toString() +
                "speciality='" + speciality + '\'' +
                ", consultationCost=" + consultationCost +
                ", shiftStart=" + shiftStart +
                ", shiftEnd=" + shiftEnd ;
    }



}
