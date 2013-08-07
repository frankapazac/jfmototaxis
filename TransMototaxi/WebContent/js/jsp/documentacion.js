$(document).ready(function(){
	
    $(".txtfecha").datepicker({dateFormat:"dd/mm/yy"});    
    
    $(function(){
    	$('#txtHoraInicio').timepicker();
    	$('#txtHoraTermino').timepicker();//$('#fechaNueva').datetimepicker(); esto sirve para mostrar la hora
    });
    
    
    $("#btnImprimirRotacion").click(function(){
    	var linkText="Documentos/ImprimirCondAltaRotacionPdf.htm";
    	window.open(linkText);
    });
    
    $("#divNuevoInformeDiario").hide();
    
    $("#btnImprimirINFDIARIO").click(function(){
    	llenarFormulario("");
    });
    
    function llenarFormulario(data)
    {
        $("#divNuevoInformeDiario").show();
    	$("#divNuevoInformeDiario").dialog({
    		title:"Informe Diario",
    		width:800,
    		//height: 550,
    		modal: true	
    	});
    }
    
    $("#txtFechaInicio").change(function(){
    	$.ajax({ 
    		data:{
    			criterio:$("#txtFechaInicio").val()
    		},
            datatype:'json',
            type: "POST", 
            url: "Operativos/ListarOperaticoxFecha.htm", 
            success: function(data){
            	llenarComboOperativo(data);
            	$("#sltOperativo").click();
            },error: function(jqXHR, textStatus, errorThrown){
            	mensajeError();
            }
    	});
    	
    });
    
    function llenarComboOperativo(data){
    	$("#sltOperativo").empty();
    	txtHtml="";
    	for(var x=0;x<data.length;x++){
    		txtHtml+="<option value='"+data[x].opecodigoD+"'>"+data[x].opetituloV+"</option>";
    	}
    	$("#sltOperativo").append(txtHtml);
    }
    
    
    $("#sltOperativo").click(function(){
    	$.ajax({ 
    		data:{
    			codigo:$("#sltOperativo").val()
    		},
            datatype:'json',
            type: "GET", 
            url: "Operativos/Obtener.htm", 
            success: function(data){
            	llenarDatosOperativo(data);
            },error: function(jqXHR, textStatus, errorThrown){
            	mensajeError();
            }
    	});
    });
       
    function llenarDatosOperativo(data){
    		$("#txtFechaInicio").val(data.opefecha);
    		$("#txtHoraInicio").val(data.opehora);
    		$("#txtHoraTermino").val(data.opehorafin);
    }
        
    $("#btnGuardar").click(function(){

	});
       
    $("#btnImprimirInfDiarioActa").click(function(){

    	$.ajax({ 
			data:{
				'opecodigoD':$("#sltOperativo").val(),
				'opeobservacion':$("#txtObservaciones").val(),
			},
		    datatype:'json',
	        type: "POST", 
	        url: "Operativos/Actualizar.htm", 
	        success: function(data){
	        	txtHtml="<p>Operación realizada correctamente</p>";
	        	mensaje(txtHtml);
	        },error: function(jqXHR, textStatus, errorThrown){
	        	mensajeError();
	        }
		});
    	$("#divNuevoInformeDiario").dialog("close");
     	
    	var FECHAINI=$("#txtFechaInicio");
    	var HORAINI=$("#txtHoraInicio");
    	var HORAFIN=$("#txtHoraTermino");
    	var OPECODIGO=$("#sltOperativo");
    	var INDOBSERVACION_V=$("#txtObservaciones");
    	var VISTAPREVIA=("");
    	var linkText="Operativos/ImprimirInfDiarioActa.htm?FECHAINI="+FECHAINI.val()+"&HORAINI="+HORAINI.val()
    															+"&HORAFIN="+HORAFIN.val()+"&OPECODIGO="+OPECODIGO.val()
    															+"&INDOBSERVACION_V="+INDOBSERVACION_V.val()
    															+"&VISTAPREVIA="+VISTAPREVIA.val();
    	window.open(linkText);	
    	
    });

    $("#btnImprimirVistaPrevia").click(function(){
    	var FECHAINI=$("#txtFechaInicio");
    	var HORAINI=$("#txtHoraInicio");
    	var HORAFIN=$("#txtHoraTermino");
    	var OPECODIGO=$("#sltOperativo");
    	var INDOBSERVACION_V=$("#txtObservaciones");
    	var textoVP = "OJO : VISTA PREVIA ESTE DOCUMENTO NO TIENE VALIDEZ - SGTEMI";
    	var VISTAPREVIA=textoVP;
    	var linkText="Operativos/ImprimirInfDiarioActa.htm?FECHAINI="+FECHAINI.val()+"&HORAINI="+HORAINI.val()
    															+"&HORAFIN="+HORAFIN.val()+"&OPECODIGO="+OPECODIGO.val()
    															+"&INDOBSERVACION_V="+INDOBSERVACION_V.val()
    															+"&VISTAPREVIA="+VISTAPREVIA;
    	window.open(linkText);	
    	
    });
    
    


});