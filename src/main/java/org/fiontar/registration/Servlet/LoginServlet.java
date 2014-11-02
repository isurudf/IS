package org.fiontar.registration.Servlet;

import org.fiontar.api.Database.DatabaseConnectionHandler;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.fiontar.registration.Undergrad;
import org.fiontar.registration.dao.UndergradDA;

public class LoginServlet extends HttpServlet {

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
            throws ServletException, IOException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        
        /**
         * Capture email and password from e-form login.jsp
         */
        
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.reset();
        md.update(request.getParameter("password").getBytes());
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(Integer.toHexString((int) (b & 0xff)));
        }
        
        String indexNum = request.getParameter("index").toUpperCase();
        String password = sb.toString();
        if(indexNum.equals("ADMIN")&& request.getParameter("password").equals("9271rur")){
                Cookie cookie = new Cookie ("admin","rur13admin");
                cookie.setMaxAge(30*24*60*60);
                response.addCookie(cookie);
                System.out.println("Admin Log in");
                response.setHeader("Refresh","0; URL=admin.jsp");
                
                return;
        }
        if(indexNum.equals("ADMIN")){
            response.setHeader("Refresh","0; URL=index.jsp?id=Invalid password.");
            return;
        }

      
        try {
            Connection con = DatabaseConnectionHandler.createConnection();
            /**
             * E-mail/password validation query
             */
            
          String queryCheck = "SELECT * from undergrad WHERE password = ? AND indexNum = ? ";
            System.out.println(password);
            PreparedStatement ps = con.prepareStatement(queryCheck);
            ps.setString(1, password);
            ps.setString(2, indexNum);
            
            ResultSet rs = ps.executeQuery();
            /**
             * Redirection
             */
            if (rs.next()) 
            {    
                System.out.println("Undergrad log in "+indexNum+" "+password+ " success");
                Cookie cookie = new Cookie ("user",rs.getString("verification")+rs.getString("indexNum"));
                con.close();
                cookie.setMaxAge(-1);
                response.addCookie(cookie);
                response.sendRedirect("undergradDashboard.jsp");
              //  response.setHeader("Refresh","0; URL=undergradDashboard.jsp");
            }
            else
            {   
                System.out.println("Undergrad log in "+indexNum+" "+password+ " failure");
                con.close();
                response.setHeader("Refresh","0; URL=index.jsp?id=Invalid email address or password!");
            }
                        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            response.setHeader("Refresh","0; URL=index.jsp?id=Oops, something went wrong. Please try again!");
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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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