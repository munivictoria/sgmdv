<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.page1}" id="page1">
			<ui:html binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.html1}" id="html1">
			<ui:head binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.head1}" id="head1">
				<ui:link binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.body1}" focus="form1:tfNombre" id="body1" onLoad="parent.footer.location.reload();Init();"
				 onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="amarillo">
							<caption>
								<ui:staticText binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.head1.title}" />
							</caption>
							<tbody>
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
										<ui:textField binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label for="tfMontoCondonacionImporte" id="lblMontoCondonacionImporte" styleClass="label" text="Monto condonado del importe" />
									</td>
									<td>
										<ui:textField binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.tfMontoCondonacionImporte}" 
											columns="10" id="tfMontoCondonacionImporte" styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label for="cbCondonacionImportePorcentual" id="lblMontoCondonacionImportePorcentual" styleClass="label" text="Porcentual" />
									</td>
									<td>
										<ui:checkbox id="cbCondonacionImportePorcentual" binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.cbCondonacionImportePorcentual}"/>
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label for="tfMontoCondonacionInteres" id="lblMontoCondonacionInteres" styleClass="label" text="Monto condonado del Interes" />
									</td>
									<td>
										<ui:textField binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.tfMontoCondonacionInteres}" 
											columns="10" id="tfMontoCondonacionInteres" styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label for="cbCondonacionInteresPorcentual" id="lblMontoCondonacionInteresPorcentual" styleClass="label" text="Porcentual" />
									</td>
									<td>
										<ui:checkbox id="cbCondonacionInteresPorcentual" binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.cbCondonacionInteresPorcentual}"/>
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label for="tfCantidadCuotas" id="lblCantidadCuotas" styleClass="label" text="Cantidad de Cuotas" />
									</td>
									<td>
										<ui:textField binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.tfCantidadCuotas}" 
											columns="10" id="tfCantidadCuotas" styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label for="tfDiaDeVencimiento" id="lblDiaDeVencimiento" styleClass="label" text="Dia de Vencimiento" />
									</td>
									<td>
										<ui:textField binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.tfDiaVencimiento}" 
											columns="10" id="tfDiaDeVencimiento" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label for="tfTasaNominalAnual" id="lblTasaNominalAnual" styleClass="label" text="Tasa Nominal Anual" />
									</td>
									<td>
										<ui:textField binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.tfTasaNominalAnual}" 
											columns="10" id="tfTasaNominalAnual" styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label for="tfInteresPunitorio" id="lblInteresPunitorio" styleClass="label" text="Interes Punitorio" />
									</td>
									<td>
										<ui:textField binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.tfInteresPunitorio}" 
											columns="10" id="tfInteresPunitorio" styleClass="textField" />
									</td>
								</tr>
								<tr>
												<td align="right">
													<ui:label id="lblTipoCalculoInteres" for="ddTipoCalculoInteres" styleClass="label" text="Tipo Calculo de Interes" />
												</td>
												<td align="left">
													<ui:dropDown binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.ddTipoCalculoInteres}"
														id="ddTipoCalculoInteres"
														items="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.ddTipoCalculoInteresDefaultOptions.options}" 
														styleClass="textField" />
												</td>
												<td></td><td></td>
											</tr>
									<tr>
									<td align="right" nowrap="nowrap">
										<ui:label for="tfCantidadDiasCese" id="lblCantidadDiasCese" styleClass="label" text="Cantidad Dias Cese" />
									</td>
									<td>
										<ui:textField binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.tfCantidadDiasCese}" 
											columns="10" id="tfCantidadDiasCese" styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label for="tfCantidadCuotasCese" id="lblCantidadCuotasCese" styleClass="label" text="Cantidad Cuotas Cese" />
									</td>
									<td>
										<ui:textField binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.tfCantidadCuotasCese}" 
											columns="10" id="tfCantidadCuotasCese" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label for="tfAniosAplicacion" id="lblAniosAplicacion" styleClass="label" text="Años aplicables" />
									</td>
									<td>
										<ui:textField binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.tfAniosAplicacion}" 
											columns="50" id="tfAniosAplicacion" styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label for="cbCondonaDeudaAntigua" id="lblCondonaDeudaAntigua" styleClass="label" text="Condona deuda antigua" />
									</td>
									<td>
										<ui:checkbox id="cbCondonaDeudaAntigua" 
											binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.cbCondonaDeudaAntigua}"/>
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label for="tfCantidadPropiedades" id="lblCantidadPropiedades" styleClass="label" text="Cantidad máxima propiedades" />
									</td>
									<td>
										<ui:textField binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.tfCantidadPropiedades}" 
											columns="10" id="tfCantidadPropiedades" styleClass="textField" />
									</td>
									<td align="right" nowrap="nowrap">
										<ui:label for="tfFechaVencimientoPrimerCuota" 
											id="lblFechaVencimientoPrimerCuota" styleClass="label" text="Fecha vencimiento primer cuota" />
									</td>
									<td>
										<ui:textField binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.tfFechaVencimientoPrimerCuota}" 
											columns="10" id="tfFechaVencimientoPrimerCuota" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:label id="lblTasas" styleClass="label57" text="Tasa Nominal Anual" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.tablaTasas}"
											id="tablaTasas">
											<script>
												<![CDATA[
												/* ----- Functions for Table Preferences Panel ----- */
												/*
												 * Toggle the table preferences panel open or closed
												 */
												function togglePreferencesPanel() {
													var table = document
															.getElementById("form1:tablaTasas");
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
															.getElementById("form1:tablaTasas");
													return table
															.filterMenuChanged();
												}
												/*
												 * Toggle the custom filter panel (if any) open or closed.
												 */
												function toggleFilterPanel() {
													var table = document
															.getElementById("form1:tablaTasas");
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
															.getElementById("form1:tablaTasas");
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
															.getElementById("form1:tablaTasas");
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
															.getElementById("form1:tablaTasas");
													var disabled = (table
															.getAllSelectedRowsCount() > 0) ? false
															: true;
													// Set disabled state for top actions
													document
															.getElementById(
																	"form1:tablaTasas:tableActionsTop:deleteTop")
															.setDisabled(
																	disabled);
													// Set disabled state for bottom actions
													document
															.getElementById(
																	"form1:tablaTasas:tableActionsBottom:deleteBottom")
															.setDisabled(
																	disabled);
												}
												]]>
											</script>
											<ui:tableRowGroup binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.trgTasas}" id="trgTasas"
												sourceData="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.ldpTasas}" sourceVar="currentRowTasas">
												<ui:tableColumn align="center" id="tcRbTasas"
													valign="middle" width="10">
													<ui:radioButton binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.rbTasas}" id="rbTasas" label=""
														name="buttonGroupTasas" selected="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.RBSelectedTasas}"
														selectedValue="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.currentRowTasas}" />
												</ui:tableColumn>
												<ui:tableColumn headerText="Hasta Cuotas" id="tcHastaCuotas" sort="cuotasHasta">
													<ui:textField id="tfCuotasHasta" text="#{currentRowTasas.value['cuotasHasta']}"/>
												</ui:tableColumn>
												<ui:tableColumn headerText="Interes" id="tcInteres" sort="interes">
													<ui:textField  id="tfInteres" text="#{currentRowTasas.value['interes']}"/>
												</ui:tableColumn>
												<ui:tableColumn headerText="Interes condonado" id="tcInteresCondonado" sort="interesCondonado">
													<ui:textField  id="tfInteresCondonado" text="#{currentRowTasas.value['interesCondonado']}"/>
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.pgTasas}" id="pgTasasAction">
													<a4j:commandButton action="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.btnAgregarTasa_action}"
														binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.btnAgregarTasa}" id="btnAgregarTasa" 
														value="Agregar" styleClass="btnAjax" reRender="tablaTasas" />
													<a4j:commandButton action="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.btnQuitarTasa_action}"
														binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.btnQuitarTasa}" id="btnQuitarTasa" 
														value="Quitar" styleClass="btnAjax" reRender="tablaTasas" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:label id="lblTablaCondicionesCondonacion" styleClass="label57" text="Condiciones de Condonación de Período" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.tablaCondicionCondonacion}"
											id="tablaCondicionCondonacion">
											<script>
												<![CDATA[
												/* ----- Functions for Table Preferences Panel ----- */
												/*
												 * Toggle the table preferences panel open or closed
												 */
												function togglePreferencesPanel() {
													var table = document
															.getElementById("form1:tablaCondicionCondonacion");
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
															.getElementById("form1:tablaCondicionCondonacion");
													return table
															.filterMenuChanged();
												}
												/*
												 * Toggle the custom filter panel (if any) open or closed.
												 */
												function toggleFilterPanel() {
													var table = document
															.getElementById("form1:tablaCondicionCondonacion");
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
															.getElementById("form1:tablaCondicionCondonacion");
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
															.getElementById("form1:tablaCondicionCondonacion");
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
															.getElementById("form1:tablaCondicionCondonacion");
													var disabled = (table
															.getAllSelectedRowsCount() > 0) ? false
															: true;
													// Set disabled state for top actions
													document
															.getElementById(
																	"form1:tablaCondicionCondonacion:tableActionsTop:deleteTop")
															.setDisabled(
																	disabled);
													// Set disabled state for bottom actions
													document
															.getElementById(
																	"form1:tablaCondicionCondonacion:tableActionsBottom:deleteBottom")
															.setDisabled(
																	disabled);
												}
												]]>
											</script>
											<ui:tableRowGroup binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.trgCondicionCondonacion}" id="trgCondicionCondonacion"
												sourceData="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.ldpCondicionCondonacion}" sourceVar="currentRowCondicion">
												<ui:tableColumn align="center" id="tcRbCondicionCondonacion"
													valign="middle" width="10">
													<ui:radioButton binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.rbCondicionCondonacion}" id="rbCondicionCondonacion" label=""
														name="buttonGroupCondicionCondonacion" selected="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.RBCondicionCondonacion}"
														selectedValue="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.currentRowCondicionCondonacion}" />
												</ui:tableColumn>
												<ui:tableColumn headerText="Año" id="tcAnio" sort="anio">
													<ui:textField id="tfAnio" text="#{currentRowCondicionCondonacion.value['anio']}"/>
												</ui:tableColumn>
												<ui:tableColumn headerText="Condicion" id="tcCondicion" sort="condicion">
													<ui:textField  id="tfCondicion" text="#{currentRowTasas.value['condicion']}"/>
												</ui:tableColumn>
												<ui:tableColumn headerText="Porcentaje" id="tcPorcentejeCondicion" sort="porcentaje">
													<ui:textField  id="tfPorcentajeCondicionCondonacion" text="#{currentRowTasas.value['porcentaje']}"/>
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.pgCondicionCondonacion}" id="pgCondicionCondonacionAction">
													<a4j:commandButton action="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.btnAgregarCondicionCondonacion_action}"
														binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.btnAgregarCondicionCondonacion}" id="btnAgregarCondicionCondonacion" 
														value="Agregar" styleClass="btnAjax" reRender="tablaCondicionCondonacion" />
													<a4j:commandButton action="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.btnQuitarCondicionCondonacion_action}"
														binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.btnQuitarCondicionCondonacion}" id="btnQuitarCondicionCondonacion" 
														value="Quitar" styleClass="btnAjax" reRender="tablaCondicionCondonacion" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:label id="lblCuentas" styleClass="label57" text="Cuentas" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.tablaCuentas}"
											id="tablaCuentas">
											<script>
												<![CDATA[
												/* ----- Functions for Table Preferences Panel ----- */
												/*
												 * Toggle the table preferences panel open or closed
												 */
												function togglePreferencesPanel() {
													var table = document
															.getElementById("form1:tablaCuentas");
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
															.getElementById("form1:tablaCuentas");
													return table
															.filterMenuChanged();
												}
												/*
												 * Toggle the custom filter panel (if any) open or closed.
												 */
												function toggleFilterPanel() {
													var table = document
															.getElementById("form1:tablaCuentas");
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
															.getElementById("form1:tablaCuentas");
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
															.getElementById("form1:tablaCuentas");
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
															.getElementById("form1:tablaCuentas");
													var disabled = (table
															.getAllSelectedRowsCount() > 0) ? false
															: true;
													// Set disabled state for top actions
													document
															.getElementById(
																	"form1:tablaCuentas:tableActionsTop:deleteTop")
															.setDisabled(
																	disabled);
													// Set disabled state for bottom actions
													document
															.getElementById(
																	"form1:tablaCuentas:tableActionsBottom:deleteBottom")
															.setDisabled(
																	disabled);
												}
												]]>
											</script>
											<ui:tableRowGroup binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.trgCuentas}" id="trgCuentas"
												sourceData="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.ldpCuentas}" sourceVar="currentRowCuentas">
												<ui:tableColumn align="center" binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.tcRbCuentas}" id="tcRbCuentas"
													valign="middle" width="10">
													<ui:radioButton binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.rbCuentas}" id="rbCuentas" label=""
														name="buttonGroupCuentas" selected="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.RBSelectedCuentas}"
														selectedValue="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.currentRowCuentas}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.tcCodigoImputacion}" headerText="Código Imputación" id="tcCodigoImputacion" sort="codigoImputacion">
													<ui:staticText id="stCodigoImputacion" text="#{currentRowCuentas.value['cuenta'].codigoImputacion}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.tcNombreCuenta}" headerText="Nombre" id="tcNombreCuenta" sort="nombre">
													<ui:staticText id="stNombreCuenta" text="#{currentRowCuentas.value['cuenta'].nombre}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.tcPorcentaje}" headerText="Porcentaje" id="tcPorcentaje" sort="porcentaje">
													<ui:textField binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.tfPorcentaje}" id="tfPorcentaje" text="#{currentRowCuentas.value['porcentaje']}"/>
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.pgCuentas}" id="pgCuentas">
													<ui:button action="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.btnAgregarCuenta_action}"
														binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.btnAgregarCuenta}" id="btnAgregarCuenta"
														styleClass="button" text="Agregar"/>
													<a4j:commandButton action="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.btnQuitarCuenta_action}"
														binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.btnQuitarCuenta}" id="btnQuitarCuenta" 
														value="Quitar" styleClass="btnAjax" reRender="tablaCuentas" />
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
										<ui:label binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
									</td>
									<td>
										<ui:textArea binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.tablaLogs}" id="tbLogsAuditoria" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.messageGroup1}" id="messageGroup1" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.btnGuardar_action}" binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.btnGuardar}"
											id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.btnCancelar_action}" binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.btnCancelar}"
											id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.hidIdPagina}" id="hidIdPagina" text="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.idPagina}" />
					<ui:hiddenField binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.idSubSesion}" />
					<ui:script binding="#{saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
