<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript">

    function check(form) {
        if (form.index.value.length != 7) {
            alert("Enter a correct Index Number. eg: 123456A");
            return false;
        }
        var Z = form.index.value;
        if (Z.charAt(0) != '0' || !(Z.charAt(1) == '9' || Z.charAt(1) == '8') || isNaN(Z.substring(0, 6)) || Z.charCodeAt(6) < 65 || Z.charCodeAt(6) > 90) {
            alert("Enter a correct Index Number. eg: 123456A");
            return false;
        }

        if (form.password.value != form.cpass.value) {
            alert("Passswords do not match!");
            form.cpass.focus();
            return false;
        }


        var x = form.email.value;
        var atpos = x.indexOf("@");
        var dotpos = x.lastIndexOf(".");
        if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= x.length)
        {
            alert("Not a valid e-mail address");
            return false;
        }

        if (form.phone.value.length != 10) {
            alert("Enter a correct 10 digit Phone Number");
            return false;
        }



        return true;



    }

    function isNumberKey(evt) {
        var charCode = (evt.which) ? evt.which : event.keyCode
        if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;
        return true;
    }





</script>

<%@ include file="up.jsp" %>


<div class="dashboard">
    <h3>Registration has been closed. :(</h3>
<!--
    <h2>Registration Form</h2>

    <div class="form">
        <form action="UndergradRegistrationServlet" name="register" method="post" id="Signup" onsubmit="return check(this)">
            <div>
                <div  class="wrapper">
                    <span>Name:</span>
                    <div class="bg"><input type="text" class="input" name="name" required="true"></div>								
                </div><br></br>
                <div  class="wrapper">
                    <span>Index Number: (Format eg: XXXXXXR) </span>
                    <div class="bg"><input type="text" class="input" name="index" required="true"></div>
                </div><br></br>
                
                <div  class="wrapper">
                    <span>Email:</span>
                    <div class="bg"><input type="text" class="input" name="email" required="true"></div>
                </div><br></br>
                <div  class="wrapper">
                    <span>Password:</span>
                    <div class="bg"><input type="password" class="input" name="password" required="true"></div>
                </div><br></br>
                <div  class="wrapper">
                    <span>Retype Password:</span>
                    <div class="bg"><input type="password" class="input" name="cpass" required="true"></div>
                </div><br></br>
                <div  class="wrapper">
                    <span>Contact Address:</span>
                    <div class="bg"><input type="text" class="input" name="addr" required="true"></div>								
                </div><br></br>
                <div  class="wrapper">
                    <span>Mobile Number:</span>
                    <div class="bg"><input type="text" class="input" name="phone" required="true" onkeypress="return isNumberKey(event)"></div>								
                </div><br></br>
				<div class="styled-select">
                    <span>Department:</span>
                    <div class="bg">
                        <select name="field" id="field" required="true" class="select">
                            <option value="" style="display:none;">Select field...</option>
                            <option value="ARCH">Architecture</option>
                            <option value="CPE">Chemical & Process Engineering</option>
                            <option value="CE">Civil Engineering</option>
                            <option value="CSE">Computer Science & Engineering</option>
                            <option value="ERM">Earth Resource Engineering</option>
                            <option value="EE">Electrical Engineering</option>

                            <option value="ENTC">Electronic & Telecommunications Engineering</option>
                            <option value="FM">Facilities Management</option>
                            <option value="IT">Faculty of Information Technology</option>
                            <option value="FD">Fashion Design  and Product Development</option>
                            <option value="ME">Materials Engineering</option>
                            <option value="MECH">Mechanical Engineering</option>                                                          
                            <option value="QS">Quantity Surverying</option>
                            <option value="TC">Textile & Clothing Technology</option>   
                            <option value="TCP">Town & Country Planning</option>
                            <option value="TLM">Transport & Logistics Management</option>


                        </select>
                    </div>
                </div><br></br><br></br>
                <p align = "right">
                    <input type="submit" value="Register" class="button" align="right">
                        <input type="reset" value="Reset" class="button"/></p>
            </div>
        </form>

    </div>
-->
</div>
<!-- end of right content-->

            <%@ include file="down.jsp" %>
