<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.head1}" id="head1">
				<ui:link binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.link1}" id="link1"
					url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
						function cargarComportamientoJQuery() {
							calendarioEnTextField("#form1:tabSet1:two:tfFechaInscripcion");
							calendarioEnTextField("#form1:tabSet1:two:tfFechaFin");
						}
					
						$(document).ready(function() {
							cargarComportamientoJQuery();
						});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.body1}" focus="form1:tabSet1:one:btnSeleccionarTipoSepultura" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.stTitulo}" id="stTitulo"
									styleClass="tituloABM" text="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td align="left" colspan="4">
										<ui:tabSet binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.tabSet1}" id="tabSet1" mini="true"
											lite="true">
											<ui:tab id="one" text="Datos">
												<table>
													<tr>
														<td>
															<br />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.lblTipoSepultura}"
																for="tfTipoSepultura" id="lblTipoSepultura" styleClass="label" text="Tipo Sepultura" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.tfTipoSepultura}"
																columns="30" disabled="true" id="tfTipoSepultura" maxLength="10" styleClass="textField" />
															<ui:button
																action="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.btnSeleccionarTipoSepultura_action}"
																binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.btnSeleccionarTipoSepultura}"
																escape="false" id="btnSeleccionarTipoSepultura" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;"
																toolTip="Seleccionar" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.lblSuperficie}"
																for="tfSuperficie" id="lblSuperficie" styleClass="label" text="Superficie" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.tfSuperficie}" columns="10"
																id="tfSuperficie" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td>
															<br />
														</td>
													</tr>
													<tr>
														<td>
															<ui:label binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.lblDifuntos}" id="lblDifuntos"
																styleClass="label57" text="Difuntos" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table augmentTitle="false"
																binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.tablaDifuntos}" id="tablaDifuntos">
																<script>
												<![CDATA[
												/* ----- Functions for Table Preferences Panel ----- */
												/*
												 * Toggle the table preferences panel open or closed
												 */
												function togglePreferencesPanel() {
													var table = document
															.getElementById("form1:tablaDifuntos");
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
															.getElementById("form1:tablaDifuntos");
													return table
															.filterMenuChanged();
												}
												/*
												 * Toggle the custom filter panel (if any) open or closed.
												 */
												function toggleFilterPanel() {
													var table = document
															.getElementById("form1:tablaDifuntos");
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
															.getElementById("form1:tablaDifuntos");
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
															.getElementById("form1:tablaDifuntos");
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
															.getElementById("form1:tablaDifuntos");
													var disabled = (table
															.getAllSelectedRowsCount() > 0) ? false
															: true;
													// Set disabled state for top actions
													document
															.getElementById(
																	"form1:tablaDifuntos:tableActionsTop:deleteTop")
															.setDisabled(
																	disabled);
													// Set disabled state for bottom actions
													document
															.getElementById(
																	"form1:tablaDifuntos:tableActionsBottom:deleteBottom")
															.setDisabled(
																	disabled);
												}
												]]>
											</script>
																<ui:tableRowGroup binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.tableRowGroup1}"
																	id="tableRowGroup1" sourceData="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.ldpDifuntos}"
																	sourceVar="currentRow">
																	<ui:tableColumn align="center"
																		binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.tableColumn1}" id="tableColumn1"
																		valign="middle" width="10">
																		<ui:radioButton binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.radioButton1}"
																			id="radioButton1" label="" name="buttonGroup"
																			selected="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.RBSelected}"
																			selectedValue="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.currentRow}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.tcPersona}"
																		headerText="Persona" id="tcPersona" sort="persona">
																		<ui:staticText binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.stPersona}"
																			id="stPersona" text="#{currentRow.value['persona']}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.tcFechaDeceso}"
																		headerText="Fecha Deceso" id="tcFechaDeceso" sort="fechaDeceso">
																		<ui:staticText binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.stFechaDeceso}"
																			id="stFechaDeceso"
																			converter="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.dateTimeConverter}"
																			text="#{currentRow.value['fechaDeceso']}" />
																	</ui:tableColumn>
																</ui:tableRowGroup>
																<f:facet name="actionsTop">
																	<ui:panelGroup binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.groupPanel1}"
																		id="groupPanel1">
																		<ui:button action="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.btnAgregarDifunto_action}"
																			binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.btnAgregarDifunto}"
																			id="btnAgregarDifunto" styleClass="button" text="Agregar" />
																		<ui:button action="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.btnModificarDifunto_action}"
																			binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.btnModificarDifunto}"
																			id="btnModificarDifunto" styleClass="button" text="Modificar" />
																		<ui:button action="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.btnConsultarDifunto_action}"
																			binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.btnConsultarDifunto}"
																			id="btnConsultarDifunto" styleClass="button" text="Consultar" />
																		<ui:staticText binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.staticText1}"
																			escape="false" id="staticText1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
																		<a4j:commandButton
																			action="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.btnQuitarDifunto_action}"
																			binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.btnQuitarDifunto}"
																			id="btnQuitarDifunto" value="Quitar" styleClass="btnAjax" reRender="tablaDifuntos"
																			onmousedown="reemplazarClickConConfirmacion(this, '');" />
																		<a4j:commandButton
																			action="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.btnQuitarTodosDifunto_action}"
																			binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.btnQuitarTodosDifunto}"
																			id="btnQuitarTodosDifunto" value="Quitar todos" styleClass="btnAjax" reRender="tablaDifuntos"
																			onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea quitar todos los elementos de esta lista?');" />
																	</ui:panelGroup>
																</f:facet>
															</ui:table>
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<div class="div" style="width: 290px; height: 15px;">Atributos Dinámicos</div>
															<table border="0" class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
																<tr>
																	<td colspan="4">
																		<ui:panelGroup binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.panelAtributoDinamico}"
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
														<td align="right">
															<ui:label
																binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
														</td>
														<td>
															<ui:textArea
																binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
														</td>
													</tr>
													<tr>
														<td>
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.tablaLogs}" id="tbLogsAuditoria" />
														</td>
													</tr>
												</table>
											</ui:tab>
											<ui:tab id="two" text="Concesión">
												<table>
													<tr>
														<td nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.lblPropietarios}"
																id="lblPropietarios" styleClass="label57" text="Propietarios" />
														</td>
													</tr>
													<tr>
														<td colspan="2">
															<ui:table augmentTitle="false"
																binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.tablaPropietarios}" id="tablaPropietarios"
																width="359">
																<script> <![CDATA[
												                    /* ----- Functions for Table Preferences Panel ----- */
												                    /*
												                     * Toggle the table preferences panel open or closed
												                     */
												                    function togglePreferencesPanel() {
												                        var table = document.getElementById("form1:tablaPropietarios");
												                        table.toggleTblePreferencesPanel();
												                    }
												                    /* ----- Functions for Filter Panel ----- */
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
												                    /* ----- Functions for Table Actions ----- */
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
												                        document.getElementById("form1:tablaPropietarios:tableActionsTop:deleteTop").setDisabled(disabled);
												                        // Set disabled state for bottom actions
												                        document.getElementById("form1:tablaPropietarios:tableActionsBottom:deleteBottom").setDisabled(disabled);
												                    }]]></script>
																<ui:tableRowGroup binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.tableRowGroup2}"
																	id="tableRowGroup2"
																	sourceData="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.ldpRegistroPropietario}"
																	sourceVar="currentRow2">
																	<ui:tableColumn align="center"
																		binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.tableColumn2}" id="tableColumn2"
																		valign="middle" width="10">
																		<ui:radioButton binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.radioButton2}"
																			id="radioButton5" label="" name="buttonGroup2"
																			selected="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.RBSelected2}"
																			selectedValue="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.currentRow2}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.tcPersonaPropietario}"
																		headerText="Persona" id="tcPersonaPropietario" sort="persona">
																		<ui:staticText binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.stPersonaPropietario}"
																			id="stPersonaPropietario" text="#{currentRow2.value['persona']}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.tcDescripcion}"
																		headerText="Descripción" id="tcDescripcion" sort="descripcion">
																		<ui:textArea binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.taDescripcion}"
																			columns="40" id="taDescripcion" styleClass="textField" text="#{currentRow2.value['descripcion']}" />
																	</ui:tableColumn>
																</ui:tableRowGroup>
																<f:facet name="actionsTop">
																	<ui:panelGroup binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.groupPanel2}"
																		id="groupPanel5">
																		<ui:button
																			action="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.btnAgregarPersonaFisica_action}"
																			binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.btnAgregarPersonaFisica}"
																			id="btnAgregarPersonaFisica" styleClass="button" text="Agregar PF" toolTip="Agregar Persona Física" />
																		<ui:button
																			action="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.btnAgregarPersonaJuridica_action}"
																			binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.btnAgregarPersonaJuridica}"
																			id="btnAgregarPersonaJuridica" styleClass="button" text="Agregar PJ" toolTip="Agregar Persona Jurídica" />
																		<ui:staticText binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.staticText2}"
																			escape="false" id="staticText2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
																		<a4j:commandButton
																			action="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.btnQuitarPersona_action}"
																			binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.btnQuitarPersona}" id="btnQuitar"
																			value="Quitar" styleClass="btnAjax" reRender="tablaPropietarios" onmousedown="reemplazarClickConConfirmacion(this, '');" />
																		<a4j:commandButton
																			action="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.btnQuitarTodosPersona_action}"
																			binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.btnQuitarTodosPersona}"
																			id="btnQuitarTodos" value="Quitar todos" styleClass="btnAjax" reRender="tablaPropietarios"
																			onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea quitar todos los elementos de esta lista?');" />
																	</ui:panelGroup>
																</f:facet>
															</ui:table>
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.lblFechaInscripcion}"
																id="lblFechaInscripcion" styleClass="label" text="Fecha Inscripción" for="tfFechaInscripcion" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.tfFechaInscripcion}"
																id="tfFechaInscripcion" styleClass="textField" maxLength="10" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.lblFechaFin}" id="lblFechaFin"
																styleClass="label" text="Fecha Finalización" for="tfFechaFin" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.tfFechaFin}" id="tfFechaFin"
																styleClass="textField" maxLength="10" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.lblTipoConcesion}"
																id="lblTipoConcesion" styleClass="label" text="Tipo de Concesión" for="ddTipoConcesion" />
														</td>
														<td>
															<ui:dropDown binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.ddTipoConcesion}"
																id="ddTipoConcesion"
																items="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.ddTipoConcesionDefaultOptions.options}"
																styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<div class="div" style="width: 290px; height: 15px;">Atributos Dinámicos</div>
															<table border="0" class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
																<tr>
																	<td colspan="4">
																		<ui:panelGroup binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.panelAtributoDinamico2}"
																			id="panelAtributoDinamico2">
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
														<td align="right">
															<ui:label text="Comentario de modificación" styleClass="label" binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.lblComentarioLogAuditoria2}" />
														</td>
														<td>
															<ui:textArea styleClass="textField" columns="40" rows="5" binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.taComentarioLogAuditoria2}" />
														</td>
													</tr>
													<tr>
														<td>
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table augmentTitle="false" id="tablaLogs2" binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.tablaLogs2}" />
														</td>
													</tr>
												</table>
											</ui:tab>
										</ui:tabSet>
									</td>
								</tr>
							</tbody>
							<tr>
								<td colspan="2">
									<ui:messageGroup binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.messageGroup}" id="messageGroup"
										styleClass="grupoMsg" />
								</td>
							</tr>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.btnGuardar_action}"
											binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.btnGuardar}" id="btnGuardar"
											styleClass="button" />
										<ui:staticText binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.stSeparador}" escape="false"
											id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.btnCancelar_action}"
											binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.btnCancelar}" id="btnCancelar"
											styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>