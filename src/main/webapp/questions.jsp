<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
        
       
%>

<%@ include file="up.jsp" %>
<div class = "dashboard">
<table>
<tr>
<td width="70%"><h2 class="title"><a href="">Ask a question</a></h2></td>
<td width = "30%">

				
                    <p align ="right"><a href="http://www.hsenidmobile.com"><img src="images/rur/hSenid.png" alt="" width="150" height="100" ></a></p>
                </td>
<tr>
</table>




<br>
					<p align="justify">Engineers' Forum is the inaugural event of the Careers' Week of the University of Moratuwa. 
					In this panel discussion, various issues regarding the industry will be discussed. 
					In order to do that, we would like to know what are the issues, you feel important to know about before you join the industry. 
<br><br><strong>Sample questions:</strong><br><ul>
<li>1.	How do I balance work with higher studies.</li>
<li>2.	Do I need any other qualifications other than academic work?</li>
<br><br></ul>
<form method="post" action="SuggestionsServlet" >
   

        <textarea name="question" rows =5 cols =65 style="resize:vertical" placeholder ="type your question here." ></textarea><br></br>

    <p align="left"> <input type="submit" value="Ask" class="button"/>
        <input type="reset" value="Clear" class="button"/>
    </p>
</form>



</p>



<%@ include file="down.jsp" %>
<% }%>