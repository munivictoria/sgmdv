<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page"
 xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.page1}" id="page1">
            <ui:html binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.html1}" id="html1">
                <ui:head binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.head1}" id="head1">
                    <ui:link binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.body1}" focus="form1:tfNombre" id="body1"
                    onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="2">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.label4}" for="tfNombre" id="label4"
                                                styleClass="label" text="Nombre"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.tfNombre}" columns="40" id="tfNombre" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.label5}" for="taDescripcion" id="label5"
                                                styleClass="label" text="Descripción"/>
                                        </td>
                                        <td>
                                            <ui:textArea binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.taDescripcion}" columns="40"
                                                id="taDescripcion" rows="5" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label for="tfFormulaDeCalculo" id="lblFormulaCalculo"
                                                styleClass="label" text="Fórmula Calculo Interes"/>
                                        </td>
                                        <td>
											<ui:textField binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.tfFormulaDeCalculo}" columns="40"
												id="tfFormulaDeCalculo" styleClass="textFieldDisabled" disabled="true" />
											<ui:button id="btnSeleccionarFormulaDeCalculo"
												binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.btnSeleccionarFormulaDeCalculo}"
												action="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.btnSeleccionarFormulaDeCalculo_action}" escape="false"
												mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
											<a4j:commandButton id="btnLimpiarFormulaDeCalculo" reRender="form1:tfFormulaDeCalculo" title="Limpiar"
												binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.btnLimpiarFormulaDeCalculo}"
												action="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.btnLimpiarFormulaDeCalculo_action}"
												styleClass="buttonLimpiarAjax" />
										</td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
									<td colspan="2">
										<ui:label binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.lblCuentas}" id="lblCuentas"
											styleClass="label57" text="Cuentas" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.tablaCuentas}"
											id="tablaCuentas">
											<script>
												<![CDATA[
												/* ----- Functions for Table Preferences Panel ----- */
												/*
												 * Toggle the table preferences panel open or closed
												 */
												function togglePreferencesPanel() {
													var table = document
															.getElementById("form1:tablaCuentas");
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
															.getElementById("form1:tablaCuentas");
													return table
															.filterMenuChanged();
												}
												/*
												 * Toggle the custom filter panel (if any) open or closed.
												 */
												function toggleFilterPanel() {
													var table = document
															.getElementById("form1:tablaCuentas");
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
															.getElementById("form1:tablaCuentas");
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
															.getElementById("form1:tablaCuentas");
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
															.getElementById("form1:tablaCuentas");
													var disabled = (table
															.getAllSelectedRowsCount() > 0) ? false
															: true;
													// Set disabled state for top actions
													document
															.getElementById(
																	"form1:tablaCuentas:tableActionsTop:deleteTop")
															.setDisabled(
																	disabled);
													// Set disabled state for bottom actions
													document
															.getElementById(
																	"form1:tablaCuentas:tableActionsBottom:deleteBottom")
															.setDisabled(
																	disabled);
												}
												]]>
											</script>
											<ui:tableRowGroup binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.trgCuentas}" id="trgCuentas"
												sourceData="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.ldpCuentas}" sourceVar="currentRowCuentas">
												<ui:tableColumn align="center" binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.tcRbCuentas}" id="tcRbCuentas"
													valign="middle" width="10">
													<ui:radioButton binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.rbCuentas}" id="rbCuentas" label=""
														name="buttonGroupCuentas" selected="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.RBSelectedCuentas}"
														selectedValue="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.currentRowCuentas}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.tcCodigoImputacion}" headerText="Código Imputación" id="tcCodigoImputacion" sort="codigoImputacion">
													<ui:staticText binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.stCodigoImputacion}" id="stCodigoImputacion" text="#{currentRowCuentas.value['cuenta'].codigoImputacion}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.tcNombreCuenta}" headerText="Nombre" id="tcNombreCuenta" sort="nombre">
													<ui:staticText binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.stNombreCuenta}" id="stNombreCuenta" text="#{currentRowCuentas.value['cuenta'].nombre}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.tcObligatoria}" headerText="Obligatoria" id="tcObligatoria" sort="obligatoria">
													<ui:checkbox binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.ckbObligatoria}" id="ckbObligatoria" selected="#{currentRowCuentas.value['obligatoria']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.tcMontoPorDefecto}" headerText="Monto Por Defecto" id="tcMontoPorDefecto" sort="montoPorDefecto">
													<ui:textField binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.tfMontoPorDefecto}" id="tfMontoPorDefecto" text="#{currentRowCuentas.value['montoPorDefecto']}"/>
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.pgCuentas}" id="pgCuentas">
													<ui:button action="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.btnAgregarCuenta_action}"
														binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.btnAgregarCuenta}" id="btnAgregarCuenta"
														styleClass="button" text="Agregar"/>
													<a4j:commandButton action="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.btnQuitarCuenta_action}"
														binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.btnQuitarCuenta}" id="btnQuitarCuenta" 
														value="Quitar" styleClass="btnAjax" reRender="tablaCuentas" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
                                    <td>
                                        <br/>
                                    </td>
                                </tr>
								<tr>
									<td colspan="2">
										<ui:label binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.lblRoles}" id="lblRoles"
											styleClass="label57" text="Roles" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.tablaRoles}"
											id="tablaRoles">
											<script>
												<![CDATA[
												/* ----- Functions for Table Preferences Panel ----- */
												/*
												 * Toggle the table preferences panel open or closed
												 */
												function togglePreferencesPanel() {
													var table = document
															.getElementById("form1:tablaRoles");
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
															.getElementById("form1:tablaRoles");
													return table
															.filterMenuChanged();
												}
												/*
												 * Toggle the custom filter panel (if any) open or closed.
												 */
												function toggleFilterPanel() {
													var table = document
															.getElementById("form1:tablaRoles");
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
															.getElementById("form1:tablaRoles");
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
															.getElementById("form1:tablaRoles");
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
															.getElementById("form1:tablaRoles");
													var disabled = (table
															.getAllSelectedRowsCount() > 0) ? false
															: true;
													// Set disabled state for top actions
													document
															.getElementById(
																	"form1:tablaRoles:tableActionsTop:deleteTop")
															.setDisabled(
																	disabled);
													// Set disabled state for bottom actions
													document
															.getElementById(
																	"form1:tablaRoles:tableActionsBottom:deleteBottom")
															.setDisabled(
																	disabled);
												}
												]]>
											</script>
											<ui:tableRowGroup binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.trgRoles}" id="trgRoles"
												sourceData="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.ldpRoles}" sourceVar="currentRowRoles">
												<ui:tableColumn align="center" binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.tcRbRoles}" id="tcRbRoles"
													valign="middle" width="10">
													<ui:radioButton binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.rbRoles}" id="rcRoles" label=""
														name="buttonGroupRoles" selected="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.RBSelectedRoles}"
														selectedValue="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.currentRowRoles}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.tcNombreRol}" headerText="Nombre" id="tcNombreRol" sort="nombre">
													<ui:staticText binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.stNombreRol}" id="stNombreRol" text="#{currentRowRoles.value['nombre']}" />
												</ui:tableColumn>
												<!--<ui:tableColumn binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.tcFirma}" headerText="Firma" id="tcFirma" sort="firma">
													<ui:staticText binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.stFirma}" id="stFirma" text="#{currentRowRoles.value['firma']}" />
												</ui:tableColumn>-->
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.pgRoles}" id="pgRoles">
													<ui:button action="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.btnAgregarRol_action}"
														binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.btnAgregarRol}" id="btnAgregarRol"
														styleClass="button" text="Agregar"/>
													<a4j:commandButton action="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.btnQuitarRol_action}"
														binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.btnQuitarRol}" id="btnQuitarRol" 
														value="Quitar" styleClass="btnAjax" reRender="tablaRoles" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
                                   <td>
                                       <br/>
                                   </td>
                               </tr>
								<tr>
									<td colspan="2">
										<ui:label binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.lblUsuarios}" id="lblUsuarios"
											styleClass="label57" text="Usuarios" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.tablaUsuarios}"
											id="tablaUsuarios">
											<script>
												<![CDATA[
												/* ----- Functions for Table Preferences Panel ----- */
												/*
												 * Toggle the table preferences panel open or closed
												 */
												function togglePreferencesPanel() {
													var table = document
															.getElementById("form1:tablaUsuarios");
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
															.getElementById("form1:tablaUsuarios");
													return table
															.filterMenuChanged();
												}
												/*
												 * Toggle the custom filter panel (if any) open or closed.
												 */
												function toggleFilterPanel() {
													var table = document
															.getElementById("form1:tablaUsuarios");
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
															.getElementById("form1:tablaUsuarios");
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
															.getElementById("form1:tablaUsuarios");
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
															.getElementById("form1:tablaUsuarios");
													var disabled = (table
															.getAllSelectedRowsCount() > 0) ? false
															: true;
													// Set disabled state for top actions
													document
															.getElementById(
																	"form1:tablaUsuarios:tableActionsTop:deleteTop")
															.setDisabled(
																	disabled);
													// Set disabled state for bottom actions
													document
															.getElementById(
																	"form1:tablaUsuarios:tableActionsBottom:deleteBottom")
															.setDisabled(
																	disabled);
												}
												]]>
											</script>
											<ui:tableRowGroup binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.trgUsuarios}" id="trgUsuarios"
												sourceData="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.ldpUsuarios}" sourceVar="currentRowUsuarios">
												<ui:tableColumn align="center" binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.tcRbUsuarios}" id="tcRbUsuarios"
													valign="middle" width="10">
													<ui:radioButton binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.rbUsuarios}" id="rcUsuarios" label=""
														name="buttonGroupUsuarios" selected="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.RBSelectedUsuarios}"
														selectedValue="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.currentRowUsuarios}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.tcNombreUsuario}" headerText="Nombre" id="tcNombreUsuario" sort="user">
													<ui:staticText binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.stNombreUsuario}" id="stFirma" text="#{currentRowUsuarios.value['user']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.tcPersonaFisica}" headerText="Persona Física" id="tcPersonaFisica" sort="nombrePersonaFisica">
													<ui:staticText binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.stPersonaFisica}" id="stPersonaFisica" text="#{currentRowUsuarios.value['nombrePersonaFisica']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.tcEstado}" headerText="Estado" id="tcEstado" sort="estado">
													<ui:staticText binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.stEstado}" id="stEstado" text="#{currentRowUsuarios.value['estado']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.pgUsuarios}" id="pgUsuarios">
													<ui:button action="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.btnAgregarUsuario_action}"
														binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.btnAgregarUsuario}" id="btnAgregarUsuario"
														styleClass="button" text="Agregar"/>
													<a4j:commandButton action="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.btnQuitarUsuario_action}"
														binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.btnQuitarUsuario}" id="btnQuitarUsuario" 
														value="Quitar" styleClass="btnAjax" reRender="tablaUsuarios" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:messageGroup binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="2" nowrap="nowrap">
                                            <ui:button action="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.btnGuardar_action}"
                                                binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.btnGuardar}" id="btnGuardar" styleClass="button" />
                                            <ui:staticText binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.stSeparador}" escape="false"
                                                id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.btnCancelar_action}"
                                                binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.btnCancelar}" id="btnCancelar" styleClass="button" />
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.hidIdPagina}" id="hidIdPagina" text="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.idPagina}"/>
                        <ui:hiddenField binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.hidIdSubSesion}" id="hidIdSubSesion" text="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.idSubSesion}"/>
                        <ui:script binding="#{comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
