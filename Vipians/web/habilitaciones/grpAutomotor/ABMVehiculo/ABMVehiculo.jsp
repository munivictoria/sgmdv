<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.head1}" id="head1">
				<ui:link binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo";

					function cargarComportamientoJQuery() {
						autoCompletarEnTextField("#form1:tabSet1:one:tfModelo", "modeloVehiculo", nombreBean, "setModeloVehiculoAutocompletar");
						autoCompletarEnTextField("#form1:tabSet1:two:tablePropietarios:tfPropietario", "persona", nombreBean,
								"setPersonaAutocompletar", "#form1:tabSet1:two:tablePropietarios:btnAgregarPropietario");
						calendarioEnTextField("#form1:tabSet1:two:tfFechaInscripcion");
					}

					function focusearTfModeloVehiculo() {
						$("#form1\\:tabSet1\\:one\\:tfModelo").focus();
					}

					function focusearTfPropietario() {
						$("#form1\\:tabSet1\\:two\\:tablePropietarios\\:tfPropietario").focus();
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.body1}" focus="form1:tabSet1:one:tfPatente" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td align="left" colspan="4">
										<ui:tabSet binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.tabSet1}" id="tabSet1" mini="true" lite="true">
											<ui:tab id="one" binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.tabOne}" text="Vehículo">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.lblPatente}" for="tfPatente" id="lblPatente"
																styleClass="label" text="Patente" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.tfPatente}" id="tfPatente" maxLength="10"
																styleClass="textField" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.lblModelo}" for="tfModelo" id="lblModelo"
																styleClass="label" text="Modelo" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.tfModelo}" columns="40" id="tfModelo"
																styleClass="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.hayModelo ? 'textFieldDisabled' : 'textField'}"
																disabled="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.hayModelo}" />
															<ui:button action="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.btnSeleccionarModelo_action}"
																binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.btnSeleccionarModelo}" escape="false"
																id="btnSeleccionarModelo" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" />
															<a4j:commandButton id="btnLimpiarModelo" reRender="tfModelo" title="Limpiar"
																binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.btnLimpiarModelo}"
																action="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.btnLimpiarModelo_action}" styleClass="buttonLimpiarAjax"
																oncomplete="cargarComportamientoJQuery(); focusearTfModeloVehiculo();" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.lblAnio}" for="tfAnio" id="lblAnio"
																styleClass="label" text="Año" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.tfAnio}" id="tfAnio" maxLength="4"
																onKeyPress="return ValidarNum(event)" styleClass="textField" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.lblPeso}" for="tfPeso" id="lblPeso"
																styleClass="label" text="Peso" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.tfPeso}" id="tfPeso" maxLength="10"
																onKeyPress="return ValidarFloat(event, this)" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.lblCapacidad}" for="tfCapacidad" id="lblCapacidad"
																styleClass="label" text="Capacidad" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.tfCapacidad}" id="tfCapacidad" maxLength="10"
																onKeyPress="return ValidarFloat(event, this)" styleClass="textField" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.lblChasis}" for="tfChasis" id="lblChasis"
																styleClass="label" text="Chasis" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.tfChasis}" id="tfChasis" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.lblMotor}" for="tfMotor" id="lblMotor"
																styleClass="label" text="Motor" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.tfMotor}" id="tfMotor" styleClass="textField" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.lblDescripcion}" id="lblDescripcion"
																styleClass="label" text="Descripción" />
														</td>
														<td>
															<ui:textArea binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.taDescripcion}" columns="40" id="taDescripcion"
																rows="5" styleClass="textField" maxLength="970" />
														</td>
													</tr>
													<tr>
														<td>
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4" style="padding-left: 1px; padding-right: 1px;">
															<ui:panelGroup binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.groupPanelObligaciones}"
																id="groupPanelObligaciones">
																<div class="div" style="width: 780px; height: 15px;">Obligaciones</div>
																<table class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 192px;">
																	<tbody>
																		<tr>
																			<td>
																				<ui:table augmentTitle="false" binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.tableObligaciones}"
																					id="tableObligaciones" width="108">
																					<script>
																						<![CDATA[
																						/* ----- Functions for Table Preferences Panel ----- */
																						/*
																						 * Toggle the table preferences panel open or closed
																						 */
																						function togglePreferencesPanel() {
																							var table = document
																									.getElementById("form1:tableObligaciones");
																							table.toggleTblePreferencesPanel();
																						}
																						/* ----- Functions for Filter Panel ----- */
																						/*
																						 * Return true if the filter menu has actually changed,
																						 * so the corresponding event should be allowed to continue.
																						 */
																						function filterMenuChanged() {
																							var table = document
																									.getElementById("form1:tableObligaciones");
																							return table.filterMenuChanged();
																						}
																						/*
																						 * Toggle the custom filter panel (if any) open or closed.
																						 */
																						function toggleFilterPanel() {
																							var table = document
																									.getElementById("form1:tableObligaciones");
																							return table.toggleTableFilterPanel();
																						}
																						/* ----- Functions for Table Actions ----- */
																						/*
																						 * Initialize all rows of the table when the state
																						 * of selected rows changes.
																						 */
																						function initAllRows() {
																							var table = document
																									.getElementById("form1:tableObligaciones");
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
																							var table = document
																									.getElementById("form1:tableObligaciones");
																							table.selectGroupRows(rowGroupId, selected);
																						}
																						/*
																						 * Disable all table actions if no rows have been selected.
																						 */
																						function disableActions() {
																							// Determine whether any rows are currently selected
																							var table = document
																									.getElementById("form1:tableObligaciones");
																							var disabled = (table.getAllSelectedRowsCount() > 0) ? false
																									: true;
																							// Set disabled state for top actions
																							document
																									.getElementById(
																											"form1:tableObligaciones:tableActionsTop:deleteTop")
																									.setDisabled(disabled);
																							// Set disabled state for bottom actions
																							document
																									.getElementById(
																											"form1:tableObligaciones:tableActionsBottom:deleteBottom")
																									.setDisabled(disabled);
																						}
																						]]>
																					</script>
																					<ui:tableRowGroup binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.tableRowObligacion}"
																						id="tableRowObligacion" emptyDataMsg="Ningún registro encontrado."
																						sourceData="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.ldpObligaciones}" sourceVar="currentRow">
																						<ui:tableColumn binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.tableColumnObligacion1}"
																							headerText="Documento Especializado" id="tableColumnObligacion1">
																							<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.staticTextObligacion1}"
																								id="staticTextObligacion1" text="#{currentRow.value['documentoEspecializado']}" />
																						</ui:tableColumn>
																						<ui:tableColumn binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.tableColumnObligacion2}"
																							headerText="Persona" id="tableColumnObligacion2">
																							<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.staticTextObligacion2}"
																								id="staticTextObligacion2" text="#{currentRow.value['persona']}" />
																						</ui:tableColumn>
																						<ui:tableColumn binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.tableColumnObligacion3}"
																							headerText="Estado" id="tableColumnObligacion3">
																							<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.staticTextObligacion3}"
																								id="staticTextObligacion3" text="#{currentRow.value['estado']}" />
																						</ui:tableColumn>
																					</ui:tableRowGroup>
																				</ui:table>
																			</td>
																		</tr>
																	</tbody>
																</table>
															</ui:panelGroup>
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<div class="div" style="width: 290px; height: 15px;">Atributos Dinámicos</div>
															<table border="0" class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
																<tr>
																	<td colspan="4">
																		<ui:panelGroup binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.panelAtributoDinamico}"
																			id="panelAtributoDinamico">
																		</ui:panelGroup>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</ui:tab>
											<ui:tab id="two" binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.tabTwo}" text="Título Propiedad">
												<table>
													<tr>
														<td colspan="2">
															<ui:label binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.lblPropietarios}" id="lblPropietarios"
																styleClass="label57" text="Propietarios" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table augmentTitle="false" binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.tablePropietarios}"
																id="tablePropietarios">
																<script>
																	<![CDATA[
																	/* ----- Functions for Table Preferences Panel ----- */
																	/*
																	 * Toggle the table preferences panel open or closed
																	 */
																	function togglePreferencesPanel() {
																		var table = document.getElementById("form1:tablePropietarios");
																		table.toggleTblePreferencesPanel();
																	}
																	/* ----- Functions for Filter Panel ----- */
																	/*
																	 * Return true if the filter menu has actually changed,
																	 * so the corresponding event should be allowed to continue.
																	 */
																	function filterMenuChanged() {
																		var table = document.getElementById("form1:tablePropietarios");
																		return table.filterMenuChanged();
																	}
																	/*
																	 * Toggle the custom filter panel (if any) open or closed.
																	 */
																	function toggleFilterPanel() {
																		var table = document.getElementById("form1:tablePropietarios");
																		return table.toggleTableFilterPanel();
																	}
																	/* ----- Functions for Table Actions ----- */
																	/*
																	 * Initialize all rows of the table when the state
																	 * of selected rows changes.
																	 */
																	function initAllRows() {
																		var table = document.getElementById("form1:tablePropietarios");
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
																		var table = document.getElementById("form1:tablePropietarios");
																		table.selectGroupRows(rowGroupId, selected);
																	}
																	/*
																	 * Disable all table actions if no rows have been selected.
																	 */
																	function disableActions() {
																		// Determine whether any rows are currently selected
																		var table = document.getElementById("form1:tablePropietarios");
																		var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
																		// Set disabled state for top actions
																		document.getElementById("form1:tablePropietarios:tableActionsTop:deleteTop")
																				.setDisabled(disabled);
																		// Set disabled state for bottom actions
																		document.getElementById(
																				"form1:tablePropietarios:tableActionsBottom:deleteBottom")
																				.setDisabled(disabled);
																	}
																	]]>
																</script>
																<ui:tableRowGroup binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.tableRowGroup1}" id="tableRowGroup1"
																	sourceData="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.ldpRegistroPropietario}" sourceVar="currentRow">
																	<ui:tableColumn align="center" binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.tableColumn1}"
																		id="tableColumn1" valign="middle" width="10">
																		<ui:radioButton binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.radioButton1}" id="radioButton1" label=""
																			name="buttonGroup" selected="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.RBSelected}"
																			selectedValue="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.currentRow}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.tcPersona}" headerText="Persona"
																		id="tcPersona" sort="persona">
																		<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.stPersona}" id="stPersona"
																			text="#{currentRow.value['persona']}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.tcDescripcion}" headerText="Descripción"
																		id="tcDescripcion" sort="descripcion">
																		<ui:textArea binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.taDescripcionPropietarios}" columns="40"
																			id="taDescripcionPropietarios" styleClass="textField" text="#{currentRow.value['descripcion']}" />
																	</ui:tableColumn>
																</ui:tableRowGroup>
																<f:facet name="actionsTop">
																	<ui:panelGroup binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.groupPanel1}" id="groupPanel1">
																		<ui:textField binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.tfPropietario}" columns="40"
																			id="tfPropietario"
																			styleClass="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.hayPersona ? 'textFieldDisabled' : 'textField'}"
																			disabled="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.hayPersona}" />
																		<a4j:commandButton action="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.btnAgregarPropietario_action}"
																			binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.btnAgregarPropietario}" title="Agregar persona seleccionada a la tabla" id="btnAgregarPropietario"
																			styleClass="buttonAgregarAjax" reRender="tablePropietarios"
																			oncomplete="cargarComportamientoJQuery(); focusearTfPropietario();"/>
																		<a4j:commandButton id="btnLimpiarPropietario" reRender="tfPropietario" title="Limpiar"
																			binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.btnLimpiarPropietario}"
																			action="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.btnLimpiarPropietario_action}"
																			styleClass="buttonLimpiarAjax" oncomplete="cargarComportamientoJQuery(); focusearTfPropietario();" />
																		<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.stSeparador2}" escape="false"
																			id="stSeparador2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
																		<ui:button action="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.btnAgregarPersonaFisica_action}"
																			binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.btnAgregarPersonaFisica}" id="btnAgregarPersonaFisica"
																			styleClass="button" text="Agregar PF" toolTip="Agregar Persona Física" />
																		<ui:button action="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.btnAgregarPersonaJuridica_action}"
																			binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.btnAgregarPersonaJuridica}" id="btnAgregarPersonaJuridica"
																			styleClass="button" text="Agregar PJ" toolTip="Agregar Persona Jurídica" />
																		<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.staticText1}" escape="false"
																			id="staticText1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
																		<a4j:commandButton action="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.btnQuitar_action}"
																			binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.btnQuitar}" id="btnQuitar" value="Quitar"
																			styleClass="btnAjax" reRender="tablePropietarios" onmousedown="reemplazarClickConConfirmacion(this, '');"
																			oncomplete="cargarComportamientoJQuery();" />
																		<a4j:commandButton action="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.btnQuitarTodos_action}"
																			binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.btnQuitarTodos}" id="btnQuitarTodos" value="Quitar todos"
																			styleClass="btnAjax" reRender="tablePropietarios"
																			onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea quitar todos los elementos de esta lista?');"
																			oncomplete="cargarComportamientoJQuery();" />
																	</ui:panelGroup>
																</f:facet>
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
															<div class="div" style="width: 290px; height: 15px;">Titulo Propiedad</div>
															<table border="0" class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
																<tr>
																	<td align="right" nowrap="nowrap">
																		<ui:label binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.lblFechaInscripcion}" id="lblFechaInscripcion"
																			styleClass="label" text="Fecha Inscripción" for="tfFechaInscripcion" />
																	</td>
																	<td>
																		<ui:textField binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.tfFechaInscripcion}" id="tfFechaInscripcion"
																			styleClass="textField" columns="10" />
																	</td>
																</tr>
																<tr>
																	<td align="right" nowrap="nowrap">
																		<ui:label binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.lblCodigo}" id="lblCodigo" styleClass="label"
																			text="Código" for="tfCodigo" />
																	</td>
																	<td>
																		<ui:textField binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.tfCodigo}" id="tfCodigo"
																			styleClass="textField" />
																	</td>
																</tr>
															</table>
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
																		<ui:panelGroup binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.panelAtributoDinamico2}"
																			id="panelAtributoDinamico2">
																		</ui:panelGroup>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</ui:tab>
										</ui:tabSet>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<table>
											<tr>
												<td>
													<br />
												</td>
											</tr>
											<tr>
												<td align="right">
													<ui:label binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.lblComentarioLogAuditoria}"
														id="lblComentarioLogAuditoria" />
												</td>
												<td>
													<ui:textArea binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.taComentarioLogAuditoria}"
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
													<ui:table binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.tablaLogs}" id="tbLogsAuditoria" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:messageGroup binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.messageGroup}" id="messageGroup"
											styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.btnGuardar_action}"
											binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.btnCancelar_action}"
											binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
