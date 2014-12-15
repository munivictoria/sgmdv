<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMTema$AdminTema.page1}" id="page1">
			<ui:html binding="#{framework$ABMTema$AdminTema.html1}" id="html1">
			<ui:head binding="#{framework$ABMTema$AdminTema.head1}" id="head1" title="Administración de Temas">
				<ui:link binding="#{framework$ABMTema$AdminTema.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{framework$ABMTema$AdminTema.body1}" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMTema$AdminTema.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{framework$ABMTema$AdminTema.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{framework$ABMTema$AdminTema.head1.title}" />
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
											<ui:panelGroup binding="#{framework$ABMTema$AdminTema.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{framework$ABMTema$AdminTema.label2}" for="tfTema" id="label2" styleClass="label" text="Tema" />
														</td>
														<td>
															<ui:textField binding="#{framework$ABMTema$AdminTema.tfTema}" columns="40" id="tfTema" styleClass="textField" />
														</td>
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
								</tbody>
								<tr>
									<td></td>
								</tr>
								<tfoot>
									<tr>
										<td align="right" colspan="2">
											<a4j:commandButton binding="#{framework$ABMTema$AdminTema.btnBuscar}" action="#{framework$ABMTema$AdminTema.btnBuscar_action}"
												id="btnBuscar" value="Buscar" styleClass="btnAjax" reRender="form1:table1" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{framework$ABMTema$AdminTema.btnReiniciar_action}"
												binding="#{framework$ABMTema$AdminTema.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1" />
											<ui:staticText binding="#{framework$ABMTema$AdminTema.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{framework$ABMTema$AdminTema.btnCancelar_action}" binding="#{framework$ABMTema$AdminTema.btnCancelar}"
												id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{framework$ABMTema$AdminTema.messageGroup}" id="messageGroup" showDetail="true" showSummary="false" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table augmentTitle="false" binding="#{framework$ABMTema$AdminTema.paginatedTable}" id="table1" paginationControls="true"
											sortPanelToggleButton="true" style="align:center">
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
											<ui:tableRowGroup binding="#{framework$ABMTema$AdminTema.tableRowGroup1}" emptyDataMsg="Ningún registro encontrado."
												id="tableRowGroup1" rows="#{ApplicationBean1.cantidadFilasTablasAdmin}" onMouseOver="jsRowMouseOver(this)"
												onMouseOut="jsRowMouseOut(this)" onClick="jsRowMouseClick(this)" onDblClick="funcionSeleccionar()"
												sourceData="#{framework$ABMTema$AdminTema.ldpTemas}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{framework$ABMTema$AdminTema.tableColumn1}" id="tableColumn1" valign="middle"
													width="10">
													<ui:radioButton binding="#{framework$ABMTema$AdminTema.radioButton1}" id="radioButton1" label="" name="buttonGroup"
														onClick="checkUncheck(this)" selected="#{framework$ABMTema$AdminTema.RBSelected}"
														selectedValue="#{framework$ABMTema$AdminTema.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{framework$ABMTema$AdminTema.tableColumn2}" headerText="Nombre" id="tableColumn2" sort="nombre">
													<ui:staticText binding="#{framework$ABMTema$AdminTema.staticText1}" id="staticText1" text="#{currentRow.value['nombre']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{framework$ABMTema$AdminTema.tableColumn3}" headerText="Descripción" id="tableColumn3"
													sort="descripcion">
													<ui:staticText binding="#{framework$ABMTema$AdminTema.staticText3}" id="staticText3" text="#{currentRow.value['descripcion']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{framework$ABMTema$AdminTema.groupPanel1}" id="groupPanel1" style="">
													<ui:button action="#{framework$ABMTema$AdminTema.btnSeleccionar_action}"
														binding="#{framework$ABMTema$AdminTema.btnSeleccionar}" id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{framework$ABMTema$AdminTema.stSeparadorSeleccionar}" escape="false" id="staticText6" />
													<ui:button action="#{framework$ABMTema$AdminTema.btnAgregar_action}" binding="#{framework$ABMTema$AdminTema.btnAgregar}"
														id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{framework$ABMTema$AdminTema.btnModificar_action}" binding="#{framework$ABMTema$AdminTema.btnModificar}"
														id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{framework$ABMTema$AdminTema.btnEliminar_action}" binding="#{framework$ABMTema$AdminTema.btnEliminar}"
														id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{framework$ABMTema$AdminTema.stSeparadorAccion}" escape="false" id="staticText8" />
													<ui:button action="#{framework$ABMTema$AdminTema.btnConsultar_action}" binding="#{framework$ABMTema$AdminTema.btnConsultar}"
														id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:staticText binding="#{framework$ABMTema$AdminTema.staticText7}" escape="false" id="staticText7"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<!--
                                                <ui:button action="#{framework$ABMTema$AdminTema.btnImprimirReporte_action}"
                                                           binding="#{framework$ABMTema$AdminTema.btnImprimirReporte}" id="btnImprimirReporte"
                                                           disabled="true" styleClass="button" text="Visualizar Listado" onClick="newWindow = window.open('ImprimirTemabtnLimpiarGrupoBien.jsp', '_parent')"/>
                                                <ui:staticText binding="#{framework$ABMTema$AdminTema.staticText9}" escape="false" id="staticText9" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                                -->
													<ui:button action="#{framework$ABMTema$AdminTema.btnExportar_action}" binding="#{framework$ABMTema$AdminTema.btnExportar}"
														id="btnExportar" styleClass="button" text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText binding="#{framework$ABMTema$AdminTema.paginatedTable.stSeparadorOrdenamiento}" id="separador_1" />
													<ui:imageHyperlink binding="#{framework$ABMTema$AdminTema.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfTema').focus();
					</script>
					<ui:hiddenField binding="#{framework$ABMTema$AdminTema.hidIdPagina}" id="hidIdPagina" text="#{framework$ABMTema$AdminTema.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMTema$AdminTema.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMTema$AdminTema.idSubSesion}" />
					<ui:script binding="#{framework$ABMTema$AdminTema.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
