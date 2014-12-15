<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.head1}" id="head1"
				title="Administración de Obligaciones: Obras y Servicios Públicos">
				<ui:link binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP";

					function cargarComportamientoJQuery() {
						autoCompletarEnTextField("#form1:tfPersonaSeleccionada", "persona", nombreBean, "setPersonaAutocompletar");
					}

					function focusearTfPersonaSeleccionada() {
						$("#form1\\:tfPersonaSeleccionada").focus();
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.body1}" focus="form1:btnSeleccionarPersonaFisica" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td colspan="2"></td>
									</tr>
									<tr>
										<td align="center" nowrap="nowrap">
											<ui:staticText escape="false" id="stFiltrarPor" styleClass="textoFiltrarPor" text="Filtrar por" />
										</td>
									</tr>
									<tr>
										<td colspan="4">
											<hr />
										</td>
									</tr>
									<tr>
										<td align="center">
											<ui:panelGroup binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.lblPersonaSeleccionada}" for="tfPersonaSeleccionada"
																id="lblPersonaSeleccionada" styleClass="label" text="Persona" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.tfPersonaSeleccionada}" columns="40"
																id="tfPersonaSeleccionada"
																styleClass="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.paginatedTable.filtro.persona != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.paginatedTable.filtro.persona != null}" />
															<ui:button action="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.btnSeleccionarPersonaFisica_action}"
																binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.btnSeleccionarPersonaFisica}" escape="false"
																id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Fisica" />
															<ui:button action="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.btnSeleccionarPersonaJuridica_action}"
																binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.btnSeleccionarPersonaJuridica}" escape="false"
																id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar Persona Jurídica" />
															<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersonaSeleccionada" title="Limpiar"
																binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.btnLimpiarPersona}"
																action="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.btnLimpiarPersona_action}" styleClass="buttonLimpiarAjax"
																oncomplete="cargarComportamientoJQuery(); focusearTfPersonaSeleccionada();" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.lblParcelaSeleccionada}" for="tfParcelaSeleccionada"
																id="lblParcelaSeleccionada" styleClass="label" text="Parcela o Subparcela" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.tfParcelaSeleccionada}" columns="40"
																disabled="true" id="tfParcelaSeleccionada" styleClass="textField" />
															<ui:button action="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.btnSeleccionarParcela_action}"
																binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.btnSeleccionarParcela}" escape="false"
																id="btnSeleccionarParcela" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar Parcela" />
															<ui:button action="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.btnSeleccionarSubparcela_action}"
																binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.btnSeleccionarSubparcela}" escape="false"
																id="btnSeleccionarSubparcela" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar Subparcela" />
															<a4j:commandButton id="btnLimpiarParcela" reRender="form1:tfParcelaSeleccionada" title="Limpiar"
																binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.btnLimpiarParcela}"
																action="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.btnLimpiarParcela_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
												</table>
												<table>
													<!--
                                        <tr>
                                            <td align="right" nowrap="nowrap">
                                                <ui:label binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.lblNroRegistro}" for="tfNumeroRegistro"
                                                          id="label1" styleClass="label" text="Nº de Registro"/>
                                            </td>
                                            <td>
                                                <ui:textField binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.tfNumeroRegistro}"
                                                              id="tfNumeroRegistro" styleClass="textField"/>
                                            </td>
                                        </tr>
                                        -->
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.lbNroCuenta}" for="tfNumeroCuenta" id="label4"
																styleClass="label" text="Nº de Cuenta" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.tfNumeroCuenta}" columns="10" id="tfNumeroCuenta"
																maxLength="5" styleClass="textField" />
														</td>
														<td align="right" nowrap="true">
															<ui:label binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.lblExenciones}" id="lblExenciones" style=""
																styleClass="label" text="Exenciones" />
														</td>
														<td nowrap="nowrap">
															<ui:dropDown binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.ddExenciones}" id="ddExenciones"
																items="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.ddExencionesDefaultOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.lblEstado}" id="lblEstado" style="" styleClass="label"
																text="Estado" />
														</td>
														<td nowrap="nowrap">
															<ui:dropDown binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.ddEstado}" id="ddEstado"
																items="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.ddEstadoDefaultOptions.options}" styleClass="textField" />
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
																		<ui:panelGroup binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.panelAtributoDinamico}"
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
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.btnBuscar}"
												action="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1 ,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.btnReiniciar_action}"
												binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros,form1:ddEstado"
												oncomplete="cargarComportamientoJQuery();" />
											<ui:staticText binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.btnCancelar_action}"
												binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:panelGroup binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.gpSeleccion}" id="gpSeleccion">
											<ui:label binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.label3}" for="ddPlantillaObligacion" id="label3"
												styleClass="label" text="Plantilla de Obligación" />
											<ui:staticText binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.staticText5}" escape="false" id="staticText5"
												styleClass="label" text="&amp;nbsp;&amp;nbsp;&amp;nbsp;" />
											<ui:dropDown binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.ddPlantillaObligacion}" id="ddPlantillaObligacion"
												items="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.ddPlantillaObligacionOptions.options}" styleClass="textField" />
										</ui:panelGroup>
									</td>
								</tr>
								<tr>
									<td>
										<ui:table binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.paginatedTable}" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.groupPanel1}" id="groupPanel1"
													separator="&lt;br/&gt;&lt;br/&gt;">
													<ui:panelGroup binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.gpBotones}" id="gpBotones">
														<ui:button action="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.btnSeleccionar_action}"
															binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
															text="Seleccionar" />
														<ui:staticText binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.stSeparadorSeleccionar}" escape="false"
															id="staticText6" />
														<ui:button action="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.btnAgregar_action}"
															binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
														<ui:button action="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.btnModificar_action}"
															binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.btnModificar}" id="btnModificar" styleClass="button"
															text="Modificar" />
														<ui:button action="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.btnEliminar_action}"
															binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.btnEliminar}" id="btnEliminar" styleClass="button"
															text="Eliminar" />
														<ui:staticText binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.stSeparadorAccion}" escape="false"
															id="staticText8" />
														<ui:button action="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.btnConsultar_action}"
															binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.btnConsultar}" id="btnConsultar" styleClass="button"
															text="Consultar" />
														<ui:staticText binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.staticText7}" escape="false" id="staticText7"
															text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.btnImprimirReporte_action}"
															binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.btnImprimirReporte}" disabled="true" id="btnImprimirReporte"
															styleClass="button" text="Visualizar Reporte" />
														<ui:staticText binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.staticText11}" escape="false" id="staticText11"
															text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:dropDown binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.ddFormatoExportar}" id="ddFormatoExportar"
															items="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.ddFormatoExportarDefaultOptions.options}" styleClass="textField" />
														<ui:button action="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.btnExportar_action}"
															binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.btnExportar}" id="btnExportar" styleClass="button"
															text="Exportar" onClick="return exportarReporte()" />
														<ui:staticText binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.paginatedTable.stSeparadorOrdenamiento}"
															id="separador_1" />
														<ui:imageHyperlink binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.paginatedTable.botonOrdenamiento}" />
													</ui:panelGroup>
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:btnSeleccionarPersonaFisica').focus();
					</script>
					<ui:hiddenField binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{habilitaciones$grpOSP$ABMDocEspOSP$AdminDocEspOSP.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
