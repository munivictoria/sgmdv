<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" isELIgnored="false"
		deferredSyntaxAllowedAsLiteral="false" />
	<f:view>
		<ui:page binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.page1}" id="page1">
			<ui:html binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.html1}" id="html1">
			<ui:head binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.head1}" id="head1">
				<ui:link binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.link1}" id="link1"
					url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio";

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
			<ui:body binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.body1}"
				focus="form1:btnSeleccionarPersonaFisica" id="body1" onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.stTitulo}" id="stTitulo"
										styleClass="tituloABM" text="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td colspan="4"></td>
									</tr>
									<tr>
										<td align="left" style="padding-left: 40px">
											<ui:panelGroup binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.label2}" for="tfPersona"
																id="label2" styleClass="label" text="Persona" />
														</td>
														<td colspan="4">
															<ui:textField binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.tfPersona}" columns="40"
																id="tfPersona"
																styleClass="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.paginatedTable.filtro.persona != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.paginatedTable.filtro.persona != null}" />
															<ui:button
																action="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnSeleccionarPersonaFisica_action}"
																binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnSeleccionarPersonaFisica}"
																escape="false" id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar" />
															<ui:button
																action="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnSeleccionarPersonaJuridica_action}"
																binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnSeleccionarPersonaJuridica}"
																escape="false" id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersona" title="Limpiar"
																binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnLimpiarPersona}"
																action="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnLimpiarPersona_action}"
																styleClass="buttonLimpiarAjax" oncomplete="cargarComportamientoJQuery(); focusearTfPersonaSeleccionada();" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label for="tfParcela" id="lblParcela" styleClass="label" text="Parcela Cementerio" />
														</td>
														<td align="left" colspan="4" nowrap="nowrap">
															<ui:textField binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.tfParcela}" columns="40"
																disabled="true" id="tfParcela" styleClass="textField" />
															<ui:button action="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnSeleccionarParcela_action}"
																binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnSeleccionarParcela}" escape="false"
																id="btnSeleccionarParcela" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarParcela" reRender="form1:tfParcela" title="Limpiar"
																binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnLimpiarParcela}"
																action="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnLimpiarParcela_action}"
																styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.lblTipoSepultura}"
																for="ddTipoSepultura" id="lblTipoSepultura" styleClass="label" text="Tipo Sepultura" />
														</td>
														<td align="left" nowrap="nowrap" colspan="4">
															<ui:dropDown binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.ddTipoSepultura}"
																id="ddTipoSepultura" styleClass="textField"
																items="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.ddTipoSepulturaOptions.options}">
															</ui:dropDown>
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
																		<ui:panelGroup binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.panelAtributoDinamico}"
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
															<ui:label binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.lblPeriodo}" id="lblPeriodo"
																styleClass="label2" text="Período" />
														</td>
													</tr>
													<tr>
														<td align="center" nowrap="nowrap">
															<ui:label binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.lblAnio}" for="ddAnios"
																id="lblAnio" styleClass="label" text="Año" />
															<ui:dropDown binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.ddAnios}" id="ddAnios"
																styleClass="textField"
																items="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.ddAniosOptions.options}"
																valueChangeListener="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.eventoSeleccionAnio(evento)}">
																<a4j:support event="onChange" reRender="form1:ddCalendarios, form1:ddPeriodos, form1:ddCuotas" />
															</ui:dropDown>
														</td>
														<td align="center" nowrap="nowrap">
															<ui:label binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.lblCalendarios}"
																for="ddCalendarios" id="lblCalendarios" styleClass="label" text="Calendario" />
															<ui:dropDown binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.ddCalendarios}"
																id="ddCalendarios" styleClass="textField"
																items="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.ddCalendariosOptions.options}"
																valueChangeListener="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.eventoSeleccionCalendario(event)}">
																<a4j:support event="onChange" reRender="form1:ddPeriodos, form1:ddCuotas" />
															</ui:dropDown>
														</td>
														<td align="center" nowrap="nowrap">
															<ui:label binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.lblPeriodos}" for="ddPeriodos"
																id="lblPeriodos" styleClass="label" text="Periodo" />
															<ui:dropDown binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.ddPeriodos}" id="ddPeriodos"
																styleClass="textField"
																items="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.ddPeriodosOptions.options}"
																valueChangeListener="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.eventoSeleccionPeriodo(event)}">
																<a4j:support event="onChange" reRender="form1:ddCuotas " />
															</ui:dropDown>
														</td>
														<td align="center" nowrap="nowrap">
															<ui:label binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.lblCuotas}" for="ddCuotas"
																id="lblCuotas" styleClass="label" text="Cuota" />
															<ui:dropDown binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.ddCuotas}" id="ddCuotas"
																styleClass="textField"
																items="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.ddCuotasOptions.options}" />
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
															<ui:label binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.lblEstado}" id="lblEstado"
																styleClass="label" text="Estado" />
															<ui:dropDown binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.ddEstado}" id="ddEstado"
																styleClass="textField"
																items="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.ddEstadoDefaultOptions.options}" />
														</td>
														<td nowrap="nowrap">
															<ui:label binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.lblTipo}" id="lblTipo"
																styleClass="label" text="Tipo" />
															<ui:dropDown binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.ddTipo}" id="ddTipo"
																styleClass="textField"
																items="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.ddTipoDefaultOptions.options}" />
														</td>
														<td nowrap="nowrap">
															<ui:label binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.lblOmitir}" id="lblOmitir"
																styleClass="label" text="Omitir monto en cero" />
															<ui:checkbox binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.chkOmitir}" id="chkOmitir"
																selected="false" />
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
											<a4j:commandButton binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnBuscar}"
												action="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnBuscar_action}" id="btnBuscar"
												value="Buscar" styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnReiniciar_action}"
												binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnReiniciar}" id="btnReiniciar"
												styleClass="btnAjax" value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros"
												oncomplete="cargarComportamientoJQuery();" />
											<ui:staticText binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.staticText2}" escape="false"
												id="staticText2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnCancelar_action}"
												binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnCancelar}" id="btnCancelar"
												styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.messageGroup}" id="messageGroup"
										showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<h:panelGrid binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.grpCargando}" columns="2"
								id="grpCargando" style="display: none; margin-left: 7px; padding-left: 10px" styleClass="msgLiquidacion">
								<ui:image binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.image1}" id="image1"
									url="/resources/imagenes/abm/wait_medium_1.gif" />
								<ui:staticText binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.staticText1}" escape="false"
									id="staticText1" styleClass="label2" text="     Generando archivos de impresión... Aguarde por favor." />
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
										<ui:table binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.paginatedTable}" id="table1"
											styleClass="tablaPaginada">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.groupPanel1}" id="groupPanel1"
													separator="&lt;br/&gt;&lt;br/&gt;">
													<ui:panelGroup binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.gpBotones}" id="gpBotones">
														<ui:button action="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnSeleccionar_action}"
															binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnSeleccionar}" id="btnSeleccionar"
															styleClass="button" text="Seleccionar" />
														<ui:staticText binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.stSeparadorSeleccionar}"
															escape="false" id="staticText6" />
														<ui:button action="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnSeleccionarTodas_action}"
															binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnSeleccionarTodas}"
															id="btnSeleccionarTodas" styleClass="button" text="Todas" toolTip="Selecciona todas" />
														<ui:button action="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnDeseleccionarTodas_action}"
															binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnDeseleccionarTodas}"
															id="btnDeseleccionarTodas" styleClass="button" text="Ninguna" toolTip="Deselecciona todas" />
														<ui:staticText escape="false" id="stSeparador2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnGenerarLiquidaciones_action}"
															binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnGenerarLiquidaciones}"
															id="btnGenerarLiquidaciones" styleClass="button" text="Generar Liquidaciones" />
														<ui:button action="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnConsultar_action}"
															binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnConsultar}" disabled="false"
															id="btnConsultar" styleClass="button" text="Consultar" />
														<ui:button action="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnImprimir_action}"
															binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnImprimir}" id="btnImprimir"
															onClick="newWindow = window.open('/Vipians/faces/ImpresionServlet', 'Reporte')" styleClass="button" text="Imprimir" />
														<ui:staticText binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.staticText14}" escape="false"
															id="staticText14" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnReliquidar_action}"
															binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnReliquidar}" id="btnReliquidar"
															styleClass="button" text="Reliquidar" />
														<ui:button action="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnActualizarDeuda_action}"
															binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnActualizarDeuda}"
															id="btnActualizarDeuda" styleClass="button" text="Actualizar Deuda" />
														<ui:staticText escape="false" id="stSeparador3" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnExportar_action}"
															binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnExportar}" id="btnExportar"
															styleClass="button" text="Exportar" onClick="return exportarReporte()" />
													</ui:panelGroup>
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.lblEncontrados}" id="lblEncontrados"
											styleClass="label2" text="Registros Encontrados: " />
										<ui:staticText binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.stCantidadRegistros}"
											id="stCantidadRegistros" styleClass="staticText" />
									</td>
								</tr>
								<tr>
									<td colspan="3">
										<div class="notifABM clrNota">
											<b>Nota: </b>La columnta <i>Monto</i> es el resultado del importe de la tasa más el valor de los modificadores correspondientes.
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<ui:panelGroup id="pgPanelAdminLiquidaciones"
											rendered="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.renderPanelAdminLiquidaciones}">
											<ui:button action="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnMarcarPagada_action}"
												binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnMarcarPagada}" id="btnMarcarPagada"
												styleClass="button" text="Marcar Pagada" />
											<ui:button action="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnMarcarImpaga_action}"
												binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnMarcarImpaga}" id="btnMarcarImpaga"
												styleClass="button" text="Marcar Impaga" />
											<ui:staticText escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnModificarLiquidacion_action}"
												binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnModificarLiquidacion}"
												id="btnModificarLiquidacion" styleClass="button" text="Modificar" />
											<ui:staticText escape="false" id="stSeparador1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnEliminarLiquidacion_action}"
												binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnEliminarLiquidacion}"
												id="btnEliminarLiquidacion" styleClass="button" text="Eliminar" />
											<ui:staticText escape="false" id="stSeparadorBotonImprimirServidor" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<a4j:commandButton binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnImprimirEnServidor}"
												action="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.btnImprimirEnServidor_action}"
												id="btnImprimirEnServidor" value="Imprimir" styleClass="btnAjax" oncomplete="changeStyleAlIngresar()" />
										</ui:panelGroup>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:btnSeleccionarPersonaFisica').focus();
					</script>
					<ui:hiddenField binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.hidIdPagina}" id="hidIdPagina"
						text="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.idPagina}" />
					<ui:hiddenField binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.idSubSesion}" />
					<ui:script binding="#{saic$grpCementerio$ABMLiquidacionCementerio$AdminLiquidacionCementerio.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
