<%@page import="org.fiontar.registration.User"%>
<%@page import="org.fiontar.admin.allocate.Assign"%>

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
        Assign.initialise();
%>
<%@ include file="up.jsp" %>

<div class = "dashboard">
    <div>
        <% 
        String message = "Assigning interviews is completed!<br><br>Redirecting now...";
        out.print("<h5 align=\"center\">"+message+"</h5>");
        response.setHeader("Refresh", "3; URL=admin.jsp");
        %>
    </div>
    <%@ include file="down.jsp" %>
    <%} %>