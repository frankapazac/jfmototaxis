<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/jquery.horizontalNav.js"></script>
<script type="text/javascript">
	$(document).ready(function(){ 
		$('.full-width').horizontalNav({});
		$("#menuJF").find('li').each(
			function(){
				var current=$(this).find('a');
				if(document.URL.toLowerCase().indexOf(current.attr('href').toLowerCase())!=-1){
					current.css({'background': '#f0f0f0','color': '#608A12','font-size': '1em'})
				}
			}		
		);
	});
</script>
<style type="text/css">
	.horizontal-nav {
	    background: #efefef;
	    border-radius: 6px;
	    /*FRANK APAZA*/
	    /*width: 1158px;*/
	    /*width: 950px;*/
	    width: 908px;
		float: left;
		font-size: 11.5px
	}
	.horizontal-nav ul {
	    background: #608A12;
	    float: left;
	    text-align: center;
	    border-radius: 6px;
	    border: 1px solid #577b15;
	    margin: 0px;
	}
	.horizontal-nav ul li {
	    float: left;
	    border-left: 1px solid #577b15;
	}
	.horizontal-nav ul li:first-child {
	    border-left: 0 none;
	}
	.horizontal-nav ul li a {
	    display: block;
	    padding: 5px 10px;
	    color: #fff;
	    border-top: 1px solid rgba(255,255,255, 0.25);
	    border-left: 1px solid rgba(255,255,255, 0.25);
	    font-size: 1em;// Agregado por FAC
	}
	.horizontal-nav ul li:first-child a {
	    border-left: 0 none;
	}
	.horizontal-nav ul li a:hover {
	    background: #f0f0f0;
	    color: #608A12;
	    font-size: 1em;//agregado por FAC
	}
	.horizontal-nav ul li:first-child a {
	    border-top-left-radius: 6px;
	    border-bottom-left-radius: 6px;
	}
	.horizontal-nav ul li:last-child a {
	    border-top-right-radius: 6px;
	    border-bottom-right-radius: 6px;
	}
	.lnkSalir{
		background-color: red;
	}
	.lnkSalir:HOVER {
		background: #f0f0f0;
	    color: red !important;
	}
</style>
<nav class="horizontal-nav full-width horizontalNav-notprocessed" id="menuJF">
	<ul>
		<c:forEach var="pagina" items="${paginas}">
			<li><a href="<c:out value="${pagina.pagurlV}"/>"><c:out value="${pagina.pagnombreV}"/></a></li>
		</c:forEach>
		<!-- li><a href="#"><img src="images/mail.png" width="11" height="11"/></a-->
		<li><a href="<c:url value="j_spring_security_logout"/>" class="lnkSalir" >SALIR</a></li>
    </ul>
</nav>