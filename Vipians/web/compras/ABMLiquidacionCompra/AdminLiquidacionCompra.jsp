<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.page1}" id="page1">
			<ui:html binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.html1}" id="html1">
			<ui:head binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.head1}" id="head1"
				title="Administración de Liquidaciones de Compra">
				<ui:link binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					function cargarComportamientoJQuery() {
						calendarioEnTextField("#form1:tfFecha");
					}
					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.body1}" focus="form1:tfNumero" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar(); "
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.head1.title}" />
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
											<ui:panelGroup binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.lblNumero}" for="tfNumero" id="lblNumero" style=""
																styleClass="label" text="Número" />
														</td>
														<td>
															<ui:textField binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.tfNumero}" id="tfNumero" maxLength="10"
																styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.lblFecha}" for="tfFecha" id="lblFecha"
																styleClass="label" text="Fecha" />
														</td>
														<td>
															<ui:textField binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.tfFecha}" id="tfFecha" columns="10"
																styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.btnBuscar}"
												action="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.btnReiniciar_action}"
												binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,stCantidadRegistros" oncomplete="cargarComportamientoJQuery();" />
											<ui:staticText binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.stSeparador1}" escape="false" id="stSeparador1"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.btnCancelar_action}"
												binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table augmentTitle="false" binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.paginatedTable}" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.groupPanel1}" id="groupPanel1" style="">
													<ui:button action="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.btnSeleccionar_action}"
														binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
														text="Seleccionar" />
													<ui:staticText binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.stSeparadorSeleccionar}" escape="false"
														id="stSeparador2" />
													<ui:button action="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.btnAgregar_action}"
														binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.btnModificar_action}"
														binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.btnModificar}" id="btnModificar" styleClass="button"
														text="Modificar" />
													<ui:button action="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.btnEliminar_action}"
														binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.btnEliminar}" id="btnEliminar" styleClass="button"
														text="Eliminar" />
													<ui:staticText binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.stSeparadorAccion}" escape="false"
														id="stSeparador3" />
													<ui:button action="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.btnConsultar_action}"
														binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.btnConsultar}" id="btnConsultar" styleClass="button"
														text="Consultar" />
													<ui:button action="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.btnImprimirReporte_action}"
														binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.btnImprimirReporte}"
														onClick="newWindow = window.open('/Vipians/faces/ImpresionServlet', 'Reporte')" id="btnImprimirReporte" styleClass="button"
														text="Visualizar Reporte" />
													<ui:staticText binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.stSeparador4}" escape="false" id="stSeparador4"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.btnExportar_action}"
														binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.btnExportar}" id="btnExportar" styleClass="button"
														text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_1" />
													<ui:imageHyperlink binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNumero').focus();
					</script>
					<ui:hiddenField binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.idSubSesion}" />
					<ui:script binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>