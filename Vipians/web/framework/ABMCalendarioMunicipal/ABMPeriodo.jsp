<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.page1}" id="page1">
			<ui:html binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.html1}" id="html1">
			<ui:head binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.head1}" id="head1">
				<ui:link binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					function cargarComportamientoJQuery() {
						calendarioEnTextField("#form1:tfFechaInicioPeriodo");
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyPress="eventoByEnter(event,'btnGuardar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{framework$ABMCalendarioMunicipal$ABMPeriodo.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.lblNombrePeriodo}" for="tfNombrePeriodo" id="lblNombrePeriodo"
											styleClass="label" text="Nombre" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.tfNombrePeriodo}" columns="40" id="tfNombrePeriodo"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.lblNumeroPeriodo}" for="tfNumeroPeriodo" id="lblNumeroPeriodo"
											styleClass="label" text="Número" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.tfNumeroPeriodo}" columns="40" id="tfNumeroPeriodo"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.lblFechaInicioPeriodo}" id="lblFechaDesde"
											for="tfFechaInicioPeriodo" styleClass="label" text="Fecha Inicio" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.tfFechaInicioPeriodo}" id="tfFechaInicioPeriodo"
											styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" columns="10"/>
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:staticText binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.stCuotas}" id="stCuotas" styleClass="label2" text="Cuotas" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.table1}" id="table1" width="283">
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
											<ui:tableRowGroup binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.tableRowGroup1}" id="tableRowGroup1"
												emptyDataMsg="Ningún registro encontrado." sourceData="#{framework$ABMCalendarioMunicipal$ABMPeriodo.ldpCuotas}"
												sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.tableColumn1}" id="tableColumn1"
													valign="middle" width="10">
													<ui:radioButton binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.radioButton1}" id="radioButton1" label=""
														name="buttonGroup" selected="#{framework$ABMCalendarioMunicipal$ABMPeriodo.RBSelected}"
														selectedValue="#{framework$ABMCalendarioMunicipal$ABMPeriodo.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.tcNombre}" headerText="Nombre" id="tcNombre">
													<ui:staticText binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.stNombre}" id="stNombre"
														text="#{currentRow.value['nombre']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.tcNumeroCuota}" headerText="Número" id="tcNumeroCuota">
													<ui:staticText binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.stNumeroCuota}" id="stNumeroCuota"
														text="#{currentRow.value['numero']}" />
												</ui:tableColumn>
												<!--<ui:tableColumn binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.tcFechaInicioCuota}"
                                                                    headerText="Fecha Inicio" id="tcFechaInicioCuota">
                                                        <ui:staticText binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.stFechaInicioCuota}" id="stFechaInicioCuota" text="#{currentRow.value['fechaInicio']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.tcFechaFinCuota}"
                                                                    headerText="Fecha Fin" id="tcFechaFinCuota">
                                                        <ui:staticText binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.stFechaFinCuota}" id="stFechaFinCuota" text="#{currentRow.value['fechaFin']}"/>
                                                    </ui:tableColumn>-->
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.groupPanel1}" id="groupPanel1">
													<ui:button action="#{framework$ABMCalendarioMunicipal$ABMPeriodo.btnAgregarCuota_action}"
														binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.btnAgregarCuota}" id="btnAgregarCuota" styleClass="button"
														text="Agregar" />
													<ui:button action="#{framework$ABMCalendarioMunicipal$ABMPeriodo.btnModificarCuota_action}"
														binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.btnModificarCuota}" id="btnModificarCuota" styleClass="button"
														text="Modificar" />
													<a4j:commandButton action="#{framework$ABMCalendarioMunicipal$ABMPeriodo.btnQuitarCuota_action}"
														binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.btnQuitarCuota}" id="btnQuitarCuota" value="Quitar"
														styleClass="btnAjax" reRender="table1" onmousedown="reemplazarClickConConfirmacion(this, '');" />
													<a4j:commandButton action="#{framework$ABMCalendarioMunicipal$ABMPeriodo.btnQuitarTodosCuota_action}"
														binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.btnQuitarTodosCuota}" id="btnQuitarTodosCuota" value="Quitar Todos"
														styleClass="btnAjax" reRender="table1"
														onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea quitar todos los elementos de esta lista?');" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.messageGroup1}" id="messageGroup1" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{framework$ABMCalendarioMunicipal$ABMPeriodo.btnGuardar_action}"
											binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{framework$ABMCalendarioMunicipal$ABMPeriodo.btnCancelar_action}"
											binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMCalendarioMunicipal$ABMPeriodo.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMCalendarioMunicipal$ABMPeriodo.idSubSesion}" />
					<ui:script binding="#{framework$ABMCalendarioMunicipal$ABMPeriodo.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>