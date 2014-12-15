<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMProveedor$AdminProveedor.page1}" id="page1">
			<ui:html binding="#{compras$ABMProveedor$AdminProveedor.html1}" id="html1">
			<ui:head binding="#{compras$ABMProveedor$AdminProveedor.head1}" id="head1" title="Administración de Proveedores">
				<ui:link binding="#{compras$ABMProveedor$AdminProveedor.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "compras$ABMProveedor$AdminProveedor";

					function cargarComportamientoJQuery() {
						autoCompletarEnTextField("#form1:tfPersonaSeleccionada", "persona", nombreBean, "setPersonaAutocompletar");
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
			<ui:body binding="#{compras$ABMProveedor$AdminProveedor.body1}" focus="form1:tfRazonSocial" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMProveedor$AdminProveedor.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{compras$ABMProveedor$AdminProveedor.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{compras$ABMProveedor$AdminProveedor.head1.title}" />
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
											<ui:panelGroup binding="#{compras$ABMProveedor$AdminProveedor.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMProveedor$AdminProveedor.lblRazonSocial}" for="tfRazonSocial" id="lblRazonSocial"
																styleClass="label" text="Razón social" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{compras$ABMProveedor$AdminProveedor.tfRazonSocial}" columns="40" id="tfRazonSocial"
																styleClass="textField" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMProveedor$AdminProveedor.lblPersona}" for="tfPersona" id="lblPersona" styleClass="label"
																text="Persona" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{compras$ABMProveedor$AdminProveedor.tfPersonaSeleccionada}" columns="40" id="tfPersonaSeleccionada"
																styleClass="#{compras$ABMProveedor$AdminProveedor.paginatedTable.filtro.persona != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{compras$ABMProveedor$AdminProveedor.paginatedTable.filtro.persona != null}" />
															<ui:button action="#{compras$ABMProveedor$AdminProveedor.btnSeleccionarPersonaJuridica_action}"
																binding="#{compras$ABMProveedor$AdminProveedor.btnSeleccionarPersonaJuridica}" escape="false"
																id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar Persona Juridica" />
															<ui:button action="#{compras$ABMProveedor$AdminProveedor.btnSeleccionarPersonaFisica_action}"
																binding="#{compras$ABMProveedor$AdminProveedor.btnSeleccionarPersonaFisica}" escape="false" id="btnSeleccionarPersonaFisica"
																mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Física" />
															<a4j:commandButton title="Limpiar" id="btnLimpiarPersona" reRender="form1:tfPersonaSeleccionada"
																binding="#{compras$ABMProveedor$AdminProveedor.btnLimpiarPersona}"
																action="#{compras$ABMProveedor$AdminProveedor.btnLimpiarPersona_action}" styleClass="buttonLimpiarAjax"
																oncomplete="cargarComportamientoJQuery(); focusearTfPersonaSeleccionada();" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMProveedor$AdminProveedor.lblCodigo}" for="tfCodigo" id="lblCodigo" styleClass="label"
																text="Código" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{compras$ABMProveedor$AdminProveedor.tfCodigo}" columns="40" id="tfCodigo" styleClass="textField" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMProveedor$AdminProveedor.lblCodigoCiiu}" id="lblCodigoCiiu" styleClass="label"
																text="Códigos CIIU" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{compras$ABMProveedor$AdminProveedor.tfCodigoCiiu}" columns="40" disabled="true" id="tfCodigoCiiu"
																styleClass="textField" />
															<ui:button action="#{compras$ABMProveedor$AdminProveedor.btnSeleccionarCodigoCiiu_action}"
																binding="#{compras$ABMProveedor$AdminProveedor.btnSeleccionarCodigoCiiu}" escape="false" id="btnSeleccionarCodigoCiiu"
																mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" />
															<a4j:commandButton id="btnLimpiarCodigoCiiu" reRender="form1:tfCodigoCiiu" title="Limpiar"
																binding="#{compras$ABMProveedor$AdminProveedor.btnLimpiarCodigoCiiu}"
																action="#{compras$ABMProveedor$AdminProveedor.btnLimpiarCodigoCiiu_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMProveedor$AdminProveedor.lblTipo}" id="lblTipo" styleClass="label" text="Tipo" />
														</td>
														<td nowrap="nowrap">
															<ui:dropDown binding="#{compras$ABMProveedor$AdminProveedor.ddTipo}" id="ddTipo"
																items="#{compras$ABMProveedor$AdminProveedor.ddTipoDefaultOptions.options}" styleClass="textField" />
														</td>
														<td align="right" nowrap="true">
															<ui:label binding="#{compras$ABMProveedor$AdminProveedor.lblTipoBien}" id="lblTipoBien" styleClass="label"
																text="Categoría Bien" />
														</td>
														<td>
															<ui:dropDown binding="#{compras$ABMProveedor$AdminProveedor.ddTipoBien}" id="ddTipoBien"
																items="#{compras$ABMProveedor$AdminProveedor.ddTipoBienDefaultOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMProveedor$AdminProveedor.lblEstado}" id="lblEstado" styleClass="label" text="Estado" />
														</td>
														<td nowrap="nowrap">
															<ui:dropDown binding="#{compras$ABMProveedor$AdminProveedor.ddEstadoProveedor}" id="ddEstadoProveedor"
																items="#{compras$ABMProveedor$AdminProveedor.ddEstadoProveedorDefaultOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<td colspan="4">
														<table border="0" width="600">
															<tr>
																<td>
																	<ui:label id="lblVacio" styleClass="label" text="" />
																</td>
																<td>
																	<ui:panelGroup binding="#{compras$ABMProveedor$AdminProveedor.panelAtributoDinamico}" id="panelAtributoDinamico">
																		<!-- AQUI VA LO QUE SE CREA DINAMICAMENTE -->
																	</ui:panelGroup>
																</td>
															</tr>
														</table>
													</td>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{compras$ABMProveedor$AdminProveedor.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{compras$ABMProveedor$AdminProveedor.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
														</td>
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
								</tbody>
								<tfoot>
									<tr>
										<td align="right" colspan="4">
											<a4j:commandButton binding="#{compras$ABMProveedor$AdminProveedor.btnBuscar}"
												action="#{compras$ABMProveedor$AdminProveedor.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{compras$ABMProveedor$AdminProveedor.btnReiniciar_action}"
												binding="#{compras$ABMProveedor$AdminProveedor.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" oncomplete="cargarComportamientoJQuery();" />
											<ui:staticText binding="#{compras$ABMProveedor$AdminProveedor.stSeparador1}" escape="false" id="stSeparador1"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{compras$ABMProveedor$AdminProveedor.btnCancelar_action}"
												binding="#{compras$ABMProveedor$AdminProveedor.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{compras$ABMProveedor$AdminProveedor.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td style="height: 103px">
										<ui:table binding="#{compras$ABMProveedor$AdminProveedor.paginatedTable}" styleClass="tablaPaginada" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{compras$ABMProveedor$AdminProveedor.groupPanel1}" id="groupPanel1">
													<ui:button action="#{compras$ABMProveedor$AdminProveedor.btnSeleccionar_action}"
														binding="#{compras$ABMProveedor$AdminProveedor.btnSeleccionar}" id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{compras$ABMProveedor$AdminProveedor.stSeparadorSeleccionar}" escape="false" id="stSeparador2" />
													<ui:button action="#{compras$ABMProveedor$AdminProveedor.btnAgregar_action}"
														binding="#{compras$ABMProveedor$AdminProveedor.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{compras$ABMProveedor$AdminProveedor.btnModificar_action}"
														binding="#{compras$ABMProveedor$AdminProveedor.btnModificar}" id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{compras$ABMProveedor$AdminProveedor.btnEliminar_action}"
														binding="#{compras$ABMProveedor$AdminProveedor.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{compras$ABMProveedor$AdminProveedor.stSeparadorAccion}" escape="false" id="stSeparador3" />
													<ui:button action="#{compras$ABMProveedor$AdminProveedor.btnConsultar_action}"
														binding="#{compras$ABMProveedor$AdminProveedor.btnConsultar}" id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:staticText text="#{compras$ABMProveedor$AdminProveedor.textoSeparador}" escape="false" id="stSeparador4" />
													<ui:button action="#{compras$ABMProveedor$AdminProveedor.btnActivar_action}"
														binding="#{compras$ABMProveedor$AdminProveedor.btnActivar}" id="btnActivar" styleClass="button" text="Recuperar Proveedor" />
													<ui:staticText text="#{compras$ABMProveedor$AdminProveedor.textoSeparador}" escape="false" id="stSeparador5" />
													<ui:button action="#{compras$ABMProveedor$AdminProveedor.btnImprimirReporte_action}"
														binding="#{compras$ABMProveedor$AdminProveedor.btnImprimirReporte}"
														onClick="newWindow = window.open('/Vipians/faces/ImpresionServlet', 'Reporte')" id="btnImprimirReporte" styleClass="button"
														text="Visualizar Reporte" disabled="true" />
													<ui:staticText text="#{compras$ABMProveedor$AdminProveedor.textoSeparador}" escape="false" id="stSeparador6" />
													<ui:button action="#{compras$ABMProveedor$AdminProveedor.btnExportar_action}"
														binding="#{compras$ABMProveedor$AdminProveedor.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
														onClick="return exportarReporte()" />
													<ui:staticText binding="#{compras$ABMProveedor$AdminProveedor.paginatedTable.stSeparadorOrdenamiento}" id="separador_1" />
													<ui:imageHyperlink binding="#{compras$ABMProveedor$AdminProveedor.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{compras$ABMProveedor$AdminProveedor.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{compras$ABMProveedor$AdminProveedor.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfRazonSocial').focus();
					</script>
					<ui:hiddenField binding="#{compras$ABMProveedor$AdminProveedor.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMProveedor$AdminProveedor.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMProveedor$AdminProveedor.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMProveedor$AdminProveedor.idSubSesion}" />
					<ui:script binding="#{compras$ABMProveedor$AdminProveedor.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
