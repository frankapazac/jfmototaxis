$(document).ready(function(){
	
	buscar("ZONESTADO_C", "A");
	
	$("#btnBuscarZona").click(function(){
    	buscar($("#sltCriterioZona").val(),$("#txtTextoZona").val());
    });
	
	$("#btnCancelarZona").click(function(){
    	$("#divNuevaZona").dialog("close");
    });
	
	function buscar(criterio,texto){
		$.ajax({ 
    		data:{
    			criterio:criterio,
    			texto:texto
    		},
            datatype:'json',
            type: "POST", 
            url: "Zona/Listar.htm", 
            success: function(data){
            	llenarTabla(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	mensajeError();
            }
    	});
	}
	
	
	function llenarTabla(data){
    	if(data==""){ 
    		$("#tblListaZona").empty();
    		return;
    	}
    	var txtHtml="";
    	$("#tblListaZona").empty();
    	txtHtml="<thead>"
			+"<th class='header'>NUM</th>"
			+"<th class='header'>NOMBRE ZONA</th>"
			+"<th class='header'>ESTADO</th>"
			+"<th class='header'>EDITAR</th>"
			+"<th class='header'>ELIMINAR</th>"
			+"</thead>"
			+"<tfoot>"
			+"<th>NUM</th>"
			+"<th>NOMBRE ZONA</th>"
			+"<th>ESTADO</th>"
			+"<th>EDITAR</th>"
			+"<th>ELIMINAR</th>"
			+"</tfoot>"
			+"<tbody></tbody>";
			$("#tblListaZona").append(txtHtml);
    	for(var x=0;x<data.length;x++){
    		txtHtml="<tr>"+
			"<td>"+(x+1)+"</td>"+
			"<td>"+data[x].zonnombre_V +"</td>"+
			"<td>"+data[x].zonEstado_C +"</td>"+
			"<td><img alt='Modificar' class='btnModificarZona' id='mod"+data[x].zoncodigo_I +"' src='images/edit.png'></td>"+
			"<td><img alt='Eliminar' class='btnEliminarZona' id='del"+data[x].zoncodigo_I +"' src='images/delete.png'></td>"+
    		"</tr>";
    		$("#tblListaZona tbody").append(txtHtml);
    	}

		$(".btnModificarZona").click(modificar);
    	$(".btnEliminarZona").click(eliminar);

    	$("#tblListaZona").paginacionPager("#pager7");
    }

    
    $("#divNuevaZona").hide();
	
	   $("#btnNuevaZona").click(function(){
	    	llenarFormulario("");
	    });
	    
	        
		function llenarFormulario(data)
		{
		    	if(data!=""){	
		    		$("#txtCodigoZona").val(data.zoncodigo_I);
		    		$("#txtnombrezona").val(data.zonnombre_V);
		    		$("#sltEstadoZona").val(data.zonEstado_C);
		       	}else{
		    		$("#txtCodigoZona").val(0);
		    		$("#txtnombrezona").val("");
		       	}   
		    	
		    	$("#divNuevaZona").show();
		    	$("#divNuevaZona").dialog({
		    		title:"Zona Administrativa",
		    		width:500,
		    		height: 240,
		    		modal: true	
		    	});
		    	
		 }
	
		 $("#btnGuardarZona").click(function(){
		    	$.ajax({ 
					data:{
						'zoncodigo_I':$("#txtCodigoZona").val(),
						'zonnombre_V':$("#txtnombrezona").val(),
						'zonEstado_C':$("#sltEstadoZona").val(),
					},
				    datatype:'json',
			        type: "POST", 
			        url: "Zona/Procesar.htm", 
			        success: function(data){
			        	txtHtml="<p>Operación realizada correctamente</p>";
			        	mensaje(txtHtml);
			        },error: function(jqXHR, textStatus, errorThrown){
			        	mensajeError();
			        }
				});	
		    	$("#divNuevaZona").dialog("close");
		    	buscar("ZONESTADO_C", "A");
			});
		 
		 function modificar(){
			   	$.ajax({ 
					data:{
						codigo:$(this).attr("id").replace("mod","")
					},
			        datatype:'json',
			        type: "GET", 
			        url: "Zona/Obtener.htm", 
			        success: function(data){
			        	llenarFormulario(data);
			        },error: function(jqXHR, textStatus, errorThrown){
			        	mensajeError();
			        }
				});
		 }
		 

		 function eliminar(){ 
		    	var codigo=$(this).attr("id").replace("del","");
		       	$("#divConfirmacion").remove();
		       	$("body").append("<div id='divConfirmacion'><p>¿Esta seguro que desea eliminar?</p></div>");
		       	$("#divConfirmacion").dialog({
		    			title:'Eliminar',
		    			resizable: false,
		    			height:180,
		    			modal: true,
		    			overlay: {
		    				backgroundColor: '#000',
		    				opacity: 0.5
		    			},buttons: {
		    				'Eliminar': function() {
		    					$.ajax({ 
		    		        		data:{
		    		        			codigo:codigo
		    		        		},
		                           datatype:'html',
		                           type: "GET", 
		                           url: "Zona/Eliminar.htm",
		                           success: function(data){
		    							mensaje(data);
		                           },error: function(jqXHR, textStatus, errorThrown){
		                           	mensajeError();
		                           }
		    		        	});
		    					buscar("ZONESTADO_C", "A");
		    		        	$(this).dialog('close');
		    				},
		    				'Cancelar': function() {
		    					$(this).dialog('close');
		    				}
		    			}
		    		});
		       }
});