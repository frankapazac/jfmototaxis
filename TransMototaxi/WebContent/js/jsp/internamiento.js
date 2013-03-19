$(document).ready(function(){
	updateTime();
	setInterval(updateTime, 30000);
	$("#divFormulario").hide();
	$("#divFormularioVer").hide();
	$("#sltConductor").change(buscarConductorPorCodigo);
	$("#txtConductorDNI").keyup(buscarConductorPorDNI);
	$("#sltPlacas").change(buscarUnidadPorCodigo);
	$("#txtPapeleta").keyup(buscarPorPapeleta);
	$("#btnProcesar").click(procesar);
	$("#btnImprimir").click(imprimirPapeleta);
	$("#btnBuscar").click(function(){buscar($("#sltCriterio").val(),$("#txtTexto").val());});
	$("#btnNuevo").click(function(){llenarDatos("");});
	$("#btnAceptarVer").click(function(){
		$("#divFormularioVer").dialog('close');
	});
	
	buscar('ITE.INTCODIGO_D','');
	
	function buscar(criterio, texto){
		$.ajax({ 
			data: {
				criterio:criterio,
				texto:texto
			},
            datatype:'json',
            type: "POST", 
            url: "Internamientos/Listar.htm", 
            success: function(data){
            	//alert(JSON.stringify(data));
            	llenarTabla(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	//mensajeError();
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
			+"<th class='header'>NUM</th>"
			+"<th class='header'>INGRESO</th>"
			+"<th class='header'>PAPELETA</th>"
			+"<th class='header'>PLACA</th>"
			+"<th class='header'>CONDUCTOR</th>"
			+"<th class='header'>COND. DNI</th>"
			+"<th class='header'>PROPIETARIO</th>"
			+"<th class='header'>PROP. DNI</th>"
			+"<th class='header'>PAP.</th>"
			+"<th class='header'>BOL. INTER.</th>"
			+"<th class='header'>ACT. CONFO.</th>"
			+"<th class='header'>VER</th>"
			+"<th class='header'>MODIFICAR</th>"
			+"</thead>"
			+"<tfoot>"
			+"<th>NUM</th>"
			+"<th>INGRESO</th>"
			+"<th>PAPELETA</th>"
			+"<th>PLACA</th>"
			+"<th>CONDUCTOR</th>"
			+"<th>COND. DNI</th>"
			+"<th>PROPIETARIO</th>"
			+"<th>PROP. DNI</th>"
			+"<th>PAP.</th>"
			+"<th>BOL. INTER.</th>"
			+"<th>ACT. CONFO.</th>"
			+"<th>VER</th>"
			+"<th>MODIFICAR</th>"
			+"</tfoot>"
			+"<tbody></tbody>";
			$("#tblLista").append(txtHtml);
    	for(var x=0;x<data.length;x++){
    		txtHtml="<tr>"+
			"<td>"+(x+1)+"</td>"+
			"<td>"+data[x].intfechaingresoF+"</td>"+
			"<td>"+data[x].papeleta.papnumeroV+"</td>"+
			"<td>"+data[x].propUnidadEmpresa.unidadempresa.uneplacanroV+"</td>"+
			"<td>"+data[x].conductor.persona.pernombresV+"</td>"+
			"<td>"+data[x].conductor.persona.perdniV+"</td>"+
			"<td>"+data[x].propUnidadEmpresa.asociado.persona.pernombresV+"</td>"+
			"<td>"+data[x].propUnidadEmpresa.asociado.persona.perdniV+"</td>"+
			"<td>"+data[x].papeleta.papcodigoD+"</td>"+
			"<td>"+data[x].boletaInternamiento.bincodigoD+"</td>"+
			"<td>"+data[x].actaConformidad.acocodigoD+"</td>"+
			"<td><img alt='Ver' class='btnVer' id='ver"+data[x].intcodigoD+"' src='images/ver.png'></td>"+
			"<td><img alt='Modificar' class='btnObtener' id='mod"+data[x].intcodigoD+"' src='images/edit.png'></td>"+
			"</tr>";
    		$("#tblLista tbody").append(txtHtml);
    	}
		
    	$(".btnObtener").click(obtener);
    	$(".btnVer").click(ver);
    	paginacion();
    }
	
	function imprimirPapeleta(){
    	var codigo=parseFloat($("#lblCodigo").val());
    	window.open("Internamientos/ImprimirPdf.htm?codigo="+codigo);
    }
	
	function obtener(){
		$.ajax({ 
			data: {
				codigo:$(this).attr("id").replace('mod','')
			},
            datatype:'json',
            type: "POST", 
            url: "Internamientos/Obtener.htm", 
            success: function(data){
            	//alert(JSON.stringify(data));
            	llenarDatos(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	//mensajeError();
            }
    	});
	}
	
	function ver(){
		$.ajax({ 
			data: {
				codigo:$(this).attr("id").replace('ver','')
			},
            datatype:'json',
            type: "POST", 
            url: "Internamientos/Obtener.htm", 
            success: function(data){
            	//alert(JSON.stringify(data));
            	llenarDatosVer(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	//mensajeError();
            }
    	});
	}
	
	function llenarDatosVer(data){
		if(data!=""){
			$(".divPartes input[value='N']").attr('checked',true);
			$("#lblCodigo").val(data.intcodigoD);
			$("#lblBinCodigo").text(data.boletaInternamiento.bincodigoD);
			$("#lblMotivo").text(data.boletaInternamiento.binmotivoV);
			$("#lblFecha").text(data.intfechaingresoF);
			$("#lblHora").text(data.intfechasalidaF);
			$("#lblPapeleta").text(data.papeleta.papnumeroV);
			$("#lblPapCodigo").text(data.papeleta.papcodigoD);
			$('#lblConductor').text(data.conductor.persona.pernombresV);
			$("#lblConductorNroLicencia").text(data.conductor.archivo.adjnumeroV);
			$("#lblEstadoLicencia").text(data.conductor.archivo.adjestadoV);
			$("#lblFechaEmision").text(data.conductor.archivo.adjfechaemisionF);
			$("#lblFechaCaducidad").text(data.conductor.archivo.adjfechacaducidadF);
			$("#lblConductorDNI").text(data.conductor.persona.perdniV);
			$("#lblNroLicencia").text(data.conductor.archivo.adjnumeroV);
			$("#lblDireccion").text(data.conductor.persona.perdomicilioV);
			$("#lblTelefono").text(data.conductor.persona.perteleffijoV);
			$("#lblCelular").text(data.conductor.persona.permovilmovV);
			//asocodigo_d=data.propUnidadEmpresa.asociado.asocodigoD;
			$('#lblPlacas').text(data.propUnidadEmpresa.unidadempresa.uneplacanroV);
			$("#lblMarca").text(data.propUnidadEmpresa.unidadempresa.marca.marnombreV);
			$("#lblNroMotor").text(data.propUnidadEmpresa.unidadempresa.unenromotorV);
			$("#lblColor").text(data.propUnidadEmpresa.unidadempresa.unecolorV);	
			$("#lblDniPropietario").text(data.propUnidadEmpresa.asociado.persona.perdniV);
			$("#lblNombresPropietario").text(data.propUnidadEmpresa.asociado.persona.pernombresV);
			$("#lblDirecPropietario").text(data.propUnidadEmpresa.asociado.persona.perdomicilioV);
			$("#lblTelefPropietario").text(data.propUnidadEmpresa.asociado.persona.perteleffijoV);
			$("#lblCelulPropietario").text(data.propUnidadEmpresa.asociado.persona.permovilmovV);
			$("#lblEmpresa").text(data.propUnidadEmpresa.asociado.empresa.emprazonsocialV);
			$("#lblEmprDireccion").text(data.propUnidadEmpresa.asociado.empresa.empdireccionV);
			$("#lblEmprTelefono").text(data.propUnidadEmpresa.asociado.empresa.emptelefono1V);
			$("#lblEmprCelular").text(data.propUnidadEmpresa.asociado.empresa.empcelularmovV);
			for(var x=0;x<data.inventarios.length;x++){
				$(".rdParte_"+data.inventarios[x].inventarioTipo.bitcodigoI+"[value="+data.inventarios[x].bivestadoC+"]").attr('checked',true);
			}
		}
		$("#divFormularioVer").dialog({
			title:"Internamiento Mototaxis",
			width:1000,
			//height: 600,
			modal: true
		});
	}
	
	function llenarDatos(data){
		if(data!=""){
			$(".divPartes input[value='N']").attr('checked',true);
			$("#btnProcesar").val("Modificar");
			$("#txtCodigo").val(data.intcodigoD);
			$("#txtBinCodigo").val(data.boletaInternamiento.bincodigoD);
			$("#txtMotivo").val(data.boletaInternamiento.binmotivoV);
			$("#txtFecha").val(data.intfechaingresoF);
			$("#txtHora").val(data.intfechasalidaF);
			$("#txtPapeleta").val(data.papeleta.papnumeroV);
			$("#txtPapCodigo").val(data.papeleta.papcodigoD);
			$('#sltConductor').combobox('autocomplete',data.conductor.concodigoD,data.conductor.persona.pernombresV);
			$("#txtConductorNroLicencia").val(data.conductor.archivo.adjnumeroV);
			$("#txtEstadoLicencia").val(data.conductor.archivo.adjestadoV);
			$("#txtFechaEmision").val(data.conductor.archivo.adjfechaemisionF);
			$("#txtFechaCaducidad").val(data.conductor.archivo.adjfechacaducidadF);
			$("#txtConductorDNI").val(data.conductor.persona.perdniV);
			$("#txtNroLicencia").val(data.conductor.archivo.adjnumeroV);
			$("#txtDireccion").val(data.conductor.persona.perdomicilioV);
			$("#txtTelefono").val(data.conductor.persona.perteleffijoV);
			$("#txtCelular").val(data.conductor.persona.permovilmovV);
			//asocodigo_d=data.propUnidadEmpresa.asociado.asocodigoD;
			$('#sltPlacas').combobox('autocomplete',data.propUnidadEmpresa.pmocodigoD,data.propUnidadEmpresa.unidadempresa.uneplacanroV);
			$("#txtMarca").val(data.propUnidadEmpresa.unidadempresa.marca.marnombreV);
			$("#txtNroMotor").val(data.propUnidadEmpresa.unidadempresa.unenromotorV);
			$("#txtColor").val(data.propUnidadEmpresa.unidadempresa.unecolorV);	
			$("#txtDniPropietario").val(data.propUnidadEmpresa.asociado.persona.perdniV);
			$("#txtNombresPropietario").val(data.propUnidadEmpresa.asociado.persona.pernombresV);
			$("#txtDirecPropietario").val(data.propUnidadEmpresa.asociado.persona.perdomicilioV);
			$("#txtTelefPropietario").val(data.propUnidadEmpresa.asociado.persona.perteleffijoV);
			$("#txtCelulPropietario").val(data.propUnidadEmpresa.asociado.persona.permovilmovV);
			$("#txtEmpresa").val(data.propUnidadEmpresa.asociado.empresa.emprazonsocialV);
			$("#txtEmprDireccion").val(data.propUnidadEmpresa.asociado.empresa.empdireccionV);
			$("#txtEmprTelefono").val(data.propUnidadEmpresa.asociado.empresa.emptelefono1V);
			$("#txtEmprCelular").val(data.propUnidadEmpresa.asociado.empresa.empcelularmovV);
			for(var x=0;x<data.inventarios.length;x++){
				$(".rdParte_"+data.inventarios[x].inventarioTipo.bitcodigoI+"[value="+data.inventarios[x].bivestadoC+"]").attr('checked',true);
			}
		}else{
			updateTime();
			$(".divPartes input[value='N']").attr('checked',true);
			$("#btnProcesar").val("Guardar");
			$("#txtCodigo").val("0");
			$("#txtBinCodigo").val("0");
			$("#txtMotivo").val("");
			$("#txtPapeleta").val("");
			$("#txtPapCodigo").val("0");
			$('#sltConductor').combobox('autocomplete','','');
			$("#txtConductorNroLicencia").val("");
			$("#txtEstadoLicencia").val("");
			$("#txtFechaEmision").val("");
			$("#txtFechaCaducidad").val("");
			$("#txtConductorDNI").val("");
			$("#txtNroLicencia").val("");
			$("#txtDireccion").val("");
			$("#txtTelefono").val("");
			$("#txtCelular").val("");
			//asocodigo_d=0;
			$('#sltPlacas').combobox('autocomplete','','');
			$("#txtMarca").val("");
			$("#txtNroMotor").val("");
			$("#txtColor").val("");	
			$("#txtDniPropietario").val("");
			$("#txtNombresPropietario").val("");
			$("#txtDirecPropietario").val("");
			$("#txtTelefPropietario").val("");
			$("#txtCelulPropietario").val("");
			$("#txtEmpresa").val("");
			$("#txtEmprDireccion").val("");
			$("#txtEmprTelefono").val("");
			$("#txtEmprCelular").val("");
		}
		$("#divFormulario").dialog({
			title:"Internamiento Mototaxis",
			width:1000,
			//height: 600,
			modal: true
		});
	}
	
	function procesar(){
		if(!validate("#divFormulario")) return;
		
		var internamiento=new Object();
		internamiento.conductor=new Object();
		internamiento.papeleta=new Object();
		internamiento.boletaInternamiento=new Object();
		internamiento.inventarios=new Array();
		internamiento.propUnidadEmpresa=new Object();
		internamiento.intcodigoD=$("#txtCodigo").val();
		internamiento.boletaInternamiento.bincodigoD=$("#txtBinCodigo").val();
		internamiento.boletaInternamiento.binmotivoV=$("#txtMotivo").val();
		internamiento.conductor.concodigoD=$("#sltConductor").val();
		internamiento.papeleta.papcodigoD=$("#txtPapCodigo").val();
		internamiento.propUnidadEmpresa.pmocodigoD=$("#sltPlacas").val();
		
		var inventario=null, cont=0, partes=null;
		if($("#btnProcesar").val()=="Guardar"){
			partes=$(".divPartes input[value='B']:checked,input[value='R']:checked,input[value='M']:checked");
		}else{
			partes=$(".divPartes input[name^='rdParte']:checked");
		}
		$.each(partes, function(key,value){	
			inventario=new Object();
			inventario.inventarioTipo=new Object();
			var codigo=$(value).attr("class").replace('rdParte_','');
			inventario.inventarioTipo.bitcodigoI=codigo;
			inventario.bivestadoC=$(value).val();
			inventario.bivcantidadI=$("#cant_"+codigo).val();
			internamiento.inventarios[cont++]=inventario;
        });
		//alert(partes.length);
		//alert(JSON.stringify(internamiento));

		if($("#btnProcesar").val()=="Guardar"){
			$.ajax({ 
				data: JSON.stringify(internamiento),
	            datatype:'json',
	            type: "POST", 
	            contentType : "application/json",
	            url: "Internamientos/Insertar.htm", 
	            success: function(data){
	            	//alert(JSON.stringify(data));
	            	$("#txtCodigo").val(data);
	            	buscar('ITE.INTCODIGO_D',data);
	            	$("#divFormulario").dialog('close');
	            },error: function(jqXHR, textStatus, errorThrown){
	            	//mensajeError();
	            }
	    	});
		}else{
			$.ajax({ 
				data: JSON.stringify(internamiento),
	            datatype:'json',
	            type: "POST", 
	            contentType : "application/json",
	            url: "Internamientos/Modificar.htm", 
	            success: function(data){
	            	//alert(JSON.stringify(data));
	            	$("#txtCodigo").val(data);
	            	buscar('ITE.INTCODIGO_D',data);
	            	$("#divFormulario").dialog('close');
	            },error: function(jqXHR, textStatus, errorThrown){
	            	//mensajeError();
	            }
	    	});			
		}		
	}
	
	function buscarPorPapeleta(){
		//alert($(this).val().length);
		if($(this).val().length==12){
			$.ajax({ 
	    		data:{
	    			papnumero:$(this).val()
	    		},
	            datatype:'json',
	            type: "POST", 
	            url: "Internamientos/BuscarPorPapeleta.htm", 
	            success: function(data){
	            	//alert(JSON.stringify(data));
	            	llenarDatosPapeleta(data);
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
            url: "Internamientos/BuscarUnidadPorCodigo.htm", 
            success: function(data){
            	//alert(JSON.stringify(data));
            	llenarDatosUnidad(data);
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
	
	function llenarDatosUnidad(data){
		if(data!=""){
			//asocodigo_d=data.asociado.asocodigoD;
			$("#lblMarca").text(data.marca.marnombreV);
			$("#lblNroMotor").text(data.unenromotorV);
			$("#lblColor").text(data.unecolorV);	
			$("#lblDniPropietario").text(data.asociado.persona.perdniV);
			$("#lblNombresPropietario").text(data.asociado.persona.perpaternoV+' '+data.asociado.persona.permaternoV+', '+data.asociado.persona.pernombresV+' ');
			$("#lblDirecPropietario").text(data.asociado.persona.perdomicilioV);
			$("#lblTelefPropietario").text(data.asociado.persona.perteleffijoV);
			$("#lblCelulPropietario").text(data.asociado.persona.permovilmovV);

			$("#lblEmpresa").text(data.asociado.empresa.emprazonsocialV);
			$("#lblEmprDireccion").text(data.asociado.empresa.empdireccionV);
			$("#lblEmprTelefono").text(data.asociado.empresa.emptelefono1V);
			$("#lblEmprCelular").text(data.asociado.empresa.empcelularmovV);
			//TODO LLENAR COMBO INFRACCION
		}else{
			//asocodigo_d="0";
			
		}
	}
	
	function llenarDatosPapeleta(data){
		if(data!=""){
			$('#sltConductor').combobox('autocomplete',data.conductor.concodigoD,data.conductor.persona.perpaternoV+' '+data.conductor.persona.permaternoV+', '+data.conductor.persona.pernombresV);
			$("#txtPapCodigo").val(data.papcodigoD);
			//$("#txtConductorNroLicencia").val(data.conductor.archivo.adjnumeroV);
			$("#txtEstadoLicencia").text(data.conductor.archivo.adjestadoV);
			$("#lblFechaEmision").text(data.conductor.archivo.adjfechaemisionF);
			$("#lblFechaCaducidad").text(data.conductor.archivo.adjfechacaducidadF);
			$("#txtConductorDNI").val(data.conductor.persona.perdniV);
			$("#lblNroLicencia").text(data.conductor.archivo.adjnumeroV);
			$("#lblDireccion").text(data.conductor.persona.perdomicilioV);
			$("#lblTelefono").text(data.conductor.persona.perteleffijoV);
			$("#lblCelular").text(data.conductor.persona.permovilmovV);
			
			asocodigo_d=data.propUnidadEmpresa.asociado.asocodigoD;
			//alert(data.propUnidadEmpresa.pmocodigoD);
			$('#sltPlacas').combobox('autocomplete',data.propUnidadEmpresa.pmocodigoD,data.propUnidadEmpresa.unidadempresa.uneplacanroV);
			$("#lblMarca").text(data.propUnidadEmpresa.unidadempresa.marca.marnombreV);
			$("#lblNroMotor").text(data.propUnidadEmpresa.unidadempresa.unenromotorV);
			$("#lblColor").text(data.propUnidadEmpresa.unidadempresa.unecolorV);	
			$("#lblDniPropietario").text(data.propUnidadEmpresa.asociado.persona.perdniV);
			$("#lblNombresPropietario").text(data.propUnidadEmpresa.asociado.persona.perpaternoV+' '+data.propUnidadEmpresa.asociado.persona.permaternoV+', '+data.propUnidadEmpresa.asociado.persona.pernombresV);
			$("#lblDirecPropietario").text(data.propUnidadEmpresa.asociado.persona.perdomicilioV);
			$("#lblTelefPropietario").text(data.propUnidadEmpresa.asociado.persona.perteleffijoV);
			$("#lblCelulPropietario").text(data.propUnidadEmpresa.asociado.persona.permovilmovV);

			$("#lblEmpresa").text(data.propUnidadEmpresa.asociado.empresa.emprazonsocialV);
			$("#lblEmprDireccion").text(data.propUnidadEmpresa.asociado.empresa.empdireccionV);
			$("#lblEmprTelefono").text(data.propUnidadEmpresa.asociado.empresa.emptelefono1V);
			$("#lblEmprCelular").text(data.propUnidadEmpresa.asociado.empresa.empcelularmovV);
		}
	}
	
	function llenarDatosConductor(data){
		if(data!=""){
			$('#sltConductor').combobox('autocomplete',data.concodigoD,data.persona.perpaternoV+' '+data.persona.permaternoV+', '+data.persona.pernombresV);
			$("#txtConductorNroLicencia").val(data.archivo.adjnumeroV);
			$("#txtEstadoLicencia").text(data.archivo.adjestadoV);
			$("#lblFechaEmision").text(data.archivo.adjfechaemisionF);
			$("#lblFechaCaducidad").text(data.archivo.adjfechacaducidadF);
			$("#txtConductorDNI").val(data.persona.perdniV);
			$("#lblNroLicencia").text(data.archivo.adjnumeroV);
			$("#lblDireccion").text(data.persona.perdomicilioV);
			$("#lblTelefono").text(data.persona.perteleffijoV);
			$("#lblCelular").text(data.persona.permovilmovV);
		}
	}
	
	function updateTime() {
		if($("#btnProcesar").val()!="Guardar")return;
	    var d= new Date();
	    /*var year = d.getFullYear();
	    var month = d.getMonth()+1;
	    var day = d.getDate();*/
	    var hour = d.getHours();
	    var minute = d.getMinutes();
	    //var second = d.getSeconds();
	    var today_date =
	        ("0" + d.getDate()).slice(-2) + "/" +
	        ("0" + (d.getMonth() + 1)).slice(-2) + "/" + 
	        d.getFullYear();
	    $("#txtFecha").text(today_date);
	    $("#txtHora").text(hour+":"+minute);
	}
	
    
    function paginacion(){
		$("#tblLista")//.tablesorter(); 
        .tablesorter({widthFixed: true, widgets: ['zebra']}) 
        .tablesorterPager({container: $("#pager")}); 	
	}
    
    function validate(elemento){
    	$(".error").remove();
    	var elementText=$(elemento+" .requiredText");
    	var elementEmail=$(elemento+" .requiredEmail");
    	var elementNumero=$(elemento+" .requiredNumber");
    	var elementDecimal=$(elemento+" .requiredDecimal");
    	var elementFecha=$(elemento+" .requiredDate");
    	var elementHora=$(elemento+" .requiredHour");
    	var elementSelect=$(elemento+" .requiredSelect");
    	var elementFile=$(elemento+" .requiredFile");
    	var elementRequired=$(elemento+" .required");
    	var contador=0;
    	$.each(elementRequired,function(key,value){
    		if($(this).val()=="0"||$(this).val()==""){
    			$(this).after("<span class='error' style='color:red'>*</span>");
    		}
		});
    	$.each(elementText,function(key,value){
    		if(!validarLetras($(this).val())){
    			contador++;
    			$(this).after("<span class='error' style='color:red'>*</span>");
    		}
		});
    	$.each(elementEmail,function(key,value){
    		if(!validarEmail($(this).val())){
    			contador++;
    			$(this).after("<span class='error' style='color:red'>*</span>");
    		}
    	});
    	$.each(elementNumero,function(key,value){
    		if(!validarNumeros($(this).val())){
    			contador++;
    			$(this).after("<span class='error' style='color:red'>*</span>");
    		}
    	});
    	$.each(elementDecimal,function(key,value){
    		if(!validarDecimales($(this).val())){
    			contador++;
    			$(this).after("<span class='error' style='color:red'>*</span>");
    		}
    	});
    	$.each(elementFecha,function(key,value){
    		if(!validarFechas($(this).val())){
    			contador++;
    			$(this).after("<span class='error' style='color:red'>*</span>");
    		}
    	});
    	$.each(elementHora,function(key,value){
    		if(!validarHoras($(this).val())){
    			contador++;
    			$(this).after("<span class='error' style='color:red'>*</span>");
    		}
    	});
    	$.each(elementSelect,function(key,value){
    		if($(this).val()=="0"||$(this).val()==""){
    			contador++;
    			$(this).after("<span class='error' style='color:red'>*</span>");
    		}
		});
    	$.each(elementFile,function(key,value){
    		if($(this).val()=="0"||$(this).val()==""){
    			contador++;
    			$(this).after("<span class='error' style='color:red'>*</span>");
    		}
		});
    	if(contador<1){
    		return true;
    	}else{
    		return false;
    	}
	}
	
	function validarEmail(texto){
	    var filter = /[\w-\.]{3,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/;
	    if(filter.test(texto))
	        return true;
	    else
	        return false;
	}

	function validarLetras(texto){
	    //var filter = /^[A-Za-z\_\-\.\s\xF1\xD1]+$/;
	    var filter =/^[a-zA-Z0-9 áéíóúAÉÍÓÚÑñ]+$/;
		if(filter.test(texto))
	        return true;
	    else
	        return false;
	}

	function validarNumeros(texto){
	    var filter = /^(?:\+|-)?\d+$/;
	    if(filter.test(texto))
	        return true;
	    else
	        return false;
	}	

	function validarDecimales(texto){
	    var filter = /^\d{1,2}\/\d{1,2}\/\d{2,4}$/;
	    if(filter.test(texto))
	        return true;
	    else
	        return false;
	}	

	function validarFechas(texto){
	    var filter = /^\d{1,2}\/\d{1,2}\/\d{2,4}$/;
	    if(filter.test(texto))
	        return true;
	    else
	        return false;
	}	

	function validarHoras(texto){
	    var filter = /^[0-2][0-9]:[0-5][0-9]$/;
	    if(filter.test(texto))
	        return true;
	    else
	        return false;
	}
	
});