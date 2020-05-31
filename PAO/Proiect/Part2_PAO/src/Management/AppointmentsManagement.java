package Management;

import Appointment.Appointment;
import Person.Patient.Patient;
import Person.Patient.Prescription;

import java.util.ArrayList;
import java.util.Iterator;

public class AppointmentsManagement {

    ArrayList <Appointment> appointments;
    ArrayList <Patient> patients;
    ArrayList<Prescription> prescriptions;

    public AppointmentsManagement() {
        appointments = new ArrayList<Appointment>();
        patients = new ArrayList<Patient>();
        prescriptions = new ArrayList<Prescription>();
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public void addAppointment(Appointment appointment) {
        String doctorFirstName = appointment.getPatient().getDoctor().getFirstName();
        String doctorLastName = appointment.getPatient().getDoctor().getLastName();
        int startShift = DoctorsManagement.showDoctorStartShift(doctorLastName, doctorFirstName);
        int endShift = DoctorsManagement.showDoctorEndShift(doctorLastName, doctorFirstName);
        //verify if the name of the doctor is correct
        if (startShift != -1 && endShift != -1) {
            if (appointment.getTime() >= startShift && appointment.getTime() <= endShift - 1) {
                patients.add(appointment.getPatient());
                appointments.add(appointment);
                System.out.println("Appointment added: " + appointment);
            } else System.out.println("The appointment could not be set because it is outside of working hours");
        } else System.out.println("Name of the doctor is not correct");
    }


    public void showAppointments() {
        System.out.println("Currently the following appointments were made:");
        for (int i = 0; i < appointments.size(); i++) {
            System.out.println(appointments.get(i));
        }
    }

    public void deleteAppointment(String firstName, String lastName, int date, int time) {
        boolean found = false;
        Iterator<Appointment> iterator = appointments.iterator();
        while (iterator.hasNext()) {
            Appointment element = iterator.next();
            if (element.getPatient().getFirstName().equals(firstName) && element.getPatient().getLastName().equals(lastName)
                    && element.getDate() == date && element.getTime() == time){
                iterator.remove(); // can call remove on iterator
                found = true;
            System.out.println("Appointment succesfully deleted");
            }}
        if (!found) {
            System.out.println("Appointment was not found");
        }
    }


    public void changeAppointment(String firstName, String lastName, int date, int time, int newDate, int newTime) {
        boolean found = false;
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getPatient().getFirstName().equals(firstName) && appointments.get(i).getPatient().getLastName().equals(lastName)
                    && appointments.get(i).getDate() == date && appointments.get(i).getTime() == time) {
                String doctorFirstName = appointments.get(i).getPatient().getDoctor().getFirstName();
                String doctorLastName = appointments.get(i).getPatient().getDoctor().getLastName();
                int startShift = DoctorsManagement.showDoctorStartShift(doctorLastName, doctorFirstName);
                int endShift = DoctorsManagement.showDoctorEndShift(doctorLastName, doctorFirstName);
                //verify if the name of the doctor is correct
                if (startShift != -1 && endShift != -1) {
                    if (appointments.get(i).getTime() >= startShift && appointments.get(i).getTime() < endShift - 1) {
                        appointments.get(i).setDate(newDate);
                        appointments.get(i).setTime(newTime);
                        System.out.println("Appointment succesfully changed");
                        System.out.println(appointments.get(i));
                        found = true;
                    } else {
                        System.out.println("The time is outside doctor's schedule");

                    }
                } else {
                    System.out.println("Doctor's name is not corect");
                }

            }
        }
        if (!found) {
            System.out.println("Appointment was not found");
        }
    }

    public void printPatientAppointments(String lastName, String firstName) {
        boolean found = false;
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getPatient().getFirstName().equals(firstName) && appointments.get(i).getPatient().getLastName().equals(lastName)) {
                System.out.println(appointments.get(i));
                found = true;
            }
        }
        if (!found) {
            System.out.println("Patient was not found");
        }
    }

    public Patient getPatient(String lastName, String firstName) {
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getPatient().getFirstName().equals(firstName) && appointments.get(i).getPatient().getLastName().equals(lastName)) {
                return appointments.get(i).getPatient();
            }
        }
        System.out.println("Patient was not found");
        return null;
    }

    public void showPatients() {
        System.out.println("Patients that have been to the clinic:");
        for (int i = 0; i < patients.size(); i++) {
            System.out.println(patients.get(i));
        }
    }

    public void addPrescription(String firstName, String lastName, Prescription prescription) {
        boolean found = false;
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getFirstName().equals(firstName) && patients.get(i).getLastName().equals(lastName)) {
                prescriptions.add(prescription);
                System.out.println("Prescription added: " + prescription);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Pacient not found");
        }
    }
}
