<?xml version="1.0" encoding="UTF-8"?>
<!-- © Copyright 2015, CoDeSoft Todos los derechos reservados. -->
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.page1}" id="page1">
			<ui:html binding="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.html1}" id="html1">
			<ui:head binding="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.head1}" id="head1">
				<ui:link binding="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<table align="center" width="70%">
											<tr>
												<td>
													<br />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="true">
													<ui:label binding="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.lblNombre}" for="tfNombre" id="lblNombre"
														styleClass="label" text="Nombre" />
												</td>
												<td>
													<ui:textField binding="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.tfNombre}" columns="40" id="tfNombre"
														styleClass="textField" />
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<hr />
													<br />
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<ui:label binding="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.table.titulo}" id="tituloTP" styleClass="label2"
														text="Trámites de Fase" />
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<ui:table augmentTitle="false" binding="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.table.table}" id="tableTP">
														<script>
															<![CDATA[
															/* ----- Functions for Table Preferences Panel ----- */
															/*
															 * Toggle the table preferences panel open or closed
															 */
															function togglePreferencesPanel() {
																var table = document.getElementById("form1:tableTP");
																table.toggleTblePreferencesPanel();
															}
															/* ----- Functions for Filter Panel ----- */
															/*
															 * Return true if the filter menu has actually changed,
															 * so the corresponding event should be allowed to continue.
															 */
															function filterMenuChanged() {
																var table = document.getElementById("form1:tableTP");
																return table.filterMenuChanged();
															}
															/*
															 * Toggle the custom filter panel (if any) open or closed.
															 */
															function toggleFilterPanel() {
																var table = document.getElementById("form1:tableTP");
																return table.toggleTableFilterPanel();
															}
															/* ----- Functions for Table Actions ----- */
															/*
															 * Initialize all rows of the table when the state
															 * of selected rows changes.
															 */
															function initAllRows() {
																var table = document.getElementById("form1:tableTP");
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
															function selectGroupRows(rowGroupId, selected) {
																var table = document.getElementById("form1:tableTP");
																table.selectGroupRows(rowGroupId, selected);
															}
															/*
															 * Disable all table actions if no rows have been selected.
															 */
															function disableActions() {
																// Determine whether any rows are currently selected
																var table = document.getElementById("form1:tableTP");
																var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
																// Set disabled state for top actions
																document.getElementById("form1:tableTP:tableActionsTop:deleteTop").setDisabled(
																		disabled);
																// Set disabled state for bottom actions
																document.getElementById("form1:tableTP:tableActionsBottom:deleteBottom").setDisabled(
																		disabled);
															}
															]]>
														</script>
														<ui:tableRowGroup binding="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.table.tableRowGroup1}" id="tableRowGroup1"
															sourceData="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.table.objectListDataProvider}" sourceVar="currentRow">
															<ui:tableColumn align="center" binding="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.table.tableColumn1}"
																id="tableColumn1" valign="middle" width="10">
																<ui:radioButton binding="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.table.radioButton1}" id="radioButton1"
																	label="" name="buttonGroup" selected="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.table.RBSelected}"
																	selectedValue="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.table.currentRow}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.table.tableColumn2}" headerText="Nombre"
																id="tableColumn2" sort="nombre" width="40">
																<ui:staticText binding="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.table.staticText1}" id="staticText1"
																	text="#{currentRow.value['nombre']}" />
															</ui:tableColumn>
														</ui:tableRowGroup>
														<f:facet name="actionsTop">
															<ui:panelGroup binding="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.table.groupPanel1}" id="groupPanel1">
																<ui:button action="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.btnAgregarTP_action}"
																	binding="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.table.btnAgregar}" id="btnAgregar" styleClass="button"
																	text="Agregar" />
																<a4j:commandButton action="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.btnQuitarTP_action}"
																	binding="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.table.btnQuitar}" id="btnQuitar" value="Quitar"
																	styleClass="btnAjax" reRender="tableTP" onmousedown="reemplazarClickConConfirmacion(this, '');" />
																<ui:staticText binding="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.table.staticText4}" escape="false"
																	id="staticText4" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
																<a4j:commandButton action="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.btnQuitarTodosTP_action}"
																	binding="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.table.btnQuitarTodos}" id="btnQuitarTodos"
																	value="Quitar todos" styleClass="btnAjax" reRender="tableTP"
																	onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea quitar todos los elementos de esta lista?');" />
															</ui:panelGroup>
														</f:facet>
													</ui:table>
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<ui:messageGroup binding="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.messageGroup}" id="messageGroup"
														styleClass="grupoMsg" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="true">
										<ui:button action="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.btnGuardar_action}"
											binding="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.btnCancelar_action}"
											binding="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.hidIdPagina}" id="hidIdPagina"
						text="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.idPagina}" />
					<ui:hiddenField binding="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.idSubSesion}" />
					<ui:script binding="#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>