# Clinic Administration

It is a programme written in Java designed to help with a clinic administration.

## Requirements
Java SE 13.0.1

## Describing the code
It provides different methods in order to update the clinic's activity, appointments, doctors.

**Classes**

 - Person (abstract)
 - Employee (abstract) extends Person
 - Patient
 - Pensioner_Patient extends Patient
 - Adult_Patient extends Patient
 - Student extends Patient
 - Prescription
 - Assistant extends Employee
 - Doctor extends Employee
 - Doctor_Comparator implements a comparator
 - Office
 - Appointment
 - Prescription
 - Gestiune - used to implement all methods 
 - Main - used to test all methods
 
 **Methods**.
 
  - public void add_doctor(Doctor doctor)  adds a new doctor
  - public  void  show_doctors() prints all doctors who work at the clinic
  - public void show_doctors_speciality (String speciality)  prints all doctors working at the clinic  and having speciality "speciality"
  - public void show_doctor_working_hours(String last_name, String first_name)  prints a doctor's schedule 
  - public int show_doctor_start_shift(String last_name, String first_name)  prints when a doctor's shift starts
  - public int show_doctor_end_shift(String last_name, String first_name)  prints when a doctor's shift ends
  - public void add_nurse(Assistant nurse)  adds a new doctor
  - public void show_nurses() prints all nurses who work at the clinic
  - public void show_patients() prints all patients that have ever been to the clinic
  - public void add_appointment(Appointment appointment) adds a new appointment
  - public void show_appointments() show all appointments made
  - public void delete_appointment(String first_name, String last_name, int date, int time) deletes an appointment
  - public void change_appointment(String first_name, String last_name, int date, int time, int new_date, int new_time) change the time for an existing appointment
  - public void print_patient_appointments(String last_name, String first_name) prints all appointments of a specific patient
  - public float consultation_cost( Patient patient) calculates the cost of the consultation (Adults pay full price, Students have a 25% discount and Pensioners have a 50% discount)
  - public void add_prescription(String first_name, String last_name, Prescription prescription) adds a prescription for a specific patient
  - public void show_clinic_info() shows information about the clinic such as address and doctors working there

