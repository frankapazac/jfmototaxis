$(document).ready(function(){
	
	buscar("TMEESTADO_C", "A");
	
	$("#btnBuscarMedida").click(function(){
    	buscar($("#sltCriterioTipoMedida").val(),$("#txtTextoTipoMedida").val());
    });
	
	$("#btnCancelarMedida").click(function(){
    	$("#divNuevaMedida").dialog("close");
    });
	
	function buscar(criterio,texto){
		$.ajax({ 
    		data:{
    			criterio:criterio,
    			texto:texto
    		},
            datatype:'json',
            type: "POST", 
            url: "TipoMedida/Listar.htm", 
            success: function(data){
            	//alert(JSON.stringify(data));
            	llenarTabla(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	mensajeError();
            }
    	});
	}
	
	
	function llenarTabla(data){
    	if(data==""){ 
    		$("#tblListaMedida").empty();
    		return;
    	}
    	var txtHtml="";
    	$("#tblListaMedida").empty();
    	txtHtml="<thead>"
			+"<th class='header'>NUM</th>"
			+"<th class='header'>TIPO DE DOCUMENTO</th>"
			+"<th class='header'>ESTADO</th>"
			+"<th class='header'>EDITAR</th>"
			+"<th class='header'>ELIMINAR</th>"
			+"</thead>"
			+"<tfoot>"
			+"<th>NUM</th>"
			+"<th>TIPO DE DOCUMENTO</th>"
			+"<th>ESTADO</th>"
			+"<th>EDITAR</th>"
			+"<th>ELIMINAR</th>"
			+"</tfoot>"
			+"<tbody></tbody>";
			$("#tblListaMedida").append(txtHtml);
    	for(var x=0;x<data.length;x++){
    		txtHtml="<tr>"+
			"<td>"+(x+1)+"</td>"+
			"<td>"+data[x].tmedescripcionV +"</td>"+
			"<td>"+data[x].tmeestadoC +"</td>"+
			"<td><img alt='Modificar' class='btnModificarMed' id='mod"+data[x].tmecodigoI +"' src='images/edit.png'></td>"+
			"<td><img alt='Eliminar' class='btnEliminarMed' id='del"+data[x].tmecodigoI +"' src='images/delete.png'></td>"+
    		"</tr>";
    		$("#tblListaMedida tbody").append(txtHtml);
    	}

		$(".btnModificarMed").click(modificar);
    	$(".btnEliminarMed").click(eliminar);
    	paginacion();
    }


    function paginacion(){
		$("#tblListaMedida") 
        .tablesorter({widthFixed: true, widgets: ['zebra']}) 
        .tablesorterPager({container: $("#pager6")}); 	
	}
    
		$("#divNuevaMedida").hide();
	
	   $("#btnNuevaMedida").click(function(){
	    	llenarFormulario("");
	    });
	    
	        
		function llenarFormulario(data)
		{
		    	if(data!=""){	
		    		$("#txtCodigoMedida").val(data.tmecodigoI);
		    		$("#txtnombremedida").val(data.tmedescripcionV);
		    		$("#sltEstadoMedida").val(data.tmeestadoC);
		       	}else{
		    		$("#txtCodigoMedida").val(0);
		    		$("#txtnombremedida").val("");
		       	}   
		    	
		    	$("#divNuevaMedida").show();
		    	$("#divNuevaMedida").dialog({
		    		title:"Tipo Medida",
		    		width:500,
		    		height: 240,
		    		modal: true	
		    	});
		    	
		 }
		
		 $("#btnGuardarMedida").click(function(){
		    	$.ajax({ 
					data:{
						'tmecodigoI':$("#txtCodigoMedida").val(),
						'tmedescripcionV':$("#txtnombremedida").val(),
						'tmeestadoC':$("#sltEstadoMedida").val(),
					},
				    datatype:'json',
			        type: "POST", 
			        url: "TipoMedida/Procesar.htm", 
			        success: function(data){
			        	txtHtml="<p>Operación realizada correctamente</p>";
			        	mensaje(txtHtml);
			        },error: function(jqXHR, textStatus, errorThrown){
			        	mensajeError();
			        }
				});
		    	$("#divNuevaMedida").dialog("close");
		    	buscar("TMEESTADO_C", "A");
			});
		 
		 

		 function modificar(){ //para que funciones agregamos dentro de  llenarTabla(data) esto $(".btnModificar").click(modificar);
			   	$.ajax({ 
					data:{
						codigo:$(this).attr("id").replace("mod","")
					},
			        datatype:'json',
			        type: "GET", 
			        url: "TipoMedida/Obtener.htm", 
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
		                           url: "TipoMedida/Eliminar.htm",
		                           success: function(data){
		    							mensaje(data);
		                           },error: function(jqXHR, textStatus, errorThrown){
		                           	mensajeError();
		                           }
		    		        	});
		    		        	$(this).dialog('close');
		    					buscar("TMEESTADO_C", "A");
		    				},
		    				'Cancelar': function() {
		    					$(this).dialog('close');
		    				}
		    			}
		    		});
		       }
		
});
	