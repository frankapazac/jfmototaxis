$(document).ready(function(){
	
    $("#btnImprimir").click(function(){
    	var fecha1=$("#txtfecha1");
    	var fecha2=$("#txtfecha2");
    	var linkText="Documentos/ImprimirPdf.htm?FECHA1="+fecha1.val()+"&FECHA2="+fecha2.val();
    	window.open(linkText);
    });
    
    $("#btnImprimirRotacion").click(function(){
    	alert("");
    	var linkText="Documentos/ImprimirCondAltaRotacionPdf.htm";
    	window.open(linkText);
    });
    
});