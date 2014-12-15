<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.head1}" id="head1">
				<ui:link binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.link1}" id="link1"
					url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.body1}"
				focus="form1:tfNumeroInscripcion" id="body1" onLoad="parent.footer.location.reload();Init();"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.stTitulo}" id="stTitulo"
									styleClass="tituloABM" text="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.label4}"
											for="tfNumeroInscripcion" id="label4" styleClass="label" text="Número de Inscripción" />
									</td>
									<td>
										<ui:textField binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.tfNumeroInscripcion}"
											id="tfNumeroInscripcion" styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.label1}" for="tfVehiculo"
											id="label1" styleClass="label" text="Vehículo" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.tfVehiculo}" columns="40"
											disabled="true" id="tfVehiculo" styleClass="textField" />
										<ui:button action="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.btnSeleccionarVehiculo_action}"
											binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.btnSeleccionarVehiculo}" escape="false"
											id="btnSeleccionarVehiculo" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarVehiculo" reRender="form1:tfVehiculo" title="Limpiar"
											binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.btnLimpiarVehiculo}"
											action="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.btnLimpiarVehiculo_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.label3}" for="tfFechaAlta"
											id="label3" styleClass="label" text="Fecha Alta" />
									</td>
									<td>
										<ui:textField binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.tfFechaAlta}"
											id="tfFechaAlta" styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.label2}" for="tfFechaBaja"
											id="label2" rendered="false" styleClass="label" text="Fecha Baja" />
									</td>
									<td>
										<ui:textField binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.tfFechaBaja}"
											id="tfFechaBaja" rendered="false" styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
										<!--<ui:staticText
                                                binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.staticText3}"
                                                escape="false" id="staticText3" rendered="false" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.label5}" for="taDescripcion"
											id="label5" styleClass="label" text="Descripción" />
									</td>
									<td>
										<ui:textArea binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.taDescripcion}" columns="40"
											id="taDescripcion" rows="5" styleClass="textField" />
									</td>
									<td>
									<td align="right" nowrap="nowrap"></td>
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
										<ui:label binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.label6}" id="label6"
											styleClass="label2" text="Inspecciones" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.table1}"
											id="table1" width="480">
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
											<ui:tableRowGroup binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.tableRowGroup1}"
												id="tableRowGroup1"
												sourceData="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.ldpInspeccionTransporteVehicular}"
												sourceVar="currentRow">
												<ui:tableColumn align="center"
													binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.tableColumn1}" id="tableColumn1"
													valign="middle" width="10">
													<ui:radioButton binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.radioButton1}"
														id="radioButton1" label="" name="buttonGroup"
														selected="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.RBSelected}"
														selectedValue="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.tableColumn2}"
													headerText="Fecha" id="tableColumn2" sort="fecha">
													<ui:staticText binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.staticText2}"
														converter="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.dateTimeConverter1}"
														id="staticText2" text="#{currentRow.value['fecha']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.tableColumn3}"
													headerText="Inspector" id="tableColumn3" sort="inspector">
													<ui:staticText binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.staticText4}"
														id="staticText4" text="#{currentRow.value['inspector']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.tableColumn4}"
													headerText="Estado" id="tableColumn4" sort="estado">
													<ui:staticText binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.staticText5}"
														id="staticText5" text="#{currentRow.value['estado']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.groupPanel1}"
													id="groupPanel1">
													<ui:button action="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.btnAgregarInspeccion_action}"
														binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.btnAgregarInspeccion}"
														id="btnAgregarInspeccion" styleClass="button" text="Agregar" />
													<ui:button action="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.btnQuitarInspeccion_action}"
														binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.btnQuitarInspeccion}"
														id="btnQuitarInspeccion"
														onClick="return (confirm(&quot;¿Está seguro que desea Quitar la Inspección del Transporte Vehicular?&quot;));"
														styleClass="button" text="Quitar" />
													<ui:staticText binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.staticText6}"
														escape="false" id="staticText6" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button
														action="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.btnConsultarInspeccion_action}"
														binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.btnConsultarInspeccion}"
														id="btnConsultarInspeccion" styleClass="button" text="Consultar" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr><td colspan="4"><br/></td></tr>
								<tr><td align="right"><ui:label binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria"/></td>
								<td><ui:textArea binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.taComentarioLogAuditoria}" id="taComentarioLogAuditoria"/></td></tr><tr><td><br/></td></tr>
								<tr><td colspan="4"><ui:table binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.tablaLogs}" id="tbLogsAuditoria"/></td></tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.messageGroup1}"
											id="messageGroup1" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.btnGuardar_action}"
											binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.btnGuardar}" id="btnGuardar"
											styleClass="button" />
										<ui:staticText binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.stSeparador}"
											escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.btnCancelar_action}"
											binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.btnCancelar}" id="btnCancelar"
											styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.hidIdSubSesion}"
						id="hidIdSubSesion" text="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular.scriptValidador}"
						id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
