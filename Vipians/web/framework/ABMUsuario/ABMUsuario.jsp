<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMUsuario$ABMUsuario.page1}" id="page1">
			<ui:html binding="#{framework$ABMUsuario$ABMUsuario.html1}" id="html1">
			<ui:head binding="#{framework$ABMUsuario$ABMUsuario.head1}" id="head1">
				<ui:link binding="#{framework$ABMUsuario$ABMUsuario.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{framework$ABMUsuario$ABMUsuario.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMUsuario$ABMUsuario.form1}" id="form1"
					virtualFormsConfig="vfCancelar | hidIdPagina hidIdSubSesion | btnCancelar">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{framework$ABMUsuario$ABMUsuario.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{framework$ABMUsuario$ABMUsuario.head1.title}" />
							</caption>
							<tr>
								<td colspan="2"></td>
							</tr>
							<tbody>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMUsuario$ABMUsuario.label4}" for="tfNombre" id="label4" styleClass="label" text="Nombre" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMUsuario$ABMUsuario.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMUsuario$ABMUsuario.label5}" for="pfPassword1" id="label5" styleClass="label" text="Contraseña" />
									</td>
									<td>
										<ui:passwordField binding="#{framework$ABMUsuario$ABMUsuario.pfPassword1}" id="pfPassword1" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{framework$ABMUsuario$ABMUsuario.label6}" for="pfConfirmPassword1" id="label6" styleClass="label"
											text="Confirme la Contraseña" />
									</td>
									<td>
										<ui:passwordField binding="#{framework$ABMUsuario$ABMUsuario.pfConfirmPassword1}" id="pfConfirmPassword1" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMUsuario$ABMUsuario.label1}" for="tfPersona" id="label1" styleClass="label" text="Persona" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{framework$ABMUsuario$ABMUsuario.tfPersona}" columns="40" disabled="true" id="tfPersona"
											styleClass="textField" />
										<ui:button action="#{framework$ABMUsuario$ABMUsuario.btnSeleccionarPersona_action}"
											binding="#{framework$ABMUsuario$ABMUsuario.btnSeleccionarPersona}" escape="false" id="btnSeleccionarPersona" mini="true"
											styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<hr />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:label binding="#{framework$ABMUsuario$ABMUsuario.label3}" id="label3" styleClass="label2" text="Roles" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:table augmentTitle="false" binding="#{framework$ABMUsuario$ABMUsuario.table1}" id="table1" width="344">
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
											<ui:tableRowGroup binding="#{framework$ABMUsuario$ABMUsuario.tableRowGroup1}" id="tableRowGroup1"
												sourceData="#{framework$ABMUsuario$ABMUsuario.ldpRolesUsuario}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{framework$ABMUsuario$ABMUsuario.tableColumn1}" id="tableColumn1" valign="middle"
													width="10">
													<ui:radioButton binding="#{framework$ABMUsuario$ABMUsuario.radioButton1}" id="radioButton1" label="" name="buttonGroup"
														selected="#{framework$ABMUsuario$ABMUsuario.RBSelected}" selectedValue="#{framework$ABMUsuario$ABMUsuario.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{framework$ABMUsuario$ABMUsuario.tableColumn2}" headerText="Nombre" id="tableColumn2" sort="nombre">
													<ui:staticText binding="#{framework$ABMUsuario$ABMUsuario.staticText1}" id="staticText1" text="#{currentRow.value['nombre']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{framework$ABMUsuario$ABMUsuario.tableColumn4}" headerText="Estado" id="tableColumn4">
													<ui:staticText binding="#{framework$ABMUsuario$ABMUsuario.staticText3}" id="staticText3" text="#{currentRow.value['estado']}" />
												</ui:tableColumn>
												<ui:tableColumn align="center" binding="#{framework$ABMUsuario$ABMUsuario.tableColumn5}" id="tableColumn5" rendered="false"
													valign="middle" width="10">
													<ui:radioButton binding="#{framework$ABMUsuario$ABMUsuario.radioButton2}" id="radioButton2" label="" name="buttonGroup2"
														selected="#{framework$ABMUsuario$ABMUsuario.RBPrincipalSelected}"
														selectedValue="#{framework$ABMUsuario$ABMUsuario.principalRow}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{framework$ABMUsuario$ABMUsuario.groupPanel1}" id="groupPanel1">
													<ui:button action="#{framework$ABMUsuario$ABMUsuario.btnAgregar_action}"
														binding="#{framework$ABMUsuario$ABMUsuario.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<a4j:commandButton action="#{framework$ABMUsuario$ABMUsuario.btnQuitar_action}"
														binding="#{framework$ABMUsuario$ABMUsuario.btnQuitar}" id="btnQuitar" value="Quitar" styleClass="btnAjax" reRender="table1"
														onmousedown="reemplazarClickConConfirmacion(this, '');" />
													<ui:staticText binding="#{framework$ABMUsuario$ABMUsuario.staticText4}" escape="false" id="staticText4"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<a4j:commandButton action="#{framework$ABMUsuario$ABMUsuario.btnQuitarTodos_action}"
														binding="#{framework$ABMUsuario$ABMUsuario.btnQuitarTodos}" id="btnQuitarTodos" value="Quitar todos" styleClass="btnAjax"
														reRender="table1"
														onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea quitar todos los elementos de esta lista?');" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<hr />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:label binding="#{framework$ABMUsuario$ABMUsuario.labelArea}" id="labelArea" styleClass="label2" text="Areas" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:table augmentTitle="false" binding="#{framework$ABMUsuario$ABMUsuario.table2}" id="table2" width="344">
											<ui:tableRowGroup binding="#{framework$ABMUsuario$ABMUsuario.tableRowGroup2}" id="tableRowGroup2"
												sourceData="#{framework$ABMUsuario$ABMUsuario.ldpAreasUsuarios}" sourceVar="currentRowArea">
												<ui:tableColumn align="center" binding="#{framework$ABMUsuario$ABMUsuario.tableColumnArea1}" id="tableColumnArea1" valign="middle"
													width="10">
													<ui:radioButton binding="#{framework$ABMUsuario$ABMUsuario.radioButtonArea1}" id="radioButtonArea1" label="" name="buttonGroup"
														selected="#{framework$ABMUsuario$ABMUsuario.RBSelectedArea}" selectedValue="#{framework$ABMUsuario$ABMUsuario.currentRowArea}" />    
												</ui:tableColumn>
												<ui:tableColumn binding="#{framework$ABMUsuario$ABMUsuario.tableColumnArea2}" headerText="Nombre" id="tableColumnArea2" sort="nombre">
													<ui:staticText binding="#{framework$ABMUsuario$ABMUsuario.staticTextArea1}" id="staticTextArea1" text="#{currentRowArea.value['nombre']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{framework$ABMUsuario$ABMUsuario.tableColumnArea3}" headerText="Descripción" id="tableColumnArea3" sort="descripcion">
													<ui:staticText binding="#{framework$ABMUsuario$ABMUsuario.staticTextArea2}" id="staticTextArea2" text="#{currentRowArea.value['descripcion']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{framework$ABMUsuario$ABMUsuario.tableColumnArea4}" headerText="Secretaría" id="tableColumnArea4">
													<ui:staticText binding="#{framework$ABMUsuario$ABMUsuario.staticTextArea3}" id="staticTextArea3" text="#{currentRowArea.value['secretaria']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{framework$ABMUsuario$ABMUsuario.groupPanel2}" id="groupPanel2">
													<ui:button action="#{framework$ABMUsuario$ABMUsuario.btnAgregarArea_action}"
														binding="#{framework$ABMUsuario$ABMUsuario.btnAgregarArea}" id="btnAgregarArea" styleClass="button" text="Agregar" />
													<a4j:commandButton action="#{framework$ABMUsuario$ABMUsuario.btnQuitarArea_action}" 												
														binding="#{framework$ABMUsuario$ABMUsuario.btnQuitarArea}" id="btnQuitarArea" value="Quitar" styleClass="btnAjax" reRender="table2"
														onmousedown="reemplazarClickConConfirmacion(this, '');" />
													<ui:staticText binding="#{framework$ABMUsuario$ABMUsuario.staticTextArea4}" escape="false" id="staticTextArea4"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<a4j:commandButton action="#{framework$ABMUsuario$ABMUsuario.btnQuitarTodasAreas_action}"
														binding="#{framework$ABMUsuario$ABMUsuario.btnQuitarTodasAreas}" id="btnQuitarTodasAreas" value="Quitar todos" styleClass="btnAjax"
														reRender="table2"
														onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea quitar todos los elementos de esta lista?');" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
							</tbody>
							<tr>
								<td colspan="4">
									<br />
								</td>
							</tr>
							<tr>
								<td align="right">
									<ui:label binding="#{framework$ABMUsuario$ABMUsuario.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
								</td>
								<td>
									<ui:textArea binding="#{framework$ABMUsuario$ABMUsuario.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
								</td>
							</tr>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<ui:table binding="#{framework$ABMUsuario$ABMUsuario.tablaLogs}" id="tbLogsAuditoria" />
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<ui:messageGroup binding="#{framework$ABMUsuario$ABMUsuario.messageGroup1}" id="messageGroup1" styleClass="grupoMsg" />
								</td>
							</tr>
							<tfoot>
								<tr>
									<td align="right" colspan="2" nowrap="nowrap">
										<ui:button action="#{framework$ABMUsuario$ABMUsuario.btnGuardar_action}" binding="#{framework$ABMUsuario$ABMUsuario.btnGuardar}"
											id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{framework$ABMUsuario$ABMUsuario.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{framework$ABMUsuario$ABMUsuario.btnCancelar_action}" binding="#{framework$ABMUsuario$ABMUsuario.btnCancelar}"
											id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{framework$ABMUsuario$ABMUsuario.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMUsuario$ABMUsuario.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMUsuario$ABMUsuario.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMUsuario$ABMUsuario.idSubSesion}" />
					<ui:script binding="#{framework$ABMUsuario$ABMUsuario.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
