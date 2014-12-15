<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.page1}" id="page1">
			<ui:html binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.html1}" id="html1">
			<ui:head binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.head1}" id="head1">
				<ui:link binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
						function cargarComportamientoJQuery() {
							mascaraCuimEnTextField("#form1:tfCuit");
						}
					
						$(document).ready(function() {
							cargarComportamientoJQuery();
						});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.body1}" focus="form1:tfRazonSocial" id="body1"
				onLoad="parent.footer.location.reload(); Init();" 
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>

								<ui:staticText binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.label5}" for="tfRazonSocial" id="label5" styleClass="label"
											text="Razón Social" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.tfRazonSocial}" columns="40" id="tfRazonSocial"
											styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.lblNombreFantasia}" for="tfNombreFantasia"
											id="lblNombreFantasia" styleClass="label" text="Nombre de fantasía" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.tfNombreFantasia}" columns="40" id="tfNombreFantasia"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.label4}" for="tfCuit" id="label4" styleClass="label"
											text="CUIT" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.tfCuit}" id="tfCuit" styleClass="textField"
											maxLength="13" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.lblNroSociedad}" for="tfNroSociedad" id="lblNroSociedad"
											styleClass="label" text="Número de sociedad" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.tfNroSociedad}" id="tfNroSociedad" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.lblTipoSocietario}" for="ddTipoSocietario"
											id="lblTipoSocietario" styleClass="label" text="Tipo societario" />
									</td>
									<td>
										<ui:dropDown binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.ddTipoSocietario}" id="ddTipoSocietario"
											styleClass="textField" items="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.ddTipoSocDefaultOptions.options}" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.lblOrganismoEmisor}" for="ddOrganismoEmisor"
											id="lblOrganismoEmisor" styleClass="label" text="Organismo emisor" />
									</td>
									<td>
										<ui:dropDown binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.ddOrganismoEmisor}" id="ddOrganismoEmisor"
											styleClass="textField" items="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.ddOrganismoDefaultOptions.options}" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.lblNroConvenio}" for="tfNroConvenio" id="lblNroConvenio"
											styleClass="label" text="Nro Convenio Multilateral" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.tfNroConvenio}" columns="40" id="tfNroConvenio"
											styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.lblIngresosBrutos}" for="tfIngresosBrutos"
											id="lblIngresosBrutos" styleClass="label" text="Número de ingresos brutos" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.tfNroIngresosBrutos}" id="tfIngresosBrutos"
											styleClass="textField" />
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
										<ui:label binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.label10}" for="tfTelefono" id="label10" styleClass="label"
											text="Teléfono" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.tfTelefono}" columns="30" id="tfTelefono"
											styleClass="textField" onKeyPress="return ValidarNum(event,this)" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.label11}" for="tfCelular" id="label11" styleClass="label"
											text="Celular" />
									</td>
									<td colspan="4">
										<ui:textField binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.tfCelular}" columns="30" id="tfCelular"
											styleClass="textField" onKeyPress="return ValidarNum(event,this)" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.label12}" for="tfEmail" id="label12" styleClass="label"
											text="E-mail" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.tfEmail}" columns="40" id="tfEmail"
											styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.label2}" for="tfNacionalidad" id="label2" styleClass="label"
											text="Nacionalidad" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.tfNacionalidad}" columns="30" id="tfNacionalidad"
											styleClass="textField" />
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
										<ui:label binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.label13}" id="label13" styleClass="label"
											text="Domicilio Fiscal" />
									</td>
									<td colspan="1">
										<ui:button action="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.btnCopiarDomicilioPostal_action}"
											binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.btnCopiarDomicilioPostal}" escape="false"
											id="btnCopiarDomicilioPostal" mini="true" styleClass="buttonAgregar" text="&amp;nbsp;" toolTip="Copiar Domicilio Postal" />
										<ui:button action="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.btnSeleccionarDomicilio_action}"
											binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.btnSeleccionarDomicilio}" escape="false" id="btnSeleccionarDomicilio"
											mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Agregar/Modificar" />
									</td>

									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.label14}" id="label14" styleClass="label"
											text="Domicilio Postal" />
									</td>
									<td colspan="3">
										<ui:button action="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.btnCopiarDomicilioFiscal_action}"
											binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.btnCopiarDomicilioFiscal}" escape="false"
											id="btnCopiarDomicilioFiscal" mini="true" styleClass="buttonAgregar" text="&amp;nbsp;" toolTip="Copiar Domicilio Fiscal" />
										<ui:button action="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.btnSeleccionarDomicilioPostal_action}"
											binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.btnSeleccionarDomicilioPostal}" escape="false"
											id="btnSeleccionarDomicilioPostal" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Agregar/Modificar" />
									</td>
								</tr>
								<tr>
									<td></td>
									<td colspan="1">
										<ui:staticText binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.stDomicilio}" escape="false" id="stDomicilio"
											styleClass="staticText" />
									</td>

									<td></td>
									<td colspan="3">
										<ui:staticText binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.stDomicilioPostal}" escape="false"
											id="stDomicilioPostal" styleClass="staticText" />
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
										<ui:label binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.label1}" id="label1" styleClass="label2" text="Socios" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.table1}" id="table1" width="479">
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
											<ui:tableRowGroup binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.tableRowGroup1}" id="tableRowGroup1"
												sourceData="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.ldpSocios}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.tableColumn1}" id="tableColumn1"
													valign="middle" width="10">
													<ui:radioButton binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.radioButton1}" id="radioButton1" label=""
														name="buttonGroup" selected="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.RBSelected}"
														selectedValue="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.tableColumn2}" headerText="Persona fisica"
													id="tableColumn2">
													<ui:staticText binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.staticText1}" id="staticText1"
														text="#{currentRow.value['persona']}" />
												</ui:tableColumn>
												<ui:tableColumn align="center" binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.tableColumn4}" headerText="Cargo"
													id="tableColumn4" valign="middle" width="10">
													<ui:dropDown binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.ddCargo}" id="ddEstado"
														items="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.ddCargoSocietarioDefaultOptions.options}" styleClass="textField"
														selected="#{currentRow.value['cargo']}" converter="EnumConverter" immediate="false" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.groupPanel1}" id="groupPanel1">

													<ui:button action="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.btnAgregar_action}"
														binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.btnAgregar}" id="btnAgregarPF" styleClass="button"
														text="Agregar PF" />
													<ui:button action="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.btnAgregarPJ_action}"
														binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.btnAgregarPJ}" id="btnAgregarPJ" styleClass="button"
														text="Agregar PJ" />
													 <a4j:commandButton action="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.btnQuitar_action}"
                                                       binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.btnQuitar}" id="btnQuitar"
                                                       value="Quitar" styleClass="btnAjax" reRender="table1" onmousedown="reemplazarClickConConfirmacion(this, '');" />
													<ui:staticText binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.staticText4}" escape="false" id="staticText4"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
												       <a4j:commandButton action="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.btnQuitarTodos_action}"
                                                       binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.btnQuitarTodos}"
                                                       id="btnQuitarTodos" value="Quitar todos" styleClass="btnAjax" reRender="table1" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<div class="div" style="width: 290px; height: 15px;">Atributos Dinámicos</div>
										<table border="0" class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
											<tr>
												<td colspan="4">
													<ui:panelGroup binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.panelAtributoDinamico}" id="panelAtributoDinamico">
														<!-- AQUI VA LO QUE SE CREA DINAMICAMENTE -->
													</ui:panelGroup>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right">
										<ui:label binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
									</td>
									<td>
										<ui:textArea binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.tablaLogs}" id="tbLogsAuditoria" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.messageGroup1}" id="messageGroup1"
											styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.btnGuardar_action}"
											binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.btnCancelar_action}"
											binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
						<br /> <br /> <br />
					</div>
					<ui:hiddenField binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.idSubSesion}" />
					<ui:script binding="#{framework$ABMPersonaJuridica$ABMPersonaJuridica.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
