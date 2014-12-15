<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.page1}" id="page1">
			<ui:html binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.html1}" id="html1">
			<ui:head binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.head1}" id="head1">
				<ui:link binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					function cambiarServletImpresion(valor) {
						var $btnImprimir = $("#form1\\:btnImprimir");
						$btnImprimir.unbind('click');
						if(valor === "Un PDF") {
							$btnImprimir.attr("onclick", "newWindow = window.open('/Vipians/faces/ImpresionServlet', 'Reporte')");
						} else if(valor === "Un PDF por Contador") {
							$btnImprimir.attr("onclick", "newWindow = window.open('/Vipians/faces/DescargarArchivoServlet', 'Reporte')");
						}
					}
				</script>
			</ui:head>
			<ui:body binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.body1}" focus="form1:tfFechaInscripcion" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<table align="center">
											<tr>
												<td>
													<br />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.lblPersona}" for="tfPersona" id="lblPersona" styleClass="label"
														text="Persona" />
												</td>
												<td nowrap="nowrap">
													<ui:textField binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.tfPersona}" columns="40" disabled="true" id="tfPersona"
														styleClass="textField" />
													<ui:button action="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.btnSeleccionarPersonaFisica_action}"
														binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.btnSeleccionarPersonaFisica}" escape="false"
														id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Fisica" />
													<ui:button action="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.btnSeleccionarPersonaJuridica_action}"
														binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.btnSeleccionarPersonaJuridica}" escape="false"
														id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar Persona Juridica" />
													<ui:button action="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.btnLimpiarPersona_action}"
														binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.btnLimpiarPersona}" escape="false" id="btnLimpiarPersona" mini="true"
														styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar" />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.lblNroInscripcion}" id="lblNroInscripcion" styleClass="label"
														text="Número Inscripción">
													</ui:label>
												</td>
												<td>
													<ui:textField binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.tfNroInscripcion}" id="tfNroInscripcion"
														styleClass="textField" columns="10">
													</ui:textField>
												</td>
											</tr>
											<tr>
												<td>
													<br />
												</td>
											</tr>
											<tr>
												<td align="center" nowrap="nowrap">
													<ui:label binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.lblAnio}" for="ddAnios" id="lblAnio" styleClass="label"
														text="Año" />
													<ui:dropDown binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.ddAnios}" id="ddAnios" styleClass="textField"
														items="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.ddAniosOptions.options}"
														valueChangeListener="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.eventoSeleccionAnio(evento)}">
														<a4j:support event="onChange" reRender="form1:ddCalendarios, form1:ddPeriodos, form1:ddCuotas" />
													</ui:dropDown>
												</td>
												<td align="center" nowrap="nowrap">
													<ui:label binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.lblCalendarios}" for="ddCalendarios" id="lblCalendarios"
														styleClass="label" text="Calendario" />
													<ui:dropDown binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.ddCalendarios}" id="ddCalendarios" styleClass="textField"
														items="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.ddCalendariosOptions.options}"
														valueChangeListener="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.eventoSeleccionCalendario(event)}">
														<a4j:support event="onChange" reRender="form1:ddPeriodos, form1:ddCuotas" />
													</ui:dropDown>
												</td>
												<td align="center" nowrap="nowrap">
													<ui:label binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.lblPeriodos}" for="ddPeriodos" id="lblPeriodos"
														styleClass="label" text="Periodo" />
													<ui:dropDown binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.ddPeriodos}" id="ddPeriodos" styleClass="textField"
														items="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.ddPeriodosOptions.options}"
														valueChangeListener="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.eventoSeleccionPeriodo(event)}">
														<a4j:support event="onChange" reRender="form1:ddCuotas " />
													</ui:dropDown>
												</td>
												<td align="center" nowrap="nowrap">
													<ui:label binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.lblCuotas}" for="ddCuotas" id="lblCuotas" styleClass="label"
														text="Cuota" />
													<ui:dropDown binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.ddCuotas}" id="ddCuotas" styleClass="textField"
														items="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.ddCuotasOptions.options}" />
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<table border="0" width="600">
														<tr>
															<td>
																<ui:label id="lblVacio" styleClass="label" text="" />
															</td>
															<td>
																<ui:panelGroup binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.panelAtributoDinamico}" id="panelAtributoDinamico">
																	<!-- AQUI VA LO QUE SE CREA DINAMICAMENTE -->
																</ui:panelGroup>
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
												<td align="center" colspan="4" nowrap="nowrap" style="height: 20px">
													<a4j:commandButton action="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.btnBuscar_action}"
														binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.btnBuscar}" id="btnBuscar" styleClass="btnAjax"
														value="Buscar Obligaciones" reRender="form1:table1" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<hr />
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:label binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.label6}" id="label6" styleClass="label2" text="Obligaciones SHPS" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:table augmentTitle="false" binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.table1}" id="table1" width="200">
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
											<ui:tableRowGroup binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.tableRowGroup1}" id="tableRowGroup1"
												sourceData="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.ldpDocEspSHPS}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.tableColumn1}" id="tableColumn1"
													valign="middle" width="10" rendered="false">
													<ui:radioButton binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.radioButton1}" id="radioButton1" label=""
														name="buttonGroup" selected="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.RBSelected}"
														selectedValue="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.tableColumn4}" headerText="Contribuyente" id="tableColumn4"
													width="40">
													<ui:staticText binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.staticText1}" id="staticText1"
														text="#{currentRow.value['persona']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.tableColumn5}" headerText="Nro Inscripción"
													id="tableColumn5" width="40">
													<ui:staticText binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.textField1}" id="textField1"
														text="#{currentRow.value['numeroInscripcion']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.tableColumn6}" headerText="Rubro" id="tableColumn6"
													width="40">
													<ui:staticText binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.textField2}" id="textField2"
														text="#{currentRow.value['persona']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.tableColumn7}" headerText="Calle" id="tableColumn7"
													width="40">
													<ui:staticText binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.textField4}" id="textField4"
														text="#{currentRow.value['persona']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.tableColumn8}" headerText="Altura" id="tableColumn8"
													width="40">
													<ui:staticText binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.textField5}" id="textField5"
														text="#{currentRow.value['persona']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop" />
										</ui:table>
									</td>
								</tr>
								<tr>
									<td>
										<table>
											<tbody>
												<tr nowrap="nowrap">
													<td colspan="4" style="padding-left: 1px; padding-right: 1px;">
														<ui:radioButtonGroup styleClass="optgroup" binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.rbgTipoImpresion}"
															items="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.rbgTipoImpresionOptions.options}" id="rbgTipoImpresionOptions" columns="3"
															labelLevel="2" onClick="cambiarServletImpresion(this.value);">
														</ui:radioButtonGroup>
													</td>
													<td nowrap="nowrap">
														<ui:button action="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.btnImprimir_action}"
															binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.btnImprimir}" id="btnImprimir" styleClass="button"
															text="Visualizar Reporte" onClick="newWindow = window.open('/Vipians/faces/ImpresionServlet', 'Reporte')" />
														<ui:staticText escape="false" id="stSeparadorBotonImprimirReiniciar" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<a4j:commandButton action="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.btnReiniciar_action}"
															binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
															reRender="form1:table1, form1:tfNroInscripcion, form1:tfPersona, form1:ddCalendarios, form1:ddPeriodos, form1:ddCuotas, 
															form1:panelAtributoDinamico, form1:rbgTipoImpresionOptions" />
														<ui:staticText binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.staticText2}" escape="false" id="staticText2"
															text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.btnCancelar_action}"
															binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
													</td>
												</tr>
											</tbody>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<div>
											<a4j:outputPanel ajaxRendered="true">
												<ui:messageGroup binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.messageGroup1}" id="messageGroup1" showGlobalOnly="true"
													styleClass="grupoMsg" />
											</a4j:outputPanel>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<ui:hiddenField binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.hidIdPagina}" id="hidIdPagina"
						text="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.idPagina}" />
					<ui:hiddenField binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.idSubSesion}" />
					<ui:script binding="#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
