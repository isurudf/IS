package org.fiontar.admin.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.fiontar.api.Mail.sendMail;
import org.fiontar.registration.Undergrad;
import org.fiontar.registration.dao.UndergradDA;

public class SendNotifications extends HttpServlet {

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
        String value = null;
        Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("admin")) {
                    value = cookies[i].getValue();
                    break;
                } else if (cookies[i].getName().equals("user")) {
                    cookies[i].setMaxAge(0);
                    response.addCookie(cookies[i]);
                    response.setHeader("Refresh", "0; URL=login.jsp?id=You are not logged in!");
                    return;
                }
            }
        }
        if (value == null || !value.equals("rur13admin")) {
            response.setHeader("Refresh", "0; URL=login.jsp?id=You are not logged in!");
            return;
        }
        int count = 0;
        ArrayList<Undergrad> list;
        String emails = "";
        for (int i = 0; i < 16; i++) {
            if (request.getParameter("field" + i) != null) {
                System.out.println("Sending email to field   "+request.getParameter("field"+i));
                list = UndergradDA.getAllUnderGradsByField(request.getParameter("field" + i));
                for (int j = 0; j < list.size(); j++) {
                    count++;
                    emails += list.get(j).getEmail() + ",";
                    if (count % 90 == 0) {
                        sendMail.sendmailBCC(emails, request.getParameter("title"), request.getParameter("text"));
                        emails = "";
                    }
                }
            }
        }
        if (!emails.equals("")) {
           sendMail.sendmailBCC(emails, request.getParameter("title"), request.getParameter("text"));
        }
        response.sendRedirect("message.jsp?message="+count+" emails have been queued for sending. "
                    + "It will take a couple of hours for completion of delivery."
                    + "Make sure you remember the limitations of your email account.");

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
