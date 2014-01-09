$(document).ready(function(){

	$("#divNuevoOperativo").hide();
    $(".dtFecha").datepicker({dateFormat:"dd/mm/yy"});
    
    $('#txtHora').timepicker();//$('#fechaNueva').datetimepicker(); esto sirve para mostrar la hora
    
    $("#btnCancelar").click(function(){
		$("#divNuevoOperativo").dialog("close");
    });

	buscar("OP.OPETITULO_V", "");
		
	$("#btnBuscar").click(function(){
    	buscar($("#sltCriterio").val(),$("#txtTexto").val());
    	$.message.Find();
    });
	
	$("input[type=text]").keyup(function(){
	  $(this).val( $(this).val().toUpperCase() );
	});
	
	function buscar(criterio,texto){
		$.ajax({ 
    		data:{
    			criterio:criterio,
    			texto:texto
    		},
            datatype:'json',
            type: "POST", 
            url: "Operativos/Listar.htm", 
            success: function(data){
            	//alert(JSON.stringify(data));
            	llenarTabla(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Delete();
            }
    	});
	}
	
	function llenarTabla(data){
		//alert(JSON.stringify(data));
    	if(data==""){ 
    		$("#tblLista").empty();
    		return;
    	}
    	var txtHtml="";
    	$("#tblLista").empty();
    	txtHtml="<thead>"
			+"<th class='header'>NUM</th>"
			+"<th class='header'>OPERATIVO</th>"
			+"<th class='header'>ZONA</th>"
			+"<th class='header'>LUGAR</th>"
			+"<th class='header'>REFERENCIA</th>"
			+"<th class='header'>FECHA</th>"
			+"<th class='header'>HORA</th>"
			+"<th class='header'>RESPONSABLE</th>"
			+"<th class='header'></th>"
			+"<th class='header'></th>"
			+"<th class='header'></th>"
			+"</thead>"
			+"<tfoot>"
			+"<th>NUM</th>"
			+"<th>NOMBRE OPERATIVO</th>"
			+"<th>ZONA ADMINISTRATIVA</th>"
			+"<th>LUGAR</th>"
			+"<th>REFERENCIA</th>"
			+"<th>FECHA</th>"
			+"<th>HORA</th>"
			+"<th>RESPONSABLE</th>"
			+"<th></th>"
			+"<th></th>"
			+"<th></th>"
			+"</tfoot>"
			+"<tbody></tbody>";
			$("#tblLista").append(txtHtml);
    	for(var x=0;x<data.length;x++){
    		txtHtml="<tr>"+
			"<td>"+(x+1)+"</td>"+
			"<td>"+data[x].operativo.opetituloV+"</td>"+
			"<td>"+data[x].operativo.zona.zonnombre_V +"</td>"+
			//"<td>"+data[x].operativo.opedescripcionV +"</td>"+
			"<td>"+data[x].operativo.opelugarV +"</td>"+
			"<td>"+data[x].operativo.opereferencia+"</td>"+
			"<td>"+data[x].operativo.opefecha +"</td>"+
			"<td>"+data[x].operativo.opehora +"</td>"+
			"<td>"+data[x].operativo.inspector.persona.pernombresV
			+" "+data[x].operativo.inspector.persona.perpaternoV
			+" "+data[x].operativo.inspector.persona.permaternoV+"</td>"+
			"<td>"+data[x].operativo.estado +"</td>"+
			"<td><img alt='Modificar' class='btnModificar' id='mod"+data[x].operativo.opecodigoD+"' src='images/edit.png'></td>"+
			"<td><img alt='Eliminar' class='btnEliminar' id='del"+data[x].operativo.opecodigoD+"' src='images/delete.png'></td>"+
    		"</tr>";
    		$("#tblLista tbody").append(txtHtml);
    	}

		$(".btnModificar").click(modificar);
    	$(".btnEliminar").click(eliminar);
    	$("#tblLista").paginacion();
    }
    
    //FIN LISTADO GENERAL
    
    //MOSTRAR NUEVO OPERATIVO
    
    $(function(){
    	$('#txtHora').timepicker();//$('#fechaNueva').datetimepicker(); esto sirve para mostrar la hora
    	$('#txtHoraFin').timepicker();
    });
    
    $("#btnNuevOperativo").click(function(){
    	llenarFormulario("");
    });
    
    function llenarFormulario(data)
    {
    	if(data!=""){	
    		$("#txtCodigoOperativo").val(data.opecodigoD);
    		$("#txtNombreOperativo").val(data.opetituloV);
    		$("#txtDescripcion").val(data.opedescripcionV);
    		$("#txtDireccion").val(data.opelugarV);
    		$("#txtReferencia").val(data.opereferencia);
    		$("#sltZona").val(data.zona.zoncodigo_I);
    		$("#txtFecha").val(data.opefecha);
    		$("#txtHora").val(data.opehora);
    		$("#txtHoraFin").val(data.opehorafin);
    		$("#sltResponsable").val(data.inspector.inscodigoI);
    		listarResponsablesNotIn(data.inspector.inscodigoI);
    	   	listarInpectorxOperativo(data.opecodigoD);
    	   	//alert("txtcodigoOperativo : " + $("#txtCodigoOperativo").val());
       	}else{
       		$("#txtCodigoOperativo").val("0");
       		$("#txtNombreOperativo").val("");
          	$("#sltZona").val("");
          	$("#sltAgregaInspector").empty();
          	$("#txtDescripcion").val("");
          	$("#txtDireccion").val("");
          	$("#txtReferencia").val("");
          	$("#txtFecha").val("");
          	$("#txtHora").val("");
          	$("#txtHoraFin").val("");
       	}   
    	
    	$("#divNuevoOperativo").show();
    	$("#divNuevoOperativo").dialog({
    		title:"Operativo",
    		width:800,
    		//height: 550,
    		modal: true	
    	});
    	
    }

  //FIN MOSTRAR NUEVO OPERATIVO
    
    //MOVER ELEMENTOS DE LOS LISTBOX
    
    $('#btnRight').click(function(e){
    	var selectedOpts = $('#sltInspectores option:selected');
    	if(selectedOpts.length == 0){
    		alert("Elija un item correcto.");
    		e.preventDefault();
    	}
    	
    	$.ajax({ 
    		data:{
    			codigo:$("#txtCodigoOperativo").val(),
    			codins:parseFloat($("#sltInspectores").val()),
    			lado:">"
    		},
           datatype:'json',
           type: "POST", 
           url: "Operativos/ActualizarEstadoIns.htm",
           success: function(data){
				mensaje(data);
           },error: function(jqXHR, textStatus, errorThrown){
           		$.message.Delete();
           }
    	});
    	
    	$('#sltAgregaInspector').append($(selectedOpts).clone());
    	$(selectedOpts).remove();
    	e.preventDefault();
    	   	
    });
    
    $('#btnLeft').click(function(e){
    	var selectedOpts = $('#sltAgregaInspector option:selected');
    	if(selectedOpts.length == 0){
    		alert("Elija un item correcto.");
    		e.preventDefault();
    	}
    	
    	$.ajax({ 
    		data:{
    			codigo:$("#txtCodigoOperativo").val(),
    			codins:parseFloat($("#sltAgregaInspector").val()),
    			lado:"<"
    		},
           datatype:'json',
           type: "POST", 
           url: "Operativos/ActualizarEstadoIns.htm",
           success: function(data){
				mensaje(data);
           },error: function(jqXHR, textStatus, errorThrown){
           	$.message.Delete();
           }
    	});
    	
    	$('#sltInspectores').append($(selectedOpts).clone());
    	$(selectedOpts).remove();
    	e.preventDefault();
    });
    //FIN MOVER ELEMENTOS DE LOS LISTBOX
    
    //GUARDAR OPERATIVOS
    $("#btnGuardar").click(function(){

    	$("#divNuevoOperativo").validate();
    	//alert("entro a guardar");
		$("#sltAgregaInspector option").prop('selected', true);
		var inspectorList=new Object();
		inspectorList.operativo=new Object();
		inspectorList.inspectores=new Array();
		
		var inspector=null; var cont=0;
		inspectorList.operativo.opecodigoD=$("#txtCodigoOperativo").val();
		inspectorList.operativo.opetituloV=$("#txtNombreOperativo").val();
		inspectorList.operativo.opedescripcionV=$("#txtDescripcion").val();
		inspectorList.operativo.opelugarV=$("#txtDireccion").val();
		inspectorList.operativo.opereferencia=$("#txtReferencia").val();
		inspectorList.operativo.opefecha=$("#txtFecha").val();
		inspectorList.operativo.opehora=$("#txtHora").val();
		inspectorList.operativo.opehorafin=$("#txtHoraFin").val();
		inspectorList.operativo.zona= new Object();
		inspectorList.operativo.zona.zoncodigo_I=$("#sltZona").val();
		inspectorList.operativo.inspector = new Object();
		inspectorList.operativo.inspector.inscodigoI=$("#sltResponsable").val();
		//alert("btnGuardar :" +inspectorList.operativo.opecodigoD);
		
		var sel=$("#sltAgregaInspector").val();
		$.each(sel, function(key,value){	
			inspector=new Object();
			inspector.inscodigoI=value;
			inspectorList.inspectores[cont++]=inspector;
        });
		
		//alert(JSON.stringify(inspectorList));
		$.ajax({ 
    		data: JSON.stringify(inspectorList),
    		datatype:'json',
            type: "POST", 
            contentType : "application/json",
            url: "Operativos/Procesar.htm", 
            success: function(data){;
            	//alert(JSON.stringify(inspectorList));
            	$("#sltAgregaInspector option").prop('selected', false);
        		buscar("OP.OPECODIGO_D",data);
        		$("#divNuevoOperativo").dialog('close');
        		$.message.Success();
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Delete();
            }
    	});
		$("#sltAgregaInspector option").prop('selected', false);
		//buscar($("#sltCriterio").val(),$("#txtTexto").val());
    	//$(this).dialog('close');
	});
    /*
    $("#btnGuardar").click(function(){
    	$.ajax({ 
			data:{
				'opecodigoD':$("#txtCodigoOperativo").val(),
				'opetituloV':$("#txtNombreOperativo").val(),
				'opedescripcionV':$("#txtDescripcion").val(),
				'opelugarV':$("#txtDireccion").val(),
				'opereferencia':$("#txtReferencia").val(),
				'opefecha':$("#txtFecha").val(),
				'opehora':$("#txtHora").val(),
				'zona.zoncodigo_I':$("#sltZona").val(),
				'inspector.inscodigoI':$("#sltResponsable").val()
			},
		    datatype:'json',
	        type: "POST", 
	        url: "Operativos/Procesar.htm", 
	        success: function(data){
	        	//alert(JSON.stringify(data));
	        	$("#txtCodigoOperativo").val(opecodigoD);
	        	txtHtml="<p>Operación realizada correctamente</p>";
	        	mensaje(txtHtml);
	        },error: function(jqXHR, textStatus, errorThrown){
	        	$.message.Delete();
	        }
		});
    	//$(this).dialog('close');
    	$("#divNuevoOperativo").dialog("close");
    	buscar($("#sltCriterio").val(),$("#txtTexto").val());
	});
	*/
    
    //FIN GUARDAR OPERATIVOS
    
    
    //OBTENER Y ACTUALIZAR DATOS PARA OPERATIVOS 
   function modificar(){ //para que funciones agregamos dentro de  llenarTabla(data) esto $(".btnModificar").click(modificar);
	   $.ajax({ 
			data:{
				codigo:$(this).attr("id").replace("mod","")
			},
	        datatype:'json',
	        type: "GET", 
	        url: "Operativos/Obtener.htm", 
	        success: function(data){
	        	//alert(JSON.stringify(data));
	        	llenarFormulario(data);
	        },error: function(jqXHR, textStatus, errorThrown){
	        	$.message.Delete();
	        }
		});
   }
    	
    //FIN OBTENER DATOS PARA OPERATIVOS	
    
   	//ELIMINAR LOGICAMENTE OPERATIVOS
    function eliminar(){  //para que funciones agregamos dentro de  llenarTabla(data) esto $(".btnEliminar").click(eliminar);
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
                       url: "Operativos/Eliminar.htm",
                       success: function(data){
							mensaje(data);
							buscar($("#sltCriterio").val(),$("#txtTexto").val());
				        	$(this).dialog('close');
							$.message.Delete();
                       },error: function(jqXHR, textStatus, errorThrown){
                       	$.message.Delete();
                       }
		        	});
				},
				'Cancelar': function() {
					$(this).dialog('close');
				}
			}
		});
   }
   //FIN ELIMINAR LOGICAMENTE OPERATIVOS
   
    function llenarListboxInspector(data){
    	$("#sltInspectores").empty();	
    	txtHtml="";
    	for(var x=0;x<data.length;x++){
    		txtHtml+="<option value='"+data[x].inscodigoI+"'>"+data[x].persona.perpaternoV+" "+data[x].persona.permaternoV+", "+data[x].persona.pernombresV+"</option>";
    	}
    	$("#sltInspectores").append(txtHtml);
    }
   
    $("#sltResponsable").change(function(){
    	listarResponsablesNotIn($("#sltResponsable").val());
    });
    		
    function listarResponsablesNotIn(codigo){
    	$.ajax({ 
    		data:{
    			codigo:codigo
    		},
            datatype:'json',
            type: "POST", 
            url: "Operativos/ListaInspectoresNotIn.htm",
            success: function(data){
            	//alert(JSON.stringify(data));
            	llenarListboxInspector(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Delete();
            }
    	});
    }
    
    //obtener datos a listboz
    function listarInpectorxOperativo(codigo){
    	$.ajax({ 
    		data:{
    			codigo:codigo
    		},
            datatype:'json',
            type: "GET", 
            url: "Operativos/ObtenerInspector.htm",
            success: function(data){
            	llenarInspectores(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	$.message.Delete();
            }
    	});
    }
    
    function llenarInspectores(data){	
    	//LimpiarSelect('sltAgregaInspector', 'AgregaInspector');
    	$("#sltAgregaInspector").empty();
    	txtHtml="";
    	for(var x=0;x<data.length;x++){
    		txtHtml+="<option value='"+data[x].operativo.inspector.inscodigoI+"'>"+data[x].operativo.inspector.persona.perpaternoV+" "+
    				" "+data[x].operativo.inspector.persona.permaternoV+
    				", "+data[x].operativo.inspector.persona.pernombresV+"</option>";
    	}
    	//$("#sltAgregaInspector").find("option[value='"+data[x].operativo.inspector.inscodigoI+"']").remove();
    	$("#sltAgregaInspector").append(txtHtml);
    	
    	
    	$("#sltInspectores option").prop('selected', true);
		var sel=$("#sltInspectores").val();
		for(var x=0;x<data.length;x++){
		    	
			$.each(sel, function(key,value){
				if(data[x].operativo.inspector.inscodigoI==value){
					$("#sltInspectores").find("option[value='"+data[x].operativo.inspector.inscodigoI+"']").remove();
				}
				
	        });
		}
    }
    /*
    $("#btnCancelar").click(function(){//SUPONGAMOS QUE VA A LA DERECHA
    	$.ajax({ 
    		data:{
    			codigo:$("#txtCodigoOperativo").val(),
    			codins:parseFloat($("#sltInspectores").val()),
    			lado:">"
    		},
           datatype:'json',
           type: "POST", 
           url: "Operativos/ActualizarEstadoIns.htm",
           success: function(data){
				mensaje(data);
           },error: function(jqXHR, textStatus, errorThrown){
           	$.message.Delete();
           }
    	});
    	
    });*/    
    
});	
