<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.page1}" id="page1">
			<ui:html binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.html1}" id="html1">
			<ui:head binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.head1}" id="head1">
				<ui:link binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.body1}" focus="form1:tfAnio" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.stTitulo}" id="stTitulo"
									styleClass="tituloABM" text="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="true">
										<ui:label binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.lblUsuario}" for="tfUsuario" id="lblUsuario"
											styleClass="label" text="Usuario" />
									</td>
									<td nowrap="true">
										<ui:textField binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.tfUsuario}" columns="40" disabled="true"
											id="tfUsuario" styleClass="textField" />
										<ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.btnSeleccionarUsuario_action}"
											binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.btnSeleccionarUsuario}" escape="false"
											id="btnSeleccionarUsuario" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarUsuario" reRender="form1:tfUsuario"
											binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.btnLimpiarUsuario}"
											action="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.btnLimpiarUsuario_action}"
											styleClass="buttonLimpiarAjax" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.lblOperaUrgentes}" for="cbOperaUrgentes" id="lblOperaUrgentes" styleClass="label"
											text="Puede operar urgentes" />
									</td>
									<td>
										<ui:checkbox binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.cbOperaUrgentes}" id="cbOperaUrgentes" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:label binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.lblEstados}" id="lblEstados"
											styleClass="label2" text="Estados Solicitud Suministro Firmables" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.table1}" id="table1"
											width="239">
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
                                                <ui:tableRowGroup binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.tableRowGroup1}"
                                                    id="tableRowGroup1"
                                                    sourceData="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.ldpEstadosSolSum}" sourceVar="currentRow">
                                                    <ui:tableColumn align="center" binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.tableColumn1}"
                                                        id="tableColumn1" valign="middle" width="10">
                                                        <ui:radioButton binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.radioButton1}"
                                                            id="radioButton1" label="" name="buttonGroup"
                                                            selected="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.RBSelected}"
                                                            selectedValue="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.currentRow}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.tcNombre}"
                                                        headerText="Nombre" id="tcNombre" sort="nombre" width="40">
                                                        <ui:staticText binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.stNombre}"
                                                            id="stUsuario" text="#{currentRow.value['nombre']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.tcDescripcion}"
                                                        headerText="Descripcion" id="tcDescripcion" sort="descripcion" width="40">
                                                        <ui:staticText binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.stDescripcion}"
                                                            id="stDescripcion" text="#{currentRow.value['descripcion']}"/>
                                                    </ui:tableColumn>
                                                </ui:tableRowGroup>
                                                <f:facet name="actionsTop">
                                                    <ui:panelGroup binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.groupPanel1}" id="groupPanel1">
                                                    	<ui:dropDown binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.ddEstadoFirmable}" id="ddEstadoFirmable"
															items="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.ddEstadoFirmableOptions.options}" styleClass="textField"/>
														<a4j:commandButton action="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.btnAgregarEstadoFirmableSeleccionado_action}"
															binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.btnAgregarEstadoFirmableSeleccionado}" id="btnAgregarEstadoFirmableSeleccionado"
															styleClass="buttonAgregarAjax" reRender="table1"/>
                                                        <ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.btnAgregar_action}"
                                                            binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.btnAgregar}" id="btnAgregar"
                                                            styleClass="button" text="Agregar"/>
                                                        <ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.btnQuitar_action}"
                                                            binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.btnQuitar}" id="btnQuitar"
                                                            styleClass="button" text="Quitar"/>
                                                        <ui:staticText binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.stSeparador1}" escape="false"
                                                            id="stSeparador1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                                        <ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.btnQuitarTodos_action}"
                                                            binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.btnQuitarTodos}" id="btnQuitarTodos"
                                                            styleClass="button" text="Quitar todos"/>
                                                    </ui:panelGroup>
                                                </f:facet>
                                            </ui:table>
                                        </td>
                                    </tr>
                                    <tr><td><br/></td></tr>
                                    <tr>
									<td colspan="4">
										<ui:label binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.lblEstadoFinalizable}" id="lblEstadoFinalizable"
											styleClass="label2" text="Estados Solicitud Suministro Finalizables" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.tablaEstadoFinalizable}" id="tablaEstadoFinalizable"
											width="239">
											<script>
												<![CDATA[
												/* ----- Functions for Table Preferences Panel ----- */
												/*
												 * Toggle the table preferences panel open or closed
												 */
												function togglePreferencesPanel() {
													var table = document
															.getElementById("form1:tablaEstadoFinalizable");
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
															.getElementById("form1:tablaEstadoFinalizable");
													return table
															.filterMenuChanged();
												}
												/*
												 * Toggle the custom filter panel (if any) open or closed.
												 */
												function toggleFilterPanel() {
													var table = document
															.getElementById("form1:tablaEstadoFinalizable");
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
															.getElementById("form1:tablaEstadoFinalizable");
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
															.getElementById("form1:tablaEstadoFinalizable");
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
															.getElementById("form1:tablaEstadoFinalizable");
													var disabled = (table
															.getAllSelectedRowsCount() > 0) ? false
															: true;
													// Set disabled state for top actions
													document
															.getElementById(
																	"form1:tablaEstadoFinalizable:tableActionsTop:deleteTop")
															.setDisabled(
																	disabled);
													// Set disabled state for bottom actions
													document
															.getElementById(
																	"form1:tablaEstadoFinalizable:tableActionsBottom:deleteBottom")
															.setDisabled(
																	disabled);
												}
												]]>
											</script>
                                                <ui:tableRowGroup binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.trgEstadoFinalizable}"
                                                    id="trgEstadosFinalizables"
                                                    sourceData="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.ldpEstadosSolSumFinalizables}" sourceVar="currentRowEstadoFinalizable">
                                                    <ui:tableColumn align="center" binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.tcRbEstadoFinalizable}"
                                                        id="tcRbEstadoFinalizable" valign="middle" width="10">
                                                        <ui:radioButton binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.rbEstadoFinalizable}"
                                                            id="rbEstadoFinalizable" label="" name="buttonGroupEstadoFinalizable"
                                                            selected="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.RBSelectedEstadoFinalizable}"
                                                            selectedValue="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.currentRowEstadoFinalizable}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.tcNombreEstadoFinalizable}"
                                                        headerText="Nombre" id="tcNombreEstadoFinalizable" sort="nombre" width="40">
                                                        <ui:staticText binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.stNombreEstadoFinalizable}"
                                                            id="stUsuarioEstadoFinalizable" text="#{currentRowEstadoFinalizable.value['nombre']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.tcDescripcionEstadoFinalizable}"
                                                        headerText="Descripcion" id="tcDescripcionEstadoFinalizable" sort="descripcion" width="40">
                                                        <ui:staticText binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.stDescripcionEstadoFinalizable}"
                                                            id="stDescripcionEstadoFinalizable" text="#{currentRowEstadoFinalizable.value['descripcion']}"/>
                                                    </ui:tableColumn>
                                                </ui:tableRowGroup>
                                                <f:facet name="actionsTop">
                                                    <ui:panelGroup binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.gpEstadoFinalizable}" id="gpEstadoFinalizable">
                                                    	<ui:dropDown binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.ddEstadoFinalizable}" id="ddEstadoFinalizable"
															items="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.ddEstadoFinalizableOptions.options}" styleClass="textField"/>
														<a4j:commandButton action="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.btnAgregarEstadoFinalizableSeleccionado_action}"
															binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.btnAgregarEstadoFinalizableSeleccionado}" id="btnAgregarEstadoFinalizableSeleccionado"
															styleClass="buttonAgregarAjax" reRender="tablaEstadoFinalizable"/>
                                                        <ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.btnAgregarEstadoFinalizable_action}"
                                                            binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.btnAgregarEstadoFinalizable}" id="btnAgregarEstadoFinalizable"
                                                            styleClass="button" text="Agregar"/>
                                                        <ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.btnQuitarEstadoFinalizable_action}"
                                                            binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.btnQuitarEstadoFinalizable}" id="btnQuitarEstadoFinalizable"
                                                            styleClass="button" text="Quitar"/>
                                                        <ui:staticText binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.stSeparador2}" escape="false"
                                                            id="stSeparador2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                                        <ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.btnQuitarTodosEstadoFinalizable_action}"
                                                            binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.btnQuitarTodosEstadoFinalizable}" id="btnQuitarTodosEstadoFinalizable"
                                                            styleClass="button" text="Quitar todos"/>
                                                    </ui:panelGroup>
                                                </f:facet>
                                            </ui:table>
                                        </td>
                                    </tr>
                                    <tr><td><br/></td></tr>
                                    <tr>
									<td colspan="4">
										<ui:label binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.lblEstadoFinalizacion}" id="lblEstadoFinalizacion"
											styleClass="label2" text="Estados Solicitud Suministro de Finalización" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.tablaEstadoFinalizacion}" id="tablaEstadoFinalizacion"
											width="239">
											<script>
												<![CDATA[
												/* ----- Functions for Table Preferences Panel ----- */
												/*
												 * Toggle the table preferences panel open or closed
												 */
												function togglePreferencesPanel() {
													var table = document
															.getElementById("form1:tablaEstadoFinalizacion");
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
															.getElementById("form1:tablaEstadoFinalizacion");
													return table
															.filterMenuChanged();
												}
												/*
												 * Toggle the custom filter panel (if any) open or closed.
												 */
												function toggleFilterPanel() {
													var table = document
															.getElementById("form1:tablaEstadoFinalizacion");
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
															.getElementById("form1:tablaEstadoFinalizacion");
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
															.getElementById("form1:tablaEstadoFinalizacion");
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
															.getElementById("form1:tablaEstadoFinalizacion");
													var disabled = (table
															.getAllSelectedRowsCount() > 0) ? false
															: true;
													// Set disabled state for top actions
													document
															.getElementById(
																	"form1:tablaEstadoFinalizacion:tableActionsTop:deleteTop")
															.setDisabled(
																	disabled);
													// Set disabled state for bottom actions
													document
															.getElementById(
																	"form1:tablaEstadoFinalizacion:tableActionsBottom:deleteBottom")
															.setDisabled(
																	disabled);
												}
												]]>
											</script>
                                                <ui:tableRowGroup binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.trgEstadoFinalizacion}"
                                                    id="trgEstadosFinalizables"
                                                    sourceData="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.ldpEstadosSolSumFinalizacion}" sourceVar="currentRowEstadoFinalizacion">
                                                    <ui:tableColumn align="center" binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.tcRbEstadoFinalizacion}"
                                                        id="tcRbEstadoFinalizacion" valign="middle" width="10">
                                                        <ui:radioButton binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.rbEstadoFinalizacion}"
                                                            id="rbEstadoFinalizacion" label="" name="buttonGroupEstadoFinalizacion"
                                                            selected="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.RBSelectedEstadoFinalizacion}"
                                                            selectedValue="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.currentRowEstadoFinalizacion}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.tcNombreEstadoFinalizacion}"
                                                        headerText="Nombre" id="tcNombreEstadoFinalizacion" sort="nombre" width="40">
                                                        <ui:staticText binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.stNombreEstadoFinalizacion}"
                                                            id="stUsuarioEstadoFinalizacion" text="#{currentRowEstadoFinalizacion.value['nombre']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.tcDescripcionEstadoFinalizacion}"
                                                        headerText="Descripcion" id="tcDescripcionEstadoFinalizacion" sort="descripcion" width="40">
                                                        <ui:staticText binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.stDescripcionEstadoFinalizacion}"
                                                            id="stDescripcionEstadoFinalizacion" text="#{currentRowEstadoFinalizacion.value['descripcion']}"/>
                                                    </ui:tableColumn>
                                                </ui:tableRowGroup>
                                                <f:facet name="actionsTop">
                                                    <ui:panelGroup binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.gpEstadoFinalizacion}" id="gpEstadoFinalizacion">
                                                    	<ui:dropDown binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.ddEstadoFinalizacion}" id="ddEstadoFinalizacion"
															items="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.ddEstadoFinalizacionOptions.options}" styleClass="textField"/>
														<a4j:commandButton action="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.btnAgregarEstadoFinalizacionSeleccionado_action}"
															binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.btnAgregarEstadoFinalizacionSeleccionado}" id="btnAgregarEstadoFinalizacionSeleccionado"
															styleClass="buttonAgregarAjax" reRender="tablaEstadoFinalizacion"/>
                                                        <ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.btnAgregarEstadoFinalizacion_action}"
                                                            binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.btnAgregarEstadoFinalizacion}" id="btnAgregarEstadoFinalizacion"
                                                            styleClass="button" text="Agregar"/>
                                                        <ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.btnQuitarEstadoFinalizacion_action}"
                                                            binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.btnQuitarEstadoFinalizacion}" id="btnQuitarEstadoFinalizacion"
                                                            styleClass="button" text="Quitar"/>
                                                        <ui:staticText binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.stSeparador3}" escape="false"
                                                            id="stSeparador2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                                        <ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.btnQuitarTodosEstadoFinalizacion_action}"
                                                            binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.btnQuitarTodosEstadoFinalizacion}" id="btnQuitarTodosEstadoFinalizacion"
                                                            styleClass="button" text="Quitar todos"/>
                                                    </ui:panelGroup>
                                                </f:facet>
                                            </ui:table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:messageGroup binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.messageGroup}" id="messageGroup" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="4" nowrap="nowrap">
                                            <ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.btnGuardar_action}"
                                                binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.btnGuardar}" id="btnGuardar" styleClass="button"/>
                                            <ui:staticText binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.stSeparador}" escape="false"
                                                id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.btnCancelar_action}"
                                                binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.btnCancelar}" id="btnCancelar" styleClass="button"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.hidIdPagina}" id="hidIdPagina" text="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.idPagina}"/>
                        <ui:hiddenField binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.hidIdSubSesion}" id="hidIdSubSesion" text="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.idSubSesion}"/>
                        <ui:script binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
