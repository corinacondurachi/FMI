package Management;

import Person.Doctor;
import Person.Patient.Patient;

import java.util.ArrayList;

public class DoctorsManagement {

   static ArrayList<Doctor> doctors;

    public DoctorsManagement() {
       doctors = new ArrayList<Doctor>();
    }

    public static ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
        //System.out.println("Doctor added: " + doctor);
    }

    public Doctor getDoctor(String FirstName, String LastName){
        for (int i = 0; i < doctors.size() && (doctors.get(i) != null); i++)
            if (doctors.get(i).getFirstName().equals(FirstName) && doctors.get(i).getLastName().equals(LastName))
                return doctors.get(i);
        return null;
    }

    public void showDoctors() {
        System.out.println("Doctors currently working at the clinic:");
        for (int i = 0; i < doctors.size() && (doctors.get(i) != null); i++) {
            System.out.println(doctors.get(i));
        }
    }

    public void showDoctorsSpeciality(String speciality) {
        System.out.println("Doctors having speciality " + speciality + " currently working at the clinic:");
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getSpeciality().equals(speciality)) {
                System.out.println(doctors.get(i));
            }
        }
    }

    public void showDoctorWorkingHours(String lastName, String firstName) {
        System.out.println("Doctor " + lastName + " " + firstName + " is currently working between:");
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getFirstName().equals(firstName) && doctors.get(i).getLastName().equals(lastName)) {
                System.out.println(doctors.get(i).getShiftStart() + " and " + doctors.get(i).getShiftEnd());
            }
        }
    }

    public static int showDoctorStartShift(String lastName, String firstName) {
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getFirstName().equals(firstName) && doctors.get(i).getLastName().equals(lastName)) {
                return doctors.get(i).getShiftStart();
            }
        }
        return -1;
    }

    public static int showDoctorEndShift(String lastName, String firstName) {
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getFirstName().equals(firstName) && doctors.get(i).getLastName().equals(lastName)) {
                return doctors.get(i).getShiftEnd();
            }
        }
        return -1;
    }

    public float consultationCost(Patient patient) {
        String docFirstName = patient.getDoctor().getFirstName();
        String docLastName = patient.getDoctor().getLastName();
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getLastName().equals(docLastName) && doctors.get(i).getFirstName().equals(docFirstName)) {
                return doctors.get(i).getConsultationCost();
            }
        }
        return -1;
    }

    public void showClinicInfo() {
        Office office = Office.getInstance(doctors.size(), "Bucharest", "Calea Floreasca nr 2A", doctors);
        System.out.println(office);
    }
}
