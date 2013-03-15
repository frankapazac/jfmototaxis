$(document).ready(function(){
	
	buscar("BC","BITESTADO_C", "A",0);
	
	$("#btnBuscarInventario").click(function(){
    	buscar("BC",$("#sltCriterioInventario").val(),$("#txtTextoInventario").val(),0);
    });
	
	$("#btnCancelarInventario").click(function(){
    	$("#divNuevoInventario").dialog("close");
    });
	
	function buscar(tipo,criterio,texto,codigo){		
		$.ajax({ 
    		data:{
    			tipo:tipo,
    			criterio:criterio,
    			texto:texto,
    			codigo:codigo
    		},
            datatype:'json',
            type: "POST", 
            url: "Inventario2/Listar.htm", 
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
    		$("#tblListaInventario").empty();
    		return;
    	}
    	var txtHtml="";
    	$("#tblListaInventario").empty();
    	txtHtml="<thead>"
			+"<th class='header'>NUM</th>"
			+"<th class='header'>NOMBRE</th>"
			+"<th class='header'>TIPO</th>"
			+"<th class='header'>ESTADO</th>"
			+"<th class='header'>EDITAR</th>"
			+"<th class='header'>ELIMINAR</th>"
			+"</thead>"
			+"<tfoot>"
			+"<th>NUM</th>"
			+"<th>NOMBRE</th>"
			+"<th>TIPO</th>"
			+"<th>ESTADO</th>"
			+"<th>EDITAR</th>"
			+"<th>ELIMINAR</th>"
			+"</tfoot>"
			+"<tbody></tbody>";
			$("#tblListaInventario").append(txtHtml);
    	for(var x=0;x<data.length;x++){
    		txtHtml="<tr>"+
			"<td>"+(x+1)+"</td>"+
			"<td>"+data[x].bitnombreV +"</td>"+
			"<td>"+data[x].bittipoC +"</td>"+
			"<td>"+data[x].bitestadoC +"</td>"+
			"<td><img alt='Modificar' class='btnModificarInv' id='mod"+data[x].bitcodigoI +"' src='images/edit.png'></td>"+
			"<td><img alt='Eliminar' class='btnEliminarInv' id='del"+data[x].bitcodigoI +"' src='images/delete.png'></td>"+
    		"</tr>";
    		$("#tblListaInventario tbody").append(txtHtml);
    	}

		$(".btnModificarInv").click(modificar);
    	$(".btnEliminarInv").click(eliminar);
    	paginacion();
    }

	 function paginacion(){
			$("#tblListaInventario") 
	        .tablesorter({widthFixed: true, widgets: ['zebra']}) 
	        .tablesorterPager({container: $("#pager4")}); 	
	 }
	 
	 $("#divNuevoInventario").hide();
	 
	 $("#btnNuevoInventario").click(function(){
	    	llenarFormulario("");
	 });
	
	 function llenarFormulario(data)
		{
		    	if(data!=""){	
		    		$("#txtCodigoInventario").val(data.bitcodigoI);
		    		$("#txtnombreInventario").val(data.bitnombreV);
		    		$("#sltTipoInventario").val(data.bittipoC);
		    		$("#sltEstadoInventario").val(data.bitestadoC);
		    	   	//alert("txtcodigoOperativo : " + $("#txtCodigoOperativo").val());
		       	}else{
		    		$("#txtCodigoInventario").val(0);
		    		$("#txtnombreInventario").val("");
		       	}   
		    	
		    	//$("#btnNuevoInventario").show();
		    	//$("#btnNuevoInventario").dialog({divNuevoInventario
		    	$("#divNuevoInventario").show();
		    	$("#divNuevoInventario").dialog({
		    		title:"Inventario",
		    		width:500,
		    		height: 240,
		    		modal: true	
		    	});
		    	
		 }
	 
	 $("#btnGuardarInventario").click(function(){
	    	$.ajax({ 
				data:{
					'bitcodigoI':$("#txtCodigoInventario").val(),
					'bitnombreV':$("#txtnombreInventario").val(),
					'bittipoC':$("#sltTipoInventario").val(),
					'bitestadoC':$("#sltEstadoInventario").val(),
				},
			    datatype:'json',
		        type: "POST", 
		        url: "Inventario2/Procesar.htm", 
		        success: function(data){
		        	txtHtml="<p>Operación realizada correctamente</p>";
		        	mensaje(txtHtml);
		        },error: function(jqXHR, textStatus, errorThrown){
		        	mensajeError();
		        }
			});
	    	$("#divNuevoInventario").dialog("close");
	    	buscar("BC","BITESTADO_C", "A",0);
		});
	 
	 function modificar(){
		   	$.ajax({ 
				data:{
					codigo:$(this).attr("id").replace("mod","")
				},
		        datatype:'json',
		        type: "POST", 
		        url: "Inventario2/Obtener.htm", 
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
	                           url: "Inventario2/Eliminar.htm",
	                           success: function(data){
	    							mensaje(data);
	                           },error: function(jqXHR, textStatus, errorThrown){
	                           	mensajeError();
	                           }
	    		        	});
	    		        	$(this).dialog('close');
	    		        	buscar("BC","BITESTADO_C", "A",0);
	    				},
	    				'Cancelar': function() {
	    					$(this).dialog('close');
	    				}
	    			}
	    		});
	       }
	
});
	
	