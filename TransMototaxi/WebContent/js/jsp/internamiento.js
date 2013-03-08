$(document).ready(function(){
	$("#divFormulario").dialog({
		title:"Conductores Mototaxis",
		width:1100,
		height: 600,
		modal: true
	});
	
	updateTime();
	setInterval(updateTime, 30000);
	$("#sltConductor").change(buscarConductorPorCodigo);
	$("#txtConductorDNI").keyup(buscarConductorPorDNI);
	$("#sltPlacas").change(buscarUnidadPorCodigo);
	$("#txtPapeleta").keyup(buscarPorPapeleta);
	
	function buscarPorPapeleta(){
		alert($(this).val().length);
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
            url: "Papeletas/BuscarUnidadPorCodigo.htm", 
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
			asocodigo_d=data.asociado.asocodigoD;
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
			asocodigo_d="0";
			
		}
	}
	
	function llenarDatosPapeleta(data){
		if(data!=""){
			$('#sltConductor').combobox('autocomplete',data.conductor.concodigoD,data.conductor.persona.perpaternoV+' '+data.conductor.persona.permaternoV+', '+data.conductor.persona.pernombresV);
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
	    $("#lblFecha").text(today_date);
	    $("#lblHora").text(hour+":"+minute);
	}
	
});