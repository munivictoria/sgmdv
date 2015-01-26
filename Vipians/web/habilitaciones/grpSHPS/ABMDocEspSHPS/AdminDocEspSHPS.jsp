<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.head1}" id="head1"
				title="Administración de Obligaciones: Salud, Higiene, Profilaxis y Seguridad">
				<ui:link binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS";

					function cargarComportamientoJQuery() {
						mascaraCuimEnTextField("#form1:tfCuim");
						autoCompletarEnTextField("#form1:tfPersonaSeleccionada", "persona", nombreBean, "setPersonaAutocompletar");
						autoCompletarEnTextField("#form1:tfContador", "persona", nombreBean, "setContadorAutocompletar");
					}

					function focusearTfPersona() {
						$("#form1\\:tfPersonaSeleccionada").focus();
					}

					function focusearTfContador() {
						$("#form1\\:tfContador").focus();
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.body1}" focus="form1:btnSeleccionarPersonaFisica" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.head1.title}" />
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
											<ui:panelGroup binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.lblPersonaSeleccionada}"
																for="tfPersonaSeleccionada" id="lblPersonaSeleccionada" styleClass="label" text="Persona" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.tfPersonaSeleccionada}" columns="40"
																id="tfPersonaSeleccionada"
																styleClass="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.paginatedTable.filtro.persona != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.paginatedTable.filtro.persona != null}" />
															<ui:button action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.btnSeleccionarPersonaFisica_action}"
																binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.btnSeleccionarPersonaFisica}" escape="false"
																id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar" />
															<ui:button action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.btnSeleccionarPersonaJuridica_action}"
																binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.btnSeleccionarPersonaJuridica}" escape="false"
																id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersonaSeleccionada" title="Limpiar"
																binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.btnLimpiarPersona}"
																action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.btnLimpiarPersona_action}" styleClass="buttonLimpiarAjax"
																oncomplete="cargarComportamientoJQuery(); focusearTfPersona();" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.label17}" for="tfContador" id="label17"
																styleClass="label" text="Contador" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.tfContador}" columns="40" id="tfContador"
																styleClass="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.paginatedTable.filtro.contador != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.paginatedTable.filtro.contador != null}" />
															<ui:button action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.btnSeleccionarContador_action}"
																binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.btnSeleccionarContador}" id="btnSeleccionarContador"
																mini="true" styleClass="button" text="PF" />
															<ui:button action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.btnSeleccionarPersonaJuridicaContador_action}"
																binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.btnSeleccionarPersonaJuridicaContador}"
																id="btnSeleccionarPersonaJuridicaContador" mini="true" styleClass="button" text="PJ" />
															<a4j:commandButton id="btnLimpiarContador" reRender="form1:tfContador" title="Limpiar"
																binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.btnLimpiarContador}"
																action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.btnLimpiarContador_action}" styleClass="buttonLimpiarAjax"
																oncomplete="cargarComportamientoJQuery(); focusearTfContador();" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.lblCuim}" for="tfCuim" id="lblCuim"
																styleClass="label" text="CUIT/CUIL" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.tfCuim}" id="tfCuim" styleClass="textField"
																columns="13" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.label4}" for="tfNumeroInscripcion" id="label4"
																styleClass="label" text="Nº Inscripción" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.tfNumeroInscripcion}" id="tfNumeroInscripcion"
																styleClass="textField" columns="10" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.lblEstado}" id="lblEstado" style=""
																styleClass="label" text="Estado" />
														</td>
														<td nowrap="nowrap">
															<ui:dropDown binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.ddEstado}" id="ddEstado"
																items="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.ddEstadoDefaultOptions.options}" styleClass="textField" />
														</td>
														<td align="right" nowrap="true">
															<ui:label binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.lblExenciones}" id="lblExenciones" style=""
																styleClass="label" text="Exenciones" />
														</td>
														<td nowrap="nowrap">
															<ui:dropDown binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.ddExenciones}" id="ddExenciones"
																items="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.ddExencionesDefaultOptions.options}" styleClass="textField" />
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
																		<ui:panelGroup binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.panelAtributoDinamico}"
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
															<ui:checkbox binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.btnBuscar}"
												action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.staticText10}" escape="false" id="staticText10"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<a4j:commandButton action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.btnReiniciar_action}"
												binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1 ,form1:stCantidadRegistros, from1:ddEstado"
												oncomplete="cargarComportamientoJQuery();" />
											<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.btnCancelar_action}"
												binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:panelGroup binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.gpSeleccion}" id="gpSeleccion">
											<ui:label binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.label3}" for="ddPlantillaObligacion" id="label3"
												styleClass="label" text="Plantilla de Obligación" />
											<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.staticText5}" escape="false" id="staticText5"
												styleClass="label" text="&amp;nbsp;&amp;nbsp;&amp;nbsp;" />
											<ui:dropDown binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.ddPlantillaObligacion}" id="ddPlantillaObligacion"
												items="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.ddPlantillaObligacionOptions.options}" styleClass="textField" />
										</ui:panelGroup>
									</td>
								</tr>
								<tr>
									<td>
										<ui:table binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.paginatedTable}" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.groupPanel1}" id="groupPanel1"
													separator="&lt;br/&gt;&lt;br/&gt;">
													<ui:panelGroup binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.gpBotones}" id="gpBotones">
														<ui:button action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.btnSeleccionar_action}"
															binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
															text="Seleccionar" />
														<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.stSeparadorSeleccionar}" escape="false"
															id="staticText6" />
														<ui:button action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.btnAgregar_action}"
															binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.btnAgregar}" id="btnAgregar" styleClass="button"
															text="Agregar" />
														<ui:button action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.btnModificar_action}"
															binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.btnModificar}" id="btnModificar" styleClass="button"
															text="Modificar" />
														<ui:button action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.btnEliminar_action}"
															binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.btnEliminar}" id="btnEliminar" styleClass="button"
															text="Eliminar" />
														<ui:button action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.btnReactivar_action}"
															binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.btnReactivar}" id="btnReactivar" styleClass="button"
															text="Reactivar" />
														<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.stSeparadorAccion}" escape="false"
															id="staticText8" />
														<ui:button action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.btnConsultar_action}"
															binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.btnConsultar}" id="btnConsultar" styleClass="button"
															text="Consultar" />
														<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.staticText7}" escape="false" id="staticText7"
															text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.btnImprimirReporte_action}"
															binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.btnImprimirReporte}" id="btnImprimirReporte"
															styleClass="button" text="Visualizar Reporte" disabled="true"
															onClick="newWindow = window.open('ImprimirDocEspSHPS.jsp', '_parent')" />
														<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.staticText12}" escape="false" id="staticText12"
															text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.btnExportar_action}"
															binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.btnExportar}" id="btnExportar" styleClass="button"
															text="Exportar" onClick="return exportarReporte()" />
														<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.paginatedTable.stSeparadorOrdenamiento}"
															id="separador_1" />
														<ui:imageHyperlink binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.paginatedTable.botonOrdenamiento}" />
													</ui:panelGroup>
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:btnSeleccionarPersonaFisica').focus();
					</script>
					<ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{habilitaciones$grpSHPS$ABMDocEspSHPS$AdminDocEspSHPS.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
