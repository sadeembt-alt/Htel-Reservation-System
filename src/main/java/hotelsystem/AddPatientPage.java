/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;

public class AddPatientPage extends JDialog {

    private JTextField txtName, txtPhone, txtEmail, txtAge;
    private JComboBox<String> cmbGender;
    private JTextArea txtNotes;

    public AddPatientPage(JFrame parent) {
        super(parent, "Add New Patient", true);
        initComponents();
    }

    private void initComponents() {
        setSize(520, 530);
        setLocationRelativeTo(getParent());
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(245, 245, 245));
        add(mainPanel);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setBounds(0, 0, 520, 60);
        headerPanel.setBackground(new Color(39, 174, 96));
        mainPanel.add(headerPanel);

        JLabel lblTitle = new JLabel("Add New Patient");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(20, 15, 300, 30);
        headerPanel.add(lblTitle);

        addLabel(mainPanel, "Full Name: *",  30, 85);
        addLabel(mainPanel, "Phone: *",      30, 135);
        addLabel(mainPanel, "Email:",        30, 185);
        addLabel(mainPanel, "Age: *",        30, 235);
        addLabel(mainPanel, "Gender: *",     30, 285);
        addLabel(mainPanel, "Notes:",        30, 335);

        txtName  = addTextField(mainPanel, 170, 85,  300);
        txtPhone = addTextField(mainPanel, 170, 135, 300);
        txtEmail = addTextField(mainPanel, 170, 185, 300);
        txtAge   = addTextField(mainPanel, 170, 235, 100);

        String[] genders = {"-- Select --", "Male", "Female"};
        cmbGender = new JComboBox<>(genders);
        cmbGender.setFont(new Font("Arial", Font.PLAIN, 13));
        cmbGender.setBounds(170, 285, 150, 30);
        mainPanel.add(cmbGender);

        txtNotes = new JTextArea();
        txtNotes.setFont(new Font("Arial", Font.PLAIN, 13));
        txtNotes.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(txtNotes);
        scroll.setBounds(170, 335, 300, 60);
        mainPanel.add(scroll);

        JButton btnSave  = new JButton("Save Patient");
        JButton btnClear = new JButton("Clear");
        JButton btnBack  = new JButton("Back");

        styleButton(btnSave,  new Color(39, 174, 96),   Color.WHITE,             60,  440, 140, 38);
        styleButton(btnClear, new Color(189, 195, 199), new Color(44, 62, 80),  220, 440, 110, 38);
        styleButton(btnBack,  new Color(231, 76, 60),   Color.WHITE,            350, 440, 110, 38);

        mainPanel.add(btnSave);
        mainPanel.add(btnClear);
        mainPanel.add(btnBack);

        btnSave.addActionListener(e  -> handleSave());
        btnClear.addActionListener(e -> clearFields());
        btnBack.addActionListener(e  -> dispose());
    }

    private void handleSave() {
        String name   = txtName.getText().trim();
        String phone  = txtPhone.getText().trim();
        String email  = txtEmail.getText().trim();
        String ageStr = txtAge.getText().trim();
        String gender = (String) cmbGender.getSelectedItem();

        if (name.isEmpty()) {
            showWarning("Full Name is required.", "Missing Field");
            txtName.requestFocus(); return;
        }
        if (phone.isEmpty()) {
            showWarning("Phone number is required.", "Missing Field");
            txtPhone.requestFocus(); return;
        }
        if (!phone.matches("\\d{10,15}")) {
            showWarning("Phone must contain only digits (10–15 digits).", "Invalid Phone");
            txtPhone.requestFocus(); return;
        }
        if (!email.isEmpty()) {
            String regex = "^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$";
            if (!Pattern.matches(regex, email)) {
                showWarning("Please enter a valid email.\nExample: name@example.com", "Invalid Email");
                txtEmail.requestFocus(); return;
            }
        }
        if (ageStr.isEmpty()) {
            showWarning("Age is required.", "Missing Field");
            txtAge.requestFocus(); return;
        }
        try {
            int age = Integer.parseInt(ageStr);
            if (age <= 0 || age > 120) {
                showWarning("Please enter a valid age (1–120).", "Invalid Age");
                txtAge.requestFocus(); return;
            }
        } catch (NumberFormatException ex) {
            showWarning("Age must be a number.", "Invalid Age");
            txtAge.requestFocus(); return;
        }
        if (gender.equals("-- Select --")) {
            showWarning("Please select the patient's gender.", "Missing Field");
            cmbGender.requestFocus(); return;
        }

         boolean success = DatabaseHelper.addPatient(name, phone, email, ageStr, gender);
         if (success) {
         JOptionPane.showMessageDialog(this, "Patient added successfully!\n\nName: " + name + "\nPhone: " + phone, "Success", JOptionPane.INFORMATION_MESSAGE);
         clearFields();
         } else {
         JOptionPane.showMessageDialog(this, "Failed to add patient. Please check database connection.", "Error", JOptionPane.ERROR_MESSAGE);
         }
         
         
        JOptionPane.showMessageDialog(this,
            "Patient added successfully!\n\nName: " + name + "\nPhone: " + phone,
            "Success", JOptionPane.INFORMATION_MESSAGE);
        clearFields();
    }

    private void clearFields() {
        txtName.setText(""); txtPhone.setText("");
        txtEmail.setText(""); txtAge.setText("");
        cmbGender.setSelectedIndex(0); txtNotes.setText("");
        txtName.requestFocus();
    }

    private void showWarning(String msg, String title) {
        JOptionPane.showMessageDialog(this, msg, title, JOptionPane.WARNING_MESSAGE);
    }

    private JLabel addLabel(JPanel p, String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Arial", Font.BOLD, 13));
        lbl.setBounds(x, y, 130, 25);
        p.add(lbl); return lbl;
    }

    private JTextField addTextField(JPanel p, int x, int y, int w) {
        JTextField tf = new JTextField();
        tf.setFont(new Font("Arial", Font.PLAIN, 13));
        tf.setBounds(x, y, w, 30);
        p.add(tf); return tf;
    }

    private void styleButton(JButton btn, Color bg, Color fg, int x, int y, int w, int h) {
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setBounds(x, y, w, h);
        btn.setBackground(bg); btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
