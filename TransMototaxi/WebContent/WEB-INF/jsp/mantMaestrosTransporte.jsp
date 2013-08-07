<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script type="text/javascript" src="js/tablesorter/jquery.tablesorter.js"></script>
	<script type="text/javascript" src="js/tablesorter/jquery.tablesorter.pager.js"></script>
	<script type="text/javascript" src="js/jquery.form.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.core.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.tabs.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.position.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.dialog.js"></script>
	<script type="text/javascript" src="js/jsp/mantInfracciones.js"></script>
	<script type="text/javascript" src="js/jsp/mantTipoDocumento.js"></script>
	<script type="text/javascript" src="js/jsp/mantParaderosTrans.js"></script>
	<script type="text/javascript" src="js/jsp/mantInventarioTipo.js"></script>
	<script type="text/javascript" src="js/jsp/mantZona.js"></script>
	<script type="text/javascript" src="js/jsp/mantMarca.js"></script>
	<script type="text/javascript" src="js/jsp/mantModelo.js"></script>
	<script type="text/javascript" src="js/jsp/mantMaestrosTransporte.js"></script>
	<script type="text/javascript" src="js/jsp/mantTipoMedida.js"></script>
	<script type="text/javascript" src="js/jefra.util.js"></script >
	
	<title>Insert title here</title>
	<style type="text/css">
		/*#tabs {margin:20px; }
		#tabs .tabs-spacer { float: left; height: 200px; }
		.tabs-bottom .ui-tabs-nav { clear: left; padding: 0 .2em .2em .2em; }
		.tabs-bottom .ui-tabs-nav li { top: auto; bottom: 0; margin: 0 .2em 1px 0; border-bottom: auto; border-top: 0; }
		.tabs-bottom .ui-tabs-nav li.ui-tabs-active { margin-top: -1px; padding-top: 1px; }*/
	</style>
