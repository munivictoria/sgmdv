<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.page1}" id="page1">
			<ui:html binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.html1}" id="html1">
			<ui:head binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.head1}" id="head1">
				<ui:link binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.link1}" id="link1" url="/resources/stylesheet.css" />
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
			<ui:body binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.body1}" focus="form1:tfFecha" id="body1"
				onLoad="parent.footer.location.reload(); Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{compras$ABMFacturaServicio$ABMFacturaServicio.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.lblFecha}" for="tfFecha" id="lblFecha" style=""
											styleClass="label" text="Fecha Emisión" />
									</td>
									<td>
										<ui:textField binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.tfFecha}" id="tfFecha" styleClass="textField"
											columns="10" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.lblNumeroFactura}" for="tfNumeroFactura" id="lblNumeroFactura"
											styleClass="label" text="Nº de Factura" />
									</td>
									<td>
										<ui:textField binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.tfNumeroFactura}" columns="20" id="tfNumeroFactura"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label for="tfBien" id="lblContrato" styleClass="label" text="Bien" />
									</td>
									<td>
										<ui:textField binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.tfBien}" columns="40" disabled="true" id="tfBien"
											styleClass="textField" />
										<ui:button action="#{compras$ABMFacturaServicio$ABMFacturaServicio.btnSeleccionarBien_action}"
											binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.btnSeleccionarBien}" escape="false" id="btnSeleccionarBien" mini="true"
											styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton title="Limpiar" id="btnLimpiarBien" reRender="form1:tfBien" binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.btnLimpiarBien}"
											action="#{compras$ABMFacturaServicio$ABMFacturaServicio.btnLimpiarBien_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.lblProveedor}" for="tfProveedor" id="lblProveedor"
											styleClass="label" text="Proveedor" />
									</td>
									<td>
										<ui:textField binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.tfProveedor}" columns="40" disabled="true" id="tfProveedor"
											styleClass="textField" />
										<ui:button action="#{compras$ABMFacturaServicio$ABMFacturaServicio.btnSeleccionarProveedor_action}"
											binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.btnSeleccionarProveedor}" escape="false" id="btnSeleccionarProveedor"
											mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton title="Limpiar" id="btnLimpiarProveedor" reRender="form1:tfProveedor" binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.btnLimpiarProveedor}"
											action="#{compras$ABMFacturaServicio$ABMFacturaServicio.btnLimpiarProveedor_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label for="tfCodigoProveedor" id="lblCodigoProveedor" styleClass="label" text="Código de Proveedor" />
									</td>
									<td>
										<ui:textField binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.tfCodigoProveedor}" columns="20" id="tfCodigoProveedor"
											styleClass="textField" />
									</td>
								</tr>
								<!--<tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label for="tfMontoTotal" id="lblMonto" styleClass="label" text="Monto Total"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.tfMontoTotal}" columns="15"
                                                id="tfMontoTotal" maxLength="10" style="text-align:right; padding-right:6px;" styleClass="textField"/>
                                        </td>
                                    </tr>-->
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label for="ddTipoFactura" id="lblTipoFactura" styleClass="label" text="Tipo de Factura" />
									</td>
									<td>
										<ui:dropDown binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.ddTipoFactura}" id="ddTipoFactura"
											items="#{compras$ABMFacturaServicio$ABMFacturaServicio.ddTipoFacturaDefaultOptions.options}" styleClass="textField" />
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
										<ui:label binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.lblLineasFactura}" id="lblLineasFactura" styleClass="label"
											text="Líneas de Factura" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:table augmentTitle="false" binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.table1}" id="table1" width="239">
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
											<ui:tableRowGroup binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.tableRowGroup1}" id="tableRowGroup1"
												sourceData="#{compras$ABMFacturaServicio$ABMFacturaServicio.ldpLineasFactura}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.tableColumn1}" id="tableColumn1"
													valign="middle" width="10">
													<ui:radioButton binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.radioButton1}" id="radioButton1" label=""
														name="buttonGroup" selected="#{compras$ABMFacturaServicio$ABMFacturaServicio.RBSelected}"
														selectedValue="#{compras$ABMFacturaServicio$ABMFacturaServicio.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.tcBienProvisto}" headerText="Bien" id="tcBienProvisto"
													sort="bien" width="40">
													<ui:staticText binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.stBienProvisto}" id="stBienProvisto"
														text="#{currentRow.value['bien']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.tcPrecio}" headerText="Precio" id="tcPrecio" width="40">
													<ui:staticText binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.stPrecio}" id="stPrecio"
														text="#{currentRow.value['montoUnitario']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.tcCantidad}" headerText="Cantidad" id="tcCantidad"
													sort="cantidad">
													<ui:textField binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.tfCantidad}" id="tfCantidad" disabled="true"
														onBlur="calcular(this)" styleClass="textField" text="#{currentRow.value['cantidad']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.tcTotal}" headerText="Total" id="tcTotal" sort="total">
													<ui:textField binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.tfTotal}" id="tfTotal" disabled="true"
														style="text-align:right; padding-right:6px;" styleClass="textFieldDisabled" text="#{currentRow.value['total']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.tcCuentaAsociada}" headerText="Cuenta Asociada"
													id="tcCuentaAsociada" sort="cuentaAsociada">
													<ui:staticText binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.stCuenta}" id="stCuenta"
														text="#{currentRow.value['cuenta'].nombre}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.groupPanel1}" id="groupPanel1">
													<!--<ui:button action="#{compras$ABMFacturaServicio$ABMFacturaServicio.btnAgregarOrden_action}"
                                                            binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.btnAgregarOrden}"
                                                            id="btnAgregarOrden" styleClass="button" text="Agregar Orden de Compra"/>-->
													<ui:button action="#{compras$ABMFacturaServicio$ABMFacturaServicio.btnAgregarLinea_action}"
														binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.btnAgregarLinea}" id="btnAgregarLinea" styleClass="button"
														text="Agregar Línea Factura" />
													<ui:staticText binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.stSeparador1}" escape="false" id="stSeparador1"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{compras$ABMFacturaServicio$ABMFacturaServicio.btnQuitar_action}"
														binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.btnQuitar}" id="btnQuitar" styleClass="button" text="Quitar" />
													<ui:staticText binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.stSeparador2}" escape="false" id="stSeparador2"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{compras$ABMFacturaServicio$ABMFacturaServicio.btnQuitarTodos_action}"
														binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.btnQuitarTodos}" id="btnQuitarTodos" styleClass="button"
														text="Quitar todos" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="right" colspan="2">
										<ui:label binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.lblTotalF}" id="lblTotalF" styleClass="label" text="Total:" />
										<ui:staticText binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.stTotal}"
											converter="#{compras$ABMFacturaServicio$ABMFacturaServicio.numberConverter1}" id="stTotal" styleClass="staticText" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right">
										<ui:label binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
									</td>
									<td>
										<ui:textArea binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.tablaLogs}" id="tbLogsAuditoria" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:messageGroup binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.messageGroup}" id="messageGroup" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="2" nowrap="nowrap">
										<ui:button action="#{compras$ABMFacturaServicio$ABMFacturaServicio.btnGuardar_action}"
											binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{compras$ABMFacturaServicio$ABMFacturaServicio.btnCancelar_action}"
											binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMFacturaServicio$ABMFacturaServicio.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMFacturaServicio$ABMFacturaServicio.idSubSesion}" />
					<ui:script binding="#{compras$ABMFacturaServicio$ABMFacturaServicio.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
