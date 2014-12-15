<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.page1}" id="page1">
			<ui:html binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.html1}" id="html1">
			<ui:head binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.head1}" id="head1" title="Administración de Personas Físicas">
				<ui:link binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					function cargarComportamientoJQuery() {
						mascaraCuimEnTextField("#form1:tfCuil");
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.body1}" focus="form1:tfCuil" id="body1"
				onLoad="parent.footer.location.reload(); Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.form1}" id="form1"
					virtualFormsConfig="vfCancelar | hidIdSubSesion hidIdPagina | btnCancelar" autoComplete="off">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{framework$ABMPersonaFisica$AdminPersonaFisica.head1.title}" />
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
											<ui:panelGroup binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.label1}" for="tfCuil" id="label1" styleClass="label"
																text="CUIL" />
														</td>
														<td>
															<ui:textField binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.tfCuil}" id="tfCuil" styleClass="textField"
																maxLength="13" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.lblNroDocumento}" for="tfNroDocumento"
																id="lblNroDocumento" styleClass="label" text="Documento" />
														</td>
														<td>
															<ui:dropDown binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.ddTipoDocumento}" id="ddTipoDocumento"
																items="#{framework$ABMPersonaFisica$AdminPersonaFisica.ddTipoDocumentoDefaultOptions.options}" styleClass="textField" />
															<ui:textField binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.tfNroDocumento}" id="tfNroDocumento"
																onKeyPress="return ValidarNum(event,this)" maxLength="8" styleClass="textField">
															</ui:textField>
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.label3}" for="tfApellido" id="label3" styleClass="label"
																text="Apellido" />
														</td>
														<td>
															<ui:textField binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.tfApellido}" columns="40" id="tfApellido"
																styleClass="textField" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.label4}" for="tfNombre" id="label4" styleClass="label"
																text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.tfNombre}" columns="40" id="tfNombre"
																styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.lblEstado}" id="lblEstado" styleClass="label"
																text="Estado" />
														</td>
														<td>
															<ui:dropDown binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.ddEstado}" id="ddEstado"
																items="#{framework$ABMPersonaFisica$AdminPersonaFisica.ddEstadoDefaultOptions.options}" styleClass="textField"
																onChange="deshabilitarBotones(ddId)" />
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
																		<ui:panelGroup binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.panelAtributoDinamico}" id="panelAtributoDinamico">
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
															<ui:checkbox binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.btnBuscar}"
												action="#{framework$ABMPersonaFisica$AdminPersonaFisica.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{framework$ABMPersonaFisica$AdminPersonaFisica.btnReiniciar_action}"
												binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.btnReiniciar}" 
												id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" 
												oncomplete="cargarComportamientoJQuery();document.getElementById('form1:tfCuil').focus();"/>
											<ui:staticText binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{framework$ABMPersonaFisica$AdminPersonaFisica.btnCancelar_action}"
												binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.paginatedTable}" styleClass="tablaPaginada" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.groupPanel1}" id="groupPanel1">
													<ui:button action="#{framework$ABMPersonaFisica$AdminPersonaFisica.btnSeleccionar_action}"
														binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
														text="Seleccionar" />
													<ui:staticText binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.stSeparadorSeleccionar}" id="separador_1"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMPersonaFisica$AdminPersonaFisica.btnAgregar_action}"
														binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{framework$ABMPersonaFisica$AdminPersonaFisica.btnModificar_action}"
														binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.btnModificar}" id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{framework$ABMPersonaFisica$AdminPersonaFisica.btnEliminar_action}"
														binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.stSeparadorAccion}" id="separador_2"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMPersonaFisica$AdminPersonaFisica.btnConsultar_action}"
														binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.btnConsultar}" id="btnConsultar" styleClass="button" text="Consultar" />
													<!--   <ui:staticText text="#{framework$ABMPersonaFisica$AdminPersonaFisica.textoSeparador}" id="separador_3" styleClass="barraSeparadoraVertical"/>
                                                <ui:button action="#{framework$ABMPersonaFisica$AdminPersonaFisica.btnImprimir_action}"
                                                           binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.btnImprimir}" id="btnImprimir"
                                                           onClick="newWindow = window.open('/Vipians/faces/ImpresionServlet', 'Reporte')"
                                                           styleClass="button" text="Imprimir"/> -->
													<ui:staticText text="#{framework$ABMPersonaFisica$AdminPersonaFisica.textoSeparador}" id="separador_4"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMPersonaFisica$AdminPersonaFisica.btnActivar_action}"
														binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.btnActivar}" id="btnActivarPersona" styleClass="button"
														text="Recuperar Persona" />
													<ui:staticText text="#{framework$ABMPersonaFisica$AdminPersonaFisica.textoSeparador}" />
													<ui:button action="#{framework$ABMPersonaFisica$AdminPersonaFisica.btnExportar_action}"
														binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
														onClick="return exportarReporte()" />
													<ui:staticText binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_5" styleClass="barraSeparadoraVertical" />
													<ui:imageHyperlink binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfCuil').focus();
					</script>
					<ui:hiddenField binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMPersonaFisica$AdminPersonaFisica.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMPersonaFisica$AdminPersonaFisica.idSubSesion}" />
					<ui:script binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
					<ui:script binding="#{framework$ABMPersonaFisica$AdminPersonaFisica.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
