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
	<script type="text/javascript" src="js/ui/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.tabs.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.position.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.dialog.js"></script>
	<script type="text/javascript" src="js/jefra.util.js"></script >
	<script type="text/javascript" src="js/jsp/mantInfracciones.js"></script>
<title>Insert title here</title>
</head>
<body>
	<h1>INFRACCIONES</h1>
		<div id="divBuscar">
			<table>
					<tr>
						<td>Criterio</td>
						<td>Texto</td>
					</tr>
					
					<tr>
						<td>
							<select id="sltCriterio">
							<option value="INF.INFINFRACCION_V">Descripción</option>
							<option value="INF.INFTITPO_C">Tipo Infracción</option>
							<option value="INF.INFMEDIDASACC_V">Medidas</option>
							<option value="INF.INFNROUIT_I">N° UIT</option>
							</select>
						</td>
						<td><input type="text" id="txtTexto" /></td>
						<td><input type="button" id="btnBuscar" value="Buscar"/></td>
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
		<img id="btnNuevaInfraccion" alt="Nuevo" src="images/new.png"/>
	</h4>	
	
	<div id="divNuevoInfraccion">
	    	<fieldset>
	        	<legend>NUEVA INFRACCION</legend>     
	        	<input type="hidden" id="txtCodigoInfraccion" value="0"/>        
	        	<table align="center">
	        			<tr>
							<td>Cod. Infracción:</td>
							<td><input type="text" id="txtCodInfraccionV" /></td>
							
							<td>Tipo Gravedad:</td>
							<td><select id="sltGravedad" style="width: 235px">
							<option value="">Seleccione</option>
							<option value="L">Leve</option>
							<option value="G">Grave</option>
							<option value="MG">Muy Grave</option>
							</select>
							</td>
						</tr>
						<tr>
							<td>Descripción:</td>
							<td colspan="3"><input type="text" id="txtDescripcion" style="width: 570px"/></td>
						</tr>						
						<tr>
							<td>N° UIT:</td>
							<td><input type="text" id="txtNroUIT"/></td>
							
							<td>Para:</td>
							<td><select id="sltPara">
							<option value="">Seleccione</option>
							<option value="1">Propietario y/o Conductor</option>
							<option value="2">Empresa</option>
							</select>
							</td>
							
						</tr>
			</table>	
			</fieldset>
		
		
			<fieldset>
				<legend>AGREGAR MEDIDAS</legend>
				<input type="hidden" id="txtCodigo" value="0"/>  
				<table align="CENTER">
						<tr>
						<td>Medidas:</td>
                        <td></td>
                        <td>Agregar Medidas:</td>
						</tr>	
						
						<tr>	
							<td>
								<select multiple="multiple" id="sltMedidas"	style="width: 100%">
					  			    <c:forEach var="tipoMedida" items="${tipoMedida}">
					  					<option value="${tipoMedida.tmecodigoI}"><c:out value="${tipoMedida.tmedescripcionV}"/></option>
					  				</c:forEach>
								</select>
							</td>	
							
							<td>
								<input type="button" id="btnRight" value =' > '/>
								<br/><input type="button" id="btnLeft" value =' < '/>
							</td>
						<td>
								<select multiple="multiple" id="sltAgregaMedidas" name="AgregaMedidas" style="width: 100%"></select>
						</td>
							
					 	</tr>	
					 	

				</table>
			</fieldset>
			<input type="button" id="btnGuardar" value ='GUARDAR' align="middle"/>
			<input type="button" id="btnCancelar" value ='CANCELAR' align="middle"/>	
			
		</div>
		
</body>
</html>