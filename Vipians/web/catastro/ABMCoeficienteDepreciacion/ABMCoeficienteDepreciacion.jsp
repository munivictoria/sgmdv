<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.page1}" id="page1">
			<ui:html binding="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.html1}" id="html1">
			<ui:head binding="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.head1}" id="head1">
				<ui:link binding="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.link1}" id="link1" url="/resources/stylesheet.css" />
				<script type="text/javascript">
					function ValidarNumFloat(e, obj) {
						tecla = (document.all) ? e.keyCode : e.which;
						if (tecla == 8)
							return true;

						Punto = obj.value.split('.');
						if (Punto.length >= 2) {
							patron = /[0-9]/;
						} else
							patron = /[0-9.]/;
						te = String.fromCharCode(tecla);

						return patron.test(te);
					}
				</script>
			</ui:head>
			<ui:body binding="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.body1}" focus="form1:tfAniosExistencia" id="body1"
				onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 242, 236); -rave-layout: grid"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.stTitulo}" id="stTitulo"
									styleClass="tituloABM" text="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td>
										<ui:label binding="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.label2}" id="label2" styleClass="label2"
											text="Coeficientes de Depreciación por Categoría" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.table1}" id="table1">
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
											<ui:tableRowGroup binding="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.tableRowGroup1}" id="tableRowGroup1"
												sourceData="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.ldpCoeficientesDepreciacionPorCategoria}"
												sourceVar="currentRow">
												<ui:tableColumn align="right" binding="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.tableColumn1}"
													headerText="Años de Existencia" id="tableColumn1" sort="aniosExistencia" width="10">
													<ui:staticText binding="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.staticText1}" id="staticText1"
														text="#{currentRow.value['aniosExistencia']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.tableColumn2}"
													headerText="Valor Estado Bueno" id="tableColumn2" sort="estadoBueno">
													<ui:textField binding="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.textField1}" columns="10"
														id="textField1" styleClass="textField" onKeyPress="return ValidarNumFloat(event,this)"
														text="#{currentRow.value['estadoBueno']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.tableColumn3}"
													headerText="Valor Estado Regular" id="tableColumn3" sort="estadoRegular">
													<ui:textField binding="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.textField2}" columns="10"
														id="textField2" styleClass="textField" onKeyPress="return ValidarNumFloat(event,this)"
														text="#{currentRow.value['estadoRegular']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.tableColumn4}"
													headerText="Valor Estado Malo" id="tableColumn4" sort="estadoMalo">
													<ui:textField binding="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.textField3}" columns="10"
														id="textField3" styleClass="textField" onKeyPress="return ValidarNumFloat(event,this)"
														text="#{currentRow.value['estadoMalo']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.groupPanel1}" id="groupPanel1">
													<ui:label binding="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.label3}" for="tfCategoria" id="label3"
														styleClass="label" text="Categoría" />
													<ui:staticText binding="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.staticText4}" escape="false"
														id="staticText4" text="&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;" />
													<ui:textField binding="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.tfCategoria}" columns="40"
														disabled="true" id="tfCategoria" styleClass="textField" />
													<ui:button action="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.btnSeleccionarCategoria_action}"
														binding="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.btnSeleccionarCategoria}" escape="false"
														id="btnSeleccionarCategoria" mini="true" rendered="false" styleClass="buttonSeleccionar" text="&amp;nbsp;"
														toolTip="Seleccionar" />
													<a4j:commandButton id="btnLimpiarCategoria" reRender="tfCategoria" title="Limpiar"
														binding="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.btnLimpiarCategoria}"
														action="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.btnLimpiarCategoria_action}"
														styleClass="buttonLimpiarAjax" />
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
										<ui:label binding="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
									</td>
									<td>
										<ui:textArea binding="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.tablaLogs}" id="tbLogsAuditoria" />
									</td>
								</tr>
								<tr>
									<td>
										<ui:messageGroup binding="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.messageGroup1}" id="messageGroup1"
											styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:button action="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.btnGuardar_action}"
											binding="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.btnGuardar}" id="btnGuardar" styleClass="button"
											text="Guardar" />
										<ui:staticText binding="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.stSeparador}" escape="false"
											id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.btnCancelar_action}"
											binding="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
						<br /> <br /> <br />
					</div>
					<ui:hiddenField binding="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.hidIdPagina}" id="hidIdPagina"
						text="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.idPagina}" />
					<ui:hiddenField binding="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.idSubSesion}" />
					<ui:script binding="#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
