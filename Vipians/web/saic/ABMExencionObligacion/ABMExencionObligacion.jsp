<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.page1}" id="page1">
			<ui:html binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.html1}" id="html1">
			<ui:head binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.head1}" id="head1">
				<ui:link binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{saic$ABMExencionObligacion$ABMExencionObligacion.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.lblNombre}" for="tfNombre" id="lblNombre"
											styleClass="label" text="Nombre" />
									</td>
									<td>
										<ui:textField binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.tfNombre}" columns="30"
											id="tfNombre" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.lblValor}" for="tfValor" id="lblValor"
											styleClass="label" text="Valor" />
									</td>
									<td>
										<ui:textField binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.tfValor}" id="tfValor" columns="10"
											styleClass="textField" />
									</td>
								</tr>
                                <tr>
                                    <td align="right" nowrap="nowrap">
                                        <ui:label binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.lblTipoValor}" id="lblTipoValor"
                                            styleClass="label" text="Tipo de Valor"/>
                                    </td>
                                    <td colspan="2">
                                        <ui:radioButton binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.rbPorcentual}" id="rbPorcentual" name="rbgTipoValor"/>
                                        <sup>
                                            <ui:label binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.lblPorcentual}" for="rbPorcentual"
                                                id="lblPorcentual" styleClass="label" text="Porcentual"/>
                                        </sup>
                                        <br/>
                                        <ui:radioButton binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.rbFijo}" id="rbFijo" name="rbgTipoValor"/>
                                        <sup>
                                            <ui:label binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.lblFijo}" for="rbFijo" id="lblFijo"
                                                styleClass="label" text="Fijo"/>
                                        </sup>
                                    </td>
                                </tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.lblMotivo}" for="taMotivo" id="lblMotivo"
											styleClass="label" text="Motivo" />
									</td>
									<td>
										<ui:textArea binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.taMotivo}" columns="37" id="taMotivo" rows="5"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.lblDigestoMunicipal}" id="lblDigestoMunicipal"
											styleClass="label" text="Digesto Municipal" />
									</td>
									<td>
										<ui:textField binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.tfDigestoMunicipal}" columns="40" disabled="true"
											id="tfDigestoMunicipal" styleClass="textField" />
										<ui:button action="#{saic$ABMExencionObligacion$ABMExencionObligacion.btnSeleccionarDigesto_action}"
											binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.btnSeleccionarDigesto}" escape="false" id="btnSeleccionarDigesto"
											mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" />
										<a4j:commandButton id="btnLimpiarDigesto" reRender="form1:tfDigestoMunicipal" title="Limpiar"
											binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.btnLimpiarDigesto}"
											action="#{saic$ABMExencionObligacion$ABMExencionObligacion.btnLimpiarDigesto_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
                                   <td align="right" nowrap="nowrap">
                                       <ui:label binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.lblSeAplica}" id="lblSeAplica" for="ddSeAplica" styleClass="label"
											text="Se aplica"/>
                     				</td>
                     				<td>
										<ui:dropDown binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.ddSeAplica}" id="ddSeAplica"
											items="#{saic$ABMExencionObligacion$ABMExencionObligacion.ddSeAplicaOptions.options}"
											styleClass="textField">
											<a4j:support event="onChange" reRender="form1:panelTabla"
												actionListener="#{saic$ABMExencionObligacion$ABMExencionObligacion.valueChangeEventDdSeAplica(event)}" />
										</ui:dropDown>
									</td>
                               </tr>
                               <tr><td><BR/></td></tr>
                               <tr>
									<td colspan="2">
									<ui:label binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.lblObligacion}" id="lblObligacion" styleClass="label2" text="Obligaciones" />
										<ui:table binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.tablaObligacion}" id="tablaObligacion">
											<script>
												<![CDATA[
														/* ----- Functions for Table Preferences Panel ----- */
														/*
														 * Toggle the table preferences panel open or closed
														 */
														function togglePreferencesPanel() {
														  var table = document.getElementById("form1:tablaObligacion");
														  table.toggleTblePreferencesPanel();
														}
														/* ----- Functions for Filter Panel ----- */
														/*
														 * Return true if the filter menu has actually changed,
														 * so the corresponding event should be allowed to continue.
														 */
														function filterMenuChanged() {
														  var table = document.getElementById("form1:tablaObligacion");
														  return table.filterMenuChanged();
														}
														/*
														 * Toggle the custom filter panel (if any) open or closed.
														 */
														function toggleFilterPanel() {
														  var table = document.getElementById("form1:tablaObligacion");
														  return table.toggleTableFilterPanel();
														}
														/* ----- Functions for Table Actions ----- */
														/*
														 * Initialize all rows of the table when the state
														 * of selected rows changes.
														 */
														function initAllRows() {
														  var table = document.getElementById("form1:tablaObligacion");
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
														  var table = document.getElementById("form1:tablaObligacion");
														  table.selectGroupRows(rowGroupId, selected);
														}
														/*
														 * Disable all table actions if no rows have been selected.
														 */
														function disableActions() {
														  // Determine whether any rows are currently selected
														  var table = document.getElementById("form1:tablaObligacion");
														  var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
														  // Set disabled state for top actions
														  document.getElementById("form1:tablaObligacion:tableActionsTop:deleteTop").setDisabled(disabled);
														  // Set disabled state for bottom actions
														  document.getElementById("form1:tablaObligacion:tableActionsBottom:deleteBottom").setDisabled(disabled);
														}]]></script>
											<ui:tableRowGroup binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.trgObligacion}"
												emptyDataMsg="Ningún registro encontrado." id="trgObligacion"
												sourceData="#{saic$ABMExencionObligacion$ABMExencionObligacion.ldpObligacion}" sourceVar="currentRowObligacion">
												<ui:tableColumn align="center" binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.tcRbObligacion}" id="tcRbObligacion"
													valign="middle" width="10">
													<ui:radioButton binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.rbObligacion}" id="rbObligacion" label=""
														name="buttonGroupObligacion" selected="#{saic$ABMExencionObligacion$ABMExencionObligacion.RBSelectedObligacion}"
														selectedValue="#{saic$ABMExencionObligacion$ABMExencionObligacion.currentRowObligacion}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.tcDocumento}" headerText="Obligación"
													id="tcDocumento" sort="obligacion">
													<ui:staticText binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.stDocumento}" id="stDocumento"
														text="#{currentRowObligacion.value['documentoEspecializado']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.pgObligacion}" id="pgObligacion">
													<ui:staticText id="stSeleccionarObligacion" text="Seleccionar Obligación: "></ui:staticText>
													<ui:button action="#{saic$ABMExencionObligacion$ABMExencionObligacion.btnSeleccionarObligacionOSP_action}" styleClass="button" text="OSP"
														binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.btnSeleccionarObligacionOSP}" id="btnObligacionOSP"/>
													<ui:button action="#{saic$ABMExencionObligacion$ABMExencionObligacion.btnSeleccionarObligacionTGI_action}"
														binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.btnSeleccionarObligacionTGI}" id="btnObligacionTGI"
														styleClass="button" text="TGI" />
													<ui:button action="#{saic$ABMExencionObligacion$ABMExencionObligacion.btnSeleccionarObligacionPFO_action}"
														binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.btnSeleccionarObligacionPFO}" id="btnObligacionPFO"
														styleClass="button" text="PFO" />
													<ui:button action="#{saic$ABMExencionObligacion$ABMExencionObligacion.btnSeleccionarObligacionSHPS_action}"
														binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.btnSeleccionarObligacionSHPS}" id="btnObligacionSHPS"
														styleClass="button" text="SHPS" />
													<ui:button action="#{saic$ABMExencionObligacion$ABMExencionObligacion.btnSeleccionarObligacionAutomotor_action}"
														binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.btnSeleccionarObligacionAutomotor}" id="btnObligacionAutomotor"
														styleClass="button" text="Automotor" />
													<ui:button action="#{saic$ABMExencionObligacion$ABMExencionObligacion.btnSeleccionarObligacionCementerio_action}"
														binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.btnSeleccionarObligacionCementerio}" id="btnObligacionCementerio"
														styleClass="button" text="Cementerio" />
													<ui:staticText binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.stSeparador2}" escape="false" id="stSeparador2"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<a4j:commandButton action="#{saic$ABMExencionObligacion$ABMExencionObligacion.btnQuitarObligacion_action}" styleClass="btnAjax" reRender="tablaObligacion, panelTabla"
														binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.btnQuitarObligacion}" id="btnQuitarObligacion" value="Quitar" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
                               <tr><td><BR/></td></tr>
								<tr>
									<td colspan="2">
									<ui:panelGroup id="panelTabla">
									<ui:label binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.lblCondicionAplicacionExencion}" id="lblCondicionAplicacionExencion" styleClass="label2" text="Condiciones de aplicación" />
										<ui:table binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.tablaCondicionAplicacionExencion}" id="tablaCondicionAplicacionExencion">
											<script>
												<![CDATA[
														/* ----- Functions for Table Preferences Panel ----- */
														/*
														 * Toggle the table preferences panel open or closed
														 */
														function togglePreferencesPanel() {
														  var table = document.getElementById("form1:tablaCondicionAplicacionExencion");
														  table.toggleTblePreferencesPanel();
														}
														/* ----- Functions for Filter Panel ----- */
														/*
														 * Return true if the filter menu has actually changed,
														 * so the corresponding event should be allowed to continue.
														 */
														function filterMenuChanged() {
														  var table = document.getElementById("form1:tablaCondicionAplicacionExencion");
														  return table.filterMenuChanged();
														}
														/*
														 * Toggle the custom filter panel (if any) open or closed.
														 */
														function toggleFilterPanel() {
														  var table = document.getElementById("form1:tablaCondicionAplicacionExencion");
														  return table.toggleTableFilterPanel();
														}
														/* ----- Functions for Table Actions ----- */
														/*
														 * Initialize all rows of the table when the state
														 * of selected rows changes.
														 */
														function initAllRows() {
														  var table = document.getElementById("form1:tablaCondicionAplicacionExencion");
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
														  var table = document.getElementById("form1:tablaCondicionAplicacionExencion");
														  table.selectGroupRows(rowGroupId, selected);
														}
														/*
														 * Disable all table actions if no rows have been selected.
														 */
														function disableActions() {
														  // Determine whether any rows are currently selected
														  var table = document.getElementById("form1:tablaCondicionAplicacionExencion");
														  var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
														  // Set disabled state for top actions
														  document.getElementById("form1:tablaCondicionAplicacionExencion:tableActionsTop:deleteTop").setDisabled(disabled);
														  // Set disabled state for bottom actions
														  document.getElementById("form1:tablaCondicionAplicacionExencion:tableActionsBottom:deleteBottom").setDisabled(disabled);
														}]]></script>
											<ui:tableRowGroup binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.trgCondicionAplicacionExencion}"
												emptyDataMsg="Ningún registro encontrado." id="trgCondicionAplicacionExencion"
												sourceData="#{saic$ABMExencionObligacion$ABMExencionObligacion.ldpCondicionAplicacionExencion}" sourceVar="currentRowCondicionAplicacionExencion">
												<ui:tableColumn align="center" binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.tcRbCondicionAplicacionExencion}" id="tcRbCondicionAplicacionExencion"
													valign="middle" width="10">
													<ui:radioButton binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.rbCondicionAplicacionExencion}" id="rbCondicionAplicacionExencion" label=""
														name="buttonGroup" selected="#{saic$ABMExencionObligacion$ABMExencionObligacion.RBSelectedCondicionAplicacionExencion}"
														selectedValue="#{saic$ABMExencionObligacion$ABMExencionObligacion.currentRowCondicionAplicacionExencion}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.tcNombre}" headerText="Nombre"
													id="tcNombre" sort="nombre">
													<ui:staticText binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.stNombre}" id="stNombre"
														text="#{currentRowCondicionAplicacionExencion.value['nombre']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.pgCondicionAplicacionExencion}" id="pgCondicionAplicacionExencion"
													separator="&lt;br/&gt;&lt;br/&gt;">
													<ui:panelGroup binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.gpBotones}" id="gpBotones">
														<ui:label binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.lblAnios}" for="ddAnios" id="lblAnios"
														styleClass="label" text="Año" />
													<ui:dropDown binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.ddAnios}" id="ddAnios" styleClass="textField"
														items="#{saic$ABMExencionObligacion$ABMExencionObligacion.ddAniosOptions.options}"
														valueChangeListener="#{saic$ABMExencionObligacion$ABMExencionObligacion.eventoSeleccionAnio(evento)}">
														<a4j:support event="onChange" reRender="ddCalendarios, ddPeriodos, ddCuotas" />
													</ui:dropDown>
													<ui:label binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.lblCalendarios}" for="ddCalendarios"
														id="lblCalendarios" styleClass="label" text="Calendario" />
													<ui:dropDown binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.ddCalendarios}" id="ddCalendarios"
														styleClass="textField" items="#{saic$ABMExencionObligacion$ABMExencionObligacion.ddCalendariosOptions.options}"
														valueChangeListener="#{saic$ABMExencionObligacion$ABMExencionObligacion.eventoSeleccionCalendario(event)}">
														<a4j:support event="onChange" reRender="ddPeriodos, ddCuotas" />
													</ui:dropDown>
													<ui:label binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.lblPeriodos}" for="ddPeriodos" id="lblPeriodos"
														styleClass="label" text="Periodo" />
													<ui:dropDown binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.ddPeriodos}" id="ddPeriodos"
														styleClass="textField" items="#{saic$ABMExencionObligacion$ABMExencionObligacion.ddPeriodosOptions.options}"
														valueChangeListener="#{saic$ABMExencionObligacion$ABMExencionObligacion.eventoSeleccionPeriodo(event)}">
														<a4j:support event="onChange" reRender="ddCuotas " />
													</ui:dropDown>
													<ui:label binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.lblCuotas}" for="ddCuotas" id="lblCuotas"
														styleClass="label" text="Cuota" />
													<ui:dropDown binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.ddCuotas}" id="ddCuotas" styleClass="textField"
														items="#{saic$ABMExencionObligacion$ABMExencionObligacion.ddCuotasOptions.options}" />
													<a4j:commandButton action="#{saic$ABMExencionObligacion$ABMExencionObligacion.btnAgregarCondicionAplicacionExencion_action}"
														binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.btnAgregarCondicionAplicacionExencion}" id="btnAgregarCondicionAplicacionExencion" styleClass="btnAjax" value="Agregar" reRender="tablaCondicionAplicacionExencion"/>
													<ui:staticText binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.staticText7}" escape="false" id="staticText7"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<a4j:commandButton action="#{saic$ABMExencionObligacion$ABMExencionObligacion.btnQuitarCondicionAplicacionExencion_action}" styleClass="btnAjax" reRender="tablaCondicionAplicacionExencion"
														binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.btnQuitarCondicionAplicacionExencion}" id="btnQuitarCondicionAplicacionExencion" value="Quitar" />
													</ui:panelGroup>
												</ui:panelGroup>
											</f:facet>
										</ui:table>
										</ui:panelGroup>
									</td>
								</tr>
								<tr><td><BR/></td></tr>
                               <tr>
									<td colspan="2">
									<ui:label binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.lblCondicionAplicacionNumeroCuota}" id="lblCondicionAplicacionNumeroCuota"
											styleClass="label2" text="Condiciones de aplicación por número de período y cuota" />
										<ui:table binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.tablaCondicionAplicacionNumeroCuota}" id="tablaCondicionAplicacionNumeroCuota">
											<script>
												<![CDATA[
														/* ----- Functions for Table Preferences Panel ----- */
														/*
														 * Toggle the table preferences panel open or closed
														 */
														function togglePreferencesPanel() {
														  var table = document.getElementById("form1:tablaCondicionAplicacionNumeroCuota");
														  table.toggleTblePreferencesPanel();
														}
														/* ----- Functions for Filter Panel ----- */
														/*
														 * Return true if the filter menu has actually changed,
														 * so the corresponding event should be allowed to continue.
														 */
														function filterMenuChanged() {
														  var table = document.getElementById("form1:tablaCondicionAplicacionNumeroCuota");
														  return table.filterMenuChanged();
														}
														/*
														 * Toggle the custom filter panel (if any) open or closed.
														 */
														function toggleFilterPanel() {
														  var table = document.getElementById("form1:tablaCondicionAplicacionNumeroCuota");
														  return table.toggleTableFilterPanel();
														}
														/* ----- Functions for Table Actions ----- */
														/*
														 * Initialize all rows of the table when the state
														 * of selected rows changes.
														 */
														function initAllRows() {
														  var table = document.getElementById("form1:tablaCondicionAplicacionNumeroCuota");
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
														  var table = document.getElementById("form1:tablaCondicionAplicacionNumeroCuota");
														  table.selectGroupRows(rowGroupId, selected);
														}
														/*
														 * Disable all table actions if no rows have been selected.
														 */
														function disableActions() {
														  // Determine whether any rows are currently selected
														  var table = document.getElementById("form1:tablaCondicionAplicacionNumeroCuota");
														  var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
														  // Set disabled state for top actions
														  document.getElementById("form1:tablaCondicionAplicacionNumeroCuota:tableActionsTop:deleteTop").setDisabled(disabled);
														  // Set disabled state for bottom actions
														  document.getElementById("form1:tablaCondicionAplicacionNumeroCuota:tableActionsBottom:deleteBottom").setDisabled(disabled);
														}]]></script>
											<ui:tableRowGroup binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.trgCondicionAplicacionNumeroCuota}"
												emptyDataMsg="Ningún registro encontrado." id="trgCondicionAplicacionNumeroCuota"
												sourceData="#{saic$ABMExencionObligacion$ABMExencionObligacion.ldpCondicionAplicacionNumeroCuota}" sourceVar="currentRowCondicionAplicacionNumeroCuota">
												<ui:tableColumn align="center" binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.tcRbCondicionAplicacionNumeroCuota}" id="tcRbCondicionAplicacionNumeroCuota"
													valign="middle" width="10">
													<ui:radioButton binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.rbCondicionAplicacionNumeroCuota}" id="rbCondicionAplicacionNumeroCuota" label=""
														name="buttonGroupCondicionAplicacionNumeroCuota" selected="#{saic$ABMExencionObligacion$ABMExencionObligacion.RBSelectedCondicionAplicacionNumeroCuota}"
														selectedValue="#{saic$ABMExencionObligacion$ABMExencionObligacion.currentRowCondicionAplicacionNumeroCuota}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.tcNumeroPeriodo}" headerText="Nº de Período"
													id="tcNumeroPeriodo" sort="numeroPeriodo">
													<ui:textField binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.tfNumeroPeriodo}" id="tfNumeroPeriodo"
														text="#{currentRowCondicionAplicacionNumeroCuota.value['numeroPeriodo']}" onKeyPress="return ValidarNum(event)"/>
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.tcNumeroCuota}" headerText="Nº de Cuota"
													id="tcNumeroCuota" sort="numeroCuota">
													<ui:textField binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.tfNumeroCuota}" id="tfNumeroCuota"
														text="#{currentRowCondicionAplicacionNumeroCuota.value['numeroCuota']}" onKeyPress="return ValidarNum(event)"/>
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.pgCondicionAplicacionNumeroCuota}" id="pgCondicionAplicacionNumeroCuota">
													<a4j:commandButton action="#{saic$ABMExencionObligacion$ABMExencionObligacion.btnAgregarCondicionAplicacionNumeroCuota_action}" styleClass="btnAjax" reRender="tablaCondicionAplicacionNumeroCuota, tablaCondicionAplicacionExencion"
														binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.btnAgregarCondicionAplicacionNumeroCuota}" id="btnAgregarCondicionAplicacionNumeroCuota" value="Agregar" />
													<ui:staticText binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.stSeparador3}" escape="false" id="stSeparador3" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
													<a4j:commandButton action="#{saic$ABMExencionObligacion$ABMExencionObligacion.btnQuitarCondicionAplicacionNumeroCuota_action}" styleClass="btnAjax" reRender="tablaCondicionAplicacionNumeroCuota, tablaCondicionAplicacionExencion"
														binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.btnQuitarCondicionAplicacionNumeroCuota}" id="btnQuitarCondicionAplicacionNumeroCuota" value="Quitar" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:messageGroup binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.messageGroup1}" id="messageGroup1"
											styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="6" nowrap="true">
										<ui:button action="#{saic$ABMExencionObligacion$ABMExencionObligacion.btnGuardar_action}"
											binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.btnGuardar}" id="btnGuardar" styleClass="button" text="Guardar" />
										<ui:staticText binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{saic$ABMExencionObligacion$ABMExencionObligacion.btnCancelar_action}"
											binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.btnCancelar}" id="btnCancelar" styleClass="button"
											text="Cancelar" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.hidIdPagina}" id="hidIdPagina"
						text="#{saic$ABMExencionObligacion$ABMExencionObligacion.idPagina}" />
					<ui:hiddenField binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$ABMExencionObligacion$ABMExencionObligacion.idSubSesion}" />
					<ui:script binding="#{saic$ABMExencionObligacion$ABMExencionObligacion.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>