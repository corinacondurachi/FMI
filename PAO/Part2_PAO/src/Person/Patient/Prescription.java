package Person.Patient;

import java.util.Arrays;

public class Prescription {

    private Patient patient;
    private String[] injections;
    private String[] pills;

    public Prescription(Patient patient, String[] injections, String[] pills) {
        this.patient = patient;
        this.injections = injections;
        this.pills = pills;
    }

    @Override
    public String toString() {
        return "Prescription " +
                "lastNamePatient='" + patient.getLastName() + '\'' +
                ", firstNamePatient='" + patient.getFirstName() + '\'' +
                ", injections=" + Arrays.toString(injections) +
                ", pills=" + Arrays.toString(pills) +
                ' ';
    }
}
