package Person;

public class Nurse extends Employee {
    private String shift;

    public Nurse(String lastName, String firstName, int age, String sex, String shift) {
        super(lastName, firstName, age, sex);
        this.shift = shift;
    }

    public String getShift() {
        return shift;
    }

    @Override
    public String toString() {
        return super.toString() +
                "shift='" + shift + '\'' +
                " " ;
    }
}


