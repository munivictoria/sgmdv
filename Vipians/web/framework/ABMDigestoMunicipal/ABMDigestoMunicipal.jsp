<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.page1}" id="page1">
			<ui:html binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.html1}" id="html1">
			<ui:head binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.head1}" id="head1">
				<ui:link binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.body1}" focus="form1:tfNumero" id="body1"
				onLoad="parent.footer.location.reload(); Init(); Init2();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.lblNumero}" id="lblNumero" styleClass="label" text="Número"
											for="tfNumero" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.tfNumero}" id="tfNumero"
											onKeyPress="return ValidarNum(event,this)" styleClass="textField" columns="10" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.label5}" id="label5" styleClass="label" text="Tipo" />
									</td>
									<td>
										<ui:dropDown binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.ddTipoDigesto}" id="ddTipoDigesto"
											items="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.ddTipoDigestoDefaultOptions.options}" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.label3}" id="label3" styleClass="label" text="Ámbito" />
									</td>
									<td>
										<ui:dropDown binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.ddAmbitoDigesto}" id="ddAmbitoDigesto"
											items="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.ddAmbitoDigestoDefaultOptions.options}" styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.label6}" id="label6" styleClass="label" text="Eje Temático" />
									</td>
									<td>
										<ui:dropDown binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.ddEjeTematicoDigesto}" id="ddEjeTematicoDigesto"
											items="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.ddEjeTematicoDigestoDefaultOptions.options}" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.lblFecha}" for="tfFecha" id="lblFecha" styleClass="label"
											text="Fecha Sanción" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.tfFecha}" id="tfFecha" styleClass="textField"
											onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
										<!--<ui:label id="lblFechaDigesto" styleClass="label" text=" [dd/mm/aaaa]"/>-->
									</td>
									
										<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.lblEstadoDigesto}" for="ddEstadoDigesto"
											id="lblEstadoDigesto" styleClass="label" text="Estado" />
									</td>
									<td>
										<ui:dropDown binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.ddEstadoDigesto}" id="ddEstadoDigesto"
											items="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.ddEstadoDigestoDefaultOptions.options}" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.lblDescripcion}" for="taDescripcion" id="lblDescripcion"
											styleClass="label" text="Descripción" />
									</td>
									<br />
									<td colspan="4" >
										<ui:textArea binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.taDescripcion}" columns="100" id="taDescripcion" rows="5"
											styleClass="textField" />
									</td>
								</tr>
								<!--                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.lblMotivoEstado}" for="taMotivoEstado"
                                                      id="lblMotivoEstado" styleClass="label" text="Motivo del Estado"/>
                                        </td>
                                        <td>
                                            <ui:textArea binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.taMotivoEstado}" columns="40"
                                                         id="taMotivoEstado" rows="3" styleClass="textField"/>
                                        </td>
                                    </tr>-->
								<tr>
									<td colspan="2">
										<hr />
										<br />
									</td>
								</tr>
								<tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.table1}" id="table1">
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
											<ui:tableRowGroup binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.tableRowGroup1}" id="tableRowGroup1"
												sourceData="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.ldpConcordanciasDigesto}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.tableColumn1}" id="tableColumn1"
													valign="middle" width="10">
													<ui:radioButton binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.radioButton1}" id="radioButton1" label=""
														name="buttonGroup" selected="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.RBSelected}"
														selectedValue="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.tableColumn2}" headerText="Número"
													id="tableColumn2" sort="numero" width="20">
													<ui:staticText binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.staticText2}" id="staticText2"
														text="#{currentRow.value['numero']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.tableColumn3}" headerText="Tipo" id="tableColumn3"
													sort="tipo" width="10">
													<ui:staticText binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.staticText3}" id="staticText3" styleClass="label"
														text="#{currentRow.value['tipo']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.tableColumn4}" headerText="Eje Tematico"
													id="tableColumn4" sort="tipo" width="10">
													<ui:staticText binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.staticText4}" id="staticText4" styleClass="label"
														text="#{currentRow.value['ejeTematico']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.tableColumn5}" headerText="Estado"
													id="tableColumn5" sort="tipo" width="10">
													<ui:staticText binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.staticText5}" id="staticText5" styleClass="label"
														text="#{currentRow.value['estado']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.gpBotones}" id="gpBotones">
													<ui:button action="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.btnSeleccionarDigesto_action}"
														binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.btnAgregarDigesto}" id="btnAgregarDigesto" styleClass="button"
														text="Agregar Digesto" />
													<ui:button rendered="true" action="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.btnEliminarDigesto_action}"
														binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.btnEliminarDigesto}" id="btnEliminarDigesto" styleClass="button"
														text="Eliminar Digesto" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								</tr>
								<tr><td colspan="4"><br/></td></tr>
								<tr><td align="right"><ui:label binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria"/></td>
								<td><ui:textArea binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.taComentarioLogAuditoria}" id="taComentarioLogAuditoria"/></td></tr><tr><td><br/></td></tr>
								<tr><td colspan="4"><ui:table binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.tablaLogs}" id="tbLogsAuditoria"/></td></tr>
								<tr>
									<td colspan="2">
										<ui:messageGroup binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.messageGroup1}" id="messageGroup1"
											styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.btnGuardar_action}"
											binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.btnCancelar_action}"
											binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.idSubSesion}" />
					<ui:script binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.scriptFinalM1}" id="scriptFinalM1"
						url="/resources/javascript/addEvent.js" />
					<ui:script binding="#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
