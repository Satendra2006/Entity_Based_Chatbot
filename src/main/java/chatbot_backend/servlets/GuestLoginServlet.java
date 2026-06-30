package chatbot_backend.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/guest-login")
public class GuestLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String purpose = request.getParameter("purpose");

        if (name != null && !name.isEmpty() &&
            phone != null && !phone.isEmpty() &&
            purpose != null && !purpose.isEmpty()) {

            out.print("SUCCESS");
        } else {
            out.print("FAIL");
        }
    }
}