<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMBien$ABMBien.page1}" id="page1">
			<ui:html binding="#{compras$ABMBien$ABMBien.html1}" id="html1">
			<ui:head binding="#{compras$ABMBien$ABMBien.head1}" id="head1">
				<ui:link binding="#{compras$ABMBien$ABMBien.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{compras$ABMBien$ABMBien.body1}" focus="form1:tfNombre" id="body1" onLoad="parent.footer.location.reload();Init();"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMBien$ABMBien.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{compras$ABMBien$ABMBien.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{compras$ABMBien$ABMBien.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>

									<td align="right" nowrap="true">
										<ui:label binding="#{compras$ABMBien$ABMBien.lblNombre}" for="tfNombre" id="lblNombre" styleClass="label" text="Nombre" />
									</td>
									<td>
										<ui:textField binding="#{compras$ABMBien$ABMBien.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
									</td>
									<td align="right" nowrap="true">
										<ui:label binding="#{compras$ABMBien$ABMBien.lblDescripcion}" for="taDescripcion" id="lblDescripcion" styleClass="label"
											text="Descripción" />
									</td>
									<td>
										<ui:textArea binding="#{compras$ABMBien$ABMBien.taDescripcion}" columns="40" id="taDescripcion" rows="5" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{compras$ABMBien$ABMBien.lblValorReferencial}" for="tfValorReferencial" id="lblValorReferencial"
											styleClass="label" text="Valor referencial" />
									</td>
									<td>
										<ui:textField binding="#{compras$ABMBien$ABMBien.tfValorReferencial}" columns="40" id="tfValorReferencial" styleClass="textField"
											onKeyPress="return ValidarFloat(event,this)" />
									</td>
									<td align="right" nowrap="true">
										<ui:label binding="#{compras$ABMBien$ABMBien.lblFechaUltimaActualizacion}" for="tfFechaUltimaActualizacion"
											id="lblFechaUltimaActualizacion" styleClass="label" text="Fecha Última Actualización" />
									</td>
									<td>
										<ui:textField binding="#{compras$ABMBien$ABMBien.tfFechaUltimaActualizacion}" styleClass="textFieldDisabled" columns="35"
											id="tfFechaUltimaActualizacion" disabled="true" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{compras$ABMBien$ABMBien.lblUnidad}" for="ddUnidad" id="lblUnidad" styleClass="label" text="Unidad de Medida" />
									</td>
									<td nowrap="nowrap">
										<ui:dropDown binding="#{compras$ABMBien$ABMBien.ddUnidad}" id="ddUnidad"
											items="#{compras$ABMBien$ABMBien.ddUnidadDefaultOptions.options}" styleClass="textField" />
										<ui:button action="#{compras$ABMBien$ABMBien.btnSeleccionarUnidad_action}"
											binding="#{compras$ABMBien$ABMBien.btnSeleccionarUnidad}" escape="false" id="btnSeleccionarUnidad" mini="true"
											styleClass="buttonSeleccionar" text="&amp;nbsp;" />
										<a4j:commandButton id="btnLimpiarUnidad" reRender="form1:ddUnidad" title="Limpiar"
											binding="#{compras$ABMBien$ABMBien.btnLimpiarUnidad}"
											action="#{compras$ABMBien$ABMBien.btnLimpiarUnidad_action}" styleClass="buttonLimpiarAjax" />
									</td>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{compras$ABMBien$ABMBien.lblTipo}" id="lblTipo" styleClass="label" text="Tipo" />
									</td>
									<td>
										<ui:dropDown binding="#{compras$ABMBien$ABMBien.ddTipo}" id="ddTipo"
											items="#{compras$ABMBien$ABMBien.ddTipoDefaultOptions.options}" styleClass="textField" />
									</td>
								</tr>
								</tr>
								<tr>
									<td colspan="4">
										<ui:staticText binding="#{compras$ABMBien$ABMBien.stCodigosCiiu}" id="stCodigosCiiu" styleClass="label2" text="Códigos CIIU" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{compras$ABMBien$ABMBien.table1}" id="table1" width="479">
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
											<ui:tableRowGroup binding="#{compras$ABMBien$ABMBien.tableRowGroup1}" id="tableRowGroup1"
												sourceData="#{compras$ABMBien$ABMBien.ldpCodigosCiiu}" sourceVar="currentRow"
												selected="#{compras$ABMBien$ABMBien.currentRowSelected}">
												<ui:tableColumn align="center" binding="#{compras$ABMBien$ABMBien.tableColumn1}" id="tableColumn1"
													onClick="setTimeout('initAllRows()', 0)" valign="middle" width="10">
													<ui:checkbox binding="#{compras$ABMBien$ABMBien.checkbox1}" id="checkbox1" name="buttonsGroup"
														selected="#{compras$ABMBien$ABMBien.selected}" selectedValue="#{compras$ABMBien$ABMBien.selectedValue}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMBien$ABMBien.tcCodigo}" headerText="Código" id="tcCodigo" sort="codigo">
													<ui:staticText binding="#{compras$ABMBien$ABMBien.stCodigo}" id="stCodigo" text="#{currentRow.value['codigo']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMBien$ABMBien.tcDescripcionCodigo}" headerText="Descripción" id="tcDescripcionCodigo"
													sort="descripcion">
													<ui:staticText binding="#{compras$ABMBien$ABMBien.stDescripcionCodigo}" id="stDescripcionCodigo"
														text="#{currentRow.value['descripcion']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{compras$ABMBien$ABMBien.groupPanel1}" id="groupPanel1">
													<ui:button action="#{compras$ABMBien$ABMBien.btnAgregar_action}" binding="#{compras$ABMBien$ABMBien.btnAgregar}"
														id="btnAgregar" styleClass="button" text="Agregar" />
													<a4j:commandButton action="#{compras$ABMBien$ABMBien.btnQuitar_action}" binding="#{compras$ABMBien$ABMBien.btnQuitar}" id="btnQuitar"
														value="Quitar" styleClass="btnAjax" reRender="table1" onmousedown="reemplazarClickConConfirmacion(this, '');"/>
													<ui:staticText binding="#{compras$ABMBien$ABMBien.stSeparador2}" escape="false" id="stSeparador2"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<a4j:commandButton action="#{compras$ABMBien$ABMBien.btnQuitarTodos_action}" binding="#{compras$ABMBien$ABMBien.btnQuitarTodos}"
														id="btnQuitarTodos" value="Quitar todos" styleClass="btnAjax" reRender="table1" onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea quitar todos los elementos de esta lista?');"/>
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<td colspan="4">
									<ui:staticText binding="#{compras$ABMBien$ABMBien.stTipoBien}" id="stTipoBien" styleClass="label2" text="Categoría Bien" />
								</td>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{compras$ABMBien$ABMBien.tableTipoBien}" id="tableTipoBien" width="479">
											<script>
												<![CDATA[
												/* ----- Functions for Table Preferences Panel ----- */
												/*
												 * Toggle the table preferences panel open or closed
												 */
												function togglePreferencesPanel() {
													var table = document
															.getElementById("form1:tableTipoBien");
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
															.getElementById("form1:tableTipoBien");
													return table
															.filterMenuChanged();
												}
												/*
												 * Toggle the custom filter panel (if any) open or closed.
												 */
												function toggleFilterPanel() {
													var table = document
															.getElementById("form1:tableTipoBien");
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
															.getElementById("form1:tableTipoBien");
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
															.getElementById("form1:tableTipoBien");
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
															.getElementById("form1:tableTipoBien");
													var disabled = (table
															.getAllSelectedRowsCount() > 0) ? false
															: true;
													// Set disabled state for top actions
													document
															.getElementById(
																	"form1:table3:tableActionsTop:deleteTop")
															.setDisabled(
																	disabled);
													// Set disabled state for bottom actions
													document
															.getElementById(
																	"form1:table3:tableActionsBottom:deleteBottom")
															.setDisabled(
																	disabled);
												}
												]]>
											</script>
											<ui:tableRowGroup binding="#{compras$ABMBien$ABMBien.tableRowGroupTipoBien}" id="tableRowGroupTipoBien"
												selected="#{compras$ABMBien$ABMBien.currentRowSelectedTipoBien}" sourceData="#{compras$ABMBien$ABMBien.ldpTipoBien}"
												sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{compras$ABMBien$ABMBien.tableColumnTipoBien}" id="tableColumnTipoBien"
													onClick="setTimeout('initAllRows()', 0)" valign="middle" width="10">
													<ui:checkbox binding="#{compras$ABMBien$ABMBien.checkbox2}" id="checkbox2" name="buttonsGroup"
														selected="#{compras$ABMBien$ABMBien.selectedTipoBien}" selectedValue="#{compras$ABMBien$ABMBien.selectedValueTipoBien}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMBien$ABMBien.tcNombre}" headerText="Nombre" id="tcNombre" sort="nombre">
													<ui:staticText binding="#{compras$ABMBien$ABMBien.stNombre}" id="stNombre" text="#{currentRow.value['nombre']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMBien$ABMBien.tcDescripcionTipoBien}" headerText="Descripción" id="tcDescripcionTipoBien"
													sort="descripcion">
													<ui:staticText binding="#{compras$ABMBien$ABMBien.tsDescripcionTipoBien}" id="stDescripcionTipoBien"
														text="#{currentRow.value['descripcion']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{compras$ABMBien$ABMBien.groupPanelTipoBien}" id="groupPanelTipoBien">

													<ui:button action="#{compras$ABMBien$ABMBien.btnSeleccionarTipoBien_action}"
														binding="#{compras$ABMBien$ABMBien.btnSeleccionarTipoBien}" escape="false" id="btnSeleccionarTipoBien" mini="true"
														styleClass="buttonSeleccionar" text="&amp;nbsp;" />

													<ui:staticText binding="#{compras$ABMBien$ABMBien.stSeparadorTipoBien}" escape="false" id="stSeparadorTipoBien"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />

													<ui:dropDown binding="#{compras$ABMBien$ABMBien.ddTipoBien}" id="ddTipoBien"
														items="#{compras$ABMBien$ABMBien.ddTipoBienDefaultOptions.options}" styleClass="textField" />
													<ui:button action="#{compras$ABMBien$ABMBien.btnAgregar_actionTipoBien}"
														binding="#{compras$ABMBien$ABMBien.btnAgregarTipoBien}" id="btnAgregarTipoBien" styleClass="button" text="Agregar" />
													<a4j:commandButton action="#{compras$ABMBien$ABMBien.btnQuitar_actionTipoBien}" binding="#{compras$ABMBien$ABMBien.btnQuitarTipoBien}"
														id="btnQuitarTipoBien" value="Quitar"  styleClass="btnAjax" reRender="tableTipoBien" onmousedown="reemplazarClickConConfirmacion(this, '');"/>
													<ui:staticText binding="#{compras$ABMBien$ABMBien.stSeparadorTipoBien2}" escape="false" id="stSeparadorTipoBien2"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<a4j:commandButton action="#{compras$ABMBien$ABMBien.btnQuitarTodos_actionTipoBien}"
														binding="#{compras$ABMBien$ABMBien.btnQuitarTodosTipoBien}" id="btnQuitarTodosTipoBien" value="Quitar todos" styleClass="btnAjax" reRender="tableTipoBien" 
														onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea quitar todos los elementos de esta lista?');"/>
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr><td colspan="4"><br/></td></tr>
								<tr><td align="right"><ui:label binding="#{compras$ABMBien$ABMBien.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria"/></td>
								<td><ui:textArea binding="#{compras$ABMBien$ABMBien.taComentarioLogAuditoria}" id="taComentarioLogAuditoria"/></td></tr><tr><td><br/></td></tr>
								<tr><td colspan="4"><ui:table binding="#{compras$ABMBien$ABMBien.tablaLogs}" id="tbLogsAuditoria"/></td></tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{compras$ABMBien$ABMBien.messageGroup}" id="messageGroup" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="true">
										<ui:button action="#{compras$ABMBien$ABMBien.btnGuardar_action}" binding="#{compras$ABMBien$ABMBien.btnGuardar}" id="btnGuardar"
											styleClass="button" />
										<ui:staticText binding="#{compras$ABMBien$ABMBien.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{compras$ABMBien$ABMBien.btnCancelar_action}" binding="#{compras$ABMBien$ABMBien.btnCancelar}"
											id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{compras$ABMBien$ABMBien.hidIdPagina}" id="hidIdPagina" text="#{compras$ABMBien$ABMBien.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMBien$ABMBien.hidIdSubSesion}" id="hidIdSubSesion" text="#{compras$ABMBien$ABMBien.idSubSesion}" />
					<ui:script binding="#{compras$ABMBien$ABMBien.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
