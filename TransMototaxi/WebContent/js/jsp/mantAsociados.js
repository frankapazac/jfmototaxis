$(document).ready(function(){       
    $("#divFormulario").hide();
    $("#divMensaje").hide();
    $("#divConstancia").hide();
    $("#tabs").tabs();
    $("#dtNacimiento").datepicker({dateFormat:"dd/mm/yy"});
    $(".dtFecha").datepicker({dateFormat:"dd/mm/yy"});
    
    $("input[type=text]").keyup(function(){
	  $(this).val( $(this).val().toUpperCase() );
	});
    
    buscar("PER.PERCODIGO_D","");
    
    //ACCIONES
    $("#btnBuscar").click(function(){
    	buscar($("#sltCriterio").val(),$("#txtTexto").val());
    	$.message.Find();
    });
    
    $("#btnNuevo").click(function(){
    	llenarFormulario("");
    	$("#tabs").tabs('enable',0).tabs("select",0);
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
            	$.message.Error();
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
            	$.message.Error();
            }
    	});
    });
    
    $("#btnProcesar").click(function(){
    	$("#tabs1").validate();
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
	    		buscar("ASO.ASOCODIGO_D",data.asocodigoD);
	        	txtHtml="<p>Operación realizada correctamente</p>";
	        	$("#divFormulario").dialog("close");
	        	$.message.Success();	        	
	        },error: function(jqXHR, textStatus, errorThrown){
	        	$.message.Error();
	        }
		});
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
			//+"<th class='header'></th>"
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
			//+"<th></th>"
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
			"<td>"+data[x].asoestadoC+"</td>"+
			//"<td><img alt='Constancia de Afiliación' class='btnConstancia' id='con"+data[x].asocodigoD+"' src='images/run_skip.png'></td>"+
			"<td><img alt='Modificar' class='btnModificar' id='mod"+data[x].asocodigoD+"' src='images/edit.png'></td>"+
			"<td><img alt='Eliminar' class='btnEliminar' id='del"+data[x].asocodigoD+"' src='images/delete.png'></td>"+
    				"</tr>";
    		$("#tblLista tbody").append(txtHtml);
    	}
    	//$(".btnConstancia").click(constancia);
    	$(".btnModificar").click(modificar);
    	$(".btnEliminar").click(eliminar);
    	$("#tblLista").paginacion();
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
    	$("#tabs1").validateClean();
    	if(data!=""){
    		//alert(JSON.stringify(data));
    		if(data.asociado.foto.adjnombreV!=null){
    			$("#imgFotoAsociado").attr("src","temp/"+data.asociado.foto.adjnombreV);
    		}else{
    			$("#imgFotoAsociado").attr("src","images/no_disponible.jpg");
			}
    		$(".fileFotoAsociado").val("");
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
    		$("#imgFotoAsociado").attr("src","images/no_disponible.jpg");
    		$("img[class='imgFotosVehiculo']").attr("src","images/no_disponible.jpg");
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
    	$("#divFormulario").dialog({
    		title:"Asociados",
    		width:1100,
    		height: 600,
    		modal: true
    	});
    }
    
    function constancia(){
    	$.ajax({ 
    		data:{
    			codigo:$(this).attr("id").replace("con","")    	
    		},
            datatype:'json',
            type: "GET", 
            url: "UnidadEmpresa/ListarPorAsociado.htm", 
            success: function(data){
            	var htmlText="<option value=''>-Seleccione-</option>";
            	for(var x=0;x<data.length;x++){
            		htmlText+="<option value='"+data[x].unidadempresa.unecodigoD+"'>"+data[x].unidadempresa.uneplacanroV+"</option>";
            	}
            	$("#sltMototaxi").html(htmlText);
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
    	
    	
    	$("#divConstancia").dialog({
    		title:"Asociados",
    		width:900,
    		modal: true
    	});
    	
    }
    
    function modificar(){
    	$.ajax({ 
    		data:{
    			codigo:$(this).attr("id").replace("mod","")
    		},
            datatype:'json',
            type: "GET", 
            url: "Asociados/Obtener.htm", 
            success: function(data){
            	llenarFormulario(data);
            	$("#tabs").tabs('enable',0).tabs("select",0);
            	$.message.Get();
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
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
    $(".formFotoAsociado").submit(function(){
    	var options={
    			type: "POST", 
                url:'Asociados/Foto.htm',
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
                	$("#imgFotoAsociado").attr("src","temp/"+responseText);
                    $("#progressFoto").hide();
                    $("#progressFoto").progressbar({
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
    
    //DOCUMENTO
    $(".formDocumento").submit(function(){
    	var options={
    			type: "POST", 
                url:'Asociados/Documento.htm',
                dataType:'html',
                beforeSubmit:function(){
                    $("#progressArchivoAsociado").show();
                },
                uploadProgress: function(event, position, total, percentComplete) {
                	$("#progressArchivoAsociado").progressbar({
                        value: percentComplete
                	});
                },
                success: function(responseText, statusText) {      
                	$("#progressArchivoAsociado").hide();
                    $("#progressArchivoAsociado").progressbar({
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
    
    //CODIGO DE LA CONSTANCIA
    $("#sltMototaxi").change(seleccionarMototaxi);
    
    function seleccionarMototaxi(){
    	if($(this).val()!=null&&$(this).val()>0){
    		$.ajax({ 
        		data:{
        			codigo:$(this).val()
        		},
                datatype:'json',
                type: "GET", 
                url: "Mototaxi/ObtenerUnidad.htm", 
                success: function(data){
                	alert(JSON.stringify(data));
                	$("#txtConsPlaca").text(data.uneplacanroV);
                	$("#txtConsMarca").text(data.marca.marnombreV);
                	$("#txtConsMotor").text(data.unenromotorV);
                	$("#txtConsSerie").text(data.unenroseriechasisV);
                	$("#txtConsColor").text(data.unenroseriechasisV);
                	$("#txtConsModelo").text(data.modelo.modnombre_V);
                	$("#txtConsAno").text(data.uneannoC);
                	$("#txtConsCombustible").text((data.unecombustibleC=='G')?'Gasolina':(data.unecombustibleC=='P')?'Petroleo':'');
                	$("#txtConsPasajeros").text(data.unenropasajerosI);
                	/*$("#txtConsCAT").text(data.unenroseriechasisV);
                	$("#txtConsCATInicio").text(data.unenroseriechasisV);
                	$("#txtConsCATFin").text(data.unenroseriechasisV);
                	$("#txtConsChofer").text(data.unenroseriechasisV);
                	$("#txtConsChoferDNI").text(data.unenroseriechasisV);
                	$("#txtConsLicencia").text(data.unenroseriechasisV);
                	$("#txtConsFecha").text(data.unenroseriechasisV);
                	$("#txtConsDomicilio").text(data.unenroseriechasisV);*/
                	$.message.Get();
                },error: function(jqXHR, textStatus, errorThrown){
                	$.message.Error();
                }
        	});
    	}
    }
});