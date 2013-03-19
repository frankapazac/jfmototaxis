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
	<script type="text/javascript" src="js/jsp/papeleta.js"></script>
	<title>Insert title here</title>
	<style type="text/css">
		.tdTitulo{
			width: 200px;
		}
		.tdContenido{
			width: 250px;
		}
		.fileFoto{
			width: 245px;
		}
		.txtText{
			width: 245px;
		}
		.txtTextCompleted{
			width: 703px;
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
			width: 200px;
		}
		.ver{
			background-color: #f0f0f0;
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
				<td><input type="button" id="btnBuscar" value="Buscar"/></td>
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
		<img id="btnNuevo" alt="Nuevo" src="images/Nuevo.png"/>
	</h4>
	
	<div id="divFormulario">
		<fieldset>
			<legend>PAPELETA</legend>
			<table>
				<tr>
					<td class="tdTitulo">Número</td>
					<td class="tdContenido"><input type="text" id="txtNumeroPapeleta" class="txtText" disabled="disabled"/></td>
					<td class="tdTitulo">Fecha</td>
					<td class="tdContenido"><input type="text" id="txtFecha"/></td>
				</tr>
			</table>
		</fieldset>
			
		<fieldset>
			<legend>DATOS DEL CONDUCTOR</legend>
			<table>
				<tr>
					<td class="tdTitulo">DNI N°</td>
					<td class="tdContenido"><input type="text" id="txtConductorDNI" class="txtText requiredNumber"/></td>
					<td class="tdTitulo">Conductor</td>
					<td class="tdContenido">
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
					<td class="tdTitulo">N° Placa</td>
					<td class="tdContenido"><select id="sltPlacas" class="requiredSelect">
							<option value="" selected="selected">-Seleccione-</option>
							<c:forEach var="placa" items="${placas}">
								<option value="${placa.unecodigoD}"><c:out value="${placa.uneplacanroV}"/></option>
							</c:forEach>
						</select>
					</td>
					<td class="tdTitulo">Año de Fabricación</td>
					<td class="tdContenido"><input id="txtAnno" type="text" class="txtText" disabled="disabled"/></td>
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
					<td class="tdTitulo">Propietario</td>
					<td class="tdContenido"><input id="txtPropietario" type="text" class="txtText" disabled="disabled" /></td>
					<td class="tdTitulo">DNI N°</td>
					<td class="tdContenido"><input id="txtPropDni" type="text" class="txtText" disabled="disabled" /></td>
					
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
					<td colspan="3"><input id="txtInfraccionLugar" type="text" class="txtTextCompleted requiredText" /></td>
				</tr>
				<tr>
					<td class="tdTitulo">Referencia</td>
					<td colspan="3"><input id="txtInfraccionReferencia" type="text" class="txtTextCompleted requiredText" /></td>
				</tr>
				<tr>
					<td class="tdTitulo">Observacion del Inspector</td>
					<td colspan="3"><textarea id="txaObserInspector" rows="3" cols="98" class="requiredText"></textarea></td>
				</tr>
				<tr>
					<td class="tdTitulo">Carnet Ident N°</td>
					<td class="tdContenido"><input id="txtCarnetPolicia" type="text" class="txtText required" /></td>
					<td class="tdTitulo">Policia</td>
					<td class="tdContenido">
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
					<td colspan="3"><textarea id="txaDescripcion" rows="3" cols="98" disabled="disabled"></textarea></td>
				</tr>
				<tr>
					<td class="tdTitulo">Observacion sobre la Infracción</td>
					<td colspan="3"><textarea id="txaObserInfraccion" rows="3" cols="98" class="requiredText"></textarea></td>
				</tr>
			</table>
		</fieldset>
		<h4 align="center">
			<input type="button" value="Guardar" id="btnGuardar"/>
			<input type="button" value="Cancelar" id="btnCancelar"/>
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
			<img id="btnImprimir" alt="Imprimir" src="images/pdf.png">
			<input type="button" value="Aceptar" id="btnAceptar"/>
		</h4>
	</div>
	
</body>
</html>