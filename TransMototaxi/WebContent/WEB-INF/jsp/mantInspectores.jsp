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
	<script type="text/javascript" src="js/ui/jquery.ui.progressbar.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.tooltip.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.effect.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.effect-clip.js"></script>
	<script type="text/javascript" src="js/jefra.onlychar.js"></script >
	<script type="text/javascript" src="js/jefra.validate.js"></script >
	<script type="text/javascript" src="js/jefra.message.js"></script >
	<script type="text/javascript" src="js/jefra.util.js"></script >
	<script type="text/javascript" src="js/jsp/mantInspectores.js"></script>
	<title>Insert title here</title>
	<style type="text/css">
		.fileDocumento{
			width: 250px;
		}
		.txtNumeroDocumento{
			width: 150px
		}
		.txtFechaEmision, .txtFechaCaducidad{
			width: 100px
		}
		#divTab1TopLeft{
			width: 70%;
			float: left;
		}
		#divTab1TopRight{
			width: 30%;
			float: right;
		}
		#divTab1Bottom{
			float: left;
			width: 100%;
		}
		.fileFotoInspector, #formFotoInspector{
			/*width: 265px;*/
			width: 220px;
		}
		#imgFotoInspector{
			/*width: 250px;*/
			width: 200px;
		}
		.ui-progressbar .ui-progressbar-value { background-image: url(images/pbar-ani.gif); }
	</style>
</head>
<body>
	<h1>Inspectores</h1>
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
						<option value="INS.INSESTADO_C">Estado</option>
					</select>
				</td>
				<td><input type="text" id="txtTexto" /></td>
				<td><input type="button" id="btnBuscar" value="Buscar" class="ui-button"/></td>
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
		<div id="divTab1Top">
				<div id="divTab1TopLeft">
					<fieldset>
						<legend>DATOS PERSONALES</legend>
						<input type="hidden" id="txtCodigoInspector" value="0"/>
						<input type="hidden" id="txtCodigoPersona" value="0"/>
						<table align="center">
							<tr>
								<td>Nombres:</td>
								<td><input type="text" id="txtNombres" class="requiredText" tabindex="1"/></td>
								<td>Dirección:</td>
								<td><input type="text" id="txtDireccion" class="required" tabindex="9"/></td>
							</tr>
							<tr>
								<td>Paterno:</td>
								<td><input type="text" id="txtPaterno" class="requiredText" tabindex="2"/></td>
								<td>Departamento:</td>
								<td>
									<select id="sltDepartamentos" class="requiredSelect" tabindex="10">
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
								<td><input type="text" id="txtMaterno" class="requiredText" tabindex="3"/></td>
								<td>Provincia:</td>
								<td>
									<select id="sltProvincia" class="requiredSelect" tabindex="11">
										<option value="">Seleccione</option>
									</select>
								</td>
							</tr>
							<tr>
								<td>DNI</td>
								<td><input type="text" id="txtDni" class="requiredNumber" tabindex="4"/></td>
								<td>Distrito:</td>
								<td>
									<select id="sltDistrito" class="requiredSelect" tabindex="12">
										<option value="">Seleccione</option>
									</select>
								</td>
							</tr>
							<tr>
								<td>Estado Civil</td>
								<td>
									<select id="sltEstadoCivil" class="requiredSelect" tabindex="5">
										<option value="" selected="selected">Seleccione</option>
										<option value="S">Soltero</option>
										<option value="C">Casado</option>
										<option value="T">Conviviente</option>
										<option value="V">Viudo</option>
										<option value="D">Divorciado</option>
									</select>
								</td>
								<td>Telefono:</td>
								<td><input type="text" id="txtTelefono" class="requiredPhone" tabindex="13"/></td>
							</tr>
							<tr>
								<td>Sexo</td>
								<td>
									<select id="sltSexo" class="requiredSelect" tabindex="6">
										<option value="" selected="selected">Seleccione</option>
										<option value="M">Masculino</option>
										<option value="F">Femenino</option>
									</select>
								</td>
								<td>Cel. Movistar:</td>
								<td><input type="text" id="txtMovistar" class="required" tabindex="14"/></td>
							</tr>
							<tr>
								<td>Fecha Nacimiento</td>
								<td><input type="text" id="dtNacimiento" class="requiredDate" tabindex="7"/></td>
								<td>Cel. Claro:</td>
								<td><input type="text" id="txtClaro" class="required" tabindex="15"/></td>
							</tr>
							<tr>
								<td>Correo:</td>
								<td><input type="text" id="txtCorreo" class="requiredEmail" tabindex="8"/></td>
								<td>Cel. Nextel:</td>
								<td><input type="text" id="txtNextel" class="required" tabindex="16"/></td>
							</tr>
						</table>
					</fieldset>
				</div>
				<div id="divTab1TopRight" align="center">
					<fieldset>
						<legend>FOTO DEL INSPECTOR</legend>
						<img alt="Foto" id="imgFotoInspector" src="images/no_disponible.jpg"/>
						<br/>
						<form action="#" method="POST" enctype="multipart/form-data" accept-charset="utf-8" class="formFotoInspector">
							<input type="file" name="fileFotoInspector" class="fileFotoInspector" tabindex="17"/>
							<input type="submit" value="Enviar"/>
						</form>
						<div id="progressFoto"></div>
					</fieldset>
				</div>
			</div>
			<div id="divTab1Bottom">
				<fieldset>
					<legend>ADJUNTAR DOCUMENTOS</legend>
					<table id="tblDocumentos">
						<tr>
							<td>N°</td>
							<td>Adjuntar</td>
							<td>N° Documento</td>
							<td>Fec. Emision</td>
							<td>Fec. Caducidad</td>
							<td></td>
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
									<td><input type="text" name="txtNumDocumento" id="txtNumDocumento_<c:out value="${documento.mtdcodigoI}"/>" class="txtNumeroDocumento required"/></td>
									<td><input type="text" name="txtFechaEmision" id="txtFechaEmision_<c:out value="${documento.mtdcodigoI}"/>" class="txtFechaEmision dtFecha required"/></td>
									<td><input type="text" name="txtFechaCaducidad" id="txtFechaCaducidad_<c:out value="${documento.mtdcodigoI}"/>" class="txtFechaCaducidad dtFecha required"/></td>
									<td>
										<input type="file" name="fileDocumento" id="fileDocumento_<c:out value="${documento.mtdcodigoI}"/>" class="fileDocumento required"/>
										<input type="submit" value="Enviar"/>
									</td>
								</tr>
							</form:form>
						</c:forEach>
					</table>
					<div id="progressArchivo"></div>
				</fieldset>
				<center>
					<input type="button" value="Guardar" id="btnProcesar" class="ui-button" tabindex="18"/>
					<input type="button" value="Cancelar" id="btnCancelar" class="ui-button" tabindex="19"/>
				</center>
			</div>
	</div>
</body>
</html>