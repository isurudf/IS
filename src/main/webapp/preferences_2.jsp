<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.fiontar.registration.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.fiontar.admin.Company.CompanyDA"%>
<%@page import="org.fiontar.admin.Company.Company"%>
<%@page import="org.fiontar.registration.Undergrad"%>
<%@page import="org.fiontar.registration.dao.UndergradDA"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">

    function f(j, num) {
        var index = document.getElementById(j).selectedIndex;
        var l;
        var k;
        for (l = j + 1; l < num; l++) {

            var op = document.getElementById(l);
            if (op.selectedIndex == index) {
                op.selectedIndex = 0;
            }
            op.options[index].style.display = 'none';
        }

    }

    function update(num) {
        var j;
        var k;

        for (j = 0; j < num; j++) {
            var op = document.getElementById(j);
            for (k = 0; k <= num; k++) {
                op.options[k].style.display = 'block';
            }

        }

        for (j = 0; j < num; j++) {
            f(j, num);
        }



    }

    function populate() {
        var s = document.getElementById('prefList').value;
        var num = document.getElementById('num').value;
        var arr = s.split(",");
        var len = arr.length;
        var i;
        for (i = 0; i < len && i < num; i++) {
            document.getElementById(i).options.selectedIndex = arr[i];
        }
		update(num);

    }
    window.onload = function() {
        populate();
    };
</script>

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
        Undergrad s = UndergradDA.getUndergrad(value2.substring(0,32),value2.substring(32,39));

%>
<%@ include file="up.jsp" %>
<div class="dashboard">
<h2>My Preference</h2>

<form name="addFile" action="UndergradCompanyServlet" method="post" id="ContactForm">
    <%
        String prefList = s.getCompanies();
        ArrayList<Company> list = CompanyDA.getCompany(s.getField()) ; 
        int num = list.size();
        if(num==0){
            out.print("Sorry. No companies have been added for your department.");
        }
        else{
            for(int i=0;i<num;i++){
                prefList=prefList.replace(list.get(i).getShortName(),""+(i+1));

    %>
    Preference Number <%=(i+1) %>&nbsp;&nbsp;&nbsp;&nbsp;
	<div class="styled-select">
    <select name="<%="pref"+i %>" id="<%=i %>" onChange="update(<%=num %>);">
        <option value="0">Select</option>

        <%
            for(int j=0;j<num;j++){

        %>

        <option value =<%=list.get(j).getShortName() %> ><%=list.get(j).getName() %> </option>
        <%
            }
        %>
    </select>
	</div>
    <br>
            <%
                   }
               }
            %>

            <input type="hidden" id="num" name="num" value="<%=num %>" />
            <input type="hidden" id="prefList" name="prefList" value="<%=prefList %>" />


            <p align="right"><input type="submit" value="Save my selection" class="button"/></p>
            </form>




</div><!-- end of right content-->

<%@ include file="down.jsp" %>   
<% }%>