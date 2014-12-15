<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.page1}" id="page1">
			<ui:html binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.html1}" id="html1">
			<ui:head binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.head1}" id="head1" title="Administración de Ficha Social">
				<ui:link binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.body1}" focus="form1:tfNumeroFichaSocial" id="body1"
				onLoad="parent.footer.location.reload();Init(); changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{accionsocial$ABMFichaSocial$AdminFichaSocial.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td colspan="2"></td>
									</tr>
									<tr>
										<td align="center" nowrap="nowrap">
											<ui:staticText escape="false" id="stFiltrarPor" styleClass="textoFiltrarPor" text="Filtrar por" />
										</td>
									</tr>
									<tr>
										<td colspan="4">
											<hr />
										</td>
									</tr>
									<tr>
										<td align="center">
											<ui:panelGroup binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.label5}" id="label5" style="" styleClass="label"
																text="Número Ficha" />
														</td>
														<td>
															<ui:textField binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.tfNumeroFichaSocial}" columns="10"
																id="tfNumeroFichaSocial" onKeyPress="return ValidarNum(event,this)" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.label1}" id="label1" style="" styleClass="label"
																text="Titular" />
														</td>
														<td>
															<ui:textField binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.tfBeneficiarioTitular}" columns="40" disabled="true"
																id="tfBeneficiarioTitular" styleClass="textField" />
															<ui:button action="#{accionsocial$ABMFichaSocial$AdminFichaSocial.btnSeleccionarBeneficiarioPF_action}"
																binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.btnSeleccionarBeneficiarioPF}" escape="false"
																id="btnSeleccionarBeneficiarioPF" mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Física" />
															<ui:button action="#{accionsocial$ABMFichaSocial$AdminFichaSocial.btnSeleccionarBeneficiarioPJ_action}"
																binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.btnSeleccionarBeneficiarioPJ}" escape="false"
																id="btnSeleccionarBeneficiarioPJ" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar Persona Jurídica" />
															<ui:button action="#{accionsocial$ABMFichaSocial$AdminFichaSocial.btnLimpiarBeneficiario_action}"
																binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.btnLimpiarBeneficiario}" escape="false" id="btnLimpiarBeneficiario"
																mini="true" styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.label4}" id="label4" style="" styleClass="label"
																text="Familiar" />
														</td>
														<td>
															<ui:textField binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.tfFamiliar}" columns="40" disabled="true"
																id="tfFamiliar" styleClass="textField" />
															<ui:button action="#{accionsocial$ABMFichaSocial$AdminFichaSocial.btnSeleccionarFamiliar_action}"
																binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.btnSeleccionarFamiliar}" escape="false" id="btnSeleccionarFamiliar"
																mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<ui:button action="#{accionsocial$ABMFichaSocial$AdminFichaSocial.btnLimpiarFamiliar_action}"
																binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.btnLimpiarFamiliar}" escape="false" id="btnLimpiarFamiliar"
																mini="true" styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.label3}" id="label3" style="" styleClass="label"
																text="Beneficio" />
														</td>
														<td>
															<ui:textField binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.tfBeneficio}" columns="40" disabled="true"
																id="tfBeneficio" styleClass="textField" />
															<ui:button action="#{accionsocial$ABMFichaSocial$AdminFichaSocial.btnSeleccionarBeneficio_action}"
																binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.btnSeleccionarBeneficio}" escape="false"
																id="btnSeleccionarBeneficio" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<ui:button action="#{accionsocial$ABMFichaSocial$AdminFichaSocial.btnLimpiarBeneficio_action}"
																binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.btnLimpiarBeneficio}" escape="false" id="btnLimpiarBeneficio"
																mini="true" styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar" />
														</td>
													</tr>
													<tr>
														<td></td>
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
								</tbody>
								<tfoot>
									<tr>
										<td align="right" colspan="2">
											<a4j:commandButton binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.btnBuscar}"
												action="#{accionsocial$ABMFichaSocial$AdminFichaSocial.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{accionsocial$ABMFichaSocial$AdminFichaSocial.btnReiniciar_action}"
												binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1,stCantidadRegistros" />
											<ui:staticText binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{accionsocial$ABMFichaSocial$AdminFichaSocial.btnCancelar_action}"
												binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<!-- </div>
                                -->
							<div>
								<ui:messageGroup binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.messageGroup}" id="messageGroup" showDetail="true"
									showSummary="false" styleClass="grupoMsgAdmin" />
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.table1}" id="table1">
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
											<ui:tableRowGroup binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.tableRowGroup1}"
												emptyDataMsg="Ningún registro encontrado." id="tableRowGroup1" onMouseOver="jsRowMouseOver(this)"
												onMouseOut="jsRowMouseOut(this)" onClick="jsRowMouseClick(this)" onDblClick="funcionSeleccionar()"
												sourceData="#{accionsocial$ABMFichaSocial$AdminFichaSocial.ldpFichaSocial}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.tableColumn1}" id="tableColumn1"
													valign="middle" width="10">
													<ui:radioButton binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.radioButton1}" id="radioButton1" label=""
														name="buttonGroup" onClick="checkUncheck(this)" selected="#{accionsocial$ABMFichaSocial$AdminFichaSocial.RBSelected}"
														selectedValue="#{accionsocial$ABMFichaSocial$AdminFichaSocial.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.tableColumn5}" headerText="Número" id="tableColumn5"
													sort="numero">
													<ui:staticText binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.staticText9}" id="staticText9"
														text="#{currentRow.value['numero']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.tableColumn2}" headerText="Fecha" id="tableColumn2"
													sort="fecha">
													<ui:staticText binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.staticText10}" id="staticText10"
														text="#{currentRow.value['fecha']}" converter="#{accionsocial$ABMFichaSocial$AdminFichaSocial.dateTimeConverter1}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.tableColumn3}" headerText="Titular" id="tableColumn3"
													sort="titular">
													<ui:staticText binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.staticText1}" id="staticText1"
														text="#{currentRow.value['titular']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.groupPanel1}" id="groupPanel1">
													<ui:button action="#{accionsocial$ABMFichaSocial$AdminFichaSocial.btnSeleccionar_action}"
														binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
														text="Seleccionar" />
													<ui:staticText binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.staticText6}" escape="false" id="staticText6"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{accionsocial$ABMFichaSocial$AdminFichaSocial.btnAgregar_action}"
														binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{accionsocial$ABMFichaSocial$AdminFichaSocial.btnModificar_action}"
														binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.btnModificar}" id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{accionsocial$ABMFichaSocial$AdminFichaSocial.btnEliminar_action}"
														binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.staticText8}" escape="false" id="staticText8"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{accionsocial$ABMFichaSocial$AdminFichaSocial.btnConsultar_action}"
														binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.btnConsultar}" id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:staticText binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.staticText11}" escape="false" id="staticText11"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{accionsocial$ABMFichaSocial$AdminFichaSocial.btnExportar_action}"
														binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
														onClick="return exportarReporte()" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNumeroFichaSocial').focus();
					</script>
					<ui:hiddenField binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.hidIdPagina}" id="hidIdPagina"
						text="#{accionsocial$ABMFichaSocial$AdminFichaSocial.idPagina}" />
					<ui:hiddenField binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{accionsocial$ABMFichaSocial$AdminFichaSocial.idSubSesion}" />
					<ui:script binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{accionsocial$ABMFichaSocial$AdminFichaSocial.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
