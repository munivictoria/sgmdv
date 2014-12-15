<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.page1}" id="page1">
			<ui:html binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.html1}" id="html1">
			<ui:head binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.head1}" id="head1">
				<ui:link binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.link1}" id="link1" url="/resources/stylesheet.css" />
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
			<ui:body binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.body1}" focus="form1:tfFecha" id="body1"
				onLoad="parent.footer.location.reload(); Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.label5}" for="tfFecha" id="label5" style="" styleClass="label"
											text="Fecha Emisión" />
									</td>
									<td>
										<ui:textField binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.tfFecha}" id="tfFecha" styleClass="textField"
											columns="10" />
										<!--<ui:staticText binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.staticText5}" escape="false"
                                                id="staticText5" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.lblNumeroFactura}" for="tfNumeroFactura" id="lblNumeroFactura"
											styleClass="label" text="Nº de Factura" />
									</td>
									<td>
										<ui:textField binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.tfNumeroFactura}" columns="20" id="tfNumeroFactura"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label for="tfDigestoMunicipal" id="lblDigestoMunicipal" styleClass="label" text="Digesto Municipal" />
									</td>
									<td>
										<ui:textField binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.tfDigestoMunicipal}" columns="40" disabled="true"
											id="tfDigestoMunicipal" styleClass="textField" />
										<ui:button action="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.btnSeleccionarDigestoMunicipal_action}"
											binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.btnSeleccionarDigestoMunicipal}" escape="false"
											id="btnSeleccionarDigestoMunicipal" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton title="Limpiar" id="btnLimpiarDigestoMunicipal" reRender="form1:tfDigestoMunicipal" binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.btnLimpiarDigestoMunicipal}"
											action="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.btnLimpiarDigestoMunicipal_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.label4}" for="tfProveedor" id="label4" styleClass="label"
											text="Proveedor" />
									</td>
									<td>
										<ui:textField binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.tfProveedor}" columns="40" disabled="true" id="tfProveedor"
											styleClass="textField" />
										<ui:button action="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.btnSeleccionarProveedor_action}"
											binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.btnSeleccionarProveedor}" escape="false" id="btnSeleccionarProveedor"
											mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton title="Limpiar" id="btnLimpiarProveedor" reRender="form1:tfProveedor" binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.btnLimpiarProveedor}"
											action="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.btnLimpiarProveedor_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label for="tfCodigoProveedor" id="lblCodigoProveedor" styleClass="label" text="Código de Proveedor" />
									</td>
									<td>
										<ui:textField binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.tfCodigoProveedor}" columns="20" id="tfCodigoProveedor"
											styleClass="textField" />
									</td>
								</tr>
								<!--<tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label for="tfMontoTotal" id="lblMonto" styleClass="label" text="Monto Total"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.tfMontoTotal}" columns="15"
                                                id="tfMontoTotal" maxLength="10" style="text-align:right; padding-right:6px;" styleClass="textField"/>
                                        </td>
                                    </tr>-->
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label for="ddTipoFactura" id="lblTipoFactura" styleClass="label" text="Tipo de Factura" />
									</td>
									<td>
										<ui:dropDown binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.ddTipoFactura}" id="ddTipoFactura"
											items="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.ddTipoFacturaDefaultOptions.options}" styleClass="textField" />
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
										<ui:label binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.label1}" id="label1" styleClass="label2"
											text="Líneas de Factura" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:table augmentTitle="false" binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.table1}" id="table1" width="239">
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
											<ui:tableRowGroup binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.tableRowGroup1}" id="tableRowGroup1"
												sourceData="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.ldpLineasFactura}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.tableColumn5}" id="tableColumn5"
													valign="middle" width="10">
													<ui:radioButton binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.radioButton1}" id="radioButton1" label=""
														name="buttonGroup" selected="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.RBSelected}"
														selectedValue="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.tableColumn2}" headerText="Bien" id="tableColumn2"
													sort="bien" width="40">
													<ui:staticText binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.staticText1}" id="staticText1"
														text="#{currentRow.value['bien']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.tableColumn1}" headerText="Precio" id="tableColumn1"
													width="40">
													<ui:staticText binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.stPrecio}" id="stPrecio"
														text="#{currentRow.value['montoUnitario']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.tableColumn3}" headerText="Cantidad" id="tableColumn3"
													sort="cantidad">
													<ui:textField binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.tfCantidad}" id="tfCantidad" onBlur="calcular(this)"
														styleClass="textField" text="#{currentRow.value['cantidad']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.tableColumn4}" headerText="Monto Total"
													id="tableColumn4" sort="total">
													<ui:textField binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.tfTotal}" id="tfTotal"
														style="text-align:right; padding-right:6px;" styleClass="textFieldDisabled" text="#{currentRow.value['total']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.tableColumn6}" headerText="Cuenta Asociada"
													id="tableColumn6" sort="cuentaAsociada">
													<ui:staticText binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.stCuenta}" id="stCuenta"
														text="#{currentRow.value['cuenta'].nombre}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.groupPanel1}" id="groupPanel1">
													<!--<ui:button action="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.btnAgregarOrden_action}"
                                                            binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.btnAgregarOrden}"
                                                            id="btnAgregarOrden" styleClass="button" text="Agregar Orden de Compra"/>-->
													<ui:button action="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.btnAgregarLinea_action}"
														binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.btnAgregarLinea}" id="btnAgregarLinea" styleClass="button"
														text="Agregar Línea Factura" />
													<ui:staticText binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.staticText4}" escape="false" id="staticText4"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.btnQuitar_action}"
														binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.btnQuitar}" id="btnQuitar" styleClass="button" text="Quitar" />
													<ui:staticText binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.staticText6}" escape="false" id="staticText6"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.btnQuitarTodos_action}"
														binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.btnQuitarTodos}" id="btnQuitarTodos" styleClass="button"
														text="Quitar todos" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="right" colspan="2">
										<ui:label binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.lblTotalF}" id="lblTotalF" styleClass="label" text="Total:" />
										<ui:staticText binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.stTotal}"
											converter="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.numberConverter1}" id="stTotal" styleClass="staticText" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right">
										<ui:label binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
									</td>
									<td>
										<ui:textArea binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.tablaLogs}" id="tbLogsAuditoria" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:messageGroup binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.messageGroup}" id="messageGroup" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="2" nowrap="nowrap">
										<ui:button action="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.btnGuardar_action}"
											binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.btnCancelar_action}"
											binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.idSubSesion}" />
					<ui:script binding="#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
