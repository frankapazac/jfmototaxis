<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
	#imgBanner{
		width: 700px; 
		height: 70px;
		/*width: 950px; 
		height: 90px;*/
		padding: 0px;
	}
	#imgLogo{
		width: 60px; 
		height: 60px;
		/*width: 90px; 
		height: 90px;*/
		padding: 5px;
	}
	#imgLogoChosica{
		/*width: 60px; 
		height: 90px;*/
		width: 40px; 
		height: 60px;
		padding: 5px;
	}
</style>
<!--  div style="width: 1200px; height: 95px" align="center">-->
<div style="width: 950px; height: 70px" align="center">
	<c:set var="user" value="${USUARIO}"></c:set>
	<!--div style="float:left; width: 120px;" align="center"-->
	<div style="float:left; width: 100px;" align="center">
		<img id="imgLogoChosica" src="images/LogoChosica.png"/>
	</div>
	<!--div style=" float:left ;width: 950px; height: 30px" align="center"-->
	<div style=" float:left ;width: 740px; height: 30px" align="center">
		<img id="imgBanner" src="<c:out value="${usuario.empresa.banner}"/>"/>
	</div>
	<!--div style="float:left; width: 120px;" align="center"-->
	<div style="float:left; width: 100px;" align="center">
		<img id="imgLogo" src="<c:out value="${usuario.empresa.logo}"/>"/>
	</div>
</div>