<?xml version="1.0" encoding="UTF-8"?>
<!-- © Copyright 2015, CoDeSoft Todos los derechos reservados. -->
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{expedientes$ABMExpediente$ABMExpediente.page1}" id="page1">
			<ui:html binding="#{expedientes$ABMExpediente$ABMExpediente.html1}" id="html1">
			<ui:head binding="#{expedientes$ABMExpediente$ABMExpediente.head1}" id="head1">
				<ui:link binding="#{expedientes$ABMExpediente$ABMExpediente.link1}" id="link1" url="/resources/stylesheet.css" />
				<style>
#comentario {
	display: block;
}

#comentario.text {
	margin-bottom: 12px;
	width: 95%;
	padding: .4em;
}

.ui-dialog .ui-state-error {
	padding: .3em;
}

.validateTips .validateTipsPass {
	border: 1px solid transparent;
	padding: 0.3em;
}
</style>
				<script>
					<![CDATA[

					function setEnterAComponente(input, boton) {
						$(input).keyup(function(e) {
							if(e.keyCode == 13) {
								$(boton).click();
							}
						});
					}

					function desactivarAutocompletePass() {
						$('#form1').attr("autocomplete", "off");
					}

					function cargarComportamientoJQuery() {
						desactivarAutocompletePass();
						// INICIO Comentario de guardado...
						var comentario = $("#form1\\:tabSet1\\:one\\:taComentario"), tips = $(".validateTips");

						$("#comentario-form").dialog({
							autoOpen: false,
							height: 300,
							width: 360,
							resizable: false,
							modal: true,
							close: function() {
								comentario.val("").removeClass("ui-state-error");
								return false;
							}
						}).parent().appendTo($("#form1"));

						$("#seguridad-modal-form").dialog({
							autoOpen: false,
							height: 120,
							width: 'auto',
							resizable: false,
							modal: true,
							close: function() {
								return false;
							}
						}).parent().appendTo($("#form1"));

						$("#form1\\:tabSet1\\:one\\:btnIrAFaseEspecial").mousedown(function() {
							$valor = $("#form1\\:tabSet1\\:one\\:ddFasesEspeciales").val();
							if($valor !== "") {
								tips.text("Agregar un comentario por el cambio a la fase especial del expediente.");
								$("#form1\\:tabSet1\\:one\\:btnComentarioIrAFaseEspecial").show();
								$("#form1\\:tabSet1\\:one\\:btnComentarioRetroceso").hide();
								$("#form1\\:tabSet1\\:one\\:btnComentarioCancelarAvance").hide();
								$("#form1\\:tabSet1\\:one\\:btnComentarioCierre").hide();

								$("#comentario-form").dialog("open");
							}
						});
						$("#form1\\:tabSet1\\:one\\:btnRetrocederFase").mousedown(function() {
							tips.text("Agregar un comentario por el retroceso de fase del expediente.");
							$("#comentario-form").dialog("option", "height", 300);
							$("#form1\\:tabSet1\\:one\\:btnComentarioRetroceso").show();
							$("#form1\\:tabSet1\\:one\\:btnComentarioCancelarAvance").hide();
							$("#form1\\:tabSet1\\:one\\:btnComentarioIrAFaseEspecial").hide();
							$("#form1\\:tabSet1\\:one\\:btnComentarioCierre").hide();

							$("#comentario-form").dialog("open");
						});
						$("#form1\\:tabSet1\\:one\\:btnCancelarAvanceFase").mousedown(function() {
							tips.text("Agregar un comentario por la cancelacion del avance de la fase.");
							$("#comentario-form").dialog("option", "height", 300);
							$("#form1\\:tabSet1\\:one\\:btnComentarioCancelarAvance").show();
							$("#form1\\:tabSet1\\:one\\:btnComentarioRetroceso").hide();
							$("#form1\\:tabSet1\\:one\\:btnComentarioIrAFaseEspecial").hide();
							$("#form1\\:tabSet1\\:one\\:btnComentarioCierre").hide();

							$("#comentario-form").dialog("open");
						});
						$("#form1\\:tabSet1\\:one\\:btnCerrarExpediente").mousedown(function() {
							tips.text("Agregar un comentario por el cierre del expediente.");
							$("#comentario-form").dialog("option", "height", 260);
							$("#form1\\:tabSet1\\:one\\:btnComentarioCierre").show();
							$("#form1\\:tabSet1\\:one\\:btnComentarioCancelarAvance").hide();
							$("#form1\\:tabSet1\\:one\\:btnComentarioIrAFaseEspecial").hide();
							$("#form1\\:tabSet1\\:one\\:btnComentarioRetroceso").hide();

							$("#comentario-form").dialog("open");
						});
					}

					function focusearTfInteresadoSeleccionada() {
						$("#form1\\:tabSet1:one:tfPersona").focus();
					}

					function actualizarTip(texto) {
						var $tips = $(".validateTips");
						$tips.text(texto).addClass("ui-state-highlight");
						setTimeout(function() {
							$tips.removeClass("ui-state-highlight", 1500);
						}, 500);
					}

					function chequearComentario(comp, min, max) {
						if(comp.val().length > max || comp.val().length < min) {
							comp.addClass("ui-state-error");
							actualizarTip("El tamaño del comentario debe ser mayor a " + min + " y menor a " + max + " caracteres.");
							return false;
						} else {
							return true;
						}
					}

					function guardarComentario(boton) {
						var funcionActual = boton.onclick;

						var funcionNueva = (function onClick(e) {
							var valido = true;
							var $comentario = $("#form1\\:tabSet1\\:one\\:taComentario");
							$comentario.removeClass("ui-state-error");
							valido = valido && chequearComentario($comentario, 10, 200);
							if(valido) {
								funcionActual.call();

								$("#form1\\:tabSet1\\:one\\:taComentario").val("");

								$("#comentario-form").dialog("close");
							}
						});

						boton.onclick = funcionNueva;
					}

					function cancelarComentario() {
						$("#comentario-form").dialog("close");
					}

					function abrirModalSeguridad() {
						if(!$("#form1\\:messageGroup").is(':visible')) {
							$("#seguridad-modal-form").dialog("open");
							$("#form1\\:pfContraseniaExpediente").val("");
							$("#form1\\:pfContraseniaExpediente").focus();
						}
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{expedientes$ABMExpediente$ABMExpediente.body1}" focus="form1:taAsunto" id="body1"
				onLoad="parent.footer.location.reload();Init();Init2();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{expedientes$ABMExpediente$ABMExpediente.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{expedientes$ABMExpediente$ABMExpediente.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{expedientes$ABMExpediente$ABMExpediente.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="4">
										<h:panelGrid styleClass="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px;width: 100%;">
											<h:panelGroup styleClass="div" layout="block">
												<table border="0" cellspacing="0" cellpadding="0">
													<tr>
														<td width="250px" align="left">
															<h:outputText style="font-weight:bold" value="Procedimiento" />
														</td>
														<td align="center" width="80%">
															<ui:label binding="#{expedientes$ABMExpediente$ABMExpediente.lblProcedimiento}" id="lblProcedimiento" styleClass="label"
																style="font-size: 12pt" />
															<ui:label binding="#{expedientes$ABMExpediente$ABMExpediente.lblEstadoProcedimiento}" id="lblEstadoProcedimiento"
																styleClass="label" style="font-size: 12pt; font-weight: bold" />
														</td>
														<td width="250px" align="right">
															<ui:dropDown binding="#{expedientes$ABMExpediente$ABMExpediente.ddProcedimiento}" id="ddProcedimiento"
																items="#{expedientes$ABMExpediente$ABMExpediente.ddProcedimientoDefaultOptions.options}" styleClass="textField" />
														</td>
														<td>
															<a4j:commandButton action="#{expedientes$ABMExpediente$ABMExpediente.btnSeleccionarProcedimiento_action}"
																binding="#{expedientes$ABMExpediente$ABMExpediente.btnSeleccionarProcedimiento}" id="btnSeleccionarProcedimiento"
																value="Seleccionar" styleClass="btnAjax" reRender="form1" oncomplete="cargarComportamientoJQuery();" />
														</td>
													</tr>
												</table>
											</h:panelGroup>
										</h:panelGrid>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="4">
										<ui:tabSet binding="#{expedientes$ABMExpediente$ABMExpediente.tabSet1}" id="tabSet1" mini="true" lite="true">
											<ui:tab id="one" binding="#{expedientes$ABMExpediente$ABMExpediente.tabOne}" text="Expediente">
												<table align="center" width="70%">
													<tr>
														<td>
															<ui:label binding="#{expedientes$ABMExpediente$ABMExpediente.lblResponsable}" id="lblResponsable" styleClass="label"
																text="Responsable" />
														</td>
														<td colspan="2">
															<h:panelGrid columns="2">
																<h:panelGrid cellpadding="0" cellspacing="0" columns="1">
																	<a4j:repeat var="cadaArea" value="#{expedientes$ABMExpediente$ABMExpediente.listaAreasResponsable}">
																		<ui:label text="#{cadaArea.entidadResponsable}" styleClass="label" style="font-weight:bold" />
																		<br />
																	</a4j:repeat>
																</h:panelGrid>
																<h:panelGrid cellpadding="0" cellspacing="0" columns="1">
																	<a4j:repeat var="cadaUsuario" value="#{expedientes$ABMExpediente$ABMExpediente.listaUsuariosResponsable}">
																		<ui:label text="#{cadaUsuario.entidadResponsable}" styleClass="label" style="font-weight:bold" />
																		<br />
																	</a4j:repeat>
																</h:panelGrid>
															</h:panelGrid>
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<hr />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{expedientes$ABMExpediente$ABMExpediente.lblNroRegistro}" for="tfNroRegistro" id="lblNroRegistro"
																styleClass="label" text="Nº de Registro" />
														</td>
														<td>
															<ui:textField binding="#{expedientes$ABMExpediente$ABMExpediente.tfNroRegistro}" columns="16" id="tfNroRegistro"
																styleClass="textField" disabled="true" />
														</td>
														<td align="right" nowrap="true">
															<ui:label binding="#{expedientes$ABMExpediente$ABMExpediente.lblFechaRegistro}" for="tfFechaRegistro" id="lblFechaRegistro"
																styleClass="label" text="Fecha de Registro" />
														</td>
														<td>
															<ui:textField disabled="true" binding="#{expedientes$ABMExpediente$ABMExpediente.tfFechaRegistro}" columns="10"
																id="tfFechaRegistro" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td>
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<div class="div" style="width: 780px; height: 15px; width: 150px;">Datos del Interesado</div>
														</td>
													</tr>
													<tr>
														<td colspan="4" style="padding-left: 1px; padding-right: 1px;">
															<table class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 100%;">
																<tr>
																	<td align="right" nowrap="nowrap">
																		<ui:label binding="#{expedientes$ABMExpediente$ABMExpediente.lblPersona}" for="tfPersona" id="label2" styleClass="label"
																			text="Interesado" />
																	</td>
																	<td colspan="3" nowrap="nowrap">
																		<ui:panelGroup id="pgComponentesInteresado">
																			<ui:textField binding="#{expedientes$ABMExpediente$ABMExpediente.tfPersona}" columns="40" id="tfPersona"
																				styleClass="#{expedientes$ABMExpediente$ABMExpediente.hayPersona ? 'textFieldDisabled' : 'textField'}"
																				disabled="#{expedientes$ABMExpediente$ABMExpediente.hayPersona}" />
																			<ui:button action="#{expedientes$ABMExpediente$ABMExpediente.btnSeleccionarPersonaFisica_action}"
																				binding="#{expedientes$ABMExpediente$ABMExpediente.btnSeleccionarPersonaFisica}" escape="false"
																				id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Fisica" />
																			<ui:button action="#{expedientes$ABMExpediente$ABMExpediente.btnSeleccionarPersonaJuridica_action}"
																				binding="#{expedientes$ABMExpediente$ABMExpediente.btnSeleccionarPersonaJuridica}" escape="false"
																				id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar Persona Jurídica" />
																			<a4j:commandButton action="#{expedientes$ABMExpediente$ABMExpediente.btnLimpiarPersona_action}"
																				binding="#{expedientes$ABMExpediente$ABMExpediente.btnLimpiarPersona}" title="Limpiar" id="btnLimpiarPersona"
																				styleClass="buttonLimpiarAjax" reRender="tfPersona, tfNroTelefono, tfNroCelular, tfEmail"
																				oncomplete="cargarComportamientoJQuery(); focusearTfInteresadoSeleccionada();" />
																		</ui:panelGroup>
																	</td>
																</tr>
																<tr>
																	<td colspan="4">
																		<table id="tablaDatosInteresado">
																			<tr>
																				<td nowrap="nowrap">
																					<ui:label binding="#{expedientes$ABMExpediente$ABMExpediente.lblNroTelefono}" for="tfNroTelefono" id="lblNroTelefono"
																						styleClass="label" text="Nº de Telefono" />
																				</td>
																				<td>
																					<ui:textField binding="#{expedientes$ABMExpediente$ABMExpediente.tfNroTelefono}" columns="20" id="tfNroTelefono"
																						styleClass="textField" disabled="#{!expedientes$ABMExpediente$ABMExpediente.hayPersona}">
																						<a4j:support event="onBlur" oncomplete="cargarComportamientoJQuery();"
																							actionListener="#{expedientes$ABMExpediente$ABMExpediente.eventoActualizarDatosInteresado(evento)}" />
																					</ui:textField>
																				</td>
																				<td nowrap="nowrap">
																					<ui:label binding="#{expedientes$ABMExpediente$ABMExpediente.lblNroCelular}" for="tfNroCelular" id="lblNroCelular"
																						styleClass="label" text="Nº de Celular" />
																				</td>
																				<td>
																					<ui:textField binding="#{expedientes$ABMExpediente$ABMExpediente.tfNroCelular}" columns="20" id="tfNroCelular"
																						styleClass="textField" disabled="#{!expedientes$ABMExpediente$ABMExpediente.hayPersona}">
																						<a4j:support event="onBlur" oncomplete="cargarComportamientoJQuery();"
																							actionListener="#{expedientes$ABMExpediente$ABMExpediente.eventoActualizarDatosInteresado(evento)}" />
																					</ui:textField>
																				</td>
																				<td nowrap="nowrap">
																					<ui:label binding="#{expedientes$ABMExpediente$ABMExpediente.lblEmail}" for="tfEmail" id="lblEmail" styleClass="label"
																						text="E-mail" />
																				</td>
																				<td>
																					<ui:textField binding="#{expedientes$ABMExpediente$ABMExpediente.tfEmail}" columns="30" id="tfEmail"
																						styleClass="textField" disabled="#{!expedientes$ABMExpediente$ABMExpediente.hayPersona}">
																						<a4j:support event="onBlur" oncomplete="cargarComportamientoJQuery();"
																							actionListener="#{expedientes$ABMExpediente$ABMExpediente.eventoActualizarDatosInteresado(evento)}" />
																					</ui:textField>
																				</td>
																			</tr>
																		</table>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<hr />
														</td>
													</tr>
													<tr>
														<td colspan="4" align="left">
															<ui:label binding="#{expedientes$ABMExpediente$ABMExpediente.lblAsunto}" for="taAsunto" id="lblAsunto" styleClass="label"
																text="Asunto" />
														</td>
													</tr>
													<tr>
														<td colspan="4" align="left">
															<ui:textArea binding="#{expedientes$ABMExpediente$ABMExpediente.taAsunto}" columns="112" rows="8" id="taAsunto"
																styleClass="textArea" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<hr />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<h:panelGrid columns="10" width="100%">
																<ui:label binding="#{expedientes$ABMExpediente$ABMExpediente.panelPlazoExpediente.lblFechaInicioPlazo}"
																	for="tfFechaInicioPlazo" id="lblFechaInicioPlazo" styleClass="label" text="Inicio Plazo" />
																<ui:textField columns="11" disabled="true"
																	binding="#{expedientes$ABMExpediente$ABMExpediente.panelPlazoExpediente.tfFechaInicioPlazo}" styleClass="textField"
																	id="tfFechaInicioPlazo" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
																<ui:label binding="#{expedientes$ABMExpediente$ABMExpediente.panelPlazoExpediente.lblCantidadDias}" for="tfCantDias"
																	id="lblCantDias" styleClass="label" text="Días De Plazo" />
																<ui:textField disabled="true" binding="#{expedientes$ABMExpediente$ABMExpediente.panelPlazoExpediente.tfCantidadDias}"
																	columns="3" id="tfCantDias" styleClass="textField" />
																<ui:label binding="#{expedientes$ABMExpediente$ABMExpediente.panelPlazoExpediente.lblCantidadDiasRestantes}"
																	for="tfCantDiasRestantes" id="lblCantDiasRestantes" styleClass="label" text="Días Restantes" />
																<ui:textField disabled="true"
																	binding="#{expedientes$ABMExpediente$ABMExpediente.panelPlazoExpediente.tfCantidadDiasRestantes}" columns="3"
																	id="tfCantDiasRestantes" styleClass="textField" />
																<ui:label binding="#{expedientes$ABMExpediente$ABMExpediente.panelPlazoExpediente.lblFechaFinPlazo}" for="tfFechaFinPlazo"
																	id="lblFechaFinPlazo" styleClass="label" text="Fin Plazo" />
																<ui:textField columns="11" disabled="true"
																	binding="#{expedientes$ABMExpediente$ABMExpediente.panelPlazoExpediente.tfFechaFinPlazo}" styleClass="textField"
																	id="tfFechaFinPlazo" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
																<ui:checkbox disabled="true" binding="#{expedientes$ABMExpediente$ABMExpediente.panelPlazoExpediente.chDiasCorridos}"
																	id="chDiasCorridos" label="días corridos"></ui:checkbox>
															</h:panelGrid>
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<hr />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<h:panelGrid styleClass="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px;width: 100%;">
																<h:panelGroup styleClass="div" layout="block">
																	<table border="0" cellspacing="0" cellpadding="0">
																		<tr>
																			<td width="480px" align="left" nowrap="nowrap">
																				<h:outputText style="font-weight:bold" value="Fases del Expediente" />
																			</td>
																			<td align="right" nowrap="nowrap">
																				<ui:panelGroup binding="#{expedientes$ABMExpediente$ABMExpediente.pgBotonesFases}" id="pgBotonesFases">
																					<ui:label binding="#{expedientes$ABMExpediente$ABMExpediente.panelFases.lbFasesEspeciales}" id="lbFasesEspeciales"
																						styleClass="label" text="Fases Especiales" />
																					<ui:dropDown binding="#{expedientes$ABMExpediente$ABMExpediente.panelFases.ddFasesEspeciales}" id="ddFasesEspeciales"
																						items="#{expedientes$ABMExpediente$ABMExpediente.panelFases.ddFasesEspecialesOptions.options}" styleClass="textField" />
																					<a4j:commandButton title="Ir a Fase Especial"
																						action="#{expedientes$ABMExpediente$ABMExpediente.btnIrAFaseEspecial_action}"
																						binding="#{expedientes$ABMExpediente$ABMExpediente.panelFases.btnIrAFaseEspecial}" id="btnIrAFaseEspecial"
																						styleClass="btnAjax" reRender="opFases, pgBotonesFases" value="Ir a Fase" oncomplete="cargarComportamientoJQuery();" />
																					<ui:staticText binding="#{expedientes$ABMExpediente$ABMExpediente.panelFases.staticText1}" escape="false"
																						id="staticText1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
																					<a4j:commandButton title="Cancelar avance Fase"
																						binding="#{expedientes$ABMExpediente$ABMExpediente.panelFases.btnCancelarAvanceFase}" id="btnCancelarAvanceFase"
																						styleClass="btnAjax" reRender="tableTramite, opFases, pgBotonesFases" value="Cancelar avance Fase"
																						oncomplete="cargarComportamientoJQuery();" />
																					<a4j:commandButton title="Retroceder Fase Activa"
																						binding="#{expedientes$ABMExpediente$ABMExpediente.panelFases.btnRetrocederFase}" id="btnRetrocederFase"
																						styleClass="btnAjax" reRender="opFases, pgBotonesFases" value="Retroceder Fase"
																						oncomplete="cargarComportamientoJQuery();" />
																					<a4j:commandButton title="Avanzar Fase Activa"
																						binding="#{expedientes$ABMExpediente$ABMExpediente.panelFases.btnAvanzarFase}" id="btnAvanzarFase"
																						styleClass="btnAjax" action="#{expedientes$ABMExpediente$ABMExpediente.btnAvanzarFase_action}"
																						reRender="opFases, pgBotonesFases, pgComponentesInteresado, tableTramite" value="Avanzar Fase"
																						oncomplete="cargarComportamientoJQuery();" />
																					<a4j:commandButton binding="#{expedientes$ABMExpediente$ABMExpediente.panelFases.btnCerrarExpediente}"
																						id="btnCerrarExpediente" styleClass="btnAjax" value="Cerrar Expediente" oncomplete="cargarComportamientoJQuery();" />
																				</ui:panelGroup>
																			</td>
																		</tr>
																		<tr>
																			<td>
																				<div id="comentario-form" title="Comentario">
																					<table>
																						<tr>
																							<td colspan="2">
																								<p class="validateTips" />
																							</td>
																						</tr>
																						<tr>
																							<td colspan="2">
																								<ui:textArea id="taComentario" binding="#{expedientes$ABMExpediente$ABMExpediente.taComentario}"
																									style="resize: none;" styleClass="text ui-widget-content ui-corner-all" columns="36" rows="6" />
																							</td>
																						</tr>
																						<tr>
																							<td>
																								<br />
																							</td>
																						</tr>
																						<tr>
																							<td align="center" colspan="2">
																								<a4j:commandButton id="btnComentarioIrAFaseEspecial"
																									binding="#{expedientes$ABMExpediente$ABMExpediente.btnComentarioIrAFaseEspecial}" value="Guardar"
																									onmousedown="guardarComentario(this);"
																									action="#{expedientes$ABMExpediente$ABMExpediente.btnIrAFaseEspecial_action}"
																									reRender="opFases, pgBotonesFases, gpBotones" />
																								<a4j:commandButton id="btnComentarioRetroceso"
																									binding="#{expedientes$ABMExpediente$ABMExpediente.btnComentarioRetroceso}" value="Guardar"
																									onmousedown="guardarComentario(this);" action="#{expedientes$ABMExpediente$ABMExpediente.btnRetrocederFase_action}"
																									reRender="opFases, pgBotonesFases, gpBotones" />
																								<a4j:commandButton id="btnComentarioCancelarAvance"
																									binding="#{expedientes$ABMExpediente$ABMExpediente.btnComentarioCancelarAvance}" value="Guardar"
																									onmousedown="guardarComentario(this);"
																									action="#{expedientes$ABMExpediente$ABMExpediente.btnCancelarAvanceFase_action}"
																									reRender="opFases, pgBotonesFases, gpBotones" />
																								<a4j:commandButton id="btnComentarioCierre" binding="#{expedientes$ABMExpediente$ABMExpediente.btnComentarioCierre}"
																									value="Guardar" onmousedown="guardarComentario(this);"
																									action="#{expedientes$ABMExpediente$ABMExpediente.btnCerrarExpediente_action}"
																									reRender="opFases, pgBotonesFases, gpBotones, lblEstadoProcedimiento" />
																								<a4j:commandButton id="btnCancelarComentario"
																									binding="#{expedientes$ABMExpediente$ABMExpediente.btnCancelarComentario}" value="Cancelar"
																									onmousedown="cancelarComentario();" />
																							</td>
																						</tr>
																					</table>
																				</div>
																			</td>
																		</tr>
																	</table>
																</h:panelGroup>
																<h:panelGrid width="100%">
																	<a4j:outputPanel id="opFases" ajaxRendered="true" layout="block"
																		binding="#{expedientes$ABMExpediente$ABMExpediente.panelFases.panelGridFase}" />
																	<hr></hr>
																	<tr>
																		<td colspan="2">
																			<ui:label binding="#{expedientes$ABMExpediente$ABMExpediente.lblResponsable}" id="lblResponsableFaseActual"
																				styleClass="label" text="Responsable" />
																			<h:panelGrid columns="2">
																				<h:panelGrid cellpadding="0" cellspacing="0" columns="1">
																					<a4j:repeat var="cadaArea" value="#{expedientes$ABMExpediente$ABMExpediente.listaAreasResponsableFaseActual}">
																						<ui:label text="#{cadaArea.entidadResponsable}" styleClass="label" style="font-weight:bold" />
																						<br />
																					</a4j:repeat>
																				</h:panelGrid>
																				<h:panelGrid cellpadding="0" cellspacing="0" columns="1">
																					<a4j:repeat var="cadaUsuario" value="#{expedientes$ABMExpediente$ABMExpediente.listaUsuariosResponsableFaseActual}">
																						<ui:label text="#{cadaUsuario.entidadResponsable}" styleClass="label" style="font-weight:bold" />
																						<br />
																					</a4j:repeat>
																				</h:panelGrid>
																			</h:panelGrid>
																		</td>
																	</tr>
																	<hr></hr>
																	<h:panelGrid columns="10" width="100%">
																		<ui:label binding="#{expedientes$ABMExpediente$ABMExpediente.panelFases.panelPlazoExpediente.lblFechaInicioPlazo}"
																			for="pFasestfFechaInicioPlazo" id="pFaseslblFechaInicioPlazo" styleClass="label" text="Inicio Plazo" />
																		<ui:textField columns="11" disabled="true"
																			binding="#{expedientes$ABMExpediente$ABMExpediente.panelFases.panelPlazoExpediente.tfFechaInicioPlazo}"
																			styleClass="textField" id="pFasestfFechaInicioPlazo" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
																		<ui:label binding="#{expedientes$ABMExpediente$ABMExpediente.panelFases.panelPlazoExpediente.lblCantidadDias}"
																			for="pFasestfCantDias" id="pFaseslblCantDias" styleClass="label" text="Días De Plazo" />
																		<ui:textField disabled="true"
																			binding="#{expedientes$ABMExpediente$ABMExpediente.panelFases.panelPlazoExpediente.tfCantidadDias}" columns="3"
																			id="pFasestfCantDias" styleClass="textField" />
																		<ui:label binding="#{expedientes$ABMExpediente$ABMExpediente.panelFases.panelPlazoExpediente.lblCantidadDiasRestantes}"
																			for="pFasestfCantDiasRestantes" id="pFaseslblCantDiasRestantes" styleClass="label" text="Días Restantes" />
																		<ui:textField disabled="true"
																			binding="#{expedientes$ABMExpediente$ABMExpediente.panelFases.panelPlazoExpediente.tfCantidadDiasRestantes}" columns="3"
																			id="pFasestfCantDiasRestantes" styleClass="textField" />
																		<ui:label binding="#{expedientes$ABMExpediente$ABMExpediente.panelFases.panelPlazoExpediente.lblFechaFinPlazo}"
																			for="pFasestfFechaFinPlazo" id="pFaseslblFechaFinPlazo" styleClass="label" text="Fin Plazo" />
																		<ui:textField columns="11" disabled="true"
																			binding="#{expedientes$ABMExpediente$ABMExpediente.panelFases.panelPlazoExpediente.tfFechaFinPlazo}"
																			styleClass="textField" id="pFasestfFechaFinPlazo" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
																		<ui:checkbox disabled="true"
																			binding="#{expedientes$ABMExpediente$ABMExpediente.panelFases.panelPlazoExpediente.chDiasCorridos}"
																			id="pFaseschDiasCorridos" label="días corridos"></ui:checkbox>
																		<ui:button action="#{expedientes$ABMExpediente$ABMExpediente.btnAgregarExtensionFase_action}"
																			binding="#{expedientes$ABMExpediente$ABMExpediente.btnAgregarExtensionFase}" id="btnAgregarExtensionFase"
																			styleClass="button" text="Agregar extensión Fase" />
																	</h:panelGrid>
																</h:panelGrid>
															</h:panelGrid>
														</td>
													</tr>
													<tr>
														<td colspan="4"></td>
													</tr>
													<tr>
														<td colspan="4">
															<h:panelGrid styleClass="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px;width: 100%;">
																<h:panelGroup styleClass="div" layout="block">
																	<table border="0" cellspacing="0" cellpadding="0">
																		<tr>
																			<td width="480px" align="left">
																				<h:outputText style="font-weight:bold" value="Trámites" />
																			</td>
																			<td align="right" nowrap="nowrap">
																				<ui:panelGroup binding="#{expedientes$ABMExpediente$ABMExpediente.gpBotones}" id="gpBotones">
																					<a4j:commandButton action="#{expedientes$ABMExpediente$ABMExpediente.btnAgregarExtensionTramite_action}"
																						binding="#{expedientes$ABMExpediente$ABMExpediente.btnAgregarExtensionTramite}" id="btnAgregarExtensionTramite"
																						styleClass="btnAjax" value="Agregar extensión Trámite" />
																					<a4j:commandButton action="#{expedientes$ABMExpediente$ABMExpediente.btnModificarTramite_action}"
																						binding="#{expedientes$ABMExpediente$ABMExpediente.panelFases.tableTramite.btnModificarTramite}" id="btnAbrirTramite"
																						styleClass="btnAjax" value="Modificar Trámite" />
																					<a4j:commandButton action="#{expedientes$ABMExpediente$ABMExpediente.btnConsultarTramite_action}"
																						binding="#{expedientes$ABMExpediente$ABMExpediente.panelFases.tableTramite.btnConsultarTramite}"
																						id="btnConsultarTramite" styleClass="btnAjax" value="Consultar Trámite" />
																				</ui:panelGroup>
																			</td>
																		</tr>
																	</table>
																</h:panelGroup>
																<ui:table augmentTitle="false" binding="#{expedientes$ABMExpediente$ABMExpediente.panelFases.tableTramite.table}"
																	id="tableTramite">
																	<ui:tableRowGroup binding="#{expedientes$ABMExpediente$ABMExpediente.panelFases.tableTramite.tableRowGroup1}"
																		id="tableRowGroup1" sourceData="#{expedientes$ABMExpediente$ABMExpediente.panelFases.tableTramite.objectListDataProvider}"
																		sourceVar="currentRow">
																		<ui:tableColumn align="center" binding="#{expedientes$ABMExpediente$ABMExpediente.panelFases.tableTramite.tableColumn1}"
																			id="tableColumn1" valign="middle" width="10">
																			<ui:radioButton binding="#{expedientes$ABMExpediente$ABMExpediente.panelFases.tableTramite.radioButton1}"
																				id="radioButton1" label="" name="#{expedientes$ABMExpediente$ABMExpediente.panelFases.tableTramite.nombreButtonGroup}"
																				selected="#{expedientes$ABMExpediente$ABMExpediente.panelFases.tableTramite.RBSelected}"
																				selectedValue="#{expedientes$ABMExpediente$ABMExpediente.panelFases.tableTramite.currentRow}" />
																		</ui:tableColumn>
																		<ui:tableColumn binding="#{expedientes$ABMExpediente$ABMExpediente.panelFases.tableTramite.tableColumn2}"
																			headerText="Nombre" id="tableColumn2" sort="plantilla" width="40">
																			<ui:staticText binding="#{expedientes$ABMExpediente$ABMExpediente.panelFases.tableTramite.staticText1}" id="staticText1"
																				text="#{currentRow.value['plantilla']}" />
																		</ui:tableColumn>
																		<ui:tableColumn headerText="Fecha Cierre" id="tableColumn4" sort="fechaFin" width="20">
																			<ui:staticText id="staticText4" text="#{currentRow.value['fechaFin']}"
																				converter="#{expedientes$ABMExpediente$ABMExpediente.panelFases.tableTramite.dateTimeConverter}" />
																		</ui:tableColumn>
																		<ui:tableColumn headerText="Estado" id="tCEstado" sort="estadoTramite" width="20">
																			<ui:staticText id="stEstado" text="#{currentRow.value['estadoTramite'].nombre}" />
																		</ui:tableColumn>
																		<ui:tableColumn headerText="Días restantes" id="tCDiasRestantes" width="20">
																			<ui:staticText id="stdiasRestantes"
																				text="#{expedientes$ABMExpediente$ABMExpediente.panelFases.tableTramite.diasRestantes}" />
																		</ui:tableColumn>
																	</ui:tableRowGroup>
																</ui:table>
															</h:panelGrid>
														</td>
													</tr>
												</table>
											</ui:tab>
											<ui:tab id="two" binding="#{expedientes$ABMExpediente$ABMExpediente.tabTwo}" text="Documentacion Presentada">
												<table align="center" width="70%">
													<tr>
														<td colspan="4">
															<h:panelGrid styleClass="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px;width: 100%;">
																<h:panelGroup styleClass="div" layout="block">
																	<h:outputText style="font-weight:bold" value="Documentación presentada" />
																</h:panelGroup>
																<ui:table binding="#{expedientes$ABMExpediente$ABMExpediente.tableDocPresentada}" id="tablaDocumentacionPresentada">
																	<ui:tableRowGroup binding="#{expedientes$ABMExpediente$ABMExpediente.trgDocPresentada}" id="trgDocPresentada"
																		sourceData="#{expedientes$ABMExpediente$ABMExpediente.ldpDocPresentada}" sourceVar="currentRow">
																		<ui:tableColumn id="tcNombre" valign="middle" width="50" headerText="Nombre">
																			<ui:staticText id="stNombre" text="#{currentRow.value['nombre']}" />
																		</ui:tableColumn>
																		<ui:tableColumn id="tcObservacion" valign="middle" width="100" headerText="Observación">
																			<ui:staticText id="stObservacion" text="#{currentRow.value['observacion']}" />
																		</ui:tableColumn>
																		<ui:tableColumn id="tcFasePresentacion" valign="middle" width="100" headerText="Fase de Presentación">
																			<ui:staticText id="stFasePresentacion" text="#{currentRow.value['tramite'].nodoPadre}" />
																		</ui:tableColumn>
																	</ui:tableRowGroup>
																</ui:table>
															</h:panelGrid>
														</td>
													</tr>
												</table>
											</ui:tab>
											<ui:tab id="three" binding="#{expedientes$ABMExpediente$ABMExpediente.tabThree}" text="Historial de Cambios">
												<table align="center" width="70%">
													<tr>
														<td colspan="4">
															<h:panelGrid styleClass="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px;width: 100%;">
																<h:panelGroup styleClass="div" layout="block">
																	<h:outputText style="font-weight:bold" value="Historial de Tramitación" />
																</h:panelGroup>
																<ui:table binding="#{expedientes$ABMExpediente$ABMExpediente.tablaHitos}" id="tablaHitos">
																	<ui:tableRowGroup binding="#{expedientes$ABMExpediente$ABMExpediente.tableRowGroup1}" id="tableRowGroup1"
																		sourceData="#{expedientes$ABMExpediente$ABMExpediente.ldpHitos}" sourceVar="currentRowHito">
																		<ui:tableColumn binding="#{expedientes$ABMExpediente$ABMExpediente.tableColumn1}" id="tableColumn1" valign="middle"
																			width="50">
																			<ui:staticText id="stHito" text="#{currentRowHito.value['stringHito']}" />
																		</ui:tableColumn>
																	</ui:tableRowGroup>
																</ui:table>
															</h:panelGrid>
														</td>
													</tr>
												</table>
											</ui:tab>
										</ui:tabSet>
									</td>
								</tr>
								<tr>
									<td>
										<div id="seguridad-modal-form" title="Seguridad">
											<table>
												<tr>
													<td>
														<ui:label id="lbContraseniaExpediente" binding="#{expedientes$ABMExpediente$ABMExpediente.lbContraseniaExpediente}"
															styleClass="label" text="Contraseña" for="pfContraseniaExpediente" />
													</td>
													<td>
														<ui:passwordField binding="#{expedientes$ABMExpediente$ABMExpediente.pfContraseniaExpediente}" columns="20"
															id="pfContraseniaExpediente" styleClass="textField"
															onKeyPress="if (event.keyCode == 13) document.getElementById('form1:btnGuardar').click();" />
													</td>
												</tr>
												<tr>
													<td>
														<br />
													</td>
												</tr>
												<tr>
													<td align="center" colspan="4">
														<ui:button action="#{expedientes$ABMExpediente$ABMExpediente.btnGuardar_action}"
															binding="#{expedientes$ABMExpediente$ABMExpediente.btnGuardar}" id="btnGuardar" text="Aceptar" styleClass="button" />
													</td>
												</tr>
											</table>
										</div>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<div>
											<a4j:outputPanel ajaxRendered="true">
												<ui:messageGroup binding="#{expedientes$ABMExpediente$ABMExpediente.messageGroup}" id="messageGroup" styleClass="grupoMsg" />
											</a4j:outputPanel>
										</div>
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="true">
										<a4j:commandButton binding="#{expedientes$ABMExpediente$ABMExpediente.btnGuardarAjax}" id="btnGuardarAjax" styleClass="btnAjax"
											oncomplete="abrirModalSeguridad();" action="#{expedientes$ABMExpediente$ABMExpediente.btnGuardarAjax_action}" />
										<ui:staticText binding="#{expedientes$ABMExpediente$ABMExpediente.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{expedientes$ABMExpediente$ABMExpediente.btnCancelar_action}"
											binding="#{expedientes$ABMExpediente$ABMExpediente.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{expedientes$ABMExpediente$ABMExpediente.hidIdPagina}" id="hidIdPagina"
						text="#{expedientes$ABMExpediente$ABMExpediente.idPagina}" />
					<ui:hiddenField binding="#{expedientes$ABMExpediente$ABMExpediente.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{expedientes$ABMExpediente$ABMExpediente.idSubSesion}" />
					<ui:script binding="#{expedientes$ABMExpediente$ABMExpediente.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>