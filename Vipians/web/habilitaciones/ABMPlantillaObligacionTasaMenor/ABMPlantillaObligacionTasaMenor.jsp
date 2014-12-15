<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.page1}" id="page1">
			<ui:html binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.html1}" id="html1">
			<ui:head binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.head1}" id="head1">
				<ui:link binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.link1}" id="link1"
					url="/resources/stylesheet.css" />
				<script>
					function deshabilitarCheckbox() {
						var cb1 = document
								.getElementById("form1:cbAsociacionAParcela");
						var cb2 = document
								.getElementById("form1:cbPersonaPropietaria");

						if (cb1.checked) {
							cb2.disabled = false;
						} else {
							cb2.checked = false;
							cb2.disabled = true;
						}

						/* Comprobacion hecha porque al entrar al consultar si el primer checkbox estaba checkeado, habilitaba el segundo de nuevo... */
						if(cb1.disabled) {
							cb2.disabled = true;
						}
					}
				</script>
			</ui:head>
			<ui:body binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.body1}" focus="form1:tfNombre"
				id="body1" onLoad="parent.footer.location.reload();Init(); deshabilitarCheckbox();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.form1}" id="form1">
					<div class="formularioABM" style="left: -4px; top: 0px; position: absolute">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.stTitulo}" id="stTitulo"
									styleClass="tituloABM" text="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap" width="50">
										<ui:label binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.label4}" for="tfNombre"
											id="label4" styleClass="label" text="Nombre de la Plantilla" />
									</td>
									<td>
										<ui:textField binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.tfNombre}" columns="40"
											id="tfNombre" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<table>
											<tr>
												<td nowrap="nowrap">
													<ui:label binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.lblAsociacionAParcela}"
														for="cbAsociacionAParcela" id="lblAsociacionAParcela" styleClass="label" text="Asociación a Parcela" />
												</td>
												<td>
													<ui:checkbox binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.cbAsociacionAParcela}"
														id="cbAsociacionAParcela" onChange="deshabilitarCheckbox();" />
												</td>
												<td nowrap="nowrap">
													<ui:label binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.lblPersonaPropietaria}"
														for="cbPersonaPropietaria" id="lblPersonaPropietaria" styleClass="label" text="Persona Propietaria" />
												</td>
												<td>
													<ui:checkbox binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.cbPersonaPropietaria}"
														id="cbPersonaPropietaria" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:label binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.lblDatosPlantilla}"
											id="lblDatosPlantilla" styleClass="label2" text="Atributos Dinámicos" />
									</td>
								</tr>
								<!--
                                    <tr>
                                        <td align="left" colspan="4">
                                    <ui:button action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.btnAgregarDato_action}"
                                               binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.btnAgregarDato}"
                                               id="btnAgregarDato" styleClass="button" text="Agregar Dato"/>
                                </td>
                                <td align="left" colspan="4">
                                    <ui:button action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.btnQuitarDato_action}"
                                               binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.btnQuitarDato}"
                                               id="btnQuitarDato" styleClass="button" text="Quitar Dato"/>
                                </td>
                            </tr>
                                    -->
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.table1}"
											id="table1" width="200" clearSortButton="true">
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
											<ui:tableRowGroup binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.tableRowGroup1}"
												id="tableRowGroup1"
												sourceData="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.ldpDatosPlantilla}"
												sourceVar="currentRow">
												<ui:tableColumn align="center"
													binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.tableColumn1}" id="tableColumn1"
													valign="middle" width="10">
													<ui:radioButton binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.radioButton1}"
														id="radioButton1" label="" name="buttonGroup"
														selected="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.RBSelected}"
														selectedValue="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.tableColumn2}"
													headerText="Nombre" id="tableColumn2" sort="nombre" width="40">
													<ui:staticText binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.staticText1}"
														id="staticText1" text="#{currentRow.value['nombre']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.tableColumn5}"
													headerText="Tipo" id="tableColumn5" sort="tipo" width="40">
													<ui:staticText binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.staticText2}"
														id="staticText2" text="#{currentRow.value['tipo']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.groupPanel1}"
													id="groupPanel1">
													<ui:button
														action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.btnAgregarAtributoDinamico_action}"
														binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.btnAgregarAtributoDinamico}"
														id="btnAgregarAtributoDinamico" styleClass="button" text="Agregar" />
													<ui:button
														action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.btnModificarAtributoDinamico_action}"
														binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.btnModificarAtributoDinamico}"
														id="btnModificarAtributoDinamico" text="Modificar" styleClass="button" onClick="reemplazarClickConConfirmacion(this, '');" />
													<ui:staticText binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.stSeparador1}"
														escape="false" id="stSeparador1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<a4j:commandButton
														action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.btnQuitarAtributoDinamico_action}"
														binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.btnQuitarAtributoDinamico}"
														id="btnQuitarAtributoDinamico" value="Quitar" styleClass="btnAjax" reRender="table1"
														onmousedown="reemplazarClickConConfirmacion(this, '');" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:label binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.lblDatosPlantillaRegValuado}"
											id="lblDatosPlantillaRegValuado" styleClass="label2" text="Atributos Dinámicos de Registro Valuado" />
									</td>
								</tr>
								<!--     <tr>
                                             <td align="left" colspan="4">
                                    <ui:button action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.btnAgregarDatoRegValuado_action}"
                                               binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.btnAgregarDatoRegValuado}"
                                               id="btnAgregarDatoRegValuado" styleClass="button" text="Agregar Dato"/>
                                </td>
                                <td align="left" colspan="4">
                                    <ui:button action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.btnQuitarDatoRegValuado_action}"
                                               binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.btnQuitarDatoRegValuado}"
                                               id="btnQuitarDatoRegValuado" styleClass="button" text="Quitar Dato"/>
                                </td>
                            </tr> -->
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.table2}"
											id="table2" width="200" clearSortButton="true">
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
											<ui:tableRowGroup binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.tableRowGroup2}"
												id="tableRowGroup2"
												sourceData="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.ldpDatosPlantillaRegValuado}"
												sourceVar="currentRow">
												<ui:tableColumn align="center"
													binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.tableColumn6}" id="tableColumn6"
													valign="middle" width="10">
													<ui:radioButton binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.radioButton2}"
														id="radioButton2" label="" name="buttonGroup2"
														selected="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.RBSelected2}"
														selectedValue="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.currentRow2}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.tableColumn7}"
													headerText="Nombre" id="tableColumn7" sort="nombre" width="40">
													<ui:staticText binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.staticText9}"
														id="staticText9" text="#{currentRow.value['nombre']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.tableColumn8}"
													headerText="Tipo" id="tableColumn8" sort="tipo" width="40">
													<ui:staticText binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.staticText10}"
														id="staticText10" text="#{currentRow.value['tipo']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.groupPanel2}"
													id="groupPanel2">
													<ui:button
														action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.btnAgregarAtributoDinamicoRegValuado_action}"
														binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.btnAgregarAtributoDinamicoRegValuado}"
														id="btnAgregarAtributoDinamicoRegValuado" styleClass="button" text="Agregar" />
													<ui:button
														action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.btnModificarAtributoDinamicoRegValuado_action}"
														binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.btnModificarAtributoDinamicoRegValuado}"
														id="btnModificarAtributoDinamicoRegValuado" text="Modificar" styleClass="button"
														onClick="reemplazarClickConConfirmacion(this, '');" />
													<ui:staticText binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.stSeparador2}"
														escape="false" id="stSeparador2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<a4j:commandButton
														action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.btnQuitarAtributoDinamicoRegValuado_action}"
														binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.btnQuitarAtributoDinamicoRegValuado}"
														id="btnQuitarAtributoDinamicoRegValuado" value="Quitar" styleClass="btnAjax" reRender="table2"
														onmousedown="reemplazarClickConConfirmacion(this, '');" />
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
											binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
									</td>
									<td>
										<ui:textArea
											binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.tablaLogs}" id="tbLogsAuditoria" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.messageGroup1}"
											id="messageGroup1" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.btnGuardar_action}"
											binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.btnGuardar}" id="btnGuardar"
											styleClass="button"/>
										<ui:staticText binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.stSeparador}"
											escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.btnCancelar_action}"
											binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.btnCancelar}" id="btnCancelar"
											styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.hidIdPagina}"
						id="hidIdPagina" text="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.hidIdSubSesion}"
						id="hidIdSubSesion" text="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.idSubSesion}" />
					<ui:script binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor.scriptValidador}"
						id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
