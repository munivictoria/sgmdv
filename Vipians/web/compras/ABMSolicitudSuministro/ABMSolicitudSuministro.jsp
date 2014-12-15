<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.page1}" id="page1">
			<ui:html binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.html1}" id="html1">
			<ui:head binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.head1}" id="head1">
				<ui:link binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "compras$ABMSolicitudSuministro$ABMSolicitudSuministro";

					function cargarComportamientoJQuery() {
						autoCompletarEnTextField("#form1:table1:tfBien", "bien", nombreBean, "setBienAutocompletar", "#form1:table1:btnAgregarBien");
					}

					function focusearTfBien() {
						$("#form1\\:table1\\:tfBien").focus();
					}

					function focusearUltimaFilaTabla() {
						var $stCantidad = $("#form1\\:table1 tr:last td:gt(1) :first");
						$stCantidad.focus();
						$stCantidad.select();
					}

					function focusearSiguiente(componente) {
						var idActual = componente.getAttribute("id");
						var idUltimo = $("#form1\\:table1 tr:last td:gt(1) :first").attr("id");

						// Si es el ultimo componente, focusea el textField de bien, sino el siguiente componente de la tabla...
						if(idActual === idUltimo) {
							focusearTfBien();
						} else {
							var $inputs = $("#form1\\:table1").find(":text:enabled");

							// Si el elemento no esta en la lista, el indice es = -1
							var actual = $inputs.index(componente);

							if(actual > -1 && (actual + 1) < $inputs.length) {
								var idSiguiente = "#" + $inputs.eq(actual + 1).attr("id").replace(/:/g, "\\:");
								$(idSiguiente).focus();
								$(idSiguiente).select();
							}
						}
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.body1}" focus="form1:ddArea" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.form1}" id="form1">
					<div class="formularioABM" style="left: -4px; top: 0px; position: absolute">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.lblArea}" for="ddArea" id="lblArea" styleClass="label"
											text="Área" />
									</td>
									<td nowrap="nowrap">
										<ui:dropDown binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.ddArea}" id="ddArea"
											items="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.ddAreaOptions.options}" styleClass="textField">
											<a4j:support event="onChange" reRender="form1:table1, form1:pgUrgente"
												actionListener="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.actionListenerDropArea(event)}"
												oncomplete="cargarComportamientoJQuery();" />
										</ui:dropDown>
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.lblUsuario}" id="lblUsuario" styleClass="label"
											text="Usuario" />
									</td>
									<td>
										<ui:textField binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.tfUsuario}" disabled="true" id="tfUsuario"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.lblFechaEmision}" for="tfFechaEmision"
											id="lblFechaEmision" styleClass="label" text="Fecha de Emisión" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.tfFechaEmision}" disabled="true"
											id="tfFechaEmision" styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.lblNumero}" for="tfNumero" id="lblNumero"
											styleClass="label" text="Número" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.tfNumero}" disabled="true" id="tfNumero"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.lblDescripcion}" id="lblDescripcion" styleClass="label"
											text="Descripción" />
									</td>
									<td>
										<ui:textArea binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.taDescripcion}" columns="40" id="taDescripcion"
											rows="5" styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:panelGroup id="pgUrgente" binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.pgUrgente}">
											<ui:label binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.lblUrgente}" for="cbUrgente" id="lblUrgente"
												styleClass="label" text="Urgente" />
											<ui:checkbox binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.cbUrgente}" id="cbUrgente" />
										</ui:panelGroup>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<hr />
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.table1}" id="table1" width="200">
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
											<ui:tableRowGroup binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.tableRowGroup1}" id="tableRowGroup1"
												sourceData="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.objectListDataProvider}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.tableColumn1}" id="tableColumn1"
													valign="middle" width="10">
													<ui:radioButton binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.radioButton1}" id="radioButton1" label=""
														name="buttonGroup" selected="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.RBSelected}"
														selectedValue="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.tcBien}" headerText="Bien" id="tcBien"
													sort="bien" width="40">
													<ui:staticText binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.stBien}" id="stBien"
														text="#{currentRow.value['bien']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.tcCantidad}" headerText="Cantidad"
													id="tcCantidad" sort="cantidad" width="40">
													<ui:textField binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.tfCantidad}" id="stCantidad"
														text="#{currentRow.value['cantidad']}" styleClass="textField" onKeyPress="return ValidarFloat(event,this);"
														onKeyUp="if(event.keyCode == 13) focusearSiguiente(this);" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.tcEstado}" headerText="Estado" id="tcEstado"
													sort="estado" width="40">
													<ui:textField binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.tfEstado}" id="stEstado"
														text="#{currentRow.value['estado']}" styleClass="textField" disabled="true" onKeyPress="return ValidarFloat(event,this)" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.tcCuentaRfr}" headerText="Cuenta"
													id="tcCuentaRfr" width="20" sort="cuenta">
													<ui:staticText binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.stCuentaRfr}" id="stCuentaRfr"
														text="#{currentRow.value['cuenta']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.groupPanel1}" id="groupPanel1">
													<ui:textField binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.tfBien}" columns="40" id="tfBien"
														styleClass="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.hayBien ? 'textFieldDisabled' : 'textField'}"
														disabled="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.hayBien}" />
													<a4j:commandButton action="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.btnAgregarBien_action}"
														binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.btnAgregarBien}" id="btnAgregarBien"
														styleClass="buttonAgregarAjax" reRender="table1" oncomplete="cargarComportamientoJQuery(); focusearUltimaFilaTabla();" />
													<a4j:commandButton id="btnLimpiarBien" reRender="tfBien" title="Limpiar"
														binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.btnLimpiarBien}"
														action="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.btnLimpiarBien_action}" styleClass="buttonLimpiarAjax"
														oncomplete="cargarComportamientoJQuery(); focusearTfBien();" />
													<ui:staticText binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.stSeparador2}" escape="false" id="stSeparador2"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.btnSeleccionarBien_action}"
														binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.btnSeleccionarBien}" id="btnSeleccionarBien"
														styleClass="button" text="Agregar Bien" />
													<ui:staticText binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.stSeparador3}" escape="false" id="stSeparador3"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<a4j:commandButton action="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.btnQuitar_action}"
														binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.btnQuitar}" id="btnQuitar" value="Quitar"
														styleClass="btnAjax" reRender="table1" onmousedown="reemplazarClickConConfirmacion(this, '');"
														oncomplete="cargarComportamientoJQuery();" />
													<ui:staticText binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.stSeparador}" escape="false" id="stSeparador"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<a4j:commandButton action="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.btnQuitarTodos_action}"
														binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.btnQuitarTodos}" id="btnQuitarTodos" value="Quitar todos"
														styleClass="btnAjax" reRender="table1"
														onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea quitar todos los elementos de esta lista?');"
														oncomplete="cargarComportamientoJQuery();" />
													<ui:staticText binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.stSeparador1}" escape="false" id="stSeparador1"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<a4j:commandButton action="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.btnSugerirCuenta_action}"
														binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.btnSugerirCuenta}" id="btnSugerirCuenta"
														value="Sugerir cuenta" styleClass="btnAjax" reRender="table1" oncomplete="cargarComportamientoJQuery();" />
													<a4j:commandButton action="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.btnCambiarCuenta_action}"
														binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.btnCambiarCuenta}" id="btnCambiarCuenta"
														value="Cambiar cuenta" styleClass="btnAjax" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<hr />
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:staticText binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.stPresupuestosSolSum}" id="stPresupuestosSolSum"
											styleClass="label2" text="Presupuestos" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.table2}" id="table2" width="479">
											<script>
												<![CDATA[
												/* ----- Functions for Table Preferences Panel ----- */
												/*
												 * Toggle the table preferences panel open or closed
												 */
												function togglePreferencesPanel() {
													var table = document.getElementById("form1:table2");
													table.toggleTblePreferencesPanel();
												}
												/* ----- Functions for Filter Panel ----- */
												/*
												 * Return true if the filter menu has actually changed,
												 * so the corresponding event should be allowed to continue.
												 */
												function filterMenuChanged() {
													var table = document.getElementById("form1:table2");
													return table.filterMenuChanged();
												}
												/*
												 * Toggle the custom filter panel (if any) open or closed.
												 */
												function toggleFilterPanel() {
													var table = document.getElementById("form1:table2");
													return table.toggleTableFilterPanel();
												}
												/* ----- Functions for Table Actions ----- */
												/*
												 * Initialize all rows of the table when the state
												 * of selected rows changes.
												 */
												function initAllRows() {
													var table = document.getElementById("form1:table2");
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
													var table = document.getElementById("form1:table2");
													table.selectGroupRows(rowGroupId, selected);
												}
												/*
												 * Disable all table actions if no rows have been selected.
												 */
												function disableActions() {
													// Determine whether any rows are currently selected
													var table = document.getElementById("form1:table2");
													var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
													// Set disabled state for top actions
													document.getElementById("form1:table2:tableActionsTop:deleteTop").setDisabled(disabled);
													// Set disabled state for bottom actions
													document.getElementById("form1:table2:tableActionsBottom:deleteBottom").setDisabled(disabled);
												}
												]]>
											</script>
											<ui:tableRowGroup binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.tableRowGroup2}" id="tableRowGroup2"
												sourceData="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.ldpPresupuestoSolSum}" sourceVar="currentRow2">
												<ui:tableColumn align="center" binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.tableColumn3}" id="tableColumn3"
													onClick="setTimeout('initAllRows()', 0)" valign="middle" width="10">
													<ui:radioButton binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.radioButton2}" id="radioButton2" label=""
														name="buttonGroup2" selected="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.RBSelected2}"
														selectedValue="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.currentRow2}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.tcProveedor}" headerText="Proveedor"
													id="tcProveedor" sort="proveedor">
													<ui:staticText binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.stProveedor}" id="stProveedor"
														text="#{currentRow2.value['proveedor']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.tcPlazo}" headerText="Plazo de mantenimiento"
													id="tcPlazo" sort="plazoMantenimiento">
													<ui:staticText binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.stPlazo}" id="stPlazo"
														text="#{currentRow2.value['plazoMantenimiento']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.tcTotalPresupuestado}"
													headerText="Total presupuestado" id="tcTotalPresupuestado" sort="total">
													<ui:staticText binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.stTotalPresupuestado}"
														id="stTotalPresupuestado" text="#{currentRow2.value['total']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.groupPanel2}" id="groupPanel2">
													<ui:button action="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.btnAgregarPresupuestoSolSum_action}"
														binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.btnAgregarPresupuestoSolSum}"
														id="btnAgregarPresupuestoSolSum" styleClass="button" text="Agregar" />
													<ui:button action="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.btnModificarPresupuestoSolSum_action}"
														binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.btnModificarPresupuestoSolSum}"
														id="btnModificarPresupuestoSolSum" styleClass="button" text="Modificar" />
													<ui:button action="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.btnConsultarPresupuestoSolSum_action}"
														binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.btnConsultarPresupuestoSolSum}"
														id="btnConsultarPresupuestoSolSum" styleClass="button" text="Consultar" />
													<a4j:commandButton action="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.btnQuitarPresupuestoSolSum_action}"
														binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.btnQuitarPresupuestoSolSum}" id="btnQuitarPresupuestoSolSum"
														value="Quitar" styleClass="btnAjax" reRender="table2" onmousedown="reemplazarClickConConfirmacion(this, '');" />
													<ui:staticText binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.stSeparador4}" escape="false" id="stSeparador4"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<a4j:commandButton action="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.btnQuitarTodosPresupuestoSolSum_action}"
														binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.btnQuitarTodosPresupuestoSolSum}"
														id="btnQuitarTodosPresupuestoSolSum" value="Quitar todos" styleClass="btnAjax" reRender="table2"
														onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea quitar todos los elementos de esta lista?');" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:panelGroup binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.pgMovimientoMercaderia}">
											<tr>
												<td colspan="4">
													<hr />
													<br />
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<ui:staticText binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.stMovimientoMercaderia}"
														id="stMovimientoMercaderia" styleClass="label2" text="Movimientos de Mercadería" />
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<ui:table augmentTitle="false" binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.table3}" id="table3"
														width="479">
														<ui:tableRowGroup binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.tableRowGroup3}" id="tableRowGroup3"
															sourceData="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.ldpMovimientoMercaderia}" sourceVar="currentRow3">
															<ui:tableColumn binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.tcStockBien}" headerText="Bien"
																id="tcStockBien" sort="bien">
																<ui:staticText binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.stStockBien}" id="stStockBien"
																	text="#{currentRow3.value['stock'].bien}" />
															</ui:tableColumn> 
															<ui:tableColumn binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.tcCantidadMercaderia}" headerText="Cantidad"
																id="tcCantidadMercaderia" sort="cantidad">
																<ui:staticText binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.stCantidadMercaderia}"
																	id="stCantidadMercaderia" text="#{currentRow3.value['cantidad']}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.tcDeposito}" headerText="Deposito"
																id="tcDeposito" sort="deposito">
																<ui:staticText binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.stDeposito}" id="stDeposito"
																	text="#{currentRow3.value['movimientoDeMercaderia'].deposito}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.tcDepositoDestino}"
																headerText="Deposito Destino" id="tcDepositoDestino" sort="depositoDestino">
																<ui:staticText binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.stDepositoDestino}" id="stDepositoDestino"
																	text="#{currentRow3.value['movimientoDeMercaderia'].depositoDestino}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.tcFecha}" headerText="Fecha" id="tcFecha"
															sort="fechaDate">
																<ui:staticText binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.stFecha}" id="stFecha"
																	text="#{currentRow3.value['movimientoDeMercaderia'].fechaDate}"
																	converter="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.dateTimeConverter}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.tcUsuario}" headerText="Usuario"
																id="tcUsuario" sort="usuario">
																<ui:staticText binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.stUsuario}" id="stUsuario"
																	text="#{currentRow3.value['movimientoDeMercaderia'].usuario}" />
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
										<ui:panelGroup binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.pgFirmasSolicitud}">
											<tr>
												<td colspan="2">
													<ui:label binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.lblListaFirmas}" id="lblListaFirmas"
														styleClass="label2" text="Firmas De La Solicitud" />
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<ui:table augmentTitle="false" binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.tableFirmas}" id="tableFirmas"
														width="200">
														<script>
															<![CDATA[
															/* ----- Functions for Table Preferences Panel ----- */
															/*
															 * Toggle the table preferences panel open or closed
															 */
															function togglePreferencesPanel() {
																var table = document.getElementById("form1:tableFirmas");
																table.toggleTblePreferencesPanel();
															}
															/* ----- Functions for Filter Panel ----- */
															/*
															 * Return true if the filter menu has actually changed,
															 * so the corresponding event should be allowed to continue.
															 */
															function filterMenuChanged() {
																var table = document.getElementById("form1:tableFirmas");
																return table.filterMenuChanged();
															}
															/*
															 * Toggle the custom filter panel (if any) open or closed.
															 */
															function toggleFilterPanel() {
																var table = document.getElementById("form1:tableFirmas");
																return table.toggleTableFilterPanel();
															}
															/* ----- Functions for Table Actions ----- */
															/*
															 * Initialize all rows of the table when the state
															 * of selected rows changes.
															 */
															function initAllRows() {
																var table = document.getElementById("form1:tableFirmas");
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
																var table = document.getElementById("form1:tableFirmas");
																table.selectGroupRows(rowGroupId, selected);
															}
															/*
															 * Disable all table actions if no rows have been selected.
															 */
															function disableActions() {
																// Determine whether any rows are currently selected
																var table = document.getElementById("form1:tableFirmas");
																var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
																// Set disabled state for top actions
																document.getElementById("form1:tableFirmas:tableActionsTop:deleteTop").setDisabled(
																		disabled);
																// Set disabled state for bottom actions
																document.getElementById("form1:tableFirmas:tableActionsBottom:deleteBottom")
																		.setDisabled(disabled);
															}
															]]>
														</script>
														<ui:tableRowGroup binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.trgFirmas}" id="trgFirmas"
															sourceData="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.ldpFirmas}" sourceVar="currentRow">
															<ui:tableColumn headerText="Usuario" id="tcUsuario">
																<ui:staticText id="stUsuario" text="#{currentRow.value['firmaPermiso'].usuario}" />
															</ui:tableColumn>
															<ui:tableColumn headerText="Fecha - Hora" id="tcFechaHora" sort="firmaPermiso">
																<ui:staticText id="stFechaHora" text="#{currentRow.value['firmaPermiso'].fechaHora}"
																	converter="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.dateTimeConverter}" />
															</ui:tableColumn>
														</ui:tableRowGroup>
													</ui:table>
												</td>
											</tr>
										</ui:panelGroup>
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:label binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.lblLineaOrdenCompra}" id="lblLineaOrdenCompra"
											styleClass="label2" text="Lineas de Orden de Compra" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.tablaLineaOrdenCompra}"
											id="tablaLineaOrdenCompra" width="479">
											<script>
												<![CDATA[
												/* ----- Functions for Table Preferences Panel ----- */
												/*
												 * Toggle the table preferences panel open or closed
												 */
												function togglePreferencesPanel() {
													var table = document.getElementById("form1:tablaLineaOrdenCompra");
													table.toggleTblePreferencesPanel();
												}
												/* ----- Functions for Filter Panel ----- */
												/*
												 * Return true if the filter menu has actually changed,
												 * so the corresponding event should be allowed to continue.
												 */
												function filterMenuChanged() {
													var table = document.getElementById("form1:tablaLineaOrdenCompra");
													return table.filterMenuChanged();
												}
												/*
												 * Toggle the custom filter panel (if any) open or closed.
												 */
												function toggleFilterPanel() {
													var table = document.getElementById("form1:tablaLineaOrdenCompra");
													return table.toggleTableFilterPanel();
												}
												/* ----- Functions for Table Actions ----- */
												/*
												 * Initialize all rows of the table when the state
												 * of selected rows changes.
												 */
												function initAllRows() {
													var table = document.getElementById("form1:tablaLineaOrdenCompra");
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
													var table = document.getElementById("form1:tablaLineaOrdenCompra");
													table.selectGroupRows(rowGroupId, selected);
												}
												/*
												 * Disable all table actions if no rows have been selected.
												 */
												function disableActions() {
													// Determine whether any rows are currently selected
													var table = document.getElementById("form1:tablaLineaOrdenCompra");
													var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
													// Set disabled state for top actions
													document.getElementById("form1:tablaLineaOrdenCompra:tableActionsTop:deleteTop").setDisabled(
															disabled);
													// Set disabled state for bottom actions
													document.getElementById("form1:tablaLineaOrdenCompra:tableActionsBottom:deleteBottom")
															.setDisabled(disabled);
												}
												]]>
											</script>
											<ui:tableRowGroup binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.trgLineaOrdenCompra}" id="trgLineaOrdenCompra"
												sourceData="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.ldpLineaOrdenCompra}" sourceVar="currentRowLineaOrdenCompra">
												<ui:tableColumn binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.tcBienLineaOrdenCompra}" headerText="Bien"
													id="tcBienLineaOrdenCompra" sort="bien">
													<ui:staticText binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.stBienLineaOrdenCompra}"
														id="stBienLineaOrdenCompra" text="#{currentRowLineaOrdenCompra.value['bien']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.tcCantidadLineaOrdenCompra}"
													headerText="Cantidad" id="tcCantidadLineaOrdenCompra" sort="cantidad">
													<ui:staticText binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.stCantidadLineaOrdenCompra}"
														id="stCantidadLineaOrdenCompra" text="#{currentRowLineaOrdenCompra.value['cantidad']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.tcMontoUnitarioLineaOrdenCompra}"
													headerText="Monto Unitario" id="tcMontoUnitarioLineaOrdenCompra" sort="montoUnitario">
													<ui:staticText binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.stMontoUnitarioLineaOrdenCompra}"
														id="stMontoUnitarioLineaOrdenCompra" text="#{currentRowLineaOrdenCompra.value['montoUnitario']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.tcMontoTotalLineaOrdenCompra}"
													headerText="Monto Total" id="tcMontoTotalLineaOrdenCompra" sort="montoTotal">
													<ui:staticText binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.stMontoTotalLineaOrdenCompra}"
														id="stMontoTotalLineaOrdenCompra" text="#{currentRowLineaOrdenCompra.value['montoTotal']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:panelGroup binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.gpFinalizarComo}">
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.lblFinalizarComo}" for="ddFinalizarComo"
														id="lblFinalizarComo" styleClass="label" text="Estado final" />
												</td>
												<td>
													<ui:dropDown binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.ddFinalizarComo}" id="ddFinalizarComo"
														items="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.ddFinalizarComoDefaultOptions.options}" styleClass="textField" />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.lblComentario}" id="lblComentario"
														styleClass="label" text="Comentario de finalización" />
												</td>
												<td>
													<ui:textArea binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.taComentario}" columns="40" id="taComentario"
														rows="5" styleClass="textField" />
												</td>
											</tr>
										</ui:panelGroup>
									</td>
								</tr>
								<tr>
									<td align="right">
										<ui:label binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
									</td>
									<td>
										<ui:textArea binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.tablaLogs}" id="tbLogsAuditoria" />
									</td>
								</tr>
								<!--<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.tablaLogs}" id="tbLogsAuditoria" id="tablaLogs"/>
									</td>
								</tr>
								<tr><td><br/></td></tr>
								<tr>
				                	<td align="right" nowrap="nowrap">
				                    	<ui:label binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria"
				                        			styleClass="label" text="Comentario"/>
				                    </td>
				                    <td nowrap="nowrap">
				                    	<ui:textArea binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.taComentarioLogAuditoria}" columns="40" id="taComentarioLogAuditoria" styleClass="textField"
				                                        rows="5"/>
				                    </td>
				                </tr>-->
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.messageGroup}" id="messageGroup"
											styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.btnGuardar_action}"
											binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.btnCancelar_action}"
											binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.idSubSesion}" />
					<ui:script binding="#{compras$ABMSolicitudSuministro$ABMSolicitudSuministro.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
