<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{inventario$ABMDeposito$ABMDeposito.page1}" id="page1">
			<ui:html binding="#{inventario$ABMDeposito$ABMDeposito.html1}" id="html1">
			<ui:head binding="#{inventario$ABMDeposito$ABMDeposito.head1}" id="head1">
				<ui:link binding="#{inventario$ABMDeposito$ABMDeposito.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					function checkear(){
					    var tabla = document.getElementById("form1:tableStockEnDeposito");
					    var filas = tabla.rows;

					    for(var i = 2; i < filas.length; i++){

					    	var cantidad = parseFloat(document.getElementById("form1:tableStockEnDeposito").rows[i].cells[2].childNodes[0].value);
					    	var cantidadLimite = parseFloat(document.getElementById("form1:tableStockEnDeposito").rows[i].cells[3].childNodes[0].value);
					    	
					    	if ((cantidad < cantidadLimite) || cantidad == 0){
					   		filas[i].cells[1].style.backgroundColor='#BDBDBD ';
					   		filas[i].cells[2].style.backgroundColor='#BDBDBD ';
					   		filas[i].cells[3].style.backgroundColor='#BDBDBD ';
					   		filas[i].cells[4].style.backgroundColor='#BDBDBD ';
					   		filas[i].cells[5].style.backgroundColor='#BDBDBD ';
					   		filas[i].cells[0].style.backgroundColor='#BDBDBD ';
							
					       	var cell = filas[i].cells[0];
					       	var rb = cell.getElementsByTagName('input');
					        rb[0].checked = true;
						   	}
						   	
					      }
					}
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{inventario$ABMDeposito$ABMDeposito.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{inventario$ABMDeposito$ABMDeposito.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{inventario$ABMDeposito$ABMDeposito.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{inventario$ABMDeposito$ABMDeposito.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{inventario$ABMDeposito$ABMDeposito.lblNombre}" for="tfNombre" id="lblNombre" styleClass="label" text="Nombre" />
									</td>
									<td>
										<ui:textField binding="#{inventario$ABMDeposito$ABMDeposito.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{inventario$ABMDeposito$ABMDeposito.lblTelefono}" for="tfTelefono" id="lblTelefono" styleClass="label"
											text="Teléfono" />
									</td>
									<td>
										<ui:textField binding="#{inventario$ABMDeposito$ABMDeposito.tfTelefono}" onKeyPress="return ValidarNum(event,this)" columns="20"
											id="tfTelefono" styleClass="textField" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{inventario$ABMDeposito$ABMDeposito.lblArea}" for="tfArea" id="lblArea" styleClass="label" text="Área" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{inventario$ABMDeposito$ABMDeposito.tfArea}" columns="40" disabled="true" id="tfArea"
											styleClass="textField" />
										<ui:button action="#{inventario$ABMDeposito$ABMDeposito.btnSeleccionarArea_action}"
											binding="#{inventario$ABMDeposito$ABMDeposito.btnSeleccionarArea}" escape="false" id="btnSeleccionarArea" mini="true"
											styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar Área" />
										<ui:button action="#{inventario$ABMDeposito$ABMDeposito.btnLimpiarArea_action}"
											binding="#{inventario$ABMDeposito$ABMDeposito.btnLimpiarArea}" escape="false" id="btnLimpiarArea" mini="true"
											styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{inventario$ABMDeposito$ABMDeposito.lblDomicilio}" id="lblDomicilio" styleClass="label" text="Domicilio" />
									</td>
									<td colspan="3">
										<ui:button action="#{inventario$ABMDeposito$ABMDeposito.btnSeleccionarDomicilio_action}"
											binding="#{inventario$ABMDeposito$ABMDeposito.btnSeleccionarDomicilio}" escape="false" id="btnSeleccionarDomicilio" mini="true"
											styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Agregar/Modificar" />
										<ui:button action="#{inventario$ABMDeposito$ABMDeposito.btnLimpiarDomicilio_action}"
											binding="#{inventario$ABMDeposito$ABMDeposito.btnLimpiarDomicilio}" escape="false" id="btnLimpiarDomicilio" mini="true"
											styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap"></td>
									<td colspan="3" nowrap="nowrap">
										<ui:staticText binding="#{inventario$ABMDeposito$ABMDeposito.stDomicilio}" escape="false" id="stDomicilio" styleClass="staticText" />
									</td>
								</tr>
								<tr>
									<td colspan="4" style="height: 19x">
										<ui:label binding="#{inventario$ABMDeposito$ABMDeposito.lblStockEnDeposito}" id="lblStockEnDeposito" styleClass="label2"
											text="Stock en Depósito"></ui:label>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{inventario$ABMDeposito$ABMDeposito.tableStockEnDeposito}" id="tableStockEnDeposito"
											width="239">
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
                                                        }]]>
                                                </script>
											<ui:tableRowGroup binding="#{inventario$ABMDeposito$ABMDeposito.tableRGStockEnDeposito}"
												emptyDataMsg="Ningún registro encontrado." id="tableRGStockEnDeposito"
												sourceData="#{inventario$ABMDeposito$ABMDeposito.ldpListaStockEnDeposito}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{inventario$ABMDeposito$ABMDeposito.tableColumn2}" id="tableColumn2"
													onClick="setTimeout('initAllRows()', 0)" valign="middle" width="10">
													<ui:checkbox binding="#{inventario$ABMDeposito$ABMDeposito.checkbox1}" id="checkbox1" name="buttonsGroup"
														selected="#{inventario$ABMDeposito$ABMDeposito.selected}" selectedValue="#{inventario$ABMDeposito$ABMDeposito.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn align="center" binding="#{inventario$ABMDeposito$ABMDeposito.tableColumn1}" id="tableColumn1" valign="middle"
													width="10">
													<ui:radioButton binding="#{inventario$ABMDeposito$ABMDeposito.radioButton1}" id="radioButton1" label="" name="buttonGroup"
														selected="#{inventario$ABMDeposito$ABMDeposito.RBSelected}" selectedValue="#{inventario$ABMDeposito$ABMDeposito.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{inventario$ABMDeposito$ABMDeposito.tableColBien}" headerText="Bien" id="tableColBien" noWrap="true"
													sort="bien">
													<ui:staticText binding="#{inventario$ABMDeposito$ABMDeposito.stBien}" id="stBien" text="#{currentRow.value['bien']}"></ui:staticText>
												</ui:tableColumn>
												<ui:tableColumn binding="#{inventario$ABMDeposito$ABMDeposito.tableColCantidad}" headerText="Cantidad" id="tableColCantidad"
													noWrap="true" sort="cantidad">
													<ui:textField binding="#{inventario$ABMDeposito$ABMDeposito.tfCantidad}" id="stCantidad" text="#{currentRow.value['cantidad']}"
														styleClass="textField" onKeyPress="return ValidarFloat(event,this)" disabled="#{currentRow.value['idStock'] != -1}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{inventario$ABMDeposito$ABMDeposito.tableColCantidadLimite}" headerText="Cantidad Limite"
													id="tableColCantidadLimite" noWrap="true" sort="cantidadLimite">
													<ui:textField binding="#{inventario$ABMDeposito$ABMDeposito.tfCantidadLimite}" id="stCantidadLimite"
														text="#{currentRow.value['cantidadLimite']}" styleClass="textField" onKeyPress="return ValidarFloat(event,this)" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{inventario$ABMDeposito$ABMDeposito.tableColCantidadAComprar}" headerText="Cantidad A Comprar"
													id="tableColCantidadAComprar" noWrap="true" sort="cantidadAComprar">
													<ui:textField binding="#{inventario$ABMDeposito$ABMDeposito.tfCantidadAComprar}" id="stCantidadAComprar"
														text="#{currentRow.value['cantidadAComprar']}" styleClass="textField" onKeyPress="return ValidarFloat(event,this)" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{inventario$ABMDeposito$ABMDeposito.tableDescripcion}" headerText="Descripcion" id="tableDescripcion"
													noWrap="true" sort="descripcion">
													<ui:textArea binding="#{inventario$ABMDeposito$ABMDeposito.taDescripcion}" id="stDescripcion"
														text="#{currentRow.value['descripcion']}" styleClass="textArea" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{inventario$ABMDeposito$ABMDeposito.groupPanel1}" id="groupPanel1">
													<a4j:commandButton action="#{inventario$ABMDeposito$ABMDeposito.btnMarcarFaltantes_action}"
														binding="#{inventario$ABMDeposito$ABMDeposito.btnMarcarFaltantes}" id="btnMarcarFaltantes" styleClass="btnAjax"
														value="Marcar Faltantes" onmousedown="checkear()" />
													<ui:button action="#{inventario$ABMDeposito$ABMDeposito.btnSeleccionarBien_action}"
														binding="#{inventario$ABMDeposito$ABMDeposito.btnSeleccionarBien}" id="btnSeleccionarBien" styleClass="button"
														text="Agregar Bien" />
													<ui:staticText binding="#{inventario$ABMDeposito$ABMDeposito.stSeparador3}" escape="false" id="stSeparador3"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<a4j:commandButton action="#{inventario$ABMDeposito$ABMDeposito.btnQuitar_action}"
														binding="#{inventario$ABMDeposito$ABMDeposito.btnQuitar}" id="btnQuitar" value="Quitar" styleClass="btnAjax"
														reRender="tableStockEnDeposito" onmousedown="reemplazarClickConConfirmacion(this, '');" />
													<ui:staticText binding="#{inventario$ABMDeposito$ABMDeposito.stSeparador}" escape="false" id="stSeparador"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<a4j:commandButton action="#{inventario$ABMDeposito$ABMDeposito.btnQuitarTodos_action}"
														binding="#{inventario$ABMDeposito$ABMDeposito.btnQuitarTodos}" id="btnQuitarTodos" value="Quitar todos" styleClass="btnAjax"
														reRender="tableStockEnDeposito"
														onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea quitar todos los elementos de esta lista?');" />
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
										<ui:label binding="#{inventario$ABMDeposito$ABMDeposito.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
									</td>
									<td>
										<ui:textArea binding="#{inventario$ABMDeposito$ABMDeposito.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table binding="#{inventario$ABMDeposito$ABMDeposito.tablaLogs}" id="tbLogsAuditoria" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:messageGroup binding="#{inventario$ABMDeposito$ABMDeposito.messageGroup}" id="messageGroup" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="2" nowrap="nowrap">
										<ui:button action="#{inventario$ABMDeposito$ABMDeposito.btnGuardar_action}"
											binding="#{inventario$ABMDeposito$ABMDeposito.btnGuardar}" id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{inventario$ABMDeposito$ABMDeposito.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{inventario$ABMDeposito$ABMDeposito.btnCancelar_action}"
											binding="#{inventario$ABMDeposito$ABMDeposito.btnCancelar}" id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{inventario$ABMDeposito$ABMDeposito.hidIdPagina}" id="hidIdPagina"
						text="#{inventario$ABMDeposito$ABMDeposito.idPagina}" />
					<ui:hiddenField binding="#{inventario$ABMDeposito$ABMDeposito.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{inventario$ABMDeposito$ABMDeposito.idSubSesion}" />
					<ui:script binding="#{inventario$ABMDeposito$ABMDeposito.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
