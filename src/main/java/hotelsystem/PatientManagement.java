/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelsystem;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PatientManagement extends JDialog {

    private JTable patientTable;
    private DefaultTableModel tableModel;
    private JTextField txtName, txtPhone, txtEmail, txtAge, txtSearch;
    private JComboBox<String> cmbGender;
    private JButton btnAdd, btnUpdate, btnDelete, btnSearch, btnClear, btnBack;

    public PatientManagement(JFrame parent) {
        super(parent, "Patient Management", true);
        initComponents();
    }

    private void initComponents() {
        setSize(850, 600);
        setLocationRelativeTo(getParent());
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(245, 245, 245));
        add(mainPanel);

        
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setBounds(0, 0, 850, 55);
        headerPanel.setBackground(new Color(41, 128, 185));
        mainPanel.add(headerPanel);

        JLabel lblTitle = new JLabel("Patient Management");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(20, 13, 300, 28);
        headerPanel.add(lblTitle);

        
        JLabel lblSearchTitle = new JLabel("Search by Name or Phone:");
        lblSearchTitle.setFont(new Font("Arial", Font.BOLD, 12));
        lblSearchTitle.setBounds(15, 70, 200, 22);
        mainPanel.add(lblSearchTitle);

        txtSearch = new JTextField();
        txtSearch.setFont(new Font("Arial", Font.PLAIN, 13));
        txtSearch.setBounds(15, 95, 280, 30);
        mainPanel.add(txtSearch);

        btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("Arial", Font.BOLD, 12));
        btnSearch.setBounds(305, 95, 90, 30);
        btnSearch.setBackground(new Color(41, 128, 185));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFocusPainted(false);
        mainPanel.add(btnSearch);

        JButton btnShowAll = new JButton("Show All");
        btnShowAll.setFont(new Font("Arial", Font.BOLD, 12));
        btnShowAll.setBounds(405, 95, 90, 30);
        btnShowAll.setBackground(new Color(127, 140, 141));
        btnShowAll.setForeground(Color.WHITE);
        btnShowAll.setFocusPainted(false);
        mainPanel.add(btnShowAll);

       
        String[] columns = {"ID", "Full Name", "Phone", "Email", "Age", "Gender"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        patientTable = new JTable(tableModel);
        patientTable.setFont(new Font("Arial", Font.PLAIN, 12));
        patientTable.setRowHeight(25);
        patientTable.setSelectionBackground(new Color(174, 214, 241));
        patientTable.setGridColor(new Color(189, 195, 199));
        patientTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        patientTable.getTableHeader().setBackground(new Color(41, 128, 185));
        patientTable.getTableHeader().setForeground(Color.WHITE);
        patientTable.getColumnModel().getColumn(0).setPreferredWidth(35);
        patientTable.getColumnModel().getColumn(1).setPreferredWidth(140);
        patientTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        patientTable.getColumnModel().getColumn(3).setPreferredWidth(130);
        patientTable.getColumnModel().getColumn(4).setPreferredWidth(40);
        patientTable.getColumnModel().getColumn(5).setPreferredWidth(60);

        JScrollPane tableScroll = new JScrollPane(patientTable);
        tableScroll.setBounds(15, 135, 490, 380);
        mainPanel.add(tableScroll);

        
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBounds(520, 65, 310, 460);
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 1));
        mainPanel.add(formPanel);

        JLabel lblFormTitle = new JLabel("Patient Details");
        lblFormTitle.setFont(new Font("Arial", Font.BOLD, 14));
        lblFormTitle.setForeground(new Color(41, 128, 185));
        lblFormTitle.setBounds(15, 12, 200, 25);
        formPanel.add(lblFormTitle);

        addFormLabel(formPanel, "Full Name: *", 15, 52);
        txtName = addFormField(formPanel, 15, 75, 280);
        addFormLabel(formPanel, "Phone: *", 15, 112);
        txtPhone = addFormField(formPanel, 15, 135, 280);
        addFormLabel(formPanel, "Email:", 15, 172);
        txtEmail = addFormField(formPanel, 15, 195, 280);
        addFormLabel(formPanel, "Age: *", 15, 232);
        txtAge = addFormField(formPanel, 15, 255, 90);
        addFormLabel(formPanel, "Gender: *", 15, 292);

        String[] genders = {"-- Select --", "Male", "Female"};
        cmbGender = new JComboBox<>(genders);
        cmbGender.setFont(new Font("Arial", Font.PLAIN, 12));
        cmbGender.setBounds(15, 315, 150, 28);
        formPanel.add(cmbGender);

        JLabel lblRequired = new JLabel("* Required fields");
        lblRequired.setFont(new Font("Arial", Font.ITALIC, 10));
        lblRequired.setForeground(Color.RED);
        lblRequired.setBounds(15, 350, 150, 18);
        formPanel.add(lblRequired);

        
        btnAdd = new JButton("Add");
        btnAdd.setBounds(15, 375, 60, 32);
        btnAdd.setBackground(new Color(39, 174, 96));  
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFocusPainted(false);
        btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        formPanel.add(btnAdd);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(85, 375, 75, 32);
        btnUpdate.setBackground(new Color(230, 126, 34));  
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setFocusPainted(false);
        btnUpdate.setCursor(new Cursor(Cursor.HAND_CURSOR));
        formPanel.add(btnUpdate);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(170, 375, 70, 32);
        btnDelete.setBackground(new Color(231, 76, 60));  
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFocusPainted(false);
        btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        formPanel.add(btnDelete);

        btnClear = new JButton("Clear");
        btnClear.setBounds(250, 375, 55, 32);
        btnClear.setBackground(new Color(127, 140, 141));  
        btnClear.setForeground(Color.WHITE);
        btnClear.setFocusPainted(false);
        btnClear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        formPanel.add(btnClear);

        btnBack = new JButton("Back to Dashboard");
        btnBack.setFont(new Font("Arial", Font.BOLD, 12));
        btnBack.setBounds(520, 535, 310, 35);
        btnBack.setBackground(new Color(44, 62, 80));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusPainted(false);
        mainPanel.add(btnBack);

        
        loadAllPatients();

        
        patientTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = patientTable.getSelectedRow();
                if (row != -1) {
                    txtName.setText(tableModel.getValueAt(row, 1).toString());
                    txtPhone.setText(tableModel.getValueAt(row, 2).toString());
                    txtEmail.setText(tableModel.getValueAt(row, 3).toString());
                    txtAge.setText(tableModel.getValueAt(row, 4).toString());
                    cmbGender.setSelectedItem(tableModel.getValueAt(row, 5).toString());
                }
            }
        });

      
        btnAdd.addActionListener(e -> {
            if (validateFields()) {
                String name = txtName.getText().trim();
                String phone = txtPhone.getText().trim();
                String email = txtEmail.getText().trim();
                String age = txtAge.getText().trim();
                String gender = (String) cmbGender.getSelectedItem();
                
                boolean success = DatabaseHelper.addPatient(name, phone, email, age, gender);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Patient added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadAllPatients();
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add patient.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

      
        btnUpdate.addActionListener(e -> {
            int row = patientTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select a patient to update.", "No Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (validateFields()) {
                int patientId = (int) tableModel.getValueAt(row, 0);
                String name = txtName.getText().trim();
                String phone = txtPhone.getText().trim();
                String email = txtEmail.getText().trim();
                String age = txtAge.getText().trim();
                String gender = (String) cmbGender.getSelectedItem();
                
                boolean success = DatabaseHelper.updatePatient(patientId, name, phone, email, age, gender);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Patient updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadAllPatients();
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update patient.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        
        btnDelete.addActionListener(e -> {
            int row = patientTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select a patient to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int patientId = (int) tableModel.getValueAt(row, 0);
            String name = tableModel.getValueAt(row, 1).toString();
            int confirm = JOptionPane.showConfirmDialog(this, "Delete patient: " + name + "?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                boolean success = DatabaseHelper.deletePatient(patientId);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Patient deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadAllPatients();
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete patient.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

       
        btnSearch.addActionListener(e -> {
            String keyword = txtSearch.getText().trim();
            if (keyword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter search keyword.", "Empty", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                ResultSet rs = DatabaseHelper.searchPatient(keyword);
                tableModel.setRowCount(0);
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    tableModel.addRow(new Object[]{
                        rs.getInt("patient_id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getInt("age"),
                        rs.getString("gender")
                    });
                }
                if (!found) {
                    JOptionPane.showMessageDialog(this, "No patients found.", "Not Found", JOptionPane.INFORMATION_MESSAGE);
                    loadAllPatients();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Search error: " + ex.getMessage());
            }
        });

        btnShowAll.addActionListener(e -> {
            txtSearch.setText("");
            loadAllPatients();
        });

        btnClear.addActionListener(e -> clearFields());
        btnBack.addActionListener(e -> dispose());
    }

    private void loadAllPatients() {
        try {
            ResultSet rs = DatabaseHelper.getAllPatients();
            tableModel.setRowCount(0);
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getInt("patient_id"),
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getString("email"),
                    rs.getInt("age"),
                    rs.getString("gender")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading patients: " + e.getMessage());
        }
    }

    private boolean validateFields() {
        String name = txtName.getText().trim();
        String phone = txtPhone.getText().trim();
        String email = txtEmail.getText().trim();
        String ageStr = txtAge.getText().trim();
        String gender = (String) cmbGender.getSelectedItem();

        if (name.isEmpty()) { showWarn("Full Name is required.", "Missing Field"); txtName.requestFocus(); return false; }
        if (phone.isEmpty()) { showWarn("Phone number is required.", "Missing Field"); txtPhone.requestFocus(); return false; }
        if (!phone.matches("\\d{10,15}")) { showWarn("Phone must contain only digits (10-15 digits).", "Invalid Phone"); txtPhone.requestFocus(); return false; }
        if (!email.isEmpty() && !email.matches("^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$")) {
            showWarn("Please enter a valid email.", "Invalid Email");
            txtEmail.requestFocus(); return false;
        }
        if (ageStr.isEmpty()) { showWarn("Age is required.", "Missing Field"); txtAge.requestFocus(); return false; }
        try {
            int age = Integer.parseInt(ageStr);
            if (age <= 0 || age > 120) { showWarn("Please enter a valid age (1-120).", "Invalid Age"); txtAge.requestFocus(); return false; }
        } catch (NumberFormatException ex) { showWarn("Age must be a number.", "Invalid Age"); txtAge.requestFocus(); return false; }
        if (gender.equals("-- Select --")) { showWarn("Please select gender.", "Missing Field"); cmbGender.requestFocus(); return false; }
        return true;
    }

    private void clearFields() {
        txtName.setText(""); txtPhone.setText(""); txtEmail.setText("");
        txtAge.setText(""); cmbGender.setSelectedIndex(0);
        patientTable.clearSelection();
        txtName.requestFocus();
    }

    private void showWarn(String msg, String title) {
        JOptionPane.showMessageDialog(this, msg, title, JOptionPane.WARNING_MESSAGE);
    }

    private JLabel addFormLabel(JPanel p, String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Arial", Font.BOLD, 12));
        lbl.setBounds(x, y, 200, 20);
        p.add(lbl); return lbl;
    }

    private JTextField addFormField(JPanel p, int x, int y, int w) {
        JTextField tf = new JTextField();
        tf.setFont(new Font("Arial", Font.PLAIN, 12));
        tf.setBounds(x, y, w, 28);
        tf.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 1));
        p.add(tf); return tf;
    }
}
