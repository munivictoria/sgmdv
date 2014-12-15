<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.page1}" id="page1">
			<ui:html binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.html1}" id="html1">
			<ui:head binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.head1}" id="head1" title="Administración de Facturas de Proveedores">
				<ui:link binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.link1}" id="link1" url="/resources/stylesheet.css" />
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
			<ui:body binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.body1}" focus="form1:tfFechaDesde" id="body1"
				onLoad="parent.footer.location.reload(); Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="Administración de Facturas de Contrato" />
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
											<ui:panelGroup binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.lblFechaDesde}" for="tfFechaDesde" id="lblFechaDesde"
																styleClass="label" text="Fecha Desde" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.tfFechaDesde}" id="tfFechaDesde"
																styleClass="textField" columns="10" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.lblFechaHasta}" for="tfFechaHasta" id="lblFechaHasta"
																styleClass="label" text="Fecha Hasta" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.tfFechaHasta}" id="tfFechaHasta"
																styleClass="textField" columns="10" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap" style="height: 20px">
															<ui:label binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.lblContrato}" id="lblContrato" styleClass="label"
																text="Contrato" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.tfContrato}" columns="40" disabled="true"
																id="tfContrato" styleClass="textField" />
															<ui:button action="#{compras$ABMFacturaContrato$AdminFacturaContrato.btnSeleccionarContrato_action}"
																binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.btnSeleccionarContrato}" escape="false"
																id="btnSeleccionarContrato" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" />
															<a4j:commandButton id="btnLimpiarContrato" reRender="form1:tfContrato" title="Limpiar"
																action="#{compras$ABMFacturaContrato$AdminFacturaContrato.btnLimpiarContrato_action}" styleClass="buttonLimpiarAjax" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.lblProveedor}" id="lblProveedor" styleClass="label"
																text="Proveedor" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.tfProveedor}" columns="40" disabled="true"
																id="tfProveedor" styleClass="textField" />
															<ui:button action="#{compras$ABMFacturaContrato$AdminFacturaContrato.btnSeleccionarProveedor_action}"
																binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.btnSeleccionarProveedor}" escape="false"
																id="btnSeleccionarProveedor" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" />
															<a4j:commandButton id="btnLimpiarProveedor" reRender="form1:tfProveedor" title="Limpiar"
																action="#{compras$ABMFacturaContrato$AdminFacturaContrato.btnLimpiarProveedor_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap" style="height: 29px">
															<ui:label binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.lblEstado}" id="lblEstado" styleClass="label"
																text="Estado" />
														</td>
														<td nowrap="nowrap">
															<ui:dropDown binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.ddEstado}" id="ddEstado"
																items="#{compras$ABMFacturaContrato$AdminFacturaContrato.ddEstadoDefaultOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.btnBuscar}"
												action="#{compras$ABMFacturaContrato$AdminFacturaContrato.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{compras$ABMFacturaContrato$AdminFacturaContrato.btnReiniciar_action}"
												binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,stCantidadRegistros" oncomplete="cargarComportamientoJQuery();" />
											<ui:staticText binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.stSeparador1}" escape="false" id="stSeparador1"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{compras$ABMFacturaContrato$AdminFacturaContrato.btnCancelar_action}"
												binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td colspan="2">
										<ui:table binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.paginatedTable}" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.groupPanel1}" id="groupPanel1">
													<ui:button action="#{compras$ABMFacturaContrato$AdminFacturaContrato.btnSeleccionar_action}"
														binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
														text="Seleccionar" />
													<ui:staticText binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.stSeparadorSeleccionar}" escape="false"
														id="stSeparador2" />
													<ui:button action="#{compras$ABMFacturaContrato$AdminFacturaContrato.btnAgregar_action}"
														binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{compras$ABMFacturaContrato$AdminFacturaContrato.btnModificar_action}"
														binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.btnModificar}" id="btnModificar" styleClass="button"
														text="Modificar" />
													<ui:button action="#{compras$ABMFacturaContrato$AdminFacturaContrato.btnEliminar_action}"
														binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.stSeparadorAccion}" escape="false" id="stSeparador3" />
													<ui:button action="#{compras$ABMFacturaContrato$AdminFacturaContrato.btnConsultar_action}"
														binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.btnConsultar}" id="btnConsultar" styleClass="button"
														text="Consultar" />
													<ui:staticText binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.stSeparador4}" escape="false" id="stSeparador4"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{compras$ABMFacturaContrato$AdminFacturaContrato.btnImprimirReporte_action}"
														binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.btnImprimirReporte}" disabled="true" id="btnImprimirReporte"
														styleClass="button" text="Visualizar Reporte" />
													<ui:staticText binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.stSeparador5}" escape="false" id="stSeparador5"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{compras$ABMFacturaContrato$AdminFacturaContrato.btnExportar_action}"
														binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
														onClick="return exportarReporte()" />
													<ui:staticText binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_1" />
													<ui:imageHyperlink binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="right" colspan="2">
										<ui:label binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.lblTotal}" id="lblTotal" styleClass="label" text="Total:" />
										<ui:staticText binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.stTotal2}"
											converter="#{compras$ABMFacturaContrato$AdminFacturaContrato.numberConverter1}" id="stTotal2" styleClass="staticText" />
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfFechaDesde').focus();
					</script>
					<ui:hiddenField binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMFacturaContrato$AdminFacturaContrato.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMFacturaContrato$AdminFacturaContrato.idSubSesion}" />
					<ui:script binding="#{compras$ABMFacturaContrato$AdminFacturaContrato.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
