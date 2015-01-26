<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.head1}" id="head1">
				<ui:link binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP";

					function cargarComportamientoJQuery() {
						autoCompletarEnTextField("#form1:tfParcela", "parcela", nombreBean, "setParcelaAutocompletar", null,
								"#form1:btnResfrescarTable1");

						var $pgTablaLogsLiquidacion = $("#form1").find("span[id *= 'tbLogsLiquidacion:gpFiltroTablaLogsLiquidacion']");

						for(var i = 0; i < $pgTablaLogsLiquidacion.length; i++) {
							var $componentes = $("#" + $pgTablaLogsLiquidacion.eq(i).attr("id").replace(/:/g, "\\:"))
									.find("input[id *= 'tfFiltrar']");

							$("#" + $componentes.eq(0).attr("id").replace(/:/g, "\\:")).attr("placeholder", "Usuario");
							$("#" + $componentes.eq(1).attr("id").replace(/:/g, "\\:")).attr("placeholder", "Fecha desde");
							$("#" + $componentes.eq(2).attr("id").replace(/:/g, "\\:")).attr("placeholder", "Fecha hasta");
							calendarioEnTextField("#" + $componentes.eq(1).attr("id"));
							calendarioEnTextField("#" + $componentes.eq(2).attr("id"));
						}
					}

					function focusearTfParcela() {
						$("#form1\\:tfParcela").focus();
					}

					function focusearTfFiltrarUsuario() {
						$("#form1\\:tbLogsLiquidacion\\:tfFiltrarUsuarioLogLiq").focus();
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.body1}" focus="form1:tfFechaInicio" id="body1"
				onLoad="parent.footer.location.reload(); Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.label2}" for="tfFechaInicio" id="label2" styleClass="label"
											text="Inicio de Actividades" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tfFechaInicio}" id="tfFechaInicio" styleClass="textField"
											onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.label5}" for="tfNumeroCuenta" id="label5" styleClass="label"
											text="Número de Cuenta/Subcuenta" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tfNumeroCuenta}" columns="7" id="tfNumeroCuenta"
											styleClass="textField" />
										<ui:staticText binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.staticText1}" escape="false" id="staticText1"
											text="&amp;nbsp;/&amp;nbsp;" />
										<ui:textField binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tfNumeroSubCuenta}" columns="4" id="tfNumeroSubCuenta"
											styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.label3}" for="tfFechaCese" id="label3" rendered="false"
											styleClass="label" text="Cese de Actividades" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tfFechaCese}" id="tfFechaCese" rendered="false"
											styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
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
										<ui:label binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.label16}" for="tfPersona" id="label16" styleClass="label"
											text="Persona Solicitante" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tfPersona}" columns="40" disabled="true" id="tfPersona"
											styleClass="textFieldDisabled" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.lblParcela1}" for="tfParcela" id="lblParcela1"
											styleClass="label" text="Parcela" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tfParcela}" columns="40" id="tfParcela"
											styleClass="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.hayParcela ? 'textFieldDisabled' : 'textField'}"
											disabled="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.hayParcela}" />
										<ui:button action="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.btnSeleccionarParcela_action}"
											binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.btnSeleccionarParcela}" escape="false" id="btnSeleccionarParcela"
											mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<ui:button action="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.btnSeleccionarSubparcela_action}"
											binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.btnSeleccionarSubparcela}" escape="false"
											id="btnSeleccionarSubParcela" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" rendered="false" />
										<a4j:commandButton id="btnLimpiarParcela" reRender="form1:tfParcela, form1:table1" title="Limpiar"
											binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.btnLimpiarParcela}"
											action="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.btnLimpiarParcela_action}" styleClass="buttonLimpiarAjax"
											oncomplete="cargarComportamientoJQuery(); focusearTfParcela();" />
										<ui:button action="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.btnConsultarParcela_action}"
											binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.btnConsultarParcela}" escape="false" id="btnConsultarParcela"
											mini="true" text="Consultar" />
										<a4j:commandButton id="btnResfrescarTable1" style="display: none;" reRender="form1:table1"
											oncomplete="cargarComportamientoJQuery();" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.label1}" id="label1" styleClass="label"
											text="Domicilio Postal" />
									</td>
									<td nowrap="nowrap">
										<ui:button action="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.btnSeleccionarDomicilioPostal_action}"
											binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.btnSeleccionarDomicilioPostal}" escape="false"
											id="btnSeleccionarDomicilioPostal" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Agregar/Modificar" />
										<a4j:commandButton id="btnLimpiarDomicilioPostal" reRender="form1:stDomicilioPostal" title="Limpiar"
											binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.btnLimpiarDomicilioPostal}"
											action="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.btnLimpiarDomicilioPostal_action}" styleClass="buttonLimpiarAjax" />
										<ui:button action="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.btnSeleccionarDomicilioSolicitante_action}"
											binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.btnSeleccionarDomicilioSolicitante}"
											id="btnSeleccionarDomicilioSolicitante" mini="true" styleClass="button" text="Del Solicitante" />
										<ui:button action="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.btnSeleccionarDomicilioParcela_action}"
											binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.btnSeleccionarDomicilioParcela}" id="btnSeleccionarDomicilioParcela"
											mini="true" styleClass="button" text="De la Parcela" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap" style="height: 17px"></td>
									<td colspan="1">
										<ui:staticText binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.stDomicilioPostal}" escape="false"
											id="stDomicilioPostal" styleClass="staticText" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.table1}" id="table1">
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
												}
												]]>
											</script>
											<ui:tableRowGroup binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tableRowGroup1}"
												emptyDataMsg="Ningún registro encontrado." id="tableRowGroup1"
												sourceData="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.ldpPropietariosParcela}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tableColumn1}" id="tableColumn1"
													valign="middle" width="10">
													<ui:radioButton binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.radioButton1}" id="radioButton1" label=""
														name="buttonGroup" selected="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.RBSelected}"
														selectedValue="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tableColumn2}" headerText="Propietarios"
													id="tableColumn2">
													<ui:staticText binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.stPersona}" id="stPersona"
														text="#{currentRow.value['toStringCompleto']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td>
										<ui:panelGroup id="groupPanel1" binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.groupPanel1}">
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.label6}" for="tfServicioOSP" id="label6"
														styleClass="label" text="Servicio" />
												</td>
												<td nowrap="nowrap">
													<ui:textField binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tfServicioOSP}" columns="40" disabled="true"
														id="tfServicioOSP" styleClass="textField" />
													<ui:button action="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.btnSeleccionarServicio_action}"
														binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.btnSeleccionarServicio}" escape="false" id="btnSeleccionarServicio"
														mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.lblCodigoMedidor}" id="lblCodigoMedidor"
														styleClass="label" text="Código de Medidor" />
												</td>
												<td nowrap="nowrap">
													<ui:textField binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tfCodigoMedidor}" id="tfCodigoMedidor"
														styleClass="textField" />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.lblCoeficienteZonal}" id="lblCoeficienteZonal"
														styleClass="label" text="Coeficiente Zonal" />
												</td>
												<td nowrap="nowrap">
													<ui:textField binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tfCoeficienteZonal}" id="tfCoeficienteZonal"
														styleClass="textField" />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.lblBaseConsumo}" id="lblBaseConsumo" styleClass="label"
														text="Base Consumo" />
												</td>
												<td nowrap="nowrap">
													<ui:textField binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tfBaseConsumo}" id="tfBaseConsumo" disabled="true"
														styleClass="textField" />
												</td>
											</tr>
										</ui:panelGroup>
										<ui:panelGroup id="groupPanel2" binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.groupPanel2}">
											<tr>
												<td colspan="2">
													<ui:label binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.label7}" id="label7" styleClass="label7" text="Servicios" />
												</td>
											</tr>
											<tr>
												<td colspan="2">
													<ui:table id="table2" width="50%">
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
																document.getElementById("form1:table2:tableActionsTop:deleteTop").setDisabled(
																		disabled);
																// Set disabled state for bottom actions
																document.getElementById("form1:table2:tableActionsBottom:deleteBottom").setDisabled(
																		disabled);
															}
															]]>
														</script>
														<ui:tableRowGroup binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tableRowGroup2}"
															emptyDataMsg="Ningún registro encontrado." id="tableRowGroup2"
															sourceData="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.ldpServicioOSP}" sourceVar="currentRow2">
															<ui:tableColumn align="center" binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tableColumn3}" id="tableColumn3"
																valign="middle" width="10">
																<ui:radioButton binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.radioButton2}" id="radioButton2" label=""
																	name="buttonGroup2" selected="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.RBSelected2}"
																	selectedValue="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.currentRow2}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tableColumn4}" headerText="Código"
																id="tableColumn4" sort="codigo" width="10">
																<ui:staticText binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.staticText13}" id="staticText13"
																	text="#{currentRow2.value['registroAlicuota'].codigo}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tableColumn5}" headerText="Descripción"
																id="tableColumn5" sort="nombre" width="10">
																<ui:staticText binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.staticText14}" id="staticText14"
																	text="#{currentRow2.value['registroAlicuota'].nombre}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tableColumn6}" headerText="Valor Mínimo"
																id="tableColumn6" sort="valor" width="10">
																<ui:staticText binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.staticText15}"
																	converter="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.numberConverter1}" id="staticText15"
																	text="#{currentRow2.value['registroAlicuota'].valor}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tableColumn7}" headerText="Medido"
																id="tableColumn7" sort="medido" width="10">
																<ui:checkbox binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.checkbox1}" disabled="true" id="checkbox1"
																	readOnly="true" selected="#{currentRow2.value['registroAlicuota'].medido}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tcCodigoMedidor}" headerText="Código del Medidor"
																id="tcCodigoMedidor" width="40" sort="codigoMedidor">
																<ui:textField binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tfCodigoMedidorTabla}" id="tfCodigoMedidor"
																	readOnly="#{!currentRow2.value['registroAlicuota'].medido}" text="#{currentRow2.value['codigoMedidor']}" />
																<!--text="#{currentRow2.value['codigoMedidor']}"/> -->
															</ui:tableColumn>
															<ui:tableColumn binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tableColumn8}" headerText="Base de Consumo"
																id="tableColumn8" sort="baseConsumo" width="10">
																<ui:staticText binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.staticText16}" id="staticText16"
																	text="#{currentRow2.value['registroAlicuota'].baseConsumo}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tableColumn9}" headerText="Valor por Excedente"
																id="tableColumn9" sort="valorPorExcedente" width="10">
																<ui:staticText binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.staticText17}"
																	converter="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.numberConverter1}" id="staticText17"
																	text="#{currentRow2.value['registroAlicuota'].valorPorExcedente}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tableColumn10}" headerText="Periodicidad"
																id="tableColumn10" sort="periodicidad" width="10">
																<ui:staticText binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.staticText18}" id="staticText18"
																	text="#{currentRow2.value['registroAlicuota'].periodicidad}" />
															</ui:tableColumn>
														</ui:tableRowGroup>
														<f:facet name="actionsTop">
															<ui:panelGroup id="groupPanel3" binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.groupPanel3}">
																<ui:button id="btnAgregarServicio" styleClass="button" text="Agregar" toolTip="Agregar Servicio"
																	binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.btnAgregarServicio}"
																	action="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.btnAgregarServicio_action}" />
																<a4j:commandButton id="btnQuitarServicio" value="Quitar" styleClass="btnAjax" reRender="table2"
																	onmousedown="reemplazarClickConConfirmacion(this, '');"
																	binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.btnQuitarServicio}"
																	action="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.btnQuitarServicio_action}" />
															</ui:panelGroup>
														</f:facet>
													</ui:table>
												</td>
											</tr>
										</ui:panelGroup>
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.label4}" id="label4" styleClass="label" text="Observaciones" />
									</td>
									<td>
										<ui:textArea binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.taNombre}" columns="40" rows="5" id="taNombre"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:staticText binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.stDocsGeneradoresDeuda}" id="stDocsGeneradoresDeuda"
											styleClass="label2" text="Documentos Generadores de Deuda" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tablaDocsGeneradoresDeuda}"
											id="tablaDocsGeneradoresDeuda" width="479">
											<script>
												<![CDATA[
												/* ----- Functions for Table Preferences Panel ----- */
												/*
												 * Toggle the table preferences panel open or closed
												 */
												function togglePreferencesPanel() {
													var table = document.getElementById("form1:tablaDocsGeneradoresDeuda");
													table.toggleTblePreferencesPanel();
												}
												/* ----- Functions for Filter Panel ----- */
												/*
												 * Return true if the filter menu has actually changed,
												 * so the corresponding event should be allowed to continue.
												 */
												function filterMenuChanged() {
													var table = document.getElementById("form1:tablaDocsGeneradoresDeuda");
													return table.filterMenuChanged();
												}
												/*
												 * Toggle the custom filter panel (if any) open or closed.
												 */
												function toggleFilterPanel() {
													var table = document.getElementById("form1:tablaDocsGeneradoresDeuda");
													return table.toggleTableFilterPanel();
												}
												/* ----- Functions for Table Actions ----- */
												/*
												 * Initialize all rows of the table when the state
												 * of selected rows changes.
												 */
												function initAllRows() {
													var table = document.getElementById("form1:tablaDocsGeneradoresDeuda");
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
													var table = document.getElementById("form1:tablaDocsGeneradoresDeuda");
													table.selectGroupRows(rowGroupId, selected);
												}
												/*
												 * Disable all table actions if no rows have been selected.
												 */
												function disableActions() {
													// Determine whether any rows are currently selected
													var table = document.getElementById("form1:tablaDocsGeneradoresDeuda");
													var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
													// Set disabled state for top actions
													document.getElementById("form1:tablaDocsGeneradoresDeuda:tableActionsTop:deleteTop").setDisabled(
															disabled);
													// Set disabled state for bottom actions
													document.getElementById("form1:tablaDocsGeneradoresDeuda:tableActionsBottom:deleteBottom")
															.setDisabled(disabled);
												}
												]]>
											</script>
											<ui:tableRowGroup binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.trgDocsGeneradoresDeuda}"
												id="trgDocsGeneradoresDeuda" sourceData="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.ldpDocsGeneradoresDeuda}"
												sourceVar="currentRow">
												<ui:tableColumn binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tcAnio}" headerText="Año" id="tcAnio" sort="anio">
													<ui:staticText binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.stAnio}" id="stAnio"
														text="#{currentRow.value['anio']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tcPlan}" headerText="Plan elegido" id="tcPlan"
													sort="planElegido">
													<ui:staticText binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.stPlan}" id="stPlan"
														text="#{currentRow.value['planElegido']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
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
										<div class="div" style="width: 290px; height: 15px;">Atributos Dinámicos</div>
										<table border="0" class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
											<tr>
												<td colspan="4">
													<ui:panelGroup binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.panelAtributoDinamico}" id="panelAtributoDinamico">
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
									<td colspan="4">
										<div class="div" style="width: 760px; height: 15px;">Logs de Liquidaciones</div>
										<ui:table binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tablaLogsLiquidacion}" id="tbLogsLiquidacion"
											clearSortButton="true" width="770px">
											<ui:tableRowGroup binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tableRowGroup}" id="tableRowGroup"
												emptyDataMsg="Ningún registro encontrado." sourceData="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.ldpLogsLiquidacion}"
												sourceVar="currentRow">
												<ui:tableColumn binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tableColumn20}" id="tableColumn20"
													headerText="Usuario" sort="usuario">
													<ui:staticText binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.staticText20}" id="staticText20"
														text="#{currentRow.value['usuario']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tableColumn21}" id="tableColumn21"
													headerText="Evento" sort="evento">
													<ui:staticText binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.staticText21}" id="staticText21"
														text="#{currentRow.value['evento']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tableColumn22}" id="tableColumn22" headerText="Cuota"
													sort="cuota">
													<ui:staticText binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.staticText22}" id="staticText22"
														text="#{currentRow.value['cuota']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tableColumn23}" id="tableColumn23"
													headerText="Fecha y Hora" sort="fecha">
													<ui:staticText binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.staticText23}" id="staticText23"
														text="#{currentRow.value['fecha']}" converter="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.dateTimeConverter2}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tableColumn24}" id="tableColumn24"
													headerText="Monto Total" sort="montoTotalLiquidacion">
													<ui:staticText binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.staticText24}" id="staticText24"
														text="#{currentRow.value['montoTotalLiquidacion']}"
														converter="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.numberConverter2}" />
												</ui:tableColumn>
												<ui:tableColumn id="tcIntereses" headerText="Intereses" sort="Intereses">
													<ui:staticText id="stIntereses" text="#{currentRow.value['intereses']}"
														converter="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.numberConverter2}" />
												</ui:tableColumn>
												<ui:tableColumn id="tcTipoDeuda" headerText="Tipo Deuda" sort="tipoDeuda">
													<ui:staticText id="stTipoDeuda" text="#{currentRow.value['tipoDeuda']}"/>
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tableColumn25}" id="tableColumn25"
													headerText="Comentario">
													<ui:textArea id="taComentario" disabled="true" styleClass="textFieldDisabled"
		 												text="#{currentRow.value['comentario']}" rows="1" columns="40" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="filter">
												<ui:panelGroup binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.gpFiltroTablaLogsLiquidacion}"
													id="gpFiltroTablaLogsLiquidacion">
													<ui:textField binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tfFiltrarUsuarioLogLiq}" id="tfFiltrarUsuarioLogLiq"
														styleClass="textField" />
													<ui:dropDown binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.ddFiltrarEventoLogLiq}" id="ddFiltrarEventoLogLiq"
														styleClass="textField" items="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.ddFiltrarEventoLogLiqOptions.options}" />
													<ui:textField id="tfFiltrarFechaDesdeLogLiq"
														binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tfFiltrarFechaDesdeLogLiq}" styleClass="textField" columns="10" />
													<ui:textField binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tfFiltrarFechaHastaLogLiq}"
														id="tfFiltrarFechaHastaLogLiq" styleClass="textField" columns="10" />
													<ui:staticText id="staticTextSeparadora1" styleClass="barraSeparadoraVertical" escape="false" text="|" />
													<a4j:commandButton binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.btnFiltrarLogLiq}"
														action="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.btnFiltrarLogLiq_action}" id="btnFiltrarLogLiq" styleClass="btnAjax"
														reRender="tbLogsLiquidacion" oncomplete="cargarComportamientoJQuery();" value="Buscar" />
													<a4j:commandButton binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.btnLimpiarFiltradoLogLiq}"
														action="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.btnLimpiarFiltradoLogLiq_action}" id="btnLimpiarFiltradoLogLiq"
														styleClass="buttonLimpiarAjax"
														reRender="tbLogsLiquidacion, tfFiltrarUsuarioLogLiq, ddFiltrarEventoLogLiq, tfFiltrarFechaDesdeLogLiq, tfFiltrarFechaHastaLogLiq"
														oncomplete="cargarComportamientoJQuery(); focusearTfFiltrarUsuario();" />
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
										<ui:label binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
									</td>
									<td>
										<ui:textArea binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.tablaLogs}" id="tbLogsAuditoria" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.messageGroup1}" id="messageGroup1"
											styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.btnGuardar_action}"
											binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.btnCancelar_action}"
											binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
						<br /> <br /> <br />
					</div>
					<ui:hiddenField binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>