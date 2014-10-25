<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="org.fiontar.registration.User"%>
<font color="#ff0000"></font>
    <%@ include file="up.jsp" %>

<div class="login">

    <p>
        <strong>
            <br>
                <h4>Request Password Reset</h4>
        </strong>

    </p>

    <p align = "justify">
        Please type your email address to which a password reset link will be sent. For further information contact admin.</p><p align="center">
        <form name="login" method="post" action="ForgotPwdServlet" id="ContactForm">

            <fieldset>
                <dl>

                    <dd><input type="text" name="email" id="" size="25" required="true" placeholder="type your email address" /></dd>
                </dl>



                <!--                    <dl>
                                        <dt><input type="checkbox" name="interests[]" id="" value="" /></dt>
                                        <dd>
                                            <label class="check_label">Remember me</label>
                                        </dd>
                                    </dl> -->
                <br />
                <p align="right">
                    <dl class="submit">
                        <input type="submit" name="submit" class="button" value="Request" />

                    </dl>
                </p>



            </fieldset>

        </form>
    </p>

</div>  
</div>

<%@ include file="down.jsp" %>