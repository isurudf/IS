<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page contentType = "text/html" pageEncoding = "utf-8"%>
<%
    Cookie cookies[] = request.getCookies();
	boolean logged=false;
    if (cookies != null) {
        for (int i = 0; i < cookies.length; i++) {
            if(cookies[i].getName().equals("user")) {
                logged=true;
            }
        }
    }
%>
	<link rel="stylesheet" type="text/css" href="engine1/style.css" />
	<script type="text/javascript" src="engine1/jquery.js"></script>
<%@ include file="up.jsp" %>



<!--/ .ajax-->



<!-- - - - - - - - - - - - - - Container - - - - - - - - - - - - - - - - -->	
<div class="pageshome">
 <div id="wowslider-container1">
	<div class="ws_images"><ul>
<li><img src="images/rur/slide1.jpg" alt="slide-1" title="slide-1" id="wows1_0"/></li>
<li><img src="images/rur/slide2.jpg" alt="slide-2" title="slide-2" id="wows1_1"/></li>
<li><img src="images/rur/slide3.jpg" alt="slide-3" title="slide-3" id="wows1_2"/></li>
<li><img src="images/rur/slide4.jpg" alt="slide-4" title="slide-4" id="wows1_3"/></li>
<li><img src="images/rur/slide5.jpg" alt="slide-5" title="slide-5" id="wows1_4"/></li>
<li><img src="images/rur/slide6.jpg" alt="slide-6" title="slide-6" id="wows1_5"/></li>
<li><img src="images/rur/slide7.jpg" alt="slide-7" title="slide-7" id="wows1_6"/></li>
<li><img src="images/rur/slide8.jpg" alt="slide-8" title="slide-8" id="wows1_7"/></li>
</ul></div>
<div class="ws_thumbs">
<div>
<a href="#" title="Sessions"><img src="images/rur/slide1.jpg" alt="" /></a>
<a href="#" title="Are You Ready? 2013"><img src="images/rur/slide2.jpg" alt="" /></a>
<a href="#" title="CV Clinic"><img src="images/rur/slide3.jpg" alt="" /></a>
<a href="#" title="Engineers' Forum"><img src="images/rur/slide4.jpg" alt="" /></a>
<a href="#" title="Criterion"><img src="images/rur/slide5.jpg" alt="" /></a>
<a href="#" title="You TM"><img src="images/rur/slide6.jpg" alt="" /></a>
<a href="#" title="Flagship Day"><img src="images/rur/slide7.jpg" alt="" /></a>
<a href="#" title="DE n G"><img src="images/rur/slide8.jpg" alt="" /></a>
</div>
</div>


	<div class="ws_shadow"></div>
	</div>
	<script type="text/javascript" src="engine1/wowslider.js"></script>
	<script type="text/javascript" src="engine1/script.js"></script>
