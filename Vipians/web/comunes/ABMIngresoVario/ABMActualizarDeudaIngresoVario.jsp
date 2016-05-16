<?xml version="1.0" encoding="UTF-8"?>
<!-- © Copyright 2015, CoDeSoft Todos los derechos reservados. -->
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.page1}" id="page1">
			<ui:html binding="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.html1}" id="html1">
			<ui:head binding="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.head1}" id="head1" title="Actualizar Deuda Ingreso Vario">
				<ui:link binding="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.link1}" id="link1" url="/resources/stylesheet.css" />
				<script type="text/javascript">
					<![CDATA[
					var nombreBean = "comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario";

					function cargarComportamientoJQuery() {
						calendarioEnTextField("#form1:tfFechaVencimiento");
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.body1}" id="body1"
				onLoad="parent.footer.location.reload();Init();activarDesactivarTfValor();"
				style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<table align="center" width="70%">
											<tr>
												<td colspan="2"></td>
											</tr>
											<tr>
												<td>
													<ui:label binding="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.lbFechaVencimiento}" for="tfFechaVencimiento"
														id="lbFechaVencimiento" styleClass="label" text="Fecha del Nuevo Vencimiento   " />
													<ui:textField binding="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.tfFechaVencimiento}" columns="10"
														id="tfFechaVencimiento" styleClass="textField" />
												</td>
											</tr>
											<tr>
												<td>
													<ui:label binding="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.lbAplicarIntereses}" for="cbAplicarIntereses"
														id="lbAplicarIntereses" styleClass="label" text="Aplicar intereses" />
													<ui:checkbox binding="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.cbAplicarIntereses}" id="cbAplicarIntereses"
														selected="true" />
												</td>
											</tr>
											<tr>
												<td colspan="2">
													<br />
												</td>
											</tr>
											<tr>
												<td colspan="2">
													<ui:label id="lblIngresosVarios" styleClass="label2" text="Ingresos Varios" />
												</td>
											</tr>
											<tr>
												<td colspan="2" nowrap="nowrap">
													<ui:table augmentTitle="false" binding="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.tbIngresosVarios}"
														id="tbIngresosVarios">
														<script>
															<![CDATA[
															/* ----- Functions for Table Preferences Panel ----- */
															/*
															 * Toggle the table preferences panel open or closed
															 */
															function togglePreferencesPanel() {
																var table = document.getElementById("form1:tbIngresosVarios");
																table.toggleTblePreferencesPanel();
															}
															/* ----- Functions for Filter Panel ----- */
															/*
															 * Return true if the filter menu has actually changed,
															 * so the corresponding event should be allowed to continue.
															 */
															function filterMenuChanged() {
																var table = document.getElementById("form1:tbIngresosVarios");
																return table.filterMenuChanged();
															}
															/*
															 * Toggle the custom filter panel (if any) open or closed.
															 */
															function toggleFilterPanel() {
																var table = document.getElementById("form1:tbIngresosVarios");
																return table.toggleTableFilterPanel();
															}
															/* ----- Functions for Table Actions ----- */
															/*
															 * Initialize all rows of the table when the state
															 * of selected rows changes.
															 */
															function initAllRows() {
																var table = document.getElementById("form1:tbIngresosVarios");
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
																var table = document.getElementById("form1:tbIngresosVarios");
																table.selectGroupRows(rowGroupId, selected);
															}
															/*
															 * Disable all table actions if no rows have been selected.
															 */
															function disableActions() {
																// Determine whether any rows are currently selected
																var table = document.getElementById("form1:tbIngresosVarios");
																var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
																// Set disabled state for top actions
																document.getElementById("form1:tbIngresosVarios:tableActionsTop:deleteTop")
																		.setDisabled(disabled);
																// Set disabled state for bottom actions
																document.getElementById("form1:tbIngresosVarios:tableActionsBottom:deleteBottom")
																		.setDisabled(disabled);
															}
															]]>
														</script>
														<ui:tableRowGroup binding="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.tbrgIngresosVarios}"
															id="tbrgIngresosVarios" sourceData="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.ldpIngresosVarios}"
															sourceVar="currentRow1">
															<ui:tableColumn align="center" onClick="setTimeout('initAllRows()', 0)"
																binding="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.tcCheckBox}" id="tcCheckBox" valign="middle" width="10">
																<ui:checkbox binding="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.checkBoxSeleccion}" id="checkbox1"
																	selected="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.selected}"
																	selectedValue="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.currentRow1}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.tableColumn1}" headerText="Persona"
																id="tableColumn1">
																<ui:staticText binding="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.stPersona}" id="stPersona"
																	text="#{currentRow1.value['persona']}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.tableColumn2}" headerText="Concepto"
																id="tableColumn2">
																<ui:staticText binding="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.stConcepto}" id="stConcepto"
																	text="#{currentRow1.value['conceptoIngresoVario']}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.tableColumn3}" headerText="Monto"
																id="tableColumn3">
																<ui:staticText binding="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.stMonto}" id="stMonto"
																	text="#{currentRow1.value['monto']}" converter="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.ncMonto}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.tableColumn4}" headerText="Vencimiento"
																id="tableColumn4">
																<ui:staticText binding="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.stVencimiento}" id="stVencimiento"
																	text="#{currentRow1.value['fechaVencimiento']}"
																	converter="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.dtcFecha}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.tableColumn5}" headerText="Estado"
																id="tableColumn5">
																<ui:staticText binding="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.stEstado}" id="stEstado"
																	text="#{currentRow1.value['estado']}" />
															</ui:tableColumn>
														</ui:tableRowGroup>
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
													<ui:messageGroup binding="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.messageGroup1}" id="messageGroup1"
														styleClass="grupoMsg" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="2" nowrap="nowrap" style="height: 33px">
										<ui:button action="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.btnGuardar_action}"
											binding="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.btnGuardar}" id="btnActualizarDeuda" styleClass="button" />
										<ui:staticText binding="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.btnCancelar_action}"
											binding="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.hidIdPagina}" id="hidIdPagina"
						text="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.idPagina}" />
					<ui:hiddenField binding="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.idSubSesion}" />
					<ui:script binding="#{comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>