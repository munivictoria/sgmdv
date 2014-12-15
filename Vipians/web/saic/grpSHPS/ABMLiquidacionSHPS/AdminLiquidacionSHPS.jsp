<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" isELIgnored="false"
		deferredSyntaxAllowedAsLiteral="false" />
	<f:view>
		<ui:page binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.page1}" id="page1">
			<ui:html binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.html1}" id="html1">
			<ui:head binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.head1}" id="head1">
				<ui:link binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS";

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
			<ui:body binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.body1}" focus="form1:btnSeleccionarPersonaFisica" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td colspan="4"></td>
									</tr>
									<tr>
										<td align="left" style="padding-left: 40px">
											<ui:panelGroup binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.label2}" for="tfPersona" id="label2"
																styleClass="label" text="Persona" />
														</td>
														<td colspan="4">
															<ui:textField binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.tfPersona}" columns="40" id="tfPersona"
																styleClass="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.paginatedTable.filtro.persona != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.paginatedTable.filtro.persona != null}" />
															<ui:button action="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnSeleccionarPersonaFisica_action}"
																binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnSeleccionarPersonaFisica}" escape="false"
																id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar" />
															<ui:button action="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnSeleccionarPersonaJuridica_action}"
																binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnSeleccionarPersonaJuridica}" escape="false"
																id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersona" title="Limpiar"
																binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnLimpiarPersona}"
																action="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnLimpiarPersona_action}" styleClass="buttonLimpiarAjax"
																oncomplete="cargarComportamientoJQuery(); focusearTfPersonaSeleccionada();" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.label3}" for="tfRubro" id="label3"
																styleClass="label" text="Rubro" />
														</td>
														<td align="left" colspan="4" nowrap="nowrap">
															<ui:textField binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.tfRubro}" columns="47" disabled="true"
																id="tfRubro" styleClass="textField" />
															<ui:button action="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnSeleccionarRubro_action}"
																binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnSeleccionarRubro}" escape="false"
																id="btnSeleccionarRubro" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarRubro" reRender="form1:tfRubro" title="Limpiar"
																binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnLimpiarRubro}"
																action="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnLimpiarRubro_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td nowrap="nowrap" align="right">
															<ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.lblNroInscripcion}" id="lblNroInscripcion"
																styleClass="label" text="Número Inscripción">
															</ui:label>
														</td>
														<td>
															<ui:textField binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.tfNroInscripcion}" id="tfNroInscripcion"
																styleClass="textField" columns="10">
															</ui:textField>
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
																		<ui:panelGroup binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.panelAtributoDinamico}"
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
															<ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.lblPeriodo}" id="lblPeriodo" styleClass="label2"
																text="Período" />
														</td>
													</tr>
													<tr>
														<td align="center" nowrap="nowrap">
															<ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.lblAnio}" for="ddAnios" id="lblAnio"
																styleClass="label" text="Año" />
															<ui:dropDown binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.ddAnios}" id="ddAnios" styleClass="textField"
																items="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.ddAniosOptions.options}"
																valueChangeListener="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.eventoSeleccionAnio(evento)}">
																<a4j:support event="onChange" reRender="form1:ddCalendarios, form1:ddPeriodos, form1:ddCuotas" />
															</ui:dropDown>
														</td>
														<td align="center" nowrap="nowrap">
															<ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.lblCalendarios}" for="ddCalendarios"
																id="lblCalendarios" styleClass="label" text="Calendario" />
															<ui:dropDown binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.ddCalendarios}" id="ddCalendarios"
																styleClass="textField" items="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.ddCalendariosOptions.options}"
																valueChangeListener="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.eventoSeleccionCalendario(event)}">
																<a4j:support event="onChange" reRender="form1:ddPeriodos, form1:ddCuotas" />
															</ui:dropDown>
														</td>
														<td align="center" nowrap="nowrap">
															<ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.lblPeriodos}" for="ddPeriodos" id="lblPeriodos"
																styleClass="label" text="Periodo" />
															<ui:dropDown binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.ddPeriodos}" id="ddPeriodos"
																styleClass="textField" items="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.ddPeriodosOptions.options}"
																valueChangeListener="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.eventoSeleccionPeriodo(event)}">
																<a4j:support event="onChange" reRender="form1:ddCuotas " />
															</ui:dropDown>
														</td>
														<td align="center" nowrap="nowrap">
															<ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.lblCuotas}" for="ddCuotas" id="lblCuotas"
																styleClass="label" text="Cuota" />
															<ui:dropDown binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.ddCuotas}" id="ddCuotas" styleClass="textField"
																items="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.ddCuotasOptions.options}" />
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
															<ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.lblEstado}" id="lblEstado" styleClass="label"
																text="Estado" />
															<ui:dropDown binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.ddEstado}" id="ddEstado" styleClass="textField"
																items="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.ddEstadoDefaultOptions.options}" />
														</td>
														<td nowrap="nowrap">
															<ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.lblTipo}" id="lblTipo" styleClass="label"
																text="Tipo" />
															<ui:dropDown binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.ddTipo}" id="ddTipo" styleClass="textField"
																items="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.ddTipoDefaultOptions.options}" />
														</td>
														<td nowrap="nowrap">
															<ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.lblOmitir}" id="lblOmitir" styleClass="label"
																text="Omitir monto en cero" />
															<ui:checkbox binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.chkOmitir}" id="chkOmitir" selected="false" />
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
											<a4j:commandButton binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnBuscar}"
												action="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnReiniciar_action}"
												binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros"
												oncomplete="cargarComportamientoJQuery();" />
											<ui:staticText binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnCancelar_action}"
												binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<h:panelGrid binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.grpCargando}" columns="2" id="grpCargando"
								style="display: none; margin-left: 7px; padding-left: 10px" styleClass="msgLiquidacion">
								<ui:image binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.image1}" id="image1"
									url="/resources/imagenes/abm/wait_medium_1.gif" />
								<ui:staticText binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.staticText1}" escape="false" id="staticText1"
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
										<ui:table binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.paginatedTable}" id="table1" styleClass="tablaPaginada">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.groupPanel1}" id="groupPanel1"
													separator="&lt;br/&gt;&lt;br/&gt;">
													<ui:panelGroup binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.gpBotones}" id="gpBotones">
														<ui:button action="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnSeleccionar_action}"
															binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
															text="Seleccionar" />
														<ui:staticText binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.stSeparadorSeleccionar}" escape="false"
															id="staticText6" />
														<ui:button action="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnSeleccionarTodas_action}"
															binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnSeleccionarTodas}" id="btnSeleccionarTodas"
															styleClass="button" text="Todas" toolTip="Selecciona todas" />
														<ui:button action="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnDeseleccionarTodas_action}"
															binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnDeseleccionarTodas}" id="btnDeseleccionarTodas"
															styleClass="button" text="Ninguna" toolTip="Deselecciona todas" />
														<ui:staticText escape="false" id="stSeparador2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnGenerarLiquidaciones_action}"
															binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnGenerarLiquidaciones}" id="btnGenerarLiquidaciones"
															styleClass="button" text="Generar Liquidaciones" />
														<ui:button action="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnConsultar_action}"
															binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnConsultar}" disabled="false" id="btnConsultar"
															styleClass="button" text="Consultar" />
														<ui:button action="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnImprimir_action}"
															binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnImprimir}" id="btnImprimir"
															onClick="newWindow = window.open('/Vipians/faces/ImpresionServlet', 'Reporte')" styleClass="button" text="Imprimir" />
														<ui:staticText binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.staticText14}" escape="false" id="staticText14"
															text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnReliquidar_action}"
															binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnReliquidar}" id="btnReliquidar" styleClass="button"
															text="Reliquidar" />
														<ui:button action="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnActualizarDeuda_action}"
															binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnActualizarDeuda}" id="btnActualizarDeuda"
															styleClass="button" text="Actualizar Deuda" />
														<ui:staticText escape="false" id="stSeparador3" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnExportar_action}"
															binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnExportar}" id="btnExportar" styleClass="button"
															text="Exportar" onClick="return exportarReporte()" />
													</ui:panelGroup>
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
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
											rendered="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.renderPanelAdminLiquidaciones}">
											<ui:button action="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnMarcarPagada_action}"
												binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnMarcarPagada}" id="btnMarcarPagada" styleClass="button"
												text="Marcar Pagada" />
											<ui:button action="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnMarcarImpaga_action}"
												binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnMarcarImpaga}" id="btnMarcarImpaga" styleClass="button"
												text="Marcar Impaga" />
											<ui:staticText escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnModificarLiquidacion_action}"
												binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnModificarLiquidacion}" id="btnModificarLiquidacion"
												styleClass="button" text="Modificar" />
											<ui:staticText escape="false" id="stSeparador1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnEliminarLiquidacion_action}"
												binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnEliminarLiquidacion}" id="btnEliminarLiquidacion"
												styleClass="button" text="Eliminar" />
											<ui:staticText escape="false" id="stSeparadorBotonImprimirServidor" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<a4j:commandButton binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnImprimirEnServidor}"
												action="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.btnImprimirEnServidor_action}" id="btnImprimirEnServidor"
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
					<ui:hiddenField binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.hidIdPagina}" id="hidIdPagina"
						text="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.idPagina}" />
					<ui:hiddenField binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.idSubSesion}" />
					<ui:script binding="#{saic$grpSHPS$ABMLiquidacionSHPS$AdminLiquidacionSHPS.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
