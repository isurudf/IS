<%-- 
    Document   : logout
    Created on : Oct 10, 2013, 6:39:48 PM
    Author     : Kasun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
String value2=null;
    Cookie cookies2[] = request.getCookies();
    if (cookies2 != null) {
        for (int i = 0; i < cookies2.length; i++) {
            if (cookies2[i].getName().equals("user")||cookies2[i].getName().equals("admin")) {
                cookies2[i].setMaxAge(0);
                response.addCookie(cookies2[i]);
                break;
            }
        }
    }
response.sendRedirect("index.jsp");
%>