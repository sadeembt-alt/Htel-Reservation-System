/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelsystem;
import java.sql.*;

public class DatabaseHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Ghost2026G22";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver not found", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static boolean checkLogin(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
            return false;
        }
    }

    public static boolean addPatient(String name, String phone, String email, String age, String gender) {
        String sql = "INSERT INTO patients (name, phone, email, age, gender) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            pstmt.setString(3, email);
            pstmt.setString(4, age);
            pstmt.setString(5, gender);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Add patient error: " + e.getMessage());
            return false;
        }
    }

    public static ResultSet getAllPatients() throws SQLException {
        return getConnection().createStatement().executeQuery("SELECT * FROM patients");
    }
    
    public static ResultSet getAllAppointments() throws SQLException {
    String sql = "SELECT a.app_id, p.name AS patient_name, d.doctor_name, a.app_date, a.app_time " +
                 "FROM appointments a " +
                 "JOIN patients p ON a.patient_id = p.patient_id " +
                 "JOIN doctors d ON a.doctor_id = d.doctor_id " +
                 "ORDER BY a.app_date, a.app_time";
    Statement stmt = getConnection().createStatement();
    return stmt.executeQuery(sql);
}

public static ResultSet searchPatient(String keyword) throws SQLException {
    String sql = "SELECT * FROM patients WHERE name LIKE ? OR phone LIKE ? OR email LIKE ?";
    PreparedStatement pstmt = getConnection().prepareStatement(sql);
    String searchKeyword = "%" + keyword + "%";
    pstmt.setString(1, searchKeyword);
    pstmt.setString(2, searchKeyword);
    pstmt.setString(3, searchKeyword);
    return pstmt.executeQuery();
}



public static boolean addDoctor(String name, String availableFrom, String availableTo) {
    String sql = "INSERT INTO doctors (doctor_name, available_from, available_to) VALUES (?, ?, ?)";
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, name);
        pstmt.setString(2, availableFrom);
        pstmt.setString(3, availableTo);
        return pstmt.executeUpdate() > 0;
    } catch (SQLException e) {
        System.out.println("Add doctor error: " + e.getMessage());
        return false;
    }
}

public static ResultSet getAllDoctors() throws SQLException {
    return getConnection().createStatement().executeQuery("SELECT * FROM doctors");
}

public static boolean deleteDoctor(int doctorId) {
    String sql = "DELETE FROM doctors WHERE doctor_id = ?";
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, doctorId);
        return pstmt.executeUpdate() > 0;
    } catch (SQLException e) {
        System.out.println("Delete doctor error: " + e.getMessage());
        return false;
    }
}

public static boolean updatePatient(int patientId, String name, String phone, String email, String age, String gender) {
    String sql = "UPDATE patients SET name=?, phone=?, email=?, age=?, gender=? WHERE patient_id=?";
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, name);
        pstmt.setString(2, phone);
        pstmt.setString(3, email);
        pstmt.setInt(4, Integer.parseInt(age));
        pstmt.setString(5, gender);
        pstmt.setInt(6, patientId);
        return pstmt.executeUpdate() > 0;
    } catch (SQLException | NumberFormatException e) {
        System.out.println("Update patient error: " + e.getMessage());
        return false;
    }
}

public static boolean deletePatient(int patientId) {
    String sql = "DELETE FROM patients WHERE patient_id = ?";
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, patientId);
        return pstmt.executeUpdate() > 0;
    } catch (SQLException e) {
        System.out.println("Delete patient error: " + e.getMessage());
        return false;
    }
}

}