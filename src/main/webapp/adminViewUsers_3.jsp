<%@page import="org.fiontar.admin.allocate.Assign"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.fiontar.admin.Company.Company"%>
<%@page import="org.fiontar.admin.Company.CompanyDA"%>
<%@page import="org.fiontar.registration.Undergrad"%>
<%@page import="org.fiontar.registration.dao.UndergradDA"%>
<%@page import="org.fiontar.admin.allocate.UG"%>
<%@page import="org.fiontar.admin.allocate.CMP"%>

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
        
        Assign.initialise();
        ArrayList<UG> list;
  
%>
<br/>
<% 
  String[] timeslots = {"8.00-8.30am","8.30-9.00am","9.00-9.30am","9.30-10.00am","10.00-10.30am","10.30-11.00am","11.00-11.30am",
    "11.30am-12.00pm","12.00-12.30pm","12.30-1.00pm","1.00-1.30pm","1.30-2.00pm","2.00-2.30pm","2.30-3.00pm","3.00-3.30pm",
    "3.30-4.00pm","4.00-4.30pm","4.30-5.00pm","5.00-5.30pm","5.30-6.00p"
            + "m"};      
        for(CMP cmp: Assign.arrCMP){
            list = cmp.arrUG;
%>

<div style="page-break-after:always; margin-left:40px;">
    <h2><%=cmp.c.getName()%></h2>
    <table>
        
        <tr style="height: 40px">
            <td width="10px">TimeSlot&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td width="600px">NAME</td>
            <td width="150px">PHONE NUMBER</td>
            
        </tr>
        
        

    <%
            for(int j=0;j<list.size();j++){
                
    %>
    
        <tr>
            
            <td style="text-align: center"><%=timeslots[j]%></td>
            <td><%
            String a = list.get(j).getName().toUpperCase();
            if(a.length()<35)
                out.print(a);
            else
                out.print(a.substring(0,35));

            %>
            </td>
            <td ><%=list.get(j).getPhone()%></td>
            
        </tr>
    
    <%}%>
    </table>
</div>
    <%

}}
    %>
