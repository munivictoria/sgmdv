<?xml version="1.0" encoding="UTF-8"?>
<!-- © Copyright 2015, CoDeSoft Todos los derechos reservados. -->
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{expedientes$ABMResponsable$ABMResponsable.page1}" id="page1">
			<ui:html binding="#{expedientes$ABMResponsable$ABMResponsable.html1}" id="html1">
			<ui:head binding="#{expedientes$ABMResponsable$ABMResponsable.head1}" id="head1">
				<ui:link binding="#{expedientes$ABMResponsable$ABMResponsable.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{expedientes$ABMResponsable$ABMResponsable.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{expedientes$ABMResponsable$ABMResponsable.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{expedientes$ABMResponsable$ABMResponsable.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{expedientes$ABMResponsable$ABMResponsable.head1.title}" />
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
													<ui:label binding="#{expedientes$ABMResponsable$ABMResponsable.lblNombre}" for="tfNombre" id="lblNombre" styleClass="label"
														text="Nombre" />
												</td>
												<td>
													<ui:textField binding="#{expedientes$ABMResponsable$ABMResponsable.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
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
													<ui:label binding="#{expedientes$ABMResponsable$ABMResponsable.tituloArea}" id="tituloArea" styleClass="label2"
														text="Catálogo de Trámites" />
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<ui:table augmentTitle="false" binding="#{expedientes$ABMResponsable$ABMResponsable.tableArea}" id="tableArea">
														<script>
															<![CDATA[
															/* ----- Functions for Table Preferences Panel ----- */
															/*
															 * Toggle the table preferences panel open or closed
															 */
															function togglePreferencesPanel() {
																var table = document.getElementById("form1:tableArea");
																table.toggleTblePreferencesPanel();
															}
															/* ----- Functions for Filter Panel ----- */
															/*
															 * Return true if the filter menu has actually changed,
															 * so the corresponding event should be allowed to continue.
															 */
															function filterMenuChanged() {
																var table = document.getElementById("form1:tableArea");
																return table.filterMenuChanged();
															}
															/*
															 * Toggle the custom filter panel (if any) open or closed.
															 */
															function toggleFilterPanel() {
																var table = document.getElementById("form1:tableArea");
																return table.toggleTableFilterPanel();
															}
															/* ----- Functions for Table Actions ----- */
															/*
															 * Initialize all rows of the table when the state
															 * of selected rows changes.
															 */
															function initAllRows() {
																var table = document.getElementById("form1:tableArea");
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
																var table = document.getElementById("form1:tableArea");
																table.selectGroupRows(rowGroupId, selected);
															}
															/*
															 * Disable all table actions if no rows have been selected.
															 */
															function disableActions() {
																// Determine whether any rows are currently selected
																var table = document.getElementById("form1:tableArea");
																var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
																// Set disabled state for top actions
																document.getElementById("form1:tableArea:tableActionsTop:deleteTop").setDisabled(
																		disabled);
																// Set disabled state for bottom actions
																document.getElementById("form1:tableArea:tableActionsBottom:deleteBottom")
																		.setDisabled(disabled);
															}
															]]>
														</script>
														<ui:tableRowGroup binding="#{expedientes$ABMResponsable$ABMResponsable.tableRowGroup1}" id="tableRowGroup1"
															sourceData="#{expedientes$ABMResponsable$ABMResponsable.objectListDataProviderArea}" sourceVar="currentRow">
															<ui:tableColumn align="center" binding="#{expedientes$ABMResponsable$ABMResponsable.tableColumn1}" id="tableColumn1"
																valign="middle" width="10">
																<ui:radioButton binding="#{expedientes$ABMResponsable$ABMResponsable.radioButton1}" id="radioButton1" label=""
																	name="buttonGroup" selected="#{expedientes$ABMResponsable$ABMResponsable.RBSelected}"
																	selectedValue="#{expedientes$ABMResponsable$ABMResponsable.currentRow}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{expedientes$ABMResponsable$ABMResponsable.tableColumn2}" headerText="Nombre" id="tableColumn2"
																sort="nombre" width="40">
																<ui:staticText binding="#{expedientes$ABMResponsable$ABMResponsable.staticText1}" id="staticText1"
																	text="#{currentRow.value['nombre']}" />
															</ui:tableColumn>
														</ui:tableRowGroup>
														<f:facet name="actionsTop">
															<ui:panelGroup binding="#{expedientes$ABMResponsable$ABMResponsable.groupPanel1}" id="groupPanel1">
																<ui:button action="#{expedientes$ABMResponsable$ABMResponsable.btnAgregarArea_action}"
																	binding="#{expedientes$ABMResponsable$ABMResponsable.btnAgregarArea}" id="btnAgregar" styleClass="button" text="Agregar" />
																<a4j:commandButton action="#{expedientes$ABMResponsable$ABMResponsable.btnQuitarArea_action}"
																	binding="#{expedientes$ABMResponsable$ABMResponsable.btnQuitarArea}" id="btnQuitar" value="Quitar" styleClass="btnAjax"
																	reRender="tableArea" onmousedown="reemplazarClickConConfirmacion(this, '');" />
																<ui:staticText binding="#{expedientes$ABMResponsable$ABMResponsable.staticText4}" escape="false" id="staticText4"
																	text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
																<a4j:commandButton action="#{expedientes$ABMResponsable$ABMResponsable.btnQuitarTodosArea_action}"
																	binding="#{expedientes$ABMResponsable$ABMResponsable.btnQuitarTodosArea}" id="btnQuitarTodos" value="Quitar todos"
																	styleClass="btnAjax" reRender="tableArea"
																	onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea quitar todos los elementos de esta lista?');" />
															</ui:panelGroup>
														</f:facet>
													</ui:table>
												</td>
											</tr>
											<tr>
												<td>
													<br />
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<ui:messageGroup binding="#{expedientes$ABMResponsable$ABMResponsable.messageGroup}" id="messageGroup" styleClass="grupoMsg" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="true">
										<ui:button action="#{expedientes$ABMResponsable$ABMResponsable.btnGuardar_action}"
											binding="#{expedientes$ABMResponsable$ABMResponsable.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{expedientes$ABMResponsable$ABMResponsable.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{expedientes$ABMResponsable$ABMResponsable.btnCancelar_action}"
											binding="#{expedientes$ABMResponsable$ABMResponsable.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{expedientes$ABMResponsable$ABMResponsable.hidIdPagina}" id="hidIdPagina"
						text="#{expedientes$ABMResponsable$ABMResponsable.idPagina}" />
					<ui:hiddenField binding="#{expedientes$ABMResponsable$ABMResponsable.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{expedientes$ABMResponsable$ABMResponsable.idSubSesion}" />
					<ui:script binding="#{expedientes$ABMResponsable$ABMResponsable.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>