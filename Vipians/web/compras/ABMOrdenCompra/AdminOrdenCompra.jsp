<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.page1}" id="page1">
			<ui:html binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.html1}" id="html1">
			<ui:head binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.head1}" id="head1" title="Administración de Ordenes de Compra">
				<ui:link binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "compras$ABMOrdenCompra$AdminOrdenCompra";

					function cargarComportamientoJQuery() {
						autoCompletarEnTextField("#form1:tfBien", "bien", nombreBean, "setBienAutocompletar");
						calendarioEnTextField("#form1:tfFechaDesde");
						calendarioEnTextField("#form1:tfFechaHasta");
					}

					function focusearTfBienSeleccionada() {
						$("#form1\\:tfBien").focus();
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.body1}" focus="form1:tfNumero" id="body1"
				onLoad="parent.footer.location.reload(); Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.form1}" id="form1">
					<div class="divAdmin" style="left: -4px; top: 0px; position: absolute">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{compras$ABMOrdenCompra$AdminOrdenCompra.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td colspan="2"></td>
									</tr>
									<tr>
										<td align="center" nowrap="nowrap">
											<ui:staticText escape="false" id="stFiltrarPor" styleClass="textoFiltrarPor" text="Filtrar por" />
										</td>
									</tr>
									<tr>
										<td colspan="4">
											<hr />
										</td>
									</tr>
									<tr>
										<td align="center">
											<ui:panelGroup binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.lblNumero}" for="tfNumero" id="lblNumero" styleClass="label"
																text="Número" />
														</td>
														<td>
															<ui:textField binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.tfNumero}" columns="10" id="tfNumero" styleClass="textField"
																onKeyPress="return ValidarNum(event)" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.lblProveedor}" id="lblProveedor" styleClass="label"
																text="Proveedor" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.tfProveedor}" columns="40" disabled="true" id="tfProveedor"
																styleClass="textField" />
															<ui:button action="#{compras$ABMOrdenCompra$AdminOrdenCompra.btnSeleccionarProveedor_action}"
																binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.btnSeleccionarProveedor}" escape="false" id="btnSeleccionarProveedor"
																mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" />
															<a4j:commandButton id="btnLimpiarProveedor" reRender="form1:tfProveedor" title="Limpiar"
																binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.btnLimpiarProveedor}"
																action="#{compras$ABMOrdenCompra$AdminOrdenCompra.btnLimpiarProveedor_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.lblFechaDesde}" for="tfFechaDesde" id="lblFechaDesde"
																styleClass="label" text="Fecha Desde" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.tfFechaDesde}" id="tfFechaDesde" styleClass="textField"
																columns="10" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.lblFechaHasta}" for="tfFechaHasta" id="lblFechaHasta"
																styleClass="label" text="Fecha Hasta" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.tfFechaHasta}" id="tfFechaHasta" styleClass="textField"
																columns="10" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.lblSecretaria}" id="lblSecretaria" styleClass="label"
																text="Secretaria" for="ddSecretaria" />
														</td>
														<td>
															<ui:dropDown binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.ddSecretaria}" id="ddSecretaria"
																items="#{compras$ABMOrdenCompra$AdminOrdenCompra.ddSecretariaOptions.options}" styleClass="textField">
																<a4j:support event="onChange" reRender="form1:ddArea"
																	actionListener="#{compras$ABMOrdenCompra$AdminOrdenCompra.actionListenerDropSecretaria(event)}" />
															</ui:dropDown>
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.lblArea}" id="lblArea" styleClass="label" text="Área"
																for="ddArea" />
														</td>
														<td>
															<ui:dropDown binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.ddArea}" id="ddArea"
																items="#{compras$ABMOrdenCompra$AdminOrdenCompra.ddAreaOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.lblBien}" for="tfBien" id="lblBien" styleClass="label"
																text="Bien" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.tfBien}" columns="40" id="tfBien"
																styleClass="#{compras$ABMOrdenCompra$AdminOrdenCompra.paginatedTable.filtro.bien != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{compras$ABMOrdenCompra$AdminOrdenCompra.paginatedTable.filtro.bien != null}" />
															<ui:button action="#{compras$ABMOrdenCompra$AdminOrdenCompra.btnSeleccionarBien_action}"
																binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.btnSeleccionarBien}" escape="false" id="btnSeleccionarBien" mini="true"
																styleClass="buttonSeleccionar" text="&amp;nbsp;" />
															<a4j:commandButton id="btnLimpiarBien" reRender="form1:tfBien" title="Limpiar"
																binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.btnLimpiarBien}"
																action="#{compras$ABMOrdenCompra$AdminOrdenCompra.btnLimpiarBien_action}" styleClass="buttonLimpiarAjax"
																oncomplete="cargarComportamientoJQuery(); focusearTfBienSeleccionada();" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.lblEstado}" id="lblEstado" styleClass="label" text="Estado" />
														</td>
														<td nowrap="nowrap">
															<ui:dropDown binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.ddEstado}" id="ddEstado"
																items="#{compras$ABMOrdenCompra$AdminOrdenCompra.ddEstadoDefaultOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td colspan="2" style="height: 18px"></td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
														</td>
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
								</tbody>
								<tfoot>
									<tr>
										<td align="right" colspan="2">
											<a4j:commandButton binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.btnBuscar}"
												action="#{compras$ABMOrdenCompra$AdminOrdenCompra.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1, form1:stTotal,stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{compras$ABMOrdenCompra$AdminOrdenCompra.btnReiniciar_action}"
												binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1, form1:stTotal,stCantidadRegistros, form1:tfFechaDesde"
												oncomplete="cargarComportamientoJQuery();" />
											<ui:staticText binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.stSeparador1}" escape="false" id="stSeparador1"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{compras$ABMOrdenCompra$AdminOrdenCompra.btnCancelar_action}"
												binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td style="height: 102px">
										<ui:table binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.paginatedTable}" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.groupPanel1}" id="groupPanel1">
													<ui:button action="#{compras$ABMOrdenCompra$AdminOrdenCompra.btnSeleccionar_action}"
														binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.btnSeleccionar}" id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<!--<ui:staticText binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.stSeparador2}" escape="false"
                                                               id="stSeparador2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                                <ui:button action="#{compras$ABMOrdenCompra$AdminOrdenCompra.btnAgregar_action}"
                                                           binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar"/>
                                                <ui:button action="#{compras$ABMOrdenCompra$AdminOrdenCompra.btnModificar_action}"
                                                           binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.btnModificar}" id="btnModificar" styleClass="button" text="Modificar"/>
                                                -->
													<ui:staticText escape="false" binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.stSeparadorSeleccionar}" id="stSeparador7" />
													<ui:button text="Pagos" id="btnGenerarPagos" styleClass="button"
														action="#{compras$ABMOrdenCompra$AdminOrdenCompra.btnGenerarPagos_action}" />
													<ui:staticText binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.stSeparador5}" escape="false" id="stSeparador5"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{compras$ABMOrdenCompra$AdminOrdenCompra.btnTransferirOrdenCompra_action}"
														binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.btnTransferirOrdenCompra}" id="btnTransferirOrdenCompra"
														styleClass="button" text="Transferir Orden" />
													<ui:button action="#{compras$ABMOrdenCompra$AdminOrdenCompra.btnFirmarOrdenCompra_action}"
														binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.btnFirmarOrdenCompra}" id="btnFirmarOrdenCompra" styleClass="button"
														text="Firmar Orden" />
													<ui:button action="#{compras$ABMOrdenCompra$AdminOrdenCompra.btnFinalizar_action}"
														binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.btnFinalizarOrdenCompra}" id="btnFinalizarOrdenCompra" styleClass="button"
														text="Finalizar Orden" />
													<ui:staticText binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.stSeparador3}" escape="false" id="stSeparador3"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{compras$ABMOrdenCompra$AdminOrdenCompra.btnConsultar_action}"
														binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.btnConsultar}" id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:staticText binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.stSeparador4}" escape="false" id="stSeparador4"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{compras$ABMOrdenCompra$AdminOrdenCompra.btnImprimirReporte_action}"
														binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.btnImprimirReporte}"
														onClick="newWindow = window.open('/Vipians/faces/ImpresionServlet', 'Reporte')" id="btnImprimirReporte" styleClass="button"
														text="Visualizar Reporte" />
													<ui:staticText binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.stSeparador6}" escape="false" id="stSeparador6"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{compras$ABMOrdenCompra$AdminOrdenCompra.btnExportar_action}"
														binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
														onClick="return exportarReporte()" />
													<ui:staticText binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.paginatedTable.stSeparadorOrdenamiento}" id="separador_1" />
													<ui:imageHyperlink binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
											<!--
                                            <div style="">
                                             <ui:tableRowGroup binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.ocultable}">
                                                            <ui:tableColumn align="center" binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.tableColumn7}"
                                                                            id="tableColumn7" onClick="setTimeout('initAllRows()', 0)" valign="middle" width="10">
                                                                <ui:checkbox binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.checkbox1}" id="checkbox1" name="buttonsGroup"
                                                                             selected="#{compras$ABMOrdenCompra$AdminOrdenCompra.selected}" selectedValue="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.selectedValue}"/>
                                                            </ui:tableColumn>
                                                            <ui:tableColumn binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.tcNumeroSolicitud}"
                                                                            headerText="Nº" id="tcNumero" sort="numero">
                                                                <ui:staticText binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.stNumeroSolicitud}"
                                                                               id="stNumero" text="#{currentRow.value['numero']}"/>
                                                            </ui:tableColumn>
                                                            <ui:tableColumn binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.tcArea}"
                                                                            headerText="Fecha Emisión" id="tcFechaEmision" sort="area">
                                                                <ui:staticText binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.stArea}"
                                                                               id="stFechaEmision" text="#{currentRow.value['fechaEmision']}"/>
                                                            </ui:tableColumn>
                                                            <ui:tableColumn binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.tcBienAsociado}"
                                                                            headerText="Proovedor" id="tcProveedor" sort="bien">
                                                                <ui:staticText binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.stBienAsociado}"
                                                                               id="stProovedor" text="#{currentRow.value['proveedor']}"/>
                                                            </ui:tableColumn>
                                                            <ui:tableColumn binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.tcCantidad}"
                                                                            headerText="Estado" id="tcEstado" sort="cantidad">
                                                               <ui:staticText binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.stCantidad}"
                                                                           id="stEstado" text="#{currentRow.value['estado']}"/>
                                                            </ui:tableColumn>
                                                            
                                                            <ui:tableColumn binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.tcPrecio}"
                                                                            headerText="Precio" id="tcPrecio" sort="cantidad">
                                                                <ui:staticText binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.stPrecio}"
                                                                           id="stPrecio" text="#{currentRow.value['total']}"/>
                                                            </ui:tableColumn>
                                                    </ui:tableRowGroup>
                                            </div> -->
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="right" colspan="2">
										<ui:label binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.lblTotal}" id="lblTotal" styleClass="label" text="Total:" />
										<ui:staticText binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.stTotal}"
											converter="#{compras$ABMOrdenCompra$AdminOrdenCompra.numberConverter1}" id="stTotal" styleClass="staticText" />
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNumero').focus();
					</script>
					<ui:hiddenField binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMOrdenCompra$AdminOrdenCompra.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMOrdenCompra$AdminOrdenCompra.idSubSesion}" />
					<ui:script binding="#{compras$ABMOrdenCompra$AdminOrdenCompra.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
