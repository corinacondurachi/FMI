package Person.Pacient;

import java.util.Arrays;

public class Prescription {

    private String last_name_patient;
    private String first_name_patient;
    private String[] injections;
    private String[] pills;

    public Prescription(String last_name_patient, String first_name_patient, String[] injections, String[] pills) {
        this.last_name_patient = last_name_patient;
        this.first_name_patient = first_name_patient;
        this.injections = injections;
        this.pills = pills;
    }

    @Override
    public String toString() {
        return "Prescription " +
                "last_name_patient='" + last_name_patient + '\'' +
                ", first_name_patient='" + first_name_patient + '\'' +
                ", injections=" + Arrays.toString(injections) +
                ", pills=" + Arrays.toString(pills) +
                ' ';
    }
}
