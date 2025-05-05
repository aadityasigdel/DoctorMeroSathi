package org.example.doctormerosathi.controller;

import org.example.doctormerosathi.model.UsersModel;
import org.example.doctormerosathi.services.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;


//To get user list for admin and provide edit function
@WebServlet("/admin/usersmgr")
public class UpdateUserServlet extends HttpServlet {
    private UserService userService = new UserService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<UsersModel> users = userService.getAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/view/usersMgr.jsp").forward(request, response);
    }


}
