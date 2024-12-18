package aston.lesson03;

import aston.lesson03.DAO.LecturerDAO;
import aston.lesson03.model.Lecturer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/lecturer")
public class LecturerServlet extends HttpServlet {

    private final LecturerDAO lecturerDAO;

    public LecturerServlet() {
        lecturerDAO = new LecturerDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listOfLecturers(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        Lecturer lecturer = new Lecturer();
        lecturer.setId(Integer.parseInt(id));
        lecturer.setFirstName(firstName);
        lecturer.setLastName(lastName);
        lecturerDAO.addLecturer(lecturer);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        Lecturer lecturer = lecturerDAO.getLecturer(Integer.parseInt(id));
        if (lecturer != null) {
            lecturer.setFirstName(firstName);
            lecturer.setLastName(lastName);
            lecturerDAO.updateLecturer(lecturer);
            response.getWriter().println("Lecturer Updated Successfully");
        } else response.getWriter().println("Lecturer Not Found");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        lecturerDAO.deleteLecturer(id);
    }

    private void listOfLecturers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Lecturer> lecturers = lecturerDAO.getAllLecturers();
        PrintWriter out = response.getWriter();
        for (Lecturer lecturer : lecturers) {
            out.println(lecturer.getFirstName() + " " + lecturer.getLastName());
        }
    }
}
