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
<script type="text/javascript" src="js/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="js/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript" src="js/jsp/configuracion.js"></script>
<title>Insert title here</title>
<style type="text/css">
	#divDatosEmpresa{
		width:1050px;
	}
	#divDatos , #divFotos{
		border: solid 1px black;
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
			  <input type="hidden" id="txtcodEmp" value="<c:out value="${emprepresentante.empProp.empresa.empcodigoD}"/>"/>
				<table>
		        		<tr> <!-- Fila -->
		                		<td>Representante Legal</td> <!-- columnas -->
		                        <td><label id="txtreplegal"><c:out value="${emprepresentante.empProp.empPropietario.persona.pernombresV}"/>&nbsp;
		                        							<c:out value="${emprepresentante.empProp.empPropietario.persona.perpaternoV}"/>&nbsp;
		                        							<c:out value="${emprepresentante.empProp.empPropietario.persona.permaternoV}"/>&nbsp;
		                         </label></td>
		                </tr>
		        		<tr> <!-- Fila -->
		                		<td>Dirección</td> <!-- columnas -->
		                        <td><label id="txtDireccion"><c:out value="${emprepresentante.empProp.empresa.empdireccionV}"/></label></td>
		                </tr>
		        		<tr> <!-- Fila -->
		                		<td>Departamento</td> <!-- columnas -->
		                        <td><label id="txtdeparmento"><c:out value="${emprepresentante.empProp.empresa.departamento.nombubigeo}"/></label></td>
		                </tr>
		        		<tr> <!-- Fila -->
		                		<td>Provincia</td> <!-- columnas -->
		                        <td><label id="txtprovincia"><c:out value="${emprepresentante.empProp.empresa.provincia.nombubigeo}"/></label></td>
		                </tr>
		        		<tr> <!-- Fila -->
		                		<td>Distrito</td> <!-- columnas -->
		                        <td><label id=""></label><c:out value="${emprepresentante.empProp.empresa.distrito.nombubigeo}"/></td>
		                </tr>
		        		<tr> <!-- Fila -->
		                		<td>RUC</td> <!-- columnas -->
		                        <td><label id="txtruc"><c:out value="${emprepresentante.empProp.empresa.emptelefono2V}"/></label></td>
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
					
			  </div>
			  
			  <div id="divFotos">
			  	<c:forEach var="foto" items="${fotos}" varStatus="status">
				  	<table>
				  		<form:form action="#" method="POST" enctype="multipart/form-data" acceptCharset="utf-8" cssClass="formFotosEmpresa">
				  			<tr>
				  				<td><c:out value="${status.count}"/>
				  					<input type="hidden" name="txtFotoCodigo" value="${foto.mtdcodigoI}"/>
				  				</td>
				  				<td><c:out value="${foto.mtdnombreV}"/></td>
				  				<td>
				  					<input type="file" name="flFoto" class="flArchivo"/>
				  					<input type="submit" value="Enviar"/>
				  				</td>
				  			</tr>
			  			</form:form>
			  		</table>	
			  	</c:forEach>
			  </div>
			  <input type="button" id="btnGuardar" value="Guardar" />
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