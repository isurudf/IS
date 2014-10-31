
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.ArrayList"%>
<%@page import="org.fiontar.registration.Undergrad"%>
<%@page import="org.fiontar.registration.dao.UndergradDA"%>
<%@page import="org.fiontar.admin.Company.CompanyDA"%>
<%@page import="org.fiontar.admin.Company.Company"%>
<%@page import="org.fiontar.registration.User"%>
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
<script type="text/javascript">
    setInterval(function() {
        $('blink').each(function() {
            $(this).css('visibility', $(this).css('visibility') === 'hidden' ? '' : 'hidden')
        });
    }, 500);
</script>
<%@page contentType = "text/html" pageEncoding = "utf-8"%>

<%@ include file="up.jsp" %>

<%
        if(s.getVerified()!=1){
%>
<h2> An email has been sent to you. Please confirm your email address by clicking on the link sent before proceeding. </h2>
<%
    }
    else{
%>
<div align ="center">
    <a href="preferences.jsp"><h3>NOTICE : UPDATE YOUR COMPANY PREFERENCES </h3></a>
</div>
<div align ="center">
    <a href="preferences.jsp"><h5>Please order all the companies for your department according to your choice. Click here</h5></a>
    <br/>
</div>
<div class="container ajax">





    <div class="four columns row">
        <div class="four columns row">

            <a href = "preferences.jsp" >
                <p align="center">
                    <img src="images/pref.png" alt="" width="100" height="150"/></p>
                <h5 align="center">Update My Companies</h5>
            </a>


        </div><!--/ .columns-->

    </div><!--/ .columns -->

    <div class="four columns row">

        <div class="four columns row">
            <a href = "<%="companies.jsp?field="+s.getField()%>" >
                <p align="center">
                    <img src="images/info.png" alt="" width="100" height="150"/></p>
                <h5 align="center">View Company Details</h5>
            </a>


        </div><!--/ .columns-->
    </div><!--/ .columns -->


    <div class="four columns row">

        <div class="four columns row">
            <a href = "upload.jsp" >
                <p align="center">
                    <img src="images/upload.png" alt="" width="100" height="150"/></p>
                <h5 align="center">Upload My CV</h5>
            </a>


        </div><!--/ .columns-->

    </div><!--/ .columns -->

    <div class="four columns row">
        <div class="four columns row">     
            <a href = "settings.jsp" >
                <p align="center">
                    <img src="images/changepw.png" width="100" height="150"/>
                </p>
                <h5 align="center">My Settings</h5>
            </a>

        </div><!--/ .columns-->

    </div><!--/ .columns -->









</div><!--/ .container-->



<%}}%>
<%@ include file="down.jsp" %>