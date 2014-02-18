$(document).ready(function(){       
    $("#divFormulario").hide();
    $("#tabs").tabs();
    $("#dtNacimiento").datepicker({dateFormat:"dd/mm/yy"});
    $(".dtFecha").datepicker({dateFormat:"dd/mm/yy"});
    
    $("#btnProcesar").click(procesar);
    $("#sltDepartamentos").change(departamento);
    $("#sltProvincia").mouseup(provincia);
    
    $("#btnNuevo").click(function(){llenarFormulario("");});
    $("#btnBuscar").click(function(){
    	buscar($("#sltCriterio").val(),$("#txtTexto").val());
    	$.message.Find();
    });
    $("#btnCancelar").click(function(){
    	$("#divFormulario").dialog("close");
    });
    
    buscar("PER.PERCODIGO_D","");
    
    $("input[type=text]").keyup(function(){
	  $(this).val( $(this).val().toUpperCase() );
	});
    
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
    
    function procesar(){
    	$("#divFormulario").validate();
    	$.ajax({ 
			data:{
				'inscodigoI':$("#txtCodigoInspector").val(),
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
	        url: "Inspectores/Procesar.htm", 
	        success: function(data){
	        	$("#txtCodigoAsociado").val(data.inscodigoI);
	        	$("#txtCodigoPersona").val(data.persona.percodigoD);
	        	buscar("INS.INSCODIGO_I",data.inscodigoI);
	    		$("#divFormulario").dialog("close");
	        	txtHtml="<p>Operación realizada correctamente</p>";
	        	$.message.Success();
	        },error: function(jqXHR, textStatus, errorThrown){
	        	$.message.Error();
	        }
		});		
    }
    
    function llenarFormulario(data){
    	if(data!=""){
    		$(".error").remove();
    		//alert(JSON.stringify(data));
    		if(data.foto.adjnombreV!=null){
    			$("#imgFotoInspector").attr("src","temp/"+data.foto.adjnombreV);
    		}else{
    			$("#imgFotoInspector").attr("src","images/no_disponible.jpg");
    		}
    		//$(".fileFotoAsociado").val("");
    		$("#txtCodigoInspector").val(data.inscodigoI);
    		$("#txtCodigoPersona").val(data.persona.percodigoD);
        	$("#txtNombres").val(data.persona.pernombresV);
        	$("#txtPaterno").val(data.persona.perpaternoV);
        	$("#txtMaterno").val(data.persona.permaternoV);
        	$("#txtDni").val(data.persona.perdniV);
        	$("#sltEstadoCivil").val(data.persona.perestadocivilC);
        	$("#sltSexo").val(data.persona.persexoC);
        	$("#dtNacimiento").val(data.persona.pernacimientoF);
        	$("#txtDireccion").val(data.persona.perdomicilioV);
        	$("#sltDepartamentos").val(data.persona.perubdptoV);
        	$("#sltProvincia").empty();
        	$("#sltProvincia").append("<option value='"+data.persona.perubprovV+"'>"+data.persona.perubprovnombreV+"</option>");
        	$("#sltDistrito").empty();
        	$("#sltDistrito").append("<option value='"+data.persona.perubidistV+"'>"+data.persona.perubidistnombreV+"</option>");
        	$("#txtCorreo").val(data.persona.peremailV);
        	$("#txtTelefono").val(data.persona.perteleffijoV);
        	$("#txtMovistar").val(data.persona.permovilmovV);
        	$("#txtClaro").val(data.persona.permovilclaV);
        	$("#txtNextel").val(data.persona.permovilnexV);
        	//alert("LENGTH: "+data.listDocumentos.length);
        	for(var x=0;x<data.documentos.length;x++){
        		$("#documento_"+data.documentos[x].tipoDocumento.mtdcodigoI).val(data.documentos[x].archivo.adjcodigoD);
        		$("#txtNumDocumento_"+data.documentos[x].tipoDocumento.mtdcodigoI).val(data.documentos[x].archivo.adjnumeroV);
        		$("#txtFechaEmision_"+data.documentos[x].tipoDocumento.mtdcodigoI).val(data.documentos[x].archivo.adjfechaemisionF);
        		$("#txtFechaCaducidad_"+data.documentos[x].tipoDocumento.mtdcodigoI).val(data.documentos[x].archivo.adjfechacaducidadF);
        	}
        	$("#btnProcesar").val("Modificar");
    	}else{
    		$(".error").remove();
    		$("#imgFotoInspector").attr("src","images/no_disponible.jpg");
    		$("#txtCodigoInspector").val(0);
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
        	$("#btnProcesar").val("Guardar");
    	}
    	$("#divFormulario").show();
    	//$("#divFormulario").dialog("open");
    	$("#divFormulario").dialog({
    		title:"Inspector",
    		width:850,
    		//height: 600,
    		modal: true
    	});
    }
    
    function buscar(criterio,texto){
    	$.ajax({ 
    		data:{
    			criterio:criterio,
    			texto:texto
    		},
            datatype:'json',
            type: "POST", 
            url: "Inspectores/Listar.htm", 
            success: function(data){
            	//alert(JSON.stringify(data));
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
			+"<th class='header'>DNI</th>"
			+"<th class='header'>NOMBRE</th>"
			+"<th class='header'>PATERNO</th>"
			+"<th class='header'>MATERNO</th>"
			+"<th class='header'>NACIMIENTO</th>"
			+"<th class='header'>FIJO</th>"
			+"<th class='header'>CORREO</th>"
			+"<th class='header'></th>"
			+"<th class='header'></th>"
			+"<th class='header'></th>"
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
			+"<th></th>"
			+"<th></th>"
			+"<th></th>"
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
			"<td>"+data[x].insestadoC+"</td>"+
			"<td><img alt='Modificar' class='btnModificar' id='mod"+data[x].inscodigoI+"' src='images/edit.png'></td>"+
			"<td><img alt='Eliminar' class='btnEliminar' id='del"+data[x].inscodigoI+"' src='images/delete.png'></td>"+
    				"</tr>";
    		$("#tblLista tbody").append(txtHtml);
    	}
		
    	$(".btnModificar").click(modificar);
    	$(".btnEliminar").click(eliminar);
    	$("#tblLista").paginacion();
    }
    
    function modificar(){
    	//alert($(this).attr("id").replace("mod",""));
    	$.ajax({ 
    		data:{
    			codigo:$(this).attr("id").replace("mod","")
    		},
            datatype:'json',
            type: "GET", 
            url: "Inspectores/Obtener.htm", 
            success: function(data){
            	//alert(JSON.stringify(data));
            	llenarFormulario(data);
            	$.message.Get();
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
    }
    
    function eliminar(){
    	var codigo=$(this).attr("id").replace("del","");
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
			            url: "Inspectores/Eliminar.htm", 
			            success: function(data){
			            	//alert(JSON.stringify(data));
			            	llenarFormulario(data);
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

    //FOTO
    $(".formFotoInspector").submit(function(){
    	var options={
    			type: "POST", 
                url:'Inspectores/Foto.htm',
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
                	$("#imgFotoInspector").attr("src","temp/"+responseText);
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
                url:'Inspectores/Documento.htm',
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
});