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
	<script type="text/javascript" src="js/jefra.message.js"></script >
	<script type="text/javascript" src="js/jefra.util.js"></script >
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
			 <input type="button" id="btnActualizarClave" value="Guardar" class="ui-button"/>
		</div>
	</div>
</body>
</html>