<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	#divDatosEmpresa{
		width:850px;
		height: auto;
		overflow: auto;
	}
	#divDatos {
		width: 420px;
		height:auto;
		padding: 5px 5px;
		float:left;		
	} 
	#divFoto{
		width: 420px;
		/*height:330px;*/
		padding: 30px 0px;
		float:right;
	}
	.tdEmpTitulo{
		background-color: #608A12;
		color: white;
		padding: 5px 10px;
	}
	#imgFotoEmpresa{
		width: 400px;
	}
</style>
</head>
<body>
	<c:set var="user" value="${USUARIO}"></c:set>
	<h1>Bienvenido <c:out value="${usuario.usuusuarioV}"/></h1>
	
	<c:if test="${not empty emprepresentante.empresa.empcodigoD}">
		<!-- ACTIVO SOLO PARA LA EMPRESA -->
		<div id="divDatosEmpresa">
			<div id="divDatos">
			  	<fieldset>
			  		<legend>DATOS DE LA EMPRESA</legend>
			  		<input type="hidden" id="txtcodEmp" value='<c:out value="${emprepresentante.empresa.empcodigoD}"/>'>
					<table>
			        		<tr> <!-- Fila -->
			                		<td class="tdEmpTitulo">Representante Legal</td> <!-- columnas -->
			                        <td><c:out value="${emprepresentante.empProp.persona.pernombresV}"/> <c:out value="${emprepresentante.empProp.persona.perpaternoV}"/> <c:out value="${emprepresentante.empProp.persona.permaternoV}"/>
			                        </td>
			                </tr>
			        		<tr> <!-- Fila -->
			                		<td class="tdEmpTitulo">Dirección</td> <!-- columnas -->
			                        <td><c:out value="${emprepresentante.empresa.empdireccionV}"/></td>
			                </tr>
			        		<tr> <!-- Fila -->
			                		<td class="tdEmpTitulo">Departamento</td> <!-- columnas -->
			                        <td><c:out value="${emprepresentante.empresa.departamento.nombubigeo}"/></td>
			                </tr>
			        		<tr> <!-- Fila -->
			                		<td class="tdEmpTitulo">Provincia</td> <!-- columnas -->
			                        <td><c:out value="${emprepresentante.empresa.provincia.nombubigeo}"/></td>
			                </tr>
			        		<tr> <!-- Fila -->
			                		<td class="tdEmpTitulo">Distrito</td> <!-- columnas -->
			                        <td><c:out value="${emprepresentante.empresa.distrito.nombubigeo}"/></td>
			                </tr>
			        		<tr> <!-- Fila -->
			                		<td class="tdEmpTitulo">RUC</td> <!-- columnas -->
			                        <td><c:out value="${emprepresentante.empresa.emprucV}"/></td>
			                </tr>
			        		<tr> <!-- Fila -->
			                		<td class="tdEmpTitulo">Teléfono 01</td> <!-- columnas -->
			                        <td><c:out value="${emprepresentante.empresa.emptelefono1V}"/></td>
			                </tr>
			                <tr> <!-- Fila -->
			                		<td class="tdEmpTitulo">Teléfono 02</td> <!-- columnas -->
			                        <td><c:out value="${emprepresentante.empresa.emptelefono2V}"/></td>
			                </tr>
			        		<tr> <!-- Fila -->
			                		<td class="tdEmpTitulo">Celular movistar</td> <!-- columnas -->
			                        <td><c:out value="${emprepresentante.empresa.empcelularmovV}"/></td>
			                </tr>
			        		<tr> <!-- Fila -->
			                		<td class="tdEmpTitulo">Celular Claro</td> <!-- columnas -->
			                        <td><c:out value="${emprepresentante.empresa.empcelularclaV}"/></td>
			                </tr>
			        		<tr> <!-- Fila -->
			                		<td class="tdEmpTitulo">Celular Nextel</td> <!-- columnas -->
			                        <td><c:out value="${emprepresentante.empresa.empcelularnexV}"/></td>
			                </tr>
			        		<tr> <!-- Fila -->
			                		<td class="tdEmpTitulo">Correo</td> <!-- columnas -->
			                        <td><c:out value="${emprepresentante.empresa.empmailV}"/></td>
			                </tr>
			        		<tr> <!-- Fila -->
			                		<td class="tdEmpTitulo">Página Web</td> <!-- columnas -->
			                        <td><a href='<c:out value="${emprepresentante.empresa.emppagwebV}"/>'><c:out value="${emprepresentante.empresa.emppagwebV}"/></a></td>
			                </tr>
	        		</table>
			  	</fieldset>	
			  </div>
				  
			  <div id="divFoto">
			  		<img alt="Foto" id='imgFotoEmpresa' src='<c:out value="${imagen.adjuntarArchivo.adjnombreV}"/>'>
			  		<br/>
				  	<c:out value="${imagen.tipoDocumento.mtdnombreV}" />
			  </div>
		</div>
	</c:if>
</body>
</html>