<%@page import="org.fiontar.registration.User"%>


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
%>



<%@ include file="up.jsp" %>

<div class = "dashboard">
    <h1>Send Email Verification Link</h1>
    <form method="post" action="EmailResend" >
        <h4>Enter comma separated index numbers with no spaces</h4>

        <textarea name="id" rows =10 cols =60 style="resize:vertical" ></textarea><br></br>

                <p align="left"> <input type="submit" value="Send" class="button"/>
                    <input type="reset" value="Clear" class="button"/>
</p>
                    </form>

                </div>



                <%@ include file="down.jsp" %>
                <%} %>