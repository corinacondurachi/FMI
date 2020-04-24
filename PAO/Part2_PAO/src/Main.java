import Management.AppointmentsManagement;
import Management.DoctorsManagement;
import Management.NursesManagement;
import Person.DoctorComparator;
import Person.Patient.*;
import Service.ReadFromFileService;
import Service.WriteToFileService;

import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static DoctorsManagement readDoctors(String pathname) {
        ReadFromFileService file = ReadFromFileService.getFile();
        DoctorsManagement doctorsManagement = file.readDoctors(pathname);
        return doctorsManagement;
    }

    public static NursesManagement readNurses(String pathname) {
        ReadFromFileService file = ReadFromFileService.getFile();
        NursesManagement nursesManagement = file.readNurses(pathname);
        return nursesManagement;
    }

    public static AppointmentsManagement readAppointments(String pathname) {

        ReadFromFileService file = ReadFromFileService.getFile();
        AppointmentsManagement appointmentsManagement = file.readAppointments(pathname);
        return appointmentsManagement;
    }

    public static void writeAppointment(AppointmentsManagement appointmentsManagement, DoctorsManagement doctorsManagement, String pathname) {

        WriteToFileService file = WriteToFileService.getFile();
        file.writeAppointmentFile(appointmentsManagement, doctorsManagement, pathname);
    }

    public static void changeAppointment(String pathname, AppointmentsManagement appointmentsManagement) {

        WriteToFileService file = WriteToFileService.getFile();
        file.changeAppointmentFile(pathname, appointmentsManagement);
    }

    public static void printAppointments(AppointmentsManagement appointmentsManagement) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your last name");
        String lastName = scanner.nextLine();
        System.out.println("Enter your first name");
        String firstName = scanner.nextLine();
        System.out.println(lastName + " " + firstName + " has the following appointments: ");
        appointmentsManagement.printPatientAppointments(lastName, firstName);
    }

    public static void deleteAppointment(String pathname, AppointmentsManagement appointmentsManagement) {

        WriteToFileService file = WriteToFileService.getFile();
        file.deleteAppointmentFile(pathname, appointmentsManagement);
    }

    public static void writePrescription(String path, AppointmentsManagement appointmentsManagement) {

        WriteToFileService file = WriteToFileService.getFile();
        file.writePrescriptionFile(path,appointmentsManagement);
    }

    public static void main(String[] args) {

        DoctorsManagement doctorsManagement = new DoctorsManagement();
        NursesManagement nursesManagement = new NursesManagement();
        AppointmentsManagement appointmentsManagement = new AppointmentsManagement();
        String PATH = "E:\\Facultate\\Part2_PAO\\src\\Data\\";
        doctorsManagement = readDoctors(PATH + "doctors.csv");
        nursesManagement = readNurses(PATH + "assistants.csv");
        appointmentsManagement = readAppointments(PATH + "appointments.csv");

        //Sort the ArayList with doctors
        Collections.sort(doctorsManagement.getDoctors(), new DoctorComparator());

        System.out.println("Enter an option \n 1. Show doctors that work at the clinic \n 2. Show doctors that have the following speciality \n" +
                " 3. Show a doctor working hours: \n 4. Show nurses that work at the clinic \n 5. Add an appointment \n" +
                " 6. Change an appointment \n 7. Delete an appointment  \n 8. Print the appointments that were set\n 9. Print appointments of a specific pacient" +
                "\n 10. Add a prescription for a patient \n 11. Show patients that have been to the clinic \n 12. Show information about the clinic \n" +
                " 13. Exit");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        while (option != 13) {
            switch (option) {
                case 1: {
                    doctorsManagement.showDoctors();
                    break;
                }
                case 2: {
                    System.out.println("Enter the speciality");
                    String speciality = scanner.next();
                    doctorsManagement.showDoctorsSpeciality(speciality);
                    break;
                }
                case 3: {
                    System.out.println("Enter the doctor's last name: ");
                    String lastName = scanner.next();
                    System.out.println("Enter the doctor's first name: ");
                    String firstName = scanner.next();
                    doctorsManagement.showDoctorWorkingHours(lastName, firstName);
                    break;
                }
                case 4: {
                    nursesManagement.showNurses();
                    break;
                }
                case 5: {
                    writeAppointment(appointmentsManagement, doctorsManagement, PATH + "appointments.csv");
                    break;
                }
                case 6: {
                    changeAppointment(PATH + "appointments.csv",appointmentsManagement);
                    break;
                }
                case 7: {
                    deleteAppointment(PATH + "appointments.csv", appointmentsManagement );
                    break;
                }
                case 8: {
                    appointmentsManagement.showAppointments();
                    break;
                }
                case 9: {
                    printAppointments(appointmentsManagement);
                    break;
                }
                case 10: {
                    writePrescription(PATH + "prescriptions.csv" , appointmentsManagement);
                    break;
                }
                case 11: {
                    appointmentsManagement.showPatients();
                    break;
                }
                case 12: {
                    doctorsManagement.showClinicInfo();
                    break;
                }
                default: {
                    System.out.println("Option is invalid ");
                    break;
                }
            }
            System.out.println("Enter an option \n 1. Show doctors that work at the clinic \n 2. Show doctors that have the following speciality \n" +
                    " 3. Show a doctor working hours: \n 4. Show nurses that work at the clinic \n 5. Add an appointment \n" +
                    " 6. Change an appointment \n 7. Delete an appointment  \n 8. Print the appointments that were set\n 9. Print appointments of a specific pacient" +
                    "\n 10. Add a prescription for a patient \n 11. Show patients that have been to the clinic \n 12. Show information about the clinic \n" +
                    " 13. Exit");
            option = scanner.nextInt();

        }
   }
}

