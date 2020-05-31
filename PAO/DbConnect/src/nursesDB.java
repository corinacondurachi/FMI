import java.sql.*;
import java.util.Scanner;

public class nursesDB {

    public static void addNurse(String host, String username, String pass) {

        Connection con = null;
        try {
            con = DriverManager.getConnection(host, username, pass);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter nurse's last name");
            String lastName = scanner.nextLine();
            System.out.println("Enter nurse's first name");
            String firstName = scanner.nextLine();
            System.out.println("Enter nurse's sex (F/M)");
            String sex = scanner.nextLine();
            System.out.println("Enter nurse's age");
            int age = scanner.nextInt();
            System.out.println("Enter nurse's shift");
            String shift = scanner.next();

            // the mysql insert statement
            String query = " insert into nurses (lastName, firstName, age, sex, shift)" + " values (?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, lastName);
            preparedStmt.setString (2, firstName);
            preparedStmt.setInt   (3, age);
            preparedStmt.setString(4, sex);
            preparedStmt.setString   (5, shift);

            // execute the preparedstatement
            preparedStmt.execute();
            con.close();
            System.out.println("Nurse added");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void showNurses(String host, String username, String pass) {
        try {
            Connection con = DriverManager.getConnection(host, username, pass);
            Statement stat = con.createStatement();
            String sql = "select * from nurses";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                int age = rs.getInt("age");
                String sex = rs.getString("sex");
                String shift = rs.getString("shift");

                String p = id + " " + firstName + " " + lastName + " " + age + " " + sex + " " + shift;
                System.out.println(p);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateNurse (String host, String username, String pass) {

        Connection con = null;
        try {
            con = DriverManager.getConnection(host, username, pass);
            con.setAutoCommit(false);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter nurse's id");
            int idnurse = scanner.nextInt();
            System.out.println("Enter doctor's new shift");
            String shift = scanner.next();


            // create the java mysql update preparedstatement
            String query = "update clinic.nurses set shift = ? where id = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString   (1, shift);
            preparedStmt.setInt   (2, idnurse);


            // execute the java preparedstatement
            preparedStmt.executeUpdate();
            con.commit();

            System.out.println("Nurse updated");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteNurse (String host, String username, String pass) {

        Connection con = null;
        try {
            con = DriverManager.getConnection(host, username, pass);
            Statement stat = con.createStatement();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter nurse's id");
            int id = scanner.nextInt();

            // create the mysql delete statement.
            String query = "delete from nurses where id = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, id);

            // execute the preparedstatement
            preparedStmt.execute();
            preparedStmt.close();
            con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
