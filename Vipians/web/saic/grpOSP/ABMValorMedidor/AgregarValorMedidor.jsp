<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax" >
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.page1}" id="page1">
			<ui:html binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.html1}" id="html1">
			<ui:head binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.head1}" id="head1" title="Agregar Mediciones de Medidores">
				<ui:link binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var _decimales = 2;

					function getById(id) {
						elem = document.getElementById(id);
						return elem;
					}

					function formatNumber(number) {
						number = Math.round(number * Math.pow(10, _decimales))
								/ Math.pow(10, _decimales);
						cantDecimales = 0;
						if (number.toString().indexOf(".") >= 0)
							cantDecimales = number.toString().substring(
									number.toString().indexOf(".") + 1).length;
						if (number == Math.floor(number))
							number = number + ".";
						for (i = 0; i < (_decimales - cantDecimales); i++)
							number += "0";
						return number;
					}

					function calcular(input) {
						var locParentNode = input.parentNode;
						var fila = locParentNode.parentNode;

						var lecturaAnterior = fila.cells[3].childNodes[0].value;
						var lecturaActual = input.value;
						if (lecturaAnterior != ' ') {
							fila.cells[5].childNodes[0].value = lecturaActual
									- lecturaAnterior;
						} else {
							fila.cells[5].childNodes[0].value = 0;
						}

						var tabla = fila.parentNode;
						//calcularTotal(tabla);
					}

					function calcularTotal(tabla) {
						var rows = tabla.rows;
						var total = 0;
						for ( var i = 0; i < rows.length; i++) {
							var valor = rows[i].cells[3].childNodes[0].value;
							if (!isNaN(valor)) {
								total = Math.abs(total) - Math.abs(valor);
							}
						}
						tfConsumo = getById("form1:tfConsumo");

						tfConsumo.value = total;
					}
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.body1}" focus="form1:tfCalle" id="body1"
				onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<table align="center">
											<tr>
												<td>
													<br />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.lblCodigoMedidor}" for="lblCodigoMedidor" id="lblCodigor"
														styleClass="label" text="Código Medidor" />
												</td>
												<td>
													<ui:textField binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.tfCodigoMedidor}" id="tfCodigoMedidor"
														styleClass="textField" columns="10" />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.lblServicioOSP}" for="tfServicioOSP" id="lblServicioOSP"
														styleClass="label" text="Servicio" />
												</td>
												<td>
													<ui:textField binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.tfServicioOSP}" columns="40" disabled="true"
														id="tfServicioOSP" styleClass="textField" />
													<ui:button action="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.btnSeleccionarServicioOSP_action}"
														binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.btnSeleccionarServicioOSP}" escape="false"
														id="btnSeleccionarServicioOSP" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
													<a4j:commandButton id="btnLimpiarServicio" reRender="form1:tfServicioOSP" title="Limpiar"
														binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.btnLimpiarServicio}"
														action="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.btnLimpiarServicio_action}" styleClass="buttonLimpiarAjax" />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.lblCalle}" for="tfCalle" id="lblCalle" styleClass="label"
														text="Calle" />
												</td>
												<td>
													<ui:textField binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.tfCalle}" columns="40" disabled="true" id="tfCalle"
														styleClass="textField" />
													<ui:button action="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.btnSeleccionarCalle_action}"
														binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.btnSeleccionarCalle}" escape="false" id="btnSeleccionarCalle"
														mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
													<a4j:commandButton id="btnLimpiarCalle" reRender="form1:tfCalle" title="Limpiar"
														binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.btnLimpiarCalle}"
														action="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.btnLimpiarCalle_action}" styleClass="buttonLimpiarAjax" />
												</td>
											</tr>
											<tr>
													<td align="right" nowrap="nowrap">
														<ui:label for="tfParcela" id="lblParcela" styleClass="label" text="Parcela" />
													</td>
													<td nowrap="nowrap" colspan="4">
														<ui:textField binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.tfParcela}" columns="49" disabled="true"
															id="tfParcela" styleClass="textField" />
														<ui:button action="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.btnSeleccionarParcela_action}"
															binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.btnSeleccionarParcela}" id="btnSeleccionarParcela"
															escape="false" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
														<a4j:commandButton id="btnLimpiarParcela" reRender="form1:tfParcela"
															binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.btnLimpiarParcela}"
															action="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.btnLimpiarParcela_action}" styleClass="buttonLimpiarAjax" />
													</td>
												</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<table align="center">
											<tr>
												<td align="center" nowrap="nowrap">
													<ui:label binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.lblAnio}" for="ddAnios" id="lblAnio" styleClass="label"
														text="Año" />
													<ui:dropDown binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.ddAnios}" id="ddAnios" styleClass="textField"
														items="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.ddAniosOptions.options}"
														valueChangeListener="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.eventoSeleccionAnio(evento)}">
														<a4j:support event="onChange" reRender="form1:ddCalendarios, form1:ddPeriodos, form1:ddCuotas" />
													</ui:dropDown>
												</td>
												<td align="center" nowrap="nowrap">
													<ui:label binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.lblCalendario}" for="ddCalendarios" id="lblCalendarios"
														styleClass="label" text="Calendario" />
													<ui:dropDown binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.ddCalendarios}" id="ddCalendarios"
														styleClass="textField" items="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.ddCalendariosOptions.options}"
														valueChangeListener="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.eventoSeleccionCalendario(event)}">
														<a4j:support event="onChange" reRender="form1:ddPeriodos, form1:ddCuotas" />
													</ui:dropDown>
												</td>
												<td align="center" nowrap="nowrap">
													<ui:label binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.lblPeriodo}" for="ddPeriodos" id="lblPeriodos"
														styleClass="label" text="Periodo" />
													<ui:dropDown binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.ddPeriodos}" id="ddPeriodos" styleClass="textField"
														items="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.ddPeriodosOptions.options}"
														valueChangeListener="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.eventoSeleccionPeriodo(event)}">
														<a4j:support event="onChange" reRender="form1:ddCuotas " />
													</ui:dropDown>
												</td>
												<td align="center" nowrap="nowrap">
													<ui:label binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.lblCuota}" for="ddCuotas" id="lblCuotas"
														styleClass="label" text="Cuota" />
													<ui:dropDown binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.ddCuotas}" id="ddCuotas" styleClass="textField"
														items="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.ddCuotasOptions.options}" />
												</td>
											</tr>
										</table>
										<table align="center">
											<tr>
												<td>
													<br />
												</td>
											</tr>
											<tr>
												<td>
													<a4j:commandButton action="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.btnCargarMedidores_action}"
														binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.btnCargarMedidores}" id="btnCargarMedidores" styleClass="btnAjax"
														value="Cargar Medidores" reRender="form1:table1" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<hr />
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:label binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.label6}" id="label6" styleClass="label2"
											text="Valores de las Mediciones" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:table augmentTitle="false" binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.table1}" id="table1" width="871">
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
											<ui:tableRowGroup binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.tableRowGroup1}" id="tableRowGroup1"
												sourceData="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.ldpMedicionMedidorOSP}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.tableColumn1}" id="tableColumn1"
													rendered="false" valign="middle" width="10">
													<ui:radioButton binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.radioButton1}" id="radioButton1" label=""
														name="buttonGroup" selected="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.RBSelected}"
														selectedValue="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.tableColumn3}" headerText="Código Medidor"
													id="tableColumn3" sort="stringCodigoMedidor" width="20">
													<ui:staticText binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.staticText5}" id="staticText5"
														text="#{currentRow.value['stringCodigoMedidor']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.tableColumn4}" headerText="Persona" id="tableColumn4"
													sort="stringPersona" width="200">
													<ui:staticText binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.staticText1}" id="staticText1"
														text="#{currentRow.value['stringPersona']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.tableColumn2}" headerText="Dirección"
													id="tableColumn2" sort="stringDireccion" width="300">
													<ui:staticText binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.staticText4}" id="staticText4"
														text="#{currentRow.value['stringDireccion']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.tableColumn7}" headerText="Lectura Anterior"
													id="tableColumn7" sort="stringLecturaAnterior" width="20">
													<ui:textField binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.tfLecturaAnterior}" id="tfLecturaAnterior"
														disabled="true" text="#{currentRow.value['stringLecturaAnterior']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.tableColumn6}" headerText="Lectura Actual"
													id="tableColumn6" sort="lectura" width="20">
													<ui:textField binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.tfLectura}" id="tfLectura"
														text="#{currentRow.value['lectura']}" onBlur="calcular(this)" />
													<ui:message binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.message2}" for="tfLectura" id="message2"
														showDetail="false" showSummary="true" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.tableColumn8}" headerText="Consumo" id="tableColumn8"
													sort="consumo" width="20">
													<ui:textField binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.tfConsumo}" id="tfConsumo"
														text="#{currentRow.value['montoImponible']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<div>
											<a4j:outputPanel ajaxRendered="true">
												<ui:messageGroup binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.messageGroup1}" id="messageGroup1"
												showGlobalOnly="true" styleClass="grupoMsg" />
											</a4j:outputPanel>
										</div>
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="2" nowrap="nowrap" style="height: 24px">
										<ui:button action="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.btnGuardar_action}"
											binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.btnGuardar}" id="btnGuardar" styleClass="button" text="Guardar" />
										<ui:staticText binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.btnCancelar_action}"
											binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.hidIdPagina}" id="hidIdPagina"
						text="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.idPagina}" />
					<ui:hiddenField binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.idSubSesion}" />
					<ui:script binding="#{saic$grpOSP$ABMValorMedidor$AgregarValorMedidor.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
