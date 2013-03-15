$(document).ready(function(){
	
	buscar("MARESTADO_C", "A");
	
	$("#btnBuscarMarca").click(function(){
    	buscar($("#sltCriterioMarca").val(),$("#txtTextoMarca").val());
    });
	
	$("#btnCancelarMarca").click(function(){
    	$("#divNuevaMarca").dialog("close");
    });
	
	function buscar(criterio,texto){
		$.ajax({ 
    		data:{
    			criterio:criterio,
    			texto:texto
    		},
            datatype:'json',
            type: "POST", 
            url: "Marca/Listar.htm", 
            success: function(data){
            	llenarTabla(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	mensajeError();
            }
    	});
	}
	
	
	function llenarTabla(data){
    	if(data==""){ 
    		$("#tblListaMarca").empty();
    		return;
    	}
    	var txtHtml="";
    	$("#tblListaMarca").empty();
    	txtHtml="<thead>"
			+"<th class='header'>NUM</th>"
			+"<th class='header'>NOMBRE MARCA</th>"
			+"<th class='header'>ESTADO</th>"
			+"<th class='header'>EDITAR</th>"
			+"<th class='header'>ELIMINAR</th>"
			+"</thead>"
			+"<tfoot>"
			+"<th>NUM</th>"
			+"<th>NOMBRE MARCA</th>"
			+"<th>ESTADO</th>"
			+"<th>EDITAR</th>"
			+"<th>ELIMINAR</th>"
			+"</tfoot>"
			+"<tbody></tbody>";
			$("#tblListaMarca").append(txtHtml);
    	for(var x=0;x<data.length;x++){
    		txtHtml="<tr>"+
			"<td>"+(x+1)+"</td>"+
			"<td>"+data[x].marnombreV +"</td>"+
			"<td>"+data[x].marestadoC +"</td>"+
			"<td><img alt='Modificar' class='btnModificarMarca' id='mod"+data[x].marcodigoI +"' src='images/edit.png'></td>"+
			"<td><img alt='Eliminar' class='btnEliminarMarca' id='del"+data[x].marcodigoI +"' src='images/delete.png'></td>"+
    		"</tr>";
    		$("#tblListaMarca tbody").append(txtHtml);
    	}

		$(".btnModificarMarca").click(modificar);
    	$(".btnEliminarMarca").click(eliminar);
    	paginacion();
    }


    function paginacion(){
		$("#tblListaMarca") 
        .tablesorter({widthFixed: true, widgets: ['zebra']}) 
        .tablesorterPager({container: $("#pager8")}); 	
	}
    
    
    //
    $("#divNuevaMarca").hide();
	
	   $("#btnNuevaMarca").click(function(){
	    	llenarFormulario("");
	    });
	    
	        
		function llenarFormulario(data)
		{
		    	if(data!=""){	
		    		$("#txtCodigoMarca").val(data.marcodigoI);
		    		$("#txtnombreMarca").val(data.marnombreV);
		    		$("#sltEstadoMarca").val(data.marestadoC);
		       	}else{
		    		$("#txtCodigoMarca").val(0);
		    		$("#txtnombreMarca").val("");
		       	}   
		    	
		    	$("#divNuevaMarca").show();
		    	$("#divNuevaMarca").dialog({
		    		title:"Marca",
		    		width:500,
		    		height: 240,
		    		modal: true	
		    	});
		    	
		 }
	
		 $("#btnGuardarMarca").click(function(){
		    	$.ajax({ 
					data:{
						'marcodigoI':$("#txtCodigoMarca").val(),
						'marnombreV':$("#txtnombreMarca").val(),
						'marestadoC':$("#sltEstadoMarca").val(),
					},
				    datatype:'json',
			        type: "POST", 
			        url: "Marca/Procesar.htm", 
			        success: function(data){
			        	txtHtml="<p>Operación realizada correctamente</p>";
			        	mensaje(txtHtml);
			        },error: function(jqXHR, textStatus, errorThrown){
			        	mensajeError();
			        }
				});	
		    	$("#divNuevaMarca").dialog("close");
		    	buscar("MARESTADO_C", "A");
			});
		 
		 function modificar(){
			   	$.ajax({ 
					data:{
						codigo:$(this).attr("id").replace("mod","")
					},
			        datatype:'json',
			        type: "GET", 
			        url: "Marca/Obtener.htm", 
			        success: function(data){
			        	alert(JSON.stringify(data));
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
		                           url: "Marca/Eliminar.htm",
		                           success: function(data){
		    							mensaje(data);
		                           },error: function(jqXHR, textStatus, errorThrown){
		                           	mensajeError();
		                           }
		    		        	});
		    		        	$(this).dialog('close');
		    		        	buscar("MARESTADO_C", "A");
		    				},
		    				'Cancelar': function() {
		    					$(this).dialog('close');
		    				}
		    			}
		    		});
		       }
    //
});
	
	