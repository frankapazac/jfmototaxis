$(document).ready(function(){   

    $("input[type=text]").keyup(function(){
	  $(this).val( $(this).val().toUpperCase() );
	});
	
	var codigoEmpresa="";
	$("#divFormulario").hide();
	$("#divUsuario").hide();
	$("#divPropietarios").hide();
	$(".dtFecha").datepicker({dateFormat:"dd/mm/yy"});
	buscar('EMP.EMPCODIGO_D','');
	
	$("#sltDepartamentos").change(departamento);
    $("#sltProvincia").mouseup(provincia);
    $("#btnProcesar").click(procesar);
	$("#btnUsuario").click(obtenerEmpresa);
	$("#btnImprimirEmpresasUsuarios").click(imprimirEmpresasUsuarios);
    /**/
	$("#btnRepProcesar").click(procesarUsuario);
	$("#btnRepImprimir").click(imprimirUsuario);
	$("#sltRepDepartamentos").change(rep_departamento);
    $("#sltRepProvincia").mouseup(rep_provincia);
	$("#sltPropDepartamentos").change(pro_departamento);
    $("#sltPropProvincia").mouseup(pro_provincia);
    $("#btnAgregarUsuario").click(agregarUsuario);
    $("#btnAgregarPropietarios").click(agregarPropietarios);
    $("#divFormularioPropietarios").hide();
    $("#btnNuevoPropietario").click(nuevoPropietario);
    $("#btnProcesarPropietario").click(procesarPropietario);
    $("#divNuevoRepresentante").hide();
    $("#divCesarRepresentante").hide();
    $("#btnNRepProcesar").click(procesarRepresentante);
	/**/
	$("#btnBuscar").click(function(){
    	buscar($("#sltCriterio").val(),$("#txtTexto").val());
    	$.message.Find();
    });
	
	$("#btnNuevo").click(function(){llenarFormulario("");});

	$("#btnCambiarRepresentante").click(function(){
		listarPropietariosDDL();
	});
	
	$("#btnCesarRepresentante").click(cesarRepresentante);
	
	$("#btnNRepCese").click(procesarCesar);
	$("#btnNRepCeseCancelar").click(function(){
		$("#divCesarRepresentante").dialog('close');
	});
	
	function cesarRepresentante(){
		if($("#txtRepreCodigo").val()=='0'){
			alert("Aun no existe representante");
			return;
		}
		$("#divCesarRepresentante").show();
    	$("#divCesarRepresentante").dialog({
    		title:"Cesar Representante",
    		width:850,
    		//height: 600,
    		modal: true
    	});
	}
	
	function procesarCesar(){
		$.ajax({
    		data:{
    			'repcodigoI': $("#txtRepreCodigo").val(),
    			'repobservaciones':$("#txtNRepObservacion").val(),
    			'repfechaceseF':$("#txtNRepFechaCese").val()
    		},
            datatype:'json',
            type: "POST", 
            url: "Empresas/ModificarRepresentante.htm", 
            success: function(data){
            	//success o error
            	if(data=='SUCCESS'){
            		$("#txtRepreCodigo").val(0);
            		$("#txtRepreDni").val("");
            		$("#txtRepreClaro").val("");
            		$("#txtRepreNombres").val("");
            		$("#txtRepreMovistar").val("");
            		$("#txtReprePaterno").val("");
            		$("#txtRepreNextel").val("");
            		$("#txtRepreMaterno").val("");
            		$("#txtRepreTelefono").val("");
            		$("#txtRepreNacimiento").val("");
            		$("#txtRepreDireccion").val("");
                	$("#divCesarRepresentante").dialog('close');	
            	}
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
	}
	
	function procesarRepresentante(){
		$("#divNuevoRepresentante").validate();
		$.ajax({
    		data:{
    			'repcodigoI': 0,
    			'empProp.eprcodigoD':$("#sltRepresentante").val(),
    			'empresa.empcodigoD':codigoEmpresa,
    			'repdescripcionV':$("#txtNRepDescripcion").val(),
    			'repfechainicioF':$("#txtNRepFecha").val()
    		},
            datatype:'json',
            type: "POST", 
            url: "Empresas/InsertarRepresentante.htm", 
            success: function(data){
            	//alert(JSON.stringify(data));
            	if(data.repcodigoI!=null){
                	$("#txtRepreCodigo").val(data.repcodigoI);
                	$("#txtRepreDni").val(data.empProp.persona.perdniV);
                	$("#txtRepreClaro").val(data.empProp.persona.permovilclaV);
                	$("#txtRepreNombres").val(data.empProp.persona.pernombresV);
                	$("#txtRepreMovistar").val(data.empProp.persona.permovilmovV);
                	$("#txtReprePaterno").val(data.empProp.persona.perpaternoV);
                	$("#txtRepreNextel").val(data.empProp.persona.permovilnexV);
                	$("#txtRepreMaterno").val(data.empProp.persona.permaternoV);
                	$("#txtRepreTelefono").val(data.empProp.persona.perteleffijoV);
                	$("#txtRepreNacimiento").val(data.empProp.persona.pernacimientoF);
                	$("#txtRepreDireccion").val(data.empProp.persona.perdomicilioV);
                	//buscar("EMP.EMPCODIGO_D", data);
                	$.message.Success();
                	$("#divNuevoRepresentante").dialog('close');
            	}
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
	}
	
	function listarPropietariosDDL(){
		$.ajax({ 
    		data:{
    			empcodigoD: codigoEmpresa
    		},
            datatype:'json',
            type: "GET", 
            url: "Empresas/ListarPropietariosDDL.htm", 
            success: function(data){
            	$("#sltRepresentante").empty();
            	for(var x=0;x<data.length;x++){
            		$("#sltRepresentante").append("<option value='"+data[x].eprcodigoD+"'>"+data[x].persona.pernombresV+"</option>");
            	}
            	//alert(JSON.stringify(data[0]));            	
            },error: function(jqXHR, textStatus, errorThrown){
            	$("body #divMenssage").remove();
    			$("body").append("<div id='divMenssage' class='ui-widget-content ui-corner-all'>" +
    					"<p>Ya existe un representante.</p>" +
						"<img src='images/Error32x32.png'>"+
    					"<input type='button' id='btnErrorAceptar' value='Aceptar' class='ui-button'/>"+
    					"</div>");
    			$("body #divMenssage").css({'width': '240px', 'height': '135px'});
    			$("body #divMenssage img").css({'padding-bottom': '10px','padding-right': '10px','float': 'right'});
    			$("body #divMenssage").dialog({
    				title:"Error",
    	    		modal: true
    	    	});
    			$("body #divMenssage #btnErrorAceptar").click(function(){
    				$("body #divMenssage").dialog("close");
    				$("#divNuevoRepresentante").dialog("close");
    			});
            }
    	});

		$("#divNuevoRepresentante").show();
    	$("#divNuevoRepresentante").dialog({
    		title:"Nuevo Representante",
    		width:850,
    		//height: 600,
    		modal: true
    	});	
	}
	
    $("#btnCancelar").click(function(){
    	$("#divFormulario").dialog("close");
    });
    $("#btnRepCancelar").click(function(){
    	$("#divUsuario").dialog("close");
    });
    
    function nuevoPropietario(){
    	llenarDataPropietario("");
    }
    
    $("#txtPropDni").keyup(function(){
		$("#txtPropPerCodigo").val("0");
		//$("#txtPropDni").val("");
		$("#txtPropClaro").val("");
		$("#txtPropNombres").val("");
		$("#txtPropMovistar").val("");
		$("#txtPropPaterno").val("");
		$("#txtPropNextel").val("");
		$("#txtPropMaterno").val("");
		$("#txtPropTelefono").val("");
		$("#txtPropNacimiento").val("");
		$("#txtPropDireccion").val("");
		$("#sltPropSexo").val("M");
		$("#sltPropDepartamentos").val("01");
		$("#sltPropEstadoCivil").val("S");
		$("#sltPropProvincia").html("<option value=''>-Seleccione-</option>");
		$("#txtPropCorreo").val("");
		$("#sltPropDistrito").html("<option value=''>-Seleccione-</option>");
    	if($.trim($(this).val()).length>=8){
    		$.ajax({
        		data:{
        			dni:$.trim($(this).val())
        		},
                datatype:'json',
                type: "GET", 
                url: "Persona/ObtenerPorDni.htm", 
                success: function(data){
                	if(data.percodigoD!=null){
                		$("#txtPropPerCodigo").val(data.percodigoD);
                		$("#txtPropDni").val(data.perdniV);
                		$("#txtPropClaro").val(data.permovilclaV);
                		$("#txtPropNombres").val(data.pernombresV);
                		$("#txtPropMovistar").val(data.permovilmovV);
                		$("#txtPropPaterno").val(data.perpaternoV);
                		$("#txtPropNextel").val(data.permovilnexV);
                		$("#txtPropMaterno").val(data.permaternoV);
                		$("#txtPropTelefono").val(data.perteleffijoV);
                		$("#txtPropNacimiento").val(data.pernacimientoF);
                		$("#txtPropDireccion").val(data.perdomicilioV);
                		$("#sltPropSexo").val(data.persexoC);
                		$("#sltPropDepartamentos").val(data.perubdptoV);
                		$("#sltPropEstadoCivil").val(data.perestadocivilC);
                		$("#sltPropProvincia").val(data.perubprovV);
                		$("#sltPropProvincia").html("<option value='"+data.perubprovV+"'>"+data.perubprovnombreV+"</option>");
                		$("#txtPropCorreo").val(data.peremailV);
                		$("#sltPropDistrito").val(data.perubidistV);
                		$("#sltPropDistrito").html("<option value='"+data.perubidistV+"'>"+data.perubidistnombreV+"</option>");
                    	$.message.Success();
                	}
                },error: function(jqXHR, textStatus, errorThrown){
                	$.message.Error();
                }
        	});
    	}
    });
    
    $("#txtRepDni").keyup(function(){
		$("#txtRepCodigo").val("0");
		//$("#txtRepDni").val("");
		$("#txtRepNombres").val("");
		$("#txtRepPaterno").val("");
		$("#txtRepMaterno").val("");
		$("#txtRepNacimiento").val("");
		$("#sltRepSexo").val("M");
		$("#sltRepEstadoCivil").val("S");
		$("#txtRepClaro").val("");
		$("#txtRepMovistar").val("");
		$("#txtRepNextel").val("");
		$("#txtRepTelefono").val("");
		$("#txtRepCorreo").val("");
		$("#txtRepDireccion").val("");
		$("#sltRepDepartamentos").val("15");
		$("#sltRepProvincia").append("<option value=''>-Seleccione-</option>");
		$("#sltRepDistrito").append("<option value=''>-Seleccione-</option>");
		if($.trim($(this).val()).length>=8){
    		$.ajax({
        		data:{
        			dni:$.trim($(this).val())
        		},
                datatype:'json',
                type: "GET", 
                url: "Persona/ObtenerPorDni.htm", 
                success: function(data){
                	if(data.percodigoD!=null){
                		$("#txtRepCodigo").val(data.percodigoD);
                		$("#txtRepDni").val(data.perdniV);
                		$("#txtRepNombres").val(data.pernombresV);
                		$("#txtRepPaterno").val(data.perpaternoV);
                		$("#txtRepMaterno").val(data.permaternoV);
                		$("#txtRepNacimiento").val(data.pernacimientoF);
                		$("#sltRepSexo").val(data.persexoC);
                		$("#sltRepEstadoCivil").val(data.perestadocivilC);
                		$("#txtRepClaro").val(data.permovilclaV);
                		$("#txtRepMovistar").val(data.permovilmovV);
                		$("#txtRepNextel").val(data.permovilnexV);
                		$("#txtRepTelefono").val(data.perteleffijoV);
                		$("#txtRepCorreo").val(data.peremailV);
                		$("#txtRepDireccion").val(data.perdomicilioV);
                		$("#sltRepDepartamentos").val(data.perubdptoV);
                		$("#sltRepProvincia").append("<option value='"+data.perubprovV+"'>"+data.perubprovnombreV+"</option>");
                		$("#sltRepDistrito").append("<option value='"+data.perubidistV+"'>"+data.perubidistnombreV+"</option>");
                		$("#sltRepProvincia").val(data.perubprovV);
                		$("#sltRepDistrito").val(data.perubidistV);
                    	$.message.Success();
                	}
                },error: function(jqXHR, textStatus, errorThrown){
                	$.message.Error();
                }
        	});
    	}
    });
    
    function llenarDataPropietario(data){
    	if(data != 0){
    		$("#txtPropCodigo").val(data.eprcodigoD);
    		$("#txtPropPerCodigo").val(data.persona.percodigoD);
    		$("#txtPropDni").val(data.persona.perdniV);
    		$("#txtPropClaro").val(data.persona.permovilclaV);
    		$("#txtPropNombres").val(data.persona.pernombresV);
    		$("#txtPropMovistar").val(data.persona.permovilmovV);
    		$("#txtPropPaterno").val(data.persona.perpaternoV);
    		$("#txtPropNextel").val(data.persona.permovilnexV);
    		$("#txtPropMaterno").val(data.persona.permaternoV);
    		$("#txtPropTelefono").val(data.persona.perteleffijoV);
    		$("#txtPropNacimiento").val(data.persona.pernacimientoF);
    		$("#txtPropDireccion").val(data.persona.perdomicilioV);
    		$("#sltPropSexo").val(data.persona.persexoC);
    		$("#sltPropDepartamentos").val(data.persona.perubdptoV);
    		$("#sltPropEstadoCivil").val(data.persona.perestadocivilC);
    		$("#sltPropProvincia").val(data.persona.perubprovV);
    		$("#sltPropProvincia").html("<option value='"+data.persona.perubprovV+"'>"+data.persona.perubprovnombreV+"</option>");
    		$("#txtPropCorreo").val(data.persona.peremailV);
    		$("#sltPropDistrito").val(data.persona.perubidistV);
    		$("#sltPropDistrito").html("<option value='"+data.persona.perubidistV+"'>"+data.persona.perubidistnombreV+"</option>");
    		$("#txtPropFechaInicio").val(data.eprfechainicioF);
    		$("#txtPropFechaFin").val(data.eprfechafinF);
    		$("#sltPropEstado").val(data.eprestadoC);
    		$("#txtObservaciones").val(data.eprobservacionesV);
    		$("#btnProcesarPropietario").val("Modificar");
    	}else{
    		$("#txtPropCodigo").val("0");
    		$("#txtPropPerCodigo").val("0");
    		$("#txtPropDni").val("");
    		$("#txtPropClaro").val("");
    		$("#txtPropNombres").val("");
    		$("#txtPropMovistar").val("");
    		$("#txtPropPaterno").val("");
    		$("#txtPropNextel").val("");
    		$("#txtPropMaterno").val("");
    		$("#txtPropTelefono").val("");
    		$("#txtPropNacimiento").val("");
    		$("#txtPropDireccion").val("");
    		$("#sltPropSexo").val("M");
    		$("#sltPropDepartamentos").val("01");
    		$("#sltPropEstadoCivil").val("S");
    		$("#sltPropProvincia").html("<option value=''>-Seleccione-</option>");
    		$("#txtPropCorreo").val("");
    		$("#sltPropDistrito").html("<option value=''>-Seleccione-</option>");
    		$("#txtPropFechaInicio").val("");
    		$("#txtPropFechaFin").val("");
    		$("#sltPropEstado").val("A");
    		$("#txtObservaciones").val("");
    		$("#btnProcesarPropietario").val("Guardar");
    	}
    	$("#divFormularioPropietarios").show();
    	$("#divFormularioPropietarios").dialog({
    		title:"Propietario",
    		width:850,
    		//height: 600,
    		modal: true
    	});
    }
    
    function procesarPropietario(){
    	$("#divFormularioPropietarios").validate();
		$.ajax({
    		data:{
    			'persona.percodigoD':$("#txtPropPerCodigo").val(),
    			'persona.perdniV':$("#txtPropDni").val(),
    			'persona.pernombresV':$("#txtPropNombres").val(),
    			'persona.perpaternoV':$("#txtPropPaterno").val(),
    			'persona.permaternoV':$("#txtPropMaterno").val(),
    			'persona.pernacimientoF':$("#txtPropNacimiento").val(),
    			'persona.perestadocivilC':$("#sltPropEstadoCivil").val(),
    			'persona.permovilclaV':$("#txtPropClaro").val(),
    			'persona.permovilmovV':$("#txtPropMovistar").val(),
    			'persona.permovilnexV':$("#txtPropNextel").val(),
    			'persona.perteleffijoV':$("#txtPropTelefono").val(),
    			'persona.peremailV':$("#txtPropCorreo").val(),
    			'persona.perdomicilioV':$("#txtPropDireccion").val(),
    			'persona.perubdptoV':$("#sltPropDepartamentos").val(),
    			'persona.perubprovV':$("#sltPropProvincia").val(),
    			'persona.perubidistV':$("#sltPropDistrito").val(),
    			'persona.persexoC':$("#sltPropSexo").val(),
    			'eprcodigoD':$("#txtPropCodigo").val(),
    			'empresa.empcodigoD':codigoEmpresa,
    			'eprfechainicioF':$("#txtPropFechaInicio").val(),
    			'eprfechafinF':$("#txtPropFechaFin").val(),
    			'eprestadoC':$("#sltPropEstado").val(),
    			'eprobservacionesV':$("#txtObservaciones").val()
    		},
            datatype:'json',
            type: "POST", 
            url: "Empresas/InsertarPropietario.htm", 
            success: function(data){
            	//alert(JSON.stringify(data));
            	buscarPropietarios(codigoEmpresa);
            	//buscar("EMP.EMPCODIGO_D", data);
            	$.message.Success();
            	$("#divFormularioPropietarios").dialog('close');
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
    }
    $("#btnCancelarPropietario").click(function(){$("#divFormularioPropietarios").dialog('close');});
    
	function buscar(criterio,texto){
    	$.ajax({ 
    		data:{
    			criterio:criterio,
    			texto:texto
    		},
            datatype:'json',
            type: "POST", 
            url: "Empresas/Listar.htm", 
            success: function(data){
            	//alert(JSON.stringify(data));
            	llenarTabla(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
    }
	
	function procesar(){
		$("#divFormulario").validate();
		$.ajax({
    		data:{
    			'empcodigoD':$("#txtCodigo").val(),
    			'zona.zoncodigo_I':$("#sltZona").val(),
    			'emprazonsocialV':$("#txtRazon").val(),
    			'empdireccionV':$("#txtDireccion").val(),
    			'emprucV':$("#txtRuc").val(),
    			'emptelefono1V':$("#txtTelefono1").val(),
    			'emptelefono2V':$("#txtTelefono2").val(),
    			'empcelularmovV':$("#txtMovistar").val(),
    			'empcelularclaV':$("#txtClaro").val(),
    			'empcelularnexV':$("#txtNextel").val(),
    			'empmailV':$("#txtCorreo").val(),
    			'emppagwebV':$("#txtPaginaWeb").val(),
    			'distrito.idubigeo':$("#sltDistrito").val(),
    			'provincia.idubigeo':$("#sltProvincia").val(),
    			'departamento.idubigeo':$("#sltDepartamentos").val(),
    			'empfechainioperacionF':$("#txtInicio").val(),
    			'empfechaceseoperacionF':$("#txtCese").val(),
    			'empresolucionV':$("#txtResolucion").val(),
    			'empunidadesautorizadasI':$("#txtUnidades").val(),
    			'empescriturapublicaV':$("#txtEscritura").val()
    		},
            datatype:'json',
            type: "POST", 
            url: "Empresas/Procesar.htm", 
            success: function(data){
            	buscar("EMP.EMPCODIGO_D", data);
            	$.message.Success();
            	$("#divFormulario").dialog('close');
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	function imprimirUsuario(){
		//var codigo=$("#txtRepCodigoUsuario").val();
    	window.open("Empresas/ImprimirSobreUsuario.htm?codigo="+codigoEmpresa);
	}
	
	function imprimirEmpresasUsuarios(){
		window.open("Empresas/ImprimirEmpresasUsuarios.htm");
	}
	
	function procesarUsuario(){
		$("#divUsuario").validate();
		$.ajax({
    		data:{
    			'usucodigoI':$("#txtRepCodigoUsuario").val(),
    			'usuusuarioV':$("#txtRepUsuario").val(),
    			'empresa.empcodigoD': codigoEmpresa,
    			'pass':$("#txtRepClave").val(),
    			'persona.percodigoD':$("#txtRepCodigo").val(),
    			'persona.perdniV':$("#txtRepDni").val(),
    			'persona.pernombresV':$("#txtRepNombres").val(),
    			'persona.perpaternoV':$("#txtRepPaterno").val(),
    			'persona.permaternoV':$("#txtRepMaterno").val(),
    			'persona.pernacimientoF':$("#txtRepNacimiento").val(),
    			'persona.persexoC':$("#sltRepSexo").val(),
    			'persona.perestadocivilC':$("#sltRepEstadoCivil").val(),
    			'persona.peremailV':$("#txtRepCorreo").val(),
    			'persona.permovilclaV':$("#txtRepClaro").val(),
    			'persona.permovilmovV':$("#txtRepMovistar").val(),
    			'persona.permovilnexV':$("#txtRepNextel").val(),
    			'persona.perteleffijoV':$("#txtRepTelefono").val(),
    			'persona.perdomicilioV':$("#txtRepDireccion").val(),
    			'persona.perubdptoV':$("#sltRepDepartamentos").val(),
    			'persona.perubprovV':$("#sltRepProvincia").val(),
    			'persona.perubidistV':$("#sltRepDistrito").val()
    		},
            datatype:'json',
            type: "POST", 
            url: "Empresas/InsertarUsuario.htm", 
            success: function(data){
            	$.message.Success();
            	$("#divUsuario").dialog('close');
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
			+"<th class='header'>ZONA</th>"
			+"<th class='header'>RAZON SOCIAL</th>"
			+"<th class='header'>DIRECCIÓN</th>"
			+"<th class='header'>RUC</th>"
			+"<th class='header'>TELEFONO</th>"
			+"<th class='header'>UNIDADES</th>"
			+"<th class='header'>INICIO</th>"
			+"<th class='header'>CESE</th>"
			+"<th class='header'></th>"
			+"<th class='header'></th>"
			+"<th class='header'></th>"
			+"<th class='header'></th>"
			+"<th class='header'></th>"
			+"</thead>"
			+"<tfoot>"
			+"<th>N°</th>"
			+"<th>ZONA</th>"
			+"<th>RAZON SOCIAL</th>"
			+"<th>DIRECCIÓN</th>"
			+"<th>RUC</th>"
			+"<th>TELEFONO</th>"
			+"<th>UNIDADES</th>"
			+"<th>INICIO</th>"
			+"<th>CESE</th>"
			+"<th></th>"
			+"<th></th>"
			+"<th></th>"
			+"<th></th>"
			+"<th></th>"
			+"</tfoot>"
			+"<tbody></tbody>";
			$("#tblLista").append(txtHtml);
    	for(var x=0;x<data.length;x++){
    		txtHtml="<tr class='trEmpresa' id='trEmp"+data[x].empcodigoD+"'>"+
			"<td>"+(x+1)+"</td>"+
			"<td>"+data[x].zona.zonnombre_V+"</td>"+
			"<td>"+data[x].emprazonsocialV+"</td>"+
			"<td>"+data[x].empdireccionV+"</td>"+
			"<td>"+data[x].emprucV+"</td>"+
			"<td>"+data[x].emptelefono1V+"  //  "+data[x].emptelefono1V+"</td>"+
			"<td>"+data[x].empunidadesautorizadasI+"</td>"+
			"<td>"+data[x].empfechainioperacionF+"</td>"+
			"<td>"+data[x].empfechaceseoperacionF+"</td>"+
			"<td>"+data[x].empestadoC+"</td>"+
			"<td><img alt='Representante' class='btnPropietario' id='pro"+data[x].empcodigoD+"' src='images/aim_protocol.png'></td>"+
			"<td><img alt='Usuario' class='btnUsuario' id='usu"+data[x].empcodigoD+"' src='images/add_user.png'></td>"+
			"<td><img alt='Modificar' class='btnModificar' id='mod"+data[x].empcodigoD+"' src='images/edit.png'></td>"+
			"<td><img alt='Eliminar' class='btnEliminar' id='del"+data[x].empcodigoD+"' src='images/delete.png'></td>"+
    				"</tr>";
    		$("#tblLista tbody").append(txtHtml);
    	}
		$(".trEmpresa").click(obtenerUsuario);
    	$(".btnModificar").click(obtener);
    	//$(".btnEliminar").click(eliminar);
    	$(".btnPropietario").click(propietarios);
    	$(".btnUsuario").click(usuario);
    	$("#tblLista").paginacion();
    }
	
	function agregarUsuario(){
		if($.trim(codigoEmpresa).length < 1 || codigoEmpresa<1){
			$.message.Error();
			return;
		}
		
		$.ajax({
    		data:{
    			codigo:codigoEmpresa
    		},
            datatype:'json',
            type: "GET", 
            url: "Empresas/ObtenerUsuario.htm", 
            success: function(data){
        		llenarUsuarioData(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
		$("#divUsuario").show();
    	$("#divUsuario").dialog({
    		title:"Usuario",
    		width:850,
    		//height: 600,
    		modal: true
    	});
	}
	
	function agregarPropietarios(){
		if($.trim(codigoEmpresa).length < 1 || codigoEmpresa<1){
			$.message.Error();
			return;
		}
		buscarRepresentante(codigoEmpresa);
		buscarPropietarios(codigoEmpresa);
	}
	
	$("#btnBuscarPropietarios").click(function(){
		buscarPropietarios(codigoEmpresa);
	});
	
	function propietarios(){
		buscarRepresentante($(this).attr("id").replace("pro",""));
		buscarPropietarios($(this).attr("id").replace("pro",""));
	}
	
	function buscarRepresentante(codigo){
		$("#txtRepreCodigo").val(0);
		$("#txtRepreDni").val("");
		$("#txtRepreClaro").val("");
		$("#txtRepreNombres").val("");
		$("#txtRepreMovistar").val("");
		$("#txtReprePaterno").val("");
		$("#txtRepreNextel").val("");
		$("#txtRepreMaterno").val("");
		$("#txtRepreTelefono").val("");
		$("#txtRepreNacimiento").val("");
		$("#txtRepreDireccion").val("");
		$.ajax({ 
    		data:{
    			empcodigoD:codigo
    		},
            datatype:'json',
            type: "GET", 
            url: "Empresas/ObtenerRepresentante.htm", 
            success: function(data){
            	if(data.repcodigoI!=null){
            		$("#txtRepreCodigo").val(data.repcodigoI);
            		$("#txtRepreDni").val(data.empProp.persona.perdniV);
                	$("#txtRepreClaro").val(data.empProp.persona.permovilclaV);
                	$("#txtRepreNombres").val(data.empProp.persona.pernombresV);
                	$("#txtRepreMovistar").val(data.empProp.persona.permovilmovV);
                	$("#txtReprePaterno").val(data.empProp.persona.perpaternoV);
                	$("#txtRepreNextel").val(data.empProp.persona.permovilnexV);
                	$("#txtRepreMaterno").val(data.empProp.persona.permaternoV);
                	$("#txtRepreTelefono").val(data.empProp.persona.perteleffijoV);
                	$("#txtRepreNacimiento").val(data.empProp.persona.pernacimientoF);
                	$("#txtRepreDireccion").val(data.empProp.persona.perdomicilioV);
            	}
            	//llenarTablaPropietarios(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
	}
	
	function buscarPropietarios(codigo){
		$.ajax({ 
    		data:{
    			empCodigoD:codigo,
    			criterio:$("#sltCriterioPropietarios").val(),
    			texto:$("#txtTextoPropietarios").val(),
    			estado:'A'
    		},
            datatype:'json',
            type: "POST", 
            url: "Empresas/ListarPropietarios.htm", 
            success: function(data){
            	llenarTablaPropietarios(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});

		$("#divPropietarios").show();
    	$("#divPropietarios").dialog({
    		title:"Propietarios",
    		width:850,
    		//height: 600,
    		modal: true
    	});	
		
	}
	
	function llenarTablaPropietarios(data){
    	if(data==""){ 
    		$("#tblListaPropietarios").empty();
    		return;
    	}
    	var txtHtml="";
    	$("#tblListaPropietarios").empty();
    	txtHtml="<thead style='background-color:gainsboro'>"
			+"<th class='header'>N°</th>"
			+"<th class='header'>PROPIETARIO</th>"
			+"<th class='header'>DNI</th>"
			+"<th class='header'>INICIO</th>"
			+"<th class='header'>CESE</th>"
			+"<th class='header'>ESTADO</th>"
			+"<th class='header'></th>"
			+"</thead>"
			+"<tfoot style='background-color:gainsboro'>"
			+"<th>N°</th>"
			+"<th>PROPIETARIO</th>"
			+"<th>DNI</th>"
			+"<th>INICIO</th>"
			+"<th>CESE</th>"
			+"<th>ESTADO</th>"
			+"<th></th>"
			+"</tfoot>"
			+"<tbody></tbody>";
			$("#tblListaPropietarios").append(txtHtml);
    	for(var x=0;x<data.length;x++){
    		txtHtml="<tr>"+
			"<td>"+(x+1)+"</td>"+
			"<td style='padding-right: 40px'>"+data[x].persona.pernombresV+"</td>"+
			"<td style='padding-right: 40px'>"+data[x].persona.perdniV+"</td>"+
			"<td style='padding-right: 40px'>"+data[x].eprfechainicioF+"</td>"+
			"<td style='padding-right: 40px'>"+data[x].eprfechafinF+"</td>"+
			"<td style='padding-right: 40px'>"+data[x].eprestadoC+"</td>"+
			"<td><img alt='Modificar' class='btnModificarPropietario' id='epr"+data[x].eprcodigoD+"' src='images/edit.png'></td>"+
    				"</tr>";
    		$("#tblListaPropietarios tbody").append(txtHtml);
    	}
		$(".btnModificarPropietario").click(editarPropietario);
    	//$("#divListaPropietarios").paginacion("pagerPropietarios");
    }
	
	function editarPropietario(){
		$.ajax({
    		data:{
    			codigo:$(this).attr("id").replace("epr","")
    		},
            datatype:'json',
            type: "GET", 
            url: "Empresas/ObtenerPropietario.htm", 
            success: function(data){
            	llenarDataPropietario(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
	}
	
	function usuario(){
		codigoEmpresa=$(this).attr("id").replace("usu","");
		$.ajax({
    		data:{
    			codigo:$(this).attr("id").replace("usu","")
    		},
            datatype:'json',
            type: "GET", 
            url: "Empresas/ObtenerUsuario.htm", 
            success: function(data){
        		llenarUsuarioData(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
		$("#divUsuario").show();
    	$("#divUsuario").dialog({
    		title:"Usuario",
    		width:850,
    		//height: 600,
    		modal: true
    	});		
	}
	
	function llenarUsuarioData(data){
		if(data!=""){
    		$("#txtRepCodigoUsuario").val(data.usucodigoI);
    		$("#txtRepUsuario").val(data.usuusuarioV);
    		//$("#txtRepClave").val($.trim(data.pass).length<1?'123456':data.pass);
    		$("#txtRepClave").val(data.pass);
    		$("#txtRepCodigo").val(data.persona.percodigoD);
    		$("#txtRepDni").val(data.persona.perdniV);
    		$("#txtRepNombres").val(data.persona.pernombresV);
    		$("#txtRepPaterno").val(data.persona.perpaternoV);
    		$("#txtRepMaterno").val(data.persona.permaternoV);
    		$("#txtRepNacimiento").val(data.persona.pernacimientoF);
    		$("#sltRepSexo").val(data.persona.persexoC);
    		$("#sltRepEstadoCivil").val(data.persona.perestadocivilC);
    		$("#txtRepClaro").val(data.persona.permovilclaV);
    		$("#txtRepMovistar").val(data.persona.permovilmovV);
    		$("#txtRepNextel").val(data.persona.permovilnexV);
    		$("#txtRepTelefono").val(data.persona.perteleffijoV);
    		$("#txtRepCorreo").val(data.persona.peremailV);
    		$("#txtRepDireccion").val(data.persona.perdomicilioV);
    		$("#sltRepDepartamentos").val(data.persona.perubdptoV);
    		$("#sltRepProvincia").append("<option value='"+data.persona.perubprovV+"'>"+data.persona.perubprovnombreV+"</option>");
    		$("#sltRepDistrito").append("<option value='"+data.persona.perubidistV+"'>"+data.persona.perubidistnombreV+"</option>");
    		$("#sltRepProvincia").val(data.persona.perubprovV);
    		$("#sltRepDistrito").val(data.persona.perubidistV);
    	}else{
    		$("#txtRepCodigoUsuario").val("0");
    		$("#txtRepCodigo").val("0");
    		$("#txtRepUsuario").val("");
    		$("#txtRepClave").val("123456");
    		$("#txtRepDni").val("");
    		$("#txtRepNombres").val("");
    		$("#txtRepPaterno").val("");
    		$("#txtRepMaterno").val("");
    		$("#txtRepNacimiento").val("");
    		$("#sltRepSexo").val("");
    		$("#sltRepEstadoCivil").val("");
    		$("#txtRepClaro").val("");
    		$("#txtRepMovistar").val("");
    		$("#txtRepNextel").val("");
    		$("#txtRepTelefono").val("");
    		$("#txtRepCorreo").val("");
    		$("#txtRepDireccion").val("");
    		$("#sltRepDepartamentos").val("");
    		$("#sltRepProvincia").html("<option value=''>Seleccione</option>");
    		$("#sltRepDistrito").html("<option value=''>Seleccione</option>");
    	}
	}
	
	function obtener(){
		$.ajax({
    		data:{
    			codigo:$(this).attr("id").replace("mod","")
    		},
            datatype:'json',
            type: "GET", 
            url: "Empresas/Obtener.htm", 
            success: function(data){
            	//alert(JSON.stringify(data));
            	llenarFormulario(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
	}
	
	function obtenerUsuario(){
		codigoEmpresa=$(this).attr('id').replace("trEmp","");
	}
	
	function obtenerEmpresa(){
		$.ajax({
    		data:{
    			codigo:codigoEmpresa
    		},
            datatype:'json',
            type: "GET", 
            url: "Empresas/Obtener.htm", 
            success: function(data){
            	alert(JSON.stringify(data));
            	//llenarFormulario(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
	}
	
	function llenarFormulario(data){
    	if(data!=""){
    		$("#txtCodigo").val(data.empcodigoD);
			$("#sltZona").val(data.zona.zoncodigo_I);
			$("#txtRazon").val(data.emprazonsocialV);
			$("#txtDireccion").val(data.empdireccionV);
			$("#txtRuc").val(data.emprucV);
			$("#txtTelefono1").val(data.emptelefono1V);
			$("#txtTelefono2").val(data.emptelefono2V);
			$("#txtMovistar").val(data.empcelularmovV);
			$("#txtClaro").val(data.empcelularclaV);
			$("#txtNextel").val(data.empcelularnexV);
			$("#txtCorreo").val(data.empmailV);
			$("#txtPaginaWeb").val(data.emppagwebV);
			$("#sltDistrito").html("<option value='"+data.distrito.idubigeo+"'>"+data.distrito.nombubigeo+"</option>");
			$("#sltProvincia").html("<option value='"+data.provincia.idubigeo+"'>"+data.provincia.nombubigeo+"</option>");
			$("#sltDepartamentos").val(data.departamento.idubigeo);
			$("#txtInicio").val(data.empfechainioperacionF);
			$("#txtCese").val(data.empfechaceseoperacionF);
			$("#txtResolucion").val(data.empresolucionV);
			$("#txtUnidades").val(data.empunidadesautorizadasI);
			$("#txtEscritura").val(data.empescriturapublicaV);
    	}else{
    		$(".error").remove();
    		$("#txtCodigo").val("0");
			$("#sltZona").val("");
			$("#txtRazon").val("");
			$("#txtDireccion").val("");
			$("#txtRuc").val("");
			$("#txtTelefono1").val("");
			$("#txtTelefono2").val("");
			$("#txtMovistar").val("");
			$("#txtClaro").val("");
			$("#txtNextel").val("");
			$("#txtCorreo").val("");
			$("#txtPaginaWeb").val("");
			$("#sltDepartamento").val("");
			$("#sltDistrito").html("<option value='0'>Seleccione</option>");
			$("#sltProvincia").html("<option value='0'>Seleccione</option>");
			$("#sltDepartamentos").val("");
			$("#txtInicio").val("");
			$("#txtCese").val("");
			$("#txtResolucion").val("");
			$("#txtUnidades").val("");
			$("#txtEscritura").val("");
        	
        	$("#btnProcesar").val("Guardar");
    	}
    	$("#divFormulario").show();
    	$("#divFormulario").dialog({
    		title:"Empresa",
    		width:850,
    		//height: 600,
    		modal: true
    	});
    }
	
	function departamento(){
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
    }
    
    function provincia(){
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
    }


	function rep_departamento(){
    	$.ajax({
    		data:{
    			idubigeo:$("#sltRepDepartamentos").val()
    		},
            datatype:'json',
            type: "POST", 
            url: "Ubigeo/Provincia.htm", 
            success: function(data){
            	rep_llenarComboProvincia(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
    }
    
    function rep_provincia(){
    	$.ajax({ 
    		data:{
    			idubigeo:$("#sltRepProvincia").val()
    		},
            datatype:'json',
            type: "POST", 
            url: "Ubigeo/Distrito.htm", 
            success: function(data){
            	rep_llenarComboDistrito(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
    }

	function pro_departamento(){
    	$.ajax({
    		data:{
    			idubigeo:$("#sltPropDepartamentos").val()
    		},
            datatype:'json',
            type: "POST", 
            url: "Ubigeo/Provincia.htm", 
            success: function(data){
            	pro_llenarComboProvincia(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
    }
    
    function pro_provincia(){
    	$.ajax({ 
    		data:{
    			idubigeo:$("#sltPropProvincia").val()
    		},
            datatype:'json',
            type: "POST", 
            url: "Ubigeo/Distrito.htm", 
            success: function(data){
            	pro_llenarComboDistrito(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
    }
    
    function llenarComboProvincia(data){
    	$("#sltProvincia").empty();
    	txtHtml="";
    	for(var x=0;x<data.length;x++){
    		txtHtml+="<option value='"+data[x].idubigeo+"'>"+data[x].nombubigeo+"</option>";
    	}
    	$("#sltProvincia").append(txtHtml);
    }
    
    function llenarComboDistrito(data){
    	$("#sltDistrito").empty();
    	txtHtml="";
    	for(var x=0;x<data.length;x++){
    		txtHtml+="<option value='"+data[x].idubigeo+"'>"+data[x].nombubigeo+"</option>";
    	}
    	$("#sltDistrito").append(txtHtml);
    }
    
    function rep_llenarComboProvincia(data){
    	$("#sltRepProvincia").empty();
    	txtHtml="";
    	for(var x=0;x<data.length;x++){
    		txtHtml+="<option value='"+data[x].idubigeo+"'>"+data[x].nombubigeo+"</option>";
    	}
    	$("#sltRepProvincia").append(txtHtml);
    }
    
    function rep_llenarComboDistrito(data){
    	$("#sltRepDistrito").empty();
    	txtHtml="";
    	for(var x=0;x<data.length;x++){
    		txtHtml+="<option value='"+data[x].idubigeo+"'>"+data[x].nombubigeo+"</option>";
    	}
    	$("#sltRepDistrito").append(txtHtml);
    }
    
    function pro_llenarComboProvincia(data){
    	$("#sltPropProvincia").empty();
    	txtHtml="";
    	for(var x=0;x<data.length;x++){
    		txtHtml+="<option value='"+data[x].idubigeo+"'>"+data[x].nombubigeo+"</option>";
    	}
    	$("#sltPropProvincia").append(txtHtml);
    }
    
    function pro_llenarComboDistrito(data){
    	$("#sltPropDistrito").empty();
    	txtHtml="";
    	for(var x=0;x<data.length;x++){
    		txtHtml+="<option value='"+data[x].idubigeo+"'>"+data[x].nombubigeo+"</option>";
    	}
    	$("#sltPropDistrito").append(txtHtml);
    }
});