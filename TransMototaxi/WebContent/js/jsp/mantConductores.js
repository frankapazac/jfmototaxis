$(document).ready(function(){
	$("#divMensaje").hide();
	$("#divFormulario").hide();
	$("#divFormularioCese").hide();

	
	var codigoConductor=0;
	var conductores=[];//Todo los conductores
	var codigoUndConductor=0;
	var UndConductor=[];
	
	buscar("ECO.EMPCODIGO_D", "");
	

	$("#btnBuscar").click(function(){
    	buscar($("#sltCriterio").val(),$("#txtTexto").val());
    });
	
    $("#btnBuscarAsignado").click(function(){
    	if(codigoConductor==0){
    		alert("Seleccione un conductor");
    		return;
    	}
    	buscarMototaxis($("#sltCriterio2").val(),$("#txtTextoAsignado").val(),codigoConductor);
    });
	
    $("#btnNuevo").click(function(){
    	llenarFormulario("");
    });
    
    $("#tblLista tr").click(function(){
    	alert("KEYUP");
    });
    
    $("#btnAsignarMoto").click(function(){
    	if(codigoConductor==0){
    		alert("Seleccione conductor");
    		return;
    	}
    	
    	var miConductor=[];//JSON que conductor
		for(var x=0;x<conductores.length;x++){
			if(conductores[x].conductor.concodigoD==codigoConductor){
				miConductor=conductores[x].conductor;
				$("#txtNombreConductor").text(miConductor.persona.pernombresV+' '+
						miConductor.persona.perpaternoV+' '+
						miConductor.persona.permaternoV);
				buscarMototaxis("EPD.EMPCODIGO_D", "", miConductor.concodigoD);
			}
		}
		$("#divFormulario").dialog({
    		title:"Conductores Mototaxis",
    		width:1100,
    		height: 400,
    		modal: true
		});
    });
    
    $("#btnCese").click(function(){
    
    	var miUnidConductor=[];
		for(var x=0;x<UndConductor.length;x++){
			if(UndConductor[x].ucocodigoD==codigoUndConductor){
				miUnidConductor=UndConductor[x];
				//alert(JSON.stringify(miUnidConductor));
				$("#txtNombreConductorCese").text(miUnidConductor.conductor.persona.pernombresV+' '+
						miUnidConductor.conductor.persona.perpaternoV+' '+
						miUnidConductor.conductor.persona.permaternoV);
				$("#txtOcultoCodigoUndConductor").val(miUnidConductor.ucocodigoD);
				//alert(miUnidConductor.ucocodigoD);
				//buscarMototaxis("EPD.EMPCODIGO_D", "", miConductor.concodigoD);
			}
		}
    	$("#divFormularioCese").dialog({
    		title:"Cese Conductor",
    		width:700,
    		height: 350,
    		modal: true
    	});
    });
    
           
    function paginacion(){
		$("#tblLista")
        .tablesorter({widthFixed: true, widgets: ['zebra']}) 
        .tablesorterPager({container: $("#pager")}); 	
	}
    
        
	function buscar(criterio,texto){
		$.ajax({ 
    		data:{
    			criterio:criterio,
    			texto:texto
    		},
            datatype:'json',
            type: "POST", 
            url: "Conductores/Listar.htm", 
            success: function(data){
            	conductores=data;
            	llenarTabla(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	mensajeError();
            }
    	});
	}
	
	function llenarTabla(data){
    	if(data==""){ 
    		$("#tblLista").empty();
    		return;
    	}
    	var txtHtml="";
    	$("#tblLista").empty();
    	txtHtml="<thead>"
    		+"<th class='header'>N°</th>"
			+"<th class='header'>Nombres</th>"
			+"<th class='header'>DNI</th>"
			+"<th class='header'>N° Motos Asignadas</th>"
			+"<th class='header'>Fec. Inicio</th>"
			+"<th class='header'>Fec. Cese</th>"
			+"</thead>"
			+"<tfoot>"
    		+"<th>N°</th>"
			+"<th>Nombres</th>"
			+"<th>DNI</th>"
			+"<th>N° Motos Asignadas</th>"
			+"<th>Fec. Inicio</th>"
			+"<th>Fec. Cese</th>"
			+"</tfoot>"
			+"<tbody></tbody>";
			$("#tblLista").append(txtHtml);
    	for(var x=0;x<data.length;x++){
    		txtHtml="<tr id='cond_"+data[x].conductor.concodigoD+"' class='trConductor'>"+
			"<td>"+(x+1)+"</td>"+
			"<td>"+data[x].conductor.persona.pernombresV+" "+
			data[x].conductor.persona.perpaternoV+" "+
			data[x].conductor.persona.permaternoV+" "+
			"<td>"+data[x].conductor.persona.perdniV+"</td>"+
			"<td>"+data[x].motosasignadasI+"</td>"+
			"<td>"+data[x].ecofechainicioF+"</td>"+
			"<td>"+data[x].ecofechaceseF+"</td>"+
			"</tr>";
    		$("#tblLista tbody").append(txtHtml);
    	}
    	$(".trConductor").click(obtenerCodigoConductor);
    	paginacion();
    }
	
	function obtenerCodigoConductor(){
		codigoConductor=$(this).attr("id").replace("cond_","");
	}
	
    function buscarMototaxis(criterio,texto,codCondu){
    	$.ajax({ 
    		data:{
    			criterio:criterio,
    			texto:texto,
    			codCondu:codCondu
    		},
            datatype:'json',
            type: "POST", 
            url: "Conductores/ListarMotosAsignadas.htm", 
            success: function(data){
            	//alert(JSON.stringify(data));
            	UndConductor=data;
            	llenarTablaMotodAsignadas(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	mensajeError();
            }
    	});
    }
	
	function llenarTablaMotodAsignadas(data){
		//alert(JSON.stringify(data));
    	if(data==""){ 
    		$("#tblListaMotosAsignadas").empty();
    		return;
    	}
    	var txtHtml="";
    	$("#tblListaMotosAsignadas").empty();
    	txtHtml="<thead>"
    		+"<th class='header'>N°</th>"
			+"<th class='header'>Propietario</th>"
			+"<th class='header'>DNI</th>"
			+"<th class='header'>Placa</th>"
			+"<th class='header'>Marca</th>"
			+"<th class='header'>Modelo</th>"
			+"<th class='header'>Año</th>"
			+"<th class='header'>Color</th>"
			+"<th class='header'>Fec. Inicio</th>"
			+"<th class='header'>Fec. Cese</th>"
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
			+"<th>Fec. Inicio</th>"
			+"<th>Fec. Cese</th>"
			+"</tfoot>"
			+"<tbody></tbody>";
			$("#tblListaMotosAsignadas").append(txtHtml);
    	for(var x=0;x<data.length;x++){
    		// txtHtml="<tr id='cond_"+data[x].conductor.concodigoD+"' class='trConductor'>"+
    		txtHtml="<tr id='condUndConductor_"+data[x].ucocodigoD+"' class='trcodUndConductor'>"+
			"<td>"+(x+1)+"</td>"+
			"<td>"+data[x].conductor.persona.pernombresV+" "+data[x].conductor.persona.perpaternoV+" "+data[x].conductor.persona.permaternoV+"</td>"+
			"<td>"+data[x].conductor.persona.perdniV+"</td>"+
			"<td>"+data[x].propietariomoto.mototaxi.uneplacanro_V+"</td>"+
			"<td>"+data[x].propietariomoto.mototaxi.unemarca_V+"</td>"+
			"<td>"+data[x].propietariomoto.mototaxi.modelo.modnombre_V+"</td>"+
			"<td>"+data[x].propietariomoto.mototaxi.uneanno_C+"</td>"+
			"<td>"+data[x].propietariomoto.mototaxi.unecolor_V+"</td>"+
			"<td>"+data[x].ucofechainicioF+"</td>"+
			"<td>"+data[x].ucofechacese+"</td>"+
			"</tr>";
    		$("#tblListaMotosAsignadas tbody").append(txtHtml);
    	}
    	$(".trcodUndConductor").click(obtenerCodigoUnidadConductor);//nombre función
    	//paginacion();
    	
    	/*$("#txttal").click(function(){
    		alert(123);
    	});
    	
    	$("txttal").click(talclick);
    	
    	function talclick(){
    		alert(123);
    	}*/
    }
	
	function obtenerCodigoUnidadConductor(){
		//codigoConductor=$(this).attr("id").replace("cond_","");
		codigoUndConductor=$(this).attr("id").replace("condUndConductor_","");
		//alert(codigoUndConductor);
	}
	
	
    $("#btnGuardarCese").click(function(){
    	alert($("#txtOcultoCodigoUndConductor").val()+" ; "+$("#txtFechaCese").val()+" ; "+
    			$("#txtObservacion").val());
    	$.ajax({
    		data:{
    			ucocodigoD:$("#txtOcultoCodigoUndConductor").val(),
    			ucofechacese:$("#txtFechaCese").val(),
    			ucoobservacionesV:$("#txtObservacion").val()
    		},
    		datatype:'html',
    		type:"POST",
    		url: "Conductores/CesarConductor.htm",
    		success: function(data){    			
    		},error: function(jqXHR, textStatus, errorThrown){
    			mensajeError();
    		}
    	});
    	//buscar("PAR.PARCODIGO_I",$("#txtCodigo").val());
    	//$("#divFormulario").dialog("close");
    }); 
});