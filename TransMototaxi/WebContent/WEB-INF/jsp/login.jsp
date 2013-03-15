<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ingresar al Sistema</title>
</head>
<body>
	<div align="center">
		<h1>Ingreso al Sistema</h1>
		<form action="<c:url value='j_spring_security_check'/>" name="f" method="post">
			<table>
				<tr>
					<td>USUARIO</td>
					<td><input type="text" name="j_username"/></td>
				</tr>
				<tr>
					<td>CLAVE</td>
					<td><input type="password" name="j_password"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
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
</body>
</html>