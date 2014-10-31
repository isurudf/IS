<%@page import="org.fiontar.admin.algo.Assign"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.fiontar.admin.Company.Company"%>
<%@page import="org.fiontar.admin.Company.CompanyDA"%>
<%@page import="org.fiontar.registration.Undergrad"%>
<%@page import="org.fiontar.registration.dao.UndergradDA"%>
<%@page import="org.fiontar.admin.algo.ug"%>
<%@page import="org.fiontar.admin.algo.cmp"%>

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
        String[] fields = {"ARCH", "CPE", "CE", "CSE", "ERM", "EE", "ENTC", "FM", "IT", "FD", "ME", "MECH", "QS", "TC", "TCP", "TLM"};
        
        String[] fieldsReal={"ARCH", "CPE", "CE", "CSE", "EM", "EE", "ENTC", "FM", "IT", "FD", "MT", "ME", "QS", "TM", "TCP", "TLM"};
        
        Assign.assign();
        cmp[] arr = Assign.arrCMP;
        ArrayList<ug> list;
  
%>
<br/>
<% 
        
        for(int i=0;i<arr.length;i++){
            list = arr[i].list;
%>

<div style="page-break-after:always; margin-left:40px;">
    <h2><%=arr[i].getName()%></h2>
    <table>
        
        <tr style="height: 40px">
            <td width="10px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td width="100px" style="text-align: center">FIELD</td>
            <td width="600px">NAME</td>
            <td width="150px">PHONE NUMBER</td>
            
        </tr>
        
        

    <%
            for(int j=0;j<list.size();j++){
                
    %>
    
        <tr>
            
            <td style="text-align: center"><%=(j+1)+"&nbsp;&nbsp;"%></td>
            <td style="text-align: center"><%=fieldsReal[list.get(j).field]%></td>
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
