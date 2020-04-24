package Service;

import Appointment.Appointment;
import Management.AppointmentsManagement;
import Management.DoctorsManagement;
import Person.Patient.*;

import java.io.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;


public class WriteToFileService {


    private WriteToFileService() {
    }

    private static WriteToFileService instance;

    public static WriteToFileService getFile() {
        if (instance == null)
            instance = new WriteToFileService();
        return instance;
    }

    public void writeAppointmentFile(AppointmentsManagement appointmentsManagement, DoctorsManagement doctorsManagement, String path) {

        File csvFile = new File(path);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(csvFile, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Add an appointment");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your last name");
        String lastName = scanner.nextLine();
        System.out.println("Enter your first name");
        String firstName = scanner.nextLine();
        System.out.println("Enter your sex (F/M)");
        String sex = scanner.nextLine();
        System.out.println("Enter your age");
        int age = scanner.nextInt();
        System.out.println("Enter the last name of the doctor");
        String lastNameDoctor = scanner.next();
        System.out.println("Enter the first name of the doctor");
        String firstNameDoctor = scanner.next();
        System.out.println("Enter the date");
        int date = scanner.nextInt();
        System.out.println("Enter the time");
        int time = scanner.nextInt();
        System.out.println("Enter an option \n" + "1. Adult \n" + "2. Student \n" + "3. Pensioner ");
        int option = scanner.nextInt();
        Patient p = null;
        float price = 0;
        if (option == 1) {
            p = new AdultPatient(lastName, firstName, age, sex, doctorsManagement.getDoctor(firstNameDoctor, lastNameDoctor));
            System.out.println(doctorsManagement.getDoctor(firstNameDoctor, lastNameDoctor));
            price = doctorsManagement.consultationCost(p);
        } else if (option == 2) {
            p = new StudentPatient(lastName, firstName, age, sex, doctorsManagement.getDoctor(firstNameDoctor, lastNameDoctor));
            price = doctorsManagement.consultationCost(p) * 0.75f;
        } else if (option == 3) {
            p = new PensionerPatient(lastName, firstName, age, sex, doctorsManagement.getDoctor(firstNameDoctor, lastNameDoctor));
            price = doctorsManagement.consultationCost(p) * 0.5f;
        } else {
            System.out.println("Invalid option");
        }
        System.out.println("The cost of the consultation is " + price);
        Appointment a = new Appointment(p, date, time);
        appointmentsManagement.addAppointment(a);
        try {
            bw.append(lastName + "," + firstName + "," + age + "," + sex + "," + lastNameDoctor + "," + firstNameDoctor + "," +
                    date + "," + time + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter fw = new FileWriter(new File("E:\\Facultate\\Part2_PAO\\src\\Service\\ServiceAudit.csv"), true);
            Date d = new Date();
            //getTime() returns current time in milliseconds
            long t = d.getTime();
            //Passed the milliseconds to constructor of Timestamp class
            Timestamp ts = new Timestamp(t);
            fw.write(new StringBuilder().append("Appointment added on ").append(ts).toString());
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteAppointmentFile(String path, AppointmentsManagement appointmentsManagement) {

        File csvFile = new File(path);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(csvFile));
            System.out.println("Delete an appointment");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your last name");
            String lastName = scanner.nextLine();
            System.out.println("Enter your first name");
            String firstName = scanner.nextLine();
            System.out.println("Enter the date of the appointment");
            int date = scanner.nextInt();
            System.out.println("Enter the time of the appointment");
            int time = scanner.nextInt();
            appointmentsManagement.deleteAppointment(firstName, lastName, date, time);
            for (int i = 0; i < appointmentsManagement.getAppointments().size(); i++) {
                Appointment app = appointmentsManagement.getAppointments().get(i);
                try {
                        bw.append(app.getPatient().getLastName() + "," + app.getPatient().getFirstName() + "," +
                                app.getPatient().getAge() + "," + app.getPatient().getSex() + "," + app.getPatient().getDoctor().getLastName() +
                                "," + app.getPatient().getDoctor().getFirstName() + "," + app.getDate() + "," + app.getTime() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter fw = new FileWriter(new File("E:\\Facultate\\Part2_PAO\\src\\Service\\ServiceAudit.csv"), true);
            Date d = new Date();
            //getTime() returns current time in milliseconds
            long t = d.getTime();
            //Passed the milliseconds to constructor of Timestamp class
            Timestamp ts = new Timestamp(t);
            fw.write(new StringBuilder().append("Appointment deleted on ").append(ts).toString());
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeAppointmentFile(String path, AppointmentsManagement appointmentsManagement) {

        File csvFile = new File(path);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(csvFile));
            System.out.println("Change an appointment");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your last name");
            String lastName = scanner.nextLine();
            System.out.println("Enter your first name");
            String firstName = scanner.nextLine();
            System.out.println("Enter the date of the original appointment");
            int date = scanner.nextInt();
            System.out.println("Enter the time of the original appointment");
            int time = scanner.nextInt();
            System.out.println("Enter the date of the new appointment");
            int newDate = scanner.nextInt();
            System.out.println("Enter the time of the new appointment");
            int newTime = scanner.nextInt();
            appointmentsManagement.changeAppointment(firstName, lastName, date, time, newDate, newTime);
            for (int i = 0; i < appointmentsManagement.getAppointments().size(); i++) {
                Appointment app = appointmentsManagement.getAppointments().get(i);
                try {
                        bw.append(app.getPatient().getLastName() + "," + app.getPatient().getFirstName() + "," +
                                app.getPatient().getAge() + "," + app.getPatient().getSex() + "," + app.getPatient().getDoctor().getLastName() +
                                "," + app.getPatient().getDoctor().getFirstName() + "," + app.getDate() + "," + app.getTime() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter fw = new FileWriter(new File("E:\\Facultate\\Part2_PAO\\src\\Service\\ServiceAudit.csv"), true);
            Date d = new Date();
            //getTime() returns current time in milliseconds
            long t = d.getTime();
            //Passed the milliseconds to constructor of Timestamp class
            Timestamp ts = new Timestamp(t);
            fw.write(new StringBuilder().append("Appointment changed on ").append(ts).toString());
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writePrescriptionFile(String path, AppointmentsManagement appointmentsManagement) {

        File csvFile = new File(path);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(csvFile, true));
            System.out.println("Add a prescription for the following patient");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter patient's last name");
            String lastName = scanner.next();
            System.out.println("Enter patient's first name");
            String firstName = scanner.next();
            scanner.nextLine();
            System.out.println("Enter recommended injections");
            String line = scanner.nextLine();
            String[] injections = line.split(" ");
            System.out.println("Enter recommended pills");
            String line2 = scanner.nextLine();
            String[] pills = line2.split(" ");
            Prescription prescription = new Prescription(appointmentsManagement.getPatient(lastName, firstName), injections, pills);
            appointmentsManagement.addPrescription(firstName, lastName, prescription);
            try {
                bw.append(lastName + "," + firstName + "," + "injecions: " + Arrays.toString(injections) + "," + "pills: " +
                        Arrays.toString(pills) + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter fw = new FileWriter(new File("E:\\Facultate\\Part2_PAO\\src\\Service\\ServiceAudit.csv"), true);
            Date d = new Date();
            //getTime() returns current time in milliseconds
            long t = d.getTime();
            //Passed the milliseconds to constructor of Timestamp class
            Timestamp ts = new Timestamp(t);
            fw.write(new StringBuilder().append("Prescription added on ").append(ts).toString());
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



