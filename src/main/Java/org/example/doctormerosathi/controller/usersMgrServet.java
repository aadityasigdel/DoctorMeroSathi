package org.example.doctormerosathi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/usersmgr")
public class usersMgrServet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Just forward the request to the JSP page
        request.getRequestDispatcher("/WEB-INF/view/usersMgr.jsp").forward(request, response);
    }
}
