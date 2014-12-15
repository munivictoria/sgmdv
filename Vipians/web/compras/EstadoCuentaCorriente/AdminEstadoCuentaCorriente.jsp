<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" isELIgnored="false" />
	<f:view>
		<ui:page binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.page1}" id="page1">
			<ui:html binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.html1}" id="html1">
			<ui:head binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.head1}" id="head1" title="Estado de Cuenta Corriente">
				<ui:link binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.body1}" focus="form1:btnSeleccionarProveedor" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td colspan="2"></td>
									</tr>
									<tr>
										<td align="center" nowrap="nowrap">
											<ui:staticText escape="false" id="stFiltrarPor" styleClass="textoFiltrarPor" text="Filtrar por" />
										</td>
									</tr>
									<tr>
										<td colspan="4">
											<hr />
										</td>
									</tr>
									<tr>
										<td align="center">
											<ui:panelGroup binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.lblProveedor}" for="tfProveedor" id="label2"
																styleClass="label" text="Proveedor" />
														</td>
														<td>
															<ui:textField binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.tfProveedor}" columns="40" disabled="true"
																id="tfProveedor" styleClass="textField" />
															<ui:button action="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.btnSeleccionarProveedor_action}"
																binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.btnSeleccionarProveedor}" escape="false"
																id="btnSeleccionarProveedor" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar Proveedor" />
															<a4j:commandButton id="btnLimpiarProveedor" reRender="form1:tfProveedor"
																binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.btnLimpiarProveedor}"
																action="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.btnLimpiarProveedor_action}"
																styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td colspan="2"></td>
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
								</tbody>
								<tfoot>
									<tr>
										<td align="right" colspan="2">
											<a4j:commandButton binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.btnBuscar}"
												action="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.btnReiniciar_action}"
												binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1" />
											<ui:staticText binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.stSeparador1}" escape="false"
												id="stSeparador1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.btnCancelar_action}"
												binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.messageGroup}" id="messageGroup"
										showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<tr>
								<td colspan="2">
									<br />
									<ui:staticText binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.stMovimientosIngreso}" id="stMovimientosIngreso"
										styleClass="label2" text="Movimientos de Ingreso" />
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<ui:table binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.table1}" id="table1">
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
														rowGroupId, selected);
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
														.setDisabled(disabled);
												// Set disabled state for bottom actions
												document
														.getElementById(
																"form1:table2:tableActionsBottom:deleteBottom")
														.setDisabled(disabled);
											}
											]]>
										</script>
										<ui:tableRowGroup binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.tableRowGroup1}" id="tableRowGroup1"
											onMouseOver="jsRowMouseOver(this)" onMouseOut="jsRowMouseOut(this)" onClick="jsRowMouseClick(this)"
											onDblClick="funcionSeleccionar()" sourceData="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.ldpMovIngreso}"
											sourceVar="currentRow">
											<ui:tableColumn binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.tcNumeroMovIngreso}" headerText="Número"
												id="tcNumeroMovIngreso" sort="numero" width="5">
												<ui:staticText binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.stNumeroMovIngreso}" id="stNumeroMovIngreso"
													text="#{currentRow.value['numero']}" />
											</ui:tableColumn>
											<ui:tableColumn binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.tcFechaMovIngreso}" headerText="Fecha"
												id="tcFechaMovIngreso" sort="fecha" width="5">
												<ui:staticText binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.stFechaMovIngreso}" id="stFechaMovIngreso"
													text="#{currentRow.value['fecha']}" converter="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.dateTimeConverter1}" />
											</ui:tableColumn>
											<ui:tableColumn binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.tcFactura}" headerText="Factura"
												id="tcFactura" sort="factura">
												<ui:staticText binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.stFactura}" id="stFactura"
													text="#{currentRow.value['factura']}" />
											</ui:tableColumn>
											<ui:tableColumn binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.tcImporteIngreso}" headerText="Importe"
												id="tcImporteIngreso" sort="importe" width="5">
												<ui:staticText binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.stImporteIngreso}" id="stImporteIngreso"
													text="#{currentRow.value['importe']}" />
											</ui:tableColumn>
										</ui:tableRowGroup>
										<f:facet name="actionsTop" />
									</ui:table>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<br />
									<ui:staticText binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.stMovimientosEgreso}" id="stMovimientosEgreso"
										styleClass="label2" text="Movimientos de Egreso" />
								</td>
							</tr>
							<tr>
								<td colspan="2" style="vertical-align: top">
									<ui:table augmentTitle="false" binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.table2}" id="table2"
										styleClass="">
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
														rowGroupId, selected);
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
														.setDisabled(disabled);
												// Set disabled state for bottom actions
												document
														.getElementById(
																"form1:table3:tableActionsBottom:deleteBottom")
														.setDisabled(disabled);
											}
											]]>
										</script>
										<ui:tableRowGroup binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.tableRowGroup2}" id="tableRowGroup2"
											onMouseOver="jsRowMouseOver(this)" onMouseOut="jsRowMouseOut(this)" onClick="jsRowMouseClick(this)"
											onDblClick="funcionSeleccionar()" sourceData="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.ldpMovEgreso}"
											sourceVar="currentRow">
											<ui:tableColumn binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.tcNumeroMovEgreso}" headerText="Número"
												id="tcNumeroMovEgreso" sort="numero">
												<ui:staticText binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.stNumeroMovEgreso}" id="stNumeroMovEgreso"
													text="#{currentRow.value['numero']}" />
											</ui:tableColumn>
											<ui:tableColumn binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.tcFechaMovEgreso}" headerText="Fecha"
												id="tcFechaMovEgreso" sort="fecha">
												<ui:staticText binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.stFechaMovEgreso}" id="stFechaMovEgreso"
													text="#{currentRow.value['fecha']}" converter="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.dateTimeConverter1}" />
											</ui:tableColumn>
											<ui:tableColumn binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.tcOrdenPago}" headerText="Orden de Pago"
												id="tcOrdenPago" sort="documentoEgresoRfr">
												<ui:staticText binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.stOrdenPago}" id="stOrdenPago"
													text="#{currentRow.value['documentoEgresoRfr']}" />
											</ui:tableColumn>
											<ui:tableColumn binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.tcImporteEgreso}" headerText="Importe"
												id="tcImporteEgreso" sort="importe">
												<ui:staticText binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.stImporteEgreso}" id="stImporteEgreso"
													text="#{currentRow.value['importe']}" />
											</ui:tableColumn>
										</ui:tableRowGroup>
										<f:facet name="actionsTop" />
									</ui:table>
								</td>
							</tr>
							<tr>
								<td align="right" colspan="2">
									<ui:label binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.lblSaldo}" id="lblSaldo" styleClass="label"
										text="Saldo:" />
									<ui:staticText binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.stSaldo}"
										converter="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.numberConverter1}" id="stSaldo" styleClass="staticText" />
								</td>
							</tr>
						</div>
					</div>
					<script>
						document
								.getElementById('form1:btnSeleccionarProveedor')
								.focus();
					</script>
					<ui:hiddenField binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.hidIdPagina}" id="hidIdPagina"
						text="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.idPagina}" />
					<ui:hiddenField binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.idSubSesion}" />
					<ui:script binding="#{compras$EstadoCuentaCorriente$AdminEstadoCuentaCorriente.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
