<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMRol$ABMRol.page1}" id="page1">
			<ui:html binding="#{framework$ABMRol$ABMRol.html1}" id="html1">
			<ui:head binding="#{framework$ABMRol$ABMRol.head1}" id="head1">
				<ui:link binding="#{framework$ABMRol$ABMRol.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{framework$ABMRol$ABMRol.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();setStyleTextoEncabezado();validarCheckBoxFila();"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMRol$ABMRol.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{framework$ABMRol$ABMRol.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{framework$ABMRol$ABMRol.head1.title}" />
							</caption>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tbody>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{framework$ABMRol$ABMRol.lblNombre}" for="tfNombre" id="label4" styleClass="label" text="Nombre" />
									</td>
									<td>
										<ui:textField binding="#{framework$ABMRol$ABMRol.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:panelGroup binding="#{framework$ABMRol$ABMRol.panelBotonesDinamicos}" id="panelBotonesDinamicos">
											<!-- AQUI VA LO QUE SE CREA DINAMICAMENTE -->
											<script>
												<![CDATA[
												function capturarClickBoton(
														boton) {
													textoBoton = boton.value;
													boton.style.border = "none";
													seteaTextoBonton(textoBoton);
												}
												function seteaTextoBonton(
														newVal) {
													var hidenField = document
															.getElementById("form1:hidIdBotonera");
													hidenField.value = newVal;
												}

												function seleccionarFila(
														componente) {
													var fila = componente.parentNode.parentNode.parentNode;
													var cbBuscar = fila.cells[2].childNodes[0].childNodes[0];
													var cbAgregar = fila.cells[3].childNodes[0].childNodes[0];
													var cbModificar = fila.cells[4].childNodes[0].childNodes[0];
													var cbEliminar = fila.cells[5].childNodes[0].childNodes[0];
													var cbAuditar = fila.cells[6].childNodes[0].childNodes[0];

													if (cbBuscar.checked
															&& cbAgregar.checked
															&& cbModificar.checked
															&& cbEliminar.checked) {
														cbBuscar.checked = false;
														cbAgregar.checked = false;
														cbModificar.checked = false;
														cbEliminar.checked = false;
														cbAuditar.checked = false
													} else {
														cbBuscar.checked = true;
														cbAgregar.checked = true;
														cbModificar.checked = true;
														cbEliminar.checked = true;
														componente.checked = true;
														cbAuditar.checked = true;
													}
												}

												function validarChecks(
														componente) {
													var fila = componente.parentNode.parentNode.parentNode;
													var checkT = fila.cells[1].childNodes[0].childNodes[0];
													//Si deschequeo ese check, el de toda la fila se tiene que deschequear.
													if (!componente.checked) {
														checkT.checked = false;
													} else {
														//Si chequea, valido que los demas esten chequeados para chequear el de todos
														var cbBuscar = fila.cells[2].childNodes[0].childNodes[0];
														var cbAgregar = fila.cells[3].childNodes[0].childNodes[0];
														var cbModificar = fila.cells[4].childNodes[0].childNodes[0];
														var cbEliminar = fila.cells[5].childNodes[0].childNodes[0];
														var cbAuditar = fila.cells[6].childNodes[0].childNodes[0];
														if (cbBuscar.checked
																&& cbAgregar.checked
																&& cbModificar.checked
																&& cbEliminar.checked
																&& cbAuditar.checked) {
															checkT.checked = true;
														}
													}
												}

												onload = function() {
													var checkTodos = document
															.getElementById("form1:table1:tableRowGroup1:0:tableColumnTodo:checkboxTodos");
													var fila = checkTodos.parentNode.parentNode.parentNode;
													var tabla = fila.parentNode;
													var rows = tabla.rows;

													for ( var i = 1; i < rows.length; i++) {
														checkTodos = rows[i].cells[1].childNodes[0].childNodes[0];
														var cbBuscar = rows[i].cells[2].childNodes[0].childNodes[0];
														var cbAgregar = rows[i].cells[3].childNodes[0].childNodes[0];
														var cbModificar = rows[i].cells[4].childNodes[0].childNodes[0];
														var cbEliminar = rows[i].cells[5].childNodes[0].childNodes[0];
														var cbAuditar = rows[i].cells[6].childNodes[0].childNodes[0];

														if (cbBuscar.checked
																&& cbAgregar.checked
																&& cbModificar.checked
																&& cbEliminar.checked
																&& cbAuditar.checked) {
															checkTodos.checked = true;
														} else {
															checkTodos.checked = false;
														}
													}
												};
												]]>
											</script>
										</ui:panelGroup>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:label binding="#{framework$ABMRol$ABMRol.label1}" id="label1" styleClass="label2" text="Recursos" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{framework$ABMRol$ABMRol.table1}" id="table1" width="959">
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
											<ui:tableRowGroup binding="#{framework$ABMRol$ABMRol.tableRowGroup1}" id="tableRowGroup1"
												sourceData="#{framework$ABMRol$ABMRol.ldpPermisos}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{framework$ABMRol$ABMRol.tableColumn1}" id="tableColumn1" rendered="false"
													valign="middle" width="10">
													<ui:radioButton binding="#{framework$ABMRol$ABMRol.radioButton1}" id="radioButton1" label="" name="buttonGroup"
														rendered="false" selected="#{framework$ABMRol$ABMRol.RBSelected}" selectedValue="#{framework$ABMRol$ABMRol.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{framework$ABMRol$ABMRol.tableColumn2}" id="tableColumn2" noWrap="true">
													<ui:staticText binding="#{framework$ABMRol$ABMRol.staticText1}" id="staticText1" text="#{currentRow.value['nombreRecurso']}" />
												</ui:tableColumn>
												<ui:tableColumn align="center" binding="#{framework$ABMRol$ABMRol.tableColumnTodo}" headerText="Todos" id="tableColumnTodo"
													valign="middle" width="50">
													<ui:checkbox id="checkboxTodos" onChange="seleccionarFila(this)" binding="#{framework$ABMRol$ABMRol.cbTodos}" />
												</ui:tableColumn>
												<ui:tableColumn align="center" binding="#{framework$ABMRol$ABMRol.tableColumn7}" headerText="Buscar" id="tableColumn7"
													valign="middle" width="50">
													<ui:checkbox id="checkbox4" selected="#{currentRow.value['select']}" onChange="validarChecks(this)"
														binding="#{framework$ABMRol$ABMRol.cbBuscar}" />
												</ui:tableColumn>
												<ui:tableColumn align="center" binding="#{framework$ABMRol$ABMRol.tableColumn3}" headerText="Agregar" id="tableColumn3"
													valign="middle" width="50">
													<ui:checkbox id="checkbox1" selected="#{currentRow.value['insert']}" onChange="validarChecks(this)"
														binding="#{framework$ABMRol$ABMRol.cbAgregar}" />
												</ui:tableColumn>
												<ui:tableColumn align="center" binding="#{framework$ABMRol$ABMRol.tableColumn5}" headerText="Modificar" id="tableColumn5"
													valign="middle" width="50">
													<ui:checkbox id="checkbox2" selected="#{currentRow.value['update']}" onChange="validarChecks(this)"
														binding="#{framework$ABMRol$ABMRol.cbEditar}" />
												</ui:tableColumn>
												<ui:tableColumn align="center" binding="#{framework$ABMRol$ABMRol.tableColumn6}" headerText="Eliminar" id="tableColumn6"
													valign="middle" width="50">
													<ui:checkbox id="checkbox3" selected="#{currentRow.value['delete']}" onChange="validarChecks(this)"
														binding="#{framework$ABMRol$ABMRol.cbEliminar}" />
												</ui:tableColumn>
												<ui:tableColumn align="center" binding="#{framework$ABMRol$ABMRol.tableColumn8}" headerText="Auditar" id="tableColumn8"
													valign="middle" width="50">
													<ui:checkbox id="checkbox5" selected="#{currentRow.value['audith']}" onChange="validarChecks(this)"
														binding="#{framework$ABMRol$ABMRol.cbAuditar}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop" />
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
										<ui:label binding="#{framework$ABMRol$ABMRol.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
									</td>
									<td>
										<ui:textArea binding="#{framework$ABMRol$ABMRol.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{framework$ABMRol$ABMRol.tablaLogs}" id="tbLogsAuditoria" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{framework$ABMRol$ABMRol.messageGroup1}" id="messageGroup1" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<table class="verde">
								<tfoot>
									<tr>
										<td align="right" colspan="4" nowrap="nowrap" class="formularioABM tfoot td">
											<ui:button action="#{framework$ABMRol$ABMRol.btnGuardar_action}" binding="#{framework$ABMRol$ABMRol.btnGuardar}" id="btnGuardar"
												styleClass="button" />
											<ui:staticText binding="#{framework$ABMRol$ABMRol.stSeparador}" escape="false" id="stSeparador"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{framework$ABMRol$ABMRol.btnCancelar_action}" binding="#{framework$ABMRol$ABMRol.btnCancelar}"
												id="btnCancelar" styleClass="button" />
										</td>
									</tr>
								</tfoot>
							</table>
						</table>
						<br /> <br /> <br />
					</div>
					<ui:hiddenField binding="#{framework$ABMRol$ABMRol.hidIdPagina}" id="hidIdPagina" text="#{framework$ABMRol$ABMRol.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMRol$ABMRol.hidIdSubSesion}" id="hidIdSubSesion" text="#{framework$ABMRol$ABMRol.idSubSesion}" />
					<ui:hiddenField binding="#{framework$ABMRol$ABMRol.hidIdBotonera}" id="hidIdBotonera" />
					<ui:script binding="#{framework$ABMRol$ABMRol.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
