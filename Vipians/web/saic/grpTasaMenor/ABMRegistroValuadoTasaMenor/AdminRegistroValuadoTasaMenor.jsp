<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.page1}" id="page1">
			<ui:html binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.html1}" id="html1">
			<ui:head binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.head1}" id="head1"
				title="Administración de Registros Valuados de Tasas Menores">
				<ui:link binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.link1}" id="link1"
					url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.body1}" focus="form1:tfCodigoMedidor"
				id="body1" onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.stTitulo}" id="stTitulo"
										styleClass="tituloABM" text="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td colspan="2"></td>
									</tr>
									<tr>
										<td align="center">
											<ui:panelGroup binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.pgParametros}"
												id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.lblPersona}" id="lblPersona"
																styleClass="label" text="Persona" />
														</td>
														<td>
															<ui:textField binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.tfPersona}"
																id="tfPersona" styleClass="textField" columns="40" />
															<ui:button
																action="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.btnSeleccionarPersonaFisica_action}"
																binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.btnSeleccionarPersonaFisica}"
																escape="false" id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF"
																toolTip="Seleccionar Persona Física" />
															<ui:button
																action="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.btnSeleccionarPersonaJuridica_action}"
																binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.btnSeleccionarPersonaJuridica}"
																escape="false" id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ"
																toolTip="Seleccionar Persona Jurídica" />
															<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersona" title="Limpiar"
																binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.btnLimpiarPersona}"
																action="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.btnLimpiarPersona_action}"
																styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.label2}"
																for="tfTipoObligacion" id="label2" styleClass="label" text="Tipo de Obligación" />
														</td>
														<td>
															<ui:textField binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.tfTipoObligacion}"
																columns="40" disabled="true" id="tfTipoObligacion" styleClass="textField" />
															<ui:button
																action="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.btnSeleccionarTipoObligacion_action}"
																binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.btnSeleccionarTipoObligacion}"
																escape="false" id="btnSeleccionarTipoObligacion" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;"
																toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarTipoObligacion" reRender="form1:tfTipoObligacion" title="Limpiar"
																binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.btnLimpiarTipoObligacion}"
																action="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.btnLimpiarTipoObligacion_action}"
																styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td colspan="2">
															<hr />
														</td>
													</tr>
													<tr>
														<td colspan="2">
															<ui:label binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.label3}" id="label3"
																styleClass="label2" text="Período" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.lblCalendarios}"
																for="ddCalendarios" id="lblCalendarios" styleClass="label" text="Calendarios" />
														</td>
														<td>
															<ui:dropDown binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.ddCalendarios}"
																id="ddCalendarios" styleClass="textField"
																items="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.ddCalendariosOptions.options}"
																valueChangeListener="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.valueChangeEvent(event)}"
																onChange="this.form.submit()" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.lblPeriodos}"
																for="ddPeriodos" id="lblPeriodos" styleClass="label" text="Periodos" />
														</td>
														<td>
															<ui:dropDown binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.ddPeriodos}"
																id="ddPeriodos" styleClass="textField"
																items="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.ddPeriodosOptions.options}"
																valueChangeListener="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.valueChangeEventDdPeriodos(event)}"
																onChange="this.form.submit()" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.lblCuotas}" for="ddCuotas"
																id="lblCuotas" styleClass="label" text="Cuotas" />
														</td>
														<td>
															<ui:dropDown binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.ddCuotas}" id="ddCuotas"
																styleClass="textField"
																items="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.ddCuotasOptions.options}" />
														</td>
													</tr>
													<tr>
														<td colspan="2"></td>
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
								</tbody>
								<tfoot>
									<tr>
										<td align="right" colspan="2">
											<a4j:commandButton binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.btnBuscar}"
												action="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.btnBuscar_action}" id="btnBuscar"
												value="Buscar" styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.btnReiniciar_action}"
												binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.btnReiniciar}" id="btnReiniciar"
												styleClass="btnAjax" value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.staticText2}"
												escape="false" id="staticText2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.btnCancelar_action}"
												binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.btnCancelar}" id="btnCancelar"
												styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.messageGroup}"
										id="messageGroup" showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.table1}" id="table1" width="622">
											<script>
												<![CDATA[
												/* ----- Functions for Table Preferences Panel ----- */
												/*
												 * Toggle the table preferences panel open or closed
												 */
												function togglePreferencesPanel() {
													var table = document
															.getElementById("form1:table1");
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
															.getElementById("form1:table1");
													return table
															.filterMenuChanged();
												}
												/*
												 * Toggle the custom filter panel (if any) open or closed.
												 */
												function toggleFilterPanel() {
													var table = document
															.getElementById("form1:table1");
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
															.getElementById("form1:table1");
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
															.getElementById("form1:table1");
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
															.getElementById("form1:table1");
													var disabled = (table
															.getAllSelectedRowsCount() > 0) ? false
															: true;
													// Set disabled state for top actions
													document
															.getElementById(
																	"form1:table1:tableActionsTop:deleteTop")
															.setDisabled(
																	disabled);
													// Set disabled state for bottom actions
													document
															.getElementById(
																	"form1:table1:tableActionsBottom:deleteBottom")
															.setDisabled(
																	disabled);
												}
												]]>
											</script>
											<ui:tableRowGroup binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.tableRowGroup1}"
												emptyDataMsg="Ningún registro encontrado." id="tableRowGroup1" onMouseOver="jsRowMouseOver(this)"
												onMouseOut="jsRowMouseOut(this)" onClick="jsRowMouseClick(this)" onDblClick="funcionSeleccionar()"
												sourceData="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.ldpRegistroValuado}"
												sourceVar="currentRow">
												<ui:tableColumn align="center"
													binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.tableColumn1}" id="tableColumn1"
													valign="middle" width="10">
													<ui:radioButton binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.radioButton1}"
														id="radioButton1" label="" name="buttonGroup" onClick="checkUncheck(this)"
														selected="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.RBSelected}"
														selectedValue="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.tableColumn5}"
													headerText="Período" id="tableColumn5" sort="periodo" width="20" noWrap="true">
													<ui:staticText binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.staticText3}"
														id="staticText3" text="#{currentRow.value['periodo']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.tableColumn2}"
													headerText="Obligación" id="tableColumn2" sort="stringObligacion" width="550">
													<ui:staticText binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.staticText1}"
														id="staticText1" text="#{currentRow.value['stringObligacion']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.tableColumn3}"
													headerText="Fecha" id="tableColumn3" sort="fecha" width="10">
													<ui:staticText binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.staticText9}"
														converter="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.dateTimeConverter1}" id="staticText9"
														text="#{currentRow.value['fecha']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.tableColumn4}"
													headerText="Consumo" id="tableColumn4" sort="montoImponible" width="10">
													<ui:staticText binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.staticText4}"
														id="staticText4" text="#{currentRow.value['montoImponible']}" />
													<ui:staticText binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.staticText11}"
														escape="false" id="staticText11" text="&amp;nbsp;[m&lt;sup&gt;3&lt;/sup&gt;]" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.tableColumn6}"
													headerText="Estado" id="tableColumn6" sort="estado" width="10">
													<ui:staticText binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.staticText7}"
														id="staticText7" text="#{currentRow.value['estado']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.groupPanel1}"
													id="groupPanel1" separator="&lt;br/&gt;&lt;br/&gt;">
													<ui:panelGroup binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.gpBotones}"
														id="gpBotones">
														<ui:button action="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.btnSeleccionar_action}"
															binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.btnSeleccionar}" id="btnSeleccionar"
															styleClass="button" text="Seleccionar" />
														<ui:staticText binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.stSeparadorSeleccionar}"
															escape="false" id="staticText6" />
														<ui:button action="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.btnAgregar_action}"
															binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.btnAgregar}" id="btnAgregar"
															styleClass="button" text="Agregar" />
														<ui:button action="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.btnModificar_action}"
															binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.btnModificar}" id="btnModificar"
															styleClass="button" text="Modificar" />
														<ui:button action="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.btnEliminar_action}"
															binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.btnEliminar}" disabled="true"
															id="btnEliminar" styleClass="button" text="Eliminar" />
														<ui:staticText binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.stSeparadorAccion}"
															escape="false" id="staticText8" />
														<ui:button action="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.btnConsultar_action}"
															binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.btnConsultar}" id="btnConsultar"
															styleClass="button" text="Consultar" />
														<ui:staticText binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.staticText14}"
															escape="false" id="staticText14" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.btnExportar_action}"
															binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.btnExportar}" id="btnExportar"
															styleClass="button" text="Exportar" onClick="return exportarReporte()" />
													</ui:panelGroup>
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfCodigoMedidor')
								.focus();
					</script>
					<ui:hiddenField binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.hidIdPagina}" id="hidIdPagina"
						text="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.idPagina}" />
					<ui:hiddenField binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.hidIdSubSesion}"
						id="hidIdSubSesion" text="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.idSubSesion}" />
					<ui:script binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AdminRegistroValuadoTasaMenor.scriptValidador}"
						id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
