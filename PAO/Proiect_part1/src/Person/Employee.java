package Person;

public abstract class Employee extends Person {
    private int id;

    public Employee(String last_name, String first_name, int age, String sex) {
        super(last_name, first_name, age, sex);
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
