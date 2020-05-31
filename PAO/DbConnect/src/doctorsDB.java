import java.sql.*;
import java.util.Scanner;

public class doctorsDB {

    public static void addDoctor(String host, String username, String pass) {

        Connection con = null;
        try {
            con = DriverManager.getConnection(host, username, pass);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter doctor's last name");
            String lastName = scanner.nextLine();
            System.out.println("Enter doctor's first name");
            String firstName = scanner.nextLine();
            System.out.println("Enter doctor's sex (F/M)");
            String sex = scanner.nextLine();
            System.out.println("Enter doctor's age");
            int age = scanner.nextInt();
            System.out.println("Enter doctor's speciality");
            String speciality = scanner.next();
            System.out.println("Enter doctor's consultation cost");
            int consultationCost = scanner.nextInt();
            System.out.println("Enter doctor's shift start");
            int shiftStart = scanner.nextInt();
            System.out.println("Enter doctor's shift end");
            int shiftEnd = scanner.nextInt();

            // the mysql insert statement
            String query = " insert into doctors (lastName, firstName, age, sex, speciality, consultationCost, shiftStart, shiftEnd)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, lastName);
            preparedStmt.setString (2, firstName);
            preparedStmt.setInt   (3, age);
            preparedStmt.setString(4, sex);
            preparedStmt.setString    (5, speciality);
            preparedStmt.setInt   (6, consultationCost);
            preparedStmt.setInt   (7, shiftStart);
            preparedStmt.setInt   (8, shiftEnd);

            // execute the preparedstatement
            preparedStmt.execute();
            con.close();
            System.out.println("Doctor added");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void showDoctors(String host, String username, String pass) {
        try {
            Connection con = DriverManager.getConnection(host, username, pass);
            Statement stat = con.createStatement();
            String sql = "select * from doctors";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("iddoctors");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                int age = rs.getInt("age");
                String sex = rs.getString("sex");
                String speciality = rs.getString("speciality");
                int consultationCost = rs.getInt("consultationCost");
                int shiftStart = rs.getInt("shiftStart");
                int shiftEnd = rs.getInt("shiftEnd");

                String p = id + " " + firstName + " " + lastName + " " + age + " " + sex + " " + speciality + " " + consultationCost + " " + shiftStart + " "
                        + shiftEnd;
                System.out.println(p);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateDoctor (String host, String username, String pass) {

        Connection con = null;
        try {
            con = DriverManager.getConnection(host, username, pass);
            con.setAutoCommit(false);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter doctor's id");
            int iddoctor = scanner.nextInt();
            System.out.println("Enter doctor's new shift start");
            int shiftStart = scanner.nextInt();
            System.out.println("Enter doctor's new shift end");
            int shiftEnd = scanner.nextInt();

            // create the java mysql update preparedstatement
            String query = "update clinic.doctors set shiftStart = ?, shiftEnd = ? where iddoctors = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt   (1, shiftStart);
            preparedStmt.setInt   (2, shiftEnd);
            preparedStmt.setInt(3, iddoctor);

            // execute the java preparedstatement
            preparedStmt.executeUpdate();
            con.commit();

            System.out.println("Doctor updated");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteDoctor (String host, String username, String pass) {

        Connection con = null;
        try {
            con = DriverManager.getConnection(host, username, pass);
            Statement stat = con.createStatement();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter doctor's id");
            int id = scanner.nextInt();

            // create the mysql delete statement.
            String query = "delete from doctors where iddoctors = ? ";
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
