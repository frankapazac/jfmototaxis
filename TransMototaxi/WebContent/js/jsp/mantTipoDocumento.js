$(document).ready(function(){
	
	buscar("MTDESTADO_C", "A");
	
	$("#btnBuscarTipDoc").click(function(){
    	buscar($("#sltCriterioInventario").val(),$("#txtTextoTipDoc").val());
    });
	
	$("#btnCancelarTipDoc").click(function(){
    	$("#divNuevoTipDoc").dialog("close");
    });
	
	function buscar(criterio,texto){
		$.ajax({ 
    		data:{
    			criterio:criterio,
    			texto:texto
    		},
            datatype:'json',
            type: "POST", 
            url: "TipoDocumento/Listar.htm", 
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
    		$("#tblListaTipDoc").empty();
    		return;
    	}
    	var txtHtml="";
    	$("#tblListaTipDoc").empty();
    	txtHtml="<thead>"
			+"<th class='header'>NUM</th>"
			+"<th class='header'>NOMBRE DOCUMENTO</th>"
			+"<th class='header'>NOMBRE TABLA</th>"
			+"<th class='header'>ESTADO</th>"
			+"<th class='header'>EDITAR</th>"
			+"<th class='header'>ELIMINAR</th>"
			+"</thead>"
			+"<tfoot>"
			+"<th>NUM</th>"
			+"<th>NOMBRE DOCUMENTO</th>"
			+"<th>NOMBRE TABLA</th>"
			+"<th>ESTADO</th>"
			+"<th>EDITAR</th>"
			+"<th>ELIMINAR</th>"
			+"</tfoot>"
			+"<tbody></tbody>";
			$("#tblListaTipDoc").append(txtHtml);
    	for(var x=0;x<data.length;x++){
    		txtHtml="<tr>"+
			"<td>"+(x+1)+"</td>"+
			"<td>"+data[x].mtdnombreV +"</td>"+
			"<td>"+data[x].mtdtablaC +"</td>"+
			"<td>"+data[x].mtdEstadoC +"</td>"+
			"<td><img alt='Modificar' class='btnModificarTipDoc' id='mod"+data[x].mtdcodigoI +"' src='images/edit.png'></td>"+
			"<td><img alt='Eliminar' class='btnEliminarTipDoc' id='del"+data[x].mtdcodigoI +"' src='images/delete.png'></td>"+
    		"</tr>";
    		$("#tblListaTipDoc tbody").append(txtHtml);
    	}

		$(".btnModificarTipDoc").click(modificar);
    	$(".btnEliminarTipDoc").click(eliminar);
    	paginacion();
    }

    function paginacion(){
		$("#tblListaTipDoc") 
        .tablesorter({widthFixed: true, widgets: ['zebra']}) 
        .tablesorterPager({container: $("#pager2")}); 	
	}
    
    
	$("#divNuevoTipDoc").hide();
	
	
	
    $("#btnNuevoTipDoc").click(function(){
    	llenarFormulario("");
    });
    
        
	function llenarFormulario(data)
	{
	    	if(data!=""){	
	    		$("#txtcodigoTipDoc").val(data.mtdcodigoI);
	    		$("#txtNombreDocumento").val(data.mtdnombreV);
	    		$("#txttabla").val(data.mtdtablaC);
	    		$("#sltEstado").val(data.mtdEstadoC);
	    	   	//alert("txtcodigoOperativo : " + $("#txtCodigoOperativo").val());
	       	}else{
	    		$("#txtcodigoTipDoc").val(0);
	    		$("#txtNombreDocumento").val("");
	    		$("#txttabla").val("");
	       	}   
	    	
	    	$("#divNuevoTipDoc").show();
	    	$("#divNuevoTipDoc").dialog({
	    		title:"Tipo Documento",
	    		width:500,
	    		height: 240,
	    		modal: true	
	    	});
	    	
	 }
	
	
    $("#btnGuardarTipDoc").click(function(){
    	$.ajax({ 
			data:{
				'mtdcodigoI':$("#txtcodigoTipDoc").val(),
				'mtdnombreV':$("#txtNombreDocumento").val(),
				'mtdtablaC':$("#txttabla").val(),
				'mtdEstadoC':$("#sltEstado").val(),
			},
		    datatype:'json',
	        type: "POST", 
	        url: "TipoDocumento/Procesar.htm", 
	        success: function(data){
	        	txtHtml="<p>Operación realizada correctamente</p>";
	        	mensaje(txtHtml);
	        },error: function(jqXHR, textStatus, errorThrown){
	        	mensajeError();
	        }
		});
    	$("#divNuevoTipDoc").dialog("close");
    	//buscar($("#sltCriterioTipDoc").val(),$("#txtTextoTipDoc").val("A"));
    	buscar("MTDESTADO_C", "A");
	});
	
	
	
	 function modificar(){ //para que funciones agregamos dentro de  llenarTabla(data) esto $(".btnModificar").click(modificar);
		   	$.ajax({ 
				data:{
					codigo:$(this).attr("id").replace("mod","")
				},
		        datatype:'json',
		        type: "GET", 
		        url: "TipoDocumento/Obtener.htm", 
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
	                           url: "TipoDocumento/Eliminar.htm",
	                           success: function(data){
	    							mensaje(data);
	                           },error: function(jqXHR, textStatus, errorThrown){
	                           	mensajeError();
	                           }
	    		        	});
	    		        	$(this).dialog('close');
	    					buscar("MTDESTADO_C", "A");
	    				},
	    				'Cancelar': function() {
	    					$(this).dialog('close');
	    				}
	    			}
	    		});
	       }
	
	 
	 
	
});
