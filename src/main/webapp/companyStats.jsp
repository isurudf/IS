<%@page import="org.fiontar.registration.User"%>

<%@page import="org.fiontar.admin.Company.CompanyStats"%>

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
<br>
    <div class = "companies">
        <table>

            <% String[][] array = CompanyStats.getStats(); 
		
                    for (int j=0; j<array.length; j++){
			
                            if(j==0)
                            out.print("<th>");
                            else
                            out.print("<tr><td></td>");
			
                            for (int i=0; i<17; i++){
                                    if(j==0&&i!=0)
                                            out.print("<td width=\"5%\"><strong>"+array[j][i]+"</td><strong>");
                                    else if(i==0&&j==0)
                                            out.print("<td width=\"20%\"><strong>"+array[j][i]+"</td><strong>");
                                    else if(i==0)
                                            out.print("<td><strong>" + array[j][i] +"<strong></td>");
                                    else
                                            out.print("<td>" + array[j][i] +"</td>");
							
                            }
                            if(j==0)
                            out.print("</th>");
                            else
                            out.print("</tr>");
			
                    }
            %>
            <tr>


            </tr>


        </table>



    </div>
    <br>




        <%@ include file="down.jsp" %>
        <%} %>