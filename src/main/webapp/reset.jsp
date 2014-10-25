<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
%>
<script language='javascript' type='text/javascript'>
    function check(input) {
        if (input.value != document.getElementById('newPassword').value) {
            input.setCustomValidity('The two passwords must match.');
        } else {
            // input is valid -- reset the error message
            input.setCustomValidity('');
        }
    }
</script>
<%@ include file="up.jsp" %>
<div class="dashboard">
    <h2>Reset Password</h2><br>

        <form name="login" method="post" action="PasswordServlet" id="ContactForm">

            <fieldset><p align="center">
                    <dl>                            
                        <dd><input type="hidden" name="link" value="<%=request.getParameter("id") %>" /></dd>
                    </dl>                            
                    <dl>                            
                        <dd><input type="password" name="newPassword" id="newPassword" size="25" required="true" placeholder="type your new password"/></dd>
                    </dl>

                    <dl>

                        <dd><input type="password" name="password" id="" size="25" required="true" placeholder="retype your new password" oninput="check(this)"/></dd>
                    </dl></p>


                <br />
                <p align="right">
                    <dl class="submit">
                        <input type="submit" name="submit" class="button" value="Update" />

                    </dl>

                </p>



            </fieldset>

        </form></div>
<%@ include file="down.jsp" %>