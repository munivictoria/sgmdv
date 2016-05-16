<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.head1}" id="head1"
				title="Administración de Parcelas Cementerio">
				<ui:link binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.link1}" id="link1"
					url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio";

					function cargarComportamientoJQuery() {
						autoCompletarEnTextField("#form1:tfPersonaSeleccionada", "persona", nombreBean, "setPersonaAutocompletar");
						calendarioEnTextField("#form1:tfFechaInscripcion");
						calendarioEnTextField("#form1:tfFechaFinalizacion");
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
			<ui:body binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.body1}" focus="form1:btnSeleccionarPersona"
				id="body1" onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.stTitulo}" id="stTitulo"
										styleClass="tituloABM" text="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.head1.title}" />
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
											<ui:panelGroup binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.pgParametros}"
												id="pgParametros">
												<table>
													<tr>
														<td>
															<table border="0" class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
																<tr>
																<td align="right" nowrap="nowrap">
																		<ui:label binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.lblPersonaSeleccionada}"
																			for="tfPersonaSeleccionada" id="lblPersonaSeleccionada" styleClass="label" text="Titular" />
																	</td>
																	<td nowrap="nowrap">
																		<ui:textField binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.tfPersonaSeleccionada}"
																			columns="40" id="tfPersonaSeleccionada"
																			styleClass="#{framework$ABMContrato$AdminContrato.paginatedTable.filtro.persona != null ? 'textFieldDisabled' : 'textField'}"
																			disabled="#{framework$ABMContrato$AdminContrato.paginatedTable.filtro.persona != null}" />
																		<ui:button
																			action="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.btnSeleccionarPersonaFisica_action}"
																			binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.btnSeleccionarPersonaFisica}"
																			escape="false" id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF"
																			toolTip="Seleccionar Persona Física" />
																		<ui:button
																			action="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.btnSeleccionarPersonaJuridica_action}"
																			binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.btnSeleccionarPersonaJuridica}"
																			escape="false" id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ"
																			toolTip="Seleccionar Persona Jurídica" />
																		<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersonaSeleccionada" title="Limpiar"
																			binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.btnLimpiarPersona}"
																			action="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.btnLimpiarPersona_action}"
																			styleClass="buttonLimpiarAjax" oncomplete="cargarComportamientoJQuery(); focusearTfPersonaSeleccionada();" />
																	</td>
																	<td align="right" nowrap="nowrap">
																		<ui:label binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.lblPersona}" for="tfPersona"
																			id="lblPersona" styleClass="label" text="Difunto" />
																	</td>
																	<td nowrap="nowrap">
																		<ui:textField binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.tfPersona}" columns="40"
																			disabled="true" id="tfPersona" styleClass="textField" />
																		<ui:button
																			action="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.btnSeleccionarDifunto_action}"
																			binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.btnSeleccionarDifunto}"
																			escape="false" id="btnSeleccionarDifunto" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;"
																			toolTip="Seleccionar" />
																		<a4j:commandButton id="btnLimpiarDifunto" reRender="form1:tfPersona" title="Limpiar"
																			binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.btnLimpiarDifunto}"
																			action="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.btnLimpiarDifunto_action}"
																			styleClass="buttonLimpiarAjax" />
																	</td>
																</tr>
																<tr>
																	<td align="right" nowrap="nowrap">
																		<ui:label 
																			for="tfNumeroCuenta" id="lblNumeroCuenta" styleClass="label" text="Número Cuenta" />
																	</td>
																	<td>
																		<ui:textField styleClass="textField" id="tfNumeroCuenta" 
																			binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.tfNumeroCuenta}"/>
																	</td>
																	<td align="right" nowrap="nowrap">
																		<ui:label binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.lblTipoSepultura}"
																			for="tfTipoSepultura" id="lblTipoSepultura" styleClass="label" text="Tipo de Sepultura" />
																	</td>
																	<td nowrap="nowrap">
																		<ui:textField binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.tfTipoSepultura}"
																			columns="40" disabled="true" id="tfTipoSepultura" styleClass="textField" />
																		<ui:button
																			action="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.btnSeleccionarTipoSepultura_action}"
																			binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.btnSeleccionarTipoSepultura}"
																			escape="false" id="btnSeleccionarTipoSepultura" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;"
																			toolTip="Seleccionar" />
																		<a4j:commandButton id="btnLimpiarTipoSepultura" reRender="form1:tfTipoSepultura" title="Limpiar"
																			binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.btnLimpiarTipoSepultura}"
																			action="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.btnLimpiarTipoSepultura_action}"
																			styleClass="buttonLimpiarAjax" />
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
																					<ui:panelGroup
																						binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.panelAtributoDinamico}"
																						id="panelAtributoDinamico">
																						<!-- AQUI VA LO QUE SE CREA DINAMICAMENTE -->
																					</ui:panelGroup>
																				</td>
																			</tr>
																		</table>
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
																binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.tablaBusquedaLogs}"
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
											<a4j:commandButton binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.btnBuscar}"
												action="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.btnBuscar_action}" id="btnBuscar"
												value="Buscar" styleClass="btnAjax" reRender="form1:table1,stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.btnReiniciar_action}"
												binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.btnReiniciar}" id="btnReiniciar"
												styleClass="btnAjax" value="Reiniciar" reRender="form1:pgParametros,form1:table1,stCantidadRegistros"
												oncomplete="cargarComportamientoJQuery();" />
											<ui:staticText binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.stSeparador1}" escape="false"
												id="stSeparador1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.btnCancelar_action}"
												binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.btnCancelar}" id="btnCancelar"
												styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.messageGroup}"
										id="messageGroup" showDetail="true" showSummary="false" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.paginatedTable}"
											styleClass="tablaPaginada" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.groupPanel1}"
													id="groupPanel1" style="">
													<ui:button action="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.btnSeleccionar_action}"
														binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.btnSeleccionar}" id="btnSeleccionar"
														styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.stSeparadorSeleccionar}"
														escape="false" id="stSeparador2" />
													<ui:button action="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.btnAgregar_action}"
														binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.btnAgregar}" id="btnAgregar"
														styleClass="button" text="Agregar" />
													<ui:button action="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.btnModificar_action}"
														binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.btnModificar}" id="btnModificar"
														styleClass="button" text="Modificar" />
													<ui:button action="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.btnEliminar_action}"
														binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.btnEliminar}" id="btnEliminar"
														styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.stSeparadorAccion}"
														escape="false" id="stSeparador3" />
													<ui:button action="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.btnConsultar_action}"
														binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.btnConsultar}" id="btnConsultar"
														styleClass="button" text="Consultar" />
													<ui:staticText text="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.textoSeparador}" escape="false"
														id="stSeparador4" />
													<ui:button action="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.btnExportar_action}"
														binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.btnExportar}" id="btnExportar"
														styleClass="button" text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText
														binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_1" />
													<ui:imageHyperlink
														binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.lblEncontrados}" id="lblEncontrados"
											styleClass="label2" text="Registros Encontrados: " />
										<ui:staticText binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.stCantidadRegistros}"
											id="stCantidadRegistros" styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:btnSeleccionarPersona').focus();
					</script>
					<ui:hiddenField binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.hidIdSubSesion}"
						id="hidIdSubSesion" text="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
