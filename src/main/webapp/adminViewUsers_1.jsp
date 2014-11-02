<%@page import="org.fiontar.admin.allocate.Assign"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.fiontar.admin.Company.Company"%>
<%@page import="org.fiontar.admin.Company.CompanyDA"%>
<%@page import="org.fiontar.registration.Undergrad"%>
<%@page import="org.fiontar.registration.dao.UndergradDA"%>
<%@page import="org.fiontar.admin.allocate.UG"%>
<%@page import="org.fiontar.admin.allocate.CMP"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<style>
    .CSStable {
	margin:0px;padding:0px;
	width:100%;
	box-shadow: 10px 10px 5px #888888;
	border:1px solid #000000;
	
	-moz-border-radius-bottomleft:0px;
	-webkit-border-bottom-left-radius:0px;
	border-bottom-left-radius:0px;
	
	-moz-border-radius-bottomright:0px;
	-webkit-border-bottom-right-radius:0px;
	border-bottom-right-radius:0px;
	
	-moz-border-radius-topright:0px;
	-webkit-border-top-right-radius:0px;
	border-top-right-radius:0px;
	
	-moz-border-radius-topleft:0px;
	-webkit-border-top-left-radius:0px;
	border-top-left-radius:0px;
}.CSStable table{
    border-collapse: collapse;
        border-spacing: 0;
	width:100%;
	height:100%;
	margin:0px;padding:0px;
}.CSStable tr:last-child td:last-child {
	-moz-border-radius-bottomright:0px;
	-webkit-border-bottom-right-radius:0px;
	border-bottom-right-radius:0px;
}
.CSStable table tr:first-child td:first-child {
	-moz-border-radius-topleft:0px;
	-webkit-border-top-left-radius:0px;
	border-top-left-radius:0px;
}
.CSStable table tr:first-child td:last-child {
	-moz-border-radius-topright:0px;
	-webkit-border-top-right-radius:0px;
	border-top-right-radius:0px;
}.CSStable tr:last-child td:first-child{
	-moz-border-radius-bottomleft:0px;
	-webkit-border-bottom-left-radius:0px;
	border-bottom-left-radius:0px;
}.CSStable tr:hover td{
	
}
.CSStable tr:nth-child(odd){ background-color:#aad4ff; }
.CSStable tr:nth-child(even)    { background-color:#ffffff; }.CSStable td{
	vertical-align:middle;
	
	
	border:1px solid #000000;
	border-width:0px 1px 1px 0px;
	text-align:left;
	padding:7px;
	font-size:14px;
	font-family:Georgia;
	font-weight:normal;
	color:#000000;
}.CSStable tr:last-child td{
	border-width:0px 1px 0px 0px;
}.CSStable tr td:last-child{
	border-width:0px 0px 1px 0px;
}.CSStable tr:last-child td:last-child{
	border-width:0px 0px 0px 0px;
}
.CSStable tr:first-child td{
		background:-o-linear-gradient(bottom, #005fbf 5%, #003f7f 100%);	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #005fbf), color-stop(1, #003f7f) );
	background:-moz-linear-gradient( center top, #005fbf 5%, #003f7f 100% );
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr="#005fbf", endColorstr="#003f7f");	background: -o-linear-gradient(top,#005fbf,003f7f);

	background-color:#005fbf;
	border:0px solid #000000;
	text-align:center;
	border-width:0px 0px 1px 1px;
	font-size:16px;
	font-family:Verdana;
	font-weight:bold;
	color:#ffffff;
}
.CSStable tr:first-child:hover td{
	background:-o-linear-gradient(bottom, #005fbf 5%, #003f7f 100%);	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #005fbf), color-stop(1, #003f7f) );
	background:-moz-linear-gradient( center top, #005fbf 5%, #003f7f 100% );
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr="#005fbf", endColorstr="#003f7f");	background: -o-linear-gradient(top,#005fbf,003f7f);

	background-color:#005fbf;
}
.CSStable tr:first-child td:first-child{
	border-width:0px 0px 1px 0px;
}
.CSStable tr:first-child td:last-child{
	border-width:0px 0px 1px 1px;
}
</style>

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
        String[] timeslots = {"8.00-8.30am","8.30-9.00am","9.00-9.30am","9.30-10.00am","10.00-10.30am","10.30-11.00am","11.00-11.30am",
    "11.30am-12.00pm","12.00-12.30pm","12.30-1.00pm","1.00-1.30pm","1.30-2.00pm","2.00-2.30pm","2.30-3.00pm","3.00-3.30pm",
    "3.30-4.00pm","4.00-4.30pm","4.30-5.00pm","5.00-5.30pm","5.30-6.00p"
            + "m"};
        %>
<div class="CSStable" >
        <table >
            <tr>
                <td>
                    Name
                </td>
                <td >
                    Index
                </td>
                <td>
                    Time slot 1
                </td>
                <td>
                    Company
                </td>
                <td>
                    Time slot 2
                </td>
                <td>
                    Company
                </td>
                <td>
                    Time slot 3
                </td>
                <td>
                    Company
                </td>
            </tr>
<%
        for(UG ug:Assign.arrUG){
%>

<tr>

        <% 
            out.println("<td>"+ug.getName().toUpperCase()+"</td>");
            out.println("<td>"+ug.u.getIndex()+"</td>");
            for(int i=0;i<3;i++){
                if(ug.assigned[i]!=null){
                    out.println("<td>"+timeslots[ug.assigned[i].arrUG.indexOf(ug)]+"</td>");
                    out.println("<td>"+ug.assigned[i].c.getName()+"</td>");
                }
            }
            out.println("<br/><br/>");
        %>
</tr>
           

        <%}%>
        
        </table>
    </div>
        <%} %>
        
         
