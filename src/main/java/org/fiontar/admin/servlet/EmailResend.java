package org.fiontar.admin.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.fiontar.api.Database.DatabaseConnectionHandler;
import org.fiontar.registration.Undergrad;
import org.fiontar.registration.UniqueID;
import org.fiontar.registration.dao.UndergradDA;

public class EmailResend extends HttpServlet {

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
        String value=null;
        Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("admin")) {
                    value = cookies[i].getValue();
                    break;
                }
                else if(cookies[i].getName().equals("user")) {
                    cookies[i].setMaxAge(0);
                    response.addCookie(cookies[i]);
                    response.setHeader("Refresh", "0; URL=login.jsp?id=You are not logged in!");
                    return;
                }
            }
        }
        if(value==null||!value.equals("rur13admin")){
            response.setHeader("Refresh", "0; URL=login.jsp?id=You are not logged in!");
            return;
        }
        String[] arr = request.getParameter("id").split(",");
        for (int i = 0; i < arr.length; i++) {
                Undergrad u = UndergradDA.getUndergrad(arr[i]);
                UndergradDA.sendVerification(u);
        }
        
        try {
            DatabaseConnectionHandler.closeConnection();
            Connection con = DatabaseConnectionHandler.createConnection();

            String queryCheck = "SELECT id FROM undergrad ";

            PreparedStatement ps = con.prepareStatement(queryCheck);

            ResultSet rs = ps.executeQuery();
            String a,b;
            ArrayList<String> list = new ArrayList<String>();
            while (rs.next()) {
                a = rs.getString("id");
                System.out.println(a);
                list.add(a);
            }
            con.close();
            
            Connection con2 = DatabaseConnectionHandler.createConnection();

            String queryCheck2 = "UPDATE undergrad "
                    + "SET verification = ? "
                    + "WHERE id = ? ";

            PreparedStatement ps2 = con2.prepareStatement(queryCheck2);
            for(int i=0;i<list.size();i++) {
                a = list.get(i);
                b=UniqueID.generate();
                System.out.println(a+"  "+b);
                ps2.setString(1, b);
                ps2.setString(2, a);
                ps2.executeUpdate();
            }
            
            con2.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
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
