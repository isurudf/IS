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
<div class="dashboard">
 <h2>Upload Your Resume</h2>
    <form name="addFile" action="UploadServlet" method="post" id="ContactForm" enctype="multipart/form-data">
        <h5>Please upload your files </h5>
            <input type="file" name="uploadFile" class="" />
            <input type="hidden" name="fileType" value ="templates" class=""/>
            <br/><strong>(Maximum file size: Ask Rilwan)</strong><br/>
            <p align="right">  
                <input type="submit" value="Upload" class="button"/>
            </p> 
    </form>
</div>
<%@ include file="down.jsp" %>
<%} %>