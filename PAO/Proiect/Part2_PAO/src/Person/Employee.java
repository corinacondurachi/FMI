package Person;

public abstract class Employee extends Person {
    private int id;

    public Employee(String lastName, String firstName, int age, String sex) {
        super(lastName, firstName, age, sex);
        id = hashCode();
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                " " + super.toString();
    }
}
