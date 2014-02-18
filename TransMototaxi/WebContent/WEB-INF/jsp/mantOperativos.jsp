<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script type="text/javascript" src="js/tablesorter/jquery.tablesorter.js"></script>
	<script type="text/javascript" src="js/tablesorter/jquery.tablesorter.pager.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.core.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.effect.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.effect-clip.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.tabs.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.position.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.dialog.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.datepicker.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.mouse.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.slider.js"></script>
	<script type="text/javascript" src="js/ui/jquery-ui-timepicker-addon.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.tooltip.js"></script>
	<script type="text/javascript" src="js/jefra.validate.js"></script >
	<script type="text/javascript" src="js/jefra.message.js"></script >
	<script type="text/javascript" src="js/jefra.util.js"></script >
	<script type="text/javascript" src="js/jsp/mantOperativos.js"></script>
	<!-- http://trentrichardson.com/examples/timepicker/ -->
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
	<h1>Operativos</h1>
	<div id="divBuscar">
		<table>
			<tr>
				<td>Criterio</td>
				<td>Texto</td>
				<td></td>
			</tr>
			<tr>
				<td>
						<select id="sltCriterio">
						<option value="OP.OPETITULO_V">Titulo</option>
						<option value="ZO.ZONNOMBRE_V">Zona Administrativa</option>
						<option value="OP.OPELUGAR_V">Lugar</option>
						<option value="PER.PERNOMBRES_V">Nombres</option>
						<option value="PER.PERPATERNO_V ">Paterno</option>
						<option value="PER.PERMATERNO_V">Materno</option>
						</select>
				</td>
				<td><input type="text" id="txtTexto" /></td>
				<td><input type="button" id="btnBuscar" value="Buscar" class="ui-button"/></td>
			</tr>
		</table>
	</div>
	
	<div id="divLista">
		<table align="center" class="tablesorter" id="tblLista"></table>
	</div>
	
	<div id="pager" class="pager">
		<form>
			<img src="js/tablesorter/addons/pager/icons/first.png" class="first">
			<img src="js/tablesorter/addons/pager/icons/prev.png" class="prev">
			<input type="text" class="pagedisplay">
			<img src="js/tablesorter/addons/pager/icons/next.png" class="next">
			<select class="pagesize">
				<option selected="selected" value="10">10</option>
				<option value="20">20</option>
				<option value="30">30</option>
				<option value="40">40</option>
			</select>
			<img src="js/tablesorter/addons/pager/icons/last.png" class="last">
		</form>
	</div>
	
	<div id="divMensaje"></div>
	
	<h4 align="center">
		<img id="btnNuevOperativo" alt="Nuevo" src="images/Add.png"/>
	</h4>	
	
	<!-- AGREGAR OPERATIVO -->	

	<div id="divNuevoOperativo">
	    	<fieldset>
	        	<legend>NUEVO OPERATIVO</legend>     
	        	<input type="hidden" id="txtCodigoOperativo" value="0"/>        
	        	<table align="center">
	        			<tr>
							<td>Nombre Operativo:</td>
							<td><input type="text" id="txtNombreOperativo" class="requiredAlpha"/></td>
						</tr>
						<tr>
							<td>Zona Administrativa:</td>
							<td><select id="sltZona" class="requiredSelect">
									<option value="">Seleccione</option>
					  				<c:forEach var="zona" items="${zona}">
					  					<option value="${zona.zoncodigo_I}"><c:out value="${zona.zonnombre_V}"/></option>
					  				</c:forEach>
								</select>
						</tr>
						<tr>
							<td>Descripción:</td>
							<td><input type="text" id="txtDescripcion" class="required"/></td>
						</tr>
						<tr>
							<td>Dirección:</td>
							<td><input type="text" id="txtDireccion" class="required"/></td>
						</tr>
						
						<tr>
							<td>Referencia:</td>
							<td><input type="text" id="txtReferencia" class="required"/></td>
						</tr>
						<tr>
							<td>Fecha:</td>
							<td><input type="text" id="txtFecha" class="txtFecha dtFecha requiredDate"/></td>
						</tr>
						<tr>
							<td>Hora Inicio:</td>
							<!--  <td><input type="text" id="txtHora"/></td> -->
							<td><input type="text" name="fecha" id="txtHora" value="" class="requiredHour"/></td>
							
						</tr>
						
						<tr>
							<td>Hora Término:</td>
							<td><input type="text" name="fecha" id="txtHoraFin" value="" class="requiredHour"/></td>
							
						</tr>
						
						
						
						<tr>
						<td>Responsable:</td>
							<td>
								<select id="sltResponsable" class="requiredSelect">
									<option value="" selected="selected">Seleccione</option>
					  				<c:forEach var="inspector" items="${inspectores}">
					  					<option value="${inspector.inscodigoI}"><c:out value="${inspector.persona.perpaternoV}"/> <c:out value="${inspector.persona.permaternoV}"/>, <c:out value="${inspector.persona.pernombresV}"/></option>
					  				</c:forEach>
								</select>
							</td>
					
					 	</tr>
			</table>	
			</fieldset>
			
			<fieldset>
				<legend>AGREGAR INSPECTORES</legend>
				<input type="hidden" id="txtCodigoInspectores" value="0"/>  
				<table align="CENTER">
						<tr>
						<td>Inspectores:</td>
                        <td></td>
                        <td>Agregar Inspectores:</td>
						</tr>	
						
						<tr>	
							<td>
								<select multiple="multiple" id="sltInspectores"	style="width: 100%">
					  			   <!--    <c:forEach var="inspector" items="${inspectores}">
					  					<option value="${inspector.inscodigoI}"><c:out value="${inspector.persona.perpaternoV}"/> <c:out value="${inspector.persona.permaternoV}"/>, <c:out value="${inspector.persona.pernombresV}"/></option>
					  				</c:forEach>
					  				-->
								</select>
							</td>	
							
							<td>
								<input type="button" id="btnRight" value =' > ' class="ui-button"/>
								<br/><input type="button" id="btnLeft" value =' < ' class="ui-button"/>
							</td>
						<td>
								<select multiple="multiple" id="sltAgregaInspector" name="AgregaInspector" style="width: 100%"></select>
						</td>
							
					 	</tr>	
					 	

				</table>
			</fieldset>
		<center>
			<input type="button" id="btnGuardar" value ='GUARDAR' align="middle" class="ui-button"/>
			<input type="button" id="btnCancelar" value ='CANCELAR' align="middle" class="ui-button"/>	
		</center>
			
			
		
		</div>
	<!--  FIN AGREGAR OPERATIVO -->
		
</body>
</html>