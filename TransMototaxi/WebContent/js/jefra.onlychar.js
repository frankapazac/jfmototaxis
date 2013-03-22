$(document).ready(function(){
	$("input[class='requiredText']").keydown(function() {
        /*if (test($(this).val(),'texto')) {
        }*/
    });
	
	function test(texto,tipo){
		var filter=null;
		switch(tipo){
			case 'texto': filter = /^[a-zA-Z áéíóúÁÉÍÓÚÑñ]+$/; break;
			case 'alfanumerico': filter = /^[a-zA-Z0-9 áéíóúÁÉÍÓÚÑñ]+$/; break;
			case 'enteros': filter = /^(?:\+|-)?\d+$/; break;
			case 'decimales': filter= /^-?[0-9]+([,\.][0-9]*)?$/; break;
			case 'telefono': filter = /^[0-9]{2,3}-? ?[0-9]{4,7}$/; break;
			case 'email': filter = /[\w-\.]{3,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/; break;
			case 'horas': filter = /^[0-2][0-9]:[0-5][0-9]$/; break;
			case 'fechas': filter = /^\d{1,2}\/\d{1,2}\/\d{2,4}$/; break;
		}
		if(filter.test(texto))
	        return true;
	    else
	        return false;
	}
});