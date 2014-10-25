<%@page import="org.fiontar.registration.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="up.jsp" %>
<div class="dashboard">
    <h3>Success</h3><p align="center">Email is verified successfully! Log in now for company selection</p>;
    <div>
        <% 
        String cookieName2 = "user";
        Cookie cookies2[] = request.getCookies();
        if (cookies2 != null) {
            for (int i = 0; i < cookies2.length; i++) {
                if (cookies2[i].getName().equals(cookieName2)) {
                    cookies2[i].setMaxAge(0);
                    response.addCookie(cookies2[i]);
                    break;
                }
            }
        }
        response.setHeader("Refresh", "5; URL=login.jsp");
        %>
    </div>
</div>

<%@ include file="down.jsp" %>