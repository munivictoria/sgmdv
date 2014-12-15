<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.page1}" id="page1">
			<ui:html binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.html1}" id="html1">
			<ui:head binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.head1}" id="head1" title="Administración de Facturas de Subsidios">
				<ui:link binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.link1}" id="link1" url="/resources/stylesheet.css" />
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
			<ui:body binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.body1}" focus="form1:tfFechaDesde" id="body1"
				onLoad="parent.footer.location.reload(); Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.head1.title}" />
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
											<ui:panelGroup binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.lblFechaDesde}" for="tfFechaDesde" id="lblFechaDesde"
																styleClass="label" text="Fecha Desde" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.tfFechaDesde}" id="tfFechaDesde"
																styleClass="textField" columns="10" />
															<!--<ui:staticText binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.stFechaDesde}" escape="false"
                                                               id="stFechaDesde" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.lblFechaHasta}" for="tfFechaHasta" id="lblFechaHasta"
																styleClass="label" text="Fecha Hasta" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.tfFechaHasta}" id="tfFechaHasta"
																styleClass="textField" columns="10" />
															<!--<ui:staticText binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.stFechaHasta}" escape="false"
                                                               id="stFechaHasta" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.lblProveedor}" id="lblProveedor" styleClass="label"
																text="Proveedor" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.tfProveedor}" columns="40" disabled="true"
																id="tfProveedor" styleClass="textField" />
															<ui:button action="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.btnSeleccionarProveedor_action}"
																binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.btnSeleccionarProveedor}" escape="false"
																id="btnSeleccionarProveedor" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" />
															<a4j:commandButton title="Limpiar" id="btnLimpiarProveedor" reRender="form1:tfProveedor"
																action="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.btnLimpiarProveedor_action}" styleClass="buttonLimpiarAjax" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label id="lblDigestoMunicipal" for="tfDigestoMunicipal" styleClass="label" text="Digesto Municipal" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.tfDigestoMunicipal}" columns="40" disabled="true"
																id="tfDigestoMunicipal" styleClass="textField" />
															<ui:button action="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.btnSeleccionarDigesto_action}"
																binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.btnSeleccionarDigesto}" escape="false" id="btnSeleccionarDigesto"
																mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" />
															<a4j:commandButton id="btnLimpiarDigesto" reRender="form1:tfDigestoMunicipal" title="Limpiar"
																action="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.btnLimpiarDigesto_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.lblEstado}" id="lblEstado" styleClass="label"
																text="Estado" />
														</td>
														<td nowrap="nowrap">
															<ui:dropDown binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.ddEstado}" id="ddEstado"
																items="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.ddEstadoDefaultOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.btnBuscar}"
												action="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.btnReiniciar_action}"
												binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,stCantidadRegistros" oncomplete="cargarComportamientoJQuery();" />
											<ui:staticText binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.stSeparador1}" escape="false" id="stSeparador1"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.btnCancelar_action}"
												binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td colspan="2">
										<ui:table binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.paginatedTable}" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.groupPanel1}" id="groupPanel1">
													<ui:button action="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.btnSeleccionar_action}"
														binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
														text="Seleccionar" />
													<ui:staticText binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.stSeparadorSeleccionar}" escape="false"
														id="stSeparador2" />
													<ui:button action="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.btnAgregar_action}"
														binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.btnModificar_action}"
														binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.btnModificar}" id="btnModificar" styleClass="button"
														text="Modificar" />
													<ui:button action="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.btnEliminar_action}"
														binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.stSeparadorAccion}" escape="false" id="stSeparador3" />
													<ui:button action="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.btnConsultar_action}"
														binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.btnConsultar}" id="btnConsultar" styleClass="button"
														text="Consultar" />
													<ui:staticText binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.stSeparador4}" escape="false" id="stSeparador4"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.btnImprimirReporte_action}"
														binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.btnImprimirReporte}" disabled="true" id="btnImprimirReporte"
														styleClass="button" text="Visualizar Reporte" />
													<ui:staticText binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.stSeparador5}" escape="false" id="stSeparador5"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.btnExportar_action}"
														binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
														onClick="return exportarReporte()" />
													<ui:staticText binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_1" />
													<ui:imageHyperlink binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="right" colspan="2">
										<ui:label binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.lblTotal}" id="lblTotal" styleClass="label" text="Total:" />
										<ui:staticText binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.stTotal}"
											converter="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.numberConverter1}" id="stTotal" styleClass="staticText" />
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfFechaDesde').focus();
					</script>
					<ui:hiddenField binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.idSubSesion}" />
					<ui:script binding="#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
