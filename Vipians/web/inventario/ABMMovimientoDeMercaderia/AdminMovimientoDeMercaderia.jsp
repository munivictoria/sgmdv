<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.page1}" id="page1">
			<ui:html binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.html1}" id="html1">
			<ui:head binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.head1}" id="head1"
				title="Administración de Movimientos De Mercadería">
				<ui:link binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.body1}" focus="form1:btnSeleccionarStockOrigen"
				id="body1" onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.stTitulo}" id="stTitulo"
										styleClass="tituloABM" text="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td align="center">
											<ui:panelGroup binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td></td>
													</tr>
													<!--<tr>
                                            <td align="right" nowrap="true">
                                                <ui:label binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.lblStockOrigen}" id="lblStockOrigen" style="" styleClass="label" text="Stock Origen"/>
                                            </td>
                                            <td>
                                                <ui:textField binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.tfStockOrigen}" columns="60" disabled="true" id="tfStockOrigen" styleClass="textField"/>
                                                <ui:button action="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.btnSeleccionarStockOrigen_action}"
                                                           binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.btnSeleccionarStockOrigen}" escape="false" id="btnSeleccionarStockOrigen"
                                                           mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar Stock Origen"/>
                                                <ui:button action="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.btnLimpiarStockOrigen_action}"
                                                           binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.btnLimpiarStockOrigen}" escape="false" id="btnLimpiarStockOrigen" mini="true"
                                                           styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar"/>
                                            </td>
                                        </tr>  -->
													<!-- <tr>
                                            <td align="right" nowrap="true">
                                                <ui:label binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.lblStockDestino}" id="lblStockDestino" style="" styleClass="label" text="Stock Destino"/>
                                            </td>
                                            <td>
                                                <ui:textField binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.tfStockDestino}" columns="60" disabled="true" id="tfStockDestino" styleClass="textField"/>
                                                <ui:button action="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.btnSeleccionarStockDestino_action}"
                                                           binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.btnSeleccionarStockDestino}" escape="false" id="btnSeleccionarStockDestino"
                                                           mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar Stock Destino"/>
                                                <ui:button action="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.btnLimpiarStockDestino_action}"
                                                           binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.btnLimpiarStockDestino}" escape="false" id="btnLimpiarStockDestino" mini="true"
                                                           styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar"/>
                                            </td>
                                        </tr> -->
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.lblUsuario}" id="lblUsuario" style=""
																styleClass="label" text="Usuario" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.tfUsuario}" columns="60"
																disabled="true" id="tfUsuario" styleClass="textField" />
															<ui:button action="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.btnSeleccionarUsuario_action}"
																binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.btnSeleccionarUsuario}" escape="false"
																id="btnSeleccionarUsuario" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar Usuario" />
															<ui:button action="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.btnLimpiarUsuario_action}"
																binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.btnLimpiarUsuario}" escape="false"
																id="btnLimpiarUsuario" mini="true" styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.lblFechaDesde}" for="tfFechaDesde"
																id="lblFechaDesde" styleClass="label" text="Fecha Desde" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.tfFechaDesde}" id="tfFechaDesde"
																styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.lblFechaHasta}" for="tfFechaHasta"
																id="lblFechaHasta" styleClass="label" text="Fecha Hasta" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.tfFechaHasta}" id="tfFechaHasta"
																styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.lblArea}" id="lblArea" style=""
																styleClass="label" text="Área" />
														</td>
														<td nowrap="nowrap">
															<ui:dropDown binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.ddArea}" id="ddArea"
																styleClass="textField" items="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.ddAreaOptions.options}" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.lblDeposito}" id="lblDeposito" style=""
																styleClass="label" text="Depósito" />
														</td>
														<td nowrap="nowrap">
															<ui:dropDown binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.ddDeposito}" id="ddDeposito"
																styleClass="textField" items="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.ddDepositoOptions.options}" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.lblTipo}" id="lblTipo" style=""
																styleClass="label" text="Tipo" />
														</td>
														<td nowrap="nowrap">
															<ui:dropDown binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.ddTipo}" id="ddTipo"
																styleClass="textField" items="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.ddTipoOptions.options}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.tablaBusquedaLogs}"
																id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.btnBuscar}"
												action="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.btnReiniciar_action}"
												binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.btnReiniciar}" id="btnReiniciar"
												styleClass="btnAjax" value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.staticText2}" escape="false"
												id="staticText2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.btnCancelar_action}"
												binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<!-- </div>
                                -->
							<div>
								<ui:messageGroup binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.messageGroup}" id="messageGroup"
									showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.paginatedTable}" styleClass="tablaPaginada"
											id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.groupPanel1}" id="groupPanel1"
													style="">
													<ui:button action="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.btnSeleccionar_action}"
														binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.btnSeleccionar}" id="btnSeleccionar"
														styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.stSeparadorSeleccionar}"
														escape="false" id="staticText6" />
													<ui:button action="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.btnAgregar_action}"
														binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.btnAgregar}" id="btnAgregar" styleClass="button"
														text="Agregar" />
													<ui:button action="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.btnModificar_action}"
														binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.btnModificar}" id="btnModificar"
														styleClass="button" text="Modificar" />
													<ui:staticText binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.stSeparadorAccion}" escape="false"
														id="staticText8" />
													<ui:button action="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.btnConsultar_action}"
														binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.btnConsultar}" id="btnConsultar"
														styleClass="button" text="Consultar" />
													<ui:staticText binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.staticText9}" escape="false"
														id="staticText9" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.btnImprimirReporte_action}"
														binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.btnImprimirReporte}"
														onClick="newWindow = window.open('/Vipians/faces/ImpresionServlet', 'Reporte')" id="btnImprimirReporte" styleClass="button"
														text="Visualizar Reporte" />
													<ui:staticText binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.stSeparador10}" escape="false"
														id="stSeparador10" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.btnExportar_action}"
														binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.btnExportar}" id="btnExportar" styleClass="button"
														text="Exportar" onClick="return exportarReporte()" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.lblEncontrados}" id="lblEncontrados"
											styleClass="label2" text="Registros Encontrados: " />
										<ui:staticText binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.stCantidadRegistros}"
											id="stCantidadRegistros" styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:btnSeleccionarStockOrigen').focus();
					</script>
					<ui:hiddenField binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.hidIdPagina}" id="hidIdPagina"
						text="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.idPagina}" />
					<ui:hiddenField binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.idSubSesion}" />
					<ui:script binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>