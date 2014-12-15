<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{saic$ABMReliquidacion$ReliquidarVarias.page1}" id="page1">
			<ui:html binding="#{saic$ABMReliquidacion$ReliquidarVarias.html1}" id="html1">
			<ui:head binding="#{saic$ABMReliquidacion$ReliquidarVarias.head1}" id="head1">
				<ui:link binding="#{saic$ABMReliquidacion$ReliquidarVarias.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					document.getElementById("form1:grpCargando").style.display = "none";

					function activarDesactivarTfValor() {
						var tabla = document.getElementById('form1:table2');

						for( var i = 1; i < tabla.rows.length; ++i) {
							var tipoValor = tabla.rows[i].cells[2].childNodes[0].value;

							if(tipoValor == "ACTUAL") {
								tabla.rows[i].cells[3].childNodes[0].value = "";
								tabla.rows[i].cells[3].childNodes[0].disabled = true;
							} else if(tipoValor == "FIJO") {
								tabla.rows[i].cells[3].childNodes[0].disabled = false;
							}
						}
					}

					]]>
				</script>
			</ui:head>
			<ui:body binding="#{saic$ABMReliquidacion$ReliquidarVarias.body1}" id="body1"
				onLoad="parent.footer.location.reload();Init();activarDesactivarTfValor();" onKeyPress="eventoByEnter(event,'btnGuardar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$ABMReliquidacion$ReliquidarVarias.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{saic$ABMReliquidacion$ReliquidarVarias.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{saic$ABMReliquidacion$ReliquidarVarias.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ABMReliquidacion$ReliquidarVarias.lblFecha}" for="tfFecha" id="lblFecha" styleClass="label"
											text="Fecha de Nuevo Vencimiento" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{saic$ABMReliquidacion$ReliquidarVarias.tfFecha}" columns="12" id="tfFecha" styleClass="textField"
											disabled="false" />
										<!--<ui:staticText binding="#{saic$ABMReliquidacion$ReliquidarVarias.stFecha}" escape="false" id="stFecha"
                                                           styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
										<!-- <ui:label id="lblAclaracion" styleClass="label3" text="Nota: Si desea mantener la fecha de vencimiento de la liquidación, deje el campo vacío o con la fecha que posee por defecto."/>-->
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ABMReliquidacion$ReliquidarVarias.lblDigesto}" for="tfDigesto" id="lblDigesto" styleClass="label"
											text="Decreto, Ordenanza, Resolución" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{saic$ABMReliquidacion$ReliquidarVarias.tfDigesto}" columns="60" disabled="true" id="tfDigesto"
											styleClass="textField" />
										<ui:button action="#{saic$ABMReliquidacion$ReliquidarVarias.btnSeleccionarDigesto_action}"
											binding="#{saic$ABMReliquidacion$ReliquidarVarias.btnSeleccionarDigesto}" escape="false" id="btnSeleccionarDigesto" mini="true"
											styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarDigesto" reRender="form1:tfDigesto"
											binding="#{saic$ABMReliquidacion$ReliquidarVarias.btnLimpiarDigesto}"
											action="#{saic$ABMReliquidacion$ReliquidarVarias.btnLimpiarDigesto_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ABMReliquidacion$ReliquidarVarias.lblAplicarInteres}" for="cbAplicarInteres" id="lblAplicarInteres"
											styleClass="label" text="Aplicar intereses" />
									</td>
									<td>
										<ui:checkbox binding="#{saic$ABMReliquidacion$ReliquidarVarias.cbAplicarInteres}" id="cbAplicarInteres" selected="true" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:staticText binding="#{saic$ABMReliquidacion$ReliquidarVarias.stSeleccionados}" id="stSeleccionados" styleClass="label2"
											text="Seleccionados" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{saic$ABMReliquidacion$ReliquidarVarias.table1}" id="table1">
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
											<ui:tableRowGroup binding="#{saic$ABMReliquidacion$ReliquidarVarias.tableRowGroup1}" emptyDataMsg="Ningún registro encontrado."
												id="tableRowGroup1" rows="#{ApplicationBean1.cantidadFilasTablasAdmin}"
												sourceData="#{saic$ABMReliquidacion$ReliquidarVarias.ldpLiquidaciones}" sourceVar="currentRow">
												<ui:tableColumn binding="#{saic$ABMReliquidacion$ReliquidarVarias.tcPeriodo}" headerText="Período" id="tcPeriodo" sort="periodo">
													<ui:staticText binding="#{saic$ABMReliquidacion$ReliquidarVarias.stPeriodo}" id="stPeriodo"
														text="#{currentRow.value['stringPeriodoLiquidado']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMReliquidacion$ReliquidarVarias.tcPersona}" headerText="Persona" id="tcPersona" sort="persona">
													<ui:textArea binding="#{saic$ABMReliquidacion$ReliquidarVarias.taObligacion}" id="taObligacion"
														text="#{currentRow.value['stringObligacion']}" disabled="true" styleClass="textFieldDisabled" rows="1" columns="70" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMReliquidacion$ReliquidarVarias.tcMonto}" headerText="Monto" id="tcMonto" sort="monto">
													<ui:staticText binding="#{saic$ABMReliquidacion$ReliquidarVarias.stMonto}"
														converter="#{saic$ABMReliquidacion$ReliquidarVarias.numberConverter1}" id="stMonto" text="#{currentRow.value['monto']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMReliquidacion$ReliquidarVarias.tcTipoDeuda}" headerText="Tipo de Deuda" id="tcTipoDeuda"
													sort="tipoDeuda">
													<ui:staticText binding="#{saic$ABMReliquidacion$ReliquidarVarias.stTipoDeuda}" id="stTipoDeuda"
														text="#{currentRow.value['tipoDeuda']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMReliquidacion$ReliquidarVarias.tcEstado}" headerText="Estado" id="tcEstado" sort="estado">
													<ui:staticText binding="#{saic$ABMReliquidacion$ReliquidarVarias.stEstado}" id="stEstado" text="#{currentRow.value['estado']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td colspan="2">
										<div>Agregue a la tabla el/los parámetro/s y elija el valor que tomará/n para la Reliquidación de las Tasas.</div>
										<hr />
									</td>
								</tr>
								<tr align="left" nowrap="nowrap">
									<td colspan="2">
										<table>
											<tr>
												<td>
													<ui:listbox binding="#{saic$ABMReliquidacion$ReliquidarVarias.lbVariables}" id="lbVariables"
														items="#{saic$ABMReliquidacion$ReliquidarVarias.lbVariablesDefaultOptions.options}" onDblClick="agregarAListBox(this.id);"
														rows="6" styleClass="textField" toolTip="Parámetros Actualmente Utilizados en la Fórmula de la Tasa" />
												</td>
												<td nowrap="nowrap">
													<a4j:commandButton action="#{saic$ABMReliquidacion$ReliquidarVarias.btnAddParametro_action}"
														binding="#{saic$ABMReliquidacion$ReliquidarVarias.btnAddParametro}" id="btnAddParametro" styleClass="buttonAgregarAjax"
														reRender="table2" oncomplete="activarDesactivarTfValor();" />
													<a4j:commandButton action="#{saic$ABMReliquidacion$ReliquidarVarias.btnDelParametro_action}"
														binding="#{saic$ABMReliquidacion$ReliquidarVarias.btnDelParametro}" id="btnDelParametro" styleClass="buttonRemoveAjax"
														reRender="table2" oncomplete="activarDesactivarTfValor();" />
												</td>
												<td>
													<ui:table augmentTitle="false" binding="#{saic$ABMReliquidacion$ReliquidarVarias.table2}" id="table2">
														<ui:tableRowGroup binding="#{saic$ABMReliquidacion$ReliquidarVarias.tableRowGroup2}" id="tableRowGroup2"
															sourceData="#{saic$ABMReliquidacion$ReliquidarVarias.ldpParametros}" sourceVar="currentRow2">
															<ui:tableColumn align="center" id="tableColumnSeleccion"
																binding="#{saic$ABMReliquidacion$ReliquidarVarias.tableColumnSeleccion}" valign="middle" width="10">
																<ui:radioButton id="radioButton1" binding="#{saic$ABMReliquidacion$ReliquidarVarias.radioButton1}" label=""
																	name="buttonGroup" selected="#{saic$ABMReliquidacion$ReliquidarVarias.RBSelected1}"
																	selectedValue="#{saic$ABMReliquidacion$ReliquidarVarias.currentRow1}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{saic$ABMReliquidacion$ReliquidarVarias.tableColumn1}" headerText="Nombre Parámetro"
																id="tableColumn1">
																<ui:staticText binding="#{saic$ABMReliquidacion$ReliquidarVarias.staticText2}" id="staticText2"
																	text="#{currentRow2.value['nombreParametro']}" />
															</ui:tableColumn>
															<ui:tableColumn align="center" binding="#{saic$ABMReliquidacion$ReliquidarVarias.tableColumn2}" headerText="Tipo Valor"
																id="tableColumn2">
																<ui:dropDown binding="#{saic$ABMReliquidacion$ReliquidarVarias.ddTipoValor}" id="ddValor"
																	items="#{saic$ABMReliquidacion$ReliquidarVarias.ddTipoValorOptions.options}" styleClass="textField"
																	selected="#{currentRow2.value['tipoValor']}" converter="EnumConverter" immediate="false"
																	onChange="activarDesactivarTfValor();" />
															</ui:tableColumn>
															<ui:tableColumn align="center" binding="#{saic$ABMReliquidacion$ReliquidarVarias.tableColumn3}" headerText="Valor"
																id="tableColumn3">
																<ui:textField binding="#{saic$ABMReliquidacion$ReliquidarVarias.textField1}" id="textField1"
																	text="#{currentRow2.value['valor']}" />
															</ui:tableColumn>
														</ui:tableRowGroup>
													</ui:table>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr align="left" nowrap="nowrap">
									<td colspan="2">
										<table>
											<tr>
												<td>
													<ui:listbox binding="#{saic$ABMReliquidacion$ReliquidarVarias.lbVariablesAlicuota}" id="lbVariablesAlicuota"
														items="#{saic$ABMReliquidacion$ReliquidarVarias.lbVariablesAlicuotaDefaultOptions.options}"
														onDblClick="agregarAListBox(this.id);" rows="6" styleClass="textField"
														toolTip="Parámetros Actualmente Utilizados en la Fórmula de la Tasa" />
												</td>
												<td>
													<a4j:commandButton action="#{saic$ABMReliquidacion$ReliquidarVarias.btnAddParametroAlicuota_action}"
														binding="#{saic$ABMReliquidacion$ReliquidarVarias.btnAddParametroAlicuota}" id="btnAddParametroAlicuota"
														styleClass="buttonAgregarAjax" />
													<a4j:commandButton action="#{saic$ABMReliquidacion$ReliquidarVarias.btnDelParametroAlicuota_action}"
														binding="#{saic$ABMReliquidacion$ReliquidarVarias.btnDelParametroAlicuota}" id="btnDelParametroAlicuota"
														styleClass="buttonRemoveAjax" />
												</td>
												<td>
													<ui:listbox binding="#{saic$ABMReliquidacion$ReliquidarVarias.lbVariablesACargarAlicuota}" id="lbVariablesACargarAlicuota"
														toolTip="Parámetros que Tomarán los Valores Actuales"
														items="#{saic$ABMReliquidacion$ReliquidarVarias.lbVariablesACargarAlicuotaDefaultOptions.options}" rows="6" style="width:50%"
														styleClass="textField" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{saic$ABMReliquidacion$ReliquidarVarias.messageGroup1}" id="messageGroup1" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{saic$ABMReliquidacion$ReliquidarVarias.btnGuardar_action}"
											binding="#{saic$ABMReliquidacion$ReliquidarVarias.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{saic$ABMReliquidacion$ReliquidarVarias.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{saic$ABMReliquidacion$ReliquidarVarias.btnCancelar_action}"
											binding="#{saic$ABMReliquidacion$ReliquidarVarias.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{saic$ABMReliquidacion$ReliquidarVarias.hidIdPagina}" id="hidIdPagina"
						text="#{saic$ABMReliquidacion$ReliquidarVarias.idPagina}" />
					<ui:hiddenField binding="#{saic$ABMReliquidacion$ReliquidarVarias.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$ABMReliquidacion$ReliquidarVarias.idSubSesion}" />
					<ui:script binding="#{saic$ABMReliquidacion$ReliquidarVarias.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
