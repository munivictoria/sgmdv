<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.head1}" id="head1">
				<ui:link binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.body1}" focus="form1:tfNumeroLibretaSanitaria"
				id="body1" onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.stTitulo}" id="stTitulo"
									styleClass="tituloABM" text="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.label15}" for="tfNumeroLibretaSanitaria"
											id="label15" styleClass="label" text="Número" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.tfNumeroLibretaSanitaria}" columns="10"
											id="tfNumeroLibretaSanitaria" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.label16}" for="tfPersonaFisica" id="label16"
											styleClass="label" text="Persona" />
									</td>
									<td>
										<ui:textField binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.tfPersonaFisica}" columns="40"
											disabled="true" id="tfPersonaFisica" styleClass="textField" />
										<ui:button action="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.btnSeleccionarPersonaFisica_action}"
											binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.btnSeleccionarPersonaFisica}" escape="false"
											id="btnSeleccionarPersonaFisica" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarPersonaFisica" reRender="form1:tfPersonaFisica" title="Limpiar"
											binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.btnLimpiarPersonaFisica}"
											action="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.btnLimpiarPersonaFisica_action}"
											styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<hr />
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.label7}" id="label7" styleClass="label2"
											text="Renovaciones" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:table augmentTitle="false" binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.table1}" id="table1"
											width="319">
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
											<ui:tableRowGroup binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.tableRowGroup1}" id="tableRowGroup1"
												sourceData="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.ldpRenovacionLibretaSanitaria}"
												sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.tableColumn1}"
													id="tableColumn1" valign="middle" width="10">
													<ui:radioButton binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.radioButton1}" id="radioButton1"
														label="" name="buttonGroup" selected="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.RBSelected}"
														selectedValue="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.tableColumn2}"
													headerText="Fecha Renovación" id="tableColumn2" sort="fechaDesde">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.staticText2}"
														converter="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.dateTimeConverter1}" id="staticText2"
														text="#{currentRow.value['fechaDesde']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.tableColumn3}"
													headerText="Fecha Vigencia" id="tableColumn3" sort="fechaVigencia">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.staticText7}"
														converter="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.dateTimeConverter1}" id="staticText7"
														text="#{currentRow.value['fechaVigencia']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.groupPanel3}" id="groupPanel3">
													<ui:button action="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.btnAgregarRenovacion_action}"
														binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.btnAgregarRenovacion}" id="btnAgregarRenovacion"
														styleClass="button" text="Agregar" />
													<a4j:commandButton action="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.btnQuitarRenovacion_action}"
														binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.btnQuitarRenovacion}" id="btnQuitarRenovacion"
														onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea Quitar la Renovación?');" value="Quitar"
														styleClass="btnAjax" reRender="table1" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.label27}" id="label27" styleClass="label2"
											text="Constancias de Vacunaciones" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:table augmentTitle="false" binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.table2}" id="table2"
											width="192">
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
											<ui:tableRowGroup binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.tableRowGroup2}" id="tableRowGroup2"
												sourceData="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.ldpConstanciaVacunacionLibretaSanitaria}"
												sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.tableColumn11}"
													id="tableColumn11" valign="middle" width="10">
													<ui:radioButton binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.radioButton2}" id="radioButton2"
														label="" name="buttonGroup2" selected="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.RBSelected2}"
														selectedValue="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.currentRow2}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.tableColumn4}"
													headerText="Fecha Vacunación" id="tableColumn4" sort="fechaVacunacion">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.staticText3}"
														converter="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.dateTimeConverter1}" id="staticText3"
														text="#{currentRow.value['fechaVacunacion']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.tableColumn5}" headerText="Vacuna"
													id="tableColumn5" sort="vacuna">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.staticText4}" id="staticText4"
														text="#{currentRow.value['vacuna']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.tableColumn6}" headerText="Validez"
													id="tableColumn6" sort="fechaValidez">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.staticText5}"
														converter="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.dateTimeConverter1}" id="staticText5"
														text="#{currentRow.value['fechaValidez']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.groupPanel1}" id="groupPanel1">
													<ui:button action="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.btnAgregarConstanciaVacunacion_action}"
														binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.btnAgregarConstanciaVacunacion}"
														id="btnAgregarConstanciaVacunacion" styleClass="button" text="Agregar" />
													<a4j:commandButton
														action="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.btnQuitarConstanciaVacunacion_action}"
														binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.btnQuitarConstanciaVacunacion}"
														id="btnQuitarConstanciaVacunacion"
														onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea Quitar la Constancia de Vacunación?');"
														value="Quitar" styleClass="btnAjax" reRender="table1" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.label28}" id="label28" styleClass="label2"
											text="Inhabilitaciones Temporarias" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:table augmentTitle="false" binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.table3}" id="table3"
											width="108">
											<script>
												<![CDATA[
/* ----- Functions for Table Preferences Panel ----- */
/*
 * Toggle the table preferences panel open or closed
 */
function togglePreferencesPanel() {
  var table = document.getElementById("form1:table3");
  table.toggleTblePreferencesPanel();
}
/* ----- Functions for Filter Panel ----- */
/*
 * Return true if the filter menu has actually changed,
 * so the corresponding event should be allowed to continue.
 */
function filterMenuChanged() {
  var table = document.getElementById("form1:table3");
  return table.filterMenuChanged();
}
/*
 * Toggle the custom filter panel (if any) open or closed.
 */
function toggleFilterPanel() {
  var table = document.getElementById("form1:table3");
  return table.toggleTableFilterPanel();
}
/* ----- Functions for Table Actions ----- */
/*
 * Initialize all rows of the table when the state
 * of selected rows changes.
 */
function initAllRows() {
  var table = document.getElementById("form1:table3");
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
  var table = document.getElementById("form1:table3");
  table.selectGroupRows(rowGroupId, selected);
}
/*
 * Disable all table actions if no rows have been selected.
 */
function disableActions() {
  // Determine whether any rows are currently selected
  var table = document.getElementById("form1:table3");
  var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
  // Set disabled state for top actions
  document.getElementById("form1:table3:tableActionsTop:deleteTop").setDisabled(disabled);
  // Set disabled state for bottom actions
  document.getElementById("form1:table3:tableActionsBottom:deleteBottom").setDisabled(disabled);
}]]></script>
											<ui:tableRowGroup binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.tableRowGroup3}" id="tableRowGroup3"
												sourceData="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.ldpInhabilitacionTemporariaLibretaSanitaria}"
												sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.tableColumn7}"
													id="tableColumn7" valign="middle" width="10">
													<ui:radioButton binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.radioButton3}" id="radioButton3"
														label="" name="buttonGroup3" selected="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.RBSelected3}"
														selectedValue="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.currentRow3}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.tableColumn8}"
													headerText="Fecha Inhabilitación" id="tableColumn8" sort="fechaInhabilitacion">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.staticText8}"
														converter="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.dateTimeConverter1}" id="staticText8"
														text="#{currentRow.value['fechaInhabilitacion']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.tableColumn9}"
													headerText="Diagnóstico" id="tableColumn9" sort="diagnostico">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.staticText9}" id="staticText9"
														text="#{currentRow.value['diagnostico']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.tableColumn10}"
													headerText="Prueba Confirmatoria" id="tableColumn10" sort="pruebaConfirmatoria">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.staticText1}" id="staticText1"
														text="#{currentRow.value['pruebaConfirmatoria']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.tableColumn12}"
													headerText="Fecha Reintegro" id="tableColumn12" sort="fechaReintegro">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.staticText6}"
														converter="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.dateTimeConverter1}" id="staticText6"
														text="#{currentRow.value['fechaReintegro']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.groupPanel2}" id="groupPanel2">
													<ui:button action="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.btnAgregarInhabilitacion_action}"
														binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.btnAgregarInhabilitacion}"
														id="btnAgregarInhabilitacion" styleClass="button" text="Agregar" />
													<a4j:commandButton action="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.btnQuitarInhabilitacion_action}"
														binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.btnQuitarInhabilitacion}"
														id="btnQuitarInhabilitacion"
														onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea Quitar la Inhabilitación Temporaria?');"
														value="Quitar" styleClass="btnAjax" reRender="table3" />
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.staticText10}" escape="false"
														id="staticText10" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.btnConsultarInhabilitacion_action}"
														binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.btnConsultarInhabilitacion}"
														id="btnConsultarInhabilitacion" styleClass="button" text="Consultar" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr><td colspan="4"><br/></td></tr>
								<tr><td align="right"><ui:label binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria"/></td>
								<td><ui:textArea binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.taComentarioLogAuditoria}" id="taComentarioLogAuditoria"/></td></tr><tr><td><br/></td></tr>
								<tr><td colspan="4"><ui:table binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.tablaLogs}" id="tbLogsAuditoria"/></td></tr>
								<tr>
									<td colspan="2">
										<ui:messageGroup binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.messageGroup1}" id="messageGroup1"
											styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="2" nowrap="nowrap">
										<ui:button action="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.btnGuardar_action}"
											binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.btnGuardar}" id="btnGuardar" styleClass="button"
											 />
										<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.stSeparador}" escape="false"
											id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.btnCancelar_action}"
											binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.btnCancelar}" id="btnCancelar" styleClass="button"
											/>
									</td>
								</tr>
							</tfoot>
						</table>
						<br /> <br />
					</div>
					<ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
