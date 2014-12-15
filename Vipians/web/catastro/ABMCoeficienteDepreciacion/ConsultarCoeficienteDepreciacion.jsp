<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{catastro$ABMCoeficienteDepreciacion$ConsultarCoeficienteDepreciacion.page1}" id="page1">
			<ui:html binding="#{catastro$ABMCoeficienteDepreciacion$ConsultarCoeficienteDepreciacion.html1}" id="html1">
			<ui:head binding="#{catastro$ABMCoeficienteDepreciacion$ConsultarCoeficienteDepreciacion.head1}" id="head1"
				title="Consultar Coeficientes de Depreciación">
				<ui:link binding="#{catastro$ABMCoeficienteDepreciacion$ConsultarCoeficienteDepreciacion.link1}" id="link1"
					url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{catastro$ABMCoeficienteDepreciacion$ConsultarCoeficienteDepreciacion.body1}" focus="form1:tfAniosExistencia"
				id="body1" onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 242, 242); -rave-layout: grid"
				onKeyUp="eventoByEscape(event,'btnVolver')">
				<ui:form binding="#{catastro$ABMCoeficienteDepreciacion$ConsultarCoeficienteDepreciacion.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="gris">
							<caption>
								<ui:staticText binding="#{catastro$ABMCoeficienteDepreciacion$ConsultarCoeficienteDepreciacion.stTitulo}" id="stTitulo"
									styleClass="tituloABM" text="#{catastro$ABMCoeficienteDepreciacion$ConsultarCoeficienteDepreciacion.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td>
										<ui:label binding="#{catastro$ABMCoeficienteDepreciacion$ConsultarCoeficienteDepreciacion.label2}" id="label2" styleClass="label2"
											text="Coeficientes de Depreciación por Categoría" />
									</td>
								</tr>
								<tr>
									<td style="height: 86px">
										<ui:table augmentTitle="false" binding="#{catastro$ABMCoeficienteDepreciacion$ConsultarCoeficienteDepreciacion.table1}"
											id="table1" width="600">
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
											<ui:tableRowGroup binding="#{catastro$ABMCoeficienteDepreciacion$ConsultarCoeficienteDepreciacion.tableRowGroup1}"
												id="tableRowGroup1"
												sourceData="#{catastro$ABMCoeficienteDepreciacion$ConsultarCoeficienteDepreciacion.ldpCoeficientesDepreciacionPorCategoria}"
												sourceVar="currentRow">
												<ui:tableColumn align="right" binding="#{catastro$ABMCoeficienteDepreciacion$ConsultarCoeficienteDepreciacion.tableColumn1}"
													headerText="Años de Existencia" id="tableColumn1" sort="aniosExistencia" width="10">
													<ui:staticText binding="#{catastro$ABMCoeficienteDepreciacion$ConsultarCoeficienteDepreciacion.staticText1}" id="staticText1"
														text="#{currentRow.value['aniosExistencia']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{catastro$ABMCoeficienteDepreciacion$ConsultarCoeficienteDepreciacion.tableColumn2}"
													headerText="Valor Estado Bueno" id="tableColumn2" sort="estadoBueno">
													<ui:label binding="#{catastro$ABMCoeficienteDepreciacion$ConsultarCoeficienteDepreciacion.label4}" id="label4"
														text="#{currentRow.value['estadoBueno']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{catastro$ABMCoeficienteDepreciacion$ConsultarCoeficienteDepreciacion.tableColumn3}"
													headerText="Valor Estado Regular" id="tableColumn3" sort="estadoRegular">
													<ui:label binding="#{catastro$ABMCoeficienteDepreciacion$ConsultarCoeficienteDepreciacion.label1}" id="label1"
														text="#{currentRow.value['estadoRegular']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{catastro$ABMCoeficienteDepreciacion$ConsultarCoeficienteDepreciacion.tableColumn4}"
													headerText="Valor Estado Malo" id="tableColumn4" sort="estadoMalo">
													<ui:label binding="#{catastro$ABMCoeficienteDepreciacion$ConsultarCoeficienteDepreciacion.label5}" id="label5"
														text="#{currentRow.value['estadoMalo']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{catastro$ABMCoeficienteDepreciacion$ConsultarCoeficienteDepreciacion.groupPanel1}" id="groupPanel1">
													<ui:label binding="#{catastro$ABMCoeficienteDepreciacion$ConsultarCoeficienteDepreciacion.label3}" for="tfCategoria"
														id="label3" styleClass="label" text="Categoría" />
													<ui:staticText binding="#{catastro$ABMCoeficienteDepreciacion$ConsultarCoeficienteDepreciacion.staticText4}" escape="false"
														id="staticText4" text="&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;" />
													<ui:textField binding="#{catastro$ABMCoeficienteDepreciacion$ConsultarCoeficienteDepreciacion.tfCategoria}" columns="40"
														disabled="true" id="tfCategoria" styleClass="textFieldDisabled" />
													<a4j:commandButton id="btnLimpiarCategoria" reRender="form1:tfCategoria"
														binding="#{catastro$ABMCoeficienteDepreciacion$ConsultarCoeficienteDepreciacion.btnLimpiarCategoria}"
														action="#{catastro$ABMCoeficienteDepreciacion$ConsultarCoeficienteDepreciacion.btnLimpiarCategoria_action}"
														styleClass="buttonLimpiarAjax" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td>
										<ui:messageGroup binding="#{catastro$ABMCoeficienteDepreciacion$ConsultarCoeficienteDepreciacion.messageGroup1}"
											id="messageGroup1" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:button action="#{catastro$ABMCoeficienteDepreciacion$ConsultarCoeficienteDepreciacion.btnVolver_action}"
											binding="#{catastro$ABMCoeficienteDepreciacion$ConsultarCoeficienteDepreciacion.btnVolver}" id="btnVolver" styleClass="button"
											text="Volver" />
									</td>
								</tr>
							</tfoot>
						</table>
						<br /> <br /> <br />
					</div>
					<ui:hiddenField binding="#{catastro$ABMCoeficienteDepreciacion$ConsultarCoeficienteDepreciacion.hidIdPagina}" id="hidIdPagina"
						text="#{catastro$ABMCoeficienteDepreciacion$ConsultarCoeficienteDepreciacion.idPagina}" />
					<ui:hiddenField binding="#{catastro$ABMCoeficienteDepreciacion$ConsultarCoeficienteDepreciacion.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{catastro$ABMCoeficienteDepreciacion$ConsultarCoeficienteDepreciacion.idSubSesion}" />
					<ui:script binding="#{catastro$ABMCoeficienteDepreciacion$ConsultarCoeficienteDepreciacion.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
