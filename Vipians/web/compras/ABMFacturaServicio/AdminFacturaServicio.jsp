<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.page1}" id="page1">
			<ui:html binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.html1}" id="html1">
			<ui:head binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.head1}" id="head1" title="Administración de Facturas de Proveedores">
				<ui:link binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.link1}" id="link1" url="/resources/stylesheet.css" />
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
			<ui:body binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.body1}" focus="form1:tfFechaDesde" id="body1"
				onLoad="parent.footer.location.reload(); Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="Administración de Facturas de Servicio" />
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
											<ui:panelGroup binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.lblFechaDesde}" for="tfFechaDesde" id="lblFechaDesde"
																styleClass="label" text="Fecha Desde" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.tfFechaDesde}" id="tfFechaDesde"
																styleClass="textField" columns="10" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.lblFechaHasta}" for="tfFechaHasta" id="lblFechaHasta"
																styleClass="label" text="Fecha Hasta" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.tfFechaHasta}" id="tfFechaHasta"
																styleClass="textField" columns="10" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.lblProveedor}" id="lblProveedor" styleClass="label"
																text="Proveedor" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.tfProveedor}" columns="40" disabled="true"
																id="tfProveedor" styleClass="textField" />
															<ui:button action="#{compras$ABMFacturaServicio$AdminFacturaServicio.btnSeleccionarProveedor_action}"
																binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.btnSeleccionarProveedor}" escape="false"
																id="btnSeleccionarProveedor" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" />
															<a4j:commandButton id="btnLimpiarProveedor" reRender="form1:tfProveedor" title="Limpiar"
																action="#{compras$ABMFacturaServicio$AdminFacturaServicio.btnLimpiarProveedor_action}" styleClass="buttonLimpiarAjax" />
														</td>
														<td align="right" nowrap="nowrap" style="height: 24px">
															<ui:label binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.lblBien}" id="lblBien" styleClass="label" text="Bien" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.tfBien}" columns="40" disabled="true" id="tfBien"
																styleClass="textField" />
															<ui:button action="#{compras$ABMFacturaServicio$AdminFacturaServicio.btnSeleccionarBien_action}"
																binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.btnSeleccionarBien}" escape="false" id="btnSeleccionarBien"
																mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" />
															<a4j:commandButton id="btnLimpiarBien" reRender="form1:tfBien" title="Limpiar"
																action="#{compras$ABMFacturaServicio$AdminFacturaServicio.btnLimpiarBien_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap" style="height: 29px">
															<ui:label binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.lblEstado}" id="lblEstado" styleClass="label"
																text="Estado" />
														</td>
														<td nowrap="nowrap">
															<ui:dropDown binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.ddEstado}" id="ddEstado"
																items="#{compras$ABMFacturaServicio$AdminFacturaServicio.ddEstadoDefaultOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.btnBuscar}"
												action="#{compras$ABMFacturaServicio$AdminFacturaServicio.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{compras$ABMFacturaServicio$AdminFacturaServicio.btnReiniciar_action}"
												binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,stCantidadRegistros" oncomplete="cargarComportamientoJQuery();" />
											<ui:staticText binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.stSeparador1}" escape="false" id="stSeparador1"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{compras$ABMFacturaServicio$AdminFacturaServicio.btnCancelar_action}"
												binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td colspan="2">
										<ui:table binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.paginatedTable}" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.groupPanel1}" id="groupPanel1">
													<ui:button action="#{compras$ABMFacturaServicio$AdminFacturaServicio.btnSeleccionar_action}"
														binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
														text="Seleccionar" />
													<ui:staticText binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.stSeparadorSeleccionar}" escape="false"
														id="stSeparador2" />
													<ui:button action="#{compras$ABMFacturaServicio$AdminFacturaServicio.btnAgregar_action}"
														binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{compras$ABMFacturaServicio$AdminFacturaServicio.btnModificar_action}"
														binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.btnModificar}" id="btnModificar" styleClass="button"
														text="Modificar" />
													<ui:button action="#{compras$ABMFacturaServicio$AdminFacturaServicio.btnEliminar_action}"
														binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.stSeparadorAccion}" escape="false" id="stSeparador3" />
													<ui:button action="#{compras$ABMFacturaServicio$AdminFacturaServicio.btnConsultar_action}"
														binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.btnConsultar}" id="btnConsultar" styleClass="button"
														text="Consultar" />
													<ui:staticText binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.stSeparador4}" escape="false" id="stSeparador4"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{compras$ABMFacturaServicio$AdminFacturaServicio.btnImprimirReporte_action}"
														binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.btnImprimirReporte}" disabled="true" id="btnImprimirReporte"
														styleClass="button" text="Visualizar Reporte" />
													<ui:staticText binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.stSeparador5}" escape="false" id="stSeparador5"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{compras$ABMFacturaServicio$AdminFacturaServicio.btnExportar_action}"
														binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
														onClick="return exportarReporte()" />
													<ui:staticText binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_1" />
													<ui:imageHyperlink binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="right" colspan="2">
										<ui:label binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.lblTotal}" id="lblTotal" styleClass="label" text="Total:" />
										<ui:staticText binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.stTotal}"
											converter="#{compras$ABMFacturaServicio$AdminFacturaServicio.numberConverter1}" id="stTotal" styleClass="staticText" />
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfFechaDesde').focus();
					</script>
					<ui:hiddenField binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMFacturaServicio$AdminFacturaServicio.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMFacturaServicio$AdminFacturaServicio.idSubSesion}" />
					<ui:script binding="#{compras$ABMFacturaServicio$AdminFacturaServicio.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
