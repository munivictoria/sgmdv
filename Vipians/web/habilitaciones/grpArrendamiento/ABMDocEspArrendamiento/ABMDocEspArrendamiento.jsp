<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.head1}" id="head1">
				<ui:link binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento";

					function cargarComportamientoJQuery() {
						autoCompletarEnTextField("#form1:tfParcela", "parcela", nombreBean, "setParcelaAutocompletar", null,
								"#form1:btnResfrescarTable1");

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

					function focusearTfParcela() {
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
			<ui:body binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.body1}" focus="form1:tfFechaInicio" id="body1"
				onLoad="parent.footer.location.reload(); Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.label4}" for="tfFechaInicio" id="label4" styleClass="label"
											text="Inicio de Actividades" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.tfFechaInicio}" id="tfFechaInicio" styleClass="textField"
											onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
										<!--<ui:staticText binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.staticText1}" escape="false"
                                                           id="staticText1" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap"></td>
									<td nowrap="nowrap"></td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.label6}" for="tfFechaCese" id="label6" rendered="false"
											styleClass="label" text="Cese de Actividades" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.tfFechaCese}" id="tfFechaCese" rendered="false"
											styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
										<!--<ui:staticText binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.staticText2}" escape="false"
                                                           id="staticText2" rendered="false" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<hr />
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.lblParcela}" for="tfParcela" id="lblParcela"
											styleClass="label" text="Parcela" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.tfParcela}" columns="40" id="tfParcela"
											styleClass="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.hayParcela ? 'textFieldDisabled' : 'textField'}"
											disabled="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.hayParcela}" />
										<ui:button action="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.btnSeleccionarParcela_action}"
											binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.btnSeleccionarParcela}" escape="false" id="btnSeleccionarParcela"
											mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Agregar/Modificar" />
										<a4j:commandButton id="btnLimpiarParcela" reRender="form1:tfParcela, form1:table1" title="Limpiar"
											binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.btnLimpiarParcela}"
											action="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.btnLimpiarParcela_action}" styleClass="buttonLimpiarAjax"
											oncomplete="cargarComportamientoJQuery(); focusearTfParcela();" />
										<ui:button action="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.btnConsultarParcela_action}"
											binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.btnConsultarParcela}" escape="false" id="btnConsultarParcela"
											mini="true" text="Consultar" />
										<a4j:commandButton id="btnResfrescarTable1" style="display: none;" reRender="form1:table1"
											oncomplete="cargarComportamientoJQuery();" />
									</td>
									<td align="right" nowrap="nowrap"></td>
									<td nowrap="nowrap"></td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:table binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.table1}" id="table1">
											<script>
												<![CDATA[
												/* Functions for Table Preferences Panel */
												/*
												 * Toggle the table preferences panel open or closed
												 */
												function togglePreferencesPanel() {
													var table = document.getElementById("form1:table1");
													table.toggleTblePreferencesPanel();
												}
												/*  Functions for Filter Panel */
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
												/*  Functions for Table Actions */
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
											<ui:tableRowGroup binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.tableRowGroup1}"
												emptyDataMsg="Ningún registro encontrado." id="tableRowGroup1"
												sourceData="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.ldpPropietarios}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.tableColumn1}" id="tableColumn1"
													valign="middle" width="10">
													<ui:radioButton binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.radioButton1}" id="radioButton1" label=""
														name="buttonGroup" selected="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.RBSelected}"
														selectedValue="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.currentRow3}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.tableColumn2}" headerText="Propietarios"
													id="tableColumn2" sort="toStringCompleto">
													<ui:staticText binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.stPersona}" id="stPersona"
														text="#{currentRow.value['toStringCompleto']}" />
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
										<ui:label binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.label1}" id="label1" styleClass="label"
											text="Domicilio Postal" />
									</td>
									<td nowrap="nowrap">
										<ui:button action="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.btnSeleccionarDomicilioPostal_action}"
											binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.btnSeleccionarDomicilioPostal}" escape="false"
											id="btnSeleccionarDomicilioPostal" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Agregar/Modificar" />
										<a4j:commandButton id="btnLimpiarDomicilioPostal" reRender="form1:stDomicilioPostal" title="Limpiar"
											binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.btnLimpiarDomicilioPostal}"
											action="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.btnLimpiarDomicilioPostal_action}" styleClass="buttonLimpiarAjax" />
										<ui:button action="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.btnSeleccionarDomicilioSolicitante_action}"
											binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.btnSeleccionarDomicilioSolicitante}"
											id="btnSeleccionarDomicilioSolicitante" mini="true" styleClass="button" text="Del Solicitante" />
										<ui:button action="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.btnSeleccionarDomicilioParcela_action}"
											binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.btnSeleccionarDomicilioParcela}" id="btnSeleccionarDomicilioParcela"
											mini="true" styleClass="button" text="De la Parcela" />
									</td>
									<td align="right" nowrap="nowrap"></td>
									<td nowrap="nowrap"></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap"></td>
									<td colspan="3">
										<ui:staticText binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.stDomicilioPostal}" escape="false"
											id="stDomicilioPostal" styleClass="staticText" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.label5}" id="label5" styleClass="label"
											text="Nombre del Documento" />
									</td>
									<td>
										<ui:textArea binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.taNombre}" columns="40" rows="5" id="taNombre"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:staticText binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.stDocsGeneradoresDeuda}" id="stDocsGeneradoresDeuda"
											styleClass="label2" text="Documentos Generadores de Deuda" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.tablaDocsGeneradoresDeuda}"
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
											<ui:tableRowGroup binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.trgDocsGeneradoresDeuda}"
												id="trgDocsGeneradoresDeuda" sourceData="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.ldpDocsGeneradoresDeuda}"
												sourceVar="currentRow">
												<ui:tableColumn binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.tcAnio}" headerText="Año" id="tcAnio" sort="anio">
													<ui:staticText binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.stAnio}" id="stAnio"
														text="#{currentRow.value['anio']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.tcPlan}" headerText="Plan elegido" id="tcPlan"
													sort="planElegido">
													<ui:staticText binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.stPlan}" id="stPlan"
														text="#{currentRow.value['planElegido']}" />
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
													<ui:panelGroup binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.panelAtributoDinamico}" id="panelAtributoDinamico">
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
										<ui:table binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.tablaLogsLiquidacion}" id="tbLogsLiquidacion"
											clearSortButton="true" width="770px">
											<ui:tableRowGroup binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.tableRowGroup}" id="tableRowGroup"
												emptyDataMsg="Ningún registro encontrado." sourceData="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.ldpLogsLiquidacion}"
												sourceVar="currentRow">
												<ui:tableColumn binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.tableColumn3}" id="tableColumn3" headerText="Usuario"
													sort="usuario">
													<ui:staticText binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.staticText3}" id="staticText3"
														text="#{currentRow.value['usuario']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.tableColumn4}" id="tableColumn4" headerText="Evento"
													sort="evento">
													<ui:staticText binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.staticText4}" id="staticText4"
														text="#{currentRow.value['evento']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.tableColumn5}" id="tableColumn5" headerText="Cuota"
													sort="cuota">
													<ui:staticText binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.staticText5}" id="staticText5"
														text="#{currentRow.value['cuota']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.tableColumn6}" id="tableColumn6"
													headerText="Fecha y Hora" sort="fecha">
													<ui:staticText binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.staticText6}" id="staticText6"
														text="#{currentRow.value['fecha']}" converter="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.dateTimeConverter1}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.tableColumn7}" id="tableColumn7"
													headerText="Monto Total" sort="montoTotalLiquidacion">
													<ui:staticText binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.staticText7}" id="staticText7"
														text="#{currentRow.value['montoTotalLiquidacion']}"
														converter="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.numberConverter1}" />
												</ui:tableColumn>
												<ui:tableColumn id="tcIntereses" headerText="Intereses" sort="Intereses">
													<ui:staticText id="stIntereses" text="#{currentRow.value['intereses']}"
														converter="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.numberConverter1}" />
												</ui:tableColumn>
												<ui:tableColumn id="tcTipoDeuda" headerText="Tipo Deuda" sort="tipoDeuda">
													<ui:staticText id="stTipoDeuda" text="#{currentRow.value['tipoDeuda']}"/>
												</ui:tableColumn>
												<ui:tableColumn id="tcComentario" headerText="Comentario">
													<ui:textArea id="taComentario" disabled="true" styleClass="textFieldDisabled"
		 												text="#{currentRow.value['comentario']}" rows="1" columns="40"/>
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="filter">
												<ui:panelGroup binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.gpFiltroTablaLogsLiquidacion}"
													id="gpFiltroTablaLogsLiquidacion">
													<ui:textField binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.tfFiltrarUsuarioLogLiq}" id="tfFiltrarUsuarioLogLiq"
														styleClass="textField" />
													<ui:dropDown binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.ddFiltrarEventoLogLiq}" id="ddFiltrarEventoLogLiq"
														styleClass="textField" items="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.ddFiltrarEventoLogLiqOptions.options}" />
													<ui:textField id="tfFiltrarFechaDesdeLogLiq"
														binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.tfFiltrarFechaDesdeLogLiq}" styleClass="textField" columns="10" />
													<ui:textField binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.tfFiltrarFechaHastaLogLiq}"
														id="tfFiltrarFechaHastaLogLiq" styleClass="textField" columns="10" />
													<ui:staticText id="staticTextSeparadora1" styleClass="barraSeparadoraVertical" escape="false" text="|" />
													<a4j:commandButton binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.btnFiltrarLogLiq}"
														action="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.btnFiltrarLogLiq_action}" id="btnFiltrarLogLiq" styleClass="btnAjax"
														reRender="tbLogsLiquidacion" oncomplete="cargarComportamientoJQuery();" value="Buscar" />
													<a4j:commandButton binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.btnLimpiarFiltradoLogLiq}"
														action="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.btnLimpiarFiltradoLogLiq_action}" id="btnLimpiarFiltradoLogLiq"
														styleClass="buttonLimpiarAjax"
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
										<ui:label binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
									</td>
									<td>
										<ui:textArea binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.tablaLogs}" id="tbLogsAuditoria" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.messageGroup1}" id="messageGroup1"
											styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.btnGuardar_action}"
											binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.btnCancelar_action}"
											binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
						<br /> <br /> <br />
					</div>
					<ui:hiddenField binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
