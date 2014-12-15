<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.page1}" id="page1">
			<ui:html binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.html1}" id="html1">
			<ui:head binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.head1}" id="head1">
				<ui:link binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.body1}" id="body1" onLoad="parent.footer.location.reload();Init();"
				onKeyPress="eventoByEnter(event,'btnGuardar')" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{saic$ImprimirLiquidaciones$ABMLiquidacion.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.lblTasas}" for="ddTasas" id="lblTasas" styleClass="label"
											text="Tasas" />
									</td>
									<td>
										<ui:dropDown binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.ddTasas}" id="ddTasas" styleClass="textField"
											items="#{saic$ImprimirLiquidaciones$ABMLiquidacion.ddTasasDefaultOptions.options}"
											onChange="#{saic$ImprimirLiquidaciones$ABMLiquidacion.seleccionarListas}" />
										<!--onChange="this.form.submit()"
                                                                valueChangeListener="#{saic$ImprimirLiquidaciones$ABMLiquidacion.valueChangeEvent(event)}"-->
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.lblBasico}" for="tfBasico" id="lblBasico" styleClass="label"
											text="Básico" />
									</td>
									<td>
										<ui:textField binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.tfBasico}" columns="20" id="tfBasico" styleClass="textField"
											onKeyPress="return ValidarFloat(event,this)" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.lblIntereses}" for="tfIntereses" id="lblIntereses"
											styleClass="label" text="Interés" />
									</td>
									<td>
										<ui:textField binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.tfIntereses}" columns="20" id="tfIntereses"
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
										<ui:staticText binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.stModificadores}" id="stModificadores" styleClass="label2"
											text="Modificadores" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.table1}" id="table1">
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
											<ui:tableRowGroup binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.tableRowGroup1}"
												emptyDataMsg="Ningún registro encontrado." id="tableRowGroup1"
												sourceData="#{saic$ImprimirLiquidaciones$ABMLiquidacion.ldpModificadores}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.tableColumn1}" id="tableColumn1"
													valign="middle" width="10">
													<ui:radioButton binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.radioButton1}" id="radioButton1" label=""
														name="buttonGroup" selected="#{saic$ImprimirLiquidaciones$ABMLiquidacion.RBSelected}"
														selectedValue="#{saic$ImprimirLiquidaciones$ABMLiquidacion.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.tcNombre}" headerText="Nombre" id="tcNombre" sort="nombre"
													width="40">
													<ui:textField binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.tfNombre}" id="tfNombre"
														text="#{currentRow.value['nombre']}" columns="40" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.tcValorModificador}" headerText="Valor"
													id="tcValorModificador" sort="valorModificador" width="20">
													<ui:textField binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.tfValorModificador}" id="tfValorModificador"
														text="#{currentRow.value['valorModificador']}" columns="20" />
												</ui:tableColumn>
												<!--<ui:tableColumn align="center" binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.tcCheckbox}"
                                                                    id="tcCheckbox" valign="middle" width="10" onClick="setTimeout('initAllRows()', 0)">
                                                        <ui:checkbox binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.checkBox1}"
                                                                     id="checkBox1" label="" selected="#{currentRow.value['']}" selectedValue="#{saic$ImprimirLiquidaciones$ABMLiquidacion.currentRow}"/>
                                                    </ui:tableColumn>-->
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.groupPanel1}" id="groupPanel1">
													<ui:button action="#{saic$ImprimirLiquidaciones$ABMLiquidacion.btnAgregarModificador_action}"
														binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.btnAgregarModificador}" id="btnAgregarModificador" styleClass="button"
														text="Agregar" />
													<ui:button action="#{saic$ImprimirLiquidaciones$ABMLiquidacion.btnQuitarModificador_action}"
														binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.btnQuitarModificador}" id="btnQuitarModificador" styleClass="button"
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
										<ui:staticText binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.stAlicuotasLiquidadas}" id="stAlicuotasLiquidadas"
											styleClass="label2" text="Alicuotas Liquidadas" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.table3}" id="table3">
											<script>
												<![CDATA[
                                                    /* ----- Functions for Table Preferences Panel ----- */
                                                    /*
                                                     * Toggle the table preferences panel open or closed
                                                     */
                                                    function togglePreferencesPanel() {
                                                        var table = document.getElementById("form1:table3");
                                                        table.toggleTblePreferencesPanel();
                                                    }
                                                    /* ----- Functions for Filter Panel ----- */
                                                    /*
                                                     * Return true if the filter menu has actually changed,
                                                     * so the corresponding event should be allowed to continue.
                                                     */
                                                    function filterMenuChanged() {
                                                        var table = document.getElementById("form1:table3");
                                                        return table.filterMenuChanged();
                                                    }
                                                    /*
                                                     * Toggle the custom filter panel (if any) open or closed.
                                                     */
                                                    function toggleFilterPanel() {
                                                        var table = document.getElementById("form1:table3");
                                                        return table.toggleTableFilterPanel();
                                                    }
                                                    /* ----- Functions for Table Actions ----- */
                                                    /*
                                                     * Initialize all rows of the table when the state
                                                     * of selected rows changes.
                                                     */
                                                    function initAllRows() {
                                                        var table = document.getElementById("form1:table3");
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
                                                        var table = document.getElementById("form1:table3");
                                                        table.selectGroupRows(rowGroupId, selected);
                                                    }
                                                    /*
                                                     * Disable all table actions if no rows have been selected.
                                                     */
                                                    function disableActions() {
                                                        // Determine whether any rows are currently selected
                                                        var table = document.getElementById("form1:table3");
                                                        var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
                                                        // Set disabled state for top actions
                                                        document.getElementById("form1:table3:tableActionsTop:deleteTop").setDisabled(disabled);
                                                        // Set disabled state for bottom actions
                                                        document.getElementById("form1:table3:tableActionsBottom:deleteBottom").setDisabled(disabled);
                                                    }]]></script>
											<ui:tableRowGroup binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.tableRowGroup3}"
												emptyDataMsg="Ningún registro encontrado." id="tableRowGroup3"
												sourceData="#{saic$ImprimirLiquidaciones$ABMLiquidacion.ldpAlicuotasLiquidadas}" sourceVar="currentRow3">
												<ui:tableColumn align="center" binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.tableColumn3}" id="tableColumn3"
													valign="middle" width="10">
													<ui:radioButton binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.radioButton3}" id="radioButton3" label=""
														name="buttonGroup3" selected="#{saic$ImprimirLiquidaciones$ABMLiquidacion.RBSelected3}"
														selectedValue="#{saic$ImprimirLiquidaciones$ABMLiquidacion.currentRow3}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.tableColumn4}" headerText="Nombre" id="tableColumn4"
													sort="regAlicuota.nombre" width="40">
													<ui:textField binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.textField1}" id="textField1"
														text="#{currentRow3.value['regAlicuota'].nombre}" columns="40" disabled="true" styleClass="textField"/>
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.tableColumn5}" headerText="Valor" id="tableColumn5"
													sort="valor" width="20">
													<ui:textField binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.textField2}" id="textField2"
														text="#{currentRow3.value['valor']}" columns="20" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.groupPanel3}" id="groupPanel3">
													<ui:button action="#{saic$ImprimirLiquidaciones$ABMLiquidacion.btnQuitarAlicuotaLiquidada_action}"
														binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.btnQuitarAlicuotaLiquidada}" id="btnQuitarAlicuotaLiquidada"
														styleClass="button" text="Quitar" />
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
										<ui:staticText binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.stVencimientos}" id="stVencimientos" styleClass="label2"
											text="Vencimientos" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.table2}" id="table2">
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
											<ui:tableRowGroup binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.tableRowGroup2}"
												emptyDataMsg="Ningún registro encontrado." id="tableRowGroup2"
												sourceData="#{saic$ImprimirLiquidaciones$ABMLiquidacion.ldpVencimientos}" sourceVar="currentRow2">
												<ui:tableColumn align="center" binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.tableColumn2}" id="tableColumn2"
													valign="middle" width="10">
													<ui:radioButton binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.radioButton2}" id="radioButton2" label=""
														name="buttonGroup2" selected="#{saic$ImprimirLiquidaciones$ABMLiquidacion.RBSelected2}"
														selectedValue="#{saic$ImprimirLiquidaciones$ABMLiquidacion.currentRow2}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.tcNombreVencimiento}" headerText="Nombre"
													id="tcNombreVencimiento" sort="nombre" width="40">
													<ui:textField binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.tfNombreVencimiento}" id="tfNombreVencimiento"
														text="#{currentRow2.value['nombre']}" columns="40" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.tcFechaVencimiento}" headerText="Fecha"
													id="tcValorModificador" sort="fecha" width="10">
													<ui:textField binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.tfFechaVencimiento}" id="tfFechaVencimiento"
														onKeyUp="mascara(this,'/',patronFecha,true)" text="#{currentRow2.value['fecha']}"
														converter="#{saic$ImprimirLiquidaciones$ABMLiquidacion.dateTimeConverter1}" columns="10" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.tcValorVencimiento}" headerText="Valor"
													id="tcValorVencimiento" sort="valor" width="10">
													<ui:textField binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.tfValorVencimiento}" id="tfValorVencimiento"
														text="#{currentRow2.value['valor']}" columns="10" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.groupPanel2}" id="groupPanel2">
													<ui:button action="#{saic$ImprimirLiquidaciones$ABMLiquidacion.btnAgregarVencimiento_action}"
														binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.btnAgregarVencimiento}" id="btnAgregarVencimiento" styleClass="button"
														text="Agregar" />
													<ui:button action="#{saic$ImprimirLiquidaciones$ABMLiquidacion.btnQuitarVencimiento_action}"
														binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.btnQuitarVencimiento}" id="btnQuitarVencimiento" styleClass="button"
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
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.lblComentario}" for="taComentario" id="lblComentario"
											styleClass="label" text="Comentario" />
									</td>
									<td>
										<ui:textArea binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.taComentario}" columns="40" id="taComentario"
											styleClass="textField" rows="5" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.messageGroup1}" id="messageGroup1" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{saic$ImprimirLiquidaciones$ABMLiquidacion.btnGuardar_action}"
											binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{saic$ImprimirLiquidaciones$ABMLiquidacion.btnCancelar_action}"
											binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.hidIdPagina}" id="hidIdPagina"
						text="#{saic$ImprimirLiquidaciones$ABMLiquidacion.idPagina}" />
					<ui:hiddenField binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$ImprimirLiquidaciones$ABMLiquidacion.idSubSesion}" />
					<ui:script binding="#{saic$ImprimirLiquidaciones$ABMLiquidacion.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
