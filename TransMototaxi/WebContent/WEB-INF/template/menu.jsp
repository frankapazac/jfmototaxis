<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/jquery.horizontalNav.js"></script>
<script type="text/javascript">
	$(document).ready(function(){ 
		$('.full-width').horizontalNav({});
	});
</script>
<style type="text/css">
	.horizontal-nav {
	    background: #efefef;
	    border-radius: 6px;
	}
	.horizontal-nav ul {
	    background: #128F9A;
	    float: left;
	    text-align: center;
	    border-radius: 6px;
	    border: 1px solid #0e7079;
	    margin: 0px;
	}
	.horizontal-nav ul li {
	    float: left;
	    border-left: 1px solid #0e7079;
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
	}
	.horizontal-nav ul li:first-child a {
	    border-left: 0 none;
	}
	.horizontal-nav ul li a:hover {
	    background: #12808a;
	}
	.horizontal-nav ul li:first-child a {
	    border-top-left-radius: 6px;
	    border-bottom-left-radius: 6px;
	}
	.horizontal-nav ul li:last-child a {
	    border-top-right-radius: 6px;
	    border-bottom-right-radius: 6px;
	}
</style>
<nav class="horizontal-nav full-width horizontalNav-notprocessed">
	<ul>
        <li><a href="Inicio.htm">INICIO</a></li>
        <li><a href="Asociados.htm">ASOCIADOS</a></li>
        <li><a href="Conductores.htm">CONDUCTORES</a></li>
        <li><a href="Mototaxis.htm">MOTOTAXIS</a></li>
        <li><a href="Paraderos.htm">PARADEROS</a></li>
	    <li><a href="Documentacion.htm">DOCUMENTACION</a></li>
	    <li><a href="Configuracion.htm">CONFIGURACION</a></li>
	    <li><a href="#"></a></li>
	    <li><a href="#"></a></li>
    </ul>
</nav>