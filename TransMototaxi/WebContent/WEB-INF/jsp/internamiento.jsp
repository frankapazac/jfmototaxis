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
	<script type="text/javascript" src="js/ui/jquery.ui.effect.js"></script>
	<script type="text/javascript" src="js/ui/jquery.ui.effect-clip.js"></script>
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
	<script type="text/javascript" src="js/ui/jquery.ui.tooltip.js"></script>
	<script type="text/javascript" src="js/jefra.validate.js"></script >
	<script type="text/javascript" src="js/jefra.message.js"></script >
	<script type="text/javascript" src="js/jefra.util.js"></script >
	<script type="text/javascript" src="js/jsp/internamiento.js"></script>
<title>Insert title here</title>
<style type="text/css">
		.tdTitulo{
			width: 200px;
		}
		.tdContenido{
			width: 250px;
		}
		.txtText{
			width: 245px;
		}
		.txtTextCompleted{
			width: 703px;
		}
		.divPartes{
			width:33%;
			float:left;
		}
		.divPartesV{
			width:33%;
			float:left;
		}
		#divPartePrincipal{
			width:100%;
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
	});
	</script>
</head>
<body>
	<h1>Internamiento</h1>

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
						<option value="ITE.INTCODIGO_D">TODOS</option>
						<option value="PAP.PAPNUMERO_V">PAPELETA</option>
						<option value="UNE.UNEPLACANRO_V">PLACA</option>
						<option value="CON.NOMBRES">CONDUCTOR</option>
						<option value="CON.DNI">CONDUCTOR DNI</option>
						<option value="ASO.NOMBRES">PROPIETARIO</option>
						<option value="ASO.DNI">PROPIETARIO DNI</option>
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
		<img id="btnNuevo" alt="Nuevo" src="images/Add.png"/>
	</h4>	

	<div id="divFormulario">
		<fieldset>
        	<legend>SOBRE EL INTERNAMIENTO</legend>
        	<input type="hidden" id="txtCodigo" value="0"/>
        	<table>
                <tr>
                    <td>Papeleta</td>
                    <td colspan="3">
                    	<input type="text" id="txtPapeleta" class="txtText required"/>
                    	<input type="hidden" id="txtPapCodigo"/>
                    </td>
                </tr>
                <tr>
                    <td>Motivo</td>
                    <td colspan="3">
                    	<textarea id="txtMotivo" class="txtTextCompleted requiredText" rows="3"></textarea>
                    </td>
                </tr>
                <tr>
                    <td class="tdTitulo">Fecha</td>
                    <td class="tdContenido"><label id="txtFecha"></label></td>
                    <td class="tdTitulo">Hora</td>
                    <td class="tdContenido"><label id="txtHora"></label></td>
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
							<option value="0">-Seleccione-</option>
							<c:forEach var="conductor" items="${conductores}">
								<option value="${conductor.concodigoD}"><c:out value="${conductor.persona.perpaternoV}"/> <c:out value="${conductor.persona.permaternoV}"/>, <c:out value="${conductor.persona.pernombresV}"/></option>
							</c:forEach>
						</select>
					</td>
				</tr>
                <tr>
					<td class="tdTitulo">Dirección</td>
					<td colspan="3"><input type="text" id="txtDireccion" class="txtTextCompleted" disabled="disabled"/></td>
				</tr>
                <tr>
					<td class="tdTitulo">Telefono</td>
					<td class="tdContenido"><input type="text" id="txtTelefono" class="txtText" disabled="disabled"/></td>
					<td class="tdTitulo">Celular</td>
					<td class="tdContenido"><input type="text" id="txtCelular" class="txtText" disabled="disabled"/></td>
				</tr>
				<tr>
					<td class="tdTitulo">N° Licencia</td>
					<td class="tdContenido"><input type="text" id="txtNroLicencia" class="txtText" disabled="disabled"/></td>
					<td class="tdTitulo">Estado</td>
					<td class="tdContenido">
						<input type="text" id="txtEstadoLicencia" class="txtText" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<td class="tdTitulo">Fecha de Emision</td>
					<td class="tdContenido"><input type="text" id="txtFechaEmision" class="txtText" disabled="disabled"/></td>
					<td class="tdTitulo">Fecha de Caducidad</td>
					<td class="tdContenido"><input type="text" id="txtFechaCaducidad" class="txtText" disabled="disabled"/></td>
				</tr>
			</table>
		</fieldset>
        <fieldset>
        	<legend>DATOS DEL VEHICULO</legend>
            <table>
                <tr>
					<td class="tdTitulo">N° Placa</td>
                    <td class="tdContenido">
                        <select id="sltPlacas" class="requiredSelect">
                            <option value="0" selected="selected">-Seleccione-</option>
							<c:forEach var="placa" items="${placas}">
								<option value="${placa.unecodigoD}"><c:out value="${placa.uneplacanroV}"/></option>
							</c:forEach>
                        </select>
                    </td>
                    <td class="tdTitulo">Marca</td>
                    <td class="tdContenido"><input type="text" id="txtMarca" class="txtText" disabled="disabled"/></td>
                </tr>
                <tr>
                	<td class="tdTitulo">N° motor</td>
                	<td class="tdContenido"><input type="text" id="txtNroMotor" class="txtText" disabled="disabled"/></td>
                	<td class="tdTitulo">Color</td>
                	<td class="tdContenido"><input type="text" id="txtColor" class="txtText" disabled="disabled"/></td>
                </tr>
            </table>
        </fieldset>
        <fieldset>
			<legend>DATOS DEL PROPIETARIO</legend>
			<table>
				<tr>
					<td class="tdTitulo">DNI N°</td>
					<td class="tdContenido"><input type="text" id="txtDniPropietario" class="txtText" disabled="disabled"/></td>
					<td class="tdTitulo">Apellidos y Nombres</td>
					<td class="tdContenido"><input type="text" id="txtNombresPropietario" class="txtText" disabled="disabled"/></td>
				</tr>
                <tr>
					<td class="tdTitulo">Dirección</td>
					<td colspan="3"><input type="text" id="txtDirecPropietario" class="txtTextCompleted" disabled="disabled"/></td>
				</tr>
                <tr>
					<td class="tdTitulo">Telefono</td>
					<td class="tdContenido"><input type="text" id="txtTelefPropietario" class="txtText" disabled="disabled"/></td>
					<td>Celular</td>
					<td class="tdContenido"><input type="text" id="txtCelulPropietario" class="txtText" disabled="disabled"/></td>
				</tr>
			</table>
		</fieldset>
        <fieldset>
			<legend>DATOS DE LA EMPRESA</legend>
			<table>
				<tr>
					<td class="tdTitulo">Empresa</td>
					<td colspan="3"><input type="text" id="txtEmpresa" class="txtTextCompleted" disabled="disabled"/></td>
				</tr>
				<tr>
					<td class="tdTitulo">Dirección</td>
					<td colspan="3"><input type="text" id="txtEmprDireccion" class="txtTextCompleted" disabled="disabled"/></td>
				</tr>
				<tr>
					<td class="tdTitulo">Telefono</td>
					<td class="tdContenido"><input type="text" id="txtEmprTelefono" class="txtText" disabled="disabled"/></td>
					<td class="tdTitulo">Celular</td>
					<td class="tdContenido"><input type="text" id="txtEmprCelular" class="txtText" disabled="disabled"/></td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>INVENTARIO</legend>
			<div id="divPartePrincipal">
		    	<div class="divPartes">
		    		<h5>PARTE EXTERIOR</h5>
					<table>
						<tr>
							<td></td>
							<td></td>
							<td>N</td>
							<td>B</td>
							<td>R</td>
							<td>M</td>
							<td>Cant.</td>
						</tr>
						<c:forEach var="parteExterior" items="${parteExterior}" varStatus="status">
							<tr>
								<td><c:out value="${status.count}"/></td>
								<td><c:out value="${parteExterior.bitnombreV}"/></td>
								<td><input type="radio" 
									name="rdParte_<c:out value="${parteExterior.bitcodigoI}"/>"
									class="rdParte_<c:out value="${parteExterior.bitcodigoI}"/>"
									value="N" checked="checked"></td>
								<td><input type="radio" 
									name="rdParte_<c:out value="${parteExterior.bitcodigoI}"/>"
									class="rdParte_<c:out value="${parteExterior.bitcodigoI}"/>"
									value="B" ></td>
								<td><input type="radio" 
									name="rdParte_<c:out value="${parteExterior.bitcodigoI}"/>"
									class="rdParte_<c:out value="${parteExterior.bitcodigoI}"/>"
									value="R" ></td>
								<td><input type="radio" 
									name="rdParte_<c:out value="${parteExterior.bitcodigoI}"/>"
									class="rdParte_<c:out value="${parteExterior.bitcodigoI}"/>"
									value="M" ></td>
								<td><input id="cant_<c:out value="${parteExterior.bitcodigoI}"/>" type="text" value="1" size="3"/></td>
							</tr>
						</c:forEach>
					</table>
				</div>
		        <div class="divPartes">
					<h5>PARTE INTERIOR</h5>
					<table>
						<tr>
							<td></td>
							<td></td>
							<td>N</td>
							<td>B</td>
							<td>R</td>
							<td>M</td>
							<td>Cant.</td>
						</tr>
						<c:forEach var="parteInterior" items="${parteInterior}" varStatus="status">
							<tr>
								<td><c:out value="${status.count}"/></td>
								<td><c:out value="${parteInterior.bitnombreV}"/></td>
								<td><input type="radio" 
									name="rdParte_<c:out value="${parteInterior.bitcodigoI}"/>"
									class="rdParte_<c:out value="${parteInterior.bitcodigoI}"/>"
									value="N" checked="checked"></td>
								<td><input type="radio" 
									name="rdParte_<c:out value="${parteInterior.bitcodigoI}"/>"
									class="rdParte_<c:out value="${parteInterior.bitcodigoI}"/>"
									value="B" ></td>
								<td><input type="radio" 
									name="rdParte_<c:out value="${parteInterior.bitcodigoI}"/>"
									class="rdParte_<c:out value="${parteInterior.bitcodigoI}"/>"
									value="R" ></td>
								<td><input type="radio" 
									name="rdParte_<c:out value="${parteInterior.bitcodigoI}"/>"
									class="rdParte_<c:out value="${parteInterior.bitcodigoI}"/>"
									value="M" ></td>
								<td><input id="cant_<c:out value="${parteInterior.bitcodigoI}"/>" type="text" value="1" size="3"/></td>
							</tr>
						</c:forEach>
					</table>
				</div>
		        <div class="divPartes">
					<h5>MOTOR</h5>
					<table>
						<tr>
							<td></td>
							<td></td>
							<td>N</td>
							<td>B</td>
							<td>R</td>
							<td>M</td>
							<td>Cant.</td>
						</tr>
						<c:forEach var="parteMotor" items="${parteMotor}" varStatus="status">
							<tr>
								<td><c:out value="${status.count}"/></td>
								<td><c:out value="${parteMotor.bitnombreV}"/></td>
								<td><input type="radio" 
									name="rdParte_<c:out value="${parteMotor.bitcodigoI}"/>"
									class="rdParte_<c:out value="${parteMotor.bitcodigoI}"/>"
									value="N" checked="checked"></td>
								<td><input type="radio" 
									name="rdParte_<c:out value="${parteMotor.bitcodigoI}"/>"
									class="rdParte_<c:out value="${parteMotor.bitcodigoI}"/>"
									value="B" ></td>
								<td><input type="radio" 
									name="rdParte_<c:out value="${parteMotor.bitcodigoI}"/>"
									class="rdParte_<c:out value="${parteMotor.bitcodigoI}"/>"
									value="R" ></td>
								<td><input type="radio" 
									name="rdParte_<c:out value="${parteMotor.bitcodigoI}"/>"
									class="rdParte_<c:out value="${parteMotor.bitcodigoI}"/>"
									value="M" ></td>
								<td><input id="cant_<c:out value="${parteMotor.bitcodigoI}"/>" type="text" value="1" size="3"/></td>
							</tr>
						</c:forEach>
					</table>
				</div>
		    </div>
		</fieldset>
		
		<h4 align="center">
			<input type="button" value="Guardar" id="btnProcesar"/>
			<input type="button" value="Cerrar" id="btnCancelar"/>
		</h4>
    </div>
    
    
	<div id="divFormularioVer">
    	<fieldset>
        	<legend>SOBRE EL INTERNAMIENTO</legend>
        	<input type="hidden" id="lblCodigo" value="0"/>
        	<table>
                <tr>
                    <td>Papeleta</td>
                    <td colspan="3">
                    	<label id="lblPapeleta"></label>
                    </td>
                </tr>
                <tr>
                    <td>Motivo</td>
                    <td colspan="3">
                    	<label id="lblMotivo"></label>
                    </td>
                </tr>
                <tr>
                    <td class="tdTitulo">Fecha</td>
                    <td class="tdContenido"><label id="lblFecha"></label></td>
                    <td class="tdTitulo">Hora</td>
                    <td class="tdContenido"><label id="lblHora"></label></td>
                </tr>
            </table>
        </fieldset>
        <fieldset>
			<legend>DATOS DEL CONDUCTOR</legend>
			<table>
				<tr>
					<td class="tdTitulo">DNI N°</td>
					<td class="tdContenido"><label id="lblConductorDNI"></label></td>
					<td class="tdTitulo">Conductor</td>
					<td class="tdContenido">
						<label id="lblConductor"></label>
					</td>
				</tr>
                <tr>
					<td class="tdTitulo">Dirección</td>
					<td colspan="3"><label id="lblDireccion"></label></td>
				</tr>
                <tr>
					<td class="tdTitulo">Telefono</td>
					<td class="tdContenido"><label id="lblTelefono"></label></td>
					<td class="tdTitulo">Celular</td>
					<td class="tdContenido"><label id="lblCelular"></label></td>
				</tr>
				<tr>
					<td class="tdTitulo">N° Licencia</td>
					<td class="tdContenido"><label id="lblNroLicencia"></label></td>
					<td class="tdTitulo">Estado</td>
					<td class="tdContenido">
						<label id="lblEstadoLicencia"></label>
					</td>
				</tr>
				<tr>
					<td class="tdTitulo">Fecha de Emision</td>
					<td class="tdContenido"><label id="lblFechaEmision"></label></td>
					<td class="tdTitulo">Fecha de Caducidad</td>
					<td class="tdContenido"><label id="lblFechaCaducidad"></label></td>
				</tr>
			</table>
		</fieldset>
        <fieldset>
        	<legend>DATOS DEL VEHICULO</legend>
            <table>
                <tr>
					<td class="tdTitulo">N° Placa</td>
                    <td class="tdContenido">
                        <label id="lblPlacas"></label>
                    </td>
                    <td class="tdTitulo">Marca</td>
                    <td class="tdContenido"><label id="lblMarca"></label></td>
                </tr>
                <tr>
                	<td class="tdTitulo">N° motor</td>
                	<td class="tdContenido"><label id="lblNroMotor"></label></td>
                	<td class="tdTitulo">Color</td>
                	<td class="tdContenido"><label id="lblColor"></label></td>
                </tr>
            </table>
        </fieldset>
        <fieldset>
			<legend>DATOS DEL PROPIETARIO</legend>
			<table>
				<tr>
					<td class="tdTitulo">DNI N°</td>
					<td class="tdContenido"><label id="lblDniPropietario"></label></td>
					<td class="tdTitulo">Apellidos y Nombres</td>
					<td class="tdContenido"><label id="lblNombresPropietario"></label></td>
				</tr>
                <tr>
					<td class="tdTitulo">Dirección</td>
					<td colspan="3"><label id="lblDirecPropietario"></label></td>
				</tr>
                <tr>
					<td class="tdTitulo">Telefono</td>
					<td class="tdContenido"><label id="lblTelefPropietario"></label></td>
					<td>Celular</td>
					<td class="tdContenido"><label id="lblCelulPropietario"></label></td>
				</tr>
			</table>
		</fieldset>
        <fieldset>
			<legend>DATOS DE LA EMPRESA</legend>
			<table>
				<tr>
					<td class="tdTitulo">Empresa</td>
					<td colspan="3"><label id="lblEmpresa"></label></td>
				</tr>
				<tr>
					<td class="tdTitulo">Dirección</td>
					<td colspan="3"><label id="lblEmprDireccion"></label></td>
				</tr>
				<tr>
					<td class="tdTitulo">Telefono</td>
					<td class="tdContenido"><label id="lblEmprTelefono"></label></td>
					<td class="tdTitulo">Celular</td>
					<td class="tdContenido"><label id="lblEmprCelular"></label></td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>INVENTARIO</legend>
			<div id="divPartePrincipal">
		    	<div class="divPartesV">
		    		<h5>PARTE EXTERIOR</h5>
					<table>
						<tr>
							<td></td>
							<td></td>
							<td>N</td>
							<td>B</td>
							<td>R</td>
							<td>M</td>
							<td>Cant.</td>
						</tr>
						<c:forEach var="parteExterior" items="${parteExterior}" varStatus="status">
							<tr>
								<td><c:out value="${status.count}"/></td>
								<td><c:out value="${parteExterior.bitnombreV}"/></td>
								<td><input type="radio" 
									name="rdPartev_<c:out value="${parteExterior.bitcodigoI}"/>"
									class="rdParte_<c:out value="${parteExterior.bitcodigoI}"/>"
									value="N" checked="checked" disabled="disabled"></td>
								<td><input type="radio" 
									name="rdPartev_<c:out value="${parteExterior.bitcodigoI}"/>"
									class="rdParte_<c:out value="${parteExterior.bitcodigoI}"/>"
									value="B" disabled="disabled"></td>
								<td><input type="radio" 
									name="rdPartev_<c:out value="${parteExterior.bitcodigoI}"/>"
									class="rdParte_<c:out value="${parteExterior.bitcodigoI}"/>"
									value="R" disabled="disabled"></td>
								<td><input type="radio" 
									name="rdPartev_<c:out value="${parteExterior.bitcodigoI}"/>"
									class="rdParte_<c:out value="${parteExterior.bitcodigoI}"/>"
									value="M" disabled="disabled"></td>
								<td><input id="cant_<c:out value="${parteExterior.bitcodigoI}"/>" type="text" value="1" size="3" disabled="disabled"/></td>
							</tr>
						</c:forEach>
					</table>
				</div>
		        <div class="divPartesV">
					<h5>PARTE INTERIOR</h5>
					<table>
						<tr>
							<td></td>
							<td></td>
							<td>N</td>
							<td>B</td>
							<td>R</td>
							<td>M</td>
							<td>Cant.</td>
						</tr>
						<c:forEach var="parteInterior" items="${parteInterior}" varStatus="status">
							<tr>
								<td><c:out value="${status.count}"/></td>
								<td><c:out value="${parteInterior.bitnombreV}"/></td>
								<td><input type="radio" 
									name="rdPartev_<c:out value="${parteInterior.bitcodigoI}"/>"
									class="rdParte_<c:out value="${parteInterior.bitcodigoI}"/>"
									value="N" checked="checked" disabled="disabled"></td>
								<td><input type="radio" 
									name="rdPartev_<c:out value="${parteInterior.bitcodigoI}"/>"
									class="rdParte_<c:out value="${parteInterior.bitcodigoI}"/>"
									value="B" disabled="disabled"></td>
								<td><input type="radio" 
									name="rdPartev_<c:out value="${parteInterior.bitcodigoI}"/>"
									class="rdParte_<c:out value="${parteInterior.bitcodigoI}"/>"
									value="R" disabled="disabled"></td>
								<td><input type="radio" 
									name="rdPartev_<c:out value="${parteInterior.bitcodigoI}"/>"
									class="rdParte_<c:out value="${parteInterior.bitcodigoI}"/>"
									value="M" disabled="disabled"></td>
								<td><input id="cant_<c:out value="${parteInterior.bitcodigoI}"/>" type="text" value="1" size="3" disabled="disabled"/></td>
							</tr>
						</c:forEach>
					</table>
				</div>
		        <div class="divPartesV">
					<h5>MOTOR</h5>
					<table>
						<tr>
							<td></td>
							<td></td>
							<td>N</td>
							<td>B</td>
							<td>R</td>
							<td>M</td>
							<td>Cant.</td>
						</tr>
						<c:forEach var="parteMotor" items="${parteMotor}" varStatus="status">
							<tr>
								<td><c:out value="${status.count}"/></td>
								<td><c:out value="${parteMotor.bitnombreV}"/></td>
								<td><input type="radio" 
									name="rdPartev_<c:out value="${parteMotor.bitcodigoI}"/>"
									class="rdParte_<c:out value="${parteMotor.bitcodigoI}"/>"
									value="N" checked="checked" disabled="disabled"></td>
								<td><input type="radio" 
									name="rdPartev_<c:out value="${parteMotor.bitcodigoI}"/>"
									class="rdParte_<c:out value="${parteMotor.bitcodigoI}"/>"
									value="B" disabled="disabled"></td>
								<td><input type="radio" 
									name="rdPartev_<c:out value="${parteMotor.bitcodigoI}"/>"
									class="rdParte_<c:out value="${parteMotor.bitcodigoI}"/>"
									value="R" disabled="disabled"></td>
								<td><input type="radio" 
									name="rdPartev_<c:out value="${parteMotor.bitcodigoI}"/>"
									class="rdParte_<c:out value="${parteMotor.bitcodigoI}"/>"
									value="M" disabled="disabled"></td>
								<td><input id="cant_<c:out value="${parteMotor.bitcodigoI}"/>" type="text" value="1" size="3" disabled="disabled"/></td>
							</tr>
						</c:forEach>
					</table>
				</div>
		    </div>
		</fieldset>
		
		<h4 align="center">
			<img id="btnImprimir" alt="Imprimir" src="images/pdf.png">
			<input type="button" value="Aceptar" id="btnAceptarVer"/>
		</h4>
    </div>
</body>
</html>