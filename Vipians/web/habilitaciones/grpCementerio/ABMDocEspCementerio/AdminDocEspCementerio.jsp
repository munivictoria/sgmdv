<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.head1}" id="head1"
				title="Administración de Obligaciones: Cementerio">
				<ui:link binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.link1}" id="link1"
					url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerior";

					function cargarComportamientoJQuery() {
						autoCompletarEnTextField("#form1:tfPersona", "persona", nombreBean, "setPersonaAutocompletar");
					}

					function focusearTfPersonaSeleccionada() {
						$("#form1\\:tfPersona").focus();
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.body1}"
				focus="form1:btnSeleccionarPersonaFisica" id="body1" onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.stTitulo}" id="stTitulo"
										styleClass="tituloABM" text="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.head1.title}" />
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
											<ui:panelGroup binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.lblPersonaSeleccionada}"
																id="lblPersona" styleClass="label" text="Persona" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.tfPersonaSeleccionada}"
																columns="40" id="tfPersona"
																styleClass="#{framework$ABMContrato$AdminContrato.paginatedTable.filtro.persona != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{framework$ABMContrato$AdminContrato.paginatedTable.filtro.persona != null}" />
															<ui:button
																action="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.btnSeleccionarPersonaFisica_action}"
																binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.btnSeleccionarPersonaFisica}"
																escape="false" id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF"
																toolTip="Seleccionar Persona Fisica" />
															<ui:button
																action="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.btnSeleccionarPersonaJuridica_action}"
																binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.btnSeleccionarPersonaJuridica}"
																escape="false" id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ"
																toolTip="Seleccionar Persona Juridica" />
															<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersona" title="Limpiar"
																binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.btnLimpiarPersona}"
																action="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.btnLimpiarPersona_action}"
																styleClass="buttonLimpiarAjax" oncomplete="cargarComportamientoJQuery(); focusearTfPersonaSeleccionada();" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.lblParcelaSeleccionada}"
																for="tfParcelaSeleccionada" id="lblParcelaSeleccionada" styleClass="label" text="Parcela Cementerio" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.tfParcelaCementerio}"
																columns="40" disabled="true" id="tfParcelaCementerio" styleClass="textField" />
															<ui:button
																action="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.btnSeleccionarParcelaCementerio_action}"
																binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.btnSeleccionarParcelaCementerio}"
																escape="false" id="btnSeleccionarParcelaCementerio" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;"
																toolTip="Seleccionar Parcela" />
															<a4j:commandButton id="btnLimpiarParcelaCementerio" reRender="form1:tfParcelaCementerio" title="Limpiar"
																binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.btnLimpiarParcelaCementerio}"
																action="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.btnLimpiarParcelaCementerio_action}"
																styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.lblEstado}" id="label4" style=""
																styleClass="label" text="Estado" />
														</td>
														<td nowrap="nowrap">
															<ui:dropDown binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.ddEstado}" id="ddEstado"
																items="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.ddEstadoDefaultOptions.options}"
																styleClass="textField" />
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
																		<ui:panelGroup binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.panelAtributoDinamico}"
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
															<ui:checkbox
																binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.tablaBusquedaLogs}"
																id="tablaBusquedaLogs" />
														</td>
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
								</tbody>
								<tr>
									<td></td>
								</tr>
								<tfoot>
									<tr>
										<td align="right" colspan="2">
											<a4j:commandButton binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.btnBuscar}"
												action="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.btnBuscar_action}" id="btnBuscar"
												value="Buscar" styleClass="btnAjax" reRender="form1:table1,stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.btnReiniciar_action}"
												binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.btnReiniciar}" id="btnReiniciar"
												styleClass="btnAjax" value="Reiniciar" reRender="form1:pgParametros,form1:table1,stCantidadRegistros"
												oncomplete="cargarComportamientoJQuery();" />
											<ui:staticText binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.stSeparador1}" escape="false"
												id="stSeparador1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.btnCancelar_action}"
												binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.btnCancelar}" id="btnCancelar"
												styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.messageGroup}" id="messageGroup"
										showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:panelGroup binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.gpSeleccion}" id="gpSeleccion">
											<ui:label binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.label3}" for="ddPlantillaObligacion"
												id="label3" styleClass="label" text="Plantilla de Obligación" />
											<ui:staticText binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.staticText5}" escape="false"
												id="staticText5" styleClass="label" text="&amp;nbsp;&amp;nbsp;&amp;nbsp;" />
											<ui:dropDown binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.ddPlantillaObligacion}"
												id="ddPlantillaObligacion"
												items="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.ddPlantillaObligacionOptions.options}"
												styleClass="textField" />
										</ui:panelGroup>
									</td>
								</tr>
								<tr>
									<td>
										<ui:table binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.paginatedTable}" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.groupPanel1}" id="groupPanel1"
													style="">
													<ui:button action="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.btnSeleccionar_action}"
														binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.btnSeleccionar}" id="btnSeleccionar"
														styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.stSeparadorSeleccionar}"
														escape="false" id="separador2" />
													<ui:button action="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.btnAgregar_action}"
														binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.btnAgregar}" id="btnAgregar"
														styleClass="button" text="Agregar" />
													<ui:button action="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.btnModificar_action}"
														binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.btnModificar}" id="btnModificar"
														styleClass="button" text="Modificar" />
													<ui:button action="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.btnEliminar_action}"
														binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.btnEliminar}" id="btnEliminar"
														styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.stSeparadorAccion}"
														escape="false" id="separador3" />
													<ui:button action="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.btnConsultar_action}"
														binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.btnConsultar}" id="btnConsultar"
														styleClass="button" text="Consultar" />
													<ui:staticText binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.stSeparador4}" escape="false"
														id="separador4" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.btnExportar_action}"
														binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.btnExportar}" id="btnExportar"
														styleClass="button" text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText
														binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_1" />
													<ui:imageHyperlink
														binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.lblEncontrados}" id="lblEncontrados"
											styleClass="label2" text="Registros Encontrados: " />
										<ui:staticText binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.stCantidadRegistros}"
											id="stCantidadRegistros" styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:btnSeleccionarPersonaFisica').focus();
					</script>
					<ui:hiddenField binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{habilitaciones$grpCementerio$ABMDocEspCementerio$AdminDocEspCementerio.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
