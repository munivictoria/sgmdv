<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.page1}" id="page1">
			<ui:html binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.html1}" id="html1">
			<ui:head binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.head1}" id="head1" title="Agregar Línea de Factura">
				<ui:link binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.body1}" focus="form1:btnSeleccionarBien" id="body1"
				onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="verde">
								<caption>
									<ui:staticText binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.head1.title}" />
								</caption>
								<tr>
									<td></td>
								</tr>
								<tbody>
									<!-- <tr>
                                            <td align="right" nowrap="true">
                                                <ui:label binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.lblProveedor}" for="tfProveedor" id="lblProveedor"
                                                          styleClass="label" text="Proveedor"/>
                                            </td>
                                            <td>
                                                <ui:textField binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.tfProveedor}" columns="40" id="tfProveedor"
                                                              styleClass="textField" disabled="true"/>
                                            </td>
                                        </tr> -->
									<tr>
										<td align="right" nowrap="true">
											<ui:label binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.lblBien}" id="lblBien" styleClass="label"
												text="Bien" />
										</td>
										<td>
											<ui:textField binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.tfBien}" columns="40" disabled="true"
												id="tfBien" styleClass="textField" />
											<ui:button action="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.btnSeleccionarBien_action}"
												binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.btnSeleccionarBien}" escape="false" id="btnSeleccionarBien"
												mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
											<a4j:commandButton id="btnLimpiarBien" reRender="form1:tfBien"
												action="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.btnLimpiarBien_action}" styleClass="buttonLimpiarAjax" />
										</td>
									</tr>
									<tr>
										<td align="right" nowrap="nowrap">
											<ui:label binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.lblCuenta}" id="lblCuenta" styleClass="label"
												text="Cuenta" />
										</td>
										<td>
											<ui:textField binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.tfCuenta}" columns="40" disabled="true"
												id="tfCuenta" styleClass="textField" />
											<ui:button action="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.btnSeleccionarCuenta_action}"
												id="btnSeleccionarCuenta" binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.btnSeleccionarCuenta}"
												escape="false" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
											<a4j:commandButton id="btnLimpiarCuenta" reRender="form1:tfCuenta"
												action="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.btnLimpiarCuenta_action}" styleClass="buttonLimpiarAjax" />
										</td>
									</tr>
									<tr>
										<td align="right" nowrap="nowrap">
											<ui:label binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.lblMonto}" id="lblMonto" styleClass="label"
												text="Precio Unitario" />
										</td>
										<td>
											<ui:textField binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.tfMonto}" id="tfMonto" styleClass="textField"
												onKeyPress="return ValidarFloat(event,this)" columns="15" />

											<ui:label binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.lblCantidad}" id="lblCantidad" styleClass="label"
												text="Cantidad" />

											<ui:textField binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.tfCantidad}" id="tfCantidad"
												styleClass="textField" onKeyPress="return ValidarNum(event,this)" columns="10" />
										</td>
									</tr>
								</tbody>
								<tr>
									<td></td>
								</tr>
								<tfoot>
									<tr>
										<td align="right" colspan="2">
											<ui:button action="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.btnAgregarLinea_action}"
												binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.btnAgregarLinea}" id="btnAgregarLinea" styleClass="button"
												text="Agregar Línea" />
											<ui:button action="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.btnAceptar_action}" id="btnAceptar"
												styleClass="button" text="Aceptar" binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.btnAceptar}" />
											<ui:staticText binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.staticText9}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.btnCancelar_action}"
												binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
						</div>
						<div>
							<ui:messageGroup binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.messageGroup}" id="messageGroup"
								showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
						</div>
						<table>
							<tr>
								<td>
									<ui:table binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.table1}" id="table1">
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
														rowGroupId, selected);
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
														.setDisabled(disabled);
												// Set disabled state for bottom actions
												document
														.getElementById(
																"form1:table1:tableActionsBottom:deleteBottom")
														.setDisabled(disabled);
											}
											]]>
										</script>
										<ui:tableRowGroup binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.tableRowGroup1}"
											emptyDataMsg="Ningún registro encontrado." id="tableRowGroup1"
											selected="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.currentRowSelected}"
											sourceData="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.ldpLineaFactura}" sourceVar="currentRow">
											<ui:tableColumn align="center" binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.tableColumn1}"
												id="tableColumn1" valign="middle" width="10">
												<ui:radioButton binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.radioButton1}" id="radioButton1" label=""
													name="buttonGroup" selected="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.RBSelected}"
													selectedValue="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.currentRow}" />
											</ui:tableColumn>
											<ui:tableColumn binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.tableColumn2}" headerText="Bien"
												id="tableColumn2" sort="bien">
												<ui:staticText binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.staticText1}" id="staticText1"
													text="#{currentRow.value['nombre']}" />
											</ui:tableColumn>
											<!--<ui:tableColumn binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.tableColumn6}" headerText="Precio" id="tableColumn6" sort="precio">
                                                    <ui:staticText binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.staticText10}" id="staticText10" text="#{currentRow.value['precio']}"/>
                                                </ui:tableColumn>-->
											<ui:tableColumn binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.tableColumn3}" headerText="Cantidad"
												id="tableColumn3" sort="cantidad">
												<ui:staticText binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.staticText3}" id="staticText3"
													text="#{currentRow.value['cantidad']}" />
											</ui:tableColumn>
											<ui:tableColumn binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.tableColumn4}" headerText="Total"
												id="tableColumn4" sort="total">
												<ui:staticText binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.staticText4}" id="staticText4"
													text="#{currentRow.value['total']}" />
											</ui:tableColumn>
											<ui:tableColumn binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.tableColumn5}" headerText="Cuenta Asociada"
												id="tableColumn5" sort="cuenta">
												<ui:staticText binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.staticText7}" id="staticText7"
													text="#{currentRow.value['cuenta']}" />
											</ui:tableColumn>
										</ui:tableRowGroup>
										<f:facet name="actionsTop">
											<ui:panelGroup binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.groupPanel1}" id="groupPanel1" style="">
													<a4j:commandButton action="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.btnQuitar_action}" binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.btnQuitar}"
														id="btnQuitar" value="Quitar"  styleClass="btnAjax" reRender="table1" onmousedown="reemplazarClickConConfirmacion(this, '');"/>
													<ui:staticText binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.staticText6}" escape="false" id="staticText6"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<a4j:commandButton action="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.btnQuitarTodos_action}"
														binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.btnQuitarTodos}" id="btnQuitarTodos" value="Quitar todos" styleClass="btnAjax" reRender="table1" 
														onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea quitar todos los elementos de esta lista?');"/>
											
											<!-- 	<ui:button action="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.btnQuitar_action}"
													binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.btnQuitar}" id="btnQuitar" styleClass="button"
													text="Quitar" />
												<ui:staticText binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.staticText6}" escape="false" id="staticText6"
													text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
												<ui:button action="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.btnQuitarTodos_action}"
													binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.btnQuitarTodos}" id="btnQuitarTodos" styleClass="button"
													text="Quitar Todos" /> -->
											</ui:panelGroup>
										</f:facet>
									</ui:table>
								</td>
							</tr>
						</table>
					</div>
					<ui:hiddenField binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.idSubSesion}" />
					<ui:script binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{compras$ABMFacturaProveedor$AgregarLineaFacturaProveedor.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
