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
	<script type="text/javascript" src="js/ui/jquery.ui.datepicker.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.progressbar.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.tooltip.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.effect.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.effect-clip.js"></script>
	<script type="text/javascript" src="js/jefra.onlychar.js"></script >
	<script type="text/javascript" src="js/jefra.validate.js"></script >
	<script type="text/javascript" src="js/jefra.message.js"></script >
	<script type="text/javascript" src="js/jefra.util.js"></script >
	<script type="text/javascript" src="js/jsp/mantEmpresa.js"></script>
<title>Insert title here</title>
</head>
<body>
	<h1>Empresas</h1>
	<div id="divBuscar">
		<table>
			<tr>
				<td>Criterio</td>
				<td>Texto</td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td>
					<select id="sltCriterio">
						<option value="EMP.EMPRAZONSOCIAL_V">Nombres</option>
						<option value="EMP.EMPDIRECCION_V">Direcci�n</option>
						<option value="EMP.EMPRUC_V">RUC</option>
						<option value="ZON.ZONNOMBRE_V">ZONA</option>
					</select>
				</td>
				<td><input type="text" id="txtTexto" /></td>
				<td><input type="button" id="btnBuscar" value="Buscar" class="ui-button"/></td>
				<td><input type="button" id="btnAgregarPropietarios" value="Agregar Propietarios" class="ui-button"/></td>
				<td><input type="button" id="btnAgregarUsuario" value="Agregar Usuario" class="ui-button"/></td>
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
	
	<h4 align="center">
		<img id="btnNuevo" alt="Nuevo" src="images/Add.png"/>
		<img id="btnImprimirEmpresasUsuarios" alt="PDF" src="images/acroread.png"/>
	</h4>
	
	<div id="divFormulario">
		<fieldset>
			<legend>DATOS DE LA EMPRESA</legend>
			<input type="hidden" id="txtCodigo"/>
			<table align="center">
				<tr>
					<td>Zona:</td>
					<td><select id="sltZona" class="requiredSelect">
							<option value="0">-Seleccione-</option>
							<c:forEach var="zona" items="${zonas}">
								<option value="${zona.zoncodigo_I}"><c:out value="${zona.zonnombre_V}"/></option>
							</c:forEach>
						</select></td>
					<td>Pagina Web:</td>
					<td><input type="text" id="txtPaginaWeb" /></td>
				</tr>
				<tr>
					<td>Raz�n Social:</td>
					<td><input type="text" id="txtRazon" class="required"/></td>
					<td>Correo:</td>
					<td><input type="text" id="txtCorreo" /></td>
				</tr>
				<tr>
					<td>RUC:</td>
					<td><input type="text" id="txtRuc" class="requiredNumber"/></td>
					<td>Movistar:</td>
					<td><input type="text" id="txtMovistar" class="required"/></td>
				</tr>
				<tr>
					<td>Direcci�n:</td>
					<td><input type="text" id="txtDireccion" class="required"/></td>
					<td>Claro:</td>
					<td><input type="text" id="txtClaro" class="required"/></td>
				</tr>
				<tr>
					<td>Departamento:</td>
					<td><select id="sltDepartamentos" class="requiredSelect">
							<option value="">Seleccione</option>
							<c:forEach items="${departamentos}" var="departamento">
								<option value="${departamento.idubigeo}">
									<c:out value="${departamento.nombubigeo}"/>
								</option>
							</c:forEach>
						</select></td>
					<td>Nextel:</td>
					<td><input type="text" id="txtNextel" /></td>
				</tr>
				<tr>
					<td>Provincia:</td>
					<td><select id="sltProvincia" class="requiredSelect">
							<option value="">Seleccione</option>
						</select></td>
					<td>Inicio de Operaci�n:</td>
					<td><input type="text" id="txtInicio" class="dtFecha"/></td>
				</tr>
				<tr>
					<td>Distrito:</td>
					<td><select id="sltDistrito" class="requiredSelect">
							<option value="">Seleccione</option>
						</select></td>
					<td>Cese de Operaci�n:</td>
					<td><input type="text" id="txtCese" class="dtFecha	"/></td>
				</tr>
				<tr>
					<td>Telefono 1:</td>
					<td><input type="text" id="txtTelefono1" /></td>
					<td>Unidades Autorizadas:</td>
					<td><input type="text" id="txtUnidades" class="requiredNumber"/></td>
				</tr>
				<tr>
					<td>Telefono 2:</td>
					<td><input type="text" id="txtTelefono2" /></td>
					<td>Escritura P�blica:</td>
					<td><input type="text" id="txtEscritura" /></td>
				</tr>
				<tr>
					<td>Resoluci�n:</td>
					<td><input type="text" id="txtResolucion" /></td>
					<td></td>
					<td></td>
				</tr>
			</table>
		</fieldset>
		
		<center>
			<input type="button" value="Guardar" id="btnProcesar" class="ui-button"/>
			<input type="button" value="Cancelar" id="btnCancelar" class="ui-button"/>
		</center>
	</div>
	
	<div id="divUsuario">
		<fieldset>
			<legend>DATOS DE INGRESO</legend>
			<input type="hidden" id="txtRepCodigoUsuario"/>
			<table align="center">
				<tr>
					<td>USUARIO</td>
					<td><input type="text" id="txtRepUsuario" class="requiredAlpha" tabindex="1"/></td>
				</tr>
				<tr>
					<td>CLAVE</td>
					<td><input type="password" id="txtRepClave" class="requiredAlpha" tabindex="2"/></td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>DATOS DEL USUARIO</legend>
			<input type="hidden" id="txtRepCodigo"/>
			<table align="center">
				<tr>
					<td>DNI</td>
					<td><input type="text" id="txtRepDni" class="requiredNumber" tabindex="3"/>
					</td><td>Celular Claro</td>
					<td><input type="text" id="txtRepClaro" class="required" tabindex="12"/></td>
				</tr>
				<tr>
					<td>Nombres</td>
					<td><input type="text" id="txtRepNombres" class="requiredText" tabindex="4"/></td>
					<td>Celular Movistar</td>
					<td><input type="text" id="txtRepMovistar" class="required" tabindex="13"/></td>
				</tr>
				<tr>
					<td>Ape. Paterno</td>
					<td><input type="text" id="txtRepPaterno" class="requiredText" tabindex="5"/></td>
					<td>Celular Nextel</td>
					<td><input type="text" id="txtRepNextel" class="required" tabindex="14"/></td>
				</tr>
				<tr>
					<td>Ape. Materno</td>
					<td><input type="text" id="txtRepMaterno" class="requiredText" tabindex="6"/></td>
					<td>Telefono</td>
					<td><input type="text" id="txtRepTelefono" class="requiredPhone" tabindex="15"/></td>
				</tr>
				<tr>
					<td>Nacimiento</td>
					<td><input type="text" id="txtRepNacimiento" class="requiredDate dtFecha" tabindex="7"/></td>
					<td>Domicilio</td>
					<td><input type="text" id="txtRepDireccion" class="required" tabindex="16"/></td>
				</tr>
				<tr>
					<td>Sexo</td>
					<td><select id="sltRepSexo" class="requiredSelect" tabindex="8">
							<option value="" selected="selected">Seleccione</option>
							<option value="M">Masculino</option>
							<option value="F">Femenino</option>
						</select>
					</td>
					<td>Departamento</td>
					<td><select id="sltRepDepartamentos" class="requiredSelect" tabindex="17">
						<option value="">Seleccione</option>
						<c:forEach items="${departamentos}" var="departamentoRep">
							<option value="${departamentoRep.idubigeo}">
								<c:out value="${departamentoRep.nombubigeo}"/>
							</option>
						</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>Estado Civil</td>
					<td><select id="sltRepEstadoCivil" class="requiredSelect" tabindex="9">
						<option value="" selected="selected">Seleccione</option>
						<option value="S">Soltero</option>
						<option value="C">Casado</option>
						<option value="T">Conviviente</option>
						<option value="V">Viudo</option>
						<option value="D">Divorciado</option>
						</select>
					</td>
					<td>Provincia</td>
					<td>
						<select id="sltRepProvincia" class="requiredSelect" tabindex="18">
							<option value="">Seleccione</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="text" id="txtRepCorreo" class="requiredEmail" tabindex="10"/></td>
					<td>Distrito</td>
					<td>
						<select id="sltRepDistrito" class="requiredSelect" tabindex="19">
							<option value="">Seleccione</option>
						</select>
					</td>
				</tr>
			</table>
		</fieldset>
		
		<center>
			<input type="button" value="Guardar" id="btnRepProcesar" class="ui-button" tabindex="19"/>
			<input type="button" value="Cancelar" id="btnRepCancelar" class="ui-button" tabindex="20"/>
			<input type="button" value="Imprimir" id="btnRepImprimir" class="ui-button" tabindex="20"/>
		</center>
	</div>
	
	<div id="divPropietarios" align="center">
		
		<fieldset>
			<legend>REPRESENTANTE DE LA EMPRESA</legend>
			<input type="hidden" id="txtRepreCodigo" value="0"/>
			<table align="center">
				<tr>
					<td>DNI</td>
					<td><input type="text" id="txtRepreDni" disabled="disabled" tabindex="3"/>
					</td><td>Celular Claro</td>
					<td><input type="text" id="txtRepreClaro" disabled="disabled" tabindex="12"/></td>
				</tr>
				<tr>
					<td>Nombres</td>
					<td><input type="text" id="txtRepreNombres" disabled="disabled" tabindex="4"/></td>
					<td>Celular Movistar</td>
					<td><input type="text" id="txtRepreMovistar" disabled="disabled" tabindex="13"/></td>
				</tr>
				<tr>
					<td>Ape. Paterno</td>
					<td><input type="text" id="txtReprePaterno" disabled="disabled" tabindex="5"/></td>
					<td>Celular Nextel</td>
					<td><input type="text" id="txtRepreNextel" disabled="disabled" tabindex="14"/></td>
				</tr>
				<tr>
					<td>Ape. Materno</td>
					<td><input type="text" id="txtRepreMaterno" disabled="disabled" tabindex="6"/></td>
					<td>Telefono</td>
					<td><input type="text" id="txtRepreTelefono" disabled="disabled" tabindex="15"/></td>
				</tr>
				<tr>
					<td>Nacimiento</td>
					<td><input type="text" id="txtRepreNacimiento" disabled="disabled" tabindex="7"/></td>
					<td>Domicilio</td>
					<td><input type="text" id="txtRepreDireccion" disabled="disabled" tabindex="16"/></td>
				</tr>
			</table>
			
			<h4 align="center">
				<img id="btnCambiarRepresentante" alt="Nuevo" src="images/edit_user.png"/>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
				<img id="btnCesarRepresentante" alt="Cese" src="images/delete_user.png"/>
			</h4>
		</fieldset>
		
		<fieldset>
			<legend>PROPIETARIOS DE LA EMPRESA</legend>
			
			<div id="divBuscarPropietarios">
				<table>
					<tr>
						<td>Criterio</td>
						<td>Texto</td>
						<td></td>
					</tr>
					<tr>
						<td>
							<select id="sltCriterioPropietarios">
								<option value="PER.PERDNI_V">DNI</option>
							</select>
						</td>
						<td><input type="text" id="txtTextoPropietarios" /></td>
						<td><input type="button" id="btnBuscarPropietarios" value="Buscar"/></td>
					</tr>
				</table>
			</div>
			
			<div id="divListaPropietarios">
				<table align="center" id="tblListaPropietarios" class="tblTablaEstilo">
				</table>
			</div>
			
			<h4 align="center">
				<img id="btnNuevoPropietario" alt="Nuevo" src="images/Add.png"/>
			</h4>
		</fieldset>
	</div>
	
	<div id="divFormularioPropietarios">
		<fieldset>
			<legend>DATOS DEL PROPIETARIO</legend>
			<input type="hidden" id="txtPropCodigo" value="0"/>
			<input type="hidden" id="txtPropPerCodigo" value="0"/>
			<table align="center">
				<tr>
					<td>DNI</td>
					<td><input type="text" id="txtPropDni" class="requiredNumber" tabindex="3"/>
					</td><td>Celular Claro</td>
					<td><input type="text" id="txtPropClaro" class="required" tabindex="12"/></td>
				</tr>
				<tr>
					<td>Nombres</td>
					<td><input type="text" id="txtPropNombres" class="requiredText" tabindex="4"/></td>
					<td>Celular Movistar</td>
					<td><input type="text" id="txtPropMovistar" class="required" tabindex="13"/></td>
				</tr>
				<tr>
					<td>Ape. Paterno</td>
					<td><input type="text" id="txtPropPaterno" class="requiredText" tabindex="5"/></td>
					<td>Celular Nextel</td>
					<td><input type="text" id="txtPropNextel" class="required" tabindex="14"/></td>
				</tr>
				<tr>
					<td>Ape. Materno</td>
					<td><input type="text" id="txtPropMaterno" class="requiredText" tabindex="6"/></td>
					<td>Telefono</td>
					<td><input type="text" id="txtPropTelefono" class="requiredPhone" tabindex="15"/></td>
				</tr>
				<tr>
					<td>Nacimiento</td>
					<td><input type="text" id="txtPropNacimiento" class="requiredDate dtFecha" tabindex="7"/></td>
					<td>Domicilio</td>
					<td><input type="text" id="txtPropDireccion" class="required" tabindex="16"/></td>
				</tr>
				<tr>
					<td>Sexo</td>
					<td><select id="sltPropSexo" class="requiredSelect" tabindex="8">
							<option value="" selected="selected">Seleccione</option>
							<option value="M">Masculino</option>
							<option value="F">Femenino</option>
						</select>
					</td>
					<td>Departamento</td>
					<td><select id="sltPropDepartamentos" class="requiredSelect" tabindex="17">
						<option value="">Seleccione</option>
						<c:forEach items="${departamentos}" var="departamentoRep">
							<option value="${departamentoRep.idubigeo}">
								<c:out value="${departamentoRep.nombubigeo}"/>
							</option>
						</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>Estado Civil</td>
					<td><select id="sltPropEstadoCivil" class="requiredSelect" tabindex="9">
						<option value="" selected="selected">Seleccione</option>
						<option value="S">Soltero</option>
						<option value="C">Casado</option>
						<option value="T">Conviviente</option>
						<option value="V">Viudo</option>
						<option value="D">Divorciado</option>
						</select>
					</td>
					<td>Provincia</td>
					<td>
						<select id="sltPropProvincia" class="requiredSelect" tabindex="18">
							<option value="">Seleccione</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="text" id="txtPropCorreo" class="requiredEmail" tabindex="10"/></td>
					<td>Distrito</td>
					<td>
						<select id="sltPropDistrito" class="requiredSelect" tabindex="19">
							<option value="">Seleccione</option>
						</select>
					</td>
				</tr>
			</table>
		</fieldset>
		
		<fieldset>
			<legend>DATOS PARA LA EMPRESA</legend>
			<table align="center">
				<tr>
					<td>Fecha Inicio</td>
					<td><input type="text" id="txtPropFechaInicio" class="requiredDate dtFecha" tabindex="20"/></td>
					<td>Fecha Cese</td>
					<td><input type="text" id="txtPropFechaFin" class="requiredDate dtFecha" tabindex="21"/></td>
				</tr>
				<tr>
					<td>Estado</td>
					<td><select id="sltPropEstado" class="requiredSelect" tabindex="22">
							<option value="A">Activo</option>
							<option value="I">Inactivo</option>
						</select></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>Observaciones</td>
					<td colspan="3" id="txtObservaciones">
						<textarea rows="4" cols="48"></textarea>
					</td>
				</tr>
			</table>
		</fieldset>
		
		<center>
			<input type="button" value="Guardar" id="btnProcesarPropietario" class="ui-button"/>
			<input type="button" value="Cancelar" id="btnCancelarPropietario" class="ui-button"/>
		</center>
	</div>
	
	<div id="divNuevoRepresentante">
		<fieldset>
			<legend>DATOS DEL REPRESENTANTE</legend>
			<table align="center">
				<tr>
					<td>Representante</td>
					<td><select id="sltRepresentante">
						<option value="">-Seleccione-</option>
					</select></td>
				</tr>
				<tr>
					<td>Descripcion</td>
					<td colspan="3">
						<textarea rows="4" cols="48" id="txtNRepDescripcion"></textarea>
					</td>
				</tr>
				<tr>
					<td>Fecha Inicio</td>
					<td><input type="text" id="txtNRepFecha" class="requiredDate dtFecha" tabindex="20"/></td>
				</tr>
			</table>
			
			<center>
				<input type="button" value="Guardar" id="btnNRepProcesar" class="ui-button"/>
				<input type="button" value="Cancelar" id="btnNRepCancelar" class="ui-button"/>
			</center>
		</fieldset>
	</div>
	
	<div id="divCesarRepresentante">
		<fieldset>
			<legend>CESAR REPRESENTANTE</legend>
			<table align="center">
				<tr>
					<td>Observaciones</td>
					<td colspan="3">
						<textarea rows="4" cols="48" id="txtNRepObservacion"></textarea>
					</td>
				</tr>
				<tr>
					<td>Fecha Cese</td>
					<td><input type="text" id="txtNRepFechaCese" class="requiredDate dtFecha" tabindex="20"/></td>
				</tr>
			</table>
			
			<center>
				<input type="button" value="Guardar" id="btnNRepCese" class="ui-button"/>
				<input type="button" value="Cancelar" id="btnNRepCeseCancelar" class="ui-button"/>
			</center>
		</fieldset>
	</div>
</body>
</html>