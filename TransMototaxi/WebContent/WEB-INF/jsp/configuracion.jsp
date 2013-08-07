<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/ui/jquery.ui.core.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.effect.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.effect-clip.js"></script>
<script type="text/javascript" src="js/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="js/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript" src="js/jefra.message.js"></script >
<script type="text/javascript" src="js/jefra.util.js"></script >
<script type="text/javascript" src="js/jsp/configuracion.js"></script>
<title>Insert title here</title>
<style type="text/css">
	#imgFotoEmpresa_13{
		max-width: 400px;
		max-height: 400px;
	}
	#imgFotoEmpresa_14{
		max-width: 400px;
		max-height: 70px;
	}
	#imgFotoEmpresa_15{
		max-width: 400px;
		max-height: 70px;
	}
	#divDatosEmpresa{
		width:1050px;
	}
	#divDatos , #divFotos{
		width: 500px;
		height:500px;
		padding: 20px 5px;		
	}
	#divFotos{
		float:right;
	}
	#divDatos{
		float:left;
	}
	
	#btnGuardar{
		margin: 10px;
		float: right;
	}
	.flArchivo{
		width: 250px;
	}
</style>
</head>
<body>
	<h1>Configuración</h1>
	<div id="divConfiguracion">
		<h3>Datos de la Empresa</h3>
		<div>
			<div id="divDatosEmpresa">
			  <div id="divDatos">
			  	<fieldset>
			  		<legend>DATOS DE LA EMPRESA</legend>
			  		<input type="hidden" id="txtcodEmp" value="<c:out value="${emprepresentante.empProp.empresa.empcodigoD}"/>"/>
					<table>
			        		<tr> <!-- Fila -->
			                		<td>Representante Legal</td> <!-- columnas -->
			                        <td><input type="text" id="txtreplegal" value="<c:out value="${emprepresentante.empProp.empPropietario.persona.pernombresV}"/> <c:out value="${emprepresentante.empProp.empPropietario.persona.perpaternoV}"/> <c:out value="${emprepresentante.empProp.empPropietario.persona.permaternoV}"/>" disabled="disabled"/>
			                        </td>
			                </tr>
			        		<tr> <!-- Fila -->
			                		<td>Dirección</td> <!-- columnas -->
			                        <td><input type="text" id="txtDireccion" value="<c:out value="${emprepresentante.empProp.empresa.empdireccionV}"/>" disabled="disabled"/></td>
			                </tr>
			        		<tr> <!-- Fila -->
			                		<td>Departamento</td> <!-- columnas -->
			                        <td><input type="text" id="txtdeparmento" value="<c:out value="${emprepresentante.empProp.empresa.departamento.nombubigeo}"/>" disabled="disabled"/></td>
			                </tr>
			        		<tr> <!-- Fila -->
			                		<td>Provincia</td> <!-- columnas -->
			                        <td><input type="text" id="txtprovincia" value="<c:out value="${emprepresentante.empProp.empresa.provincia.nombubigeo}"/>" disabled="disabled"/></td>
			                </tr>
			        		<tr> <!-- Fila -->
			                		<td>Distrito</td> <!-- columnas -->
			                        <td><input type="text" id="txtdistrito" value="<c:out value="${emprepresentante.empProp.empresa.distrito.nombubigeo}"/>" disabled="disabled"/></td>
			                </tr>
			        		<tr> <!-- Fila -->
			                		<td>RUC</td> <!-- columnas -->
			                        <td><input type="text" id="txtruc" value="<c:out value="${emprepresentante.empProp.empresa.emprucV}"/>" disabled="disabled"/></td>
			                </tr>
			        		<tr> <!-- Fila -->
			                		<td>Teléfono 01</td> <!-- columnas -->
			                        <td><input type="text" id="txtTelefono1" value="<c:out value="${emprepresentante.empProp.empresa.emptelefono1V}"/>"/></td>
			                </tr>
			                <tr> <!-- Fila -->
			                		<td>Teléfono 02</td> <!-- columnas -->
			                        <td><input type="text" id="txtTelefono2" value="<c:out value="${emprepresentante.empProp.empresa.emptelefono2V}"/>"/></td>
			                </tr>
			        		<tr> <!-- Fila -->
			                		<td>Celular movistar</td> <!-- columnas -->
			                        <td><input type="text" id="txtCelMov" value="<c:out value="${emprepresentante.empProp.empresa.empcelularmovV}"/>"/></td>
			                </tr>
			        		<tr> <!-- Fila -->
			                		<td>Celular Claro</td> <!-- columnas -->
			                        <td><input type="text" id="txtCelClaro" value="<c:out value="${emprepresentante.empProp.empresa.empcelularclaV}"/>"/></td>
			                </tr>
			        		<tr> <!-- Fila -->
			                		<td>Celular Nextel</td> <!-- columnas -->
			                        <td><input type="text" id="txtCelNex" value="<c:out value="${emprepresentante.empProp.empresa.empcelularnexV}"/>"/> </td>
			                </tr>
			        		<tr> <!-- Fila -->
			                		<td>Correo</td> <!-- columnas -->
			                        <td><input type="text" id="txtCorreo" value="<c:out value="${emprepresentante.empProp.empresa.empmailV}"/>"/></td>
			                </tr>
			        		<tr> <!-- Fila -->
			                		<td>Página Web</td> <!-- columnas -->
			                        <td><input type="text" id="txtPag" value="<c:out value="${emprepresentante.empProp.empresa.emppagwebV}"/>"/></td>
			                </tr>
	        		</table>
			  	</fieldset>	
			  </div>
			  
			  <div id="divFotos">
			  	<fieldset>
			  		<legend>FOTOS DE LA EMPRESA</legend>
			  		<c:forEach var="imagen" items="${imagenes}" varStatus="status">
				  		<img alt="Foto" id="imgFotoEmpresa_<c:out value="${imagen.tipoDocumento.mtdcodigoI}" />" src="<c:out value="${imagen.adjuntarArchivo.adjnombreV}"/>"/>
					  	<table>
					  		<form:form action="#" method="POST" enctype="multipart/form-data" acceptCharset="utf-8" cssClass="formFotosEmpresa">
					  			<tr>
					  				<td><c:out value="${status.count}"/>
					  					<input type="hidden" name="txtFotoCodigo" value="${imagen.tipoDocumento.mtdcodigoI}"/>
					  				</td>
					  				<td><c:out value="${imagen.tipoDocumento.mtdnombreV}"/></td>
					  				<td>
					  					<input type="file" name="flFoto" class="flArchivo"/>
					  					<input type="submit" value="Enviar"/>
					  				</td>
					  			</tr>
				  			</form:form>
				  		</table>	
				  	</c:forEach>
			  	</fieldset>
			  </div>
			  <center>
			  	<input type="button" id="btnGuardar" value="Guardar" />
			  </center>
			</div>
		</div>
		<h3>Cambio de clave</h3>
		
		<div>
			<table>
					<tr>
							<td>Usuario</td>
							<td><label id="lblnomUsuario"><c:out value="${USUARIO}"/></label></td>
					</tr>
					<tr>
							<td>Clave Anterior</td>
							<td><input type="password" id="txtpassAnterior"></td>
					</tr>
					<tr>
							<td>Clave Nueva</td>
							<td><input type="password" id="txtpassNuevo"></td>
					</tr>
					<tr>
							<td>Confirme Clave Nueva</td>
							<td><input type="password" id="txtpassConfirma"></td>
					</tr>
			</table>
			 <input type="button" id="btnActualizarClave" value="Guardar" />
		</div>
	</div>
</body>
</html>