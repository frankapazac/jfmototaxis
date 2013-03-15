<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/jsp/documentacion.js"></script>

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
							<td><input type="text" id="txtfecha1"/></td>
							<td>FECHA 2</td>
							<td><input type="text" id="txtfecha2"/></td>
							<td><input type="button" id="btnImprimir" value="Visualizar" style="left"/></td>
							
							
							<tr>
							<td>CONDUCTORES CON ALTA ROTACIÓN</td>
							<td></td>						
							<td><input type="button" id="btnImprimirRotacion" value="Visualizar" style="left"/></td>
			
					</tr>
			 </table>
			 </fieldset>
		</div>
		
		
	</div>
	


</body>
</html>