</div> <br><br>
<br><br>
<div class="pages">
<div class="container ajax">


    <div class="sixteen columns">

        <p align="justify">

            “Are  You  Ready?”,  organized  by  the  Rotaract  Club  of  University  of  Moratuwa  in 
            collaboration  with  the  Career  Guidance  Unit  is  a  much  awaited  event  in  the  university 
            calendar  which  marks  the  careers’  week  of  the  university.  Introduced  in  1998,  this  year 
            marks  the  18th “Are  You  Ready?”.  The  limelight  of  the  event  is  on  the  mock  or  real 
            interviews,  conducted  by  prospective  employers,  for  the  passing  out  batch  of  the 
            university.  This  serves  as  a  pre-qualification  for  a  future  recruitment  after  the 
            undergraduates receive their bachelor’s degree. The program paves way for employers to 
            identify  befitting  and  resourceful  employees  among  the  fresh  graduates  from  the 
            university.<br><br>
            Furthermore,  "Are  You  Ready?  2013"  hosts  a  series  of  sessions  offering  specialized 
            knowledge  and  expertise  on  imperative  aspects  in  the  nature  of  career  guidance, 
            developing business trends and  personal grooming. The sessions aim at guiding budding 
            engineers to become effective leaders and productive entrepreneurs. 
        </p>
        <p align="justify">
            The  world  today  is  in  constant  search  for  high  performing  employees  and  satisfactory 
            employment. Undergraduates of  University of Moratuwa, though equipped with technical 
            competency  and  thorough  resourcefulness,  have  yet  to  be  educated  on  the  current 
            business culture and traits, what it demands from the fresh entrants to corporate sector.
            The  country’s  undergraduate  community,  though  having  great  potential,  has 
            demonstrated a worrying tendency towards being unaware of the professional etiquettes 
            of the commercial world. Therefore, the highly productive skills of these youngsters never 
            get exposed to the business arena.  <br><br>

            From the employers’ perspective, since the profile and capabilities of these graduates are 
            not highlighted in the right manner, they naturally tend to underestimate their talents and 
            go in search of better employees. 
            Here, the business world loses the suitable person with the  needed  talent. Such situations 
            may  very  well  lead  to  the  unfortunate  selection  of  less  capable  personnel  for  the 
            demanding positions in the corporate sector. 
            Realizing  this  gap  between  the  graduates  and  the  needs  of  the  corporate  sector,  the 
            Rotaract club of University of  Moratuwa  initiated the project  “Are You Ready?”,  which has 
            grown over the years to form a solid bridge between competent graduates and demands 
            of  the  business  world,  transforming  technically  sound  undergraduates  into  true 
            professionals.
        </p>


        <br><br>		

    </div><!--/ .columns-->



    <h3 class="section-title">Sessions</h3>
	

    <section id="main" class="twelve columns">

        <article class="entry clearfix">

            <div class="ten columns">



                <div class="entry-date">
                    <a href="cvclinic.jsp" class = "entry-cv">
                        <span class="entry-day">8</span>
                        <span class="entry-month">OCT</span>
                    </a>
                    <span class="entry-year">2013</span>
                </div><!--/ .entry-date-->

                <div class="entry-meta">
                    <h3 class="title"><a href="cvclinic.jsp">CV Clinic</a></h3>

                    <span class="author"><a href="organizers.jsp#cvclinic">Anushka Abeyrathna and Sameera Gunarathne</a></span>
                    <span class="categories"><a href="cvclinic.jsp">Curriculum Vitae Workshop</a></span>

                </div><!--/ .entry-meta-->

                <div class="clear"></div>
                <div class="entry-body">

                    <p align="justify">
                        Curriculum Vitae is the first image of the job seeker that 
                        impresses the employer. This session was initiated for the first time at an “Are You Ready” 
                        program on behalf of the undergraduates of the University to offer the proficiency of preparing 
                        the most important piece, the Curriculum Vitae before entrance to the corporate world.
                    </p>


                </div><!--/ .entry-body-->


            </div><!--/ .columns-->

        </article><!--/ .entry-->			


        <article class="entry clearfix">

            <div class="ten columns">



                <div class="entry-date" >
                    <a href="eforum.jsp" class = "entry-eforum">
                        <span class="entry-day">17</span>
                        <span class="entry-month">DEC</span>
                    </a>
                    <span class="entry-year">2013</span>
                </div><!--/ .entry-date-->

                <div class="entry-meta">
                    <h3 class="title"><a href="eforum.jsp">Engineers' Forum</a></h3>

                    <span class="author"><a href="organizers.jsp#eforum">Ishara Paranawithana and Dimithri Abeygunawardane</a></span>
                    <span class="categories"><a href="eforum.jsp">Engineering Problems Discussion</a></span>

                </div><!--/ .entry-meta-->

                <div class="clear"></div>
                <div class="entry-body">

                    <p align="justify">
                        This session is expected to create a platform for competent engineering undergraduates 
                        to  identify  various  aspects  of  engineering  problems  encountered  in  the  world  of 
                        employment. There will be a panel discussion regarding such problems in the industry 
                        and  how  those  problems  can  be  addressed  using  the  theoretical  and  practical 
                        knowledge of an engineer. Also the event is expected to give an insight into the current 
                        trends in the industry.
                    </p>

                </div><!--/ .entry-body-->


            </div><!--/ .columns-->

        </article><!--/ .entry-->	<article class="entry clearfix">

            <div class="ten columns">



                <div class="entry-date" >
                    <a href="criterion.jsp" class = "entry-criterion">
                        <span class="entry-day">18</span>
                        <span class="entry-month">DEC</span>
                    </a>
                    <span class="entry-year">2013</span>
                </div><!--/ .entry-date-->

                <div class="entry-meta">
                    <h3 class="title"><a href="criterion.jsp">The Criterion</a></h3>

                    <span class="author"><a href="organizers.jsp#criterion">Lovindu Wijesinghe and Thilini Perera</a></span>
                    <span class="categories"><a href="criterion.jsp">Interview Workshop</a></span>

                </div><!--/ .entry-meta-->

                <div class="clear"></div>
                <div class="entry-body">

                    <p align="justify">
                        The  session  comprises  of  a  workshop  which  will  be  helpful  to  inspire,  explore  and 
                        develop the skills that an undergraduate possesses and should possess, and at the same 
                        time, enabling him to better comprehend the values and traits that are sought after by 
                        the employers globally.
                    </p>



                </div><!--/ .entry-body-->


            </div><!--/ .columns-->

        </article><!--/ .entry-->	<article class="entry clearfix">

            <div class="ten columns">



                <div class="entry-date" >
                    <a href="youtm.jsp" class = "entry-youtm">
                        <span class="entry-day">19</span>
                        <span class="entry-month">DEC</span>
                    </a>
                    <span class="entry-year">2013</span>
                </div><!--/ .entry-date-->

                <div class="entry-meta">
                    <h3 class="title"><a href="youtm.jsp">YOU&trade;</a></h3>

                    <span class="author"><a href="organizers.jsp#youtm">Amitha Dissanayake and Lochana Ranaweera</a></span>
                    <span class="categories"><a href="youtm.jsp">Professional Branding Workshop</a></span>

                </div><!--/ .entry-meta-->

                <div class="clear"></div>
                <div class="entry-body">

                    <p align="justify">
                        Having a strong personal Brand requires being connected to a network of resources for mutual development and growth. YOU
                        TM 
                        is a session where our undergraduates get an 
                        opportunity to learn how to build their network and a unique personal brand.
                    </p>



                </div><!--/ .entry-body-->


            </div><!--/ .columns-->

        </article><!--/ .entry-->	<article class="entry clearfix">

            <div class="ten columns">



                <div class="entry-date" >
                    <a href="flagship.jsp" class = "entry-flagship">
                        <span class="entry-day">20</span>
                        <span class="entry-month">DEC</span>
                    </a>
                    <span class="entry-year">2013</span>
                </div><!--/ .entry-date-->

                <div class="entry-meta">
                    <h3 class="title"><a href="flagship.jsp">Flagship Day</a></h3>

                    <span class="author"><a href="organizers.jsp#flagship">Sumudu Herath and Himantha Alahakoon</a></span>
                    <span class="categories"><a href="flagship.jsp">Company Interviews</a></span>

                </div><!--/ .entry-meta-->

                <div class="clear"></div>
                <div class="entry-body">

                    <p align="justify">
                        This  is  the  prime  event  of  ‘Are  You  Ready?  programme,  in  which  the  prospective 
                        employers’ role comes into play. The employers are given the opportunity to interview 
                        the  skilful  and  resourceful  undergraduates  of  the  University.  This  will  be  a  great 
                        opportunity  for  the  employers  to  reach  the  undergraduates  of  the  University  and  to 
                        increase the interaction with them.
                    </p>



                </div><!--/ .entry-body-->


            </div><!--/ .columns-->

        </article><!--/ .entry-->	<article class="entry clearfix">

            <div class="ten columns">


                <div class="entry-date">
                    <a href="deng.jsp"  class = "entry-deng">
                        <span class="entry-day">30</span>
                        <span class="entry-month">DEC</span>    
					</a>
                    <span class="entry-year">2013</span>
                </div><!--/ .entry-date-->

                <div class="entry-meta">
                    <h3 class="title"><a href="deng.jsp">Dining Etiquette and Grooming</a></h3>
                    <span class="author"><a href="organizers.jsp#deng">Buddhi Herath and Chamara Herath</a></span>
                    <span class="categories"><a href="deng.jsp">Professional Etiquette Workshop</a></span>

                </div><!--/ .entry-meta-->

                <div class="clear"></div>
                <div class="entry-body">

                    <p align="justify">
                        In the  corporate world,  polite behaviour and effective presentation hold an  importance, 
                        as much as knowledge and skills. The Dining Etiquette and Grooming workshop is to 
                        acquaint the undergraduates with the accepted behaviour in the corporate world.
                    </p>



                </div><!--/ .entry-body-->


            </div><!--/ .columns-->

        </article><!--/ .entry-->



    </section>

     <aside id="sidebar" class="four columns">
	 
		<% if(!logged){
		%>
		<div class="widget widget_search">
            <h3 class="widget-title">Sign In</h3>
            <form method="post" action="LoginServlet" >
                <fieldset>
                    <dl>

                        <dd><input type="text" name="index" id="" size="25" placeholder="type your index number" /></dd>
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
                    <br />
					<p align = "left">
					<a href="undergradRegistration.jsp" class ="homelink" >Not Registered?</a>&nbsp;&nbsp;&nbsp;
					<a href="forgot.jsp">Forgot Password?</a>&nbsp;&nbsp;&nbsp;
                    </p>
					<br>
                    <p align="right">
                    <dl class="submit">
                        <input type="submit" name="submit" class="button" value="Login" />

                    </dl>
                    </p>



                </fieldset>

            </form><!--/ .contact-form-->	

        </div><!--/ .widget-->
        <% } %>
        
        <div class="widget widget_calendar">

            <div id="calendar_wrap">

                <table id="wp-calendar">

                    <caption>Events in December 2013</caption>

                    <thead>
                        <tr>
                            <th title="Sunday" scope="col">S</th>
                            <th title="Monday" scope="col">M</th>
                            <th title="Tuesday" scope="col">T</th>
                            <th title="Wednesday" scope="col">W</th>
                            <th title="Thursday" scope="col">T</th>
                            <th title="Friday" scope="col">F</th>
                            <th title="Saturday" scope="col">S</th>
                        </tr>
                    </thead>
                    <tfoot>

                    </tfoot>
                    <tbody>
                        <tr>

                            <td>1</td>
                            <td>2</td>
                            <td>3</td>
                            <td>4</td>
                            <td>5</td>
                            <td>6</td>
                            <td>7</td>
                        </tr>
                        <tr>

                            <td>8</td>
                            <td>9</td>
                            <td>10</td>
                            <td>11</td>
                            <td>12</td>
                            <td>13</td>
                            <td>14</td>
                        </tr>
                        <tr>

                            <td>15</td>
                            <td>16</td>
                            <td id="today"><a href="eforum.jsp" title="Engineers Forum" id ="today">17</a></td>
                            <td id="today"><a href="criterion.jsp" title="The Criterion" id ="today">18</a></td>
                            <td id="today"><a href="youtm.jsp" title="You TM" id ="today">19</a></td>
                            <td id="today"><a href="flagship.jsp" title="Flagship Day" id ="today">20</a></td>
                            <td>21</td>
                        </tr>
                        <tr>

                            <td>22</td>
                            <td>23</td>
                            <td>24</td>
                            <td>25</td>
                            <td>26</td>
                            <td >27</td>
                            <td>28</td>
                        </tr>
                        <tr>

                            <td>29</td>
                            <td id="today"><a href="deng.jsp" title="Dining Etiquette and Grooming" id ="today">30</a></td>
                            <td>31</td>
                            <td colspan="2" class="pad">&nbsp;</td>
                        </tr>
                    </tbody>
                </table>

            </div>

        </div><!--/ .widget-->





        <div class="widget widget_popular_posts">

            <h4 class="widget-title">Partners</h4>

            <ul>					
                <li> <div class="post-holder">
                        <h5 class="widget-heading">Strategic Partner
                        </h5>
                    </div>
                    <div class="image-holder">
                        <p align ="left"><a href="http://www.dimolanka.com/"><img src="images/rur/dimo.jpg" width="200" alt="" /></a></p>
                    </div>

                </li>
                <li> <div class="post-holder">
                        <h5 class="widget-heading">Platinum Sponsor
                        </h5>
                    </div>
                    <div class="image-holder">
                        <p align ="left"><a href="http://www.brandix.com/"><img src="images/rur/brandix.jpg" alt="" /></a></p>
                    </div>

                </li>
                <li> <div class="post-holder">
                        <h5 class="widget-heading">Gold Sponsor
                        </h5>
                    </div>
                    <div class="image-holder">
                        <p align ="left"><a href="http://www.mobitel.lk/"><img src="images/rur/mobitel.jpg" width="200" alt="" /></a></p>
                    </div>

                </li>
            </ul>

        </div><!--/ .widget-->




    </aside><!--/ #sidebar-->

</div><!--/ .container-->

</div>
<!-- - - - - - - - - - - - - end Container - - - - - - - - - - - - - - - - -->		
<!--/ .ajax-->

<%@ include file="down.jsp" %>