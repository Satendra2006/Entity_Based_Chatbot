package chatbot_backend;

import java.sql.*;

public class DBConnection {

    private static String url = "jdbc:postgresql://localhost:5432/campus_db";
    private static String user = "postgres";
    private static String pass = "24becc16";

    public static Connection getConnection() {

        Connection conn = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected ✅");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }
}