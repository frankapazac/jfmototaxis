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
	$("#btnBuscar").click(function(){
		buscar($("#sltCriterio").val(),$("#txtTexto").val());
		$.message.Find();
	});
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
			+"<th class='header'>NUM</th>"
			+"<th class='header'>INGRESO</th>"
			+"<th class='header'>PAPELETA</th>"
			+"<th class='header'>PLACA</th>"
			+"<th class='header'>CONDUCTOR</th>"
			+"<th class='header'>C. DNI</th>"
			+"<th class='header'>PROPIETARIO</th>"
			+"<th class='header'>P. DNI</th>"
			+"<th class='header'>PAP.</th>"
			+"<th class='header'>BOL. INTER.</th>"
			+"<th class='header'>ACT. CONFO.</th>"
			+"<th class='header'></th>"
			+"<th class='header'></th>"
			+"<th class='header'></th>"
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
			+"<th></th>"
			+"<th></th>"
			+"<th></th>"
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
			"<td><img alt='Imprimir' class='btnImprimir' id='imp"+data[x].intcodigoD+"' src='images/printer.png'></td>"+
			"</tr>";
    		$("#tblLista tbody").append(txtHtml);
    	}
		
    	$(".btnObtener").click(obtener);
    	$(".btnVer").click(ver);
    	$(".btnImprimir").click(printInternamiento);
    	$("#tblLista").paginacion();
    }
	function printInternamiento(){
    	var codigo=$(this).attr("id").replace("imp","");
    	window.open("Internamientos/ImprimirPdf.htm?codigo="+codigo);
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
            	llenarDatos(data);
            	$.message.Get();
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
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
            	llenarDatosVer(data);
            	$.message.Get();
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
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
		$("#divFormulario").validate();
		
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
	            	$("#txtCodigo").val(data);
	            	buscar('ITE.INTCODIGO_D',data);
	            	$("#divFormulario").dialog('close');
	            	$.message.Insert();
	            },error: function(jqXHR, textStatus, errorThrown){
	            	$.message.Error();
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
	            	$("#txtCodigo").val(data);
	            	buscar('ITE.INTCODIGO_D',data);
	            	$("#divFormulario").dialog('close');
	            	$.message.Update();
	            },error: function(jqXHR, textStatus, errorThrown){
	            	$.message.Error();
	            }
	    	});			
		}		
	}
	
	function buscarPorPapeleta(){
		if($(this).val().length==12){
			$.ajax({ 
	    		data:{
	    			papnumero:$(this).val()
	    		},
	            datatype:'json',
	            type: "POST", 
	            url: "Internamientos/BuscarPorPapeleta.htm", 
	            success: function(data){
	            	llenarDatosPapeleta(data);
	            	$.message.Get();
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
            url: "Internamientos/BuscarUnidadPorCodigo.htm", 
            success: function(data){
            	llenarDatosUnidad(data);
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
	
	function llenarDatosUnidad(data){
		if(data!=""){
			$("#txtMarca").val(data.marca.marnombreV);
			$("#txtNroMotor").val(data.unenromotorV);
			$("#txtColor").val(data.unecolorV);	
			$("#txtDniPropietario").val(data.asociado.persona.perdniV);
			$("#txtNombresPropietario").val(data.asociado.persona.perpaternoV+' '+data.asociado.persona.permaternoV+', '+data.asociado.persona.pernombresV+' ');
			$("#txtDirecPropietario").val(data.asociado.persona.perdomicilioV);
			$("#txtTelefPropietario").val(data.asociado.persona.perteleffijoV);
			$("#txtCelulPropietario").val(data.asociado.persona.permovilmovV);

			$("#txtEmpresa").val(data.asociado.empresa.emprazonsocialV);
			$("#txtEmprDireccion").val(data.asociado.empresa.empdireccionV);
			$("#txtEmprTelefono").val(data.asociado.empresa.emptelefono1V);
			$("#txtEmprCelular").val(data.asociado.empresa.empcelularmovV);
			//TODO LLENAR COMBO INFRACCION
		}
	}
	
	function llenarDatosPapeleta(data){
		if(data!=""){
			$('#sltConductor').combobox('autocomplete',data.conductor.concodigoD,data.conductor.persona.perpaternoV+' '+data.conductor.persona.permaternoV+', '+data.conductor.persona.pernombresV);
			$("#txtPapCodigo").val(data.papcodigoD);
			//$("#txtConductorNroLicencia").val(data.conductor.archivo.adjnumeroV);
			$("#txtEstadoLicencia").val(data.conductor.archivo.adjestadoV);
			$("#txtFechaEmision").val(data.conductor.archivo.adjfechaemisionF);
			$("#txtFechaCaducidad").val(data.conductor.archivo.adjfechacaducidadF);
			$("#txtConductorDNI").val(data.conductor.persona.perdniV);
			$("#txtNroLicencia").val(data.conductor.archivo.adjnumeroV);
			$("#txtDireccion").val(data.conductor.persona.perdomicilioV);
			$("#txtTelefono").val(data.conductor.persona.perteleffijoV);
			$("#txtCelular").val(data.conductor.persona.permovilmovV);
			
			//asocodigo_d=data.propUnidadEmpresa.asociado.asocodigoD;
			//alert(data.propUnidadEmpresa.pmocodigoD);
			$('#sltPlacas').combobox('autocomplete',data.propUnidadEmpresa.pmocodigoD,data.propUnidadEmpresa.unidadempresa.uneplacanroV);
			$("#txtMarca").val(data.propUnidadEmpresa.unidadempresa.marca.marnombreV);
			$("#txtNroMotor").val(data.propUnidadEmpresa.unidadempresa.unenromotorV);
			$("#txtColor").val(data.propUnidadEmpresa.unidadempresa.unecolorV);	
			$("#txtDniPropietario").val(data.propUnidadEmpresa.asociado.persona.perdniV);
			$("#txtNombresPropietario").val(data.propUnidadEmpresa.asociado.persona.perpaternoV+' '+data.propUnidadEmpresa.asociado.persona.permaternoV+', '+data.propUnidadEmpresa.asociado.persona.pernombresV);
			$("#txtDirecPropietario").val(data.propUnidadEmpresa.asociado.persona.perdomicilioV);
			$("#txtTelefPropietario").val(data.propUnidadEmpresa.asociado.persona.perteleffijoV);
			$("#txtCelulPropietario").val(data.propUnidadEmpresa.asociado.persona.permovilmovV);

			$("#txtEmpresa").val(data.propUnidadEmpresa.asociado.empresa.emprazonsocialV);
			$("#txtEmprDireccion").val(data.propUnidadEmpresa.asociado.empresa.empdireccionV);
			$("#txtEmprTelefono").val(data.propUnidadEmpresa.asociado.empresa.emptelefono1V);
			$("#txtEmprCelular").val(data.propUnidadEmpresa.asociado.empresa.empcelularmovV);
		}
	}
	
	function llenarDatosConductor(data){
		if(data!=""){
			$('#sltConductor').combobox('autocomplete',data.concodigoD,data.persona.perpaternoV+' '+data.persona.permaternoV+', '+data.persona.pernombresV);
			$("#txtConductorNroLicencia").val(data.archivo.adjnumeroV);
			$("#txtEstadoLicencia").val(data.archivo.adjestadoV);
			$("#txtFechaEmision").val(data.archivo.adjfechaemisionF);
			$("#txtFechaCaducidad").val(data.archivo.adjfechacaducidadF);
			$("#txtConductorDNI").val(data.persona.perdniV);
			$("#txtNroLicencia").val(data.archivo.adjnumeroV);
			$("#txtDireccion").val(data.persona.perdomicilioV);
			$("#txtTelefono").val(data.persona.perteleffijoV);
			$("#txtCelular").val(data.persona.permovilmovV);
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
});