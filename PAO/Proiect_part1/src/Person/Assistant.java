package Person;

public class Assistant extends Employee {
    private String shift;

    public Assistant(String last_name, String first_name, int age, String sex, String shift) {
        super(last_name, first_name, age, sex);
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


