<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.page1}" id="page1">
			<ui:html binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.html1}" id="html1">
			<ui:head binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.head1}" id="head1">
				<ui:link binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.body1}" focus="form1:tfNumero" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.lblNumero}" for="tfNumero" id="lblNumero"
											styleClass="label" text="Número" />
									</td>
									<td>
										<ui:textField binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.tfNumero}" id="tfNumero"
											styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.lblFecha}" for="tfFecha" id="lblFecha"
											styleClass="label" text="Fecha" />
									</td>
									<td>
										<ui:textField binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.tfFecha}" id="tfFecha" maxLength="10"
											styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)"/>
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:label binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.lblFacturas}" id="lblFacturas"
											styleClass="label57" text="Facturas" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.tablaFacturas}"
											id="tablaFacturas">
											<script>
												<![CDATA[
												/* ----- Functions for Table Preferences Panel ----- */
												/*
												 * Toggle the table preferences panel open or closed
												 */
												function togglePreferencesPanel() {
													var table = document
															.getElementById("form1:tablaFacturas");
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
															.getElementById("form1:tablaFacturas");
													return table
															.filterMenuChanged();
												}
												/*
												 * Toggle the custom filter panel (if any) open or closed.
												 */
												function toggleFilterPanel() {
													var table = document
															.getElementById("form1:tablaFacturas");
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
															.getElementById("form1:tablaFacturas");
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
															.getElementById("form1:tablaFacturas");
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
															.getElementById("form1:tablaFacturas");
													var disabled = (table
															.getAllSelectedRowsCount() > 0) ? false
															: true;
													// Set disabled state for top actions
													document
															.getElementById(
																	"form1:tablaFacturas:tableActionsTop:deleteTop")
															.setDisabled(
																	disabled);
													// Set disabled state for bottom actions
													document
															.getElementById(
																	"form1:tablaFacturas:tableActionsBottom:deleteBottom")
															.setDisabled(
																	disabled);
												}
												]]>
											</script>
											<ui:tableRowGroup binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.tableRowGroup1}" id="tableRowGroup1"
												sourceData="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.ldpFacturas}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.tableColumn1}" id="tableColumn1"
													valign="middle" width="10">
													<ui:radioButton binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.radioButton1}" id="radioButton1" label=""
														name="buttonGroup" selected="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.RBSelected}"
														selectedValue="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.tcFechaEmision}" headerText="Fecha de Emisión" id="tcFechaEmision"
													sort="fechaEmision">
													<ui:staticText binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.stFechaEmision}" id="stFechaEmision"
														text="#{currentRow.value['fechaEmision']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.tcTipoFactura}" headerText="Tipo de Factura"
													id="tcTipoFactura" sort="tipoFactura">
													<ui:staticText binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.stTipoFactura}"
														id="stTipoFactura" text="#{currentRow.value['tipoFactura']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.tcProveedor}" headerText="Proveedor"
													id="tcProveedor" sort="proveedor">
													<ui:staticText binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.stProveedor}"
														id="stProveedor" text="#{currentRow.value['proveedor']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.tcMonto}" headerText="Monto"
													id="tcMonto" sort="total">
													<ui:staticText binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.stMonto}"
														id="stMonto" text="#{currentRow.value['total']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.groupPanel1}" id="groupPanel1">
													<ui:staticText text="Seleccionar Factura: "/>
													<ui:button action="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.btnSeleccionarFacturaProveedor_action}"
														binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.btnSeleccionarFacturaProveedor}" id="btnSeleccionarFacturaProveedor"
														styleClass="button" text="de Proveedor" toolTip="Seleccionar una Factura de Proveedor"/>
													<ui:button action="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.btnSeleccionarFacturaServicio_action}"
														binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.btnSeleccionarFacturaServicio}" id="btnSeleccionarFacturaServicio"
														styleClass="button" text="de Servicio" toolTip="Seleccionar una Factura de Servicio"/>
													<ui:button action="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.btnSeleccionarFacturaContrato_action}"
														binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.btnSeleccionarFacturaContrato}" id="btnSeleccionarFacturaContrato"
														styleClass="button" text="de Contrato" toolTip="Seleccionar una Factura de Contrato"/>
													<ui:button action="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.btnSeleccionarFacturaSubsidio_action}"
														binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.btnSeleccionarFacturaSubsidio}" id="btnSeleccionarFacturaSubsidio"
														styleClass="button" text="de Subsidio" toolTip="Seleccionar una Factura de Subsidio"/>
													<ui:staticText text="#{compras$ABMBien$AdminBien.textoSeparador}" escape="false" id="stSeparador" />
													<ui:button action="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.btnQuitarFactura_action}"
														binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.btnQuitarFactura}" id="btnQuitarFactura"
														styleClass="button" text="Quitar" toolTip="Quitar una Factura"/>
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:label binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.lblLineasLiquidacionCompra}" id="lblLineasLiquidacionCompra"
											styleClass="label57" text="Líneas de Liquidación de Compra" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.tablaLineasLiquidacionCompra}"
											id="tablaLineasLiquidacionCompra">
											<script>
												<![CDATA[
												/* ----- Functions for Table Preferences Panel ----- */
												/*
												 * Toggle the table preferences panel open or closed
												 */
												function togglePreferencesPanel() {
													var table = document
															.getElementById("form1:tablaLineasLiquidacionCompra");
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
															.getElementById("form1:tablaLineasLiquidacionCompra");
													return table
															.filterMenuChanged();
												}
												/*
												 * Toggle the custom filter panel (if any) open or closed.
												 */
												function toggleFilterPanel() {
													var table = document
															.getElementById("form1:tablaLineasLiquidacionCompra");
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
															.getElementById("form1:tablaLineasLiquidacionCompra");
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
															.getElementById("form1:tablaLineasLiquidacionCompra");
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
															.getElementById("form1:tablaLineasLiquidacionCompra");
													var disabled = (table
															.getAllSelectedRowsCount() > 0) ? false
															: true;
													// Set disabled state for top actions
													document
															.getElementById(
																	"form1:tablaLineasLiquidacionCompra:tableActionsTop:deleteTop")
															.setDisabled(
																	disabled);
													// Set disabled state for bottom actions
													document
															.getElementById(
																	"form1:tablaLineasLiquidacionCompra:tableActionsBottom:deleteBottom")
															.setDisabled(
																	disabled);
												}
												]]>
											</script>
											<ui:tableRowGroup binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.tableRowGroup2}" id="tableRowGroup2"
												sourceData="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.ldpLineasLiquidacionCompra}" sourceVar="currentRow2">
												<ui:tableColumn binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.tcBien}" headerText="Bien" id="tcBien"
													sort="nombre">
													<ui:staticText binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.stBien}" id="stBien"
														text="#{currentRow2.value['nombre']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.tcUnidadMedida}" headerText="Unidad de Medida"
													id="tcUnidadMedida" sort="unidadMedida">
													<ui:staticText binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.stUnidadMedida}"
														id="stUnidadMedida" text="#{currentRow2.value['unidadMedida']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.tcCantidad}" headerText="Cantidad"
													id="tcCantidad" sort="cantidad">
													<ui:staticText binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.stCantidad}"
														id="stCantidad" text="#{currentRow2.value['cantidad']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.tcPrecioUnitario}" headerText="Precio Unitario"
													id="tcPrecioUnitario" sort="montoUnitario">
													<ui:staticText binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.stPrecioUnitario}"
														id="stPrecioUnitario" text="#{currentRow2.value['montoUnitario']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.tcPrecioTotal}" headerText="Precio Total"
													id="tcPrecioTotal" sort="montoTotal">
													<ui:staticText binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.stPrecioTotal}"
														id="stPrecioTotal" text="#{currentRow2.value['montoTotal']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
										</ui:table>
									</td>
								</tr>
								<tr><td colspan="4"><br/></td></tr>
								<tr><td align="right"><ui:label binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria"/></td>
								<td><ui:textArea binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.taComentarioLogAuditoria}" id="taComentarioLogAuditoria"/></td></tr><tr><td><br/></td></tr>
								<tr><td colspan="4"><ui:table binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.tablaLogs}" id="tbLogsAuditoria"/></td></tr>
								<tr>
									<td colspan="2">
										<ui:messageGroup binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.messageGroup}" id="messageGroup"
											styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.btnGuardar_action}"
											binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.btnCancelar_action}"
											binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.idSubSesion}" />
					<ui:script binding="#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>