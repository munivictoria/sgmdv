<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.page1}" id="page1">
			<ui:html binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.html1}" id="html1">
			<ui:head binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.head1}" id="head1">
				<ui:link binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.link1}" id="link1"
					url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.body1}" focus="form1:ddArea" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.stTitulo}" id="stTitulo"
									styleClass="tituloABM" text="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<td align="right" nowrap="nowrap">
									<ui:label binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.lblArea}" for="ddArea"
										id="lblArea" styleClass="label" text="Área" />
								</td>
								<td nowrap="nowrap">
									<ui:dropDown binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.ddArea}" id="ddArea"
										items="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.ddAreaOptions.options}"
										styleClass="textField" />
								</td>
								<tr>
									<td colspan="4">
										<hr />
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:label binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.lblUsuario}" id="lblUsuario"
											styleClass="label2" text="Usuarios Autorizadores" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.table1}"
											id="table1" width="239">
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
											<ui:tableRowGroup binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.tableRowGroup1}"
												id="tableRowGroup1"
												sourceData="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.ldpUsuariosAutorizadores}"
												sourceVar="currentRow">
												<ui:tableColumn align="center"
													binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.tableColumn1}" id="tableColumn1"
													valign="middle" width="10">
													<ui:radioButton binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.radioButton1}"
														id="radioButton1" label="" name="buttonGroup"
														selected="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.RBSelected}"
														selectedValue="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.tcNombre}"
													headerText="Nombre" id="tcNombre" sort="usuario" width="40">
													<ui:staticText binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.stUsuario}"
														id="stUsuario" text="#{currentRow.value['usuario']}" />
												</ui:tableColumn>
												<ui:tableColumn headerText="Estados firmables" id="tcEstadosFirmables" sort="stringEstadosFirmables" width="40">
													<ui:staticText id="stStringEstadosFirmables" text="#{currentRow.value['stringEstadosFirmables']}" />
												</ui:tableColumn>
												<ui:tableColumn headerText="Opera Urgentes" id="tcOperaUrgentes" sort="stringOperaUrgentes" width="40">
													<ui:staticText id="stOperaUrgentes" text="#{currentRow.value['stringOperaUrgentes']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.groupPanel1}"
													id="groupPanel1">
													<ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.btnAgregar_action}"
														binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.btnAgregar}" id="btnAgregar"
														styleClass="button" text="Agregar" />
													<ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.btnModificar_action}"
														binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.btnModificar}" id="btnModificar"
														styleClass="button" text="Modificar" />
													<ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.btnConsultar_action}"
														binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.btnConsultar}" id="btnConsultar"
														styleClass="button" text="Consultar" />
													<a4j:commandButton action="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.btnQuitar_action}"
														binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.btnQuitar}" id="btnQuitar"
														value="Quitar" styleClass="btnAjax" reRender="table1" onmousedown="reemplazarClickConConfirmacion(this, '');" />
													<ui:staticText binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.stSeparador1}"
														escape="false" id="stSeparador1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<a4j:commandButton
														action="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.btnQuitarTodos_action}"
														binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.btnQuitarTodos}" id="btnQuitarTodos"
														value="Quitar todos" styleClass="btnAjax" reRender="table1"
														onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea quitar todos los elementos de esta lista?');" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:label binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.lblUsuariosSuplentes}"
											id="lblUsuariosSuplentes" styleClass="label2" text="Usuarios Suplentes" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.table2}"
											id="table2" width="239">
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
											<ui:tableRowGroup binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.tableRowGroup2}"
												id="tableRowGroup2"
												sourceData="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.ldpUsuariosSuplentes}"
												sourceVar="currentRow2">
												<ui:tableColumn align="center"
													binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.tableColumn2}" id="tableColumn2"
													valign="middle" width="10">
													<ui:radioButton binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.radioButton2}"
														id="radioButton2" label="" name="buttonGroup2"
														selected="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.RBSelected2}"
														selectedValue="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.currentRow2}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.tcUsuarioSuplente}"
													headerText="Nombre Usuario" id="tcUsuarioSuplente" sort="usuario" width="40">
													<ui:staticText binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.stUsuarioSuplente}"
														id="stUsuarioSuplente" text="#{currentRow2.value['usuario']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.tcUsuarioSuplido}"
													headerText="Usuario Suplido" id="tcUsuarioSuplido" sort="usuarioSuplido" width="40">
													<ui:staticText binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.stUsuarioSuplido}"
														id="stUsuarioSuplido" text="#{currentRow2.value['usuarioSuplido']}" />
												</ui:tableColumn>
												<ui:tableColumn headerText="Desde" id="tcFechaDesde" sort="fechaDesde">
													<ui:staticText id="stFechaDesde" text="#{currentRow2.value['fechaDesde']}"
														converter="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.dateTimeConverter1}" />
												</ui:tableColumn>
												<ui:tableColumn headerText="Hasta" id="tcFechaHasta" sort="fechaHasta">
													<ui:staticText id="stFechaHasta" text="#{currentRow2.value['fechaHasta']}"
														converter="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.dateTimeConverter1}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.groupPanel2}"
													id="groupPanel2">
													<ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.btnAgregarSuplente_action}"
														binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.btnAgregarSuplente}"
														id="btnAgregarSuplente" styleClass="button" text="Agregar" />
													<ui:button
														action="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.btnModificarSuplente_action}"
														binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.btnModificarSuplente}"
														id="btnModificarSuplente" styleClass="button" text="Modificar" />
													<ui:button
														action="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.btnConsultarSuplente_action}"
														binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.btnConsultarSuplente}"
														id="btnConsultarSuplente" styleClass="button" text="Consultar" />
													<a4j:commandButton
														action="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.btnQuitarSuplente_action}"
														binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.btnQuitarSuplente}"
														id="btnQuitarSuplente" value="Quitar" styleClass="btnAjax" reRender="table2"
														onmousedown="reemplazarClickConConfirmacion(this, '');" />
													<ui:staticText binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.stSeparador2}"
														escape="false" id="stSeparador2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<a4j:commandButton
														action="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.btnQuitarTodosSuplente_action}"
														binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.btnQuitarTodosSuplente}"
														id="btnQuitarTodosSuplente" value="Quitar todos" styleClass="btnAjax" reRender="table2"
														onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea quitar todos los elementos de esta lista?');" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:label binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.lblReglasFirmas}"
											id="lblReglasFirma" styleClass="label2" text="Reglas Firmas" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.table3}"
											id="table3" width="239">
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
											<ui:tableRowGroup binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.tableRowGroup3}"
												id="tableRowGroup3"
												sourceData="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.ldpReglasFirmas}"
												sourceVar="currentRow3">
												<ui:tableColumn align="center"
													binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.tableColumn3}" id="tableColumn3"
													valign="middle" width="10">
													<ui:radioButton binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.radioButton3}"
														id="radioButton3" label="" name="buttonGroup3"
														selected="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.RBSelected3}"
														selectedValue="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.currentRow3}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.tcOrden}"
													headerText="Orden" id="tcOrden" sort="orden" width="40">
													<ui:staticText binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.stOrden}" id="stOrden"
														text="#{currentRow3.value['orden']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.tcListaUsuarios}"
													headerText="Usuarios Autorizadores" id="tcListaUsuarios" sort="stringListaUsuarios" width="40">
													<ui:staticText binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.stListaUsuarios}"
														id="stListaUsuarios" text="#{currentRow3.value['stringListaUsuarios']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.tcEstado}"
													headerText="Estado" id="tcEstado" sort="estado" width="40">
													<ui:staticText binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.stEstado}"
														id="stEstado" text="#{currentRow3.value['estado']}" />
												</ui:tableColumn>
												<ui:tableColumn headerText="Urgente" id="tcReglaUrgente" sort="stringUrgente" width="40">
													<ui:staticText id="stReglaUrgente" text="#{currentRow3.value['stringUrgente']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.groupPanel3}"
													id="groupPanel3">
													<ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.btnAgregarRegla_action}"
														binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.btnAgregarRegla}"
														id="btnAgregarRegla" styleClass="button" text="Agregar" />
													<ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.btnModificarRegla_action}"
														binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.btnModificarRegla}"
														id="btnModificarRegla" styleClass="button" text="Modificar" />
													<ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.btnConsultarRegla_action}"
														binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.btnConsultarRegla}"
														id="btnConsultarRegla" styleClass="button" text="Consultar" />
													<ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.btnQuitarRegla_action}"
														binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.btnQuitarRegla}" id="btnQuitarRegla"
														styleClass="button" text="Quitar" />
													<ui:staticText binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.stSeparador2}"
														escape="false" id="stSeparador2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.btnQuitarTodosRegla_action}"
														binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.btnQuitarTodosRegla}"
														id="btnQuitarTodosRegla" styleClass="button" text="Quitar todos" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right">
										<ui:label
											binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
									</td>
									<td>
										<ui:textArea
											binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.tablaLogs}" id="tbLogsAuditoria" />
									</td>
								</tr>
								<tr><td colspan="4">
									<a4j:outputPanel ajaxRendered="true">
										<ui:messageGroup binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.messageGroup}"
											id="messageGroup" styleClass="grupoMsg" />
									</a4j:outputPanel>
								</td></tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.btnGuardar_action}"
											binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.btnGuardar}" id="btnGuardar"
											styleClass="button" />
										<ui:staticText binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.stSeparador}"
											escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.btnCancelar_action}"
											binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.btnCancelar}" id="btnCancelar"
											styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.hidIdSubSesion}"
						id="hidIdSubSesion" text="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.idSubSesion}" />
					<ui:script binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro.scriptValidador}"
						id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
