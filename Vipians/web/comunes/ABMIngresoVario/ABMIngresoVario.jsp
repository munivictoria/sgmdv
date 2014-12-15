<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{comunes$ABMIngresoVario$ABMIngresoVario.page1}" id="page1">
			<ui:html binding="#{comunes$ABMIngresoVario$ABMIngresoVario.html1}" id="html1">
			<ui:head binding="#{comunes$ABMIngresoVario$ABMIngresoVario.head1}" id="head1">
				<ui:link binding="#{comunes$ABMIngresoVario$ABMIngresoVario.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
						var nombreBean = "comunes$ABMIngresoVario$ABMIngresoVario";
						
						function cargarComportamientoJQuery() {
							autoCompletarEnTextField("#form1:tfPersona", "persona", nombreBean, "setPersonaAutocompletar");
						}
						
						function focusearTfPersonaSeleccionada() {
							$("#form1\\:tfPersona").focus();
						}
						
						$(document).ready(function() {
							cargarComportamientoJQuery();
						});

					function calcularTotal() {

						var tabla = document
								.getElementById('form1:tablaImputaciones');

						var filas = tabla.rows;

						var acum = 0;

						for ( var i = 2; i < filas.length; i++) // el for empieza de 2 xq fila 0 y 1 son para nombres de las columnas y los botones
						{
							var span = filas[i].cells[2].childNodes[0];
							if (isNaN(parseFloat(span.value))) {
								span.value = 0.00;
							}
							acum += parseFloat(span.value);
						}
						document.getElementById('form1:stTotal').innerHTML = "$"
								+ roundNumber(acum, 2);
					}
					function roundNumber(number, digits) {
						var multiple = Math.pow(10, digits);
						var rndedNum = Math.round(number * multiple) / multiple;
						return rndedNum;
					}

					]]>
				</script>
			</ui:head>
			<ui:body binding="#{comunes$ABMIngresoVario$ABMIngresoVario.body1}" focus="form1:btnSeleccionarPersonaFisica"
				id="body1" onLoad="parent.footer.location.reload();Init();calcularTotal();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{comunes$ABMIngresoVario$ABMIngresoVario.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{comunes$ABMIngresoVario$ABMIngresoVario.stTitulo}" id="stTitulo"
									styleClass="tituloABM" text="#{comunes$ABMIngresoVario$ABMIngresoVario.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{comunes$ABMIngresoVario$ABMIngresoVario.lblNumero}" for="tfNumero"
											id="lblNumero" styleClass="label" text="Número" />
									</td>
									<td>
										<ui:textField binding="#{comunes$ABMIngresoVario$ABMIngresoVario.tfNumero}" disabled="true"
											id="tfNumero" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{comunes$ABMIngresoVario$ABMIngresoVario.label4}" for="tfFechaEmision" id="label4"
											styleClass="label" text="Fecha de Emisión" />
									</td>
									<td>
										<ui:textField binding="#{comunes$ABMIngresoVario$ABMIngresoVario.tfFechaEmision}" disabled="true"
											id="tfFechaEmision" styleClass="textField" />
										<!--<ui:staticText binding="#{comunes$ABMIngresoVario$ABMIngresoVario.staticText2}" escape="false"
                                                id="staticText2" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{comunes$ABMIngresoVario$ABMIngresoVario.label5}" for="tfPersona" id="label5"
											styleClass="label" text="Persona" />
									</td>
									<td>
										<ui:textField binding="#{comunes$ABMIngresoVario$ABMIngresoVario.tfPersona}" columns="40" id="tfPersona"
											styleClass="#{comunes$ABMIngresoVario$ABMIngresoVario.hayPersona ? 'textFieldDisabled' : 'textField'}"
											disabled="#{comunes$ABMIngresoVario$ABMIngresoVario.hayPersona}" />
										<ui:button action="#{comunes$ABMIngresoVario$ABMIngresoVario.btnSeleccionarPersonaFisica_action}"
											binding="#{comunes$ABMIngresoVario$ABMIngresoVario.btnSeleccionarPersonaFisica}"
											id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Física" />
										<ui:button action="#{comunes$ABMIngresoVario$ABMIngresoVario.btnSeleccionarPersonaJuridica_action}"
											binding="#{comunes$ABMIngresoVario$ABMIngresoVario.btnSeleccionarPersonaJuridica}"
											id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar Persona Jurídica" />
										<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersona" title="Limpiar"
											binding="#{comunes$ABMIngresoVario$ABMIngresoVario.btnLimpiarPersona}"
											action="#{comunes$ABMIngresoVario$ABMIngresoVario.btnLimpiarPersona_action}" styleClass="buttonLimpiarAjax" 
											oncomplete="cargarComportamientoJQuery(); focusearTfPersonaSeleccionada();"/>
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{comunes$ABMIngresoVario$ABMIngresoVario.label2}" for="tfConceptoIngresoVario" id="label2"
											styleClass="label" text="Concepto" />
									</td>
									<td>
										<ui:dropDown binding="#{comunes$ABMIngresoVario$ABMIngresoVario.ddConceptoIngresoVario}" id="ddConceptoIngresoVario"
													items="#{comunes$ABMIngresoVario$ABMIngresoVario.ddConceptoIngresoVarioOptions.options}" styleClass="textField"
													onChange="#{comunes$ABMIngresoVario$ABMIngresoVario.seleccionarConcepto}"/>
										<!--<ui:button action="#{comunes$ABMIngresoVario$ABMIngresoVario.btnSeleccionarConceptoIngresoVario_action}"
											binding="#{comunes$ABMIngresoVario$ABMIngresoVario.btnSeleccionarConceptoIngresoVario}" escape="false"
											id="btnSeleccionarConceptoIngresoVario" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />-->
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{comunes$ABMIngresoVario$ABMIngresoVario.lblEstado}" for="tfEstado" id="label1"
											styleClass="label" text="Estado" />
									</td>
									<td>
										<ui:textField binding="#{comunes$ABMIngresoVario$ABMIngresoVario.tfEstado}" columns="10" id="tfEstado" disabled="true"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{comunes$ABMIngresoVario$ABMIngresoVario.lblObservaciones}" id="lblObservaciones"
											for="taObservaciones" styleClass="label" text="Observaciones" />
									</td>
									<td>
										<ui:textArea binding="#{comunes$ABMIngresoVario$ABMIngresoVario.taObservaciones}" id="taObservaciones"
											columns="100" rows="7" styleClass="textField" />
									</td>
								</tr>
								<tr>
                                    <td>
                                        <br/>
                                    </td>
                                </tr>
                                <tr>
									<td colspan="2">
										<ui:label binding="#{comunes$ABMIngresoVario$ABMIngresoVario.lblImputaciones}" id="lblImputaciones"
											styleClass="label57" text="Imputaciones" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{comunes$ABMIngresoVario$ABMIngresoVario.tablaImputaciones}"
											id="tablaImputaciones">
											<script>
												<![CDATA[
												/* ----- Functions for Table Preferences Panel ----- */
												/*
												 * Toggle the table preferences panel open or closed
												 */
												function togglePreferencesPanel() {
													var table = document
															.getElementById("form1:tablaImputaciones");
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
															.getElementById("form1:tablaImputaciones");
													return table
															.filterMenuChanged();
												}
												/*
												 * Toggle the custom filter panel (if any) open or closed.
												 */
												function toggleFilterPanel() {
													var table = document
															.getElementById("form1:tablaImputaciones");
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
															.getElementById("form1:tablaImputaciones");
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
															.getElementById("form1:tablaImputaciones");
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
															.getElementById("form1:tablaImputaciones");
													var disabled = (table
															.getAllSelectedRowsCount() > 0) ? false
															: true;
													// Set disabled state for top actions
													document
															.getElementById(
																	"form1:tablaImputaciones:tableActionsTop:deleteTop")
															.setDisabled(
																	disabled);
													// Set disabled state for bottom actions
													document
															.getElementById(
																	"form1:tablaImputaciones:tableActionsBottom:deleteBottom")
															.setDisabled(
																	disabled);
												}
												]]>
											</script>
											<ui:tableRowGroup binding="#{comunes$ABMIngresoVario$ABMIngresoVario.trgImputaciones}" id="trgImputaciones"
												sourceData="#{comunes$ABMIngresoVario$ABMIngresoVario.ldpImputaciones}" sourceVar="currentRowImputaciones">
												<ui:tableColumn align="center" binding="#{comunes$ABMIngresoVario$ABMIngresoVario.tcRbImputaciones}" id="tcRbImputaciones"
													valign="middle" width="10">
													<ui:radioButton binding="#{comunes$ABMIngresoVario$ABMIngresoVario.rbImputaciones}" id="rbCuentas" label=""
														name="buttonGroupImputaciones" selected="#{comunes$ABMIngresoVario$ABMIngresoVario.RBSelectedImputaciones}"
														selectedValue="#{comunes$ABMIngresoVario$ABMIngresoVario.currentRowImputaciones}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{comunes$ABMIngresoVario$ABMIngresoVario.tcCuenta}" headerText="Cuenta" id="tcCuenta" sort="cuenta">
													<ui:staticText binding="#{comunes$ABMIngresoVario$ABMIngresoVario.stCuenta}" id="stCuenta" text="#{currentRowImputaciones.value['cuenta']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{comunes$ABMIngresoVario$ABMIngresoVario.tcMonto}" headerText="Monto" id="tcMonto" sort="monto">
													<ui:textField binding="#{comunes$ABMIngresoVario$ABMIngresoVario.tfMonto}" id="tfMonto" text="#{currentRowImputaciones.value['monto']}"
													onKeyPress="return ValidarFloat(event,this)" onBlur="calcularTotal(this)"/>
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{comunes$ABMIngresoVario$ABMIngresoVario.pgImputaciones}" id="pgImputaciones">
													<ui:dropDown binding="#{comunes$ABMIngresoVario$ABMIngresoVario.ddTodasRelasDelConceptoSeleccionado}" id="ddTodasRelasDelConceptoSeleccionado"
														items="#{comunes$ABMIngresoVario$ABMIngresoVario.ddTodasRelasDelConceptoSeleccionadoOptions.options}" styleClass="textField"/>
													<a4j:commandButton action="#{comunes$ABMIngresoVario$ABMIngresoVario.btnAgregarRelaSeleccionada_action}"
														binding="#{comunes$ABMIngresoVario$ABMIngresoVario.btnAgregarRelaSeleccionada}" id="btnAgregarRelaSeleccionada"
														styleClass="buttonAgregarAjax" reRender="tablaImputaciones"/>
													<ui:staticText text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" escape="false" id="stSeparador"/>
													<ui:button action="#{comunes$ABMIngresoVario$ABMIngresoVario.btnAgregarCuenta_action}" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;"
														binding="#{comunes$ABMIngresoVario$ABMIngresoVario.btnAgregarCuenta}" id="btnAgregarCuenta" escape="false"/>
													<a4j:commandButton action="#{comunes$ABMIngresoVario$ABMIngresoVario.btnQuitarCuenta_action}"
														binding="#{comunes$ABMIngresoVario$ABMIngresoVario.btnQuitarCuenta}" id="btnQuitarCuenta" 
														value="Quitar" styleClass="btnAjax" reRender="tablaImputaciones"/>
												</ui:panelGroup>
											</f:facet>
										</ui:table>
										<tr>
											<td align="right" colspan="3" id="tdTotal">
												<ui:label binding="#{comunes$ABMIngresoVario$ABMIngresoVario.lblTotal}" id="lblTotal" styleClass="label" text="Total:" />
												<ui:staticText binding="#{comunes$ABMIngresoVario$ABMIngresoVario.stTotal}" id="stTotal" styleClass="staticText" />
											</td>
										</tr>
									</td>
								</tr>
								<tr>
									<td colspan="2">
									<a4j:outputPanel ajaxRendered="true">
										<ui:messageGroup binding="#{comunes$ABMIngresoVario$ABMIngresoVario.messageGroup}" id="messageGroup"
											styleClass="grupoMsg" />
									</a4j:outputPanel>
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="2" nowrap="nowrap">
										<ui:button action="#{comunes$ABMIngresoVario$ABMIngresoVario.btnGuardar_action}"
											binding="#{comunes$ABMIngresoVario$ABMIngresoVario.btnGuardar}" id="btnGuardar" styleClass="button"
											text="Guardar" />
										<ui:staticText binding="#{comunes$ABMIngresoVario$ABMIngresoVario.stSeparador}" escape="false"
											id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{comunes$ABMIngresoVario$ABMIngresoVario.btnCancelar_action}"
											binding="#{comunes$ABMIngresoVario$ABMIngresoVario.btnCancelar}" id="btnCancelar" styleClass="button"
											text="Cancelar" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{comunes$ABMIngresoVario$ABMIngresoVario.hidIdPagina}" id="hidIdPagina"
						text="#{comunes$ABMIngresoVario$ABMIngresoVario.idPagina}" />
					<ui:hiddenField binding="#{comunes$ABMIngresoVario$ABMIngresoVario.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{comunes$ABMIngresoVario$ABMIngresoVario.idSubSesion}" />
					<ui:script binding="#{comunes$ABMIngresoVario$ABMIngresoVario.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
