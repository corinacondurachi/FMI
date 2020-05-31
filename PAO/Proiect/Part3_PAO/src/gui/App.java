package gui;

import dataBase.doctorsDB;
import dataBase.nursesDB;
import dataBase.appointmentsDB;
import dataBase.prescriptionsDB;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Date;

public class App implements ActionListener {

    JButton button1 = new JButton("Show doctors working at the clinic");
    JButton button2 = new JButton("Show nurses working at the clinic");
    JButton button3 = new JButton("Show appointments that were set");
    JButton button4 = new JButton("Show prescriptions that were made");
    JButton button5 = new JButton("Add an appointment");
    JButton button6 = new JButton("Change an appointment");
    JButton button7 = new JButton("Delete an appointment");


    public App() {
        JFrame frame = new JFrame();
        JLabel label = new JLabel("Chose your option");
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);
        button7.addActionListener(this);

        JPanel panel = new JPanel(null);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(label);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);
        panel.add(button7);


        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Clinic Imperial");
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new App();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String host = "jdbc:mysql://localhost:3306/clinic?serverTimezone=UTC";
        String username = "root";
        String pass = "corina99";

        if (e.getSource() == button1) {
            JFrame frame = new JFrame("Doctors working at Imperial Clinic");
            JPanel panel = new JPanel(null);
            panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
            panel.setLayout(new GridLayout(0, 1));
            String text = doctorsDB.showDoctors(host, username, pass);
            String[] lines = text.split("\n");
            for (String line : lines) {
                JLabel label = new JLabel(line);
                panel.add(label);
            }
            frame.add(panel, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);
            try {
                FileWriter fw = new FileWriter(new File("src/Service/ServiceAudit.csv"), true);
                Date d = new Date();
                //getTime() returns current time in milliseconds
                long t = d.getTime();
                //Passed the milliseconds to constructor of Timestamp class
                Timestamp ts = new Timestamp(t);
                String currentThreadName = Thread.currentThread().getName();
                fw.write("showDoctors" + "," +  ts.toString() + currentThreadName + "\n");
                fw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        if (e.getSource() == button2) {
            JFrame frame = new JFrame("Nurses working at Imperial Clinic");
            JPanel panel = new JPanel(null);
            panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
            panel.setLayout(new GridLayout(0, 1));
            String text = nursesDB.showNurses(host, username, pass);
            String[] lines = text.split("\n");
            for (String line : lines) {
                JLabel label = new JLabel(line);
                panel.add(label);
            }
            frame.add(panel, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);

            try {
                FileWriter fw = new FileWriter(new File("src/Service/ServiceAudit.csv"), true);
                Date d = new Date();
                //getTime() returns current time in milliseconds
                long t = d.getTime();
                //Passed the milliseconds to constructor of Timestamp class
                Timestamp ts = new Timestamp(t);
                String currentThreadName = Thread.currentThread().getName();
                fw.write("showNurses" + "," +  ts.toString() + currentThreadName + "\n");
                fw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        if (e.getSource() == button3) {
            JFrame frame = new JFrame("Appointments set at Imperial Clinic");
            JPanel panel = new JPanel(null);
            panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
            panel.setLayout(new GridLayout(0, 1));
            String text = appointmentsDB.showAppointments(host, username, pass);
            String[] lines = text.split("\n");
            for (String line : lines) {
                JLabel label = new JLabel(line);
                panel.add(label);
            }
            frame.add(panel, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);

            try {
                FileWriter fw = new FileWriter(new File("src/Service/ServiceAudit.csv"), true);
                Date d = new Date();
                //getTime() returns current time in milliseconds
                long t = d.getTime();
                //Passed the milliseconds to constructor of Timestamp class
                Timestamp ts = new Timestamp(t);
                String currentThreadName = Thread.currentThread().getName();
                fw.write("showAppointments" + "," +  ts.toString() + currentThreadName + "\n");
                fw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        if (e.getSource() == button4) {
            JFrame frame = new JFrame("Prescriptions made at Imperial Clinic");
            JPanel panel = new JPanel(null);
            panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
            panel.setLayout(new GridLayout(0, 1));
            String text = prescriptionsDB.showPrescriptions(host, username, pass);
            String[] lines = text.split("\n");
            for (String line : lines) {
                JLabel label = new JLabel(line);
                panel.add(label);
            }
            frame.add(panel, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);
            try {
                FileWriter fw = new FileWriter(new File("src/Service/ServiceAudit.csv"), true);
                Date d = new Date();
                //getTime() returns current time in milliseconds
                long t = d.getTime();
                //Passed the milliseconds to constructor of Timestamp class
                Timestamp ts = new Timestamp(t);
                String currentThreadName = Thread.currentThread().getName();
                fw.write("showPrescriptions" + "," +  ts.toString() + currentThreadName + "\n");
                fw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }


        if (e.getSource() == button5) {
            JFrame frame = new JFrame("Set an appointment");
            JPanel panel = new JPanel(null);
            panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
            panel.setLayout(new GridLayout(0, 2));
            JLabel label1 = new JLabel("Patient's last name:");
            JTextField text1 = new JTextField();
            JLabel label2 = new JLabel("Patient's first name:");
            JTextField text2 = new JTextField();
            JLabel label3 = new JLabel("Patient's sex:");
            JTextField text3 = new JTextField();
            JLabel label4 = new JLabel("Patient's age:");
            JTextField text4 = new JTextField();
            JLabel label5 = new JLabel("Doctor's last name:");
            JTextField text5 = new JTextField();
            JLabel label6 = new JLabel("Doctor's first name:");
            JTextField text6 = new JTextField();
            JLabel label7 = new JLabel("Date of the app:");
            JTextField text7 = new JTextField();
            JLabel label8 = new JLabel("Time of the appointment:");
            JTextField text8 = new JTextField();

            panel.add(label1);
            panel.add(text1);
            panel.add(label2);
            panel.add(text2);
            panel.add(label3);
            panel.add(text3);
            panel.add(label4);
            panel.add(text4);
            panel.add(label5);
            panel.add(text5);
            panel.add(label6);
            panel.add(text6);
            panel.add(label7);
            panel.add(text7);
            panel.add(label8);
            panel.add(text8);


            JButton button = new JButton("Submit");
            panel.add(button);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Connection con = null;
                    String lastName = text1.getText();
                    String firstName = text3.getText();
                    String sex = text3.getText();
                    int age = Integer.parseInt(text4.getText());
                    String doctorLastName = text5.getText();
                    String doctorFirstName = text6.getText();
                    int date = Integer.parseInt(text7.getText());
                    int time = Integer.parseInt(text8.getText());
                    try {
                        con = DriverManager.getConnection(host, username, pass);
                        String query = " insert into appointments (lastName, firstName, age, sex, doctorFirstName, doctorLastName, date, time)"
                                + " values (?, ?, ?, ?, ?, ?, ?, ?)";

                        // create the mysql insert preparedstatement
                        PreparedStatement preparedStmt = con.prepareStatement(query);
                        preparedStmt.setString (1, lastName);
                        preparedStmt.setString (2, firstName);
                        preparedStmt.setInt   (3, age);
                        preparedStmt.setString(4, sex);
                        preparedStmt.setString    (5, doctorFirstName);
                        preparedStmt.setString   (6, doctorLastName);
                        preparedStmt.setInt   (7, date);
                        preparedStmt.setInt   (8, time);

                        // execute the preparedstatement
                        preparedStmt.execute();

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(frame.getComponent(0), "Appointment added!");


                }
            });
            frame.add(panel, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);
            try {
                FileWriter fw = new FileWriter(new File("src/Service/ServiceAudit.csv"), true);
                Date d = new Date();
                //getTime() returns current time in milliseconds
                long t = d.getTime();
                //Passed the milliseconds to constructor of Timestamp class
                Timestamp ts = new Timestamp(t);
                String currentThreadName = Thread.currentThread().getName();
                fw.write("addAppointment" + "," +  ts.toString() + currentThreadName + "\n");
                fw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        if (e.getSource() == button6) {
            JFrame frame = new JFrame("Change an appointment");
            JPanel panel = new JPanel(null);
            panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
            panel.setLayout(new GridLayout(0, 2));
            JLabel label1 = new JLabel("Patient's last name:");
            JTextField text1 = new JTextField();
            JLabel label2 = new JLabel("Patient's first name:");
            JTextField text2 = new JTextField();
            JLabel label3 = new JLabel("New date of the app:");
            JTextField text3 = new JTextField();
            JLabel label4 = new JLabel("New time of the appointment:");
            JTextField text4 = new JTextField();

            panel.add(label1);
            panel.add(text1);
            panel.add(label2);
            panel.add(text2);
            panel.add(label3);
            panel.add(text3);
            panel.add(label4);
            panel.add(text4);

            JButton button = new JButton("Submit");
            panel.add(button);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Connection con = null;
                    String lastName = text1.getText();
                    String firstName = text2.getText();
                    int newDate = Integer.parseInt(text3.getText());
                    int newTime = Integer.parseInt(text4.getText());
                    try {
                        con = DriverManager.getConnection(host, username, pass);
                        con.setAutoCommit(false);
                        String query = "update clinic.appointments set date = ?, time = ? where lastName = ? and firstName = ?";
                        PreparedStatement preparedStmt = con.prepareStatement(query);
                        preparedStmt.setInt   (1, newDate);
                        preparedStmt.setInt   (2, newTime);
                        preparedStmt.setString(3, lastName);
                        preparedStmt.setString(4, firstName);

                        // execute the java preparedstatement
                        preparedStmt.executeUpdate();
                        con.commit();
                        preparedStmt.close();
                        con.close();

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(frame.getComponent(0), "Appointment changed!");
                }
            });
            frame.add(panel, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);

            try {
                FileWriter fw = new FileWriter(new File("src/Service/ServiceAudit.csv"), true);
                Date d = new Date();
                //getTime() returns current time in milliseconds
                long t = d.getTime();
                //Passed the milliseconds to constructor of Timestamp class
                Timestamp ts = new Timestamp(t);
                String currentThreadName = Thread.currentThread().getName();
                fw.write("changeAppointment" + "," +  ts.toString() + currentThreadName + "\n");
                fw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        if (e.getSource() == button7) {
            JFrame frame = new JFrame("Remove an appointment");
            JPanel panel = new JPanel(null);
            panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
            panel.setLayout(new GridLayout(0, 2));
            JLabel label1 = new JLabel("Patient's last name:");
            JTextField text1 = new JTextField();
            JLabel label2 = new JLabel("Patient's first name:");
            JTextField text2 = new JTextField();


            panel.add(label1);
            panel.add(text1);
            panel.add(label2);
            panel.add(text2);

            JButton button = new JButton("Submit");
            panel.add(button);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Connection con = null;
                    String lastName = text1.getText();
                    String firstName = text2.getText();
                    try {
                        con = DriverManager.getConnection(host, username, pass);
                        // create the mysql delete statement.
                        String query = "delete from appointments where lastName = ? and firstName = ?";
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
                    JOptionPane.showMessageDialog(frame.getComponent(0), "Appointment removed!");
                }
            });
            frame.add(panel, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);

            try {
                FileWriter fw = new FileWriter(new File("src/Service/ServiceAudit.csv"), true);
                Date d = new Date();
                //getTime() returns current time in milliseconds
                long t = d.getTime();
                //Passed the milliseconds to constructor of Timestamp class
                Timestamp ts = new Timestamp(t);
                String currentThreadName = Thread.currentThread().getName();
                fw.write("deleteAppointment" + "," +  ts.toString() + currentThreadName + "\n");
                fw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
