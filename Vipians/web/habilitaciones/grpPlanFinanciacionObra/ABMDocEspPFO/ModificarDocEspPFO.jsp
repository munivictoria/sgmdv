<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" errorPage="/faces/comunes/ErrorHandler.jsp" isErrorPage="false"
		pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.head1}" id="head1"
				title="Modificar Obligación: Plan de Financiación de Obra">
				<ui:link binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.link1}" id="link1"
					url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.body1}" focus="form1:btnSeleccionarPlanCuenta"
				id="body1" onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242,242,236); -rave-layout: grid"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.form1}" id="form1">
					<div class="formularioABM" style="left: -4px; top: 0px; position: absolute">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.stTitulo}" id="stTitulo"
									styleClass="tituloABM" text="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.label4}" for="tfFechaInicio"
											id="label4" rendered="false" styleClass="label" text="Inicio de Actividades" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.tfFechaInicio}" disabled="true"
											id="tfFechaInicio" maxLength="10" rendered="false" styleClass="textFieldDisabled" />
										<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.staticText1}" escape="false"
											id="staticText1" rendered="false" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.label3}" for="tfObra" id="label3"
											styleClass="label" text="Obra" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.tfObra}" columns="40" id="tfObra"
											styleClass="textField" disabled="true" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.label6}" for="tfFechaCese" id="label6"
											rendered="false" styleClass="label" text="Cese de Actividades" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.tfFechaCese}" disabled="true"
											id="tfFechaCese" maxLength="10" rendered="false" styleClass="textFieldDisabled" />
										<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.staticText2}" escape="false"
											id="staticText2" rendered="false" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<hr />
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.label16}" for="tfPersona" id="label16"
											styleClass="label" text="Persona Solicitante" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.tfPersona}" columns="40"
											disabled="true" id="tfPersona" styleClass="textFieldDisabled" />
										<!--
                                            <ui:button
                                                action="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.btnSeleccionarPersonaFisica_action}"
                                                binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.btnSeleccionarPersonasFisica1}"
                                                escape="false" id="btnSeleccionarPersonasFisica1" mini="true" styleClass="button" text="PF"/>
                                            <ui:button
                                                action="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.btnSeleccionarPersonaJuridica_action}"
                                                binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.btnSeleccionarPersonaJuridica1}"
                                                escape="false" id="btnSeleccionarPersonaJuridica1" mini="true" styleClass="button" text="PJ"/>
                                            -->

									</td>

									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.label2}" for="tfPlanCuenta"
											id="label2" styleClass="label" text="Plan de Cuenta" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.tfPlanCuenta}" columns="40"
											disabled="true" id="tfPlanCuenta" styleClass="textFieldDisabled" />
										<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.btnSeleccionarPlanCuenta_action}"
											binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.btnSeleccionarPlanCuenta}" escape="false"
											id="btnSeleccionarPlanCuenta" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Agregar/Modificar" />
										<a4j:commandButton id="btnLimpiarPlanCuenta" reRender="form1:tfPlanCuenta"
											binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.btnLimpiarPlanCuenta}"
											action="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.btnLimpiarPlanCuenta_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.lblParcela}" for="tfParcela"
											id="lblParcela" styleClass="label" text="Parcela" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.tfParcela}" columns="40"
											disabled="true" id="tfParcela" styleClass="textFieldDisabled" />
									</td>
									<td align="right" nowrap="nowrap"></td>
									<td nowrap="nowrap"></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.label1}" id="label1"
											styleClass="label" text="Domicilio Postal" />
									</td>
									<td nowrap="nowrap">
										<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.btnSeleccionarDomicilioPostal_action}"
											binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.btnSeleccionarDomicilioPostal1}" escape="false"
											id="btnSeleccionarDomicilioPostal1" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Agregar/Modificar" />
										<a4j:commandButton id="btnLimpiarDomicilioPostal1" reRender="form1:stDomicilioPostal"
											binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.btnLimpiarDomicilioPostal1}"
											action="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.btnLimpiarDomicilioPostal_action}" styleClass="buttonLimpiarAjax" />
										<ui:button
											action="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.btnSeleccionarDomicilioSolicitante_action}"
											binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.btnSeleccionarDomicilioSolicitante1}"
											id="btnSeleccionarDomicilioSolicitante1" mini="true" styleClass="button" text="Del Solicitante" />
										<ui:button
											action="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.btnSeleccionarDomicilioParcela_action}"
											binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.btnSeleccionarDomicilioParcela1}"
											id="btnSeleccionarDomicilioParcela1" mini="true" styleClass="button" text="De la Parcela" />
									</td>
									<td align="right" nowrap="nowrap"></td>
									<td nowrap="nowrap"></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap"></td>
									<td colspan="3">
										<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.stDomicilioPostal}"
											escape="false" id="stDomicilioPostal" styleClass="staticText" />
									</td>
								</tr>
								<tr>
									<td colspan="4" style="height: 15px">
										<hr />
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.label7}" id="label7"
											styleClass="label2" text="Cuadras Afectadas" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.table1}"
											id="table1">
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
											<ui:tableRowGroup binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.tableRowGroup1}"
												id="tableRowGroup1" rows="#{ApplicationBean1.cantidadFilasTablasAdmin}"
												sourceData="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.ldpCuadrasAfectadasPFO}"
												sourceVar="currentRow">
												<ui:tableColumn binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.tableColumn1}"
													headerText="Cuadra" id="tableColumn1" sort="cuadra">
													<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.staticText4}" id="staticText4"
														text="#{currentRow.value['cuadra']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.tableColumn2}"
													headerText="Metros" id="tableColumn2" sort="metros">
													<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.staticText5}" id="staticText5"
														text="#{currentRow.value['metros']} mts" />
												</ui:tableColumn>
											</ui:tableRowGroup>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="4"></td>
								</tr>
								<tr>
									<td colspan="4" style="height: 18px">
										<ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.label28}" id="label28"
											rendered="false" styleClass="label2" text="Modificaciones" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.table3}"
											id="table3" rendered="false" width="158">
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
											<ui:tableRowGroup binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.tableRowGroup3}"
												id="tableRowGroup3"
												sourceData="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.ldpLogModificacionesPFO}"
												sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.tableColumn7}"
													id="tableColumn7" rendered="false" valign="middle" width="10">
													<ui:radioButton binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.radioButton3}"
														id="radioButton3" label="" name="buttonGroup3"
														selected="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.RBSelected3}"
														selectedValue="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.currentRow3}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.tableColumn8}"
													headerText="Número de Inscripción" id="tableColumn8" sort="numeroInscripcion">
													<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.staticText8}" id="staticText8"
														text="#{currentRow.value['numeroInscripcion']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.tableColumn16}"
													headerText="Fecha" id="tableColumn16" sort="accion">
													<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.staticText3}"
														converter="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.dateTimeConverter1}" id="staticText3"
														text="#{currentRow.value['fecha']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop" />
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.messageGroup1}"
											id="messageGroup1" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap" style="height: 30px">
										<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.btnGuardar_action}"
											binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.btnGuardar}" id="btnGuardar"
											styleClass="button" text="Guardar" />
										<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.stSeparador}" escape="false"
											id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.btnCancelar_action}"
											binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.btnCancelar}" id="btnCancelar"
											styleClass="button" text="Cancelar" />
									</td>
								</tr>
							</tfoot>
						</table>
						<br /> <br /> <br />
					</div>
					<ui:hiddenField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ModificarDocEspPFO.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
