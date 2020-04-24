package Service;

import Appointment.Appointment;
import Management.AppointmentsManagement;
import Management.DoctorsManagement;
import Management.NursesManagement;
import Person.Doctor;
import Person.Nurse;
import Person.Patient.AdultPatient;
import Person.Patient.Patient;

import java.io.*;

public class ReadFromFileService {

    DoctorsManagement doctorsManagement = new DoctorsManagement();
    NursesManagement nursesManagement = new NursesManagement();
    AppointmentsManagement appointmentsManagement = new AppointmentsManagement();

    private ReadFromFileService() {
    }

    private static ReadFromFileService instance;

    public static ReadFromFileService getFile() {
        if (instance == null)
            instance = new ReadFromFileService();
        return instance;
    }

    public DoctorsManagement readDoctors(String path) {

        String csvFile = path;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                var element = line.split(cvsSplitBy);
                String lastName = element[0];
                String firstName = element[1];
                int age = Integer.parseInt(element[2]);
                String sex = element[3];
                String speciality = element[4];
                int consultation_cost = Integer.parseInt(element[5]);
                int shiftStart = Integer.parseInt(element[6]);
                int shiftEnd = Integer.parseInt(element[7]);
                Doctor d = new Doctor(lastName, firstName, age, sex, speciality, consultation_cost, shiftStart, shiftEnd);
                doctorsManagement.addDoctor(d);
            }
            return doctorsManagement;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public NursesManagement readNurses(String path) {

        String csvFile = path;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                var element = line.split(cvsSplitBy);
                String lastName = element[0];
                String firstName = element[1];
                int age = Integer.parseInt(element[2]);
                String sex = element[3];
                String shift = element[4];
                Nurse n = new Nurse(lastName, firstName, age, sex, shift);
                nursesManagement.addNurse(n);
            }
            return nursesManagement;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public AppointmentsManagement readAppointments(String path) {
        String csvFile = path;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                var element = line.split(cvsSplitBy);
                String lastName = element[0];
                String firstName = element[1];
                int age = Integer.parseInt(element[2]);
                String sex = element[3];
                String lastNameDoctor = element[4];
                String firstNameDoctor = element[5];
                int date = Integer.parseInt(element[6]);
                int time = Integer.parseInt(element[7]);
                Patient p = new AdultPatient(lastName, firstName, age, sex, doctorsManagement.getDoctor(firstNameDoctor, lastNameDoctor)) ;
                Appointment a = new Appointment(p, date, time);
                appointmentsManagement.addAppointment(a);
            }
            return appointmentsManagement;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



}


