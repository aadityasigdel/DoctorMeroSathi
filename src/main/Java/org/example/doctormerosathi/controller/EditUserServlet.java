package org.example.doctormerosathi.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.doctormerosathi.model.UsersModel;
import org.example.doctormerosathi.services.UserService;

import java.io.IOException;

@WebServlet("/admin/editUser")
public class EditUserServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int userId = Integer.parseInt(request.getParameter("id"));
            UsersModel user = userService.getUserById(userId);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/view/editUser.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/usersmgr");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int userId = Integer.parseInt(request.getParameter("id"));
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String role = request.getParameter("role");

        try {
            userService.updateUser(userId, fullName, email, role);
            response.sendRedirect(request.getContextPath() + "/admin/usersmgr");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to update user.");
            request.getRequestDispatcher("/WEB-INF/view/editUser.jsp").forward(request, response);
        }
    }
}
