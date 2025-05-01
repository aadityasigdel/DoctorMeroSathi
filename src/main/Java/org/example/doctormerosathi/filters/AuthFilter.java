package org.example.doctormerosathi.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

@WebFilter(filterName = "AuthFilter", urlPatterns = {
        "/admin/dashboard",
        "/admin/usersmgr",
        "/admin/appointmentmgr"
})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        boolean isAuthenticated = false;
        boolean isAdmin = false;

        if (session != null) {
            Object userObj = session.getAttribute("user");

            if (userObj instanceof Map<?, ?>) {
                String role = (String) ((Map<?, ?>) userObj).get("role");
                isAuthenticated = role != null;
                isAdmin = "admin".equalsIgnoreCase(role);
            } else if (userObj != null) {
                try {
                    String role = (String) userObj.getClass().getMethod("getRole").invoke(userObj);
                    isAuthenticated = role != null;
                    isAdmin = "admin".equalsIgnoreCase(role);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if (isAuthenticated && isAdmin) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/access-denied");
        }
    }

}
