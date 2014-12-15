<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" isELIgnored="false"
		deferredSyntaxAllowedAsLiteral="false" />
	<f:view>
		<ui:page binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.page1}" id="page1">
			<ui:html binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.html1}" id="html1">
			<ui:head binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.head1}" id="head1">
				<ui:link binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI";

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
			<ui:body binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.body1}" focus="form1:btnSeleccionarPersonaFisica" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td colspan="4"></td>
									</tr>
									<tr>
										<td align="left" style="padding-left: 40px">
											<ui:panelGroup binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.label2}" for="tfPersona" id="label2"
																styleClass="label" text="Persona" />
														</td>
														<td colspan="4">
															<ui:textField binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.tfPersona}" columns="40" id="tfPersona"
																styleClass="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.paginatedTable.filtro.persona != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.paginatedTable.filtro.persona != null}" />
															<ui:button action="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnSeleccionarPersonaFisica_action}"
																binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnSeleccionarPersonaFisica}" escape="false"
																id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Fisica" />
															<ui:button action="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnSeleccionarPersonaJuridica_action}"
																binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnSeleccionarPersonaJuridica}" escape="false"
																id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar Persona Juridica" />
															<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersonaFisica" title="Limpiar"
																binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnLimpiarPersona}"
																action="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnLimpiarPersona_action}" styleClass="buttonLimpiarAjax"
																oncomplete="cargarComportamientoJQuery(); focusearTfPersonaSeleccionada();" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.label1}" for="tfParcela" id="label1"
																styleClass="label" text="Parcela" />
														</td>
														<td align="left" colspan="4" nowrap="nowrap">
															<ui:textField binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.tfParcela}" columns="40" id="tfParcela"
																styleClass="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.paginatedTable.filtro.parcela != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.paginatedTable.filtro.parcela != null}" />
															<ui:button action="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnSeleccionarParcela_action}"
																binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnSeleccionarParcela}" escape="false"
																id="btnSeleccionarParcela" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarParcela" reRender="form1:tfParcela" title="Limpiar"
																binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnLimpiarParcela}"
																action="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnLimpiarParcela_action}" styleClass="buttonLimpiarAjax"
																oncomplete="cargarComportamientoJQuery(); focusearTfParcelaSeleccionada();" />
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
																		<ui:panelGroup binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.panelAtributoDinamico}"
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
														<td colspan="4">
															<ui:label binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.label3}" id="label3" styleClass="label2"
																text="Período" />
														</td>
													</tr>
													<tr>
														<td align="center" nowrap="nowrap">
															<ui:label binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.lblAnio}" for="ddAnios" id="lblAnio"
																styleClass="label" text="Año" />
															<ui:dropDown binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.ddAnios}" id="ddAnios" styleClass="textField"
																items="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.ddAniosOptions.options}"
																valueChangeListener="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.eventoSeleccionAnio(evento)}">
																<a4j:support event="onChange" reRender="form1:ddCalendarios, form1:ddPeriodos, form1:ddCuotas" />
															</ui:dropDown>
														</td>
														<td align="center" nowrap="nowrap">
															<ui:label binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.lblCalendarios}" for="ddCalendarios"
																id="lblCalendarios" styleClass="label" text="Calendario" />
															<ui:dropDown binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.ddCalendarios}" id="ddCalendarios"
																styleClass="textField" items="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.ddCalendariosOptions.options}"
																valueChangeListener="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.eventoSeleccionCalendario(event)}">
																<a4j:support event="onChange" reRender="form1:ddPeriodos, form1:ddCuotas" />
															</ui:dropDown>
														</td>
														<td align="center" nowrap="nowrap">
															<ui:label binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.lblPeriodos}" for="ddPeriodos" id="lblPeriodos"
																styleClass="label" text="Periodo" />
															<ui:dropDown binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.ddPeriodos}" id="ddPeriodos" styleClass="textField"
																items="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.ddPeriodosOptions.options}"
																valueChangeListener="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.eventoSeleccionPeriodo(event)}">
																<a4j:support event="onChange" reRender="form1:ddCuotas " />
															</ui:dropDown>
														</td>
														<td align="center" nowrap="nowrap">
															<ui:label binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.lblCuotas}" for="ddCuotas" id="lblCuotas"
																styleClass="label" text="Cuota" />
															<ui:dropDown binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.ddCuotas}" id="ddCuotas" styleClass="textField"
																items="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.ddCuotasOptions.options}" />
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
															<ui:label binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.lblEstado}" id="lblEstado" styleClass="label"
																text="Estado" />
															<ui:dropDown binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.ddEstado}" id="ddEstado" styleClass="textField"
																items="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.ddEstadoDefaultOptions.options}" />
														</td>
														<td nowrap="nowrap">
															<ui:label binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.lblTipo}" id="lblTipo" styleClass="label" text="Tipo" />
															<ui:dropDown binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.ddTipo}" id="ddTipo" styleClass="textField"
																items="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.ddTipoDefaultOptions.options}" />
														</td>
														<td nowrap="nowrap">
															<ui:label binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.lblOmitir}" id="lblOmitir" styleClass="label"
																text="Omitir monto en cero" />
															<ui:checkbox binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.chkOmitir}" id="chkOmitir" selected="false" />
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
											<a4j:commandButton binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnBuscar}"
												action="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnReiniciar_action}"
												binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros"
												oncomplete="cargarComportamientoJQuery();" />
											<ui:staticText binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnCancelar_action}"
												binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<h:panelGrid binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.grpCargando}" columns="2" id="grpCargando"
								style="display: none; margin-left: 7px; padding-left: 10px" styleClass="msgLiquidacion">
								<ui:image binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.image1}" id="image1"
									url="/resources/imagenes/abm/wait_medium_1.gif" />
								<ui:staticText binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.staticText4}" escape="false" id="staticText4"
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
										<ui:table binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.paginatedTable}" id="table1" styleClass="tablaPaginada">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.groupPanel1}" id="groupPanel1"
													separator="&lt;br/&gt;&lt;br/&gt;">
													<ui:panelGroup binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.gpBotones}" id="gpBotones">
														<ui:button action="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnSeleccionar_action}"
															binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
															text="Seleccionar" />
														<ui:staticText binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.stSeparadorSeleccionar}" escape="false"
															id="staticText6" />
														<ui:button action="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnSeleccionarTodas_action}"
															binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnSeleccionarTodas}" id="btnSeleccionarTodas"
															styleClass="button" text="Todas" toolTip="Selecciona todas" />
														<ui:button action="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnDeseleccionarTodas_action}"
															binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnDeseleccionarTodas}" id="btnDeseleccionarTodas"
															styleClass="button" text="Ninguna" toolTip="Deselecciona todas" />
														<ui:staticText escape="false" id="stSeparador2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnGenerarLiquidaciones_action}"
															binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnGenerarLiquidaciones}" id="btnGenerarLiquidaciones"
															styleClass="button" text="Generar Liquidaciones" />
														<ui:button action="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnConsultar_action}"
															binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnConsultar}" id="btnConsultar" styleClass="button"
															text="Consultar" />
														<ui:button action="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnImprimir_action}"
															binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnImprimir}" id="btnImprimir"
															onClick="newWindow = window.open('/Vipians/faces/ImpresionServlet', 'Reporte')" styleClass="button" text="Imprimir" />
														<ui:staticText binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.staticText16}" escape="false" id="staticText16"
															text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnReliquidar_action}"
															binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnReliquidar}" id="btnReliquidar" styleClass="button"
															text="Reliquidar" />
														<ui:button action="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnActualizarDeuda_action}"
															binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnActualizarDeuda}" id="btnActualizarDeuda" styleClass="button"
															text="Actualizar Deuda" />
														<ui:staticText escape="false" id="stSeparador3" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnExportar_action}"
															binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnExportar}" id="btnExportar" styleClass="button"
															text="Exportar" onClick="return exportarReporte()" />
													</ui:panelGroup>
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.stCantidadRegistros}" id="stCantidadRegistros"
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
											rendered="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.renderPanelAdminLiquidaciones}">
											<ui:button action="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnMarcarPagada_action}"
												binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnMarcarPagada}" id="btnMarcarPagada" styleClass="button"
												text="Marcar Pagada" />
											<ui:button action="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnMarcarImpaga_action}"
												binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnMarcarImpaga}" id="btnMarcarImpaga" styleClass="button"
												text="Marcar Impaga" />
											<ui:staticText escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnModificarLiquidacion_action}"
												binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnModificarLiquidacion}" id="btnModificarLiquidacion"
												styleClass="button" text="Modificar" />
											<ui:staticText escape="false" id="stSeparador1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnEliminarLiquidacion_action}"
												binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnEliminarLiquidacion}" id="btnEliminarLiquidacion"
												styleClass="button" text="Eliminar" />
											<ui:staticText escape="false" id="stSeparadorBotonImprimirServidor" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<a4j:commandButton binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnImprimirEnServidor}"
												action="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.btnImprimirEnServidor_action}" id="btnImprimirEnServidor"
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
					<ui:hiddenField binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.hidIdPagina}" id="hidIdPagina"
						text="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.idPagina}" />
					<ui:hiddenField binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.idSubSesion}" />
					<ui:script binding="#{saic$grpTGI$ABMLiquidacionTGI$AdminLiquidacionTGI.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
