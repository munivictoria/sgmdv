<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{catastro$ABMZona$ABMZona.page1}" id="page1">
			<ui:html binding="#{catastro$ABMZona$ABMZona.html1}" id="html1">
			<ui:head binding="#{catastro$ABMZona$ABMZona.head1}" id="head1">
				<ui:link binding="#{catastro$ABMZona$ABMZona.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "catastro$ABMZona$ABMZona";

					function cargarComportamientoJQuery() {
						autoCompletarEnTextField("#form1:table1:tfParcela", "parcela", nombreBean, "setParcelaAutocompletar",
								"#form1:table1:btnAgregarParcela");
						$("#form1\\:table1\\:tfFiltrar").attr("placeholder", "Filtrar por...");
					}

					function focusearTfParcela() {
						$("#form1\\:table1\\:tfParcela").focus();
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{catastro$ABMZona$ABMZona.body1}" id="body1" onLoad="parent.footer.location.reload();Init();" focus="form1:tfNombre">
				<ui:form binding="#{catastro$ABMZona$ABMZona.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{catastro$ABMZona$ABMZona.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{catastro$ABMZona$ABMZona.head1.title}" />
							</caption>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tbody>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{catastro$ABMZona$ABMZona.label4}" for="tfNombre" id="label4" styleClass="label" text="Nombre" />
									</td>
									<td>
										<ui:textField binding="#{catastro$ABMZona$ABMZona.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{catastro$ABMZona$ABMZona.lblZonificacion}" for="tfZonificacion" id="lblZonificacion" styleClass="label"
											text="Zonificación" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{catastro$ABMZona$ABMZona.tfZonificacion}" columns="40" disabled="true" id="tfZonificacion"
											styleClass="textField" />
										<ui:button action="#{catastro$ABMZona$ABMZona.btnSeleccionarZonificacion_action}"
											binding="#{catastro$ABMZona$ABMZona.btnSeleccionarZonificacion}" escape="false" id="btnSeleccionarZonificacion" mini="true"
											styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarZonificacion" reRender="form1:tfZonificacion" title="Limpiar"
											binding="#{catastro$ABMZona$ABMZona.btnLimpiarZonificacion}" action="#{catastro$ABMZona$ABMZona.btnLimpiarZonificacion_action}"
											styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap" style="height: 22px">
										<ui:label binding="#{catastro$ABMZona$ABMZona.lblPrioridad}" id="lblPrioridad" for="tfPrioridad" styleClass="label"
											text="Prioridad" />
									</td>
									<td>
										<ui:textField binding="#{catastro$ABMZona$ABMZona.tfPrioridad}" maxLength="8" id="tfPrioridad"
											onKeyPress="return ValidarNum(event,this)" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{catastro$ABMZona$ABMZona.label5}" id="label5" styleClass="label" text="Descripción" />
									</td>
									<td>
										<ui:textArea binding="#{catastro$ABMZona$ABMZona.txDescripcion}" columns="40" id="txDescripcion" rows="5" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{catastro$ABMZona$ABMZona.table1}" id="table1" width="359" paginationControls="true" filterText="">
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
											<ui:tableRowGroup binding="#{catastro$ABMZona$ABMZona.tableRowGroup1}" id="tableRowGroup1" rows="20"
												emptyDataMsg="Ningún registro encontrado." sourceData="#{catastro$ABMZona$ABMZona.ldpAsociacion}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{catastro$ABMZona$ABMZona.tableColumn1}" id="tableColumn1" width="10">
													<ui:radioButton binding="#{catastro$ABMZona$ABMZona.radioButton1}" id="radioButton1" label="" name="buttonGroup"
														selected="#{catastro$ABMZona$ABMZona.RBSelected}" selectedValue="#{catastro$ABMZona$ABMZona.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{catastro$ABMZona$ABMZona.tableColumn2}" headerText="Nombre" id="tableColumn2" noWrap="true"
													sort="nombre">
													<ui:staticText binding="#{catastro$ABMZona$ABMZona.staticText2}" escape="false" id="staticText2"
														text="#{currentRow.value['nombre']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{catastro$ABMZona$ABMZona.tableColumn3}" headerText="Asociación" id="tableColumn3" noWrap="true"
													sort="tipo">
													<ui:staticText binding="#{catastro$ABMZona$ABMZona.staticText7}" escape="false" id="staticText7"
														text="#{currentRow.value['tipo']}&amp;nbsp;m&lt;sup&gt;2&lt;/sup&gt;" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{catastro$ABMZona$ABMZona.groupPanel3}" id="groupPanel3">
													<ui:label binding="#{catastro$ABMZona$ABMZona.lbParcela}" for="tfParcela" id="lbParcela" styleClass="label" text="Parcela" />
													<ui:textField binding="#{catastro$ABMZona$ABMZona.tfParcela}" columns="40" id="tfParcela"
														styleClass="#{catastro$ABMZona$ABMZona.hayParcela ? 'textFieldDisabled' : 'textField'}"
														disabled="#{catastro$ABMZona$ABMZona.hayParcela}" />
													<a4j:commandButton action="#{catastro$ABMZona$ABMZona.btnAgregarParcela_action}"
														binding="#{catastro$ABMZona$ABMZona.btnAgregarParcela}" id="btnAgregarParcela" styleClass="buttonAgregarAjax"
														reRender="table1" oncomplete="cargarComportamientoJQuery(); focusearTfParcela();" />
													<a4j:commandButton id="btnLimpiarParcela" reRender="tfParcela" title="Limpiar" binding="#{catastro$ABMZona$ABMZona.btnLimpiarParcela}"
														action="#{catastro$ABMZona$ABMZona.btnLimpiarParcela_action}" styleClass="buttonLimpiarAjax"
														oncomplete="cargarComportamientoJQuery(); focusearTfParcela();" />
													<ui:staticText binding="#{catastro$ABMZona$ABMZona.stSeparador1}" escape="false" id="stSeparador1"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{catastro$ABMZona$ABMZona.btnSeleccionarParcela_action}"
														binding="#{catastro$ABMZona$ABMZona.btnSeleccionarParcela}" id="btnSeleccionarParcela" styleClass="button" text="Parcela"
														toolTip="Seleccionar Parcela" />
													<ui:button action="#{catastro$ABMZona$ABMZona.btnSeleccionarCuadra_action}"
														binding="#{catastro$ABMZona$ABMZona.btnSeleccionarCuadra}" id="btnSeleccionarCuadra" styleClass="button" text="Cuadra"
														toolTip="Seleccionar Cuadra" />
													<ui:button action="#{catastro$ABMZona$ABMZona.btnSeleccionarCalle_action}"
														binding="#{catastro$ABMZona$ABMZona.btnSeleccionarCalle}" id="btnSeleccionarCalle" styleClass="button" text="Calle"
														toolTip="Seleccionar Calle" />
													<ui:button action="#{catastro$ABMZona$ABMZona.btnSeleccionarManzana_action}"
														binding="#{catastro$ABMZona$ABMZona.btnSeleccionarManzana}" id="btnSeleccionarManzana" styleClass="button" text="Manzana"
														toolTip="Seleccionar Manzana" />
													<ui:staticText binding="#{catastro$ABMZona$ABMZona.staticText14}" escape="false" id="staticText14"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<a4j:commandButton action="#{catastro$ABMZona$ABMZona.btnQuitarAsociacion_action}"
														binding="#{catastro$ABMZona$ABMZona.btnQuitarAsociacion}" id="btnQuitarAsociacion" value="Quitar" styleClass="btnAjax"
														reRender="table1" onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea Quitar la Asociación?');"
														oncomplete="cargarComportamientoJQuery();" />
												</ui:panelGroup>
											</f:facet>
											<f:facet name="filter">
												<ui:panelGroup binding="#{catastro$ABMZona$ABMZona.groupPanel4}" id="groupPanel4">
													<ui:textField id="tfFiltrar" binding="#{catastro$ABMZona$ABMZona.tfFiltrar}" columns="20" styleClass="textField" />
													<a4j:commandButton action="#{catastro$ABMZona$ABMZona.btnFiltrar_action}" binding="#{catastro$ABMZona$ABMZona.btnFiltrar}"
														id="btnFiltrar" styleClass="buttonFiltrarAjax" reRender="table1" oncomplete="cargarComportamientoJQuery();" />
													<a4j:commandButton id="btnLimpiarFiltrado" reRender="table1" title="Limpiar" binding="#{catastro$ABMZona$ABMZona.btnLimpiarFiltrado}"
														action="#{catastro$ABMZona$ABMZona.btnLimpiarFiltrado_action}" styleClass="buttonLimpiarAjax"
														oncomplete="cargarComportamientoJQuery();" />
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
										<ui:label binding="#{catastro$ABMZona$ABMZona.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
									</td>
									<td>
										<ui:textArea binding="#{catastro$ABMZona$ABMZona.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{catastro$ABMZona$ABMZona.tablaLogs}" id="tbLogsAuditoria" />
									</td>
								</tr>
							</tbody>
							<tr>
								<td style="height: 15px">
									<br />
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<ui:messageGroup binding="#{catastro$ABMZona$ABMZona.messageGroup1}" id="messageGroup1" styleClass="grupoMsg" />
								</td>
							</tr>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="nowrap">
										<ui:button action="#{catastro$ABMZona$ABMZona.btnGuardar_action}" binding="#{catastro$ABMZona$ABMZona.btnGuardar}" id="btnGuardar"
											styleClass="button" />
										<ui:staticText binding="#{catastro$ABMZona$ABMZona.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{catastro$ABMZona$ABMZona.btnCancelar_action}" binding="#{catastro$ABMZona$ABMZona.btnCancelar}"
											id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{catastro$ABMZona$ABMZona.hidIdPagina}" id="hidIdPagina" text="#{catastro$ABMZona$ABMZona.idPagina}" />
					<ui:hiddenField binding="#{catastro$ABMZona$ABMZona.hidIdSubSesion}" id="hidIdSubSesion" text="#{catastro$ABMZona$ABMZona.idSubSesion}" />
					<ui:hiddenField binding="#{catastro$ABMZona$ABMZona.hidIdObjeto}" id="hidIdObjeto" />
					<ui:script binding="#{catastro$ABMZona$ABMZona.scriptFinal1}" id="scriptFinal1" url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{catastro$ABMZona$ABMZona.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
