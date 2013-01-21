<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="css/ui/smoothness/jquery-ui-1.9.2.custom.css" rel="stylesheet" type="text/css">
	<link href="css/tablesorter/green/style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="js/tablesorter/jquery-latest.js"></script>
<title><tiles:insertAttribute name="title" ignore="true"/></title>
<style type="text/css">
	h2{
		font-size: 1.5em;
		font-weight: bold;
		margin: 5px 0px 5px 0px;
	}
	a{
		font-size: 18px;
		text-decoration: none;
	}
	#divPrincipal{
		border: 1px solid #000000;
		width:1200px;
	}
	#divHeader,#divContent,#divFooter {
		border: 1px solid #000000;
		width:100%;
	}
	#divHeader{
		height: 100px;
	}
	#divContent{
		min-height:500px;
		height: auto;
	}
	#divFooter{
		height: 60px;
	}
	#divBody{
		width:100%;
		height:100%;
	}	
</style>
</head>
<body>
	<div align="center">
		<div id="divPrincipal">
		  	<div id="divHeader" align="center">
				<tiles:insertAttribute name="header"/>
			</div>
		  
		  	<div id="divMenu">
		  		<tiles:insertAttribute name="menu"/>
			</div>
		  
		  	<div id="divContent">
		    	<div id="divBody">
					<tiles:insertAttribute name="body"/>
				</div>
		  	</div>
		  
		  	<div id="divFooter" align="center">
				<tiles:insertAttribute name="footer"/>
			</div>
		</div>
	</div>
</body>
</html>