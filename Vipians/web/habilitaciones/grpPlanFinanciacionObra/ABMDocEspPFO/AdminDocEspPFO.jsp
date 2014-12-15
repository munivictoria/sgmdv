<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.head1}" id="head1"
				title="Administración de Obligaciones: Planes de Financiación de Obras">
				<ui:link binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.link1}" id="link1"
					url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.body1}" focus="form1:btnSeleccionarPersonaFisica"
				id="body1" onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.stTitulo}" id="stTitulo"
										styleClass="tituloABM" text="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.head1.title}" />
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
											<ui:panelGroup binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.label2}" for="tfPersonaFisica"
																id="label2" styleClass="label" text="Persona" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.tfPersona}" columns="40"
																disabled="true" id="tfPersona" styleClass="textField" />
															<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.btnSeleccionarPersonaFisica_action}"
																binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.btnSeleccionarPersonaFisica}" escape="false"
																id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar" />
															<ui:button
																action="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.btnSeleccionarPersonaJuridica_action}"
																binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.btnSeleccionarPersonaJuridica}" escape="false"
																id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" />
															<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersona" title="Limpiar"
																binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.btnLimpiarPersona}"
																action="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.btnLimpiarPersona_action}"
																styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.label3}" for="tfObra" id="label3"
																styleClass="label" text="Obra" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.tfObra}" columns="40"
																disabled="true" id="tfObra" styleClass="textField" />
															<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.btnSeleccionarObra_action}"
																binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.btnSeleccionarObra}" escape="false"
																id="btnSeleccionarObra" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarObra" reRender="form1:tfObra" title="Limpiar"
																binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.btnLimpiarObra}"
																action="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.btnLimpiarObra_action}"
																styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.label1}" for="tfNumeroRegistro"
																id="label1" styleClass="label" text="Nº de Registro" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.tfNumeroRegistro}"
																id="tfNumeroRegistro" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td colspan="2"></td>
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
								</tbody>
								<tfoot>
									<tr>
										<td align="right" colspan="2">
											<a4j:commandButton binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.btnBuscar}"
												action="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.btnReiniciar_action}"
												binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.btnReiniciar}" id="btnReiniciar"
												styleClass="btnAjax" value="Reiniciar" reRender="form1:pgParametros,form1:table1" />
											<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.staticText2}" escape="false"
												id="staticText2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.btnCancelar_action}"
												binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.messageGroup}" id="messageGroup"
										showDetail="true" showSummary="false" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.table1}" id="table1">
											<script>
												<![CDATA[
												/* ----- Functions for Table Preferences Panel ----- */
												/*
												 * Toggle the table preferences panel open or closed
												 */
												function togglePreferencesPanel() {
													var table = document
															.getElementById("form1:table1");
													table
															.toggleTblePreferencesPanel();
												}
												/* ----- Functions for Filter Panel ----- */
												/*
												 * Return true if the filter menu has actually changed,
												 * so the corresponding event should be allowed to continue.
												 */
												function filterMenuChanged() {
													var table = document
															.getElementById("form1:table1");
													return table
															.filterMenuChanged();
												}
												/*
												 * Toggle the custom filter panel (if any) open or closed.
												 */
												function toggleFilterPanel() {
													var table = document
															.getElementById("form1:table1");
													return table
															.toggleTableFilterPanel();
												}
												/* ----- Functions for Table Actions ----- */
												/*
												 * Initialize all rows of the table when the state
												 * of selected rows changes.
												 */
												function initAllRows() {
													var table = document
															.getElementById("form1:table1");
													table.initAllRows();
												}
												/*
												 * Set the selected state for the given row groups
												 * displayed in the table.  This functionality requires
												 * the 'selectId' of the tableColumn to be set.
												 *
												 * @param rowGroupId HTML element id of the tableRowGroup component
												 * @param selected Flag indicating whether components should be selected
												 */
												function selectGroupRows(
														rowGroupId, selected) {
													var table = document
															.getElementById("form1:table1");
													table.selectGroupRows(
															rowGroupId,
															selected);
												}
												/*
												 * Disable all table actions if no rows have been selected.
												 */
												function disableActions() {
													// Determine whether any rows are currently selected
													var table = document
															.getElementById("form1:table1");
													var disabled = (table
															.getAllSelectedRowsCount() > 0) ? false
															: true;
													// Set disabled state for top actions
													document
															.getElementById(
																	"form1:table1:tableActionsTop:deleteTop")
															.setDisabled(
																	disabled);
													// Set disabled state for bottom actions
													document
															.getElementById(
																	"form1:table1:tableActionsBottom:deleteBottom")
															.setDisabled(
																	disabled);
												}
												]]>
											</script>
											<ui:tableRowGroup binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.tableRowGroup1}"
												emptyDataMsg="Ningún registro encontrado." id="tableRowGroup1" onMouseOver="jsRowMouseOver(this)"
												onMouseOut="jsRowMouseOut(this)" onClick="jsRowMouseClick(this)" onDblClick="funcionSeleccionar()"
												sourceData="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.ldpDocEspPFO}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.tableColumn1}"
													id="tableColumn1" valign="middle" width="10">
													<ui:radioButton binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.radioButton1}" id="radioButton1"
														label="" name="buttonGroup" onClick="checkUncheck(this)"
														selected="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.RBSelected}"
														selectedValue="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.tableColumn2}"
													headerText="Nº de Trámite" id="tableColumn2" sort="numeroTramite">
													<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.staticText1}" id="staticText1"
														text="#{currentRow.value['numeroTramite']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.tableColumn5}"
													headerText="Contribuyente" id="tableColumn5" sort="persona">
													<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.staticText3}" id="staticText3"
														text="#{currentRow.value['persona']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.tableColumn3}"
													headerText="Documento" id="tableColumn3" sort="documentoEspecializado">
													<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.stDocumento}" id="stDocumento"
														text="#{currentRow.value['documentoEspecializado']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.tableColumn4}" headerText="Estado"
													id="tableColumn4" sort="estado">
													<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.staticText4}" id="staticText4"
														text="#{currentRow.value['estado']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.tableColumn6}"
													headerText="Posee Exenciones" id="tableColumn6" sort="stringPoseeExenciones">
													<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.staticText9}" id="staticText9"
														text="#{currentRow.value['stringPoseeExenciones']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.groupPanel1}" id="groupPanel1"
													separator="&lt;br/&gt;&lt;br/&gt;">
													<ui:panelGroup binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.gpBotones}" id="gpBotones">
														<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.btnSeleccionar_action}"
															binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.btnSeleccionar}" id="btnSeleccionar"
															styleClass="button" text="Seleccionar" />
														<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.staticText6}" escape="false"
															id="staticText6" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.btnAgregar_action}"
															binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.btnAgregar}" id="btnAgregar"
															styleClass="button" text="Agregar" />
														<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.btnModificar_action}"
															binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.btnModificar}" id="btnModificar"
															styleClass="button" text="Modificar" />
														<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.btnEliminar_action}"
															binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.btnEliminar}" id="btnEliminar"
															styleClass="button" text="Eliminar" />
														<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.staticText7}" escape="false"
															id="staticText8" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.btnConsultar_action}"
															binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.btnConsultar}" id="btnConsultar"
															styleClass="button" text="Consultar" />
														<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.staticText5}" escape="false"
															id="staticText5" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.btnImprimirReporte_action}"
															binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.btnImprimirReporte}" disabled="true"
															id="btnImprimirReporte" styleClass="button" text="Visualizar Reporte" />
														<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.staticText10}" escape="false"
															id="staticText10" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.btnExportar_action}"
															binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.btnExportar}" id="btnExportar"
															styleClass="button" text="Exportar" onClick="return exportarReporte()" />
													</ui:panelGroup>
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById(
								'form1:btnSeleccionarPersonaFisica').focus();
					</script>
					<ui:hiddenField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AdminDocEspPFO.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
