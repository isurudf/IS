<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<font color="#ff0000"></font>
    <%
        boolean loggedIn = false;
        Cookie cookies2[] = request.getCookies();
        if (cookies2 != null) {
            for (int i = 0; i < cookies2.length; i++) {
                if (cookies2[i].getName().equals("user")) {
                    response.setHeader("Refresh", "3; URL=undergradDashboard.jsp");
                    loggedIn=true;
                    break;
                }
                else if (cookies2[i].getName().equals("admin")) {
                    if(cookies2[i].getValue().equals("rur13admin"))
                        response.setHeader("Refresh", "3; URL=admin.jsp");
                    else{
                        response.setHeader("Refresh", "3; URL=liveEdit.jsp");
                    }
                    loggedIn=true;
                    break;
                }
            }
        }

    %>
	
<script type="text/javascript">

    function check(form) {
        if (form.index.value.indexOf("@")>=0) {
            alert("Enter your Index Number, not the email address");
            return false;
        }
        return true;
    }
</script>
    <%@ include file="up.jsp" %>

<div class="login">

    <p>
        <strong>
            <%
                if (loggedIn) {
                    out.print("<h2 align=\"center\">You are logged in!</h2><br>");
                    out.print("<p align=\"center\">Redirecting you now.....</p>");
                } else {

            %><br>
                <h2 class="price">Sign In</h1>
                    </strong>
                    <strong>
                        <font color="#ff0000" size="2px">
                            <%
                                String msg = request.getParameter("id");
                                if (msg != null) {
                                    out.print("<p align=\"center\">" + msg + "</p>");
                                }
                            %>
                        </font>

                    </strong>

                    </p>

                    <p align = "center">
                        <form name="login" method="post" action="LoginServlet" id="ContactForm" onsubmit="return check(this)" >
						
							

                            <fieldset>
                                <dl>

                                    <dd><input type="text" name="index" id="index" size="25" placeholder="type your index number" /></dd>
                                </dl>

                                <dl>

                                    <dd><input type="password" name="password" id="" size="25" placeholder="type your password"/></dd>
                                </dl>

                                <!--                    <dl>
                                                        <dt><input type="checkbox" name="interests[]" id="" value="" /></dt>
                                                        <dd>
                                                            <label class="check_label">Remember me</label>
                                                        </dd>
                                                    </dl> -->
                             
                                <p align="right">
                                    <dl class="submit">
                                        <input type="submit" name="submit" class="button" value="Login" />
                                        <input type="reset" value="Clear" class="button"/>
                                    </dl>
                                </p>
                                <a href="forgot.jsp" class="forgot_pass">Forgot Password?</a>&nbsp;&nbsp;&nbsp;
                                <a href="undergradRegistration.jsp" class="forgot_pass">Not Registered? Sign Up</a>


                            </fieldset>

                        </form>
                    </p>

                    </div>  
                    </div>

                    <%}
                    %>
                    <%@ include file="down.jsp" %>