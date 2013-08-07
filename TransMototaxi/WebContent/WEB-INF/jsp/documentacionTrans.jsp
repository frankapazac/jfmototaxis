<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/jsp/documentacionTrans.js"></script>
<script type="text/javascript" src="js/ui/jquery.ui.datepicker.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.core.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.widget.js"></script>
	
	<script type="text/javascript" src="js/ui/jquery.ui.position.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.dialog.js"></script>	
	<script type="text/javascript" src="js/ui/jquery.ui.mouse.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.slider.js"></script>
	<script type="text/javascript" src="js/ui/jquery-ui-timepicker-addon.js"></script>

<title>Insert title here</title>
<style type="text/css">
	.ui-timepicker-div .ui-widget-header { margin-bottom: 8px; }
	.ui-timepicker-div dl { text-align: left; }
	.ui-timepicker-div dl dt { height: 25px; margin-bottom: -25px; }
	.ui-timepicker-div dl dd { margin: 0 10px 10px 65px; }
	.ui-timepicker-div td { font-size: 90%; }
	.ui-tpicker-grid-label { background: none; border: none; margin: 0; padding: 0; }
	
	.ui-timepicker-rtl{ direction: rtl; }
	.ui-timepicker-rtl dl { text-align: right; }
	.ui-timepicker-rtl dl dd { margin: 0 65px 10px 10px; }
</style>	
</head>
<body>
	<h1>Documentación</h1>
	
	<div id="divdocumentacion">
			
		<div>
			<fieldset>
	        <legend>REPORTES</legend>  
			 <table>
					<tr>
							<td>CONDUCTORES CON PAPELETA ENTRE RANGOS DE FECHAS</td>
							<td></td>						
							<td>FECHA 1</td>
							<td><input type="text" id="txtfecha1" class="requiredDate txtfecha"/></td>
							<td>FECHA 2</td>
							<td><input type="text" id="txtfecha2" class="requiredDate txtfecha"/></td>
							<td><input type="button" id="btnImprimir" value="Visualizar"/></td>
													
					</tr>
					<tr>
							<td>MOTOTAXIS DADOS DE ALTA ENTRE RANGOS DE FECHAS</td>
							<td></td>						
							<td>FECHA 1</td>
							<td><input type="text" id="txtfecha1a" class="requiredDate txtfecha" /></td>
							<td>FECHA 2</td>
							<td><input type="text" id="txtfecha2a" class="requiredDate txtfecha"/></td>
							<td><input type="button" id="btnImprimirAlta" value="Visualizar"/></td>						
					</tr>
							
					<tr>
							<td>MOTOTAXIS DADOS DE BAJA ENTRE RANGOS DE FECHAS</td>
							<td></td>						
							<td>FECHA 1</td>
							<td><input type="text" id="txtfecha1b" class="requiredDate txtfecha"/></td>
							<td>FECHA 2</td>
							<td><input type="text" id="txtfecha2b" class="requiredDate txtfecha"/></td>
							<td><input type="button" id="btnImprimirBaja" value="Visualizar"/></td>
					</tr>
			 </table>
			 </fieldset>
		</div>
	</div>
</body>
</html>