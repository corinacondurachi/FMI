import Person.Assistant;
import Person.Doctor;
import Person.Pacient.Pacient;
import Person.Pacient.Prescription;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Gestiune {

    private final int nrMax;
    private int Index_doctors;
    ArrayList<Doctor> doctors;
    ArrayList<Appointment> appointments;
    ArrayList<Pacient> patients;
    ArrayList<Assistant> nurses;
    ArrayList<Prescription> prescriptions;

    public Gestiune(int nrMax) {
        if (nrMax > 0) {
            this.nrMax = nrMax;
            this.doctors = new ArrayList<Doctor>();
            nurses = new ArrayList<Assistant>();
            patients = new ArrayList<Pacient>();
            appointments = new ArrayList<Appointment>();
            prescriptions = new ArrayList<Prescription>();
        } else {
            throw new RuntimeException("The number is not positive");
        }
    }

    public void add_doctor(Doctor doctor) {
        doctors.add(doctor);
        //System.out.println("Doctor added: " + doctor);
    }

    public void show_doctors() {
        System.out.println("Doctors currently working at the clinic:");
        for (int i = 0; i < doctors.size() && (doctors.get(i) != null); i++) {
            System.out.println(doctors.get(i));
        }
    }

    public void show_doctors_speciality(String speciality) {
        System.out.println("Doctors having speciality " + speciality + " currently working at the clinic:");
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getSpeciality().equals(speciality)) {
                System.out.println(doctors.get(i));
            }
        }
    }

    public void show_doctor_working_hours(String last_name, String first_name) {
        System.out.println("Doctor " + last_name + " " + first_name + " is currently working between:");
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getFirst_name().equals(first_name) && doctors.get(i).getLast_name().equals(last_name)) {
                System.out.println(doctors.get(i).getShift_start() + " and " + doctors.get(i).getShift_end());
            }
        }
    }

    public int show_doctor_start_shift(String last_name, String first_name) {
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getFirst_name().equals(first_name) && doctors.get(i).getLast_name().equals(last_name)) {
                return doctors.get(i).getShift_start();
            }
        }
        return -1;
    }

    public int show_doctor_end_shift(String last_name, String first_name) {
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getFirst_name().equals(first_name) && doctors.get(i).getLast_name().equals(last_name)) {
                return doctors.get(i).getShift_end();
            }
        }
        return -1;
    }

    public void add_nurse(Assistant nurse) {
        nurses.add(nurse);
        // System.out.println("Nurse added: " + nurse);
    }

    public void show_nurses() {
        System.out.println("Nurses currently working at the clinic:");
        for (int i = 0; i < nurses.size(); i++) {
            System.out.println(nurses.get(i));
        }
    }

    public void show_patients() {
        System.out.println("Patients that have been to the clinic:");
        for (int i = 0; i < patients.size(); i++) {
            System.out.println(patients.get(i));
        }
    }

    public void add_appointment(Appointment appointment) {
        String doctor_first_name = appointment.getPacient().getDoctor_first_name();
        String doctor_last_name = appointment.getPacient().getDoctor_last_name();
        int start_shift = show_doctor_start_shift(doctor_last_name, doctor_first_name);
        int end_shift = show_doctor_end_shift(doctor_last_name, doctor_first_name);
        //verify if the name of the doctor is correct
        if (start_shift != -1 && end_shift != -1) {
            if (appointment.getTime() >= start_shift && appointment.getTime() <= end_shift - 1) {
                patients.add(appointment.getPacient());
                appointments.add(appointment);
                System.out.println("Appointment added: " + appointment);
            } else System.out.println("The appointment could not be set because it is outside of working hours");
        } else System.out.println("Name of the doctor is not correct");
    }


    public void show_appointments() {
        System.out.println("Currently the following appointments were made:");
        for (int i = 0; i < appointments.size(); i++) {
            System.out.println(appointments.get(i));
        }
    }


    public void delete_appointment(String first_name, String last_name, int date, int time) {
        boolean found = false;
        for (int i = 0; i < appointments.size(); i++)
            if (appointments.get(i).getPacient().getFirst_name().equals(first_name) && appointments.get(i).getPacient().getLast_name().equals(last_name)
                    && appointments.get(i).getDate() == date && appointments.get(i).getTime() == time) {
                appointments.remove(i);
                patients.remove(i);
                System.out.println("Appointment succesfully deleted");
                found = true;
            }
        if (found == false) {
            System.out.println("Appointment was not found");
        }
    }

    public void change_appointment(String first_name, String last_name, int date, int time, int new_date, int new_time) {
        boolean found = false;
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getPacient().getFirst_name().equals(first_name) && appointments.get(i).getPacient().getLast_name().equals(last_name)
                    && appointments.get(i).getDate() == date && appointments.get(i).getTime() == time) {
                String doctor_first_name = appointments.get(i).getPacient().getDoctor_first_name();
                String doctor_last_name = appointments.get(i).getPacient().getDoctor_last_name();
                int start_shift = show_doctor_start_shift(doctor_last_name, doctor_first_name);
                int end_shift = show_doctor_end_shift(doctor_last_name, doctor_first_name);
                //verify if the name of the doctor is correct
                if (start_shift != -1 && end_shift != -1) {
                    if (appointments.get(i).getTime() >= start_shift && appointments.get(i).getTime() < end_shift - 1) {
                        appointments.get(i).setDate(new_date);
                        appointments.get(i).setTime(new_time);
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
        if (found == false) {
            System.out.println("Appointment was not found");
        }
    }


    public void print_patient_appointments(String last_name, String first_name) {
        boolean found = false;
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getPacient().getFirst_name().equals(first_name) && appointments.get(i).getPacient().getLast_name().equals(last_name)) {
                System.out.println(appointments.get(i));
                found = true;
            }
        }
        if (found == false) {
            System.out.println("Patient was not found");
        }
    }

    public float consultation_cost(Pacient pacient) {
        String doc_first_name = pacient.getDoctor_first_name();
        String doc_last_name = pacient.getDoctor_last_name();
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getLast_name().equals(doc_last_name) && doctors.get(i).getFirst_name().equals(doc_first_name)) {
                return doctors.get(i).getConsultation_cost();
            }
        }
        return -1;
    }

    public void add_prescription(String first_name, String last_name, Prescription prescription) {
        boolean found = false;
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getFirst_name().equals(first_name) && patients.get(i).getLast_name().equals(last_name)) {
                prescriptions.add(prescription);
                System.out.println("Prescription added: " + prescription);
                found = true;
            }
        }
        if (found == false) {
            System.out.println("Pacient not found");
        }
    }

    public void show_clinic_info() {

        Office office = new Office(doctors.size(), "Bucharest", "Calea Floreasca nr 2A", doctors);
        System.out.println(office);
    }


}
