<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.head1}" id="head1"
				title="Administración de Obligaciones: Tasa General Inmobiliaria">
				<ui:link binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI";

					function cargarComportamientoJQuery() {
						autoCompletarEnTextField("#form1:tfPersonaSeleccionada", "persona", nombreBean, "setPersonaAutocompletar");
						autoCompletarEnTextField("#form1:tfParcelaSeleccionada", "parcela", nombreBean, "setParcelaAutocompletar");
					}

					function focusearTfPersonaSeleccionada() {
						$("#form1\\:tfPersonaSeleccionada").focus();
					}

					function focusearTfParcelaSeleccionada() {
						$("#form1\\:tfParcelaSeleccionada").focus();
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.body1}" focus="form1:tfNumeroRegistro" id="body1"
				onLoad="parent.footer.location.reload(); changeStyleAlIngresar();" style="background-color: rgb(236, 236, 242); -rave-layout: grid"
				onKeyPress="return eventoByEnter(event,'btnBuscar')" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.head1.title}" />
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
											<ui:panelGroup binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.lblPersonaSeleccionada}" for="tfPersonaSeleccionada"
																id="lblPersonaSeleccionada" styleClass="label" text="Persona" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.tfPersonaSeleccionada}" columns="40"
																id="tfPersonaSeleccionada"
																styleClass="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.paginatedTable.filtro.persona != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.paginatedTable.filtro.persona != null}" />
															<ui:button action="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.btnSeleccionarPersonaFisica_action}"
																binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.btnSeleccionarPersonaFisica}" escape="false"
																id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Fisica" />
															<ui:button action="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.btnSeleccionarPersonaJuridica_action}"
																binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.btnSeleccionarPersonaJuridica}" escape="false"
																id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar Persona Juridica" />
															<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersonaSeleccionada" title="Limpiar"
																binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.btnLimpiarPersona}"
																action="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.btnLimpiarPersona_action}" styleClass="buttonLimpiarAjax"
																oncomplete="cargarComportamientoJQuery(); focusearTfPersonaSeleccionada();" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.lblParcelaSeleccionada}" for="tfParcelaSeleccionada"
																id="lblParcelaSeleccionada" styleClass="label" text="Parcela" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.tfParcelaSeleccionada}" columns="40"
																id="tfParcelaSeleccionada"
																styleClass="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.paginatedTable.filtro.parcela != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.paginatedTable.filtro.parcela != null}" />
															<ui:button action="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.btnSeleccionarParcela_action}"
																binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.btnSeleccionarParcela}" escape="false"
																id="btnSeleccionarParcela" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar Parcela" />
															<a4j:commandButton id="btnLimpiarParcela" reRender="form1:tfParcelaSeleccionada" title="Limpiar"
																binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.btnLimpiarParcela}"
																action="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.btnLimpiarParcela_action}" styleClass="buttonLimpiarAjax"
																oncomplete="cargarComportamientoJQuery(); focusearTfParcelaSeleccionada();" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap" style="height: 18px">
															<ui:label binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.label1}" for="tfNumeroParcela" id="label1"
																styleClass="label" text="Nº de Parcela" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.tfNroParcela}" id="tfNroParcela"
																styleClass="textField" />
														</td>
														<td align="right" nowrap="true">
															<ui:label binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.label4}" id="label4" style="" styleClass="label"
																text="Estado" />
														</td>
														<td nowrap="nowrap">
															<ui:dropDown binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.ddEstado}" id="ddEstado"
																items="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.ddEstadoDefaultOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.lblExenciones}" id="lblExenciones" style=""
																styleClass="label" text="Exenciones" />
														</td>
														<td nowrap="nowrap">
															<ui:dropDown binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.ddExenciones}" id="ddExenciones"
																items="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.ddExencionesDefaultOptions.options}" styleClass="textField" />
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
																		<ui:panelGroup binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.panelAtributoDinamico}"
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
															<ui:checkbox binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.btnBuscar}"
												action="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.btnReiniciar_action}"
												binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros, form1:ddEstado"
												oncomplete="cargarComportamientoJQuery();" />
											<ui:staticText binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.btnCancelar_action}"
												binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:panelGroup binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.gpSeleccion}" id="gpSeleccion">
											<ui:label binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.label3}" for="ddPlantillaObligacion" id="label3"
												styleClass="label" text="Plantilla de Obligación" />
											<ui:staticText binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.staticText5}" escape="false" id="staticText5"
												styleClass="label" text="&amp;nbsp;&amp;nbsp;&amp;nbsp;" />
											<ui:dropDown binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.ddPlantillaObligacion}" id="ddPlantillaObligacion"
												items="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.ddPlantillaObligacionOptions.options}" styleClass="textField" />
										</ui:panelGroup>
									</td>
								</tr>
								<tr>
									<td>
										<ui:table binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.paginatedTable}" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.groupPanel1}" id="groupPanel1"
													separator="&lt;br/&gt;&lt;br/&gt;">
													<ui:panelGroup binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.gpBotones}" id="gpBotones">
														<ui:button action="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.btnSeleccionar_action}"
															binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
															text="Seleccionar" />
														<ui:staticText binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.stSeparadorSeleccionar}" escape="false"
															id="staticText6" />
														<ui:button action="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.btnAgregar_action}"
															binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
														<ui:button action="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.btnModificar_action}"
															binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.btnModificar}" id="btnModificar" styleClass="button"
															text="Modificar" />
														<ui:button action="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.btnEliminar_action}"
															binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.btnEliminar}" id="btnEliminar" styleClass="button"
															text="Eliminar" />
														<ui:button action="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.btnReactivar_action}"
															binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.btnReactivar}" id="btnReactivar" styleClass="button"
															text="Reactivar" />
														<ui:staticText binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.stSeparadorAccion}" escape="false"
															id="staticText8" />
														<ui:button action="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.btnDardeAlta_action}"
															binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.btnDardeAButton}" id="btnDardeAlta" styleClass="button"
															text="Dar de Alta" />
														<ui:button action="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.btnConsultar_action}"
															binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.btnConsultar}" id="btnConsultar" styleClass="button"
															text="Consultar" />
														<ui:staticText binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.staticText7}" escape="false" id="staticText7"
															text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.idImprimirReporte}" disabled="true"
															id="idImprimirReporte" styleClass="button" text="Visualizar Reporte" />
														<ui:staticText binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.staticText11}" escape="false" id="staticText11"
															text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.btnExportar_action}"
															binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.btnExportar}" id="btnExportar" styleClass="button"
															text="Exportar" onClick="return exportarReporte()" />
														<ui:staticText binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.paginatedTable.stSeparadorOrdenamiento}"
															id="separador_1" />
														<ui:button action="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.btnImprimirReporte_action}"
															binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.btnImprimirReporte}"
															onClick="newWindow = window.open('/Vipians/faces/ImpresionServlet', 'Reporte')" id="btnImprimirReporte" styleClass="button"
															text="Visualizar Reporte" />
														<ui:imageHyperlink binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.paginatedTable.botonOrdenamiento}" />
													</ui:panelGroup>
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<ui:hiddenField binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{habilitaciones$grpTGI$ABMDocEspTGI$AdminDocEspTGI.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
