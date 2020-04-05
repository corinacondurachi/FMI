package Person;

public abstract class Person {

    private String last_name;
    private String first_name;
    private int age;
    private String sex;

    public Person(String last_name, String first_name, int age, String sex) {
        this.last_name = last_name;
        this.first_name = first_name;
        this.age = age;
        this.sex = sex;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return  "last_name='" + last_name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' ;
    }
}
