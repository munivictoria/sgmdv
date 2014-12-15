<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMLicitacion$ABMLicitacion.page1}" id="page1">
			<ui:html binding="#{compras$ABMLicitacion$ABMLicitacion.html1}" id="html1">
			<ui:head binding="#{compras$ABMLicitacion$ABMLicitacion.head1}" id="head1">
				<ui:link binding="#{compras$ABMLicitacion$ABMLicitacion.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[

					function calcular(input) {
						var locParentNode = input.parentNode;
						var fila = locParentNode.parentNode;
						var cantidad = parseFloat(fila.cells[2].childNodes[0].innerHTML);
						var precioUnitario = parseFloat(input.value);
						fila.cells[4].childNodes[0].innerHTML = roundNumber(cantidad * precioUnitario, 2);
						calcularTotal(fila.parentNode.parentNode);
					}

					function calcularTotal() {

						var tabla = document.getElementById('form1:tabSet1:one:table1');

						var filas = tabla.rows;

						var acum = 0;

						for( var i = 2; i < filas.length; i++) // el for empieza de 2 xq fila 0 y 1 son para nombres de las columnas y los botones
						{
							var span = filas[i].cells[4].childNodes[0];
							if(isNaN(parseFloat(span.innerHTML))) {
								span.innerHTML = 0.00;
							}
							acum += parseFloat(span.innerHTML);
						}
						document.getElementById('form1:tabSet1:one:stTotal').innerHTML = "$" + roundNumber(acum, 2);
					}
					function roundNumber(number, digits) {
						var multiple = Math.pow(10, digits);
						var rndedNum = Math.round(number * multiple) / multiple;
						return rndedNum;
					}

					]]>
				</script>
			</ui:head>
			<ui:body binding="#{compras$ABMLicitacion$ABMLicitacion.body1}" focus="form1:tabSet1:one:taComentarios" id="body1"
				onLoad="parent.footer.location.reload(); Init(); calcularTotal();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMLicitacion$ABMLicitacion.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{compras$ABMLicitacion$ABMLicitacion.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{compras$ABMLicitacion$ABMLicitacion.head1.title}" />
							</caption>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tbody>
								<tr>
									<td align="left" colspan="4">
										<ui:tabSet binding="#{compras$ABMLicitacion$ABMLicitacion.tabSet1}" id="tabSet1" mini="true" lite="true">
											<ui:tab id="one" text="1 - Líneas">
												<table>
													<tr>
														<td>
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:label binding="#{compras$ABMLicitacion$ABMLicitacion.lblLineasContratacion}" id="lblLineasContratacion"
																styleClass="label2" text="Líneas de la Contratación" />
														</td>
													</tr>
													<tr>
														<td colspan="4" style="height: 51px">
															<ui:table augmentTitle="false" binding="#{compras$ABMLicitacion$ABMLicitacion.table1}" id="table1" width="240">
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
																		document.getElementById("form1:table1:tableActionsTop:deleteTop")
																				.setDisabled(disabled);
																		// Set disabled state for bottom actions
																		document.getElementById("form1:table1:tableActionsBottom:deleteBottom")
																				.setDisabled(disabled);
																	}
																	]]>
																</script>
																<ui:tableRowGroup binding="#{compras$ABMLicitacion$ABMLicitacion.tableRowGroup1}" id="tableRowGroup1"
																	sourceData="#{compras$ABMLicitacion$ABMLicitacion.ldpLineasContratacion}" sourceVar="currentRow">
																	<ui:tableColumn align="center" binding="#{compras$ABMLicitacion$ABMLicitacion.tableColumn1}" id="tableColumn1"
																		valign="middle" width="10">
																		<ui:radioButton binding="#{compras$ABMLicitacion$ABMLicitacion.radioButton1}" id="radioButton1" label=""
																			name="buttonGroup" selected="#{compras$ABMLicitacion$ABMLicitacion.RBSelected}"
																			selectedValue="#{compras$ABMLicitacion$ABMLicitacion.currentRow}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{compras$ABMLicitacion$ABMLicitacion.tcBien}" headerText="Bien" id="tcBien" sort="bien">
																		<ui:staticText binding="#{compras$ABMLicitacion$ABMLicitacion.stBien}" id="stBien" text="#{currentRow.value['bien']}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{compras$ABMLicitacion$ABMLicitacion.tcCantidad}" headerText="Cantidad" id="tcCantidad"
																		sort="cantidad">
																	<!--	<ui:textField binding="#{compras$ABMLicitacion$ABMLicitacion.tfCantidad}" id="tfCantidad" 
																			text="#{currentRow.value['cantidad']}"  readOnly="#{compras$ABMLicitacion$ABMLicitacion.readOnlyTfCantidad}" /> 
																			<a4j:support event="onBlur" reRender="table1, tcPrecioTotal" actionListener="#{compras$ABMLicitacion$ABMLicitacion.validarCantidadLinea}"/> -->
																		<ui:textField binding="#{compras$ABMLicitacion$ABMLicitacion.tfCantidad}" id="tfCantidad"
																			text="#{currentRow.value['cantidad']}" readOnly="#{compras$ABMLicitacion$ABMLicitacion.readOnlyTfCantidad}">
																			<a4j:support event="onBlur" reRender="table1"
																				actionListener="#{compras$ABMLicitacion$ABMLicitacion.validarCantidadLinea}" />
																		</ui:textField>
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{compras$ABMLicitacion$ABMLicitacion.tcPrecioUnitReferencial}"
																		headerText="Precio Unitario Referencial" id="tcPrecioUnitReferencial" sort="valorReferencial" width="40">
																		<ui:textField binding="#{compras$ABMLicitacion$ABMLicitacion.tfPrecioUnitarioReferencial}"
																			id="tfPrecioUnitarioReferencial" text="#{currentRow.value['valorReferencial']}" onBlur="calcular(this)"	onKeyPress="return ValidarFloat(event,this)"> 
																			<a4j:support event="onChange"  reRender="table1, tcPrecioTotal"/>
																		</ui:textField> 
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{compras$ABMLicitacion$ABMLicitacion.tcPrecioTotal}" headerText="Subtotal" id="tcPrecioTotal"
																		sort="montoTotalReferencial" width="40">
																		<ui:staticText binding="#{compras$ABMLicitacion$ABMLicitacion.stPrecioTotal}" id="stPrecioTotal"
																			text="#{currentRow.value['montoTotalReferencial']}" />
																	</ui:tableColumn>
																</ui:tableRowGroup>
																<f:facet name="actionsTop">
																	<ui:panelGroup binding="#{compras$ABMLicitacion$ABMLicitacion.groupPanel1}" id="groupPanel1">
																		<ui:button action="#{compras$ABMLicitacion$ABMLicitacion.btnAgregarLinea_action}"
																			binding="#{compras$ABMLicitacion$ABMLicitacion.btnAgregarLinea}" id="btnAgregarLinea" styleClass="button"
																			text="Agregar Línea" />
																		<a4j:commandButton action="#{compras$ABMLicitacion$ABMLicitacion.btnQuitarLinea_action}"
																			binding="#{compras$ABMLicitacion$ABMLicitacion.btnQuitarLinea}" id="btnQuitarLinea" value="Quitar" styleClass="btnAjax"
																			reRender="table1" onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea quitar todos los elementos de esta lista?');"/>
																		<ui:staticText binding="#{compras$ABMLicitacion$ABMLicitacion.stSeparador2}" escape="false" id="stSeparador2"
																			text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
																		<a4j:commandButton action="#{compras$ABMLicitacion$ABMLicitacion.btnQuitarTodosLinea_action}"
																			binding="#{compras$ABMLicitacion$ABMLicitacion.btnQuitarTodosLinea}" id="btnQuitarTodosLinea"
																			value="Quitar todos" styleClass="btnAjax" reRender="table1"
																			onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea quitar todos los elementos de esta lista?');" />	
																	</ui:panelGroup>
																</f:facet>
															</ui:table>
													<tr>
														<td align="right" colspan="3" id="tdTotal">
															<ui:label binding="#{compras$ABMLicitacion$ABMLicitacion.lblTotal}" id="lblTotal" styleClass="label" text="Total:" />
															<ui:staticText binding="#{compras$ABMLicitacion$ABMLicitacion.stTotal}" id="stTotal" styleClass="staticText" />
														</td>
													</tr>
													</td>
													</tr>
													<tr>
														<td colspan="4" style="text-align: right">
															<ui:staticText text="" id="stTotalReferencial" />
															<!--el total de la tabla-->
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:staticText binding="#{compras$ABMLicitacion$ABMLicitacion.stProveedores}" id="stProveedores" styleClass="label2"
																text="Proveedores" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table augmentTitle="false" binding="#{compras$ABMLicitacion$ABMLicitacion.table2}" id="table2" width="479">
																<script>
																	<![CDATA[
																	/* ----- Functions for Table Preferences Panel ----- */
																	/*
																	 * Toggle the table preferences panel open or closed
																	 */
																	function togglePreferencesPanel() {
																		var table = document.getElementById("form1:table2");
																		table.toggleTblePreferencesPanel();
																	}
																	/* ----- Functions for Filter Panel ----- */
																	/*
																	 * Return true if the filter menu has actually changed,
																	 * so the corresponding event should be allowed to continue.
																	 */
																	function filterMenuChanged() {
																		var table = document.getElementById("form1:table2");
																		return table.filterMenuChanged();
																	}
																	/*
																	 * Toggle the custom filter panel (if any) open or closed.
																	 */
																	function toggleFilterPanel() {
																		var table = document.getElementById("form1:table2");
																		return table.toggleTableFilterPanel();
																	}
																	/* ----- Functions for Table Actions ----- */
																	/*
																	 * Initialize all rows of the table when the state
																	 * of selected rows changes.
																	 */
																	function initAllRows() {
																		var table = document.getElementById("form1:table2");
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
																		var table = document.getElementById("form1:table2");
																		table.selectGroupRows(rowGroupId, selected);
																	}
																	/*
																	 * Disable all table actions if no rows have been selected.
																	 */
																	function disableActions() {
																		// Determine whether any rows are currently selected
																		var table = document.getElementById("form1:table2");
																		var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
																		// Set disabled state for top actions
																		document.getElementById("form1:table2:tableActionsTop:deleteTop")
																				.setDisabled(disabled);
																		// Set disabled state for bottom actions
																		document.getElementById("form1:table2:tableActionsBottom:deleteBottom")
																				.setDisabled(disabled);
																	}
																	]]>
																</script>
																<ui:tableRowGroup binding="#{compras$ABMLicitacion$ABMLicitacion.tableRowGroup2}" id="tableRowGroup2"
																	sourceData="#{compras$ABMLicitacion$ABMLicitacion.ldpProveedores}" sourceVar="currentRow2">
																	<ui:tableColumn align="center" binding="#{compras$ABMLicitacion$ABMLicitacion.tableColumn2}" id="tableColumn2"
																		valign="middle" width="10">
																		<ui:radioButton binding="#{compras$ABMLicitacion$ABMLicitacion.radioButton2}" id="radioButton2" label=""
																			name="buttonGroup2" selected="#{compras$ABMLicitacion$ABMLicitacion.RBSelected2}"
																			selectedValue="#{compras$ABMLicitacion$ABMLicitacion.currentRow2}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{compras$ABMLicitacion$ABMLicitacion.tcRazonSocial}" headerText="Razón Social" id="tcRazonSocial"
																		sort="razonSocial">
																		<ui:staticText binding="#{compras$ABMLicitacion$ABMLicitacion.stRazonSocial}" id="stRazonSocial"
																			text="#{currentRow2.value['razonSocial']}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{compras$ABMLicitacion$ABMLicitacion.tcCuit}" headerText="CUIT" id="tcCuit" sort="cuit">
																		<ui:staticText binding="#{compras$ABMLicitacion$ABMLicitacion.stCuit}" id="stCuit" text="#{currentRow2.value['cuit']}" />
																	</ui:tableColumn>
																</ui:tableRowGroup>
																<f:facet name="actionsTop">
																	<ui:panelGroup binding="#{compras$ABMLicitacion$ABMLicitacion.groupPanel2}" id="groupPanel2">
																		<ui:button action="#{compras$ABMLicitacion$ABMLicitacion.btnAgregarProveedor_action}"
																			binding="#{compras$ABMLicitacion$ABMLicitacion.btnAgregarProveedor}" id="btnAgregarProveedor" styleClass="button"
																			text="Agregar" />
																		<a4j:commandButton action="#{compras$ABMLicitacion$ABMLicitacion.btnQuitarProveedor_action}"
																			binding="#{compras$ABMLicitacion$ABMLicitacion.btnQuitarProveedor}" id="btnQuitarProveedor" value="Quitar"
																			styleClass="btnAjax" reRender="table2" onmousedown="reemplazarClickConConfirmacion(this, '');" />
																		<ui:staticText binding="#{compras$ABMLicitacion$ABMLicitacion.stSeparador3}" escape="false" id="stSeparador3"
																			text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
																		<a4j:commandButton action="#{compras$ABMLicitacion$ABMLicitacion.btnQuitarTodosProveedor_action}"
																			binding="#{compras$ABMLicitacion$ABMLicitacion.btnQuitarTodosProveedor}" id="btnQuitarTodosProveedor"
																			value="Quitar todos" styleClass="btnAjax" reRender="table2"
																			onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea quitar todos los elementos de esta lista?');" />
																	</ui:panelGroup>
																</f:facet>
															</ui:table>
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMLicitacion$ABMLicitacion.lblComentarios}" id="lblComentarios" styleClass="label"
																text="Comentarios" />
														</td>
														<td>
															<ui:textArea binding="#{compras$ABMLicitacion$ABMLicitacion.taComentarios}" columns="50" id="taComentarios" rows="5"
																styleClass="textField" />
														</td>
														<td>
															<ui:button action="#{compras$ABMLicitacion$ABMLicitacion.btnPresupuesto_action}"
																binding="#{compras$ABMLicitacion$ABMLicitacion.btnPresupuesto}" id="btnPresupuesto" styleClass="button" text="Presupuesto"
																onClick="newWindow = window.open('/Vipians/faces/DescargarArchivoServlet', 'Reportes Presupuesto')" />
														</td>
													</tr>
												</table>
											</ui:tab>
											<ui:tab id="two" text="2 - Datos">
												<table>
													<tr>
														<td>
															<br />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMLicitacion$ABMLicitacion.lblNumero}" for="tfNumero" id="lblNumero" styleClass="label"
																text="Número" />
														</td>
														<td>
															<ui:textField binding="#{compras$ABMLicitacion$ABMLicitacion.tfNumero}" columns="16" id="tfNumero" styleClass="textField"
																disabled="true" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMLicitacion$ABMLicitacion.lblTipoLicitacion}" id="lblTipoLicitacion" styleClass="label"
																text="Tipo de Contratación" />
														</td>
														<td nowrap="nowrap">
															<ui:dropDown binding="#{compras$ABMLicitacion$ABMLicitacion.ddTipoLicitacion}" id="ddTipoLicitacion"
																items="#{compras$ABMLicitacion$ABMLicitacion.ddTipoLicitacionDefaultOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMLicitacion$ABMLicitacion.lblObjeto}" for="tfObjeto" id="lblObjeto" styleClass="label"
																text="Objeto" />
														</td>
														<td colspan="3">
															<ui:textArea binding="#{compras$ABMLicitacion$ABMLicitacion.tfObjeto}" columns="85" id="taObjeto" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMLicitacion$ABMLicitacion.lblPresupuestoOficial}" for="tfPresupuestoOficial"
																id="lblPresupuestoOficial" styleClass="label" text="Presupuesto Oficial" />
														</td>
														<td>
															<ui:textField binding="#{compras$ABMLicitacion$ABMLicitacion.tfPresupuestoOficial}"
																onKeyPress="return ValidarFloat(event,this)" columns="10" id="tfPresupuestoOficial" styleClass="textField" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMLicitacion$ABMLicitacion.lblDigestoMunicipalLicitacion}" for="tfDigestoMunicipalLicitacion"
																id="lblDigestoMunicipalLicitacion" styleClass="label" text="Digesto Municipal" />
														</td>
														<td>
															<ui:textField binding="#{compras$ABMLicitacion$ABMLicitacion.tfDigestoMunicipalLicitacion}" columns="30" disabled="true"
																id="tfDigestoMunicipalLicitacion" maxLength="10" styleClass="textField" />
															<ui:button action="#{compras$ABMLicitacion$ABMLicitacion.btnSeleccionarDigestoMunicipalLicitacion_action}"
																binding="#{compras$ABMLicitacion$ABMLicitacion.btnSeleccionarDigestoMunicipalLicitacion}" escape="false"
																id="btnSeleccionarDigestoMunicipalLicitacion" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;"
																toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarDigestoMunicipalLicitacion" reRender="form1:tfDigestoMunicipalLicitacion" title="Limpiar"
																binding="#{compras$ABMLicitacion$ABMLicitacion.btnLimpiarDigestoMunicipalLicitacion}"
																action="#{compras$ABMLicitacion$ABMLicitacion.btnLimpiarDigestoMunicipalLicitacion_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMLicitacion$ABMLicitacion.lblPlazoMantenimientoOferta}" for="tfPlazoMantenimientoOferta"
																id="lblPlazoMantenimientoOferta" styleClass="label" text="Mantenimiento de la oferta" />
														</td>
														<td>
															<ui:textField binding="#{compras$ABMLicitacion$ABMLicitacion.tfPlazoMantenimientoOferta}" columns="10"
																id="tfPlazoMantenimientoOferta" styleClass="textField" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMLicitacion$ABMLicitacion.lblExtensionMantenimientoOferta}"
																for="tfExtensionMantenimientoOferta" id="lblExtensionMantenimientoOferta" styleClass="label" text="Extensión de la oferta" />
														</td>
														<td>
															<ui:textField binding="#{compras$ABMLicitacion$ABMLicitacion.tfExtensionMantenimientoOferta}" columns="30"
																id="tfExtensionMantenimientoOferta" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMLicitacion$ABMLicitacion.lblFechaEntrega}" for="tfFechaEntrega" id="lblFechaEntrega"
																styleClass="label" text="Fecha Entrega" />
														</td>
														<td>
															<ui:textField binding="#{compras$ABMLicitacion$ABMLicitacion.tfFechaEntrega}" columns="20" id="tfFechaEntrega"
																styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
														</td>
													</tr>
													<tr>
														<!--          <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{compras$ABMLicitacion$ABMLicitacion.lblEstadoLicitacion}" id="lblEstadoLicitacion" styleClass="label" text="Estado de Licitación"/>
                                        </td>
                                       <td nowrap="nowrap">
                                            <ui:dropDown binding="#{compras$ABMLicitacion$ABMLicitacion.ddEstadoLicitacion}" id="ddEstadoLicitacion"
                                                         items="#{compras$ABMLicitacion$ABMLicitacion.ddEstadoLicitacionDefaultOptions.options}" styleClass="textField"/>
                                        </td> -->
													</tr>
													<tr>
														<td colspan="4">
															<hr />
															<br />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMLicitacion$ABMLicitacion.lblFechaPublicacion}" for="tfFechaPublicacion"
																id="lblFechaPublicacion" styleClass="label" text="Fecha de Publicación" />
														</td>
														<td>
															<ui:textField binding="#{compras$ABMLicitacion$ABMLicitacion.tfFechaPublicacion}" id="tfFechaPublicacion"
																styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
															<!--<ui:staticText binding="#{compras$ABMLicitacion$ABMLicitacion.stFechaPublicacion}" escape="false"
                                                               id="stFechaPublicacion" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMLicitacion$ABMLicitacion.lblFechaCierre}" for="tfFechaCierre" id="lblFechaCierre"
																styleClass="label" text="Fecha de Cierre" />
														</td>
														<td>
															<ui:textField binding="#{compras$ABMLicitacion$ABMLicitacion.tfFechaCierre}" id="tfFechaCierre" styleClass="textField"
																onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
															<!--<ui:staticText binding="#{compras$ABMLicitacion$ABMLicitacion.stFechaCierre}" escape="false"
                                                               id="stFechaCierre" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMLicitacion$ABMLicitacion.lblHoraCierre}" for="tfHoraCierre" id="lblHoraCierre"
																styleClass="label" text="Hora de Cierre" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{compras$ABMLicitacion$ABMLicitacion.tfHoraCierre}" onKeyPress="return ValidarNum(event,this)"
																id="tfHoraCierre" styleClass="textField" maxLength="2" columns="2" />
															<ui:staticText text=" : " />
															<ui:textField binding="#{compras$ABMLicitacion$ABMLicitacion.tfMinutoCierre}" onKeyPress="return ValidarNum(event,this)"
																id="tfMinutoCierre" styleClass="textField" maxLength="2" columns="2" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMLicitacion$ABMLicitacion.lblFechaAperturaSobres}" for="tfFechaAperturaSobres"
																id="lblFechaAperturaSobres" styleClass="label" text="Fecha de Apertura de Sobres" />
														</td>
														<td>
															<ui:textField binding="#{compras$ABMLicitacion$ABMLicitacion.tfFechaAperturaSobres}" id="tfFechaAperturaSobres"
																styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
															<!--<ui:staticText binding="#{compras$ABMLicitacion$ABMLicitacion.stFechaAperturaSobres}" escape="false"
                                                               id="stFechaAperturaSobres" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMLicitacion$ABMLicitacion.lblHoraAperturaSobres}" for="tfHoraAperturaSobres"
																id="lblHoraAperturaSobres" styleClass="label" text="Hora de Apertura de sobres" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{compras$ABMLicitacion$ABMLicitacion.tfHoraAperturaSobres}"
																onKeyPress="return ValidarNum(event,this)" id="tfHoraAperturaSobres" styleClass="textField" maxLength="2" columns="2" />
															<ui:staticText text=" : " />
															<ui:textField binding="#{compras$ABMLicitacion$ABMLicitacion.tfMinutoAperturaSobres}"
																onKeyPress="return ValidarNum(event,this)" id="tfMinutoAperturaSobres" styleClass="textField" maxLength="2" columns="2" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<hr />
															<br />
														</td>
													</tr>
												</table>
											</ui:tab>
											<ui:tab id="three" text="3 - Acta / Presupuestos">
												<table>
													<tr>
														<td>
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:staticText binding="#{compras$ABMLicitacion$ABMLicitacion.stOfertasContratacion}" id="stOfertasContratacion"
																styleClass="label2" text="Ofertas / Presupuestos" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table augmentTitle="false" binding="#{compras$ABMLicitacion$ABMLicitacion.table3}" id="table3" width="479">
																<script>
																	<![CDATA[
																	/* ----- Functions for Table Preferences Panel ----- */
																	/*
																	 * Toggle the table preferences panel open or closed
																	 */
																	function togglePreferencesPanel() {
																		var table = document.getElementById("form1:table3");
																		table.toggleTblePreferencesPanel();
																	}
																	/* ----- Functions for Filter Panel ----- */
																	/*
																	 * Return true if the filter menu has actually changed,
																	 * so the corresponding event should be allowed to continue.
																	 */
																	function filterMenuChanged() {
																		var table = document.getElementById("form1:table3");
																		return table.filterMenuChanged();
																	}
																	/*
																	 * Toggle the custom filter panel (if any) open or closed.
																	 */
																	function toggleFilterPanel() {
																		var table = document.getElementById("form1:table3");
																		return table.toggleTableFilterPanel();
																	}
																	/* ----- Functions for Table Actions ----- */
																	/*
																	 * Initialize all rows of the table when the state
																	 * of selected rows changes.
																	 */
																	function initAllRows() {
																		var table = document.getElementById("form1:table3");
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
																		var table = document.getElementById("form1:table3");
																		table.selectGroupRows(rowGroupId, selected);
																	}
																	/*
																	 * Disable all table actions if no rows have been selected.
																	 */
																	function disableActions() {
																		// Determine whether any rows are currently selected
																		var table = document.getElementById("form1:table3");
																		var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
																		// Set disabled state for top actions
																		document.getElementById("form1:table3:tableActionsTop:deleteTop")
																				.setDisabled(disabled);
																		// Set disabled state for bottom actions
																		document.getElementById("form1:table3:tableActionsBottom:deleteBottom")
																				.setDisabled(disabled);
																	}
																	]]>
																</script>
																<ui:tableRowGroup binding="#{compras$ABMLicitacion$ABMLicitacion.tableRowGroup3}" id="tableRowGroup3"
																	sourceData="#{compras$ABMLicitacion$ABMLicitacion.ldpOfertasContratacion}" sourceVar="currentRow3">
																	<ui:tableColumn align="center" binding="#{compras$ABMLicitacion$ABMLicitacion.tableColumn3}" id="tableColumn3"
																		onClick="setTimeout('initAllRows()', 0)" valign="middle" width="10">
																		<ui:radioButton binding="#{compras$ABMLicitacion$ABMLicitacion.radioButton3}" id="radioButton3" label=""
																			name="buttonGroup3" selected="#{compras$ABMLicitacion$ABMLicitacion.RBSelected3}"
																			selectedValue="#{compras$ABMLicitacion$ABMLicitacion.currentRow3}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{compras$ABMLicitacion$ABMLicitacion.tcProveedor}" headerText="Proveedor" id="tcProveedor"
																		sort="proveedor">
																		<ui:staticText binding="#{compras$ABMLicitacion$ABMLicitacion.stProveedor}" id="stProveedor"
																			text="#{currentRow3.value['proveedor']}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{compras$ABMLicitacion$ABMLicitacion.tcPlazo}" headerText="Plazo de mantenimiento" id="tcPlazo"
																		sort="plazo">
																		<ui:staticText binding="#{compras$ABMLicitacion$ABMLicitacion.stPlazo}" id="stPlazo" text="#{currentRow3.value['plazo']}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{compras$ABMLicitacion$ABMLicitacion.tcTotalPresupuestado}" headerText="Total presupuestado"
																		id="tcTotalPresupuestado" sort="total">
																		<ui:staticText binding="#{compras$ABMLicitacion$ABMLicitacion.stTotalPresupuestado}" id="stTotalPresupuestado"
																			text="#{currentRow3.value['total']}" />
																	</ui:tableColumn>
																</ui:tableRowGroup>
																<f:facet name="actionsTop">
																	<ui:panelGroup binding="#{compras$ABMLicitacion$ABMLicitacion.groupPanel3}" id="groupPanel3">
																		<ui:button action="#{compras$ABMLicitacion$ABMLicitacion.btnAgregarOfertaContratacion_action}"
																			binding="#{compras$ABMLicitacion$ABMLicitacion.btnAgregarOfertaContratacion}" id="btnAgregarOfertaContratacion"
																			styleClass="button" text="Agregar" />
																		<ui:button action="#{compras$ABMLicitacion$ABMLicitacion.btnModificarOfertaContratacion_action}"
																			binding="#{compras$ABMLicitacion$ABMLicitacion.btnModificarOfertaContratacion}" id="btnModificarOfertaContratacion"
																			styleClass="button" text="Modificar" />
																		<ui:button action="#{compras$ABMLicitacion$ABMLicitacion.btnConsultarOfertaContratacion_action}"
																			binding="#{compras$ABMLicitacion$ABMLicitacion.btnConsultarOfertaContratacion}" id="btnConsultarOfertaContratacion"
																			styleClass="button" text="Consultar" />
																		<a4j:commandButton action="#{compras$ABMLicitacion$ABMLicitacion.btnQuitarOfertaContratacion_action}"
																			binding="#{compras$ABMLicitacion$ABMLicitacion.btnQuitarOfertaContratacion}" id="btnQuitarOfertaContratacion"
																			value="Quitar" styleClass="btnAjax" reRender="table3" onmousedown="reemplazarClickConConfirmacion(this, '');" />
																		<ui:staticText binding="#{compras$ABMLicitacion$ABMLicitacion.stSeparador4}" escape="false" id="stSeparador4"
																			text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
																		<a4j:commandButton action="#{compras$ABMLicitacion$ABMLicitacion.btnQuitarTodosOfertaContratacion_action}"
																			binding="#{compras$ABMLicitacion$ABMLicitacion.btnQuitarTodosOfertaContratacion}" id="btnQuitarTodosOfertaContratacion"
																			value="Quitar todos" styleClass="btnAjax" reRender="table3"
																			onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea quitar todos los elementos de esta lista?');" />
																	</ui:panelGroup>
																</f:facet>
															</ui:table>
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
															<hr />
															<br />
														</td>
													</tr>
													<tr>
														<td align="left" nowrap="true" colspan="4">
															<ui:staticText escape="false" id="stActaAperturas" styleClass="textoFiltrarPor" text="Acta de Apertura" />
														</td>
													</tr>
													<tr>
														<td>
															<br />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{compras$ABMLicitacion$ABMLicitacion.lblLugar}" id="lblLugar" styleClass="label" text="Lugar" />
														</td>
														<td nowrap="true">
															<ui:textField binding="#{compras$ABMLicitacion$ABMLicitacion.tfLugar}" columns="40" id="tfLugar" styleClass="textField" />
														</td>
														<!--                                            <td align="right" nowrap="true">
                                                <ui:label binding="#{compras$ABMLicitacion$ABMLicitacion.lblFechaApertura}" for="tfFechaApertura" id="lblFechaApertura" styleClass="label" text="Fecha de apertura"/>
                                            </td>
                                            <td nowrap="true">
                                                <ui:textField binding="#{compras$ABMLicitacion$ABMLicitacion.tfFechaApertura}" columns="40" id="tfFechaApertura" styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10"/>
                                            </td>-->
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{compras$ABMLicitacion$ABMLicitacion.lblRegistroEscrito}" for="taRegistroEscrito" id="lblRegistroEscrito"
																styleClass="label" text="Registro escrito" />
														</td>
														<td nowrap="true" colspan="3">
															<ui:textArea binding="#{compras$ABMLicitacion$ABMLicitacion.taRegistroEscrito}" columns="104" id="taRegistroEscrito"
																styleClass="textField" rows="8" />
														</td>
													</tr>
													<tr>
														<td>
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:staticText binding="#{compras$ABMLicitacion$ABMLicitacion.stRepresentantes}" id="stRepresentantes" styleClass="label2"
																text="Representantes" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table augmentTitle="false" binding="#{compras$ABMLicitacion$ABMLicitacion.table4}" id="table4" width="479">
																<script>
																	<![CDATA[
																	/* ----- Functions for Table Preferences Panel ----- */
																	/*
																	 * Toggle the table preferences panel open or closed
																	 */
																	function togglePreferencesPanel() {
																		var table = document.getElementById("form1:table4");
																		table.toggleTblePreferencesPanel();
																	}
																	/* ----- Functions for Filter Panel ----- */
																	/*
																	 * Return true if the filter menu has actually changed,
																	 * so the corresponding event should be allowed to continue.
																	 */
																	function filterMenuChanged() {
																		var table = document.getElementById("form1:table4");
																		return table.filterMenuChanged();
																	}
																	/*
																	 * Toggle the custom filter panel (if any) open or closed.
																	 */
																	function toggleFilterPanel() {
																		var table = document.getElementById("form1:table4");
																		return table.toggleTableFilterPanel();
																	}
																	/* ----- Functions for Table Actions ----- */
																	/*
																	 * Initialize all rows of the table when the state
																	 * of selected rows changes.
																	 */
																	function initAllRows() {
																		var table = document.getElementById("form1:table4");
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
																		var table = document.getElementById("form1:table4");
																		table.selectGroupRows(rowGroupId, selected);
																	}
																	/*
																	 * Disable all table actions if no rows have been selected.
																	 */
																	function disableActions() {
																		// Determine whether any rows are currently selected
																		var table = document.getElementById("form1:table4");
																		var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
																		// Set disabled state for top actions
																		document.getElementById("form1:table4:tableActionsTop:deleteTop")
																				.setDisabled(disabled);
																		// Set disabled state for bottom actions
																		document.getElementById("form1:table4:tableActionsBottom:deleteBottom")
																				.setDisabled(disabled);
																	}
																	]]>
																</script>
																<ui:tableRowGroup binding="#{compras$ABMLicitacion$ABMLicitacion.tableRowGroup4}" id="tableRowGroup4"
																	sourceData="#{compras$ABMLicitacion$ABMLicitacion.ldpRepresentantesActaApertura}" sourceVar="currentRow4">
																	<ui:tableColumn align="center" binding="#{compras$ABMLicitacion$ABMLicitacion.tableColumn4}" id="tableColumn4"
																		valign="middle" width="10">
																		<ui:radioButton binding="#{compras$ABMLicitacion$ABMLicitacion.radioButton4}" id="radioButton4" label=""
																			name="buttonGroup4" selected="#{compras$ABMLicitacion$ABMLicitacion.RBSelected4}"
																			selectedValue="#{compras$ABMLicitacion$ABMLicitacion.currentRow4}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{compras$ABMLicitacion$ABMLicitacion.tcPersona}" headerText="Persona" id="tcPersona"
																		sort="toStringCompleto">
																		<ui:staticText binding="#{compras$ABMLicitacion$ABMLicitacion.stPersona}" id="stPersona"
																			text="#{currentRow4.value['persona']}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{compras$ABMLicitacion$ABMLicitacion.tcCargo}" headerText="Cargo" id="tcCargo"
																		sort="toStringCompleto">
																		<ui:textField binding="#{compras$ABMLicitacion$ABMLicitacion.tfCargoRepresentante}" id="tfCargoRepresentante"
																			text="#{currentRow4.value['cargo']}" />
																	</ui:tableColumn>
																</ui:tableRowGroup>
																<f:facet name="actionsTop">
																	<ui:panelGroup binding="#{compras$ABMLicitacion$ABMLicitacion.groupPanel4}" id="groupPanel4">
																		<ui:button action="#{compras$ABMLicitacion$ABMLicitacion.btnAgregarRepresentante_action}"
																			binding="#{compras$ABMLicitacion$ABMLicitacion.btnAgregarRepresentante}" id="btnAgregarRepresentante" styleClass="button"
																			text="Agregar" />
																		<a4j:commandButton action="#{compras$ABMLicitacion$ABMLicitacion.btnQuitarRepresentante_action}"
																			binding="#{compras$ABMLicitacion$ABMLicitacion.btnQuitarRepresentante}" id="btnQuitarRepresentante" value="Quitar"
																			styleClass="btnAjax" reRender="table4" onmousedown="reemplazarClickConConfirmacion(this, '');" />
																		<ui:staticText binding="#{compras$ABMLicitacion$ABMLicitacion.stSeparador5}" escape="false" id="stSeparador5"
																			text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
																		<a4j:commandButton action="#{compras$ABMLicitacion$ABMLicitacion.btnQuitarTodosRepresentante_action}"
																			binding="#{compras$ABMLicitacion$ABMLicitacion.btnQuitarTodosRepresentante}" id="btnQuitarTodosRepresentantes"
																			value="Quitar todos" styleClass="btnAjax" reRender="table4"
																			onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea quitar todos los elementos de esta lista?');" />
																	</ui:panelGroup>
																</f:facet>
															</ui:table>
														</td>
													</tr>
												</table>
											</ui:tab>
											<ui:tab id="four" text="4 - Adjudicación">
												<table>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td>
															<ui:panelGroup id="pgAdjudicarProveedor" binding="#{compras$ABMLicitacion$ABMLicitacion.gpAdjudicarProveedor}">
																<tr>
																	<td align="right" nowrap="nowrap">
																		<ui:label binding="#{compras$ABMLicitacion$ABMLicitacion.lblProveedorAdjudicacion}" for="tfProveedorAdjudicacion"
																			id="lblProveedorAdjudicacion" styleClass="label" text="Adjudicar a" />
																	</td>
																	<td>
																		<ui:textField binding="#{compras$ABMLicitacion$ABMLicitacion.tfProveedorAdjudicacion}" columns="30" disabled="true"
																			id="tfProveedorAdjudicacion" maxLength="10" styleClass="textField" />
																		<ui:button action="#{compras$ABMLicitacion$ABMLicitacion.btnSeleccionarProveedor_action}"
																			binding="#{compras$ABMLicitacion$ABMLicitacion.btnSeleccionarProveedor}" escape="false" id="btnSeleccionarProveedor"
																			mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
																		<a4j:commandButton id="btnLimpiarProveedor" reRender="form1:tfProveedorAdjudicacion" title="Limpiar"
																			binding="#{compras$ABMLicitacion$ABMLicitacion.btnLimpiarProveedor}"
																			action="#{compras$ABMLicitacion$ABMLicitacion.btnLimpiarProveedor_action}" styleClass="buttonLimpiarAjax" />
																	</td>
																</tr>
																<tr>
																	<td colspan="4">
																		<div class="notifABM clrNota">
																			<b>Nota: </b> Si adjudica directamente a un proveedor, se tomarán los Precios Unitarios Referenciales como válidos.
																		</div>
																	</td>
																</tr>
															</ui:panelGroup>
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMLicitacion$ABMLicitacion.lblDigestoMunicipalLicitacion2}" for="tfDigestoMunicipalLicitacion2"
																id="lblDigestoMunicipalLicitacion2" styleClass="label" text="Digesto Municipal que adjudica" />
														</td>
														<td>
															<ui:textField binding="#{compras$ABMLicitacion$ABMLicitacion.tfDigestoMunicipalLicitacion2}" columns="30" disabled="true"
																id="tfDigestoMunicipalLicitacion2" maxLength="10" styleClass="textField" />
															<ui:button action="#{compras$ABMLicitacion$ABMLicitacion.btnSeleccionarDigestoMunicipalLicitacion_action}"
																binding="#{compras$ABMLicitacion$ABMLicitacion.btnSeleccionarDigestoMunicipalLicitacion2}" escape="false"
																id="btnSeleccionarDigestoMunicipalLicitacion2" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;"
																toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarDigestoMunicipalLicitacion2" reRender="form1:tfDigestoMunicipalLicitacion2" title="Limpiar"
																binding="#{compras$ABMLicitacion$ABMLicitacion.btnLimpiarDigestoMunicipalLicitacion2}"
																action="#{compras$ABMLicitacion$ABMLicitacion.btnLimpiarDigestoMunicipalLicitacion_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td>
															<br />
														</td>
													</tr>
													<tr>
														<td>
															<ui:label binding="#{compras$ABMLicitacion$ABMLicitacion.lblLineasContratacionAdj}" id="lblLineasContratacionAdj"
																styleClass="label2" text="Líneas de la Contratación" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMLicitacion$ABMLicitacion.lblAdjudicarTodas}"
																rendered="#{compras$ABMLicitacion$ABMLicitacion.hayOfertas}" for="ddAdjudicarTodas" id="lblAdjudicarTodas"
																styleClass="label" text="Adjudicar todo a" />
														</td>
														<td>
															<ui:dropDown binding="#{compras$ABMLicitacion$ABMLicitacion.ddAdjudicarTodas}"
																rendered="#{compras$ABMLicitacion$ABMLicitacion.hayOfertas}"
																items="#{compras$ABMLicitacion$ABMLicitacion.ddProveedoresDefaultOptions.options}" id="ddAdjudicarTodas"
																toolTip="Adjudica todas las Lineas a un Proveedor" onChange="this.form.submit()"
																valueChangeListener="#{compras$ABMLicitacion$ABMLicitacion.valueChangeEvent(event)}" />
														</td>
													</tr>
													<tr>
														<td colspan="4" style="height: 51px">
															<ui:table augmentTitle="false" binding="#{compras$ABMLicitacion$ABMLicitacion.table5}" sortPanelToggleButton="false"
																clearSortButton="false" id="table5" width="240">
																<script>
																	<![CDATA[
																	/* ----- Functions for Table Preferences Panel ----- */
																	/*
																	 * Toggle the table preferences panel open or closed
																	 */
																	function togglePreferencesPanel() {
																		var table = document.getElementById("form1:table5");
																		table.toggleTblePreferencesPanel();
																	}
																	/* ----- Functions for Filter Panel ----- */
																	/*
																	 * Return true if the filter menu has actually changed,
																	 * so the corresponding event should be allowed to continue.
																	 */
																	function filterMenuChanged() {
																		var table = document.getElementById("form1:table5");
																		return table.filterMenuChanged();
																	}
																	/*
																	 * Toggle the custom filter panel (if any) open or closed.
																	 */
																	function toggleFilterPanel() {
																		var table = document.getElementById("form1:table5");
																		return table.toggleTableFilterPanel();
																	}
																	/* ----- Functions for Table Actions ----- */
																	/*
																	 * Initialize all rows of the table when the state
																	 * of selected rows changes.
																	 */
																	function initAllRows() {
																		var table = document.getElementById("form1:table5");
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
																		var table = document.getElementById("form1:table5");
																		table.selectGroupRows(rowGroupId, selected);
																	}
																	/*
																	 * Disable all table actions if no rows have been selected.
																	 */
																	function disableActions() {
																		// Determine whether any rows are currently selected
																		var table = document.getElementById("form1:table5");
																		var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
																		// Set disabled state for top actions
																		document.getElementById("form1:table5:tableActionsTop:deleteTop")
																				.setDisabled(disabled);
																		// Set disabled state for bottom actions
																		document.getElementById("form1:table5:tableActionsBottom:deleteBottom")
																				.setDisabled(disabled);
																	}
																	]]>
																</script>
																<ui:tableRowGroup binding="#{compras$ABMLicitacion$ABMLicitacion.tableRowGroup5}" id="tableRowGroup5"
																	sourceData="#{compras$ABMLicitacion$ABMLicitacion.ldpLineasContratacion}" sourceVar="currentRow5">
																	<ui:tableColumn binding="#{compras$ABMLicitacion$ABMLicitacion.tcBienAdj}" headerText="Bien" id="tcBienAdj" sort="bien">
																		<ui:staticText binding="#{compras$ABMLicitacion$ABMLicitacion.stBienAdj}" id="stBienAdj"
																			text="#{currentRow5.value['bien']}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{compras$ABMLicitacion$ABMLicitacion.tcCantidadAdj}" headerText="Cantidad" id="tcCantidadAdj"
																		sort="cantidad">
																		<ui:staticText binding="#{compras$ABMLicitacion$ABMLicitacion.stCantidadAdj}" id="stCantidadAdj"
																			text="#{currentRow5.value['cantidad']}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{compras$ABMLicitacion$ABMLicitacion.tcPrecioUnitReferencialAdj}"
																		headerText="Precio Unitario Referencial" id="tcPrecioUnitReferencialAdj" sort="valorReferencial" width="40">
																		<ui:staticText binding="#{compras$ABMLicitacion$ABMLicitacion.stPrecioUnitarioReferencialAdj}"
																			id="stPrecioUnitarioReferencialAdj" text="#{currentRow5.value['valorReferencial']}" />
																	</ui:tableColumn>
																	<ui:tableColumn binding="#{compras$ABMLicitacion$ABMLicitacion.tcOfertasContratacion}"
																		rendered="#{compras$ABMLicitacion$ABMLicitacion.hayOfertas}" headerText="Se adjudica a" id="tcOfertasContratacion"
																		width="40">
																		<ui:hiddenField value="#{currentRow5.value['bien']}" disabled="true"
																			binding="#{compras$ABMLicitacion$ABMLicitacion.hiddenField}" />
																		<ui:dropDown binding="#{compras$ABMLicitacion$ABMLicitacion.ddOfertasContratacion}" id="ddOfertasContratacion"
																			items="#{compras$ABMLicitacion$ABMLicitacion.opcionesAdjudicables}"
																			selected="#{compras$ABMLicitacion$ABMLicitacion.selected}" />
																	</ui:tableColumn>
																</ui:tableRowGroup>
															</ui:table>
														</td>
													</tr>
												</table>
											</ui:tab>
										</ui:tabSet>
									</td>
								</tr>
							</tbody>
							<tr>
								<td colspan="4">
									<br />
								</td>
							</tr>
							<tr>
								<td align="right">
									<ui:label binding="#{compras$ABMLicitacion$ABMLicitacion.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
								</td>
								<td>
									<ui:textArea binding="#{compras$ABMLicitacion$ABMLicitacion.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
								</td>
							</tr>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<ui:table binding="#{compras$ABMLicitacion$ABMLicitacion.tablaLogs}" id="tbLogsAuditoria" />
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<a4j:outputPanel ajaxRendered="true">
										<ui:messageGroup binding="#{compras$ABMLicitacion$ABMLicitacion.messageGroup}" id="messageGroup" styleClass="grupoMsg" />
									</a4j:outputPanel>
								</td>
							</tr>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{compras$ABMLicitacion$ABMLicitacion.btnGuardar_action}"
											binding="#{compras$ABMLicitacion$ABMLicitacion.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{compras$ABMLicitacion$ABMLicitacion.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{compras$ABMLicitacion$ABMLicitacion.btnCancelar_action}"
											binding="#{compras$ABMLicitacion$ABMLicitacion.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{compras$ABMLicitacion$ABMLicitacion.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMLicitacion$ABMLicitacion.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMLicitacion$ABMLicitacion.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMLicitacion$ABMLicitacion.idSubSesion}" />
					<ui:script binding="#{compras$ABMLicitacion$ABMLicitacion.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
