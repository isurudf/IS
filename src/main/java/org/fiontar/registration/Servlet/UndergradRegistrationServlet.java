package org.fiontar.registration.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.fiontar.registration.Undergrad;
import org.fiontar.registration.UniqueID;
import org.fiontar.registration.dao.UndergradDA;

public class UndergradRegistrationServlet extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        //comment if registration is open
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
            response.sendRedirect("message.jsp?message=Hey, registration is closed. There's nothing you can do, buddy. goo.gl/1ZdTOs");
            System.out.println("Idiot " + request.getParameter("name") + " " + request.getParameter("index"));
            return;
        }
        //end 

        
        try {
            System.out.println("Undergrad Registration");
            if (UniqueID.searchIndex(request.getParameter("index"), request.getParameter("email"))) {
                System.out.println("Index or Email is already registered. " + request.getParameter("index") + "   " + request.getParameter("email"));
                response.sendRedirect("message.jsp?message=Your index or email is already registered. Contact us for more details");
                response.setHeader("Refresh", "5; URL=undergradRegistration.jsp");
                return;
            }
        } catch (Exception e) {
            response.sendRedirect("message.jsp?message=Error in registration. Please try again in a few minutes.");
            return;
        }

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
        }
        md.reset();
        md.update(request.getParameter("password").getBytes());
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(Integer.toHexString((int) (b & 0xff)));
        }
        Undergrad s = new Undergrad(
                request.getParameter("index"),
                request.getParameter("email"),
                request.getParameter("name"),
                sb.toString(),
                request.getParameter("addr"),
                request.getParameter("phone"),
                " ",
                1,
                UniqueID.generate(),
                0,
                " ",
                request.getParameter("field"),
                " ",
                UniqueID.generate(),
                Float.parseFloat(request.getParameter("gpa")));
        try {
            UndergradDA.addUndergrad(s);
            System.out.println("Undergrad registered " + s.getEmail());
            Cookie cookie = new Cookie("user", s.getVerification() + s.getIndex());
            cookie.setMaxAge(-1);
            response.addCookie(cookie);
            response.sendRedirect("undergradDashboard.jsp");
        } catch (Exception ex) {
            System.out.println("Error " + request.getParameter("index") + " " + request.getParameter("phone") + " " + request.getParameter("email"));
            response.sendRedirect("message.jsp?message=Error in registration. Please try again in a few minutes.");

        }
          
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
