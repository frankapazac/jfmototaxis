<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script type="text/javascript" src="js/tablesorter/jquery.tablesorter.js"></script>
	<script type="text/javascript" src="js/tablesorter/jquery.tablesorter.pager.js"></script>
	<script type="text/javascript" src="js/jquery.form.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.effect.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.effect-clip.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.core.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.tabs.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.position.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.dialog.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.datepicker.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.progressbar.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.tooltip.js"></script>
	<script type="text/javascript" src="js/jefra.validate.js"></script >
	<script type="text/javascript" src="js/jefra.message.js"></script >
	<script type="text/javascript" src="js/jefra.util.js"></script >
	<script type="text/javascript" src="js/jsp/mantConductores.js"></script>
	<script type="text/javascript" src="js/jsp/mantConductoresAsignar.js"></script>
<title>Insert title here</title>
</head>
<style type="text/css">
	.fileDocumento{
		width: 210px;
	}
	.txtNumeroDocumento{
		width: 150px
	}
	.txtFechaEmision, .txtFechaCaducidad{
		width: 100px
	}	
	#divAsignarIzq{
		width:38%;
		float:left;
	}
	#divAsignarDer{
		width:58%;
		float:right;
	}
	#divAsignarAba{
		width:100%;	
		float:left;
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
	.fileFotoConductor, #formFotoConductor{
		width: 215px;
	}
	#imgFotoConductor{
		width: 188px;
	}
