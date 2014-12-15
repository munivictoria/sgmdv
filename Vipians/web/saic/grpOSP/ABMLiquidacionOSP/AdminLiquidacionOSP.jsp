<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" isELIgnored="false" />
	<f:view>
		<ui:page binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.page1}" id="page1">
			<ui:html binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.html1}" id="html1">
			<ui:head binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.head1}" id="head1">
				<ui:link binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP";

					function cargarComportamientoJQuery() {
						autoCompletarEnTextField("#form1:tfPersona", "persona", nombreBean, "setPersonaAutocompletar");
						autoCompletarEnTextField("#form1:tfParcela", "parcela", nombreBean, "setParcelaAutocompletar");
					}

					function focusearTfPersonaSeleccionada() {
						$("#form1\\:tfPersona").focus();
					}

					function focusearTfParcelaSeleccionada() {
						$("#form1\\:tfParcela").focus();
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.body1}" focus="form1:btnSeleccionarPersonaFisica" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td colspan="4"></td>
									</tr>
									<tr>
										<td align="left" style="padding-left: 40px">
											<ui:panelGroup binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.label2}" for="tfPersona" id="label2"
																styleClass="label" text="Persona" />
														</td>
														<td colspan="4">
															<ui:textField binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.tfPersona}" columns="40" id="tfPersona"
																styleClass="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.paginatedTable.filtro.persona != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.paginatedTable.filtro.persona != null}" />
															<ui:button action="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnSeleccionarPersonaFisica_action}"
																binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnSeleccionarPersonaFisica}" escape="false"
																id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar" />
															<ui:button action="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnSeleccionarPersonaJuridica_action}"
																binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnSeleccionarPersonaJuridica}" escape="false"
																id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersona" title="Limpiar"
																binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnLimpiarPersona}"
																action="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnLimpiarPersona_action}" styleClass="buttonLimpiarAjax"
																oncomplete="cargarComportamientoJQuery(); focusearTfPersonaSeleccionada();" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.label3}" for="tfCuadra" id="label3" styleClass="label"
																text="Cuadra" />
														</td>
														<td>
															<ui:textField binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.tfCuadra}" columns="40" disabled="true"
																id="tfCuadra" styleClass="textField" />
															<ui:button action="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnSeleccionarCuadra_action}"
																binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnSeleccionarCuadra}" escape="false" id="btnSeleccionarCuadra"
																mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarCuadra" reRender="form1:tfCuadra" title="Limpiar"
																binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnLimpiarCuadra}"
																action="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnLimpiarCuadra_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.label7}" for="tfCalle" id="label7" styleClass="label"
																text="Calle" />
														</td>
														<td>
															<ui:textField binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.tfCalle}" columns="40" disabled="true"
																id="tfCalle" styleClass="textField" />
															<ui:button action="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnSeleccionarCalle_action}"
																binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnSeleccionarCalle}" escape="false" id="btnSeleccionarCalle"
																mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarCalle" reRender="form1:tfCalle" title="Limpiar"
																binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnLimpiarCalle}"
																action="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnLimpiarCalle_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.label9}" id="label9" styleClass="label"
																text="Servicio OSP" />
														</td>
														<td>
															<ui:textField binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.tfServicioOSP}" columns="40" disabled="true"
																id="tfServicioOSP" styleClass="textField" />
															<ui:button action="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnSeleccionarServicioOSP_action}"
																binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnSeleccionarServicioOSP}" escape="false"
																id="btnSeleccionarServicioOSP" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarServicioOSP" reRender="tfServicioOSP" title="Limpiar"
																binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnLimpiarServicioOSP}"
																action="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnLimpiarServicioOSP_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.lblParcela}" for="tfParcela" id="lblParcela"
																styleClass="label" text="Parcela" />
														</td>
														<td>
															<ui:textField binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.tfParcela}" columns="40" id="tfParcela"
																styleClass="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.paginatedTable.filtro.parcela != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.paginatedTable.filtro.parcela != null}" />
															<ui:button action="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnSeleccionarParcela_action}"
																binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnSeleccionarParcela}" escape="false"
																id="btnSeleccionarParcela" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarParcela" reRender="form1:tfParcela" title="Limpiar"
																binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnLimpiarParcela}"
																action="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnLimpiarParcela_action}" styleClass="buttonLimpiarAjax"
																oncomplete="cargarComportamientoJQuery(); focusearTfParcelaSeleccionada();" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.label8}" id="label8" styleClass="label"
																text="Tipo de Servicio OSP" />
														</td>
														<td>
															<ui:dropDown binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.ddServicioMedido}" id="ddServicioMedido"
																items="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.ddServicioMedidoDefaultOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<table border="0" width="600">
																<tr>
																	<td>
																		<ui:label id="lblVacio" styleClass="label" text="" />
																	</td>
																	<td>
																		<ui:panelGroup binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.panelAtributoDinamico}"
																			id="panelAtributoDinamico">
																			<!-- AQUI VA LO QUE SE CREA DINAMICAMENTE -->
																		</ui:panelGroup>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td colspan="2">
															<hr />
														</td>
													</tr>
													<tr>
														<td colspan="2">
															<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.label10}" id="label10" styleClass="label2"
																text="Período" />
														</td>
													</tr>
													<tr>
														<td nowrap="nowrap" colspan="2">
															<table>
																<tbody>
																	<tr>
																		<td align="center" nowrap="nowrap">
																			<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.lblAnio}" for="ddAnios" id="lblAnio"
																				styleClass="label" text="Año" />
																			<ui:dropDown binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.ddAnios}" id="ddAnios" styleClass="textField"
																				items="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.ddAniosOptions.options}"
																				valueChangeListener="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.eventoSeleccionAnio(evento)}">
																				<a4j:support event="onChange" reRender="form1:ddCalendarios, form1:ddPeriodos, form1:ddCuotas" />
																			</ui:dropDown>
																		</td>
																		<td align="center" nowrap="nowrap">
																			<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.lblCalendarios}" for="ddCalendarios"
																				id="lblCalendarios" styleClass="label" text="Calendario" />
																			<ui:dropDown binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.ddCalendarios}" id="ddCalendarios"
																				styleClass="textField" items="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.ddCalendariosOptions.options}"
																				valueChangeListener="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.eventoSeleccionCalendario(event)}">
																				<a4j:support event="onChange" reRender="form1:ddPeriodos, form1:ddCuotas" />
																			</ui:dropDown>
																		</td>
																		<td align="center" nowrap="nowrap">
																			<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.lblPeriodos}" for="ddPeriodos" id="lblPeriodos"
																				styleClass="label" text="Periodo" />
																			<ui:dropDown binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.ddPeriodos}" id="ddPeriodos"
																				styleClass="textField" items="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.ddPeriodosOptions.options}"
																				valueChangeListener="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.eventoSeleccionPeriodo(event)}">
																				<a4j:support event="onChange" reRender="form1:ddCuotas " />
																			</ui:dropDown>
																		</td>
																		<td align="center" nowrap="nowrap">
																			<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.lblCuotas}" for="ddCuotas" id="lblCuotas"
																				styleClass="label" text="Cuota" />
																			<ui:dropDown binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.ddCuotas}" id="ddCuotas" styleClass="textField"
																				items="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.ddCuotasOptions.options}" />
																		</td>
																	</tr>
																</tbody>
															</table>
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
															<hr />
															<br />
														</td>
													</tr>
												</table>
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.lblEstado}" styleClass="label" id="lblEstado"
																text="Estado" />
															<ui:dropDown binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.ddEstado}" styleClass="textField" id="ddEstado"
																items="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.ddEstadoDefaultOptions.options}" />
														</td>
														<td nowrap="nowrap">
															<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.lblTipo}" id="lblTipo" styleClass="label" text="Tipo" />
															<ui:dropDown binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.ddTipo}" id="ddTipo" styleClass="textField"
																items="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.ddTipoDefaultOptions.options}" />
														</td>
														<td nowrap="nowrap">
															<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.lblOmitir}" id="lblOmitir" styleClass="label"
																text="Omitir monto en cero" />
															<ui:checkbox binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.chkOmitir}" id="chkOmitir" selected="false" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
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
											<a4j:commandButton binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnBuscar}"
												action="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnReiniciar_action}"
												binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros"
												oncomplete="cargarComportamientoJQuery();" />
											<ui:staticText binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnCancelar_action}"
												binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<h:panelGrid columns="2" id="grpCargando" style="display: none; margin-left: 7px; padding-left: 10px" styleClass="msgLiquidacion">
								<ui:image binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.image1}" id="image1"
									url="/resources/imagenes/abm/wait_medium_1.gif" />
								<ui:staticText binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.staticText1}" escape="false" id="staticText1"
									styleClass="label2" text="     Generando archivos de impresión... Aguarde por favor." />
							</h:panelGrid>
							<script>
								<![CDATA[
								document.getElementById("form1:grpCargando").style.display = "none";

								function mostrarProgreso() {
									tabla = document.getElementById("form1:grpCargando");
									tabla.style.display = "block";
								}
								]]>
							</script>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.paginatedTable}" id="table1" styleClass="tablaPaginada">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.groupPanel1}" id="groupPanel1">
													<ui:panelGroup binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.gpBotones}" id="gpBotones">
														<ui:button action="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnSeleccionar_action}"
															binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
															text="Seleccionar" />
														<ui:staticText binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.stSeparadorSeleccionar}" escape="false"
															id="staticText6" />
														<ui:button action="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnSeleccionarTodas_action}"
															binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnSeleccionarTodas}" id="btnSeleccionarTodas"
															styleClass="button" text="Todas" toolTip="Selecciona todas" />
														<ui:button action="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnDeseleccionarTodas_action}"
															binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnDeseleccionarTodas}" id="btnDeseleccionarTodas"
															styleClass="button" text="Ninguna" toolTip="Deselecciona todas" />
														<ui:staticText escape="false" id="stSeparador2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnGenerarLiquidaciones_action}"
															binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnGenerarLiquidaciones}" id="btnGenerarLiquidaciones"
															styleClass="button" text="Generar Liquidaciones" />
														<ui:button action="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnConsultar_action}"
															binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnConsultar}" id="btnConsultar" styleClass="button"
															text="Consultar" />
														<ui:button action="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnImprimir_action}"
															binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnImprimir}" id="btnImprimir"
															onClick="newWindow = window.open('/Vipians/faces/ImpresionServlet', 'Reporte')" styleClass="button" text="Imprimir" />
														<ui:staticText binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.staticText15}" escape="false" id="staticText15"
															text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnReliquidar_action}"
															binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnReliquidar}" id="btnReliquidar" styleClass="button"
															text="Reliquidar" />
														<ui:button action="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnActualizarDeuda_action}"
															binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnActualizarDeuda}" id="btnActualizarDeuda" styleClass="button"
															text="Actualizar Deuda" />
														<ui:staticText escape="false" id="stSeparador3" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnExportar_action}"
															binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnExportar}" id="btnExportar" styleClass="button"
															text="Exportar" onClick="return exportarReporte()" />
													</ui:panelGroup>
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
								<tr>
									<td colspan="3">
										<div class="notifABM clrNota">
											<b>Nota: </b>La columna <i>Monto</i> es el resultado del importe de la tasa más el valor de los modificadores correspondientes.
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<ui:panelGroup id="pgPanelAdminLiquidaciones"
											rendered="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.renderPanelAdminLiquidaciones}">
											<ui:button action="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnMarcarPagada_action}"
												binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnMarcarPagada}" id="btnMarcarPagada" styleClass="button"
												text="Marcar Pagada" />
											<ui:button action="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnMarcarImpaga_action}"
												binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnMarcarImpaga}" id="btnMarcarImpaga" styleClass="button"
												text="Marcar Impaga" />
											<ui:staticText escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnModificarLiquidacion_action}"
												binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnModificarLiquidacion}" id="btnModificarLiquidacion"
												styleClass="button" text="Modificar" />
											<ui:staticText escape="false" id="stSeparador1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnEliminarLiquidacion_action}"
												binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnEliminarLiquidacion}" id="btnEliminarLiquidacion"
												styleClass="button" text="Eliminar" />
											<ui:staticText escape="false" id="stSeparadorBotonImprimirServidor" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<a4j:commandButton binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnImprimirEnServidor}"
												action="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.btnImprimirEnServidor_action}" id="btnImprimirEnServidor"
												value="Imprimir" styleClass="btnAjax" oncomplete="changeStyleAlIngresar()" />
										</ui:panelGroup>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:btnSeleccionarPersonaFisica').focus();
					</script>
					<ui:hiddenField binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.hidIdPagina}" id="hidIdPagina"
						text="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.idPagina}" />
					<ui:hiddenField binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.idSubSesion}" />
					<ui:script binding="#{saic$grpOSP$ABMLiquidacionOSP$AdminLiquidacionOSP.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
