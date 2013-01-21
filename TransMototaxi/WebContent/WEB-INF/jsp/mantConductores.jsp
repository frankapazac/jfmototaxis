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
	<script type="text/javascript" src="js/ui/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.tabs.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.position.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.dialog.js"></script>
	<script type="text/javascript" src="js/jsp/mantConductores.js"></script>
<title>Insert title here</title>
</head>
<body>
	<h1>Conductores</h1>
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
						<option value="PER.PERNOMBRES_V">Nombres</option>
						<option value="PER.PERPATERNO_V">Apellido Paterno</option>
						<option value="PER.PERMATERNO_V">Apellido Materno</option>
						<option value="PER.PERDNI_V">DNI</option>
					</select>
				</td>
				<td><input type="text" id="txtTexto" /></td>
				<td><input type="button" id="btnBuscar" value="Buscar"/></td>
				<td><input type="button" id="btnAsignarMoto" value="Mototaxi Asignar/Cese"/></td>
			</tr>
		</table>
	</div>
	
  	<div id="divLista">
		<table align="center" class="tablesorter" id="tblLista">		
		</table>
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


	<div id="divFormulario">
		<p id="txtNombreConductor">NOMBRE DEL CONDUCTOR</p>		
		<div id="tabs">
			<ul>
			</ul>
			<div id="tabs1">
				<input type="hidden" id="txtCodigo"/>
				<input type="button" value="Asignar Mototaxi" id="btnAsignar" class="ui-button"/>
				<input type="button" value="Cese" id="btnCese" class="ui-button"/>
			
				<div id="divBuscar">
				<table>
					<tr>
						<td>Criterio</td>
						<td>Texto</td>
						<td></td>
					</tr>
					<tr>
						<td>
							<select id="sltCriterio2">
								<option value="PER.PERNOMBRES_V">Nombres</option>
								<option value="PER.PERPATERNO_V">Apellido Paterno</option>
								<option value="PER.PERMATERNO_V">Apellido Materno</option>
								<option value="PER.PERDNI_V">DNI</option>
								<option value="UEM.UNEPLACANRO_V">Placa</option>
								<option value="UEM.UNEMARCA_V">Marca</option>
								<option value="MDL.MODNOMBRE_V">Modelo</option>
								<option value="UEM.UNEANNO_C">A�o</option>
								<option value="UEM.UNECOLOR_V">Color</option>
							</select>
						</td>
						<td><input type="text" id="txtTextoAsignado" /></td>
						<td><input type="button" id="btnBuscarAsignado" value="Buscar"/></td>
					</tr>
				</table>
				</div>
			</div>
		</div>

	  	<div id="divListaMotosAsignadas">
			<table align="center" class="tablesorter" id="tblListaMotosAsignadas">		
			</table>
			</div>
	</div>
	
	
	<div id="divFormularioCese">
		<input type="hidden" id="txtOcultoCodigoUndConductor"/>
		<p id="">Fecha</p><input type="text" id="txtFechaCese"/>
		<p>Observaciones</p>
		<p id="txtNombreConductorCese">NOMBRE DEL CONDUCTOR</p>		
		<input type="hidden" id="txtCodigoCese"/>
		<textarea cols="50" rows=4 id="txtObservacion"></textarea>
		<table>
			<tr>
				<td><input type="button" id="btnGuardarCese" value="Cese"/></td>
				<td><input type="button" id="btnCancelarCese" value="Cancelar"/></td>
			</tr>
		</table>
	</div>	
</body>
</html>