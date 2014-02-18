<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/jsp/documentacion.js"></script>
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
							<td>CONDUCTORES CON ALTA ROTACIÓN</td>
							<td></td>
							<td><input type="button" id="btnImprimirRotacion" value="Visualizar" class="ui-button"/></td>
					</tr>
			 </table>
			 </fieldset>
		</div>
		
		<div>
			<fieldset>
	        <legend>INFORMES</legend>  
			 <table>
					<tr>
							<td>HOJA DE INFORME DIARIO Y ACTA DE INTERVENCIÓN</td>
							<td></td>						
							<td><input type="button" id="btnImprimirINFDIARIO" value="Elaborar" class="ui-button"/></td>
					</tr>
			 </table>
			 </fieldset>
		</div>
		
		
	</div>

		
		<div id="divNuevoInformeDiario">
	    	<fieldset>
	        	<legend>NUEVO INFORME DIARIO</legend>     
	        	<input type="hidden" id="" value="0"/>        
	        	<table align="center">
	        			
						
						<tr>
							<td>Fecha Inicio :</td>
							<td><input type="text" id="txtFechaInicio" class="requiredDate txtfecha"/></td>
						</tr>
								  
						<tr>
							<td>Operativo:</td>
							<td><select id="sltOperativo">
									<option value="">Seleccione</option>
					  				<c:forEach var="operativo" items="${operativo}">
					  					<option value="${operativo.opecodigoD}"><c:out value="${operativo.opetituloV}"/></option>
					  				</c:forEach>
								</select>
							</td>
								
						</tr>
								        	
	        			<tr>
							<td>Hora Inicio :</td>
							<td><input type="text" id="txtHoraInicio" class="requiredHour"/></td>
						</tr>
						
						<tr>
							<td>Hora Término :</td>
							<td><input type="text" id="txtHoraTermino" class="requiredHour"/></td>
						</tr>
						
						<tr>
							<td>Observaciones :</td>
							<td><textarea id="txtObservaciones" rows="3"></textarea></td>
						</tr>
						
				</table>
				<input type="button" id="btnImprimirVistaPrevia" value ='VISTA PREVIA' align="middle" class="ui-button"/>
				<input type="button" id="btnImprimirInfDiarioActa" value ='IMPRIMIR' align="middle" class="ui-button"/>
				<input type="button" id="btnCancelar" value ='CANCELAR' align="middle" class="ui-button"/>	
			</fieldset>
		</div>

</body>
</html>