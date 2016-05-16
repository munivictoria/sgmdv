<?xml version="1.0" encoding="UTF-8"?>
<!-- © Copyright 2015, CoDeSoft Todos los derechos reservados. -->
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui"
	xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.page1}" id="page1">
			<ui:html binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.html1}" id="html1">
			<ui:head binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.head1}" id="head1" title="Administración de Refinanciaciones">
				<ui:link binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.link1}" id="link1" url="/resources/stylesheet.css" />
				<style>
.ui-dialog .ui-state-error {
	padding: .3em;
}

.validateTips {
	border: 1px solid transparent;
	padding: 0.3em;
}
</style>
				<script>
					<![CDATA[
					var nombreBean = "excepciones$ABMRefinanciacion$AdminRefinanciacion";

					function cargarComportamientoJQuery() {
						autoCompletarEnTextField("#form1:tfPersona", "persona", nombreBean, "setPersonaAutocompletar");

						calendarioEnTextField("#form1:tfAlDiaDe");

						var fecha = $("#form1\\:tfAlDiaDe");
						var tips = $(".validateTips");
						$("#modal-form").dialog({
							autoOpen : false,
							height : 'auto',
							width : 'auto',
							resizable : false,
							modal : true,
							close : function() {
								fecha.val("").removeClass("ui-state-error");
								tips.text("").removeClass("ui-state-error");

								return false;
							}
						}).parent().appendTo($("#form1"));
					}

					function focusearTfPersona() {
						$("#form1\\:tfPersona").focus();
					}

					function actualizarTip(texto) {
						var $tips = $(".validateTips");
						$tips.text(texto).addClass("ui-state-highlight");
						setTimeout(function() {
							$tips.removeClass("ui-state-highlight", 1500);
						}, 500);
					}
					function validarFecha(fecha) {
						if (fecha.val().trim().length != 10) {
							fecha.addClass("ui-state-error");
							actualizarTip("Debe ingresar la fecha.");

							return false;
						} else {
							return true;
						}
					}

					function abrirModal() {
						if (!$("#form1\\:messageGroup").is(':visible')) {
							$("#form1\\:tfAlDiaDe").val($.datepicker.formatDate('dd/mm/yy', new Date()));
							$("#form1\\:tfSolicitante").val("Titular");

							$("#modal-form").dialog("open");

							$("#form1\\:tfMotivo").focus();
						}
					}

					function imprimirReporte(boton) {
						var funcionActual = boton.onclick;

						var funcionNueva = (function onClick(e) {
							var valido = true;
							var $fecha = $("#form1\\:tfAlDiaDe");

							$fecha.removeClass("ui-state-error");

							valido = valido && validarFecha($fecha);

							if (valido) {
								funcionActual.call();

								$("#modal-form").dialog("close");
							}
						});

						boton.onclick = funcionNueva;
					}

					function cerrarModal() {
						$("#modal-form").dialog("close");
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.body1}" focus="form1:tfNumeroTramite" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();" style="background-color: rgb(236, 236, 242); -rave-layout: grid"
				onKeyPress="return eventoByEnter(event,'btnBuscar')" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td colspan="2"></td>
									</tr>
									<tr>
										<td align="center">
											<ui:panelGroup binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.label1}" for="tfNumeroTramite" id="label1" styleClass="label" text="Nº de Refinanciación" />
														</td>
														<td>
															<ui:textField binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.tfNumeroTramite}" onKeyPress="return ValidarNum(event,this)" columns="10" id="tfNumeroTramite"
																styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label for="tfNumeroCuenta" id="lblNumeroCuenta" styleClass="label" text="Nº de Cuenta" />
														</td>
														<td>
															<ui:textField binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.tfNumeroCuenta}" onKeyPress="return ValidarNum(event,this)" columns="10" id="tfNumeroCuenta"
															 	styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.label2}" for="tfPersona" id="label2" styleClass="label" text="Contribuyente" />
														</td>
														<td>
															<ui:textField binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.tfPersonaSeleccionada}" columns="40" id="tfPersona"
																styleClass="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.paginatedTable.filtro.persona != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.paginatedTable.filtro.persona != null}" />
															<ui:button action="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnSeleccionarPersonaFisica_action}"
																binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnSeleccionarPersonaFisica}" escape="false" id="btnSeleccionarPersonaFisica" mini="true" styleClass="button"
																text="PF" toolTip="Seleccionar Persona Física" />
															<ui:button action="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnSeleccionarPersonaJuridica_action}"
																binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnSeleccionarPersonaJuridica}" escape="false" id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button"
																text="PJ" toolTip="Seleccionar Persona Jurídica" />
															<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersona" title="Limpiar" binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnLimpiarPersona}"
																action="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnLimpiarPersona_action}" styleClass="buttonLimpiarAjax"
																oncomplete="cargarComportamientoJQuery(); focusearTfPersona();" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.label3}" for="ddEstado" id="label3" styleClass="label" text="Estado" />
														</td>
														<td>
															<ui:dropDown binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.ddEstado}" id="ddEstado" styleClass="textField"
																items="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.ddEstadoDefaultOptions.options}" />
														</td>
													</tr>
													<tr>
														<td>
															<div id="modal-form" title="Datos Extras">
																<table>
																	<tr>
																		<td colspan="2">
																			<ui:label id="lblAlDiaDe" text="Al día de:" styleClass="label2" />
																		</td>
																		<td>
																			<ui:textField binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.tfAlDiaDe}" id="tfAlDiaDe" styleClass="textField" columns="10" />
																		</td>
																	</tr>
																	<tr>
																		<td colspan="2">
																			<ui:label id="lblSolicitante" text="Solicitado por:" styleClass="label2" />
																		</td>
																		<td>
																			<ui:textField binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.tfSolicitante}" id="tfSolicitante" styleClass="textField" columns="20" />
																		</td>
																	</tr>
																	<tr>
																		<td colspan="2">
																			<ui:label id="lblMotivo" text="Motivo:" styleClass="label2" />
																		</td>
																		<td>
																			<ui:textField binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.tfMotivo}" id="tfMotivo" styleClass="textField" columns="20" />
																		</td>
																	</tr>
																	<tr>
																		<td colspan="2">
																			<ui:label id="lblObservaciones" text="Observaciones:" styleClass="label2" />
																		</td>
																		<td>
																			<ui:textField binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.tfObservaciones}" id="tfObservaciones" styleClass="textField" columns="30" />
																		</td>
																	</tr>
																	<tr>
																		<td nowrap="nowrap" colspan="4">
																			<p class="validateTips" />
																		</td>
																	</tr>
																	<tr>
																		<td align="center" colspan="4">
																			<a4j:commandButton id="btnAceptarDatos" binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnAceptarDatos}" value="Imprimir"
																				onclick="newWindow = window.open('/Vipians/faces/ImpresionServlet', 'Reporte')"
																				action="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnImprimirReconocimientoDeuda_action}" styleClass="btnAjax" onmousedown="imprimirReporte(this);" />
																			<a4j:commandButton id="btnCerrarModal" binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnCerrarModal}" value="Cancelar" oncomplete="cerrarModal();"
																				styleClass="btnAjax" />
																		</td>
																	</tr>
																</table>
															</div>
														</td>
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
								</tbody>
								<tfoot>
									<tr>
										<td align="right" colspan="2">
											<a4j:commandButton binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnBuscar}" action="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnBuscar_action}" id="btnBuscar"
												value="Buscar" styleClass="btnAjax" reRender="form1:table1" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnReiniciar_action}" binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnReiniciar}"
												id="btnReiniciar" styleClass="btnAjax" value="Reiniciar" reRender="form1:pgParametros,form1:table1" oncomplete="cargarComportamientoJQuery();" />
											<ui:staticText binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.staticText2}" escape="false" id="staticText2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnCancelar_action}" binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnCancelar}" id="btnCancelar"
												styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.messageGroup}" id="messageGroup" showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<div class="divGeneral">
								<table class="general">
									<tr>
										<td colspan="4">
											<ui:table binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.paginatedTable}" styleClass="tablaPaginada" id="table1">
												<f:facet name="actionsTop">
													<ui:panelGroup binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.groupPanel1}" id="groupPanel1" style="">
														<ui:button action="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnSeleccionar_action}" binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnSeleccionar}"
															id="btnSeleccionar" styleClass="button" text="Seleccionar" />
														<ui:staticText binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.stSeparadorSeleccionar}" escape="false" id="staticText6" styleClass="barraSeparadoraVertical" />
														<ui:button action="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnGenerar_action}" binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnGenerar}" id="btnGenerar"
															styleClass="button" text="Generar" />
														<ui:button action="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnModificar_action}" binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnModificar}"
															id="btnModificar" styleClass="button" text="Modificar" />
														<ui:button action="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnEliminar_action}" binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnEliminar}" id="btnEliminar"
															styleClass="button" text="Eliminar" />
														<ui:button action="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnDarDeBaja_action}" binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnDarDeBaja}"
															id="btnDarDeBaja" styleClass="button" text="Dar de Baja" />
														<ui:button action="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnDarDeAlta_action}" binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnDarDeAlta}"
															id="btnDarDeAlta" styleClass="button" text="Recuperar" />
														<ui:staticText binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.stSeparadorAccion}" escape="false" id="staticText7" styleClass="barraSeparadoraVertical" />
														<ui:button action="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnConsultar_action}" binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnConsultar}"
															id="btnConsultar" styleClass="button" text="Consultar" />
														<ui:staticText text="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.textoSeparador}" escape="false" id="staticText10" styleClass="barraSeparadoraVertical" />
														<a4j:commandButton id="btnImprimirReconocimientoDeuda" binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnImprimirReconocimientoDeuda}"
															value="Constancia Plan de Pago al día" oncomplete="abrirModal();" styleClass="btnAjax"
															action="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnReconocimientoDeuda_action}" />
														<ui:button action="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnImprimirReporte_action}" binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnImprimirReporte}"
															onClick="newWindow = window.open('/Vipians/faces/ImpresionServlet', 'Reporte')" id="btnImprimirReporte" styleClass="button" text="Imprimir Reconocimiento de Deuda" />
														<ui:staticText escape="false" id="stSeparadorReporte" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnExportar_action}" binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnExportar}" id="btnExportar"
															styleClass="button" text="Exportar" onClick="return exportarReporte()" />
														<ui:staticText binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.paginatedTable.stSeparadorOrdenamiento}" styleClass="barraSeparadoraVertical" />
														<ui:imageHyperlink binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.paginatedTable.botonOrdenamiento}" />
													</ui:panelGroup>
												</f:facet>
											</ui:table>
										</td>
									</tr>
									<tr>
										<td align="left" colspan="2">
											<ui:label binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.lblEncontrados}" id="lblEncontrados" styleClass="label2" text="Registros Encontrados: " />
											<ui:staticText binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.stCantidadRegistros}" id="stCantidadRegistros" styleClass="staticText" />
										</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNumeroTramite').focus();
					</script>
					<ui:hiddenField binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.hidIdPagina}" id="hidIdPagina" text="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.idPagina}" />
					<ui:hiddenField binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.hidIdSubSesion}" id="hidIdSubSesion" text="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.idSubSesion}" />
					<ui:script binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.scriptFinal1}" id="scriptFinal1" url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>