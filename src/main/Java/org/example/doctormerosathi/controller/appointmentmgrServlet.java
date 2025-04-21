package org.example.doctormerosathi.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/admin/appointmentmgr")
public class appointmentmgrServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Just forward the request to the JSP page
        request.getRequestDispatcher("/WEB-INF/view/appointmentmgr.jsp").forward(request, response);
    }

}
