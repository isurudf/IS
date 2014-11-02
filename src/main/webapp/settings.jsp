<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.fiontar.registration.Undergrad"%>
<%@page import="org.fiontar.registration.dao.UndergradDA"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.fiontar.registration.User"%>
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
<script language='javascript' type='text/javascript'>
    function check(input) {
        if (input.value != document.getElementById('newPassword').value) {
            input.setCustomValidity('The two passwords must match.');
        } else {
            // input is valid -- reset the error message
            input.setCustomValidity('');
        }
    }
    function message(){
        var result = confirm("Are you really want to do this?");
        return result;
    }
</script>
<%@ include file="up.jsp" %>

<div class="dashboard">
    <h2>Change Name and Address</h2>
    <h4>First Name, Last Name and Address should be entered.</h4>
    <p>
        Current Name : <%= s.getName() %> <br />
        Current Address : <%= s.getAddr() %>
        
    </p>
    <form name="login" method="post" action="SettingsServlet" id="ContactForm">

        <fieldset>
            <dl>
                <dd><input type="text" name="firstName" id="" size="25" required="true" placeholder="Type your first name" /></dd>
            </dl>
            <dl>
                <dd><input type="text" name="lastName" id="" size="25" required="true" placeholder="Type your last name" /></dd>
            </dl>
            <br></br>
            <dl>
                <dd><textarea name="address" rows =5 cols =40 style="resize:vertical" 
                                  placeholder="Permanent Address"></textarea></dd>
            </dl>
            
        </fieldset>
            <p align="right">
                <dl class="submit">
                        <input type="submit" name="submit" class="button" value="Change Name and Address" />
                        <input type="reset" value="Clear" class="button"/>
                </dl>
            </p>
    </form>
</div>


<div class="dashboard">
    <h2>Change Password</h2>

    <form name="login" method="post" action="PasswordServlet" id="ContactForm">

        <fieldset>
            <dl>
                <dd><input type="password" name="oldPassword" id="" size="25" required="true" placeholder="type your current password" /></dd>
            </dl>
            <br/><br/>
			<dl>
				<dd><input type="password" name="newPassword" id="newPassword" size="25" required="true" placeholder="type your new password"/></dd>
			</dl>
			<dl>
				<dd><input type="password" name="password" id="" size="25" required="true" placeholder="retype your new password" oninput="check(this)"/></dd>
			</dl>
            <br/>
			<p align="right">
				<dl class="submit">
					<input type="submit" name="submit" class="button" value="Change Password" />
					<input type="reset" value="Clear" class="button"/>
				</dl>
			</p>
        </fieldset>
    </form>
</div>

<div class="dashboard">
    <h2>I won't be coming for the interviews</h2>
    <form name="login" method="post" action="DeleteServlet" id="ContactForm">

        <fieldset>
            <dl>
                <dd><input type="password" name="oldPassword" id="" size="25" required="true" placeholder="type your current password" /></dd>
            </dl>
			<p align="right">
				<dl class="submit">
					<input type="submit" name="submit" class="button" onclick="return message();" value="Delete My Account" />
				</dl>
			</p>
		</fieldset>
	</form>
</div>

<% }%>
<%@ include file="down.jsp" %>