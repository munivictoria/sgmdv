<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.page1}" id="page1">
			<ui:html binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.html1}" id="html1">
			<ui:head binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.head1}" id="head1">
				<ui:link binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload(); Init();" 
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.form1}" id="form1"
					virtualFormsConfig="vfCancelar | hidIdPagina hidIdSubSesion | btnCancelar" >
					<div class="formularioABM">
						<table border="0" class="verde" >
							<caption>

								<ui:staticText binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.label1}" for="tfNombre" id="label1" styleClass="label"
											text="Nombre" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.tfNombre}" id="tfNombre" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.label2}" for="tfRecurso" id="label2" styleClass="label"
											text="Recurso" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.tfRecurso}" columns="40" disabled="true" id="tfRecurso"
											styleClass="textFieldDisabled" />
										<ui:button action="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.btnSeleccionarRecurso_action}"
											binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.btnSeleccionarRecurso}" escape="false" id="btnSeleccionarRecurso"
											mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarRecurso" reRender="form1:tfRecurso" title="Limpiar"
											binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.btnLimpiarRecurso}"
											action="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.btnLimpiarRecurso_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.label3}" for="ddTipo" id="label3" styleClass="label"
											text="Tipo" />
									</td>
									<td nowrap="nowrap">
										<ui:dropDown binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.ddTipo}" id="ddTipo"
											valueChangeListener="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.valueChangeEvent(event)}" onChange="this.form.submit()"
											items="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.ddTipoDefaultOptions.options}" styleClass="textFieldDisabled" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.label5}" for="cbBusqueda" id="label5" styleClass="label"
											text="Parametro de búsqueda" />
									</td>
									<td>
										<ui:checkbox binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.cbBusqueda}" id="cbBusqueda" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.label6}" for="cbRequerido" id="label6" styleClass="label"
											text="Requerido" />
									</td>
									<td>
										<ui:checkbox binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.cbRequerido}" id="cbRequerido" />
									</td>
								</tr>
								<td></td>
								<tr>
									<td colspan="2">
										<ui:label binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.lblOpcionesListado}" id="lblOpcionesListado"
											styleClass="label2" text="Opciones del listado" />
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.table1}" id="table1" width="200">
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
											<ui:tableRowGroup binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.tableRowGroup1}" id="tableRowGroup1"
												sourceData="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.ldpDatosListado}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.tableColumn1}" id="tableColumn1"
													valign="middle" width="5">
													<ui:radioButton binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.radioButton1}" id="radioButton1" label=""
														name="buttonGroup" selected="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.RBSelected}"
														selectedValue="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.tableColumn2}" headerText="Opción"
													id="tableColumn2" width="40">

													<ui:textField binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.tfOpcionTabla}" id="tfOpcionTabla"
														text="#{currentRow.value['valor']}" />
													<!--<ui:staticText binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.staticText2}"
                                                                       id="staticText2" text="#{currentRow.value['valor']}"/>
                                                        -->
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup>
													<ui:label binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.label4}" for="tfOpcion" id="label4" styleClass="label"
														text="Opción" />
													<ui:textField binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.tfOpcion}" columns="40" id="tfOpcion"
														styleClass="textField" />
													<a4j:commandButton action="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.btnAgregarDato_action}"
														binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.btnAgregarDato}" id="btnAgregarDato"
														styleClass="buttonAgregarAjax" reRender="table1" />
													<a4j:commandButton action="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.btnQuitarDato_action}"
														binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.btnQuitarDato}"
														id="btnQuitarDato"  styleClass="buttonRemoveAjax" reRender="table1" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr><td align="right"><ui:label binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria"/></td>
								<td><ui:textArea binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" /></td></tr><tr><td><br/></td></tr>
								<tr><td colspan="4"><ui:table binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.tablaLogs}" id="tbLogsAuditoria"/></td></tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.messageGroup1}" id="messageGroup1"
											styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.btnGuardar_action}"
											binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.btnCancelar_action}"
											binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.idSubSesion}" />
					<ui:script binding="#{framework$ABMAtributoDinamico$ABMAtributoDinamico.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
