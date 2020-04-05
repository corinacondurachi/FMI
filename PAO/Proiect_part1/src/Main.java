import Person.Assistant;
import Person.Doctor;
import Person.Doctor_Comparator;
import Person.Pacient.*;

import java.io.File;
import java.util.Collections;
import java.util.Scanner;


public class Main {

    public static void add_doctors(String pathname, Gestiune clinic) {
        try {
            Scanner scanner = new Scanner(new File(pathname));
            while (scanner.hasNext()) {
                String last_name = scanner.next();
                String first_name = scanner.next();
                int age = scanner.nextInt();
                String sex = scanner.next();
                String speciality = scanner.next();
                int consultation_cost = scanner.nextInt();
                int shift_start = scanner.nextInt();
                int shift_end = scanner.nextInt();
                Doctor d = new Doctor(last_name, first_name, age, sex, speciality, consultation_cost, shift_start, shift_end);
                clinic.add_doctor(d);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("finally");
        }
    }

    public static void add_nurses(String pathname, Gestiune clinic) {
        try {
            Scanner scanner = new Scanner(new File(pathname));
            while (scanner.hasNext()) {
                String last_name = scanner.next();
                String first_name = scanner.next();
                int age = scanner.nextInt();
                String sex = scanner.next();
                String shift = scanner.next();
                Assistant a = new Assistant(last_name, first_name, age, sex, shift);
                clinic.add_nurse(a);
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("finally");
        }
    }

    public static void add_appointment(Gestiune clinic) {
        System.out.println("Add an appointment");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your last name");
        String last_name = scanner.nextLine();
        System.out.println("Enter your first name");
        String first_name = scanner.nextLine();
        System.out.println("Enter your sex (F/M)");
        String sex = scanner.nextLine();
        System.out.println("Enter your age");
        int age = scanner.nextInt();
        clinic.show_doctors();
        System.out.println("Enter the last_name of the doctor");
        String last_name_doctor = scanner.next();
        System.out.println("Enter the first_name of the doctor");
        String first_name_doctor = scanner.next();
        System.out.println("Enter the date");
        int date = scanner.nextInt();
        System.out.println("Enter the time");
        int time = scanner.nextInt();
        System.out.println("Enter an option \n" + "1. Adult \n" + "2. Student \n" + "3. Pensioner ");
        int option = scanner.nextInt();
        Pacient p = null;
        float price = 0;
        if (option == 1) {
            p = new Adult_Pacient(last_name, first_name, age, sex, first_name_doctor, last_name_doctor);
            price = clinic.consultation_cost(p);
        } else if (option == 2) {
            p = new Student(last_name, first_name, age, sex, first_name_doctor, last_name_doctor);
            price = clinic.consultation_cost(p) * 0.75f;
        } else if (option == 3) {
            p = new Pensioner_Pacient(last_name, first_name, age, sex, first_name_doctor, last_name_doctor);
            price = clinic.consultation_cost(p) * 0.5f;
        } else
            System.out.println("Invalid option");
        Appointment a = new Appointment(p, date, time);
        clinic.add_appointment(a);
        System.out.println("The cost of the consultation is " + price);
    }

    public static void change_appointment(Gestiune clinic) {
        System.out.println("Change an appointment");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your last name");
        String last_name = scanner.nextLine();
        System.out.println("Enter your first name");
        String first_name = scanner.nextLine();
        System.out.println("Enter the date of the original appointment");
        int date = scanner.nextInt();
        System.out.println("Enter the time of the original appointment");
        int time = scanner.nextInt();
        System.out.println("Enter the date of the new appointment");
        int new_date = scanner.nextInt();
        clinic.show_doctors();
        System.out.println("Enter the time of the new appointment");
        int new_time = scanner.nextInt();
        clinic.change_appointment(first_name, last_name, date, time, new_date, new_time);
    }

    public static void print_appointments(Gestiune clinic) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your last name");
        String last_name = scanner.nextLine();
        System.out.println("Enter your first name");
        String first_name = scanner.nextLine();
        System.out.println(last_name + " " + first_name + " has the following appointments: ");
        clinic.print_patient_appointments(last_name, first_name);
    }

    public static void delete_appointment(Gestiune clinic) {
        System.out.println("Delete an appointment");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your last name");
        String last_name = scanner.nextLine();
        System.out.println("Enter your first name");
        String first_name = scanner.nextLine();
        System.out.println("Enter the date of the appointment");
        int date = scanner.nextInt();
        System.out.println("Enter the time of the appointment");
        int time = scanner.nextInt();
        clinic.delete_appointment(first_name, last_name, date, time);
    }

    public static void add_prescription (Gestiune clinic) {
        System.out.println("Add a prescription for the following patient");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter patient's last name");
        String last_name = scanner.next();
        System.out.println("Enter patient's first name");
        String first_name = scanner.next();
        scanner.nextLine();
        System.out.println("Enter recommended injections");
        String line = scanner.nextLine();
        String[] injections = line.split(" ");
        System.out.println("Enter recommended pills");
        String line2 = scanner.nextLine();
        String[] pills = line2.split(" ");
        Prescription prescription = new Prescription(last_name, first_name, injections, pills);
        clinic.add_prescription(first_name,last_name, prescription);
    }


        public static void main(String[] args) {

        Gestiune Clinic = new Gestiune(100);
        add_doctors("E:\\Facultate\\Sem 2\\src\\Person\\doctors.txt", Clinic);
        add_nurses("E:\\Facultate\\Sem 2\\src\\Person\\assistants.txt", Clinic);
        //Sort the ArayList with doctors
        Collections.sort( Clinic.doctors, new Doctor_Comparator());

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
                    Clinic.show_doctors();
                    break;
                }
                case 2: {
                    System.out.println("Enter the speciality");
                    String speciality = scanner.next();
                    Clinic.show_doctors_speciality(speciality);
                    break;
                }
                case 3: {
                    System.out.println("Enter the doctor's last name: ");
                    String last_name = scanner.next();
                    System.out.println("Enter the doctor's first name: ");
                    String first_name = scanner.next();
                    Clinic.show_doctor_working_hours(last_name, first_name);
                    break;
                }
                case 4: {
                    Clinic.show_nurses();
                    break;
                }
                case 5: {
                    add_appointment(Clinic);
                    break;
                }
                case 6: {
                    change_appointment(Clinic);
                    break;
                }
                case 7: {
                    delete_appointment(Clinic);
                    break;
                }
                case 8: {
                    Clinic.show_appointments();
                    break;
                }
                case 9: {
                    print_appointments(Clinic);
                    break;
                }
                case 10:{
                    add_prescription(Clinic);
                    break;
                }
                case 11:{
                    Clinic.show_patients();
                    break;
                }
                case 12:{
                    Clinic.show_clinic_info();
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

