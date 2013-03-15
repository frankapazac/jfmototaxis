$(document).ready(function(){
	$("#divMensaje").hide();
	$("#divFormulario").hide();
	$("#divInformeMotoTaxi").hide();
	var impCodigo="0"; //variable global para reporte
	buscar("EMP.EMPCODIGO_D", "");
	
	$("#btnBuscar").click(function(){
    	buscar($("#sltCriterio").val(),$("#txtTexto").val());
    });
	
    $("#btnNuevo").click(function(){
    	llenarFormulario("");
    });
    //para imprimir reporte en nueva pagina
    $("#btnImprimir").click(function(){
    	window.open("Mototaxi/ImprimirPdf.htm?codigo="+impCodigo);
    });
    //fin para imprimir reporte en nueva pagina
	function buscar(criterio,texto){
		$.ajax({ 
    		data:{
    			criterio:criterio,
    			texto:texto
    		},
            datatype:'json',
            type: "POST", 
            url: "Mototaxi/Listar.htm", 
            success: function(data){
            	//alert(JSON.stringify(data));
            	//alert(data[0].parnombreV);
            	llenarTabla(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	mensajeError();
            }
    	});
	}
	
	function paginacion(){
		$("#tblLista")//.tablesorter(); 
        .tablesorter({widthFixed: true, widgets: ['zebra']}) 
        .tablesorterPager({container: $("#pager")}); 	
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
			+"<th class='header'>Nombres</th>"
			+"<th class='header'>DNI</th>"
			+"<th class='header'>Placa</th>"
			+"<th class='header'>Marca</th>"
			+"<th class='header'>Modelo</th>"
			+"<th class='header'>Año</th>"
			+"<th class='header'>Color</th>"
			+"<th class='header'>Registro</th>"
			+"<th class='header'>Cese</th>"
			+"<th class='header'>Mensajes</th>"
			+"</thead>"
			+"<tfoot>"
    		+"<th>N°</th>"
			+"<th>Nombres</th>"
			+"<th>DNI</th>"
			+"<th>Placa</th>"
			+"<th>Marca</th>"
			+"<th>Modelo</th>"
			+"<th>Año</th>"
			+"<th>Color</th>"
			+"<th>Registro</th>"
			+"<th>Cese</th>"
			+"<th>Mensajes</th>"
			+"</tfoot>"
			+"<tbody></tbody>";
			$("#tblLista").append(txtHtml);
    	for(var x=0;x<data.length;x++){
    		txtHtml="<tr>"+
			"<td>"+(x+1)+"</td>"+
			"<td>"+data[x].asociado.persona.pernombresV
			+" "+data[x].asociado.persona.perpaternoV
			+" "+data[x].asociado.persona.permaternoV+"</td>"+
			"<td>"+data[x].asociado.persona.perdniV+"</td>"+
			"<td>"+data[x].unidadEmpresa.uneplacanroV+"</td>"+
			"<td>"+data[x].unidadEmpresa.marca.marnombreV+"</td>"+
			"<td>"+data[x].unidadEmpresa.modelo.modnombre_V+"</td>"+
			"<td>"+data[x].unidadEmpresa.uneannoC+"</td>"+
			"<td>"+data[x].unidadEmpresa.unecolorV+"</td>"+
			"<td>"+data[x].empfechainicioF+"</td>"+
			"<td>"+data[x].empfechaceseF+"</td>"+
			"<td><img alt='Informe' class='btnInforme' id='mod"+data[x].propUnidadEmpresa.pmocodigoD+"' src='images/edit.png'></td>"+
			"</tr>";
    		$("#tblLista tbody").append(txtHtml);
    		//alert(data[x].propUnidadEmpresa.pmocodigoD);
    	}
    	$(".btnInforme").click(mostrarInforme);
    	paginacion();
    }

	
	   function mostrarInforme(){
		   llenarFormulario("");
		   var codigo=$(this).attr("id").replace("mod","");
		   //Asignando variable global al reporte
		   impCodigo=codigo;
		   $.ajax({ 
				data:{
					codigo:codigo
				},
		        datatype:'json',
		        type: "GET", 
		        url: "Mototaxi/ObtenerInforme.htm", 
		        success: function(data){
		        	//alert(JSON.stringify(data));
		        	llenarFormulario(data);
		        	llenarPapeleta(codigo);
		        	llenarConductor(codigo);
		        },error: function(jqXHR, textStatus, errorThrown){
		        	mensajeError();
		        }
			});
		   
	   }
	   
	    function llenarFormulario(data)
	    {
	    	if(data!=""){	
	    		//alert(data.unidadEmpresa.unetituloV);
	    		$("#txtNombresPropietario").text(data.asociado.persona.pernombresV);
	    		$("#txtdni").text(data.asociado.persona.perdniV);
	    		$("#txtdireccion").text(data.asociado.persona.perdomicilioV);
	    		$("#txtDistrito").text(data.asociado.persona.perubidistnombreV);
	    		$("#txtTeléfono").text(data.asociado.persona.perteleffijoV);
	    		$("#txtprovincia").text(data.asociado.persona.perubprovnombreV);
	    		$("#txtmovClaro").text(data.asociado.persona.permovilclaV);
	    		$("#txtdepartamento").text(data.asociado.persona.perubdptonombreV);
	    		$("#txtmovNextel").text(data.asociado.persona.permovilnexV);
	    		$("#txtCorreo").text(data.asociado.persona.peremailV);
	    		$("#txtmovMovistar").text(data.asociado.persona.permovilmovV);
	    		$("#txtplaca").text(data.unidadEmpresa.uneplacanroV);
	    		$("#txttitulo").text(data.unidadEmpresa.unetituloV);
	    		$("#txtclase").text(data.unidadEmpresa.uneclaseV);
	    		$("#txtnroRuedas").text(data.unidadEmpresa.unenroruedasI);
	    		$("#txtmarca").text(data.unidadEmpresa.marca.marnombreV);
	    		$("#txtmodelo").text(data.unidadEmpresa.modelo.modnombre_V);
	    		$("#txtanno").text(data.unidadEmpresa.marca.marnombreV);
	    		$("#txtcolor").text(data.unidadEmpresa.marca.marnombreV);
	    		$("#txtcombustible").text(data.unidadEmpresa.unecombustibleC);
	    		$("#txtcarroceria").text("TRIMOTO DE PASAJEROS");
	    		$("#txtnroserie").text(data.unidadEmpresa.unenroseriechasisV);
	    		$("#txtnromotor").text(data.unidadEmpresa.unenromotorV);
	    		$("#txtnroasientos").text(data.unidadEmpresa.unenroasientosI);
	    		$("#txtnropasajeros").text(data.unidadEmpresa.unenropasajerosI);
	    		$("#txtcargautil").text(data.unidadEmpresa.unecargautilD);
	    		$("#txtlongitud").text(data.unidadEmpresa.unelongitudD);
	    		$("#txtancho").text(data.unidadEmpresa.uneanchoD);
	    		$("#txtalto").text(data.unidadEmpresa.unealtoD);
	    	}
	    	$("#divInformeMotoTaxi").show();
	    	$("#divInformeMotoTaxi").dialog({
	    		title:"Informe",
	    		width:1200,
	    		//height: 800,
	    		modal: true	
	    	});
	    	
	    }
	    
	    //llenando papeletas
	    
		function llenarPapeleta(codigo){
			$.ajax({ 
	    		data:{
	    			codigo:codigo
	    		},
	            datatype:'json',
	            type: "GET", 
	            url: "Papeletas/ListaPapeletaxCodigo.htm", 
	            success: function(data){
	            	llenarTabla2(data);
	            },error: function(jqXHR, textStatus, errorThrown){
	            	mensajeError();
	            }
	    	});
		}
		
		function paginacion2(){
			$("#tblListaPapeleta")
	        .tablesorter({widthFixed: true, widgets: ['zebra']}) 
	        .tablesorterPager({container: $("#pager2")}); 	
		}
		
	    function llenarTabla2(data){
	    	if(data==""){ 
	    		$("#tblListaPapeleta").empty();
	    		return;
	    	}
	    	var txtHtml="";
	    	$("#tblListaPapeleta").empty();
	    	txtHtml="<thead>"
	    		+"<th class='header'>N°</th>"
				+"<th class='header'>Fecha</th>"
				+"<th class='header'>Hora</th>"
				+"<th class='header'>Cod.Inf.</th>"
				+"<th class='header'>Tipo</th>"
				+"<th class='header'>N° Papeleta</th>"
				+"<th class='header'>Conductor</th>"
				+"<th class='header'>Inspector</th>"
				+"<th class='header'>Estado</th>"
				+"</thead>"
				+"<tfoot>"
	    		+"<th>N°</th>"
				+"<th>Fecha</th>"
				+"<th>Hora</th>"
				+"<th>Cod.Inf.</th>"
				+"<th>Tipo</th>"
				+"<th>N° Papeleta</th>"
				+"<th>Conductor</th>"
				+"<th>Inspector</th>"
				+"<th>Estado</th>"
				+"</tfoot>"
				+"<tbody></tbody>";
				$("#tblListaPapeleta").append(txtHtml);
	    	for(var x=0;x<data.length;x++){
	    		txtHtml="<tr>"+
				"<td>"+(x+1)+"</td>"+
				"<td>"+data[x].papfechainfraccionF+"</td>"+
				"<td>"+data[x].paphorainfraccionF+"</td>"+
				"<td>"+data[x].infrMedida.infraccion.infcodigoV+"</td>"+
				"<td>"+data[x].infrMedida.infraccion.inftipoC+"</td>"+
				"<td>"+data[x].papnumeroV+"</td>"+
				"<td>"+data[x].conductor.persona.pernombresV
				+" "+data[x].conductor.persona.perpaternoV
				+" "+data[x].conductor.persona.permaternoV+"</td>"+
				"<td>"+data[x].inspector.persona.pernombresV
				+" "+data[x].inspector.persona.perpaternoV
				+" "+data[x].inspector.persona.permaternoV+"</td>"+
				"<td>"+data[x].papestadoC+"</td>"+
				//"<td><img alt='Informe' class='btnInforme' id='mod"+data[x].propUnidadEmpresa.pmocodigoD+"' src='images/edit.png'></td>"+
				"</tr>";
	    		$("#tblListaPapeleta tbody").append(txtHtml);
	    		//alert(data[x].propUnidadEmpresa.pmocodigoD);
	    	}
	    	paginacion2();   	
	    }
	    
	    //llenar conductores
		function llenarConductor(codigo){
			$.ajax({ 
	    		data:{
	    			codigo:codigo
	    		},
	            datatype:'json',
	            type: "GET", 
	            url: "Papeletas/ListaConductorxVehiculo.htm", 
	            success: function(data){
	            	//alert(JSON.stringify(data));
	            	llenarTabla3(data);
	            },error: function(jqXHR, textStatus, errorThrown){
	            	mensajeError();
	            }
	    	});
		}
		
		function llenarTabla3(data){
	    	if(data==""){ 
	    		$("#tblListaConductor").empty();
	    		return;
	    	}
	    	var txtHtml="";
	    	$("#tblListaConductor").empty();
	    	txtHtml="<thead>"
	    		+"<th class='header'>N°</th>"
				+"<th class='header'>Conductor</th>"
				+"<th class='header'>DNI</th>"
				+"<th class='header'>Inicio</th>"
				+"<th class='header'>Cese</th>"
				+"<th class='header'>Estado</th>"
				+"</thead>"
				+"<tfoot>"
	    		+"<th>N°</th>"
				+"<th>Conductor</th>"
				+"<th>DNI</th>"
				+"<th>Inicio</th>"
				+"<th>Cese</th>"
				+"<th>Estado</th>"
				+"</tfoot>"
				+"<tbody></tbody>";
				$("#tblListaConductor").append(txtHtml);
	    	for(var x=0;x<data.length;x++){
	    		txtHtml="<tr>"+
				"<td>"+(x+1)+"</td>"+
				"<td>"+data[x].conductor.persona.pernombresV+"</td>"+
				"<td>"+data[x].conductor.persona.perdniV+"</td>"+
				+" "+data[x].conductor.persona.perpaternoV
				+" "+data[x].conductor.persona.permaternoV+"</td>"+
				"<td>"+data[x].ucofechainicioF+"</td>"+
				"<td>"+data[x].ucofechacese+"</td>"+
				"<td>"+data[x].ucoestadoC+"</td>"+
				"</tr>";
	    		$("#tblListaConductor tbody").append(txtHtml);
	    	} 	
	    	paginacion3();  
	    }
		
		function paginacion3(){
			$("#tblListaConductor")
	        .tablesorter({widthFixed: true, widgets: ['zebra']}) 
	        .tablesorterPager({container: $("#pager3")}); 	
		}
	    $("#btnCerrar").click(function(){
	    	$("#divInformeMotoTaxi").dialog("close");
	    });
});