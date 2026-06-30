package chatbot_backend;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class ApiClient {

    // Method to send user message dynamically
    public static String getBotResponse(String userMessage) {
        String response = "";

        try {
            URL url = new URI("http://localhost:5000/predict").toURL();

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // JSON input
            String jsonInput = "{\"message\":\"" + userMessage + "\"}";

            OutputStream os = conn.getOutputStream();
            os.write(jsonInput.getBytes());
            os.flush();
            os.close();

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );

            String line;
            StringBuilder result = new StringBuilder();

            while ((line = br.readLine()) != null) {
                result.append(line);
            }

            br.close();

            response = result.toString();

        } catch (Exception e) {
            e.printStackTrace();
            response = "{\"reply\":\"Error connecting to chatbot\"}";
        }

        return response;
    }
}