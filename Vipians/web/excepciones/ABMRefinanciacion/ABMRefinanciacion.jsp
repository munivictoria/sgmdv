<?xml version="1.0" encoding="UTF-8"?>
<!-- © Copyright 2015, CoDeSoft Todos los derechos reservados. -->
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui"
	xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.page1}" id="page1">
			<ui:html binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.html1}" id="html1">
			<ui:head binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.head1}" id="head1" title="Consultar Refinanciación">
				<ui:link binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.body1}" focus="form1:tfNombre" id="body1" onLoad="parent.footer.location.reload();Init();"
				style="background-color: rgb(242, 242, 242); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.form1}" id="form1">
					<div align="left" class="formularioABM">
						<table border="0" class="gris">
							<caption>
								<ui:staticText binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<table align="center" width="70%">
											<tr>
												<td colspan="4" nowrap="nowrap" style="height: 16px">
													<br />
												</td>
											</tr>
											<tr>
												<td align="left" colspan="4" nowrap="nowrap">
													<ui:textField binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tfNombreRefinanciacion}" columns="60" disabled="true" id="tfNombreRefinanciacion" styleClass="textField2" />
												</td>
											</tr>
											<tr>
												<td align="left" colspan="4" nowrap="nowrap">
													<br />
												</td>
											</tr>
											<tr>
												<td align="right" style="height: 16px">
													<ui:label binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.lblNroRefinanciacion}" for="tfNroRefinanciacion" id="lblNroRefinanciacion" styleClass="label"
														text="Número de Refinanciación" />
												</td>
												<td nowrap="nowrap">
													<ui:textField binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tfNroRefinanciacion}" columns="8" disabled="true" id="tfNroRefinanciacion" styleClass="textField" />
												</td>
											</tr>
											<tr>
												<td align="right" style="height: 16px">
													<ui:label binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.lblFechaRefinanciacion}" for="tfFechaRefinanciacion" id="lblFechaRefinanciacion" styleClass="label"
														text="Fecha de Refinanciacion" />
												</td>
												<td nowrap="nowrap">
													<ui:textField binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tfFechaRefinanciacion}" columns="10" disabled="true" id="tfFechaRefinanciacion" styleClass="textField" />
												</td>
											</tr>
											<tr>
												<td align="right">
													<ui:label binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.lblEstado}" for="tfEstado" id="lblEstado" styleClass="label" text="Estado" />
												</td>
												<td nowrap="true">
													<ui:textField binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tfEstado}" columns="10" disabled="true" id="tfEstado" styleClass="textField" />
												</td>
											</tr>
											<tr>
												<td align="right" style="height: 16px">
													<ui:label binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.lblContribuyente}" for="tfContribuyente" id="lblContribuyente" styleClass="label" text="Contribuyente" />
												</td>
												<td nowrap="nowrap">
													<ui:textField binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tfContribuyente}" columns="40" disabled="true" id="tfContribuyente" styleClass="textField" />
												</td>
											</tr>
											<tr>
												<td align="right" style="height: 33px">
													<ui:label binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.lblDigesto}" for="tfDigesto" id="lblDigesto" styleClass="label" text="Digesto Municipal" />
												</td>
												<td nowrap="nowrap">
													<ui:textField binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tfDigesto}" columns="40" disabled="true" id="tfDigesto" styleClass="textField" />
												</td>
											</tr>
											<tr>
												<td align="right" style="height: 16px">
													<ui:label binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.lblInmuebles}" id="lblInmuebles" styleClass="label" text="Inmuebles" />
												</td>
												<td>
													<ui:textArea binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.taInmuebles}" columns="40" disabled="true" id="taInmuebles" rows="5" styleClass="textField" />
												</td>
											</tr>
											<tr>
												<td align="right" style="height: 16px">
													<ui:label binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.lblComercios}" id="lblComercios" styleClass="label" text="Comercios" />
												</td>
												<td nowrap="nowrap">
													<ui:textArea binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.taComercios}" columns="40" disabled="true" id="taComercios" rows="5" styleClass="textField" />
												</td>
											</tr>
											<tr>
												<td>
													<br />
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<br />
													<ui:label binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.lblTitle1}" id="lblTitle1" styleClass="label2" text="Detalle de la Refinanciación" />
													<hr />
													<br />
												</td>
											</tr>
											<tr>
												<td align="right" style="height: 25px">
													<ui:label binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.lblCantCuotas}" for="tfCantCuotas" id="lblCantCuotas" styleClass="label" text="Cantidad de Cuotas" />
												</td>
												<td>
													<ui:textField binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tfCantCuotas}" columns="2" disabled="true" id="tfCantCuotas" styleClass="textField" />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap" style="height: 16px">
													<ui:label binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.lblTasaNominal}" for="tfTasaNominal" id="lblTasaNominal" styleClass="label" text="Tasa Nominal Anual" />
												</td>
												<td>
													<ui:textField binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tfTasaNominal}" columns="6" disabled="true" id="tfTasaNominal" styleClass="textField" />
												</td>
											</tr>
											<tr>
												<td align="right" style="height: 21px">
													<ui:label binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.lblInteresDiario}" for="tfInteresDiario" id="lblInteresDiario" styleClass="label" text="Interés Diario" />
												</td>
												<td nowrap="nowrap">
													<ui:textField binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tfInteresDiario}" columns="6" disabled="true" id="tfInteresDiario" styleClass="textField" />
												</td>
												<td align="right" nowrap="nowrap"></td>
												<td align="right" nowrap="nowrap"></td>
											</tr>
											<tr>
												<td colspan="4">
													<br /> <br />
													<hr />
												</td>
											</tr>
											<tr>
												<td align="left" colspan="4" nowrap="true">
													<ui:label binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.lblImporteTotal}" for="tfImporteTotal" id="lblImporteTotal" styleClass="label" text="Importe Total :" />
													<ui:textField binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tfImporteTotal}" columns="10" disabled="true" id="tfImporteTotal" styleClass="textField" />
													<ui:staticText binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.stSeparador1}" escape="false" id="stSeparador1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:label binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.lblImporteCondonado}" for="tfImporteCondonado" id="lblImporteCondonado" styleClass="label"
														text="Importe Condonado:" />
													<ui:textField binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tfImporteCondonado}" columns="10" disabled="true" id="tfImporteCondonado" styleClass="textField" />
													<ui:staticText binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.stSeparador2}" escape="false" id="stSeparador2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:label binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.lblSubTotal}" for="tfSubTotal" id="lblSubTotal" style="true" styleClass="label" text="Importe Refinanciado:" />
													<ui:textField binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tfSubTotal}" columns="10" disabled="true" id="tfSubTotal" styleClass="textField" />
												</td>
											</tr>
											<tr>
												<td align="left" colspan="4" nowrap="true">
													<ui:label binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.lblInteresTotal}" for="tfInteresTotal" id="lblInteresTotal" styleClass="label" text="Interés Total:" />
													<ui:textField binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tfInteresTotal}" columns="10" disabled="true" id="tfInteresTotal" styleClass="textField" />
													<ui:staticText binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.stSeparador3}" escape="false" id="stSeparador3" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:label binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.lblInteresCondonado}" for="tfInteresCondonado" id="lblInteresCondonado" styleClass="label"
														text="Interés Condonado:" />
													<ui:textField binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tfInteresCondonado}" columns="10" disabled="true" id="tfInteresCondonado" styleClass="textField" />
													<ui:staticText binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.stSeparador4}" escape="false" id="stSeparador4" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:label binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.lblInteresRefinanciacion}" for="tfInteresRefinanciacion" id="lblInteresRefinanciacion" styleClass="label"
														text="Interés Refinanciado:" />
													<ui:textField binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tfInteresRefinanciacion}" columns="10" disabled="true" id="tfInteresRefinanciacion" styleClass="textField" />
												</td>
											</tr>
											<tr>
												<td align="left" colspan="4" nowrap="true">
													<ui:label binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.lblRecargoTotal}" for="tfRecargoTotal" id="lblRecargoTotal" styleClass="label" text="Recargo Total:" />
													<ui:textField binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tfRecargoTotal}" columns="10" disabled="true" id="tfRecargoTotal" styleClass="textField" />
													<ui:staticText binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.stSeparador5}" escape="false" id="stSeparador5" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:label binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.lblRecargoCondonado}" for="tfRecargoCondonado" id="lblRecargoCondonado" styleClass="label"
														text="Recargo Condonado:" />
													<ui:textField binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tfRecargoCondonado}" columns="10" disabled="true" id="tfRecargoCondonado" styleClass="textField" />
													<ui:staticText binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.stSeparador6}" escape="false" id="stSeparador6" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:label binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.lblRecargoRefinanciacion}" for="tfRecargoRefinanciacion" id="lblRecargoRefinanciacion" styleClass="label"
														text="Recargo Refinanciado:" />
													<ui:textField binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tfRecargoRefinanciacion}" columns="10" disabled="true" id="tfRecargoRefinanciacion" styleClass="textField" />
												</td>
											</tr>
											<tr>
												<td align="left" colspan="4" nowrap="nowrap">
													<ui:label binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.lblMultaTotal}" for="tfMultaTotal" id="lblMultaTotal" styleClass="label" text="Multa Total" />
													<ui:textField binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tfMultaTotal}" columns="10" disabled="true" id="tfMultaTotal" styleClass="textField" />
													<ui:staticText binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.stSeparador7}" escape="false" id="stSeparador7" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:label binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.lblMultaCondonado}" for="tfMultaCondonado" id="lblMultaCondonado" styleClass="label" text="Multas Condonadas:" />
													<ui:textField binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tfMultaCondonado}" columns="10" disabled="true" id="tfMultaCondonado" styleClass="textField" />
													<ui:staticText binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.stSeparador8}" escape="false" id="stSeparador8" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:label binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.lblMultaRefinanciacion}" for="tfMultaRefinanciacion" id="lblMultaRefinanciacion" styleClass="label"
														text="Multas Refinanciadas:" />
													<ui:textField binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tfMultaRefinanciacion}" columns="10" disabled="true" id="tfMultaRefinanciacion" styleClass="textField" />
												</td>
											</tr>
											<tr>
												<td>
													<br />
												</td>
											</tr>
											<tr>
												<td align="left" colspan="4" nowrap="nowrap">
													<ui:label binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.lblMontoTotal}" for="tfMontoTotal" id="lblMontoTotal" styleClass="label2" text="Monto Total: " />
													<ui:textField binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tfMontoTotal}" columns="10" disabled="true" id="tfMontoTotal" styleClass="textField" />
													<ui:staticText binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.stSeparador9}" escape="false" id="stSeparador9" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:label binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.lblTotalCondonado}" for="tfTotalCondonado" id="lblTotalCondonado" styleClass="label2" text="Total Condonado:" />
													<ui:textField binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tfTotalCondonado}" columns="10" disabled="true" id="tfTotalCondonado" styleClass="textField" />
													<ui:staticText binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.stSeparador10}" escape="false" id="stSeparador10" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:label binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.lblCapitalRefinanciado}" for="tfCapitalRefinanciado" id="lblCapitalRefinanciado" styleClass="label2"
														text="Capital a Pagar:" />
													<ui:textField binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tfCapitalRefinanciado}" columns="10" disabled="true" id="tfCapitalRefinanciado" styleClass="textField" />
												</td>
											</tr>
											<tr>
												<td colspan="2">
													<br />
												</td>
											</tr>
											<tr>
												<td>
													<ui:label binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.lblCuotasGeneradas}" id="lblCuotasGeneradas" styleClass="label2" text="Cuotas Generadas" />
													<hr />
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<ui:table augmentTitle="false" binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.table1}" id="table1">
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
														<ui:tableRowGroup binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tableRowGroup1}" id="tableRowGroup1"
															selected="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.currentRowSelected}" sourceData="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.ldpCuotasGeneradas}"
															sourceVar="currentRow">
															<ui:tableColumn align="center" binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tableColumn1}" id="tableColumn1" valign="middle" width="10">
																<ui:checkbox binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.cbSeleccion}" id="cbSeleccion" label="" name="buttonGroupCB"
																	selected="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.selected}" selectedValue="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.selectedValue}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tableColumn2}" headerText="Cuota Nº" id="tableColumn2" sort="numeroCuota">
																<ui:staticText binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.staticText1}" id="staticText1" text="#{currentRow.value['numeroCuota']}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tableColumn3}" headerText="Cuota Pura" id="tableColumn3" sort="monto">
																<ui:staticText binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.staticText2}" converter="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.numberConverter1}"
																	id="staticText2" text="#{currentRow.value['valorCuota']}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tableColumn4}" headerText="Interés" id="tableColumn4" sort="interes">
																<ui:staticText binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.staticText3}" converter="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.numberConverter1}"
																	id="staticText3" text="#{currentRow.value['interes']}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tableColumn5}" headerText="Recargo" id="tableColumn5" sort="recargo">
																<ui:staticText binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.staticText4}" converter="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.numberConverter1}"
																	id="staticText4" text="#{currentRow.value['recargo']}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tableColumn9}" headerText="Monto Total" id="tableColumn9" sort="monto">
																<ui:staticText binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.staticText8}" converter="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.numberConverter1}"
																	id="staticText8" text="#{currentRow.value['monto']}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tableColumn8}" headerText="Estado" id="tableColumn8" sort="estado">
																<ui:staticText binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.staticText7}" id="staticText7" text="#{currentRow.value['estado']}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tableColumn7}" headerText="Fecha Vencimiento" id="tableColumn7" sort="fechaVencimiento">
																<ui:staticText binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.staticText6}" converter="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.dateTimeConverter1}"
																	id="staticText6" text="#{currentRow.value['fechaVencimiento']}" />
															</ui:tableColumn>
														</ui:tableRowGroup>
														<f:facet name="actionsTop">
															<ui:panelGroup binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.groupPanel1}" id="groupPanel1">
																<ui:textField id="tfFechaActualizarDeuda" styleClass="textField" columns="10"
																	binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tfFechaActualizacionDeuda}"/>
																<ui:checkbox binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.cbAplicarIntereses}" label="Aplicar intereses"/>
																<a4j:commandButton action="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.btnActualizarDeuda_action}"
																	binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.btnActualizarDeuda}" id="btnActualizarDeuda" styleClass="btnAjax" value="Actualizar Deuda" reRender="table1" />
																<ui:staticText binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.stSeparador11}" escape="false" id="stSeparador11" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
																<ui:button action="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.btnImprimirCuotasGeneradas_action}"
																	binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.btnImprimirCuotasGeneradas}" id="btnImprimirCuotasGeneradas" styleClass="button" text="Imprimir Cuotas"
																	onClick="newWindow = window.open('/Vipians/faces/ImpresionServlet', 'Reporte')" />
																<ui:button action="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.btnImprimirTodas_action}"
																	binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.btnImprimirTodas}" id="btnImprimirTodas" styleClass="button" text="Imprimir Todas"
																	onClick="newWindow = window.open('/Vipians/faces/ImpresionServlet', 'Reporte')" />
															</ui:panelGroup>
														</f:facet>
													</ui:table>
												</td>
											</tr>
											<tr>
												<td></td>
											</tr>
											<tr>
												<td>
													<ui:label id="lbDeudaAlDia" text="Deuda al día de hoy:" styleClass="label2" />
													<ui:textField id="tfDeudaAlDia" binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tfDeudaAlDia}" converter="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.numberConverter1}"
														disabled="true" styleClass="textFieldDisabled" columns="10" />
												</td>
											</tr>
											<tr>
												<td>
													<ui:panelGroup id="pgLibreDeuda" binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.pgLibreDeuda}">
														<tr>
															<td colspan="4">
																<div class="div" style="width: 150px; height: 15px;">Libres de Deuda Impresos</div>
																<ui:table binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tablaLibresDeuda}" id="tablaLibresDeuda" clearSortButton="true">
																	<ui:tableRowGroup binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.trgLibresDeuda}" id="tableRowGroup" emptyDataMsg="Ningún registro encontrado."
																		sourceData="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.ldpLibresDeuda}" sourceVar="currentRowLibreDeuda">
																		<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tcNroLibreDeuda}" id="tcNroLibreDeuda" headerText="Nº" sort="nroLibreDeuda">
																			<ui:staticText binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.stNroLibreDeuda}" id="stNroLibreDeuda" text="#{currentRowLibreDeuda.value['nroLibreDeuda']}" />
																		</ui:tableColumn>
																		<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tcSolicitanteLibreDeuda}" id="tcSolicitanteLibreDeuda" headerText="Solicitante" sort="solicitante">
																			<ui:staticText binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.stSolicitanteLibreDeuda}" id="stSolicitanteLibreDeuda"
																				text="#{currentRowLibreDeuda.value['solicitante']}" />
																		</ui:tableColumn>
																		<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tcMotivoLibreDeuda}" id="tcMotivoLibreDeuda" headerText="Motivo" sort="motivo">
																			<ui:textArea binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.taMotivoLibreDeuda}" id="taMotivoLibreDeuda" text="#{currentRowLibreDeuda.value['motivo']}"
																				disabled="true" styleClass="textFieldDisabled" rows="1" columns="20" />
																		</ui:tableColumn>
																		<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tcFechaGeneradaLibreDeuda}" id="tcFechaGeneradaLibreDeuda" headerText="Fecha Impresión"
																			sort="fechaGenerada">
																			<ui:staticText binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.stFechaGeneradaLibreDeuda}" id="stFechaGeneradaLibreDeuda"
																				text="#{currentRowLibreDeuda.value['fechaGenerada']}" converter="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.dateTimeConverter2}" />
																		</ui:tableColumn>
																		<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tcFechaSolicitadaLibreDeuda}" id="tcFechaSolicitadaLibreDeuda" headerText="Fecha Solicitada"
																			sort="fechaSolicitada">
																			<ui:staticText binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.stFechaSolicitadaLibreDeuda}" id="stFechaSolicitadaLibreDeuda"
																				text="#{currentRowLibreDeuda.value['fechaSolicitada']}" converter="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.dateTimeConverter1}" />
																		</ui:tableColumn>
																		<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tcUsuario}" id="tcUsuario" headerText="Confeccionado por" sort="usuario">
																			<ui:staticText binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.stUsuario}" id="stUsuario" text="#{currentRowLibreDeuda.value['usuario']}" />
																		</ui:tableColumn>
																		<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tcObservacionesLibreDeuda}" id="tcObservacionesLibreDeuda" headerText="Observaciones"
																			sort="observaciones">
																			<ui:textArea binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.taObservacionesLibreDeuda}" id="taObservacionesLibreDeuda"
																				text="#{currentRowLibreDeuda.value['observaciones']}" disabled="true" styleClass="textFieldDisabled" rows="1" columns="30" />
																		</ui:tableColumn>
																	</ui:tableRowGroup>
																</ui:table>
															</td>
														</tr>
													</ui:panelGroup>
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<br />
												</td>
											</tr>
											<tr>
												<td>
													<ui:label binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.lblResumenDeuda}" id="lblResumenDeuda" styleClass="label2" text="Resumen de Deuda" />
													<hr />
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<ui:table augmentTitle="false" binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.table2}" id="table2">
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
														<ui:tableRowGroup binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tableRowGroup2}" id="tableRowGroup1" rows="15"
															sourceData="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.ldpPeriodosAdeudados}" sourceVar="currentRow">
															<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tableColumn10}" headerText="Período" id="tableColumn10" sort="stringPeriodoLiquidado">
																<ui:textArea id="taPeriodoLiquidado" disabled="true"
																text="#{currentRow.value['stringPeriodoLiquidado']}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tableColumn11}" headerText="Obligación" id="tableColumn11" sort="stringObligacion">
																<ui:textArea id="taObligacion" disabled="true" text="#{currentRow.value['stringObligacion']}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tableColumn12}" headerText="Monto" id="tableColumn12" sort="monto">
																<ui:staticText binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.staticText11}" converter="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.numberConverter1}"
																	id="staticText11" text="#{currentRow.value['monto']}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tableColumn13}" headerText="Interés" id="tableColumn13" sort="interes">
																<ui:staticText binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.staticText12}" converter="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.numberConverter1}"
																	id="staticText12" text="#{currentRow.value['interes']}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tableColumn14}" headerText="Recargo" id="tableColumn14" sort="recargo">
																<ui:staticText binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.staticText13}" converter="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.numberConverter1}"
																	id="staticText13" text="#{currentRow.value['recargo']}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tableColumn15}" headerText="Tipo de Deuda" id="tableColumn15" sort="tipoDeuda">
																<ui:staticText binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.staticText14}" id="staticText14" text="#{currentRow.value['tipoDeuda']}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.tableColumn16}" headerText="Vencimiento" id="tableColumn16" sort="fechaVencimiento">
																<ui:staticText binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.staticText15}" converter="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.dateTimeConverter1}"
																	id="staticText15" text="#{currentRow.value['fechaVencimiento']}" />
															</ui:tableColumn>
														</ui:tableRowGroup>
													</ui:table>
												</td>
											</tr>
											<tr>
												<td colspan="2">
													<div>
														<a4j:outputPanel ajaxRendered="true">
															<ui:messageGroup binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.messageGroup1}" id="messageGroup1" styleClass="grupoMsg" />
														</a4j:outputPanel>
													</div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="2" nowrap="nowrap">
										<ui:button action="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.btnGuardar_action}" binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.btnGuardar}" id="btnGuardar"
											styleClass="button" />
										<ui:staticText binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.stSeparador12}" escape="false" id="stSeparador12" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.btnCancelar_action}" binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.btnCancelar}" id="btnCancelar"
											styleClass="button" text="Volver" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.hidIdPagina}" id="hidIdPagina" text="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.idPagina}" />
					<ui:hiddenField binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.hidIdSubSesion}" id="hidIdSubSesion" text="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.idSubSesion}" />
					<ui:script binding="#{excepciones$ABMRefinanciacion$ABMRefinanciacion.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>