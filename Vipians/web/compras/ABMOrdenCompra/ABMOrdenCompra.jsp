<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.page1}" id="page1">
			<ui:html binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.html1}" id="html1">
			<ui:head binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.head1}" id="head1">
				<ui:link binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
                        
                        function calcular(input){
                            var locParentNode = input.parentNode;
                            var fila = locParentNode.parentNode;
                            
                            var cantidad = parseFloat(fila.cells[2].childNodes[0].innerText);
                            var precioUnitario = input.value;
                            fila.cells[4].childNodes[0].innerText = cantidad * precioUnitario;
                            
                            calcularTotal();
                            
                        }
                            
                        function calcularTotal(){
                            
                            var filas = document.getElementById("form1:table2").rows;
                            //alert(filas);
                            //alert(filas.length);
                            
                            var acum = 0;
                            
                            for(var i = 2; i < filas.length ;i++) // el for empieza de 2 xq fila 0 y 1 son para nombre de la tabla y los botones
                            {
                                //alert("i = " + i);
                                //alert(filas[i].cells[4].childNodes[0]);
                                acum += isNan(filas[i].cells[4].childNodes[0].innerHTML) ? 0 : parseFloat(filas[i].cells[4].childNodes[0].innerHTML);
                                alert("acum = " + acum);
                            }
                            document.getElementById("form1:stTotal").innerText = acum;
                            //var stTotal = document.getElementById("form1:stTotal");
                            //stTotal.innerText = acum;
                        }
                    ]]></script>
			</ui:head>
			<ui:body binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.body1}" focus="form1:btnSeleccionarProveedor" id="body1"
				onLoad="parent.footer.location.reload(); Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{compras$ABMOrdenCompra$ABMOrdenCompra.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.lblNumero}" for="tfNumero" id="lblNumero" styleClass="label" text="Nº" />
									</td>
									<td>
										<ui:textField binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tfNumero}" columns="25" disabled="true"
											styleClass="textFieldDisabled" id="tfNumero" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.lblProveedor}" for="tfProveedor" id="lblProveedor" styleClass="label"
											text="Proveedor" />
									</td>
									<td>
										<ui:textField binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tfProveedor}" columns="40" disabled="true" styleClass="textField"
											id="tfProveedor" />
										<ui:button action="#{compras$ABMOrdenCompra$ABMOrdenCompra.btnSeleccionarProveedor_action}"
											binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.btnSeleccionarProveedor}" escape="false" id="btnSeleccionarProveedor"
											mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarProveedor" reRender="form1:tfProveedor" title="Limpiar"
											binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.btnLimpiarProveedor}"
											action="#{compras$ABMOrdenCompra$ABMOrdenCompra.btnLimpiarProveedor_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.lblDescripcion}" id="lblDescripcion" styleClass="label"
											text="Descripción" />
									</td>
									<td nowrap="nowrap">
										<ui:textArea binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.taDescripcion}" columns="40" id="taDescripcion"
											styleClass="textField" rows="5" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.lblFecha}" for="tfFecha" id="lblFecha" style="" styleClass="label"
											text="Fecha" />
									</td>
									<td>
										<ui:textField binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tfFecha}" id="tfFecha" onKeyUp="mascara(this,'/',patronFecha,true)"
											maxLength="10" styleClass="textField" />
										<!--<ui:staticText binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.staticText5}" escape="false" id="staticText5"
                                                           styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
									</td>
								</tr>
								<tr>
									<td colspan="4" style="height: 20px">
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:label binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.lblLineaOrdenCompra}" id="lblLineaOrdenCompra" styleClass="label2"
											text="Líneas de la Orden de Compra" />
										<!-- Solicitudes de Suministro: Agregar primero una solicitud de suministro -->
									</td>
								</tr>
								<tr>
									<td colspan="4" style="height: 51px">
										<ui:table augmentTitle="false" binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.table2}" id="table2" width="240">
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
                                                        document.getElementById("form1:table2:tableActionsTop:deleteTop").setDisabled(disabled);
                                                        // Set disabled state for bottom actions
                                                        document.getElementById("form1:table2:tableActionsBottom:deleteBottom").setDisabled(disabled);
                                                }]]></script>
											<ui:tableRowGroup binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tableRowGroup2}" id="tableRowGroup2"
												sourceData="#{compras$ABMOrdenCompra$ABMOrdenCompra.ldpLineasOrdenCompra}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tableColumn2}" id="tableColumn2" valign="middle"
													width="10">
													<ui:radioButton binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.radioButton2}" id="radioButton2" label="" name="buttonGroup"
														selected="#{compras$ABMOrdenCompra$ABMOrdenCompra.RBSelected}"
														selectedValue="#{compras$ABMOrdenCompra$ABMOrdenCompra.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tcBien2}" headerText="Bien" id="tcBien2" sort="bien">
													<ui:staticText binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.stBien2}" id="stBien2" text="#{currentRow.value['bien']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tcCantidad2}" headerText="Cantidad" id="tcCantidad2"
													sort="cantidad">
													<ui:staticText binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.stCantidad2}" id="stCantidad2"
														text="#{currentRow.value['cantidad']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tcPrecioUnit}" headerText="Precio Unitario" id="tcPrecioUnit"
													sort="montoUnitario" width="40">
													<ui:textField binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tfPrecioUnitario}" id="tfPrecioUnitario"
														text="#{currentRow.value['montoUnitario']}" onBlur="calcular(this)" onKeyPress="return ValidarFloat(event,this)" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tcPrecioTotal}" headerText="Precio Total" id="tcPrecioTotal"
													sort="montoTotal" width="40">
													<ui:staticText binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.stPrecioTotal}" id="stPrecioTotal"
														text="#{currentRow.value['montoTotal']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.groupPanel1}" id="groupPanel1">
													<ui:button action="#{compras$ABMOrdenCompra$ABMOrdenCompra.btnAgregarLineaOrden_action}"
														binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.btnAgregarLineaOrden}" id="btnAgregarLineaOrden" styleClass="button"
														text="Agregar Línea Orden" />
													<ui:button action="#{compras$ABMOrdenCompra$ABMOrdenCompra.btnQuitar_action}"
														binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.btnQuitar}" id="btnQuitar" styleClass="button" text="Quitar" />
													<ui:staticText binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.stSeparador2}" escape="false" id="stSeparador2"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{compras$ABMOrdenCompra$ABMOrdenCompra.btnQuitarTodos_action}"
														binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.btnQuitarTodos}" id="btnQuitarTodos" styleClass="button" text="Quitar todos" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="right" colspan="2">
										<ui:label binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.lblMontoTotal}" id="lblMontoTotal" styleClass="label"
											text="Monto Total:" />
										<ui:staticText binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.stTotal}"
											converter="#{compras$ABMOrdenCompra$ABMOrdenCompra.numberConverter1}" id="stTotal" styleClass="staticText" />
									</td>
								</tr>
								<tr>
									<td colspan="4" style="height: 10px">
										<ui:label binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.lblSolicitudSuministro}" id="lblSolicitudSuministro"
											styleClass="label2" text="Líneas de Solicitudes de Suministro" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.table1}" id="table1" width="239">
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
											<ui:tableRowGroup binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tableRowGroup1}" id="tableRowGroup1"
												sourceData="#{compras$ABMOrdenCompra$ABMOrdenCompra.ldpLineasSoSuministro}" sourceVar="currentRow">
												<ui:tableColumn binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tcNumeroSolicitud}" headerText="Nº Solicitud"
													id="tcNumeroSolicitud" sort="numeroSolicitud">
													<ui:staticText binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.stNumeroSolicitud}" id="stNumeroSolicitud"
														text="#{currentRow.value['numeroSolicitud']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tcArea}" headerText="Area" id="tcArea" sort="area">
													<ui:staticText binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.stArea}" id="stArea" text="#{currentRow.value['area']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tcBien}" headerText="Bien" id="tcBien" sort="bien">
													<ui:staticText binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.stBien}" id="staticText2" text="#{currentRow.value['bien']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tcCantidad}" headerText="Cantidad" id="tcCantidad"
													sort="cantidad">
													<ui:staticText binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.stCantidad}" id="stCantidad"
														text="#{currentRow.value['cantidad']}" />
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
									<td colspan="4">
										<ui:staticText binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.stMovimientoMercaderia}" id="stMovimientoMercaderia"
											styleClass="label2" text="Movimientos de Mercadería" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.table5}" id="table5" width="479">
											<ui:tableRowGroup binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tableRowGroup5}" id="tableRowGroup5"
												sourceData="#{compras$ABMOrdenCompra$ABMOrdenCompra.ldpMovimientoMercaderia}" sourceVar="currentRow5">
												<ui:tableColumn binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tcStockBien}" headerText="Bien" id="tcStockBien"
													sort="stock.bien">
													<ui:staticText binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.stStockBien}" id="stStockBien"
														text="#{currentRow5.value['stock'].bien}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tcCantidadMercaderia}" headerText="Cantidad"
													id="tcCantidadMercaderia" sort="cantidad">
													<ui:staticText binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.stCantidadMercaderia}" id="stCantidadMercaderia"
														text="#{currentRow5.value['cantidad']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tcDeposito}" headerText="Deposito" id="tcDeposito"
													sort="movimientoMercaderia.deposito">
													<ui:staticText binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.stDeposito}" id="stDeposito"
														text="#{currentRow5.value['movimientoDeMercaderia'].deposito}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tcDepositoDestino}" headerText="Deposito Destino"
													id="tcDepositoDestino" sort="movimientoMercaderia.depositoDestino">
													<ui:staticText binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.stDepositoDestino}" id="stDepositoDestino"
														text="#{currentRow5.value['movimientoDeMercaderia'].depositoDestino}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tcFecha}" headerText="Fecha" id="tcFecha"
													sort="movimientoMercaderia.fecha">
													<ui:staticText binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.stFecha}" id="stFecha"
														text="#{currentRow5.value['movimientoDeMercaderia'].fechaDate}"
														converter="#{compras$ABMOrdenCompra$ABMOrdenCompra.dateTimeConverter}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tcUsuario}" headerText="Usuario" id="tcUsuario"
													sort="movimientoMercaderia.usuario">
													<ui:staticText binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.stUsuario}" id="stUsuario"
														text="#{currentRow5.value['movimientoDeMercaderia'].usuario}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:panelGroup id="pgHistoricidadTransferencias" binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.pgHistoricidadTransferencias}">
											<ui:label binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.lblHistoricidad}" id="lblHistoricidad" styleClass="label2"
												text="Historicidad de transferencia" />
											<ui:table augmentTitle="false" binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.table4}" id="table4" width="120">
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
                                    document.getElementById("form1:table3:tableActionsTop:deleteTop").setDisabled(disabled);
                                    // Set disabled state for bottom actions
                                    document.getElementById("form1:table3:tableActionsBottom:deleteBottom").setDisabled(disabled);
                                }]]></script>
												<ui:tableRowGroup binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tableRowGroup4}" id="tableRowGroup4"
													sourceData="#{compras$ABMOrdenCompra$ABMOrdenCompra.ldpTransferenciaOrdenCompra}" sourceVar="currentRow">
													<ui:tableColumn binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tcProveedorAnterior}" headerText="Proveedor anterior"
														id="tcProveedorAnterior" sort="" valign="middle">
														<ui:staticText binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.stProveedorAnterior}" id="stProveedorAnterior"
															text="#{currentRow.value['proveedorAnterior']}" />
													</ui:tableColumn>
													<ui:tableColumn binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tcFechaTransferencia}" headerText="Fecha de transferencia"
														id="tcFechaTransferencia" sort="" valign="middle">
														<ui:staticText binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.stFechaTransferencia}" id="stFechaTransferencia"
															text="#{currentRow.value['fecha']}" />
													</ui:tableColumn>
													<ui:tableColumn binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tcComentarioTransferencia}"
														headerText="Comentario de transferencia" id="tcComentarioTransferencia" sort="comentario" valign="middle">
														<ui:staticText binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.stComentarioTransferencia}" id="stComentarioTransferencia"
															text="#{currentRow.value['comentario']}" />
													</ui:tableColumn>
												</ui:tableRowGroup>
											</ui:table>
										</ui:panelGroup>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<br />
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:label binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.lblPagos}" id="lblPagos" styleClass="label2" text="Pagos" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:panelGroup id="pgPagos" binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.pgPagos}">
											<table style="border-style: solid; border-width: 1px;">
												<tr>
													<td>
														<ui:label text="Generar pagos: " styleClass="label2" />
													</td>
												</tr>
												<tr>
													<td align="right" nowrap="nowrap">
														<ui:label binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.lblEntregaInicial}" for="tfEntregaInicial" id="lblEntregaInicial"
															styleClass="label" text="Con entrega inicial de $ " style="font-size: 12px" />
														<ui:textField binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tfEntregaInicial}" id="tfEntregaInicial" styleClass="textField"
															columns="15" onKeyPress="return ValidarFloat(event)" />
														<ui:label binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.lblCantidadCuotas}" id="lblCantidadCuotas" styleClass="label"
															text=", en " for="tfCantidadCuotas" style="font-size: 12px" />
														<ui:textField binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tfCantidadCuotas}" id="tfCantidadCuotas" styleClass="textField"
															columns="15" onKeyPress="return ValidarNum(event)" />
														<ui:label binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.lblValorCuota}" for="tfValorCuota" id="lblValorCuota"
															styleClass="label" text="cuotas o cuotas de" style="font-size: 12px" />
														<ui:textField binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tfValorCuota}" id="tfFechaEmision" styleClass="textField"
															columns="15" onKeyPress="return ValidarFloat(event)" />
														<ui:radioButton binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.rbValorCuotaFijo}" id="rbValorCuotaFijo" name="rbgValorCuota"
															selected="true" />
														<ui:label binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.lblValorCuotaFijo}" for="rbValorCuotaFijo" id="lblValorCuotaFijo"
															styleClass="label" text="$" />
														<ui:radioButton binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.rbValorCuotaPorcentual}" id="rbValorCuotaPorcentual"
															name="rbgValorCuota" />
														<ui:label binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.lblValorCuotaPorcentual}" for="rbValorCuotaPorcentual"
															id="lblValorCuotaPorcentual" styleClass="label" text="%" />
													</td>
												</tr>
												<tr>
													<td colspan="6" style="text-align: center;">
														<a4j:commandButton styleClass="btnAjax" value="Generar"
															action="#{compras$ABMOrdenCompra$ABMOrdenCompra.btnGenerarPagos_action}"
															binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.btnGenerarPagos}" reRender="form1:tablePagos" />
													</td>
												</tr>
											</table>
										</ui:panelGroup>
									</td>
								</tr>
								<tr>
									<td colspan="4">
											<ui:table augmentTitle="false" binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tablePagos}" id="tablePagos" width="200">
												<script>
													<![CDATA[
                                                    /* ----- Functions for Table Preferences Panel ----- */
                                                    /*
                                                     * Toggle the table preferences panel open or closed
                                                     */
                                                    function togglePreferencesPanel() {
                                                        var table = document.getElementById("form1:tablePagos");
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
                                                        var table = document.getElementById("form1:tablePagos");
                                                        return table.toggleTableFilterPanel();
                                                    }
                                                    /* ----- Functions for Table Actions ----- */
                                                    /*
                                                     * Initialize all rows of the table when the state
                                                     * of selected rows changes.
                                                     */
                                                    function initAllRows() {
                                                        var table = document.getElementById("form1:tablePagos");
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
                                                        var table = document.getElementById("form1:tablePagos");
                                                        table.selectGroupRows(rowGroupId, selected);
                                                    }
                                                    /*
                                                     * Disable all table actions if no rows have been selected.
                                                     */
                                                    function disableActions() {
                                                        // Determine whether any rows are currently selected
                                                        var table = document.getElementById("form1:tablePagos");
                                                        var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
                                                        // Set disabled state for top actions
                                                        document.getElementById("form1:tablePagos:tableActionsTop:deleteTop").setDisabled(disabled);
                                                        // Set disabled state for bottom actions
                                                        document.getElementById("form1:tablePagos:tableActionsBottom:deleteBottom").setDisabled(disabled);
                                                    }]]></script>
												<ui:tableRowGroup binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tableRowGroupPagos}" id="tableRowGroupPagos"
													sourceData="#{compras$ABMOrdenCompra$ABMOrdenCompra.objectListDataProviderPagos}" sourceVar="currentRow">
													<ui:tableColumn align="center" binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tableColumn1Pagos}" id="tableColumnPagos"
														valign="middle" width="10">
														<ui:radioButton binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.radioButton1Pagos}" id="radioButtonPagos" label=""
															name="buttonGroupPago" selected="#{compras$ABMOrdenCompra$ABMOrdenCompra.RBSelectedPagos}"
															selectedValue="#{compras$ABMOrdenCompra$ABMOrdenCompra.currentRowPagos}" />
													</ui:tableColumn>
													<ui:tableColumn binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tcNombre}" headerText="Nombre" id="tcNombrePago" sort="nombre"
														width="40">
														<ui:textField binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tfNombrePago}" id="stNombrePago"
															text="#{currentRow.value['nombre']}" />
														<!-- readOnly="#{compras$ABMOrdenCompra$ABMOrdenCompra.readOnly}" -->
													</ui:tableColumn>
													<ui:tableColumn binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tcMonto}" headerText="Monto" id="tcMontoPago" sort="monto"
														width="40">
														<ui:textField binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tfMontoPago}" id="tfMontoPago"
															text="#{currentRow.value['monto']}" styleClass="textField" columns="10" onKeyPress="return ValidarFloat(event,this)" />
														<!-- disabled="#{compras$ABMOrdenCompra$ABMOrdenCompra.readOnly}" -->
													</ui:tableColumn>
													<ui:tableColumn binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tcNroFactura}" headerText="Nº Factura" id="tcNroFactura"
														sort="factura" width="40">
														<ui:staticText binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.stNroFactura}" id="stNroFactura"
															text="#{currentRow.value['factura'].numero}" />
													</ui:tableColumn>
												</ui:tableRowGroup>
												<f:facet name="actionsTop">
													<ui:panelGroup binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.groupPanelPagos}" id="groupPanelPagos">
														<a4j:commandButton value="Agregar" binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.btnAgregarPago}"
															action="#{compras$ABMOrdenCompra$ABMOrdenCompra.btnAgregarPago_action}" styleClass="btnAjax" reRender="tablePagos" />
														<ui:staticText escape="false" id="stSeparadorPagos" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<a4j:commandButton value="Quitar" binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.btnQuitarPago}"
															action="#{compras$ABMOrdenCompra$ABMOrdenCompra.btnQuitarPago_action}" styleClass="btnAjax" reRender="tablePagos" />
													</ui:panelGroup>
												</f:facet>
											</ui:table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:panelGroup binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.pgFinalizarComo}">
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.lblFinalizarComo}" for="ddFinalizarComo" id="lblFinalizarComo"
														styleClass="label" text="Finalizar como" />
												</td>
												<td>
													<ui:dropDown binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.ddFinalizarComo}" id="ddFinalizarComo"
														items="#{compras$ABMOrdenCompra$ABMOrdenCompra.ddFinalizarComoDefaultOptions.options}" styleClass="textField" />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.lblComentario}" id="lblComentario" styleClass="label"
														text="Comentario de finalización" />
												</td>
												<td>
													<ui:textArea binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.taComentario}" columns="40" id="taComentario" rows="5"
														styleClass="textField" />
												</td>
											</tr>
										</ui:panelGroup>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:panelGroup binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.pgTransferirOrdenCompra}">
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.lblProveedor2}" for="tfNuevoProveedor" id="lblProveedor2"
														styleClass="label" text="Nuevo Proveedor" />
												</td>
												<td>
													<ui:textField binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tfNuevoProveedor}" columns="40" disabled="true"
														styleClass="textField" id="tfNuevoProveedor" />
													<ui:button action="#{compras$ABMOrdenCompra$ABMOrdenCompra.btnSeleccionarNuevoProveedor_action}"
														binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.btnSeleccionarNuevoProveedor}" escape="false"
														id="btnSeleccionarNuevoProveedor" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
													<a4j:commandButton id="btnLimpiarNuevoProveedor" reRender="form1:tfNuevoProveedor" title="Limpiar"
														binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.btnLimpiarNuevoProveedor}"
														action="#{compras$ABMOrdenCompra$ABMOrdenCompra.btnLimpiarNuevoProveedor_action}" styleClass="buttonLimpiarAjax" />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.lblComentarioTransferencia}" id="lblComentarioTransferencia"
														styleClass="label" text="Comentario de transferencia" />
												</td>
												<td>
													<ui:textArea binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.taComentarioTransferencia}" columns="40"
														id="taComentarioTransferencia" rows="5" styleClass="textField" />
												</td>
											</tr>
										</ui:panelGroup>
									</td>
								</tr>
								<tr>
									<td>
										<ui:panelGroup binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.pgFirmas}">
											<tr>
												<td colspan="2">
													<ui:label binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.lblListaFirmas}" id="lblListaFirmas"
														styleClass="label2" text="Firmas" />
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<ui:table augmentTitle="false" binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tableFirmas}" id="tableFirmas"
														width="200">
														<script>
															<![CDATA[
															/* ----- Functions for Table Preferences Panel ----- */
															/*
															 * Toggle the table preferences panel open or closed
															 */
															function togglePreferencesPanel() {
																var table = document
																		.getElementById("form1:tableFirmas");
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
																		.getElementById("form1:tableFirmas");
																return table
																		.filterMenuChanged();
															}
															/*
															 * Toggle the custom filter panel (if any) open or closed.
															 */
															function toggleFilterPanel() {
																var table = document
																		.getElementById("form1:tableFirmas");
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
																		.getElementById("form1:tableFirmas");
																table
																		.initAllRows();
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
																	rowGroupId,
																	selected) {
																var table = document
																		.getElementById("form1:tableFirmas");
																table
																		.selectGroupRows(
																				rowGroupId,
																				selected);
															}
															/*
															 * Disable all table actions if no rows have been selected.
															 */
															function disableActions() {
																// Determine whether any rows are currently selected
																var table = document
																		.getElementById("form1:tableFirmas");
																var disabled = (table
																		.getAllSelectedRowsCount() > 0) ? false
																		: true;
																// Set disabled state for top actions
																document
																		.getElementById(
																				"form1:tableFirmas:tableActionsTop:deleteTop")
																		.setDisabled(
																				disabled);
																// Set disabled state for bottom actions
																document
																		.getElementById(
																				"form1:tableFirmas:tableActionsBottom:deleteBottom")
																		.setDisabled(
																				disabled);
															}
															]]>
														</script>
														<ui:tableRowGroup binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.trgFirmas}" id="trgFirmas"
															sourceData="#{compras$ABMOrdenCompra$ABMOrdenCompra.ldpFirmas}" sourceVar="currentRow">
															<ui:tableColumn headerText="Usuario" id="tcUsuario" >
																<ui:staticText id="stUsuario" text="#{currentRow.value['firmaPermiso'].usuario}" />
															</ui:tableColumn>
															<ui:tableColumn headerText="Fecha - Hora" id="tcFechaHora" sort="fechaHora">
																<ui:staticText id="stFechaHora" text="#{currentRow.value['firmaPermiso'].fechaHora}" 
																converter="#{compras$ABMOrdenCompra$ABMOrdenCompra.dateTimeConverter}"/>
															</ui:tableColumn>
															<ui:tableColumn headerText="Comentario" id="tcComentarioFirma" sort="comentario">
																<ui:staticText id="stComentarioFirma" text="#{currentRow.value['firmaPermiso'].comentario}"/>
															</ui:tableColumn>
														</ui:tableRowGroup>
													</ui:table>
												</td>
											</tr>
										</ui:panelGroup>
									</td>
								</tr>
									<tr>
									<td colspan="4">
										<ui:table binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.tablaLogs}" id="tbLogsAuditoria" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.messageGroup}" id="messageGroup" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{compras$ABMOrdenCompra$ABMOrdenCompra.btnGuardar_action}"
											binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{compras$ABMOrdenCompra$ABMOrdenCompra.btnCancelar_action}"
											binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMOrdenCompra$ABMOrdenCompra.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMOrdenCompra$ABMOrdenCompra.idSubSesion}" />
					<ui:script binding="#{compras$ABMOrdenCompra$ABMOrdenCompra.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
