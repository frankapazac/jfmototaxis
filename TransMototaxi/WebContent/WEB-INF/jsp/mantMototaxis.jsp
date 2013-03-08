<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	<script type="text/javascript" src="js/jsp/mantMototaxis.js"></script>
<title>Insert title here</title>
</head>
<body>
	<h1>Mototaxis</h1>
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
						<option value="UEM.UNEPLACANRO_V">N° Placa</option>
						<option value="UEM.UNEMARCA_V">Marca</option>
						<option value="MMO.MODNOMBRE_V">Modelo</option>
						<option value="UEM.UNEANNO_C">Año</option>
						<option value="UEM.UNECOLOR_V">Color</option>
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
	
	
	<!-- ELABORACION DE INFORME SOBRE MOTOTAXIS -->
	
		<div id="divInformeMotoTaxi">
	    	<fieldset>
	        	<legend>DATOS DEL PROPIETARIO</legend>     
	        	<input type="hidden" id="txtcodigoMototaxi" value="0"/>        
	        	<table align="center">
	        			<tr>
							<td>Nombres:</td>
							<!-- td><input type="text" id="txtNombresPropietario"/></td> -->
							<td><label id="txtNombresPropietario"></label></td> 
							<td>DNI:</td>
							<td><label id="txtdni"></label></td> 
						</tr>
						<tr>
							<td>Dirección:</td>
							<!--td colspan="3"><input type="text" id="txtdireccion" style="width: 580px"/></td-->
							<td colspan="3"><label id="txtdireccion" style="width: 580px"></label></td> 
						</tr>
						
						<tr>
							<td>Distrito:</td>
							<td><label id="txtDistrito"></label></td> 
							<td>Teléfono:</td>
							<td><label id="txtTeléfono"></label></td> 
						</tr>
						
						<tr>
							<td>Provincia:</td>
							<td><label id="txtprovincia"></label></td> 
							<td>Celular Claro :</td>
							<td><label id="txtmovClaro"></label></td>
						</tr>
						
						<tr>
							<td>Departamento:</td>
							<td><label id="txtdepartamento"></label></td>
							<td>Celular Nextel :</td>
							<td><label id="txtmovNextel"></label></td>
						</tr>
						<tr>
							<td>Correo:</td>
							<td><label id="txtCorreo"></label></td>
							<td>Celular Movistar :</td>
							<td><label id="txtmovMovistar"></label></td> 
						</tr>	
						<tr>

						</tr>							
			</table>	
			</fieldset>
			
			<fieldset>
	        	<legend>DATOS DEL MOTOTAXI</legend>     
	        	<!--  input type="hidden" id="txtcodigoMototaxi" value="0"/>-->        
	        	<table align="center">
	        			<tr>
							<td>N° Placa:</td>
							<td><label id="txtplaca"></label></td>
							<td>N° Titulo:</td>
							<td><label id="txttitulo"></label></td>
							<td>Clase:</td>
							<td><label id="txtclase"></label></td>
						</tr>
						<tr>
							<td>Marca:</td>
							<td><label id="txtmarca"></label></td>
							<td>Modelo:</td>
							<td><label id="txtmodelo"></label></td>
							<td>Año:</td>
							<td><label id="txtanno"></label></td>
						</tr>						
						<tr>
							<td>Color :</td>
							<td><label id="txtcolor"></label></td>
							<td>Combustible:</td>
							<td><label id="txtcombustible"></label></td>
							<td>Carroceria :</td>
							<td><label id="txtcarroceria"></label></td>
						</tr>

						<tr>
							<td>N° Serie/Chasis:</td>
							<td><label id="txtnroserie"></label></td>
							<td>N° Motor :</td>
							<td><label id="txtnromotor"></label></td>
							<td>Carga Útil:</td>
							<td><label id="txtcargautil"></label></td>
						</tr>	
			
						<tr>
							<td>Longitud :</td>
							<td><label id="txtlongitud"></label></td>
							<td>Ancho :</td>
							<td><label id="txtancho"></label></td>
							<td>Alto :</td>
							<td><label id="txtalto"></label></td>
						</tr>	
						
						<tr>
							<td>N° Asientos:</td>
							<td><label id="txtnroasientos"></label></td>
							<td>N° Pasajeros :</td>
							<td><label id="txtnropasajeros"></label></td>
							<td>N° Ruedas :</td>
							<td><label id="txtnroRuedas"></label></td>
						</tr>
									
			</table>	
			</fieldset>

			<fieldset>
	        	<legend>PAPELETAS</legend>     
					<div id="divListaPapeleta">
						<table align="center" class="tablesorter" id="tblListaPapeleta"></table>
						<div id="pager2" class="pager">
						<form>
							<img src="js/tablesorter/addons/pager/icons/first.png" class="first">
							<img src="js/tablesorter/addons/pager/icons/prev.png" class="prev">
							<input type="text" class="pagedisplay">
							<img src="js/tablesorter/addons/pager/icons/next.png" class="next">
							<select class="pagesize">
								<option selected="selected" value="5">5</option>
								<option value="10">10</option>
								<option value="10">15</option>
								<option value="15">20</option>
							</select>
							<img src="js/tablesorter/addons/pager/icons/last.png" class="last">
						</form>
						</div>
					</div>
			</fieldset>
			
			<fieldset>
	        	<legend>CONDUCTORES</legend>  
				<div id="divListaConductor">
					<table align="center" class="tablesorter" id="tblListaConductor"></table>
						<div id="pager3" class="pager">
						<form>
							<img src="js/tablesorter/addons/pager/icons/first.png" class="first">
							<img src="js/tablesorter/addons/pager/icons/prev.png" class="prev">
							<input type="text" class="pagedisplay">
							<img src="js/tablesorter/addons/pager/icons/next.png" class="next">
							<select class="pagesize">
								<option selected="selected" value="5">5</option>
								<option value="10">10</option>
								<option value="10">15</option>
								<option value="15">20</option>
							</select>
							<img src="js/tablesorter/addons/pager/icons/last.png" class="last">
						</form>
						</div>
				</div>

		 	</fieldset>
				
			<input type="button" id="btnImprimir" value ='IMPRIMIR' align="middle"/>
			<input type="button" id="btnCerrar" value ='CERRAR' align="middle"/>			
		</div>
		
		

	
	<!-- FIN ELABORACION DE INFORME SOBRE MOTOTAXIS -->
	
</body>
</html>