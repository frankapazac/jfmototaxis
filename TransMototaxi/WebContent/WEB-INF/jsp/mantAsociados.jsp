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
	<script type="text/javascript" src="js/ui/jquery.ui.effect.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.effect-clip.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.tabs.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.position.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.dialog.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.datepicker.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.progressbar.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.tooltip.js"></script>
	<script type="text/javascript" src="js/jefra.validate.js"></script>
	<script type="text/javascript" src="js/jefra.message.js"></script>
	<script type="text/javascript" src="js/jefra.util.js"></script>
	<script type="text/javascript" src="js/jsp/mantAsociados.js"></script>
	<!--script type="text/javascript" src="js/jsp/mantAsocMototaxis.js"></script-->
	<style type="text/css">
		#tabs{
			height: 660px;
		}
		.fileDocumento{
			width: 180px;
		}
		.txtNumeroDocumento{
			width: 150px
		}
		.txtFechaEmision, .txtFechaCaducidad{
			width: 100px
		}
		#divRegistrarMoto{
			width:775px;
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
			width: 210px;
		}
		#imgFotoAsociado{
			width: 200px;
		} 
		.imgFotosVehiculo{
			width: 155px;
		}
		.txtCons{
			width: 100px;
		}
		.txtConsNumero{
			font-size: 30px !important;
			height: 50px;
			width: 150px;
		}
		.txtConsCodigo{
			font-size: 15px !important;
			width: 150px;
		}
	</style>
	
