package controller;

import model.Student;
import service.StudentService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(urlPatterns = "/edit")
public class EditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Student student = StudentService.findStudentById(id);
        req.setAttribute("student", student);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/formEdit.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        LocalDate dateOfBirth = LocalDate.parse(req.getParameter("dateOfBirth"));
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        int classroom = Integer.parseInt(req.getParameter("classroom"));
        StudentService.edit(new Student(id,name,dateOfBirth,address,phone,email,classroom));
        resp.sendRedirect("/showList");
    }
}
