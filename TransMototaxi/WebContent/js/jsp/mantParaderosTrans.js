$(document).ready(function(){
	
	buscar("PAR.PARESTADO_C", "A");
	
	$("#btnBuscarParadero").click(function(){
    	buscar($("#sltCriterioParadero").val(),$("#txtTextoParadero").val());
    });
	
	$("#btnCancelarParadero").click(function(){
    	$("#divNuevoParadero").dialog("close");
    });
	
	function buscar(criterio,texto){
		$.ajax({ 
    		data:{
    			criterio:criterio,
    			texto:texto
    		},
            datatype:'json',
            type: "POST", 
            url: "Paradero/ListarParaderoTrans.htm", 
            success: function(data){
            	llenarTabla(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	mensajeError();
            }
    	});
	}
	
	function llenarTabla(data){
    	if(data==""){ 
    		$("#tblListaParaderos").empty();
    		return;
    	}
    	var txtHtml="";
    	$("#tblListaParaderos").empty();
    	txtHtml="<thead>"
			+"<th class='header'>NUM</th>"
			+"<th class='header'>NOMBRE</th>"
			+"<th class='header'>UBICACIÓN</th>"
			+"<th class='header'>ZONA ADMINISTRATIVA</th>"
			+"<th class='header'>ESTADO</th>"
			+"<th class='header'>ACTUALIZAR</th>"
			+"<th class='header'>ELIMINAR</th>"
			+"</thead>"
			+"<tfoot>"
			+"<th>NUM</th>"
			+"<th>NOMBRE</th>"
			+"<th>UBICACIÓN</th>"
			+"<th>ZONA ADMINISTRATIVA</th>"
			+"<th>ESTADO</th>"
			+"<th>ACTUALIZAR</th>"
			+"<th>ELIMINAR</th>"
			+"</tfoot>"
			+"<tbody></tbody>";
			$("#tblListaParaderos").append(txtHtml);
    	for(var x=0;x<data.length;x++){
    		txtHtml="<tr>"+
			"<td>"+(x+1)+"</td>"+
			"<td>"+data[x].parnombreV+"</td>"+
			"<td>"+data[x].parubicacionV+"</td>"+
			"<td>"+data[x].zona.zonnombre_V +"</td>"+
			"<td>"+data[x].parestadoC+"</td>"+
			"<td><img alt='Modificar' class='btnModificarParadero' id='mod"+data[x].parcodigoI +"' src='images/edit.png'></td>"+
			"<td><img alt='Eliminar' class='btnEliminarParadero' id='del"+data[x].parcodigoI+"' src='images/delete.png'></td>"+"</tr>";
    		$("#tblListaParaderos tbody").append(txtHtml);
    	}
    	
    	$(".btnModificarParadero").click(modificar);
    	$(".btnEliminarParadero").click(eliminar);
		

    	$("#tblListaParaderos").paginacionPager("#pager3");
    }
    
	$("#divNuevoParadero").hide();
	
	
    $("#btnNuevoParadero").click(function(){
    	llenarFormulario("");
    });
    
	function llenarFormulario(data)
	{
	    	if(data!=""){
	    		alert(data.parcodigoI);
	    		$("#txtCodigoParadero").val(data.parcodigoI);
	    		$("#txtnombreparadero").val(data.parnombreV);
	    		$("#txtUbicacion").val(data.parubicacionV);
	    		$("#sltZonaAdministrativa").val(data.zona.zoncodigo_I);
	    		$("#sltEstadoParadero").val(data.parestadoC);
	    	   	//alert("txtcodigoOperativo : " + $("#sltEstado").val());
	       	}else{
	    		$("#txtCodigoParadero").val(0);
	    		$("#txtnombreparadero").val("");
	    		$("#txtUbicacion").val("");
	    		//$("#sltZonaAdministrativa").empty();
	       	}   
	    	
	    	$("#divNuevoParadero").show();
	    	$("#divNuevoParadero").dialog({
	    		title:"Nuevo Paradero",
	    		width:700,
	    		height: 350,
	    		modal: true	
	    	});
	    	
	 }
	
	
    //ProcesarParadero
    $("#btnGuardarParadero").click(function(){
    	$.ajax({ 
			data:{
				'parcodigoI':$("#txtCodigoParadero").val(),
				'parnombreV':$("#txtnombreparadero").val(),
				'parubicacionV':$("#txtUbicacion").val(),
				'zona.zoncodigo_I':$("#sltZonaAdministrativa").val(),
				'parestadoC':$("#sltEstado").val(),
			},
		    datatype:'json',
	        type: "POST", 
	        url: "Paradero/ProcesarParadero.htm", 
	        success: function(data){
	        	txtHtml="<p>Operación realizada correctamente</p>";
	        	mensaje(txtHtml);
	        },error: function(jqXHR, textStatus, errorThrown){
	        	mensajeError();
	        }
		});
    	$("#divNuevoParadero").dialog("close");
    	buscar("PAR.PARESTADO_C", "A");
	});
    
	 function modificar(){ 
		   	$.ajax({ 
				data:{
					codigo:$(this).attr("id").replace("mod","")
				},
		        datatype:'json',
		        type: "GET", 
		        url: "Paradero/Obtener.htm", 
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
	                           url: "Paradero/EliminarParadero.htm",
	                           success: function(data){
	    							mensaje(data);
	                           },error: function(jqXHR, textStatus, errorThrown){
	                           	mensajeError();
	                           }
	    		        	}); 
	    					buscar("PAR.PARESTADO_C", "A");
	    		        	$(this).dialog('close');
	    				},
	    				'Cancelar': function() {
	    					$(this).dialog('close');
	    				}
	    			}
	    		});
	       }
	
});