<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.head1}" id="head1"
				title="Administración de Planes de Cuenta para Obras">
				<ui:link binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.link1}" id="link1"
					url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.body1}" focus="form1:tfNombre"
				id="body1" onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.stTitulo}" id="stTitulo"
										styleClass="tituloABM" text="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.head1.title}" />
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
											<ui:panelGroup binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.pgParametros}"
												id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.label1}" id="label1"
																style="" styleClass="label" text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.tfNombre}" columns="40"
																id="tfNombre" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.label2}" id="label2"
																styleClass="label" text="Obra" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.tfObra}" columns="40"
																disabled="true" id="tfObra" styleClass="textField" />
															<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.btnSeleccionarObra_action}"
																binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.btnSeleccionarObra}" escape="false"
																id="btnSeleccionarObra" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarObra" reRender="form1:tfObra" title="Limpiar"
																binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.btnLimpiarObra}"
																action="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.btnLimpiarObra_action}"
																styleClass="buttonLimpiarAjax" />
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
											<a4j:commandButton binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.btnBuscar}"
												action="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.btnBuscar_action}" id="btnBuscar"
												value="Buscar" styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.btnReiniciar_action}"
												binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.btnReiniciar}" id="btnReiniciar"
												styleClass="btnAjax" value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.staticText2}"
												escape="false" id="staticText2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.btnCancelar_action}"
												binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.btnCancelar}" id="btnCancelar"
												styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.messageGroup}"
										id="messageGroup" showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.table1}" id="table1" width="359">
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
											<ui:tableRowGroup binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.tableRowGroup1}"
												emptyDataMsg="Ningún registro encontrado." id="tableRowGroup1" onMouseOver="jsRowMouseOver(this)"
												onMouseOut="jsRowMouseOut(this)" onClick="jsRowMouseClick(this)" onDblClick="funcionSeleccionar()"
												sourceData="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.ldpPlanCuentaObras}"
												sourceVar="currentRow">
												<ui:tableColumn align="center"
													binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.tableColumn1}" id="tableColumn1"
													valign="middle" width="10">
													<ui:radioButton binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.radioButton1}"
														id="radioButton1" label="" name="buttonGroup" onClick="checkUncheck(this)"
														selected="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.RBSelected}"
														selectedValue="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.tableColumn2}"
													headerText="Nombre" id="tableColumn2" sort="nombre">
													<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.staticText1}"
														id="staticText1" text="#{currentRow.value['nombre']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.tableColumn4}"
													headerText="Cant. Cuotas" id="tableColumn4" sort="cantidadCuotas">
													<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.staticText4}"
														id="staticText4" text="#{currentRow.value['cantidadCuotas']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.tableColumn5}"
													headerText="Tasa Anual" id="tableColumn5" sort="tasaAnual">
													<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.staticText5}"
														id="staticText5" text="#{currentRow.value['tasaAnual']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.tableColumn3}"
													headerText="Periodicidad" id="tableColumn3" sort="periodicidad">
													<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.staticText3}"
														id="staticText3" text="#{currentRow.value['periodicidad']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.groupPanel1}"
													id="groupPanel1" style="">
													<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.btnSeleccionar_action}"
														binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.btnSeleccionar}" id="btnSeleccionar"
														styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.staticText6}"
														escape="false" id="staticText6" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.btnAgregar_action}"
														binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.btnAgregar}" id="btnAgregar"
														styleClass="button" text="Agregar" />
													<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.btnModificar_action}"
														binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.btnModificar}" id="btnModificar"
														styleClass="button" text="Modificar" />
													<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.btnEliminar_action}"
														binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.btnEliminar}" id="btnEliminar"
														styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.staticText8}"
														escape="false" id="staticText8" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.btnConsultar_action}"
														binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.btnConsultar}" id="btnConsultar"
														styleClass="button" text="Consultar" />
													<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.staticText9}"
														escape="false" id="staticText9" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.btnExportar_action}"
														binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.btnExportar}" id="btnExportar"
														styleClass="button" text="Exportar" onClick="return exportarReporte()" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.hidIdSubSesion}"
						id="hidIdSubSesion" text="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AdminPlanCuentaObra.scriptValidador}"
						id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
