package aston.lesson03;

import aston.lesson03.DAO.StudentDAO;
import aston.lesson03.models.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(urlPatterns = "/student/", name = "Servlet")
public class Servlet extends HttpServlet {

    private StudentDAO studentDAO = new StudentDAO();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException {
        String id = request.getParameter("id");
        if(id != null && !id.isEmpty()) {
            try {
                Student student = studentDAO.getStudent(Integer.parseInt(id));
                if(student != null && student.getFirstName() != null && student.getLastName() != null) {
                    response.getWriter().println(student.getId() + " " + student.getFirstName() + " " + student.getLastName());
                } else response.getWriter().println("ID is NULL");
            } catch (NumberFormatException e) {
                response.getWriter().println("Invalid ID");
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        studentDAO.addStudent(id, firstName, lastName);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        studentDAO.updateStudent(Integer.parseInt(id), firstName, lastName);
    }
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        studentDAO.deleteStudent(Integer.parseInt(id));
    }
}
