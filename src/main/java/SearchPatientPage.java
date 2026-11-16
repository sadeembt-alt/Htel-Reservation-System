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

public class SearchPatientPage extends JFrame {
    private JTextField txtSearch;
    private JButton btnSearch;
    private JTable table;
    private DefaultTableModel tableModel;

    public SearchPatientPage() {
        setTitle("Search Patient");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.add(new JLabel("Search:"));
        txtSearch = new JTextField(20);
        searchPanel.add(txtSearch);
        btnSearch = new JButton("Search");
        searchPanel.add(btnSearch);
        add(searchPanel, BorderLayout.NORTH);

        
        String[] columns = {"ID", "Name", "Phone", "Email"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

       
        btnSearch.addActionListener(e -> performSearch());

        setVisible(true);
    }

    private void performSearch() {
        String keyword = txtSearch.getText().trim();
        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter search keyword.");
            return;
        }

        try {
            ResultSet rs = DatabaseHelper.searchPatient(keyword);
            tableModel.setRowCount(0); 
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("patient_id"),
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getString("email")
                };
                tableModel.addRow(row);
            }
            if (tableModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "No patients found.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}