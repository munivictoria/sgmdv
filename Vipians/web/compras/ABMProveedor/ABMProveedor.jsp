<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMProveedor$ABMProveedor.page1}" id="page1">
			<ui:html binding="#{compras$ABMProveedor$ABMProveedor.html1}" id="html1">
			<ui:head binding="#{compras$ABMProveedor$ABMProveedor.head1}" id="head1">
				<ui:link binding="#{compras$ABMProveedor$ABMProveedor.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
				    	var nombreBean = "compras$ABMProveedor$ABMProveedor";
				    
						function cargarComportamientoJQuery() {
							autoCompletarEnTextField("#form1:tfProveedorLocal", "persona", nombreBean, "setPersonaAutocompletar");
						}

						function focusearTfPersonaSeleccionada() {
							$("#form1\\:tfProveedorLocal").focus();
						}
					
						$(document).ready(function() {
							cargarComportamientoJQuery();
						});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{compras$ABMProveedor$ABMProveedor.body1}" focus="form1:btnSeleccionarPersonaJuridica" id="body1"
				onLoad="parent.footer.location.reload(); Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMProveedor$ABMProveedor.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{compras$ABMProveedor$ABMProveedor.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{compras$ABMProveedor$ABMProveedor.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMProveedor$ABMProveedor.lblProveedorLocal}" for="tfProveedorLocal" id="lblProveedorLocal"
											styleClass="label" text="Razón Social" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{compras$ABMProveedor$ABMProveedor.tfProveedorLocal}" columns="40" id="tfProveedorLocal"
											styleClass="#{compras$ABMProveedor$ABMProveedor.hayPersona ? 'textFieldDisabled' : 'textField'}"
											disabled="#{compras$ABMProveedor$ABMProveedor.hayPersona}" />
										<ui:button action="#{compras$ABMProveedor$ABMProveedor.btnSeleccionarPersonaJuridica_action}"
											binding="#{compras$ABMProveedor$ABMProveedor.btnSeleccionarPersonaJuridica}" escape="false" id="btnSeleccionarPersonaJuridica"
											mini="true" styleClass="button" text="PJ" toolTip="Seleccionar Persona Juridica" />
										<ui:button action="#{compras$ABMProveedor$ABMProveedor.btnSeleccionarPersonaFisica_action}"
											binding="#{compras$ABMProveedor$ABMProveedor.btnSeleccionarPersonaFisica}" escape="false" id="btnSeleccionarPersonaFisica"
											mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Física"  />
										<a4j:commandButton title="Limpiar" id="btnLimpiarRazonSocial" reRender="form1:tfProveedorLocal" binding="#{compras$ABMProveedor$ABMProveedor.btnLimpiarRazonSocial}"
											action="#{compras$ABMProveedor$ABMProveedor.btnLimpiarRazonSocial_action}" styleClass="buttonLimpiarAjax" />
									</td>
									<td align="right">
										<ui:label binding="#{compras$ABMProveedor$ABMProveedor.lblCodigo}" id="lblCodigo" styleClass="label" text="Código" for="tfCodigo" />
									</td>
									<td>
										<ui:textField binding="#{compras$ABMProveedor$ABMProveedor.tfCodigo}" id="tfCodigo" styleClass="textField" maxLength="13" />
									</td>
								</tr>
								<tr>
								</tr>
								<tr>
									<td align="right">
										<ui:label binding="#{compras$ABMProveedor$ABMProveedor.lblContrato}" for="tfContacto" id="lblContrato" styleClass="label"
											text="Contacto" />
									</td>
									<td>
										<ui:textField binding="#{compras$ABMProveedor$ABMProveedor.tfContacto}" columns="40" id="tfContacto" styleClass="textField" />
									</td>
									<td align="right">
										<ui:label binding="#{compras$ABMProveedor$ABMProveedor.lblEmail}" for="tfEmail" id="lblEmail" styleClass="label" text="Email" />
									</td>
									<td>
										<ui:textField binding="#{compras$ABMProveedor$ABMProveedor.tfEmail}" columns="40" id="tfEmail" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" style="height: 19px">
										<ui:label binding="#{compras$ABMProveedor$ABMProveedor.lblTelefono}" for="tfTelefono" id="lblTelefono" styleClass="label"
											text="Teléfono" />
									</td>
									<td>
										<ui:textField binding="#{compras$ABMProveedor$ABMProveedor.tfTelefono}" columns="30" id="tfTelefono" styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMProveedor$ABMProveedor.lblTipo}" id="lblTipo" styleClass="label" text="Tipo" />
									</td>
									<td nowrap="nowrap">
										<ui:dropDown binding="#{compras$ABMProveedor$ABMProveedor.ddTipo}" id="ddTipo"
											items="#{compras$ABMProveedor$ABMProveedor.ddTipoDefaultOptions.options}" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<hr />
										<br />
									</td>
								</tr>
								<tr align="right">
									<td>
										<ui:label binding="#{compras$ABMProveedor$ABMProveedor.lblDomicilioFiscal}" id="lblDomicilioFiscal" styleClass="label"
											text="Domicilio Fiscal" />
									</td>
									<td align="left" nowrap="nowrap">
										<ui:button action="#{compras$ABMProveedor$ABMProveedor.btnSeleccionarDomicilioProveedor_action}"
											binding="#{compras$ABMProveedor$ABMProveedor.btnSeleccionarDomicilioProveedor}" id="btnSeleccionarDomicilioProveedor" mini="true"
											styleClass="button" text="Del Proveedor" />
										<ui:button action="#{compras$ABMProveedor$ABMProveedor.btnSeleccionarDomicilio_action}"
											binding="#{compras$ABMProveedor$ABMProveedor.btnSeleccionarDomicilio}" escape="false" id="btnSeleccionarDomicilio" mini="true"
											styleClass="buttonSeleccionar" text="&amp;nbsp;" />
									</td>
								</tr>
								<tr>
									<td></td>
									<td colspan="3" nowrap="nowrap">
										<ui:staticText binding="#{compras$ABMProveedor$ABMProveedor.stDomicilio}" escape="false" id="stDomicilio" styleClass="staticText " />
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
										<ui:staticText binding="#{compras$ABMProveedor$ABMProveedor.stCodigosCiiu}" id="stCodigosCiiu" styleClass="label2"
											text="Códigos CIIU" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{compras$ABMProveedor$ABMProveedor.table2}" id="table2" width="479">
											<script>
												<![CDATA[
												/* ----- Functions for Table Preferences Panel ----- */
												/*
												 * Toggle the table preferences panel open or closed
												 */
												function togglePreferencesPanel() {
													var table = document
															.getElementById("form1:table2");
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
															.getElementById("form1:table2");
													return table
															.filterMenuChanged();
												}
												/*
												 * Toggle the custom filter panel (if any) open or closed.
												 */
												function toggleFilterPanel() {
													var table = document
															.getElementById("form1:table2");
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
															.getElementById("form1:table2");
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
															.getElementById("form1:table2");
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
															.getElementById("form1:table2");
													var disabled = (table
															.getAllSelectedRowsCount() > 0) ? false
															: true;
													// Set disabled state for top actions
													document
															.getElementById(
																	"form1:table2:tableActionsTop:deleteTop")
															.setDisabled(
																	disabled);
													// Set disabled state for bottom actions
													document
															.getElementById(
																	"form1:table2:tableActionsBottom:deleteBottom")
															.setDisabled(
																	disabled);
												}
												]]>
											</script>
											<ui:tableRowGroup binding="#{compras$ABMProveedor$ABMProveedor.tableRowGroup2}" id="tableRowGroup2"
												selected="#{compras$ABMProveedor$ABMProveedor.currentRowSelected}"
												sourceData="#{compras$ABMProveedor$ABMProveedor.ldpCodigosCiiu}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{compras$ABMProveedor$ABMProveedor.tableColumn2}" id="tableColumn2"
													onClick="setTimeout('initAllRows()', 0)" valign="middle" width="10">
													<ui:checkbox binding="#{compras$ABMProveedor$ABMProveedor.checkbox1}" id="checkbox1" name="buttonsGroup2"
														selected="#{compras$ABMProveedor$ABMProveedor.selected}" selectedValue="#{compras$ABMProveedor$ABMProveedor.selectedValue}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMProveedor$ABMProveedor.tcCodigo}" headerText="Código" id="tcCodigo" sort="codigo">
													<ui:staticText binding="#{compras$ABMProveedor$ABMProveedor.stCodigo}" id="stCodigo" text="#{currentRow.value['codigo']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMProveedor$ABMProveedor.tcDescripcionCodigo}" headerText="Descripción"
													id="tcDescripcionCodigo" sort="descripcion">
													<ui:staticText binding="#{compras$ABMProveedor$ABMProveedor.stDescripcionCodigo}" id="stDescripcionCodigo"
														text="#{currentRow.value['descripcion']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{compras$ABMProveedor$ABMProveedor.groupPanel2}" id="groupPanel2">
													<ui:button action="#{compras$ABMProveedor$ABMProveedor.btnAgregarCodigosCiiu_action}"
														binding="#{compras$ABMProveedor$ABMProveedor.btnAgregar2}" id="btnAgregar2" styleClass="button" text="Agregar" />
													<a4j:commandButton action="#{compras$ABMProveedor$ABMProveedor.btnQuitarCodigosCiiu_action}"
														binding="#{compras$ABMProveedor$ABMProveedor.btnQuitar2}" id="btnQuitar2" value="Quitar" styleClass="btnAjax"
														reRender="table2" onmousedown="reemplazarClickConConfirmacion(this, '');" />
													<ui:staticText binding="#{compras$ABMProveedor$ABMProveedor.stSeparador2}" escape="false" id="stSeparador2"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<a4j:commandButton action="#{compras$ABMProveedor$ABMProveedor.btnQuitarTodosCodigosCiiu_action}"
														binding="#{compras$ABMProveedor$ABMProveedor.btnQuitarTodos2}" id="btnQuitarTodos2" value="Quitar todos" styleClass="btnAjax"
														reRender="table2"
														onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea quitar todos los elementos de esta lista?');" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<td colspan="4">
									<ui:staticText binding="#{compras$ABMProveedor$ABMProveedor.stTipoBien}" id="stTipoBien" styleClass="label2" text="Categoría Bien" />
								</td>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{compras$ABMProveedor$ABMProveedor.table3}" id="table3" width="479">
											<script>
												<![CDATA[
												/* ----- Functions for Table Preferences Panel ----- */
												/*
												 * Toggle the table preferences panel open or closed
												 */
												function togglePreferencesPanel() {
													var table = document
															.getElementById("form1:table3");
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
															.getElementById("form1:table3");
													return table
															.filterMenuChanged();
												}
												/*
												 * Toggle the custom filter panel (if any) open or closed.
												 */
												function toggleFilterPanel() {
													var table = document
															.getElementById("form1:table3");
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
															.getElementById("form1:table3");
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
															.getElementById("form1:table3");
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
															.getElementById("form1:table3");
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
											<ui:tableRowGroup binding="#{compras$ABMProveedor$ABMProveedor.tableRowGroup3}" id="tableRowGroup3"
												selected="#{compras$ABMProveedor$ABMProveedor.currentRowSelectedTipoBien}"
												sourceData="#{compras$ABMProveedor$ABMProveedor.ldpTipoBien}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{compras$ABMProveedor$ABMProveedor.tableColumn3}" id="tableColumn3"
													onClick="setTimeout('initAllRows()', 0)" valign="middle" width="10">
													<ui:checkbox binding="#{compras$ABMProveedor$ABMProveedor.checkbox2}" id="checkbox2" name="buttonsGroup"
														selected="#{compras$ABMProveedor$ABMProveedor.selectedTipoBien}"
														selectedValue="#{compras$ABMProveedor$ABMProveedor.selectedValueTipoBien}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMProveedor$ABMProveedor.tcNombre}" headerText="Nombre" id="tcNombre" sort="nombre">
													<ui:staticText binding="#{compras$ABMProveedor$ABMProveedor.stNombre}" id="stNombre" text="#{currentRow.value['nombre']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMProveedor$ABMProveedor.tcDescripcionTipoBien}" headerText="Descripción"
													id="tcDescripcionTipoBien" sort="descripcion">
													<ui:staticText binding="#{compras$ABMProveedor$ABMProveedor.tsDescripcionTipoBien}" id="stDescripcionTipoBien"
														text="#{currentRow.value['descripcion']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{compras$ABMProveedor$ABMProveedor.groupPanel3}" id="groupPanel3">
													<ui:button action="#{compras$ABMProveedor$ABMProveedor.btnSeleccionarTipoBien_action}"
														binding="#{compras$ABMProveedor$ABMProveedor.btnSeleccionarTipoBien}" escape="false" id="btnSeleccionarTipoBien" mini="true"
														styleClass="buttonSeleccionar" text="&amp;nbsp;" />
													<ui:staticText binding="#{compras$ABMProveedor$ABMProveedor.stSeparador2}" escape="false" id="stSeparador2"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:dropDown binding="#{compras$ABMProveedor$ABMProveedor.ddTipoBien}" id="ddTipoBien"
														items="#{compras$ABMProveedor$ABMProveedor.ddTipoBienDefaultOptions.options}" styleClass="textField" />
													<ui:button action="#{compras$ABMProveedor$ABMProveedor.btnAgregar_action}"
														binding="#{compras$ABMProveedor$ABMProveedor.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<a4j:commandButton action="#{compras$ABMProveedor$ABMProveedor.btnQuitar_action}"
														binding="#{compras$ABMProveedor$ABMProveedor.btnQuitar}" id="btnQuitar" value="Quitar" styleClass="btnAjax" reRender="table3" />
													<ui:staticText binding="#{compras$ABMProveedor$ABMProveedor.stSeparador1}" escape="false" id="stSeparador1"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<a4j:commandButton action="#{compras$ABMProveedor$ABMProveedor.btnQuitarTodos_action}"
														binding="#{compras$ABMProveedor$ABMProveedor.btnQuitarTodos}" id="btnQuitarTodos" value="Quitar todos" styleClass="btnAjax"
														reRender="table3" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<div class="div" style="width: 290px; height: 15px;">Atributos Dinámicos</div>
										<table border="0" class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
											<tr>
												<td colspan="4">
													<ui:panelGroup binding="#{compras$ABMProveedor$ABMProveedor.panelAtributoDinamico}" id="panelAtributoDinamico">
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
									<td align="right">
										<ui:label binding="#{compras$ABMProveedor$ABMProveedor.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
									</td>
									<td>
										<ui:textArea binding="#{compras$ABMProveedor$ABMProveedor.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{compras$ABMProveedor$ABMProveedor.tablaLogs}" id="tbLogsAuditoria" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{compras$ABMProveedor$ABMProveedor.messageGroup}" id="messageGroup" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="nowrap">
										<ui:button action="#{compras$ABMProveedor$ABMProveedor.btnGuardar_action}"
											binding="#{compras$ABMProveedor$ABMProveedor.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{compras$ABMProveedor$ABMProveedor.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{compras$ABMProveedor$ABMProveedor.btnCancelar_action}"
											binding="#{compras$ABMProveedor$ABMProveedor.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{compras$ABMProveedor$ABMProveedor.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMProveedor$ABMProveedor.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMProveedor$ABMProveedor.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMProveedor$ABMProveedor.idSubSesion}" />
					<ui:script binding="#{compras$ABMProveedor$ABMProveedor.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
