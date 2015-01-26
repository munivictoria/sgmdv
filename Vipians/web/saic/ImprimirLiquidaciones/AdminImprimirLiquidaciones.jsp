<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" isELIgnored="false"
		deferredSyntaxAllowedAsLiteral="false" />
	<f:view>
		<ui:page binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.page1}" id="page1">
			<ui:html binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.html1}" id="html1">
			<ui:head binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.head1}" id="head1" title="Servicios Municipales">
				<ui:link binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones";

					function cargarComportamientoJQuery() {
						autoCompletarEnTextField("#form1:tfPersonaSeleccionada", "persona", nombreBean, "setPersonaAutocompletar");
						autoCompletarEnTextField("#form1:tfParcelaSeleccionada", "parcela", nombreBean, "setParcelaAutocompletar");
					}

					function focusearTfPersonaSeleccionada() {
						$("#form1\\:tfPersonaSeleccionada").focus();
					}

					function focusearTfParcelaSeleccionada() {
						$("#form1\\:tfParcelaSeleccionada").focus();
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.body1}" focus="form1:btnSeleccionarPersonaFisica" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td colspan="2"></td>
									</tr>
									<tr>
										<td align="left" style="padding-left: 40px">
											<ui:panelGroup binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.lblPersonaSeleccionada}"
																for="tfPersonaSeleccionada" id="lblPersonaSeleccionada" styleClass="label" text="Persona" />
														</td>
														<td>
															<ui:textField binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.tfPersonaSeleccionada}" columns="40"
																id="tfPersonaSeleccionada" styleClass="#{SessionBean1.personaSeleccionada != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{SessionBean1.personaSeleccionada != null}" />
															<ui:button action="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnSeleccionarPersonaFisica_action}"
																binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnSeleccionarPersonaFisica}" escape="false"
																id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar" />
															<ui:button action="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnSeleccionarPersonaJuridica_action}"
																binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnSeleccionarPersonaJuridica}" escape="false"
																id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersonaSeleccionada" title="Limpiar"
																binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnLimpiarPersona}"
																action="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnLimpiarPersona_action}" styleClass="buttonLimpiarAjax"
																oncomplete="cargarComportamientoJQuery(); focusearTfPersonaSeleccionada();" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.lblNroDocumento}" for="tfDni" id="lblNroDocumento"
																styleClass="label" text="DNI" />
														</td>
														<td>
															<ui:textField binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.tfNroDocumento}" columns="10"
																id="tfNroDocumento" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.lblParcelaSeleccionada}"
																for="tfParcelaSeleccionada" id="lblParcelaSeleccionada" styleClass="label" text="Parcela" />
														</td>
														<td>
															<ui:textField binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.tfParcelaSeleccionada}" columns="40"
																id="tfParcelaSeleccionada" styleClass="#{SessionBean1.parcelaSeleccionada != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{SessionBean1.parcelaSeleccionada != null}" />
															<ui:button action="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnSeleccionarParcela_action}"
																binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnSeleccionarParcela}" escape="false"
																id="btnSeleccionarParcela" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarParcela" reRender="form1:tfParcelaSeleccionada" title="Limpiar"
																binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnLimpiarParcela}"
																action="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnLimpiarParcela_action}" styleClass="buttonLimpiarAjax"
																oncomplete="cargarComportamientoJQuery(); focusearTfParcelaSeleccionada();" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.lblNroParcela}" for="tfNroParcela"
																id="lblNroParcela" styleClass="label" text="Nº de Parcela / Inscripción" />
														</td>
														<td>
															<ui:textField binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.tfNroParcela}" columns="20" id="tfNroParcela"
																styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<hr />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:label binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.label8}" id="label8" styleClass="label2"
																text="Tipo Obligación y Período" />
														</td>
													</tr>
													<tr>
														<td align="left" nowrap="nowrap" colspan="4">
															<table>
																<tr>
																	<td nowrap="nowrap" colspan="4">
																		<ui:radioButtonGroup styleClass="optgroup"
																			binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.rbgTipoObligacion}"
																			items="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.rbgTipoObligacionOptions.options}" id="rbgTipoObligacion"
																			columns="3" labelLevel="2">
																			<a4j:support event="onClick" reRender="form1:ddAnios, form1:ddCalendarios, form1:ddPeriodos, form1:ddCuotas"
																				actionListener="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.actionEventCambioTipoTasa(evento)}" />
																		</ui:radioButtonGroup>
																	</td>
																</tr>
																<tr>
																	<td align="center" nowrap="nowrap">
																		<ui:label binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.lblAnios}" for="ddAnios" id="lblAnios"
																			styleClass="label" text="Año" />
																		<ui:dropDown binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.ddAnios}" id="ddAnios"
																			styleClass="textField" items="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.ddAniosOptions.options}">
																			<a4j:support event="onChange"
																				actionListener="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.eventoSeleccionAnio(evento)}"
																				reRender="form1:ddCalendarios, form1:ddPeriodos, form1:ddCuotas" />
																		</ui:dropDown>
																	</td>
																	<td align="center" nowrap="nowrap">
																		<ui:label binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.lblCalendarios}" for="ddCalendarios"
																			id="lblCalendarios" styleClass="label" text="Calendario" />
																		<ui:dropDown binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.ddCalendarios}" id="ddCalendarios"
																			styleClass="textField" items="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.ddCalendariosOptions.options}">
																			<a4j:support event="onChange"
																				actionListener="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.actionListenerSeleccionCalendario(event)}"
																				reRender="form1:ddPeriodos, form1:ddCuotas" />
																		</ui:dropDown>
																	</td>
																	<td align="center" nowrap="nowrap">
																		<ui:label binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.lblPeriodos}" for="ddPeriodos" id="lblPeriodos"
																			styleClass="label" text="Periodo" />
																		<ui:dropDown binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.ddPeriodos}" id="ddPeriodos"
																			styleClass="textField" items="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.ddPeriodosOptions.options}">
																			<a4j:support event="onChange" reRender="form1:ddCuotas "
																				actionListener="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.actionListenerSeleccionPeriodo(event)}" />
																		</ui:dropDown>
																	</td>
																	<td align="center" nowrap="nowrap">
																		<ui:label binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.lblCuotas}" for="ddCuotas" id="lblCuotas"
																			styleClass="label" text="Cuota" />
																		<ui:dropDown binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.ddCuotas}" id="ddCuotas"
																			styleClass="textField" items="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.ddCuotasOptions.options}" />
																	</td>
																<tr>
																	<td colspan="4">
																		<hr />
																	</td>
																</tr>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td nowrap="nowrap" colspan="2">
															<table>
																<tbody>
																	<tr>
																		<td align="right" nowrap="nowrap">
																			<ui:label binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.lblEstado}" id="lblEstado" styleClass="label"
																				text="Estado" />
																			<ui:dropDown binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.ddEstado}" id="ddEstado"
																				styleClass="textField" items="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.ddEstadoDefaultOptions.options}" />
																		</td>
																		<td nowrap="nowrap">
																			<ui:label binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.lblTipo}" id="lblTipo" styleClass="label"
																				text="Tipo" />
																			<ui:dropDown binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.ddTipo}" id="ddTipo" styleClass="textField"
																				items="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.ddTipoDefaultOptions.options}" />
																		</td>
																		<td nowrap="nowrap">
																			<ui:label binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.lblOmitir}" id="lblOmitir" styleClass="label"
																				text="Omitir monto en cero" />
																			<ui:checkbox binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.chkOmitir}" id="chkOmitir" selected="false" />
																		</td>
																		<td nowrap="nowrap">
																			<ui:label id="lblOmitirMostrador" styleClass="label"
																				text="Omitir mostrador" for="cbOmitirMostrador"/>
																			<ui:checkbox binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.cbOmitirMostrador}" id="cbOmitirMostrador" selected="false" />
																		</td>
																	</tr>
																</tbody>
															</table>
														</td>
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
								</tbody>
								<tfoot>
									<tr>
										<td align="right" colspan="2">
											<a4j:commandButton binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnBuscar}"
												action="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1, form1:stTotal,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnReiniciar_action}"
												binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1, form1:stTotal,form1:stCantidadRegistros"
												oncomplete="cargarComportamientoJQuery();" />
											<ui:staticText binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnCancelar_action}"
												binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.messageGroup}" id="messageGroup"
										showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<h:panelGrid columns="2" id="grpCargando" style="display: none; margin-left: 7px; padding-left: 10px" styleClass="msgLiquidacion">
								<ui:image binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.image1}" id="image1"
									url="/resources/imagenes/abm/wait_medium_1.gif" />
								<ui:staticText binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.staticText1}" escape="false" id="staticText1"
									styleClass="label2" text="     Generando archivos de impresión... Aguarde por favor." />
							</h:panelGrid>
							<script>
								<![CDATA[
								document.getElementById("form1:grpCargando").style.display = "none";

								function mostrarProgreso() {
									tabla = document.getElementById("form1:grpCargando");
									tabla.style.display = "block";
								}
								]]>
							</script>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.table1}" id="table1" width="128">
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
											<ui:tableRowGroup binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.tableRowGroup1}"
												emptyDataMsg="Ningún registro encontrado." id="tableRowGroup1" onMouseOver="jsRowMouseOver(this)"
												onMouseOut="jsRowMouseOut(this)" onClick="jsRowMouseClick(this)" onDblClick="funcionSeleccionar()"
												sourceData="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.ldpLiquidacionTasaRefer}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.tableColumn1}" id="tableColumn1"
													valign="middle" width="10">
													<ui:radioButton binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.radioButton1}" id="radioButton1" label=""
														name="buttonGroup" onClick="checkUncheck(this)" selected="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.RBSelected}"
														selectedValue="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn align="center" binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.tcCheckbox}" id="tcCheckbox"
													valign="middle" width="10" onClick="setTimeout('initAllRows()', 0)">
													<ui:checkbox binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.checkBox1}" id="checkBox1" label=""
														name="buttonGroupCB" onClick="checkUncheck(this)" selected="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.selected}"
														selectedValue="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.tcAnio}" headerText="Año" id="tcAnio"
													sort="anio">
													<ui:staticText binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.stAnio}" id="stAnio"
														text="#{currentRow.value['anio']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.tableColumn2}" headerText="Período"
													id="tableColumn2" sort="periodo">
													<ui:staticText binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.staticText3}" id="staticText3"
														text="#{currentRow.value['periodo']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.tcPersona}" headerText="Persona" id="tcPersona"
													sort="stringPersona">
													<ui:textArea binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.taPersona}" id="taPersona"
														text="#{currentRow.value['stringPersona']}" disabled="true" styleClass="textFieldDisabled" rows="1" columns="40" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.tcParcela}" headerText="Parcela/Inscripción" id="tcParcela"
													sort="parcela">
													<ui:staticText binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.stParcela}" id="stParcela"
														text="#{currentRow.value['parcela']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.tableColumn3}" headerText="Monto"
													id="tableColumn3" sort="total">
													<ui:staticText binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.staticText4}"
														converter="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.numberConverter1}" id="staticText4"
														text="#{currentRow.value['total']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.tableColumn6}" headerText="Tipo de Deuda"
													id="tableColumn6" sort="tipo">
													<ui:staticText binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.staticText12}" id="staticText12"
														text="#{currentRow.value['tipo']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.tableColumn7}" headerText="Estado"
													id="tableColumn7" sort="estado">
													<ui:staticText binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.staticText13}" id="staticText13"
														text="#{currentRow.value['estado']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.groupPanel1}" id="groupPanel1"
													separator="&lt;br/&gt;&lt;br/&gt;">
													<ui:panelGroup binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.gpBotones}" id="gpBotones">
														<ui:button action="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnSeleccionar_action}"
															binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
															text="Seleccionar" />
														<ui:staticText binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.stSeparadorSeleccionar}" escape="false"
															id="staticText6" />
														<ui:button action="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnCambiarSeleccion_action}"
															binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnCambiarSeleccion}" id="btnCambiarSeleccion"
															styleClass="button" text="Cambiar Selección" />
														<ui:button action="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnSeleccionarTodas_action}"
															binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnSeleccionarTodas}" id="btnSeleccionarTodas"
															styleClass="button" text="Seleccionar Todas"
															rendered="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.elementoPila.seleccionMultiple}" />
														<ui:staticText escape="false" id="stSeparador2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnConsultar_action}"
															binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnConsultar}" disabled="false" id="btnConsultar"
															styleClass="button" text="Consultar" />
														<ui:button action="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnImprimirSeleccionadas_action}"
															binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnImprimirSeleccionada}" id="btnImprimirSeleccionada"
															onClick="newWindow = window.open('/Vipians/faces/ImpresionServlet', 'Reporte')" styleClass="button" text="Imprimir" />
														<ui:staticText escape="false" id="srSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnReliquidar_action}"
															binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnReliquidar}" id="btnReliquidar" styleClass="button"
															text="Reliquidar" />
														<ui:button action="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnActualizarDeuda_action}"
															binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnActualizarDeuda}" id="btnActualizarDeuda"
															styleClass="button" text="Actualizar Deuda" />
														<ui:button action="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnNotificar_action}"
															binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnNotificar}" id="btnNotificar"
															styleClass="button" text="Notificar" />
														<ui:staticText text="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.textoSeparador}" id="stSeparadorExportal"
															styleClass="barraSeparadoraVertical" />
														<ui:button action="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnExportar_action}"
															binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnExportar}" id="btnExportar" styleClass="button"
															text="A Planilla" onClick="return exportarReporte('Exportando..')" />
														<ui:button action="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnExportarPDF_action}"
															binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnExportarPDF}" id="btnExportarPDF" styleClass="button"
															text="A PDF" onClick="return exportarReporte('Exportando..');" />
													</ui:panelGroup>
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="right" colspan="3" id="tdTotal">
										<ui:label binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.lblTotal}" id="lblTotal" styleClass="label"
											text="Total:" />
										<ui:staticText binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.stTotal}" id="stTotal" styleClass="staticText" />
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.lblEncontrados}" id="lblEncontrados"
											styleClass="label2" text="Registros Encontrados: " />
										<ui:staticText binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
								<tr>
									<td colspan="3">
										<div class="notifABM clrNota">
											<b>Nota: </b>La columnta <i>Monto</i> es el resultado del importe de la tasa más el valor de los modificadores correspondientes.
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<ui:panelGroup id="pgPanelAdminLiquidaciones"
											rendered="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.renderPanelAdminLiquidaciones}">
											<ui:button action="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnMarcarPagada_action}"
												binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnMarcarPagada}" id="btnMarcarPagada" styleClass="button"
												text="Marcar Pagada" />
											<ui:button action="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnMarcarImpaga_action}"
												binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnMarcarImpaga}" id="btnMarcarImpaga" styleClass="button"
												text="Marcar Impaga" />
											<ui:staticText escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnModificarLiquidacion_action}"
												binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnModificarLiquidacion}" id="btnModificarLiquidacion"
												styleClass="button" text="Modificar" />
											<ui:staticText escape="false" id="stSeparador1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnEliminarLiquidacion_action}"
												binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnEliminarLiquidacion}" id="btnEliminarLiquidacion"
												styleClass="button" text="Eliminar" />
											<ui:staticText escape="false" id="stSeparadorNoAgrupar" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:label text="No agrupar" for="cbNoAgruparLiquidaciones" id="lblNoAgruparLiquidaciones" />
											<ui:checkbox binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.cbNoAgruparLiquidaciones}"
												id="cbNoAgruparLiquidaciones" />
											<ui:staticText escape="false" id="stSeparadorBotonImprimirServidor" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<a4j:commandButton binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnImprimirEnServidor}"
												action="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.btnImprimirEnServidor_action}" id="btnImprimirEnServidor"
												value="Imprimir" styleClass="btnAjax" oncomplete="changeStyleAlIngresar()" />
										</ui:panelGroup>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:btnSeleccionarPersonaFisica').focus();
					</script>
					<ui:hiddenField binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.hidIdPagina}" id="hidIdPagina"
						text="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.idPagina}" />
					<ui:hiddenField binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.idSubSesion}" />
					<ui:script binding="#{saic$ImprimirLiquidaciones$AdminImprimirLiquidaciones.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
