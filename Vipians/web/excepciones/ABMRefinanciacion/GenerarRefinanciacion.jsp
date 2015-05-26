<?xml version="1.0" encoding="UTF-8"?>
<!-- © Copyright 2015, CoDeSoft Todos los derechos reservados. -->
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.page1}" id="page1">
			<ui:html binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.html1}" id="html1">
			<ui:head binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.head1}" id="head1">
				<ui:link binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "excepciones$ABMRefinanciacion$GenerarRefinanciacion";

					function cargarComportamientoJQuery() {
						autoCompletarEnTextField("#form1:tsRefinanciacion:one:tfContribuyente", "persona", nombreBean, "setPersonaAutocompletar",
								null, "#form1:tsRefinanciacion:one:btnVerObligaciones");
					}

					function focusearTfPersonaSeleccionada() {
						$("#form1\\:tsRefinanciacion\\:one\\:tfContribuyente").focus();
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid"
				onKeyUp="eventoByEscape(event,'btnVolver')">
				<ui:form binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="2">
										<ui:tabSet id="tsRefinanciacion" binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.tabSet1}" mini="true" lite="true">
											<ui:tab id="one" text="Refinanciación">
												<table align="center" width="70%">
													<br />
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.label4}" for="tfContribuyente" id="label4"
																styleClass="label" text="Contribuyente" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.tfContribuyente}" columns="40" id="tfContribuyente"
																styleClass="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.hayPersona ? 'textFieldDisabled' : 'textField'}"
																disabled="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.hayPersona}" />
															<ui:button action="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.btnSeleccionarPersonaFisica_action}"
																binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.btnSeleccionarPersonaFisica}" escape="false"
																id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Física" />
															<ui:button action="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.btnSeleccionarPersonaJuridica_action}"
																binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.btnSeleccionarPersonaJuridica}" escape="false"
																id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar Persona Jurídica" />
															<a4j:commandButton id="btnLimpiarPersona" reRender="tfContribuyente, table1, table2" title="Limpiar"
																action="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.btnLimpiarPersona_action}" styleClass="buttonLimpiarAjax"
																oncomplete="cargarComportamientoJQuery(); focusearTfPersonaSeleccionada();" />
															<a4j:commandButton id="btnVerObligaciones" reRender="table1, table2" style="visibility: hidden;"
																action="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.cargarObligaciones}" oncomplete="cargarComportamientoJQuery();" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.label2}" for="ddTipoObligacion" id="label2"
																styleClass="label" text="Tipo de Obligación" rendered="false" />
														</td>
														<td>
															<ui:dropDown binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.ddTipoObligacion}" id="ddTipoObligacion"
																immediate="true" items="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.ddTipoObligacionDefaultOptions.options}"
																onChange="common_timeoutSubmitForm(this.form, 'div:table:tbody:tr:td:ddTipoObligacion');" styleClass="textField"
																valueChangeListener="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.valueChangeEventDDTipoObligacion}" rendered="false" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap"></td>
														<td align="right" nowrap="nowrap"></td>
													</tr>
													<tr>
														<td colspan="2">
															<hr />
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="2">
															<ui:label binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.label1}" id="label1" styleClass="label2"
																text="Obligaciones del Contribuyente" />
														</td>
													</tr>
													<tr>
														<td colspan="2">
															<ui:table augmentTitle="false" binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.table1}" id="table1" width="10">
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
																		document.getElementById("form1:table1:tableActionsTop:deleteTop")
																				.setDisabled(disabled);
																		// Set disabled state for bottom actions
																		document.getElementById("form1:table1:tableActionsBottom:deleteBottom")
																				.setDisabled(disabled);
																	}
																	]]>
																</script>
																<ui:tableRowGroup binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.tableRowGroup1}" id="tableRowGroup1"
																	selected="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.currentRowSelected}"
																	sourceData="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.ldpObligaciones}" sourceVar="currentRow">
																	<ui:tableColumn align="center" binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.tableColumn7}" id="tableColumn7"
																		valign="middle" width="10">
																		<ui:checkbox binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.checkbox4}" id="checkbox4"
																			selected="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.selected}"
																			selectedValue="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.selectedValue}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.tableColumn2}" headerText="Nº Tramite"
																		id="tableColumn2" noWrap="true" sort="numeroTramite">
																		<ui:staticText binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.staticText1}" id="staticText1"
																			text="#{currentRow.value['numeroTramite']}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.tableColumn1}" headerText="Documento"
																		id="tableColumn1" sort="documentoEspecializado">
																		<ui:staticText binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.staticText2}" id="staticText2"
																			text="#{currentRow.value['documentoEspecializado']}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.tableColumn3}" headerText="Estado"
																		id="tableColumn3" sort="estado">
																		<ui:staticText binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.staticText3}" id="staticText3"
																			text="#{currentRow.value['estado']}" />
																	</ui:tableColumn>
																</ui:tableRowGroup>
																<f:facet name="actionsBottom">
																	<ui:panelGroup binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.groupPanel1}" id="groupPanel1">
																		<a4j:commandButton id="btnVerPeriodos" reRender="table2" value="Ver Períodos Adeudados"
																			action="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.btnVerPeriodos_action}" styleClass="btnAjax"
																			oncomplete="cargarComportamientoJQuery();" />
																	</ui:panelGroup>
																</f:facet>
															</ui:table>
														</td>
													</tr>
													<tr>
														<td colspan="2">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="2">
															<ui:label binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.label3}" id="label3" styleClass="label2"
																text="Períodos Adeudados" />
														</td>
													</tr>
													<tr>
														<td colspan="2">
															<ui:table augmentTitle="false" binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.table2}" id="table2" width="72">
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
																		document.getElementById("form1:table1:tableActionsTop:deleteTop")
																				.setDisabled(disabled);
																		// Set disabled state for bottom actions
																		document.getElementById("form1:table1:tableActionsBottom:deleteBottom")
																				.setDisabled(disabled);
																	}
																	]]>
																</script>
																<ui:tableRowGroup binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.tableRowGroup2}" id="tableRowGroup2"
																	selected="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.currentRowSelectedTabla2}"
																	sourceData="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.ldpPeriodosAdeudados}" sourceVar="currentRow">
																	<ui:tableColumn align="center" binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.tableColumn15}"
																		id="tableColumn15" valign="middle" width="10">
																		<ui:checkbox binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.checkbox1}" id="checkbox1"
																			selected="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.selectedTabla2}"
																			selectedValue="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.selectedValueTabla2}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.tableColumn16}" headerText="Período"
																		id="tableColumn16" noWrap="true" sort="nombre">
																		<ui:staticText binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.staticText11}" id="staticText11"
																			text="#{currentRow.value['nombre']}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.tableColumn12}" headerText="Monto"
																		id="tableColumn12" sort="monto">
																		<ui:staticText binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.staticText10}"
																			converter="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.numberConverter1}" id="staticText10"
																			text="#{currentRow.value['monto']}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.tableColumn9}" headerText="Interés"
																		id="tableColumn9" sort="interes">
																		<ui:staticText binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.staticText7}"
																			converter="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.numberConverter1}" id="staticText7"
																			text="#{currentRow.value['interes']}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.tableColumn4}" headerText="Recargo"
																		id="tableColumn4" sort="recargo">
																		<ui:staticText binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.staticText4}"
																			converter="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.numberConverter1}" id="staticText4"
																			text="#{currentRow.value['recargo']}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.tableColumn5}" headerText="Multas"
																		id="tableColumn5" sort="multas">
																		<ui:staticText binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.staticText5}"
																			converter="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.numberConverter1}" id="staticText5"
																			text="#{currentRow.value['multas']}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.tableColumn11}" headerText="Fecha Vencimiento"
																		id="tableColumn11" sort="fechaVencimiento">
																		<ui:staticText binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.staticText9}"
																			converter="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.dateTimeConverter}" id="staticText9"
																			text="#{currentRow.value['fechaVencimiento']}" />
																	</ui:tableColumn>
																</ui:tableRowGroup>
																<f:facet name="actionsBottom">
																	<ui:panelGroup binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.groupPanel2}" id="groupPanel2">
																		<ui:button action="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.btnArmarPlanDePago_action}"
																			binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.btnArmarPlanDePago}" id="btnArmarPlanDePago"
																			styleClass="button" text="Armar Plan de Pago" />
																	</ui:panelGroup>
																</f:facet>
															</ui:table>
														</td>
													</tr>
												</table>
											</ui:tab>
											<ui:tab id="two" text="Refinanciar Auditoría Tributaria" rendered="false">
												<table align="center" width="70%">
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.label5}" for="tfAuditoriaTributaria" id="label5"
																styleClass="label" text="Auditoria Tributaria" />
														</td>
														<td>
															<ui:textField binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.tfAuditoriaTributaria}" columns="60" disabled="true"
																id="tfAuditoriaTributaria" maxLength="10" styleClass="textField" />
															<ui:button action="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.btnSeleccionarAuditoriaTributaria_action}"
																binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.btnSeleccionarAuditoriaTributaria}" escape="false"
																id="btnSeleccionarAuditoriaTributaria" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<ui:button action="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.btnLimpiarAuditoriaTributaria_action}"
																binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.btnLimpiarAuditoriaTributaria}" escape="false"
																id="btnLimpiarAuditoriaTributaria" mini="true" styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar" />
														</td>
													</tr>
													<tr>
														<td colspan="2">
															<ui:label binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.label6}" id="label6" styleClass="label2"
																text="Períodos Adeudados" />
														</td>
													</tr>
													<tr>
														<td colspan="2">
															<ui:table augmentTitle="false" binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.table3}" id="table3" width="72">
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
																		document.getElementById("form1:table3:tableActionsTop:deleteTop")
																				.setDisabled(disabled);
																		// Set disabled state for bottom actions
																		document.getElementById("form1:table3:tableActionsBottom:deleteBottom")
																				.setDisabled(disabled);
																	}
																	]]>
																</script>
																<ui:tableRowGroup binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.tableRowGroup3}" id="tableRowGroup3"
																	sourceData="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.ldpPeriodosAdeudadosAuditoria}" sourceVar="currentRow">
																	<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.tableColumn17}" headerText="Período"
																		id="tableColumn17" noWrap="true" sort="nombre">
																		<ui:staticText binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.staticText12}" id="staticText12"
																			text="#{currentRow.value['nombre']}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.tableColumn18}" headerText="Monto"
																		id="tableColumn18" sort="monto">
																		<ui:staticText binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.staticText13}"
																			converter="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.numberConverter1}" id="staticText13"
																			text="#{currentRow.value['monto']}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.tableColumn19}" headerText="Interés"
																		id="tableColumn19" sort="interes">
																		<ui:staticText binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.staticText14}"
																			converter="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.numberConverter1}" id="staticText14"
																			text="#{currentRow.value['interes']}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.tableColumn20}" headerText="Recargo"
																		id="tableColumn20" sort="recargo">
																		<ui:staticText binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.staticText15}"
																			converter="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.numberConverter1}" id="staticText15"
																			text="#{currentRow.value['recargo']}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.tableColumn21}" headerText="Multas"
																		id="tableColumn21" sort="multas">
																		<ui:staticText binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.staticText16}"
																			converter="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.numberConverter1}" id="staticText16"
																			text="#{currentRow.value['multas']}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.tableColumn22}" headerText="Fecha Vencimiento"
																		id="tableColumn22" sort="fechaVencimiento">
																		<ui:staticText binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.staticText17}"
																			converter="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.dateTimeConverter}" id="staticText17"
																			text="#{currentRow.value['fechaVencimiento']}" />
																	</ui:tableColumn>
																</ui:tableRowGroup>
																<f:facet name="actionsTop">
																	<ui:panelGroup binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.groupPanel3}" id="groupPanel3">
																		<ui:button action="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.btnArmarPlanDePagoAuditoria_action}"
																			binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.btnArmarPlanDePagoAuditoria}" id="btnArmarPlanDePagoAuditoria"
																			styleClass="button" text="Armar Plan de Pago" />
																	</ui:panelGroup>
																</f:facet>
															</ui:table>
														</td>
													</tr>
												</table>
											</ui:tab>
										</ui:tabSet>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:messageGroup binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.messageGroup1}" id="messageGroup1"
											styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="2" nowrap="nowrap">
										<ui:button action="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.btnCancelar_action}"
											binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.hidIdPagina}" id="hidIdPagina"
						text="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.idPagina}" />
					<ui:hiddenField binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.idSubSesion}" />
					<ui:script binding="#{excepciones$ABMRefinanciacion$GenerarRefinanciacion.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>