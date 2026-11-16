/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package hotelsystem;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class LoginPage extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnClear;

    public LoginPage() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Hotel Reservation System - Login");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(245, 245, 245));

        JLabel lblTitle = new JLabel("Hotel Reservation System");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setForeground(new Color(44, 62, 80));
        lblTitle.setBounds(80, 30, 300, 35);
        mainPanel.add(lblTitle);

        JLabel lblSubtitle = new JLabel("Please login to continue");
        lblSubtitle.setFont(new Font("Arial", Font.PLAIN, 13));
        lblSubtitle.setForeground(Color.GRAY);
        lblSubtitle.setBounds(145, 65, 200, 20);
        mainPanel.add(lblSubtitle);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Arial", Font.BOLD, 13));
        lblUsername.setBounds(60, 110, 100, 25);
        mainPanel.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(170, 110, 200, 30);
        mainPanel.add(txtUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Arial", Font.BOLD, 13));
        lblPassword.setBounds(60, 160, 100, 25);
        mainPanel.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(170, 160, 200, 30);
        mainPanel.add(txtPassword);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(100, 220, 110, 38);
        btnLogin.setBackground(new Color(41, 128, 185));
        btnLogin.setForeground(Color.WHITE);
        mainPanel.add(btnLogin);

        btnClear = new JButton("Clear");
        btnClear.setBounds(230, 220, 110, 38);
        btnClear.setBackground(new Color(189, 195, 199));
        mainPanel.add(btnClear);

        add(mainPanel);

        btnLogin.addActionListener(e -> handleLogin());
        btnClear.addActionListener(e -> clearFields());
    }

    private void handleLogin() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter username and password.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (DatabaseHelper.checkLogin(username, password)) {
            new MainDashboard(username).setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        txtUsername.setText("");
        txtPassword.setText("");
        txtUsername.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginPage().setVisible(true));
    }
}

