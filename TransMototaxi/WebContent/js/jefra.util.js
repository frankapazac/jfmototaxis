(function($){	
	var classTr="";
	
	$.fn.paginacion = function(){
		$(this).
		tablesorter({widthFixed: true, widgets: ['zebra']}) 
        .tablesorterPager({container: $("#pager")});
		$(this).find("tbody tr").click(seleccionarFila);
	};
	
	$.fn.paginacionPager = function(id){
		$(this).
		tablesorter({widthFixed: true, widgets: ['zebra']})
        .tablesorterPager({container: $(id)});
		$(this).find("tbody tr").click(seleccionarFila);
	};
	
	function seleccionarFila(){ 
		$(".jfhover").attr('class',classTr);
		classTr=$(this).attr('class');
		$(this).attr('class',classTr.replace("odd","jfhover").replace('even','jfhover'));
	}
})(jQuery);