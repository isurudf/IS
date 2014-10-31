<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.fiontar.admin.Company.CompanyDA"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
        CompanyDA.processCompany();
%>
<%@ include file="up.jsp" %>
<div class="dashboard">
    <div class="right_content">
        <h1>Add New Company</h1>
        <form action="CompanyServlet" method="post" >
            <input type="hidden" name ="newCompany" value="yes" />

            <input type="text" name="name" placeholder="company name"></input><br></br>

            <input type="text" name="shortName" placeholder="shortened name for the company"></input><br></br>
            <input type="text" name="logo" placeholder="copy the link for company logo"></input><br></br>
            <textarea name="description" rows =10 cols =60 style="resize:vertical" placeholder="brief description of the company"></textarea><br></br>


            <h3>Fields</h3>
            <div class="companies">
                <input type="checkbox" name="field0" value="ARCH">&nbsp;&nbsp;&nbsp;Architecture</input><br></br>
                <input type="checkbox" name="field1" value="CPE">&nbsp;&nbsp;&nbsp;Chemical & Process Engineering</input><br></br>
                <input type="checkbox" name="field2" value="CE">&nbsp;&nbsp;&nbsp;Civil Engineering</input><br></br>
                <input type="checkbox" name="field3" value="CSE">&nbsp;&nbsp;&nbsp;Computer Science & Engineering</input><br></br>
                <input type="checkbox" name="field4" value="ERM">&nbsp;&nbsp;&nbsp;Earth Resource Engineering</input><br></br>
                <input type="checkbox" name="field5" value="EE">&nbsp;&nbsp;&nbsp;Electrical Engineering</input><br></br>
                <input type="checkbox" name="field6" value="ENTC">&nbsp;&nbsp;&nbsp;Electronic & Telecommunications Engineering</input><br></br>
                <input type="checkbox" name="field7" value="FM">&nbsp;&nbsp;&nbsp;Facilities Management</input><br></br>
                <input type="checkbox" name="field8" value="IT">&nbsp;&nbsp;&nbsp;Faculty of Information Technology</input><br></br>
                <input type="checkbox" name="field9" value="FD">&nbsp;&nbsp;&nbsp;Fashion Design  and Product Development</input><br></br>
                <input type="checkbox" name="field10" value="ME">&nbsp;&nbsp;&nbsp;Materials Engineering</input><br></br>
                <input type="checkbox" name="field11" value="MECH">&nbsp;&nbsp;&nbsp;Mechanical Engineering</input><br></br>
                <input type="checkbox" name="field12" value="QS">&nbsp;&nbsp;&nbsp;Quantity Surverying</input><br></br>
                <input type="checkbox" name="field13" value="TC">&nbsp;&nbsp;&nbsp;Textile & Clothing Technology</input><br></br>
                <input type="checkbox" name="field14" value="TCP">&nbsp;&nbsp;&nbsp;Town & Country Planning</input><br></br>
                <input type="checkbox" name="field15" value="TLM">&nbsp;&nbsp;&nbsp;Transport & Logistics Management</input><br></br>

            </div><br><br>
                    <p align="right"> <input type="submit" value="Submit" class="button"/></p>
                    </form>



                    </div><!-- end of right content-->
                    </div>
                    <br><br>

                            <% }%>
                            <%@ include file="down.jsp" %>