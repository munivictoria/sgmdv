<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.page1}" id="page1">
			<ui:html binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.html1}" id="html1">
			<ui:head binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.head1}" id="head1" title="Administración de Personas Jurídicas">
				<ui:link binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					function cargarComportamientoJQuery() {
						mascaraCuimEnTextField("#form1:tfCuit");
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.body1}" focus="form1:tfCuit" id="body1"
				onLoad="parent.footer.location.reload(); Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.head1.title}" />
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
											<ui:panelGroup binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.label3}" id="label3" styleClass="label" text="CUIT" />
														</td>
														<td>
															<ui:textField binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.tfCuit}" id="tfCuit" styleClass="textField"
																maxLength="13" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.label2}" for="tfRazonSocial" id="label2"
																styleClass="label" text="Razón Social" />
														</td>
														<td>
															<ui:textField binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.tfRazonSocial}" columns="40" id="tfRazonSocial"
																styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.lblNombreFantasia}" for="tfNombreFantasia"
																id="lblNombreFantasia" styleClass="label" text="Nombre de Fantasía" />
														</td>
														<td>
															<ui:textField binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.tfNombreFantasia}" columns="40"
																id="tfNombreFantasia" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.label1}" id="label1" styleClass="label" text="Socio" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.tfTitular}" columns="40" disabled="true"
																id="tfTitular" styleClass="textField" />
															<ui:button action="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.btnSeleccionarTitular_action}"
																binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.btnSeleccionarTitular}" escape="false"
																id="btnSeleccionarTitular" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarTitular" reRender="form1:tfTitular" title="Limpiar"
																binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.btnLimpiarTitular}"
																action="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.btnLimpiarTitular_action}" styleClass="buttonLimpiarAjax" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.lblEstado}" id="lblEstado" styleClass="label"
																text="Estado" />
														</td>
														<td>
															<ui:dropDown binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.ddEstado}" id="ddEstado"
																items="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.ddEstadoDefaultOptions.options}" styleClass="textField"
																onChange="deshabilitarBotones(ddId)" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<table border="0" width="600">
																<tr>
																	<td width="40">
																		<ui:label id="lblVacio" styleClass="label" text="" />
																	</td>
																	<td>
																		<ui:panelGroup binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.panelAtributoDinamico}"
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
															<ui:checkbox binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.btnBuscar}"
												action="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.btnReiniciar_action}"
												binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" 
												oncomplete="cargarComportamientoJQuery();document.getElementById('form1:tfCuit').focus();"/>
											<ui:staticText binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.btnCancelar_action}"
												binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.paginatedTable}" id="table1" styleClass="tablaPaginada">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.groupPanel1}" id="groupPanel1">
													<ui:button action="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.btnSeleccionar_action}"
														binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
														text="Seleccionar" />
													<ui:staticText binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.stSeparadorSeleccionar}" id="separador_1"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.btnAgregar_action}"
														binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.btnModificar_action}"
														binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.btnModificar}" id="btnModificar" styleClass="button"
														text="Modificar" />
													<ui:button action="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.btnEliminar_action}"
														binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.btnEliminar}" id="btnEliminar" styleClass="button"
														text="Eliminar" />
													<ui:staticText binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.stSeparadorAccion}" id="separador_2"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.btnConsultar_action}"
														binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.btnConsultar}" id="btnConsultar" styleClass="button"
														text="Consultar" />
													<!-- <ui:staticText text="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.textoSeparador}" id="separador_3" styleClass="barraSeparadoraVertical"/>
                                                <ui:button action="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.btnImprimirReporte_action}"
                                                           binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.btnImprimirReporte}" id="btnImprimirReporte"
                                                           styleClass="button" text="Visualizar Reporte" onClick="newWindow = window.open('ImprimirPersonaJuridica.jsp', '_parent')"/>  -->
													<ui:staticText text="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.textoSeparador}" id="separador_4"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.btnActivarPersona_action}"
														binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.btnActivarPersona}" id="btnActivarPersona" styleClass="button"
														text="Recuperar Persona" />
													<ui:staticText text="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.textoSeparador}" id="separador_5"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.btnExportar_action}"
														binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.btnExportar}" id="btnExportar" styleClass="button"
														text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_6" styleClass="barraSeparadoraVertical" />
													<ui:imageHyperlink binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfCuit').focus();
					</script>
					<ui:hiddenField binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.idSubSesion}" />
					<ui:script binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{framework$ABMPersonaJuridica$AdminPersonaJuridica.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
