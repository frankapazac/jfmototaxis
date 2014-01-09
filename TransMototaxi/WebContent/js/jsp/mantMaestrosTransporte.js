$(document).ready(function(){
	$("input[type=text]").keyup(function(){
		  $(this).val( $(this).val().toUpperCase() );
	});
	
	$("textarea").keyup(function(){
	  $(this).val( $(this).val().toUpperCase() );
	});
	
	$( "#tabs" ).tabs();
	// fix the classes
	/*$( ".tabs-bottom .ui-tabs-nav, .tabs-bottom .ui-tabs-nav > *" )
		.removeClass( "ui-corner-all ui-corner-top" )
		.addClass( "ui-corner-bottom" );
	 */
	
	
	// move the nav to the bottom
	//$( ".tabs-bottom .ui-tabs-nav" ).appendTo( ".tabs-bottom" );
	
});