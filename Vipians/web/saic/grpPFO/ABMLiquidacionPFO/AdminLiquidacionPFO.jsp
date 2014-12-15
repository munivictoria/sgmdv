<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" isELIgnored="false"
		deferredSyntaxAllowedAsLiteral="false" />
	<f:view>
		<ui:page binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.page1}" id="page1">
			<ui:html binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.html1}" id="html1">
			<ui:head binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.head1}" id="head1">
				<ui:link binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO";

					function cargarComportamientoJQuery() {
						autoCompletarEnTextField("#form1:tfPersona", "persona", nombreBean, "setPersonaAutocompletar");
					}

					function focusearTfPersonaSeleccionada() {
						$("#form1\\:tfPersona").focus();
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.body1}" focus="form1:btnSeleccionarPersonaFisica" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td colspan="4"></td>
									</tr>
									<tr>
										<td align="left" style="padding-left: 40px">
											<ui:panelGroup binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.label2}" for="tfPersona" id="label2"
																styleClass="label" text="Persona" />
														</td>
														<td>
															<ui:textField binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.tfPersona}" columns="40" id="tfPersona"
																styleClass="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.paginatedTable.filtro.persona != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.paginatedTable.filtro.persona != null}" />
															<ui:button action="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnSeleccionarPersonaFisica_action}"
																binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnSeleccionarPersonaFisica}" escape="false"
																id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar" />
															<ui:button action="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnSeleccionarPersonaJuridica_action}"
																binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnSeleccionarPersonaJuridica}" escape="false"
																id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersona" title="Limpiar"
																binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnLimpiarPersona}"
																action="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnLimpiarPersona_action}" styleClass="buttonLimpiarAjax"
																oncomplete="cargarComportamientoJQuery(); focusearTfPersonaSeleccionada();" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.label8}" id="label8" styleClass="label" text="Obra" />
														</td>
														<td>
															<ui:textField binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.tfObra}" columns="40" disabled="true" id="tfObra"
																styleClass="textField" />
															<ui:button action="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnSeleccionarObra_action}"
																binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnSeleccionarObra}" escape="false" id="btnSeleccionarObra"
																mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarObra" reRender="form1:tfObra" title="Limpiar"
																binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnLimpiarObra}"
																action="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnLimpiarObra_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.label3}" for="tfCuadra" id="label3" styleClass="label"
																text="Cuadra" />
														</td>
														<td>
															<ui:textField binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.tfCuadra}" columns="40" disabled="true"
																id="tfCuadra" styleClass="textField" />
															<ui:button action="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnSeleccionarCuadra_action}"
																binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnSeleccionarCuadra}" escape="false" id="btnSeleccionarCuadra"
																mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarCuadra" reRender="form1:tfCuadra" title="Limpiar"
																binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnLimpiarCuadra}"
																action="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnLimpiarCuadra_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.label7}" for="tfCalle" id="label7" styleClass="label"
																text="Calle" />
														</td>
														<td>
															<ui:textField binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.tfCalle}" columns="40" disabled="true"
																id="tfCalle" styleClass="textField" />
															<ui:button action="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnSeleccionarCalle_action}"
																binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnSeleccionarCalle}" escape="false" id="btnSeleccionarCalle"
																mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarCalle" reRender="form1:tfCalle" title="Limpiar"
																binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnLimpiarCalle}"
																action="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnLimpiarCalle_action}" styleClass="buttonLimpiarAjax" />
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
																		<ui:panelGroup binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.panelAtributoDinamico}"
																			id="panelAtributoDinamico">
																			<!-- AQUI VA LO QUE SE CREA DINAMICAMENTE -->
																		</ui:panelGroup>
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
														<td colspan="2" style="height: 15px">
															<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.label9}" id="label9" styleClass="label2"
																text="Período" />
														</td>
													</tr>
													<tr>
														<td nowrap="nowrap" colspan="2">
															<table>
																<tbody>
																	<tr>
																		<td align="center" nowrap="nowrap">
																			<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.lblAnios}" for="ddAnios" id="lblAnios"
																				styleClass="label" text="Año" />
																			<ui:dropDown binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.ddAnios}" id="ddAnios" styleClass="textField"
																				items="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.ddAniosOptions.options}"
																				valueChangeListener="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.eventoSeleccionAnio(evento)}">
																				<a4j:support event="onChange" reRender="form1:ddCalendarios, form1:ddPeriodos, form1:ddCuotas" />
																			</ui:dropDown>
																		</td>
																		<td align="center" nowrap="nowrap">
																			<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.lblCalendarios}" for="ddCalendarios"
																				id="lblCalendarios" styleClass="label" text="Calendario" />
																			<ui:dropDown binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.ddCalendarios}" id="ddCalendarios"
																				styleClass="textField" items="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.ddCalendariosOptions.options}"
																				valueChangeListener="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.eventoSeleccionCalendario(event)}">
																				<a4j:support event="onChange" reRender="form1:ddPeriodos, form1:ddCuotas" />
																			</ui:dropDown>
																		</td>
																		<td align="center" nowrap="nowrap">
																			<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.lblPeriodos}" for="ddPeriodos" id="lblPeriodos"
																				styleClass="label" text="Periodo" />
																			<ui:dropDown binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.ddPeriodos}" id="ddPeriodos"
																				styleClass="textField" items="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.ddPeriodosOptions.options}"
																				valueChangeListener="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.eventoSeleccionPeriodo(event)}">
																				<a4j:support event="onChange" reRender="form1:ddCuotas " />
																			</ui:dropDown>
																		</td>
																		<td align="center" nowrap="nowrap">
																			<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.lblCuotas}" for="ddCuotas" id="lblCuotas"
																				styleClass="label" text="Cuota" />
																			<ui:dropDown binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.ddCuotas}" id="ddCuotas" styleClass="textField"
																				items="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.ddCuotasOptions.options}" />
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
															<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.lblEstado}" id="lblEstado" styleClass="label"
																text="Estado" />
															<ui:dropDown binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.ddEstado}" id="ddEstado" styleClass="textField"
																items="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.ddEstadoDefaultOptions.options}" />
														</td>
														<td nowrap="nowrap">
															<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.lblTipo}" id="lblTipo" styleClass="label" text="Tipo" />
															<ui:dropDown binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.ddTipo}" id="ddTipo" styleClass="textField"
																items="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.ddTipoDefaultOptions.options}" />
														</td>
														<td nowrap="nowrap">
															<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.lblOmitir}" id="lblOmitir" styleClass="label"
																text="Omitir monto en cero" />
															<ui:checkbox binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.chkOmitir}" id="chkOmitir" selected="false" />
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
											<a4j:commandButton binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnBuscar}"
												action="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnReiniciar_action}"
												binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1 ,form1:stCantidadRegistros"
												oncomplete="cargarComportamientoJQuery();" />
											<ui:staticText binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnCancelar_action}"
												binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<h:panelGrid binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.grpCargando}" columns="2" id="grpCargando"
								style="display: none; margin-left: 7px; padding-left: 10px" styleClass="msgLiquidacion">
								<ui:image binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.image1}" id="image1"
									url="/resources/imagenes/abm/wait_medium_1.gif" />
								<ui:staticText binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.staticText1}" escape="false" id="staticText1"
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
										<ui:table binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.paginatedTable}" id="table1" styleClass="tablaPaginada">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.gpBotones}" id="gpBotones">
													<ui:button action="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnSeleccionar_action}"
														binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
														text="Seleccionar" />
													<ui:staticText binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.stSeparadorSeleccionar}" escape="false"
														id="staticText6" />
													<ui:button action="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnSeleccionarTodas_action}"
														binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnSeleccionarTodas}" id="btnSeleccionarTodas"
														styleClass="button" text="Todas" />
													<ui:button action="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnDeseleccionarTodas_action}"
														binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnDeseleccionarTodas}" id="btnDeSeleccionarTodas"
														styleClass="button" text="Ninguna" />
													<ui:staticText escape="false" id="stSeparador2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnGenerarLiquidaciones_action}"
														binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnGenerarLiquidaciones}" id="btnGenerarLiquidaciones"
														styleClass="button" text="Generar Liquidaciones" />
													<ui:button action="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnConsultar_action}"
														binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnConsultar}" disabled="false" id="btnConsultar"
														styleClass="button" text="Consultar" />
													<ui:button action="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnImprimir_action}"
														binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnImprimir}" id="btnImprimir"
														onClick="newWindow = window.open('/Vipians/faces/ImpresionServlet', 'Reporte')" styleClass="button" text="Imprimir" />
													<ui:staticText binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.staticText15}" escape="false" id="staticText15"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnReliquidar_action}"
														binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnReliquidar}" id="btnReliquidar" styleClass="button"
														text="Reliquidar" />
													<ui:button action="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnActualizarDeuda_action}"
														binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnActualizarDeuda}" id="btnActualizarDeuda" styleClass="button"
														text="Actualizar Deuda" />
													<ui:staticText escape="false" id="stSeparador3" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnExportar_action}"
														binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnExportar}" id="btnExportar" styleClass="button"
														text="Exportar" onClick="return exportarReporte()" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.stCantidadRegistros}" id="stCantidadRegistros"
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
											rendered="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.renderPanelAdminLiquidaciones}">
											<ui:button action="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnMarcarPagada_action}"
												binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnMarcarPagada}" id="btnMarcarPagada" styleClass="button"
												text="Marcar Pagada" />
											<ui:button action="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnMarcarImpaga_action}"
												binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnMarcarImpaga}" id="btnMarcarImpaga" styleClass="button"
												text="Marcar Impaga" />
											<ui:staticText escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnModificarLiquidacion_action}"
												binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnModificarLiquidacion}" id="btnModificarLiquidacion"
												styleClass="button" text="Modificar" />
											<ui:staticText escape="false" id="stSeparador1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnEliminarLiquidacion_action}"
												binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnEliminarLiquidacion}" id="btnEliminarLiquidacion"
												styleClass="button" text="Eliminar" />
											<ui:staticText escape="false" id="stSeparadorBotonImprimirServidor" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<a4j:commandButton binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnImprimirEnServidor}"
												action="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.btnImprimirEnServidor_action}" id="btnImprimirEnServidor"
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
					<ui:hiddenField binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.hidIdPagina}" id="hidIdPagina"
						text="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.idPagina}" />
					<ui:hiddenField binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.idSubSesion}" />
					<ui:script binding="#{saic$grpPFO$ABMLiquidacionPFO$AdminLiquidacionPFO.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>