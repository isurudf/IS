<%@page import="org.fiontar.admin.Company.CompanyDA"%>
<%@page import="org.fiontar.admin.Company.Company"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                response.setHeader("Refresh", "0; URL=login.jsp?id=You are not logged in!");
                return;
            }
        }
    }
    if(value==null||!value.equals("rur13admin")){
        response.setHeader("Refresh", "0; URL=login.jsp?id=You are not logged in!");
    } 
    else{
%>
<script type="text/javascript">

    function select() {
        for (l = 0; l < 16; l++) {
            document.getElementById(l).checked = true;
        }
    }
    function notifyCoord(box) {
        if (box.checked == true) {
            document.getElementById('c1').checked = true;
        }
    }
    function notifyCoord2(box) {
        if (box.checked == true) {
            document.getElementById('c2').checked = true;
        }
    }
    function countChar(val) {
        var len = val.value.length;
        $('#charNum').text('Chars :' + (160 - len));

    }

</script>



<%@ include file="up.jsp" %>
<div class="companies">
    <h2>Send SMS</h2>


    <form method="post" action="SendSMS" >

        <textarea name="text" rows =10 cols =60 style="resize:vertical" placeholder="Type Your SMS" onkeyup="countChar(this)"></textarea><br></br>
        
        <div id="charNum"></div>
        <br/>
        <input type="password" name="password" placeholder="Type the SMS Gateway Password" ></input> 
        
        <br/>
        <div class="acc-box type-1">
            <span data-mode="toggle" class="acc-trigger">
                <a href="">By Field</a>
            </span>

            <div class="acc-container">
                <table>
                    <tr>
                        <td><input type="checkbox" name="field0" id="0" value="ARCH">&nbsp;&nbsp;&nbsp;Architecture</input></td>
                        <td><input type="checkbox" name="field1" id="1" value="CPE">&nbsp;&nbsp;&nbsp;Chemical & Process Engineering</input></td>
                        <td><input type="checkbox" name="field2" id="2" value="CE">&nbsp;&nbsp;&nbsp;Civil Engineering</input></td>
                        <td><input type="checkbox" name="field3" id="3" value="CSE">&nbsp;&nbsp;&nbsp;Computer Science & Engineering</input></td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" name="field4" id="4" value="ERM">&nbsp;&nbsp;&nbsp;Earth Resource Engineering</input></td>
                        <td><input type="checkbox" name="field5" id="5" value="EE">&nbsp;&nbsp;&nbsp;Electrical Engineering</input></td>
                        <td><input type="checkbox" name="field6" id="6" value="ENTC">&nbsp;&nbsp;&nbsp;Electronic & Telecommunications Engineering</input></td>
                        <td><input type="checkbox" name="field7" id="7" value="FM">&nbsp;&nbsp;&nbsp;Facilities Management</input></td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" name="field8" id="8" value="IT">&nbsp;&nbsp;&nbsp;Faculty of Information Technology</input></td>
                        <td><input type="checkbox" name="field9" id="9" value="FD">&nbsp;&nbsp;&nbsp;Fashion Design  and Product Development</input></td>
                        <td><input type="checkbox" name="field10" id="10" value="ME">&nbsp;&nbsp;&nbsp;Materials Engineering</input></td>
                        <td><input type="checkbox" name="field11" id="11" value="MECH">&nbsp;&nbsp;&nbsp;Mechanical Engineering</input></td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" name="field12" id="12" value="QS">&nbsp;&nbsp;&nbsp;Quantity Surverying</input></td>
                        <td><input type="checkbox" name="field13" id="13" value="TC">&nbsp;&nbsp;&nbsp;Textile & Clothing Technology</input></td>
                        <td><input type="checkbox" name="field14" id="14" value="TCP">&nbsp;&nbsp;&nbsp;Town & Country Planning</input></td>
                        <td><input type="checkbox" name="field15" id="15" value="TLM">&nbsp;&nbsp;&nbsp;Transport & Logistics Management</input></td>
                    </tr>
                </table>
                <button type="button" onclick="select()">Select All</button>
            </div>
        </div>
        <div class="acc-box type-1">
            <span data-mode="toggle" class="acc-trigger">
                <a href="">By Company</a>
            </span>

            <div class="acc-container">
                <div class="styled-select">
                    <select name="pref">
                        <option value="None">Select</option>

                        <%
                            ArrayList<Company> list = CompanyDA.getCompany() ; 
                            for(int j=0;j<list.size();j++){

                        %>

                        <option value =<%=list.get(j).getShortName() %> ><%=list.get(j).getName() %> </option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <br/>    
                <input type="checkbox" name="fieldComp" value="yes" onChange="notifyCoord(this);">&emsp;All Undergrads in Fields </input><br/>
                <input type="checkbox" name="fieldAssign" value="yes" onChange="notifyCoord(this);">&emsp;All Undergrads Assigned</input><br/>
                <input type="checkbox" name="fieldPref" value="yes" onChange="notifyCoord(this);">&emsp;All Undergrads Preferred-Assigned</input><br/>
                <input type="checkbox" name="coord" id="c1" value="yes">&emsp;Company & Building Co-ordinator</input><br/>


                <br/>
            </div>

        </div>
        <div class="acc-box type-1">
            <span data-mode="toggle" class="acc-trigger">
                <a href="">Coordinators</a>
            </span>

            <div class="acc-container">
                <br/>    
                <input type="checkbox" name="allBuild" id="c2" value="yes" >&emsp;All Building Co-ordinators </input><br/>
                <input type="checkbox" name="allComp" value="yes" onChange="notifyCoord2(this);">&emsp;All Company Co-ordinators</input><br/>;
                <br/>
            </div>

        </div>
        <div class="acc-box type-1">
            <span data-mode="toggle" class="acc-trigger">
                <a href="">By Phone Number</a>
            </span>

            <div class="acc-container">
                <textarea name="commaNumbers" rows =10 cols =60 style="resize:vertical" placeholder="Comma Separated Numbers" onkeyup="countChar(this)"></textarea><br></br>
            </div>

        </div>
                    
        <br/><br/>
        <p align="left"> <input type="submit" value="Send" class="button"/>
            <input type="reset" value="Clear" class="button"/>

    </form>
</p>

</div>

<%@ include file="down.jsp" %>
<%} %>