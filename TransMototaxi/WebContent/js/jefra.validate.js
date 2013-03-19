(function($){
	$.fn.validate = function() {
		var requires=$(this).find("input[class*='required'],select[class*='required']");
		$.each(requires,function(key,value){
			alert(value);
			/*if($(this).prop("tagName")=='INPUT'){
				
			}else if($(this).prop("tagName")=='SELECT'){
				
			}*/
		});
		//alert(requires.length);
	};
	 
	function imprimir(texto,tipo){
		var filter=null;
		switch(tipo){
			case 'texto': filter = /^[A-Za-z\_\-\.\s\xF1\xD1]+$/; break;
			case 'enteros': filter = /^(?:\+|-)?\d+$/; break;
			case 'decimales': filter= /^\d{1,2}\/\d{1,2}\/\d{2,4}$/; break;
			case 'celular': break;
			case 'email': filter = /[\w-\.]{3,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/; break;
			case 'alfanumerico': break;
			case 'horas': filter = /^[0-2][0-9]:[0-5][0-9]$/;
			case 'fechas': filter = /^\d{1,2}\/\d{1,2}\/\d{2,4}$/;
		}
		if(filter.test(texto))
	        return true;
	    else
	        return false;
	}		
})(jQuery);