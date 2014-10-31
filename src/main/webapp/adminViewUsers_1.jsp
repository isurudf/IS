<%@page import="org.fiontar.admin.algo.Assign"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.fiontar.admin.Company.Company"%>
<%@page import="org.fiontar.admin.Company.CompanyDA"%>
<%@page import="org.fiontar.registration.Undergrad"%>
<%@page import="org.fiontar.registration.dao.UndergradDA"%>
<%@page import="org.fiontar.admin.algo.ug"%>
<%@page import="org.fiontar.admin.algo.cmp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
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
    } 
    else{
        String[] fields = {"ARCH", "CPE", "CE", "CSE", "ERM", "EE", "ENTC", "FM", "IT", "FD", "ME", "MECH", "QS", "TC", "TCP", "TLM"};
        
        String[] fieldsReal={"ARCH", "CPE", "CE", "CSE", "EM", "EE", "ENTC", "FM", "IT", "FD", "MT", "ME", "QS", "TM", "TCP", "TLM"};
        
        Assign.assign();
        ug[] arr = Assign.arrUG;
        for(int i=0;i<arr.length;i++){
%>
        <% if(i%2==0){ %>
        <div style="" >
        <% }else{ %>
        <div>

        <%}%>
    
</div>


        <%}} %>