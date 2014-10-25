package org.fiontar.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.fiontar.registration.Undergrad;
import org.fiontar.registration.dao.UndergradDA;

public class SettingsServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    static final long serialVersionUID = 1L;
    private static final int BUFSIZE = 4096;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cookieName2 = "user", value2 = null;
        Cookie cookies2[] = request.getCookies();
        Cookie cookie = null;
        if (cookies2 != null) {
            for (int i = 0; i < cookies2.length; i++) {
                if (cookies2[i].getName().equals(cookieName2)) {
                    value2 = cookies2[i].getValue();
                    cookie = cookies2[i];
                    break;
                }
            }
        }
/*
        try {
            if (value2 != null) {        
                String name = request.getParameter("firstName") + " " + request.getParameter("lastName");
                System.out.println("Updating name "+name+" "+value2.substring(32,39));
                UndergradDA.updateNameAddr(value2.substring(0, 32), value2.substring(32, 39), name, request.getParameter("address"));
                response.sendRedirect("message.jsp?message=Your name and address have been changed successfully");
            } else {
                response.sendRedirect("login.jsp?message=You are not logged in!");
            }

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            response.sendRedirect("message.jsp?message=There was an error in changing your name and address. Please try again in a few minutes.");
        }*/
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
