<?xml version="1.0" encoding="UTF-8"?>
<!-- © Copyright 2015, CoDeSoft Todos los derechos reservados. -->
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMReporte$ABMReporte.page1}" id="page1">
			<ui:html binding="#{framework$ABMReporte$ABMReporte.html1}" id="html1">
			<ui:head binding="#{framework$ABMReporte$ABMReporte.head1}" id="head1">
				<ui:link binding="#{framework$ABMReporte$ABMReporte.link1}" id="link1" url="/resources/stylesheet.css" />
				<style>
.ui-dialog .ui-state-error {
	padding: .3em;
}

.validateTips {
	border: 1px solid transparent;
	padding: 0.3em;
}
</style>
				<script>
					<![CDATA[

					function cargarComportamientoJQuery() {
						$("#opciones-modal-form").dialog({
							autoOpen: false,
							height: 'auto',
							width: 'auto',
							resizable: false,
							modal: true,
							close: function() {
								return false;
							}
						}).parent().appendTo($("#form1"));
					}

					function abrirModalOpciones() {
						if(!$("#form1\\:messageGroup1").is(':visible')) {
							$("#opciones-modal-form").dialog("open");
						}
					}

					function cerrarModalOpciones() {
						$("#opciones-modal-form").dialog("close");
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});

					function validarNombreAtributo(e) {
						var tecla = (document.all) ? e.keyCode : e.which;
						if(tecla == 8)
							return true;
						if(tecla == 0)
							return true;
						
						var nombre = (document.all) ? e.keyCode : e.which;
						
						patron = /[A-Za-z0-9\\_]/;
						te = String.fromCharCode(nombre);
						
						return patron.test(te);
					}

					]]>
				</script>
			</ui:head>
			<ui:body binding="#{framework$ABMReporte$ABMReporte.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMReporte$ABMReporte.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{framework$ABMReporte$ABMReporte.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{framework$ABMReporte$ABMReporte.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<table align="center" width="70%">
											<tr>
												<td>
													<br />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label for="tfNombre" id="label4" styleClass="label" text="Nombre" />
												</td>
												<td>
													<ui:textField binding="#{framework$ABMReporte$ABMReporte.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
												</td>
												<td align="right" nowrap="nowrap">
													<ui:label for="tfNombreJasper" id="lblNombreJasper" styleClass="label" text="Nombre Jasper" />
												</td>
												<td>
													<ui:textField binding="#{framework$ABMReporte$ABMReporte.tfNombreJasper}" columns="40" id="tfNombreJasper"
														styleClass="textField" />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label for="ddTipo" id="lblTipo" styleClass="label" text="Tipo" />
												</td>
												<td>
													<ui:dropDown binding="#{framework$ABMReporte$ABMReporte.ddTipo}"
														items="#{framework$ABMReporte$ABMReporte.ddTipoDefaultOptions.options}" id="ddTipo" styleClass="textField" />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label for="tfRecurso" id="label5" styleClass="label" text="Recurso" />
												</td>
												<td>
													<ui:textField binding="#{framework$ABMReporte$ABMReporte.tfRecurso}" columns="40" id="tfRecurso" styleClass="textFieldDisabled"
														disabled="true" />
													<ui:button action="#{framework$ABMReporte$ABMReporte.btnBuscarRecurso_action}"
														binding="#{framework$ABMReporte$ABMReporte.btnBuscarRecurso}" escape="false" id="btnSeleccionarRecurso" mini="true"
														styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
													<a4j:commandButton id="btnLimpiarRecurso" reRender="form1:tfRecurso" title="Limpiar"
														binding="#{framework$ABMReporte$ABMReporte.btnLimpiarRecurso}"
														action="#{framework$ABMReporte$ABMReporte.btnLimpiarRecurso_action}" styleClass="buttonLimpiarAjax" />
												</td>
												<td align="right" nowrap="nowrap">
													<ui:label for="ddTipo" id="lblSeleccionaEntidad" styleClass="label" text="Selecciona Entidad" />
												</td>
												<td>
													<ui:dropDown binding="#{framework$ABMReporte$ABMReporte.ddSeleccionaEntidad}"
														items="#{framework$ABMReporte$ABMReporte.ddSeleccionaEntidadDefaultOptions.options}" id="ddSeleccionaEntidad"
														styleClass="textField" />
												</td>
											</tr>
											<tr>
												<td>
													<br />
												</td>
											</tr>
											<tr>
												<td colspan="2">
													<ui:label id="lblUsuarios" styleClass="label" text="Usuarios" />
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<ui:table augmentTitle="false" binding="#{framework$ABMReporte$ABMReporte.tablaUsuarios}" id="tablaUsuarios">
														<script>
															<![CDATA[
															/* ----- Functions for Table Preferences Panel ----- */
															/*
															 * Toggle the table preferences panel open or closed
															 */
															function togglePreferencesPanel() {
																var table = document.getElementById("form1:tablaUsuarios");
																table.toggleTblePreferencesPanel();
															}
															/* ----- Functions for Filter Panel ----- */
															/*
															 * Return true if the filter menu has actually changed,
															 * so the corresponding event should be allowed to continue.
															 */
															function filterMenuChanged() {
																var table = document.getElementById("form1:tablaUsuarios");
																return table.filterMenuChanged();
															}
															/*
															 * Toggle the custom filter panel (if any) open or closed.
															 */
															function toggleFilterPanel() {
																var table = document.getElementById("form1:tablaUsuarios");
																return table.toggleTableFilterPanel();
															}
															/* ----- Functions for Table Actions ----- */
															/*
															 * Initialize all rows of the table when the state
															 * of selected rows changes.
															 */
															function initAllRows() {
																var table = document.getElementById("form1:tablaUsuarios");
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
																var table = document.getElementById("form1:tablaUsuarios");
																table.selectGroupRows(rowGroupId, selected);
															}
															/*
															 * Disable all table actions if no rows have been selected.
															 */
															function disableActions() {
																// Determine whether any rows are currently selected
																var table = document.getElementById("form1:tablaUsuarios");
																var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
																// Set disabled state for top actions
																document.getElementById("form1:tablaUsuarios:tableActionsTop:deleteTop").setDisabled(
																		disabled);
																// Set disabled state for bottom actions
																document.getElementById("form1:tablaUsuarios:tableActionsBottom:deleteBottom")
																		.setDisabled(disabled);
															}
															]]>
														</script>
														<ui:tableRowGroup binding="#{framework$ABMReporte$ABMReporte.trgUsuarios}" id="trgUsuarios"
															sourceData="#{framework$ABMReporte$ABMReporte.ldpUsuarios}" sourceVar="currentRowUsuarios">
															<ui:tableColumn align="center" binding="#{framework$ABMReporte$ABMReporte.tcRbUsuarios}" id="tcRbUsuarios" valign="middle"
																width="10">
																<ui:radioButton binding="#{framework$ABMReporte$ABMReporte.rbUsuarios}" id="rcUsuarios" label="" name="buttonGroupUsuarios"
																	selected="#{framework$ABMReporte$ABMReporte.RBSelectedUsuarios}"
																	selectedValue="#{framework$ABMReporte$ABMReporte.currentRowUsuarios}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{framework$ABMReporte$ABMReporte.tcNombreUsuario}" headerText="Nombre" id="tcNombreUsuario"
																sort="user">
																<ui:staticText binding="#{framework$ABMReporte$ABMReporte.stNombreUsuario}" id="stFirma"
																	text="#{currentRowUsuarios.value['user']}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{framework$ABMReporte$ABMReporte.tcPersonaFisica}" headerText="Persona Física" id="tcPersonaFisica"
																sort="nombrePersonaFisica">
																<ui:staticText binding="#{framework$ABMReporte$ABMReporte.stPersonaFisica}" id="stPersonaFisica"
																	text="#{currentRowUsuarios.value['nombrePersonaFisica']}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{framework$ABMReporte$ABMReporte.tcEstado}" headerText="Estado" id="tcEstado" sort="estado">
																<ui:staticText binding="#{framework$ABMReporte$ABMReporte.stEstado}" id="stEstado"
																	text="#{currentRowUsuarios.value['estado']}" />
															</ui:tableColumn>
														</ui:tableRowGroup>
														<f:facet name="actionsTop">
															<ui:panelGroup binding="#{framework$ABMReporte$ABMReporte.pgUsuarios}" id="pgUsuarios">
																<ui:button action="#{framework$ABMReporte$ABMReporte.btnAgregarUsuario_action}"
																	binding="#{framework$ABMReporte$ABMReporte.btnAgregarUsuario}" id="btnAgregarUsuario" styleClass="button" text="Agregar" />
																<a4j:commandButton action="#{framework$ABMReporte$ABMReporte.btnQuitarUsuario_action}"
																	binding="#{framework$ABMReporte$ABMReporte.btnQuitarUsuario}" id="btnQuitarUsuario" value="Quitar" styleClass="btnAjax"
																	reRender="tablaUsuarios" />
															</ui:panelGroup>
														</f:facet>
													</ui:table>
												</td>
											</tr>
											<tr>
												<td>
													<br />
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<ui:label binding="#{framework$ABMReporte$ABMReporte.lblParametros}" id="label1" styleClass="label2" text="Parametros Reporte" />
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<ui:table augmentTitle="false" binding="#{framework$ABMReporte$ABMReporte.tableParametros}" id="tableParametros" width="479">
														<script>
															<![CDATA[
															/* ----- Functions for Table Preferences Panel ----- */
															/*
															 * Toggle the table preferences panel open or closed
															 */
															function togglePreferencesPanel() {
																var table = document.getElementById("form1:tableParametros");
																table.toggleTblePreferencesPanel();
															}
															/* ----- Functions for Filter Panel ----- */
															/*
															 * Return true if the filter menu has actually changed,
															 * so the corresponding event should be allowed to continue.
															 */
															function filterMenuChanged() {
																var table = document.getElementById("form1:tableParametros");
																return table.filterMenuChanged();
															}
															/*
															 * Toggle the custom filter panel (if any) open or closed.
															 */
															function toggleFilterPanel() {
																var table = document.getElementById("form1:tableParametros");
																return table.toggleTableFilterPanel();
															}
															/* ----- Functions for Table Actions ----- */
															/*
															 * Initialize all rows of the table when the state
															 * of selected rows changes.
															 */
															function initAllRows() {
																var table = document.getElementById("form1:tableParametros");
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
																var table = document.getElementById("form1:tableParametros");
																table.selectGroupRows(rowGroupId, selected);
															}
															/*
															 * Disable all table actions if no rows have been selected.
															 */
															function disableActions() {
																// Determine whether any rows are currently selected
																var table = document.getElementById("form1:tableParametros");
																var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
																// Set disabled state for top actions
																document.getElementById("form1:tableParametros:tableActionsTop:deleteTop")
																		.setDisabled(disabled);
																// Set disabled state for bottom actions
																document.getElementById("form1:tableParametros:tableActionsBottom:deleteBottom")
																		.setDisabled(disabled);
															}
															]]>
														</script>
														<ui:tableRowGroup binding="#{framework$ABMReporte$ABMReporte.tableRowGroupParametros}" id="tableRowGroupParametros"
															sourceData="#{framework$ABMReporte$ABMReporte.ldpParametros}" sourceVar="currentRowParametros">
															<ui:tableColumn align="center" binding="#{framework$ABMReporte$ABMReporte.tableColumnRB}" id="tableColumnRB" valign="middle"
																width="10">
																<ui:radioButton binding="#{framework$ABMReporte$ABMReporte.radioButtonParametro}" id="radioButtonParametro" label=""
																	name="buttonGroup" selected="#{framework$ABMReporte$ABMReporte.RBSelectedParametro}"
																	selectedValue="#{framework$ABMReporte$ABMReporte.currentRowParametros}" />
															</ui:tableColumn>
															<ui:tableColumn width="6" align="center" binding="#{framework$ABMReporte$ABMReporte.tableColumnOrden}" headerText="Orden"
																id="tableColumnOrden">
																<ui:textField binding="#{framework$ABMReporte$ABMReporte.tfOrden}" id="tfOrden" columns="2"
																	text="#{currentRowParametros.value['orden']}" onKeyPress="return ValidarNum(event,this)" />
															</ui:tableColumn>
															<ui:tableColumn width="30" align="center" binding="#{framework$ABMReporte$ABMReporte.tableColumnNombre}" headerText="Nombre"
																id="tableColumnNombre">
																<ui:textField binding="#{framework$ABMReporte$ABMReporte.tfNombreParametro}" id="tfNombreParametro"
																	text="#{currentRowParametros.value['nombre']}" />
															</ui:tableColumn>
															<ui:tableColumn width="30" align="center" binding="#{framework$ABMReporte$ABMReporte.tableColumnNombreAtributo}"
																headerText="Nombre Atributo" id="tableColumnNombreAtributo">
																<ui:textField binding="#{framework$ABMReporte$ABMReporte.tfNombreAtributo}" id="tfNombreAtributo"
																	text="#{currentRowParametros.value['nombreAtributo']}" onKeyPress="return validarNombreAtributo(event)" />
															</ui:tableColumn>
															<ui:tableColumn align="center" binding="#{framework$ABMReporte$ABMReporte.tableColumnTipoParametro}" headerText="Tipo"
																id="tableColumnTipoParametro" valign="middle" width="10">
																<ui:dropDown binding="#{framework$ABMReporte$ABMReporte.ddTipoParametro}" id="ddTipoParametro"
																	items="#{framework$ABMReporte$ABMReporte.ddTipoParametroDefaultOptions.options}" styleClass="textField"
																	selected="#{currentRowParametros.value['tipo']}" converter="EnumConverter" immediate="false" />
															</ui:tableColumn>
															<ui:tableColumn align="center" binding="#{framework$ABMReporte$ABMReporte.tableColumnRequerido}" headerText="Requerido"
																id="tableColumnRequerido" valign="middle" width="10">
																<ui:checkbox binding="#{framework$ABMReporte$ABMReporte.cbRequerido}" id="cbRequerido"
																	selected="#{currentRowParametros.value['requerido']}" immediate="false" />
															</ui:tableColumn>
															<ui:tableColumn align="center" binding="#{framework$ABMReporte$ABMReporte.tableColumnNombreRecurso}"
																headerText="Recurso / Opciones" id="tableColumnNombreRecurso" valign="middle" width="10">
																<div style="width: 300px; overflow: auto;">
																	<ui:staticText binding="#{framework$ABMReporte$ABMReporte.stNombreRecurso}" id="stNombreRecurso"
																		text="#{currentRowParametros.value['stringOpcionesORecurso']}" />
																</div>
															</ui:tableColumn>
														</ui:tableRowGroup>
														<f:facet name="actionsTop">
															<ui:panelGroup binding="#{framework$ABMReporte$ABMReporte.groupPanelParametro}" id="groupPanelParametro">
																<ui:button action="#{framework$ABMReporte$ABMReporte.btnAgregarParametro_action}"
																	binding="#{framework$ABMReporte$ABMReporte.btnAgregarParametro}" id="btnAgregarParametro" styleClass="button"
																	text="Agregar Parametro" />
																<a4j:commandButton action="#{framework$ABMReporte$ABMReporte.btnSeleccionarRecurso_action}"
																	binding="#{framework$ABMReporte$ABMReporte.btnSeleccionarRecurso}" id="btnSeleccionarRecurso" styleClass="btnAjax"
																	reRender="tableParametros" value="Seleccionar Recurso" />
																<a4j:commandButton action="#{framework$ABMReporte$ABMReporte.btnModificarOpciones_action}" id="btnModificarOpciones"
																	styleClass="btnAjax" value="Modificar Opciones" oncomplete="abrirModalOpciones();" reRender="tableOpciones" />
																<ui:staticText binding="#{framework$ABMReporte$ABMReporte.staticText4}" escape="false" id="staticText4"
																	text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
																<a4j:commandButton action="#{framework$ABMReporte$ABMReporte.btnQuitarParametro_action}"
																	binding="#{framework$ABMReporte$ABMReporte.btnQuitarParametro}" id="btnQuitarParametro" value="Quitar" styleClass="btnAjax"
																	reRender="tableParametros" onmousedown="reemplazarClickConConfirmacionEnTabla('tableParametros', true, this, '');" />
																<a4j:commandButton action="#{framework$ABMReporte$ABMReporte.btnQuitarTodosParametro_action}"
																	binding="#{framework$ABMReporte$ABMReporte.btnQuitarTodosParametro}" id="btnQuitarTodosParametro" value="Quitar todos"
																	styleClass="btnAjax" reRender="tableParametros" />
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
													<ui:label binding="#{framework$ABMReporte$ABMReporte.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
												</td>
												<td>
													<ui:textArea binding="#{framework$ABMReporte$ABMReporte.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
												</td>
											</tr>
											<tr>
												<td>
													<br />
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<ui:table binding="#{framework$ABMReporte$ABMReporte.tablaLogs}" id="tbLogsAuditoria" />
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<a4j:outputPanel ajaxRendered="true">
														<ui:messageGroup binding="#{framework$ABMReporte$ABMReporte.messageGroup}" id="messageGroup1" styleClass="grupoMsg" />
													</a4j:outputPanel>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{framework$ABMReporte$ABMReporte.btnGuardar_action}" binding="#{framework$ABMReporte$ABMReporte.btnGuardar}"
											id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{framework$ABMReporte$ABMReporte.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{framework$ABMReporte$ABMReporte.btnCancelar_action}" binding="#{framework$ABMReporte$ABMReporte.btnCancelar}"
											id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{framework$ABMReporte$ABMReporte.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMReporte$ABMReporte.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMReporte$ABMReporte.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMReporte$ABMReporte.idSubSesion}" />
					<ui:script binding="#{framework$ABMReporte$ABMReporte.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
					<div id="opciones-modal-form" title="Opciones">
						<table>
							<tr>
								<td colspan="2">
									<ui:table binding="#{framework$ABMReporte$ABMReporte.tablaOpcionesParametro}" id="tableOpciones" width="128">
										<ui:tableRowGroup binding="#{framework$ABMReporte$ABMReporte.trgOpcionesParametro}" id="trgOpciones" rows="20"
											emptyDataMsg="Ningún registro encontrado." sourceData="#{framework$ABMReporte$ABMReporte.ldpOpciones}"
											sourceVar="currentRowOpcion">
											<ui:tableColumn align="center" id="tableColumn8" width="10">
												<ui:radioButton binding="#{framework$ABMReporte$ABMReporte.rbOpcion}" id="radioButton2" label="" name="grupoOpciones"
													selected="#{framework$ABMReporte$ABMReporte.RBSelectedOpcion}"
													selectedValue="#{framework$ABMReporte$ABMReporte.currentRowOpcion}" />
											</ui:tableColumn>
											<ui:tableColumn headerText="Nombre" id="tableColumn9" noWrap="true" sort="nombre">
												<ui:textField id="staticText14" text="#{currentRowOpcion.value['nombre']}" />
											</ui:tableColumn>
										</ui:tableRowGroup>
										<f:facet name="actionsTop">
											<ui:panelGroup id="groupPanelOpciones">
												<a4j:commandButton action="#{framework$ABMReporte$ABMReporte.btnAgregarOpcion_action}" id="btnAgregarOpcion"
													styleClass="btnAjax" reRender="tableOpciones" value="Agregar Opción" />
												<a4j:commandButton action="#{framework$ABMReporte$ABMReporte.btnQuitarOpcion_action}" id="btnQuitarOpcion" styleClass="btnAjax"
													value="Quitar Opción" reRender="tableOpciones" />
											</ui:panelGroup>
										</f:facet>
									</ui:table>
								</td>
							</tr>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tr>
								<td align="center" colspan="2">
									<a4j:commandButton id="btnSeleccionarObligacion" binding="#{framework$ABMReporte$ABMReporte.btnModificarOpciones}" value="Guardar"
										action="#{framework$ABMReporte$ABMReporte.btnGuardarOpciones_action}" oncomplete="cerrarModalOpciones();"
										reRender="tableParametros" styleClass="btnAjax" />
									<a4j:commandButton id="btncerrarModalObligaciones" value="Cerrar" oncomplete="cerrarModalOpciones();" styleClass="btnAjax" />
								</td>
							</tr>
						</table>
					</div>
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>