package chatbot_backend.servlets;

import chatbot_backend.dao.FacultyDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/faculty-login")
public class FacultyLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        FacultyDAO dao = new FacultyDAO();
        boolean success = dao.loginUser(email, password, "faculty");

        if (success) {
            out.print("Login is successful!");
        } else {
            out.print("Login failed!");
        }
    }
}