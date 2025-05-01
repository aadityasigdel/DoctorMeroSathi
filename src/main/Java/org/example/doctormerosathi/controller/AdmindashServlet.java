package org.example.doctormerosathi.controller;

import org.example.doctormerosathi.model.Admindash;
import org.example.doctormerosathi.services.DashboardService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;


//to display admin dashboard with dynamic contents
@WebServlet("/admin/dashboard")
public class AdmindashServlet extends HttpServlet {
    private DashboardService dashboardService;

    @Override
    public void init() {
        this.dashboardService = new DashboardService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Admindash dashboardStats = dashboardService.getDashboardData();
        request.setAttribute("dashboardStats", dashboardStats);

        request.getRequestDispatcher("/WEB-INF/view/Admindash.jsp").forward(request, response);
    }
}