<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script type="text/javascript" src="js/tablesorter/jquery.tablesorter.js"></script>
	<script type="text/javascript" src="js/tablesorter/jquery.tablesorter.pager.js"></script>
	<script type="text/javascript" src="js/jquery.form.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.core.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.effect.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.effect-clip.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.button.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.tabs.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.position.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.dialog.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.datepicker.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.progressbar.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.menu.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.autocomplete.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.progressbar.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.tooltip.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.mouse.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.slider.js"></script>
	<script type="text/javascript" src="js/ui/jquery-ui-timepicker-addon.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.tooltip.js"></script>
	<script type="text/javascript" src="js/jefra.validate.js"></script >
	<script type="text/javascript" src="js/jefra.message.js"></script >
	<script type="text/javascript" src="js/jefra.util.js"></script >
	<script type="text/javascript" src="js/jsp/papeleta.js"></script>
	<title>Insert title here</title>
	<style type="text/css">
		.tdTitulo{
			width: 140px;
			background-color: #608A12;
			color: white;
			padding: 0px 5px;
		}
		.tdContenido{
			width: 250px;
		}
		.fileFoto{
			width: 245px;
		}
		.txtText{
			width: 200px;
		}
		.txtTextCompleted{
			width: 594px;
		}
		.ui-combobox {
			position: relative;
			display: inline-block;
		}
		.ui-combobox-toggle {
			position: absolute;
			top: 0;
			bottom: 0;
			margin-left: -1px;
			padding: 0;
			/* adjust styles for IE 6/7 */
			*height: 1.7em;
			*top: 0.1em;
		}
		.ui-combobox-input {
			margin: 0;
			padding: 0.3em;
			width: 190px;
		}
		.ver{
			background-color: #f0f0f0;
		} 
		a{
			font-size: 12px;
		}
		.tblTablaEstilo{
			width: 100%;
		}
	</style>
	<script>
	(function( $ ) {
		$.widget( "ui.combobox", {
			_create: function() {
				var input,
					that = this,
					select = this.element.hide(),
					selected = select.children( ":selected" ),
					value = selected.val() ? selected.text() : "",
					wrapper = this.wrapper = $( "<span>" )
						.addClass( "ui-combobox" )
						.insertAfter( select );

				function removeIfInvalid(element) {
					var value = $( element ).val(),
						matcher = new RegExp( "^" + $.ui.autocomplete.escapeRegex( value ) + "$", "i" ),
						valid = false;
					select.children( "option" ).each(function() {
						if ( $( this ).text().match( matcher ) ) {
							this.selected = valid = true;
							return false;
						}
					});
					if ( !valid ) {
						// remove invalid value, as it didn't match anything
						$( element )
							.val( "" )
							.attr( "title", value + " no se escogio ningun item" )
							.tooltip( "open" );
						select.val( "" );
						setTimeout(function() {
							input.tooltip( "close" ).attr( "title", "" );
						}, 2500 );
						input.data( "autocomplete" ).term = "";
						return false;
					}
				}

				input = this.input = $( "<input>" )
					.appendTo( wrapper )
					.val( value )
					.attr( "title", "" )
					.addClass( "ui-state-default ui-combobox-input" )
					.autocomplete({
						delay: 0,
						minLength: 0,
						source: function( request, response ) {
							var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
							response( select.children( "option" ).map(function() {
								var text = $( this ).text();
								if ( this.value && ( !request.term || matcher.test(text) ) )
									return {
										label: text.replace(
											new RegExp(
												"(?![^&;]+;)(?!<[^<>]*)(" +
												$.ui.autocomplete.escapeRegex(request.term) +
												")(?![^<>]*>)(?![^&;]+;)", "gi"
											), "<strong>$1</strong>" ),
										value: text,
										option: this
									};
							}) );
						},
						/*select: function( event, ui ) {
							ui.item.option.selected = true;
							that._trigger( "selected", event, {
								item: ui.item.option
							});
						},*/
						select: function( event, ui ) {
						    ui.item.option.selected = true;
						    that._trigger( "selected", event, {
						        item: ui.item.option
						    });
						    select.trigger("change"); 
						},
						change: function( event, ui ) {
							if ( !ui.item )
								return removeIfInvalid( this );
						}
					})
					.addClass( "ui-widget ui-widget-content ui-corner-left" );

				input.data( "autocomplete" )._renderItem = function( ul, item ) {
					return $( "<li>" )
						.data( "item.autocomplete", item )
						.append( "<a>" + item.label + "</a>" )
						.appendTo( ul );
				};

				$( "<a>" )
					.attr( "tabIndex", -1 )
					.attr( "title", "Mostrar todos" )
					.tooltip()
					.appendTo( wrapper )
					.button({
						icons: {
							primary: "ui-icon-triangle-1-s"
						},
						text: false
					})
					.removeClass( "ui-corner-all" )
					.addClass( "ui-corner-right ui-combobox-toggle" )
					.click(function() {
						// close if already visible
						if ( input.autocomplete( "widget" ).is( ":visible" ) ) {
							input.autocomplete( "close" );
							removeIfInvalid( input );
							return;
						}

						// work around a bug (likely same cause as #5265)
						$( this ).blur();

						// pass empty string as value to search for, displaying all results
						input.autocomplete( "search", "" );
						input.focus();
					});

					input
						.tooltip({
							position: {
								of: this.button
							},
							tooltipClass: "ui-state-highlight"
						});
			},

			destroy: function() {
				this.wrapper.remove();
				this.element.show();
				$.Widget.prototype.destroy.call( this );
			},
			
			autocomplete : function(key,value) {
				this.element.val(key);
			   	this.input.val(value);
			},
			
			getValue:function(){
				return this.element.val();
			}
		});
	})( jQuery );

	$(function() {
		$( "#sltConductor" ).combobox();
		$( "#sltPlacas" ).combobox();
		$( "#sltInspector" ).combobox();
		$( "#sltInfraccion" ).combobox();
		$( "#sltSancion" ).combobox();
		$( "#sltPolicia" ).combobox();
	});
	</script>
