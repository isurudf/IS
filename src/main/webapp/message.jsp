
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="up.jsp" %>
<div class="dashboard">
    <div>
        <% 
        String message = request.getParameter("message");
        out.print("<h5 align=\"center\">"+message+"</h5>");
        %>
    </div>
</div>
<%@ include file="down.jsp" %>