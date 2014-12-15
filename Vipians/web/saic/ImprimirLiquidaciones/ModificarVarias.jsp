<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{saic$ImprimirLiquidaciones$ModificarVarias.page1}" id="page1">
			<ui:html binding="#{saic$ImprimirLiquidaciones$ModificarVarias.html1}" id="html1">
			<ui:head binding="#{saic$ImprimirLiquidaciones$ModificarVarias.head1}" id="head1">
				<ui:link binding="#{saic$ImprimirLiquidaciones$ModificarVarias.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{saic$ImprimirLiquidaciones$ModificarVarias.body1}" id="body1" onLoad="parent.footer.location.reload();Init();"
				onKeyPress="eventoByEnter(event,'btnGuardar')" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$ImprimirLiquidaciones$ModificarVarias.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{saic$ImprimirLiquidaciones$ModificarVarias.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{saic$ImprimirLiquidaciones$ModificarVarias.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ImprimirLiquidaciones$ModificarVarias.lblTasas}" for="ddTasas" id="lblTasas" styleClass="label"
											text="Tasas" />
									</td>
									<td>
										<ui:dropDown binding="#{saic$ImprimirLiquidaciones$ModificarVarias.ddTasas}" id="ddTasas" styleClass="textField"
											items="#{saic$ImprimirLiquidaciones$ModificarVarias.ddTasasDefaultOptions.options}"
											onChange="#{saic$ImprimirLiquidaciones$ModificarVarias.seleccionarListas}" />
										<!--onChange="this.form.submit()"
                                                                valueChangeListener="#{saic$ImprimirLiquidaciones$ModificarVarias.valueChangeEvent(event)}"-->
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ImprimirLiquidaciones$ModificarVarias.lblBasico}" for="tfBasico" id="lblBasico" styleClass="label"
											text="Básico" />
									</td>
									<td>
										<ui:textField binding="#{saic$ImprimirLiquidaciones$ModificarVarias.tfBasico}" columns="20" id="tfBasico" styleClass="textField"
											onKeyPress="return ValidarFloat(event,this)" onBlur="completarTf(this)" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ImprimirLiquidaciones$ModificarVarias.lblIntereses}" for="tfIntereses" id="lblIntereses"
											styleClass="label" text="Interés" />
									</td>
									<td>
										<ui:textField binding="#{saic$ImprimirLiquidaciones$ModificarVarias.tfIntereses}" columns="20" id="tfIntereses"
											styleClass="textField" onKeyPress="return ValidarFloat(event,this)" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:staticText binding="#{saic$ImprimirLiquidaciones$ModificarVarias.stModificadores}" id="stModificadores" styleClass="label2"
											text="Modificadores" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{saic$ImprimirLiquidaciones$ModificarVarias.table1}" id="table1">
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
											<ui:tableRowGroup binding="#{saic$ImprimirLiquidaciones$ModificarVarias.tableRowGroup1}"
												emptyDataMsg="Ningún registro encontrado." id="tableRowGroup1"
												sourceData="#{saic$ImprimirLiquidaciones$ModificarVarias.ldpModificadores}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{saic$ImprimirLiquidaciones$ModificarVarias.tableColumn1}" id="tableColumn1"
													valign="middle" width="10">
													<ui:radioButton binding="#{saic$ImprimirLiquidaciones$ModificarVarias.radioButton1}" id="radioButton1" label=""
														name="buttonGroup" selected="#{saic$ImprimirLiquidaciones$ModificarVarias.RBSelected}"
														selectedValue="#{saic$ImprimirLiquidaciones$ModificarVarias.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ImprimirLiquidaciones$ModificarVarias.tcNombre}" headerText="Nombre" id="tcNombre" sort="nombre">
													<ui:textField binding="#{saic$ImprimirLiquidaciones$ModificarVarias.tfNombre}" id="tfNombre"
														text="#{currentRow.value['nombre']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ImprimirLiquidaciones$ModificarVarias.tcValorModificador}" headerText="Valor"
													id="tcValorModificador" sort="valorModificador">
													<ui:textField binding="#{saic$ImprimirLiquidaciones$ModificarVarias.tfValorModificador}" id="tfValorModificador"
														text="#{currentRow.value['valorModificador']}" />
												</ui:tableColumn>
												<!--<ui:tableColumn align="center" binding="#{saic$ImprimirLiquidaciones$ModificarVarias.tcCheckbox}"
                                                                    id="tcCheckbox" valign="middle" width="10" onClick="setTimeout('initAllRows()', 0)">
                                                        <ui:checkbox binding="#{saic$ImprimirLiquidaciones$ModificarVarias.checkBox1}"
                                                                     id="checkBox1" label="" selected="#{currentRow.value['']}" selectedValue="#{saic$ImprimirLiquidaciones$ModificarVarias.currentRow}"/>
                                                    </ui:tableColumn>-->
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{saic$ImprimirLiquidaciones$ModificarVarias.groupPanel1}" id="groupPanel1">
													<ui:button action="#{saic$ImprimirLiquidaciones$ModificarVarias.btnAgregarModificador_action}"
														binding="#{saic$ImprimirLiquidaciones$ModificarVarias.btnAgregarModificador}" id="btnAgregarModificador" styleClass="button"
														text="Agregar" />
													<ui:button action="#{saic$ImprimirLiquidaciones$ModificarVarias.btnQuitarModificador_action}"
														binding="#{saic$ImprimirLiquidaciones$ModificarVarias.btnQuitarModificador}" id="btnQuitarModificador" styleClass="button"
														text="Quitar" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:staticText binding="#{saic$ImprimirLiquidaciones$ModificarVarias.stVencimientos}" id="stVencimientos" styleClass="label2"
											text="Vencimientos" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{saic$ImprimirLiquidaciones$ModificarVarias.table2}" id="table2">
											<script>
												<![CDATA[
                                                    /* ----- Functions for Table Preferences Panel ----- */
                                                    /*
                                                     * Toggle the table preferences panel open or closed
                                                     */
                                                    function togglePreferencesPanel() {
                                                        var table = document.getElementById("form1:table2");
                                                        table.toggleTblePreferencesPanel();
                                                    }
                                                    /* ----- Functions for Filter Panel ----- */
                                                    /*
                                                     * Return true if the filter menu has actually changed,
                                                     * so the corresponding event should be allowed to continue.
                                                     */
                                                    function filterMenuChanged() {
                                                        var table = document.getElementById("form1:table2");
                                                        return table.filterMenuChanged();
                                                    }
                                                    /*
                                                     * Toggle the custom filter panel (if any) open or closed.
                                                     */
                                                    function toggleFilterPanel() {
                                                        var table = document.getElementById("form1:table2");
                                                        return table.toggleTableFilterPanel();
                                                    }
                                                    /* ----- Functions for Table Actions ----- */
                                                    /*
                                                     * Initialize all rows of the table when the state
                                                     * of selected rows changes.
                                                     */
                                                    function initAllRows() {
                                                        var table = document.getElementById("form1:table2");
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
                                                        var table = document.getElementById("form1:table2");
                                                        table.selectGroupRows(rowGroupId, selected);
                                                    }
                                                    /*
                                                     * Disable all table actions if no rows have been selected.
                                                     */
                                                    function disableActions() {
                                                        // Determine whether any rows are currently selected
                                                        var table = document.getElementById("form1:table2");
                                                        var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
                                                        // Set disabled state for top actions
                                                        document.getElementById("form1:table2:tableActionsTop:deleteTop").setDisabled(disabled);
                                                        // Set disabled state for bottom actions
                                                        document.getElementById("form1:table2:tableActionsBottom:deleteBottom").setDisabled(disabled);
                                                    }]]></script>
											<ui:tableRowGroup binding="#{saic$ImprimirLiquidaciones$ModificarVarias.tableRowGroup2}"
												emptyDataMsg="Ningún registro encontrado." id="tableRowGroup2"
												sourceData="#{saic$ImprimirLiquidaciones$ModificarVarias.ldpVencimientos}" sourceVar="currentRow2">
												<ui:tableColumn align="center" binding="#{saic$ImprimirLiquidaciones$ModificarVarias.tableColumn2}" id="tableColumn2"
													valign="middle" width="10">
													<ui:radioButton binding="#{saic$ImprimirLiquidaciones$ModificarVarias.radioButton2}" id="radioButton2" label=""
														name="buttonGroup2" selected="#{saic$ImprimirLiquidaciones$ModificarVarias.RBSelected2}"
														selectedValue="#{saic$ImprimirLiquidaciones$ModificarVarias.currentRow2}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ImprimirLiquidaciones$ModificarVarias.tcNombreVencimiento}" headerText="Nombre"
													id="tcNombreVencimiento" sort="nombre">
													<ui:textField binding="#{saic$ImprimirLiquidaciones$ModificarVarias.tfNombreVencimiento}" id="tfNombreVencimiento"
														text="#{currentRow2.value['nombre']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ImprimirLiquidaciones$ModificarVarias.tcFechaVencimiento}" headerText="Fecha"
													id="tcValorModificador" sort="fecha">
													<ui:textField binding="#{saic$ImprimirLiquidaciones$ModificarVarias.tfFechaVencimiento}" id="tfFechaVencimiento"
														onKeyUp="mascara(this,'/',patronFecha,true)" text="#{currentRow2.value['fecha']}" />
													<!--converter="#{saic$ImprimirLiquidaciones$ModificarVarias.dateTimeConverter1}" -->
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ImprimirLiquidaciones$ModificarVarias.tcValorVencimiento}" headerText="Valor"
													id="tcValorVencimiento" sort="valor" width="10">
													<ui:textField binding="#{saic$ImprimirLiquidaciones$ModificarVarias.tfValorVencimiento}" id="tfValorVencimiento"
														text="#{currentRow2.value['valor']}" columns="10" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{saic$ImprimirLiquidaciones$ModificarVarias.groupPanel2}" id="groupPanel2">
													<ui:button action="#{saic$ImprimirLiquidaciones$ModificarVarias.btnAgregarVencimiento_action}"
														binding="#{saic$ImprimirLiquidaciones$ModificarVarias.btnAgregarVencimiento}" id="btnAgregarVencimiento" styleClass="button"
														text="Agregar" />
													<ui:button action="#{saic$ImprimirLiquidaciones$ModificarVarias.btnQuitarVencimiento_action}"
														binding="#{saic$ImprimirLiquidaciones$ModificarVarias.btnQuitarVencimiento}" id="btnQuitarVencimiento" styleClass="button"
														text="Quitar" />
													<ui:button action="#{saic$ImprimirLiquidaciones$ModificarVarias.btnQuitarTodosVencimiento_action}"
														binding="#{saic$ImprimirLiquidaciones$ModificarVarias.btnQuitarTodosVencimiento}" id="btnQuitarTodosVencimiento"
														styleClass="button" text="Quitar Todos" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ImprimirLiquidaciones$ModificarVarias.lblComentario}" for="taComentario" id="lblComentario"
											styleClass="label" text="Comentario" />
									</td>
									<td>
										<ui:textArea binding="#{saic$ImprimirLiquidaciones$ModificarVarias.taComentario}" columns="40" id="taComentario"
											styleClass="textField" rows="5" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{saic$ImprimirLiquidaciones$ModificarVarias.messageGroup1}" id="messageGroup1" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{saic$ImprimirLiquidaciones$ModificarVarias.btnGuardar_action}"
											binding="#{saic$ImprimirLiquidaciones$ModificarVarias.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{saic$ImprimirLiquidaciones$ModificarVarias.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{saic$ImprimirLiquidaciones$ModificarVarias.btnCancelar_action}"
											binding="#{saic$ImprimirLiquidaciones$ModificarVarias.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{saic$ImprimirLiquidaciones$ModificarVarias.hidIdPagina}" id="hidIdPagina"
						text="#{saic$ImprimirLiquidaciones$ModificarVarias.idPagina}" />
					<ui:hiddenField binding="#{saic$ImprimirLiquidaciones$ModificarVarias.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$ImprimirLiquidaciones$ModificarVarias.idSubSesion}" />
					<ui:script binding="#{saic$ImprimirLiquidaciones$ModificarVarias.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>