<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{catastro$ABMParcela$SubdividirParcela.page1}" id="page1">
			<ui:html binding="#{catastro$ABMParcela$SubdividirParcela.html1}" id="html1">
			<ui:head binding="#{catastro$ABMParcela$SubdividirParcela.head1}" id="head1" title="Subdividir Parcelas">
				<ui:link binding="#{catastro$ABMParcela$SubdividirParcela.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{catastro$ABMParcela$SubdividirParcela.body1}" focus="form1:tfTitularSubdivision" id="body1"
				onLoad="parent.footer.location.reload(); Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{catastro$ABMParcela$SubdividirParcela.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{catastro$ABMParcela$SubdividirParcela.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{catastro$ABMParcela$SubdividirParcela.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{catastro$ABMParcela$SubdividirParcela.label1}" id="label1" styleClass="label" text="Titular" />
									</td>
									<td>
										<ui:textField binding="#{catastro$ABMParcela$SubdividirParcela.tfTitularSubdivision}" disabled="true" columns="40"
											id="tfTitularSubdivision" styleClass="textField" />
										<ui:button action="#{catastro$ABMParcela$SubdividirParcela.btnSeleccionarPersonaFisica_action}"
											binding="#{catastro$ABMParcela$SubdividirParcela.btnSeleccionarPersonaFisica}" escape="false" id="btnSeleccionarPersonaFisica"
											mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Fisica" />
										<ui:button action="#{catastro$ABMParcela$SubdividirParcela.btnSeleccionarPersonaJuridica_action}"
											binding="#{catastro$ABMParcela$SubdividirParcela.btnSeleccionarPersonaJuridica}" escape="false"
											id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar Persona Juridica" />
										<a4j:commandButton id="btnLimpiarTitular" reRender="form1:tfTitularSubdivision" 
											binding="#{catastro$ABMParcela$SubdividirParcela.btnLimpiarTitular}"
											action="#{catastro$ABMParcela$SubdividirParcela.btnLimpiarTitular_action}" styleClass="buttonLimpiarAjax" />

									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{catastro$ABMParcela$SubdividirParcela.label2}" id="label2" styleClass="label" text="Superficie" />
									</td>
									<td>
										<ui:textField binding="#{catastro$ABMParcela$SubdividirParcela.tfSuperficieSubdivision}" columns="10" id="tfSuperficieSubdivision"
											styleClass="textField" onKeyPress="return ValidarFloat(event,this)" />

									</td>
									<td align="right" nowrap="nowrap">
										<ui:button action="#{catastro$ABMParcela$SubdividirParcela.btnAgregarSubdivision_action}"
											binding="#{catastro$ABMParcela$SubdividirParcela.btnAgregarSubdivision}" id="btnAgregarSubdivision" escape="false" mini="true"
											text="&amp;nbsp;" styleClass="buttonAgregar" />
										<ui:button action="#{catastro$ABMParcela$SubdividirParcela.btnEliminarSubdivision_action}"
											binding="#{catastro$ABMParcela$SubdividirParcela.btnEliminarSubdivision}" id="btnEliminarSubdivision" escape="false" mini="true"
											styleClass="buttonRemove" text="&amp;nbsp;" />
									</td>
								</tr>
								<tr>
									<td colspan="6">
										<ui:table binding="#{catastro$ABMParcela$SubdividirParcela.table1}" id="table1">
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
											<ui:tableRowGroup binding="#{catastro$ABMParcela$SubdividirParcela.tableRowGroup1}" emptyDataMsg="Ningún registro encontrado."
												id="tableRowGroup1" sourceData="#{catastro$ABMParcela$SubdividirParcela.ldpSubParcelas}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{catastro$ABMParcela$SubdividirParcela.tableColumn4}" id="tableColumn4" valign="middle"
													width="10">
													<ui:radioButton binding="#{catastro$ABMParcela$SubdividirParcela.radioButton1}" id="radioButton1" label="" name="buttonGroup"
														selected="#{catastro$ABMParcela$SubdividirParcela.RBSelected}"
														selectedValue="#{catastro$ABMParcela$SubdividirParcela.currentRow}" />
												</ui:tableColumn>

												<ui:tableColumn binding="#{catastro$ABMParcela$SubdividirParcela.tcTitular}" headerText="Titular" id="tcTitular" sort="titular">

													<ui:staticText binding="#{catastro$ABMParcela$SubdividirParcela.staticText5}" id="staticText5"
														text="#{currentRow.value['titular']}" />

												</ui:tableColumn>
												<ui:tableColumn binding="#{catastro$ABMParcela$SubdividirParcela.tcSuperficie}" headerText="Superficie" id="tcSuperficie"
													sort="superficie">
													<ui:staticText binding="#{catastro$ABMParcela$SubdividirParcela.staticText6}" id="staticText6"
														text="#{currentRow.value['superficie']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>

										</ui:table>
									</td>
								</tr>
							</tbody>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tr>
								<td colspan="4" style="height: 22px">
									<ui:messageGroup binding="#{catastro$ABMParcela$SubdividirParcela.messageGroup1}" id="messageGroup1" styleClass="grupoMsg" />
								</td>
							</tr>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="true">
										<ui:button action="#{catastro$ABMParcela$SubdividirParcela.btnGuardar_action}"
											binding="#{catastro$ABMParcela$SubdividirParcela.btnGuardar}" id="btnGuardar" styleClass="button" text="Agregar" />
										<ui:staticText binding="#{catastro$ABMParcela$SubdividirParcela.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{catastro$ABMParcela$SubdividirParcela.btnCancelar_action}"
											binding="#{catastro$ABMParcela$SubdividirParcela.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{catastro$ABMParcela$SubdividirParcela.hidIdPagina}" id="hidIdPagina"
						text="#{catastro$ABMParcela$SubdividirParcela.idPagina}" />
					<ui:hiddenField binding="#{catastro$ABMParcela$SubdividirParcela.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{catastro$ABMParcela$SubdividirParcela.idSubSesion}" />
					<ui:script binding="#{catastro$ABMParcela$SubdividirParcela.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>