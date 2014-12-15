<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.page1}" id="page1">
			<ui:html binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.html1}" id="html1">
			<ui:head binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.head1}" id="head1"
				title="Administración Línea de Solicitud de Suministro">
				<ui:link binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.body1}" focus="form1:btnSeleccionarSolSuministro" id="body1"
				onLoad="parent.footer.location.reload();Init(); changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="verde">
								<caption>
									<ui:staticText binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.head1.title}" />
								</caption>
								<tr>
									<td></td>
								</tr>
								<tbody>
									<tr>
										<td align="center">
											<ui:panelGroup binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.lblBienProvisto}" id="lblBienProvisto"
																styleClass="label" text="Para el Bien Provisto" />
														</td>
														<td nowrap="nowrap">
															<ui:staticText binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.stBienProvisto}" id="stBienProvisto" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.lblSolicitudSuministro}"
																id="lblSolicitudSuministro" styleClass="label" text="Solicitud Suministro" />
														</td>
														<td>
															<ui:textField binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.tfSolSuministro}" columns="40"
																disabled="true" id="tfSolSuministro" styleClass="textField" />
															<ui:button action="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.btnSeleccionarSolSuministro_action}"
																binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.btnSeleccionarSolSuministro}" escape="false"
																id="btnSeleccionarSolSuministro" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarSolSuministro" reRender="form1:tfSolSuministro"
																binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.btnLimpiarSolSuministro}"
																action="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.btnLimpiarSolSuministro_action}"
																styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td></td>
													</tr>
													<tr>
														<td colspan="4" style="height: 20px">
															<hr />
															<br />
														</td>
													</tr>
													<tr>
														<td align="right" colspan="2">
															<a4j:commandButton binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.btnBuscar}"
																action="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.btnBuscar_action}" id="btnBuscar" value="Buscar"
																styleClass="btnAjax" reRender="form1:table1" oncomplete="changeStyleAlIngresar()" />
															<a4j:commandButton action="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.btnReiniciar_action}"
																binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
																value="Reiniciar" reRender="form1:pgParametros,form1:table1" />
															<ui:staticText binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.staticText9}" escape="false"
																id="staticText2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
															<ui:button action="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.btnCancelar_action}"
																binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.btnCancelar}" id="btnCancelar" styleClass="button"
																text="Cancelar" />
														</td>
													</tr>
													<tr>
														<td colspan="4" style="height: 20px"></td>
													</tr>
													<tr>
														<td colspan="4" style="height: 19px">
															<ui:label binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.lblTexto1}" id="lblTexto1" styleClass="label2"
																text="Seleccione las líneas de Solicitud Suministro que conformarán el listado de Líneas de la Orden de Compra" />
															<!-- Solicitudes de Suministro: Agregar primero una solicitud de suministro -->
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.table1}" id="table1">
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
																		table
																				.initAllRows();
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
																			rowGroupId,
																			selected) {
																		var table = document
																				.getElementById("form1:table1");
																		table
																				.selectGroupRows(
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
																<ui:tableRowGroup binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.tableRowGroup1}"
																	emptyDataMsg="Ningún registro encontrado." id="tableRowGroup1" onMouseOver="jsRowMouseOver(this)"
																	onMouseOut="jsRowMouseOut(this)" onClick="jsRowMouseClick(this)" onDblClick="funcionSeleccionar()"
																	selected="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.currentRowSelected}"
																	sourceData="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.ldpLineaFactura}" sourceVar="currentRow">
																	<ui:tableColumn align="center" binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.tableColumn7}"
																		id="tableColumn7" onClick="setTimeout('initAllRows()', 0)" valign="middle" width="10">
																		<ui:checkbox binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.checkbox1}" id="checkbox1"
																			selected="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.selected}" onClick="checkUncheck(this)"
																			selectedValue="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.selectedValue}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.tableColumn2}" headerText="Bien Asociado"
																		id="tableColumn2" sort="bienAsociado">
																		<ui:staticText binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.staticText1}" id="staticText1"
																			text="#{currentRow.value['bienAsociado']}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.tableColumn3}" headerText="Cantidad"
																		id="tableColumn3" sort="cantidad">
																		<ui:staticText binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.staticText3}" id="staticText3"
																			text="#{currentRow.value['cantidad']}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.tableColumn6}"
																		headerText="Precio Unitario" id="tableColumn6" sort="precio">
																		<ui:staticText binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.staticText10}" id="staticText10"
																			text="#{currentRow.value['valorEstimado']}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.tableColumn5}"
																		headerText="Cuenta Asociada" id="tableColumn5" sort="cuentaRfr">
																		<ui:staticText binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.staticText7}" id="staticText7"
																			text="#{currentRow.value['cuentaRfr']}" />
																	</ui:tableColumn>
																</ui:tableRowGroup>
																<f:facet name="actionsTop">
																	<ui:panelGroup binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.groupPanel1}" id="groupPanel1" style="">
																		<ui:button action="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.btnSeleccionar_action}"
																			binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.btnSeleccionar}" id="btnSeleccionar"
																			styleClass="button" text="Seleccionar" />
																		<ui:staticText binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.staticText8}" escape="false"
																			id="staticText8" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
																		<!--<ui:button action="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.btnQuitar_action}"
                                                           binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.btnQuitar}" id="btnQuitar"
                                                           styleClass="button" text="Quitar"/>
                                                <ui:staticText binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.staticText6}"
                                                               escape="false" id="staticText6" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                                <ui:button action="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.btnQuitarTodos_action}"
                                                           binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.btnQuitarTodos}" id="btnQuitarTodos"
                                                           styleClass="button" text="Quitar Todos"/>-->
																	</ui:panelGroup>
																</f:facet>
															</ui:table>
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<div>
																<a4j:outputPanel ajaxRendered="true">
																	<ui:messageGroup binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.messageGroup}" id="messageGroup"
																		showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
																</a4j:outputPanel>
															</div>
														</td>
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfCodigo').focus();
					</script>
					<ui:hiddenField binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.idSubSesion}" />
					<ui:script binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{compras$ABMSolicitudSuministro$AdminLineaSolSuministro.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
