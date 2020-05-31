import java.sql.*;
import java.util.Scanner;

public class prescriptionsDB {

    public static void addPrescription(String host, String username, String pass) {

        Connection con = null;
        try {
            con = DriverManager.getConnection(host, username, pass);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter patient's last name");
            String lastName = scanner.nextLine();
            System.out.println("Enter patient's first name");
            String firstName = scanner.nextLine();
            System.out.println("Enter recommended injections");
            String injections = scanner.nextLine();
            System.out.println("Enter recommended pills");
            String pills = scanner.nextLine();

            // the mysql insert statement
            String query = " insert into prescriptions (lastName, firstName, injections, pills )"
                    + " values (?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, lastName);
            preparedStmt.setString (2, firstName);
            preparedStmt.setString   (3, injections);
            preparedStmt.setString(4, pills);

            // execute the preparedstatement
            preparedStmt.execute();
            con.close();
            System.out.println("Doctor added");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void showPrescriptions(String host, String username, String pass) {
        try {
            Connection con = DriverManager.getConnection(host, username, pass);
            Statement stat = con.createStatement();
            String sql = "select * from prescriptions";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String injections = rs.getString("injections");
                String pills = rs.getString("pills");

                String p =firstName + " " + lastName + " injections: " + injections + " pills: " + pills;
                System.out.println(p);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updatePrescription(String host, String username, String pass) {

        Connection con = null;
        try {
            con = DriverManager.getConnection(host, username, pass);
            con.setAutoCommit(false);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter patient's last name");
            String lastName = scanner.nextLine();
            System.out.println("Enter patient's first name");
            String firstName = scanner.nextLine();
            System.out.println("Enter patient's recommended injections");
            String injections = scanner.nextLine();
            System.out.println("Enter patient's recommended pills");
            String pills = scanner.nextLine();

            // create the java mysql update preparedstatement
            String query = "update clinic.prescriptions set injections = ?, pills = ? where lastName = ? and firstName = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString   (1, injections);
            preparedStmt.setString   (2, pills);
            preparedStmt.setString(3, lastName);
            preparedStmt.setString(4, firstName);

            // execute the java preparedstatement
            preparedStmt.executeUpdate();
            con.commit();

            System.out.println("Doctor updated");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deletePrescription (String host, String username, String pass) {

        Connection con = null;
        try {
            con = DriverManager.getConnection(host, username, pass);
            Statement stat = con.createStatement();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter patient's last name");
            String lastName = scanner.nextLine();
            System.out.println("Enter patient's first name");
            String firstName = scanner.nextLine();

            // create the mysql delete statement.
            String query = "delete from prescriptions where lastName = ? and firstName = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, lastName);
            preparedStmt.setString(2, firstName);

            // execute the preparedstatement
            preparedStmt.execute();
            preparedStmt.close();
            con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
