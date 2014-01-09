$(document).ready(function(){   

    $("input[type=text]").keyup(function(){
	  $(this).val( $(this).val().toUpperCase() );
	});
	
	var codigoEmpresa="";
	$("#divFormulario").hide();
	$("#divUsuario").hide();
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
	/**/
	$("#btnBuscar").click(function(){
    	buscar($("#sltCriterio").val(),$("#txtTexto").val());
    	$.message.Find();
    });
	
	$("#btnNuevo").click(function(){llenarFormulario("");});

    $("#btnCancelar").click(function(){
    	$("#divFormulario").dialog("close");
    });
    $("#btnRepCancelar").click(function(){
    	$("#divUsuario").dialog("close");
    });
    
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
			"<td><img alt='Usuario' class='btnUsuario' id='usu"+data[x].empcodigoD+"' src='images/add_user.png'></td>"+
			"<td><img alt='Modificar' class='btnModificar' id='mod"+data[x].empcodigoD+"' src='images/edit.png'></td>"+
			"<td><img alt='Eliminar' class='btnEliminar' id='del"+data[x].empcodigoD+"' src='images/delete.png'></td>"+
    				"</tr>";
    		$("#tblLista tbody").append(txtHtml);
    	}
		$(".trEmpresa").click(obtenerUsuario);
    	$(".btnModificar").click(obtener);
    	//$(".btnEliminar").click(eliminar);
    	$(".btnUsuario").click(usuario);
    	$("#tblLista").paginacion();
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
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
		$("#divUsuario").show();
    	$("#divUsuario").dialog({
    		title:"Usuario",
    		width:1100,
    		//height: 600,
    		modal: true
    	});		
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
    		width:1100,
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
});