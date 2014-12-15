<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMLicitacion$AgregarLineaContratacion.page1}" id="page1">
			<ui:html binding="#{compras$ABMLicitacion$AgregarLineaContratacion.html1}" id="html1">
			<ui:head binding="#{compras$ABMLicitacion$AgregarLineaContratacion.head1}" id="head1" title="Seleccione las líneas de Solicitud">
				<ui:link binding="#{compras$ABMLicitacion$AgregarLineaContratacion.link1}" id="link1" url="/resources/stylesheet.css" />
				<script type="text/javascript">
					<![CDATA[
                        var _decimales = 2;

                        function getById(id) {
                            elem = document.getElementById(id);
                            return elem;
                        }

                        function formatNumber(number) {
                            number = Math.round( number * Math.pow(10,_decimales) ) / Math.pow(10,_decimales);
                            cantDecimales = 0;
                            if (number.toString().indexOf(".") >= 0) cantDecimales = number.toString().substring( number.toString().indexOf(".")+1 ).length;
                            if (number == Math.floor(number)) number = number + ".";
                            for (i = 0; i < (_decimales-cantDecimales); i++) number += "0";
                            return number;
                        }
                   
                        function prueba(){
                            var lenght = document.getElementById('form1:table1').rows.length - 2;
                            //   var cant = document.getElementById('form1:tfCantidadBien');
                            //  var monto = document.getElementById('form1:tfMontoUnit');
                            // if(monto == null){ valorEstimado = formatNumber(0.0)}
                            var montoTotal = 0;
                            var montoLinea = 0;

                            for (var i=0;i<lenght;i++) {
                                var monto = formatNumber(document.getElementById('form1:table1:tableRowGroup1:'+i+':tableColumn5:tfMontoUnit').value);
                                var cant = formatNumber(document.getElementById('form1:table1:tableRowGroup1:'+i+':tableColumn3:tfCantidadBien').value);
                                document.getElementById('form1:table1:tableRowGroup1:'+i+':tableColumn6:tfMontoTotalLinea').value = formatNumber(parseFloat(monto)*parseFloat(cant));
                                montoLinea = formatNumber(document.getElementById('form1:table1:tableRowGroup1:'+i+':tableColumn6:tfMontoTotalLinea').value);
                                montoTotal += parseFloat(montoLinea);
                            }
                            
                            document.getElementById('form1:stMontoTotal').innerHTML =  formatNumber(parseFloat(montoTotal));
                        
                            //  document.getElementById('form1:tfMontoTotal').value = formatNumber(parseFloat(valorEstimado));

                        }

                    ]]></script>
			</ui:head>
			<ui:body binding="#{compras$ABMLicitacion$AgregarLineaContratacion.body1}" id="body1"
				onLoad="parent.footer.location.reload();Init();prueba();" style="background-color: rgb(236, 236, 242); -rave-layout: grid"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMLicitacion$AgregarLineaContratacion.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="verde">
								<caption>
									<ui:staticText binding="#{compras$ABMLicitacion$AgregarLineaContratacion.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{compras$ABMLicitacion$AgregarLineaContratacion.head1.title}" />
								</caption>
								<tr>
									<td></td>
								</tr>
								<tbody>
									<tr>
										<td colspan="2">
											<table>
												<tr>
													<td align="right" nowrap="nowrap">
														<ui:label binding="#{compras$ABMLicitacion$AgregarLineaContratacion.lblBien}" for="tfBien" id="lblBien" styleClass="label"
															text="Bien" />
													</td>
													<td>
														<ui:textField binding="#{compras$ABMLicitacion$AgregarLineaContratacion.tfBien}" columns="40" disabled="true" id="tfBien"
															styleClass="textField" />
														<ui:button action="#{compras$ABMLicitacion$AgregarLineaContratacion.btnSeleccionarBien_action}"
															binding="#{compras$ABMLicitacion$AgregarLineaContratacion.btnSeleccionarBien}" escape="false" id="btnSeleccionarBien"
															mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
														<a4j:commandButton id="btnLimpiarBien" reRender="form1:tfBien"
															binding="#{compras$ABMLicitacion$AgregarLineaContratacion.btnLimpiarBien}"
															action="#{compras$ABMLicitacion$AgregarLineaContratacion.btnLimpiarBien_action}" styleClass="buttonLimpiarAjax" />
													</td>
												</tr>
												<tr>
													<td align="right" nowrap="nowrap">
														<ui:label binding="#{compras$ABMLicitacion$AgregarLineaContratacion.lblSolicitudSum}" for="tfSolicitudSum"
															id="lblSolicitudSum" styleClass="label" text="Solicitud de Suministro" />
													</td>
													<td>
														<ui:textField binding="#{compras$ABMLicitacion$AgregarLineaContratacion.tfSolicitudSum}" columns="40" disabled="true"
															id="tfSolicitudSum" styleClass="textField" />
														<ui:button action="#{compras$ABMLicitacion$AgregarLineaContratacion.btnSeleccionarSolicitud_action}"
															binding="#{compras$ABMLicitacion$AgregarLineaContratacion.btnSeleccionarSolicitud}" escape="false"
															id="btnSeleccionarSolicitud" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
														<a4j:commandButton id="btnLimpiarSolicitud" reRender="form1:tfSolicitudSum"
															binding="#{compras$ABMLicitacion$AgregarLineaContratacion.btnLimpiarSolicitud}"
															action="#{compras$ABMLicitacion$AgregarLineaContratacion.btnLimpiarSolicitud_action}" styleClass="buttonLimpiarAjax" />
													</td>
												</tr>
											</table>
										</td>
										<td colspan="2">
											<div class="div" style="width: 290px; height: 15px;">Filtrar según los Proveedores seleccionados</div>
											<table border="0" class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
												<tr>
													<td style="padding: 0px;">
														<ui:checkbox binding="#{compras$ABMLicitacion$AgregarLineaContratacion.chbFiltrarCodCiiu}" id="chbFiltrarCodCiiu"
															label="Por sus Códigos CIIU" selected="false"
															toolTip="Filtrar las Líneas por los Códigos CIIU de los Proveedores seleccionados previamente en la Contratación">
														</ui:checkbox>
													</td>
												</tr>
												<tr>
													<td style="padding: 0px;">
														<ui:checkbox binding="#{compras$ABMLicitacion$AgregarLineaContratacion.chbFiltrarCategoriaBien}" id="chbFiltrarCategoriaBien"
															label="Por sus Categorías de Bienes" selected="false"
															toolTip="Filtrar las Líneas por las Categorías de los Proveedores seleccionados previamente en la Contratación">
														</ui:checkbox>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<br> </br>
										</td>
									</tr>
									<tr>
										<td colspan="4" style="text-align: center">
											<ui:button text="Buscar" styleClass="button" action="#{compras$ABMLicitacion$AgregarLineaContratacion.btnBuscar_action}"/>
											<ui:button text="Seleccionar Todas" styleClass="button" 
											action="#{compras$ABMLicitacion$AgregarLineaContratacion.btnSeleccionarTodas_action}"
											binding="#{compras$ABMLicitacion$AgregarLineaContratacion.btnSeleccionarTodas}" id="btnSeleccionarTodas" 
											/> 
										</td>
									</tr>
									<tr>
										<td colspan="4" style="height: 20px">
											<hr />
											<br />
										</td>
									</tr>
									<tr>
										<td colspan="4" style="height: 19px">
											<ui:label binding="#{compras$ABMLicitacion$AgregarLineaContratacion.lblTexto1}" id="lblTexto1" styleClass="label2"
												text="Listado de Líneas de Solicitud de Suministros" />

										</td>
									</tr>
									<tr>
										<td colspan="4">
											<ui:table binding="#{compras$ABMLicitacion$AgregarLineaContratacion.table1}" id="table1">
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
												<ui:tableRowGroup binding="#{compras$ABMLicitacion$AgregarLineaContratacion.tableRowGroup1}"
													emptyDataMsg="Ningún registro encontrado." id="tableRowGroup1"
													selected="#{compras$ABMLicitacion$AgregarLineaContratacion.currentRowSelected}"
													sourceData="#{compras$ABMLicitacion$AgregarLineaContratacion.ldpLineasSoSuministro}" sourceVar="currentRow">
													<ui:tableColumn align="center" binding="#{compras$ABMLicitacion$AgregarLineaContratacion.tableColumn7}" id="tableColumn7"
														onClick="setTimeout('initAllRows()', 0)" valign="middle" width="10">
														<ui:checkbox binding="#{compras$ABMLicitacion$AgregarLineaContratacion.checkbox1}" id="checkbox1" name="buttonsGroup"
															selected="#{compras$ABMLicitacion$AgregarLineaContratacion.selected}"
															selectedValue="#{compras$ABMLicitacion$AgregarLineaContratacion.selectedValue}" />
													</ui:tableColumn>
													<ui:tableColumn binding="#{compras$ABMLicitacion$AgregarLineaContratacion.tcNumeroSolicitud}" headerText="Nº Solicitud"
														id="tcNumeroSolicitud" sort="numeroSolicitud">
														<ui:staticText binding="#{compras$ABMLicitacion$AgregarLineaContratacion.stNumeroSolicitud}" id="stNumeroSolicitud"
															text="#{currentRow.value['numeroSolicitud']}" />
													</ui:tableColumn>
													<ui:tableColumn binding="#{compras$ABMLicitacion$AgregarLineaContratacion.tcArea}" headerText="Area" id="tcArea" sort="area">
														<ui:staticText binding="#{compras$ABMLicitacion$AgregarLineaContratacion.stArea}" id="stArea"
															text="#{currentRow.value['area']}" />
													</ui:tableColumn>
													<ui:tableColumn binding="#{compras$ABMLicitacion$AgregarLineaContratacion.tcBienAsociado}" headerText="Bien"
														id="tcBienAsociado" sort="bien">
														<ui:staticText binding="#{compras$ABMLicitacion$AgregarLineaContratacion.stBienAsociado}" id="stBienAsociado"
															text="#{currentRow.value['bien']}" />
													</ui:tableColumn>
													<ui:tableColumn binding="#{compras$ABMLicitacion$AgregarLineaContratacion.tcCantidad}" headerText="Cantidad" id="tcCantidad"
														sort="cantidad">
														<ui:staticText binding="#{compras$ABMLicitacion$AgregarLineaContratacion.stCantidad}" id="stCantidad"
															text="#{currentRow.value['cantidad']}" />
														<!--<ui:textField binding="#{compras$ABMLicitacion$AgregarLineaContratacion.tfCantidadBien}"
                                                                          onBlur="prueba()" onKeyPress="return ValidarFloat(event,this)" id="tfCantidadBien" text="#{currentRow.value['cantidad']}"/>-->
													</ui:tableColumn>
													<ui:tableColumn binding="#{compras$ABMLicitacion$AgregarLineaContratacion.tcCantidadRestante}" headerText="Cantidad Restante" id="tcCantidadRestante"
														sort="cantidadRestante">
														<ui:staticText binding="#{compras$ABMLicitacion$AgregarLineaContratacion.stCantidadRestante}" id="stCantidadRestante"
															text="#{currentRow.value['cantidadRestante']}" />
													</ui:tableColumn>
												</ui:tableRowGroup>
											</ui:table>
										</td>
									</tr>
									<tr>
										<td colspan="4">
											<div>
												<ui:messageGroup binding="#{compras$ABMLicitacion$AgregarLineaContratacion.messageGroup}" id="messageGroup" showDetail="true"
													showSummary="false" styleClass="grupoMsgAdmin" />
											</div>
										</td>
									</tr>
								</tbody>
								<tfoot>
									<tr>
										<td align="right" colspan="4" nowrap="nowrap">
											<ui:button action="#{compras$ABMLicitacion$AgregarLineaContratacion.btnAceptar_action}"
												binding="#{compras$ABMLicitacion$AgregarLineaContratacion.btnAceptar}" id="btnAceptar" styleClass="button" text="Aceptar Líneas" />
											<ui:staticText binding="#{compras$ABMLicitacion$AgregarLineaContratacion.stSeparador2}" escape="false" id="stSeparador2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{compras$ABMLicitacion$AgregarLineaContratacion.btnCancelar_action}"
												binding="#{compras$ABMLicitacion$AgregarLineaContratacion.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
					<ui:hiddenField binding="#{compras$ABMLicitacion$AgregarLineaContratacion.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMLicitacion$AgregarLineaContratacion.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMLicitacion$AgregarLineaContratacion.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMLicitacion$AgregarLineaContratacion.idSubSesion}" />
					<ui:script binding="#{compras$ABMLicitacion$AgregarLineaContratacion.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{compras$ABMLicitacion$AgregarLineaContratacion.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
