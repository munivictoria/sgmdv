<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.head1}" id="head1">
				<ui:link binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS";

					function cargarComportamientoJQuery() {
						calendarioEnTextField("#form1:tfFechaInicio");
						calendarioEnTextField("#form1:tfFechaCese");
						autoCompletarEnTextField("#form1:tfPersona", "persona", nombreBean, "setPersonaAutocompletar");
						autoCompletarEnTextField("#form1:tfContador", "persona", nombreBean, "setContadorAutocompletar");

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

					function focusearTfContador() {
						$("#form1\\:tfContador").focus();
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
			<ui:body binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.body1}" focus="form1:tfNumeroInscripcion" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.label15}" for="tfNumeroInscripcion" id="label15"
											styleClass="label" text="Número de Inscripción" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tfNumeroInscripcion}" id="tfNumeroInscripcion"
											styleClass="textField" columns="10" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.label2}" for="tfFechaInicio" id="label2"
											styleClass="label" text="Inicio de Actividades" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tfFechaInicio}" id="tfFechaInicio"
											styleClass="textField" columns="10" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.label5}" for="tfNombre" id="label5" styleClass="label"
											text="Número de Expediente" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tfNombre}" columns="40" id="tfNombre"
											styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.label3}" for="tfFechaCese" id="label3" styleClass="label"
											text="Cese de Actividades" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tfFechaCese}" id="tfFechaCese" styleClass="textField"
											columns="10" />
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
										<ui:label binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.label16}" for="tfPersona" id="label16" styleClass="label"
											text="Persona Solicitante" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tfPersona}" columns="40" id="tfPersona"
											styleClass="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.hayPersona ? 'textFieldDisabled' : 'textField'}"
											disabled="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.hayPersona}" />
										<ui:button action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnSeleccionarPersonaFisica_action}"
											binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnSeleccionarPersonaFisica}" id="btnSeleccionarPersonaFisica"
											mini="true" styleClass="button" text="PF" />
										<ui:button action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnSeleccionarPersonaJuridica_action}"
											binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnSeleccionarPersonaJuridica}" id="btnSeleccionarPersonaJuridica"
											mini="true" styleClass="button" text="PJ" />
										<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersona" title="Limpiar"
											binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnLimpiarPersona}"
											action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnLimpiarPersona_action}" styleClass="buttonLimpiarAjax"
											oncomplete="cargarComportamientoJQuery(); focusearTfPersona();" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.label17}" for="tfContador" id="label17" styleClass="label"
											text="Contador" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tfContador}" columns="40" id="tfContador"
											styleClass="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.hayContador ? 'textFieldDisabled' : 'textField'}"
											disabled="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.hayContador}" />
										<ui:button action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnSeleccionarContador_action}"
											binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnSeleccionarContador}" id="btnSeleccionarContador" mini="true"
											styleClass="button" text="PF" />
										<ui:button action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnSeleccionarPersonaJuridicaContador_action}"
											binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnSeleccionarPersonaJuridicaContador}"
											id="btnSeleccionarPersonaJuridicaContador" mini="true" styleClass="button" text="PJ" />
										<a4j:commandButton id="btnLimpiarContador" reRender="form1:tfContador" title="Limpiar"
											binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnLimpiarContador}"
											action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnLimpiarContador_action}" styleClass="buttonLimpiarAjax"
											oncomplete="cargarComportamientoJQuery(); focusearTfContador();" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.label1}" id="label1" styleClass="label"
											text="Domicilio Postal" />
									</td>
									<td nowrap="nowrap">
										<ui:button action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnSeleccionarDomicilioPostal_action}"
											binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnSeleccionarDomicilioPostal}" escape="false"
											id="btnSeleccionarDomicilioPostal" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Agregar/Modificar" />
										<a4j:commandButton id="btnLimpiarDomicilioPostal" reRender="form1:stDomicilioPostal" title="Limpiar"
											binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnLimpiarDomicilioPostal}"
											action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnLimpiarDomicilioPostal_action}" styleClass="buttonLimpiarAjax" />
										<ui:button action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnSeleccionarDomicilioSolicitante_action}"
											binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnSeleccionarDomicilioSolicitante}"
											id="btnSeleccionarDomicilioSolicitante" mini="true" styleClass="button" text="Del Solicitante" />
										<ui:button action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnSeleccionarDomicilioContador_action}"
											binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnSeleccionarDomicilioSolicitanteContador}"
											id="btnSeleccionarDomicilioSolicitanteContador" mini="true" styleClass="button" text="Del Contador" />
									</td>
								</tr>
								<tr>
									<td></td>
									<td align="left" nowrap="nowrap">
										<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.stDomicilioPostal}" escape="false"
											id="stDomicilioPostal" styleClass="staticText" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.label6}" for="tfDenominacionEntidad" id="label6"
											styleClass="label" text="Denominación de la Entidad" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tfDenominacionEntidad}" columns="40"
											id="tfDenominacionEntidad" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<hr />
										<br />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.label29}" id="label29" styleClass="label2"
											text="Libretas Sanitarias" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.table5}" id="table5" width="359">
											<script>
												<![CDATA[
												/* ----- Functions for Table Preferences Panel ----- */
												/*
												 * Toggle the table preferences panel open or closed
												 */
												function togglePreferencesPanel() {
													var table = document.getElementById("form1:table5");
													table.toggleTblePreferencesPanel();
												}
												/* ----- Functions for Filter Panel ----- */
												/*
												 * Return true if the filter menu has actually changed,
												 * so the corresponding event should be allowed to continue.
												 */
												function filterMenuChanged() {
													var table = document.getElementById("form1:table5");
													return table.filterMenuChanged();
												}
												/*
												 * Toggle the custom filter panel (if any) open or closed.
												 */
												function toggleFilterPanel() {
													var table = document.getElementById("form1:table5");
													return table.toggleTableFilterPanel();
												}
												/* ----- Functions for Table Actions ----- */
												/*
												 * Initialize all rows of the table when the state
												 * of selected rows changes.
												 */
												function initAllRows() {
													var table = document.getElementById("form1:table5");
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
													var table = document.getElementById("form1:table5");
													table.selectGroupRows(rowGroupId, selected);
												}
												/*
												 * Disable all table actions if no rows have been selected.
												 */
												function disableActions() {
													// Determine whether any rows are currently selected
													var table = document.getElementById("form1:table5");
													var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
													// Set disabled state for top actions
													document.getElementById("form1:table5:tableActionsTop:deleteTop").setDisabled(disabled);
													// Set disabled state for bottom actions
													document.getElementById("form1:table5:tableActionsBottom:deleteBottom").setDisabled(disabled);
												}
												]]>
											</script>
											<ui:tableRowGroup binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableRowGroup5}" id="tableRowGroup5"
												sourceData="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.ldpLibretasSanitarias}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn20}" id="tableColumn20"
													valign="middle" width="10">
													<ui:radioButton binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.radioButton5}" id="radioButton5" label=""
														name="buttonGroup5" selected="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.RBSelected5}"
														selectedValue="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.currentRow5}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn21}" headerText="Número"
													id="tableColumn21" noWrap="true" sort="numeroLibretaSanitaria">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.staticText9}" escape="false" id="staticText9"
														text="#{currentRow.value['numeroLibretaSanitaria']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn28}" headerText="Persona"
													id="tableColumn28" noWrap="true" sort="nombrePersonaFisica">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.staticText10}" escape="false" id="staticText10"
														text="#{currentRow.value['nombrePersonaFisica']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.groupPanel4}" id="groupPanel4">
													<ui:button action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnAgregarLibretaSanitaria_action}"
														binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnAgregarLibretaSanitaria}" id="btnAgregarLibretaSanitaria"
														styleClass="button" text="Agregar" />
													<a4j:commandButton action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnQuitarLibretaSanitaria_action}"
														binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnQuitarLibretaSanitaria}" id="btnQuitarLibretaSanitaria"
														onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea Quitar la Libreta Sanitarial?');" value="Quitar"
														styleClass="btnAjax" reRender="table5" />
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
									<td colspan="4">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.label30}" id="label30" styleClass="label2" text="Rubros" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.table6}" id="table6" width="599">
											<script>
												<![CDATA[
												/* ----- Functions for Table Preferences Panel ----- */
												/*
												 * Toggle the table preferences panel open or closed
												 */
												function togglePreferencesPanel() {
													var table = document.getElementById("form1:table3");
													table.toggleTblePreferencesPanel();
												}
												/* ----- Functions for Filter Panel ----- */
												/*
												 * Return true if the filter menu has actually changed,
												 * so the corresponding event should be allowed to continue.
												 */
												function filterMenuChanged() {
													var table = document.getElementById("form1:table3");
													return table.filterMenuChanged();
												}
												/*
												 * Toggle the custom filter panel (if any) open or closed.
												 */
												function toggleFilterPanel() {
													var table = document.getElementById("form1:table3");
													return table.toggleTableFilterPanel();
												}
												/* ----- Functions for Table Actions ----- */
												/*
												 * Initialize all rows of the table when the state
												 * of selected rows changes.
												 */
												function initAllRows() {
													var table = document.getElementById("form1:table3");
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
													var table = document.getElementById("form1:table3");
													table.selectGroupRows(rowGroupId, selected);
												}
												/*
												 * Disable all table actions if no rows have been selected.
												 */
												function disableActions() {
													// Determine whether any rows are currently selected
													var table = document.getElementById("form1:table3");
													var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
													// Set disabled state for top actions
													document.getElementById("form1:table3:tableActionsTop:deleteTop").setDisabled(disabled);
													// Set disabled state for bottom actions
													document.getElementById("form1:table3:tableActionsBottom:deleteBottom").setDisabled(disabled);
												}
												]]>
											</script>
											<ui:tableRowGroup binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableRowGroup6}" id="tableRowGroup6"
												sourceData="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.ldpRubroSHPS}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn22}" id="tableColumn22"
													valign="middle" width="10">
													<ui:radioButton binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.radioButton6}" id="radioButton6" label=""
														name="buttonGroup6" selected="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.RBSelected6}"
														selectedValue="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.currentRow6}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn23}" headerText="Código"
													id="tableColumn23" sort="codigo">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.staticText17}" id="staticText17"
														text="#{currentRow.value['registroAlicuota'].codigo}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn24}" headerText="Descripción"
													id="tableColumn24" sort="nombre">
													<ui:textArea binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.textArea4}" columns="40" disabled="true"
														id="textArea4" styleClass="textFieldDisabled" text="#{currentRow.value['registroAlicuota'].nombre}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn25}" headerText="Alícuota"
													id="tableColumn25" sort="valor">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.staticText18}" id="staticText18"
														text="#{currentRow.value['registroAlicuota'].valor} #{currentRow.value['registroAlicuota'].simbolo}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn26}" headerText="Periodicidad"
													id="tableColumn26" sort="periodicidad">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.staticText19}" id="staticText19"
														text="#{currentRow.value['registroAlicuota'].periodicidad}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn27}" headerText="Mínimo"
													id="tableColumn27" sort="minimo">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.staticText20}" id="staticText20"
														text="#{currentRow.value['registroAlicuota'].minimo}" />
												</ui:tableColumn>
												<ui:tableColumn align="center" binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn29}"
													headerText="Principal" id="tableColumn29" valign="middle" width="10">
													<ui:radioButton binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.radioButton7}" id="radioButton7" label=""
														name="buttonGroup7" selected="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.RBPrincipalSelected}"
														selectedValue="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.principalRow}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.groupPanel5}" id="groupPanel5">
													<ui:button action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnAgregarRubro_action}"
														binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnAgregarRubro}" id="btnAgregarRubro" styleClass="button"
														text="Agregar" />
													<a4j:commandButton action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnQuitarRubro_action}"
														binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnQuitarRubro}" id="btnQuitarRubro" value="Quitar"
														styleClass="btnAjax" reRender="table6" onmousedown="reemplazarClickConConfirmacion(this, '');" />
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
									<td colspan="4">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.label7}" id="label7" styleClass="label2"
											text="Locales Comerciales" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.table1}" id="table1" width="359">
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
											<ui:tableRowGroup binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableRowGroup1}" id="tableRowGroup1"
												sourceData="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.ldpLocalComercialSHPS}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn1}" id="tableColumn1"
													valign="middle" width="10">
													<ui:radioButton binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.radioButton1}" id="radioButton1" label=""
														name="buttonGroup" selected="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.RBSelected}"
														selectedValue="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn2}"
													headerText="Nº Inscripción del Local" id="tableColumn2" noWrap="true" sort="numeroInscripcion">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.staticText2}" escape="false" id="staticText2"
														text="#{currentRow.value['numeroInscripcion']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn3}" headerText="Sup. Cubierta"
													id="tableColumn3" noWrap="true" sort="superficieCubiertaAfectada">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.staticText7}" escape="false" id="staticText7"
														text="#{currentRow.value['superficieCubiertaAfectada']}&amp;nbsp;m&lt;sup&gt;2&lt;/sup&gt;" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn13}" headerText="Sup. Semicubierta"
													id="tableColumn13" noWrap="true" sort="superficieSemicubiertaAfectada">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.staticText13}" escape="false" id="staticText13"
														text="#{currentRow.value['superficieSemicubiertaAfectada']}&amp;nbsp;m&lt;sup&gt;2&lt;/sup&gt;" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn14}" headerText="Descripción"
													id="tableColumn14" sort="descripcion">
													<ui:textArea binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.textArea1}" columns="40" disabled="true"
														id="textArea1" styleClass="textFieldDisabled" text="#{currentRow.value['descripcion']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.groupPanel3}" id="groupPanel3">
													<ui:button action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnAgregarLocalComercial_action}"
														binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnAgregarLocalComercial}" id="btnAgregarLocalComercial"
														styleClass="button" text="Agregar" />
													<ui:button action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnModificarLocalComercial_action}"
														binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnModificarLocalComercial}" disabled="true"
														id="btnModificarLocalComercial" rendered="false" styleClass="button" text="Modificar" />
													<a4j:commandButton action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnQuitarLocalComercial_action}"
														binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnQuitarLocalComercial}" id="btnQuitarLocalComercial"
														onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea Quitar el Local Comercial?');" value="Quitar"
														styleClass="btnAjax" reRender="tableActionsTop" />
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.staticText14}" escape="false" id="staticText14"
														rendered="false" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnConsultarLocalComercial_action}"
														binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnConsultarLocalComercial}" disabled="true"
														id="btnConsultarLocalComercial" rendered="false" styleClass="button" text="Consultar" />
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
									<td colspan="4">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.label27}" id="label27" styleClass="label2"
											text="Transportes Vehiculares" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.table2}" id="table2" width="479">
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
											<ui:tableRowGroup binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableRowGroup2}" id="tableRowGroup2"
												sourceData="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.ldpTransporteVehicularSHPS}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn11}" id="tableColumn11"
													valign="middle" width="10">
													<ui:radioButton binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.radioButton2}" id="radioButton2" label=""
														name="buttonGroup2" selected="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.RBSelected2}"
														selectedValue="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.currentRow2}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn4}"
													headerText="Nº Inscripción del Transporte" id="tableColumn4" noWrap="true" sort="numeroInscripcion">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.staticText5}" id="staticText5"
														text="#{currentRow.value['numeroInscripcion']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn17}" headerText="Vehículo"
													id="tableColumn17" noWrap="true" sort="vehiculo">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.staticText4}" id="staticText4"
														text="#{currentRow.value['vehiculo']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn5}" headerText="Fecha Alta"
													id="tableColumn5" noWrap="true" sort="fechaAlta">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.staticText15}"
														converter="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.dateTimeConverter1}" id="staticText15"
														text="#{currentRow.value['fechaAlta']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn6}" headerText="Fecha Baja"
													id="tableColumn6" noWrap="true" sort="fechaBaja">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.staticText16}"
														converter="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.dateTimeConverter1}" id="staticText16"
														text="#{currentRow.value['fechaBaja']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn15}" headerText="Descripción"
													id="tableColumn15" sort="descripcion">
													<ui:textArea binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.textArea2}" columns="40" disabled="true"
														id="textArea2" styleClass="textFieldDisabled" text="#{currentRow.value['descripcion']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.groupPanel1}" id="groupPanel1">
													<ui:button action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnAgregarTransporteVehicular_action}"
														binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnAgregarTransporteVehicular}"
														id="btnAgregarTransporteVehicular" styleClass="button" text="Agregar" />
													<ui:button action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnModificarTransporteVehicular_action}"
														binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnModificarTransporteVehicular}" disabled="true"
														id="btnModificarTransporteVehicular" rendered="false" styleClass="button" text="Modificar" />
													<a4j:commandButton action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnQuitarTransporteVehicular_action}"
														binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnQuitarTransporteVehicular}" id="btnQuitarTransporteVehicular"
														onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea Quitar el Transporte Vehicular?');" value="Quitar"
														styleClass="btnAjax" reRender="table2" />
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
									<td colspan="4">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.label8}" id="label8" styleClass="label2"
											text="Clausuras Temporarias" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.table4}" id="table4" width="480">
											<script>
												<![CDATA[
												/* ----- Functions for Table Preferences Panel ----- */
												/*
												 * Toggle the table preferences panel open or closed
												 */
												function togglePreferencesPanel() {
													var table = document.getElementById("form1:table4");
													table.toggleTblePreferencesPanel();
												}
												/* ----- Functions for Filter Panel ----- */
												/*
												 * Return true if the filter menu has actually changed,
												 * so the corresponding event should be allowed to continue.
												 */
												function filterMenuChanged() {
													var table = document.getElementById("form1:table4");
													return table.filterMenuChanged();
												}
												/*
												 * Toggle the custom filter panel (if any) open or closed.
												 */
												function toggleFilterPanel() {
													var table = document.getElementById("form1:table4");
													return table.toggleTableFilterPanel();
												}
												/* ----- Functions for Table Actions ----- */
												/*
												 * Initialize all rows of the table when the state
												 * of selected rows changes.
												 */
												function initAllRows() {
													var table = document.getElementById("form1:table4");
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
													var table = document.getElementById("form1:table4");
													table.selectGroupRows(rowGroupId, selected);
												}
												/*
												 * Disable all table actions if no rows have been selected.
												 */
												function disableActions() {
													// Determine whether any rows are currently selected
													var table = document.getElementById("form1:table4");
													var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
													// Set disabled state for top actions
													document.getElementById("form1:table4:tableActionsTop:deleteTop").setDisabled(disabled);
													// Set disabled state for bottom actions
													document.getElementById("form1:table4:tableActionsBottom:deleteBottom").setDisabled(disabled);
												}
												]]>
											</script>
											<ui:tableRowGroup binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableRowGroup4}" id="tableRowGroup4"
												sourceData="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.ldpClausuraSHPS}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn9}" id="tableColumn9"
													valign="middle" width="10">
													<ui:radioButton binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.radioButton4}" id="radioButton4" label=""
														name="buttonGroup4" selected="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.RBSelected4}"
														selectedValue="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.currentRow4}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn10}" headerText="Fecha Alta"
													id="tableColumn10" sort="fechaAlta">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.staticText1}"
														converter="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.dateTimeConverter1}" id="staticText1"
														text="#{currentRow.value['fechaAlta']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn12}" headerText="Fecha Baja"
													id="tableColumn12" sort="fechaBaja">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.staticText6}"
														converter="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.dateTimeConverter1}" id="staticText6"
														text="#{currentRow.value['fechaBaja']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn18}" headerText="Activa"
													id="tableColumn18" sort="clausuraActiva">
													<ui:checkbox binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.checkbox1}" disabled="true" id="checkbox1"
														selected="#{currentRow.value['clausuraActiva']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn19}" headerText="Descripción"
													id="tableColumn19" sort="descripcion">
													<ui:textArea binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.textArea3}" columns="40" disabled="true"
														id="textArea3" styleClass="textFieldDisabled" text="#{currentRow.value['descripcion']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.groupPanel2}" id="groupPanel2">
													<ui:button action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnAgregarClausura_action}"
														binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnAgregarClausura}" id="btnAgregarClausura" styleClass="button"
														text="Agregar" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.label28}" id="label28" rendered="false"
											styleClass="label2" text="Modificaciones" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.table3}" id="table3" rendered="false"
											width="158">
											<script>
												<![CDATA[
												/* ----- Functions for Table Preferences Panel ----- */
												/*
												 * Toggle the table preferences panel open or closed
												 */
												function togglePreferencesPanel() {
													var table = document.getElementById("form1:table3");
													table.toggleTblePreferencesPanel();
												}
												/* ----- Functions for Filter Panel ----- */
												/*
												 * Return true if the filter menu has actually changed,
												 * so the corresponding event should be allowed to continue.
												 */
												function filterMenuChanged() {
													var table = document.getElementById("form1:table3");
													return table.filterMenuChanged();
												}
												/*
												 * Toggle the custom filter panel (if any) open or closed.
												 */
												function toggleFilterPanel() {
													var table = document.getElementById("form1:table3");
													return table.toggleTableFilterPanel();
												}
												/* ----- Functions for Table Actions ----- */
												/*
												 * Initialize all rows of the table when the state
												 * of selected rows changes.
												 */
												function initAllRows() {
													var table = document.getElementById("form1:table3");
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
													var table = document.getElementById("form1:table3");
													table.selectGroupRows(rowGroupId, selected);
												}
												/*
												 * Disable all table actions if no rows have been selected.
												 */
												function disableActions() {
													// Determine whether any rows are currently selected
													var table = document.getElementById("form1:table3");
													var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
													// Set disabled state for top actions
													document.getElementById("form1:table3:tableActionsTop:deleteTop").setDisabled(disabled);
													// Set disabled state for bottom actions
													document.getElementById("form1:table3:tableActionsBottom:deleteBottom").setDisabled(disabled);
												}
												]]>
											</script>
											<ui:tableRowGroup binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableRowGroup3}" id="tableRowGroup3"
												sourceData="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.ldpLogModificacionesSHPS}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn7}" id="tableColumn7"
													rendered="false" valign="middle" width="10">
													<ui:radioButton binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.radioButton3}" id="radioButton3" label=""
														name="buttonGroup3" selected="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.RBSelected3}"
														selectedValue="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.currentRow3}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn8}" headerText="Número de Inscripción"
													id="tableColumn8" sort="numeroInscripcion">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.staticText8}" id="staticText8"
														text="#{currentRow.value['numeroInscripcion']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn16}" headerText="Fecha"
													id="tableColumn16" sort="fecha">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.staticText3}" id="staticText3"
														text="#{currentRow.value['fecha']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop" />
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.stDocsGeneradoresDeuda}" id="stDocsGeneradoresDeuda"
											styleClass="label2" text="Documentos Generadores de Deuda" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tablaDocsGeneradoresDeuda}"
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
											<ui:tableRowGroup binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.trgDocsGeneradoresDeuda}"
												id="trgDocsGeneradoresDeuda" sourceData="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.ldpDocsGeneradoresDeuda}"
												sourceVar="currentRow">
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tcAnio}" headerText="Año" id="tcAnio" sort="anio">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.stAnio}" id="stAnio"
														text="#{currentRow.value['anio']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tcPlan}" headerText="Plan elegido" id="tcPlan"
													sort="plan">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.stPlan}" id="stPlan"
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
													<ui:panelGroup binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.panelAtributoDinamico}" id="panelAtributoDinamico">
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
										<ui:table binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tablaLogsLiquidacion}" id="tbLogsLiquidacion"
											clearSortButton="true" width="770px">
											<ui:tableRowGroup binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableRowGroup}" id="tableRowGroup"
												emptyDataMsg="Ningún registro encontrado." sourceData="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.ldpLogsLiquidacion}"
												sourceVar="currentRow">
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn30}" id="tableColumn30"
													headerText="Usuario" sort="usuario">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.staticText30}" id="staticText30"
														text="#{currentRow.value['usuario']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn31}" id="tableColumn31"
													headerText="Evento" sort="evento">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.staticText31}" id="staticText31"
														text="#{currentRow.value['evento']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn32}" id="tableColumn32"
													headerText="Cuota" sort="cuota">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.staticText32}" id="staticText32"
														text="#{currentRow.value['cuota']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn33}" id="tableColumn33"
													headerText="Fecha y Hora" sort="fecha">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.staticText33}" id="staticText33"
														text="#{currentRow.value['fecha']}" converter="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.dateTimeConverter2}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn34}" id="tableColumn34"
													headerText="Monto Total" sort="montoTotalLiquidacion">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.staticText34}" id="staticText34"
														text="#{currentRow.value['montoTotalLiquidacion']}"
														converter="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.numberConverter2}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tableColumn35}" id="tableColumn35"
													headerText="Comentario">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.staticText35}" id="staticText35"
														text="#{currentRow.value['comentario']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="filter">
												<ui:panelGroup binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.gpFiltroTablaLogsLiquidacion}"
													id="gpFiltroTablaLogsLiquidacion">
													<ui:textField binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tfFiltrarUsuarioLogLiq}"
														id="tfFiltrarUsuarioLogLiq" styleClass="textField" />
													<ui:dropDown binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.ddFiltrarEventoLogLiq}" id="ddFiltrarEventoLogLiq"
														styleClass="textField" items="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.ddFiltrarEventoLogLiqOptions.options}" />
													<ui:textField id="tfFiltrarFechaDesdeLogLiq"
														binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tfFiltrarFechaDesdeLogLiq}" styleClass="textField" columns="10" />
													<ui:textField binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tfFiltrarFechaHastaLogLiq}"
														id="tfFiltrarFechaHastaLogLiq" styleClass="textField" columns="10" />
													<ui:staticText id="staticTextSeparadora1" styleClass="barraSeparadoraVertical" escape="false" text="|" />
													<a4j:commandButton binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnFiltrarLogLiq}"
														action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnFiltrarLogLiq_action}" id="btnFiltrarLogLiq"
														styleClass="btnAjax" reRender="tbLogsLiquidacion" oncomplete="cargarComportamientoJQuery();" value="Buscar" />
													<a4j:commandButton binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnLimpiarFiltradoLogLiq}"
														action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnLimpiarFiltradoLogLiq_action}" id="btnLimpiarFiltradoLogLiq"
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
										<ui:label binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
									</td>
									<td>
										<ui:textArea binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.taComentarioLogAuditoria}"
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
										<ui:table binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.tablaLogs}" id="tbLogsAuditoria" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.messageGroup}" id="messageGroup1"
											styleClass="grupoMsg" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnGuardar_action}"
											binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnCancelar_action}"
											binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
						<br /> <br /> <br />
					</div>
					<ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
