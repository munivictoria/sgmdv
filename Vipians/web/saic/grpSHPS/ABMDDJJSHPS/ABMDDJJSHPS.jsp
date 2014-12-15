<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.page1}" id="page1">
			<ui:html binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.html1}" id="html1">
			<ui:head binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.head1}" id="head1">
				<ui:link binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
						var nombreBean = "saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS";
	
						function cargarComportamientoJQuery() {
							autoCompletarEnTextField("#form1:tfPersona", "persona", nombreBean, "setPersonaAutocompletar");
						}
	
						function focusearTfPersona() {
							$("#form1\\:tfPersona").focus();
						}
	
						$(document).ready(function() {
							cargarComportamientoJQuery();
						});

						function calcularMonto() {
							var $filas = $("#form1\\:table1").find('tr');

							for(var i = 1; i < $filas.length; i++) {
								var monto = $filas.eq(i).find("input[id *= 'textField2']").val();
								var alicuota = $filas.eq(i).find("span[id *= 'staticText4']").text();
								$filas.eq(i).find("span[id *= 'staticText5']").text(monto*alicuota);
							}
						}
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.body1}" focus="form1:btnSeleccionarPersonaFisica" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<table>
											<tr>
												<td>
													<br />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.label5}" for="tfPersona" id="label5" styleClass="label"
														text="Persona" />
												</td>
												<td nowrap="nowrap">
													<ui:textField binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.tfPersona}" columns="40" id="tfPersona"
														styleClass="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.hayPersona ? 'textFieldDisabled' : 'textField'}"
														disabled="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.hayPersona}" />
													<ui:button action="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.btnSeleccionarPersonaFisica_action}"
														binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.btnSeleccionarPersonaFisica}" escape="false" id="btnSeleccionarPersonaFisica"
														mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Fisica" />
													<ui:button action="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.btnSeleccionarPersonaJuridica_action}"
														binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.btnSeleccionarPersonaJuridica}" escape="false"
														id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar Persona Juridica" />
													<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersona" styleClass="buttonLimpiarAjax" title="Limpiar"
														action="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.btnLimpiarPersona_action}"
														binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.btnLimpiarPersona}"
														oncomplete="cargarComportamientoJQuery(); focusearTfPersona();" />
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" style="padding-left: 200px">
													<ui:label binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.lblNroInscripcion}" id="lblNroInscripcion" styleClass="label"
														text="Número Inscripción">
													</ui:label>
												</td>
												<td>
													<ui:textField binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.tfNroInscripcion}" id="tfNroInscripcion" styleClass="textField"
														columns="10">
													</ui:textField>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<table align="center">
											<tr>
												<td align="center" nowrap="nowrap">
													<ui:label binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.lblAnio}" for="ddAnios" id="lblAnio" styleClass="label" text="Año" />
													<ui:dropDown binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.ddAnios}" id="ddAnios" styleClass="textField"
														items="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.ddAniosOptions.options}"
														valueChangeListener="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.eventoSeleccionAnio(evento)}">
														<a4j:support event="onChange" reRender="form1:ddCalendarios, form1:ddPeriodos, form1:ddCuotas" />
													</ui:dropDown>
												</td>
												<td align="center" nowrap="nowrap">
													<ui:label binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.lblCalendarios}" for="ddCalendarios" id="lblCalendarios"
														styleClass="label" text="Calendario" />
													<ui:dropDown binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.ddCalendarios}" id="ddCalendarios" styleClass="textField"
														items="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.ddCalendariosOptions.options}"
														valueChangeListener="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.eventoSeleccionCalendario(event)}">
														<a4j:support event="onChange" reRender="form1:ddPeriodos, form1:ddCuotas" />
													</ui:dropDown>
												</td>
												<td align="center" nowrap="nowrap">
													<ui:label binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.lblPeriodos}" for="ddPeriodos" id="lblPeriodos" styleClass="label"
														text="Periodo" />
													<ui:dropDown binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.ddPeriodos}" id="ddPeriodos" styleClass="textField"
														items="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.ddPeriodosOptions.options}"
														valueChangeListener="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.eventoSeleccionPeriodo(event)}">
														<a4j:support event="onChange" reRender="form1:ddCuotas " />
													</ui:dropDown>
												</td>
												<td align="center" nowrap="nowrap">
													<ui:label binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.lblCuotas}" for="ddCuotas" id="lblCuotas" styleClass="label"
														text="Cuota" />
													<ui:dropDown binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.ddCuotas}" id="ddCuotas" styleClass="textField"
														items="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.ddCuotasOptions.options}" />
												</td>
											</tr>
										</table>
										<table align="center">
											<tr>
												<td>
													<br />
												</td>
											</tr>
											<tr>
												<td>
													<a4j:commandButton action="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.btnCargarRubros_action}"
														binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.btnCargarRubros}" id="btnCargarRubros" styleClass="btnAjax"
														value="Cargar Rubros" reRender="form1:table1" />
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
										<ui:label binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.label6}" id="label6" styleClass="label2"
											text="Montos Imponibles Declarados por Rubro" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:table augmentTitle="false" binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.table1}" id="table1" width="571">
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
											<ui:tableRowGroup binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.tableRowGroup1}" id="tableRowGroup1"
												sourceData="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.ldpMontoImponibleDeclarado}" sourceVar="currentRow">
												<!--
												<ui:tableColumn align="center" binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.tableColumn1}" id="tableColumn1" rendered="false"
													valign="middle" width="10">
													<ui:radioButton binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.radioButton1}" id="radioButton1" label="" name="buttonGroup"
														selected="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.RBSelected}"
														selectedValue="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.currentRow}" />
												</ui:tableColumn>
												-->
												<ui:tableColumn binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.tableColumn5}" headerText="Nro Incripción" id="tableColumn5"
													width="20">
													<ui:staticText binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.staticText2}" id="labelNroInscripcion"
														text="#{currentRow.value['nroInscripcion']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.tableColumn4}" headerText="Rubro" id="tableColumn4" width="500">
													<ui:staticText binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.staticText1}" id="staticText1"
														text="#{currentRow.value['rubro']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.tableColumn6}" headerText="Monto Declarado" id="tableColumn6"
													width="10" style="white-space: nowrap;">
													<ui:staticText binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.staticText3}" escape="false" id="staticText3" styleClass="label"
														text="&amp;nbsp;[$]" style="margin-right: 5px" />
													<ui:textField binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.textField2}" id="textField2"
														text="#{currentRow.value['importe']}" onKeyPress="return ValidarFloat(event,this)" onBlur="calcularMonto(this);" columns="10" />
													<ui:message binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.message2}" for="textField2" id="message2" showDetail="false"
														showSummary="true" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.tableColumn7}" headerText="Alícuota %" id="tableColumn7"
													width="500">
													<ui:staticText binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.staticText4}" id="staticText4"
														text="#{currentRow.value['rubro'].valor}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.tableColumn8}" headerText="Calculado" id="tableColumn8"
													width="500">
													<ui:staticText binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.staticText5}" id="staticText5" 
													converter="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.numberConverter1}" text="0"/>
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop" />
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap" style="padding-left: 40px">
										<ui:label binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.lblRetenciones}" for="tfRetenciones" id="lblRetenciones"
											styleClass="label" text="Retenciones" style="margin-right: 10px" />
										<ui:textField binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.tfRetenciones}" columns="10" id="tfRetenciones"
											styleClass="textField" onKeyPress="return ValidarFloat(event,this)" />
									</td>
								</tr>
								<tr>
									<td>
										<div>
											<a4j:outputPanel ajaxRendered="true">
												<ui:messageGroup binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.messageGroup1}" id="messageGroup1" showGlobalOnly="true"
													styleClass="grupoMsg" />
											</a4j:outputPanel>
										</div>
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="2" nowrap="nowrap" style="height: 24px">
										<ui:button action="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.btnGuardar_action}"
											binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:button action="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.btnGuardarYLiquidar_action}"
											binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.btnGuardarYLiquidar}" id="btnGuardarYLiquidar" text="Guardar y Liquidar"
											styleClass="button" />
										<ui:staticText binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.btnCancelar_action}"
											binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.hidIdPagina}" id="hidIdPagina"
						text="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.idPagina}" />
					<ui:hiddenField binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.idSubSesion}" />
					<ui:script binding="#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