</style>
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
				<td><input type="button" id="btnBuscar" value="Buscar" class="ui-button"/></td>
				<td><input type="button" id="btnAsignarMoto" value="Mototaxi Asignar/Cese" class="ui-button"/></td>
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
		<h1>Conductor: <label id="txtNombreConductor">NOMBRE DEL CONDUCTOR</label></h1>		
		<div id="tabs">
			<ul>
			</ul>
			<div id="tabs1">
				<div id="divBuscar" align="center">
				<table>
					<tr>
						<td>Criterio</td>
						<td>Texto</td>
						<td></td>
					</tr>
					<tr>
						<td>
							<select id="sltCriterioAsignado">
								<option value="PER.PERNOMBRES_V">Nombres</option>
								<option value="PER.PERPATERNO_V">Apellido Paterno</option>
								<option value="PER.PERMATERNO_V">Apellido Materno</option>
								<option value="PER.PERDNI_V">DNI</option>
								<option value="UEM.UNEPLACANRO_V">Placa</option>
								<option value="MAR.MARNOMBRE_V">Marca</option>
								<option value="MDL.MODNOMBRE_V">Modelo</option>
								<option value="UEM.UNEANNO_C">A�o</option>
								<option value="UEM.UNECOLOR_V">Color</option>
							</select>
						</td>
						<td><input type="text" id="txtTextoAsignado" /></td>
						<td><input type="button" id="btnBuscarAsignado" value="Buscar" class="ui-button"/></td>
					</tr>
				</table>
				</div>				
			</div>
		</div>

	  	<div id="divListaMotosAsignadas">
			<table align="center" class="tablesorter" id="tblListaMotosAsignadas">		
			</table>
			
			<div id="pagerMotosAsignadas" class="pager" align="center">
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
		
		<div style="margin: 10px 0px;">
			<center>
				<input type="hidden" id="txtCodigo"/>
				<input type="button" value="Asignar Mototaxi" id="btnAsignar" class="ui-button"/>
				<input type="button" value="Cese" id="btnCese" class="ui-button"/>
				<input type="button" id="btnCancelarFormulario" value="Cancelar" class="ui-button"/>
			</center>
		</div>				
	</div>
	
	<div id="divFormularioCese">
		<h1 id="txtNombreConductorCese">NOMBRE DEL CONDUCTOR</h1>		
		<input type="hidden" id="txtCodigoCese"/>
		<input type="hidden" id="txtOcultoCodigoUndConductor"/>
		<p id="">Fecha</p><input type="text" id="txtFechaCese"/>
		<p>Observaciones</p>
		<textarea cols="50" rows=4 id="txtObservacion"></textarea>
		<table>
			<tr>
				<td><input type="button" id="btnGuardarCese" value="Cese" class="ui-button"/></td>
				<td><input type="button" id="btnCancelarCese" value="Cancelar" class="ui-button"/></td>
			</tr>
		</table>
	</div>	
	
	<div id="divAsignarMototaxi">
		<div>
	        <div id="divAsignarIzq">
	            <fieldset>
	                <legend>PROPIETARIOS</legend>
	                <div class="divBuscar">
	                    <table>
	                        <tr>
	                            <td>Criterio</td>
	                            <td>Texto</td>
	                            <td></td>
	                        </tr>
	                        <tr>
	                            <td>
	                                <select id="sltCriterioPropietario">
	                                    <option value="PER.PERDNI_V">DNI</option>
	                                    <option value="PER.PERNOMBRES_V">Nombres</option>
	                                    <option value="PER.PERPATERNO_V">Paterno</option>
	                                    <option value="PER.PERMATERNO_V">Materno</option>
	                                </select>
	                            </td>
	                            <td><input type="text" id="txtTextoPropietario" /></td>
	                            <td><input type="button" id="btnBuscarPropietarios" value="Buscar" class="ui-button"/></td>
	                        </tr>
	                    </table>
	                </div>
	                <div class="divLista">
	                    <table align="center" class="tablesorter" id="tblListaAsociados">		
	                    </table>
	                </div>
	            </fieldset>
	        </div>
	        <div id="divAsignarDer">
	            <fieldset>
	                <legend>MOTOTAXI</legend>
	                <div class="divBuscar">
	                    <table>
	                        <tr>
	                            <td>Criterio</td>
	                            <td>Texto</td>
	                            <td></td>
	                        </tr>
	                        <tr>
	                            <td>
	                                <select id="sltCriterioMototaxi">
	                                    <option value="UNE.UNEPLACANRO_V">N� PLACA</option>
	                                    <option value="MAR.MARNOMBRE_V">MARCA</option>
	                                    <option value="MDL.MODNOMBRE_V">MODELO</option>
	                                    <option value="UNE.UNEANNO_C">A�O</option>
	                                    <option value="UNE.UNECOLOR_V">COLOR</option>
	                                </select>
	                            </td>
	                            <td><input type="text" id="txtTextoMototaxi" /></td>
	                            <td><input type="button" id="btnBuscarMototaxi" value="Buscar" class="ui-button"/></td>
	                        </tr>
	                    </table>
	                </div>
	                
	                <div class="divLista">
	                    <table align="center" class="tablesorter" id="tblListaMototaxis">		
	                    </table>
	                </div>
	     		</fieldset>
	    	</div>
		</div>
		<div id="divAsignarAba">
	    	<fieldset>
	        	<legend>MOTOS PARA ASIGNAR</legend>            
	            <div class="divLista">
	                <table align="center" class="tablesorter" id="tblListaMotosParaAsignar">		
	                </table>
	            </div>
	            <center>
	            	<input type="button" id="btnMotosAsignar" value="Asignar Mototaxi" class="ui-button"/>
                	<input type="button" id="btnMotosAsignarCancelar" value="Cancelar" class="ui-button"/>
	            </center>
	        </fieldset>
	    </div>
	</div>
	
		<!-- MANTENIMIENTO CONDUCTOR  -->
		
		<div id="divNuevoCond">
			<div id="divTab1Top">
				<div id="divTab1TopLeft">
			    	<fieldset>
			        	<legend>NUEVO CONDUCTOR</legend>     
			        	<input type="hidden" id="txtCodigoConductor" value="0"/>
						<input type="hidden" id="txtCodigoPersona" value="0"/>        
			        	<table align="center">
								<tr>
									<td>Nombres:</td>
									<td><input type="text" id="txtNombres" class="requiredText" tabindex="1"/></td>
									<td>Direcci�n:</td>
									<td><input type="text" id="txtDireccion" class="requiredAlpha" tabindex="10"/></td>
								</tr>
								<tr>
									<td>Paterno:</td>
									<td><input type="text" id="txtPaterno" class="requiredText" tabindex="2"/></td>
									<td>Departamento:</td>
									<td>
										<select id="sltDepartamentos" class="requiredSelect" tabindex="11">
											<option value="" selected="selected">Seleccione</option>
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
											<option value="" selected="selected">Seleccione</option>
										</select>
									</td>
								</tr>
								<tr>
									<td>DNI</td>
									<td><input type="text" id="txtDni" class="requiredNumber" tabindex="4"/></td>
									<td>Distrito:</td>
									<td>
										<select id="sltDistrito" class="requiredSelect" tabindex="13">
											<option value="" selected="selected">Seleccione</option>
										</select>
									</td>
								</tr>
								<tr>
									<td>Correo:</td>
									<td><input type="text" id="txtCorreo" class="requiredEmail" tabindex="5"/></td>
									<td>Telefono:</td>
									<td><input type="text" id="txtTelefono" class="requiredPhone" tabindex="14"/></td>
								</tr>
								<tr>
									<td>Estado Civil</td>
									<td>
										<select id="sltEstadoCivil" class="requiredSelect" tabindex="6">
											<option value="S" selected="selected">Soltero</option>
											<option value="C">Casado</option>
											<option value="T">Conviviente</option>
											<option value="V">Viudo</option>
											<option value="D">Divorciado</option>
										</select>
									</td>
									<td>Cel. Movistar:</td>
									<td><input type="text" id="txtMovistar" class="required" tabindex="15"/></td>
								</tr>
								<tr>
									<td>Sexo</td>
									<td>
										<select id="sltSexo" class="requiredSelect" tabindex="7">
											<option value="" selected="selected">Seleccione</option>
											<option value="M">Masculino</option>
											<option value="F">Femenino</option>
										</select>
									</td>
									<td>Cel. Claro:</td>
									<td><input type="text" id="txtClaro" class="required" tabindex="16"/></td>
								</tr>
								<tr>
									<td>Fecha Nacimiento</td>
									<td><input type="text" id="dtNacimiento" class="requiredDate" tabindex="8"/></td>
									<td>Cel. Nextel:</td>
									<td><input type="text" id="txtNextel" class="required" tabindex="17"/></td>
								</tr>
								<tr>
									<td>Fecha Inicio:</td>
									<td><input type="text" id="txtFechaInicio" class="requiredDate" tabindex="9"/></td>
								</tr>						
						</table>
			        </fieldset>
		        </div>
		        <div id="divTab1TopRight" align="center">
					<fieldset>
						<legend>FOTO DEL CONDUCTOR</legend>
						<img alt="Foto" id="imgFotoConductor" src="images/no_disponible.jpg"/>
						<br/>
						<form action="#" method="POST" enctype="multipart/form-data" accept-charset="utf-8" class="formFotoConductor">
							<input type="file" name="fileFotoConductor" class="fileFotoConductor" tabindex="18"/>
							<input type="submit" value="Enviar" tabindex="19"/>
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
								<td>N�</td>
								<td>Adjuntar</td>
								<td>N� Documento</td>
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
											<input type="file" name="fileDocumento" id="fileDocumento_<c:out value="${documento.mtdcodigoI}"/>" class="fileDocumento"/>
											<input type="submit" value="Enviar"/>
										</td>
									</tr>
								</form:form>
							</c:forEach>	
						</table>
						<div id="progressArchivo"></div>
					</fieldset>
					<center>
						<input type="button" id="btnGuardarCond" class="ui-button" value="Guardar"/>
						<input type="button" id="btnCancelarCond" class="ui-button" value="Cancelar"/>
					</center>
				</div>
	    </div>
	    
		<h4 align="center">
		<img id="btnNuevoConductor" alt="Nuevo" src="images/Add.png"/>
		</h4>
</body>
</html>