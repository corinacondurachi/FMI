package dataBase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Date;
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
            try {
                FileWriter fw = new FileWriter(new File("src/Service/ServiceAudit.csv"), true);
                java.util.Date d = new Date();
                //getTime() returns current time in milliseconds
                long t = d.getTime();
                //Passed the milliseconds to constructor of Timestamp class
                Timestamp ts = new Timestamp(t);
                String currentThreadName = Thread.currentThread().getName();
                fw.write("addNurse" + "," +  ts.toString() + currentThreadName + "\n");
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static String showNurses(String host, String username, String pass) {
        try {
            Connection con = DriverManager.getConnection(host, username, pass);
            Statement stat = con.createStatement();
            String sql = "select * from nurses";
            ResultSet rs = stat.executeQuery(sql);
            String p = "";
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                int age = rs.getInt("age");
                String sex = rs.getString("sex");
                String shift = rs.getString("shift");

                p += id + " " + firstName + " " + lastName + " " + age + " " + sex + " " + shift + "\n";

            }
            con.close();
            try {
                FileWriter fw = new FileWriter(new File("src/Service/ServiceAudit.csv"), true);
                java.util.Date d = new Date();
                //getTime() returns current time in milliseconds
                long t = d.getTime();
                //Passed the milliseconds to constructor of Timestamp class
                Timestamp ts = new Timestamp(t);
                String currentThreadName = Thread.currentThread().getName();
                fw.write("showNurses" + "," +  ts.toString() + currentThreadName + "\n");
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return p;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
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
            try {
                FileWriter fw = new FileWriter(new File("src/Service/ServiceAudit.csv"), true);
                Date d = new Date();
                //getTime() returns current time in milliseconds
                long t = d.getTime();
                //Passed the milliseconds to constructor of Timestamp class
                Timestamp ts = new Timestamp(t);
                String currentThreadName = Thread.currentThread().getName();
                fw.write("updateNurse" + "," +  ts.toString() + currentThreadName + "\n");
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

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
            try {
                FileWriter fw = new FileWriter(new File("src/Service/ServiceAudit.csv"), true);
                Date d = new Date();
                //getTime() returns current time in milliseconds
                long t = d.getTime();
                //Passed the milliseconds to constructor of Timestamp class
                Timestamp ts = new Timestamp(t);
                String currentThreadName = Thread.currentThread().getName();
                fw.write("deleteNurse" + "," +  ts.toString() + currentThreadName + "\n");
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
