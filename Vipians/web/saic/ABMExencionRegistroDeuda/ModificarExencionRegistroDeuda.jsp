<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.page1}" id="page1">
			<ui:html binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.html1}" id="html1">
			<ui:head binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.head1}" id="head1"
				title="Modificar Exenciones de Registro de Deuda">
				<ui:link binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.stTitulo}" id="stTitulo"
									styleClass="tituloABM" text="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.lblNombre}" for="tfNombre" id="lblNombre"
											styleClass="label" text="Nombre" />
									</td>
									<td>
										<ui:textField binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.tfNombre}" columns="50" disabled="false"
											id="tfNombre" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.lblPorcentaje}" for="tfPorcentaje"
											id="lblPorcentaje" styleClass="label" text="Porcentaje" />
									</td>
									<td>
										<ui:textField binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.tfPorcentaje}" id="tfPorcentaje"
											columns="10" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.lblMotivo}" for="taMotivo" id="lblMotivo"
											styleClass="label" text="Motivo" />
									</td>
									<td>
										<ui:textArea binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.taMotivo}" columns="37" id="taMotivo"
											rows="5" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.lblDigestoMunicipal}" id="lblDigestoMunicipal"
											styleClass="label" text="Digesto Municipal" />
									</td>
									<td>
										<ui:textField binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.tfDigestoMunicipal}" columns="40"
											disabled="true" id="tfDigestoMunicipal" styleClass="textFieldDisabled" />
										<ui:button action="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.btnSeleccionarDigesto_action}"
											binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.btnSeleccionarDigesto}" escape="false"
											id="btnSeleccionarDigesto" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" />
										<a4j:commandButton id="btnLimpiarDigesto" reRender="form1:tfDigestoMunicipal"
											binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.btnLimpiarDigesto}"
											action="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.btnLimpiarDigesto_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<hr />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:label binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.label3}" id="label3" styleClass="label2"
											text="Período" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.label4}" for="tfAnio" id="label4"
											styleClass="label" text="Año" />
									</td>
									<td>
										<ui:textField binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.tfAnio}" columns="10" id="tfAnio"
											maxLength="4" styleClass="textField" />
										<ui:staticText binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.staticText5}" escape="false"
											id="staticText5" styleClass="label" text="&amp;nbsp;[aaaa]" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.label5}" for="ddPeriodicidad" id="label5"
											styleClass="label" text="Periodicidad" />
									</td>
									<td>
										<ui:dropDown binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.ddPeriodicidad}" id="ddPeriodicidad"
											items="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.ddPeriodicidadDefaultOptions.options}"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.label6}" for="tfPeriodo" id="label6"
											styleClass="label" text="Período Número" />
									</td>
									<td>
										<ui:textField binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.tfPeriodo}" columns="10" id="tfPeriodo"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<hr />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:label binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.label7}" id="label7" styleClass="label2"
											text="Periodicidad de la cuota" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.label8}" for="ddPeriodicidadCuota" id="label8"
											styleClass="label" text="Periodicidad" />
									</td>
									<td>
										<ui:dropDown binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.ddPeriodicidadCuota}"
											id="ddPeriodicidadCuota"
											items="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.ddPeriodicidadCuotaDefaultOptions.options}"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:messageGroup binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.messageGroup1}" id="messageGroup1"
											styleClass="grupoMsg" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:table binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.table1}" id="table1">
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
											<ui:tableRowGroup binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.tableRowGroup1}"
												emptyDataMsg="Ningún registro encontrado." id="tableRowGroup1"
												sourceData="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.ldpRegistroDeuda}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.tableColumn1}"
													id="tableColumn1" valign="middle" width="10">
													<ui:radioButton binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.radioButton1}" id="radioButton1"
														label="" name="buttonGroup" selected="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.RBSelected}"
														selectedValue="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.currentRow3}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.tcPeriodo}" headerText="Período"
													id="tcPeriodo" sort="stringPeriodoLiquidado">
													<ui:staticText binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.stPeriodo}" id="stPeriodo"
														text="#{currentRow.value['stringPeriodoLiquidado']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.tcObligacion}" headerText="Obligacion"
													id="tcObligacion" sort="obligacion">
													<ui:staticText binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.stObligacion}" id="stObligacion"
														text="#{currentRow.value['stringObligacion']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.tcMontoExento}"
													headerText="Monto Exento" id="tcMontoExento" sort="montoExencion">
													<ui:staticText binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.stMontoExento}"
														converter="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.numberConverter1}" id="stMontoExento"
														text="#{currentRow.value['montoExencion']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.tcMonto}"
													headerText="Monto con Exención" id="tcMonto" sort="monto">
													<ui:staticText binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.stMonto}"
														converter="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.numberConverter1}" id="stMonto"
														text="#{currentRow.value['monto']}" />
												</ui:tableColumn>
												<!--                            NUEVO                               -->
												<ui:tableColumn binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.tcFechaVencimiento}"
													headerText="Fecha Vencimiento" id="tcFechaVencimiento" sort="fechaVencimiento">
													<ui:staticText binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.stFechaVencimiento}"
														id="stFechaVencimiento" converter="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.dateTimeConverter1}"
														text="#{currentRow.value['fechaVencimiento']}" />
												</ui:tableColumn>
												<!--                            Fin nuevo                           -->
												<ui:tableColumn binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.tcReferenciaNotaHCD}"
													headerText="Referencia Nota HCD" id="tcReferenciaNotaHCD" sort="referenciaNotaHCD">
													<ui:textField binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.tfNotaHCD}" id="tfNotaHCD" columns="15"
														styleClass="textField" text="#{currentRow.value['referenciaNotaHCD']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.groupPanel1}" id="groupPanel1"
													separator="&lt;br/&gt;&lt;br/&gt;">
													<ui:panelGroup binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.gpBotones}" id="gpBotones">
														<ui:button action="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.btnSeleccionarLiquidacionOSP_action}"
															binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.btnSeleccionarLiquidacionOSP}" id="btnLiquidacionOSP"
															styleClass="button" text="Liquidación OSP" />
														<ui:button action="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.btnSeleccionarLiquidacionTGI_action}"
															binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.btnSeleccionarLiquidacionTGI}" id="btnLiquidacionTGI"
															styleClass="button" text="Liquidación TGI" />
														<ui:button action="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.btnSeleccionarLiquidacionPFO_action}"
															binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.btnSeleccionarLiquidacionPFO}" id="btnLiquidacionPFO"
															styleClass="button" text="Liquidación PFO" />
														<ui:button action="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.btnSeleccionarLiquidacionSHPS_action}"
															binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.btnSeleccionarLiquidacionSHPS}"
															id="btnLiquidacionSHPS" styleClass="button" text="Liquidación SHPS" />
														<ui:button action="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.btnSeleccionarReliquidacion_action}"
															binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.btnSeleccionarReliquidacion}" id="btnReliquidacion"
															styleClass="button" text="Reliquidación" />
														<ui:staticText binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.staticText7}" escape="false"
															id="staticText7" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.btnQuitar_action}"
															binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.btnQuitar}" id="btnQuitar" styleClass="button"
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
										<ui:button action="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.btnGuardar_action}"
											binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.btnGuardar}" id="btnGuardar" styleClass="button"
											text="Guardar" />
										<ui:staticText binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.stSeparador}" escape="false"
											id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.btnCancelar_action}"
											binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.btnCancelar}" id="btnCancelar" styleClass="button"
											text="Cancelar" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.hidIdPagina}" id="hidIdPagina"
						text="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.idPagina}" />
					<ui:hiddenField binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.idSubSesion}" />
					<ui:script binding="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>