<title>Insert title here</title>
</head>
<body>
	<h1>AFILIADOS</h1>
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
				<td><input type="button" id="btnBuscar" value="Buscar" class="ui-button"/></td>
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
				<li><a href="#tabs1" tabindex="0">DATOS PROPIETARIO</a></li>
				<li><a href="#tabs2" tabindex="23">REGISTRAR MOTO</a></li>
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
									<td><input type="text" id="txtNombres" class="requiredText" tabindex="1"/></td>
									<td>Dirección:</td>
									<td><input type="text" id="txtDireccion" class="required" tabindex="10"/></td>
								</tr>
								<tr>
									<td>Paterno:</td>
									<td><input type="text" id="txtPaterno" class="requiredText" tabindex="2"/></td>
									<td>Departamento:</td>
									<td>
										<select id="sltDepartamentos" class="requiredSelect" tabindex="11">
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
										<select id="sltProvincia" class="requiredSelect" tabindex="12">
											<option value="">Seleccione</option>
										</select>
									</td>
								</tr>
								<tr>
									<td>DNI</td>
									<td><input type="text" id="txtDni" class="requiredNumber" tabindex="4"/></td>
									<td>Distrito:</td>
									<td>
										<select id="sltDistrito" class="requiredSelect" tabindex="13">
											<option value="">Seleccione</option>
										</select>
									</td>
								</tr>
								<tr>
									<td>RUC</td>
									<td><input type="text" id="txtRuc" tabindex="5"/></td>
									<td>Correo:</td>
									<td><input type="text" id="txtCorreo" class="requiredEmail" tabindex="14"/></td>
								</tr>
								<tr>
									<td>Razón Social</td>
									<td><input type="text" id="txtRazonSocial" tabindex="6"/></td>
									<td>Teléfono:</td>
									<td><input type="text" id="txtTelefono" class="requiredPhone" tabindex="15"/></td>
								</tr>
								<tr>
									<td>Estado Civil</td>
									<td>
										<select id="sltEstadoCivil" class="requiredSelect" tabindex="7">
											<option value="" selected="selected">Seleccione</option>
											<option value="S">Soltero</option>
											<option value="C">Casado</option>
											<option value="T">Conviviente</option>
											<option value="V">Viudo</option>
											<option value="D">Divorciado</option>
										</select>
									</td>
									<td>Cel. Movistar:</td>
									<td><input type="text" id="txtMovistar" class="required" tabindex="16"/></td>
								</tr>
								<tr>
									<td>Sexo</td>
									<td>
										<select id="sltSexo" class="requiredSelect" tabindex="8">
											<option value="" selected="selected">Seleccione</option>
											<option value="M">Masculino</option>
											<option value="F">Femenino</option>
										</select>
									</td>
									<td>Cel. Claro:</td>
									<td><input type="text" id="txtClaro" class="required" tabindex="17"/></td>
								</tr>
								<tr>
									<td>Fecha Nacimiento</td>
									<td><input type="text" id="dtNacimiento" class="requiredDate" tabindex="9"/></td>
									<td>Cel. Nextel:</td>
									<td><input type="text" id="txtNextel" class="required" tabindex="18"/></td>
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
								<input type="file" name="fileFotoAsociado" class="fileFotoAsociado" tabindex="19"/>
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
					
					<center>
						<input type="button" value="Guardar" id="btnProcesar" class="ui-button" tabindex="20"/>
						<input type="button" value="Cancelar" id="btnCancelar" class="ui-button" tabindex="21"/>
						<input type="button" value="Siguiente" id="btnSiguiente" class="ui-button" tabindex="22"/>
					</center>
				</div>
		  	</div>
			<div id="tabs2">
				<div id="divRegistrarMoto">
					<div id="divMotos">
				    	<fieldset>
				        	<legend>Motos</legend>
				        	<table id="tblMotos" align="center" class="tablesorter tblTablaEstilo" style="width: 220px;"></table>
				        </fieldset>
				        <div>
				        	<input type="button" value="Nuevo" id="btnMotoNuevo" tabindex="24" class="ui-button"/>
				        	<input type="button" value="Editar" id="btnMotoEditar" tabindex="25" class="ui-button"/>
				        	<input type="button" value="Cese" id="btnMotoCese" class="ui-button"/>
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
				                	<td><input type="text" id="txtNroPlaca" class="requiredAlpha" tabindex="26"/></td>
				                	<td>Carroceria</td>
				                	<td><select id="sltCarroceria" class="requiredSelect" tabindex="37">
				                    		<option value="" selected="selected">Seleccione</option>
				                    		<option value="T">TRIMOVIL DE PASAJEROS</option>
				                    	</select>
				                    </td>
				                </tr>
				            	<tr>
				                	<td>Of. Registral</td>
				                	<td><select id="sltOfRegistral" class="requiredSelect" tabindex="27">
				                    		<option value="">Seleccione</option>
				                    		<c:forEach var="oficina" items="${oficinas}">
				                    			<option value="${oficina.oficodigoI}"><c:out value="${oficina.ofinombreV}"/></option>
				                    		</c:forEach>
				                    	</select>
									</td>
				                	<td>N° Serie/Chasis</td>
				                	<td><input type="text" id="txtNroSerieChasis" class="requiredAlpha" tabindex="38"/></td>
				                </tr>
				            	<tr>
				                	<td>N° Padrón</td>
				                	<td><input type="text" id="txtNroPadron" class="requiredNumber" tabindex="28"/></td>
				                	<td>N° Motor</td>
				                	<td><input type="text" id="txtNroMotor" class="requiredAlpha" tabindex="39"/></td>
				                </tr>
				            	<tr>
				                	<td>Part. Registral</td>
				                	<td><input type="text" id="txtPartRegistral" class="requiredAlpha" tabindex="29"/></td>
				                	<td>N° NIV</td>
				                	<td><input type="text" id="txtNroNiv" class="requiredAlpha" tabindex="40"/></td>
				                </tr>
				            	<tr>
				                	<td>Titulo</td>
				                	<td><input type="text" id="txtTitulo" class="requiredAlpha" tabindex="30"/></td>
				                	<td>Ruedas</td>
				                	<td><input type="text" id="txtRuedas" class="requiredNumber" tabindex="41"/></td>
				                </tr>
				            	<tr>
				                	<td>Clase</td>
				                	<td><input type="text" id="txtClase" class="requiredAlpha" tabindex="31"/></td>
				                	<td>N° Asientos</td>
				                	<td><input type="text" id="txtNroAsientos" class="requiredNumber" tabindex="42"/></td>
				                </tr>
				            	<tr>
				                	<td>Marca</td>
				                	<td><select id="sltMarca" class="requiredSelect" tabindex="32">
				                    		<option value="" selected="selected">Seleccione</option>
				                    		<c:forEach var="marca" items="${marcas}">
				                    			<option value="${marca.marcodigoI}"><c:out value="${marca.marnombreV }"/></option>
				                    		</c:forEach>
				                    	</select></td>
				                	<td>N° Pasajeros</td>
				                	<td><input type="text" id="txtNroPasajeros" class="requiredNumber" tabindex="43"/></td>
				                </tr>
				            	<tr>
				                	<td>Modelo</td>
				                	<td><select id="sltModelo" class="requiredSelect" tabindex="33">
				                    		<option value="" selected="selected">Seleccione</option>
				                    		<c:forEach var="modelo" items="${modelos}">
				                    			<option value="${modelo.modcodigo_D }"><c:out value="${modelo.modnombre_V }" /></option>
				                    		</c:forEach>
				                    	</select></td>
				                	<td>Carga Util</td>
				                	<td><input type="text" id="txtCargaUtil" class="requiredDecimal" tabindex="44"/>Kg.</td>
				                </tr>
				            	<tr>
				                	<td>Año</td>
				                	<td><input type="text" id="txtAno" class="requiredNumber" tabindex="34"/></td>
				                	<td>Longitud</td>
				                	<td><input type="text" id="txtLongitud" class="requiredDecimal" tabindex="45"/>Mts.</td>
				                </tr>
				            	<tr>
				                	<td>Color</td>
				                	<td><input type="text" id="txtColor" class="requiredText" tabindex="35"/></td>
				                	<td>Ancho</td>
				                	<td><input type="text" id="txtAncho" class="requiredDecimal" tabindex="46"/>Mts.</td>
				                </tr>
				            	<tr>
				                	<td>Combustible</td>
				                	<td><select id="sltCombustible" class="requiredSelect" tabindex="36">
				                    		<option value="" selected="selected">Seleccione</option>
				                    		<option value="P">PETROLEO</option>
				                    		<option value="G">GASOLINA</option>
				                    	</select></td>
				                	<td>Alto</td>
				                	<td><input type="text" id="txtAlto" class="requiredDecimal" tabindex="47"/>Mts.</td>
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
									<td></td>
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
											<td><input type="text" name="txtNumDocumento" id="txtUndNumDocumento_<c:out value="${docunidad.mtdcodigoI}"/>" class="txtNumeroDocumento txtVehNumDocumento required"/></td>
											<td><input type="text" name="txtFechaEmision" id="txtUndFechaEmision_<c:out value="${docunidad.mtdcodigoI}"/>" class="txtFechaEmision dtFecha txtVehFecEmision required"/></td>
											<td><input type="text" name="txtFechaCaducidad" id="txtUndFechaCaducidad_<c:out value="${docunidad.mtdcodigoI}"/>" class="txtFechaCaducidad dtFecha txtVehFecCaducidad required"/></td>
											<td>
												<form action="#" method="POST" enctype="multipart/form-data" accept-charset="utf-8" class="formDocumento">
													<input type="file" name="fileDocumento" id="fileUndDocumento_<c:out value="${docunidad.mtdcodigoI}"/>" class="fileDocumento required"/>
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
							<div id="gallery">
								<c:forEach var="docunidadfoto" items="${documentosUnidadFotos}" varStatus="status">
									<div style="width: 187px; float: left;" align="center"> 
										<div style="width: 158px; height: 130px">
											<img alt="<c:out value="${docunidadfoto.mtdnombreV}"/>" id="imgFotoVehiculo_<c:out value="${docunidadfoto.mtdcodigoI}"/>" class="imgFotosVehiculo" src="images/no_disponible.jpg"/>
										</div>
										<p><c:out value="${docunidadfoto.mtdnombreV}"/></p>
									</div>
								</c:forEach>
								<div id="progressVehiculo"></div>
							</div>
					
					
				        </fieldset>
				        <div>
					        <center>
					        	<input type="button" value="Agregar" id="btnVehiculoProcesar" class="ui-button"/>
				        		<input type="button" value="Cancelar" id="btnVehiculoCancelar" class="ui-button"/>
					        </center>
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
		<img id="btnNuevo" alt="Nuevo" src="images/Add.png"/>
		<img id="btnImprimirPdf" alt="Nuevo" src="images/acroread.png"/>
	</h4>
	
	<div id="divFormularioCese">
		<h1 id="txtNombreAsociado">NOMBRE DEL CONDUCTOR</h1>		
		<input type="hidden" id="txtCodigoCese"/>
		<p id="">Fecha</p><input type="text" id="txtFechaCese" class="dtFecha"/>
		<p>Observaciones</p>
		<textarea cols="50" rows=4 id="txtObservacion"></textarea>
		<table>
			<tr>
				<td><input type="button" id="btnGuardarCese" value="Cese" class="ui-button"/></td>
				<td><input type="button" id="btnCancelarCese" value="Cancelar" class="ui-button"/></td>
			</tr>
		</table>
	</div>	
	
	<div id="divFormularioMotoCese">
		<h1 id="txtPlacaMoto">PLACA DE LA MOTO</h1>		
		<input type="hidden" id="txtCodigoMotoCese"/>
		<p id="">Fecha</p><input type="text" id="txtFechaMotoCese" class="dtFecha"/>
		<p>Observaciones</p>
		<textarea cols="50" rows=4 id="txtObservacionMoto"></textarea>
		<table>
			<tr>
				<td><input type="button" id="btnGuardarMotoCese" value="Cese" class="ui-button"/></td>
				<td><input type="button" id="btnCancelarMotoCese" value="Cancelar" class="ui-button"/></td>
			</tr>
		</table>
	</div>	
	
	<div id="divConstancia" align="center">
		<fieldset>
			<legend>Datos del Mototaxi</legend>
			<table>
				<tr>
					<td>Seleccione Mototaxi</td>
					<td>
						<select id="sltMototaxi">
							<option value="">Seleccione</option>
						</select>
					</td>
					<td>Seleccione Conductor</td>
					<td>
						<select id="sltConductor">
							<option value="">Seleccione</option>
							<c:forEach items="${conductores}" var="conductor">
								<option value="${conductor.ecocodigoD}">
									<c:out value="${conductor.conductor.persona.pernombresV}"/> <c:out value="${conductor.conductor.persona.perpaternoV}"/> <c:out value="${conductor.conductor.persona.permaternoV}"/>
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</table>
		</fieldset>
		<fieldset style="background-color: #f0f0f0;">
			<legend>Datos de la Constancia</legend>
			<div id="divConstanciaPrincipal">
				<div style="width: 60%;float: left;">
					<h3><c:out value="${USUARIO.empresa.emprazonsocialV}"/></h3>
					<table>
						<tr>
							<td>TOMO:</td>
							<td><input type="text" class="txtCons" id="txtConsTomo"/></td>
							<td>ASIENTO:</td>
							<td><input type="text" class="txtCons" id="txtConsAsiento"/></td>
							<td>FICHA:</td>
							<td><input type="text" class="txtCons" id="txtConsFicha"/></td>
						</tr>
						<tr>
							<td>RUC:</td>
							<td><input type="text" class="txtCons" id="txtConsRuc" value="<c:out value="${USUARIO.empresa.emprucV}"/>"/></td>
							<td>TELF:</td>
							<td><input type="text" class="txtCons" id="txtConsTelefono"/></td>
							<td>CEL:</td>
							<td><input type="text" class="txtCons" id="txtConsCelular"/></td>
						</tr>
						<tr>
							<td colspan="6"><c:out value="${USUARIO.empresa.empdireccionV}"/></td>
						</tr>
						<tr>
							<td colspan="6">LURIGANCHO - CHOSICA</td>
						</tr>
					</table>
				</div>
				<div style="width: 35%; height:100%; float: left; background-color: #C0EDB6; padding: 20px">
					<table>
						<tr>
							<td>N°</td>
							<td><input type="text" class="txtConsNumero"/></td>
						</tr>
						<tr>
							<td>Código:</td>
							<td><input type="text" class="txtConsCodigo"/></td>
						</tr>
					</table>
				</div>
			</div>
		</fieldset>
		<fieldset>
			<h3>El Gerente General de la Empresa, el que suscribe, otorga la</h3>
			<h1>CONSTANCIA DE AFILIACIÓN</h1>
			<h2>A don(ña): <label id="txtConsPropietario">Propietario del Vehiculo</label> DNI: <label id="txtConsPropietarioDni">DNI</label></h2>
			<p>Propietario del Vehiculo Menor con Placa de Rodaje: <label id="txtConsPlaca">Placa</label>, Marca <label id="txtConsMarca">Marca</label>, 
			Motor: <label id="txtConsMotor">Motor</label>, Serie: <label id="txtConsSerie">Serie</label>, Color: <label id="txtConsColor">Color</label>, 
			Modelo: <label id="txtConsModelo">Modelo</label>, Año de Fabricación: <label id="txtConsAno">Ano</label>, 
			Combustible: <label id="txtConsCombustible">Combustible</label>, Pasajeros: <label id="txtConsPasajeros">Pasajeros</label>, 
			CAT: <label id="txtConsCAT">CAT</label> con periodo de vigencia: <label id="txtConsCATInicio">CAT Inicio</label> hasta 
			<label id="txtConsCATFin">CAT Fin</label>. CHOFER: <label id="txtConsChofer">Chofer</label>, DNI: <label id="txtConsChoferDNI">DNI</label>, 
			Licencia de Conducir: <label id="txtConsLicencia">Licencia</label>, Fecha de Revalidación: <label id="txtConsFecha">Fecha</label>, 
			domiciliado en <label id="txtConsDomicilio">Domicilio</label></p>
			<center>
				<input type="button" value="Guardar" id="btnGuardarConstancia" class="ui-button" tabindex="20" class="ui-button"/>
				<input type="button" value="Imprimir" id="btnImprimirConstancia" class="ui-button" tabindex="20" class="ui-button"/>
				<input type="button" value="Cancelar" id="btnCancelarConstancia" class="ui-button" tabindex="20" class="ui-button"/>
			</center>
		</fieldset>
	</div>
	
	<div id="divMensaje"></div>
</body>
</html>