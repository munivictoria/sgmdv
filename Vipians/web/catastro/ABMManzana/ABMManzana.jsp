<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{catastro$ABMManzana$ABMManzana.page1}" id="page1">
			<ui:html binding="#{catastro$ABMManzana$ABMManzana.html1}" id="html1">
			<ui:head binding="#{catastro$ABMManzana$ABMManzana.head1}" id="head1">
				<ui:link binding="#{catastro$ABMManzana$ABMManzana.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{catastro$ABMManzana$ABMManzana.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{catastro$ABMManzana$ABMManzana.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{catastro$ABMManzana$ABMManzana.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{catastro$ABMManzana$ABMManzana.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{catastro$ABMManzana$ABMManzana.label4}" for="tfNombre" id="label4" styleClass="label" text="Nombre" />
									</td>
									<td>
										<ui:textField binding="#{catastro$ABMManzana$ABMManzana.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{catastro$ABMManzana$ABMManzana.label5}" for="tfNroManzana" id="label5" styleClass="label"
											text="Número de Manzana" />
									</td>
									<td>
										<ui:textField binding="#{catastro$ABMManzana$ABMManzana.tfNroManzana}" onKeyPress="return ValidarNum(event,this)" columns="10"
											id="tfNroManzana" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<hr />
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:label binding="#{catastro$ABMManzana$ABMManzana.label1}" id="label1" styleClass="label2" text="Cuadras Limitantes" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{catastro$ABMManzana$ABMManzana.table1}" id="table1">
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
											<ui:tableRowGroup binding="#{catastro$ABMManzana$ABMManzana.tableRowGroup1}" id="tableRowGroup1"
												sourceData="#{catastro$ABMManzana$ABMManzana.ldpCuadrasPorManzana}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{catastro$ABMManzana$ABMManzana.tableColumn1}" id="tableColumn1" valign="middle"
													width="10">
													<ui:radioButton binding="#{catastro$ABMManzana$ABMManzana.radioButton1}" id="radioButton1" label="" name="buttonGroup"
														selected="#{catastro$ABMManzana$ABMManzana.RBSelected}" selectedValue="#{catastro$ABMManzana$ABMManzana.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{catastro$ABMManzana$ABMManzana.tableColumn2}" headerText="Cuadra" id="tableColumn2" sort="nombre"
													width="40">
													<ui:staticText binding="#{catastro$ABMManzana$ABMManzana.staticText1}" id="staticText1" text="#{currentRow.value['nombre']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{catastro$ABMManzana$ABMManzana.groupPanel1}" id="groupPanel1">
													<ui:button action="#{catastro$ABMManzana$ABMManzana.btnAgregar_action}" binding="#{catastro$ABMManzana$ABMManzana.btnAgregar}"
														id="btnAgregar" styleClass="button" text="Agregar" />
													<a4j:commandButton action="#{catastro$ABMManzana$ABMManzana.btnQuitar_action}"
														binding="#{catastro$ABMManzana$ABMManzana.btnQuitar}" id="btnQuitar" value="Quitar" styleClass="btnAjax" reRender="table1"
														onmousedown="reemplazarClickConConfirmacion(this, '');" />
													<ui:staticText binding="#{catastro$ABMManzana$ABMManzana.staticText4}" escape="false" id="staticText4"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<a4j:commandButton action="#{catastro$ABMManzana$ABMManzana.btnQuitarTodos_action}"
														binding="#{catastro$ABMManzana$ABMManzana.btnQuitarTodos}" id="btnQuitarTodos" value="Quitar todos" styleClass="btnAjax"
														reRender="table1"
														onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea quitar todos los elementos de esta lista?');" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<!--        <tr>
                                            <td colspan="4">
                                <ui:table augmentTitle="false" binding="#{catastro$ABMManzana$ABMManzana.table2}" id="table2">
                                    
                                    <ui:tableRowGroup binding="#{catastro$ABMManzana$ABMManzana.tableRowGroup2}" id="tableRowGroup2"
                                                      sourceData="#{catastro$ABMManzana$ABMManzana.ldpAtributosManzana}" sourceVar="currentRow">
                                        <ui:tableColumn align="center" binding="#{catastro$ABMManzana$ABMManzana.tableColumn3}"
                                                        id="tableColumn3" rendered="false" valign="middle" width="10">
                                            <ui:radioButton binding="#{catastro$ABMManzana$ABMManzana.radioButton2}" id="radioButton2"
                                                            label="" name="buttonGroup" selected="#{catastro$ABMManzana$ABMManzana.RBSelected}" selectedValue="#{catastro$ABMManzana$ABMManzana.currentRow}"/>
                                        </ui:tableColumn> 
                                        <ui:tableColumn binding="#{catastro$ABMManzana$ABMManzana.tableColumn4}" headerText="Atributo"
                                                        id="tableColumn4" sort="nombre" width="50">
                                            <ui:staticText binding="#{catastro$ABMManzana$ABMManzana.staticText2}" id="staticText2" text="#{currentRow.value['nombre']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{catastro$ABMManzana$ABMManzana.tableColumn5}" headerText="Valor"
                                                        id="tableColumn5" sort="valorString" width="10">
                                            <ui:textField binding="#{catastro$ABMManzana$ABMManzana.textField1}" id="textField1"
                                                          styleClass="textField" text="#{currentRow.value['valorString']}"/>                                                
                                        </ui:tableColumn>
                                    </ui:tableRowGroup>

                                </ui:table>
                            </td>
                        </tr> -->
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:label binding="#{catastro$ABMManzana$ABMManzana.label6}" id="label6" styleClass="label5" text="Zonas" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:table id="table2" width="50%">
											<ui:tableRowGroup id="tableRowGroup2" binding="#{catastro$ABMManzana$ABMManzana.tableRowGroup2}"
												sourceData="#{catastro$ABMManzana$ABMManzana.ldpZonas}" sourceVar="currentRowZona">
												<ui:tableColumn align="center" id="tableColumn3" binding="#{catastro$ABMManzana$ABMManzana.tableColumn3}" valign="middle"
													width="10">
													<ui:radioButton id="radioButton2" binding="#{catastro$ABMManzana$ABMManzana.radioButton2}" label="" name="buttonGroup"
														selected="#{catastro$ABMManzana$ABMManzana.RBSelected2}" selectedValue="#{catastro$ABMManzana$ABMManzana.currentRow2}" />
												</ui:tableColumn>
												<ui:tableColumn id="tableColumn4" headerText="Nombre" sort="nombre" binding="#{catastro$ABMManzana$ABMManzana.tableColumn4}">
													<ui:staticText id="staticText5" binding="#{catastro$ABMManzana$ABMManzana.staticText5}" text="#{currentRowZona.value['zona']}" />
												</ui:tableColumn>
												<ui:tableColumn id="tableColumn5" headerText="Zonificación" sort="#{currentRowZona.value.zona.zonificacion.nombre}"
													binding="#{catastro$ABMManzana$ABMManzana.tableColumn5}">
													<ui:staticText id="staticText6" binding="#{catastro$ABMManzana$ABMManzana.staticText6}"
														text="#{currentRowZona.value['zona'].zonificacion.nombre}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup id="groupPanel2" binding="#{catastro$ABMManzana$ABMManzana.groupPanel2}">
													<ui:button id="btnAgregarZona" styleClass="button" text="Agregar" toolTip="Agregar Zona"
														binding="#{catastro$ABMManzana$ABMManzana.btnAgregarZona}" action="#{catastro$ABMManzana$ABMManzana.btnAgregarZona_action}" />
													<a4j:commandButton id="btnQuitarZona" value="Quitar" styleClass="btnAjax" reRender="table2"
														onmousedown="reemplazarClickConConfirmacion(this, '');" binding="#{catastro$ABMManzana$ABMManzana.btnQuitarZona}"
														action="#{catastro$ABMManzana$ABMManzana.btnQuitarZona_action}" />
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
									<td colspan="4">
										<div class="div" style="width: 290px; height: 15px;">Atributos Dinámicos</div>
										<table border="0" class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
											<tr>
												<td colspan="4">
													<ui:panelGroup binding="#{catastro$ABMManzana$ABMManzana.panelAtributoDinamico}" id="panelAtributoDinamico">
														<!-- AQUI VA LO QUE SE CREA DINAMICAMENTE -->
													</ui:panelGroup>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right">
										<ui:label binding="#{catastro$ABMManzana$ABMManzana.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
									</td>
									<td>
										<ui:textArea binding="#{catastro$ABMManzana$ABMManzana.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{catastro$ABMManzana$ABMManzana.tablaLogs}" id="tbLogsAuditoria" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{catastro$ABMManzana$ABMManzana.messageGroup1}" id="messageGroup1" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{catastro$ABMManzana$ABMManzana.btnGuardar_action}" binding="#{catastro$ABMManzana$ABMManzana.btnGuardar}"
											id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{catastro$ABMManzana$ABMManzana.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{catastro$ABMManzana$ABMManzana.btnCancelar_action}" binding="#{catastro$ABMManzana$ABMManzana.btnCancelar}"
											id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{catastro$ABMManzana$ABMManzana.hidIdPagina}" id="hidIdPagina"
						text="#{catastro$ABMManzana$ABMManzana.idPagina}" />
					<ui:hiddenField binding="#{catastro$ABMManzana$ABMManzana.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{catastro$ABMManzana$ABMManzana.idSubSesion}" />
					<ui:script binding="#{catastro$ABMManzana$ABMManzana.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
