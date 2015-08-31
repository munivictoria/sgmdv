<?xml version="1.0" encoding="UTF-8"?>
<!-- © Copyright 2015, CoDeSoft Todos los derechos reservados. -->
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<style>
.MniTabTblSelTd {
	background-color: rgb(236, 236, 242);
	font-weight: bold;
}

.TabGrpBox {
	border: none;
}
</style>
		<ui:page binding="#{expedientes$ABMExpediente$AdminExpediente.page1}" id="page1">
			<ui:html binding="#{expedientes$ABMExpediente$AdminExpediente.html1}" id="html1">
			<ui:head binding="#{expedientes$ABMExpediente$AdminExpediente.head1}" id="head1" title="Administración de Expedientes">
				<ui:link binding="#{expedientes$ABMExpediente$AdminExpediente.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "expedientes$ABMExpediente$AdminExpediente";

					function cargarComportamientoJQuery() {
						autoCompletarEnTextField("#form1:tabSet1:tabBusquedaBasica:tfInteresado", "persona", nombreBean, "setPersonaAutocompletar");
						calendarioEnTextField("#form1:tabSet1:tabBusquedaBasica:tfFechaRegistroDesde");
						calendarioEnTextField("#form1:tabSet1:tabBusquedaBasica:tfFechaRegistroHasta");
					}

					function focusearTfInteresadoSeleccionada() {
						$("#form1\\:tabSet1\\:tabBusquedaBasica\\:tfInteresado").focus();
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
				<f:loadBundle basename="muni.expedientes.includesURL" var="url" />
			</ui:head>
			<ui:body binding="#{expedientes$ABMExpediente$AdminExpediente.body1}" focus="form1:tfAsunto" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid"
				onKeyPress="return eventoByEnter(event,'tabSet1:tabBusquedaBasica:btnBuscar')"
				onKeyUp="eventoByEscape(event,'tabSet1:tabBusquedaBasica:btnCancelar')">
				<ui:form binding="#{expedientes$ABMExpediente$AdminExpediente.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{expedientes$ABMExpediente$AdminExpediente.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{expedientes$ABMExpediente$AdminExpediente.head1.title}" />
								</caption>
								<tr>
									<td colspan="2"></td>
								</tr>
								<tr>
									<td align="center" nowrap="nowrap">
										<ui:staticText escape="false" id="stFiltrarPor" styleClass="textoFiltrarPor" text="Filtrar por" />
									</td>
								</tr>
								<tr>
									<td>
										<ui:tabSet style="border:none" binding="#{expedientes$ABMExpediente$AdminExpediente.tabSetAdmin}" id="tabSet1" mini="true"
											lite="true">
											<ui:tab id="tabBusquedaBasica" binding="#{expedientes$ABMExpediente$AdminExpediente.tabBusquedaBasica}" text="Búsqueda Básica">
												<table id="nueva" border="0" class="azul">
													<tbody>
														<tr>
															<td align="left">
																<ui:panelGroup binding="#{expedientes$ABMExpediente$AdminExpediente.pgParametros}" id="pgParametros">
																	<table align="center">
																		<tr>
																			<td align="right" nowrap="nowrap">
																				<ui:label binding="#{expedientes$ABMExpediente$AdminExpediente.lblAsunto}" for="tfAsunto" id="lblAsunto"
																					styleClass="label" text="Asunto" />
																			</td>
																			<td colspan="4">
																				<ui:textField binding="#{expedientes$ABMExpediente$AdminExpediente.tfAsunto}" columns="40" id="tfAsunto"
																					styleClass="textField" />
																			</td>
																		</tr>
																		<tr>
																			<td align="right" nowrap="nowrap">
																				<ui:label binding="#{expedientes$ABMExpediente$AdminExpediente.lblNroRegistro}" for="tfNroRegistro" id="lblNroRegistro"
																					styleClass="label" text="Nº de Registro" />
																			</td>
																			<td colspan="4">
																				<ui:textField binding="#{expedientes$ABMExpediente$AdminExpediente.tfNroRegistro}" columns="16" id="tfNroRegistro"
																					styleClass="textField" />
																			</td>
																		</tr>
																		<tr>
																			<td align="right">
																				<ui:label id="lbDesde" styleClass="label" text="Fecha de Registro desde" />
																			</td>
																			<td>
																				<ui:textField binding="#{expedientes$ABMExpediente$AdminExpediente.tfFechaRegistroDesde}" id="tfFechaRegistroDesde"
																					styleClass="textField" columns="10" />
																			</td>
																			<td align="right">
																				<ui:label id="lbHasta" styleClass="label" text="Fecha de Registro hasta" />
																			</td>
																			<td>
																				<ui:textField binding="#{expedientes$ABMExpediente$AdminExpediente.tfFechaRegistroHasta}" id="tfFechaRegistroHasta"
																					styleClass="textField" columns="10" />
																			</td>
																		</tr>
																		<tr>
																			<td align="right" nowrap="nowrap">
																				<ui:label binding="#{expedientes$ABMExpediente$AdminExpediente.lblPersonaSeleccionada}" for="tfPersonaSeleccionada"
																					id="label2" styleClass="label" text="Interesado" />
																			</td>
																			<td colspan="4" nowrap="nowrap">
																				<ui:textField binding="#{expedientes$ABMExpediente$AdminExpediente.tfPersonaSeleccionada}" columns="40"
																					id="tfInteresado"
																					styleClass="#{expedientes$ABMExpediente$AdminExpediente.paginatedTable.filtro.interesado != null ? 'textFieldDisabled' : 'textField'}"
																					disabled="#{expedientes$ABMExpediente$AdminExpediente.paginatedTable.filtro.interesado != null}" />
																				<ui:button action="#{expedientes$ABMExpediente$AdminExpediente.btnSeleccionarPersonaFisica_action}"
																					binding="#{expedientes$ABMExpediente$AdminExpediente.btnSeleccionarPersonaFisica}" escape="false"
																					id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Fisica" />
																				<ui:button action="#{expedientes$ABMExpediente$AdminExpediente.btnSeleccionarPersonaJuridica_action}"
																					binding="#{expedientes$ABMExpediente$AdminExpediente.btnSeleccionarPersonaJuridica}" escape="false"
																					id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar Persona Jurídica" />
																				<a4j:commandButton action="#{expedientes$ABMExpediente$AdminExpediente.btnLimpiarPersona_action}"
																					binding="#{expedientes$ABMExpediente$AdminExpediente.btnLimpiarPersona}" title="Limpiar" id="btnLimpiarPersona"
																					styleClass="buttonLimpiarAjax" reRender="tfInteresado"
																					oncomplete="cargarComportamientoJQuery(); focusearTfInteresadoSeleccionada();" />
																			</td>
																		</tr>
																		<tr>
																			<td align="right" nowrap="nowrap">
																				<ui:label binding="#{expedientes$ABMExpediente$AdminExpediente.labelProcedimiento}" for="ddProcedimiento"
																					id="labelProcedimiento" styleClass="label" text="Procedimiento" />
																			</td>
																			<td colspan="4">
																				<ui:dropDown binding="#{expedientes$ABMExpediente$AdminExpediente.ddProcedimiento}" id="ddProcedimiento"
																					items="#{expedientes$ABMExpediente$AdminExpediente.ddProcedimientoOptions.options}" styleClass="textField" />
																				<ui:button action="#{expedientes$ABMExpediente$AdminExpediente.btnSeleccionarProcedimiento_action}"
																					binding="#{expedientes$ABMExpediente$AdminExpediente.btnSeleccionarProcedimiento}" escape="false"
																					id="btnSeleccionarProcedimiento" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
																				<a4j:commandButton id="btnLimpiarProcedimiento" reRender="ddProcedimiento" title="Limpiar"
																					binding="#{expedientes$ABMExpediente$AdminExpediente.btnLimpiarProcedimiento}"
																					action="#{expedientes$ABMExpediente$AdminExpediente.btnLimpiarProcedimiento_action}" styleClass="buttonLimpiarAjax" />
																			</td>
																		</tr>
																		<tr>
																			<td align="right" nowrap="nowrap">
																				<ui:label binding="#{expedientes$ABMExpediente$AdminExpediente.lblEstado}" for="ddEstado" id="lblEstado"
																					styleClass="label" text="Estado" />
																			</td>
																			<td colspan="4">
																				<ui:dropDown binding="#{expedientes$ABMExpediente$AdminExpediente.ddEstado}" id="ddEstado"
																					items="#{expedientes$ABMExpediente$AdminExpediente.ddEstadoOptions.options}" styleClass="textField" />
																			</td>
																		</tr>
																	</table>
																</ui:panelGroup>
															</td>
														</tr>
													</tbody>
													<tr>
														<td></td>
													</tr>
													<tfoot>
														<tr>
															<td align="right" colspan="2">
																<a4j:commandButton binding="#{expedientes$ABMExpediente$AdminExpediente.btnBuscar}"
																	action="#{expedientes$ABMExpediente$AdminExpediente.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
																	reRender="table1, stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
																<a4j:commandButton action="#{expedientes$ABMExpediente$AdminExpediente.btnReiniciar_action}"
																	binding="#{expedientes$ABMExpediente$AdminExpediente.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
																	value="Reiniciar" reRender="pgParametros, table1, stCantidadRegistros" oncomplete="cargarComportamientoJQuery();" />
																<ui:staticText binding="#{expedientes$ABMExpediente$AdminExpediente.stSeparador1}" escape="false" id="stSeparador1"
																	text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
																<ui:button action="#{expedientes$ABMExpediente$AdminExpediente.btnCancelar_action}"
																	binding="#{expedientes$ABMExpediente$AdminExpediente.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
															</td>
														</tr>
													</tfoot>
												</table>
												<a4j:outputPanel ajaxRendered="true">
													<div>
														<ui:messageGroup binding="#{expedientes$ABMExpediente$AdminExpediente.messageGroup}" styleClass="grupoMsgAdmin"
															id="messageGroup" showDetail="true" showSummary="false" />
													</div>
												</a4j:outputPanel>
												<div class="divGeneral">
													<table class="general">
														<tr>
															<td>
																<ui:table binding="#{expedientes$ABMExpediente$AdminExpediente.paginatedTable}" id="table1">
																	<f:facet name="actionsTop">
																		<ui:panelGroup binding="#{expedientes$ABMExpediente$AdminExpediente.groupPanel1}" id="groupPanel1" style="">
																			<ui:button action="#{expedientes$ABMExpediente$AdminExpediente.btnSeleccionar_action}"
																				binding="#{expedientes$ABMExpediente$AdminExpediente.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
																				text="Seleccionar" />
																			<ui:staticText binding="#{expedientes$ABMExpediente$AdminExpediente.stSeparadorSeleccionar}" escape="false"
																				id="stSeparador2" />
																			<ui:button action="#{expedientes$ABMExpediente$AdminExpediente.btnAgregar_action}"
																				binding="#{expedientes$ABMExpediente$AdminExpediente.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
																			<ui:button action="#{expedientes$ABMExpediente$AdminExpediente.btnModificar_action}"
																				binding="#{expedientes$ABMExpediente$AdminExpediente.btnModificar}" id="btnModificar" styleClass="button"
																				text="Modificar" />
																			<ui:button action="#{expedientes$ABMExpediente$AdminExpediente.btnEliminar_action}"
																				binding="#{expedientes$ABMExpediente$AdminExpediente.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar" />
																			<ui:staticText binding="#{expedientes$ABMExpediente$AdminExpediente.stSeparadorAccion}" escape="false" id="stSeparador3" />
																			<ui:button action="#{expedientes$ABMExpediente$AdminExpediente.btnConsultar_action}"
																				binding="#{expedientes$ABMExpediente$AdminExpediente.btnConsultar}" id="btnConsultar" styleClass="button"
																				text="Consultar" />
																			<ui:staticText binding="#{expedientes$ABMExpediente$AdminExpediente.stSeparador4}" escape="false" id="stSeparador4"
																				text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
																			<ui:button action="#{expedientes$ABMExpediente$AdminExpediente.btnExportar_action}"
																				binding="#{expedientes$ABMExpediente$AdminExpediente.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
																				onClick="newWindow = window.open('/Vipians/faces/ImpresionServlet', 'Exportar');" />
																			<ui:button action="#{expedientes$ABMExpediente$AdminExpediente.btnImprimir_action}"
																				binding="#{expedientes$ABMExpediente$AdminExpediente.btnImprimir}" id="btnImprimir"
																				onClick="newWindow = window.open('/Vipians/faces/ImpresionServlet', 'Reporte')" styleClass="button" text="Imprimir Alta" />
																			<ui:staticText binding="#{expedientes$ABMExpediente$AdminExpediente.paginatedTable.stSeparadorOrdenamiento}"
																				id="separador_1" />
																			<ui:imageHyperlink binding="#{expedientes$ABMExpediente$AdminExpediente.paginatedTable.botonOrdenamiento}" />
																		</ui:panelGroup>
																	</f:facet>
																</ui:table>
															</td>
														</tr>
													</table>
												</div>
											</ui:tab>
											<ui:tab id="tabListaTrabajo" binding="#{expedientes$ABMExpediente$AdminExpediente.tabListaTrabajo}" text="Lista Trabajo">
												<table border="0" class="azul" width="100%">
													<tbody>
														<tr>
															<td colspan="3" style="background-color: rgb(221, 221, 221); border-radius: 5px 5px 0 0;" valign="top">
																<ui:checkbox style="font-weight: bold"
																	binding="#{expedientes$ABMExpediente$AdminExpediente.panelListaTrabajo.chPlazoVencido}" id="chPlazoVencido"
																	label="Solo plazos vencidos">
																	<a4j:support actionListener="#{expedientes$ABMExpediente$AdminExpediente.seleccionarOpcionVencidos}" event="onChange"
																		reRender="lbProcedimientos, lbFases, lbTramites, tableExpedientes, form1:stCantidadRegistros" />
																</ui:checkbox>
															</td>
														</tr>
														<tr>
															<td align="center">
																<ui:label binding="#{expedientes$ABMExpediente$AdminExpediente.panelListaTrabajo.labelProcedimientos}"
																	for="lbProcedimientos" id="LT_labelProcedimiento" styleClass="label" text="Procedimiento" />
															</td>
															<td align="center">
																<ui:label binding="#{expedientes$ABMExpediente$AdminExpediente.panelListaTrabajo.labelFases}" for="lbFases"
																	id="LT_labelFases" styleClass="label" text="Fases" />
															</td>
															<td align="center">
																<ui:label binding="#{expedientes$ABMExpediente$AdminExpediente.panelListaTrabajo.labelTramites}" for="lbTramites"
																	id="LT_labelTramies" styleClass="label" text="Tramites" />
															</td>
														</tr>
														<tr>
															<td valign="top" align="center">
																<ui:listbox multiple="true"
																	converter="#{expedientes$ABMExpediente$AdminExpediente.panelListaTrabajo.converterProcedimiento}"
																	binding="#{expedientes$ABMExpediente$AdminExpediente.panelListaTrabajo.lbProcedimientos}" id="lbProcedimientos"
																	items="#{expedientes$ABMExpediente$AdminExpediente.panelListaTrabajo.procedimientosOptions}" rows="6" style="width:250px"
																	styleClass="textField" toolTip="Ctrl + click para selección múltiple">
																	<a4j:support reRender="lbFases, lbTramites, tableExpedientes, form1:stCantidadRegistros"
																		actionListener="#{expedientes$ABMExpediente$AdminExpediente.seleccionarOpcionProcedimientos}" event="onChange" />
																</ui:listbox>
															</td>
															<td align="center">
																<ui:listbox multiple="true" converter="#{expedientes$ABMExpediente$AdminExpediente.panelListaTrabajo.converterFaseCatalogo}"
																	binding="#{expedientes$ABMExpediente$AdminExpediente.panelListaTrabajo.lbFases}" id="lbFases"
																	items="#{expedientes$ABMExpediente$AdminExpediente.panelListaTrabajo.fasesOptions}" rows="6" style="width:250px"
																	styleClass="textField" toolTip="Ctrl + click para selección múltiple">
																	<a4j:support reRender="lbTramites, tableExpedientes, form1:stCantidadRegistros"
																		actionListener="#{expedientes$ABMExpediente$AdminExpediente.seleccionarOpcionFases}" event="onChange" />
																</ui:listbox>
															</td>
															<td align="center">
																<ui:listbox multiple="true"
																	converter="#{expedientes$ABMExpediente$AdminExpediente.panelListaTrabajo.converterTramiteCatalogo}"
																	binding="#{expedientes$ABMExpediente$AdminExpediente.panelListaTrabajo.lbTramites}" id="lbTramites"
																	items="#{expedientes$ABMExpediente$AdminExpediente.panelListaTrabajo.tramitesOptions}" rows="6" style="width:250px"
																	styleClass="textField" toolTip="Ctrl + click para selección múltiple">
																	<a4j:support actionListener="#{expedientes$ABMExpediente$AdminExpediente.seleccionarOpcionTramites}" event="onChange"
																		reRender="tableExpedientes, form1:stCantidadRegistros" />
																</ui:listbox>
															</td>
														</tr>
														<tr>
															<td colspan="3"></td>
														</tr>
													</tbody>
												</table>
												<a4j:outputPanel ajaxRendered="true">
													<div>
														<ui:messageGroup id="messageGroup1" showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
													</div>
												</a4j:outputPanel>
												<div class="divGeneral">
													<table class="general">
														<tbody>
															<tr>
																<td colspan="3">
																	<ui:table augmentTitle="false" paginationControls="true"
																		binding="#{expedientes$ABMExpediente$AdminExpediente.panelListaTrabajo.tableExpedientes.table}" id="tableExpedientes">
																		<ui:tableRowGroup binding="#{expedientes$ABMExpediente$AdminExpediente.panelListaTrabajo.tableExpedientes.tableRowGroup1}"
																			id="tableRowGroup1"
																			sourceData="#{expedientes$ABMExpediente$AdminExpediente.panelListaTrabajo.tableExpedientes.objectListDataProvider}"
																			sourceVar="currentRow">
																			<ui:tableColumn align="center"
																				binding="#{expedientes$ABMExpediente$AdminExpediente.panelListaTrabajo.tableExpedientes.tableColumn1}" id="tableColumn1"
																				valign="middle" width="10">
																				<ui:radioButton binding="#{expedientes$ABMExpediente$AdminExpediente.panelListaTrabajo.tableExpedientes.radioButton1}"
																					id="radioButton1" label=""
																					name="#{expedientes$ABMExpediente$AdminExpediente.panelListaTrabajo.tableExpedientes.nombreButtonGroup}"
																					selected="#{expedientes$ABMExpediente$AdminExpediente.panelListaTrabajo.tableExpedientes.RBSelected}"
																					selectedValue="#{expedientes$ABMExpediente$AdminExpediente.panelListaTrabajo.tableExpedientes.currentRow}" />
																			</ui:tableColumn>
																			<ui:tableColumn headerText="Nro Registro" id="tableColumnNroRegistro" sort="nroRegistro" width="20">
																				<ui:staticText id="staticTextNroRegistro" text="#{currentRow.value['nroRegistro']}" />
																			</ui:tableColumn>
																			<ui:tableColumn headerText="Fecha Registro" id="tableColumnFechaRegistro" sort="fechaRegistro" width="30">
																				<ui:staticText id="staticTextFechaRegistro" text="#{currentRow.value['fechaRegistro']}"
																					converter="#{expedientes$ABMExpediente$AdminExpediente.panelListaTrabajo.tableExpedientes.dateTimeConverter}" />
																			</ui:tableColumn>
																			<ui:tableColumn headerText="Procedimiento" id="tableColumn2" sort="nodoProcedimiento" width="40">
																				<ui:staticText id="staticText1" text="#{currentRow.value['nodoProcedimiento']}" />
																			</ui:tableColumn>
																			<ui:tableColumn headerText="Interesado" id="tableColumnInteresado" sort="stringInteresado" width="40">
																				<ui:staticText id="staticTextInteresado" text="#{currentRow.value['stringInteresado']}" />
																			</ui:tableColumn>
																			<ui:tableColumn headerText="Estado" id="tableColumnEstado" sort="estado" width="30">
																				<ui:staticText id="staticTextEstado" text="#{currentRow.value['estado']}" />
																			</ui:tableColumn>
																			<ui:tableColumn headerText="Fase Activa" id="tableColumnFase" width="50" sort="stringFaseActiva">
																				<ui:staticText id="staticTextFase" text="#{currentRow.value['stringFaseActivaSegunPermisos']}" />
																			</ui:tableColumn>
																		</ui:tableRowGroup>
																		<f:facet name="actionsTop">
																			<ui:panelGroup binding="#{expedientes$ABMExpediente$AdminExpediente.panelListaTrabajo.tableExpedientes.groupPanel1}"
																				id="groupPanel1">
																				<ui:button action="#{expedientes$ABMExpediente$AdminExpediente.btnModificarListaTrabajo_action}"
																					binding="#{expedientes$ABMExpediente$AdminExpediente.panelListaTrabajo.tableExpedientes.btnModificar}"
																					id="btnModificar" styleClass="button" text="Modificar" />
																				<ui:button action="#{expedientes$ABMExpediente$AdminExpediente.btnConsultarListaTrabajo_action}"
																					binding="#{expedientes$ABMExpediente$AdminExpediente.panelListaTrabajo.tableExpedientes.btnConsultar}" id="btnConultar"
																					styleClass="button" text="Consultar" />
																			</ui:panelGroup>
																		</f:facet>
																	</ui:table>
																</td>
															</tr>
														</tbody>
													</table>
												</div>
											</ui:tab>
										</ui:tabSet>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{expedientes$ABMExpediente$AdminExpediente.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{expedientes$ABMExpediente$AdminExpediente.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfAsunto').focus();
					</script>
					<ui:hiddenField binding="#{expedientes$ABMExpediente$AdminExpediente.hidIdPagina}" id="hidIdPagina"
						text="#{expedientes$ABMExpediente$AdminExpediente.idPagina}" />
					<ui:hiddenField binding="#{expedientes$ABMExpediente$AdminExpediente.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{expedientes$ABMExpediente$AdminExpediente.idSubSesion}" />
					<ui:script binding="#{expedientes$ABMExpediente$AdminExpediente.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>