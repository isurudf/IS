<%@page import="org.fiontar.registration.User"%>
<%@page import="org.fiontar.registration.dao.UndergradDA"%>


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
        if(request.getParameter("reset")!=null)
            UndergradDA.resetPassword(request.getParameter("reset"));
        if(request.getParameter("removePhone")!=null)
            UndergradDA.removePhone(request.getParameter("removePhone"));

%>



<%@ include file="up.jsp" %>

<div class = "dashboard">
    <h1>Reset Password</h1>
    <form method="post" action="adminReset.jsp" >


        <input type="text" name="reset" placeholder="Enter Index. Pw will be reset to 123"></input><br></br>
        <input type="text" name="removePhone" placeholder="Enter Index. Phone number will be removed."></input><br></br>

        <p align="left"> <input type="submit" value="Submit" class="button"/>
            <input type="reset" value="Clear" class="button"/>

    </form>

</div>



<%@ include file="down.jsp" %>
<%} %>