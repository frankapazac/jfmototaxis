$(document).ready(function(){
	$("#divMensaje").hide();
	$("#divFormulario").hide();
	$("#divFormularioCese").hide();
	$("#divAsignarMototaxi").hide();
	$("#txtFechaInicio").datepicker({dateFormat:"dd/mm/yy"});
	$("#dtNacimiento").datepicker({dateFormat:"dd/mm/yy"});
	
	var codigoConductor=0;
	var conductores=[];//Todo los conductores
	var codigoUndConductor=0;
	var UndConductor=[];
	
	$("input[type=text]").keyup(function(){
	  $(this).val( $(this).val().toUpperCase() );
	});
	
	$("#txtFechaCese").datepicker({dateFormat:"dd/mm/yy"});
	buscar("ECO.EMPCODIGO_D", "");

	$("#btnCancelarFormulario").click(function(){
		$("#divFormulario").dialog("close");
	});
	
	$("#btnCancelarCond").click(function(){
		$("#divNuevoCond").dialog("close");
	});
	
	$("#btnAsignar").click(function(){
		$("#divAsignarMototaxi").show();
		$("#divAsignarMototaxi").dialog({
    		title:"Asignar mototaxi",
    		width:1100,
    		height: 500,
    		modal: true
		});
	});

	$("#btnBuscar").click(function(){
    	buscar($("#sltCriterio").val(),$("#txtTexto").val());
    	$.message.Find();
    });
	
    $("#btnBuscarAsignado").click(function(){
    	if(codigoConductor==0){
    		alert("Seleccione un conductor");
    		return;
    	}
    	buscarMototaxis($("#sltCriterioAsignado").val(),$("#txtTextoAsignado").val(),codigoConductor);
    	$.message.Find();
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

    	$("#tbllistaMototaxis").empty();
    	$("#tblListaMotosParaAsignar").empty();
    	
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
    		modal: true
		});
    });
    
    $("#btnCese").click(function(){
    	if(codigoUndConductor<1){
    		alert("Seleccione un propietario para cesar");
    		return;
    	}
    	
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
    		//height: 350,
    		modal: true
    	});
    });
        
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
            	$.message.Error();
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
			+"<th class='header'></th>"
			+"<th class='header'></th>"
			+"</thead>"
			+"<tfoot>"
    		+"<th>N°</th>"
			+"<th>Nombres</th>"
			+"<th>DNI</th>"
			+"<th>N° Motos Asignadas</th>"
			+"<th>Fec. Inicio</th>"
			+"<th>Fec. Cese</th>"
			+"<th></th>"
			+"<th></th>"
			+"</tfoot>"
			+"<tbody></tbody>";
			$("#tblLista").append(txtHtml);
    	for(var x=0;x<data.length;x++){
    		txtHtml="<tr id='cond_"+data[x].conductor.concodigoD+"' class='trConductor'>"+
			"<td>"+(x+1)+"</td>"+
			"<td>"+data[x].conductor.persona.pernombresV+" "+
			data[x].conductor.persona.perpaternoV+" "+
			data[x].conductor.persona.permaternoV+"</td>"+
			"<td>"+data[x].conductor.persona.perdniV+"</td>"+
			"<td>"+data[x].motosasignadasI+"</td>"+
			"<td>"+data[x].ecofechainicioF+"</td>"+
			"<td>"+data[x].ecofechaceseF+"</td>"+
			"<td><img alt='Modificar' class='btnModificar' id='mod"+data[x].conductor.concodigoD+"' src='images/edit.png'></td>"+
			"<td><img alt='Eliminar' class='btnEliminar' id='del"+data[x].conductor.concodigoD+"' src='images/delete.png'></td>"+
			"</tr>";
    		$("#tblLista tbody").append(txtHtml);
    	}
    	$(".trConductor").click(obtenerCodigoConductor);
    	$(".btnModificar").click(modificar);
    	$(".btnEliminar").click(eliminar);
    	$("#tblLista").paginacion();
    }
	
    function modificar(){
    	$.ajax({ 
    		data:{
    			codigo:$(this).attr("id").replace("mod","")
    		},
            datatype:'json',
            type: "GET", 
            url: "Conductores/Obtener.htm", 
            success: function(data){
            	llenarFormulario(data);
            	$.message.Get();
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
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
            	UndConductor=data;
            	llenarTablaMotodAsignadas(data);
            	$.message.Get();
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
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
			+"<th class='header'>Inicio</th>"
			+"<th class='header'>Cese</th>"
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
			+"<th>Inicio</th>"
			+"<th>Cese</th>"
			+"</tfoot>"
			+"<tbody></tbody>";
			$("#tblListaMotosAsignadas").append(txtHtml);
    	for(var x=0;x<data.length;x++){
    		// txtHtml="<tr id='cond_"+data[x].conductor.concodigoD+"' class='trConductor'>"+
    		var classe="even";
    		if(x%2==0) classe="odd";
    		txtHtml="<tr id='condUndConductor_"+data[x].ucocodigoD+"' class='trcodUndConductor "+classe+"'>"+
			"<td>"+(x+1)+"</td>"+
			"<td>"+data[x].conductor.persona.pernombresV+" "+data[x].conductor.persona.perpaternoV+" "+data[x].conductor.persona.permaternoV+"</td>"+
			"<td>"+data[x].conductor.persona.perdniV+"</td>"+
			"<td>"+data[x].propietariomoto.unidadempresa.uneplacanroV+"</td>"+
			"<td>"+data[x].propietariomoto.unidadempresa.marca.marnombreV+"</td>"+
			"<td>"+data[x].propietariomoto.unidadempresa.modelo.modnombre_V+"</td>"+
			"<td>"+data[x].propietariomoto.unidadempresa.uneannoC+"</td>"+
			"<td>"+data[x].propietariomoto.unidadempresa.unecolorV+"</td>"+
			"<td>"+data[x].ucofechainicioF+"</td>"+
			"<td>"+data[x].ucofechacese+"</td>"+
			"</tr>";
    		$("#tblListaMotosAsignadas tbody").append(txtHtml);
    	}
    	$(".trcodUndConductor").click(obtenerCodigoUnidadConductor);//nombre función
    	$("#tblListaMotosAsignadas").paginacionPager("#pagerMotosAsignadas");
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
    	/*alert($("#txtOcultoCodigoUndConductor").val()+" ; "+$("#txtFechaCese").val()+" ; "+
    			$("#txtObservacion").val());*/
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
    			$.message.Success();
    		},error: function(jqXHR, textStatus, errorThrown){
    			$.message.Error();
    		}
    	});
    	//buscar("PAR.PARCODIGO_I",$("#txtCodigo").val());
    	//$("#divFormulario").dialog("close");
    	$("#divFormularioCese").dialog("close");
    	buscarMototaxis($("#sltCriterioAsignado").val(),$("#txtTextoAsignado").val(),codigoConductor);
    }); 
    
    $("#divNuevoCond").hide();
    
    $("#btnNuevoConductor").click(function(){
    	llenarFormulario("");
    });
    
    $("#btnModificar").click(function(){
    	llenarFormulario("");
    });
    
    function llenarFormulario(data){
    	if(data!=""){
    		$(".error").remove();
    		if(data.conductor.foto.adjnombreV==null)$("#imgFotoConductor").attr("src","images/no_disponible.jpg");
    		else $("#imgFotoConductor").attr("src","temp/"+data.conductor.foto.adjnombreV);
    		$("#txtCodigoConductor").val(data.conductor.concodigoD);
    		$("#txtCodigoPersona").val(data.conductor.persona.percodigoD);
        	$("#txtNombres").val(data.conductor.persona.pernombresV);
        	//alert(JSON.stringify(data.conductor.persona.pernombresV));
        	$("#txtPaterno").val(data.conductor.persona.perpaternoV);
        	$("#txtMaterno").val(data.conductor.persona.permaternoV);
        	$("#txtDni").val(data.conductor.persona.perdniV);
        	$("#sltEstadoCivil").val(data.conductor.persona.perestadocivilC);
        	$("#sltSexo").val(data.conductor.persona.persexoC);
        	$("#dtNacimiento").val(data.conductor.persona.pernacimientoF);
        	$("#txtDireccion").val(data.conductor.persona.perdomicilioV);
        	$("#sltDepartamentos").val(data.conductor.persona.perubdptoV);
        	$("#sltProvincia").empty();
        	$("#sltProvincia").append("<option value='"+data.conductor.persona.perubprovV+"'>"+data.conductor.persona.perubprovnombreV+"</option>");
        	$("#sltDistrito").empty();
        	$("#sltDistrito").append("<option value='"+data.conductor.persona.perubidistV+"'>"+data.conductor.persona.perubidistnombreV+"</option>");
        	$("#txtCorreo").val(data.conductor.persona.peremailV);
        	$("#txtTelefono").val(data.conductor.persona.perteleffijoV);
        	$("#txtMovistar").val(data.conductor.persona.permovilmovV);
        	$("#txtClaro").val(data.conductor.persona.permovilclaV);
        	$("#txtNextel").val(data.conductor.persona.permovilnexV);
        	$("#txtNextel").val(data.conductor.persona.permovilnexV);
        	//alert(JSON.stringify(data.empresaConductor.ecofechainicioF));
        	$("#txtFechaInicio").val(data.empresaConductor.ecofechainicioF);
        	
        	//alert("LENGTH: "+data.conductor.documentos.length);
            for(var x=0;x<data.conductor.documentos.length;x++){
        		$("#documento_"+data.conductor.documentos[x].tipoDocumento.mtdcodigoI).val(data.conductor.documentos[x].adjuntarArchivo.adjcodigoD);
        		$("#txtNumDocumento_"+data.conductor.documentos[x].tipoDocumento.mtdcodigoI).val(data.conductor.documentos[x].adjuntarArchivo.adjnumeroV);
        		$("#txtFechaEmision_"+data.conductor.documentos[x].tipoDocumento.mtdcodigoI).val(data.conductor.documentos[x].adjuntarArchivo.adjfechaemisionF);
        		$("#txtFechaCaducidad_"+data.conductor.documentos[x].tipoDocumento.mtdcodigoI).val(data.conductor.documentos[x].adjuntarArchivo.adjfechacaducidadF);
        		//$("#fileDocumento_"+data.listDocumentos[x].tipoDocumento.mtdcodigoI).val(data.listDocumentos[x].archivo.adjnombreV);	
        	}
    	}else{
    		$(".error").remove();
    		$("#imgFotoConductor").attr("src","images/no_disponible.jpg");
    		$("#txtCodigoAsociado").val(0);
    		$("#txtCodigoPersona").val(0);
        	$("#txtNombres").val("");
        	$("#txtPaterno").val("");
        	$("#txtMaterno").val("");
        	$("#txtDni").val("");
        	$("#txtRuc").val("");
        	$("#txtRazonSocial").val("");
        	$("#sltEstadoCivil").val("");
        	$("#sltSexo").val("");
        	$("#dtNacimiento").val("");
        	$("#txtDireccion").val("");
        	$("#sltDepartamentos").val("");
        	$("#sltProvincia").empty();
        	$("#sltProvincia").append("<option value=''>Seleccione</option>");
        	$("#sltDistrito").empty();
        	$("#sltDistrito").append("<option value=''>Seleccione</option>");
        	$("#txtCorreo").val("");
        	$("#txtTelefono").val("");
        	$("#txtMovistar").val("");
        	$("#txtClaro").val("");
        	$("#txtNextel").val("");
        	$("input[name='txtNumDocumento']").val("");
        	$("input[name='txtFechaEmision']").val("");
        	$("input[name='txtFechaCaducidad']").val("");
        	$("input[name='fileDocumento']").val("");
        	$("#btnVehiculoProcesar").val("Agregar");
    	}
    	$("#divNuevoCond").show();
    	$("#divNuevoCond").dialog({
    		title:"Persona",
    		width:1100,
    		//height: 600,
    		modal: true
    	});
    }
    /*change*/
    $("#sltDepartamentos").change(function(){
    	$.ajax({ 
    		data:{
    			idubigeo:$("#sltDepartamentos").val()
    		},
            datatype:'json',
            type: "POST", 
            url: "Ubigeo/Provincia.htm", 
            success: function(data){
            	llenarComboProvincia(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
    });
    
    function llenarComboProvincia(data){
    	$("#sltProvincia").empty();
    	txtHtml="";
    	for(var x=0;x<data.length;x++){
    		txtHtml+="<option value='"+data[x].idubigeo+"'>"+data[x].nombubigeo+"</option>";
    	}
    	$("#sltProvincia").append(txtHtml);
    }
    
    
    $("#sltProvincia").click(function(){
    	$.ajax({ 
    		data:{
    			idubigeo:$("#sltProvincia").val()
    		},
            datatype:'json',
            type: "POST", 
            url: "Ubigeo/Distrito.htm", 
            success: function(data){
            	llenarComboDistrito(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
    });
    
    function llenarComboDistrito(data){
    	$("#sltDistrito").empty();
    	txtHtml="";
    	for(var x=0;x<data.length;x++){
    		txtHtml+="<option value='"+data[x].idubigeo+"'>"+data[x].nombubigeo+"</option>";
    	}
    	$("#sltDistrito").append(txtHtml);
    }
    
    
    $("#btnGuardarCond").click(function(){

    	$("#divNuevoCond").validate();
    	
    	$.ajax({ 
			data:{
				'conductor.concodigoD':$("#txtCodigoConductor").val(),
				'conductor.persona.percodigoD':$("#txtCodigoPersona").val(),
				'conductor.persona.perdniV':$("#txtDni").val(),
				'conductor.persona.perdomicilioV':encodeURIComponent($("#txtDireccion").val()),
				'conductor.persona.peremailV':encodeURIComponent($("#txtCorreo").val()),
				'conductor.persona.perestadocivilC':encodeURIComponent($("#sltEstadoCivil").val()),
				'conductor.persona.permaternoV':encodeURIComponent($("#txtMaterno").val()),
				'conductor.persona.permovilclaV':$("#txtClaro").val(),
				'conductor.persona.permovilmovV':$("#txtMovistar").val(),
				'conductor.persona.permovilnexV':$("#txtNextel").val(),
				'conductor.persona.pernacimientoF':$("#dtNacimiento").val(),
				'conductor.persona.pernombresV':encodeURIComponent($("#txtNombres").val()),
				'conductor.persona.perpaternoV':encodeURIComponent($("#txtPaterno").val()),
				'conductor.persona.persexoC':encodeURIComponent($("#sltSexo").val()),
				'conductor.persona.perteleffijoV':$("#txtTelefono").val(),
				'conductor.persona.perubdptoV':encodeURIComponent($("#sltDepartamentos").val()),
				'conductor.persona.perubidistV':encodeURIComponent($("#sltDistrito").val()),
				'conductor.persona.perubprovV':encodeURIComponent($("#sltProvincia").val()),
				'ecofechainicioF':$("#txtFechaInicio").val(),
			},
		    datatype:'json',
	        type: "POST", 
	        url: "Conductores/Procesar.htm", 
	        success: function(data){
	        	//alert(JSON.stringify(data));
	        	$("#txtCodigoConductor").val(data.conductor.concodigoD);
	        	$("#txtCodigoPersona").val(data.conductor.persona.percodigoD);
	        	buscar("COND.CONDCODIGO_I",data.conductor.concodigoD);
	        	$("#divNuevoCond").dialog("close");
	        	txtHtml="<p>Operación realizada correctamente</p>";
	        	$.message.Success();
	        },error: function(jqXHR, textStatus, errorThrown){
	        	$.message.Error();
	        }
		});
    	//$(this).dialog('close');
    	
    	
		//$("#divFormulario").dialog("close");
		//buscar("ASO.ASOCODIGO_D",$("#txtCodigoAsociado").val());
    
	});
    

    //FOTO
    $(".formFotoConductor").submit(function(){
    	var options={
    			type: "POST", 
                url:'Conductores/Foto.htm',
                dataType:'json',
                beforeSubmit:function(){
                    $("#progressFoto").show();
                },
                uploadProgress: function(event, position, total, percentComplete) {
                	$("#progressFoto").progressbar({
                        value: percentComplete
                	});
                },
                success: function(responseText, statusText) {     
                	$("#imgFotoConductor").attr("src","temp/"+responseText);
                    $("#progressFoto").hide();
                    $("#progressFoto").progressbar({
                        value: 0
                	});
                },
                error:function(){
                    alert("ERROR");
                }
            };
            $(this).ajaxSubmit(options);
            return false;
    });
    
    //DOCUMENTO
    $(".formDocumento").submit(function(){
    	var options={
    			type: "POST", 
                url:'Conductores/Documento.htm',
                dataType:'json',
                beforeSubmit:function(){
                	$("#progressArchivo").show();
                },
                uploadProgress: function(event, position, total, percentComplete) {
                	$("#progressArchivo").progressbar({
                        value: percentComplete
                	});
                },
                success: function(responseText, statusText) {      
                	$("#progressArchivo").hide();
                	$("#progressArchivo").progressbar({
                        value: 0
                	});
                } ,
                error:function(){
                    alert("ERROR");
                }
            };
            $(this).ajaxSubmit(options);
            return false;
    });

    function eliminar(){
    	var codigo=$(this).attr("id").replace("del","");
    	//alert(codigo);
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
                        url: "Conductores/Eliminar.htm",
                        success: function(data){
							buscar($("#sltCriterio").val(), $("#txtTexto").val());
							$.message.Delete();
                        },error: function(jqXHR, textStatus, errorThrown){
                        	$.message.Error();
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
});