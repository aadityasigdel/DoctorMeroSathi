package org.example.doctormerosathi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.doctormerosathi.model.UsersModel;
import org.example.doctormerosathi.services.Authservice;
import java.io.IOException;

//to display home page content with dynamic changes
@WebServlet(name = "Home", value = "/home")
public class Home extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check if the user is authenticated
        if (!Authservice.isAuthenticated(request)) {
            // Redirect to login page
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Get the current session
        UsersModel user = Authservice.getCurrentUser(request);

        //  redirect to login page
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/view/Home.jsp").forward(request, response);
    }
}

