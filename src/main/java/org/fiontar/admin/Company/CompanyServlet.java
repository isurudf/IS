package org.fiontar.admin.Company;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fiontar.api.Mail.sendMail;
import org.fiontar.registration.Undergrad;
import org.fiontar.registration.dao.UndergradDA;

public class CompanyServlet extends HttpServlet {

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
        
        if(request.getParameter("addstu").equals("true")){
        	String cmp = request.getParameter("company");
        	Company company = CompanyDA.getCompanyByName(cmp);
        	ArrayList<Undergrad> undlist = UndergradDA.getAllUnderGrads();
        	ArrayList<UGPref> prefList = new ArrayList<UGPref>();
        	
        	String undergrads = null;
        	
        	for(int i = 0; i < undlist.size(); ++i){
        		Undergrad a = undlist.get(i);
        		if(request.getParameter("undergrad"+a.getId()) != null){
        			prefList.add(new UGPref(a.getIndex(), Integer.parseInt(request.getParameter("ugPref"+a.getId()))));
        		}
        	}
        	Collections.sort(prefList);
        	undergrads = prefList.get(0).index;
        			
        	for(int i = 1; i < prefList.size(); ++i){
        		undergrads += ","+prefList.get(i).index;
        	}
        	
        	company.setUndergrads(undergrads);
        	CompanyDA.updateCompany(company);
        	response.setHeader("Refresh", "0; URL=companyEdit.jsp");
        	return;
        }
        
        if (request.getParameter("name") != null) {
            String fields = ",";

            for (int i = 0; i < 16; i++) {
                if (request.getParameter("field" + i) != null) {
                    fields += request.getParameter("field" + i) + ",";
                }
            }
            System.out.println(request.getParameter("name") + "  " + fields);
            
            try {
                CompanyDA.addCompany(request.getParameter("name"),
                        request.getParameter("description"),
                        request.getParameter("shortName"),
                        request.getParameter("logo"),
                        fields);
                CompanyDA.processCompany();
            } catch (SQLException ex) {
                System.out.println(ex.getLocalizedMessage());
                return;
            }

            ArrayList<Undergrad> list;
            String emails = "";
            int count = 0;
            for (int i = 0; i < 16; i++) {
                if (request.getParameter("field" + i) != null) {
                    list = UndergradDA.getAllUnderGradsByField(request.getParameter("field" + i));
                    for (int j = 0; j < list.size(); j++) {
                        emails += list.get(j).getEmail() + ",";
                        count++;
                        if (count %90==0) {

                            sendMail.sendmailBCC(emails, "Are you Ready? 2013 : New Company " + request.getParameter("name") + " Added",
                                    request.getParameter("name") + " has verified their presence at Are You Ready ? 2013.\n\n"
                                    + "The company profile is as follows,\n\n"
                                    + request.getParameter("description") + "\n\n"
                                    + "You can now register for this company at our website, \n\n"
                                    + "http://www.areyouready.uom.lk/undergradDashboard.jsp"
                                    + "\n\nRtr. Sumudu Herath,\n\n"
                                    + "Project Co-Chairperson,\n"
                                    + "Are you Ready? 2013,\n"
                                    + "Official Careers Fair,\n"
                                    + "University of Moratuwa\n");

                            emails = "";
                            
                        }
                    }
                }
            }

            if (!emails.equals("")) {
                sendMail.sendmailBCC(emails, "Are you Ready? 2013 : New Company " + request.getParameter("name") + " Added",
                        request.getParameter("name") + " has verified their presence at Are You Ready ? 2013.\n\n"
                        + "The company profile is as follows,\n\n"
                        + request.getParameter("description") + "\n\n"
                        + "You can now register for this company at our website, \n\n"
                        + "http://www.areyouready.uom.lk/undergradDashboard.jsp"
                        + "\n\nRtr. Sumudu Herath,\n"
                        + "Project Co-Chairperson,\n"
                        + "Are you Ready? 2013,\n"
                        + "Official Careers Fair,\n"
                        + "University of Moratuwa\n");
            }
            System.out.println("Company Added ");
            
            response.sendRedirect("message.jsp?message="+count+" emails have been queued for sending. <br>"
                    + "It will take a couple of hours for completion of delivery.<br>"
                    + "Make sure you remember the limitations of your email account.");
        }
        else{
            ArrayList<Company> list = CompanyDA.getCompany();
            Company a;
            for(int i=0;i<list.size();i++){
                a= list.get(i);
                if(request.getParameter("edit"+a.getId())!=null){
                    try{
                    a.setName(request.getParameter("name"+a.getId()));
                    a.setDescription(request.getParameter("description"+a.getId()));
                    a.setLogo(request.getParameter("logo"+a.getId()));
                    a.setCapacity(Integer.parseInt(request.getParameter("capacity"+a.getId())));
                    a.setBuilding(request.getParameter("building"+a.getId()));
                    a.setTel(request.getParameter("tel"+a.getId()));
                    a.setBuildTel(request.getParameter("buildTel"+a.getId()));
                    CompanyDA.updateCompany(a);
                    }
                    catch(NumberFormatException e){
                        System.out.println("Number Format Exception for Company capacity");
                    }
                }
            }
            CompanyDA.processCompany();
            response.sendRedirect("companyEdit.jsp");
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

class UGPref implements Comparable<UGPref>{
	public String index;
	public int preference;
	
	
	public UGPref(String index, int preference) {
	    super();
	    this.index = index;
	    this.preference = preference;
    }

	public int compareTo(UGPref o) {
		if(this.preference > o.preference)
			return 1;
		else if(this.preference < o.preference)
			return -1;
		
		return 0;
    }
	
}
