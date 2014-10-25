<%@page import="org.fiontar.admin.Company.CompanyDA"%>
<%@page import="org.fiontar.admin.Company.Company"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.fiontar.admin.servlet.SendSMS"%>

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
<script type="text/javascript">

    function select() {
        for (l = 0; l < 16; l++) {
            document.getElementById(l).checked = true;
        }
    }
    

</script>



<%@ include file="up.jsp" %>
<div class="companies">
    <h2>Send SMS</h2>
    
    <h6>There are <%=SendSMS.count %> SMSs to be sent. The message is 
        "<%=request.getParameter("message") %>". Do you really want to do this?</h6>
    
        <a href="SendSMS?send=true" ><button type="button">Send SMS</button></a>
        <a href="SendSMS?send=false" ><button type="button">Cancel</button></a>
        <br/>
        <%=SendSMS.urlParameters %>
</p>

</div>

<%@ include file="down.jsp" %>
<%} %>