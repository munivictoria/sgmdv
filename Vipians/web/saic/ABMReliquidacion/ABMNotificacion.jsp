<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{saic$ABMReliquidacion$ABMNotificacion.page1}" id="page1">
			<ui:html binding="#{saic$ABMReliquidacion$ABMNotificacion.html1}" id="html1">
			<ui:head binding="#{saic$ABMReliquidacion$ABMNotificacion.head1}" id="head1" title="Notificar Deudas">
				<ui:link binding="#{saic$ABMReliquidacion$ABMNotificacion.link1}" id="link1" url="/resources/stylesheet.css" />
				<script type="text/javascript">
					<![CDATA[
					var nombreBean = "saic$ABMReliquidacion$ABMNotificacion";

					function cargarComportamientoJQuery() {
						calendarioEnTextField("#form1:tfFechaNotificacion");
						calendarioEnTextField("#form1:tfFechaApremio");
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{saic$ABMReliquidacion$ABMNotificacion.body1}" id="body1"
				onLoad="parent.footer.location.reload();Init();activarDesactivarTfValor();"
				style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$ABMReliquidacion$ABMNotificacion.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{saic$ABMReliquidacion$ABMNotificacion.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{saic$ABMReliquidacion$ABMNotificacion.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="2"></td>
								</tr>
								<tr>
									<td>
										<ui:label for="tfFechaNotificacion"
											id="lblFechaNotificacion" styleClass="label" text="Fecha Notificacion" />
										<ui:textField binding="#{saic$ABMReliquidacion$ABMNotificacion.tfFechaNotificacion}" 
											columns="10" id="tfFechaNotificacion"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td>
										<ui:label for="tfFechaApremio"
											id="lblFechaApremio" styleClass="label" text="Fecha Apremio" />
										<ui:textField binding="#{saic$ABMReliquidacion$ABMNotificacion.tfFechaApremio}" 
											columns="10" id="tfFechaApremio"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:label id="lblComentario" styleClass="label" text="Comentario"/>
										<ui:textArea binding="#{saic$ABMReliquidacion$ABMNotificacion.taComentario}" 
											id="taComentario" styleClass="textField"/>
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
										<ui:table augmentTitle="false" binding="#{saic$ABMReliquidacion$ABMNotificacion.tbLiquidaciones}" id="tbLiquidaciones">
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
											<ui:tableRowGroup binding="#{saic$ABMReliquidacion$ABMNotificacion.tbrgLiquidaciones}" id="tbrgLiquidaciones"
												sourceData="#{saic$ABMReliquidacion$ABMNotificacion.ldpLiquidaciones}" sourceVar="currentRow1">
												<ui:tableColumn align="center" onClick="setTimeout('initAllRows()', 0)"
													binding="#{saic$ABMReliquidacion$ABMNotificacion.tcCheckBox}" id="tcCheckBox"
													valign="middle" width="10">
													<ui:checkbox binding="#{saic$ABMReliquidacion$ABMNotificacion.checkBoxSeleccion}"
														id="checkbox1"
														selected="#{saic$ABMReliquidacion$ABMNotificacion.selected}"
														selectedValue="#{saic$ABMReliquidacion$ABMNotificacion.currentRow1}" />
												</ui:tableColumn>
												<ui:tableColumn  headerText="Período" id="tcPeriodo">
													<ui:staticText id="stPeriodo"
														text="#{currentRow1.value['periodoAnio']}" />
												</ui:tableColumn>
												<ui:tableColumn headerText="Obligación" id="tcObligacion">
													<ui:textArea id="taObligacion"
														text="#{currentRow1.value['stringObligacion']}" disabled="true" styleClass="textFieldDisabled" rows="1" columns="70"/>
												</ui:tableColumn>
												<ui:tableColumn headerText="Vencimiento" id="tcVencimiento">
													<ui:staticText id="stVencimiento"
														text="#{currentRow1.value['fechaVencimiento']}" 
														converter="#{saic$ABMReliquidacion$ABMNotificacion.dtcFecha}" />
												</ui:tableColumn>
												<ui:tableColumn headerText="Monto" id="tcMonto">
													<ui:staticText id="stMonto" text="#{currentRow1.value['monto']}"
														converter="#{saic$ABMReliquidacion$ABMNotificacion.ncMonto}" />
												</ui:tableColumn>
												<ui:tableColumn  headerText="Estado" id="tcEstado">
													<ui:staticText id="stEstado"
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
										<ui:messageGroup binding="#{saic$ABMReliquidacion$ABMNotificacion.messageGroup}" id="messageGroup1" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="2" nowrap="nowrap" style="height: 33px">
										<ui:button action="#{saic$ABMReliquidacion$ABMNotificacion.btnGuardar_action}"
											binding="#{saic$ABMReliquidacion$ABMNotificacion.btnGuardar}" id="btnNotificar" styleClass="button"/>
										<ui:staticText binding="#{saic$ABMReliquidacion$ABMNotificacion.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{saic$ABMReliquidacion$ABMNotificacion.btnCancelar_action}"
											binding="#{saic$ABMReliquidacion$ABMNotificacion.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{saic$ABMReliquidacion$ABMNotificacion.hidIdPagina}" id="hidIdPagina"
						text="#{saic$ABMReliquidacion$ABMNotificacion.idPagina}" />
					<ui:hiddenField binding="#{saic$ABMReliquidacion$ABMNotificacion.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$ABMReliquidacion$ABMNotificacion.idSubSesion}" />
					<ui:script binding="#{saic$ABMReliquidacion$ABMNotificacion.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>