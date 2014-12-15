<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.page1}" id="page1">
			<ui:html binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.html1}" id="html1">
			<ui:head binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.head1}" id="head1" title="Administración de Volantes Catastrales">
				<ui:link binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.body1}" focus="form1:btnSeleccionarParcela" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.head1.title}" />
								</caption>
								<tr>
									<td></td>
								</tr>
								<tbody>
									<tr>
										<td align="center">
											<ui:panelGroup binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.label1}" id="label1" style="" styleClass="label"
																text="Parcela" />
														</td>
														<td>
															<ui:textField binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.tfParcela}" columns="40" disabled="true"
																id="tfParcela" styleClass="textField" />
															<ui:button action="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.btnSeleccionarParcela_action}"
																binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.btnSeleccionarParcela}" escape="false"
																id="btnSeleccionarParcela" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.label2}" for="tfNroVolanteCatastral" id="label2"
																styleClass="label" text="Nº de Volante Catastral" />
														</td>
														<td>
															<ui:textField binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.tfNroVolanteCatastral}"
																id="tfNroVolanteCatastral" styleClass="textField" />
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
											<a4j:commandButton binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.btnBuscar}"
												action="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.btnReiniciar_action}"
												binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1" />
											<ui:staticText binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.btnCancelar_action}"
												binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.table1}" id="table1">
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
											<ui:tableRowGroup binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.tableRowGroup1}"
												emptyDataMsg="Ningún registro encontrado." id="tableRowGroup1" onMouseOver="jsRowMouseOver(this)"
												onMouseOut="jsRowMouseOut(this)" onClick="jsRowMouseClick(this)" onDblClick="funcionSeleccionar()"
												sourceData="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.ldpVolanteCatastral}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.tableColumn1}" id="tableColumn1"
													valign="middle" width="10">
													<ui:radioButton binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.radioButton1}" id="radioButton1" label=""
														name="buttonGroup" onClick="checkUncheck(this)" selected="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.RBSelected}"
														selectedValue="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.tableColumn3}"
													headerText="Nº de Volante Catastral" id="tableColumn3" sort="nroVolanteCatastral">
													<ui:staticText binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.staticText3}" id="staticText3"
														text="#{currentRow.value['nroVolanteCatastral']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.tableColumn4}" headerText="Parcela"
													id="tableColumn4" sort="parcela">
													<ui:staticText binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.staticText4}" id="staticText4"
														text="#{currentRow.value['parcela']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.tableColumn2}" headerText="Avalúo del Terreno"
													id="tableColumn2" sort="avaluoTotalTerreno">
													<ui:staticText binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.staticText1}" id="staticText1"
														text="#{currentRow.value['avaluoTotalTerreno']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.tableColumn5}" headerText="Avalúo de Mejoras"
													id="tableColumn5" sort="avaluoTotalMejoras">
													<ui:staticText binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.staticText5}" id="staticText5"
														text="#{currentRow.value['avaluoTotalMejoras']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.tableColumn6}" headerText="Fecha"
													id="tableColumn6" sort="fecha">
													<ui:staticText binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.staticText7}" id="staticText7"
														text="#{currentRow.value['fecha']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.tableColumn7}" headerText="Radio Céntrico"
													id="tableColumn7" sort="radio" width="10">
													<ui:checkbox binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.checkbox1}" disabled="true" id="checkbox1"
														selected="#{currentRow.value['radio']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.groupPanel1}" id="groupPanel1" style="">
													<ui:button action="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.btnConsultar_action}"
														binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.btnConsultar}" id="btnConsultar" styleClass="button"
														text="Consultar" />
													<ui:staticText binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.staticText6}" escape="false" id="staticText6"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.btnExportar_action}"
														binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.btnExportar}" id="btnExportar" styleClass="button"
														text="Exportar" onClick="return exportarReporte()" />
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
                    document.getElementById('form1:btnSeleccionarParcela').focus();
                        </script>
					<ui:hiddenField binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.hidIdPagina}" id="hidIdPagina"
						text="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.idPagina}" />
					<ui:hiddenField binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.idSubSesion}" />
					<ui:script binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.scriptFinal1}" id="scriptFinal1" />
					<ui:script binding="#{catastro$ABMVolanteCatastral$AdminVolanteCatastral.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
