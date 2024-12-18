package aston.lesson03;

import aston.lesson03.DAO.StudentDAO;
import aston.lesson03.model.Student;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

@WebServlet(urlPatterns = "/student")
public class StudentServlet extends HttpServlet {
    private final StudentDAO studentDAO;

    public StudentServlet() {
        studentDAO = new StudentDAO();
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listStudents(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        studentDAO.addStudent(student);
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        Student student = studentDAO.getStudent(id);
        if (student != null) {
            student.setFirstName(firstName);
            student.setLastName(lastName);
            studentDAO.updateStudent(student);
            response.getWriter().println("Student updated successfully");
        } else {
            response.getWriter().println("Student not found");
        }
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentDAO.deleteStudent(id);
    }


    private void listStudents(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Set<Student> students = studentDAO.getAllStudents();
//        List<Student> studentList = studentDAO.getAllStudentsNPlusOne(); N+1
        PrintWriter writer = response.getWriter();
        for (Student student : students) {
            writer.println(student.getFirstName() + " " + student.getLastName() + " " + student.getAge() + student.getCourseWorks());
        }
    }
}