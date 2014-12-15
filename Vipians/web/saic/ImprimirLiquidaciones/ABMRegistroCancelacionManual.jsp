<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.page1}" id="page1">
			<ui:html binding="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.html1}" id="html1">
			<ui:head binding="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.head1}" id="head1">
				<ui:link binding="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.body1}" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyPress="eventoByEnter(event,'btnGuardar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.lblComentario}" for="taComentario" id="lblComentario"
											styleClass="label" text="Comentario" />
									</td>
									<td>
										<ui:textArea binding="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.taComentario}" columns="40" id="taComentario"
											styleClass="textField" rows="5" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:staticText binding="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.stSeleccionados}" id="stSeleccionados"
											styleClass="label2" text="Seleccionados" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.table1}" id="table1">
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
											<ui:tableRowGroup binding="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.tableRowGroup1}"
												emptyDataMsg="Ningún registro encontrado." id="tableRowGroup1" rows="#{ApplicationBean1.cantidadFilasTablasAdmin}"
												sourceData="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.ldpLiquidaciones}" sourceVar="currentRow">
												<ui:tableColumn binding="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.tcPeriodo}" headerText="Período"
													id="tcPeriodo">
													<ui:staticText binding="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.stPeriodo}" id="stPeriodo"
														text="#{currentRow.value['stringPeriodoLiquidado']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.tcObligacion}" headerText="Obligación"
													id="tcObligacion">
													<ui:textArea binding="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.taObligacion}" id="taObligacion"
														text="#{currentRow.value['stringObligacion']}" disabled="true" styleClass="textFieldDisabled" rows="1" columns="70"/>
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.tcVencimiento}" headerText="Vencimiento"
													id="tcVencimiento">
													<ui:staticText binding="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.stVencimiento}" id="stVencimiento"
														text="#{currentRow.value['fechaVencimiento']}"
														converter="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.dateConverter1}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.tcMonto}" headerText="Monto" id="tcMonto"
													sort="monto">
													<ui:staticText binding="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.stMonto}"
														converter="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.numberConverter1}" id="stMonto"
														text="#{currentRow.value['monto']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.tcTipoDeuda}" headerText="Tipo de Deuda"
													id="tcTipoDeuda">
													<ui:staticText binding="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.stTipoDeuda}" id="stTipoDeuda"
														text="#{currentRow.value['tipoDeuda']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.tcEstado}" headerText="Estado" id="tcEstado">
													<ui:staticText binding="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.stEstado}" id="stEstado"
														text="#{currentRow.value['estado']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.messageGroup1}" id="messageGroup1"
											styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.btnGuardar_action}"
											binding="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.btnCancelar_action}"
											binding="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.hidIdPagina}" id="hidIdPagina"
						text="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.idPagina}" />
					<ui:hiddenField binding="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.idSubSesion}" />
					<ui:script binding="#{saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
