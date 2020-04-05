import Person.Doctor;
import Person.Pacient.Pacient;

import java.util.Objects;

public class Appointment {

    private Pacient pacient;
    private int date;
    private int time;

    public Appointment(Pacient pacient, int date, int time) {
        this.pacient = pacient;
        this.date = date;
        this.time = time;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public int getDate() {
        return date;
    }

    public int getTime() {
        return time;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return   pacient +
                ", date=" + date +
                ", time=" + time ;
    }
}
