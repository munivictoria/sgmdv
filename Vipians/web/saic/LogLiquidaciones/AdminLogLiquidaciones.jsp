<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.page1}" id="page1">
			<ui:html binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.html1}" id="html1">
			<ui:head binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.head1}" id="head1" title="Administración de Log de Liquidaciones">
				<ui:link binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.link1}" id="link1" url="/resources/stylesheet.css" />
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
			<ui:body binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{saic$LogLiquidaciones$AdminLogLiquidaciones.head1.title}" />
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
											<ui:panelGroup binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.lbTipoObligacion}" id="lbTipoObligacion" styleClass="label"
																text="Tipo de Obligacion" />
														</td>
														<td>
															<ui:dropDown binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.ddTipoObligacion}" id="ddTipoObligacion"
																styleClass="textField" items="#{saic$LogLiquidaciones$AdminLogLiquidaciones.ddTipoObligacionOptions.options}" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.lbEvento}" id="lbEvento" styleClass="label" text="Evento" />
														</td>
														<td>
															<ui:dropDown binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.ddEvento}" id="ddEvento" styleClass="textField"
																items="#{saic$LogLiquidaciones$AdminLogLiquidaciones.ddEventoOptions.options}" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.lbUsuario}" id="lbUsuario" styleClass="label" text="Usuario" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.tfUsuario}" columns="30" disabled="true" id="tfUsuario"
																styleClass="textFieldDisabled" />
															<ui:button action="#{saic$LogLiquidaciones$AdminLogLiquidaciones.btnSeleccionarUsuario_action}"
																binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.btnSeleccionarUsuario}" escape="false" id="btnSeleccionarUsuario"
																mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarUsuario" reRender="form1:tfUsuario" title="Limpiar"
																binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.btnLimpiarUsuario}"
																action="#{saic$LogLiquidaciones$AdminLogLiquidaciones.btnLimpiarUsuario_action}" styleClass="buttonLimpiarAjax"
																oncomplete="cargarComportamientoJQuery();" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.lbAnio}" id="lbAnio" styleClass="label" text="Año" />
														</td>
														<td>
															<ui:textField binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.tfAnio}" id="tfAnio" styleClass="textField" columns="4"
																maxLength="4" onKeyPress="return ValidarNum(event)" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.lbNroPeriodo}" id="lbNroPeriodo" styleClass="label"
																text="Nº Periodo" />
														</td>
														<td>
															<ui:textField binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.tfNroPeriodo}" id="tfNroPeriodo" styleClass="textField"
																columns="4" maxLength="4" onKeyPress="return ValidarNum(event)" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.lbNroCuota}" id="lbNroCuota" styleClass="label"
																text="Nº Cuota" />
														</td>
														<td>
															<ui:textField binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.tfNroCuota}" id="tfNroCuota" styleClass="textField"
																columns="4" maxLength="4" onKeyPress="return ValidarNum(event)" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.lbFechaDesde}" id="lbFechaDesde" styleClass="label"
																text="Fecha desde" />
														</td>
														<td>
															<ui:textField binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.tfFechaDesde}" id="tfFechaDesde" styleClass="textField"
																columns="10" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.lbFechaHasta}" id="lbFechaHasta" styleClass="label"
																text="Fecha hasta" />
														</td>
														<td>
															<ui:textField binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.tfFechaHasta}" id="tfFechaHasta" styleClass="textField"
																columns="10" />
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
											<a4j:commandButton binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.btnBuscar}"
												action="#{saic$LogLiquidaciones$AdminLogLiquidaciones.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{saic$LogLiquidaciones$AdminLogLiquidaciones.btnReiniciar_action}"
												binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" oncomplete="cargarComportamientoJQuery();" />
											<ui:staticText binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.staticText1}" escape="false" id="staticText1"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{saic$LogLiquidaciones$AdminLogLiquidaciones.btnCancelar_action}"
												binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.paginatedTable}" id="table1" styleClass="tablaPaginada">
											<f:facet name="actionsTop">
												<ui:panelGroup id="groupPanel1">
													<ui:dropDown id="ddFormatoExportar" binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.ddFormatoExportar}"
														items="#{saic$LogLiquidaciones$AdminLogLiquidaciones.ddFormatoExportarDefaultOptions.options}" />
													<ui:button id="btnExportar" binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.btnExportar}" text="Exportar"
														action="#{saic$LogLiquidaciones$AdminLogLiquidaciones.btnExportar_action}" styleClass="button"
														onClick="return exportarReporte()" />
													<ui:imageHyperlink binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<ui:hiddenField binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.hidIdPagina}" id="hidIdPagina"
						text="#{saic$LogLiquidaciones$AdminLogLiquidaciones.idPagina}" />
					<ui:hiddenField binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$LogLiquidaciones$AdminLogLiquidaciones.idSubSesion}" />
					<ui:script binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{saic$LogLiquidaciones$AdminLogLiquidaciones.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
