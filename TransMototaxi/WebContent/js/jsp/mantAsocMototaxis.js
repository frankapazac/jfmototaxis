$(document).ready(function(){
	$("#btnSiguiente").click(fncSiguiente);
	$("#btnVehiculoProcesar").click(fncVehiculoProcesar);
	$(".formDocumentoUnidad").submit(fncDocumentoUnidad);
	$(".formDocumentoFoto").submit(fncDocumentoFoto);
	$("#btnMotoEditar").click(fncEditarMoto);
	$("#btnVehiculoCancelar").click(fncCancelar);
	$("#btnMotoNuevo").click(function(){llenarDatosMototaxi([]);});
	var codigoMototaxi="0";
	
	//DOCUMENTO 
    function fncDocumentoUnidad(){
    	var options={
    			type: "POST", 
                url:'UnidadEmpresa/Documento.htm',
                dataType:'html',
                beforeSubmit:function(){
                	$("#progressMototaxiArchivo").show();
                },
                uploadProgress: function(event, position, total, percentComplete) {
                	$("#progressMototaxiArchivo").progressbar({
                        value: percentComplete
                	});
                },
                success: function(responseText, statusText) {      
                	$("#progressMototaxiArchivo").hide();
                    $("#progressMototaxiArchivo").progressbar({
                        value: 0
                	});
                } ,
                error:function(){
                	alert("ERROR");
                }
            };
            $(this).ajaxSubmit(options);
            return false;
    }
    //DOCUMENTO 
    function fncDocumentoFoto(){
    	var options={
    			type: "POST", 
                url:'UnidadEmpresa/DocumentoFoto.htm',
                dataType:'json',
                beforeSubmit:function(){
                    $("#progressVehiculo").show();
                },
                uploadProgress: function(event, position, total, percentComplete) {
                	$("#progressVehiculo").progressbar({
                        value: percentComplete
                	});
                },
                success: function(responseText, statusText) {
                	var texto=responseText.split('|');
                	$("#imgFotoVehiculo_"+texto[0]).attr("src","temp/"+texto[1]);
                    $("#progressVehiculo").hide();
                    $("#progressVehiculo").progressbar({
                        value: 0
                	});
                } ,
                error:function(){
                	alert("ERROR");
                }
            };
            $(this).ajaxSubmit(options);
            return false;
    }
	
	function fncVehiculoProcesar(){
    	$("#tabs2").validate();
		$.ajax({ 
    		data:{
    			'propUnidadEmpresa.asociado.asocodigoD':$("#txtCodigoAsociado").val(),
    			'empadronamiento.epocodigoD':$("#txtCodigoEmpadronamiento").val(),
    			'propUnidadEmpresa.unidadempresa.unecodigoD':$("#txtCodigoVehiculo").val(),
    			'propUnidadEmpresa.unidadempresa.uneplacanroV':$("#txtNroPlaca").val(),
    			'propUnidadEmpresa.unidadempresa.oficina.oficodigoI':$("#sltOfRegistral").val(),
    			'propUnidadEmpresa.unidadempresa.unenropadronV':$("#txtNroPadron").val(),
    			'propUnidadEmpresa.unidadempresa.unenropartidaregistralV':$("#txtPartRegistral").val(),
    			'propUnidadEmpresa.unidadempresa.unetituloV':$("#txtTitulo").val(),
    			'propUnidadEmpresa.unidadempresa.uneclaseV':$("#txtClase").val(),
    			'propUnidadEmpresa.unidadempresa.marca.marcodigoI':$("#sltMarca").val(),
    			'propUnidadEmpresa.unidadempresa.modelo.modcodigo_D':$("#sltModelo").val(),
    			'propUnidadEmpresa.unidadempresa.uneannoC':$("#txtAno").val(),
    			'propUnidadEmpresa.unidadempresa.unecolorV':$("#txtColor").val(),
    			'propUnidadEmpresa.unidadempresa.unecombustibleC':$("#sltCombustible").val(),
    			'propUnidadEmpresa.unidadempresa.unecarroceriaC':$("#sltCarroceria").val(),
    			'propUnidadEmpresa.unidadempresa.unenroseriechasisV':$("#txtNroSerieChasis").val(),
    			'propUnidadEmpresa.unidadempresa.unenromotorV':$("#txtNroMotor").val(),
    			'propUnidadEmpresa.unidadempresa.unenroidentificacionV':$("#txtNroNiv").val(),
    			'propUnidadEmpresa.unidadempresa.unenroruedasI':$("#txtRuedas").val(),
    			'propUnidadEmpresa.unidadempresa.unenroasientosI':$("#txtNroAsientos").val(),
    			'propUnidadEmpresa.unidadempresa.unenropasajerosI':$("#txtNroPasajeros").val(),
    			'propUnidadEmpresa.unidadempresa.unecargautilD':$("#txtCargaUtil").val(),
    			'propUnidadEmpresa.unidadempresa.unelongitudD':$("#txtLongitud").val(),
    			'propUnidadEmpresa.unidadempresa.uneanchoD':$("#txtAncho").val(),
    			'propUnidadEmpresa.unidadempresa.unealtoD':$("#txtAlto").val()
    		},
            datatype:'json',
            type: "POST", 
            url: "UnidadEmpresa/Procesar.htm", 
            success: function(data){
            	$("#txtCodigoVehiculo").val(data.propUnidadEmpresa.unidadempresa.unecodigoD);
            	$("#txtCodigoEmpadronamiento").val(data.empadronamiento.epocodigoD);
            	llenarTablaMotos();
            	$.message.Success();
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
	}
	
	function fncSiguiente(){
		if($("#txtCodigoAsociado").val()==0){
			$.message.Info('Información','Guarde antes de continuar por favor');
			return;
		}
		llenarTablaMotos();
		llenarDatosMototaxi("");
		$("#tabs").tabs('enable',1).tabs("select",1);
	}
	
	function llenarTablaMotos(){
		$.ajax({ 
    		data:{
    			codigo:$("#txtCodigoAsociado").val()
    		},
            datatype:'json',
            type: "GET", 
            url: "UnidadEmpresa/ListarPorAsociado.htm", 
            success: function(data){
            	if(data==""){ 
            		$("#tblMotos").empty();
            		return;
            	}
            	var txtHtml="";
            	$("#tblMotos").empty();
            	txtHtml="<thead>"
        			+"<th class='header'>NUM</th>"
        			+"<th class='header'>PLACA</th>"
        			+"<th class='header'>MARCA</th>"
        			+"</thead>"
        			+"<tfoot>"
        			+"<th>NUM</th>"
        			+"<th>PLACA</th>"
        			+"<th>MARCA</th>"
        			+"</tfoot>"
        			+"<tbody></tbody>";
        			$("#tblMotos").append(txtHtml);
            	for(var x=0;x<data.length;x++){
            		var classe="even";
            		if(x%2==0) classe="odd";
            		txtHtml="<tr id='mot"+data[x].unidadempresa.unecodigoD+"' class='trMotos "+classe+"'>"+
        			"<td>"+(x+1)+"</td>"+
        			"<td>"+data[x].unidadempresa.uneplacanroV+"</td>"+
        			"<td>"+data[x].unidadempresa.marca.marnombreV+"</td>"+
        			"</tr>";
            		$("#tblMotos tbody").append(txtHtml);
            	}
        		$(".trMotos").click(fncCambiaMoto);
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
	}
	
	function fncCambiaMoto(){
		codigoMototaxi=$(this).attr("id").replace("mot","");
	}
	
	function fncEditarMoto(){
		if(codigoMototaxi=="0"){
			mensaje("Seleccione una moto de la tabla antes de continuar");
		}
		$.ajax({ 
    		data:{
    			codigo:codigoMototaxi
    		},
            datatype:'json',
            type: "GET", 
            url: "Mototaxi/Obtener.htm", 
            success: function(data){
            	llenarDatosMototaxi(data);
            	$.message.Get();
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
	}
	
	function llenarDatosMototaxi(data){
    	$("#tabs2").validateClean();
		if(data!=""){
			$("divVehiculoArchivos input[name='txtCodArchivo']").val("0");
			$("divVehiculoArchivos input[name='txtNumDocumento']").val("");
			$("divVehiculoArchivos input[name='txtFechaEmision']").val("");
			$("divVehiculoArchivos input[name='txtFechaCaducidad']").val("");
			
			$("img[class='imgFotosVehiculo']").attr("src","images/no_disponible.jpg");
    		$("#txtCodigoVehiculo").val(data.unidadEmpresa.unecodigoD);
			$("#txtCodigoEmpadronamiento").val(data.empadronamiento.epocodigoD);
			$("#txtEmpadFechaInicio").val(data.empadronamiento.empfechainicioF);
			$("#txtEmpadFechaCese").val(data.empadronamiento.empfechaceseF);
			$("#txtNroPlaca").val(data.unidadEmpresa.uneplacanroV);
			$("#sltCarroceria").val(data.unidadEmpresa.unecarroceriaC);
			$("#sltOfRegistral").val(data.unidadEmpresa.oficina.oficodigoI);
			$("#txtNroSerieChasis").val(data.unidadEmpresa.unenroseriechasisV);
			$("#txtNroPadron").val(data.unidadEmpresa.unenropadronV);
			$("#txtNroMotor").val(data.unidadEmpresa.unenromotorV);
			$("#txtPartRegistral").val(data.unidadEmpresa.unenropartidaregistralV);
			$("#txtNroNiv").val(data.unidadEmpresa.unenroidentificacionV);
			$("#txtTitulo").val(data.unidadEmpresa.unetituloV);
			$("#txtRuedas").val(data.unidadEmpresa.unenroruedasI);
			$("#txtClase").val(data.unidadEmpresa.uneclaseV);
			$("#txtNroAsientos").val(data.unidadEmpresa.unenroasientosI);
			$("#sltMarca").val(data.unidadEmpresa.marca.marcodigoI);
			$("#txtNroPasajeros").val(data.unidadEmpresa.unenropasajerosI);
			$("#sltModelo").val(data.unidadEmpresa.modelo.modcodigo_D);
			$("#txtCargaUtil").val(data.unidadEmpresa.unecargautilD);
			$("#txtAno").val(data.unidadEmpresa.uneannoC);
			$("#txtLongitud").val(data.unidadEmpresa.unelongitudD);
			$("#txtColor").val(data.unidadEmpresa.unecolorV);
			$("#txtAncho").val(data.unidadEmpresa.uneanchoD);
			$("#sltCombustible").val(data.unidadEmpresa.unecombustibleC);
			$("#txtAlto").val(data.unidadEmpresa.unealtoD);
			for(var x=0;x<data.documentos.length;x++){
				$("#documentoUnd_"+data.documentos[x].tipoDocumento.mtdcodigoI).val(data.documentos[x].adjuntarArchivo.adjcodigoD);
        		$("#txtUndNumDocumento_"+data.documentos[x].tipoDocumento.mtdcodigoI).val(data.documentos[x].adjuntarArchivo.adjnumeroV);
        		$("#txtUndFechaEmision_"+data.documentos[x].tipoDocumento.mtdcodigoI).val(data.documentos[x].adjuntarArchivo.adjfechaemisionF);
        		$("#txtUndFechaCaducidad_"+data.documentos[x].tipoDocumento.mtdcodigoI).val(data.documentos[x].adjuntarArchivo.adjfechacaducidadF);
			}
			for(var f=0;f<data.fotos.length;f++){
	    		$("#imgFotoVehiculo_"+data.fotos[f].tipoDocumento.mtdcodigoI).attr("src","temp/"+data.fotos[f].adjuntarArchivo.adjnombreV);	
			}
			$("#btnVehiculoProcesar").val("Modificar");
		}else{
			$("divVehiculoArchivos input[name='txtCodArchivo']").val("0");
			$("divVehiculoArchivos input[name='txtNumDocumento']").val("");
			$("divVehiculoArchivos input[name='txtFechaEmision']").val("");
			$("divVehiculoArchivos input[name='txtFechaCaducidad']").val("");
			
			$("img[class='imgFotosVehiculo']").attr("src","images/no_disponible.jpg");
			$("#txtCodigoVehiculo").val("");
			$("#txtCodigoEmpadronamiento").val("");
			$("#txtEmpadFechaInicio").val("");
			$("#txtEmpadFechaCese").val("");
			$("#txtNroPlaca").val("");
			$("#sltCarroceria").val("");
			$("#sltOfRegistral").val("");
			$("#txtNroSerieChasis").val("");
			$("#txtNroPadron").val("");
			$("#txtNroMotor").val("");
			$("#txtPartRegistral").val("");
			$("#txtNroNiv").val("");
			$("#txtTitulo").val("");
			$("#txtRuedas").val("");
			$("#txtClase").val("");
			$("#txtNroAsientos").val("");
			$("#sltMarca").val("");
			$("#txtNroPasajeros").val("");
			$("#sltModelo").val("");
			$("#txtCargaUtil").val("");
			$("#txtAno").val("");
			$("#txtLongitud").val("");
			$("#txtColor").val("");
			$("#txtAncho").val("");
			$("#sltCombustible").val("");
			$("#txtAlto").val("");
			$(".txtVehNumDocumento").val("");
			$(".txtVehFecEmision").val("");
			$(".txtVehFecCaducidad").val("");
			$("#btnVehiculoProcesar").val("Insertar");
		}
	}
	
	function fncCancelar(){
		$("#divFormulario").dialog("close");
	}
});