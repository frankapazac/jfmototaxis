$(document).ready(function(){
	buscar("MODESTADO_V", "A");
	
	$("#btnBuscarModelo").click(function(){
    	buscar($("#sltCriterioModelo").val(),$("#txtTextoModelo").val());
    });
	
	$("#btnCancelarModelo").click(function(){
    	$("#divNuevaModelo").dialog("close");
    });
	function buscar(criterio,texto){
		$.ajax({ 
    		data:{
    			criterio:criterio,
    			texto:texto
    		},
            datatype:'json',
            type: "POST", 
            url: "Modelo/Listar.htm", 
            success: function(data){
            	llenarTabla(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	mensajeError();
            }
    	});
	}
	
	
	function llenarTabla(data){
    	if(data==""){ 
    		$("#tblListaModelo").empty();
    		return;
    	}
    	var txtHtml="";
    	$("#tblListaModelo").empty();
    	txtHtml="<thead>"
			+"<th class='header'>NUM</th>"
			+"<th class='header'>NOMBRE MODELO</th>"
			+"<th class='header'>ESTADO</th>"
			+"<th class='header'>EDITAR</th>"
			+"<th class='header'>ELIMINAR</th>"
			+"</thead>"
			+"<tfoot>"
			+"<th>NUM</th>"
			+"<th>NOMBRE MODELO</th>"
			+"<th>ESTADO</th>"
			+"<th>EDITAR</th>"
			+"<th>ELIMINAR</th>"
			+"</tfoot>"
			+"<tbody></tbody>";
			$("#tblListaModelo").append(txtHtml);
    	for(var x=0;x<data.length;x++){
    		txtHtml="<tr>"+
			"<td>"+(x+1)+"</td>"+
			"<td>"+data[x].modnombre_V +"</td>"+
			"<td>"+data[x].modestado_C +"</td>"+
			"<td><img alt='Modificar' class='btnModificarModelo' id='mod"+data[x].modcodigo_D +"' src='images/edit.png'></td>"+
			"<td><img alt='Eliminar' class='btnEliminarModelo' id='del"+data[x].modcodigo_D +"' src='images/delete.png'></td>"+
    		"</tr>";
    		$("#tblListaModelo tbody").append(txtHtml);
    	}

		$(".btnModificarModelo").click(modificar);
    	$(".btnEliminarModelo").click(eliminar);
    	paginacion();
    }


    function paginacion(){
		$("#tblListaModelo") 
        .tablesorter({widthFixed: true, widgets: ['zebra']}) 
        .tablesorterPager({container: $("#pager9")}); 	
	}
    
    
    $("#divNuevaModelo").hide();
	
	   $("#btnNuevaModelo").click(function(){
	    	llenarFormulario("");
	    });
	    
	        
		function llenarFormulario(data)
		{
		    	if(data!=""){	
		    		$("#txtCodigoModelo").val(data.modcodigo_D);
		    		$("#txtnombreModelo").val(data.modnombre_V);
		    		$("#sltEstadoModelo").val(data.modestado_C);
		       	}else{
		    		$("#txtCodigoModelo").val(0);
		    		$("#txtnombreModelo").val("");
		       	}   
		    	
		    	$("#divNuevaModelo").show();
		    	$("#divNuevaModelo").dialog({
		    		title:"Modelo",
		    		width:500,
		    		height: 240,
		    		modal: true	
		    	});
		    	
		 }
	
		 $("#btnGuardarModelo").click(function(){
		    	$.ajax({ 
					data:{
						'modcodigo_D':$("#txtCodigoModelo").val(),
						'modnombre_V':$("#txtnombreModelo").val(),
						'modestado_C':$("#sltEstadoModelo").val(),
					},
				    datatype:'json',
			        type: "POST", 
			        url: "Modelo/Procesar.htm", 
			        success: function(data){
			        	txtHtml="<p>Operación realizada correctamente</p>";
			        	mensaje(txtHtml);
			        },error: function(jqXHR, textStatus, errorThrown){
			        	mensajeError();
			        }
				});	
		    	$("#divNuevaModelo").dialog("close");
		    	buscar("MODESTADO_V", "A");
			});
		 
		 function modificar(){
			   	$.ajax({ 
					data:{
						codigo:$(this).attr("id").replace("mod","")
					},
			        datatype:'json',
			        type: "GET", 
			        url: "Modelo/Obtener.htm", 
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
		                           url: "Modelo/Eliminar.htm",
		                           success: function(data){
		    							mensaje(data);
		                           },error: function(jqXHR, textStatus, errorThrown){
		                           	mensajeError();
		                           }
		    		        	});
		    		        	$(this).dialog('close');
		    		        	buscar("MODESTADO_V", "A");
		    				},
		    				'Cancelar': function() {
		    					$(this).dialog('close');
		    				}
		    			}
		    		});
		       }
});