$(document).ready(function(){
	
	buscar("INF.INFINFRACCION_V", "");
	
	$("#btnBuscar").click(function(){
    	buscar($("#sltCriterio").val(),$("#txtTexto").val());
    });
	
	$("#btnCancelar").click(function(){
    	$("#divNuevoInfraccion").dialog("close");
    });
	
	function buscar(criterio,texto){
		$.ajax({ 
    		data:{
    			criterio:criterio,
    			texto:texto	
    		},
            datatype:'json',
            type: "POST", 
            url: "Infracciones/Listar.htm", 
            success: function(data){
            	llenarTabla(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	mensajeError();
            }
    	});
	}
	
	function llenarTabla(data){
		//alert(JSON.stringify(data));
    	if(data==""){ 
    		$("#tblLista").empty();
    		return;
    	}
    	var txtHtml="";
    	$("#tblLista").empty();
    	txtHtml="<thead>"
			+"<th class='header'>NUM</th>"
			+"<th class='header'>CÓDIGO</th>"
			+"<th class='header'>DESCRIPCIÓN</th>"
			+"<th class='header'>TIPO INFRACCION</th>"
			+"<th class='header'>TIPO PERSONA</th>"
			+"<th class='header'>N° UIT</th>"
			+"<th class='header'>ESTADO</th>"
			+"<th class='header'>EDITAR</th>"
			+"<th class='header'>ELIMINAR</th>"
			+"</thead>"
			+"<tfoot>"
			+"<th>NUM</th>"
			+"<th>CÓDIGO</th>"
			+"<th>DESCRIPCION</th>"
			+"<th>TIPO INFRACCION</th>"
			+"<th>TIPO PERSONA</th>"
			+"<th>N° UIT</th>"
			+"<th>ESTADO</th>"
			+"<th>EDITAR</th>"
			+"<th>ELIMINAR</th>"
			+"</tfoot>"
			+"<tbody></tbody>";
			$("#tblLista").append(txtHtml);
    	for(var x=0;x<data.length;x++){
    		txtHtml="<tr>"+
			"<td>"+(x+1)+"</td>"+
			"<td>"+data[x].infcodigoV+"</td>"+
			"<td>"+data[x].infinfraccionV+"</td>"+
			"<td>"+data[x].inftipoC+"</td>"+
			"<td>"+data[x].inftipopersonaC+"</td>"+
			"<td>"+data[x].infnrouitI+"</td>"+
			"<td>"+data[x].infestadoC+"</td>"+
			"<td><img alt='Modificar' class='btnModificar' id='mod"+data[x].infcodigoD+"' src='images/edit.png'></td>"+
			"<td><img alt='Eliminar' class='btnEliminar' id='del"+data[x].infcodigoD+"' src='images/delete.png'></td>"+
    		"</tr>";
    		$("#tblLista tbody").append(txtHtml);
    	}

		$(".btnModificar").click(modificar);
    	$(".btnEliminar").click(eliminar);
    	$("#tblLista").paginacion();
    }
    
    $("#divNuevoInfraccion").hide();
    
    $("#btnNuevaInfraccion").click(function(){
    	llenarFormulario("");
    });
    
    
    $("#btnGuardar").click(function(){
    	
		$("#sltAgregaMedidas option").prop('selected', true);
    	var tipoMedidasList = new Object();
    	tipoMedidasList.infraccion = new Object();
    	tipoMedidasList.tipoMedidas = new Array();
    	
    	var tipoMedida= null; var cont=0;
    	tipoMedidasList.infraccion.infcodigoD=$("#txtCodigoInfraccion").val();
    	tipoMedidasList.infraccion.infcodigoV=$("#txtCodInfraccionV").val();
    	tipoMedidasList.infraccion.infinfraccionV=$("#txtDescripcion").val();
    	tipoMedidasList.infraccion.infmedidasAccV=$("#txtMedidas").val();
    	tipoMedidasList.infraccion.inftipoC=$("#sltGravedad").val();
    	tipoMedidasList.infraccion.inftipopersonaC=$("#sltPara").val();
    	tipoMedidasList.infraccion.infnrouitI=$("#txtNroUIT").val();
		//alert(tipoMedidasList.infraccion.infcodigoD);
    	
    	var sel=$("#sltAgregaMedidas").val();
		$.each(sel, function(key,value){	
			tipoMedida=new Object();
			tipoMedida.tmecodigoI=value;
			tipoMedidasList.tipoMedidas[cont++]=tipoMedida;
			//alert(tipoMedida);
		});
		
    	$.ajax({ 
			data: JSON.stringify(tipoMedidasList),
		    datatype:'json',
	        type: "POST", 
	        contentType : "application/json",
	        url: "Infracciones/Procesar.htm", 
	        success: function(data){
	        	txtHtml="<p>Operación realizada correctamente</p>";
	        	mensaje(txtHtml);
	        },error: function(jqXHR, textStatus, errorThrown){
	        	mensajeError();
	        }
		});
    	$("#sltAgregaMedidas option").prop('selected', false);
    	$("#divNuevoInfraccion").dialog("close");
    	buscar($("#sltCriterio").val(),$("#txtTexto").val());    
	});
    /*
    $("#btnGuardar").click(function(){
    	$.ajax({ 
			data:{
				'infcodigoD':$("#txtCodigoInfraccion").val(),
				'infcodigoV':$("#txtCodInfraccionV").val(),
				'infinfraccionV':$("#txtDescripcion").val(),
				'infmedidasAccV':($("#txtMedidas").val()),
				'inftipoC':$("#sltGravedad").val(),
				'inftipoPersonaC':$("#sltPara").val(),
				'infnrouitI':$("#txtNroUIT").val()
			},
		    datatype:'json',
	        type: "POST", 
	        url: "Infracciones/Procesar.htm", 
	        success: function(data){
	        	//alert(JSON.stringify(data));
	        	//$("#txtCodigoInfraccion").val(data.infcodigoD);
	        	txtHtml="<p>Operación realizada correctamente</p>";
	        	mensaje(txtHtml);
	        },error: function(jqXHR, textStatus, errorThrown){
	        	mensajeError();
	        }
		});
    	$("#divNuevoInfraccion").dialog("close");
    	buscar($("#sltCriterio").val(),$("#txtTexto").val());    
	});
	*/

	
    function llenarFormulario(data)
    {
    	if(data!=""){	
    		$("#txtCodigoInfraccion").val(data.infcodigoD);
    		$("#txtCodInfraccionV").val(data.infcodigoV);
    		$("#txtDescripcion").val(data.infinfraccionV);
    		$("#txtMedidas").val(data.infmedidasAccV);
    		$("#sltPara").val(data.inftipopersonaC);
    		$("#sltGravedad").val(data.inftipoC);
    		$("#txtNroUIT").val(data.infnrouitI);
    		listarMedidaxInfraccion(data.infcodigoD);
    		//alert($("#txtCodigoInfraccion").val());
       	}else{
       		$("#txtCodigoInfraccion").val(0);	
       		$("#txtCodInfraccionV").val("");
    		$("#txtDescripcion").val("");
    		$("#txtMedidas").val("");
    		$("#sltGravedad").val("");
    		$("#sltPara").val("");
    		$("#txtNroUIT").val(0);
       	}   
    	
    	$("#divNuevoInfraccion").show();
    	$("#divNuevoInfraccion").dialog({
    		title:"Infracción",
    		width:800,
    		height: 400,
    		modal: true	
    	});
    	
    }
    
    function modificar(){ //para que funciones agregamos dentro de  llenarTabla(data) esto $(".btnModificar").click(modificar);
	   	$.ajax({ 
			data:{
				codigo:$(this).attr("id").replace("mod","")
			},
	        datatype:'json',
	        type: "GET", 
	        url: "Infracciones/Obtener.htm", 
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
                           url: "Infracciones/Eliminar.htm",
                           success: function(data){
    							mensaje(data);
                           },error: function(jqXHR, textStatus, errorThrown){
                           	mensajeError();
                           }
    		        	});
    					buscar($("#sltCriterio").val(),$("#txtTexto").val());
    		        	$(this).dialog('close');
    				},
    				'Cancelar': function() {
    					$(this).dialog('close');
    				}
    			}
    		});
       }
    
    
    $('#btnRight').click(function(e){
    	//alert($("#txtCodigoInfraccion").val());
    	var selectedOpts = $('#sltMedidas option:selected');
    	if(selectedOpts.length == 0){
    		alert("Elija un item correcto.");
    		e.preventDefault();
    	}
    	
    	$.ajax({ 
    		data:{
    			codMedida:$("#txtCodigoInfraccion").val(),
    			codInfraccion:parseFloat($("#sltMedidas").val()),
    			lado:">"
    		},
           datatype:'json',
           type: "POST", 
           url: "Infracciones/ActualizarEstadoInFMedida.htm",
           success: function(data){
				mensaje(data);
           },error: function(jqXHR, textStatus, errorThrown){
           	mensajeError();
           }
    	});
    	
    	$('#sltAgregaMedidas').append($(selectedOpts).clone());
    	$(selectedOpts).remove();
    	e.preventDefault();
    	   	
    });
    
    $('#btnLeft').click(function(e){
    	//alert($("#txtCodigoInfraccion").val());
    	
    	var selectedOpts = $('#sltAgregaMedidas option:selected');
    	if(selectedOpts.length == 0){
    		alert("Elija un item correcto.");
    		e.preventDefault();
    	}
    	
    	$.ajax({ 
    		data:{
    			codMedida:$("#txtCodigoInfraccion").val(),
    			codInfraccion:parseFloat($("#sltAgregaMedidas").val()),
    			lado:"<"
    		},
           datatype:'json',
           type: "POST", 
           url: "Infracciones/ActualizarEstadoInFMedida.htm",
           success: function(data){
				mensaje(data);
           },error: function(jqXHR, textStatus, errorThrown){
           	mensajeError();
           }
    	});
    	
    	$('#sltMedidas').append($(selectedOpts).clone());
    	$(selectedOpts).remove();
    	e.preventDefault();
    });
    
    function listarMedidaxInfraccion(codigo){
    	//alert(codigo);
    	$.ajax({ 
    		data:{
    			codigo:codigo
    		},
            datatype:'json',
            type: "GET", 
            url: "Infracciones/ObtenerMedidasxInfraccion.htm",
            success: function(data){
            	llenarTipoMedida(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	mensajeError();
            }
    	});
    }
    
    function llenarTipoMedida(data){	
    	//LimpiarSelect('sltAgregaInspector', 'AgregaInspector');
    	$("#sltAgregaMedidas").empty();
    	txtHtml="";
    	for(var x=0;x<data.length;x++){
    		txtHtml+="<option value='"+data[x].tipoMedida.tmecodigoI+"'>"
    				   +data[x].tipoMedida.tmedescripcionV+"</option>";
    	}
    	
    	$("#sltAgregaMedidas").append(txtHtml);
    	
    	$("#sltMedidas option").prop('selected', true);
		var sel=$("#sltMedidas").val();
		for(var x=0;x<data.length;x++){
			$.each(sel, function(key,value){
				if(data[x].tipoMedida.tmecodigoI==value){
					$("#sltMedidas").find("option[value='"+data[x].tipoMedida.tmecodigoI+"']").remove();
					//alert("option[value='"+data[x].tipoMedida.tmecodigoI+"']");FRANK
				}
	        });
		}
    }
});