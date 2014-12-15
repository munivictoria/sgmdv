<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.page1}" id="page1">
			<ui:html binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.html1}" id="html1">
			<ui:head binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.head1}" id="head1">
				<ui:link binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					//  <![CDATA[
					function cambioTipoMovimiento() {
						ddTipoMovimiento = document
								.getElementById("form1:ddTipoMovimiento");
						lblStockOrigen = document
								.getElementById("form1:lblStockOrigen");
						tfStockOrigen = document
								.getElementById("form1:tfStockOrigen");
						btnSeleccionarStockOrigen = document
								.getElementById("form1:btnSeleccionarStockOrigen");
						lblStockDestino = document
								.getElementById("form1:lblStockDestino");
						tfStockDestino = document
								.getElementById("form1:tfStockDestino");
						btnSeleccionarStockDestino = document
								.getElementById("form1:btnSeleccionarStockDestino");

						btnSeleccionarStockOrigen.disabled = true;
						lblStockOrigen.disabled = true;
						tfStockOrigen.disabled = true;

						btnSeleccionarStockDestino.disabled = true;
						lblStockDestino.disabled = true;
						tfStockDestino.disabled = true;

						/*  btnSeleccionarStockOrigen.style.display="inline";
						   lblStockOrigen.style.display="inline";
						   tfStockOrigen.style.display = "inline";
						   
						   btnSeleccionarStockDestino.style.display="inline";
						   lblStockDestino.style.display="inline";
						   tfStockDestino.style.display = "inline";
						 */

						if (ddTipoMovimiento.value == "MOVIMIENTO") {
							btnSeleccionarStockOrigen.disabled = false;
							lblStockOrigen.disabled = false;
							tfStockOrigen.disabled = false;
							btnSeleccionarStockDestino.disabled = false;
							lblStockDestino.disabled = false;
							tfStockDestino.disabled = false;

							/* btnSeleccionarStockOrigen.style.display="none";
							   lblStockOrigen.style.display="none";
							   tfStockOrigen.style.display = "none";
							   btnSeleccionarStockDestino.style.display="none";
							   lblStockDestino.style.display="none";
							   tfStockDestino.style.display = "none";*/
						} else if (ddTipoMovimiento.value == "INGRESO") {
							/* btnSeleccionarStockOrigen.style.display="none";
							 lblStockOrigen.style.display="none";
							 tfStockOrigen.style.display = "none";*/
							btnSeleccionarStockDestino.disabled = false;
							lblStockDestino.disabled = false;
							tfStockDestino.disabled = false;
							tfStockOrigen.value = "";

						} else if (ddTipoMovimiento.value == "EGRESO") {
							/*  btnSeleccionarStockDestino.style.display="none";
							  lblStockDestino.style.display="none";
							  tfStockDestino.style.display = "none";*/
							btnSeleccionarStockOrigen.disabled = false;
							lblStockOrigen.disabled = false;
							tfStockOrigen.disabled = false;
							tfStockDestino.value = "";
						}

					}
					//  ]]>
				</script>
			</ui:head>
			<ui:body binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.body1}" focus="form1:taMotivo" id="body1"
				onLoad="cambioTipoMovimiento();parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.stTitulo}" id="stTitulo"
									styleClass="tituloABM" text="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.lblFecha}" for="tfFecha" id="lblFecha"
											styleClass="label" text="Fecha" />
									</td>
									<td nowrap="nowrap" align="left">
										<ui:textField binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.tfFecha}" columns="10" disabled="true"
											id="tfFecha" styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.lblUsuario}" for="tfUsuario" id="lblUsuario"
											styleClass="label" text="Usuario" />
									</td>
									<td nowrap="nowrap" align="left">
										<ui:textField binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.tfUsuario}" columns="40" disabled="true"
											id="tfUsuario" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.lblTipoMovimiento}" for="ddTipoMovimiento"
											id="lblTipoMovimiento" styleClass="label" text="Tipo" />
									</td>
									<td>
										<ui:dropDown binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.ddTipoMovimiento}" id="ddTipoMovimiento"
											items="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.ddTipoMovimientoDefaultOptions.options}"
											styleClass="textField">
											<a4j:support event="onChange" reRender="form1:ddDepositoDestino,form1:table1"
												actionListener="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.valueChangeEventTipo(event)}" />
										</ui:dropDown>
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.lblMotivo}" id="lblMotivo" styleClass="label"
											text="Motivo" />
									</td>
									<td align="left" nowrap="nowrap">
										<ui:textArea binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.taMotivo}" columns="50" id="taMotivo"
											styleClass="textField" rows="5" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.lblDepositoOrigen}" for="ddDepositoOrigen"
											id="lblDepositoOrigen" styleClass="label" text="Depósito" />
									</td>
									<td>
										<ui:dropDown binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.ddDepositoOrigen}" id="ddDepositoOrigen"
											items="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.ddDepositoOrigenDefaultOptions.options}"
											styleClass="textField">
											<a4j:support event="onChange" reRender="form1:table1"
												actionListener="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.valueChangeEventDepOrigen(event)}" />
										</ui:dropDown>
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.lblDepositoDestino}" for="ddDepositoDestino"
											id="lblDepositoDestino" styleClass="label" text="Depósito Destino" />
									</td>
									<td>
										<ui:dropDown binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.ddDepositoDestino}" id="ddDepositoDestino"
											items="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.ddDepositoDestinoDefaultOptions.options}"
											styleClass="textField">
											<a4j:support event="onChange" reRender="form1:table1"
												actionListener="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.valueChangeEventDepDestino(event)}" />
										</ui:dropDown>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:label binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.lblLineasMovMerc}" id="lblLineasMovMerc"
											styleClass="label2" text="Líneas de Movimiento de Mercadería" />
									</td>
								</tr>
								<tr>
									<td colspan="4" style="height: 51px">
										<ui:table augmentTitle="false" binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.table1}" id="table1"
											width="240">
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
											<ui:tableRowGroup binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.tableRowGroup1}" id="tableRowGroup1"
												sourceData="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.ldpLineasMovMerc}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.tableColumn1}"
													id="tableColumn1" valign="middle" width="10">
													<ui:radioButton binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.radioButton1}" id="radioButton1"
														label="" name="buttonGroup" selected="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.RBSelected}"
														selectedValue="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.tcBien}" headerText="Bien" id="tcBien"
													sort="bien" width="10">
													<ui:textArea binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.taBien}" id="taBien"
														text="#{currentRow.value['bien']}" columns="10" disabled="true" styleClass="textFieldDisabled" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.tcStock}" headerText="Stock"
													id="tcStock" width="40">
													<ui:staticText binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.stStock}" id="stStock"
														text="#{currentRow.value['stock']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.tcStockDestino}"
													headerText="Stock Destino" id="tcStockDestino" width="40">
													<ui:staticText binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.stStockDestino}" id="stStockDestino"
														text="#{currentRow.value['stockDestino']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.tcCantidad}" headerText="Cantidad"
													id="tcCantidad" sort="cantidad" width="3">
													<ui:textField binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.tfCantidad}" id="tfCantidad"
														text="#{currentRow.value['cantidad']}" columns="3" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.tcCantidadEntregasPrev}"
													headerText="Cantidad Entregas Previas" id="tcCantidadEntregasPrev" width="10">
													<ui:staticText binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.stCantidadEntregasPrev}"
														id="stCantidadEntregasPrev" text="#{currentRow.value['cantidadEntregasPrevias']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.tcOrdenOSolicitud}"
													headerText="Orden Compra/Solicitud Suministro" id="tcOrdenOSolicitud">
													<ui:staticText binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.stOrdenOSolicitud}"
														id="stOrdenOSolicitud" text="#{currentRow.value['ordenOSolicitud']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.groupPanel1}" id="groupPanel1">
													<ui:button action="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.btnSeleccionarOrdenCompra_action}"
														binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.btnSeleccionarOrdenCompra}"
														id="btnSeleccionarOrdenCompra" styleClass="button" text="Orden de Compra" />
													<ui:button action="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.btnSeleccionarSolSum_action}"
														binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.btnSeleccionarSolSum}" id="btnSeleccionarSolSum"
														styleClass="button" text="Solicitud de Suministro" />
													<ui:button action="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.btnQuitarLinea_action}"
														binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.btnQuitarLinea}" id="btnQuitarLinea"
														styleClass="button" text="Quitar" />
													<ui:staticText binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.stSeparador}" escape="false"
														id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.btnQuitarTodosLinea_action}"
														binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.btnQuitarTodosLinea}" id="btnQuitarTodosLinea"
														styleClass="button" text="Quitar todos" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								</tr>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right">
										<ui:label binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
									</td>
									<td>
										<ui:textArea binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.tablaLogs}" id="tbLogsAuditoria" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.messageGroup}" id="messageGroup"
											styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.btnGuardar_action}"
											binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.btnGuardar}" id="btnGuardar" styleClass="button"
											text="Guardar" />
										<ui:staticText binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.stSeparador}" escape="false"
											id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.btnCancelar_action}"
											binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.btnCancelar}" id="btnCancelar" styleClass="button"
											/>
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.hidIdPagina}" id="hidIdPagina"
						text="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.idPagina}" />
					<ui:hiddenField binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.idSubSesion}" />
					<ui:script binding="#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
