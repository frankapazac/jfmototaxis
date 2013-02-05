$(document).ready(function(){
	$("#divConfiguracion").accordion();
	$("#btnGuardar").click(fncActualizar);
	$(".formFotosEmpresa").submit(fncEnviarFotos);
	$("#btnActualizarClave").click(fncActualizarPass);
	
	function fncEnviarFotos(){
		var options={
    			type: "POST", 
                url:"EmpresaDocumento/Agregar.htm",
                dataType:'html',
                beforeSubmit:function(){
                    //$("#progressbar").show();
                },
                uploadProgress: function(event, position, total, percentComplete) {
                	//$("#txtCargandoUnidad").empty();
                	//$("#txtCargandoUnidad").val(percentComplete);
                },
                success: function(responseText, statusText) {      
                } ,
                error:function(){
                }
            };
            $(this).ajaxSubmit(options);
            return false;
	}
	
	
	function fncActualizarPass(){
		if($("#txtpassNuevo").val()!=$("#txtpassConfirma").val()){
			alert("no coinciden las contraseñas");
			return;
		}
		$.ajax({ 
    		data:{
    			usuusuarioV:$("#lblnomUsuario").text(),
    			pass:$("#txtpassAnterior").val(),
    			newPass:$("#txtpassNuevo").val()
    		},
    		datatype:'json',
            type: "POST", 
            url: "Actualizar.htm",   
            success: function(data){
                alert(data),
            	alert("Actualización exitosa");
            },error: function(jqXHR, textStatus, errorThrown){
            	//mensajeError();
            }
    	});
		
	}
	
	function fncActualizar(){
		alert("hola");
		$.ajax({ 
    		data:{
    			empcodigoD:$("#txtcodEmp").val(),
    			emptelefono1V:$("#txtTelefono1").val(),
    			emptelefono2V:$("#txtTelefono2").val(),
    			empcelularclaV:$("#txtCelClaro").val(),
    			empcelularmovV:$("#txtCelMov").val(),
    			empcelularnexV:$("#txtCelNex").val(),
    			empmailV:$("#txtCorreo").val(),
    			emppagwebV:$("#txtPag").val()
    		},
    		datatype:'json',
            type: "POST", 
            url: "Configuracion/Actualizar.htm", 
            success: function(data){
                alert(data),
            	alert("Insersion exitosa");
            },error: function(jqXHR, textStatus, errorThrown){
            	mensajeError();
            }
    	});
	}
/*
 * 
 * 
 *	$("#btnBuscar").click(function(){
    	buscar($("#sltCriterio").val(),$("#txtTexto").val());
    });
 * */
});