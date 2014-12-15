<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{saic$ABMReliquidacion$ABMActualizarDeuda.page1}" id="page1">
			<ui:html binding="#{saic$ABMReliquidacion$ABMActualizarDeuda.html1}" id="html1">
			<ui:head binding="#{saic$ABMReliquidacion$ABMActualizarDeuda.head1}" id="head1" title="Actualizar Deuda Liquidaciones">
				<ui:link binding="#{saic$ABMReliquidacion$ABMActualizarDeuda.link1}" id="link1" url="/resources/stylesheet.css" />
				<script type="text/javascript">
					<![CDATA[
					var nombreBean = "saic$ABMReliquidacion$ABMActualizarDeuda";

					function cargarComportamientoJQuery() {
						calendarioEnTextField("#form1:tfFechaVencimiento");
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{saic$ABMReliquidacion$ABMActualizarDeuda.body1}" id="body1"
				onLoad="parent.footer.location.reload();Init();activarDesactivarTfValor();"
				style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$ABMReliquidacion$ABMActualizarDeuda.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{saic$ABMReliquidacion$ABMActualizarDeuda.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{saic$ABMReliquidacion$ABMActualizarDeuda.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="2"></td>
								</tr>
								<tr>
									<td>
										<ui:label binding="#{saic$ABMReliquidacion$ABMActualizarDeuda.lbFechaVencimiento}" for="tfFechaVencimiento"
											id="lbFechaVencimiento" styleClass="label" text="Fecha del Nuevo Vencimiento   " />
										<ui:textField binding="#{saic$ABMReliquidacion$ABMActualizarDeuda.tfFechaVencimiento}" columns="10" id="tfFechaVencimiento"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td>
										<ui:label binding="#{saic$ABMReliquidacion$ABMActualizarDeuda.lbAplicarIntereses}" for="cbAplicarIntereses"
											id="lbAplicarIntereses" styleClass="label" text="Aplicar intereses" />
										<ui:checkbox binding="#{saic$ABMReliquidacion$ABMActualizarDeuda.cbAplicarIntereses}" id="cbAplicarIntereses" selected="true" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:label id="lblLiquidacion" styleClass="label2" text="Liquidaciones" />
									</td>
								</tr>
								<tr>
									<td colspan="2" nowrap="nowrap">
										<ui:table augmentTitle="false" binding="#{saic$ABMReliquidacion$ABMActualizarDeuda.tbLiquidaciones}" id="tbLiquidaciones">
										<script>
												<![CDATA[
												/* ----- Functions for Table Preferences Panel ----- */
												/*
												 * Toggle the table preferences panel open or closed
												 */
												function togglePreferencesPanel() {
													var table = document
															.getElementById("form1:tbLiquidaciones");
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
															.getElementById("form1:tbLiquidaciones");
													return table
															.filterMenuChanged();
												}
												/*
												 * Toggle the custom filter panel (if any) open or closed.
												 */
												function toggleFilterPanel() {
													var table = document
															.getElementById("form1:tbLiquidaciones");
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
															.getElementById("form1:tbLiquidaciones");
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
															.getElementById("form1:tbLiquidaciones");
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
															.getElementById("form1:tbLiquidaciones");
													var disabled = (table
															.getAllSelectedRowsCount() > 0) ? false
															: true;
													// Set disabled state for top actions
													document
															.getElementById(
																	"form1:tbLiquidaciones:tableActionsTop:deleteTop")
															.setDisabled(
																	disabled);
													// Set disabled state for bottom actions
													document
															.getElementById(
																	"form1:tbLiquidaciones:tableActionsBottom:deleteBottom")
															.setDisabled(
																	disabled);
												}
												]]>
											</script>
											<ui:tableRowGroup binding="#{saic$ABMReliquidacion$ABMActualizarDeuda.tbrgLiquidaciones}" id="tbrgLiquidaciones"
												sourceData="#{saic$ABMReliquidacion$ABMActualizarDeuda.ldpLiquidaciones}" sourceVar="currentRow1">
												<ui:tableColumn align="center" onClick="setTimeout('initAllRows()', 0)"
													binding="#{saic$ABMReliquidacion$ABMActualizarDeuda.tcCheckBox}" id="tcCheckBox"
													valign="middle" width="10">
													<ui:checkbox binding="#{saic$ABMReliquidacion$ABMActualizarDeuda.checkBoxSeleccion}"
														id="checkbox1"
														selected="#{saic$ABMReliquidacion$ABMActualizarDeuda.selected}"
														selectedValue="#{saic$ABMReliquidacion$ABMActualizarDeuda.currentRow1}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMReliquidacion$ABMActualizarDeuda.tcPeriodo}" headerText="Período" id="tcPeriodo">
													<ui:staticText binding="#{saic$ABMReliquidacion$ABMActualizarDeuda.stPeriodo}" id="stPeriodo"
														text="#{currentRow1.value['periodoAnio']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMReliquidacion$ABMActualizarDeuda.tcObligacion}" headerText="Obligación" id="tcObligacion">
													<ui:textArea binding="#{saic$ABMReliquidacion$ABMActualizarDeuda.taObligacion}" id="taObligacion"
														text="#{currentRow1.value['stringObligacion']}" disabled="true" styleClass="textFieldDisabled" rows="1" columns="70"/>
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMReliquidacion$ABMActualizarDeuda.tcVencimiento}" headerText="Vencimiento" id="tcVencimiento">
													<ui:staticText binding="#{saic$ABMReliquidacion$ABMActualizarDeuda.stVencimiento}" id="stVencimiento"
														text="#{currentRow1.value['fechaVencimiento']}" converter="#{saic$ABMReliquidacion$ABMActualizarDeuda.dtcFecha}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMReliquidacion$ABMActualizarDeuda.tcMonto}" headerText="Monto" id="tcMonto">
													<ui:staticText binding="#{saic$ABMReliquidacion$ABMActualizarDeuda.stMonto}" id="stMonto" text="#{currentRow1.value['monto']}"
														converter="#{saic$ABMReliquidacion$ABMActualizarDeuda.ncMonto}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMReliquidacion$ABMActualizarDeuda.tcEstado}" headerText="Estado" id="tcEstado">
													<ui:staticText binding="#{saic$ABMReliquidacion$ABMActualizarDeuda.stEstado}" id="stEstado"
														text="#{currentRow1.value['estado']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:messageGroup binding="#{saic$ABMReliquidacion$ABMActualizarDeuda.messageGroup1}" id="messageGroup1" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="2" nowrap="nowrap" style="height: 33px">
										<ui:button action="#{saic$ABMReliquidacion$ABMActualizarDeuda.btnGuardar_action}"
											binding="#{saic$ABMReliquidacion$ABMActualizarDeuda.btnGuardar}" id="btnActualizarDeuda" styleClass="button"/>
										<ui:button action="#{saic$ABMReliquidacion$ABMActualizarDeuda.btnActualizarEImprimirDeuda_action}" text="Actualizar e imprimir"
											onClick="newWindow = window.open('/Vipians/faces/ImpresionServlet', 'Reporte')"
											binding="#{saic$ABMReliquidacion$ABMActualizarDeuda.btnActualizarEImprimirDeuda}" id="btnActualizarEImprimir" styleClass="button"/>
										<ui:staticText binding="#{saic$ABMReliquidacion$ABMActualizarDeuda.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{saic$ABMReliquidacion$ABMActualizarDeuda.btnCancelar_action}"
											binding="#{saic$ABMReliquidacion$ABMActualizarDeuda.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{saic$ABMReliquidacion$ABMActualizarDeuda.hidIdPagina}" id="hidIdPagina"
						text="#{saic$ABMReliquidacion$ABMActualizarDeuda.idPagina}" />
					<ui:hiddenField binding="#{saic$ABMReliquidacion$ABMActualizarDeuda.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$ABMReliquidacion$ABMActualizarDeuda.idSubSesion}" />
					<ui:script binding="#{saic$ABMReliquidacion$ABMActualizarDeuda.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>