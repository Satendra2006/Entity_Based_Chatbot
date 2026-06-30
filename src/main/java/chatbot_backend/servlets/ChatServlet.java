package chatbot_backend.servlets;

import chatbot_backend.ApiClient;
import chatbot_backend.dao.ChatDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/chat")
public class ChatServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String userMessage = request.getParameter("message");

        System.out.println("User: " + userMessage);

        // 🔥 Call Flask API
        String flaskResponse = ApiClient.getBotResponse(userMessage);

        System.out.println("Flask: " + flaskResponse);

        String intent = "unknown";
        String entity = "general";

        try {
            intent = flaskResponse.split("\"intent\":\"")[1].split("\"")[0];
            entity = flaskResponse.split("\"entity\":\"")[1].split("\"")[0];
        } catch (Exception e) {
            System.out.println("Parsing failed");
        }

        System.out.println("Intent: " + intent);
        System.out.println("Entity: " + entity);

        // 🔥 Fetch response
        String finalResponse = ChatDAO.getResponse(intent, entity);

        if (finalResponse == null) {
            finalResponse = "Sorry, I couldn't find that information.";
        }

        // 🔥 Save chat log
        HttpSession session = request.getSession(false);
        int userId = 0;

        if (session != null && session.getAttribute("userId") != null) {
            userId = (int) session.getAttribute("userId");
        }

        ChatDAO.saveChat(userId, userMessage, intent, entity, finalResponse);

        // 🔥 Escape JSON
        finalResponse = finalResponse.replace("\"", "\\\"");

        out.print("{\"response\":\"" + finalResponse + "\"}");
    }
}