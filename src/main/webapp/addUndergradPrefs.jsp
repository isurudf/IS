<%@page import="java.util.Collections"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.fiontar.admin.Company.Company"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.fiontar.admin.Company.CompanyDA"%>
<%@page import="org.fiontar.registration.dao.UndergradDA"%>
<%@page import="org.fiontar.registration.Undergrad"%>

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
                response.setHeader("Refresh", "0; URL=login.jsp?id=You are not logged in as an Administrator!");
                return;
            }
        }
    }
    if(value==null||!value.equals("rur13admin")){
        response.setHeader("Refresh", "0; URL=login.jsp?id=You are not logged in as an Administrator!");
    } 
    else{
        String cmp = request.getParameter("cmp");
        ArrayList<Undergrad> undergradList= UndergradDA.getAllUnderGrads();
        Collections.sort(undergradList);
        Company company = CompanyDA.getCompanyByName(cmp);
                
%>
<%@ include file="up.jsp"%>
<div>
    <div class="right-content">
        <h1><%=company.getName() %></h1>
        <h6>Add Undergraduate Preferences</h6>

        <form action="CompanyServlet" method="post">
            <input type="hidden" name="addstu" value="true">
            <input type="hidden" name="company" value="<%=cmp%>">
            <table>
                <%
                        String undergrads = company.getUndergrads(); 
                        int size = undergradList.size();
    for (int i = 0; i < size; i++) {
        Undergrad a= undergradList.get(i);
        boolean b = undergrads.contains(a.getIndex());
                        
                %>
                <tr>
                    <td>
                        <%if(b){%>
                        <input type="checkbox" name="undergrad<%=a.getId() %>" checked="checked"></input>
                        <%}else {%>
                        <input type="checkbox" name="undergrad<%=a.getId() %>"></input>
                        <%}%>
                    </td>
                    <td>
                        <%=a.getName() %>
                    </td>
                    <td>
                        <%if(b){%>
                        <input type="text" name="ugPref<%=a.getId() %>" value="<%=undergrads.indexOf(a.getIndex())/8 + 1 %>" />
                        <%}else {%>
                        <input type="text" name="ugPref<%=a.getId() %>" />
                        <%}%>
                    </td>
                    <td>
                        <%if(b){%>
                        <input type="text" name="ugPref<%=a.getId() %>" value="<%=undergrads.indexOf(a.getIndex())/8 + 1 %>" />
                        <%}else {%>
                        <input type="text" name="ugPref<%=a.getId() %>" />
                        <%}%>
                    </td>
                    <td>
                        <%
                        if(a.getResume().length()>6){
                            out.print("<a href=\"DownloadServlet?name=Resume&path=" + u.getResume() + "\">down&nbsp;</a>");
                        }else{
                            out.print("NOCV&nbsp");
                        }
                        %>
                    </td>
                </tr>
                <%
                    }
                %>
            </table>
            <input type="submit" value="Save Preferences" class="button"/>
        </form>
    </div>
</div>

<%
   }
%>
<%@ include file="down.jsp" %>