<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.page1}" id="page1">
			<ui:html binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.html1}" id="html1">
			<ui:head binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.head1}" id="head1">
				<ui:link binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
						function cargarComportamientoJQuery() {
							calendarioEnTextField("#form1:tfFecha");
						}
						$(document).ready(function() {
							cargarComportamientoJQuery();
						});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.body1}" focus="form1:tfFecha" id="body1"
				onLoad="parent.footer.location.reload(); Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{compras$ABMFacturaContrato$ABMFacturaContrato.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.lblFecha}" for="tfFecha" id="lblFecha" style=""
											styleClass="label" text="Fecha Emisión" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.tfFecha}" columns="10" id="tfFecha" styleClass="textField"/>
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.lblNumeroFactura}" for="tfNumeroFactura" id="lblNumeroFactura"
											styleClass="label" text="Nº de Factura" />
									</td>
									<td>
										<ui:textField binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.tfNumeroFactura}" columns="20" id="tfNumeroFactura"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label for="tfContrato" id="lblContrato" styleClass="label" text="Contrato" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.tfContrato}" columns="40" disabled="true" id="tfContrato"
											styleClass="textField" />
										<ui:button action="#{compras$ABMFacturaContrato$ABMFacturaContrato.btnSeleccionarContrato_action}"
											binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.btnSeleccionarContrato}" escape="false" id="btnSeleccionarContrato"
											mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton title="Limpiar" id="btnLimpiarContrato" reRender="form1:tfContrato" binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.btnLimpiarContrato}"
											action="#{compras$ABMFacturaContrato$ABMFacturaContrato.btnLimpiarContrato_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.lblProveedor}" for="tfProveedor" id="lblProveedor"
											styleClass="label" text="Proveedor" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.tfProveedor}" columns="40" disabled="true" id="tfProveedor"
											styleClass="textField" />
										<ui:button action="#{compras$ABMFacturaContrato$ABMFacturaContrato.btnSeleccionarProveedor_action}"
											binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.btnSeleccionarProveedor}" escape="false" id="btnSeleccionarProveedor"
											mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton title="Limpiar" id="btnLimpiarProveedor" reRender="form1:tfProveedor"
											binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.btnLimpiarProveedor}"
											action="#{compras$ABMFacturaContrato$ABMFacturaContrato.btnLimpiarProveedor_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label for="tfCodigoProveedor" id="lblCodigoProveedor" styleClass="label" text="Código de Proveedor" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.tfCodigoProveedor}" columns="20" id="tfCodigoProveedor"
											styleClass="textField" />
									</td>
								</tr>
								<!--<tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label for="tfMontoTotal" id="lblMonto" styleClass="label" text="Monto Total"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.tfMontoTotal}" columns="15"
                                            id="tfMontoTotal" maxLength="10" styleClass="textField" style="text-align:right; padding-right:6px;"/>
                                        </td>
                                    </tr>-->
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label for="ddTipoFactura" id="lblTipoFactura" styleClass="label" text="Tipo de Factura" />
									</td>
									<td>
										<ui:dropDown binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.ddTipoFactura}" id="ddTipoFactura"
											items="#{compras$ABMFacturaContrato$ABMFacturaContrato.ddTipoFacturaDefaultOptions.options}" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td colspan="4"></td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.table1}" id="table1" width="239">
											<script>
												<![CDATA[
												/* ----- Functions for Table Preferences Panel ----- */
												/*
												 * Toggle the table preferences panel open or closed
												 */
												function togglePreferencesPanel() {
													var table = document
															.getElementById("form1:table1");
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
															.getElementById("form1:table1");
													return table
															.filterMenuChanged();
												}
												/*
												 * Toggle the custom filter panel (if any) open or closed.
												 */
												function toggleFilterPanel() {
													var table = document
															.getElementById("form1:table1");
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
															.getElementById("form1:table1");
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
															.getElementById("form1:table1");
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
															.getElementById("form1:table1");
													var disabled = (table
															.getAllSelectedRowsCount() > 0) ? false
															: true;
													// Set disabled state for top actions
													document
															.getElementById(
																	"form1:table1:tableActionsTop:deleteTop")
															.setDisabled(
																	disabled);
													// Set disabled state for bottom actions
													document
															.getElementById(
																	"form1:table1:tableActionsBottom:deleteBottom")
															.setDisabled(
																	disabled);
												}
												]]>
											</script>
											<ui:tableRowGroup binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.tableRowGroup1}" id="tableRowGroup1"
												sourceData="#{compras$ABMFacturaContrato$ABMFacturaContrato.ldpLineaFactura}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.tableColumn1}" id="tableColumn1"
													valign="middle" width="10">
													<ui:radioButton binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.radioButton1}" id="radioButton1" label=""
														name="buttonGroup" selected="#{compras$ABMFacturaContrato$ABMFacturaContrato.RBSelected}"
														selectedValue="#{compras$ABMFacturaContrato$ABMFacturaContrato.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.tcBienProvisto}" headerText="Bien" id="tcBienProvisto"
													sort="Bien" width="40">
													<ui:staticText binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.stBienProvisto}" id="stBienProvisto"
														text="#{currentRow.value['bien']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.tcPrecio}" headerText="Precio" id="tcPrecio" width="40">
													<ui:staticText binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.stPrecio}" id="stPrecio"
														text="#{currentRow.value['montoUnitario']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.tcCantidad}" headerText="Cantidad" id="tcCantidad"
													sort="cantidad">
													<ui:textField binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.tfCantidad}" maxLength="10" disabled="true"
														id="tfCantidad" onBlur="calcular(this)" styleClass="textFieldDisabled" text="#{currentRow.value['cantidad']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.tcTotal}" headerText="Monto Total" id="tcTotal"
													sort="total">
													<ui:textField binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.tfTotal}" id="tfTotal" disabled="true" maxLength="40"
														style="text-align:right; padding-right:6px;" styleClass="textFieldDisabled" text="#{currentRow.value['total']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.tcCuentaAsociada}" headerText="Cuenta Asociada"
													id="tcCuentaAsociada" sort="cuentaAsociada">
													<ui:staticText binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.stCuenta}" id="stCuenta"
														text="#{currentRow.value['cuenta'].nombre}" />
												</ui:tableColumn>
												<!--<ui:tableColumn binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.tableColumn5}"
                                                        headerText="Cuenta Asociada" id="tableColumn5" sort="Cuenta">
                                                        <ui:textField binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.tfCuentaRfr}" id="tfCuentaRfr"
                                                            style="text-align:right; padding-right:6px;" styleClass="textFieldDisabled"  text="#{currentRow.value['cuenta']}"/>
                                                        <ui:button binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.btnSeleccionarCuentaRfr}" visible="true"
                                                                   action="#{compras$ABMFacturaContrato$ABMFacturaContrato.btnSeleccionarCuentaRfr_action}"
                                                                   id="btnSeleccionarCuentaRfr" escape="false" mini="true" styleClass="buttonSeleccionar"
                                                                   text="&amp;nbsp;" toolTip="Seleccionar"/>
                                                        <ui:button binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.btnLimpiarCuenta}" id="btnLimpiarCuenta"
                                                                   action="#{compras$ABMFacturaContrato$ABMFacturaContrato.btnLimpiarCuenta_action}" escape="false"
                                                                   mini="true" styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar"/>
                                                    </ui:tableColumn>

                                                    <ui:tableColumn binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.tableColumn3}"
                                                        headerText="Fecha de Recepción" id="tableColumn3" sort="cantidad">
                                                        <ui:staticText binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.staticText2}"
                                                            id="staticText2" text="#{currentRow.value['fechaRecepcion']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.tableColumn4}"
                                                        headerText="Estado" id="tableColumn4" sort="estado">
                                                        <ui:staticText binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.staticText3}"
                                                            id="staticText3" text="#{currentRow.value['estado']}"/>
                                                    </ui:tableColumn>
                                                    -->
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.groupPanel1}" id="groupPanel1">
													<!--<ui:button action="#{compras$ABMFacturaContrato$ABMFacturaContrato.btnAgregarOrden_action}"
                                                            binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.btnAgregarOrden}"
                                                            id="btnAgregarOrden" styleClass="button" text="Agregar Orden de Compra"/>-->
													<ui:button action="#{compras$ABMFacturaContrato$ABMFacturaContrato.btnAgregarLinea_action}"
														binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.btnAgregarLinea}" id="btnAgregarLinea" styleClass="button"
														text="Agregar Línea Factura" />
													<ui:staticText binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.stSeparador1}" escape="false" id="stSeparador1"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{compras$ABMFacturaContrato$ABMFacturaContrato.btnQuitar_action}"
														binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.btnQuitar}" id="btnQuitar" styleClass="button" text="Quitar" />
													<ui:staticText binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.stSeparador2}" escape="false" id="stSeparador2"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{compras$ABMFacturaContrato$ABMFacturaContrato.btnQuitarTodos_action}"
														binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.btnQuitarTodos}" id="btnQuitarTodos" styleClass="button"
														text="Quitar todos" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="right" colspan="4">
										<ui:label binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.lblTotalF}" id="lblTotalF" styleClass="label" text="Total:" />
										<ui:staticText binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.stTotal}"
											converter="#{compras$ABMFacturaContrato$ABMFacturaContrato.numberConverter1}" id="stTotal" styleClass="staticText" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right">
										<ui:label binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
									</td>
									<td>
										<ui:textArea binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.tablaLogs}" id="tbLogsAuditoria" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.messageGroup}" id="messageGroup" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{compras$ABMFacturaContrato$ABMFacturaContrato.btnGuardar_action}"
											binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{compras$ABMFacturaContrato$ABMFacturaContrato.btnCancelar_action}"
											binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMFacturaContrato$ABMFacturaContrato.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMFacturaContrato$ABMFacturaContrato.idSubSesion}" />
					<ui:script binding="#{compras$ABMFacturaContrato$ABMFacturaContrato.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
