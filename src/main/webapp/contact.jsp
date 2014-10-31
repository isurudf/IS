
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="up.jsp" %>
<div class="dashboard">

    <h2>Contact Form</h2>
    <p align ="justify">
        You can write to us regarding any problem you have about Are You Ready? 2013 or any technical difficulties related to the site etc. 
        Our technical team will contact you within 24 hours after you post your inquiry. 
    </p>
    <div class="form">
        <form action="ContactFormServlet" name="contactUs" id="ContactForm" method="post" >
            <div>
                <div  class="wrapper">
                    <h4>Contact Details</h4>

                    <div class="bg"><input type="text" class="input" name="name" required="true" placeholder="your name"></div>
                </div>
                <div  class="wrapper">

                    <div class="bg"><input type="text" class="input" name="address" required="true" placeholder="your contact number"></div>								
                </div>
                <div  class="wrapper">

                    <div class="bg"><input type="text" class="input" name="email" required="true" placeholder="your contact email"></div>								
                </div><br>
                    <h4>Your Message</h4>
                    <div  class="textarea_box">

                        <div class="bg"><textarea name="message" cols="50" rows="5" required="true" placeholder="what do you have to say?"></textarea></div>	
                    </div><br><p align="right">
                            <input type="submit" value="Send" class="button" align="right">
                                <input type="reset" value="Clear" class="button"/></p>
                        </div>
                        </form>
                        </div>
                        </div>
                        <br><br>
                                <%@ include file="down.jsp" %>