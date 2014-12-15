<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.head1}" id="head1">
				<ui:link binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor";

					function cargarComportamientoJQuery() {
						autoCompletarEnTextField("#form1:tfParcela", "parcela", nombreBean, "setParcelaAutocompletar");

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

					function focusearTfParcelaSeleccionada() {
						$("#form1\\:tfParcela").focus();
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
			<ui:body binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload(); Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.stTitulo}" id="stTitulo"
									styleClass="tituloABM" text="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.label4}" id="label4" styleClass="label"
											text="Inicio de Actividades" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.tfFechaInicio}" id="tfFechaInicio"
											styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
										<!--<ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.staticText1}" escape="false"
                                                           id="staticText1" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
									</td>
									<td>
										<ui:panelGroup binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.pgPersona}">
											<tr>
												<td colspan="4">
													<hr />
													<br />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.lblPersona}" for="tfPersona"
														id="lblPersona" styleClass="label" text="Persona" />
												</td>
												<td nowrap="nowrap">
													<ui:textField binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.tfPersona}" columns="40"
														disabled="true" id="tfPersona" styleClass="textField" />
													<ui:button action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.btnSeleccionarPersona_action}"
														binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.btnSeleccionarPersona}" escape="false"
														id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Fisica" />
													<ui:button action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.btnSeleccionarPersonaJuridica_action}"
														binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.btnSeleccionarPersonaJuridica}" escape="false"
														id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar Persona Juridica" />
													<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersona" title="Limpiar"
														binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.btnLimpiarPersona}"
														action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.btnLimpiarPersona_action}"
														styleClass="buttonLimpiarAjax" />
												</td>
											</tr>
										</ui:panelGroup>
									</td>
									<td>
										<ui:panelGroup binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.pgParcela}">
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.lblParcela}" for="tfParcela"
														id="lblParcela" styleClass="label" text="Parcela" />
												</td>
												<td nowrap="nowrap">
													<ui:textField binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.tfParcela}" columns="40"
														id="tfParcela"
														styleClass="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.hayParcela ? 'textFieldDisabled' : 'textField'}"
														disabled="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.hayParcela}" />
													<ui:button action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.btnSeleccionarParcela_action}"
														binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.btnSeleccionarParcela}" escape="false"
														id="btnSeleccionarParcela" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
													<a4j:commandButton id="btnLimpiarParcela" reRender="form1:tfParcela, form1:table1" title="Limpiar"
														binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.btnLimpiarParcela}"
														action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.btnLimpiarParcela_action}"
														styleClass="buttonLimpiarAjax" oncomplete="cargarComportamientoJQuery(); focusearTfParcelaSeleccionada();" />
												</td>
											</tr>
										</ui:panelGroup>
									</td>
									<td>
										<ui:panelGroup binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.pgPersonaSolicitante}">
											<tr>
												<td nowrap="nowrap">
													<ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.staticText3}" id="staticText3"
														styleClass="label" text="Persona Solicitante" />
												</td>
											</tr>
											<tr>
												<td colspan="2">
													<ui:table binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.table1}" id="table1">
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
																document.getElementById("form1:table1:tableActionsTop:deleteTop").setDisabled(
																		disabled);
																// Set disabled state for bottom actions
																document.getElementById("form1:table1:tableActionsBottom:deleteBottom").setDisabled(
																		disabled);
															}
															]]>
														</script>
														<ui:tableRowGroup binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.tableRowGroup1}"
															emptyDataMsg="Ningún registro encontrado." id="tableRowGroup1"
															sourceData="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.ldpPropietarios}" sourceVar="currentRow">
															<ui:tableColumn align="center" binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.tableColumn1}"
																id="tableColumn1" valign="middle" width="10">
																<ui:radioButton binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.radioButton1}"
																	id="radioButton1" label="" name="buttonGroup"
																	selected="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.RBSelected}"
																	selectedValue="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.currentRow3}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.tableColumn2}"
																headerText="Propietarios" id="tableColumn2" sort="persona">
																<ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.stPersona}" id="stPersona"
																	text="#{currentRow.value['toStringCompleto']}" />
															</ui:tableColumn>
														</ui:tableRowGroup>
													</ui:table>
												</td>
											</tr>
										</ui:panelGroup>
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.label1}" id="label1" styleClass="label"
											text="Domicilio Postal" />
									</td>
									<td nowrap="nowrap">
										<ui:button action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.btnSeleccionarDomicilioPostal_action}"
											binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.btnSeleccionarDomicilioPostal}" escape="false"
											id="btnSeleccionarDomicilioPostal" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Agregar/Modificar" />
										<a4j:commandButton id="btnLimpiarDomicilioPostal" reRender="form1:stDomicilioPostal" title="Limpiar"
											binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.btnLimpiarDomicilioPostal}"
											action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.btnLimpiarDomicilioPostal_action}"
											styleClass="buttonLimpiarAjax" />
										<ui:button action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.btnSeleccionarDomicilioSolicitante_action}"
											binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.btnSeleccionarDomicilioSolicitante}"
											id="btnSeleccionarDomicilioSolicitante" mini="true" styleClass="button" text="Del Solicitante" />
										<ui:button action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.btnSeleccionarDomicilioParcela_action}"
											binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.btnSeleccionarDomicilioParcela}"
											id="btnSeleccionarDomicilioParcela" mini="true" styleClass="button" text="De la Parcela" />
									</td>
									<td align="right" nowrap="nowrap"></td>
									<td nowrap="nowrap"></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap"></td>
									<td colspan="3">
										<ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.stDomicilioPostal}" escape="false"
											id="stDomicilioPostal" styleClass="staticText" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<div class="div" style="width: 290px; height: 15px;">Atributos Dinámicos</div>
										<table border="0" class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
											<tr>
												<td colspan="4">
													<ui:panelGroup binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.panelAtributoDinamico}"
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
										<ui:table binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.tablaLogsLiquidacion}"
											id="tbLogsLiquidacion" clearSortButton="true" width="770px">
											<ui:tableRowGroup binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.tableRowGroup}" id="tableRowGroup"
												emptyDataMsg="Ningún registro encontrado."
												sourceData="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.ldpLogsLiquidacion}" sourceVar="currentRow">
												<ui:tableColumn binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.tableColumn4}" id="tableColumn4"
													headerText="Usuario" sort="usuario">
													<ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.staticText4}" id="staticText4"
														text="#{currentRow.value['usuario']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.tableColumn5}" id="tableColumn5"
													headerText="Evento" sort="evento">
													<ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.staticText5}" id="staticText5"
														text="#{currentRow.value['evento']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.tableColumn6}" id="tableColumn6"
													headerText="Cuota" sort="cuota">
													<ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.staticText6}" id="staticText6"
														text="#{currentRow.value['cuota']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.tableColumn7}" id="tableColumn7"
													headerText="Fecha y Hora" sort="fecha">
													<ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.staticText7}" id="staticText7"
														text="#{currentRow.value['fecha']}"
														converter="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.dateTimeConverter1}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.tableColumn8}" id="tableColumn8"
													headerText="Monto Total" sort="montoTotalLiquidacion">
													<ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.staticText8}" id="staticText8"
														text="#{currentRow.value['montoTotalLiquidacion']}"
														converter="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.numberConverter1}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.tableColumn9}" id="tableColumn9"
													headerText="Comentario">
													<ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.staticText9}" id="staticText9"
														text="#{currentRow.value['comentario']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="filter">
												<ui:panelGroup binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.gpFiltroTablaLogsLiquidacion}"
													id="gpFiltroTablaLogsLiquidacion">
													<ui:textField binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.tfFiltrarUsuarioLogLiq}"
														id="tfFiltrarUsuarioLogLiq" styleClass="textField" />
													<ui:dropDown binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.ddFiltrarEventoLogLiq}"
														id="ddFiltrarEventoLogLiq" styleClass="textField"
														items="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.ddFiltrarEventoLogLiqOptions.options}" />
													<ui:textField id="tfFiltrarFechaDesdeLogLiq"
														binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.tfFiltrarFechaDesdeLogLiq}"
														styleClass="textField" columns="10" />
													<ui:textField binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.tfFiltrarFechaHastaLogLiq}"
														id="tfFiltrarFechaHastaLogLiq" styleClass="textField" columns="10" />
													<ui:staticText id="staticTextSeparadora1" styleClass="barraSeparadoraVertical" escape="false" text="|" />
													<a4j:commandButton binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.btnFiltrarLogLiq}"
														action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.btnFiltrarLogLiq_action}" id="btnFiltrarLogLiq"
														styleClass="btnAjax" reRender="tbLogsLiquidacion" oncomplete="cargarComportamientoJQuery();" value="Buscar" />
													<a4j:commandButton binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.btnLimpiarFiltradoLogLiq}"
														action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.btnLimpiarFiltradoLogLiq_action}"
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
										<ui:label binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.lblComentarioLogAuditoria}"
											id="lblComentarioLogAuditoria" />
									</td>
									<td>
										<ui:textArea binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.taComentarioLogAuditoria}"
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
										<ui:table binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.tablaLogs}" id="tbLogsAuditoria" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.messageGroup1}" id="messageGroup1"
											styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.btnGuardar_action}"
											binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.stSeparador}" escape="false"
											id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.btnCancelar_action}"
											binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
						<br /> <br /> <br />
					</div>
					<ui:hiddenField binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
