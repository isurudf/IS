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
<h2> Admin Panel </h2><br/>
<div class = "company">
    <a href="adminNotification.jsp"><h4>Send Email</h4></a><br/>
    <a href="adminSMS.jsp"><h4>Send SMS</h4></a><br/>
    <a href="adminViewUsers.jsp?field=all&company=all"><h4>View Users</h4></a><br/>
    <a href="companyStats.jsp"><h4>View Company Stats</h4></a><br/>
    <a href="companyDashboard.jsp"><h4>Add Companies</h4></a><br/>
    <a href="companyEdit.jsp"><h4>Edit Companies</h4></a><br/>
    <a href="logout.jsp"><h4>Logout</h4></a><br/>
    <a href="adminViewQs.jsp"><h4>View Questions</h4></a><br/>
    <a href="adminVerificationEmail.jsp"><h4>Email Verification Resend</h4></a><br/>
    <a href="adminReset.jsp"><h4>Reset Passwords</h4></a><br/>
    <a href="adminUgRegistration.jsp"><h4>Add Undergrads</h4></a><br/>
    <a href="adminViewUsers_1.jsp"><h4>Undergrad sheets</h4></a><br/>
    <a href="adminViewUsers_3.jsp"><h4>Company sheets</h4></a><br/>
    <a href="adminViewUsers_2.jsp"><h4>Registration sheets</h4></a><br/>
</div>
<%@ include file="down.jsp" %>
<%} %>