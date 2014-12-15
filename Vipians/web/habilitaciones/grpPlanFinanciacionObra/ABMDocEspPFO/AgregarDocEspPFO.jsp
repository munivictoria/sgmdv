<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.head1}" id="head1"
				title="Agregar Obligación: Planes de Financiación de Obras">
				<ui:link binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.link1}" id="link1"
					url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.body1}"
				focus="form1:btnSeleccionarPlantillaObligacion" id="body1" onLoad="parent.footer.location.reload();Init();"
				style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.stTitulo}" id="stTitulo"
									styleClass="tituloABM" text="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.label4}" for="tfPlantillaObligacion"
											id="label4" styleClass="label" text="Plantilla de Obligación" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.tfPlantillaObligacion}" columns="40"
											disabled="true" id="tfPlantillaObligacion" styleClass="textField" />
										<ui:button
											action="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.btnSeleccionarPlantillaObligacion_action}"
											binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.btnSeleccionarPlantillaObligacion}"
											escape="false" id="btnSeleccionarPlantillaObligacion" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;"
											toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarPlantillaObligacion" reRender="form1:tfPlantillaObligacion"
											binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.btnLimpiarPlantillaObligacion}"
											action="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.btnLimpiarPlantillaObligacion_action}"
											styleClass="buttonLimpiarAjax" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.label2}" for="tfFechaInicio" id="label2"
											rendered="false" styleClass="label" text="Inicio de Actividades" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.tfFechaInicio}" id="tfFechaInicio"
											rendered="false" styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
										<!--<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.staticText11}"
                                                           escape="false" id="staticText11" rendered="false" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
									</td>
								</tr>
								<tr></tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.label16}" for="tfObra" id="label16"
											styleClass="label" text="Obra" />
									</td>
									<td>
										<ui:textField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.tfObra}" columns="40"
											disabled="true" id="tfObra" styleClass="textField" />
										<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.btnSeleccionarObra_action}"
											binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.btnSeleccionarObra}" escape="false"
											id="btnSeleccionarObra" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarObra" reRender="form1:tfObra"
											binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.btnLimpiarObra}"
											action="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.btnLimpiarObra_action}"
											styleClass="buttonLimpiarAjax" />
									</td>
									<td align="right" nowrap="nowrap"></td>
									<td nowrap="nowrap"></td>
								</tr>
								<tr>
									<td colspan="4">
										<hr />
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.label1}" id="label1" styleClass="label2"
											text="Planes de Cuenta" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.table1}"
											id="table1" width="360">
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
											<ui:tableRowGroup binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.tableRowGroup1}"
												id="tableRowGroup1" sourceData="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.ldpPlanCuentaObraPFO}"
												sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.tableColumn1}"
													id="tableColumn1" valign="middle" width="10">
													<ui:radioButton binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.radioButton1}"
														id="radioButton1" label="" name="buttonGroup"
														selected="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.RBSelected}"
														selectedValue="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.tableColumn2}"
													headerText="Nombre" id="tableColumn2" sort="nombre">
													<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.staticText1}" id="staticText1"
														text="#{currentRow.value['nombre']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.tableColumn3}"
													headerText="Periodicidad" id="tableColumn3" sort="periodicidad">
													<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.staticText2}" id="staticText2"
														text="#{currentRow.value['periodicidad']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
										</ui:table>
									</td>
								</tr>
								<!--       <tr>
                                        <td colspan="4">         
                                            <div  class="div" style="width: 290px; height: 15px;"> Atributos Dinámicos </div>
                                            <table border="0" class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
                                                <tr>
                                                    <td colspan="4">         
                                                        <ui:panelGroup binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.panelAtributoDinamico}" id="panelAtributoDinamico">   -->
								<!-- AQUI VA LO QUE SE CREA DINAMICAMENTE -->
								<!--                                                        </ui:panelGroup>
                                                    </td>
                                                </tr> 
                                            </table>
                                        </td>
                                    </tr> -->
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.messageGroup1}"
											id="messageGroup1" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.btnGenerarObligaciones_action}"
											binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.btnGenerarObligaciones}"
											id="btnGenerarObligaciones" styleClass="button" text="Generar Obligaciones" />
										<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.stSeparador}" escape="false"
											id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.btnCancelar_action}"
											binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.btnCancelar}" id="btnCancelar"
											styleClass="button" text="Cancelar" />
									</td>
								</tr>
							</tfoot>
						</table>
						<br /> <br /> <br />
					</div>
					<ui:hiddenField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$AgregarDocEspPFO.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
