<%@page import="org.fiontar.admin.algo.Assign"%>
<%@page import="java.util.ArrayList"%>
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
        
        String[] fieldsLong={"Architecture","Chemical & Process Engineering","Civil Engineering",
        "Computer Science & Engineering","Earth Resource Engineering","Electrical Engineering",
        "Electronic & Telecommunications Engineering","Facilities Management",
        "Faculty of Information Technology","Fashion Design  and Product Development",
        "Materials Engineering","Mechanical Engineering","Quantity Surverying","Textile & Clothing Technology",
        "Town & Country Planning","Transport & Logistics Management","Undergraduates with no Preference"};

        ArrayList<Undergrad> list;
        ArrayList<Undergrad> none=new ArrayList<Undergrad>();

%>
<br/>
<% 
        
        for(int i=0;i<17;i++){
            if(i<16)
                list = UndergradDA.getAllUnderGradsByField(fields[i]);
            else list=none;
%>

<div style="page-break-after:always; margin-left:40px;">
    <h2><%=fieldsLong[i]%></h2>
    <table>
        
        <tr style="height: 40px">
            <td width="10px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td width="100px">INDEX NUM</td>
            <td width="600px">NAME</td>
            <td width="150px">PHONE NUMBER</td>
            <td width="200px">SIGNATURE</td>
        </tr>
        
        

    <%
            int count=0;
            for(int j=0;j<list.size();j++){
                if(i<16&&list.get(j).getCompanies().length()<2)
                {
                    none.add(list.get(j));
                    continue;
                }
                count++;
    %>
    
        <tr>
            
            <td style="text-align: center"><%=count+"&nbsp;&nbsp;"%></td>
            <td style="text-align: center"><%=list.get(j).getIndex()%></td>
            <td><%
            String a = list.get(j).getName().toUpperCase();
            if(a.length()<35)
                out.print(a);
            else
                out.print(a.substring(0,35));

            %>
            </td>
            <td ><%=list.get(j).getPhone()%></td>
            <td></td>
        </tr>
    
    <%}%>
    </table>
</div>
    <%

}}
    %>
