package chatbot_backend.servlets;

import chatbot_backend.dao.StudentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/student-register")
public class StudentRegisterServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/plain");
    	PrintWriter out = response.getWriter();
    	
    	String name = request.getParameter("name");
    	String phone = request.getParameter("phone");
    	String email = request.getParameter("email");
    	String password = request.getParameter("password");
    	
    	boolean success = StudentDAO.registerUser(name, phone, email, password, "student");
    	
    	if(success) {
    		out.print("Registered successfully!");
    	}
    	else {
    		out.print("Failed to register!");
    	}
    }
}
