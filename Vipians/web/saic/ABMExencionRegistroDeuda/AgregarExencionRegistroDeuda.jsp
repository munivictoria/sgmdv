<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.page1}" id="page1">
			<ui:html binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.html1}" id="html1">
			<ui:head binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.head1}" id="head1"
				title="Agregar Exenciones de Registro de Deuda">
				<ui:link binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.lblNombre}" for="tfNombre" id="lblNombre"
											styleClass="label" text="Nombre" />
									</td>
									<td>
										<ui:textField binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.tfNombre}" columns="50" disabled="false"
											id="tfNombre" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.lblPorcentaje}" for="tfPorcentaje"
											id="lblPorcentaje" styleClass="label" text="Porcentaje" />
									</td>
									<td>
										<ui:textField binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.tfPorcentaje}" id="tfPorcentaje" columns="10"
											disabled="false" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.lblMotivo}" for="taMotivo" id="lblMotivo"
											styleClass="label" text="Motivo" />
									</td>
									<td>
										<ui:textArea binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.taMotivo}" columns="37" id="taMotivo" rows="5"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.lblDigestoMunicipal}" id="lblDigestoMunicipal"
											styleClass="label" text="Digesto Municipal" />
									</td>
									<td>
										<ui:textField binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.tfDigestoMunicipal}" columns="40"
											disabled="true" id="tfDigestoMunicipal" styleClass="textFieldDisabled" />
										<ui:button action="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.btnSeleccionarDigesto_action}"
											binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.btnSeleccionarDigesto}" escape="false"
											id="btnSeleccionarDigesto" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" />
										<a4j:commandButton id="btnLimpiarDigesto" reRender="form1:tfDigestoMunicipal" title="Limpiar"
											binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.btnLimpiarDigesto}"
											action="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.btnLimpiarDigesto_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<hr />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:label binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.label3}" id="label3" styleClass="label2"
											text="Período" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.lblCalendarios}" for="ddCalendarios"
											id="lblCalendarios" styleClass="label" text="Calendarios" />
									</td>
									<td>
										<ui:dropDown binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.ddCalendarios}" id="ddCalendarios"
											styleClass="textField" items="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.ddCalendariosOptions.options}"
											valueChangeListener="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.valueChangeEvent(event)}"
											onChange="this.form.submit()" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.lblPeriodos}" for="ddPeriodos" id="lblPeriodos"
											styleClass="label" text="Periodos" />
									</td>
									<td>
										<ui:dropDown binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.ddPeriodos}" id="ddPeriodos"
											styleClass="textField" items="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.ddPeriodosOptions.options}"
											valueChangeListener="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.valueChangeEventDdPeriodos(event)}"
											onChange="this.form.submit()" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.lblCuotas}" for="ddCuotas" id="lblCuotas"
											styleClass="label" text="Cuotas" />
									</td>
									<td>
										<ui:dropDown binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.ddCuotas}" id="ddCuotas" styleClass="textField"
											items="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.ddCuotasOptions.options}" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<hr />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:label binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.label7}" id="label7" styleClass="label2"
											text="Periodicidad de la cuota" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.label8}" for="ddPeriodicidadCuota" id="label8"
											styleClass="label" text="Periodicidad" />
									</td>
									<td>
										<ui:dropDown binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.ddPeriodicidadCuota}" id="ddPeriodicidadCuota"
											items="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.ddPeriodicidadCuotaDefaultOptions.options}"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:messageGroup binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.messageGroup1}" id="messageGroup1"
											styleClass="grupoMsg" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:table binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.table1}" id="table1">
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
											<ui:tableRowGroup binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.tableRowGroup1}"
												emptyDataMsg="Ningún registro encontrado." id="tableRowGroup1"
												sourceData="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.ldpExencion}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.tableColumn1}"
													id="tableColumn1" valign="middle" width="10">
													<ui:radioButton binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.radioButton1}" id="radioButton1" label=""
														name="buttonGroup" selected="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.RBSelected}"
														selectedValue="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.currentRow3}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.tcPeriodo}" headerText="Período"
													id="tcPeriodo" sort="stringPeriodoLiquidado">
													<ui:staticText binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.stPeriodo}" id="stPeriodo"
														text="#{currentRow.value['stringPeriodoLiquidado']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.tcObligacion}" headerText="Obligación"
													id="tcObligacion" sort="obligacion">
													<ui:staticText binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.stObligacion}" id="stObligacion"
														text="#{currentRow.value['stringObligacion']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.tcMontoExento}" headerText="Monto Exento"
													id="tcMontoExento" sort="montoExencion">
													<ui:staticText binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.stMontoExento}"
														converter="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.numberConverter1}" id="stMontoExento"
														text="#{currentRow.value['montoExencion']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.tcMonto}" headerText="Monto con Exención"
													id="tcMonto" sort="monto">
													<ui:staticText binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.stMonto}"
														converter="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.numberConverter1}" id="stMonto"
														text="#{currentRow.value['monto']}" />
												</ui:tableColumn>
												<!--                            NUEVO                               -->
												<ui:tableColumn binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.tcFechaVencimiento}"
													headerText="Fecha Vencimiento" id="tcFechaVencimiento" sort="fechaVencimiento">
													<ui:staticText binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.stFechaVencimiento}"
														id="stFechaVencimiento" converter="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.dateTimeConverter1}"
														text="#{currentRow.value['fechaVencimiento']}" />
												</ui:tableColumn>
												<!--                            Fin nuevo                           -->
												<ui:tableColumn binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.tcReferenciaNotaHCD}"
													headerText="Referencia Nota HCD" id="tcReferenciaNotaHCD" sort="referenciaNotaHCD">
													<ui:textField binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.tfNotaHCD}" id="tfNotaHCD" columns="15"
														styleClass="textField" text="#{currentRow.value['referenciaNotaHCD']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.groupPanel1}" id="groupPanel1"
													separator="&lt;br/&gt;&lt;br/&gt;">
													<ui:panelGroup binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.gpBotones}" id="gpBotones">
														<ui:button action="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.btnSeleccionarLiquidacionOSP_action}"
															binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.btnSeleccionarLiquidacionOSP}" id="btnLiquidacionOSP"
															styleClass="button" text="Liquidación OSP" />
														<ui:button action="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.btnSeleccionarLiquidacionTGI_action}"
															binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.btnSeleccionarLiquidacionTGI}" id="btnLiquidacionTGI"
															styleClass="button" text="Liquidación TGI" />
														<ui:button action="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.btnSeleccionarLiquidacionPFO_action}"
															binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.btnSeleccionarLiquidacionPFO}" id="btnLiquidacionPFO"
															styleClass="button" text="Liquidación PFO" />
														<ui:button action="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.btnSeleccionarLiquidacionSHPS_action}"
															binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.btnSeleccionarLiquidacionSHPS}" id="btnLiquidacionSHPS"
															styleClass="button" text="Liquidación SHPS" />
														<ui:button action="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.btnSeleccionarReliquidacion_action}"
															binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.btnSeleccionarReliquidacion}" id="btnReliquidacion"
															styleClass="button" text="Reliquidación" />
														<ui:staticText binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.staticText7}" escape="false"
															id="staticText7" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.btnQuitar_action}"
															binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.btnQuitar}" id="btnQuitar" styleClass="button"
															text="Quitar" />
													</ui:panelGroup>
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="true">
										<ui:button action="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.btnGuardar_action}"
											binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.btnGuardar}" id="btnGuardar" styleClass="button"
											text="Guardar" />
										<ui:staticText binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.btnCancelar_action}"
											binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.btnCancelar}" id="btnCancelar" styleClass="button"
											text="Cancelar" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.hidIdPagina}" id="hidIdPagina"
						text="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.idPagina}" />
					<ui:hiddenField binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.idSubSesion}" />
					<ui:script binding="#{saic$ABMExencionRegistroDeuda$AgregarExencionRegistroDeuda.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>