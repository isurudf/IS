package org.fiontar.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.fiontar.api.Mail.sendMail;
import org.fiontar.registration.UniqueID;
import org.fiontar.registration.dao.UndergradDA;

public class ForgotPwdServlet extends HttpServlet {

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
        
        String link=UniqueID.generate();
        String email = request.getParameter("email");
        String message;
        if(UndergradDA.changePwdLink(request.getParameter("email"), link))
        {
        sendMail.sendmail(email, "Password Change for RUR 2013",
                "If you did not register for Are You Ready? 2013, please ignore this email.\n\n"
                + "Please click the following to change your password. \n\n\n"
                + "http://www.areyouready.uom.lk/reset.jsp?id="
                + link
                + "\n\nRtr. Himantha Alahakoon,\n"
                + "Project Co-Chairperson,\n"
                + "Are you Ready? 2013,\n"
                + "Official Careers Fair,\n"
                + "University of Moratuwa\n");
            message="An email has been sent to this email with password reset link.";
        }
        else
            message = "Sorry, there is no account associated with this email address. Please contact us via admin@rotaractmora.org if this is in error.";
        response.sendRedirect("message.jsp?message="+ message);
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
