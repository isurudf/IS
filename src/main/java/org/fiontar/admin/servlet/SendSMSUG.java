package org.fiontar.admin.servlet;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.fiontar.admin.algo.Assign;
import org.fiontar.admin.algo.ug;

public class SendSMSUG extends HttpServlet {

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
    public static String urlParameters;
    public static int count=0;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
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
        count=0;
        Assign.assign();
        ug[] arr = Assign.arrUG;
        for(int i=0;i<arr.length;i++){
            String str1 = "XML=\n"
                    + "<SMS>\n"
                    + "<authentication>\n"
                    + "<username>RotaractClub</username>\n"
                    + "<password>Cc6yAhUu</password>\n"
                    + "</authentication>\n"
                    + "<message>\n"
                    + "<sender>WebSMS</sender>\n"
                    + "<text>Your assigned companies are "+arr[i].getAssigned(0)+", "+arr[i].getAssigned(1)+", "+arr[i].getAssigned(2)+"</text>\n"
                    + "<recipients>\n";

            String str3 = "</recipients>\n"
                    + "</message>\n"
                    + "</SMS>";
            String str2 ;
            try{
            str2= "<gsm>94" + arr[i].getPhone().substring(1)+ "</gsm>\n";
            }
            catch(Exception e){
                continue;
            }
            
            count++;
            urlParameters = str1 + str2 + str3;
            
            
            
                System.out.println(urlParameters);
      String req = "http://api.websms.lk/api/v3/sendsms/xml";
            URL url = new URL(req);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoOutput(true);
            /*connection.setDoInput(true);
             connection.setInstanceFollowRedirects(false); 
             connection.setRequestMethod("POST"); 
             connection.setRequestProperty("Content-Type", "application/xml"); 
             connection.setRequestProperty("charset", "utf-8");
             connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
             connection.setUseCaches (false);*/


            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            connection.disconnect();
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            
            wr.close();
            reader.close();}
            
        
        
                    

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
