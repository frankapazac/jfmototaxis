<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
	<script type="text/javascript" src="js/jsp/mantAsociados.js"></script>
	<style type="text/css">
		.fileDocumento{
			width: 300px;
		}
		.txtNumeroDocumento{
			width: 150px
		}
		.txtFechaEmision, .txtFechaCaducidad{
			width: 100px
		}
	</style>
<title>Insert title here</title>
</head>
<body>
	<h1>Asociados</h1>
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
						<option value="PER.PERDNI_V">DNI</option>
						<option value="PER.PERNOMBRES_V">Nombres</option>
						<option value="PER.PERPATERNO_V">Paterno</option>
						<option value="PER.PERMATERNO_V">Materno</option>
						<option value="PER.PEREMAIL_V">Correo</option>
						<option value="ASO.ASOESTADO_C">Estado</option>
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
				<li><a href="#tabs1">DATOS PROPIETARIO</a></li>
				<li><a href="#tabs2">REGISTRAR MOTO</a></li>
			</ul>
			<div id="tabs1">
				<fieldset>
					<legend>DATOS PERSONALES</legend>
					<input type="hidden" id="txtCodigoAsociado" value="0"/>
					<input type="hidden" id="txtCodigoPersona" value="0"/>
					<table align="center">
						<tr>
							<td>Nombres:</td>
							<td><input type="text" id="txtNombres"/></td>
							<td>Dirección:</td>
							<td><input type="text" id="txtDireccion"/></td>
						</tr>
						<tr>
							<td>Paterno:</td>
							<td><input type="text" id="txtPaterno"/></td>
							<td>Departamento:</td>
							<td>
								<select id="sltDepartamentos">
									<option value="">Seleccione</option>
									<c:forEach items="${departamentos}" var="departamento">
										<option value="${departamento.idubigeo}">
											<c:out value="${departamento.nombubigeo}"/>
										</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td>Materno:</td>
							<td><input type="text" id="txtMaterno"/></td>
							<td>Provincia:</td>
							<td>
								<select id="sltProvincia">
									<option value="">Seleccione</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>DNI</td>
							<td><input type="text" id="txtDni"/></td>
							<td>Distrito:</td>
							<td>
								<select id="sltDistrito">
									<option value="">Seleccione</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>RUC</td>
							<td><input type="text" id="txtRuc"/></td>
							<td>Correo:</td>
							<td><input type="text" id="txtCorreo"/></td>
						</tr>
						<tr>
							<td>Razón Social</td>
							<td><input type="text" id="txtRazonSocial"/></td>
							<td>Telefono:</td>
							<td><input type="text" id="txtTelefono"/></td>
						</tr>
						<tr>
							<td>Estado Civil</td>
							<td>
								<select id="sltEstadoCivil">
									<option value="S" selected="selected">Soltero</option>
									<option value="C">Casado</option>
									<option value="T">Conviviente</option>
									<option value="V">Viudo</option>
									<option value="D">Divorciado</option>
								</select>
							</td>
							<td>Cel. Movistar:</td>
							<td><input type="text" id="txtMovistar"/></td>
						</tr>
						<tr>
							<td>Sexo</td>
							<td>
								<select id="sltSexo">
									<option value="M" selected="selected">Masculino</option>
									<option value="F">Femenino</option>
								</select>
							</td>
							<td>Cel. Claro:</td>
							<td><input type="text" id="txtClaro"/></td>
						</tr>
						<tr>
							<td>Fecha Nacimiento</td>
							<td><input type="text" id="dtNacimiento"/></td>
							<td>Cel. Nextel:</td>
							<td><input type="text" id="txtNextel"/></td>
						</tr>
					</table>
				</fieldset>
				<fieldset>
					<legend>ADJUNTAR DOCUMENTOS</legend>
					<table id="tblDocumentos">
						<tr>
							<td colspan="2">N°</td>
							<td>Adjuntar</td>
							<td>N° Documento</td>
							<td>Fec. Emision</td>
							<td>Fec. Caducidad</td>
							<td>Porcentaje: <input type="text" id="txtCargando" size="4"/>%</td>
						</tr>
						<c:forEach var="documento" items="${documentos}" varStatus="status">
							<form:form action="#" method="POST" enctype="multipart/form-data" acceptCharset="utf-8" cssClass="formDocumento">
								<tr>
									<td>
										<c:out value="${status.count}"/>
										<input type="hidden" name="txtCodDocumento" value="<c:out value="${documento.mtdcodigoI}"/>"/>
										<input type="hidden" name="txtCodArchivo" id="documento_<c:out value="${documento.mtdcodigoI}"/>" value="0"/>
									</td>
									<td><c:out value="${documento.mtdnombreV}"/></td>
									<td><input type="text" name="txtNumDocumento" id="txtNumDocumento_<c:out value="${documento.mtdcodigoI}"/>" class="txtNumeroDocumento"/></td>
									<td><input type="text" name="txtFechaEmision" id="txtFechaEmision_<c:out value="${documento.mtdcodigoI}"/>" class="txtFechaEmision dtFecha"/></td>
									<td><input type="text" name="txtFechaCaducidad" id="txtFechaCaducidad_<c:out value="${documento.mtdcodigoI}"/>" class="txtFechaCaducidad dtFecha"/></td>
									<td colspan="2">
										<form action="#" method="POST" enctype="multipart/form-data" accept-charset="utf-8" class="formDocumento">
											<input type="file" name="fileDocumento" id="fileDocumento_<c:out value="${documento.mtdcodigoI}"/>" class="fileDocumento"/>
											<input type="submit" value="Enviar"/>
										</form>
									</td>
								</tr>
							</form:form>
						</c:forEach>
					</table>
				</fieldset>
				<input type="button" value="Guardar" id="btnProcesar" class="ui-button"/>
				<input type="button" value="Cancelar" id="btnCancelar" class="ui-button"/>

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
		<img id="btnNuevo" alt="Nuevo" src="images/new.png"/>
		<a href="Reporte/Persona/DescargarPdf.jf">
			<img alt="Imprimir" src="images/pdf.png">
		</a>
	</h4>
	<div id="divMensaje"></div>
</body>
</html>