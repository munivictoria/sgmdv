<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{accionsocial$ABMBeneficio$AdminBeneficio.page1}" id="page1">
			<ui:html binding="#{accionsocial$ABMBeneficio$AdminBeneficio.html1}" id="html1">
			<ui:head binding="#{accionsocial$ABMBeneficio$AdminBeneficio.head1}" id="head1" title="Administración de Beneficio">
				<ui:link binding="#{accionsocial$ABMBeneficio$AdminBeneficio.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{accionsocial$ABMBeneficio$AdminBeneficio.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 236, 242); -rave-layout: grid"
				onKeyPress="eventoByEnter(event,'btnBuscar')" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{accionsocial$ABMBeneficio$AdminBeneficio.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{accionsocial$ABMBeneficio$AdminBeneficio.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{accionsocial$ABMBeneficio$AdminBeneficio.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td></td>
									</tr>
									<tr>
										<td align="right" nowrap="true">
											<ui:label binding="#{accionsocial$ABMBeneficio$AdminBeneficio.label2}" for="tfNombre" id="label2" styleClass="label"
												text="Nombre" />
										</td>
										<td>
											<ui:textField binding="#{accionsocial$ABMBeneficio$AdminBeneficio.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
										</td>
									</tr>
									<tr>
										<td align="right" nowrap="true">
											<ui:label binding="#{accionsocial$ABMBeneficio$AdminBeneficio.label1}" for="ddTipoBeneficio" id="label1" styleClass="label"
												text="Tipo de Beneficio" />
										</td>
										<td>
											<ui:dropDown binding="#{accionsocial$ABMBeneficio$AdminBeneficio.ddTipoBeneficio}" id="ddTipoBeneficio"
												items="#{accionsocial$ABMBeneficio$AdminBeneficio.ddTipoBeneficioDefaultOptions.options}" styleClass="textField" />
										</td>
									</tr>
									<tr>
										<td></td>
									</tr>
								</tbody>
								<tfoot>
									<tr>
										<td align="right" colspan="2">
											<ui:button action="#{accionsocial$ABMBeneficio$AdminBeneficio.btnBuscar_action}"
												binding="#{accionsocial$ABMBeneficio$AdminBeneficio.btnBuscar}" id="btnBuscar" styleClass="button" text="Buscar" />
											<ui:button action="#{accionsocial$ABMBeneficio$AdminBeneficio.btnReiniciar_action}"
												binding="#{accionsocial$ABMBeneficio$AdminBeneficio.btnReiniciar}" id="btnReiniciar" styleClass="button" text="Reiniciar" />
											<ui:staticText binding="#{accionsocial$ABMBeneficio$AdminBeneficio.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{accionsocial$ABMBeneficio$AdminBeneficio.btnCancelar_action}"
												binding="#{accionsocial$ABMBeneficio$AdminBeneficio.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<!--</div>
                                -->
							<div>
								<ui:messageGroup binding="#{accionsocial$ABMBeneficio$AdminBeneficio.messageGroup}" id="messageGroup" showDetail="true"
									showSummary="false" styleClass="grupoMsgAdmin" />
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{accionsocial$ABMBeneficio$AdminBeneficio.table1}" id="table1">
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
											<ui:tableRowGroup binding="#{accionsocial$ABMBeneficio$AdminBeneficio.tableRowGroup1}" emptyDataMsg="Ningún registro encontrado."
												id="tableRowGroup1" sourceData="#{accionsocial$ABMBeneficio$AdminBeneficio.ldpBeneficio}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{accionsocial$ABMBeneficio$AdminBeneficio.tableColumn1}" id="tableColumn1"
													valign="middle" width="10">
													<ui:radioButton binding="#{accionsocial$ABMBeneficio$AdminBeneficio.radioButton1}" id="radioButton1" label=""
														name="buttonGroup" selected="#{accionsocial$ABMBeneficio$AdminBeneficio.RBSelected}"
														selectedValue="#{accionsocial$ABMBeneficio$AdminBeneficio.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{accionsocial$ABMBeneficio$AdminBeneficio.tableColumn2}" headerText="Nombre" id="tableColumn2"
													sort="nombre">
													<ui:staticText binding="#{accionsocial$ABMBeneficio$AdminBeneficio.staticText1}" id="staticText1"
														text="#{currentRow.value['nombre']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{accionsocial$ABMBeneficio$AdminBeneficio.tableColumn3}" headerText="Tipo de Beneficio"
													id="tableColumn3" sort="tipoBeneficio">
													<ui:staticText binding="#{accionsocial$ABMBeneficio$AdminBeneficio.staticText3}" id="staticText3"
														text="#{currentRow.value['tipoBeneficio']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{accionsocial$ABMBeneficio$AdminBeneficio.groupPanel1}" id="groupPanel1">
													<ui:button action="#{accionsocial$ABMBeneficio$AdminBeneficio.btnSeleccionar_action}"
														binding="#{accionsocial$ABMBeneficio$AdminBeneficio.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
														text="Seleccionar" />
													<ui:staticText binding="#{accionsocial$ABMBeneficio$AdminBeneficio.staticText6}" escape="false" id="staticText6"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{accionsocial$ABMBeneficio$AdminBeneficio.btnAgregar_action}"
														binding="#{accionsocial$ABMBeneficio$AdminBeneficio.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{accionsocial$ABMBeneficio$AdminBeneficio.btnModificar_action}"
														binding="#{accionsocial$ABMBeneficio$AdminBeneficio.btnModificar}" id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{accionsocial$ABMBeneficio$AdminBeneficio.btnEliminar_action}"
														binding="#{accionsocial$ABMBeneficio$AdminBeneficio.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{accionsocial$ABMBeneficio$AdminBeneficio.staticText4}" escape="false" id="staticText4"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{accionsocial$ABMBeneficio$AdminBeneficio.btnConsultar_action}"
														binding="#{accionsocial$ABMBeneficio$AdminBeneficio.btnConsultar}" id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:staticText binding="#{accionsocial$ABMBeneficio$AdminBeneficio.staticText9}" escape="false" id="staticText9"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{accionsocial$ABMBeneficio$AdminBeneficio.btnExportar_action}"
														binding="#{accionsocial$ABMBeneficio$AdminBeneficio.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
														onClick="newWindow = window.open('/Muni/faces/ImpresionServlet', '_parent')" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								  <tr>
									<td align="left" colspan="2">
										<ui:label binding="#{accionsocial$ABMBeneficio$AdminBeneficio.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{accionsocial$ABMBeneficio$AdminBeneficio.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr> 
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{accionsocial$ABMBeneficio$AdminBeneficio.hidIdPagina}" id="hidIdPagina"
						text="#{accionsocial$ABMBeneficio$AdminBeneficio.idPagina}" />
					<ui:hiddenField binding="#{accionsocial$ABMBeneficio$AdminBeneficio.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{accionsocial$ABMBeneficio$AdminBeneficio.idSubSesion}" />
					<ui:script binding="#{accionsocial$ABMBeneficio$AdminBeneficio.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{accionsocial$ABMBeneficio$AdminBeneficio.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>