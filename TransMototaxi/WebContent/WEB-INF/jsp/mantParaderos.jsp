<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<script type="text/javascript" src="js/ui/jquery.ui.tooltip.js"></script>
	<script type="text/javascript" src="js/jefra.validate.js"></script >
	<script type="text/javascript" src="js/jefra.message.js"></script >
	<script type="text/javascript" src="js/jefra.util.js"></script >
	<script type="text/javascript" src="js/jsp/mantParaderos.js"></script>
<title>Insert title here</title>
</head>
<body>
	<h1>Paraderos</h1>
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
						<option value="PAR.PARNOMBRE_V">Nombre del Paradero</option>
						<option value="PAR.PARUBICACION_V">Ubicación</option>
						<option value="PAR.PARZONAADM_I">Zona Administrativa</option>
						<option value="PAR.PARESTADO_C">Estado</option>
					</select>
				</td>
				<td><input type="text" id="txtTexto" /></td>
				<td><input type="button" id="btnBuscar" value="Buscar"/></td>
			</tr>
		</table>
	</div>
	
	<div id="divLista">
		<table align="center" class="tablesorter" id="tblLista">		
		</table>
	</div>
	
	<div id="divFormulario">
		<div id="tabs">
			<ul>
			</ul>
			<div id="tabs1">
				<input type="hidden" id="txtCodigo"/>
				<table align="center">
					<tr>
						<td>Paradero:</td>
						<td>
						  <select id="sltParadero" style="width:250px;" class="requiredSelect">
						  		<option value="" selected="selected">Seleccione</option>
				  				<c:forEach var="paradero" items="${paraderos}">
				  					<option value="${paradero.parcodigoI}"><c:out value="${paradero.parnombreV}"/></option>
				  				</c:forEach>
						  </select>
						</td>
						<td>Zona Administrativa:</td>
						<td><input type="text" id="txtZonaAdministrativa" class="requiredText" disabled="disabled"/></td>
					</tr>
					<tr>
						<td>Ubicación:</td>
						<td colspan="3"><textarea rows=4 cols="75" id="txtUbicacion" disabled="disabled"></textarea></td>
					</tr>
				</table>
				<center>
					<input type="button" value="Agregar" id="btnAgregar" class="ui-button"/>
					<input type="button" value="Cancelar" id="btnCancelar" class="ui-button"/>
				</center>
		  	</div>
			<div id="tabs2">
			</div>
		</div>
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
	
	<h4 align="center">
		<img id="btnNuevo" alt="Nuevo" src="images/Add.png"/>
	</h4>
	<div id="divMensaje"></div>
</body>
</html>