<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMCalendarioMunicipal$ABMCuota.page1}" id="page1">
			<ui:html binding="#{framework$ABMCalendarioMunicipal$ABMCuota.html1}" id="html1">
			<ui:head binding="#{framework$ABMCalendarioMunicipal$ABMCuota.head1}" id="head1">
				<ui:link binding="#{framework$ABMCalendarioMunicipal$ABMCuota.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					function cargarComportamientoJQuery() {
						var $filas = $("#form1\\:table1 tr");
						for(var i = 2; i < $filas.length; i++) {
							if($filas.eq(i).find("td:gt(0) :first").attr("id")) {
								calendarioEnTextField("#" + $filas.eq(i).find("td:gt(0) :first").attr("id"));
							}
						}
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{framework$ABMCalendarioMunicipal$ABMCuota.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyPress="eventoByEnter(event,'btnGuardar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMCalendarioMunicipal$ABMCuota.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{framework$ABMCalendarioMunicipal$ABMCuota.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{framework$ABMCalendarioMunicipal$ABMCuota.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMCalendarioMunicipal$ABMCuota.lblNombre}" for="tfNombre" id="lblNombre" styleClass="label"
											text="Nombre" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMCalendarioMunicipal$ABMCuota.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMCalendarioMunicipal$ABMCuota.lblNumero}" for="tfNumero" id="lblNumero" styleClass="label"
											text="Número" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMCalendarioMunicipal$ABMCuota.tfNumero}" columns="40" id="tfNumero" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:staticText binding="#{framework$ABMCalendarioMunicipal$ABMCuota.stVencimientos}" id="stVencimientos" styleClass="label2"
											text="Vencimientos" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{framework$ABMCalendarioMunicipal$ABMCuota.table1}" id="table1" width="283">
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
											<ui:tableRowGroup binding="#{framework$ABMCalendarioMunicipal$ABMCuota.tableRowGroup1}" id="tableRowGroup1"
												emptyDataMsg="Ningún registro encontrado." sourceData="#{framework$ABMCalendarioMunicipal$ABMCuota.ldpVencimientos}"
												sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{framework$ABMCalendarioMunicipal$ABMCuota.tableColumn1}" id="tableColumn1"
													valign="middle" width="10">
													<ui:radioButton binding="#{framework$ABMCalendarioMunicipal$ABMCuota.radioButton1}" id="radioButton1" label=""
														name="buttonGroup" selected="#{framework$ABMCalendarioMunicipal$ABMCuota.RBSelected}"
														selectedValue="#{framework$ABMCalendarioMunicipal$ABMCuota.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{framework$ABMCalendarioMunicipal$ABMCuota.tcFechaVencimiento}" headerText="Fecha"
													id="tcFechaVencimiento" sort="time">
													<ui:textField binding="#{framework$ABMCalendarioMunicipal$ABMCuota.tfFechaVencimiento}" id="tfFechaVencimiento"
														styleClass="textField" columns="10" text="#{currentRow.value['time']}" converter="DateConverter" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{framework$ABMCalendarioMunicipal$ABMCuota.groupPanel1}" id="groupPanel1">
													<ui:button action="#{framework$ABMCalendarioMunicipal$ABMCuota.btnAgregarVencimiento_action}"
														binding="#{framework$ABMCalendarioMunicipal$ABMCuota.btnAgregarVencimiento}" id="btnAgregarVencimiento" styleClass="button"
														text="Agregar" />
													<a4j:commandButton binding="#{framework$ABMCalendarioMunicipal$ABMCuota.btnQuitarVencimiento}" id="btnQuitarVencimiento"
														action="#{framework$ABMCalendarioMunicipal$ABMCuota.btnQuitarVencimiento_action}" value="Quitar" styleClass="btnAjax"
														reRender="table1" onmousedown="reemplazarClickConConfirmacion(this, '');" />
													<a4j:commandButton binding="#{framework$ABMCalendarioMunicipal$ABMCuota.btnQuitarTodosVencimiento}"
														id="btnQuitarTodosVencimiento" action="#{framework$ABMCalendarioMunicipal$ABMCuota.btnQuitarTodosVencimiento_action}"
														value="Quitar Todas" styleClass="btnAjax" reRender="table1"
														onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea quitar todos los elementos de esta lista?');" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{framework$ABMCalendarioMunicipal$ABMCuota.messageGroup1}" id="messageGroup1" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{framework$ABMCalendarioMunicipal$ABMCuota.btnGuardar_action}"
											binding="#{framework$ABMCalendarioMunicipal$ABMCuota.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{framework$ABMCalendarioMunicipal$ABMCuota.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{framework$ABMCalendarioMunicipal$ABMCuota.btnCancelar_action}"
											binding="#{framework$ABMCalendarioMunicipal$ABMCuota.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{framework$ABMCalendarioMunicipal$ABMCuota.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMCalendarioMunicipal$ABMCuota.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMCalendarioMunicipal$ABMCuota.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMCalendarioMunicipal$ABMCuota.idSubSesion}" />
					<ui:script binding="#{framework$ABMCalendarioMunicipal$ABMCuota.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>