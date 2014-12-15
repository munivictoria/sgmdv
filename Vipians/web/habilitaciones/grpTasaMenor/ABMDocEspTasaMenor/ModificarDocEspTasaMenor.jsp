<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.head1}" id="head1"
				title="Modificar Obligación: Tasa Menor">
				<ui:link binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.link1}" id="link1"
					url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload(); Init();" style="background-color: rgb(242, 242, 236); -rave-layout: grid"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.stTitulo}" id="stTitulo"
									styleClass="tituloABM" text="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.label5}" for="tfNombre" id="label5"
											styleClass="label" text="Nombre del Documento" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.tfNombre}" columns="40"
											id="tfNombre" styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.label4}" for="tfFechaInicio"
											id="label4" styleClass="label" text="Inicio de Actividades" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.tfFechaInicio}"
											id="tfFechaInicio" styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
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
										<ui:label binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.lblPersona}" for="tfPersona"
											id="lblPersona" styleClass="label" text="Persona" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.tfPersona}" columns="40"
											disabled="true" id="tfPersona" styleClass="textFieldDisabled" />
									</td>
									<td align="right" nowrap="nowrap"></td>
									<td nowrap="nowrap"></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.lblParcela}" for="tfParcela"
											id="lblParcela" styleClass="label" text="Parcela" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.tfParcela}" columns="40"
											disabled="true" id="tfParcela" styleClass="textFieldDisabled" />
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
										<ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.staticText3}" id="staticText3"
											styleClass="label" text="Persona Solicitante" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:table binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.table1}" id="table1">
											<script>
												<![CDATA[
                                                    /* Functions for Table Preferences Panel */
                                                    /*
                                                     * Toggle the table preferences panel open or closed
                                                     */
                                                    function togglePreferencesPanel() {
                                                        var table = document.getElementById("form1:table1");
                                                        table.toggleTblePreferencesPanel();
                                                    }
                                                    /*  Functions for Filter Panel */
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
                                                    /*  Functions for Table Actions */
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
											<ui:tableRowGroup binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.tableRowGroup1}"
												emptyDataMsg="Ningún registro encontrado." id="tableRowGroup1"
												sourceData="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.ldpPropietarios}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.tableColumn1}"
													id="tableColumn1" valign="middle" width="10">
													<ui:radioButton binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.radioButton1}"
														id="radioButton1" label="" name="buttonGroup"
														selected="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.RBSelected}"
														selectedValue="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.currentRow3}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.tableColumn2}"
													headerText="Propietarios" id="tableColumn2" sort="persona">
													<ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.stPersona}" id="stPersona"
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
										<ui:label binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.label1}" id="label1"
											styleClass="label" text="Domicilio Postal" />
									</td>
									<td nowrap="nowrap">
										<ui:button
											action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.btnSeleccionarDomicilioPostal_action}"
											binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.btnSeleccionarDomicilioPostal}" escape="false"
											id="btnSeleccionarDomicilioPostal" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Agregar/Modificar" />
										<a4j:commandButton id="btnLimpiarDomicilioPostal" reRender="form1:stDomicilioPostal"
											binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.btnLimpiarDomicilioPostal}"
											action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.btnLimpiarDomicilioPostal_action}"
											styleClass="buttonLimpiarAjax" />
										<ui:button
											action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.btnSeleccionarDomicilioSolicitante_action}"
											binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.btnSeleccionarDomicilioSolicitante}"
											id="btnSeleccionarDomicilioSolicitante" mini="true" styleClass="button" text="Del Solicitante" />
										<ui:button
											action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.btnSeleccionarDomicilioParcela_action}"
											binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.btnSeleccionarDomicilioParcela}"
											id="btnSeleccionarDomicilioParcela" mini="true" styleClass="button" text="De la Parcela" />
									</td>
									<td align="right" nowrap="nowrap"></td>
									<td nowrap="nowrap"></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap"></td>
									<td colspan="3">
										<ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.stDomicilioPostal}"
											escape="false" id="stDomicilioPostal" styleClass="staticText" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<div class="div" style="width: 290px; height: 15px;">Atributos Dinámicos</div>
										<table border="0" class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
											<tr>
												<td colspan="4">
													<ui:panelGroup binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.panelAtributoDinamico}"
														id="panelAtributoDinamico">
														<!-- AQUI VA LO QUE SE CREA DINAMICAMENTE -->
													</ui:panelGroup>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.messageGroup1}"
											id="messageGroup1" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.btnGuardar_action}"
											binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.btnGuardar}" id="btnGuardar"
											styleClass="button" text="Guardar" />
										<ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.stSeparador}" escape="false"
											id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.btnCancelar_action}"
											binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.btnCancelar}" id="btnCancelar"
											styleClass="button" text="Cancelar" />
									</td>
								</tr>
							</tfoot>
						</table>
						<br /> <br /> <br />
					</div>
					<ui:hiddenField binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ModificarDocEspTasaMenor.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
