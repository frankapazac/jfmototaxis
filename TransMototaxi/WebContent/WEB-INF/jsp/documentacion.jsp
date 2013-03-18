<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/jsp/documentacion.js"></script>
<script type="text/javascript" src="js/ui/jquery.ui.datepicker.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.core.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.widget.js"></script>

<title>Insert title here</title>
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
							
							
							<tr>
							<td>CONDUCTORES CON ALTA ROTACIÓN</td>
							<td></td>						
							<td><input type="button" id="btnImprimirRotacion" value="Visualizar"/></td>
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
							<td><input type="text" id="txtfecha2b" class="requiredDate txtfecha"s/></td>
							<td><input type="button" id="btnImprimirBaja" value="Visualizar"/></td>
							</tr>
			 </table>
			 </fieldset>
		</div>
		
		
	</div>
	


</body>
</html>