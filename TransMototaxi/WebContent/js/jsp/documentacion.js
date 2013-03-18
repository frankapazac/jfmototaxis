$(document).ready(function(){
	
    $(".txtfecha").datepicker({dateFormat:"dd/mm/yy"});    
    
    $("#btnImprimir").click(function(){
    	var fecha1=$("#txtfecha1");
    	var fecha2=$("#txtfecha2");
    	var linkText="Documentos/ImprimirPdf.htm?FECHA1="+fecha1.val()+"&FECHA2="+fecha2.val();
    	window.open(linkText);
    });
    
    $("#btnImprimirRotacion").click(function(){
    	var linkText="Documentos/ImprimirCondAltaRotacionPdf.htm";
    	window.open(linkText);
    });
    
    $("#btnImprimirAlta").click(function(){
    	var fecha1a=$("#txtfecha1a");
    	var fecha2a=$("#txtfecha2a");
    	var linkText="Mototaxi/ImprimirMotosconAltas.htm?FECHA1="+fecha1a.val()+"&FECHA2="+fecha2a.val();
    	window.open(linkText);
    });
    
    $("#btnImprimirBaja").click(function(){
    	var fecha1=$("#txtfecha1b");
    	var fecha2=$("#txtfecha2b");
    	var linkText="Mototaxi/ImprimirMotosconBajas.htm?FECHA1="+fecha1.val()+"&FECHA2="+fecha2.val();
    	window.open(linkText);
    });
});