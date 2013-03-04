$(document).ready(function(){
	$("#divFormulario").hide();
    
    var asocodigo_d="0";
    
	
	
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
    //$(".dtFecha").datepicker({dateFormat:"dd/mm/yy"});
    $("#txtFecha").datetimepicker(
    	{	dateFormat:"dd/mm/yy",
    		Timeformat: "hh: mm tt",
    		minDate: getMinFormattedDate(new Date()),
    		maxDate: getMaxFormattedDate(new Date())
    	}
    );
    buscar("PMO.CONDNOMBCOMP","");
    
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
            	alert(JSON.stringify(data));
            	llenarTablaPapeletas(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	//mensajeError();
            }
    	});
    }
    
    function nuevaPapeleta(){
    	$("#divFormulario").dialog({
    		title:"Inspector",
    		width:1100,
    		height: 600,
    		modal: true
    	});
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
    	$.ajax({ 
    		data:{
    			'policia.polcodigoI':$("#sltPolicia").val(),
    			'infrMedida.imecodigoI':$("#sltSancion").val(),
    			'inspector.inscodigoI':$("#sltInspector").val(),
    			'propUnidadEmpresa.pmocodigoD':asocodigo_d,
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
            },error: function(jqXHR, textStatus, errorThrown){
            	//mensajeError();
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
            	//alert(JSON.stringify(data));
            	llenarComboInfraccion(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	//mensajeError();
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
            	//alert(JSON.stringify(data));
            	llenarComboInfraccion(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	//mensajeError();
            }
    	});	
	}
	
	$("#btnGuardar").click(function(){
		//alert($('#sltConductor').combobox('getValue'));
	});
	
	function buscarPorInfraccion(){
		$.ajax({ 
    		data:{
    			codigo:this.value
    		},
            datatype:'json',
            type: "GET", 
            url: "Papeletas/BuscarTipoMedidaPorInfraccion.htm", 
            success: function(data){
            	//alert(JSON.stringify(data));
            	llenarDatosInfraccion(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	//mensajeError();
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
            	//alert(JSON.stringify(data));
            	llenarDatosPolicia(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	//mensajeError();
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
	            	//alert(JSON.stringify(data));
	            	llenarDatosPolicia(data);
	            },error: function(jqXHR, textStatus, errorThrown){
	            	//mensajeError();
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
	            	//alert(JSON.stringify(data));
	            	llenarDatosInspector(data);
	            },error: function(jqXHR, textStatus, errorThrown){
	            	//mensajeError();
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
            	//alert(JSON.stringify(data));
            	llenarDatosUnidad(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	//mensajeError();
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
	            	//mensajeError();
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
            	//mensajeError();
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
            	//alert(JSON.stringify(data));
            	llenarDatosConductor(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	//mensajeError();
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
			+"<th class='header'>Propietario</th>"
			+"<th class='header'>Placa</th>"
			+"<th class='header'>Conductor</th>"
			+"<th class='header'>Licencia</th>"
			+"<th class='header'>Fecha Infracción</th>"
			+"<th class='header'>Ver</th>"
			+"<th class='header'>Modificar</th>"
			+"</thead>"
			+"<tfoot>"
    		+"<th>N°</th>"
			+"<th>Propietario</th>"
			+"<th>Placa</th>"
			+"<th>Conductor</th>"
			+"<th>Licencia</th>"
			+"<th>Fecha Infracción</th>"
			+"<th>Ver</th>"
			+"<th>Modificar</th>"
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
			"<td><img alt='Ver' class='btnVer' id='ver"+data[x].papcodigoD+"' src='images/edit.png'></td>"+
			"<td><img alt='Modificar' class='btnModificar' id='mod"+data[x].papcodigoD+"' src='images/edit.png'></td>"+
			"</tr>";
    		$("#tblLista tbody").append(txtHtml);
    	}
    	paginacion();
    }

	function paginacion(){
		$("#tblLista")//.tablesorter(); 
        .tablesorter({widthFixed: true, widgets: ['zebra']}) 
        .tablesorterPager({container: $("#pager")}); 	
	}
	
});

