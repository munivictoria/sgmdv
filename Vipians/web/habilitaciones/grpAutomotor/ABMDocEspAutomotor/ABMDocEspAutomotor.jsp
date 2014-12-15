<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.head1}" id="head1">
				<ui:link binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					function cargarComportamientoJQuery() {
						var $pgTablaLogsLiquidacion = $("#form1").find("span[id *= 'tbLogsLiquidacion:gpFiltroTablaLogsLiquidacion']");

						for(var i = 0; i < $pgTablaLogsLiquidacion.length; i++) {
							var $componentes = $("#" + $pgTablaLogsLiquidacion.eq(i).attr("id").replace(/:/g, "\\:"))
									.find("input[id *= 'tfFiltrar']");

							$("#" + $componentes.eq(0).attr("id").replace(/:/g, "\\:")).attr("placeholder", "Usuario");
							$("#" + $componentes.eq(1).attr("id").replace(/:/g, "\\:")).attr("placeholder", "Fecha desde");
							$("#" + $componentes.eq(2).attr("id").replace(/:/g, "\\:")).attr("placeholder", "Fecha hasta");
							calendarioEnTextField("#" + $componentes.eq(1).attr("id"));
							calendarioEnTextField("#" + $componentes.eq(2).attr("id"));
						}
					}

					function focusearTfFiltrarUsuario() {
						$("#form1\\:tbLogsLiquidacion\\:tfFiltrarUsuarioLogLiq").focus();
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.body1}" focus="form1:tfNumeroCuenta" id="body1"
				onLoad="parent.footer.location.reload(); Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.stTitulo}" id="stTitulo"
									styleClass="tituloABM" text="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="center" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.lblNroCuenta}" id="lblNroCuenta" style=""
											styleClass="label" text="Número de Cuenta" />
										<ui:textField binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.tfNumeroCuenta}" id="tfNumeroCuenta"
											styleClass="textField" />
									</td>
									<td nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.lblVehiculo}" id="lblVehiculo"
											styleClass="label" for="tfVehiculo" text="Vehiculo" />
										<ui:textField binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.tfVehiculo}" columns="40"
											disabled="true" id="tfVehiculo" styleClass="textField" />
										<ui:button action="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.btnSeleccionarVehiculo_action}"
											binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.btnSeleccionarVehiculo}" escape="false"
											id="btnSeleccionarVehiculo" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" />
										<a4j:commandButton id="btnLimpiarVehiculo" reRender="form1:tfVehiculo" title="Limpiar"
											binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.btnLimpiarVehiculo}"
											action="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.btnLimpiarVehiculo_action}"
											styleClass="buttonLimpiarAjax" />
										<ui:button action="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.btnConsultarAutomotor_action}"
											binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.btnConsultarAutomotor}" escape="false"
											id="btnConsultarAutomotor" mini="true" text="Consultar" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:table binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.tablaPropietarios}" id="tablaPropietarios">
											<script>
												<![CDATA[
												/* Functions for Table Preferences Panel */
												/*
												 * Toggle the table preferences panel open or closed
												 */
												function togglePreferencesPanel() {
													var table = document.getElementById("form1:tablaPropietarios");
													table.toggleTblePreferencesPanel();
												}
												/*  Functions for Filter Panel */
												/*
												 * Return true if the filter menu has actually changed,
												 * so the corresponding event should be allowed to continue.
												 */
												function filterMenuChanged() {
													var table = document.getElementById("form1:tablaPropietarios");
													return table.filterMenuChanged();
												}
												/*
												 * Toggle the custom filter panel (if any) open or closed.
												 */
												function toggleFilterPanel() {
													var table = document.getElementById("form1:tablaPropietarios");
													return table.toggleTableFilterPanel();
												}
												/*  Functions for Table Actions */
												/*
												 * Initialize all rows of the table when the state
												 * of selected rows changes.
												 */
												function initAllRows() {
													var table = document.getElementById("form1:tablaPropietarios");
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
													var table = document.getElementById("form1:tablaPropietarios");
													table.selectGroupRows(rowGroupId, selected);
												}
												/*
												 * Disable all table actions if no rows have been selected.
												 */
												function disableActions() {
													// Determine whether any rows are currently selected
													var table = document.getElementById("form1:tablaPropietarios");
													var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
													// Set disabled state for top actions
													document.getElementById("form1:tablaPropietarios:tableActionsTop:deleteTop")
															.setDisabled(disabled);
													// Set disabled state for bottom actions
													document.getElementById("form1:tablaPropietarios:tableActionsBottom:deleteBottom").setDisabled(
															disabled);
												}
												]]>
											</script>
											<ui:tableRowGroup binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.trgPropietarios}"
												emptyDataMsg="Ningún registro encontrado." id="trgPropietarios"
												sourceData="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.ldpPropietarios}" sourceVar="currentRow2">
												<ui:tableColumn align="center" binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.tableColumn1}"
													id="tableColumn1" valign="middle" width="10">
													<ui:radioButton binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.radioButton1}" id="radioButton1"
														label="" name="buttonGroup" selected="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.RBSelected}"
														selectedValue="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.currentRow2}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.tcPersona}"
													headerText="Propietarios" id="tcPersona" sort="persona">
													<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.stPersona}" id="stPersona"
														text="#{currentRow2.value['toStringCompleto']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.lblDomicilio}" id="lblDomicilio"
											styleClass="label" text="Domicilio Postal" />
									</td>
									<td nowrap="nowrap">
										<ui:button action="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.btnSeleccionarDomicilioPostal_action}"
											binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.btnSeleccionarDomicilioPostal}" escape="false"
											id="btnSeleccionarDomicilioPostal" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Agregar/Modificar" />
										<a4j:commandButton id="btnLimpiarDomicilioPostal" reRender="form1:stDomicilioPostal" title="Limpiar"
											binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.btnLimpiarDomicilioPostal}"
											action="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.btnLimpiarDomicilioPostal_action}"
											styleClass="buttonLimpiarAjax" />
										<ui:button action="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.btnSeleccionarDomicilioSolicitante_action}"
											binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.btnSeleccionarDomicilioSolicitante}"
											id="btnSeleccionarDomicilioSolicitante" mini="true" styleClass="button" text="Del Solicitante" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap" style="height: 17px"></td>
									<td colspan="1">
										<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.stDomicilioPostal}" escape="false"
											id="stDomicilioPostal" styleClass="staticText" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.stDocsGeneradoresDeuda}"
											id="stDocsGeneradoresDeuda" styleClass="label2" text="Documentos Generadores de Deuda" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false"
											binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.tablaDocsGeneradoresDeuda}"
											id="tablaDocsGeneradoresDeuda" width="479">
											<script>
												<![CDATA[
												/* ----- Functions for Table Preferences Panel ----- */
												/*
												 * Toggle the table preferences panel open or closed
												 */
												function togglePreferencesPanel() {
													var table = document.getElementById("form1:tablaDocsGeneradoresDeuda");
													table.toggleTblePreferencesPanel();
												}
												/* ----- Functions for Filter Panel ----- */
												/*
												 * Return true if the filter menu has actually changed,
												 * so the corresponding event should be allowed to continue.
												 */
												function filterMenuChanged() {
													var table = document.getElementById("form1:tablaDocsGeneradoresDeuda");
													return table.filterMenuChanged();
												}
												/*
												 * Toggle the custom filter panel (if any) open or closed.
												 */
												function toggleFilterPanel() {
													var table = document.getElementById("form1:tablaDocsGeneradoresDeuda");
													return table.toggleTableFilterPanel();
												}
												/* ----- Functions for Table Actions ----- */
												/*
												 * Initialize all rows of the table when the state
												 * of selected rows changes.
												 */
												function initAllRows() {
													var table = document.getElementById("form1:tablaDocsGeneradoresDeuda");
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
													var table = document.getElementById("form1:tablaDocsGeneradoresDeuda");
													table.selectGroupRows(rowGroupId, selected);
												}
												/*
												 * Disable all table actions if no rows have been selected.
												 */
												function disableActions() {
													// Determine whether any rows are currently selected
													var table = document.getElementById("form1:tablaDocsGeneradoresDeuda");
													var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
													// Set disabled state for top actions
													document.getElementById("form1:tablaDocsGeneradoresDeuda:tableActionsTop:deleteTop").setDisabled(
															disabled);
													// Set disabled state for bottom actions
													document.getElementById("form1:tablaDocsGeneradoresDeuda:tableActionsBottom:deleteBottom")
															.setDisabled(disabled);
												}
												]]>
											</script>
											<ui:tableRowGroup binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.trgDocsGeneradoresDeuda}"
												id="trgDocsGeneradoresDeuda"
												sourceData="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.ldpDocsGeneradoresDeuda}" sourceVar="currentRow">
												<ui:tableColumn binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.tcAnio}" headerText="Año"
													id="tcAnio" sort="anio">
													<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.stAnio}" id="stAnio"
														text="#{currentRow.value['anio']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.tcPlan}" headerText="Plan"
													id="tcPlan" sort="plan">
													<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.stPlan}" id="stPlan"
														text="#{currentRow.value['periodicidad']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<div class="div" style="width: 290px; height: 15px;">Atributos Dinámicos</div>
										<table border="0" class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
											<tr>
												<td colspan="4">
													<ui:panelGroup binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.panelAtributoDinamico}"
														id="panelAtributoDinamico">
														<!-- AQUI VA LO QUE SE CREA DINAMICAMENTE -->
													</ui:panelGroup>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<div class="div" style="width: 760px; height: 15px;">Logs de Liquidaciones</div>
										<ui:table binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.tablaLogsLiquidacion}"
											id="tbLogsLiquidacion" clearSortButton="true" width="770px">
											<ui:tableRowGroup binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.tableRowGroup}" id="tableRowGroup"
												emptyDataMsg="Ningún registro encontrado."
												sourceData="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.ldpLogsLiquidacion}" sourceVar="currentRow">
												<ui:tableColumn binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.tableColumn2}" id="tableColumn2"
													headerText="Usuario" sort="usuario">
													<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.staticText2}" id="staticText2"
														text="#{currentRow.value['usuario']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.tableColumn3}" id="tableColumn3"
													headerText="Evento" sort="evento">
													<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.staticText3}" id="staticText3"
														text="#{currentRow.value['evento']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.tableColumn4}" id="tableColumn4"
													headerText="Cuota" sort="cuota">
													<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.staticText4}" id="staticText4"
														text="#{currentRow.value['cuota']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.tableColumn5}" id="tableColumn5"
													headerText="Fecha y Hora" sort="fecha">
													<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.staticText5}" id="staticText5"
														text="#{currentRow.value['fecha']}"
														converter="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.dateTimeConverter1}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.tableColumn6}" id="tableColumn6"
													headerText="Monto Total" sort="montoTotalLiquidacion">
													<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.staticText6}" id="staticText6"
														text="#{currentRow.value['montoTotalLiquidacion']}"
														converter="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.numberConverter1}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.tableColumn7}" id="tableColumn7"
													headerText="Comentario">
													<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.staticText7}" id="staticText7"
														text="#{currentRow.value['comentario']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="filter">
												<ui:panelGroup binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.gpFiltroTablaLogsLiquidacion}"
													id="gpFiltroTablaLogsLiquidacion">
													<ui:textField binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.tfFiltrarUsuarioLogLiq}"
														id="tfFiltrarUsuarioLogLiq" styleClass="textField" />
													<ui:dropDown binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.ddFiltrarEventoLogLiq}"
														id="ddFiltrarEventoLogLiq" styleClass="textField"
														items="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.ddFiltrarEventoLogLiqOptions.options}" />
													<ui:textField id="tfFiltrarFechaDesdeLogLiq"
														binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.tfFiltrarFechaDesdeLogLiq}"
														styleClass="textField" columns="10" />
													<ui:textField binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.tfFiltrarFechaHastaLogLiq}"
														id="tfFiltrarFechaHastaLogLiq" styleClass="textField" columns="10" />
													<ui:staticText id="staticTextSeparadora1" styleClass="barraSeparadoraVertical" escape="false" text="|" />
													<a4j:commandButton binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.btnFiltrarLogLiq}"
														action="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.btnFiltrarLogLiq_action}" id="btnFiltrarLogLiq"
														styleClass="btnAjax" reRender="tbLogsLiquidacion" oncomplete="cargarComportamientoJQuery();" value="Buscar" />
													<a4j:commandButton binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.btnLimpiarFiltradoLogLiq}"
														action="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.btnLimpiarFiltradoLogLiq_action}"
														id="btnLimpiarFiltradoLogLiq" styleClass="buttonLimpiarAjax"
														reRender="tbLogsLiquidacion, tfFiltrarUsuarioLogLiq, ddFiltrarEventoLogLiq, tfFiltrarFechaDesdeLogLiq, tfFiltrarFechaHastaLogLiq"
														oncomplete="cargarComportamientoJQuery(); focusearTfFiltrarUsuario();" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right">
										<ui:label binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.lblComentarioLogAuditoria}"
											id="lblComentarioLogAuditoria" />
									</td>
									<td>
										<ui:textArea binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.taComentarioLogAuditoria}"
											id="taComentarioLogAuditoria" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.tablaLogs}" id="tbLogsAuditoria" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.messageGroup1}" id="messageGroup1"
											styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.btnGuardar_action}"
											binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.stSeparador}" escape="false"
											id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.btnCancelar_action}"
											binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
						<br /> <br /> <br />
					</div>
					<ui:hiddenField binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
