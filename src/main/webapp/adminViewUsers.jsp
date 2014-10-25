<%@page import="org.fiontar.registration.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.fiontar.registration.Undergrad"%>
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
%>
<%@ include file="up.jsp" %>
<br>
    <div class = "companies">
        <%
                out.print("<a href=\"adminViewUsers.jsp?company=all&field=all\">View All</a><br/>");         
                
                out.print("Company Name : <a href=\"adminViewUsers.jsp?field=all&company="+request.getParameter("company")+"\">"+request.getParameter("company")+"</a>"
 + "                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                out.print("Field : <a href=\"adminViewUsers.jsp?company=all&field="+request.getParameter("field")+"\">"+request.getParameter("field")+"</a><br/>");         
                
        %>
         <br/>
        <table>
            <th>
                <td width="8%"><strong>Index Number</strong></td>
                <td width="15%"><strong>Name</strong></td>
                <td width="8%"><strong>Contact Number</strong></td>
                <td width="10%"><strong>Email Address</strong></td>
                <td width="4%"><strong>Field</strong></td>
                <td width="15%"><strong>Verification Link</strong></td>
                <td width="2%"><strong>Vfd</strong></td>
                <td width="15%"><strong>Address</strong></td>
                <td width="5%"><strong>CV</strong></td>
                <td width="18%"><strong>Companies</strong></td>

            </th>
            <% 
                    
                    ArrayList<Undergrad> list ;
                    if(request.getParameter("field").equals("all"))
                        list= UndergradDA.getAllUnderGrads(); 
                    else
                        list= UndergradDA.getAllUnderGradsByField(request.getParameter("field"));
                    Undergrad u;
                    String link,link2;
                    String arr[];
                    int count=0;
                    for (int i=0; i<list.size(); i++){                  
                        u = list.get(i);
                        if(!request.getParameter("company").equals("all")&&!u.getCompanies().contains(request.getParameter("company")))
                            continue;
                        out.print("<tr>");
                        out.print("<td>" +  "</td>");
                        out.print("<td>" + u.getIndex() + "&nbsp;</td>");
                        out.print("<td>" + u.getName() + "</td>");
                        out.print("<td>" + u.getPhone() + "&nbsp;</td>");
                        out.print("<td>" + u.getEmail() + "</td>");
                        out.print("<td><a href=\"adminViewUsers.jsp?company="+request.getParameter("company")+"&field=" + u.getField() + "\">"+u.getField()+"</a>&nbsp;</td>");
                        out.print("<td>" + u.getEmailLink() + "</td>");
                        out.print("<td>" + u.getVerified() + "</td>");
                        out.print("<td>" + u.getAddr().replace("\n","") + "</td>");
                        
                        if(u.getResume().length()>6)
                            out.print("<td><a href=\"DownloadServlet?name=Resume&path=" + u.getResume() + "\">down&nbsp;</a></td>");
                        else
                            out.print("<td>NOCV&nbsp;</td>");
                        arr = u.getCompanies().split(",");
                        out.print("<td>");
                        link= "<a href=\"adminViewUsers.jsp?field="+request.getParameter("field")+"&company=";
                        for(int j=0;j<arr.length;j++){
                            out.print(link+arr[j]+"\">"+arr[j]+",&nbsp;</a>");
                        }
                        out.print("</td>");
                        out.print("</tr>");
                        count++;
                    }

            %>
        </table>
        Count <%=count %>
    </div>
    <br>




        <%@ include file="down.jsp" %>
        <%} %>