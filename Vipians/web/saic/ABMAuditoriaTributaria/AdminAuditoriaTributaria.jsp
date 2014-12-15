<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.page1}" id="page1">
			<ui:html binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.html1}" id="html1">
			<ui:head binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.head1}" id="head1"
				title="Administración de Auditoría Tributaria">
				<ui:link binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.body1}" focus="form1:btnSeleccionarPersonaJuridica" id="body1"
				onLoad="parent.footer.location.reload(); Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td colspan="2"></td>
									</tr>
									<tr>
										<td align="center">
											<ui:panelGroup binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.label2}" for="tfPersonaFisica" id="label2"
																styleClass="label" text="Persona" />
														</td>
														<td>
															<ui:textField binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.tfPersona}" columns="50" disabled="true"
																id="tfPersona" styleClass="textField" />
															<ui:button action="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.btnSeleccionarPersonaFisica_action}"
																binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.btnSeleccionarPersonaFisica}" escape="false"
																id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Fisica" />
															<ui:button action="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.btnSeleccionarPersonaJuridica_action}"
																binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.btnSeleccionarPersonaJuridica}" escape="false"
																id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar Persona Juridica" />
															<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersona" title="Limpiar"
																binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.btnLimpiarPersona}"
																action="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.btnLimpiarPersona_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.label5}" for="ddTipoTasa" id="label5"
																styleClass="label" text="Tipo de obligación" />
														</td>
														<td>
															<ui:dropDown binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.ddTipoTasa}" id="ddTipoTasa"
																items="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.ddTipoTasaDefaultOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.label3}" for="ddEstado" id="label3"
																styleClass="label" text="Estado" />
														</td>
														<td>
															<ui:dropDown binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.ddEstado}" id="ddEstado"
																items="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.ddEstadoDefaultOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.lblFechaDesde}" id="lblFechaDesde"
																styleClass="label" text="Fecha Desde" />
														</td>
														<td>
															<ui:textField binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.tfFechaDesde}" id="tfFechaDesde"
																styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
															<!--<ui:staticText binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.staticText12}" escape="false"
                                                               id="staticText12" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.lblFechaHasta}" id="lblFechaHasta"
																styleClass="label" text="Fecha Hasta" />
														</td>
														<td>
															<ui:textField binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.tfFechaHasta}" id="tfFechaHasta"
																styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
															<!--<ui:staticText binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.staticText13}" escape="false"
                                                               id="staticText13" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/> -->
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
											<a4j:commandButton binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.btnBuscar}"
												action="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.btnReiniciar_action}"
												binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1" />
											<ui:staticText binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.btnCancelar_action}"
												binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.table1}" id="table1" width="622">
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
											<ui:tableRowGroup binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.tableRowGroup1}"
												emptyDataMsg="Ningún registro encontrado." id="tableRowGroup1" onMouseOver="jsRowMouseOver(this)"
												onMouseOut="jsRowMouseOut(this)" onClick="jsRowMouseClick(this)" onDblClick="funcionSeleccionar()"
												sourceData="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.ldpAuditoriaTributaria}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.tableColumn1}" id="tableColumn1"
													valign="middle" width="10">
													<ui:radioButton binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.radioButton1}" id="radioButton1" label=""
														onClick="checkUncheck(this)" name="buttonGroup" selected="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.RBSelected}"
														selectedValue="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.tableColumn5}" headerText="Contribuyente"
													id="tableColumn5" noWrap="true" sort="contribuyente" width="20">
													<ui:staticText binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.staticText3}" id="staticText3"
														text="#{currentRow.value['contribuyente']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.tableColumn2}" headerText="Tipo de Obligación"
													id="tableColumn2" sort="tipoObligacion" width="550">
													<ui:staticText binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.staticText1}" id="staticText1"
														text="#{currentRow.value['tipoObligacion']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.tableColumn3}" headerText="Fecha de Creación"
													id="tableColumn3" sort="fechaCreacion" width="10">
													<ui:staticText binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.staticText9}"
														converter="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.dateTimeConverter1}" id="staticText9"
														text="#{currentRow.value['fechaCreacion']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.tableColumn4}" headerText="Monto"
													id="tableColumn4" sort="monto" width="10">
													<ui:staticText binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.staticText4}"
														converter="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.numberConverter1}" id="staticText4"
														text="#{currentRow.value['monto']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.tableColumn6}" headerText="Estado"
													id="tableColumn6" sort="estado" width="10">
													<ui:staticText binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.staticText7}" id="staticText7"
														text="#{currentRow.value['estado']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.groupPanel1}" id="groupPanel1"
													separator="&lt;br/&gt;&lt;br/&gt;">
													<ui:panelGroup binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.gpBotones}" id="gpBotones">
														<ui:button action="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.btnSeleccionar_action}"
															binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
															text="Seleccionar" />
														<ui:staticText binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.staticText6}" escape="false" id="staticText6"
															text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.btnAgregar_action}"
															binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.btnAgregar}" id="btnAgregar" styleClass="button"
															text="Agregar" />
														<ui:button action="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.btnModificar_action}"
															binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.btnModificar}" id="btnModificar" styleClass="button"
															text="Modificar" />
														<ui:button action="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.btnEliminar_action}"
															binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.btnEliminar}" id="btnEliminar" styleClass="button"
															text="Eliminar" />
														<ui:staticText binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.staticText8}" escape="false" id="staticText8"
															text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.btnEnJuicio_action}"
															binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.btnEnJuicio}" id="btnEnJuicio" styleClass="button"
															text="a Juicio" />
														<ui:button action="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.btnProvisorio_action}"
															binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.btnProvisorio}" id="btnProvisorio" styleClass="button"
															text="Provisorio" />
														<ui:button action="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.btnFirmar_action}"
															binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.btnFirmar}" id="btnFirmar" styleClass="button" text="Firmar" />
														<ui:staticText binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.staticText11}" escape="false" id="staticText11"
															text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.btnConsultar_action}"
															binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.btnConsultar}" id="btnConsultar" styleClass="button"
															text="Consultar" />
														<!--                                                         
                                                    <ui:button action="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.btnImprimirReporte_action}"
                                                               binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.btnImprimirReporte}"
                                                               id="btnImprimirReporte" styleClass="button" text="Visualizar Reporte"
                                                               onClick="newWindow = window.open('ImprimirAuditoriaTributaria.jsp', '_parent')"
                                                               />
                                                    -->
														<ui:staticText binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.staticText14}" escape="false" id="staticText14"
															text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.btnExportar_action}"
															binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.btnExportar}" id="btnExportar" styleClass="button"
															text="Exportar" onClick="return exportarReporte()" />
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
								'form1:btnSeleccionarPersonaJuridica').focus();
					</script>
					<ui:hiddenField binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.hidIdPagina}" id="hidIdPagina"
						text="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.idPagina}" />
					<ui:hiddenField binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.idSubSesion}" />
					<ui:script binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{saic$ABMAuditoriaTributaria$AdminAuditoriaTributaria.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
