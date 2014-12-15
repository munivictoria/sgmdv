<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" isELIgnored="false"
		deferredSyntaxAllowedAsLiteral="false" />
	<f:view>
		<ui:page binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.page1}" id="page1">
			<ui:html binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.html1}" id="html1">
			<ui:head binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.head1}" id="head1"
				title="Administración de Exenciones de Registro de Deuda">
				<ui:link binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init(); changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td colspan="2"></td>
									</tr>
									<tr>
										<td align="center">
											<ui:panelGroup binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.lblNombre}" for="tfNombre" id="lblNombre"
																styleClass="label" text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.tfNombre}" columns="30" disabled="false"
																id="tfNombre" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.lblEstado}" for="ddEstado" id="lblEstado"
																styleClass="label" text="Estado" />
														</td>
														<td>
															<ui:dropDown binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.ddEstado}" id="ddEstado"
																items="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.ddEstadoDefaultOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td colspan="2">
															<hr />
														</td>
													</tr>
													<tr>
														<td colspan="2">
															<ui:label binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.label3}" id="label3" styleClass="label2"
																text="Período" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.lblCalendarios}" for="ddCalendarios"
																id="lblCalendarios" styleClass="label" text="Calendarios" />
														</td>
														<td>
															<ui:dropDown binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.ddCalendarios}" id="ddCalendarios"
																styleClass="textField" items="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.ddCalendariosOptions.options}"
																valueChangeListener="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.valueChangeEvent(event)}"
																onChange="this.form.submit()" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.lblPeriodos}" for="ddPeriodos" id="lblPeriodos"
																styleClass="label" text="Periodos" />
														</td>
														<td>
															<ui:dropDown binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.ddPeriodos}" id="ddPeriodos"
																styleClass="textField" items="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.ddPeriodosOptions.options}"
																valueChangeListener="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.valueChangeEventDdPeriodos(event)}"
																onChange="this.form.submit()" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.lblCuotas}" for="ddCuotas" id="lblCuotas"
																styleClass="label" text="Cuotas" />
														</td>
														<td>
															<ui:dropDown binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.ddCuotas}" id="ddCuotas"
																styleClass="textField" items="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.ddCuotasOptions.options}" />
														</td>
													</tr>
													<tr>
														<td colspan="2"></td>
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
								</tbody>
								<tfoot>
									<tr>
										<td align="right" colspan="2">
											<a4j:commandButton binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.btnBuscar}"
												action="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.btnReiniciar_action}"
												binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.btnCancelar_action}"
												binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.messageGroup}" id="messageGroup"
										showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<h:panelGrid binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.grpCargando}" columns="2" id="grpCargando"
								style="display: none; margin-left: 7px; padding-left: 10px" styleClass="msgLiquidacion">
								<ui:image binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.image1}" id="image1"
									url="/resources/imagenes/abm/wait_medium_1.gif" />
								<ui:staticText binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.staticText1}" escape="false" id="staticText1"
									styleClass="label2" text="     Generando archivos de impresión... Aguarde por favor." />
							</h:panelGrid>
							<script>
								<![CDATA[
								document.getElementById("form1:grpCargando").style.display = "none";

								function mostrarProgreso() {
									tabla = document.getElementById("form1:grpCargando");
									tabla.style.display = "block";
								}
								]]>
							</script>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.table1}" id="table1">
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
												}
												]]>
											</script>
											<ui:tableRowGroup binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.tableRowGroup1}"
												emptyDataMsg="Ningún registro encontrado." id="tableRowGroup1" onMouseOver="jsRowMouseOver(this)"
												onMouseOut="jsRowMouseOut(this)" onClick="jsRowMouseClick(this)" onDblClick="funcionSeleccionar()"
												sourceData="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.ldpExencion}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.tableColumn1}"
													id="tableColumn1" valign="middle" width="10">
													<ui:radioButton binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.radioButton1}" id="radioButton1" label=""
														name="buttonGroup" onClick="checkUncheck(this)"
														selected="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.RBSelected}"
														selectedValue="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.tcNombre}" headerText="Nombre" id="tcNombre"
													sort="nombre">
													<ui:staticText binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.stNombre}" id="stNombre"
														text="#{currentRow.value['nombre']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.tcPorcentaje}" headerText="Porcentaje"
													id="tcPorcentaje" sort="porcentaje">
													<ui:staticText binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.stPorcentaje}" id="stPorcentaje"
														text="#{currentRow.value['porcentaje']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.tcPeriodo}" headerText="Período"
													id="tcPeriodo" sort="periodo">
													<ui:staticText binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.stPeriodo}" id="stPeriodo"
														text="#{currentRow.value['periodo']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.tcEstado}" headerText="Estado" id="tcEstado"
													sort="estado">
													<ui:staticText binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.stEstado}" id="stEstado"
														text="#{currentRow.value['estado']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.groupPanel1}" id="groupPanel1"
													separator="&lt;br/&gt;&lt;br/&gt;">
													<ui:panelGroup binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.gpBotones}" id="gpBotones">
														<ui:button action="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.btnSeleccionar_action}"
															binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
															text="Seleccionar" />
														<ui:staticText binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.staticText6}" escape="false"
															id="staticText6" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.btnAgregar_action}"
															binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.btnAgregar}" id="btnAgregar" styleClass="button"
															text="Agregar" />
														<ui:button action="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.btnModificar_action}"
															binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.btnModificar}" id="btnModificar" styleClass="button"
															text="Modificar" />
														<ui:button action="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.btnTerminar_action}"
															binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.btnTerminar}" id="btnTerminar" styleClass="button"
															text="Terminar" />
														<ui:staticText binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.staticText7}" escape="false"
															id="staticText7" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.btnAutorizar_action}"
															binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.btnAutorizar}" id="btnAutorizar" styleClass="button"
															text="Autorizar" />
														<ui:staticText binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.staticText8}" escape="false"
															id="staticText8" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.btnConsultar_action}"
															binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.btnConsultar}" id="btnConsultar" styleClass="button"
															text="Consultar" />
														<ui:staticText binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.staticText10}" escape="false"
															id="staticText10" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.btnExportar_action}"
															binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.btnExportar}" id="btnExportar" styleClass="button"
															text="Exportar" onClick="return exportarReporte()" />
													</ui:panelGroup>
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.lblEncontrados}" id="lblEncontrados"
											styleClass="label2" text="Registros Encontrados: " />
										<ui:staticText binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.hidIdPagina}" id="hidIdPagina"
						text="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.idPagina}" />
					<ui:hiddenField binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.idSubSesion}" />
					<ui:script binding="#{saic$ABMExencionRegistroDeuda$AdminExencionRegistroDeuda.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
