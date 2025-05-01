package org.example.doctormerosathi.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.doctormerosathi.services.Authservice;
import java.io.IOException;


//To logout user and invalidate user session
@WebServlet(name = "LogoutServlet", value = "/logout")
public class logoutservlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Call the logout service
        Authservice.logout(request);

        // redirect the user to the login page
        response.sendRedirect(request.getContextPath() +"/login");
    }
}
