<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.head1}" id="head1"
				title="Dar de Alta Obligación: Tasa General Inmobiliaria">
				<ui:link binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.body1}" focus="form1:tfNumeroLibretaSanitaria" id="body1"
				onLoad="parent.footer.location.reload(); Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid">
				<ui:form binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.label5}" for="tfNombre" id="label5" styleClass="label"
											text="Nombre del Documento" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.tfNombre}" columns="40" id="tfNombre"
											styleClass="textField" disabled="false" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.label4}" for="tfFechaInicio" id="label4"
											styleClass="label" text="Inicio de Actividades" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.tfFechaInicio}" id="tfFechaInicio"
											styleClass="textField" disabled="false" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
										<!--<ui:staticText binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.staticText1}" escape="false"
                                                           id="staticText1" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap"></td>
									<td nowrap="nowrap"></td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.label6}" for="tfFechaCese" id="label6" rendered="false"
											styleClass="label" text="Cese de Actividades" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.tfFechaCese}" id="tfFechaCese" rendered="false"
											styleClass="textField" disabled="false" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
										<!--<ui:staticText binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.staticText2}" escape="false"
                                                           id="staticText2" rendered="false" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<hr />
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.lblParcela}" for="tfParcela" id="lblParcela"
											styleClass="label" text="Parcela" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.tfParcela}" columns="40" id="tfParcela"
											styleClass="textFieldDisabled" disabled="true" />
										<!--    <ui:button action="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.btnSeleccionarParcela_action}"
                                                       binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.btnSeleccionarParcela}" escape="false"
                                                       id="btnSeleccionarParcela" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar"/>
                                            <ui:button action="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.btnLimpiarParcela_action}"
                                                       binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.btnLimpiarParcela}" escape="false"
                                                       id="btnLimpiarParcela" mini="true" styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar"/>
                                        -->
									</td>
									<td align="right" nowrap="nowrap"></td>
									<td nowrap="nowrap"></td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td nowrap="nowrap">
										<ui:staticText binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.staticText3}" id="staticText3" styleClass="label"
											text="Persona Solicitante" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:table binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.table1}" id="table1">
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
											<ui:tableRowGroup binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.tableRowGroup1}"
												emptyDataMsg="Ningún registro encontrado." id="tableRowGroup1"
												sourceData="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.ldpPropietarios}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.tableColumn1}" id="tableColumn1"
													valign="middle" width="10">
													<ui:radioButton binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.radioButton1}" id="radioButton1" label=""
														name="buttonGroup" selected="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.RBSelected}"
														selectedValue="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.currentRow3}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.tableColumn2}" headerText="Propietarios"
													id="tableColumn2" sort="persona">
													<ui:staticText binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.stPersona}" id="stPersona"
														text="#{currentRow.value['persona']}" />
												</ui:tableColumn>

											</ui:tableRowGroup>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>

								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.label1}" id="label1" styleClass="label"
											text="Domicilio Postal" />
									</td>
									<td nowrap="nowrap">
										<ui:button action="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.btnSeleccionarDomicilioPostal_action}"
											binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.btnSeleccionarDomicilioPostal}" escape="false"
											id="btnSeleccionarDomicilioPostal" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Agregar/Modificar" />
										<a4j:commandButton id="btnLimpiarDomicilioPostal" reRender="form1:stDomicilioPostal"
											binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.btnLimpiarDomicilioPostal}"
											action="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.btnLimpiarDomicilioPostal_action}" styleClass="buttonLimpiarAjax" />
										<ui:button action="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.btnSeleccionarDomicilioSolicitante_action}"
											binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.btnSeleccionarDomicilioSolicitante}"
											id="btnSeleccionarDomicilioSolicitante" mini="true" styleClass="button" text="Del Solicitante" />
										<ui:button action="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.btnSeleccionarDomicilioParcela_action}"
											binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.btnSeleccionarDomicilioParcela}"
											id="btnSeleccionarDomicilioParcela" mini="true" styleClass="button" text="De la Parcela" />
									</td>
									<td align="right" nowrap="nowrap"></td>
									<td nowrap="nowrap"></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap"></td>
									<td colspan="3">
										<ui:staticText binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.stDomicilioPostal}" escape="false"
											id="stDomicilioPostal" styleClass="staticText" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.messageGroup1}" id="messageGroup1"
											styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.btnGuardar_action}"
											binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.btnGuardar}" id="btnGuardar" styleClass="button" text="Guardar" />
										<ui:staticText binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.btnCancelar_action}"
											binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.btnCancelar}" id="btnCancelar" styleClass="button"
											text="Cancelar" />
									</td>
								</tr>
							</tfoot>
						</table>
						<br /> <br /> <br />
					</div>
					<ui:hiddenField binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpTGI$ABMDocEspTGI$DardeAltaDocEspTGI.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
