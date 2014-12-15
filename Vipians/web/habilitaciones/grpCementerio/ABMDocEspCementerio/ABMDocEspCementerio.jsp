<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.head1}" id="head1">
				<ui:link binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.link1}" id="link1"
					url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio";

					function cargarComportamientoJQuery() {
						autoCompletarEnTextField("#form1:tfPersona", "persona", nombreBean, "setPersonaAutocompletar");

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

					function focusearTfPersona() {
						$("#form1\\:tfPersona").focus();
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
			<ui:body binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.body1}"
				focus="form1:btnSeleccionarPersonaFisica" id="body1" onLoad="parent.footer.location.reload(); Init();"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.stTitulo}" id="stTitulo"
									styleClass="tituloABM" text="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.lblPersona}" id="lblPersona"
											styleClass="label" for="tfPersona" text="Persona" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.tfPersona}" columns="40"
											id="tfPersona"
											styleClass="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.hayPersona ? 'textFieldDisabled' : 'textField'}"
											disabled="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.hayPersona}" />
										<ui:button action="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.btnSeleccionarPersonaFisica_action}"
											binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.btnSeleccionarPersonaFisica}" escape="false"
											id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Fisica" />
										<ui:button action="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.btnSeleccionarPersonaJuridica_action}"
											binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.btnSeleccionarPersonaJuridica}" escape="false"
											id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar Persona Juridica" />
										<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersona" title="Limpiar"
											binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.btnLimpiarPersona}"
											action="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.btnLimpiarPersona_action}"
											styleClass="buttonLimpiarAjax" oncomplete="cargarComportamientoJQuery(); focusearTfPersona();" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.lblDomicilio}" id="lblDomicilio"
											styleClass="label" text="Domicilio Postal" />
									</td>
									<td nowrap="nowrap">
										<ui:button action="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.btnSeleccionarDomicilioPostal_action}"
											binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.btnSeleccionarDomicilioPostal}" escape="false"
											id="btnSeleccionarDomicilioPostal" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Agregar/Modificar" />
										<a4j:commandButton id="btnLimpiarDomicilioPostal" reRender="form1:tfPersona" title="Limpiar"
											binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.btnLimpiarDomicilioPostal}"
											action="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.btnLimpiarDomicilioPostal_action}"
											styleClass="buttonLimpiarAjax" />
										<ui:button
											action="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.btnSeleccionarDomicilioSolicitante_action}"
											binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.btnSeleccionarDomicilioSolicitante}"
											id="btnSeleccionarDomicilioSolicitante" mini="true" styleClass="button" text="Del Solicitante" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap" style="height: 17px"></td>
									<td colspan="1">
										<ui:staticText binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.stDomicilioPostal}" escape="false"
											id="stDomicilioPostal" styleClass="staticText" />
									</td>
								</tr>
								<tr>
									<td>
										<ui:label binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.lblParcelasCementerio}"
											id="lblParcelasCementerio" styleClass="label57" text="Parcelas Cementerio" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false"
											binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.tablaParcelasCementerio}"
											id="tablaParcelasCementerio">
											<script>
												<![CDATA[
												/* ----- Functions for Table Preferences Panel ----- */
												/*
												 * Toggle the table preferences panel open or closed
												 */
												function togglePreferencesPanel() {
													var table = document.getElementById("form1:tablaParcelasCementerio");
													table.toggleTblePreferencesPanel();
												}
												/* ----- Functions for Filter Panel ----- */
												/*
												 * Return true if the filter menu has actually changed,
												 * so the corresponding event should be allowed to continue.
												 */
												function filterMenuChanged() {
													var table = document.getElementById("form1:tablaParcelasCementerio");
													return table.filterMenuChanged();
												}
												/*
												 * Toggle the custom filter panel (if any) open or closed.
												 */
												function toggleFilterPanel() {
													var table = document.getElementById("form1:tablaParcelasCementerio");
													return table.toggleTableFilterPanel();
												}
												/* ----- Functions for Table Actions ----- */
												/*
												 * Initialize all rows of the table when the state
												 * of selected rows changes.
												 */
												function initAllRows() {
													var table = document.getElementById("form1:tablaParcelasCementerio");
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
													var table = document.getElementById("form1:tablaParcelasCementerio");
													table.selectGroupRows(rowGroupId, selected);
												}
												/*
												 * Disable all table actions if no rows have been selected.
												 */
												function disableActions() {
													// Determine whether any rows are currently selected
													var table = document.getElementById("form1:tablaParcelasCementerio");
													var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
													// Set disabled state for top actions
													document.getElementById("form1:tablaParcelasCementerio:tableActionsTop:deleteTop").setDisabled(
															disabled);
													// Set disabled state for bottom actions
													document.getElementById("form1:tablaParcelasCementerio:tableActionsBottom:deleteBottom")
															.setDisabled(disabled);
												}
												]]>
											</script>
											<ui:tableRowGroup binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.tableRowGroup1}"
												id="tableRowGroup1" sourceData="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.ldpParcelasCementerio}"
												sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.tableColumn1}"
													id="tableColumn1" valign="middle" width="10">
													<ui:radioButton binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.radioButton1}"
														id="radioButton1" label="" name="buttonGroup"
														selected="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.RBSelected}"
														selectedValue="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.currentRow}" />
												</ui:tableColumn>
												<!--<ui:tableColumn
													binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.tcConcesion}"
													headerText="Concesión" id="tcConcesion" sort="concesion">
													<ui:staticText
														binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.stConcesion}"
														id="stConcesion" text="#{currentRow.value['concesion']}" />
												</ui:tableColumn>-->
												<ui:tableColumn binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.tcSuperficie}"
													headerText="Superficie" id="tcSuperficie" sort="superficie">
													<ui:staticText binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.stSuperficie}" id="stSuperficie"
														text="#{currentRow.value['superficie']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.tcTipoSepultura}"
													headerText="Tipo de Sepultura" id="tcTipoSepultura" sort="tipoSepultura">
													<ui:staticText binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.stTipoSepultura}"
														id="stTipoSepultura" text="#{currentRow.value['registroAlicuota']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.groupPanel1}" id="groupPanel1">
													<ui:button action="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.btnAgregarParcelaCementerio_action}"
														binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.btnAgregarParcelaCementerio}"
														id="btnAgregarParcelaCementerio" styleClass="button" text="Agregar" />
													<ui:staticText binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.staticText1}" escape="false"
														id="staticText1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<a4j:commandButton
														action="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.btnQuitarParcelaCementerio_action}"
														binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.btnQuitarParcelaCementerio}"
														id="btnQuitarParcelaCementerio" value="Quitar" styleClass="btnAjax" reRender="tablaParcelasCementerio"
														onmousedown="reemplazarClickConConfirmacion(this, '');" />
													<a4j:commandButton
														action="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.btnQuitarTodosParcelaCementerio_action}"
														binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.btnQuitarTodosParcelaCementerio}"
														id="btnQuitarTodosParcelaCementerio" value="Quitar todos" styleClass="btnAjax" reRender="tablaParcelasCementerio"
														onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea quitar todos los elementos de esta lista?');" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:staticText binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.stDocsGeneradoresDeuda}"
											id="stDocsGeneradoresDeuda" styleClass="label2" text="Documentos Generadores de Deuda" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false"
											binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.tablaDocsGeneradoresDeuda}"
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
											<ui:tableRowGroup binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.trgDocsGeneradoresDeuda}"
												id="trgDocsGeneradoresDeuda"
												sourceData="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.ldpDocsGeneradoresDeuda}"
												sourceVar="currentRow">
												<ui:tableColumn binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.tcAnio}" headerText="Año"
													id="tcAnio" sort="anio">
													<ui:staticText binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.stAnio}" id="stAnio"
														text="#{currentRow.value['anio']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.tcPlan}" headerText="Plan"
													id="tcPlan" sort="plan">
													<ui:staticText binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.stPlan}" id="stPlan"
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
													<ui:panelGroup binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.panelAtributoDinamico}"
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
										<ui:table binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.tablaLogsLiquidacion}"
											id="tbLogsLiquidacion" clearSortButton="true" width="770px">
											<ui:tableRowGroup binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.tableRowGroup}"
												id="tableRowGroup" emptyDataMsg="Ningún registro encontrado."
												sourceData="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.ldpLogsLiquidacion}" sourceVar="currentRow">
												<ui:tableColumn binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.tableColumn2}" id="tableColumn2"
													headerText="Usuario" sort="usuario">
													<ui:staticText binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.staticText2}" id="staticText2"
														text="#{currentRow.value['usuario']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.tableColumn3}" id="tableColumn3"
													headerText="Evento" sort="evento">
													<ui:staticText binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.staticText3}" id="staticText3"
														text="#{currentRow.value['evento']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.tableColumn4}" id="tableColumn4"
													headerText="Cuota" sort="cuota">
													<ui:staticText binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.staticText4}" id="staticText4"
														text="#{currentRow.value['cuota']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.tableColumn5}" id="tableColumn5"
													headerText="Fecha y Hora" sort="fecha">
													<ui:staticText binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.staticText5}" id="staticText5"
														text="#{currentRow.value['fecha']}"
														converter="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.dateTimeConverter1}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.tableColumn6}" id="tableColumn6"
													headerText="Monto Total" sort="montoTotalLiquidacion">
													<ui:staticText binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.staticText6}" id="staticText6"
														text="#{currentRow.value['montoTotalLiquidacion']}"
														converter="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.numberConverter1}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.tableColumn7}" id="tableColumn7"
													headerText="Comentario">
													<ui:staticText binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.staticText7}" id="staticText7"
														text="#{currentRow.value['comentario']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="filter">
												<ui:panelGroup binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.gpFiltroTablaLogsLiquidacion}"
													id="gpFiltroTablaLogsLiquidacion">
													<ui:textField binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.tfFiltrarUsuarioLogLiq}"
														id="tfFiltrarUsuarioLogLiq" styleClass="textField" />
													<ui:dropDown binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.ddFiltrarEventoLogLiq}"
														id="ddFiltrarEventoLogLiq" styleClass="textField"
														items="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.ddFiltrarEventoLogLiqOptions.options}" />
													<ui:textField id="tfFiltrarFechaDesdeLogLiq"
														binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.tfFiltrarFechaDesdeLogLiq}"
														styleClass="textField" columns="10" />
													<ui:textField binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.tfFiltrarFechaHastaLogLiq}"
														id="tfFiltrarFechaHastaLogLiq" styleClass="textField" columns="10" />
													<ui:staticText id="staticTextSeparadora1" styleClass="barraSeparadoraVertical" escape="false" text="|" />
													<a4j:commandButton binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.btnFiltrarLogLiq}"
														action="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.btnFiltrarLogLiq_action}" id="btnFiltrarLogLiq"
														styleClass="btnAjax" reRender="tbLogsLiquidacion" oncomplete="cargarComportamientoJQuery();" value="Buscar" />
													<a4j:commandButton binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.btnLimpiarFiltradoLogLiq}"
														action="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.btnLimpiarFiltradoLogLiq_action}"
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
										<ui:label binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.lblComentarioLogAuditoria}"
											id="lblComentarioLogAuditoria" />
									</td>
									<td>
										<ui:textArea binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.taComentarioLogAuditoria}"
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
										<ui:table binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.tablaLogs}" id="tbLogsAuditoria" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.messageGroup1}"
											id="messageGroup1" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.btnGuardar_action}"
											binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.stSeparador}" escape="false"
											id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.btnCancelar_action}"
											binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.btnCancelar}" id="btnCancelar"
											styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
						<br /> <br /> <br />
					</div>
					<ui:hiddenField binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
