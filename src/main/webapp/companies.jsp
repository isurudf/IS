<%@page import="org.fiontar.admin.Company.Company"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.fiontar.admin.Company.CompanyDA"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.fiontar.registration.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="ddsmoothmenu.js"></script>
<script type="text/javascript" src="jquery-1.js"></script>
<script type="text/javascript" src="scripts.js"></script>
<%@ include file="up.jsp" %>

<div class="companies">
    <h1>Company Profiles</h1>
    <br/>
    <div class="acc-box type-1">
        <%
            if (CompanyDA.getCompany() != null) {
							
                ArrayList<Company> CompanyList;
                if(request.getParameter("field")!=null){
                    CompanyList= CompanyDA.getCompany(request.getParameter("field"));
                }
                else
                    CompanyList= CompanyDA.getCompany();
                for (int i = 0; i < CompanyList.size(); i++) {
                    Company a= CompanyList.get(i);
                    if(!a.getShortName().equals("CTC")){

        %>

        <span data-mode="toggle" class="acc-trigger">
            <a href=""><%=a.getName() %></a>
        </span>

        <div class="acc-container">

            <p align="justify">
                <img src ="<%=a.getLogo() %>"alt="" align="left" width="100" height = "100" style="padding: 0px 10px 10px"/>

                <%=a.getDescription() %></p><br/><br/>

        </div>






        <%
						
                                            }
                }
            }
        %>
    </div>
</div>
</div>
<%@ include file="down.jsp" %>