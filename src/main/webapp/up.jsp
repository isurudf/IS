<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="javax.servlet.http.Cookie"%>
<%
    String name = "user", val=null;
    Cookie cookie[] = request.getCookies();
    if (cookie != null) {
        for (int i = 0; i < cookie.length; i++) {
            if (cookie[i].getName().equals(name)) {
                val = cookie[i].getValue();
                break;
            }
        }
    }

%>
<!--[if lte IE 8]>              <html class="ie8 no-js" lang="en">     <![endif]-->
<!--[if IE 9]>					<html class="ie9 no-js" lang="en">     <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--> <html class="not-ie no-js" lang="en">  <!--<![endif]-->
    <head>
        <!-- Google Web Fonts
  ================================================== -->
        <link href='http://fonts.googleapis.com/css?family=Allura|Oswald:400,700,300' rel='stylesheet' type='text/css'>

            <!-- Basic Page Needs
      ================================================== -->
            <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
            <!--[if ie]><meta http-equiv='X-UA-Compatible' content="IE=edge,IE=9,IE=8,chrome=1" /><![endif]-->

            <title>Are You Ready? 2013</title>

            <meta name="description" content="">
                <meta name="author" content="Rotaract Club of University of Moratuwa">
                    <link rel="shortcut" href="images/favicon.html" />

                    <!-- Mobile Specific Metas
              ================================================== -->
                    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

                        <!-- CSS
                  ================================================== -->
                        <link rel="stylesheet" href="css/style.css" />
                        <link rel="stylesheet" href="css/skeleton.css" />
                        <link rel="stylesheet" href="css/layout.css" />

                        <link rel="stylesheet" href="css/font-awesome.css" />
                        <link rel="stylesheet" href="js/fancybox/jquery.fancybox.css" />
                        <link rel="stylesheet" href="js/epicslider/epicslider.css" />
                        <link rel="stylesheet" href="js/epicslider/epicslider-reponsive.css" />

                        <!-- HTML5 Shiv
                        ================================================== -->
                        <script src="js/jquery.modernizr.js"></script>


                        </head>
                        <body>




                            <header id="header">

                                <div class="container">

                                    <div class="sixteen columns">



                                        <nav id="navigation" class="navigation">

                                            <div class="menu">

                                                <ul>
                                                    <li><a href="index.jsp">Home</a></li>
                                                    <li><a href="">Sessions</a>
                                                        <ul>
                                                            <li><a href="cvclinic.jsp">CV Clinic</a></li>
                                                            <li><a href="eforum.jsp">Engineers' Forum</a></li>
                                                            <li><a href="criterion.jsp">The Criterion</a></li>
                                                            <li><a href="youtm.jsp">You&trade;</a></li>
                                                            <li><a href="flagship.jsp">Flagship Day</a></li>
                                                            <li><a href="deng.jsp">Dining Etiquette and Grooming</a></li>
                                                        </ul>
                                                    </li>
                                                    <li><a href="">Gallery</a>                        
                                                        <ul>

                                                            <li><a href="">Are You Ready? 2012</a><ul>  	
                                                                    <li><a href="albumeforum.jsp">Engineers' Forum</a></li>
                                                                    <li><a href="albumbenchmark.jsp">Benchmark</a></li>
                                                                    <li><a href="albumbeknown.jsp">Be Known</a></li>
                                                                    <li><a href="albumflag.jsp">Flagship Day</a></li>
                                                                    <li><a href="albumdeng.jsp">Dining Etiquettes and Grooming</a></li></ul></li>
                                                            <li><a href="">Are You Ready? 2013</a><ul>  
                                                                    <li><a href="albumcv.jsp">CV Clinic</a></li></ul>

                                                            </li>


                                                        </ul>
                                                    </li> <li><a href="">About Us</a>
                                                        <ul>
                                                            <li><a href="">Partners</a>
                                                                <ul>
                                                                    <li><a href="http://www.dimolanka.com/">DIMO</a></li>
                                                                    <li><a href="http://www.brandix.com/">Brandix</a></li>
                                                                    <li><a href="http://www.mobitel.lk">Mobitel</a></li>
                                                                </ul>
                                                            </li>
                                                            <li><a href="rotaract.jsp">Rotaract Mora </a></li>
                                                            <li><a href="organizers.jsp">Organizers</a></li>
                                                            <li><a href="contact.jsp">Contact Us</a></li>

                                                        </ul>
                                                    </li>

                                                    <li><a href="companies.jsp">Companies</a></li>
                                                    <li><a href="">My Profile</a>
                                                        <% if (val != null&&val.length()>38) {
                                                        %>
                                                        <ul>
                                                            <li>
                                                                <a href = "undergradDashboard.jsp" >My Preferences <br>
								(<%=val.substring(32,39) %>)</a>
                                                            </li>
                                                            <li>
                                                                <a href = "logout.jsp" class ="logout">Logout</a>
                                                            </li>
                                                        </ul>
                                                        <%}
                                                           else{
                                                        %>
                                                        <ul>
                                                            <li>
                                                                <a href = "login.jsp" >Log In</a>
                                                            </li>
                                                   <!--        <li>
                                                                <a href = "undergradRegistration.jsp" >Register</a>
                                                            </li>   -->
                                                        </ul>

                                                        <%} %>




                                                    </li>






                                                </ul>

                                            </div><!--/ .menu-->

                                        </nav><!--/ #navigation-->	
                                        <div id="logo">
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <img src="images/rur/Header.png" alt="" width="200" height="50">
                                        </div>

                                    </div><!--/ .columns-->

                                </div><!--/ .container-->

                            </header><!--/ #header-->

                            <!-- - - - - - - - - - - - - - end Header - - - - - - - - - - - - - - - - -->	


                            <!-- - - - - - - - - - - - - - - - Header - - - - - - - - - - - - - - - - -->	


                            <!-- - - - - - - - - - - - - - - - Dynamic Content - - - - - - - - - - - - - - - - -->	

                            <div id="wrapper" class="sbr">

                                <div id="content">