</head>
<body>
	<div id="tabs" class="tabs-bottom">
		<ul>
			<li><a href="#tabs-1">INFRACCIONES</a></li>
			<li><a href="#tabs-2">TIPO DE DOCUMENTO</a></li>
			<li><a href="#tabs-3">PARADEROS</a></li>
			<li><a href="#tabs-4">INVENTARIO</a></li>
			<li><a href="#tabs-5">TIPO MEDIDA</a></li>
			<li><a href="#tabs-6">ZONA ADMINISTRATIVA</a></li>
			<li><a href="#tabs-7">MARCA</a></li>
			<li><a href="#tabs-8">MODELO</a></li>
		</ul>
		<div class="tabs-spacer"></div>
		<div id="tabs-1">
			<h1>Infracciones</h1>
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
				<img id="btnNuevaInfraccion" alt="Nuevo" src="images/Add.png"/>
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
		</div>
		<div id="tabs-2">
			<div id="divBuscarTipoDoc">
				<table>
						<tr>
							<td>Criterio</td>
							<td>Texto</td>
						</tr>
						
						<tr>
							<td>
								<select id="sltCriterioTipDoc">
								<option value="MTDNOMBRE_V">Descripción</option>
								<option value="MTDTABLA_C">Tipo Infracción</option>
								<option value="MTDESTADO_C">Estado</option>
								</select>
							</td>
							<td><input type="text" id="txtTextoTipDoc" /></td>
							<td><input type="button" id="btnBuscarTipDoc" value="Buscar"/></td>
						</tr>		
				</table>
			</div>
			
			<div id="divListaTipDoc">
			<table align="center" class="tablesorter" id="tblListaTipDoc"></table>	</div>
			
			<div id="pager2" class="pager">
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
			
			<div id="divMensajeTipDoc"></div>
				
			<h4 align="center">
				<img id="btnNuevoTipDoc" alt="Nuevo" src="images/Add.png"/>
			</h4>
			
			<div id="divNuevoTipDoc">
	    	<fieldset>
	        	<legend>NUEVO TIPO DE DOCUMENTO</legend>     
	        	<input type="hidden" id="txtcodigoTipDoc" value="0"/>        
	        	<table align="center">
	        			<tr>
							<td>Nombre Documento:</td>
							<td><input type="text" id="txtNombreDocumento" /></td>
						</tr>
						<tr>
							<td>Nombre Tabla:</td>
							<td><input type="text" id="txttabla"/></td>
						</tr>
						
						<tr>
							<td>Estado</td>
							<td>
								<select id="sltEstado">
								<option value="A">ACTIVO</option>
								<option value="I">INACTIVO</option>
								</select>
							</td>
						</tr>
			</table>	
			</fieldset>
			
			<input type="button" id="btnGuardarTipDoc" value ='GUARDAR' align="middle"/>
			<input type="button" id="btnCancelarTipDoc" value ='CANCELAR' align="middle"/>	
			
		
		</div>
		
		</div>
		<div id="tabs-3">
				<div id="divBuscarParadero">
					<table>
						<tr>
							<td>Criterio</td>
							<td>Texto</td>
							<td></td>
						</tr>
						<tr>
							<td>
								<select id="sltCriterioParadero">
									<option value="PAR.PARNOMBRE_V">Nombre del Paradero</option>
									<option value="PAR.PARUBICACION_V">Ubicación</option>
									<option value="PAR.PARZONAADM_I">Zona Administrativa</option>
									<option value="PAR.PARESTADO_C">Estado</option>
								</select>
							</td>
							<td><input type="text" id="txtTextoParadero" /></td>
							<td><input type="button" id="btnBuscarParadero" value="Buscar"/></td>
						</tr>
					</table>
				</div>	
				
			<div id="divListaParaderos">
				<table align="center" class="tablesorter" id="tblListaParaderos"></table>
				<div id="pager3" class="pager">
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
			</div>
			
			<h4 align="center">
				<img id="btnNuevoParadero" alt="Nuevo" src="images/Add.png"/>
			</h4>
			
			<div id="divNuevoParadero">
					<fieldset>
					<legend>NUEVO PARADERO</legend>     
						<input type="hidden" id="txtCodigoParadero"/>
						<table align="center">
							
							<tr>
								<td>Nombre Paradero:</td>
								<td><input type="text" id="txtnombreparadero"/></td>
							</tr>
							
							<tr>
								<td>Ubicación:</td>
								<td colspan="3"><textarea rows=4 id="txtUbicacion"></textarea></td>
							</tr>
							
							<tr>
								<td>Zona Administrativa:</td>
								<td>
								  <select id="sltZonaAdministrativa" style="width:250px;">
								  		<option>Seleccione</option>
						  				<c:forEach var="zona" items="${zona}">
						  					<option value="${zona.zoncodigo_I}"><c:out value="${zona.zonnombre_V}"/></option>
						  				</c:forEach>
								  </select>
								</td>
							</tr>
							<tr>
							<td>Estado</td>
							<td>
								<select id="sltEstadoParadero">
								<option value="A">ACTIVO</option>
								<option value="I">INACTIVO</option>
								</select>
							</td>
						</tr>

						</table>
						</fieldset>
						<input type="button" value="Guardar" id="btnGuardarParadero" class="ui-button"/>
						<input type="button" value="Cancelar" id="btnCancelarParadero" class="ui-button"/>
			</div>
		</div>
		<div id="tabs-4">
				<div id="divBuscarInventario">
					<table>
						<tr>
							<td>Criterio</td>
							<td>Texto</td>
							<td></td>
						</tr>
						<tr>
							<td>
								<select id="sltCriterioInventario">
									<option value="BITNOMBRE_V">Nombre</option>
									<option value="BITTIPO_C">Tipo</option>
									<option value="BITESTADO_C">Estado</option>
								</select>
							</td>
							<td><input type="text" id="txtTextoInventario" /></td>
							<td><input type="button" id="btnBuscarInventario" value="Buscar"/></td>
						</tr>
					</table>
				</div>	
				
				<div id="divListaInventario">
				<table align="center" class="tablesorter" id="tblListaInventario"></table>
				</div>
				
					<div id="pager4" class="pager">
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
				<img id="btnNuevoInventario" alt="Nuevo" src="images/Add.png"/>
				</h4>
			
				<div id="divNuevoInventario">
					<fieldset>
					<legend>INVENTARIO</legend>     
						<input type="hidden" id="txtCodigoInventario"/>
						<table align="center">
							
							<tr>
								<td>Nombre Inventario:</td>
								<td><input type="text" id="txtnombreInventario"/></td>
							</tr>
							
							<tr>
								<td>Tipo</td>
								<td>
									<select id="sltTipoInventario">
									<option value="E">EXTERNO</option>
									<option value="I">INTERNO</option>
									<option value="M">MOTOR</option>
									</select>
								</td>
							</tr>
							
							<tr>
							<td>Estado</td>
							<td>
								<select id="sltEstadoInventario">
								<option value="A">ACTIVO</option>
								<option value="I">INACTIVO</option>
								</select>
							</td>
						</tr>

						</table>
						</fieldset>
						<input type="button" value="Guardar" id="btnGuardarInventario" class="ui-button"/>
						<input type="button" value="Cancelar" id="btnCancelarInventario" class="ui-button"/>
				</div>
				
				
				
				
		</div>
		
		<div id="tabs-5">
				<div id="divBuscarTipoMedida">
					<table>
						<tr>
							<td>Criterio</td>
							<td>Texto</td>
							<td></td>
						</tr>
						<tr>
							<td>
								<select id="sltCriterioTipoMedida">
									<option value="TMEDESCRIPCION_V">Tipo Medida</option>
									<option value="TMEESTADO_C">Estado</option>
								</select>
							</td>
							<td><input type="text" id="txtTextoTipoMedida" /></td>
							<td><input type="button" id="btnBuscarMedida" value="Buscar"/></td>
						</tr>
					</table>
				</div>	
				
				<div id="divListaMedida">
				<table align="center" class="tablesorter" id="tblListaMedida"></table>
					<div id="pager6" class="pager">
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
				</div>
				
				<h4 align="center">
				<img id="btnNuevaMedida" alt="Nuevo" src="images/Add.png"/>
				</h4>
				
				
				<div id="divNuevaMedida">
					<fieldset>
					<legend>TIPO MEDIDA</legend>     
						<input type="hidden" id="txtCodigoMedida"/>
						<table align="center">
							
						<tr>
								<td>Tipo Medida:</td>
								<td><input type="text" id="txtnombremedida"/></td>
						</tr>

						<tr>
							<td>Estado</td>
							<td>
								<select id="sltEstadoMedida">
								<option value="A">ACTIVO</option>
								<option value="I">INACTIVO</option>
								</select>
							</td>
						</tr>

						</table>
						</fieldset>
						<input type="button" value="Guardar" id="btnGuardarMedida" class="ui-button"/>
						<input type="button" value="Cancelar" id="btnCancelarMedida" class="ui-button"/>
				</div>
				
		</div>
		
		
		<div id="tabs-6">
			
				<div id="divBuscarZona">
					<table>
						<tr>
							<td>Criterio</td>
							<td>Texto</td>
							<td></td>
						</tr>
						<tr>
							<td>
								<select id="sltCriterioZona">
									<option value="ZONNOMBRE_V">Zona Administrativa</option>
									<option value="ZONESTADO_C">Estado</option>
								</select>
							</td>
							<td><input type="text" id="txtTextoZona" /></td>
							<td><input type="button" id="btnBuscarZona" value="Buscar"/></td>
						</tr>
					</table>
				</div>	
			
				
				
			<div id="divListaZona">
			<table align="center" class="tablesorter" id="tblListaZona"></table>
			</div>
			
			<div id="pager7" class="pager">
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
				<img id="btnNuevaZona" alt="Nuevo" src="images/Add.png"/>
				</h4>
				
				
				<div id="divNuevaZona">
					<fieldset>
					<legend>ZONA</legend>     
						<input type="hidden" id="txtCodigoZona"/>
						<table align="center">
							
						<tr>
								<td>Nombre Zona:</td>
								<td><input type="text" id="txtnombrezona"/></td>
						</tr>

						<tr>
							<td>Estado</td>
							<td>
								<select id="sltEstadoZona">
								<option value="A">ACTIVO</option>
								<option value="I">INACTIVO</option>
								</select>
							</td>
						</tr>

						</table>
						</fieldset>
						<input type="button" value="Guardar" id="btnGuardarZona" class="ui-button"/>
						<input type="button" value="Cancelar" id="btnCancelarZona" class="ui-button"/>
				</div>
		</div>
		
		
		
		<div id="tabs-7">
			
				<div id="divBuscarMarca">
					<table>
						<tr>
							<td>Criterio</td>
							<td>Texto</td>
							<td></td>
						</tr>
						<tr>
							<td>
								<select id="sltCriterioMarca">
									<option value="MARNOMBRE_V">Marca</option>
									<option value="MARESTADO_C">Estado</option>
								</select>
							</td>
							<td><input type="text" id="txtTextoMarca" /></td>
							<td><input type="button" id="btnBuscarMarca" value="Buscar"/></td>
						</tr>
					</table>
				</div>	
			
				
				
			<div id="divListaMarca">
			<table align="center" class="tablesorter" id="tblListaMarca"></table>
			</div>
			
			<div id="pager8" class="pager">
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
				<img id="btnNuevaMarca" alt="Nuevo" src="images/Add.png"/>
				</h4>
				
				
				<div id="divNuevaMarca">
					<fieldset>
					<legend>MARCA</legend>     
						<input type="hidden" id="txtCodigoMarca"/>
						<table align="center">
							
						<tr>
								<td>Nombre Marca:</td>
								<td><input type="text" id="txtnombreMarca"/></td>
						</tr>

						<tr>
							<td>Estado</td>
							<td>
								<select id="sltEstadoMarca">
								<option value="A">ACTIVO</option>
								<option value="I">INACTIVO</option>
								</select>
							</td>
						</tr>

						</table>
						</fieldset>
						<input type="button" value="Guardar" id="btnGuardarMarca" class="ui-button"/>
						<input type="button" value="Cancelar" id="btnCancelarMarca" class="ui-button"/>
				</div>
		</div>
		
		<div id="tabs-8">
			
				<div id="divBuscarModelo">
					<table>
						<tr>
							<td>Criterio</td>
							<td>Texto</td>
							<td></td>
						</tr>
						<tr>
							<td>
								<select id="sltCriterioModelo">
									<option value="MODNOMBRE_V">Modelo</option>
									<option value="MODESTADO_V">Estado</option>
								</select>
							</td>
							<td><input type="text" id="txtTextoModelo" /></td>
							<td><input type="button" id="btnBuscarModelo" value="Buscar"/></td>
						</tr>
					</table>
				</div>	
			
				
				
			<div id="divListaModelo">
			<table align="center" class="tablesorter" id="tblListaModelo"></table>
			</div>
			
			<div id="pager9" class="pager">
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
				<img id="btnNuevaModelo" alt="Nuevo" src="images/Add.png"/>
			</h4>
				
				
				<div id="divNuevaModelo">
					<fieldset>
					<legend>MODELO</legend>     
						<input type="hidden" id="txtCodigoModelo"/>
						<table align="center">
							
						<tr>
								<td>Nombre Modelo:</td>
								<td><input type="text" id="txtnombreModelo"/></td>
						</tr>

						<tr>
							<td>Estado</td>
							<td>
								<select id="sltEstadoModelo">
								<option value="A">ACTIVO</option>
								<option value="I">INACTIVO</option>
								</select>
							</td>
						</tr>

						</table>
						</fieldset>
						<input type="button" value="Guardar" id="btnGuardarModelo" class="ui-button"/>
						<input type="button" value="Cancelar" id="btnCancelarModelo" class="ui-button"/>
				</div>
		</div>
		
		
	</div>

</body>
</html>