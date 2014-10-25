package org.fiontar.admin.servlet;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.fiontar.admin.Company.Company;
import org.fiontar.admin.Company.CompanyDA;
import org.fiontar.admin.algo.Assign;
import org.fiontar.registration.Undergrad;
import org.fiontar.registration.dao.UndergradDA;

public class SendSMS extends HttpServlet {

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
        if (request.getParameter("send") == null) {
            if (request.getParameter("text").length() > 160) {
                response.setHeader("Refresh", "0; URL=message.jsp?message=Number of characters in a SMS should be less than 160");
                return;
            }
            if(request.getParameter("password")==null||!request.getParameter("password").startsWith("Cc6yAhU")){
                response.setHeader("Refresh", "0; URL=message.jsp?message=Invalid password");
                return;
            }
            HashSet<Integer> numbers = new HashSet();
            ArrayList<Undergrad> list;
            for (int i = 0; i < 16; i++) {
                if (request.getParameter("field" + i) != null) {
                    System.out.println("Sending SMS to UG by Field " + request.getParameter("field" + i));
                    list = UndergradDA.getAllUnderGradsByField(request.getParameter("field" + i));
                    for (int j = 0; j < list.size(); j++) {
                        if (list.get(j).getPhone().length() == 10) {
                            numbers.add(Integer.parseInt(list.get(j).getPhone()));
                        }
                    }
                }
            }

            if (request.getParameter("pref") != null && !request.getParameter("pref").equals("")) {
                String company = request.getParameter("pref");
                if (request.getParameter("fieldComp") != null) {
                    CompanyDA.getCompany();
                    Company comp = CompanyDA.getCompanyByName(company);
                    String[] arr = comp.getFields().split(",");
                    for (int i = 1; i < arr.length; i++) {
                        list = UndergradDA.getAllUnderGradsByField(arr[i]);
                        for (int j = 0; j < list.size(); j++) {
                            if (list.get(j).getPhone().length() == 10) {
                                numbers.add(Integer.parseInt(list.get(j).getPhone()));
                            }
                        }
                    }
                }
                if (request.getParameter("fieldAssign") != null) {
                    Assign.assign();
                    numbers.addAll(Assign.getNumbersAssigned(company));
                }
                if (request.getParameter("fieldPref") != null) {
                    Assign.assign();
                    numbers.addAll(Assign.getNumbersPreferred(company));
                }
                if (request.getParameter("coord") != null) {
                    CompanyDA.getCompany();
                    Company comp = CompanyDA.getCompanyByName(company);
                    numbers.add(Integer.parseInt(comp.getBuildTel()));
                    numbers.add(Integer.parseInt(comp.getTel()));
                }
                if (request.getParameter("allBuild") != null) {
                    ArrayList<Company> compList = CompanyDA.getCompany();
                    for (int j = 0; j < compList.size(); j++) {
                        numbers.add(Integer.parseInt(compList.get(j).getBuildTel()));
                    }
                }
                if (request.getParameter("allComp") != null) {
                    ArrayList<Company> compList = CompanyDA.getCompany();
                    for (int j = 0; j < compList.size(); j++) {
                        numbers.add(Integer.parseInt(compList.get(j).getTel()));
                    }
                }
                if (request.getParameter("commaNumbers") != null) {
                    String[] arr = request.getParameter("commaNumbers").split(",");
                    for(int i=0;i<arr.length;i++){
                        if(arr[i]!=null&&arr[i].length()==10){
                            numbers.add(Integer.parseInt(arr[i]));
                        }
                    }
                }
                

            }

            String str1 = "XML=\n"
                    + "<SMS>\n"
                    + "<authentication>\n"
                    + "<username>RotaractClub</username>\n"
                    + "<password>"+request.getParameter("password")+"</password>\n"
                    + "</authentication>\n"
                    + "<message>\n"
                    + "<sender>WebSMS</sender>\n"
                    + "<text>" + request.getParameter("text") + "</text>\n"
                    + "<recipients>\n";

            String str3 = "</recipients>\n"
                    + "</message>\n"
                    + "</SMS>";
            String str2 = "";
            System.out.println("asdasd");
            int cnt= numbers.size();
            Iterator itr = numbers.iterator();
            while (itr.hasNext()) {
                int number = (Integer) (itr.next());
                str2 += "<gsm>94" + number + "</gsm>\n";
            }
            urlParameters = str1 + str2 + str3;
            count=cnt;
            response.sendRedirect("adminSMS_1.jsp?message="+request.getParameter("text"));
        } 
        
        else if (request.getParameter("send").equals("false")) {
            urlParameters = null;
            count=0;
        } 
        
        else if (request.getParameter("send").equals("true")) {
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
            while ((line = reader.readLine()) != null) {
                out.print(line);
            }
            wr.close();
            reader.close();
            urlParameters=null;
            count=0;
                    
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
