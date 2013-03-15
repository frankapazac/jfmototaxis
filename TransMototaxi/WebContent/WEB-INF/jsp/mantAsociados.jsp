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
	<script type="text/javascript" src="js/jsp/mantAsociados.js"></script>
	<script type="text/javascript" src="js/jsp/mantAsocMototaxis.js"></script>
	<style type="text/css">
		#tabs{
			height: 910px;
		}
		.fileDocumento{
			width: 300px;
		}
		.txtNumeroDocumento{
			width: 150px
		}
		.txtFechaEmision, .txtFechaCaducidad{
			width: 100px
		}
		#divRegistrarMoto{
			width:1000px;
			min-height:600px;
		}
		#divMotos{
			width:30%;
			float:left;
		}
		#divVehiculo{
			width:68%;
			float:right;
		}
		#divVehiculoArchivos{
			float: left;
			width: 100%;
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
		.fileFotoAsociado, #formFotoAsociado{
			width: 265px;
		}
		#imgFotoAsociado{
			width: 250px;
		} 
		.imgFotosVehiculo{
			height: 160px;
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
				<div id="divTab1Top">
					<div id="divTab1TopLeft">
						<fieldset>
							<legend>DATOS PERSONALES</legend>
							<input type="hidden" id="txtCodigoAsociado" value="0"/>
							<input type="hidden" id="txtCodigoPersona" value="0"/>
							<table align="center">
								<tr>
									<td>Nombres:</td>
									<td><input type="text" id="txtNombres" class="requiredText"/></td>
									<td>Dirección:</td>
									<td><input type="text" id="txtDireccion" class="requiredText"/></td>
								</tr>
								<tr>
									<td>Paterno:</td>
									<td><input type="text" id="txtPaterno" class="requiredText"/></td>
									<td>Departamento:</td>
									<td>
										<select id="sltDepartamentos" class="requiredSelect">
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
									<td><input type="text" id="txtMaterno" class="requiredText"/></td>
									<td>Provincia:</td>
									<td>
										<select id="sltProvincia" class="requiredSelect">
											<option value="">Seleccione</option>
										</select>
									</td>
								</tr>
								<tr>
									<td>DNI</td>
									<td><input type="text" id="txtDni" class="requiredNumber"/></td>
									<td>Distrito:</td>
									<td>
										<select id="sltDistrito" class="requiredSelect">
											<option value="">Seleccione</option>
										</select>
									</td>
								</tr>
								<tr>
									<td>RUC</td>
									<td><input type="text" id="txtRuc"/></td>
									<td>Correo:</td>
									<td><input type="text" id="txtCorreo" class="requiredEmail"/></td>
								</tr>
								<tr>
									<td>Razón Social</td>
									<td><input type="text" id="txtRazonSocial"/></td>
									<td>Teléfono:</td>
									<td><input type="text" id="txtTelefono" class="requiredNumber"/></td>
								</tr>
								<tr>
									<td>Estado Civil</td>
									<td>
										<select id="sltEstadoCivil" class="requiredSelect">
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
										<select id="sltSexo" class="requiredSelect">
											<option value="M" selected="selected">Masculino</option>
											<option value="F">Femenino</option>
										</select>
									</td>
									<td>Cel. Claro:</td>
									<td><input type="text" id="txtClaro"/></td>
								</tr>
								<tr>
									<td>Fecha Nacimiento</td>
									<td><input type="text" id="dtNacimiento" class="requiredDate"/></td>
									<td>Cel. Nextel:</td>
									<td><input type="text" id="txtNextel"/></td>
								</tr>
							</table>
						</fieldset>
					</div>
					<div id="divTab1TopRight" align="center">
						<fieldset>
							<legend>FOTO DEL ASOCIADO</legend>
							<img alt="Foto" id="imgFotoAsociado" src="images/no_disponible.jpg"/>
							<br/>
							<form action="#" method="POST" enctype="multipart/form-data" accept-charset="utf-8" class="formFotoAsociado">
								<input type="file" name="fileFotoAsociado" class="fileFotoAsociado"/>
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
						<div id="progressArchivoAsociado"></div>
					</fieldset>
					
					<input type="button" value="Guardar" id="btnProcesar" class="ui-button"/>
					<input type="button" value="Cancelar" id="btnCancelar" class="ui-button"/>
					<input type="button" value="Siguiente" id="btnSiguiente" class="ui-button"/>
				</div>
		  	</div>
			<div id="tabs2">
				<div id="divRegistrarMoto">
					<div id="divMotos">
				    	<fieldset>
				        	<legend>Motos</legend>
				        	<table id="tblMotos" align="center" class="tablesorter" style="width: 280px;"></table>
				        </fieldset>
				        <div>
				        	<input type="button" value="Nuevo" id="btnMotoNuevo"/>
				        	<input type="button" value="Editar" id="btnMotoEditar"/>
				        </div>
				    </div>
				    <div id="divVehiculo">
				    	<input type="hidden" id="txtCodigoVehiculo" value="0"/>
				    	<input type="hidden" id="txtCodigoEmpadronamiento" value="0"/>
				    	<fieldset>
				        	<legend>DATOS VEHICULO</legend>
				            <table>
				            	<tr>
				                	<td>N° Placa</td>
				                	<td><input type="text" id="txtNroPlaca" class="requiredText"/></td>
				                	<td>Carroceria</td>
				                	<td><select id="sltCarroceria" class="requiredSelect">
				                    		<option value="">Seleccione</option>
				                    		<option value="T">TRIMOVIL DE PASAJEROS</option>
				                    	</select>
				                    </td>
				                </tr>
				            	<tr>
				                	<td>Of. Registral</td>
				                	<td><select id="sltOfRegistral" class="requiredSelect">
				                    		<option value="">Seleccione</option>
				                    		<c:forEach var="oficina" items="${oficinas}">
				                    			<option value="${oficina.oficodigoI}"><c:out value="${oficina.ofinombreV}"/></option>
				                    		</c:forEach>
				                    	</select>
									</td>
				                	<td>N° Serie/Chasis</td>
				                	<td><input type="text" id="txtNroSerieChasis" class="requiredText"/></td>
				                </tr>
				            	<tr>
				                	<td>N° Padrón</td>
				                	<td><input type="text" id="txtNroPadron" class="requiredText"/></td>
				                	<td>N° Motor</td>
				                	<td><input type="text" id="txtNroMotor" class="requiredText"/></td>
				                </tr>
				            	<tr>
				                	<td>Part. Registral</td>
				                	<td><input type="text" id="txtPartRegistral" class="requiredText"/></td>
				                	<td>N° NIV</td>
				                	<td><input type="text" id="txtNroNiv" class="requiredText"/></td>
				                </tr>
				            	<tr>
				                	<td>Titulo</td>
				                	<td><input type="text" id="txtTitulo" class="requiredText"/></td>
				                	<td>Ruedas</td>
				                	<td><input type="text" id="txtRuedas" class="requiredNumber"/></td>
				                </tr>
				            	<tr>
				                	<td>Clase</td>
				                	<td><input type="text" id="txtClase" class="requiredText"/></td>
				                	<td>N° Asientos</td>
				                	<td><input type="text" id="txtNroAsientos" class="requiredNumber"/></td>
				                </tr>
				            	<tr>
				                	<td>Marca</td>
				                	<td><select id="sltMarca" class="requiredSelect">
				                    		<option value="">Seleccione</option>
				                    		<c:forEach var="marca" items="${marcas}">
				                    			<option value="${marca.marcodigoI}"><c:out value="${marca.marnombreV }"/></option>
				                    		</c:forEach>
				                    	</select></td>
				                	<td>N° Pasajeros</td>
				                	<td><input type="text" id="txtNroPasajeros" class="requiredNumber"/></td>
				                </tr>
				            	<tr>
				                	<td>Modelo</td>
				                	<td><select id="sltModelo" class="requiredSelect">
				                    		<option value="">Seleccione</option>
				                    		<c:forEach var="modelo" items="${modelos}">
				                    			<option value="${modelo.modcodigo_D }"><c:out value="${modelo.modnombre_V }" /></option>
				                    		</c:forEach>
				                    	</select></td>
				                	<td>Carga Util</td>
				                	<td><input type="text" id="txtCargaUtil" class="requiredDecimal"/></td>
				                </tr>
				            	<tr>
				                	<td>Año</td>
				                	<td><input type="text" id="txtAno" class="requiredNumber"/></td>
				                	<td>Longitud</td>
				                	<td><input type="text" id="txtLongitud" class="requiredDecimal"/></td>
				                </tr>
				            	<tr>
				                	<td>Color</td>
				                	<td><input type="text" id="txtColor" class="requiredText"/></td>
				                	<td>Ancho</td>
				                	<td><input type="text" id="txtAncho" class="requiredDecimal"/></td>
				                </tr>
				            	<tr>
				                	<td>Combustible</td>
				                	<td><select id="sltCombustible" class="requiredSelect">
				                    		<option value="">Seleccione</option>
				                    		<option value="P">PETROLEO</option>
				                    		<option value="G">GASOLINA</option>
				                    	</select></td>
				                	<td>Alto</td>
				                	<td><input type="text" id="txtAlto" class="requiredDecimal"/></td>
				                </tr>
				            </table>
				        </fieldset>
				    </div>
					<div id="divVehiculoArchivos">
						<fieldset>
				        	<legend>ADJUNTAR DOCUMENTOS</legend>
				        	<table id="tblDocumentos">
								<tr>
									<td>N°</td>
									<td>Adjuntar</td>
									<td>N° Documento</td>
									<td>Fec. Emision</td>
									<td>Fec. Caducidad</td>
									<td>Porcentaje: <input type="text" id="txtCargandoUnidad" size="4"/>%</td>
								</tr>
					            <c:forEach var="docunidad" items="${documentosUnidad}" varStatus="status">
									<form:form action="#" method="POST" enctype="multipart/form-data" acceptCharset="utf-8" cssClass="formDocumentoUnidad">
										<tr>
											<td>
												<c:out value="${status.count}"/>
												<input type="hidden" name="txtCodDocumento" value="<c:out value="${docunidad.mtdcodigoI}"/>"/>
												<input type="hidden" name="txtCodArchivo" id="documentoUnd_<c:out value="${docunidad.mtdcodigoI}"/>" value="0"/>
											</td>
											<td><c:out value="${docunidad.mtdnombreV}"/></td>
											<td><input type="text" name="txtNumDocumento" id="txtUndNumDocumento_<c:out value="${docunidad.mtdcodigoI}"/>" class="txtNumeroDocumento txtVehNumDocumento requiredNumber"/></td>
											<td><input type="text" name="txtFechaEmision" id="txtUndFechaEmision_<c:out value="${docunidad.mtdcodigoI}"/>" class="txtFechaEmision dtFecha txtVehFecEmision requiredDate"/></td>
											<td><input type="text" name="txtFechaCaducidad" id="txtUndFechaCaducidad_<c:out value="${docunidad.mtdcodigoI}"/>" class="txtFechaCaducidad dtFecha txtVehFecCaducidad requiredDate"/></td>
											<td>
												<form action="#" method="POST" enctype="multipart/form-data" accept-charset="utf-8" class="formDocumento">
													<input type="file" name="fileDocumento" id="fileUndDocumento_<c:out value="${docunidad.mtdcodigoI}"/>" class="fileDocumento requiredFile"/>
													<input type="submit" value="Enviar"/>
												</form>
											</td>
										</tr>
									</form:form>
								</c:forEach>
							</table>
							<div id="progressMototaxiArchivo"></div>
				        </fieldset>
				    	<fieldset>
				        	<legend>ADJUNTAR FOTOS</legend>
				            <table id="tblDocumentos">
								<tr>
									<td>N°</td>
									<td>Adjuntar</td>
									<td></td>
								</tr>
					            <c:forEach var="docunidadfoto" items="${documentosUnidadFotos}" varStatus="status">
									<form:form action="#" method="POST" enctype="multipart/form-data" acceptCharset="utf-8" cssClass="formDocumentoFoto">
										<tr>
											<td>
												<c:out value="${status.count}"/>
												<input type="hidden" name="txtCodDocumento" value="<c:out value="${docunidadfoto.mtdcodigoI}"/>"/>
												<input type="hidden" name="txtCodArchivo" id="documento_<c:out value="${docunidadfoto.mtdcodigoI}"/>" value="0"/>
											</td>
											<td><c:out value="${docunidadfoto.mtdnombreV}"/></td>
											<td>
												<form action="#" method="POST" enctype="multipart/form-data" accept-charset="utf-8" class="formDocumento">
													<input type="file" name="fileDocumento" id="fileDocumento_<c:out value="${docunidadfoto.mtdcodigoI}"/>" class="fileDocumento"/>
													<input type="submit" value="Enviar" id="btnFoto_<c:out value="${docunidadfoto.mtdcodigoI}"/>"/>
												</form>
											</td>
										</tr>
									</form:form>
								</c:forEach>
							</table>
			
							<div id="gallery" align="center">
								<c:forEach var="docunidadfoto" items="${documentosUnidadFotos}" varStatus="status">
									<img alt="<c:out value="${docunidadfoto.mtdnombreV}"/>" id="imgFotoVehiculo_<c:out value="${docunidadfoto.mtdcodigoI}"/>" class="imgFotosVehiculo" src="images/no_disponible.jpg"/>
								</c:forEach>
								<div id="progressVehiculo"></div>
							</div>
			
				        </fieldset>
				        <div>
				        	<input type="button" value="Agregar" id="btnVehiculoProcesar"/>
				        	<input type="button" value="Cancelar" id="btnVehiculoCancelar"/>
				        </div>
					</div>
				</div>
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