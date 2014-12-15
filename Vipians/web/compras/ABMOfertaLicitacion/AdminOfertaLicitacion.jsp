<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.page1}" id="page1">
			<ui:html binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.html1}" id="html1">
			<ui:head binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.head1}" id="head1"
				title="Administración de Ofertas de Licitaciones">
				<ui:link binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.body1}" focus="form1:btnSeleccionarLicitacion" id="body1"
				onLoad="parent.footer.location.reload(); Init();changeStyleAlIngresar(); "
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.head1.title}" />
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
											<ui:panelGroup binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.lblLicitacion}" for="tfLicitacion" id="lblLicitacion"
																styleClass="label" text="Licitación" />
														</td>
														<td>
															<ui:textField binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.tfLicitacion}" columns="30" id="tfLicitacion"
																styleClass="textField" disabled="true" />
															<ui:button action="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.btnSeleccionarLicitacion_action}"
																binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.btnSeleccionarLicitacion}" escape="false"
																id="btnSeleccionarLicitacion" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<ui:button action="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.btnLimpiarLicitacion_action}"
																binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.btnLimpiarLicitacion}" escape="false" id="btnLimpiarLicitacion"
																mini="true" styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.lblProveedor}" for="tfProveedor" id="lblProveedor"
																styleClass="label" text="Proveedor" />
														</td>
														<td>
															<ui:textField binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.tfProveedor}" columns="30" id="tfProveedor"
																styleClass="textField" disabled="true" />
															<ui:button action="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.btnSeleccionarProveedor_action}"
																binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.btnSeleccionarProveedor}" escape="false"
																id="btnSeleccionarProveedor" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<ui:button action="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.btnLimpiarProveedor_action}"
																binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.btnLimpiarProveedor}" escape="false" id="btnLimpiarProveedor"
																mini="true" styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.lblFechaOferta}" id="lblFechaOferta"
																styleClass="label" text="Fecha Oferta" for="tfFechaOferta" />
														</td>
														<td>
															<ui:textField binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.tfFechaOferta}" id="tfFechaOferta"
																styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
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
											<a4j:commandButton binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.btnBuscar}"
												action="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.btnReiniciar_action}"
												binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1" />
											<ui:staticText binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.stSeparador1}" escape="false" id="stSeparador1"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.btnCancelar_action}"
												binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.table1}" id="table1">
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
                                                    }]]></script>
											<ui:tableRowGroup binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.tableRowGroup1}"
												emptyDataMsg="Ningún registro encontrado." id="tableRowGroup1" onMouseOver="jsRowMouseOver(this)"
												onMouseOut="jsRowMouseOut(this)" onClick="jsRowMouseClick(this)" onDblClick="funcionSeleccionar()"
												sourceData="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.ldpOfertasLicitaciones}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.tableColumn1}" id="tableColumn1"
													valign="middle" width="10">
													<ui:radioButton binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.radioButton1}" id="radioButton1" label=""
														name="buttonGroup" onClick="checkUncheck(this)" selected="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.RBSelected}"
														selectedValue="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.tcProveedor}" headerText="Proveedor"
													id="tcProveedor" sort="proveedor">
													<ui:staticText binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.stProveedor}" id="stProveedor"
														text="#{currentRow.value['proveedor']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.tcFechaOferta}" headerText="Fecha Oferta"
													id="tcFechaOferta" sort="fechaOferta">
													<ui:staticText binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.stFechaOferta}" id="stFechaOferta"
														text="#{currentRow.value['fechaOferta']}" converter="#{compras$ABMLicitacion$AdminLicitacion.dateTimeConverter1}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.tcImporte}" headerText="Importe" id="tcImporte"
													sort="importe">
													<ui:staticText binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.stImporte}" id="stImporte"
														text="#{currentRow.value['importe']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.tcPlazo}" headerText="Plazo" id="tcPlazo"
													sort="plazo">
													<ui:staticText binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.stPlazo}" id="stPlazo"
														text="#{currentRow.value['plazo']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.tcEstado}" headerText="Estado" id="tcEstado"
													sort="estado">
													<ui:staticText binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.stEstado}" id="stEstado"
														text="#{currentRow.value['estado']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.groupPanel1}" id="groupPanel1">
													<ui:button action="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.btnSeleccionar_action}"
														binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
														text="Seleccionar" />
													<ui:staticText binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.stSeparadorSeleccionar}" escape="false"
														id="stSeparador2" />
													<ui:button action="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.btnAgregar_action}"
														binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.btnModificar_action}"
														binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.btnModificar}" id="btnModificar" styleClass="button"
														text="Modificar" />
													<ui:button action="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.btnEliminar_action}"
														binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.btnEliminar}" id="btnEliminar" styleClass="button"
														text="Eliminar" />
													<ui:staticText binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.stSeparadorAccion}" escape="false"
														id="stSeparador3" />
													<ui:button action="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.btnConsultar_action}"
														binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.btnConsultar}" id="btnConsultar" styleClass="button"
														text="Consultar" />
													<ui:staticText binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.stSeparador4}" escape="false" id="stSeparador4"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.btnExportar_action}"
														binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.btnExportar}" id="btnExportar" styleClass="button"
														text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_1" />
													<ui:imageHyperlink binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
                    document.getElementById('form1:btnSeleccionarLicitacion').focus();
                        </script>
					<ui:hiddenField binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.idSubSesion}" />
					<ui:script binding="#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
