<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.head1}" id="head1"
				title="Administración de Parámetros Parcelarios">
				<ui:link binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.link1}" id="link1"
					url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.body1}"
				focus="form1:btnBuscar" id="body1" onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.stTitulo}"
										id="stTitulo" styleClass="tituloABM"
										text="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td></td>
									</tr>
									<tr>
										<td colspan="2" nowrap="true">
											<div>
												Haga clic en <b>Buscar</b> para obtener la lista de Parámetros Parcelarios.
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
											<a4j:commandButton binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.btnBuscar}"
												action="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.btnBuscar_action}"
												id="btnBuscar" value="Buscar" styleClass="btnAjax" reRender="form1:table1" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton
												action="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.btnReiniciar_action}"
												binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.btnReiniciar}"
												id="btnReiniciar" styleClass="btnAjax" value="Reiniciar" reRender="form1:pgParametros,form1:table1" />
											<ui:staticText binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.staticText2}"
												escape="false" id="staticText2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.btnCancelar_action}"
												binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.btnCancelar}"
												id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.messageGroup}"
										id="messageGroup" showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.table1}" id="table1">
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
												binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.tableRowGroup1}"
												emptyDataMsg="Ningún registro encontrado." id="tableRowGroup1" onMouseOver="jsRowMouseOver(this)"
												onMouseOut="jsRowMouseOut(this)" onClick="jsRowMouseClick(this)" onDblClick="funcionSeleccionar()"
												sourceData="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.ldpTipoParametroParcelario}"
												sourceVar="currentRow"
												selected="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.currentRowSelected}">
												<ui:tableColumn align="center" onClick="setTimeout('initAllRows()', 0)"
													binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.tableColumn1}"
													id="tableColumn1" valign="middle" width="10">
													<ui:checkbox binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.checkbox1}"
														id="checkbox1" onClick="checkUncheck(this)"
														selected="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.selected}"
														selectedValue="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.selectedValue}" />
												</ui:tableColumn>
												<ui:tableColumn
													binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.tableColumn2}"
													headerText="Nombre de Variable" id="tableColumn2" sort="nombreVariable">
													<ui:staticText binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.staticText1}"
														id="staticText1" text="#{currentRow.value['nombreVariable']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.groupPanel1}"
													id="groupPanel1">
													<ui:button
														action="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.btnSeleccionar_action}"
														binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.btnSeleccionar}"
														id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.staticText6}"
														escape="false" id="staticText6" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button
														action="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.btnAgregar_action}"
														binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.btnAgregar}"
														disabled="true" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button
														action="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.btnModificar_action}"
														binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.btnModificar}"
														disabled="true" id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button
														action="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.btnEliminar_action}"
														binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.btnEliminar}"
														disabled="true" id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.staticText8}"
														escape="false" id="staticText8" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button
														action="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.btnExportar_action}"
														binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.btnExportar}"
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
					<ui:hiddenField binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.hidIdPagina}"
						id="hidIdPagina" text="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.hidIdSubSesion}"
						id="hidIdSubSesion" text="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.scriptFinal1}"
						id="scriptFinal1" url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroParcelario$AdminTipoParametroParcelario.scriptValidador}"
						id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
