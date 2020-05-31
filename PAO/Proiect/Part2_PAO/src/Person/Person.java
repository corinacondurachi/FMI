package Person;

public abstract class Person {

    private String lastName;
    private String firstName;
    private int age;
    private String sex;

    public Person(String lastName, String firstName, int age, String sex) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.sex = sex;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }


    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return  "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' ;
    }
}
