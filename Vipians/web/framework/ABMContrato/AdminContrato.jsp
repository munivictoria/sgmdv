<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMContrato$AdminContrato.page1}" id="page1">
			<ui:html binding="#{framework$ABMContrato$AdminContrato.html1}" id="html1">
			<ui:head binding="#{framework$ABMContrato$AdminContrato.head1}" id="head1" title="Administración de Facturas de Proveedores">
				<ui:link binding="#{framework$ABMContrato$AdminContrato.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "framework$ABMContrato$AdminContrato";

					function cargarComportamientoJQuery() {
						autoCompletarEnTextField("#form1:tfPersonaSeleccionada", "persona", nombreBean, "setPersonaAutocompletar");
						calendarioEnTextField("#form1:tfFechaInicioDesde");
						calendarioEnTextField("#form1:tfFechaInicioHasta");
						calendarioEnTextField("#form1:tfFechaFinDesde");
						calendarioEnTextField("#form1:tfFechaFinHasta");
					}

					function focusearTfPersonaSeleccionada() {
						$("#form1\\:tfPersonaSeleccionada").focus();
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{framework$ABMContrato$AdminContrato.body1}" focus="form1:tfFechaInicioDesde" id="body1"
				onLoad="parent.footer.location.reload(); Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMContrato$AdminContrato.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{framework$ABMContrato$AdminContrato.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="Administración de Contratos" />
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
											<ui:panelGroup binding="#{framework$ABMContrato$AdminContrato.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{framework$ABMContrato$AdminContrato.lblPersonaSeleccionada}" for="tfPersonaSeleccionada"
																id="lblPersonaSeleccionada" styleClass="label" text="Persona" />
														</td>
														<td>
															<ui:textField binding="#{framework$ABMContrato$AdminContrato.tfPersonaSeleccionada}" columns="40" id="tfPersonaSeleccionada"
																styleClass="#{framework$ABMContrato$AdminContrato.paginatedTable.filtro.persona != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{framework$ABMContrato$AdminContrato.paginatedTable.filtro.persona != null}" />
															<ui:button action="#{framework$ABMContrato$AdminContrato.btnSeleccionarPersonaFisica_action}"
																binding="#{framework$ABMContrato$AdminContrato.btnSeleccionarPersonaFisica}" id="btnSeleccionarPersonaFisica" mini="true"
																styleClass="button" text="PF" toolTip="Seleccionar Persona Física" />
															<ui:button action="#{framework$ABMContrato$AdminContrato.btnSeleccionarPersonaJuridica_action}"
																binding="#{framework$ABMContrato$AdminContrato.btnSeleccionarPersonaJuridica}" id="btnSeleccionarPersonaJuridica"
																mini="true" styleClass="button" text="PJ" toolTip="Seleccionar Persona Jurídica" />
															<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersonaSeleccionada" title="Limpiar"
																binding="#{framework$ABMContrato$AdminContrato.btnLimpiarPersona}"
																action="#{framework$ABMContrato$AdminContrato.btnLimpiarPersona_action}" styleClass="buttonLimpiarAjax"
																oncomplete="cargarComportamientoJQuery(); focusearTfPersonaSeleccionada();" />
														</td>
													</tr>
												</table>
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{framework$ABMContrato$AdminContrato.lblFechaInicioDesde}" for="tfFechaInicioDesde"
																id="lblFechaInicioDesde" styleClass="label" text="Fecha Inicio Desde" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{framework$ABMContrato$AdminContrato.tfFechaInicioDesde}" id="tfFechaInicioDesde"
																styleClass="textField" maxLength="10" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{framework$ABMContrato$AdminContrato.lblFechaInicioHasta}" for="tfFechaInicioHasta"
																id="lblFechaInicioHasta" styleClass="label" text="Fecha Inicio Hasta" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{framework$ABMContrato$AdminContrato.tfFechaInicioHasta}" id="tfFechaInicioHasta"
																styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{framework$ABMContrato$AdminContrato.lblFechaFinDesde}" for="tfFechaFinDesde" id="lblFechaFinDesde"
																styleClass="label" text="Fecha Fin Desde" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{framework$ABMContrato$AdminContrato.tfFechaFinDesde}" id="tfFechaFinDesde" styleClass="textField"
																onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{framework$ABMContrato$AdminContrato.lblFechaFinHasta}" for="tfFechaFinHasta" id="lblFechaFinHasta"
																styleClass="label" text="Fecha Fin Hasta" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{framework$ABMContrato$AdminContrato.tfFechaFinHasta}" id="tfFechaFinHasta" styleClass="textField"
																onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap" />
														<td nowrap="nowrap" />
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
								</tbody>
								<tfoot>
									<tr>
										<td align="right" colspan="2">
											<a4j:commandButton binding="#{framework$ABMContrato$AdminContrato.btnBuscar}"
												action="#{framework$ABMContrato$AdminContrato.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{framework$ABMContrato$AdminContrato.btnReiniciar_action}"
												binding="#{framework$ABMContrato$AdminContrato.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" oncomplete="cargarComportamientoJQuery();" />
											<ui:staticText binding="#{framework$ABMContrato$AdminContrato.stSeparador1}" escape="false" id="stSeparador1"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{framework$ABMContrato$AdminContrato.btnCancelar_action}"
												binding="#{framework$ABMContrato$AdminContrato.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{framework$ABMContrato$AdminContrato.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td colspan="2">
										<ui:table augmentTitle="false" binding="#{framework$ABMContrato$AdminContrato.paginatedTable}" styleClass="tablaPaginada"
											id="table1" width="599">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{framework$ABMContrato$AdminContrato.groupPanel1}" id="groupPanel1">
													<ui:button action="#{framework$ABMContrato$AdminContrato.btnSeleccionar_action}"
														binding="#{framework$ABMContrato$AdminContrato.btnSeleccionar}" id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{framework$ABMContrato$AdminContrato.stSeparadorSeleccionar}" id="separador_1"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMContrato$AdminContrato.btnAgregar_action}"
														binding="#{framework$ABMContrato$AdminContrato.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{framework$ABMContrato$AdminContrato.btnModificar_action}"
														binding="#{framework$ABMContrato$AdminContrato.btnModificar}" id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{framework$ABMContrato$AdminContrato.btnEliminar_action}"
														binding="#{framework$ABMContrato$AdminContrato.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{framework$ABMContrato$AdminContrato.stSeparadorAccion}" id="separador_2"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMContrato$AdminContrato.btnConsultar_action}"
														binding="#{framework$ABMContrato$AdminContrato.btnConsultar}" id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:staticText text="#{framework$ABMContrato$AdminContrato.textoSeparador}" id="separador_3"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMContrato$AdminContrato.btnExportar_action}"
														binding="#{framework$ABMContrato$AdminContrato.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
														onClick="return exportarReporte()" />
													<ui:staticText binding="#{framework$ABMContrato$AdminContrato.paginatedTable.stSeparadorOrdenamiento}" id="separador_4"
														styleClass="barraSeparadoraVertical" />
													<ui:imageHyperlink binding="#{framework$ABMContrato$AdminContrato.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{framework$ABMContrato$AdminContrato.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{framework$ABMContrato$AdminContrato.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfFechaInicioDesde').focus();
					</script>
					<ui:hiddenField binding="#{framework$ABMContrato$AdminContrato.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMContrato$AdminContrato.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMContrato$AdminContrato.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMContrato$AdminContrato.idSubSesion}" />
					<ui:script binding="#{framework$ABMContrato$AdminContrato.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
