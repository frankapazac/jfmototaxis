$(document).ready(function(){
	$("#divMensaje").hide();
	$("#divFormulario").hide();
	
	
	buscar("PAR.PARCODIGO_I", "");
	
	$("#btnBuscar").click(function(){
    	buscar($("#sltCriterio").val(),$("#txtTexto").val());
    });
	
    $("#btnNuevo").click(function(){
    	$("#divFormulario").dialog({
    		title:"Nuevo paradero",
    		width:700,
    		height: 350,
    		modal: true
    	});
    });
    
    $("#sltParadero").change(function(){
    	$.ajax({ 
    		data:{
    			codigo:$(this).val()
    		},
            datatype:'json',
            type: "POST", 
            url: "Paradero/Obtener.htm", 
            success: function(data){
            	$("#txtZonaAdministrativa").val(data.zona.zonnombre_V);
            	$("#txtUbicacion").val(data.parubicacionV);
            	$("#txtCodigo").val(data.parcodigoI);
            },error: function(jqXHR, textStatus, errorThrown){
            	mensajeError();
            }
    	});
    });
    
    $("#btnAgregar").click(function(){
    	$.ajax({
    		data:{
    			'paradero.parcodigoI':$("#txtCodigo").val()
    		},
    		datatype:'html',
    		type:"POST",
    		url: "Paradero/Agregar.htm",
    		success: function(data){  
    			
    		},error: function(jqXHR, textStatus, errorThrown){
    			mensajeError();
    		}
    	});
    	buscar("EPA.EPACODIGO_I","");
    	$("#divFormulario").dialog("close");
    });    
    
	function buscar(criterio,texto){
		$.ajax({ 
    		data:{
    			criterio:criterio,
    			texto:texto
    		},
            datatype:'json',
            type: "POST", 
            url: "Paradero/Listar.htm", 
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
			+"<th class='header'>NUM</th>"
			+"<th class='header'>NOMBRE</th>"
			+"<th class='header'>UBICACIÓN</th>"
			+"<th class='header'>ZONA ADMINISTRATIVA</th>"
			+"<th class='header'>ESTADO</th>"
			+"<th class='header'>ELIMINAR</th>"
			+"</thead>"
			+"<tfoot>"
			+"<th>NUM</th>"
			+"<th>NOMBRE</th>"
			+"<th>UBICACIÓN</th>"
			+"<th>ZONA ADMINISTRATIVA</th>"
			+"<th>ESTADO</th>"
			+"<th>ELIMINAR</th>"
			+"</tfoot>"
			+"<tbody></tbody>";
			$("#tblLista").append(txtHtml);
    	for(var x=0;x<data.length;x++){
    		txtHtml="<tr>"+
			"<td>"+(x+1)+"</td>"+
			"<td>"+data[x].parnombreV+"</td>"+
			"<td>"+data[x].parubicacionV+"</td>"+
			"<td>"+data[x].zona.zonnombre_V +"</td>"+
			"<td>"+data[x].parestadoC+"</td>"+
			"<td><img alt='Eliminar' class='btnEliminar' id='del"+data[x].parcodigoI+"' src='images/delete.png'></td>"+
    				"</tr>";
    		$("#tblLista tbody").append(txtHtml);
    	}
    	
    	$(".btnEliminar").click(eliminar);
    	paginacion();
    }
	
	function eliminar(){
    	$.ajax({
    		data:{
    			codigo: $(this).attr("id").replace("del","")
    		},
    		datatype:'html',
    		type:"POST",
    		url: "Paradero/Eliminar.htm",
    		success: function(data){    			
    		},error: function(jqXHR, textStatus, errorThrown){
    			mensajeError();
    		}
    	});
    	//alert("CODIGO: "+codigo);
    	//buscar("EMP.EMPCODIGO_D",$("#txtCodigo").val());
    	buscar("EMP.EMPCODIGO_D","");
    	paginacion();
	}
	
	
    function paginacion(){
		$("#tblLista")//.tablesorter(); 
        .tablesorter({widthFixed: true, widgets: ['zebra']}) 
        .tablesorterPager({container: $("#pager")}); 	
	}
});