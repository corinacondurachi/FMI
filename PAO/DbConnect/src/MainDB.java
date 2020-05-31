import java.util.Scanner;

public class MainDB {

    public static void main(String[] args) {
        String host = "jdbc:mysql://localhost:3306/clinic?serverTimezone=UTC";
        String username = "root";
        String pass = "corina99";

        System.out.println("Enter an option \n 1. Add a doctor  \n 2. Show doctors that work at the clinic\n 3. Update a doctor's schedule \n" +
                "4. Delete a doctor \n 5. Add an appointment \n 6. Show appointments that were set \n 7. Update the date and time of an appointment \n" +
                " 8. Delete an appointment \n 9. Add a nurse \n 10. Show nurses that work at the clinic \n 11. Update a nurse's schedule \n "  +
                " 12. Delete a nurse  \n 13. Add a prescirption\n  14. Show prescriptions that were made \n 15. Update a prescription " +
                "16. Delete a prescription \n 17. Exit");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        while (option != 17) {
            switch (option) {
                case 1: {
                    doctorsDB.addDoctor(host, username, pass);
                    break;
                }
                case 2: {
                    doctorsDB.showDoctors(host,username,pass);
                    break;
                }
                case 3: {
                    doctorsDB.updateDoctor(host, username, pass);
                    break;
                }
                case 4: {
                    doctorsDB.deleteDoctor(host,username,pass);
                    break;
                }
                case 5: {
                    appointmentsDB.addAppointment(host, username, pass);
                    break;
                }
                case 6: {
                    appointmentsDB.showAppointments(host,username,pass);
                    break;
                }
                case 7: {
                    appointmentsDB.updateAppointment(host, username, pass);
                    break;
                }
                case 8: {
                    appointmentsDB.deleteAppointment(host,username,pass);
                    break;
                }
                case 9: {
                    nursesDB.addNurse(host, username, pass);
                    break;
                }
                case 10: {
                    nursesDB.showNurses(host,username,pass);
                    break;
                }
                case 11: {
                    nursesDB.updateNurse(host, username, pass);
                    break;
                }
                case 12: {
                    nursesDB.deleteNurse(host,username,pass);
                    break;
                }
                case 13: {
                    prescriptionsDB.addPrescription(host, username, pass);
                    break;
                }
                case 14: {
                    prescriptionsDB.showPrescriptions(host,username,pass);
                    break;
                }
                case 15: {
                    prescriptionsDB.updatePrescription(host, username, pass);
                    break;
                }
                case 16: {
                    prescriptionsDB.deletePrescription(host,username,pass);
                    break;
                }
                default: {
                    System.out.println("Option is invalid ");
                    break;
                }
            }
            System.out.println("Enter an option \n 1. Add a doctor  \n 2. Show doctors that work at the clinic\n 3. Update a doctor's schedule \n" +
                    "4. Delete a doctor \n 5. Add an appointment \n 6. Show appointments that were set \n 7. Update the date and time of an appointment \n" +
                    " 8. Delete an appointment \n 9. Add a nurse \n 10. Show nurses that work at the clinic \n 11. Update a nurse's schedule \n "  +
                    " 12. Delete a nurse  \n 13. Add a prescirption\n  14. Show prescriptions that were made \n 15. Update a prescription " +
                    "16. Delete a prescription \n 17. Exit");
            option = scanner.nextInt();

        }

    }
}
