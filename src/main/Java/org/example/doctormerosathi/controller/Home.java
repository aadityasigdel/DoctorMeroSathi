package org.example.doctormerosathi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "Home", value = "/home")
public class Home extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to home.jsp or return a welcome message
        request.getRequestDispatcher("/WEB-INF/view/Home.jsp").forward(request, response);
    }
}

