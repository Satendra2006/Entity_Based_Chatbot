package chatbot_backend.dao;

import java.sql.*;

import chatbot_backend.DBConnection;

public class ChatDAO {

    // 🔥 Get intent_id
    public static int getIntentId(String intentName) {
        int id = -1;
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT id FROM intents WHERE name = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, intentName);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    // 🔥 Validate entity using entities table
    public static boolean isValidEntity(String entity, int intentId) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM entities WHERE name = ? AND intent_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, entity);
            ps.setInt(2, intentId);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 🔥 MAIN RESPONSE FETCH
    public static String getResponse(String intent, String entity) {

        String response = null;

        try {
            Connection conn = DBConnection.getConnection();

            int intentId = getIntentId(intent);

            // ✅ Validate entity
            if (!isValidEntity(entity, intentId)) {
                entity = "general";  // fallback entity
            }

            // 🔥 MAIN QUERY
            String sql = "SELECT value FROM knowledge_base WHERE intent_id = ? AND entity = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, intentId);
            ps.setString(2, entity);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                response = rs.getString("value");
            } else {
                // 🔥 fallback (intent only)
                String fallback = "SELECT value FROM knowledge_base WHERE intent_id = ? LIMIT 1";
                PreparedStatement ps2 = conn.prepareStatement(fallback);
                ps2.setInt(1, intentId);

                ResultSet rs2 = ps2.executeQuery();
                if (rs2.next()) {
                    response = rs2.getString("value");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    // 🔥 SAVE CHAT LOG
    public static void saveChat(int userId, String query, String intent, String entity, String response) {
        try {
            Connection conn = DBConnection.getConnection();

            String sql = "INSERT INTO chat_logs(user_id, query, intent, entity, response) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, userId);
            ps.setString(2, query);
            ps.setString(3, intent);
            ps.setString(4, entity);
            ps.setString(5, response);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}