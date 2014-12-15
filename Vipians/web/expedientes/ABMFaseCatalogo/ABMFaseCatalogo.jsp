<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.page1}" id="page1">
			<ui:html binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.html1}" id="html1">
			<ui:head binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.head1}" id="head1">
				<ui:link binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.lblNombre}" for="tfNombre" id="lblNombre" styleClass="label"
											text="Nombre" />
									</td>
									<td>
										<ui:textField binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
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
										<ui:label binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.tableTC.titulo}" id="tituloTC" styleClass="label2"
											text="Catálogo de Trámites" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.tableTC.table}" id="tableTC">
											<script>
												<![CDATA[
												/* ----- Functions for Table Preferences Panel ----- */
												/*
												 * Toggle the table preferences panel open or closed
												 */
												function togglePreferencesPanel() {
													var table = document.getElementById("form1:tableTC");
													table.toggleTblePreferencesPanel();
												}
												/* ----- Functions for Filter Panel ----- */
												/*
												 * Return true if the filter menu has actually changed,
												 * so the corresponding event should be allowed to continue.
												 */
												function filterMenuChanged() {
													var table = document.getElementById("form1:tableTC");
													return table.filterMenuChanged();
												}
												/*
												 * Toggle the custom filter panel (if any) open or closed.
												 */
												function toggleFilterPanel() {
													var table = document.getElementById("form1:tableTC");
													return table.toggleTableFilterPanel();
												}
												/* ----- Functions for Table Actions ----- */
												/*
												 * Initialize all rows of the table when the state
												 * of selected rows changes.
												 */
												function initAllRows() {
													var table = document.getElementById("form1:tableTC");
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
													var table = document.getElementById("form1:tableTC");
													table.selectGroupRows(rowGroupId, selected);
												}
												/*
												 * Disable all table actions if no rows have been selected.
												 */
												function disableActions() {
													// Determine whether any rows are currently selected
													var table = document.getElementById("form1:tableTC");
													var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
													// Set disabled state for top actions
													document.getElementById("form1:tableTC:tableActionsTop:deleteTop").setDisabled(disabled);
													// Set disabled state for bottom actions
													document.getElementById("form1:tableTC:tableActionsBottom:deleteBottom").setDisabled(disabled);
												}
												]]>
											</script>
											<ui:tableRowGroup binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.tableTC.tableRowGroup1}" id="tableRowGroup1"
												sourceData="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.tableTC.objectListDataProvider}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.tableTC.tableColumn1}" id="tableColumn1"
													valign="middle" width="10">
													<ui:radioButton binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.tableTC.radioButton1}" id="radioButton1" label=""
														name="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.tableTC.nombreButtonGroup}" 
														selectedValue="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.tableTC.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.tableTC.tableColumn2}" headerText="Nombre"
													id="tableColumn2" sort="nombre" width="40">
													<ui:staticText binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.tableTC.staticText1}" id="staticText1"
														text="#{currentRow.value['nombre']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.tableTC.groupPanel1}" id="groupPanel1">
													<ui:button action="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.btnAgregarTC_action}"
														binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.tableTC.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<a4j:commandButton action="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.btnQuitarTC_action}"
														binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.tableTC.btnQuitar}" id="btnQuitar" value="Quitar" styleClass="btnAjax"
														reRender="tableTC" onmousedown="reemplazarClickConConfirmacion(this, '');" />
													<ui:staticText binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.tableTC.staticText4}" escape="false" id="staticText4"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<a4j:commandButton action="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.btnQuitarTodosTC_action}"
														binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.tableTC.btnQuitarTodos}" id="btnQuitarTodos" value="Quitar todos"
														styleClass="btnAjax" reRender="tableTC"
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
										<ui:label binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.lbFasesEspeciales}" id="lbFasesEspeciales" styleClass="label2"
											text="Fases de Catálogo Especiales" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.tablaFaseEspecial}" id="tablaFaseEspecial">
											<script>
												<![CDATA[
												/* ----- Functions for Table Preferences Panel ----- */
												/*
												 * Toggle the table preferences panel open or closed
												 */
												function togglePreferencesPanel() {
													var table = document.getElementById("form1:tablaFaseEspecial");
													table.toggleTblePreferencesPanel();
												}
												/* ----- Functions for Filter Panel ----- */
												/*
												 * Return true if the filter menu has actually changed,
												 * so the corresponding event should be allowed to continue.
												 */
												function filterMenuChanged() {
													var table = document.getElementById("form1:tablaFaseEspecial");
													return table.filterMenuChanged();
												}
												/*
												 * Toggle the custom filter panel (if any) open or closed.
												 */
												function toggleFilterPanel() {
													var table = document.getElementById("form1:tablaFaseEspecial");
													return table.toggleTableFilterPanel();
												}
												/* ----- Functions for Table Actions ----- */
												/*
												 * Initialize all rows of the table when the state
												 * of selected rows changes.
												 */
												function initAllRows() {
													var table = document.getElementById("form1:tablaFaseEspecial");
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
													var table = document.getElementById("form1:tablaFaseEspecial");
													table.selectGroupRows(rowGroupId, selected);
												}
												/*
												 * Disable all table actions if no rows have been selected.
												 */
												function disableActions() {
													// Determine whether any rows are currently selected
													var table = document.getElementById("form1:tablaFaseEspecial");
													var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
													// Set disabled state for top actions
													document.getElementById("form1:tablaFaseEspecial:tableActionsTop:deleteTop")
															.setDisabled(disabled);
													// Set disabled state for bottom actions
													document.getElementById("form1:tablaFaseEspecial:tableActionsBottom:deleteBottom").setDisabled(
															disabled);
												}
												]]>
											</script>
											<ui:tableRowGroup binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.tableRowGroup1}" id="tableRowGroup1"
												sourceData="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.ldpFasesEspeciales}" sourceVar="currentRowFaseEspecial">
												<ui:tableColumn align="center" binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.tableColumn1}" id="tableColumn1"
													valign="middle" width="10">
													<ui:radioButton binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.radioButton1}" id="radioButton1" label=""
														name="buttonGroup" selected="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.RBSelected}"
														selectedValue="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.currentRowFaseEspecial}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.tableColumn2}" headerText="Nombre" id="tableColumn2"
													sort="nombre" width="40">
													<ui:staticText binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.staticText1}" id="staticText1"
														text="#{currentRowFaseEspecial.value['nombre']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.groupPanel1}" id="groupPanel1">
													<ui:button action="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.btnAgregarFaseEspecial_action}"
														binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.btnAgregarFaseEspecial}" id="btnAgregarFaseEspecial"
														styleClass="button" text="Agregar" />
													<a4j:commandButton action="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.btnQuitarFaseEspecial_action}"
														binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.btnQuitarFaseEspecial}" id="btnQuitarFaseEspecial" value="Quitar"
														styleClass="btnAjax" reRender="tablaFaseEspecial" onmousedown="reemplazarClickConConfirmacion(this, '');" />
													<ui:staticText binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.staticText2}" escape="false" id="staticText2"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<a4j:commandButton action="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.btnQuitarTodas_action}"
														binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.btnQuitarTodas}" id="btnQuitarTodas" value="Quitar todas"
														styleClass="btnAjax" reRender="tablaFaseEspecial"
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
										<ui:messageGroup binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.messageGroup}" id="messageGroup" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="true">
										<ui:button action="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.btnGuardar_action}"
											binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.btnCancelar_action}"
											binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.hidIdPagina}" id="hidIdPagina"
						text="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.idPagina}" />
					<ui:hiddenField binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.idSubSesion}" />
					<ui:script binding="#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
