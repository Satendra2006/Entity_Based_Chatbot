package chatbot_backend.servlets;

import chatbot_backend.dao.FacultyDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/faculty-register")
public class FacultyRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean success = FacultyDAO.registerUser(name, phone, email, password, "faculty");

        if (success) {
            out.print("Successfully Registered!");
        } else {
            out.print("Failed to register!");
        }
    }
}