<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.head1}" id="head1"
				title="Consultar Obligación: Tasa Menor">
				<ui:link binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.link1}" id="link1"
					url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.body1}"
				focus="form1:tfNumeroLibretaSanitaria" id="body1" onLoad="parent.footer.location.reload();Init();"
				style="background-color: rgb(242, 242, 242); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnVolver')">
				<ui:form binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="gris">
							<caption>
								<ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.stTitulo}" id="stTitulo"
									styleClass="tituloABM" text="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.label5}" for="tfNombre" id="label5"
											styleClass="label" text="Nombre del Documento" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.tfNombre}" columns="40"
											disabled="true" id="tfNombre" styleClass="textFieldDisabled" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.label4}" for="tfFechaInicio"
											id="label4" styleClass="label" text="Inicio de Actividades" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.tfFechaInicio}" disabled="true"
											id="tfFechaInicio" maxLength="10" styleClass="textFieldDisabled" />
										<!--<ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.staticText1}" escape="false"
                                                           id="staticText1" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap"></td>
									<td nowrap="nowrap"></td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.label6}" for="tfFechaCese"
											id="label6" rendered="false" styleClass="label" text="Cese de Actividades" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.tfFechaCese}" id="tfFechaCese"
											maxLength="10" rendered="false" styleClass="textField" />
										<!--<ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.staticText2}" escape="false"
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
										<ui:label binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.label16}" for="tfPersona"
											id="label16" styleClass="label" text="Persona Solicitante" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.tfPersona}" columns="40"
											disabled="true" id="tfPersona" styleClass="textFieldDisabled" />
									</td>
									<td align="right" nowrap="nowrap"></td>
									<td nowrap="nowrap"></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.lblParcela}" for="tfParcela"
											id="lblParcela" styleClass="label" text="Parcela" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.tfParcela}" columns="40"
											disabled="true" id="tfParcela" styleClass="textFieldDisabled" />
										<ui:button action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.btnSeleccionarParcela_action}"
											binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.btnSeleccionarParcela}" escape="false"
											id="btnSeleccionarParcela" mini="true" rendered="false" styleClass="buttonSeleccionar" text="&amp;nbsp;"
											toolTip="Agregar/Modificar" />
										<a4j:commandButton id="btnLimpiarParcela" reRender="form1:tfParcela"
											binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.btnLimpiarParcela}"
											action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.btnLimpiarParcela_action}"
											styleClass="buttonLimpiarAjax" />
									</td>
									<td align="right" nowrap="nowrap"></td>
									<td nowrap="nowrap"></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap" style="height: 18px">
										<ui:label binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.label1}" id="label1"
											styleClass="label" text="Domicilio Postal" />
									</td>
									<td nowrap="nowrap" />
									<td align="right" nowrap="nowrap"></td>
									<td nowrap="nowrap"></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap"></td>
									<td colspan="3">
										<ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.stDomicilioPostal}"
											escape="false" id="stDomicilioPostal" styleClass="staticText" />
									</td>
								</tr>

								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:label binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.label9}" id="label9"
											styleClass="label9" text="Exenciones" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.table5}"
											id="table5" width="480">
											<script>
												<![CDATA[
                                                    /* ----- Functions for Table Preferences Panel ----- */
                                                    /*
                                                     * Toggle the table preferences panel open or closed
                                                     */
                                                    function togglePreferencesPanel() {
                                                        var table = document.getElementById("form1:table5");
                                                        table.toggleTblePreferencesPanel();
                                                    }
                                                    /* ----- Functions for Filter Panel ----- */
                                                    /*
                                                     * Return true if the filter menu has actually changed,
                                                     * so the corresponding event should be allowed to continue.
                                                     */
                                                    function filterMenuChanged() {
                                                        var table = document.getElementById("form1:table5");
                                                        return table.filterMenuChanged();
                                                    }
                                                    /*
                                                     * Toggle the custom filter panel (if any) open or closed.
                                                     */
                                                    function toggleFilterPanel() {
                                                        var table = document.getElementById("form1:table5");
                                                        return table.toggleTableFilterPanel();
                                                    }
                                                    /* ----- Functions for Table Actions ----- */
                                                    /*
                                                     * Initialize all rows of the table when the state
                                                     * of selected rows changes.
                                                     */
                                                    function initAllRows() {
                                                        var table = document.getElementById("form1:table5");
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
                                                        var table = document.getElementById("form1:table5");
                                                        table.selectGroupRows(rowGroupId, selected);
                                                    }
                                                    /*
                                                     * Disable all table actions if no rows have been selected.
                                                     */
                                                    function disableActions() {
                                                        // Determine whether any rows are currently selected
                                                        var table = document.getElementById("form1:table5");
                                                        var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
                                                        // Set disabled state for top actions
                                                        document.getElementById("form1:table5:tableActionsTop:deleteTop").setDisabled(disabled);
                                                        // Set disabled state for bottom actions
                                                        document.getElementById("form1:table5:tableActionsBottom:deleteBottom").setDisabled(disabled);
                                                    }]]></script>
											<ui:tableRowGroup binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.tableRowGroup5}"
												id="tableRowGroup5" sourceData="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.ldpExenciones}"
												sourceVar="currentRow">
												<ui:tableColumn align="center"
													binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.tableColumn20}" id="tableColumn20"
													valign="middle" width="10">
													<ui:radioButton binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.radioButton5}"
														id="radioButton5" label="" name="buttonGroup5"
														selected="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.RBSelected5}"
														selectedValue="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.currentRow5}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.tcExencion}"
													headerText="Exencion" id="tcExencion">
													<ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.stExencion}" id="stExencion"
														text="#{currentRow.value['stringRegistroExencion']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<div class="div" style="width: 290px; height: 15px;">Atributos Dinámicos</div>
										<table border="0" class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
											<tr>
												<td colspan="4">
													<ui:panelGroup binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.panelAtributoDinamico}"
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
										<ui:messageGroup binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.messageGroup1}"
											id="messageGroup1" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.btnVolver_action}"
											binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.btnVolver}" id="btnVolver" styleClass="button"
											text="Volver" />
									</td>
								</tr>
							</tfoot>
						</table>
						<br /> <br /> <br />
					</div>
					<ui:hiddenField binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ConsultarDocEspTasaMenor.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
