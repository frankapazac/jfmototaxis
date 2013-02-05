$(document).ready(function(){       
    $("#divFormulario").hide();
    $("#divMensaje").hide();
    $("#tabs").tabs();
    $("#dtNacimiento").datepicker({dateFormat:"dd/mm/yy"});
    $(".dtFecha").datepicker({dateFormat:"dd/mm/yy"});
    
    buscar("PER.PERCODIGO_D","");
    
    //ACCIONES
    $("#btnBuscar").click(function(){
    	buscar($("#sltCriterio").val(),$("#txtTexto").val());
    });
    
    $("#btnNuevo").click(function(){
    	llenarFormulario("");
    });

    $("#btnCancelar").click(function(){
    	$("#divFormulario").dialog("close");
    });

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
            	mensajeError();
            }
    	});
    });

    $("#sltProvincia").change(function(){
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
            	mensajeError();
            }
    	});
    });
    
    $("#btnProcesar").click(function(){
		$.ajax({ 
			data:{
				'asocodigoD':$("#txtCodigoAsociado").val(),
				'asorazonsocialV':encodeURIComponent($("#txtRazonSocial").val()),
				'asorucV':$("#txtRuc").val(),
				'persona.percodigoD':$("#txtCodigoPersona").val(),
				'persona.perdniV':$("#txtDni").val(),
				'persona.perdomicilioV':encodeURIComponent($("#txtDireccion").val()),
				'persona.peremailV':encodeURIComponent($("#txtCorreo").val()),
				'persona.perestadocivilC':encodeURIComponent($("#sltEstadoCivil").val()),
				'persona.permaternoV':encodeURIComponent($("#txtMaterno").val()),
				'persona.permovilclaV':$("#txtClaro").val(),
				'persona.permovilmovV':$("#txtMovistar").val(),
				'persona.permovilnexV':$("#txtNextel").val(),
				'persona.pernacimientoF':$("#dtNacimiento").val(),
				'persona.pernombresV':encodeURIComponent($("#txtNombres").val()),
				'persona.perpaternoV':encodeURIComponent($("#txtPaterno").val()),
				'persona.persexoC':encodeURIComponent($("#sltSexo").val()),
				'persona.perteleffijoV':$("#txtTelefono").val(),
				'persona.perubdptoV':encodeURIComponent($("#sltDepartamentos").val()),
				'persona.perubidistV':encodeURIComponent($("#sltDistrito").val()),
				'persona.perubprovV':encodeURIComponent($("#sltProvincia").val()),
			},
		    datatype:'json',
	        type: "POST", 
	        url: "Asociados/Procesar.htm", 
	        success: function(data){
	        	$("#txtCodigoAsociado").val(data.asocodigoD);
	        	$("#txtCodigoPersona").val(data.persona.percodigoD);
	        	txtHtml="<p>Operación realizada correctamente</p>";
	        	mensaje(txtHtml);
	        }/*,error: function(jqXHR, textStatus, errorThrown){
	        	mensajeError();
	        }*/
		});
		//$("#divFormulario").dialog("close");
		buscar("ASO.ASOCODIGO_D",$("#txtCodigoAsociado").val());
	});
    
	//FUNCIONES    
    function buscar(criterio,texto){
    	$.ajax({ 
    		data:{
    			criterio:criterio,
    			texto:texto
    		},
            datatype:'json',
            type: "POST", 
            url: "Asociados/Listar.htm", 
            success: function(data){
            	//alert(JSON.stringify(data));
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
			+"<th class='header'>NUM</th>"
			+"<th class='header'>DNI</th>"
			+"<th class='header'>NOMBRE</th>"
			+"<th class='header'>PATERNO</th>"
			+"<th class='header'>MATERNO</th>"
			+"<th class='header'>NACIMIENTO</th>"
			+"<th class='header'>FIJO</th>"
			+"<th class='header'>CORREO</th>"
			+"<th class='header'>ESTADO</th>"
			+"<th class='header'>MODIFICAR</th>"
			+"<th class='header'>ELIMINAR</th>"
			+"</thead>"
			+"<tfoot>"
			+"<th>NUM</th>"
			+"<th>DNI</th>"
			+"<th>NOMBRE</th>"
			+"<th>PATERNO</th>"
			+"<th>MATERNO</th>"
			+"<th>NACIMIENTO</th>"
			+"<th>FIJO</th>"
			+"<th>CORREO</th>"
			+"<th>ESTADO</th>"
			+"<th>MODIFICAR</th>"
			+"<th>ELIMINAR</th>"
			+"</tfoot>"
			+"<tbody></tbody>";
			$("#tblLista").append(txtHtml);
    	for(var x=0;x<data.length;x++){
    		txtHtml="<tr>"+
			"<td>"+(x+1)+"</td>"+
			"<td>"+data[x].persona.perdniV+"</td>"+
			"<td>"+data[x].persona.pernombresV+"</td>"+
			"<td>"+data[x].persona.perpaternoV+"</td>"+
			"<td>"+data[x].persona.permaternoV+"</td>"+
			"<td>"+data[x].persona.pernacimientoF+"</td>"+
			"<td>"+data[x].persona.perteleffijoV+"</td>"+
			"<td>"+data[x].persona.peremailV+"</td>"+
			"<td>"+data[x].asoestadoC+"</td>"+
			"<td><img alt='Modificar' class='btnModificar' id='mod"+data[x].persona.percodigoD+"' src='images/edit.png'></td>"+
			"<td><img alt='Eliminar' class='btnEliminar' id='del"+data[x].persona.percodigoD+"' src='images/delete.png'></td>"+
    				"</tr>";
    		$("#tblLista tbody").append(txtHtml);
    	}
		
    	$(".btnModificar").click(modificar);
    	$(".btnEliminar").click(eliminar);
    	paginacion();
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
    
    function llenarFormulario(data){
    	if(data!=""){
    		//alert(JSON.stringify(data));
    		$("#txtCodigoAsociado").val(data.asociado.asocodigoD);
    		$("#txtCodigoPersona").val(data.asociado.persona.percodigoD);
        	$("#txtNombres").val(data.asociado.persona.pernombresV);
        	$("#txtPaterno").val(data.asociado.persona.perpaternoV);
        	$("#txtMaterno").val(data.asociado.persona.permaternoV);
        	$("#txtDni").val(data.asociado.persona.perdniV);
        	$("#txtRuc").val(data.asociado.asorucV);
        	$("#txtRazonSocial").val(data.asociado.asorazonsocialV);
        	$("#sltEstadoCivil").val(data.asociado.persona.perestadocivilC);
        	$("#sltSexo").val(data.asociado.persona.persexoC);
        	$("#dtNacimiento").val(data.asociado.persona.pernacimientoF);
        	$("#txtDireccion").val(data.asociado.persona.perdomicilioV);
        	$("#sltDepartamentos").val(data.asociado.persona.perubdptoV);
        	$("#sltProvincia").empty();
        	$("#sltProvincia").append("<option value='"+data.asociado.persona.perubprovV+"'>"+data.asociado.persona.perubprovnombreV+"</option>");
        	$("#sltDistrito").empty();
        	$("#sltDistrito").append("<option value='"+data.asociado.persona.perubidistV+"'>"+data.asociado.persona.perubidistnombreV+"</option>");
        	$("#txtCorreo").val(data.asociado.persona.peremailV);
        	$("#txtTelefono").val(data.asociado.persona.perteleffijoV);
        	$("#txtMovistar").val(data.asociado.persona.permovilmovV);
        	$("#txtClaro").val(data.asociado.persona.permovilclaV);
        	$("#txtNextel").val(data.asociado.persona.permovilnexV);
        	//alert("LENGTH: "+data.listDocumentos.length);
        	for(var x=0;x<data.listDocumentos.length;x++){
        		$("#documento_"+data.listDocumentos[x].tipoDocumento.mtdcodigoI).val(data.listDocumentos[x].archivo.adjcodigoD);
        		$("#txtNumDocumento_"+data.listDocumentos[x].tipoDocumento.mtdcodigoI).val(data.listDocumentos[x].archivo.adjnumeroV);
        		$("#txtFechaEmision_"+data.listDocumentos[x].tipoDocumento.mtdcodigoI).val(data.listDocumentos[x].archivo.adjfechaemisionF);
        		$("#txtFechaCaducidad_"+data.listDocumentos[x].tipoDocumento.mtdcodigoI).val(data.listDocumentos[x].archivo.adjfechacaducidadF);
        		//$("#fileDocumento_"+data.listDocumentos[x].tipoDocumento.mtdcodigoI).val(data.listDocumentos[x].archivo.adjnombreV);	
        	}
    	}else{
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
    	$("#divFormulario").show();
    	//$("#divFormulario").dialog("open");
    	$("#divFormulario").dialog({
    		title:"Persona",
    		width:1100,
    		height: 600,
    		modal: true
    	});
    }
    
    function mensaje(data){
    	$("#divMensaje").empty();
    	$("#divMensaje").append(data);
    	$("#divMensaje").show();
    	var top=(screen.height-200)+'px';
    	var left=(screen.width-400)+'px';
    	$("#divMensaje").css({'position':'absolute','top':top,'left':left});
    	setTimeout(function() {
			$("#divMensaje").hide();
		}, 1500 );
    }
    
	//$(".btnModificar").click(modificar); 
    
    function modificar(){
    	//alert($(this).attr("id").replace("mod",""));
    	$.ajax({ 
    		data:{
    			codigo:$(this).attr("id").replace("mod","")
    		},
            datatype:'json',
            type: "GET", 
            url: "Asociados/Obtener.htm", 
            success: function(data){
            	//alert(JSON.stringify(data));
            	llenarFormulario(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	mensajeError();
            }
    	});
    }
    
    function mensajeError(){
    	$("#divConfirmacion").remove();
    	$("body").append("<div id='divConfirmacion'><p>Usted no puede efectuar esta operación.</p></div>");
    	$("#divConfirmacion").dialog({
    		title: 'Error',
			bgiframe: true,
			modal: true,
			buttons: {
				'Aceptar': function() {
					$(this).dialog('close');
				}
			}
		});
    }
    
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
                        url: "Asociados/Eliminar.htm", 
                        success: function(data){
							mensaje(data);
                        }/*,error: function(jqXHR, textStatus, errorThrown){
                        	mensajeError();
                        }*/
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
    
    //DOCUMENTO
    $(".formDocumento").submit(function(){
    	var options={
                //scriptCharset:"utf-8",
                //contentType:"application/json; charset=utf-8",
    			type: "POST", 
                url:'Asociados/Documento.htm',
                dataType:'html',
                beforeSubmit:function(){
                    //$("#progressbar").show();
                },
                uploadProgress: function(event, position, total, percentComplete) {
                	$("#txtCargando").empty();
                	$("#txtCargando").val(percentComplete);
                	//$("#progressbar").empty();
                    //$("#progressbar").progressbar({
                    //        value: percentComplete
                    //});
                },
                success: function(responseText, statusText) {      
                	//$("#divContenedorTab2").empty();
                    //$("#divContenedorTab2").append(responseText);
                } ,
                error:function(){
                    //alert("ERROR DE ENVIO");
                }
            };
            $(this).ajaxSubmit(options);
            return false;
    });
    		        
    function paginacion(){
		$("#tblLista")//.tablesorter(); 
        .tablesorter({widthFixed: true, widgets: ['zebra']}) 
        .tablesorterPager({container: $("#pager")}); 	
	}
});