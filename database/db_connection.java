package database;
import java.sql.*;

public class db_connection {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://db.fgydeciecbyzvexmvlne.supabase.co:5432/postgres?sslmode=require&preferQueryMode=simple";       
        String user = "postgres";
        String pass = "Satendra@126";

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);

            System.out.println("Connected ✅");

            // TEST QUERY
            String userInput = "where is cse block";  
            String entity = "cse block";
            String query = "SELECT value FROM knowledge_base WHERE entity ILIKE ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, "%" + entity + "%");

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println("Response: " + rs.getString("value"));
        } else {
            System.out.println("No response found ❌");
        }          
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
