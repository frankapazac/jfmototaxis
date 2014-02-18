$(document).ready(function(){    	
    $("#divFormulario").hide();
    $("#divMensaje").hide();
    $("#divConstancia").hide();
    //$("#tabs").tabs();
    $("#dtNacimiento").datepicker({dateFormat:"dd/mm/yy"});
    $(".dtFecha").datepicker({dateFormat:"dd/mm/yy"});
    $("#divFormularioCese").hide();
    $("#divFormularioMotoCese").hide();
    $("#btnGuardarCese").click(guardarCese);
    $("#txtNroPlaca").focusout(buscarNroPlaca);
    
    ///mototaxi
    $("#btnSiguiente").click(fncSiguiente);
	$("#btnVehiculoProcesar").click(fncVehiculoProcesar);
	$(".formDocumentoUnidad").submit(fncDocumentoUnidad);
	$(".formDocumentoFoto").submit(fncDocumentoFoto);
	$("#btnMotoEditar").click(fncEditarMoto);
	$("#btnVehiculoCancelar").click(fncCancelar);
	$("#btnMotoNuevo").click(function(){llenarDatosMototaxi([]);});
	var codigoMototaxi="0";
   
    $("#tabs").tabs({
    	activate:function(event,ui){                                                       
    		//alert(ui.newTab.index());
    		if(ui.newTab.index()==1){
    			if($("#txtCodigoAsociado").val()==0){
    				$.message.Info('Información','Guarde antes de continuar por favor');
    				$("#tabs").tabs('enable',0).tabs("select",0);
    				//evt.stopPropagation();
    				return;
    			}
    			llenarTablaMotos();
    			llenarDatosMototaxi("");
    		}
    	}
    });

    /*function llenarTablaMotosTAB(){
		$.ajax({ 
    		data:{
    			codigo:$("#txtCodigoAsociado").val()
    		},
            datatype:'json',
            type: "GET", 
            url: "UnidadEmpresa/ListarPorAsociado.htm", 
            success: function(data){
            	if(data==""){ 
            		$("#tblMotos").empty();
            		return;
            	}
            	var txtHtml="";
            	$("#tblMotos").empty();
            	txtHtml="<thead>"
        			+"<th class='header'>NUM</th>"
        			+"<th class='header'>PLACA</th>"
        			+"<th class='header'>MARCA</th>"
        			+"</thead>"
        			+"<tfoot>"
        			+"<th>NUM</th>"
        			+"<th>PLACA</th>"
        			+"<th>MARCA</th>"
        			+"</tfoot>"
        			+"<tbody></tbody>";
        			$("#tblMotos").append(txtHtml);
            	for(var x=0;x<data.length;x++){
            		var classe="even";
            		if(x%2==0) classe="odd";
            		txtHtml="<tr id='mot"+data[x].unidadempresa.unecodigoD+"' class='trMotos "+classe+"'>"+
        			"<td>"+(x+1)+"</td>"+
        			"<td>"+data[x].unidadempresa.uneplacanroV+"</td>"+
        			"<td>"+data[x].unidadempresa.marca.marnombreV+"</td>"+
        			"</tr>";
            		$("#tblMotos tbody").append(txtHtml);
            	}
        		$(".trMotos").click(fncCambiaMotoTAB);
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
	}
    
    function fncCambiaMotoTAB(){
		codigoMototaxi=$(this).attr("id").replace("mot","");
	}
    
    function llenarDatosMototaxiTAB(data){
		//alert(JSON.stringify(data.unidadEmpresa));
    	$("#tabs2").validateClean();
		if(data!=""){
			$("divVehiculoArchivos input[name='txtCodArchivo']").val("0");
			$("divVehiculoArchivos input[name='txtNumDocumento']").val("");
			$("divVehiculoArchivos input[name='txtFechaEmision']").val("");
			$("divVehiculoArchivos input[name='txtFechaCaducidad']").val("");
			//alert(123);
			$("img[class='imgFotosVehiculo']").attr("src","images/no_disponible.jpg");
    		$("#txtCodigoVehiculo").val(data.unidadEmpresa.unecodigoD);
			//alert(data.empadronamiento.empfechainicioF);
    		$("#txtCodigoEmpadronamiento").val(data.empadronamiento.epocodigoD);
			$("#txtEmpadFechaInicio").val(data.empadronamiento.empfechainicioF);
			$("#txtEmpadFechaCese").val(data.empadronamiento.empfechaceseF);
			$("#txtNroPlaca").val(data.unidadEmpresa.uneplacanroV);
			$("#sltCarroceria").val(data.unidadEmpresa.unecarroceriaC);
			$("#sltOfRegistral").val(data.unidadEmpresa.oficina.oficodigoI);
			$("#txtNroSerieChasis").val(data.unidadEmpresa.unenroseriechasisV);
			$("#txtNroPadron").val(data.unidadEmpresa.unenropadronV);
			$("#txtNroMotor").val(data.unidadEmpresa.unenromotorV);
			$("#txtPartRegistral").val(data.unidadEmpresa.unenropartidaregistralV);
			$("#txtNroNiv").val(data.unidadEmpresa.unenroidentificacionV);
			$("#txtTitulo").val(data.unidadEmpresa.unetituloV);
			$("#txtRuedas").val(data.unidadEmpresa.unenroruedasI);
			$("#txtClase").val(data.unidadEmpresa.uneclaseV);
			$("#txtNroAsientos").val(data.unidadEmpresa.unenroasientosI);
			$("#sltMarca").val(data.unidadEmpresa.marca.marcodigoI);
			$("#txtNroPasajeros").val(data.unidadEmpresa.unenropasajerosI);
			$("#sltModelo").val(data.unidadEmpresa.modelo.modcodigo_D);
			$("#txtCargaUtil").val(data.unidadEmpresa.unecargautilD);
			$("#txtAno").val($.trim(data.unidadEmpresa.uneannoC));
			$("#txtLongitud").val(data.unidadEmpresa.unelongitudD);
			$("#txtColor").val(data.unidadEmpresa.unecolorV);
			$("#txtAncho").val(data.unidadEmpresa.uneanchoD);
			$("#sltCombustible").val(data.unidadEmpresa.unecombustibleC);
			$("#txtAlto").val(data.unidadEmpresa.unealtoD);
			for(var x=0;x<data.documentos.length;x++){
				$("#documentoUnd_"+data.documentos[x].tipoDocumento.mtdcodigoI).val(data.documentos[x].adjuntarArchivo.adjcodigoD);
        		$("#txtUndNumDocumento_"+data.documentos[x].tipoDocumento.mtdcodigoI).val(data.documentos[x].adjuntarArchivo.adjnumeroV);
        		$("#txtUndFechaEmision_"+data.documentos[x].tipoDocumento.mtdcodigoI).val(data.documentos[x].adjuntarArchivo.adjfechaemisionF);
        		$("#txtUndFechaCaducidad_"+data.documentos[x].tipoDocumento.mtdcodigoI).val(data.documentos[x].adjuntarArchivo.adjfechacaducidadF);
			}
			for(var f=0;f<data.fotos.length;f++){
	    		$("#imgFotoVehiculo_"+data.fotos[f].tipoDocumento.mtdcodigoI).attr("src","temp/"+data.fotos[f].adjuntarArchivo.adjnombreV);	
			}
			$("#btnVehiculoProcesar").val("Modificar");
		}else{
			$("divVehiculoArchivos input[name='txtCodArchivo']").val("0");
			$("divVehiculoArchivos input[name='txtNumDocumento']").val("");
			$("divVehiculoArchivos input[name='txtFechaEmision']").val("");
			$("divVehiculoArchivos input[name='txtFechaCaducidad']").val("");
			
			$("img[class='imgFotosVehiculo']").attr("src","images/no_disponible.jpg");
			$("#txtCodigoVehiculo").val("");
			$("#txtCodigoEmpadronamiento").val("");
			$("#txtEmpadFechaInicio").val("");
			$("#txtEmpadFechaCese").val("");
			$("#txtNroPlaca").val("");
			$("#sltCarroceria").val("");
			$("#sltOfRegistral").val("");
			$("#txtNroSerieChasis").val("");
			$("#txtNroPadron").val("");
			$("#txtNroMotor").val("");
			$("#txtPartRegistral").val("");
			$("#txtNroNiv").val("");
			$("#txtTitulo").val("");
			$("#txtRuedas").val("");
			$("#txtClase").val("");
			$("#txtNroAsientos").val("");
			$("#sltMarca").val("");
			$("#txtNroPasajeros").val("");
			$("#sltModelo").val("");
			$("#txtCargaUtil").val("");
			$("#txtAno").val("");
			$("#txtLongitud").val("");
			$("#txtColor").val("");
			$("#txtAncho").val("");
			$("#sltCombustible").val("");
			$("#txtAlto").val("");
			$(".txtVehNumDocumento").val("");
			$(".txtVehFecEmision").val("");
			$(".txtVehFecCaducidad").val("");
			$("#btnVehiculoProcesar").val("Insertar");
		}
	}*/
    
    $("input[type=text]").keyup(function(){
	  $(this).val( $(this).val().toUpperCase() );
	});
    
    buscar("PER.PERCODIGO_D","");
    
    //ACCIONES
    $("#btnBuscar").click(function(){
    	buscar($("#sltCriterio").val(),$("#txtTexto").val());
    	$.message.Find();
    });
    
    $("#btnImprimirPdf").click(function(){
    	window.open("Asociados/ImprimirTodosAsociadosActivos.htm");
    });
    
    $("#btnNuevo").click(function(){
    	llenarFormulario("");
    	$("#tabs").tabs('enable',0).tabs("select",0);
    });

    $("#btnCancelar").click(function(){
    	$("#divFormulario").dialog("close");
    });
    
    function buscarNroPlaca(){
    	var placa=$.trim($(this).val());
    	$.ajax({
    		data:{
    			placa:placa
    		},
            datatype:'json',
            type: "POST", 
            url: "Asociados/BuscarNroPlaca.htm", 
            success: function(data){
            	if(data.length>0){
            		$("body #divMenssage").remove();
        			var htmlText="<div id='divMenssage' class='ui-widget-content ui-corner-all'>" +
        					"<table class='tblTablaEstilo'>";
        			htmlText+="<thead><tr style='background-color:gainsboro'><th class='header'>EMPRESA</th>" +
        					"<th class='header'>PADRON</th>" +
        					"<th class='header'>PARTIDA</th>" +
        					"<th class='header'>SERIE</th>" +
        					"<th class='header'>MOTOR</th>" +
        					"<th class='header'>COLOR</th>" +
        					"<th class='header'>INICIO</th>" +
        					"<th class='header'>CESE</th>" +
        					"<th class='header'>OBSERVACION</th></tr><thead>";
					for(var x=0;x<data.length;x++){
						htmlText+="<tbody><tr><td>"+data[x].asociado.empresa.emprazonsocialV+"</td>" +
								"<td>"+data[x].unidadempresa.unenropadronV+"</td>" +
								"<td>"+data[x].unidadempresa.unenropartidaregistralV+"</td>" +
								"<td>"+data[x].unidadempresa.unenroseriechasisV+"</td>" +
								"<td>"+data[x].unidadempresa.unenromotorV+"</td>" +
								"<td>"+data[x].unidadempresa.unecolorV+"</td>" +
								"<td>"+data[x].pmofechainicioV+"</td>" +
								"<td>"+data[x].pmofechaceseV+"</td>" +
								"<td>"+data[x].pmoobservacionV+"</td></tr></tbody>";
					}
					htmlText+="</table>" +
        					"<p>NOTA: El Afiliado no puede registrarse ya que no esta cesado en las otras empresas.</p>" +
        					"<img src='images/Error32x32.png'>"+
        					"<input type='button' id='btnErrorAceptar' value='Aceptar' class='ui-button'/>"+
        					"</div>";
					$("body").append(htmlText);
        			$("body #divMenssage").css({'width': '830px', 'height': 'auto'});
        			$("body #divMenssage img").css({'padding-bottom': '10px','padding-right': '10px','float': 'right'});
        			$("body #divMenssage").dialog({
        				open: function(event, ui) { $(".ui-dialog-titlebar-close").click(function(){
        					$("body #divMenssage").dialog("close");
            				$("#divFormulario").dialog('close');
        				}); },
        				width: 850,
        				title:"Informe del Afiliado",
        				closeOnEscape: false,
        	    		modal: true
        	    	});
        			$("body #divMenssage #btnErrorAceptar").click(function(){
        				$("body #divMenssage").dialog("close");
        				$("#divFormulario").dialog('close');
        			});
            	}else{
            		BuscarExisteUnidad(placa);
            	}
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
    }
    
    function BuscarExisteUnidad(nroPlaca){
    	$.ajax({ 
    		data:{
    			placa:nroPlaca
    		},
            datatype:'json',
            type: "GET", 
            url: "UnidadEmpresa/Obtener.htm", 
            success: function(data){
            	if(data.unecodigoD>0){
                	$("#txtCodigoVehiculo").val(data.unecodigoD);
            		$("#txtNroPlaca").val(data.uneplacanroV);
        			$("#sltCarroceria").val(data.unecarroceriaC);
        			$("#sltOfRegistral").val(data.oficina.oficodigoI);
        			$("#txtNroSerieChasis").val(data.unenroseriechasisV);
        			$("#txtNroPadron").val(data.unenropadronV);
        			$("#txtNroMotor").val(data.unenromotorV);
        			$("#txtPartRegistral").val(data.unenropartidaregistralV);
        			$("#txtNroNiv").val(data.unenroidentificacionV);
        			$("#txtTitulo").val(data.unetituloV);
        			$("#txtRuedas").val(data.unenroruedasI);
        			$("#txtClase").val(data.uneclaseV);
        			$("#txtNroAsientos").val(data.unenroasientosI);
        			$("#sltMarca").val(data.marca.marcodigoI);
        			$("#txtNroPasajeros").val(data.unenropasajerosI);
        			$("#sltModelo").val(data.modelo.modcodigo_D);
        			$("#txtCargaUtil").val(data.unecargautilD);
        			$("#txtAno").val($.trim(data.uneannoC));
        			$("#txtLongitud").val(data.unelongitudD);
        			$("#txtColor").val(data.unecolorV);
        			$("#txtAncho").val(data.uneanchoD);
        			$("#sltCombustible").val(data.unecombustibleC);
        			$("#txtAlto").val(data.unealtoD);
                	$.message.Get();
            	}
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});	
    }
    
    function guardarCese(){
    	$.ajax({
    		data:{
    			asocodigoD:$.trim($("#txtCodigoCese").val()),
    			asofechaceseF:$.trim($("#txtFechaCese").val()),
    			asoobservacionV:$.trim($("#txtObservacion").val())
    		},
            datatype:'json',
            type: "POST", 
            url: "Asociados/GuardarCese.htm", 
            success: function(data){
	        	buscar($("#sltCriterio").val(),$("#txtTexto").val());
	        	$("#divFormularioCese").dialog('close');
	        	$.message.Success();
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
    }
    $("#btnCancelarCese").click(function(){
    	$("#divFormularioCese").dialog('close');
    });
    
    $("#txtDni").focusout(function(){
    	if($.trim($(this).val()).length>=8){
	    	$.ajax({
	    		data:{
	    			dni:$.trim($(this).val())
	    		},
	            datatype:'json',
	            type: "GET", 
	            url: "Asociados/ExisteAsociadoPorDNI.htm", 
	            success: function(data){
	        		//alert(JSON.stringify(data));
	            	if(data.length>0){
	            		$("body #divMenssage").remove();
	        			var htmlText="<div id='divMenssage' class='ui-widget-content ui-corner-all'>" +
	        					"<table class='tblTablaEstilo'>";
	        			htmlText+="<thead><tr style='background-color:gainsboro'><th class='header'>EMPRESA</td>" +
	        					"<th class='header'>MOVISTAR</td>" +
	        					"<th class='header'>CLARO</td>" +
	        					"<th class='header'>NEXTEL</td>" +
	        					"<th class='header'>TELEFONOS</td>" +
	        					"<th class='header'>F. INICIO</td>" +
	        					"<th class='header'>F. CESE</td>" +
	        					"<th class='header'>OBSERVACIONES</td>" +
	        					"<th class='header'>ESTADO</td></tr><thead>";
    					for(var x=0;x<data.length;x++){
    						htmlText+="<tbody><tr><td>"+data[x].empresa.emprazonsocialV+"</td>" +
    								"<td>"+data[x].empresa.empcelularmovV+"</td>" +
    								"<td>"+data[x].empresa.empcelularclaV+"</td>" +
    								"<td>"+data[x].empresa.empcelularnexV+"</td>" +
    								"<td>"+data[x].empresa.emptelefono1V+"</td>" +
    								"<td>"+data[x].asofechainicioF+"</td>" +
    								"<td>"+data[x].asofechaceseF+"</td>" +
    								"<td>"+data[x].asoobservacionV+"</td>" +
    								"<td>"+data[x].asoestadoC+"</td></tr></tbody>";
    					}
    					htmlText+="</table>" +
	        					"<p>NOTA: "+
	        					(data[0].empresa.empestadoC=="ACTIVO"?"Usted puede continuar con los tramites normalmente.":"El nuevo Afiliado tiene contratos con otras empresas.")+
	        					"</p>" +
	        					"<img src='images/Error32x32.png'>"+
	        					"<input type='button' id='btnErrorAceptar' value='Aceptar' class='ui-button'/>"+
	        					"</div>";
    					$("body").append(htmlText);
	        			$("body #divMenssage").css({'width': '830px', 'height': 'auto'});
	        			$("body #divMenssage img").css({'padding-bottom': '10px','padding-right': '10px','float': 'right'});
	        			$("body #divMenssage").dialog({
	        				width: 850,
	        				title:"Informe del Afiliado",
	        	    		modal: true
	        	    	});
	        			$("body #divMenssage #btnErrorAceptar").click(function(){
	        				$("body #divMenssage").dialog("close");
	        			});
	            	}
	            },error: function(jqXHR, textStatus, errorThrown){
	            	$.message.Error();
	            }
	    	});
    	}
    });

    $("#txtDni").keyup(function(){
    	/*$("#txtCodigoPersona").val("0");
		$("#txtNombres").val("");
		$("#txtPaterno").val("");
		$("#txtMaterno").val("");
		$("#txtNacimiento").val("");
		$("#sltSexo").val("M");
		$("#sltEstadoCivil").val("S");
		$("#txtClaro").val("");
		$("#txtMovistar").val("");
		$("#txtNextel").val("");
		$("#txtTelefono").val("");
		$("#txtCorreo").val("");
		$("#txtDireccion").val("");
		$("#sltDepartamentos").val("15");
		$("#sltProvincia").empty();
		$("#sltDistrito").empty();
		$("#sltProvincia").append("<option value=''>-Seleccione-</option>");
		$("#sltDistrito").append("<option value=''>-Seleccione-</option>");*/
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
                		$("#txtCodigoPersona").val(data.percodigoD);
                		$("#txtDni").val(data.perdniV);
                		$("#txtNombres").val(data.pernombresV);
                		$("#txtPaterno").val(data.perpaternoV);
                		$("#txtMaterno").val(data.permaternoV);
                		$("#dtNacimiento").val(data.pernacimientoF);
                		$("#sltSexo").val(data.persexoC);
                		$("#sltEstadoCivil").val(data.perestadocivilC);
                		$("#txtClaro").val(data.permovilclaV);
                		$("#txtMovistar").val(data.permovilmovV);
                		$("#txtNextel").val(data.permovilnexV);
                		$("#txtTelefono").val(data.perteleffijoV);
                		$("#txtCorreo").val(data.peremailV);
                		$("#txtDireccion").val(data.perdomicilioV);
                		$("#sltDepartamentos").val(data.perubdptoV);
                		$("#sltProvincia").append("<option value='"+data.perubprovV+"'>"+data.perubprovnombreV+"</option>");
                		$("#sltDistrito").append("<option value='"+data.perubidistV+"'>"+data.perubidistnombreV+"</option>");
                		$("#sltProvincia").val(data.perubprovV);
                		$("#sltDistrito").val(data.perubidistV);
                    	$.message.Success();
                	}
                },error: function(jqXHR, textStatus, errorThrown){
                	$.message.Error();
                }
        	});
    	}
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

    $("#sltProvincia").bind("click focus change",function(){
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
			+"<th class='header'>N°</th>"
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
			+"<th class='header'></th>"
			+"<th class='header'></th>"
			+"</thead>"
			+"<tfoot>"
			+"<th>N°</th>"
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
			"<td><img alt='Modificar' class='btnCesar' id='ces"+data[x].asocodigoD+"' src='images/delete_user_16.png'></td>"+
			"<td><img alt='Modificar' class='btnModificar' id='mod"+data[x].asocodigoD+"' src='images/edit.png'></td>"+
			"<td><img alt='Eliminar' class='btnEliminar' id='del"+data[x].asocodigoD+"' src='images/delete.png'></td>"+
			"<td><img alt='Imprimir' class='btnImprimir' id='imp"+data[x].asocodigoD+"' src='images/agt_print.png'></td>"+
    				"</tr>";
    		$("#tblLista tbody").append(txtHtml);
    	}
    	//$(".btnConstancia").click(constancia);
    	$(".btnCesar").click(cesar);
    	$(".btnModificar").click(modificar);
    	$(".btnEliminar").click(eliminar);
    	$(".btnImprimir").click(imprimir);
    	$("#tblLista").paginacion();
    }
    
    function imprimir(){
    	var codigo=$(this).attr("id").replace("imp","");
    	window.open("Asociados/Imprimir.htm?codigo="+codigo);
    }
    
    function cesar(){
    	$("#txtFechaCese").val("");
    	$("#txtObservacion").val("");
    	
    	var idAsociado=$(this).attr("id").replace("ces","");
    	$.ajax({ 
    		data:{
    			codigo:idAsociado
    		},
            datatype:'json',
            type: "GET", 
            url: "Asociados/ObtenerCese.htm", 
            success: function(data){
            	$("#txtCodigoCese").val(idAsociado);
            	$("#txtNombreAsociado").text(data.persona.pernombresV);
            	$("#txtFechaCese").val(data.asofechaceseF);
            	$("#txtObservacion").val(data.asoobservacionV);
            	$.message.Get();
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
    	
    	$("#divFormularioCese").show();
    	$("#divFormularioCese").dialog({
    		title:"Cesar Afiliado",
    		width:500,
    		modal: true
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
    		title:"Afiliados",
    		width:850,
    		height: 520,
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
    		title:"Afiliados",
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
    
    ///////MOTOTAXI
    $("#btnMotoCese").click(function(){
    	$("#txtCodigoMotoCese").val("0");
    	$("#txtFechaMotoCese").val("");
    	$("#txtObservacionMoto").val("");
    	
    	$.ajax({ 
    		data:{
    			codigo:codigoMototaxi
    		},
            datatype:'json',
            type: "GET", 
            url: "Mototaxi/ObtenerCeseMoto.htm", 
            success: function(data){
            	//alert(JSON.stringify(data));
            	$("#txtPlacaMoto").text(data.uneplacanroV);
            	//llenarDatosMototaxi(data);
            	$.message.Get();
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
    	
    	$("#divFormularioMotoCese").show();
    	$("#divFormularioMotoCese").dialog({
    		title:"Cesar Unidad",
    		width:500,
    		modal: true
    	});
    });
    
    $("#btnGuardarMotoCese").click(function(){
    	$.ajax({ 
    		data:{
    			"unidadempresa.unecodigoD":codigoMototaxi,
    			"pmofechaceseV":$("#txtFechaMotoCese").val(),
    			"pmoobservacionV":$("#txtObservacionMoto").val()
    		},
            datatype:'json',
            type: "POST", 
            url: "Mototaxi/GuardarCeseMoto.htm", 
            success: function(data){
            	llenarTablaMotos();
            	$.message.Success();
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
    });
	
	//DOCUMENTO 
    function fncDocumentoUnidad(){
    	var options={
    			type: "POST", 
                url:'UnidadEmpresa/Documento.htm',
                dataType:'html',
                beforeSubmit:function(){
                	$("#progressMototaxiArchivo").show();
                },
                uploadProgress: function(event, position, total, percentComplete) {
                	$("#progressMototaxiArchivo").progressbar({
                        value: percentComplete
                	});
                },
                success: function(responseText, statusText) {      
                	$("#progressMototaxiArchivo").hide();
                    $("#progressMototaxiArchivo").progressbar({
                        value: 0
                	});
                } ,
                error:function(){
                	alert("ERROR");
                }
            };
            $(this).ajaxSubmit(options);
            return false;
    }
    //DOCUMENTO 
    function fncDocumentoFoto(){
    	var options={
    			type: "POST", 
                url:'UnidadEmpresa/DocumentoFoto.htm',
                dataType:'json',
                beforeSubmit:function(){
                    $("#progressVehiculo").show();
                },
                uploadProgress: function(event, position, total, percentComplete) {
                	$("#progressVehiculo").progressbar({
                        value: percentComplete
                	});
                },
                success: function(responseText, statusText) {
                	var texto=responseText.split('|');
                	$("#imgFotoVehiculo_"+texto[0]).attr("src","temp/"+texto[1]);
                    $("#progressVehiculo").hide();
                    $("#progressVehiculo").progressbar({
                        value: 0
                	});
                } ,
                error:function(){
                	alert("ERROR");
                }
            };
            $(this).ajaxSubmit(options);
            return false;
    }
	
	function fncVehiculoProcesar(){
    	$("#tabs2").validate();
		$.ajax({ 
    		data:{
    			'propUnidadEmpresa.asociado.asocodigoD':$("#txtCodigoAsociado").val(),
    			'empadronamiento.epocodigoD':$("#txtCodigoEmpadronamiento").val(),
    			'propUnidadEmpresa.unidadempresa.unecodigoD':$("#txtCodigoVehiculo").val(),
    			'propUnidadEmpresa.unidadempresa.uneplacanroV':$("#txtNroPlaca").val(),
    			'propUnidadEmpresa.unidadempresa.oficina.oficodigoI':$("#sltOfRegistral").val(),
    			'propUnidadEmpresa.unidadempresa.unenropadronV':$("#txtNroPadron").val(),
    			'propUnidadEmpresa.unidadempresa.unenropartidaregistralV':$("#txtPartRegistral").val(),
    			'propUnidadEmpresa.unidadempresa.unetituloV':$("#txtTitulo").val(),
    			'propUnidadEmpresa.unidadempresa.uneclaseV':$("#txtClase").val(),
    			'propUnidadEmpresa.unidadempresa.marca.marcodigoI':$("#sltMarca").val(),
    			'propUnidadEmpresa.unidadempresa.modelo.modcodigo_D':$("#sltModelo").val(),
    			'propUnidadEmpresa.unidadempresa.uneannoC':$("#txtAno").val(),
    			'propUnidadEmpresa.unidadempresa.unecolorV':$("#txtColor").val(),
    			'propUnidadEmpresa.unidadempresa.unecombustibleC':$("#sltCombustible").val(),
    			'propUnidadEmpresa.unidadempresa.unecarroceriaC':$("#sltCarroceria").val(),
    			'propUnidadEmpresa.unidadempresa.unenroseriechasisV':$("#txtNroSerieChasis").val(),
    			'propUnidadEmpresa.unidadempresa.unenromotorV':$("#txtNroMotor").val(),
    			'propUnidadEmpresa.unidadempresa.unenroidentificacionV':$("#txtNroNiv").val(),
    			'propUnidadEmpresa.unidadempresa.unenroruedasI':$("#txtRuedas").val(),
    			'propUnidadEmpresa.unidadempresa.unenroasientosI':$("#txtNroAsientos").val(),
    			'propUnidadEmpresa.unidadempresa.unenropasajerosI':$("#txtNroPasajeros").val(),
    			'propUnidadEmpresa.unidadempresa.unecargautilD':$("#txtCargaUtil").val(),
    			'propUnidadEmpresa.unidadempresa.unelongitudD':$("#txtLongitud").val(),
    			'propUnidadEmpresa.unidadempresa.uneanchoD':$("#txtAncho").val(),
    			'propUnidadEmpresa.unidadempresa.unealtoD':$("#txtAlto").val()
    		},
            datatype:'json',
            type: "POST", 
            url: "UnidadEmpresa/Procesar.htm", 
            success: function(data){
            	$("#txtCodigoVehiculo").val(data.propUnidadEmpresa.unidadempresa.unecodigoD);
            	$("#txtCodigoEmpadronamiento").val(data.empadronamiento.epocodigoD);
            	llenarTablaMotos();
            	$("#tabs2").validateClean();
            	limpiarVehiculo();
            	$.message.Success();
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
	}
	
	function fncSiguiente(){
		if($("#txtCodigoAsociado").val()==0){
			$.message.Info('Información','Guarde antes de continuar por favor');
			evt.stopPropagation();
			return;
			
		}
		llenarTablaMotos();
		llenarDatosMototaxi("");
		$("#tabs").tabs('enable',1).tabs("select",1);
	}
	
	function llenarTablaMotos(){
		$.ajax({ 
    		data:{
    			codigo:$("#txtCodigoAsociado").val()
    		},
            datatype:'json',
            type: "GET", 
            url: "UnidadEmpresa/ListarPorAsociado.htm", 
            success: function(data){
            	if(data==""){ 
            		$("#tblMotos").empty();
            		return;
            	}
            	var txtHtml="";
            	$("#tblMotos").empty();
            	txtHtml="<thead>"
        			+"<th class='header'>NUM</th>"
        			+"<th class='header'>PLACA</th>"
        			+"<th class='header'>MARCA</th>"
        			+"</thead>"
        			+"<tfoot>"
        			+"<th>NUM</th>"
        			+"<th>PLACA</th>"
        			+"<th>MARCA</th>"
        			+"</tfoot>"
        			+"<tbody></tbody>";
        			$("#tblMotos").append(txtHtml);
            	for(var x=0;x<data.length;x++){
            		var classe="even";
            		if(x%2==0) classe="odd";
            		txtHtml="<tr id='mot"+data[x].unidadempresa.unecodigoD+"' class='trMotos "+classe+"'>"+
        			"<td>"+(x+1)+"</td>"+
        			"<td>"+data[x].unidadempresa.uneplacanroV+"</td>"+
        			"<td>"+data[x].unidadempresa.marca.marnombreV+"</td>"+
        			"</tr>";
            		$("#tblMotos tbody").append(txtHtml);
            	}
        		$(".trMotos").click(fncCambiaMoto);
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
	}
	
	function fncCambiaMoto(){
		codigoMototaxi=$(this).attr("id").replace("mot","");
	}
	
	function fncEditarMoto(){
		if(codigoMototaxi=="0"){
			mensaje("Seleccione una moto de la tabla antes de continuar");
		}
		$.ajax({ 
    		data:{
    			codigo:codigoMototaxi
    		},
            datatype:'json',
            type: "GET", 
            url: "Mototaxi/Obtener.htm", 
            success: function(data){
            	//alert(JSON.stringify(data));
            	llenarDatosMototaxi(data);
            	$.message.Get();
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Error();
            }
    	});
	}
	
	function llenarDatosMototaxi(data){
		//alert(JSON.stringify(data.unidadEmpresa));
    	$("#tabs2").validateClean();
		if(data!=""){
			$("divVehiculoArchivos input[name='txtCodArchivo']").val("0");
			$("divVehiculoArchivos input[name='txtNumDocumento']").val("");
			$("divVehiculoArchivos input[name='txtFechaEmision']").val("");
			$("divVehiculoArchivos input[name='txtFechaCaducidad']").val("");
			//alert(123);
			$("img[class='imgFotosVehiculo']").attr("src","images/no_disponible.jpg");
    		$("#txtCodigoVehiculo").val(data.unidadEmpresa.unecodigoD);
			//alert(data.empadronamiento.empfechainicioF);
    		$("#txtCodigoEmpadronamiento").val(data.empadronamiento.epocodigoD);
			$("#txtEmpadFechaInicio").val(data.empadronamiento.empfechainicioF);
			$("#txtEmpadFechaCese").val(data.empadronamiento.empfechaceseF);
			$("#txtNroPlaca").val(data.unidadEmpresa.uneplacanroV);
			$("#sltCarroceria").val(data.unidadEmpresa.unecarroceriaC);
			$("#sltOfRegistral").val(data.unidadEmpresa.oficina.oficodigoI);
			$("#txtNroSerieChasis").val(data.unidadEmpresa.unenroseriechasisV);
			$("#txtNroPadron").val(data.unidadEmpresa.unenropadronV);
			$("#txtNroMotor").val(data.unidadEmpresa.unenromotorV);
			$("#txtPartRegistral").val(data.unidadEmpresa.unenropartidaregistralV);
			$("#txtNroNiv").val(data.unidadEmpresa.unenroidentificacionV);
			$("#txtTitulo").val(data.unidadEmpresa.unetituloV);
			$("#txtRuedas").val(data.unidadEmpresa.unenroruedasI);
			$("#txtClase").val(data.unidadEmpresa.uneclaseV);
			$("#txtNroAsientos").val(data.unidadEmpresa.unenroasientosI);
			$("#sltMarca").val(data.unidadEmpresa.marca.marcodigoI);
			$("#txtNroPasajeros").val(data.unidadEmpresa.unenropasajerosI);
			$("#sltModelo").val(data.unidadEmpresa.modelo.modcodigo_D);
			$("#txtCargaUtil").val(data.unidadEmpresa.unecargautilD);
			$("#txtAno").val($.trim(data.unidadEmpresa.uneannoC));
			$("#txtLongitud").val(data.unidadEmpresa.unelongitudD);
			$("#txtColor").val(data.unidadEmpresa.unecolorV);
			$("#txtAncho").val(data.unidadEmpresa.uneanchoD);
			$("#sltCombustible").val(data.unidadEmpresa.unecombustibleC);
			$("#txtAlto").val(data.unidadEmpresa.unealtoD);
			for(var x=0;x<data.documentos.length;x++){
				$("#documentoUnd_"+data.documentos[x].tipoDocumento.mtdcodigoI).val(data.documentos[x].adjuntarArchivo.adjcodigoD);
        		$("#txtUndNumDocumento_"+data.documentos[x].tipoDocumento.mtdcodigoI).val(data.documentos[x].adjuntarArchivo.adjnumeroV);
        		$("#txtUndFechaEmision_"+data.documentos[x].tipoDocumento.mtdcodigoI).val(data.documentos[x].adjuntarArchivo.adjfechaemisionF);
        		$("#txtUndFechaCaducidad_"+data.documentos[x].tipoDocumento.mtdcodigoI).val(data.documentos[x].adjuntarArchivo.adjfechacaducidadF);
			}
			for(var f=0;f<data.fotos.length;f++){
	    		$("#imgFotoVehiculo_"+data.fotos[f].tipoDocumento.mtdcodigoI).attr("src","temp/"+data.fotos[f].adjuntarArchivo.adjnombreV);	
			}
			$("#btnVehiculoProcesar").val("Modificar");
		}else{
			limpiarVehiculo();
		}
	}
	
	function limpiarVehiculo(){
		$("divVehiculoArchivos input[name='txtCodArchivo']").val("0");
		$("divVehiculoArchivos input[name='txtNumDocumento']").val("");
		$("divVehiculoArchivos input[name='txtFechaEmision']").val("");
		$("divVehiculoArchivos input[name='txtFechaCaducidad']").val("");
		
		$("img[class='imgFotosVehiculo']").attr("src","images/no_disponible.jpg");
		$("#txtCodigoVehiculo").val("");
		$("#txtCodigoEmpadronamiento").val("");
		$("#txtEmpadFechaInicio").val("");
		$("#txtEmpadFechaCese").val("");
		$("#txtNroPlaca").val("");
		$("#sltCarroceria").val("");
		$("#sltOfRegistral").val("");
		$("#txtNroSerieChasis").val("");
		$("#txtNroPadron").val("");
		$("#txtNroMotor").val("");
		$("#txtPartRegistral").val("");
		$("#txtNroNiv").val("");
		$("#txtTitulo").val("");
		$("#txtRuedas").val("");
		$("#txtClase").val("");
		$("#txtNroAsientos").val("");
		$("#sltMarca").val("");
		$("#txtNroPasajeros").val("");
		$("#sltModelo").val("");
		$("#txtCargaUtil").val("");
		$("#txtAno").val("");
		$("#txtLongitud").val("");
		$("#txtColor").val("");
		$("#txtAncho").val("");
		$("#sltCombustible").val("");
		$("#txtAlto").val("");
		$(".txtVehNumDocumento").val("");
		$(".txtVehFecEmision").val("");
		$(".txtVehFecCaducidad").val("");
		$("#btnVehiculoProcesar").val("Insertar");
	}
	
	function fncCancelar(){
		$("#divFormulario").dialog("close");
	}
});