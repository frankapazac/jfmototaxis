<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<title>Ingresar al Sistema</title>
<script type="text/javascript">
	$(document).ready(function(){
		$("#txtUsuario").focus(function(){
			if($(this).val()=="Usuario")$(this).val("");
		});
		$("#txtClave").focus(function(){
			if($(this).val()=="********")$(this).val("");
		});
		$("#txtUsuario").hover(function(){
			if($(this).val()=="Usuario")$(this).val("");
		},function(){
			if($(this).val()=="")$(this).val("Usuario");
		});
		$("#txtClave").hover(function(){
			if($(this).val()=="********")$(this).val("");
		},function(){
			if($(this).val()=="")$(this).val("********");
		});
	});
</script>
<style type="text/css">
	body{
		font-family :Arial, sans-serif;
		background-color :#f0f0f0;
	}
	#divPrincipal{
		width: 1000px;
		text-align: center;
	}
	#divIzquierdo{
		width: 59%;
		float: left;
	}
	#divDerecho{
		padding: 120px 0px 0px 0px;
		width: 40%;
		float: left;
	}
	input[type=text],input[type=password]{
		width: 300px;
		height: 20px;
		font-size: 15px;
	}
	input[type=submit],input[type=reset]{
		background-color:#577b15;
		color: white;
		width: 150px;
		height: 35px;
		font-size: 15px;
	}
	h1{
		color:#577b15;
		margin: 10px;
	}
</style>
</head>
<body>
	<div align="center" style="padding: 60px;">
		<div id="divPrincipal">
			<div id="divIzquierdo">
				<img src="images/PANEL2.png">
			</div>
			<div id="divDerecho">
				<div align="center">
					<h1>Ingreso al Sistema</h1>
					<form action="<c:url value='j_spring_security_check'/>" name="f" method="post">
						<table>
							<tr>
								<!-- td>USUARIO</td> -->
								<td><input type="text" name="j_username" id="txtUsuario" value="Usuario"/></td>
							</tr>
							<tr>
								<!-- td>CLAVE</td> -->
								<td><input type="password" name="j_password" id="txtClave" value="********"/></td>
							</tr>
							<tr>
								<td>
									<input type="submit" value="Ingresar"/>
									<input type="reset" value="Limpiar"/>
								</td>
							</tr>
						</table>
					</form>
					
					<c:if test="${not empty error}">
						<div class="divError">
							<!-- c:out value="${error}"/ -->
							Login error: Por favor intente de nuevo<br/>
							${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</body>
</html>