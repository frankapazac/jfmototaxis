(function($){	
	var texto=[
	   		'Ingrese caracteres',
			'Seleccione un valor',
			'Ingrese numeros',
			'Ingrese telefono/celular valido',
			'Ingrese fecha valida',
			'Ingrese correo valido',
			'Campo requerido',
			'Ingrese hora valida',
			'Ingrese caracteres/numeros',
			'Ingrese numeros/decimales'
	];
	
	$.fn.validate = function() {
		$(".error").remove();
		requires=$(this).find("input[class*='required'],select[class*='required'],textarea[class*='required']");
		/*$.each(requires,function(key,value){
			$(this).removeAttr("style");
		});*/
		var contError=0;
		$.each(requires,function(key,value){
			var tipo=$(this).attr('class');
			var error=0;
			var agregar=false;
			if(tipo.indexOf('requiredText')!=-1){
				if(!test($(this).val(),'texto')){
					agregar=true; error=0;
				}
			}else if(tipo.indexOf('requiredAlpha')!=-1){
				if(!test($(this).val(),'alfanumerico')){
					agregar=true; error=8;
				}
			}else if(tipo.indexOf('requiredSelect')!=-1){
				if($(this).val()==''||$(this).val()=='0'){
					agregar=true; error=1;
				}
			}else if(tipo.indexOf('requiredNumber')!=-1){
				if(!test($(this).val(),'enteros')){
					agregar=true; error=2;
				}
			}else if(tipo.indexOf('requiredDecimal')!=-1){
				if(!test($(this).val(),'decimales')){
					agregar=true; error=9;
				}
			}else if(tipo.indexOf('requiredPhone')!=-1){
				if(!test($(this).val(),'telefono')){
					agregar=true; error=3;
				}
			}else if(tipo.indexOf('requiredDate')!=-1){
				if(!test($(this).val(),'fechas')){
					agregar=true; error=4;
				}
			}else if(tipo.indexOf('requiredHour')!=-1){
				if(!test($(this).val(),'horas')){
					agregar=true; error=7;
				}
			}else if(tipo.indexOf('requiredEmail')!=-1){
				if(!test($(this).val(),'email')){
					agregar=true; error=5;
				}
			}else if(tipo.indexOf('required')!=-1){
				if($(this).val()==''||$(this).val()=='0'){
					$(this).after("<span class='error' style='color:red'>*</span>");
					$(this).css({'background-color':'rgb(232, 239, 255)'});
					$(this).attr('title',texto[6]);
				}
			}
			
			if(agregar==true){
				$(this).after("<span class='error' style='color:red'>*</span>");
				$(this).css({'background-color':'rgb(255, 232, 238)'});
				$(this).attr('title',texto[error]);contError++;
			}
			$(this).blur(removeError);
		});
		$(".error").css({'font-size':'20px'});
		$(".error").tooltip({track: true});
		
		$(this).bind('dialogclose',function(){
			$(".error").remove();
			var elements=$(this).find("input[class*='required'],select[class*='required']");
			$.each(elements,function(key,value){
				$(this).removeAttr("style");
			});
		});
		
		if(contError>0) evt.stopPropagation();
		//$(this).
	};	
	$.fn.validateClean = function() {
		$(".error").remove();
		var elements=$(this).find("input[class*='required'],select[class*='required']");
		$.each(elements,function(key,value){
			$(this).removeAttr("style");
		});
	};
	 
	function removeError(){
		var tipo=$(this).attr('class'); 
		var remove=false;
		if(tipo.indexOf('requiredText')!=-1){
			if(test($(this).val(),'texto')){
				remove=true;
			}
		}else if(tipo.indexOf('requiredAlpha')!=-1){
			if(test($(this).val(),'alfanumerico')){
				remove=true;
			}
		}else if(tipo.indexOf('requiredSelect')!=-1){
			if($(this).val()!=''&&$(this).val()!='0'){
				remove=true;
			}
		}else if(tipo.indexOf('requiredNumber')!=-1){
			if(test($(this).val(),'enteros')){
				remove=true;
			}
		}else if(tipo.indexOf('requiredDecimal')!=-1){
			if(test($(this).val(),'decimales')){
				remove=true;
			}
		}else if(tipo.indexOf('requiredPhone')!=-1){
			if(test($(this).val(),'telefono')){
				remove=true;
			}
		}else if(tipo.indexOf('requiredDate')!=-1){
			if(test($(this).val(),'fechas')){
				remove=true;
			}
		}else if(tipo.indexOf('requiredHour')!=-1){
			if(test($(this).val(),'horas')){
				remove=true;
			}
		}else if(tipo.indexOf('requiredEmail')!=-1){
			if(test($(this).val(),'email')){
				remove=true;
			}
		}else if(tipo.indexOf('required')!=-1){
			if($(this).val()!=''&&$(this).val()!='0'){
				remove=true;
			}
		}
		
		if(remove==true){
			$(this).removeAttr("style");
			$(this).removeAttr("title");
			$(this).next().remove();
		}
	}
	
	function test(texto,tipo){
		var filter=null;
		switch(tipo){
			case 'texto': filter = /^[a-zA-Z ·ÈÌÛ˙¡…Õ”⁄—Ò]+$/; break;
			case 'alfanumerico': filter = /^[a-zA-Z0-9 ·ÈÌÛ˙¡…Õ”⁄—Ò]+$/; break;
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
})(jQuery);