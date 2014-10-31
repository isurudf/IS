package org.fiontar.admin.servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.fiontar.registration.Undergrad;
import org.fiontar.registration.dao.UndergradDA;

public class DeleteServlet extends HttpServlet {

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
        Cookie cookie=null;
        if (cookies2 != null) {
            for (int i = 0; i < cookies2.length; i++) {
                if (cookies2[i].getName().equals(cookieName2)) {
                    value2 = cookies2[i].getValue();
                    cookie=cookies2[i];
                    break;
                }
            }
        }
        /*
        if (value2 != null) {

            Undergrad u = UndergradDA.getUndergrad(value2.substring(0, 32), value2.substring(32, 39));
            MessageDigest md;
            try {
                int id = u.getId();
                md = MessageDigest.getInstance("MD5");
                md.reset();
                md.update(request.getParameter("oldPassword").getBytes());
                byte[] digest = md.digest();
                StringBuilder sb = new StringBuilder();
                for (byte b : digest) {
                    sb.append(Integer.toHexString((int) (b & 0xff)));
                }
                String oldPassword = sb.toString();
                if(oldPassword.equals(u.getPassword())){
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    UndergradDA.delete(id);
                    response.sendRedirect("message.jsp?message=We are sorry to see you go. :(");
                    return;
                }
                else{
                    response.sendRedirect("message.jsp?message=Invalid password");
                    return;
                }
            } catch (NoSuchAlgorithmException ex) {
                
            }
            
            
        }
        
        response.sendRedirect("message.jsp?message=Error in Deleting Account");*/
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