</head>
<body>
	<h1>Papeletas</h1>
	<div id="divBuscar">
		<table>
			<tr>
				<td>Criterio</td>
				<td>Texto</td>
				<td></td>
			</tr>
			<tr>
				<td>
					<select id="sltCriterio">
						<option value="PMO.PLACANRO">NRO. PLACA</option>
						<option value="PMO.PERDNI_V">PROPIETARIO DNI</option>
						<option value="PMO.CONDNOMBCOMP">PROPIETARIO NOMBRES</option>
						<option value="CON.PERDNI_V">CONDUCTOR DNI</option>
						<option value="CON.CONDNOMBCOMP">CONDUCTOR NOMBRES</option>
						<option value="CON.LICENCIANRO">CONDUCTOR NRO. LICENCIA</option>
					</select>
				</td>
				<td><input type="text" id="txtTexto" /></td>
				<td><input type="button" id="btnBuscar" value="Buscar" class="ui-button"/></td>
			</tr>
		</table>
	</div>
	
	<div id="divLista">
		<table align="center" class="tablesorter" id="tblLista">		
		</table>
	</div>
	
	
	
	<div id="pager" class="pager">
		<form>
			<img src="js/tablesorter/addons/pager/icons/first.png" class="first">
			<img src="js/tablesorter/addons/pager/icons/prev.png" class="prev">
			<input type="text" class="pagedisplay">
			<img src="js/tablesorter/addons/pager/icons/next.png" class="next">
			<select class="pagesize">
				<option selected="selected" value="10">10</option>
				<option value="20">20</option>
				<option value="30">30</option>
				<option value="40">40</option>
			</select>
			<img src="js/tablesorter/addons/pager/icons/last.png" class="last">
		</form>
	</div>
	
	<h4 align="center">
		<img id="btnNuevo" alt="Nuevo" src="images/Add.png"/>
	</h4>
	
	<div id="divFormulario">
		<fieldset>
			<legend>PAPELETA</legend>
			<table>
				<tr>
					<td class="tdTitulo" width="140px">Número</td>
					<td class="tdContenido" width="250px"><input type="text" id="txtNumeroPap" class="txtText" disabled="disabled"/>
					<input type="hidden" id="txtNumeroPapeleta" class="txtText" disabled="disabled"/></td>
					<td class="tdTitulo" width="140px">Fecha</td>
					<td class="tdContenido" width="250px"><input type="text" id="txtFecha" class="required"/></td>
				</tr>
			</table>
		</fieldset>
			
		<fieldset>
			<legend>DATOS DEL CONDUCTOR</legend>
			<table>
				<tr>
					<td class="tdTitulo" width="140px">DNI N°</td>
					<td class="tdContenido" width="250px"><input type="text" id="txtConductorDNI" class="txtText requiredNumber"/></td>
					<td class="tdTitulo" width="140px">Conductor</td>
					<td class="tdContenido" width="250px">
						<img src="images/add_user.png" id="agregarConductor"/>
						<select id="sltConductor" class="requiredSelect">
							<option value="">-Seleccione-</option>
							<c:forEach var="conductor" items="${conductores}">
								<option value="${conductor.concodigoD}"><c:out value="${conductor.persona.perpaternoV}"/> <c:out value="${conductor.persona.permaternoV}"/>, <c:out value="${conductor.persona.pernombresV}"/></option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="tdTitulo">N° Licencia</td>
					<td class="tdContenido"><input type="text" id="txtConductorNroLicencia" class="txtText" disabled="disabled"/></td>
					<td class="tdTitulo">Estado</td>
					<td class="tdContenido">
						<label id="txtEstadoLicencia">SIN CONFIRMAR</label> 
						<img src="images/interrogacion.png" id="imgMensajeConductor"/>[<label id="txtMensajeConductor">0</label>] mensajes
					</td>
				</tr>
				<tr>
					<td class="tdTitulo">Fecha de Emision</td>
					<td class="tdContenido"><input type="text" id="txtLicenciaEmision" class="txtText" disabled="disabled"/></td>
					<td class="tdTitulo">Fecha de Caducidad</td>
					<td class="tdContenido"><input type="text" id="txtLicenciaCaducidad" class="txtText" disabled="disabled"/></td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>DATOS DEL VEHICULO</legend>
			<table>
				<tr>
					<td class="tdTitulo" width="140px">N° Placa</td>
					<td class="tdContenido" width="250px">
						<img src="images/edit_add.png" id="agregarMoto"/>
						<select id="sltPlacas" class="required">
							<option value="0">-SIN PLACA-</option>
							<c:forEach var="placa" items="${placas}">
								<option value="${placa.unecodigoD}"><c:out value="${placa.uneplacanroV}"/></option>
							</c:forEach>
						</select>
					</td>
					<td class="tdTitulo" width="140px">Año de Fabricación</td>
					<td class="tdContenido" width="250px"><input id="txtAnno" type="text" class="txtText" disabled="disabled"/></td>
				</tr>
				<tr>
					<td class="tdTitulo">Marca</td>
					<td class="tdContenido"><input id="txtMarca" type="text" class="txtText" disabled="disabled"/></td>
					<td class="tdTitulo">Modelo</td>
					<td class="tdContenido"><input id="txtModelo" type="text" class="txtText" disabled="disabled" /></td>
				</tr>
				<tr>
					<td class="tdTitulo">Colores</td>
					<td colspan="3"><input id="txtColor" type="text" class="txtTextCompleted" disabled="disabled" /></td>
				</tr>
				<tr>
					<td class="tdTitulo">N° Tarjeta de Propiedad</td>
					<td class="tdContenido"><input id="txtTarjPropiedad" type="text" class="txtText" disabled="disabled" /></td>
					<td class="tdTitulo">Estado</td>
					<td class="tdContenido">
						<label id="txtEstadoUnidad">SIN CONFIRMAR</label> 
						<img src="images/interrogacion.png" id="imgMensajeUnidad"/>[<label id="txtMensajeUnidad">0</label>] mensajes
					</td>
				</tr>
				<tr>
					<td class="tdTitulo">Fecha de Emision</td>
					<td class="tdContenido"><input type="text" id="txtUnidadEmision" class="txtText" disabled="disabled" /></td>
					<td class="tdTitulo">Fecha de Caducidad</td>
					<td class="tdContenido"><input type="text" id="txtUnidadCaducidad" class="txtText" disabled="disabled" /></td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>DATOS DEL PROPIETARIO</legend>
			<table>
				<tr>
					<td class="tdTitulo">Razón Social</td>
					<td colspan="3"><input id="txtPropRazonSocial" type="text" class="txtText" disabled="disabled" /></td>
				</tr>
				<tr>
					<td class="tdTitulo" width="140px">Propietario</td>
					<td class="tdContenido" width="250px"><input id="txtPropietario" type="text" class="txtText" disabled="disabled" /></td>
					<td class="tdTitulo" width="140px">DNI N°</td>
					<td class="tdContenido" width="250px"><input id="txtPropDni" type="text" class="txtText" disabled="disabled" /></td>
					
				</tr>
				<tr>
					<td class="tdTitulo">Domicilio</td>
					<td colspan="3"><input id="txtPropDomicilio" type="text" class="txtTextCompleted" disabled="disabled" /></td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>SOBRE LA INFRACCIÓN</legend>
			<table>
				<tr>
					<td class="tdTitulo">DNI N°</td>
					<td class="tdContenido"><input id="txtDniInspector" type="text" class="txtText required" /></td>
					<td class="tdTitulo">Inspector</td>
					<td class="tdContenido">
						<select id="sltInspector" class="required">
							<option value="">-Seleccione-</option>
							<c:forEach var="inspector" items="${inspectores}">
								<option value="${inspector.inscodigoI}"><c:out value="${inspector.persona.perpaternoV}"/> <c:out value="${inspector.persona.permaternoV}"/>, <c:out value="${inspector.persona.pernombresV}"/></option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="tdTitulo">Lugar de Infracción</td>
					<td colspan="3"><input id="txtInfraccionLugar" type="text" class="txtTextCompleted required" /></td>
				</tr>
				<tr>
					<td class="tdTitulo">Referencia</td>
					<td colspan="3"><input id="txtInfraccionReferencia" type="text" class="txtTextCompleted required" /></td>
				</tr>
				<tr>
					<td class="tdTitulo">Observacion del Inspector</td>
					<td colspan="3"><textarea id="txaObserInspector" rows="3" cols="70" class="required"></textarea></td>
				</tr>
				<tr>
					<td class="tdTitulo">Carnet Ident N°</td>
					<td class="tdContenido"><input id="txtCarnetPolicia" type="text" class="txtText required" /></td>
					<td class="tdTitulo">Policia</td>
					<td class="tdContenido">
						<img src="images/add_user.png" id="agregarPolicia"/>
						<select id="sltPolicia" class="required">
							<option value="">-Seleccione-</option>
							<c:forEach var="policia" items="${policias}">
								<option value="${policia.polcodigoI}"><c:out value="${policia.polpaternoV}"/> <c:out value="${policia.polmaternoV}"/>, <c:out value="${policia.polnombresV}"/></option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>				
					<td class="tdTitulo">Es propietario - conductor</td>
					<td class="tdContenido">
						<label><input type="radio" name="rdPropietario" value="S" id="rdPropietarioSi"/> Si </label>
						<label><input type="radio" name="rdPropietario" value="N" id="rdPropietarioNo"/> No </label>
					</td>
					<td class="tdTitulo">Adjuntar Imagen</td>
					<td class="tdContenido">
						<form action="#" method="POST" enctype="multipart/form-data" accept-charset="utf-8" id="formFoto">
							<input type="file" name="fileFoto" class="fileFoto"/>
							<input type="submit" value="Enviar"/>
						</form>
						<div id="progressFoto"></div>
					</td>
				</tr>
				<tr>
					<td class="tdTitulo">Codigo de Infracción</td>
					<td class="tdContenido">
						<select id="sltInfraccion" class="requiredSelect">
							<option value="" selected="selected">-Seleccione-</option>
							<c:forEach var="infraccion" items="${infracciones}">
								<option value="${infraccion.infcodigoD}"><c:out value="${infraccion.infcodigoV}"/></option>
							</c:forEach>
						</select>
					</td>
					<td class="tdTitulo">Medidas/Sacion Complementaria</td>
					<td class="tdContenido">
						<select id="sltSancion" class="requiredSelect">
							<option value="" selected="selected">-Seleccione-</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="tdTitulo">Descripción</td>
					<td colspan="3"><textarea id="txaDescripcion" rows="3" cols="70" disabled="disabled"></textarea></td>
				</tr>
				<tr>
					<td class="tdTitulo">Observacion sobre la Infracción</td>
					<td colspan="3"><textarea id="txaObserInfraccion" rows="3" cols="70" class="required"></textarea></td>
				</tr>
			</table>
		</fieldset>
		<h4 align="center">
			<input type="button" value="Guardar" id="btnGuardar" class="ui-button"/>
			<input type="button" value="Cancelar" id="btnCancelar" class="ui-button"/>
		</h4>
	</div>
	
	<div id="divFormularioVer">
		<fieldset>
			<legend>PAPELETA</legend>
			<table>
				<tr class="ver">
					<td class="tdTitulo">Número</td>
					<td class="tdContenido"><label id="verNumeroPapeleta" class="txtText"></label></td>
					<td class="tdTitulo">Fecha</td>
					<td class="tdContenido"><label id="verFecha"></label></td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>DATOS DEL CONDUCTOR</legend>
			<table>
				<tr class="ver">
					<td class="tdTitulo">DNI N°</td>
					<td class="tdContenido"><label id="verConductorDNI" class="txtText"></label></td>
					<td class="tdTitulo">Conductor</td>
					<td class="tdContenido"><label id="verConductor"></label></td>
				</tr>
				<tr>
					<td class="tdTitulo">N° Licencia</td>
					<td class="tdContenido"><label id="verConductorNroLicencia" class="txtText"></label></td>
					<td class="tdTitulo">Estado</td>
					<td class="tdContenido"><label id="verEstadoLicencia">SIN CONFIRMAR</label></td>
				</tr>
				<tr class="ver">
					<td class="tdTitulo">Fecha de Emision</td>
					<td class="tdContenido"><label id="verLicenciaEmision" class="txtText"></label></td>
					<td class="tdTitulo">Fecha de Caducidad</td>
					<td class="tdContenido"><label id="verLicenciaCaducidad" class="txtText"></label></td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>DATOS DEL VEHICULO</legend>
			<table>
				<tr>
					<td class="tdTitulo">N° Placa</td>
					<td class="tdContenido"><label id="verPlacas"></label></td>
					<td class="tdTitulo">Año de Fabricación</td>
					<td class="tdContenido"><label id="verAnno" class="txtText"></label></td>
				</tr>
				<tr class="ver">
					<td class="tdTitulo">Marca</td>
					<td class="tdContenido"><label id="verMarca" class="txtText"></label></td>
					<td class="tdTitulo">Modelo</td>
					<td class="tdContenido"><label id="verModelo" class="txtText"></label></td>
				</tr>
				<tr>
					<td class="tdTitulo">Colores</td>
					<td colspan="3"><label id="verColor" class="txtTextCompleted"></label></td>
				</tr>
				<tr class="ver">
					<td class="tdTitulo">N° Tarjeta de Propiedad</td>
					<td class="tdContenido"><label id="verTarjPropiedad" class="txtText"></label></td>
					<td class="tdTitulo">Estado</td>
					<td class="tdContenido"><label id="verEstadoUnidad">SIN CONFIRMAR</label></td>
				</tr>
				<tr>
					<td class="tdTitulo">Fecha de Emision</td>
					<td class="tdContenido"><label id="verUnidadEmision" class="txtText"></label></td>
					<td class="tdTitulo">Fecha de Caducidad</td>
					<td class="tdContenido"><label id="verUnidadCaducidad" class="txtText"></label></td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>DATOS DEL PROPIETARIO</legend>
			<table>
				<tr class="ver">
					<td class="tdTitulo">Razón Social</td>
					<td colspan="3"><label id="verPropRazonSocial" class="txtText"></label></td>
				</tr>
				<tr>
					<td class="tdTitulo">Propietario</td>
					<td class="tdContenido"><label id="verPropietario" class="txtText"></label></td>
					<td class="tdTitulo">DNI N°</td>
					<td class="tdContenido"><label id="verPropDni" class="txtText"></label></td>
					
				</tr>
				<tr class="ver">
					<td class="tdTitulo">Domicilio</td>
					<td colspan="3"><label id="verPropDomicilio" class="txtTextCompleted"></label></td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>SOBRE LA INFRACCIÓN</legend>
			<table>
				<tr>
					<td class="tdTitulo">DNI N°</td>
					<td class="tdContenido"><label id="verDniInspector" class="txtText"></label></td>
					<td class="tdTitulo">Inspector</td>
					<td class="tdContenido"><label id="verInspector"></label></td>
				</tr>
				<tr class="ver">
					<td class="tdTitulo">Lugar de Infracción</td>
					<td colspan="3"><label id="verInfraccionLugar" class="txtTextCompleted"></label></td>
				</tr>
				<tr>
					<td class="tdTitulo">Referencia</td>
					<td colspan="3"><label id="verInfraccionReferencia" class="txtTextCompleted"></label></td>
				</tr>
				<tr class="ver">
					<td class="tdTitulo">Observacion del Inspector</td>
					<td colspan="3"><label id="verObserInspector"></label></td>
				</tr>
				<tr>
					<td class="tdTitulo">Carnet Ident N°</td>
					<td class="tdContenido"><label id="verCarnetPolicia" class="txtText"></label></td>
					<td class="tdTitulo">Policia</td>
					<td class="tdContenido"><label id="verPolicia"></label>
					</td>
				</tr>
				<tr class="ver">				
					<td class="tdTitulo">Es propietario - conductor</td>
					<td class="tdContenido">
						<label><input type="radio" name="rdPropietarioVer" value="S" id="rdPropietarioSiVer" disabled="disabled"/> Si </label>
						<label><input type="radio" name="rdPropietarioVer" value="N" id="rdPropietarioNoVer" disabled="disabled"/> No </label>
					</td>
					<td class="tdTitulo">Adjuntar Imagen</td>
					<td class="tdContenido"><label id="lblFoto"></label></td>
				</tr>
				<tr>
					<td class="tdTitulo">Codigo de Infracción</td>
					<td class="tdContenido"><label id="verInfraccion"></label>
					</td>
					<td class="tdTitulo">Medidas/Sacion Complementaria</td>
					<td class="tdContenido"><label id="verSancion"></label>
					</td>
				</tr>
				<tr class="ver">
					<td class="tdTitulo">Descripción</td>
					<td colspan="3"><label id="verDescripcion"></label></td>
				</tr>
				<tr>
					<td class="tdTitulo">Observacion sobre la Infracción</td>
					<td colspan="3"><label id="verObserInfraccion"></label></td>
				</tr>
			</table>
		</fieldset>
		<h4 align="center">
			<input type="button" value="Imprimir" id="btnImprimir" class="ui-button"/>
			<input type="button" value="Aceptar" id="btnAceptar" class="ui-button"/>
		</h4>
	</div>
	
	<div id="divAgregarConductor">
		<fieldset>
			<legend>Nuevo Conductor</legend>
			<input type="hidden" id="txtCodigoConductor" value="0"/>
			<input type="hidden" id="txtCodigoPersona" value="0"/>        
        	<table align="center">
					<tr>
						<td>Nombres:</td>
						<td><input type="text" id="txtNombres" class="requiredText" tabindex="1"/></td>
						<td>Dirección:</td>
						<td><input type="text" id="txtDireccion" class="requiredAlpha" tabindex="10"/></td>
					</tr>
					<tr>
						<td>Paterno:</td>
						<td><input type="text" id="txtPaterno" class="requiredText" tabindex="2"/></td>
						<td>Departamento:</td>
						<td>
							<select id="sltDepartamentos" class="requiredSelect" tabindex="11">
								<option value="" selected="selected">Seleccione</option>
								<c:forEach items="${departamentos}" var="departamento">
									<option value="${departamento.idubigeo}">
										<c:out value="${departamento.nombubigeo}"/>
									</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>Materno:</td>
						<td><input type="text" id="txtMaterno" class="requiredText" tabindex="3"/></td>
						<td>Provincia:</td>
						<td>
							<select id="sltProvincia" class="requiredSelect" tabindex="12">
								<option value="" selected="selected">Seleccione</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>DNI</td>
						<td><input type="text" id="txtDni" class="requiredNumber" tabindex="4"/></td>
						<td>Distrito:</td>
						<td>
							<select id="sltDistrito" class="requiredSelect" tabindex="13">
								<option value="" selected="selected">Seleccione</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>Correo:</td>
						<td><input type="text" id="txtCorreo" class="required" tabindex="5"/></td>
						<td>Telefono:</td>
						<td><input type="text" id="txtTelefono" class="required" tabindex="14"/></td>
					</tr>
					<tr>
						<td>Estado Civil</td>
						<td>
							<select id="sltEstadoCivil" class="required" tabindex="6">
								<option value="S" selected="selected">Soltero</option>
								<option value="C">Casado</option>
								<option value="T">Conviviente</option>
								<option value="V">Viudo</option>
								<option value="D">Divorciado</option>
							</select>
						</td>
						<td>Cel. Movistar:</td>
						<td><input type="text" id="txtMovistar" class="required" tabindex="15"/></td>
					</tr>
					<tr>
						<td>Sexo</td>
						<td>
							<select id="sltSexo" class="required" tabindex="7">
								<option value="" selected="selected">Seleccione</option>
								<option value="M">Masculino</option>
								<option value="F">Femenino</option>
							</select>
						</td>
						<td>Cel. Claro:</td>
						<td><input type="text" id="txtClaro" class="required" tabindex="16"/></td>
					</tr>
					<tr>
						<td>Fecha Nacimiento</td>
						<td><input type="text" id="dtNacimiento" class="required" tabindex="8"/></td>
						<td>Cel. Nextel:</td>
						<td><input type="text" id="txtNextel" class="required" tabindex="17"/></td>
					</tr>					
			</table>
			
			<center>
				<input type="button" id="btnGuardarCond" class="ui-button" value="Guardar"/>
				<input type="button" id="btnCancelarCond" class="ui-button" value="Cancelar"/>
			</center>
		</fieldset>
	</div>
	
	<div id="divVehiculo">
    	<input type="hidden" id="txtCodigoVehiculo" value="0"/>
    	<input type="hidden" id="txtCodigoEmpadronamiento" value="0"/>
    	<fieldset>
        	<legend>DATOS VEHICULO</legend>
            <table align="center">
            	<tr>
                	<td>N° Placa</td>
                	<td><input type="text" id="txtNroPlaca" class="requiredAlpha" tabindex="26"/></td>
                	<td>Carroceria</td>
                	<td><select id="sltCarroceria" class="required" tabindex="37">
                    		<option value="" selected="selected">Seleccione</option>
                    		<option value="T">TRIMOVIL DE PASAJEROS</option>
                    	</select>
                    </td>
                </tr>
            	<tr>
                	<td>Of. Registral</td>
                	<td><select id="sltOfRegistral" class="required" tabindex="27">
                    		<option value="">Seleccione</option>
                    		<c:forEach var="oficina" items="${oficinas}">
                    			<option value="${oficina.oficodigoI}"><c:out value="${oficina.ofinombreV}"/></option>
                    		</c:forEach>
                    	</select>
					</td>
                	<td>N° Serie/Chasis</td>
                	<td><input type="text" id="txtNroSerieChasis" class="required" tabindex="38"/></td>
                </tr>
            	<tr>
                	<td>N° Padrón</td>
                	<td><input type="text" id="txtNroPadron" class="required" tabindex="28"/></td>
                	<td>N° Motor</td>
                	<td><input type="text" id="txtNroMotor" class="required" tabindex="39"/></td>
                </tr>
            	<tr>
                	<td>Part. Registral</td>
                	<td><input type="text" id="txtPartRegistral" class="required" tabindex="29"/></td>
                	<td>N° NIV</td>
                	<td><input type="text" id="txtNroNiv" class="required" tabindex="40"/></td>
                </tr>
            	<tr>
                	<td>Titulo</td>
                	<td><input type="text" id="txtTitulo" class="required" tabindex="30"/></td>
                	<td>Ruedas</td>
                	<td><input type="text" id="txtRuedas" class="required" tabindex="41"/></td>
                </tr>
            	<tr>
                	<td>Clase</td>
                	<td><input type="text" id="txtClase" class="required" tabindex="31"/></td>
                	<td>N° Asientos</td>
                	<td><input type="text" id="txtNroAsientos" class="required" tabindex="42"/></td>
                </tr>
            	<tr>
                	<td>Marca</td>
                	<td><select id="sltMarca" class="requiredSelect" tabindex="32">
                    		<option value="" selected="selected">Seleccione</option>
                    		<c:forEach var="marca" items="${marcas}">
                    			<option value="${marca.marcodigoI}"><c:out value="${marca.marnombreV }"/></option>
                    		</c:forEach>
                    	</select></td>
                	<td>N° Pasajeros</td>
                	<td><input type="text" id="txtNroPasajeros" class="required" tabindex="43"/></td>
                </tr>
            	<tr>
                	<td>Modelo</td>
                	<td><select id="sltModelo" class="requiredSelect" tabindex="33">
                    		<option value="" selected="selected">Seleccione</option>
                    		<c:forEach var="modelo" items="${modelos}">
                    			<option value="${modelo.modcodigo_D }"><c:out value="${modelo.modnombre_V }" /></option>
                    		</c:forEach>
                    	</select></td>
                	<td>Carga Util</td>
                	<td><input type="text" id="txtCargaUtil" class="required" tabindex="44"/>Kg.</td>
                </tr>
            	<tr>
                	<td>Año</td>
                	<td><input type="text" id="txtAno" class="requiredNumber" tabindex="34"/></td>
                	<td>Longitud</td>
                	<td><input type="text" id="txtLongitud" class="required" tabindex="45"/>Mts.</td>
                </tr>
            	<tr>
                	<td>Color</td>
                	<td><input type="text" id="txtMotColor" class="required" tabindex="35"/></td>
                	<td>Ancho</td>
                	<td><input type="text" id="txtAncho" class="required" tabindex="46"/>Mts.</td>
                </tr>
            	<tr>
                	<td>Combustible</td>
                	<td><select id="sltCombustible" class="required" tabindex="36">
                    		<option value="" selected="selected">Seleccione</option>
                    		<option value="P">PETROLEO</option>
                    		<option value="G">GASOLINA</option>
                    	</select></td>
                	<td>Alto</td>
                	<td><input type="text" id="txtAlto" class="required" tabindex="47"/>Mts.</td>
                </tr>
            </table>
            
			<center>
				<input type="button" id="btnGuardarMoto" class="ui-button" value="Guardar"/>
				<input type="button" id="btnCancelarMoto" class="ui-button" value="Cancelar"/>
			</center>
        </fieldset>
    </div>
	
	<div id="divPolicia">
    	<input type="hidden" id="txtCodigoPolicia" value="0"/>
    	<fieldset>
        	<legend>DATOS POLICIA</legend>
            <table align="center">
            	<tr>
                	<td>N° Carnet Identidad</td>
                	<td><input type="text" id="txtPolCarnet" class="requiredAlpha" tabindex="26"/></td>
                	<td>Nombre</td>
                	<td><input type="text" id="txtPolNombre" class="requiredAlpha" tabindex="38"/>
                    </td>
                </tr>
            	<tr>
                	<td></td>
                	<td></td>
                	<td>Paterno</td>
                	<td><input type="text" id="txtPolPaterno" class="requiredAlpha" tabindex="38"/></td>
                </tr>
            	<tr>
                	<td></td>
                	<td></td>
                	<td>Materno</td>
                	<td><input type="text" id="txtPolMaterno" class="requiredAlpha" tabindex="39"/></td>
                </tr>
            </table>
            
			<center>
				<input type="button" id="btnGuardarPol" class="ui-button" value="Guardar"/>
				<input type="button" id="btnCancelarPol" class="ui-button" value="Cancelar"/>
			</center>
        </fieldset>
    </div>
</body>
</html>