/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;

public class ViewAppointmentsPage extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public ViewAppointmentsPage() {
        setTitle("View Appointments");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

       
        String[] columns = {"ID", "Patient Name", "Doctor Name", "Date", "Time"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);
        loadAppointments();
        setVisible(true);
    }

    private void loadAppointments() {
        try {
            ResultSet rs = DatabaseHelper.getAllAppointments();
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("app_id"),
                    rs.getString("patient_name"),
                    rs.getString("doctor_name"),
                    rs.getDate("app_date"),
                    rs.getTime("app_time")
                };
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading appointments: " + e.getMessage());
        }
    }
}