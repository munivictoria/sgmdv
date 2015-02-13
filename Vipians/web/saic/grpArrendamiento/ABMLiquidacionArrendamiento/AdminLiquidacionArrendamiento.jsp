<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" isELIgnored="false"
		deferredSyntaxAllowedAsLiteral="false" />
	<f:view>
		<ui:page binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.page1}" id="page1">
			<ui:html binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.html1}" id="html1">
			<ui:head binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.head1}" id="head1">
				<ui:link binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento";

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
			<ui:body binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.body1}" focus="form1:btnSeleccionarPersonaFisica" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td colspan="4"></td>
									</tr>
									<tr>
										<td align="left" style="padding-left: 40px">
											<ui:panelGroup binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.label2}" for="tfPersona" id="label2"
																styleClass="label" text="Persona" />
														</td>
														<td colspan="4">
															<ui:textField binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.tfPersona}" columns="40" id="tfPersona"
																styleClass="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.paginatedTable.filtro.persona != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.paginatedTable.filtro.persona != null}" />
															<ui:button action="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnSeleccionarPersonaFisica_action}"
																binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnSeleccionarPersonaFisica}" escape="false"
																id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Fisica" />
															<ui:button action="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnSeleccionarPersonaJuridica_action}"
																binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnSeleccionarPersonaJuridica}" escape="false"
																id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar Persona Juridica" />
															<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersonaFisica" title="Limpiar"
																binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnLimpiarPersona}"
																action="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnLimpiarPersona_action}" styleClass="buttonLimpiarAjax"
																oncomplete="cargarComportamientoJQuery(); focusearTfPersonaSeleccionada();" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.label1}" for="tfParcela" id="label1"
																styleClass="label" text="Parcela" />
														</td>
														<td align="left" colspan="4" nowrap="nowrap">
															<ui:textField binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.tfParcela}" columns="40" id="tfParcela"
																styleClass="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.paginatedTable.filtro.parcela != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.paginatedTable.filtro.parcela != null}" />
															<ui:button action="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnSeleccionarParcela_action}"
																binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnSeleccionarParcela}" escape="false"
																id="btnSeleccionarParcela" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarParcela" reRender="form1:tfParcela" title="Limpiar"
																binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnLimpiarParcela}"
																action="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnLimpiarParcela_action}" styleClass="buttonLimpiarAjax"
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
																		<ui:panelGroup binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.panelAtributoDinamico}"
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
															<ui:label binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.label3}" id="label3" styleClass="label2"
																text="Período" />
														</td>
													</tr>
													<tr>
														<td align="center" nowrap="nowrap">
															<ui:label binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.lblAnio}" for="ddAnios" id="lblAnio"
																styleClass="label" text="Año" />
															<ui:dropDown binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.ddAnios}" id="ddAnios" styleClass="textField"
																items="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.ddAniosOptions.options}"
																valueChangeListener="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.eventoSeleccionAnio(evento)}">
																<a4j:support event="onChange" reRender="form1:ddCalendarios, form1:ddPeriodos, form1:ddCuotas" />
															</ui:dropDown>
														</td>
														<td align="center" nowrap="nowrap">
															<ui:label binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.lblCalendarios}" for="ddCalendarios"
																id="lblCalendarios" styleClass="label" text="Calendario" />
															<ui:dropDown binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.ddCalendarios}" id="ddCalendarios"
																styleClass="textField" items="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.ddCalendariosOptions.options}"
																valueChangeListener="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.eventoSeleccionCalendario(event)}">
																<a4j:support event="onChange" reRender="form1:ddPeriodos, form1:ddCuotas" />
															</ui:dropDown>
														</td>
														<td align="center" nowrap="nowrap">
															<ui:label binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.lblPeriodos}" for="ddPeriodos" id="lblPeriodos"
																styleClass="label" text="Periodo" />
															<ui:dropDown binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.ddPeriodos}" id="ddPeriodos" styleClass="textField"
																items="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.ddPeriodosOptions.options}"
																valueChangeListener="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.eventoSeleccionPeriodo(event)}">
																<a4j:support event="onChange" reRender="form1:ddCuotas " />
															</ui:dropDown>
														</td>
														<td align="center" nowrap="nowrap">
															<ui:label binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.lblCuotas}" for="ddCuotas" id="lblCuotas"
																styleClass="label" text="Cuota" />
															<ui:dropDown binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.ddCuotas}" id="ddCuotas" styleClass="textField"
																items="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.ddCuotasOptions.options}" />
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
															<ui:label binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.lblEstado}" id="lblEstado" styleClass="label"
																text="Estado" />
															<ui:dropDown binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.ddEstado}" id="ddEstado" styleClass="textField"
																items="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.ddEstadoDefaultOptions.options}" />
														</td>
														<td nowrap="nowrap">
															<ui:label binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.lblTipo}" id="lblTipo" styleClass="label" text="Tipo" />
															<ui:dropDown binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.ddTipo}" id="ddTipo" styleClass="textField"
																items="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.ddTipoDefaultOptions.options}" />
														</td>
														<td nowrap="nowrap">
															<ui:label binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.lblOmitir}" id="lblOmitir" styleClass="label"
																text="Omitir monto en cero" />
															<ui:checkbox binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.chkOmitir}" id="chkOmitir" selected="false" />
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
											<a4j:commandButton binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnBuscar}"
												action="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnReiniciar_action}"
												binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros"
												oncomplete="cargarComportamientoJQuery();" />
											<ui:staticText binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnCancelar_action}"
												binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<h:panelGrid binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.grpCargando}" columns="2" id="grpCargando"
								style="display: none; margin-left: 7px; padding-left: 10px" styleClass="msgLiquidacion">
								<ui:image binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.image1}" id="image1"
									url="/resources/imagenes/abm/wait_medium_1.gif" />
								<ui:staticText binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.staticText4}" escape="false" id="staticText4"
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
										<ui:table binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.paginatedTable}" id="table1" styleClass="tablaPaginada">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.groupPanel1}" id="groupPanel1"
													separator="&lt;br/&gt;&lt;br/&gt;">
													<ui:panelGroup binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.gpBotones}" id="gpBotones">
														<ui:button action="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnSeleccionar_action}"
															binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
															text="Seleccionar" />
														<ui:staticText binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.stSeparadorSeleccionar}" escape="false"
															id="staticText6" />
														<ui:button action="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnSeleccionarTodas_action}"
															binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnSeleccionarTodas}" id="btnSeleccionarTodas"
															styleClass="button" text="Todas" toolTip="Selecciona todas" />
														<ui:button action="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnDeseleccionarTodas_action}"
															binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnDeseleccionarTodas}" id="btnDeseleccionarTodas"
															styleClass="button" text="Ninguna" toolTip="Deselecciona todas" />
														<ui:staticText escape="false" id="stSeparador2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnGenerarLiquidaciones_action}"
															binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnGenerarLiquidaciones}" id="btnGenerarLiquidaciones"
															styleClass="button" text="Generar Liquidaciones" />
														<ui:button action="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnConsultar_action}"
															binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnConsultar}" id="btnConsultar" styleClass="button"
															text="Consultar" />
														<ui:button action="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnImprimir_action}"
															binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnImprimir}" id="btnImprimir"
															onClick="newWindow = window.open('/Vipians/faces/ImpresionServlet', 'Reporte')" styleClass="button" text="Imprimir" />
														<ui:staticText binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.staticText16}" escape="false" id="staticText16"
															text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnReliquidar_action}"
															binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnReliquidar}" id="btnReliquidar" styleClass="button"
															text="Reliquidar" />
														<ui:button action="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnActualizarDeuda_action}"
															binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnActualizarDeuda}" id="btnActualizarDeuda" styleClass="button"
															text="Actualizar Deuda" />
														<ui:staticText escape="false" id="stSeparador3" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnExportar_action}"
															binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnExportar}" id="btnExportar" styleClass="button"
															text="Exportar" onClick="return exportarReporte()" />
													</ui:panelGroup>
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.stCantidadRegistros}" id="stCantidadRegistros"
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
											rendered="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.renderPanelAdminLiquidaciones}">
											<ui:button action="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnMarcarPagada_action}"
												binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnMarcarPagada}" id="btnMarcarPagada" styleClass="button"
												text="Marcar Pagada" />
											<ui:button action="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnMarcarImpaga_action}"
												binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnMarcarImpaga}" id="btnMarcarImpaga" styleClass="button"
												text="Marcar Impaga" />
											<ui:staticText escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnModificarLiquidacion_action}"
												binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnModificarLiquidacion}" id="btnModificarLiquidacion"
												styleClass="button" text="Modificar" />
											<ui:staticText escape="false" id="stSeparador1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnEliminarLiquidacion_action}"
												binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnEliminarLiquidacion}" id="btnEliminarLiquidacion"
												styleClass="button" text="Eliminar" />
											<ui:staticText escape="false" id="stSeparadorBotonImprimirServidor" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<a4j:commandButton binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnImprimirEnServidor}"
												action="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.btnImprimirEnServidor_action}" id="btnImprimirEnServidor"
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
					<ui:hiddenField binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.hidIdPagina}" id="hidIdPagina"
						text="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.idPagina}" />
					<ui:hiddenField binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.idSubSesion}" />
					<ui:script binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$AdminLiquidacionArrendamiento.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
