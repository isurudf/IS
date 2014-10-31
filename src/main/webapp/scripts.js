
ddsmoothmenu.init({
	mainmenuid: "smoothmenu1", 
	orientation: 'h', 
	classname: 'ddsmoothmenu', 
	shadow: {enable:false}, 
	
	contentsource: "markup" 
})


$(function() {

$('ul.grid img, .post a img, #about a img, .content a img').css("opacity","1.0");	

$('ul.grid img, .post a img, #about a img, .content a img').hover(function () {										  

$(this).stop().animate({ opacity: 0.75 }, "fast"); },	

function () {			

$(this).stop().animate({ opacity: 1.0 }, "fast");
});
});


$(function() {

$('a.button, .comment-form input#submit-button, #contact-form input#submit-button').css("opacity","1.0");	

$('a.button, .comment-form input#submit-button, #contact-form input#submit-button').hover(function () {										  

$(this).stop().animate({ opacity: 0.8 }, "fast"); },	

function () {			

$(this).stop().animate({ opacity: 1.0 }, "fast");
});
});

$(document).ready(function(){

$(".togglebox").hide();

$("h2").click(function(){

$(this).toggleClass("active").next(".togglebox").slideToggle("slow");
return true;
});
});

$(document).ready(function() {	
  //Get all the LI from the #tabMenu UL
  $('#tab-menu > li').click(function(){
    //remove the selected class from all LI    
    $('#tab-menu > li').removeClass('selected');
    //Reassign the LI
    $(this).addClass('selected');
    //Hide all the DIV in .tab-content
    $('.tab-content div.tab').slideUp('slow');
    //Look for the right DIV in boxBody according to the Navigation UL index, therefore, the arrangement is very important.
    $('.tab-content div.tab:eq(' + $('#tab-menu > li').index(this) + ')').slideDown('slow');
  }).mouseover(function() {
    //Add and remove class, Personally I dont think this is the right way to do it, anyone please suggest    
    $(this).addClass('mouseover');
    $(this).removeClass('mouseout');   
  }).mouseout(function() {
    //Add and remove class
    $(this).addClass('mouseout');
    $(this).removeClass('mouseover');    
  });
});


$(function() {
            var offset = $("#tab-menu").offset();
            var topPadding = 15;
            $(window).scroll(function() {
                if ($(window).scrollTop() > offset.top) {
                    $("#tab-menu").stop().animate({
                        marginTop: $(window).scrollTop() - offset.top + topPadding
                    });
                } else {
                    $("#tab-menu").stop().animate({
                        marginTop: 0
                    });
                };
            });
});

$(document).ready(function() {

	$(".tab_content").hide(); 
	$("ul.tabs li:first").addClass("active").show(); 
	$(".tab_content:first").show(); 
	

	$("ul.tabs li").click(function() {
		$("ul.tabs li").removeClass("active"); 
		$(this).addClass("active");
		$(".tab_content").hide(); 
		var activeTab = $(this).find("a").attr("href"); 
		$(activeTab).fadeIn(); 
		return false;
	});

});