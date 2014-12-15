<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.head1}" id="head1"
				title="Administración de Parámetros de Deuda">
				<ui:link binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.link1}" id="link1"
					url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.body1}"
				focus="form1:btnBuscar" id="body1" onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.stTitulo}"
										id="stTitulo" styleClass="tituloABM"
										text="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td></td>
									</tr>
									<tr>
										<td colspan="2" nowrap="true">
											<div>
												Haga clic en <b>Buscar</b> para obtener la lista de Parámetros de Deuda.
											</div>
										</td>
									</tr>
									<tr>
										<td></td>
									</tr>
								</tbody>
								<tfoot>
									<tr>
										<td align="right" colspan="2">
											<a4j:commandButton binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.btnBuscar}"
												action="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.btnBuscar_action}"
												id="btnBuscar" value="Buscar" styleClass="btnAjax" reRender="form1:table1" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton
												action="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.btnReiniciar_action}"
												binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.btnReiniciar}"
												id="btnReiniciar" styleClass="btnAjax" value="Reiniciar" reRender="form1:pgParametros,form1:table1" />
											<ui:staticText binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.staticText2}"
												escape="false" id="staticText2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.btnCancelar_action}"
												binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.btnCancelar}"
												id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.messageGroup}"
										id="messageGroup" showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.table1}" id="table1">
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
											<ui:tableRowGroup
												binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.tableRowGroup1}"
												emptyDataMsg="Ningún registro encontrado." id="tableRowGroup1" onMouseOver="jsRowMouseOver(this)"
												onMouseOut="jsRowMouseOut(this)" onClick="jsRowMouseClick(this)" onDblClick="funcionSeleccionar()"
												sourceData="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.ldpTipoParametroDeuda}"
												sourceVar="currentRow"
												selected="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.currentRowSelected}">
												<ui:tableColumn align="center" onClick="setTimeout('initAllRows()', 0)"
													binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.tableColumn1}"
													id="tableColumn1" valign="middle" width="10">
													<ui:checkbox binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.checkbox1}"
														id="checkbox1" onClick="checkUncheck(this)"
														selected="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.selected}"
														selectedValue="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.selectedValue}" />
												</ui:tableColumn>
												<ui:tableColumn
													binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.tableColumn2}"
													headerText="Nombre de Variable" id="tableColumn2" sort="nombreVariable">
													<ui:staticText binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.staticText1}"
														id="staticText1" text="#{currentRow.value['nombreVariable']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.groupPanel1}"
													id="groupPanel1">
													<ui:button
														action="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.btnSeleccionar_action}"
														binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.btnSeleccionar}"
														id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.staticText6}"
														escape="false" id="staticText6" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button
														action="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.btnAgregar_action}"
														binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.btnAgregar}"
														disabled="true" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button
														action="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.btnModificar_action}"
														binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.btnModificar}"
														disabled="true" id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button
														action="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.btnEliminar_action}"
														binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.btnEliminar}"
														disabled="true" id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.staticText8}"
														escape="false" id="staticText8" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button
														action="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.btnExportar_action}"
														binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.btnExportar}"
														id="btnExportar" styleClass="button" text="Exportar" onClick="return exportarReporte()" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
                    		document.getElementById('form1:btnBuscar').focus();
                        </script>
					<ui:hiddenField binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.hidIdPagina}"
						id="hidIdPagina" text="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.hidIdSubSesion}"
						id="hidIdSubSesion" text="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.scriptFinal1}"
						id="scriptFinal1" url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroDeuda$AdminTipoParametroDeuda.scriptValidador}"
						id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>