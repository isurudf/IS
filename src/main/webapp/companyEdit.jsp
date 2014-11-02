<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.fiontar.admin.Company.Company"%>
<%@page import="java.util.ArrayList"%>
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
                response.setHeader("Refresh", "0; URL=login.jsp?id=You are not logged in as an Administrator!");
                return;
            }
        }
    }
    if(value==null||!value.equals("rur13admin")){
        response.setHeader("Refresh", "0; URL=login.jsp?id=You are not logged in as an Administrator!");
    } 
    else{
        ArrayList<Company> CompanyList= CompanyDA.getCompany();
        
                
%>
<%@ include file="up.jsp" %>
<div >
    <div class="right_content">
        <h1>Edit Company</h1>
        <h6>Building name should be in the format of Building-Division. eg: "Civil-Research"</h6>
        <form action="CompanyServlet" method="post" >
            <table>
                <tr>
                    <td>Edit&nbsp;</td>
                    <td>ShortName</td>
                    <td>Fields</td>
                    <td></td>
                    <td>Name</td>
                    <td>Description</td>
                    <td>Logo</td>
                    <td>Capacity</td>
                    <td>BuildingName</td>
                    <td>BuildCoTel</td>
                    <td>CompCoTel</td>
                    <td></td>
                </tr>
                <%
                    for (int i = 0; i < CompanyList.size(); i++) {
                        Company a= CompanyList.get(i);
                %>
                <tr>
                    <td><input type="checkbox" name="edit<%=a.getId() %>" ></input></td>
                    <td><%out.print(a.getShortName()); %></td>
                    <td><input type="text" placeholder="Fields" 
                               value="<%out.print(a.getFields()); %>"></input></td>
                    <td><a href="addUndergradPrefs.jsp?cmp=<%out.print(a.getShortName()); %>" target="new">Add Undergraduate Preferences</a></td>  
                    <td><input type="text" name="name<%=a.getId() %>" placeholder="company name" value="<%=a.getName() %>"></input></td>
                    <td><textarea name="description<%=a.getId() %>" rows =1 cols =40 style="resize:vertical" 
                                  placeholder="brief description of the company"><%=a.getDescription() %></textarea></td>
                    <td><input type="text" name="logo<%=a.getId() %>" placeholder="copy the link for company logo" 
                               value="<%=a.getLogo() %>"></input></td>
                    <td><input type="text" name="capacity<%=a.getId() %>" placeholder="capacity" 
                               value="<%=a.getCapacity() %>"></input></td>
                    <td><input type="text" name="building<%=a.getId() %>" placeholder="Dept-Building" 
                               value="<%=a.getBuilding() %>"></input></td>
                    <td><input type="text" name="buildTel<%=a.getId() %>" placeholder="Building co-ordinator tel" 
                               value="<%=a.getBuildTel() %>"></input></td>
                    <td><input type="text" name="tel<%=a.getId() %>" placeholder="Co-ordinator tel" 
                               value="<%=a.getTel() %>"></input></td>
                </tr> 
                <%
                    }
                %>

            </table>

            <p align="right"> <input type="submit" value="Submit" class="button"/></p>
        </form>



    </div><!-- end of right content-->
</div>
<br><br>

        <% }%>
        <%@ include file="down.jsp" %>