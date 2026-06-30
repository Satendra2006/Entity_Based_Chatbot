package chatbot_backend.dao;

import java.sql.*;
import chatbot_backend.DBConnection;

public class StudentDAO {

    public static boolean registerUser(String name, String phone, String email, String password, String role) {
        try {
            Connection conn = DBConnection.getConnection();

            String query = "INSERT INTO users(name, phone, email, password, role) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setString(5, role);

            int rows = ps.executeUpdate();
            conn.close();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean loginUser(String email, String password, String role) {
        try {
            Connection conn = DBConnection.getConnection();

            String query = "SELECT * FROM users WHERE email=? AND password=? AND role=?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, role);

            ResultSet rs = ps.executeQuery();

            boolean result = rs.next();
            conn.close();

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}