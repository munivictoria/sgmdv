<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.page1}" id="page1">
			<ui:html binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.html1}" id="html1">
			<ui:head binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.head1}" id="head1"
				title="Permisos Sin Firmar: Tasa General Inmobiliaria">
				<ui:link binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.body1}" focus="form1:tfNumeroLibretaSanitaria"
				id="body1" onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 242, 242); -rave-layout: grid"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="gris">
							<caption>
								<ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.stTitulo}" id="stTitulo"
									styleClass="tituloABM" text="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.label5}" for="tfNombre" id="label5"
											styleClass="label" text="Nombre del Documento" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.tfNombre}" columns="40" disabled="true"
											id="tfNombre" styleClass="textFieldDisabled" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.label4}" for="tfFechaInicio" id="label4"
											styleClass="label" text="Inicio de Actividades" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.tfFechaInicio}" disabled="true"
											id="tfFechaInicio" maxLength="10" styleClass="textFieldDisabled" />
										<!--<ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.staticText1}" escape="false"
                                                id="staticText1" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap"></td>
									<td nowrap="nowrap"></td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.label6}" for="tfFechaCese" id="label6"
											rendered="false" styleClass="label" text="Cese de Actividades" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.tfFechaCese}" id="tfFechaCese"
											maxLength="10" rendered="false" styleClass="textField" />
										<!--<ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.staticText2}" escape="false"
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
										<ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.label16}" for="tfPersona" id="label16"
											styleClass="label" text="Persona Solicitante" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.tfPersona}" columns="40" disabled="true"
											id="tfPersona" styleClass="textFieldDisabled" />
									</td>
									<td align="right" nowrap="nowrap"></td>
									<td nowrap="nowrap"></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.lblParcela}" for="tfParcela" id="lblParcela"
											styleClass="label" text="Parcela" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.tfParcela}" columns="40" disabled="true"
											id="tfParcela" styleClass="textFieldDisabled" />
										<ui:button action="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.btnSeleccionarParcela_action}"
											binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.btnSeleccionarParcela}" escape="false"
											id="btnSeleccionarParcela" mini="true" rendered="false" styleClass="buttonSeleccionar" text="&amp;nbsp;"
											toolTip="Agregar/Modificar" />
										<a4j:commandButton id="btnLimpiarParcela" reRender="form1:tfParcela"
											binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.btnLimpiarParcela}"
											action="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.btnLimpiarParcela_action}" styleClass="buttonLimpiarAjax" />
									</td>
									<td align="right" nowrap="nowrap"></td>
									<td nowrap="nowrap"></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.label1}" id="label1" styleClass="label"
											text="Domicilio Postal" />
									</td>
									<td nowrap="nowrap">
										<ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.stDomicilioPostal}" escape="false"
											id="stDomicilioPostal" styleClass="staticText " />
									</td>
									<td align="right" nowrap="nowrap"></td>
									<td nowrap="nowrap"></td>
								</tr>
								<tr>
									<td colspan="4">
										<hr />
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.label2}" id="label2" styleClass="label2"
											text="Permisos Sin Firmar" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.table1}" id="table1">
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
											<ui:tableRowGroup binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.tableRowGroup1}" id="tableRowGroup1"
												rows="#{ApplicationBean1.cantidadFilasTablasAdmin}"
												selected="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.currentRowSelected}"
												sourceData="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.ldpFirmaPendienteTGI}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.tableColumn1}"
													id="tableColumn1" valign="middle" width="10">
													<ui:checkbox binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.checkbox1}" id="checkbox1"
														selected="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.selected}"
														selectedValue="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.selectedValue}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.tableColumn3}" headerText="Nombre"
													id="tableColumn3" sort="nombre" width="1000">
													<ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.staticText4}" id="staticText4"
														text="#{currentRow.value['nombre']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.tableColumn2}" headerText="Estado"
													id="tableColumn2" sort="estado" width="20">
													<ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.staticText3}" id="staticText3"
														text="#{currentRow.value['estado']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.messageGroup1}" id="messageGroup1"
											styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.btnFirmar_action}"
											binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.btnFirmar}" id="btnFirmar" styleClass="button"
											text="Firmar Permisos" />
										<ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.stSeparador}" escape="false"
											id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.btnCancelar_action}"
											binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.btnCancelar}" id="btnCancelar" styleClass="button"
											text="Cancelar" />
									</td>
								</tr>
							</tfoot>
						</table>
						<br /> <br /> <br />
					</div>
					<ui:hiddenField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.idSubSesion}" />
					<ui:script binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaTGI.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
