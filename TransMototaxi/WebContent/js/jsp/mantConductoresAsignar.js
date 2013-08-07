$(document).ready(function(){
	var asocodigo="";
	$("#btnAsignar").click(function(){fncSelectPropietarios('PER.PERDNI_V', '');});
	$("#btnBuscarPropietarios").click(function(){fncSelectPropietarios($("#sltCriterioPropietario").val(), 
			$("#txtTextoPropietario").val());});
	$("#btnBuscarMototaxi").click(function(){fncPropietarioCambio($("#sltCriterioMototaxi").val(), 
			$("#txtTextoMototaxi").val(),asocodigo);});
	$("#btnMotosAsignar").click(fncAsignarMotos);
	$("#btnMotosAsignarCancelar").click(fncAsignarMotosCancelar);
	
	function fncAsignarMotos(){
		$.ajax({ 
            datatype:'html',
            type: "POST", 
            url: "PropUnidadEmpresa/Asignar.htm", 
            success: function(data){
            	$.message.Success();
            	$("#divAsignarMototaxi").dialog('close');
            },error: function(jqXHR, textStatus, errorThrown){
            	mensajeError();
            }
    	});
    	//$("#divFormularioCese").dialog("close");
	}
	
	function fncAsignarMotosCancelar(){
		$("#divAsignarMototaxi").dialog("close");
	}
	
	function fncSelectPropietarios(criterio, texto){
		$.ajax({ 
    		data:{
    			criterio:criterio,
    			texto:texto
    		},
            datatype:'json',
            type: "POST", 
            url: "Asociados/Listar.htm", 
            success: function(data){
            	//alert(JSON.stringify(data));
            	llenartablaasociados(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	mensajeError();
            }
    	});
		//tblListaAsociados
	}
	
	function llenartablaasociados(data){
		if(data==""){ 
    		$("#tblListaAsociados").empty();
    		return;
    	}
    	var txtHtml="";
    	$("#tblListaAsociados").empty();
    	txtHtml="<thead>"
    		+"<th class='header'>N°</th>"
			+"<th class='header'>Propietario</th>"
			+"<th class='header'>DNI</th>"
			+"</thead>"
			+"<tfoot>"
			+"<th>N°</th>"
			+"<th>Propietario</th>"
			+"<th>DNI</th>"
			+"</tfoot>"
			+"<tbody></tbody>";
			$("#tblListaAsociados").append(txtHtml);
    	for(var x=0;x<data.length;x++){
    		var classe="even";
    		if(x%2==0) classe="odd";
    		txtHtml="<tr id='prop_"+data[x].asocodigoD+"' class='trcodPropietario "+classe+"'>"+
			"<td>"+(x+1)+"</td>"+
			"<td>"+data[x].persona.pernombresV+" "+data[x].persona.perpaternoV+" "+data[x].persona.permaternoV+"</td>"+
			"<td>"+data[x].persona.perdniV+"</td>"+
			"</tr>";
    		$("#tblListaAsociados tbody").append(txtHtml);
    	}
    	$(".trcodPropietario").click(function(){
    		asocodigo=$(this).attr("id").replace("prop_","");
    		fncPropietarioCambio('UNE.UNEPLACANRO_V', '', asocodigo);
    	});
    	$("#tblListaAsociados").paginacion();
	}
	
	function fncPropietarioCambio(criterio,texto,asocodigoD){
		//alert(asocodigoD);
		$.ajax({ 
    		data:{
    			criterio:criterio,
    			texto:texto,
    			codigo:asocodigoD
    		},
            datatype:'json',
            type: "GET", 
            url: "PropUnidadEmpresa/BuscarPorAsociado.htm", 
            success: function(data){
            	//alert(JSON.stringify(data));
            	llenartablamototaxis(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	mensajeError();
            }
    	});
	}
	
	function llenartablamototaxis(data){
		if(data==""){ 
    		$("#tblListaMototaxis").empty();
    		return;
    	}
    	var txtHtml="";
    	$("#tblListaMototaxis").empty();
    	txtHtml="<thead>"
    		+"<th class='header'>N°</th>"
			+"<th class='header'>Placa</th>"
			+"<th class='header'>Marca</th>"
			+"<th class='header'>Modelo</th>"
			+"<th class='header'>Año</th>"
			+"<th class='header'>Color</th>"
			+"<th class='header'>Selec</th>"
			+"</thead>"
			+"<tfoot>"
			+"<th>N°</th>"
			+"<th>Placa</th>"
			+"<th>Marca</th>"
			+"<th>Modelo</th>"
			+"<th>Año</th>"
			+"<th>Color</th>"
			+"<th>Selec</th>"
			+"</tfoot>"
			+"<tbody></tbody>";
			$("#tblListaMototaxis").append(txtHtml);
    	for(var x=0;x<data.length;x++){
    		var classe="even";
    		if(x%2==0) classe="odd";
    		txtHtml="<tr class='"+classe+"'>"+
			"<td>"+(x+1)+"</td>"+
			"<td>"+data[x].unidadempresa.uneplacanroV+"</td>"+
			"<td>"+data[x].unidadempresa.marca.marnombreV+"</td>"+
			"<td>"+data[x].unidadempresa.modelo.modnombre_V+"</td>"+
			"<td>"+data[x].unidadempresa.uneannoC+"</td>"+
			"<td>"+data[x].unidadempresa.unecolorV+"</td>"+
			"<td><input type='checkbox' id='selec_"+data[x].pmocodigoD+"' class='cbMoto'/></td>"+
			"</tr>";
    		$("#tblListaMototaxis tbody").append(txtHtml);
    	}
    	$(".cbMoto").change(fncMotoSeleccionada);
    	//$(".trcodPropietario").click(function(){fncPropietarioCambio('UNE.UNEPLACANRO_V', '', $(this).attr("id").replace("prop_",""));});
	}
	
	function fncMotoSeleccionada(){
		if($(this).is(':checked')){
			$.ajax({ 
	    		data:{
	    			codigo:$(this).attr("id").replace("selec_","")
	    		},
	            datatype:'json',
	            type: "POST", 
	            url: "PropUnidadEmpresa/Agregar.htm", 
	            success: function(data){
	            	//alert(JSON.stringify(data));
	            	llenartablaparaasignar(data);
	            },error: function(jqXHR, textStatus, errorThrown){
	            	mensajeError();
	            }
	    	});
		}else{
			$.ajax({ 
	    		data:{
	    			codigo:$(this).attr("id").replace("selec_","")
	    		},
	            datatype:'json',
	            type: "POST", 
	            url: "PropUnidadEmpresa/Eliminar.htm", 
	            success: function(data){
	            	//alert(JSON.stringify(data));
	            	llenartablaparaasignar(data);
	            },error: function(jqXHR, textStatus, errorThrown){
	            	mensajeError();
	            }
	    	});
			//alert('NO CHEKEADO: '+$(this).attr("id"));
		}
	}
	
	function llenartablaparaasignar(data){
		//tblListaMotosParaAsignar
		if(data==""){ 
			$("#tblListaMotosParaAsignar").empty();
    		return;
    	}
    	var txtHtml="";
    	$("#tblListaMotosParaAsignar").empty();
    	txtHtml="<thead>"
    		+"<th class='header'>N°</th>"
			+"<th class='header'>Propietario</th>"
			+"<th class='header'>DNI</th>"
			+"<th class='header'>Placa</th>"
			+"<th class='header'>Marca</th>"
			+"<th class='header'>Modelo</th>"
			+"<th class='header'>Año</th>"
			+"<th class='header'>Color</th>"
			+"<th class='header'>Eliminar</th>"
			+"</thead>"
			+"<tfoot>"
			+"<th>N°</th>"
			+"<th>Propietario</th>"
			+"<th>DNI</th>"
			+"<th>Placa</th>"
			+"<th>Marca</th>"
			+"<th>Modelo</th>"
			+"<th>Año</th>"
			+"<th>Color</th>"
			+"<th>Eliminar</th>"
			+"</tfoot>"
			+"<tbody></tbody>";
			$("#tblListaMotosParaAsignar").append(txtHtml);
    	for(var x=0;x<data.length;x++){
    		var classe="even";
    		if(x%2==0) classe="odd";
    		txtHtml="<tr class='"+classe+"'>"+
			"<td>"+(x+1)+"</td>"+
			"<td>"+data[x].asociado.persona.pernombresV+" "+data[x].asociado.persona.perpaternoV+" "+data[x].asociado.persona.permaternoV+"</td>"+
			"<td>"+data[x].asociado.persona.perdniV+"</td>"+
			"<td>"+data[x].unidadempresa.uneplacanroV+"</td>"+
			"<td>"+data[x].unidadempresa.marca.marnombreV+"</td>"+
			"<td>"+data[x].unidadempresa.modelo.modnombre_V+"</td>"+
			"<td>"+data[x].unidadempresa.uneannoC+"</td>"+
			"<td>"+data[x].unidadempresa.unecolorV+"</td>"+
			"<td><input type='button' class='btnEliminarMotoAsignar' id='eliMotAsig_"+data[x].pmocodigoD+"' value='Eliminar'/></td>"+
			"</tr>";
    		$("#tblListaMotosParaAsignar tbody").append(txtHtml);
    	}
    	$(".dtFecha").datepicker({dateFormat:"dd/mm/yy"});
    	$(".btnEliminarMotoAsignar").click(eliminarMotoAsignada);
	}
	
	function eliminarMotoAsignada(){
		$.ajax({ 
    		data:{
    			codigo:$(this).attr("id").replace("eliMotAsig_","")
    		},
            datatype:'json',
            type: "POST", 
            url: "PropUnidadEmpresa/Eliminar.htm", 
            success: function(data){
            	//alert(JSON.stringify(data));
            	llenartablaparaasignar(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	mensajeError();
            }
    	});
	}
});