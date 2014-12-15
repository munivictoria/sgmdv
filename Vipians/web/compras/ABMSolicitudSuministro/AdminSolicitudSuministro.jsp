<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.page1}" id="page1">
			<ui:html binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.html1}" id="html1">
			<ui:head binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.head1}" id="head1"
				title="Administración de Solicitudes de Suministro">
				<ui:link binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "compras$ABMSolicitudSuministro$AdminSolicitudSuministro";

					function cargarComportamientoJQuery() {
						calendarioEnTextField("#form1:tfFechaDesde");
						calendarioEnTextField("#form1:tfFechaHasta");
						autoCompletarEnTextField("#form1:tfBien", "bien", nombreBean, "setBienAutocompletar");
					}

					function focusearTfBien() {
						$("#form1\\:tfBien").focus();
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.body1}" focus="form1:tfNumero" id="body1"
				onLoad="parent.footer.location.reload(); Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td colspan="2"></td>
									<tr>
										<td align="center" nowrap="nowrap">
											<ui:staticText binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.stFiltrarPor}" escape="false" id="stFiltrarPor"
												styleClass="textoFiltrarPor" text="Filtrar por" />
										</td>
									</tr>
									<tr>
										<td colspan="4">
											<hr />
										</td>
									</tr>
									</tr>
									<tr>
										<td align="center">
											<ui:panelGroup binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.lblNro}" for="tfNumero" id="lblNro"
																styleClass="label" text="Número" />
														</td>
														<td>
															<ui:textField binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.tfNumero}" columns="10" disabled="false"
																id="tfNumero" styleClass="textField" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.lblEstado}" id="lblEstado" styleClass="label"
																text="Estado" />
														</td>
														<td nowrap="nowrap">
															<ui:dropDown binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.ddEstado}" id="ddEstado"
																items="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.ddEstadoDefaultOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.lblFechaDesde}" id="lblFechaDesde"
																styleClass="label" text="Desde la Fecha" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.tfFechaDesde}" id="tfFechaDesde"
																styleClass="textField" columns="10" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.lblFechaHasta}" id="lblfechaHasta"
																styleClass="label" text="Hasta la Fecha" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.tfFechaHasta}" id="tfFechaHasta"
																styleClass="textField" columns="10" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.lblSecretaria}" id="lblSecretaria"
																styleClass="label" text="Secretaria" for="ddSecretaria" />
														</td>
														<td>
															<ui:dropDown binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.ddSecretaria}" id="ddSecretaria"
																items="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.ddSecretariaOptions.options}" styleClass="textField">
																<a4j:support event="onChange" reRender="form1:ddArea"
																	actionListener="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.actionListenerDropSecretaria(event)}" />
															</ui:dropDown>
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.lblArea}" id="lblArea" styleClass="label"
																text="Área" for="ddArea" />
														</td>
														<td>
															<ui:dropDown binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.ddArea}" id="ddArea"
																items="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.ddAreaOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.lblProducto}" for="tfBien" id="lblProducto"
																styleClass="label" text="Bien" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.tfBien}" columns="40" id="tfBien"
																styleClass="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.paginatedTable.filtro.bien != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.paginatedTable.filtro.bien != null}" />
															<ui:button action="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.btnSeleccionarBien_action}"
																binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.btnSeleccionarBien}" escape="false"
																id="btnSeleccionarBien" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" />
															<a4j:commandButton id="btnLimpiarProducto" reRender="form1:tfBien" title="Limpiar"
																binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.btnLimpiarProducto}"
																action="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.btnLimpiarProducto_action}" styleClass="buttonLimpiarAjax"
																oncomplete="cargarComportamientoJQuery(); focusearTfBien();" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.lblUrgente}" id="lblUrgente" styleClass="label"
																text="Urgente" />
														</td>
														<td nowrap="nowrap">
															<ui:dropDown binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.ddUrgente}" id="ddUrgente"
																items="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.ddUrgenteDefaultOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.btnBuscar}"
												action="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.btnReiniciar_action}"
												binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros"
												oncomplete="cargarComportamientoJQuery();" />
											<ui:staticText binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.stSeparador1}" escape="false" id="stSeparador1"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.btnCancelar_action}"
												binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.messageGroup}" id="messageGroup"
										showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td style="height: 102px">
										<ui:table binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.paginatedTable}" styleClass="tablaPaginada"
											id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.groupPanel1}" id="groupPanel1">
													<ui:button action="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.btnSeleccionar_action}"
														binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
														text="Seleccionar" />
													<ui:staticText binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.stSeparadorSeleccionar}" escape="false"
														id="stSeparador3" />
													<ui:button action="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.btnAgregar_action}"
														binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.btnAgregar}" id="btnAgregar" styleClass="button"
														text="Agregar" />
													<ui:button action="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.btnModificar_action}"
														binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.btnModificar}" id="btnModificar" styleClass="button"
														text="Modificar" />
													<ui:staticText binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.stSeparadorAccion}" escape="false"
														id="stSeparador2" />
													<ui:label binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.lblAccion}" for="ddOpcionesExtras" id="lblAccion"
														styleClass="label" text="Acción:" />
													<ui:dropDown id="ddOpcionesExtras" binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.ddOpcionesExtras}"
														items="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.ddOpcionesExtrasOptions.options}" styleClass="textField" />
													<ui:button id="btnIr" binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.btnIr}"
														action="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.btnIr_action()}" styleClass="button" text="Ir" />
													<ui:staticText text="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.textoSeparador}" escape="false"
														id="stSeparador5" />
													<ui:button action="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.btnConsultar_action}"
														binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.btnConsultar}" id="btnConsultar" styleClass="button"
														text="Consultar" />
													<ui:staticText text="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.textoSeparador}" escape="false"
														id="stSeparador6" />
													<ui:button action="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.btnImprimirReporte_action}"
														binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.btnImprimirReporte}"
														onClick="newWindow = window.open('/Vipians/faces/ImpresionServlet', 'Reporte')" id="btnImprimirReporte" styleClass="button"
														text="Visualizar Reporte" />
													<ui:staticText text="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.textoSeparador}" escape="false"
														id="stSeparador7" />
													<ui:button action="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.btnExportar_action}"
														binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.btnExportar}" id="btnExportar" styleClass="button"
														text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_1" />
													<ui:imageHyperlink binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.lblEncontrados}" id="lblEncontrados"
											styleClass="label2" text="Registros Encontrados: " />
										<ui:staticText binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNumero').focus();
					</script>
					<ui:hiddenField binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.idSubSesion}" />
					<ui:script binding="#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
