<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.page1}" id="page1">
			<ui:html binding="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.html1}" id="html1">
			<ui:head binding="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.head1}" id="head1"
				title="Administración de Tipos de Transacciones Catastrales">
				<ui:link binding="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.link1}" id="link1"
					url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.stTitulo}" id="stTitulo"
										styleClass="tituloABM" text="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.head1.title}" />
								</caption>
								<tr>
									<td></td>
								</tr>
								<tbody>
									<tr>
										<td align="center">
											<ui:panelGroup binding="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.label1}" id="label1" style=""
																styleClass="label" text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.tfNombre}" columns="40"
																id="tfNombre" styleClass="textField" />
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
											<a4j:commandButton binding="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.btnBuscar}"
												action="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.btnReiniciar_action}"
												binding="#{compras$ABMBien$AdminBien.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1" />
											<ui:staticText binding="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.staticText2}" escape="false"
												id="staticText2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.btnCancelar_action}"
												binding="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.messageGroup}" id="messageGroup"
										showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.table1}" id="table1">
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
											<ui:tableRowGroup binding="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.tableRowGroup1}"
												emptyDataMsg="Ningún registro encontrado." id="tableRowGroup1" onMouseOver="jsRowMouseOver(this)"
												onMouseOut="jsRowMouseOut(this)" onClick="jsRowMouseClick(this)" onDblClick="funcionSeleccionar()"
												sourceData="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.ldpTipoTransaccionCatastral}"
												sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.tableColumn1}"
													id="tableColumn1" valign="middle" width="10">
													<ui:radioButton binding="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.radioButton1}" id="radioButton1"
														label="" name="buttonGroup" onClick="checkUncheck(this)"
														selected="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.RBSelected}"
														selectedValue="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.tableColumn2}" headerText="Nombre"
													id="tableColumn2" sort="nombre">
													<ui:staticText binding="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.staticText1}" id="staticText1"
														text="#{currentRow.value['nombre']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.groupPanel1}" id="groupPanel1"
													style="">
													<ui:button action="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.btnSeleccionar_action}"
														binding="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.btnSeleccionar}" id="btnSeleccionar"
														styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.staticText6}" escape="false"
														id="staticText6" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.btnAgregar_action}"
														binding="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.btnAgregar}" id="btnAgregar" styleClass="button"
														text="Agregar" />
													<ui:button action="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.btnModificar_action}"
														binding="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.btnModificar}" id="btnModificar"
														styleClass="button" text="Modificar" />
													<ui:button action="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.btnEliminar_action}"
														binding="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.btnEliminar}" id="btnEliminar"
														styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.staticText8}" escape="false"
														id="staticText8" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.btnConsultar_action}"
														binding="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.btnConsultar}" id="btnConsultar"
														styleClass="button" text="Consultar" />
													<ui:staticText binding="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.staticText9}" escape="false"
														id="staticText9" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.btnExportar_action}"
														binding="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.btnExportar}" id="btnExportar"
														styleClass="button" text="Exportar" onClick="return exportarReporte()" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
							</table>
						</div>
					</div>
					<script>
                    document.getElementById('form1:tfNombre').focus();
                        </script>
					<ui:hiddenField binding="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.hidIdPagina}" id="hidIdPagina"
						text="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.idPagina}" />
					<ui:hiddenField binding="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.idSubSesion}" />
					<ui:script binding="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{catastro$ABMTipoTransaccionCatastral$AdminTipoTransaccionCatastral.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
