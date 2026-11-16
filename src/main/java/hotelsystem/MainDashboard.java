/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package hotelsystem;

import javax.swing.*;
import java.awt.*;

public class MainDashboard extends JFrame {
    private String loggedInUser;

    public MainDashboard(String username) {
        this.loggedInUser = username;
        initComponents();
    }

    private void initComponents() {
        setTitle("Hotel Reservation System - Main Dashboard");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(236, 240, 241));
        add(mainPanel);

        
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setBounds(0, 0, 700, 80);
        headerPanel.setBackground(new Color(41, 128, 185));
        mainPanel.add(headerPanel);

        JLabel lblTitle = new JLabel("Hotel Reservation System");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(20, 15, 350, 30);
        headerPanel.add(lblTitle);

        JLabel lblWelcome = new JLabel("Welcome, " + loggedInUser + "!");
        lblWelcome.setFont(new Font("Arial", Font.PLAIN, 13));
        lblWelcome.setForeground(new Color(214, 234, 248));
        lblWelcome.setBounds(20, 48, 300, 20);
        headerPanel.add(lblWelcome);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(580, 25, 90, 30);
        btnLogout.setBackground(new Color(231, 76, 60));
        btnLogout.setForeground(Color.WHITE);
        headerPanel.add(btnLogout);

        
        JButton btnAddPatient = createMenuButton("Add Patient", "Add New");
        JButton btnViewAppointments = createMenuButton("View Appointments", "View All");
        JButton btnBooking = createMenuButton("New Booking", "Book");
        JButton btnSearch = createMenuButton("Search Patient", "Find");
        JButton btnDoctors = createMenuButton("Manage Doctors", "Edit");
        JButton btnReports = createMenuButton("Reports", "View");

        btnAddPatient.setBounds(30, 160, 190, 100);
        btnViewAppointments.setBounds(250, 160, 190, 100);
        btnBooking.setBounds(470, 160, 190, 100);
        btnSearch.setBounds(30, 285, 190, 100);
        btnDoctors.setBounds(250, 285, 190, 100);
        btnReports.setBounds(470, 285, 190, 100);

        mainPanel.add(btnAddPatient);
        mainPanel.add(btnViewAppointments);
        mainPanel.add(btnBooking);
        mainPanel.add(btnSearch);
        mainPanel.add(btnDoctors);
        mainPanel.add(btnReports);

       
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(null);
        footerPanel.setBounds(0, 455, 700, 45);
        footerPanel.setBackground(new Color(44, 62, 80));
        mainPanel.add(footerPanel);

        JLabel lblFooter = new JLabel("Hotel Reservation System © 2025 | CS342");
        lblFooter.setFont(new Font("Arial", Font.PLAIN, 11));
        lblFooter.setForeground(new Color(189, 195, 199));
        lblFooter.setBounds(200, 13, 350, 20);
        footerPanel.add(lblFooter);

        
        btnAddPatient.addActionListener(e -> new PatientManagement(this).setVisible(true));
        
        btnViewAppointments.addActionListener(e -> new ViewAppointmentsPage().setVisible(true));
        btnSearch.addActionListener(e -> new SearchPatientPage().setVisible(true));
        btnDoctors.addActionListener(e -> new ManageDoctorsPage().setVisible(true));
        
        btnLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                new LoginPage().setVisible(true);
                this.dispose();
            }
        });
    }

    private JButton createMenuButton(String title, String subtitle) {
        JButton btn = new JButton("<html><center><b style='font-size:14px'>" + title + "</b><br><span style='font-size:10px;color:#7f8c8d'>" + subtitle + "</span></center></html>");
        btn.setBackground(new Color(52, 73, 94));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
}
    