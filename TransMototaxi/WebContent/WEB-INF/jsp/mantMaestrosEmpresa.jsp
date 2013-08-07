<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script type="text/javascript" src="js/tablesorter/jquery.tablesorter.js"></script>
	<script type="text/javascript" src="js/tablesorter/jquery.tablesorter.pager.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.core.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.position.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.menu.js"></script>
	<script type="text/javascript" src="js/jefra.util.js"></script >
	<script type="text/javascript" src="js/jsp/mantMaestrosEmpresa.js"></script>
	<title>Insert title here</title>
	<style type="text/css">
		#divMaestros{
			width: 950px;
		}
		#divMaestros #divMaestrosMenu{
			float: left;
			width: 230px;
		}
		#divMaestros #divMaestrosContenido{
			float: right;
			width: 700px;
		}
		.ui-menu { width: 200px; }
	</style>
</head>
<body>
	<h1>Maestros</h1>
	<div id="divMaestros" align="center">
		<div id="divMaestrosMenu">
			<ul id="menu">
				<li><a href="Infraccion.htm">Infracciones</a></li>
				<li><a href="#">Tabla 1</a></li>
				<li><a href="#">Tabla 2</a></li>
				<li><a href="#">Tabla 3</a></li>
			</ul>
		</div>
		<div id="divMaestrosContenido"></div>
	</div>
</body>
</html>