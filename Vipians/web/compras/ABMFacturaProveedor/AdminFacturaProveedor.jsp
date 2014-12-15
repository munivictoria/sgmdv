<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.page1}" id="page1">
			<ui:html binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.html1}" id="html1">
			<ui:head binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.head1}" id="head1"
				title="Administración de Facturas de Proveedores">
				<ui:link binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					function cargarComportamientoJQuery() {
						calendarioEnTextField("#form1:tfFechaDesde");
						calendarioEnTextField("#form1:tfFechaHasta");
					}
					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.body1}" focus="form1:tfFechaDesde" id="body1"
				onLoad="parent.footer.location.reload(); Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.head1.title}" />
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
										<td colspan="8">
											<hr />
										</td>
									</tr>
									<tr>
										<td align="center">
											<ui:panelGroup binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.lblProveedor}" id="lblProveedor" styleClass="label"
																text="Proveedor" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.tfProveedor}" columns="40" disabled="true"
																id="tfProveedor" styleClass="textField" />
															<ui:button action="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.btnSeleccionarProveedor_action}"
																binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.btnSeleccionarProveedor}" escape="false"
																id="btnSeleccionarProveedor" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" />
															<a4j:commandButton id="btnLimpiarProveedor" reRender="form1:tfProveedor" title="Limpiar"
																action="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.btnLimpiarProveedor_action}" styleClass="buttonLimpiarAjax" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.lblBien}" for="tfBien" id="lblBien" styleClass="label"
																text="Bien" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.tfBien}" columns="40" disabled="true" id="tfBien"
																styleClass="textField" />
															<ui:button action="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.btnSeleccionarBien_action}"
																binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.btnSeleccionarBien}" escape="false" id="btnSeleccionarBien"
																mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" />
															<a4j:commandButton id="btnLimpiarBien" reRender="form1:tfBien" title="Limpiar"
																action="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.btnLimpiarBien_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.lblFechaDesde}" for="tfFechaDesde" id="lblFechaDesde"
																styleClass="label" text="Fecha Desde" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.tfFechaDesde}" id="tfFechaDesde"
																styleClass="textField" columns="10" />
															<!--<ui:staticText binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.stFechaDesde}" escape="false"
                                                               id="stFechaDesde" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.lblFechaHasta}" for="tfFechaHasta" id="lblFechaHasta"
																styleClass="label" text="Fecha Hasta" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.tfFechaHasta}" id="tfFechaHasta"
																styleClass="textField" columns="10" />
															<!--<ui:staticText binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.stFechaHasta}" escape="false"
                                                               id="stFechaHasta" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.lblSecretaria}" id="lblSecretaria" styleClass="label"
																text="Secretaria" for="ddSecretaria" />
														</td>
														<td>
															<ui:dropDown binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.ddSecretaria}" id="ddSecretaria"
																items="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.ddSecretariaOptions.options}" styleClass="textField">
																<a4j:support event="onChange" reRender="form1:ddArea"
																	actionListener="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.actionListenerDropSecretaria(event)}" />
															</ui:dropDown>
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.lblArea}" id="lblArea" styleClass="label" text="Área"
																for="ddArea" />
														</td>
														<td>
															<ui:dropDown binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.ddArea}" id="ddArea"
																items="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.ddAreaOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.lblEstado}" id="lblEstado" styleClass="label"
																text="Estado" />
														</td>
														<td nowrap="nowrap">
															<ui:dropDown binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.ddEstado}" id="ddEstado"
																items="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.ddEstadoDefaultOptions.options}" styleClass="textField" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.lblNro}" id="lblNro" styleClass="label" text="Número" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.tfNro}" id="tfNro" styleClass="textField"
																columns="10" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<table border="0" width="600">
																<tr>
																	<td>
																		<ui:label id="lblVacio" styleClass="label" text="" />
																	</td>
																	<td>
																		<ui:panelGroup binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.panelAtributoDinamico}"
																			id="panelAtributoDinamico">
																		</ui:panelGroup>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.btnBuscar}"
												action="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros,form1:stTotal" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.btnReiniciar_action}"
												binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros,form1:stTotal"
												oncomplete="cargarComportamientoJQuery();" />
											<ui:staticText binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.stSeparador1}" escape="false" id="stSeparador1"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.btnCancelar_action}"
												binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td colspan="2">
										<ui:table binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.paginatedTable}" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.groupPanel1}" id="groupPanel1">
													<ui:button action="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.btnSeleccionar_action}"
														binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
														text="Seleccionar" />
													<ui:staticText binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.stSeparadorSeleccionar}" escape="false"
														id="stSeparador2" />
													<ui:button action="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.btnAgregar_action}"
														binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.btnModificar_action}"
														binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.btnModificar}" id="btnModificar" styleClass="button"
														text="Modificar" />
													<ui:button action="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.btnEliminar_action}"
														binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.btnEliminar}" id="btnEliminar" styleClass="button"
														text="Eliminar" />
													<ui:staticText binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.stSeparadorAccion}" escape="false"
														id="stSeparador3" />
													<ui:button action="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.btnConsultar_action}"
														binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.btnConsultar}" id="btnConsultar" styleClass="button"
														text="Consultar" />
													<ui:staticText binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.stSeparador4}" escape="false" id="stSeparador4"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.btnImprimirReporte_action}"
														binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.btnImprimirReporte}" disabled="true" id="btnImprimirReporte"
														styleClass="button" text="Visualizar Reporte" />
													<ui:staticText binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.stSeparador5}" escape="false" id="stSeparador5"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.btnExportar_action}"
														binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.btnExportar}" id="btnExportar" styleClass="button"
														text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.paginatedTable.stSeparadorOrdenamiento}"
														styleClass="barraSeparadoraVertical" />
													<ui:imageHyperlink binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="right" colspan="2">
										<ui:label binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.lblTotal}" id="lblTotal" styleClass="label" text="Total:" />
										<ui:staticText binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.stTotal}"
											converter="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.numberConverter1}" id="stTotal" styleClass="staticText" />
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfFechaDesde').focus();
					</script>
					<ui:hiddenField binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.idSubSesion}" />
					<ui:script binding="#{compras$ABMFacturaProveedor$AdminFacturaProveedor.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
