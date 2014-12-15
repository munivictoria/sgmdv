<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.page1}" id="page1">
			<ui:html binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.html1}" id="html1">
			<ui:head binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.head1}" id="head1">
				<ui:link binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[

					function calcular(input) {
						var locParentNode = input.parentNode;
						var fila = locParentNode.parentNode;
						
						var cantidad = parseFloat(fila.cells[3].childNodes[0].value);
						var precioUnitario = parseFloat(fila.cells[2].childNodes[0].value);
						fila.cells[4].childNodes[0].value = cantidad * precioUnitario;
						calcularTotal();
					}

					function calcularTotal() {
						
						var tabla = document.getElementById('form1:table1');
						var filas = tabla.rows;
						var acum = 0;

						for ( var i = 2; i < filas.length; i++) // el for empieza de 2 xq fila 0 y 1 son para nombre de la tabla y los botones
						{
							var span = filas[i].cells[4].childNodes[0].value;
							acum += parseFloat(span);
						}
						document.getElementById('form1:stTotal').innerHTML = "$" + acum;
					}

					function cargarComportamientoJQuery() {
						calendarioEnTextField("#form1:tfFechaEmision");
					}
					$(document).ready(function() {
						cargarComportamientoJQuery();
					});

					]]>
				</script>
			</ui:head>
			<ui:body binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.body1}" focus="form1:tfFechaEmision" id="body1"
				onLoad="parent.footer.location.reload(); Init(); calcularTotal();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.lblFechaEmision}" for="tfFechaEmision" id="lblFechaEmision"
											styleClass="label" text="Fecha Emisión" />
									</td>
									<td>
										<ui:textField binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.tfFechaEmision}" id="tfFechaEmision"
											styleClass="textField" columns="10" />
										<!--<ui:staticText binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.stFechaEmision}" escape="false"
                                                id="stFechaEmision" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.lblNumeroFactura}" for="tfNumeroFactura"
											id="lblNumeroFactura" styleClass="label" text="Nº de Factura" />
									</td>
									<td>
										<ui:textField binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.tfNumeroFactura}" columns="20" id="tfNumeroFactura"
											styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.lblProveedor}" for="tfProveedor" id="lblProveedor"
											styleClass="label" text="Proveedor" />
									</td>
									<td>
										<ui:textField binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.tfProveedor}" columns="40" disabled="true"
											id="tfProveedor" styleClass="textField" />
										<ui:button action="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.btnSeleccionarProveedor_action}"
											binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.btnSeleccionarProveedor}" escape="false" id="btnSeleccionarProveedor"
											mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.lblTipo}" for="ddTipoFactura" id="lblTipo" styleClass="label"
											text="Tipo de Factura" />
									</td>
									<td nowrap="nowrap">
										<ui:dropDown binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.ddTipoFactura}" id="ddTipoFactura"
											items="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.ddTipoFacturaDefaultOptions.options}" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<div class="div" style="width: 290px; height: 15px;">Atributos Dinámicos</div>
										<table border="0" class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
											<tr>
												<td colspan="4">
													<ui:panelGroup binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.panelAtributoDinamico}" id="panelAtributoDinamico">
														<!-- AQUI VA LO QUE SE CREA DINAMICAMENTE -->
													</ui:panelGroup>
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
										<ui:label binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.lblLineaFactura}" id="lblLineaFactura" styleClass="label2"
											text="Líneas de la Factura" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:table augmentTitle="false" binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.table1}" id="table1" width="239">
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
}]]></script>
											<ui:tableRowGroup binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.tableRowGroup1}" id="tableRowGroup1"
												sourceData="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.ldpLineaFactura}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.tableColumn1}" id="tableColumn1"
													valign="middle" width="10">
													<ui:radioButton binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.radioButton1}" id="radioButton1" label=""
														name="buttonGroup" selected="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.RBSelected}"
														selectedValue="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.tcNombre}" headerText="Nombre" id="tcNombre"
													sort="nombre" width="40">
													<ui:staticText binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.stNombre}" id="stNombre"
														text="#{currentRow.value['nombre']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.tcPrecio}" headerText="Precio" id="tcPrecio"
													width="40" sort="montoUnitario">
													<ui:textField binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.tfPrecio}" styleClass="textField" id="tfPrecio" onBlur="calcular(this)" text="#{currentRow.value['montoUnitario']}"/>
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.tcCantidad}" headerText="Cantidad" id="tcCantidad"
													sort="cantidad">
													<ui:textField binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.tfCantidad}" maxLength="10" 
														id="tfCantidad" onBlur="calcular(this)" styleClass="textField" text="#{currentRow.value['cantidad']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.tcTotal}" headerText="Monto Total" id="tcTotal"
													sort="total">
													<ui:textField binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.tfTotal}" id="tfTotal" disabled="true" maxLength="40"
														style="text-align:right; padding-right:6px;" styleClass="textFieldDisabled" text="#{currentRow.value['total']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.tcCuentaAsociada}" headerText="Cuenta Asociada"
													id="tcCuentaAsociada" sort="cuenta">
													<ui:staticText binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.stCuenta}" id="stCuenta"
														text="#{currentRow.value['cuenta']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.groupPanel1}" id="groupPanel1">
													<ui:button action="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.btnAgregarOrden_action}"
														binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.btnAgregarOrden}" id="btnAgregarOrden" styleClass="button"
														text="Agregar Orden de Compra" />
													<ui:staticText binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.stSeparador3}" escape="false" id="stSeparador3"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.btnAgregarLinea_action}"
														binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.btnAgregarLinea}" id="btnAgregarLinea" styleClass="button"
														text="Agregar Línea Factura" />
													<!--<ui:button action="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.btnModificarLinea_action}"
                                                                   binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.btnModificarLinea}"
                                                                   id="btnModificarLinea" styleClass="button" text="Modificar Línea Factura"/>-->
													<ui:staticText binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.stSeparador}" escape="false" id="stSeparador"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:staticText binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.stSeparador2}" escape="false" id="stSeparador2"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<a4j:commandButton action="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.btnQuitar_action}"
														binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.btnQuitar}" id="btnQuitar" value="Quitar" styleClass="btnAjax"
														reRender="table1,stTotal" onmousedown="reemplazarClickConConfirmacion(this, '');" />
													<a4j:commandButton action="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.btnQuitarTodos_action}"
														binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.btnQuitarTodos}" id="btnQuitarTodos" value="Quitar todos"
														styleClass="btnAjax" reRender="table1,stTotal"
														onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea quitar todos los elementos de esta lista?');" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="right" colspan="2">
										<ui:label binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.lblTotalF}" id="label4" styleClass="label" text="Total:" />
										<ui:staticText binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.stTotal}"
											converter="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.numberConverter1}" id="stTotal" styleClass="staticText" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right">
										<ui:label binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
									</td>
									<td>
										<ui:textArea binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.tablaLogs}" id="tbLogsAuditoria" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:messageGroup binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.messageGroup}" id="messageGroup" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="2" nowrap="nowrap">
										<ui:button action="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.btnGuardar_action}"
											binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.btnCancelar_action}"
											binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.idSubSesion}" />
					<ui:script binding="#{compras$ABMFacturaProveedor$ABMFacturaProveedor.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
