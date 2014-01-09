$(document).ready(function(){
	$("#divFormulario").hide();
	$("#divFormularioVer").hide();  
	$("#divAgregarConductor").hide();
	$("#sltConductor").change(buscarConductorPorCodigo);
	$("#txtConductorDNI").keyup(buscarConductorPorDNI);
	$("#sltPlacas").change(buscarUnidadPorCodigo);
	$("#sltInfraccion").change(buscarPorInfraccion);
	$("#txtDniInspector").keyup(buscarInspectorPorDni);
	$("#sltInspector").change(buscarInspectorPorCodigo);
	$("#txtCarnetPolicia").keyup(buscarPoliciaPorCarnet);
	$("#sltPolicia").change(buscarPoliciaPorCodigo);
	$("#rdPropietarioSi").change(radioPropietarioSi);
	$("#rdPropietarioNo").change(radioPropietarioNo);
	$("#btnGuardar").click(insertarPapeleta);
	$("#formFoto").submit(enviarFoto);
	$("#btnNuevo").click(nuevaPapeleta);
	$("#btnImprimir").click(imprimirPapeleta);
	
	/**/
	$("#dtNacimiento").datepicker({dateFormat:"dd/mm/yy"});
	$("#agregarConductor").click(function(){
		$("#divAgregarConductor").dialog({
    		title:"Nuevo Conductor",
    		width: 950,
    		//height: 700,
    		modal: true
    	});
	});
	
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
    
	/**/
	
	$("#btnBuscar").click(function(){
		buscar($("#sltCriterio").val(),$("#txtTexto").val());
		$.message.Find();
	});
	//$(".dtFecha").datepicker({dateFormat:"dd/mm/yy"});
    $("#txtFecha").datetimepicker(
    	{	dateFormat:"dd/mm/yy",
    		Timeformat: "hh: mm tt",
    		//minDate: getMinFormattedDate(new Date()),
    		maxDate: getMaxFormattedDate(new Date())
    	}
    );
    buscar("PMO.CONDNOMBCOMP","");
    
	$("input[type=text],textarea").keyup(function(){
	  $(this).val( $(this).val().toUpperCase() );
	});
    
    function buscar(criterio, texto){
    	$.ajax({ 
    		data:{
    			criterio:criterio,
    			texto:texto
    		},
            datatype:'json',
            type: "GET", 
            url: "Papeletas/BuscarPorCriterio.htm", 
            success: function(data){
            	//alert(JSON.stringify(data));
            	llenarTablaPapeletas(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	//mensajeError();
            }
    	});
    }
    
    function nuevaPapeleta(){
    	llenarPapeleta("");
    }
    
    function imprimirPapeleta(){
    	var codigo=parseFloat($("#verNumeroPapeleta").text());
    	window.open("Papeletas/ImprimirPdf.htm?codigo="+codigo);
    }
    
    function enviarFoto(){
    	var options={
    			type: "POST", 
                url:'Papeletas/Foto.htm',
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
                	$("#progressFoto").hide();
                	$("#progressFoto").progressbar({
                        value: 0
                	});
                } ,
                error:function(){
                    alert("ERROR DE ENVIO");
                }
            };
            $(this).ajaxSubmit(options);
            return false;
    }

    function insertarPapeleta(){
    	$("#divFormulario").validate();
    	$.ajax({ 
    		data:{
    			'papcodigoD':$("#txtNumeroPapeleta").val(),
    			'policia.polcodigoI':$("#sltPolicia").val(),
    			'infrMedida.imecodigoI':$("#sltSancion").val(),
    			'inspector.inscodigoI':$("#sltInspector").val(),
    			'propUnidadEmpresa.pmocodigoD':$("#sltPlacas").val(),
    			'conductor.concodigoD':$("#sltConductor").val(),
    			'papfechainfraccionF':$("#txtFecha").val(),
    			'paphorainfraccionF':$("#txtFecha").val(),
    			'papinfrdireccionV':$("#txtInfraccionLugar").val(),
    			'papinfrreferenciaV':$("#txtInfraccionReferencia").val(),
    			'pappropietarioC':($("#rdPropietarioSi").attr('checked')=='checked')? 'S' : 'N',
    			'papobservinfraccionV':$("#txaObserInfraccion").val(),
    			'papobservinspectorV':$("#txaObserInspector").val()
    		},
            datatype:'json',
            type: "POST", 
            url: "Papeletas/InsertarPapeleta.htm", 
            success: function(data){
            	$("#txtNumeroPapeleta").val(data.papcodigoD);
            	$("#txtNumeroPap").val(data.papnumeroV);
            	buscar("PAP.PAPCODIGO_D",data.papcodigoD);
            	$("#divFormulario").dialog('close');
            	$.message.Success();
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
    }
    function getMinFormattedDate(date) {
        var day = date.getDate();
        var month = date.getMonth() + 1;
        var year = date.getFullYear().toString().slice(2);
        return day + '/' + month + '/' + year;
    }
    function getMaxFormattedDate(date) {
        var day = date.getDate();
        var month = date.getMonth() + 1;
        var year = date.getFullYear().toString().slice(2);
        return day + '/' + month + '/' + year;
    }
    
	function radioPropietarioSi(event){
		$.ajax({ 
    		data:{
    			estado:'S'
    		},
            datatype:'json',
            type: "GET", 
            url: "Papeletas/BuscarInfraccionPorEstado.htm", 
            success: function(data){
            	llenarComboInfraccion(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});	
	}
	
	function radioPropietarioNo(event){
		$.ajax({ 
    		data:{
    			estado:'N'
    		},
            datatype:'json',
            type: "GET", 
            url: "Papeletas/BuscarInfraccionPorEstado.htm", 
            success: function(data){
            	llenarComboInfraccion(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});	
	}
	
	function buscarPorInfraccion(){
		$.ajax({ 
    		data:{
    			codigo:this.value
    		},
            datatype:'json',
            type: "GET", 
            url: "Papeletas/BuscarTipoMedidaPorInfraccion.htm", 
            success: function(data){
            	llenarDatosInfraccion(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});	
	}
	
	function buscarPoliciaPorCodigo(){
		$.ajax({ 
    		data:{
    			codigo:this.value
    		},
            datatype:'json',
            type: "GET", 
            url: "Papeletas/BuscarPoliciaPorCodigo.htm", 
            success: function(data){
            	llenarDatosPolicia(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
	}
	
	function buscarPoliciaPorCarnet(){
		if($(this).val().length==9){
			$.ajax({ 
	    		data:{
	    			carnet:$(this).val()
	    		},
	            datatype:'json',
	            type: "GET", 
	            url: "Papeletas/BuscarPoliciaPorCarnet.htm", 
	            success: function(data){
	            	llenarDatosPolicia(data);
	            },error: function(jqXHR, textStatus, errorThrown){
	            	$.message.Error();
	            }
	    	});
		}
	}
	
	function buscarInspectorPorDni(){
		if($(this).val().length==8){
			$.ajax({ 
	    		data:{
	    			dni:$(this).val()
	    		},
	            datatype:'json',
	            type: "GET", 
	            url: "Papeletas/BuscarInspectorPorDni.htm", 
	            success: function(data){
	            	llenarDatosInspector(data);
	            },error: function(jqXHR, textStatus, errorThrown){
	            	$.message.Error();
	            }
	    	});
		}
	}
	
	function buscarUnidadPorCodigo(){
		//alert(this.value);
		$.ajax({ 
    		data:{
    			codigo:this.value
    		},
            datatype:'json',
            type: "GET", 
            url: "Papeletas/BuscarUnidadPorCodigo.htm", 
            success: function(data){
            	llenarDatosUnidad(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
	}

	function buscarConductorPorDNI(){
		if($(this).val().length==8){
			$.ajax({ 
	    		data:{
	    			dni:$(this).val()
	    		},
	            datatype:'json',
	            type: "GET", 
	            url: "Papeletas/BuscarConductorPorDNI.htm", 
	            success: function(data){
	            	llenarDatosConductor(data);
	            },error: function(jqXHR, textStatus, errorThrown){
	            	$.message.Error();
	            }
	    	});
		}
	}
	
	function buscarInspectorPorCodigo(){
		$.ajax({ 
    		data:{
    			codigo:this.value
    		},
            datatype:'json',
            type: "GET", 
            url: "Papeletas/BuscarInspectorPorCodigo.htm", 
            success: function(data){
            	llenarDatosInspector(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
	}
	
	function buscarConductorPorCodigo(){
		$.ajax({ 
    		data:{
    			codigo:this.value
    		},
            datatype:'json',
            type: "GET", 
            url: "Papeletas/BuscarConductorPorCodigo.htm", 
            success: function(data){
            	llenarDatosConductor(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
	}
	
	function llenarDatosInfraccion(data){
		if(data!=""){
			var htmlText="";
			$("#sltSancion").empty();
			for(var x=0;x<data.length;x++){
				htmlText+="<option value='"+data[x].imecodigoI+"'>"+data[x].tipoMedida.tmedescripcionV+"</option>";
				$("#txaDescripcion").val(data[x].infraccion.infinfraccionV);
			}
			$("#sltSancion").append(htmlText);	
			$("#sltSancion").combobox('autocomplete',data[0].imecodigoI,data[0].tipoMedida.tmedescripcionV);
		}
	}

	function llenarDatosPolicia(data){
		if(data!=""){
			$("#sltPolicia").combobox('autocomplete',data.polcodigoI,data.polpaternoV+' '+data.polmaternoV+', '+data.polnombresV);
			$("#txtCarnetPolicia").val(data.polcarnetidentV);
		}
	}
	
	function llenarDatosInspector(data){
		if(data!=""){
			$('#sltInspector').combobox('autocomplete',data.fiscalizador.inscodigoI,data.fiscalizador.persona.perpaternoV+' '+data.fiscalizador.persona.permaternoV+', '+data.fiscalizador.persona.pernombresV);
			$("#txtDniInspector").val(data.fiscalizador.persona.perdniV);
			$("#txtInfraccionLugar").val(data.operativo.opelugarV);
			$("#txtInfraccionReferencia").val(data.operativo.opereferencia);	
		}
	}
	
	function llenarDatosConductor(data){
		if(data!=""){
			$('#sltConductor').combobox('autocomplete',data.concodigoD,data.persona.perpaternoV+' '+data.persona.permaternoV+', '+data.persona.pernombresV);
			$("#txtConductorNroLicencia").val(data.archivo.adjnumeroV);
			$("#txtEstadoLicencia").text(data.archivo.adjestadoV);
			$("#txtLicenciaEmision").val(data.archivo.adjfechaemisionF);
			$("#txtLicenciaCaducidad").val(data.archivo.adjfechacaducidadF);
			$("#txtConductorDNI").val(data.persona.perdniV);
			if(data.mensaje>0){
				$("#imgMensajeConductor").attr("src","images/error.png");
				$("#txtMensajeConductor").text(data.mensaje);
			}else{
				$("#imgMensajeConductor").attr("src","images/visto_bueno.png");
				$("#txtMensajeConductor").text(data.mensaje);
			}
		}
	}
	
	function llenarDatosUnidad(data){
		if(data!=""){
			asocodigo_d=data.asociado.asocodigoD;
			$("#txtEstadoUnidad").text(data.archivo.adjestadoV);
			$("#txtAnno").val(data.uneannoC);
			$("#txtTarjPropiedad").val(data.archivo.adjnumeroV);
			$("#txtMarca").val(data.marca.marnombreV);
			$("#txtModelo").val(data.modelo.modnombre_V);
			$("#txtColor").val(data.unecolorV);			
			$("#txtUnidadEmision").val(data.archivo.adjfechaemisionF);			
			$("#txtUnidadCaducidad").val(data.archivo.adjfechacaducidadF);	
			$("#txtPropRazonSocial").val(data.asociado.asorazonsocialV);
			$("#txtPropietario").val(data.asociado.persona.perpaternoV+' '+data.asociado.persona.permaternoV+', '+data.asociado.persona.pernombresV+' ');
			$("#txtPropDni").val(data.asociado.persona.perdniV);
			$("#txtPropDomicilio").val(data.asociado.persona.perdomicilioV);
			if(data.mensaje>0){
				$("#imgMensajeUnidad").attr("src","images/error.png");
				$("#txtMensajeUnidad").text(data.mensaje);
			}else{
				$("#imgMensajeUnidad").attr("src","images/visto_bueno.png");
				$("#txtMensajeUnidad").text(data.mensaje);
			}
			//TODO LLENAR COMBO INFRACCION
		}else{
			asocodigo_d="0";
			
		}
	}
	
	function llenarComboInfraccion(data){
		$("#sltInfraccion").empty();
		var txtHtml="";
		for(var x=0;x<data.length;x++){
			txtHtml+="<option value='"+data[x].infcodigoD+"'>"+data[x].infcodigoV+"</option>";
		}
		$('#sltInfraccion').combobox('autocomplete',data[0].infcodigoD,data[0].infcodigoV);
		$("#sltInfraccion").append(txtHtml);	
	}
	
	function llenarTablaPapeletas(data){
		if(data==""){ 
    		$("#tblLista").empty();
    		return;
    	}
    	var txtHtml="";
    	$("#tblLista").empty();
    	txtHtml="<thead>"
    		+"<th class='header'>N°</th>"
			+"<th class='header'>PROPIETARIO</th>"
			+"<th class='header'>PLACA</th>"
			+"<th class='header'>CONDUCTOR</th>"
			+"<th class='header'>LICENCIA</th>"
			+"<th class='header'>FECHA INFRACCIÓN</th>"
			+"<th class='header'></th>"
			+"<th class='header'></th>"
			+"<th class='header'></th>"
			+"</thead>"
			+"<tfoot>"
    		+"<th>N°</th>"
			+"<th>PROPIETARIO</th>"
			+"<th>PLACA</th>"
			+"<th>CONDUCTOR</th>"
			+"<th>LICENCIA</th>"
			+"<th>FECHA INFRACCIÓN</th>"
			+"<th></th>"
			+"<th></th>"
			+"<th></th>"
			+"</tfoot>"
			+"<tbody></tbody>";
			$("#tblLista").append(txtHtml);
    	for(var x=0;x<data.length;x++){
    		txtHtml="<tr>"+
			"<td>"+(x+1)+"</td>"+
			"<td>"+data[x].propUnidadEmpresa.asociado.persona.pernombresV+"</td>"+
			"<td>"+data[x].propUnidadEmpresa.unidadempresa.uneplacanroV+"</td>"+
			"<td>"+data[x].conductor.persona.pernombresV+"</td>"+
			"<td>"+data[x].conductor.archivo.adjnumeroV+"</td>"+
			"<td>"+data[x].papfechainfraccionF+"</td>"+
			"<td><img alt='Ver' class='btnVer' id='ver"+data[x].papcodigoD+"' src='images/ver.png'></td>"+
			"<td><img alt='Modificar' class='btnModificar' id='mod"+data[x].papcodigoD+"' src='images/edit.png'></td>"+
			"<td><img alt='Imprimir' class='btnImprimir' id='imp"+data[x].papcodigoD+"' src='images/printer.png'></td>"+
			"</tr>";
    		$("#tblLista tbody").append(txtHtml);
    	}
    	$(".btnVer").click(verAjaxPapeleta);
    	$(".btnModificar").click(obtenerPapeleta);
    	$(".btnImprimir").click(printPapeleta);
    	$("#tblLista").paginacion();
    }
	
	function printPapeleta(){
    	var codigo=$(this).attr("id").replace("imp","");
    	window.open("Papeletas/ImprimirPdf.htm?codigo="+codigo);
    }
	
	function obtenerPapeleta(){
		$.ajax({ 
    		data:{
    			codigo:$(this).attr("id").replace("mod","")
    		},
            datatype:'json',
            type: "GET", 
            url: "Papeletas/Obtener.htm", 
            success: function(data){
            	//alert(JSON.stringify(data));
            	llenarPapeleta(data);
            	$.message.Get();
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
	}
	
	function llenarPapeleta(data){
		if(data!=""){
			$("#txtNumeroPapeleta").val(data.papcodigoD);
			$("#txtNumeroPap").val(data.papnumeroV);
			$("#txtFecha").val(data.papfechainfraccionF);
			$("#txtConductorDNI").val(data.conductor.persona.perdniV);
			$('#sltConductor').combobox('autocomplete',data.conductor.concodigoD,data.conductor.persona.perpaternoV+' '+data.conductor.persona.permaternoV+', '+data.conductor.persona.pernombresV);
			$("#txtConductorNroLicencia").val(data.conductor.archivo.adjnumeroV);
			$("#txtEstadoLicencia").text(data.conductor.archivo.adjestadoV);
			$("#txtLicenciaEmision").val(data.conductor.archivo.adjfechaemisionF);
			$("#txtLicenciaCaducidad").val(data.conductor.archivo.adjfechacaducidadF);
			$("#sltPlacas").combobox('autocomplete',data.propUnidadEmpresa.pmocodigoD,data.propUnidadEmpresa.unidadempresa.uneplacanroV);
			$("#txtAnno").val(data.propUnidadEmpresa.unidadempresa.uneannoC);
			$("#txtMarca").val(data.propUnidadEmpresa.unidadempresa.marca.marnombreV);
			$("#txtModelo").val(data.propUnidadEmpresa.unidadempresa.modelo.modnombre_V);
			$("#txtColor").val(data.propUnidadEmpresa.unidadempresa.unecolorV);
			$("#txtTarjPropiedad").val(data.propUnidadEmpresa.unidadempresa.archivo.adjnumeroV);
			$("#txtEstadoUnidad").text(data.propUnidadEmpresa.unidadempresa.archivo.adjestadoV);
			$("#txtUnidadEmision").val(data.propUnidadEmpresa.unidadempresa.archivo.adjfechaemisionF);
			$("#txtUnidadCaducidad").val(data.propUnidadEmpresa.unidadempresa.archivo.adjfechacaducidadF);
			$("#txtPropRazonSocial").val(data.propUnidadEmpresa.asociado.asorazonsocialV);
			$("#txtPropietario").val(data.propUnidadEmpresa.asociado.persona.perpaternoV+" "+
					data.propUnidadEmpresa.asociado.persona.permaternoV+", "+
					data.propUnidadEmpresa.asociado.persona.pernombresV);
			$("#txtPropDni").val(data.propUnidadEmpresa.asociado.persona.perdniV);
			$("#txtPropDomicilio").val(data.propUnidadEmpresa.asociado.persona.perdomicilioV);
			$("#txtDniInspector").val(data.inspector.persona.perdniV);
			$("#txtInfraccionLugar").val(data.papinfrdireccionV);
			$("#txtInfraccionReferencia").val(data.papinfrreferenciaV);
			var inspectorNombres="";
			if(data.inspector.inscodigoI>0) inspectorNombres=data.inspector.persona.perpaternoV+' '+data.inspector.persona.permaternoV+', '+data.inspector.persona.pernombresV;
			$('#sltInspector').combobox('autocomplete',data.inspector.inscodigoI,inspectorNombres);
			$("#txaObserInspector").val(data.papobservinspectorV);
			$("#txtCarnetPolicia").val(data.policia.polcarnetidentV);
			var policiaNombres="";
			if(data.policia.polcodigoI>0) policiaNombres=data.policia.polpaternoV+' '+data.policia.polmaternoV+', '+data.policia.polnombresV;
			$('#sltPolicia').combobox('autocomplete',data.policia.polcodigoI,policiaNombres);
			if(data.pappropietarioC=='S'){
				$("#rdPropietarioSi").attr("checked",true);
			}else{
				$("#rdPropietarioNo").attr("checked",true);
			}
			$("#sltInfraccion").combobox('autocomplete',data.infrMedida.infraccion.infcodigoD,data.infrMedida.infraccion.infcodigoV);
			$("#sltSancion").append("<option value='"+data.infrMedida.imecodigoI+"'>"+data.infrMedida.tipoMedida.tmedescripcionV+"</option>");
			$("#sltSancion").combobox('autocomplete',data.infrMedida.imecodigoI,data.infrMedida.tipoMedida.tmedescripcionV);
			$("#txaDescripcion").val(data.infrMedida.infraccion.infinfraccionV);
			$("#txaObserInfraccion").val(data.papobservinfraccionV);
		}else{
			$("#txtNumeroPapeleta").val("0");
			$("#txtNumeroPap").val("");
			$("#txtFecha").val("");
			$("#txtConductorDNI").val("");
			$('#sltConductor').combobox('autocomplete',"","");
			$("#txtConductorNroLicencia").val("");
			$("#txtEstadoLicencia").val("");
			$("#txtLicenciaEmision").val("");
			$("#txtLicenciaCaducidad").val("");
			$("#sltPlacas").combobox('autocomplete',"","");
			$("#txtAnno").val("");
			$("#txtMarca").val("");
			$("#txtModelo").val("");
			$("#txtColor").val("");
			$("#txtTarjPropiedad").val("");
			$("#txtEstadoUnidad").val("");
			$("#txtUnidadEmision").val("");
			$("#txtUnidadCaducidad").val("");
			$("#txtPropRazonSocial").val("");
			$("#txtPropietario").val("");
			$("#txtPropDni").val("");
			$("#txtPropDomicilio").val("");
			$("#txtDniInspector").val("");
			$("#txtInfraccionLugar").val("");
			$("#txtInfraccionReferencia").val("");
			$('#sltInspector').combobox('autocomplete',"","");
			$("#txaObserInspector").val("");
			$("#txtCarnetPolicia").val("");
			$('#sltPolicia').combobox('autocomplete',"","");
			$("#rdPropietarioSi").attr("checked",true);
			$("#sltInfraccion").combobox('autocomplete',"","");
			$("#sltSancion").combobox('autocomplete',"","");
			$("#txaDescripcion").val("");
			$("#txaObserInfraccion").val("");
		}
		$("#divFormulario").dialog({
    		title:"Papeleta",
    		width: 950,
    		height: 650,
    		modal: true
    	});
	}
	
	function verAjaxPapeleta(){
		$.ajax({ 
    		data:{
    			codigo:$(this).attr("id").replace("ver","")
    		},
            datatype:'json',
            type: "GET", 
            url: "Papeletas/Obtener.htm", 
            success: function(data){
            	verPapeleta(data);
            	$.message.Get();
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
	}
	
	function verPapeleta(data){
		if(data!=""){
			$("#verNumeroPapeleta").text(data.papcodigoD);
			$("#verFecha").text(data.papfechainfraccionF);
			$("#verConductorDNI").text(data.conductor.persona.perdniV);
			$('#verConductor').text(data.conductor.persona.perpaternoV+' '+data.conductor.persona.permaternoV+', '+data.conductor.persona.pernombresV);
			$("#verConductorNroLicencia").text(data.conductor.archivo.adjnumeroV);
			$("#verEstadoLicencia").text(data.conductor.archivo.adjestadoV);
			$("#verLicenciaEmision").text(data.conductor.archivo.adjfechaemisionF);
			$("#verLicenciaCaducidad").text(data.conductor.archivo.adjfechacaducidadF);
			$("#verPlacas").text(data.propUnidadEmpresa.unidadempresa.uneplacanroV);
			$("#verAnno").text(data.propUnidadEmpresa.unidadempresa.uneannoC);
			$("#verMarca").text(data.propUnidadEmpresa.unidadempresa.marca.marnombreV);
			$("#verModelo").text(data.propUnidadEmpresa.unidadempresa.modelo.modnombre_V);
			$("#verColor").text(data.propUnidadEmpresa.unidadempresa.unecolorV);
			$("#verTarjPropiedad").text(data.propUnidadEmpresa.unidadempresa.archivo.adjnumeroV);
			$("#verEstadoUnidad").text(data.propUnidadEmpresa.unidadempresa.archivo.adjestadoV);
			$("#verUnidadEmision").text(data.propUnidadEmpresa.unidadempresa.archivo.adjfechaemisionF);
			$("#verUnidadCaducidad").text(data.propUnidadEmpresa.unidadempresa.archivo.adjfechacaducidadF);
			$("#verPropRazonSocial").text(data.propUnidadEmpresa.asociado.asorazonsocialV);
			$("#verPropietario").text(data.propUnidadEmpresa.asociado.persona.perpaternoV+" "+
					data.propUnidadEmpresa.asociado.persona.permaternoV+", "+
					data.propUnidadEmpresa.asociado.persona.pernombresV);
			$("#verPropDni").text(data.propUnidadEmpresa.asociado.persona.perdniV);
			$("#verPropDomicilio").text(data.propUnidadEmpresa.asociado.persona.perdomicilioV);
			$("#verDniInspector").text(data.inspector.persona.perdniV);
			$("#verInfraccionLugar").text(data.papinfrdireccionV);
			$("#verInfraccionReferencia").text(data.papinfrreferenciaV);
			$('#verInspector').text(data.inspector.persona.perpaternoV+' '+data.inspector.persona.permaternoV+', '+data.inspector.persona.pernombresV);
			$("#verObserInspector").text(data.papobservinspectorV);
			$("#verCarnetPolicia").text(data.policia.polcarnetidentV);
			$('#verPolicia').text(data.policia.polpaternoV+' '+data.policia.polmaternoV+', '+data.policia.polnombresV);
			if(data.pappropietarioC=='S'){
				$("#rdPropietarioSiVer").attr("checked",true);
			}else{
				$("#rdPropietarioNoVer").attr("checked",true);
			}
			$("#verInfraccion").text(data.infrMedida.infraccion.infcodigoD,data.infrMedida.infraccion.infcodigoV);
			$("#verSancion").text(data.infrMedida.tipoMedida.tmedescripcionV);
			$("#verSancion").text(data.infrMedida.tipoMedida.tmedescripcionV);
			$("#verDescripcion").text(data.infrMedida.infraccion.infinfraccionV);
			$("#verObserInfraccion").text(data.papobservinfraccionV);
		}
		$("#divFormularioVer").dialog({
    		title:"Ver",
    		width: 900,
    		//height: 600,
    		modal: true
    	});
	}

	$("#btnAceptar").click(function(){
		$("#divFormularioVer").dialog("close");
	});
	
	$("#btnCancelar").click(function(){
		$("#divFormulario").dialog("close");
	});
	
});

