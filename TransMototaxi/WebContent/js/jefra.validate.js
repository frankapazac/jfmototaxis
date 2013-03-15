(function($){
	 $.fn.validate = function() {
		 alert("entro");
			alert(elemento+" .requiredText");
			var elements=$(elemento+" .requiredText");
			alert(elements.length);
			$.each(elements,function(key,value){
				alert(key+" "+value);
			}); 
	 } 
	function validarEmail(texto){
	    var filter = /[\w-\.]{3,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/;
	    if(filter.test(texto))
	        return true;
	    else
	        return false;
	}

	function validarLetras(texto){
	    var filter = /^[A-Za-z\_\-\.\s\xF1\xD1]+$/;
	    if(filter.test(texto))
	        return true;
	    else
	        return false;
	}

	function validarNumerosEnteros(texto){
	    var filter = /^(?:\+|-)?\d+$/;
	    if(filter.test(texto))
	        return true;
	    else
	        return false;
	}	
})( jQuery );