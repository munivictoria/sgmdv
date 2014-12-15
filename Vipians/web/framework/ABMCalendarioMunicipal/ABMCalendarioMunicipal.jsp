<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.page1}" id="page1">
			<ui:html binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.html1}" id="html1">
			<ui:head binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.head1}" id="head1">
				<ui:link binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" 
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.lblNombre}" for="tfNombre" id="lblNombre"
											styleClass="label" text="Nombre" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.tfNombre}" columns="40" id="tfNombre"
											styleClass="textField" />
									</td>
									<td>
										<ui:label binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.lblAnio}" for="tfAnio" id="lblAnio"
											styleClass="label" text="Año" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.tfAnio}"
											onKeyPress="return ValidarNum(event,this)" columns="10" maxLength="4" id="tfAnio" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.lblTipoObligacion}" for="ddTipoObligacion"
											id="lblTipoObligacion" styleClass="label" text="Tipo de Obligación" />
									</td>
									<td>
										<ui:dropDown binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.ddTipoObligacion}" id="ddTipoObligacion"
											items="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.ddTipoObligacionDefaultOptions.options}"
											valueChangeListener="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.valueChangeEvent(event)}" styleClass="textField">
											<a4j:support event="onChange" reRender="form1:ddPlanes" />
										</ui:dropDown>
									</td>
									<td>
										<ui:label binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.lblPlan}" for="ddPlanes" id="lblPlan"
											styleClass="label" text="Plan" />
									</td>
									<td>
										<ui:dropDown binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.ddPlanes}" id="ddPlanes"
											items="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.ddPlanDefaultOptions.options}" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:staticText binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.stPeriodos}" id="stPeriodos" styleClass="label2"
											text="Períodos" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.table1}" id="table1" width="283">
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
											<ui:tableRowGroup binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.tableRowGroup1}" id="tableRowGroup1"
												emptyDataMsg="Ningún registro encontrado." sourceData="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.ldpPeriodos}"
												sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.tableColumn1}"
													id="tableColumn1" valign="middle" width="10">
													<ui:radioButton binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.radioButton1}" id="radioButton1" label=""
														name="buttonGroup" selected="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.RBSelected}"
														selectedValue="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.tcNombrePeriodo}" headerText="Nombre"
													id="tcNombrePeriodo">
													<ui:staticText binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.stNombrePeriodo}" id="tfNombrePeriodo"
														text="#{currentRow.value['nombre']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.tcNumeroPeriodo}" headerText="Número"
													id="tcNumeroPeriodo">
													<ui:staticText binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.stNumeroPeriodo}" id="tfNumeroPeriodo"
														text="#{currentRow.value['numero']}" />
												</ui:tableColumn>
												<!--<ui:tableColumn binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.tcFechaInicioPeriodo}"
                                                                    headerText="Fecha Inicio" id="tcFechaInicioPeriodo">
                                                        <ui:staticText binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.stFechaInicioPeriodo}" id="tfFechaInicioPeriodo" text="#{currentRow.value['fechaInicio']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.tcFechaFinPeriodo}"
                                                                    headerText="Fecha Fin" id="tcFechaFinPeriodo">
                                                        <ui:staticText binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.stFechaFinPeriodo}" id="tfFechaFinPeriodo" text="#{currentRow.value['fechaFin']}"/>
                                                    </ui:tableColumn>-->
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.groupPanel1}" id="groupPanel1">
													<ui:button action="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.btnAgregarPeriodo_action}"
														binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.btnAgregarPeriodo}" id="btnAgregarPeriodo"
														styleClass="button" text="Agregar" />
													<ui:button action="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.btnModificarPeriodo_action}"
														binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.btnModificarPeriodo}" id="btnModificarPeriodo"
														styleClass="button" text="Modificar" />
													<ui:button action="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.btnConsultarPeriodo_action}"
														binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.btnConsultarPeriodo}" id="btnConsultarPeriodo"
														styleClass="button" text="Consultar" />
													<a4j:commandButton binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.btnQuitarPeriodo}" id="btnQuitarPeriodo"
														action="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.btnQuitarPeriodo_action}" value="Quitar"
														styleClass="btnAjax" reRender="table1" onmousedown="reemplazarClickConConfirmacion(this, '');" />
													<a4j:commandButton binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.btnQuitarTodosPeriodo}"
														id="btnQuitarTodosPeriodo" action="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.btnQuitarTodosPeriodo_action}"
														value="Quitar Todos" styleClass="btnAjax" reRender="table1"
														onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea quitar todos los elementos de esta lista?');" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right">
										<ui:label binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
									</td>
									<td>
										<ui:textArea binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.tablaLogs}" id="tbLogsAuditoria" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.messageGroup1}" id="messageGroup1"
											styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.btnGuardar_action}"
											binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.btnCancelar_action}"
											binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.idSubSesion}" />
					<ui:script binding="#{framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>