/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class ManageDoctorsPage extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtName, txtFrom, txtTo;
    private JButton btnAdd, btnDelete, btnRefresh;

    public ManageDoctorsPage() {
        setTitle("Manage Doctors");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        
        JPanel addPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        addPanel.setBorder(BorderFactory.createTitledBorder("Add New Doctor"));
        addPanel.add(new JLabel("Name:"));
        txtName = new JTextField();
        addPanel.add(txtName);
        addPanel.add(new JLabel("Available From (HH:MM):"));
        txtFrom = new JTextField();
        addPanel.add(txtFrom);
        addPanel.add(new JLabel("Available To (HH:MM):"));
        txtTo = new JTextField();
        addPanel.add(txtTo);
        btnAdd = new JButton("Add Doctor");
        addPanel.add(btnAdd);
        add(addPanel, BorderLayout.NORTH);

       
        String[] columns = {"ID", "Name", "Available From", "Available To"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnDelete = new JButton("Delete Selected");
        btnRefresh = new JButton("Refresh");
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnRefresh);
        add(buttonPanel, BorderLayout.SOUTH);

        
        btnAdd.addActionListener(e -> addDoctor());
        btnDelete.addActionListener(e -> deleteDoctor());
        btnRefresh.addActionListener(e -> loadDoctors());

        loadDoctors();
        setVisible(true);
    }

    private void loadDoctors() {
        try {
            ResultSet rs = DatabaseHelper.getAllDoctors();
            tableModel.setRowCount(0);
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("doctor_id"),
                    rs.getString("doctor_name"),
                    rs.getString("available_from"),
                    rs.getString("available_to")
                };
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading doctors: " + e.getMessage());
        }
    }

    private void addDoctor() {
        String name = txtName.getText().trim();
        String from = txtFrom.getText().trim();
        String to = txtTo.getText().trim();

        if (name.isEmpty() || from.isEmpty() || to.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }

        boolean success = DatabaseHelper.addDoctor(name, from, to);
        if (success) {
            JOptionPane.showMessageDialog(this, "Doctor added successfully!");
            txtName.setText("");
            txtFrom.setText("");
            txtTo.setText("");
            loadDoctors();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add doctor.");
        }
    }

    private void deleteDoctor() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a doctor to delete.");
            return;
        }

        int doctorId = (int) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = DatabaseHelper.deleteDoctor(doctorId);
            if (success) {
                JOptionPane.showMessageDialog(this, "Doctor deleted successfully!");
                loadDoctors();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete doctor.");
            }
        }
    }
}