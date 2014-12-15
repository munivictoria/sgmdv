<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.page1}" id="page1">
			<ui:html binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.html1}" id="html1">
			<ui:head binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.head1}" id="head1"
				title="Administración de Permisos Pendientes de Firma">
				<ui:link binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.body1}" focus="form1:btnSeleccionarPersonaFisica" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td colspan="2"></td>
									</tr>
									<tr>
										<td colspan="2">
											<div>
												Haga clic en <b>Listar Firmas Pendientes</b> para ver las Obligaciones que requieren su Firma.
											</div>
										</td>
									</tr>
									<tr>
										<td colspan="2"></td>
									</tr>
									<tr>
										<td align="right" nowrap="nowrap">
											<ui:label binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.lblPersona}" for="tfPersona" id="lblPersona"
												styleClass="label" text="Persona" />
										</td>
										<td>
											<ui:textField binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.tfPersonaFisica}" columns="40" disabled="true"
												id="tfPersonaFisica" styleClass="textField" />
											<ui:button action="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.btnSeleccionarPersonaFisica_action}"
												binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.btnSeleccionarPersonaFisica}" escape="false"
												id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Fisica" />
											<ui:button action="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.btnSeleccionarPersonaJuridica_action}"
												binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.btnSeleccionarPersonaJuridica}" escape="false"
												id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar Persona Juridica" />
											<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersonaFisica" title="Limpiar"
												binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.btnLimpiarPersona}"
												action="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.btnLimpiarPersona_action}" styleClass="buttonLimpiarAjax" />
										</td>
									</tr>
								</tbody>
								<tfoot>
									<tr>
										<td align="right" colspan="2">
											<a4j:commandButton binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.btnBuscar}"
												action="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1" oncomplete="changeStyleAlIngresar()" />
											<ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.btnCancelar_action}"
												binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.messageGroup}" id="messageGroup"
										showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.table1}" id="table1">
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
											<ui:tableRowGroup binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.tableRowGroup1}"
												emptyDataMsg="Ningún registro encontrado." id="tableRowGroup1" onMouseOver="jsRowMouseOver(this)"
												onMouseOut="jsRowMouseOut(this)" onClick="jsRowMouseClick(this)" onDblClick="funcionSeleccionar()"
												sourceData="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.ldpFirmaPendiente}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.tableColumn1}" id="tableColumn1"
													valign="middle" width="10">
													<ui:radioButton binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.radioButton1}" id="radioButton1" label=""
														name="buttonGroup" onClick="checkUncheck(this)" selected="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.RBSelected}"
														selectedValue="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.tableColumn3}" headerText="Obligación"
													id="tableColumn3" sort="obligacion">
													<ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.staticText3}" id="staticText3"
														text="#{currentRow.value['obligacion']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.tableColumn4}" headerText="Nombre"
													id="tableColumn4" sort="nombre">
													<ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.staticText4}" id="staticText4"
														text="#{currentRow.value['nombre']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.tableColumn2}" headerText="Estado"
													id="tableColumn2" sort="estado">
													<ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.staticText1}" id="staticText1"
														text="#{currentRow.value['estado']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.groupPanel1}" id="groupPanel1"
													separator="&lt;br/&gt;&lt;br/&gt;">
													<ui:panelGroup binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.gpBotones}" id="gpBotones">
														<ui:button action="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.btnConsultarObligacion_action}"
															binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.btnConsultarObligacion}" id="btnConsultarObligacion"
															styleClass="button" text="Consultar Obligación" />
														<ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.staticText8}" escape="false" id="staticText8"
															text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.btnExportar_action}"
															binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.btnExportar}" id="btnExportar" styleClass="button"
															text="Exportar" onClick="return exportarReporte()" />
													</ui:panelGroup>
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
							</table>
							<br /> <br /> <br />
						</div>
					</div>
					<script>
						document.getElementById(
								'form1:btnSeleccionarPersonaFisica').focus();
					</script>
					<ui:hiddenField binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.idSubSesion}" />
					<ui:script binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{habilitaciones$ABMFirmaPendiente$AdminPermisoSinFirma.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
