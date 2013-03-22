(function($){	
	//$.message[Info(titulo,texto),Find(),Success(),Insert(),Delete(),Update(),Get(),Error()]
	$.message = {
		Info: function(titulo,texto) {
			$("body #divMenssage").remove();
			$("body").append("<div id='divMenssage' class='ui-widget-content ui-corner-all'>" +
					"<h3 class='ui-widget-header ui-corner-all'>"+titulo+"</h3>" +
					"<p>"+texto+"</p>" +
					"<img src='images/information.png'>"+
					"</div>");
			$("body #divMenssage").css({'width': '240px', 'height': '135px', 'padding': '0.4em', 
				'position': 'absolute', 'bottom':'5px','right':'10px','z-index':'1300'});
			$("body #divMenssage h3").css({ 'margin': '0', 'padding': '0.4em', 'text-align': 'center'});
			$("body #divMenssage img").css({'padding-bottom': '10px','padding-right': '10px','float': 'right'});
			$("#divMenssage").effect("clip", {}, 3000, fncClose);
		},
		Find: function(){
			$("body #divMenssage").remove();
			$("body").append("<div id='divMenssage' class='ui-widget-content ui-corner-all'>" +
					"<h3 class='ui-widget-header ui-corner-all'>Mensaje</h3>" +
					"<p>Busqueda completada</p>" +
					"<img src='images/table.png'>"+
					"</div>");
			$("body #divMenssage").css({'width': '240px', 'height': '135px', 'padding': '0.4em', 
				'position': 'absolute', 'bottom':'5px','right':'10px','z-index':'1300'});
			$("body #divMenssage h3").css({ 'margin': '0', 'padding': '0.4em', 'text-align': 'center'});
			$("body #divMenssage img").css({'padding-bottom': '10px','padding-right': '10px','float': 'right'});
			$("#divMenssage").effect("clip", {}, 3000, fncClose);
		},
		Success: function(){
			$("body #divMenssage").remove();
			$("body").append("<div id='divMenssage' class='ui-widget-content ui-corner-all'>" +
					"<h3 class='ui-widget-header ui-corner-all'>Mensaje</h3>" +
					"<p>Operación realizada exitosamente</p>" +
					"<img src='images/accept.png'>"+
					"</div>");
			$("body #divMenssage").css({'width': '240px', 'height': '135px', 'padding': '0.4em', 
				'position': 'absolute', 'bottom':'5px','right':'10px','z-index':'1300'});
			$("body #divMenssage h3").css({ 'margin': '0', 'padding': '0.4em', 'text-align': 'center'});
			$("body #divMenssage img").css({'padding-bottom': '10px','padding-right': '10px','float': 'right'});
			$("#divMenssage").effect("clip", {}, 3000, fncClose);
		},
		Insert: function(){
			$("body #divMenssage").remove();
			$("body").append("<div id='divMenssage' class='ui-widget-content ui-corner-all'>" +
					"<h3 class='ui-widget-header ui-corner-all'>Mensaje</h3>" +
					"<p>Insersión realizada exitosamente</p>" +
					"<img src='images/table_insert.png'>"+
					"</div>");
			$("body #divMenssage").css({'width': '240px', 'height': '135px', 'padding': '0.4em', 
				'position': 'absolute', 'bottom':'5px','right':'10px','z-index':'1300'});
			$("body #divMenssage h3").css({ 'margin': '0', 'padding': '0.4em', 'text-align': 'center'});
			$("body #divMenssage img").css({'padding-bottom': '10px','padding-right': '10px','float': 'right'});
			$("#divMenssage").effect("clip", {}, 3000, fncClose);
		},
		Delete: function(){
			$("body #divMenssage").remove();
			$("body").append("<div id='divMenssage' class='ui-widget-content ui-corner-all'>" +
					"<h3 class='ui-widget-header ui-corner-all'>Mensaje</h3>" +
					"<p>Eliminación realizada exitosamente</p>" +
					"<img src='images/table_delete.png'>"+
					"</div>");
			$("body #divMenssage").css({'width': '240px', 'height': '135px', 'padding': '0.4em', 
				'position': 'absolute', 'bottom':'5px','right':'10px','z-index':'1300'});
			$("body #divMenssage h3").css({ 'margin': '0', 'padding': '0.4em', 'text-align': 'center'});
			$("body #divMenssage img").css({'padding-bottom': '10px','padding-right': '10px','float': 'right'});
			$("#divMenssage").effect("clip", {}, 3000, fncClose);
		},
		Update: function(){
			$("body #divMenssage").remove();
			$("body").append("<div id='divMenssage' class='ui-widget-content ui-corner-all'>" +
					"<h3 class='ui-widget-header ui-corner-all'>Mensaje</h3>" +
					"<p>Actualización realizada exitosamente</p>" +
					"<img src='images/table_refresh.png'>"+
					"</div>");
			$("body #divMenssage").css({'width': '240px', 'height': '135px', 'padding': '0.4em', 
				'position': 'absolute', 'bottom':'5px','right':'10px','z-index':'1300'});
			$("body #divMenssage h3").css({ 'margin': '0', 'padding': '0.4em', 'text-align': 'center'});
			$("body #divMenssage img").css({'padding-bottom': '10px','padding-right': '10px','float': 'right'});
			$("#divMenssage").effect("clip", {}, 3000, fncClose);
		},
		File: function(){
			$("body #divMenssage").remove();
			$("body").append("<div id='divMenssage' class='ui-widget-content ui-corner-all'>" +
					"<h3 class='ui-widget-header ui-corner-all'>Mensaje</h3>" +
					"<p>Archivo subido exitosamente</p>" +
					"<img src='images/inbox_upload.png'>"+
					"</div>");
			$("body #divMenssage").css({'width': '240px', 'height': '135px', 'padding': '0.4em', 
				'position': 'absolute', 'bottom':'5px','right':'10px','z-index':'1300'});
			$("body #divMenssage h3").css({ 'margin': '0', 'padding': '0.4em', 'text-align': 'center'});
			$("body #divMenssage img").css({'padding-bottom': '10px','padding-right': '10px','float': 'right'});
			$("#divMenssage").effect("clip", {}, 3000, fncClose);
		},
		Get: function(){
			$("body #divMenssage").remove();
			$("body").append("<div id='divMenssage' class='ui-widget-content ui-corner-all'>" +
					"<h3 class='ui-widget-header ui-corner-all'>Mensaje</h3>" +
					"<p>Selección obtenida</p>" +
					"<img src='images/table_edit.png'>"+
					"</div>");
			$("body #divMenssage").css({'width': '240px', 'height': '135px', 'padding': '0.4em', 
				'position': 'absolute', 'bottom':'5px','right':'10px','z-index':'1300'});
			$("body #divMenssage h3").css({ 'margin': '0', 'padding': '0.4em', 'text-align': 'center'});
			$("body #divMenssage img").css({'padding-bottom': '10px','padding-right': '10px','float': 'right'});
			$("#divMenssage").effect("clip", {}, 3000, fncClose);
		},
		Error: function(){
			$("body #divMenssage").remove();
			$("body").append("<div id='divMenssage' class='ui-widget-content ui-corner-all'>" +
					"<p>Se encontro un error, por favor intente mas tarde.</p>" +
					"<img src='images/Error32x32.png'>"+
					"<input type='button' id='btnErrorAceptar' value='Aceptar' class='ui-button'/>"+
					"</div>");
			$("body #divMenssage").css({'width': '240px', 'height': '135px'});
			$("body #divMenssage img").css({'padding-bottom': '10px','padding-right': '10px','float': 'right'});
			$("body #divMenssage").dialog({
				title:"Error",
	    		modal: true
	    	});
			$("body #divMenssage #btnErrorAceptar").click(function(){
				$("body #divMenssage").dialog("close");
			});
		},
		
		/*Confirmation: function(titulo,mensaje){
			$("body #divMenssage").remove();
			$("body").append("<div id='divMenssage' class='ui-widget-content ui-corner-all'>" +
					"<p>"+mensaje+"</p>" +
					"<img src='images/answer.png'>"+
					"<input type='button' id='btnConfirmationYes' value='Si' class='ui-button'/>"+
					"<input type='button' id='btnConfirmationNo' value='No' class='ui-button'/>"+
							"</div>");
			$("body #divMenssage").css({'width': '240px', 'height': '135px'});
			$("body #divMenssage img").css({'padding-bottom': '10px','padding-right': '10px','float': 'right'});
			$("body #divMenssage").dialog({
				title:titulo,
	    		modal: true,
	    		buttons: {
	    	        "ok": function() {
	    	            $(this).dialog("close");
	    	            processResult(true);
	    	        },
	    	        "cancel": function() {
	    	            $(this).dialog("close");
	    	            processResult(false);
	    	        }
	    		}
	    	});

			$("body #divMenssage #btnConfirmationYes").click(function(){
				$("body #divMenssage").dialog("close");
			});
			$("body #divMenssage #btnConfirmationNo").click(function(){
				$("body #divMenssage").dialog("close");
				evt.stopPropagation();				
			});
		}*/
	};	
	
	function fncClose() {
		setTimeout(function() {
			$(this).removeAttr( "style" ).hide().fadeIn();
		}, 2000 );
		$(this).remove();
	};

})(jQuery);
