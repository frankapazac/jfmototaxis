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
			</tr>
			<tr>
				<td>
					<select id="sltCriterio">
						<option value="EMP.EMPRAZONSOCIAL_V">Nombres</option>
						<option value="EMP.EMPDIRECCION_V">Dirección</option>
						<option value="EMP.EMPRUC_V">RUC</option>
						<option value="ZON.ZONNOMBRE_V">ZONA</option>
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
	
	<h4 align="center">
		<img id="btnNuevo" alt="Nuevo" src="images/Add.png"/>
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
					<td>Razón Social:</td>
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
					<td>Dirección:</td>
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
					<td>Inicio de Operación:</td>
					<td><input type="text" id="txtInicio" class="dtFecha"/></td>
				</tr>
				<tr>
					<td>Distrito:</td>
					<td><select id="sltDistrito" class="requiredSelect">
							<option value="">Seleccione</option>
						</select></td>
					<td>Cese de Operación:</td>
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
					<td>Escritura Pública:</td>
					<td><input type="text" id="txtEscritura" /></td>
				</tr>
				<tr>
					<td>Resolución:</td>
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
			<legend>DATOS DEL USUARIO</legend>
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
			<legend>DATOS DEL REPRESENTANTE</legend>
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
		</center>
	</div>
</body>
</html>