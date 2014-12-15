<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.head1}" id="head1">
				<ui:link binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.body1}" focus="form1:tfNumeroObra" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.label4}" for="tfNumeroObra" id="label4"
											styleClass="label" text="Nº de Obra" />
									</td>
									<td nowrap="nowrap" style="width: 168px">
										<ui:textField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.tfNumeroObra}" columns="10" id="tfNumeroObra"
											styleClass="textField" />
									</td>
									<!--
                                        <td align="right" nowrap="nowrap"></td>
                                        <td nowrap="nowrap"></td>
                                        -->
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.label15}" for="taDescripcion" id="label15"
											styleClass="label" text="Descripción" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.taDescripcion}" columns="40" id="taDescripcion"
											rows="5" styleClass="textField" />
									</td>
									<!--
                                        <td align="right" nowrap="nowrap"></td>
                                        <td nowrap="nowrap"></td>
                                        -->
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.label1}" for="ddTipoObra" id="label1"
											styleClass="label" text="Tipo de Obra" />
									</td>
									<td nowrap="nowrap">
										<ui:dropDown binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.ddTipoObra}" id="ddTipoObra"
											items="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.ddTipoObraDefaultOptions.options}" styleClass="textField" />
									</td>
									<!--
                                        <td align="right" nowrap="nowrap"></td>
                                        <td nowrap="nowrap"></td>
                                        -->
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.label16}" for="tfMetrosTotalAfectados" id="label16"
											styleClass="label" text="Total Metros Afectados" />
									</td>
									<td>
										<ui:textField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.tfMetrosTotalAfectados}" columns="10"
											id="tfMetrosTotalAfectados" styleClass="textField" />
										<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.staticText1}" escape="false" id="staticText1"
											styleClass="label" text="&amp;nbsp;[mts]" />
									</td>
									<!--
                                        <td align="right" nowrap="nowrap"></td>
                                        <td nowrap="nowrap"></td>
                                        -->
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.label2}" for="tfValorPorMetro" id="label2"
											styleClass="label" text="Valor por Metro" />
									</td>
									<td>
										<ui:textField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.tfValorPorMetro}" columns="10"
											id="tfValorPorMetro" styleClass="textField" />
										<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.staticText4}" escape="false" id="staticText4"
											styleClass="label" text="&amp;nbsp;[$]" />
									</td>
									<!--
                                        <td align="right" nowrap="nowrap"></td>
                                        <td nowrap="nowrap"></td>
                                        -->
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.label3}" for="tfCostoTotalObra" id="label3"
											styleClass="label" text="Costo Total" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.tfCostoTotalObra}" columns="10"
											id="tfCostoTotalObra" styleClass="textField" />
										<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.staticText6}" escape="false" id="staticText6"
											styleClass="label" text="&amp;nbsp;[$]" />
									</td>
									<!--
                                        <td align="right" nowrap="nowrap"></td>
                                        <td nowrap="nowrap"></td>
                                        -->
								</tr>
								<tr>
									<td align="right" nowrap="nowrap" style="width: 21px">
										<ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.label5}" id="label5" styleClass="label"
											text="Digesto Municipal" />
									</td>
									<td>
										<ui:textField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.tfDigestoMunicipal}" columns="40" disabled="true"
											id="tfDigestoMunicipal" styleClass="textFieldDisabled" />
										<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.btnSeleccionarDigesto_action}"
											binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.btnSeleccionarDigesto}" escape="false"
											id="btnSeleccionarDigesto" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" />
										<a4j:commandButton id="btnLimpiarDigesto" reRender="form1:tfDigestoMunicipal" title="Limpiar"
											binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.btnLimpiarDigesto}"
											action="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.btnLimpiarDigesto_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.lblObraViaAdministracion}"
											for="cbObraViaAdministracion" id="lblObraViaAdministracion" styleClass="label" text="Obra por via de administración" />
									</td>
									<td nowrap="nowrap">
										<ui:checkbox binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.cbObraViaAdministracion}"
											id="cbObraViaAdministracion" />
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
										<ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.label7}" id="label7" styleClass="label2"
											text="Cuadras Afectadas" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.table1}" id="table1" width="239">
											<script>
												<![CDATA[
/* ----- Functions for Table Preferences Panel ----- */
/*
 * Toggle the table preferences panel open or closed
 */
function togglePreferencesPanel() {
  var table = document.getElementById("form1:table1");
  table.toggleTblePreferencesPanel();
}
/* ----- Functions for Filter Panel ----- */
/*
 * Return true if the filter menu has actually changed,
 * so the corresponding event should be allowed to continue.
 */
function filterMenuChanged() {
  var table = document.getElementById("form1:table1");
  return table.filterMenuChanged();
}
/*
 * Toggle the custom filter panel (if any) open or closed.
 */
function toggleFilterPanel() {
  var table = document.getElementById("form1:table1");
  return table.toggleTableFilterPanel();
}
/* ----- Functions for Table Actions ----- */
/*
 * Initialize all rows of the table when the state
 * of selected rows changes.
 */
function initAllRows() {
  var table = document.getElementById("form1:table1");
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
  var table = document.getElementById("form1:table1");
  table.selectGroupRows(rowGroupId, selected);
}
/*
 * Disable all table actions if no rows have been selected.
 */
function disableActions() {
  // Determine whether any rows are currently selected
  var table = document.getElementById("form1:table1");
  var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
  // Set disabled state for top actions
  document.getElementById("form1:table1:tableActionsTop:deleteTop").setDisabled(disabled);
  // Set disabled state for bottom actions
  document.getElementById("form1:table1:tableActionsBottom:deleteBottom").setDisabled(disabled);
}]]></script>
											<ui:tableRowGroup binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.tableRowGroup1}" id="tableRowGroup1"
												sourceData="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.ldpCuadra}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.tableColumn1}"
													id="tableColumn1" valign="middle" width="10">
													<ui:radioButton binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.radioButton1}" id="radioButton1" label=""
														name="buttonGroup" selected="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.RBSelected}"
														selectedValue="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.tableColumn2}" headerText="Cuadra"
													id="tableColumn2" sort="nombre">
													<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.staticText2}" id="staticText2"
														text="#{currentRow.value['nombre']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.groupPanel3}" id="groupPanel3">
													<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.btnAgregarCuadra_action}"
														binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.btnAgregarCuadra}" id="btnAgregarCuadra" styleClass="button"
														text="Agregar" />
													<a4j:commandButton action="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.btnQuitarCuadra_action}"
														binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.btnQuitarCuadra}"
														onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea Quitar la Cuadra de la Lista? Si existen obligaciones para esta, las mismas serán anuladas al guardar los cambios.');"
														id="btnQuitarCuadra" value="Quitar" styleClass="btnAjax" reRender="table1" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="4" style="height: 17px">
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.label27}" id="label27" styleClass="label2"
											text="Planes de Cuenta Disponibles" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.table2}" id="table2">
											<script>
												<![CDATA[
/* ----- Functions for Table Preferences Panel ----- */
/*
 * Toggle the table preferences panel open or closed
 */
function togglePreferencesPanel() {
  var table = document.getElementById("form1:table2");
  table.toggleTblePreferencesPanel();
}
/* ----- Functions for Filter Panel ----- */
/*
 * Return true if the filter menu has actually changed,
 * so the corresponding event should be allowed to continue.
 */
function filterMenuChanged() {
  var table = document.getElementById("form1:table2");
  return table.filterMenuChanged();
}
/*
 * Toggle the custom filter panel (if any) open or closed.
 */
function toggleFilterPanel() {
  var table = document.getElementById("form1:table2");
  return table.toggleTableFilterPanel();
}
/* ----- Functions for Table Actions ----- */
/*
 * Initialize all rows of the table when the state
 * of selected rows changes.
 */
function initAllRows() {
  var table = document.getElementById("form1:table2");
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
  var table = document.getElementById("form1:table2");
  table.selectGroupRows(rowGroupId, selected);
}
/*
 * Disable all table actions if no rows have been selected.
 */
function disableActions() {
  // Determine whether any rows are currently selected
  var table = document.getElementById("form1:table2");
  var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
  // Set disabled state for top actions
  document.getElementById("form1:table2:tableActionsTop:deleteTop").setDisabled(disabled);
  // Set disabled state for bottom actions
  document.getElementById("form1:table2:tableActionsBottom:deleteBottom").setDisabled(disabled);
}]]></script>
											<ui:tableRowGroup binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.tableRowGroup2}" id="tableRowGroup2"
												sourceData="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.ldpPlanCuentaPorObra}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.tableColumn11}"
													id="tableColumn11" valign="middle" width="10">
													<ui:radioButton binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.radioButton2}" id="radioButton2" label=""
														name="buttonGroup2" selected="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.RBSelected2}"
														selectedValue="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.currentRow2}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.tableColumn4}" headerText="Nombre"
													id="tableColumn4" sort="nombre">
													<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.staticText3}" id="staticText3"
														text="#{currentRow.value['nombre']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.groupPanel1}" id="groupPanel1">
													<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.btnAgregarPlanCuentaObra_action}"
														binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.btnAgregarPlanCuentaObra}" id="btnAgregarPlanCuentaObra"
														styleClass="button" text="Agregar" />
													<a4j:commandButton action="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.btnQuitarPlanCuentaObra_action}"
														binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.btnQuitarPlanCuentaObra}" id="btnQuitarPlanCuentaObra"
														onmousedown="reemplazarClickConConfirmacion(this, '');" value="Quitar" styleClass="btnAjax" reRender="table2" />
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
									<td colspan="4">
										<ui:messageGroup binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.messageGroup1}" id="messageGroup1"
											styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.btnGuardar_action}"
											binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.btnGuardar}" id="btnGuardar" styleClass="button" text="Guardar" />
										<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.btnCancelar_action}"
											binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.btnCancelar}" id="btnCancelar" styleClass="button"
											text="Cancelar" />
									</td>
								</tr>
							</tfoot>
						</table>
						<br /> <br />
					</div>
					<ui:hiddenField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
