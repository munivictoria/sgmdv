<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.head1}" id="head1"
				title="Administración de Obligaciones: Tasa General Inmobiliaria">
				<ui:link binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento";

					function cargarComportamientoJQuery() {
						autoCompletarEnTextField("#form1:tfPersonaSeleccionada", "persona", nombreBean, "setPersonaAutocompletar");
						autoCompletarEnTextField("#form1:tfParcelaSeleccionada", "parcela", nombreBean, "setParcelaAutocompletar");
					}

					function focusearTfPersonaSeleccionada() {
						$("#form1\\:tfPersonaSeleccionada").focus();
					}

					function focusearTfParcelaSeleccionada() {
						$("#form1\\:tfParcelaSeleccionada").focus();
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.body1}" focus="form1:tfNumeroRegistro" id="body1"
				onLoad="parent.footer.location.reload(); changeStyleAlIngresar();" style="background-color: rgb(236, 236, 242); -rave-layout: grid"
				onKeyPress="return eventoByEnter(event,'btnBuscar')" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.head1.title}" />
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
											<ui:panelGroup binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.lblPersonaSeleccionada}" for="tfPersonaSeleccionada"
																id="lblPersonaSeleccionada" styleClass="label" text="Persona" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.tfPersonaSeleccionada}" columns="40"
																id="tfPersonaSeleccionada"
																styleClass="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.paginatedTable.filtro.persona != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.paginatedTable.filtro.persona != null}" />
															<ui:button action="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.btnSeleccionarPersonaFisica_action}"
																binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.btnSeleccionarPersonaFisica}" escape="false"
																id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Fisica" />
															<ui:button action="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.btnSeleccionarPersonaJuridica_action}"
																binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.btnSeleccionarPersonaJuridica}" escape="false"
																id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar Persona Juridica" />
															<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersonaSeleccionada" title="Limpiar"
																binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.btnLimpiarPersona}"
																action="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.btnLimpiarPersona_action}" styleClass="buttonLimpiarAjax"
																oncomplete="cargarComportamientoJQuery(); focusearTfPersonaSeleccionada();" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.lblParcelaSeleccionada}" for="tfParcelaSeleccionada"
																id="lblParcelaSeleccionada" styleClass="label" text="Parcela" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.tfParcelaSeleccionada}" columns="40"
																id="tfParcelaSeleccionada"
																styleClass="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.paginatedTable.filtro.parcela != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.paginatedTable.filtro.parcela != null}" />
															<ui:button action="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.btnSeleccionarParcela_action}"
																binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.btnSeleccionarParcela}" escape="false"
																id="btnSeleccionarParcela" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar Parcela" />
															<a4j:commandButton id="btnLimpiarParcela" reRender="form1:tfParcelaSeleccionada" title="Limpiar"
																binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.btnLimpiarParcela}"
																action="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.btnLimpiarParcela_action}" styleClass="buttonLimpiarAjax"
																oncomplete="cargarComportamientoJQuery(); focusearTfParcelaSeleccionada();" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap" style="height: 18px">
															<ui:label binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.label1}" for="tfNumeroParcela" id="label1"
																styleClass="label" text="Nº de Parcela" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.tfNroParcela}" id="tfNroParcela"
																styleClass="textField" />
														</td>
														<td align="right" nowrap="true">
															<ui:label binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.label4}" id="label4" style="" styleClass="label"
																text="Estado" />
														</td>
														<td nowrap="nowrap">
															<ui:dropDown binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.ddEstado}" id="ddEstado"
																items="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.ddEstadoDefaultOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.lblExenciones}" id="lblExenciones" style=""
																styleClass="label" text="Exenciones" />
														</td>
														<td nowrap="nowrap">
															<ui:dropDown binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.ddExenciones}" id="ddExenciones"
																items="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.ddExencionesDefaultOptions.options}" styleClass="textField" />
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
																		<ui:panelGroup binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.panelAtributoDinamico}"
																			id="panelAtributoDinamico">
																			<!-- AQUI VA LO QUE SE CREA DINAMICAMENTE -->
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
															<ui:checkbox binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.btnBuscar}"
												action="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.btnReiniciar_action}"
												binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros, form1:ddEstado"
												oncomplete="cargarComportamientoJQuery();" />
											<ui:staticText binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.btnCancelar_action}"
												binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:panelGroup binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.gpSeleccion}" id="gpSeleccion">
											<ui:label binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.label3}" for="ddPlantillaObligacion" id="label3"
												styleClass="label" text="Plantilla de Obligación" />
											<ui:staticText binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.staticText5}" escape="false" id="staticText5"
												styleClass="label" text="&amp;nbsp;&amp;nbsp;&amp;nbsp;" />
											<ui:dropDown binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.ddPlantillaObligacion}" id="ddPlantillaObligacion"
												items="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.ddPlantillaObligacionOptions.options}" styleClass="textField" />
										</ui:panelGroup>
									</td>
								</tr>
								<tr>
									<td>
										<ui:table binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.paginatedTable}" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.groupPanel1}" id="groupPanel1"
													separator="&lt;br/&gt;&lt;br/&gt;">
													<ui:panelGroup binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.gpBotones}" id="gpBotones">
														<ui:button action="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.btnSeleccionar_action}"
															binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
															text="Seleccionar" />
														<ui:staticText binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.stSeparadorSeleccionar}" escape="false"
															id="staticText6" />
														<ui:button action="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.btnAgregar_action}"
															binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
														<ui:button action="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.btnModificar_action}"
															binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.btnModificar}" id="btnModificar" styleClass="button"
															text="Modificar" />
														<ui:button action="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.btnEliminar_action}"
															binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.btnEliminar}" id="btnEliminar" styleClass="button"
															text="Eliminar" />
														<ui:button action="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.btnReactivar_action}"
															binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.btnReactivar}" id="btnReactivar" styleClass="button"
															text="Reactivar" />
														<ui:staticText binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.stSeparadorAccion}" escape="false"
															id="staticText8" />
														<ui:button action="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.btnConsultar_action}"
															binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.btnConsultar}" id="btnConsultar" styleClass="button"
															text="Consultar" />
														<ui:staticText binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.staticText7}" escape="false" id="staticText7"
															text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.idImprimirReporte}" disabled="true"
															id="idImprimirReporte" styleClass="button" text="Visualizar Reporte" />
														<ui:staticText binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.staticText11}" escape="false" id="staticText11"
															text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.btnExportar_action}"
															binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.btnExportar}" id="btnExportar" styleClass="button"
															text="Exportar" onClick="return exportarReporte()" />
														<ui:staticText binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.paginatedTable.stSeparadorOrdenamiento}"
															id="separador_1" />
														<ui:button action="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.btnImprimirReporte_action}"
															binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.btnImprimirReporte}"
															onClick="newWindow = window.open('/Vipians/faces/ImpresionServlet', 'Reporte')" id="btnImprimirReporte" styleClass="button"
															text="Visualizar Reporte" />
														<ui:imageHyperlink binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.paginatedTable.botonOrdenamiento}" />
													</ui:panelGroup>
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<ui:hiddenField binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$AdminDocEspArrendamiento.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
