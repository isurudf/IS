<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.fiontar.registration.User"%>
<%@page import="org.fiontar.registration.Undergrad"%>
<%@page import="org.fiontar.registration.dao.UndergradDA"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String cookieName2 = "user", value2=null;
    Cookie cookies2[] = request.getCookies();
    if (cookies2 != null) {
        for (int i = 0; i < cookies2.length; i++) {
            if (cookies2[i].getName().equals(cookieName2)) {
                value2 = cookies2[i].getValue();
                break;
            }
        }
    }
    if(value2==null){
        response.setHeader("Refresh", "0; URL=login.jsp?id=You're not logged in!");
    }
    else{
        Undergrad s = UndergradDA.getUndergrad(value2.substring(0,32),value2.substring(32,39));

%>
<%@ include file="up.jsp" %>
<div class="dashboard">
    <h2>Upload Your Resume</h2>
    <form name="addFile" action="UploadServlet" method="post" id="ContactForm" enctype="multipart/form-data">
        <h5>Please upload your resume in PDF format.</h5><br/>Format: <%=s.getIndex() %>.pdf<br/><br/>
        <input type="file" name="uploadFile" class="" />
        <input type="hidden" name="fileType" value ="Resume" class=""/>
        <br/>
        <strong>(Maximum file size: 5 MB)</strong><br/>
        <p align="right">  
            <input type="submit" value="Upload" class="button"/>
        </p> 
    </form>
    <%

           if(s.getResume().length()>=6){

    %>
    <p align = "right">
        <a href="DownloadServlet?name=Resume" ><button>Download Resume</button></a>
    </p>

    <%
      }
    %>




</div>
<%@ include file="down.jsp" %>   
<% }%>