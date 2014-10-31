<%@page import="java.util.ArrayList"%>
<%@page import="org.fiontar.admin.SuggestedQs"%>

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
            <th>
                <td width="20%"><strong>Index Number</strong></td>
                <td width="80%"><strong>Question</strong></td>

            </th>
            <% 
                    ArrayList<String> list = SuggestedQs.getQs(); 
                    String u;
                    for (int i=0; i<list.size(); i++){
                    u = list.get(i);
                    out.print("<tr>");
                    out.print("<td>" +  "</td>");
                    out.print("<td>" + u.substring(0,7) + "</td>");
                    out.print("<td>" + u.substring(7) + "</td>");
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