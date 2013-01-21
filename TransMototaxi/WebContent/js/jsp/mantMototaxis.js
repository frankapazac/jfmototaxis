$(document).ready(function(){
	$("#divMensaje").hide();
	$("#divFormulario").hide();
	
	
	buscar("EMP.EMPCODIGO_D", "");
	
	$("#btnBuscar").click(function(){
    	buscar($("#sltCriterio").val(),$("#txtTexto").val());
    });
	
    $("#btnNuevo").click(function(){
    	llenarFormulario("");
    });
    
	function buscar(criterio,texto){
		//salert("entrO");
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
			+"<th class='header'>N° Placa</th>"
			+"<th class='header'>Marca</th>"
			+"<th class='header'>Modelo</th>"
			+"<th class='header'>Año</th>"
			+"<th class='header'>Color</th>"
			+"<th class='header'>Fec. Registro</th>"
			+"<th class='header'>Fec. Cese</th>"
			+"<th class='header'>Mensajes</th>"
			+"</thead>"
			+"<tfoot>"
    		+"<th>N°</th>"
			+"<th>Nombres</th>"
			+"<th>DNI</th>"
			+"<th>N° Placa</th>"
			+"<th>Marca</th>"
			+"<th>Modelo</th>"
			+"<th>Año</th>"
			+"<th>Color</th>"
			+"<th>Fec. Registro</th>"
			+"<th>Fec. Cese</th>"
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
			"<td>"+data[x].mototaxi.uneplacanro_V+"</td>"+
			"<td>"+data[x].mototaxi.unemarca_V+"</td>"+
			"<td>"+data[x].mototaxi.modelo.modnombre_V+"</td>"+
			"<td>"+data[x].mototaxi.uneanno_C+"</td>"+
			"<td>"+data[x].mototaxi.unecolor_V+"</td>"+
			"<td>"+data[x].empfechainicio_F+"</td>"+
			"<td>"+data[x].empfechaceses_F+"</td>"+
			"</tr>";
    		$("#tblLista tbody").append(txtHtml);
    	}
    }

    
});