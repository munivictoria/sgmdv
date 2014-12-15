<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.head1}" id="head1">
				<ui:link binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
				    	var nombreBean = "habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial";
				    
						function cargarComportamientoJQuery() {
							autoCompletarEnTextField("#form1:tfParcela", "parcela", nombreBean, "setParcelaAutocompletar");
						}

						function focusearTfParcela() {
							$("#form1\\:tfParcela").focus();
						}
						
						$(document).ready(function() {
							cargarComportamientoJQuery();
						});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.body1}" focus="form1:tfNumeroInscripcion" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.label4}" for="tfNumeroInscripcion" id="label4"
											styleClass="label" text="Número de Inscripción" />
									</td>
									<td>
										<ui:textField binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.tfNumeroInscripcion}" id="tfNumeroInscripcion"
											styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.label1}" for="tfParcela" id="label1"
											styleClass="label" text="Parcela" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.tfParcela}" columns="40" id="tfParcela" 
											styleClass="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.hayParcela ? 'textFieldDisabled' : 'textField'}"
											disabled="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.hayParcela}" />
										<ui:button action="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.btnSeleccionarParcela_action}"
											binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.btnSeleccionarParcela}" escape="false"
											id="btnSeleccionarParcela" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarParcela" reRender="form1:tfParcela" title="Limpiar"
											binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.btnLimpiarParcela}"
											action="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.btnLimpiarParcela_action}" styleClass="buttonLimpiarAjax" 
											oncomplete="cargarComportamientoJQuery(); focusearTfParcela();" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.label3}" for="tfSuperficieCubierta" id="label3"
											styleClass="label" text="Superficie Cubierta Afectada" />
									</td>
									<td>
										<ui:textField binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.tfSuperficieCubierta}" columns="10"
											id="tfSuperficieCubierta" styleClass="textField" />
										<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.staticText1}" escape="false" id="staticText1"
											styleClass="label" text="&amp;nbsp;[m&lt;sup&gt;2&lt;/sup&gt;]" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.label2}" for="tfTelefono" id="label2"
											styleClass="label" text="Teléfono" />
									</td>
									<td>
										<ui:textField binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.tfTelefono}" columns="30" id="tfTelefono"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.label6}" for="tfSuperficieSemiCubierta"
											id="label6" styleClass="label" text="Superficie Semicubierta Afectada" />
									</td>
									<td>
										<ui:textField binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.tfSuperficieSemiCubierta}" columns="10"
											id="tfSuperficieSemiCubierta" styleClass="textField" />
										<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.staticText2}" escape="false" id="staticText2"
											styleClass="label" text="&amp;nbsp;[m&lt;sup&gt;2&lt;/sup&gt;]" />
									</td>
									<td align="right" nowrap="nowrap"></td>
									<td></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.label5}" for="taDescripcion" id="label5"
											styleClass="label" text="Descripción" />
									</td>
									<td>
										<ui:textArea binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.taDescripcion}" columns="40" id="taDescripcion"
											rows="5" styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap"></td>
									<td></td>
								</tr>
								<tr>
									<td colspan="4">
										<hr />
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.label7}" id="label7" styleClass="label2"
											text="Inspecciones" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.table1}" id="table1"
											width="480">
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
											<ui:tableRowGroup binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.tableRowGroup1}" id="tableRowGroup1"
												rows="#{ApplicationBean1.cantidadFilasTablasAdmin}"
												sourceData="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.ldpInspeccionLocalComercial}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.tableColumn1}"
													id="tableColumn1" valign="middle">
													<ui:radioButton binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.radioButton1}" id="radioButton1" label=""
														name="buttonGroup" selected="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.RBSelected}"
														selectedValue="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.tableColumn2}" headerText="Fecha"
													id="tableColumn2" sort="fecha">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.staticText4}"
														converter="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.dateTimeConverter1}" id="staticText4"
														text="#{currentRow.value['fecha']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.tableColumn3}" headerText="Inspector"
													id="tableColumn3" sort="inspector">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.staticText5}" id="staticText5"
														text="#{currentRow.value['inspector']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.tableColumn4}" headerText="Estado"
													id="tableColumn4" sort="estado">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.staticText7}" id="staticText7"
														text="#{currentRow.value['estado']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.groupPanel1}" id="groupPanel1">
													<ui:button action="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.btnAgregarInspeccion_action}"
														binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.btnAgregarInspeccion}" id="btnAgregarInspeccion"
														styleClass="button" text="Agregar" />
													<a4j:commandButton action="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.btnQuitarInspeccion_action}"
														binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.btnQuitarInspeccion}" id="btnQuitarInspeccion"
														onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea Quitar la Inspección del Local Comercial?');"
														value="Quitar" styleClass="btnAjax" reRender="tableActionsTop" />
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.staticText6}" escape="false"
														id="staticText6" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.btnConsultarInspeccion_action}"
														binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.btnConsultarInspeccion}" id="btnConsultarInspeccion"
														styleClass="button" text="Consultar" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.messageGroup1}" id="messageGroup1"
											styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="nowrap">
										<ui:button action="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.btnGuardar_action}"
											binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.btnCancelar_action}"
											binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.btnCancelar}" id="btnCancelar" styleClass="button"
											 />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
